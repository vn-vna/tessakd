"""Inventory/extract the reference JAR, optionally decompile with a supplied CFR JAR.

Usage: python tools/prepare.py [--decompiler /path/to/cfr-0.152.jar]
Only the decompiler is executed; the game is treated as archive/bytecode data.
"""

import argparse
from collections import Counter
import csv
import hashlib
import json
from pathlib import Path, PurePosixPath
import subprocess
import zipfile


def verify(root, jar, output):
    metadata = json.loads((output / "artifact.json").read_text(encoding="utf-8"))
    assert hashlib.sha256(jar.read_bytes()).hexdigest() == metadata["sha256"], "JAR changed"
    with (output / "inventory.csv").open(newline="", encoding="utf-8") as handle:
        rows = list(csv.DictReader(handle))
    versions = Counter()
    for row in rows:
        data = (output / "extracted" / row["path"]).read_bytes()
        assert hashlib.sha256(data).hexdigest() == row["sha256"], row["path"]
        if row["path"].endswith(".class"):
            assert data[:4] == b"\xca\xfe\xba\xbe", row["path"]
            versions[str(int.from_bytes(data[6:8], "big"))] += 1
    sources = list((output / "decompiled").rglob("*.java"))
    warnings = []
    for source in sources:
        for number, line in enumerate(source.read_text(encoding="utf-8").splitlines(), 1):
            if any(marker in line for marker in ("WARNING", "Could not decompile", "Decompilation failed")):
                warnings.append({"path": source.relative_to(output).as_posix(),
                                 "line": number, "text": line.strip()})
    bytecode = output / "bytecode"
    bytecode.mkdir(exist_ok=True)
    selected = ["com.silverknight.TemMidlet", "com.silverknight.TemCanvas",
                "GameScreen.LogoScreen", "CLib.Session_ME", "CLib.Session_ME$MessageCollector",
                "Main.GameCanvas", "GameScreen.GameScreen", "GameScreen.LoadMapScreen",
                "GameObjects.Player", "GameObjects.MainObject", "Thread_More.LoadMap",
                "Skill.ListSkill", "netcommand.global.GlobalService",
                "netcommand.global.ReadMessenge", "GameObjects.Item",
                "GameObjects.MainItem", "InterfaceComponents.TabShopNew",
                "GameObjects.MainMonster", "GameObjects.Monstervantieu"]
    for name in selected:
        result = subprocess.run(["javap", "-classpath", str(jar), "-c", "-p", name],
                                capture_output=True, text=True, check=True)
        (bytecode / (name + ".txt")).write_text(result.stdout, encoding="utf-8")
    report = {"original_sha256_matches": True, "extracted_files_verified": len(rows),
              "class_major_versions": dict(versions), "java_source_files": len(sources),
              "decompiler_warnings": warnings, "bytecode_classes_exported": selected}
    (output / "verification.json").write_text(json.dumps(report, indent=2) + "\n", encoding="utf-8")
    print(json.dumps(report, indent=2))


def main():
    parser = argparse.ArgumentParser(description=__doc__)
    parser.add_argument("--decompiler", type=Path)
    parser.add_argument("--verify-only", action="store_true")
    args = parser.parse_args()
    root = Path(__file__).resolve().parents[1]
    jar = root / "KnightOnline_v403.jar"
    output = root / "analysis"
    if args.verify_only:
        verify(root, jar, output)
        return
    extracted = output / "extracted"
    output.mkdir(exist_ok=True)
    # Refuse reuse to avoid mixing an earlier extraction with new archive contents.
    if extracted.exists():
        raise SystemExit(f"Extraction already exists: {extracted}")
    rows = []
    with zipfile.ZipFile(jar) as archive:
        entries = archive.infolist()
        destinations = set()
        for entry in entries:
            relative = PurePosixPath(entry.filename)
            if (relative.is_absolute() or ".." in relative.parts
                    or "\\" in entry.filename or ":" in entry.filename
                    or (entry.external_attr >> 16) & 0o170000 == 0o120000):
                raise SystemExit(f"Unsafe archive path: {entry.filename}")
            destination = (extracted / Path(*relative.parts)).resolve()
            if not destination.is_relative_to(extracted.resolve()):
                raise SystemExit(f"Escaping archive path: {entry.filename}")
            key = str(destination).casefold()
            if key in destinations:
                raise SystemExit(f"Duplicate/case-colliding archive path: {entry.filename}")
            destinations.add(key)
        for entry in entries:
            destination = extracted / Path(*PurePosixPath(entry.filename).parts)
            if entry.is_dir():
                destination.mkdir(parents=True, exist_ok=True)
                continue
            data = archive.read(entry)
            destination.parent.mkdir(parents=True, exist_ok=True)
            destination.write_bytes(data)
            rows.append({"path": entry.filename, "bytes": len(data),
                         "compressed_bytes": entry.compress_size,
                         "crc32": f"{entry.CRC:08x}",
                         "sha256": hashlib.sha256(data).hexdigest()})
        manifest = archive.read("META-INF/MANIFEST.MF").decode("utf-8")
    with (output / "inventory.csv").open("w", newline="", encoding="utf-8") as handle:
        writer = csv.DictWriter(handle, fieldnames=list(rows[0]))
        writer.writeheader()
        writer.writerows(rows)
    classes = sum(row["path"].endswith(".class") for row in rows)
    metadata = {"file": jar.name, "bytes": jar.stat().st_size,
                "sha256": hashlib.sha256(jar.read_bytes()).hexdigest(),
                "files": len(rows), "directories": len(entries) - len(rows),
                "classes": classes, "other_files": len(rows) - classes,
                "manifest": manifest}
    (output / "artifact.json").write_text(
        json.dumps(metadata, indent=2) + "\n", encoding="utf-8")
    print(json.dumps(metadata, indent=2))
    if args.decompiler:
        cfr = args.decompiler.resolve(strict=True)
        metadata["decompiler"] = {"name": "CFR", "version": "0.152",
                                  "sha256": hashlib.sha256(cfr.read_bytes()).hexdigest()}
        command = ["java", "-Xmx2g", "-jar", str(cfr), str(jar),
                   "--outputdir", str(output / "decompiled"), "--silent", "true"]
        metadata["decompiler"]["command"] = command
        with (output / "decompile.log").open("w", encoding="utf-8") as log:
            result = subprocess.run(command, stdout=log, stderr=subprocess.STDOUT, check=False)
        metadata["decompiler"]["exit_code"] = result.returncode
        (output / "artifact.json").write_text(
            json.dumps(metadata, indent=2) + "\n", encoding="utf-8")
        print(f"CFR exit code: {result.returncode}; see analysis/decompile.log")
        result.check_returncode()
    verify(root, jar, output)


if __name__ == "__main__":
    main()
