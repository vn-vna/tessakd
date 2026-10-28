"""Validate a phone .knav export, optionally write JSON and SVGs to a new folder.

python mod/read_capture.py file.knav [--output new-folder]
No application/game code or captured strings are executed.
"""
import argparse
import base64
import hashlib
import html
import io
import json
from pathlib import Path
import struct


class Reader:
    def __init__(self, data):
        self.stream = io.BytesIO(data)

    def read(self, count):
        if count < 0 or count > 1024 * 1024:
            raise ValueError("Invalid byte count")
        data = self.stream.read(count)
        if len(data) != count:
            raise ValueError("Truncated capture")
        return data

    def number(self, fmt):
        return struct.unpack(">" + fmt, self.read(struct.calcsize(fmt)))[0]

    def utf(self):
        raw = self.read(self.number("H"))
        text = raw.replace(b"\xc0\x80", b"\x00").decode("utf-8", "surrogatepass")
        return text.encode("utf-16-le", "surrogatepass").decode("utf-16-le")

    def done(self):
        return self.stream.tell() == len(self.stream.getvalue())


def parse_map(data):
    source = Reader(data)
    map_id = source.number("h")
    spawn = [source.number("h") * 24, source.number("h") * 24]
    mini = source.number("h")
    name = source.utf()
    terrain = source.read(source.number("h"))
    if len(terrain) < 3:
        raise ValueError("Missing terrain")
    width, height, tile_set = terrain[:3]
    if not 0 < width < 128 or not 0 < height < 128 or len(terrain) < 3 + width * height:
        raise ValueError("Invalid terrain dimensions")
    if source.number("b") >= 0:
        source.number("h")
    source.read(source.number("h"))
    count = source.number("b")
    if not 0 <= count <= 32:
        raise ValueError("Invalid exit count")
    exits = []
    for _ in range(count):
        x, y, label = source.number("h"), source.number("h"), source.utf()
        if not 0 <= x <= width * 24 or not 0 <= y <= height * 24:
            raise ValueError("Exit outside terrain")
        exits.append({"x": x, "y": y, "name": label})
    teleport, area, map_type = source.number("b"), source.number("b"), source.number("b")
    if map_id < 0 or area < 0:
        raise ValueError("Invalid map identity/area")
    return {"id": map_id, "name": name, "area_zero_based": area, "displayed_area": area + 1,
            "width": width, "height": height, "tile_size": 24, "tile_set": tile_set,
            "spawn_pixels": spawn, "minimap_id": mini, "type": map_type,
            "teleport_flag": teleport, "exits": exits, "tiles": list(terrain[3:3 + width * height]),
            "raw_packet_sha256": hashlib.sha256(data).hexdigest(),
            "raw_packet_base64": base64.b64encode(data).decode("ascii")}


def decode(data):
    if len(data) > 4 * 1024 * 1024:
        raise ValueError("Capture exceeds 4 MiB reader limit")
    source = Reader(data)
    if source.read(4) != b"KNAV":
        raise ValueError("Unsupported KNAV format")
    version = source.number("H")
    if version not in (1, 2):
        raise ValueError("Unsupported KNAV version")
    profile = source.utf()
    if len(profile) != 64 or any(c not in "0123456789abcdef" for c in profile):
        raise ValueError("Invalid profile digest")
    count = source.number("i")
    if not 0 <= count <= 2048:
        raise ValueError("Invalid record count")
    maps, edges, diagnostics, external_links, blocked_exits = [], [], [], [], []
    for _ in range(count):
        length = source.number("i")
        if not 0 < length <= 98304:
            raise ValueError("Invalid record length")
        record = Reader(source.read(length))
        kind, server = record.number("B"), record.utf()
        map_id, area = record.number("i"), record.number("i")
        if version == 1 and kind not in (1, 2, 3):
            raise ValueError("Record requires KNAV version 2")
        if kind == 1:
            observed = record.number("q")
            payload = record.read(record.number("i"))
            item = parse_map(payload)
            if (item["id"], item["area_zero_based"]) != (map_id, area):
                raise ValueError("Map identity mismatch")
            cells = record.number("i")
            if cells != item["width"] * item["height"]:
                raise ValueError("Collision grid size mismatch")
            item.update({"server": server, "observed_ms": observed,
                         "collision_observed": [x if x < 128 else x - 256 for x in record.read(cells)],
                         "collision_authority": "live-client-snapshot-may-include-dynamic-blockers"})
            maps.append(item)
        elif kind == 2:
            edge = {"server": server, "source_map": map_id, "source_area_zero_based": area,
                    "exit_index": record.number("i"), "exit_x": record.number("i"),
                    "exit_y": record.number("i"), "exit_name": record.utf(),
                    "destination_map": record.number("i"), "destination_area_zero_based": record.number("i"),
                    "arrival_x": record.number("i"), "arrival_y": record.number("i"),
                    "observed_ms": record.number("q"),
                    "evidence": "map-change-correlated-with-pending-exit-navigation"}
            edges.append(edge)
        elif kind == 3:
            dropped, rejected = record.number("i"), record.number("i")
            if dropped < 0 or rejected < 0:
                raise ValueError("Invalid capture diagnostics")
            diagnostics.append({"dropped_map_packets": dropped, "rejected_map_packets": rejected})
        elif kind == 4:
            link = {"server": server, "source_map": map_id, "source_area_zero_based": area,
                    "destination_map": record.number("i"), "destination_area_zero_based": record.number("i"),
                    "kind": record.utf(), "note": record.utf(), "observed_player_level": record.number("i"),
                    "user_entered_minimum_level": record.number("i"), "observed_ms": record.number("q"),
                    "auto_walk_allowed": False, "minimum_level_authority": "user-entered-not-server-verified"}
            if link["kind"] not in ("npc", "unclassified"):
                raise ValueError("Unknown external transition kind")
            external_links.append(link)
        elif kind == 5:
            blocked_exits.append({"server": server, "map": map_id, "area_zero_based": area,
                                  "exit_x": record.number("i"), "exit_y": record.number("i"),
                                  "exit_name": record.utf(), "character": record.utf(),
                                  "attempted_level": record.number("i"), "reason": record.utf(),
                                  "observed_ms": record.number("q"), "required_level": None})
        else:
            raise ValueError("Unknown record type")
        if not record.done():
            raise ValueError("Unexpected record tail")
    if not source.done():
        raise ValueError("Unexpected capture tail")
    components = walk_components(maps, edges)
    return {"schema": version, "input_capture_sha256": hashlib.sha256(data).hexdigest(),
            "game_profile_sha256": profile, "maps": maps, "edges": edges,
            "capture_diagnostics": diagnostics, "external_links": external_links,
            "blocked_exits": blocked_exits, "walking_components": components,
            "walking_components_meaning": "weak-components-of-observed-walking-links-not-mutual-reachability-or-proven-game-dimensions"}


