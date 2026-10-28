import sys
import unittest
import struct
from pathlib import Path

sys.path.insert(0, str(Path(__file__).resolve().parents[1]))
from read_capture import decode, svg, Reader

FIXTURE = None


class CaptureTests(unittest.TestCase):
    @classmethod
    def setUpClass(cls):
        cls.data = Path(FIXTURE).read_bytes()

    def test_java_export_roundtrip_preserves_modified_utf_and_topology(self):
        result = decode(self.data)
        self.assertEqual(result["maps"][0]["name"], "Valley é\x00😀")
        self.assertEqual(result["maps"][0]["spawn_pixels"], [48, 72])
        self.assertEqual(result["maps"][0]["displayed_area"], 6)
        self.assertEqual(result["edges"][0]["destination_map"], 9)
        self.assertEqual(result["capture_diagnostics"], [{"dropped_map_packets": 2, "rejected_map_packets": 1}])
        self.assertEqual(result["schema"], 2)

    def test_npc_world_is_not_joined_to_walking_components(self):
        result = decode(self.data)
        self.assertEqual(result["external_links"][0]["kind"], "npc")
        self.assertFalse(result["external_links"][0]["auto_walk_allowed"])
        self.assertEqual(result["external_links"][0]["user_entered_minimum_level"], 101)
        self.assertEqual(result["blocked_exits"][0]["attempted_level"], 104)
        self.assertIsNone(result["blocked_exits"][0]["required_level"])
        regions = [set(node["map"] for node in component) for component in result["walking_components"]]
        self.assertIn({99}, regions)
        self.assertFalse(any(8 in region and 99 in region for region in regions))

    def test_legacy_v1_map_and_edge_records_remain_readable(self):
        source = Reader(self.data)
        source.read(4); source.number("H"); source.utf(); count = source.number("i")
        records = []
        for _ in range(count):
            record = source.read(source.number("i"))
            if record[0] in (1, 2, 3):
                records.append(struct.pack(">i", len(record)) + record)
        legacy = self.data[:4] + struct.pack(">H", 1) + self.data[6:72] + struct.pack(">i", len(records)) + b"".join(records)
        result = decode(legacy)
        self.assertEqual(result["schema"], 1)
        self.assertEqual(len(result["maps"]), 2)
        self.assertEqual(result["external_links"], [])

    def test_truncation_rejected(self):
        with self.assertRaisesRegex(ValueError, "Truncated"):
            decode(self.data[:-1])

    def test_unknown_record_kind_rejected(self):
        data = bytearray(self.data)
        data[80] = 255  # header(76) + first record length(4)
        with self.assertRaisesRegex(ValueError, "Unknown record"):
            decode(bytes(data))

    def test_extra_bytes_rejected(self):
        with self.assertRaisesRegex(ValueError, "tail"):
            decode(self.data + b"unexpected")

    def test_svg_escapes_recorded_labels(self):
        item = decode(self.data)["maps"][0]
        item["exits"][0]["name"] = "<script>bad</script>"
        document = svg(item)
        self.assertNotIn("<script>", document)
        self.assertIn("&lt;script&gt;", document)


if __name__ == "__main__":
    FIXTURE = sys.argv.pop(1)
    unittest.main(verbosity=2)