def walk_components(maps, edges):
    """NPC/unclassified links never join the observed walking regions."""
    adjacency = {}
    def node(server, map_id, area):
        key = (server, map_id, area)
        adjacency.setdefault(key, set())
        return key
    for item in maps:
        node(item["server"], item["id"], item["area_zero_based"])
    for edge in edges:
        a = node(edge["server"], edge["source_map"], edge["source_area_zero_based"])
        b = node(edge["server"], edge["destination_map"], edge["destination_area_zero_based"])
        adjacency[a].add(b)
        adjacency[b].add(a)
    components, seen = [], set()
    for start in sorted(adjacency):
        if start in seen:
            continue
        queue, nodes = [start], []
        seen.add(start)
        while queue:
            current = queue.pop()
            nodes.append({"server": current[0], "map": current[1], "area_zero_based": current[2]})
            for other in sorted(adjacency[current]):
                if other not in seen:
                    seen.add(other)
                    queue.append(other)
        components.append(nodes)
    return components


def svg(item):
    width, height = item["width"], item["height"]
    result = [f'<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 {width * 8} {height * 8}">',
              '<rect width="100%" height="100%" fill="#b69c5c"/>']
    palette = {0: "#e8e6d9", 1: "#283743", -1: "#283743", 2: "#60accb"}
    for index, value in enumerate(item["collision_observed"]):
        x, y = index % width * 8, index // width * 8
        result.append(f'<rect x="{x}" y="{y}" width="8" height="8" fill="{palette.get(value, "#b69c5c")}"/>')
    for exit in item["exits"]:
        result.append(f'<circle cx="{exit["x"] / 3:g}" cy="{exit["y"] / 3:g}" r="3" fill="#f14d56"><title>{html.escape(exit["name"])}</title></circle>')
    result.append("</svg>")
    return "\n".join(result)


def main():
    parser = argparse.ArgumentParser(description=__doc__)
    parser.add_argument("capture", type=Path)
    parser.add_argument("--output", type=Path)
    args = parser.parse_args()
    if args.capture.stat().st_size > 4 * 1024 * 1024:
        parser.error("Capture exceeds 4 MiB")
    result = decode(args.capture.read_bytes())
    if args.output:
        if args.output.exists() or not args.output.parent.is_dir():
            parser.error("Output folder must be absent and its parent must exist")
        args.output.mkdir()
        (args.output / "maps.json").write_text(json.dumps(result, indent=2, ensure_ascii=True) + "\n", encoding="utf-8")
        for item in result["maps"]:
            server = hashlib.sha256(item["server"].encode()).hexdigest()[:8]
            name = f"map-{server}-{item['id']}-area-{item['area_zero_based']}.svg"
            (args.output / name).write_text(svg(item), encoding="utf-8")
    print(json.dumps({"schema": result["schema"], "maps": len(result["maps"]), "edges": len(result["edges"]),
                      "external_links": len(result["external_links"]), "blocked_exits": len(result["blocked_exits"]),
                      "walking_regions": len(result["walking_components"]),
                      "capture_sha256": result["input_capture_sha256"]}, indent=2))


if __name__ == "__main__":
    main()
