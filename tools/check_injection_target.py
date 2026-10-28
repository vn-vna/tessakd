"""Inspect a candidate KnightOnline JAR; this tool never patches or runs the game.

Usage: python tools/check_injection_target.py input.jar [--report new-report.json]
Requires javap from a JDK. Only the exact analyzed artifact is recognized today.
"""

import argparse
from collections import Counter
import hashlib
import io
import json
from pathlib import Path, PurePosixPath
import re
import subprocess
import zipfile


REFERENCE_SHA256 = "f023b34e0ba0a3dcf44249ed5f90b78f0427deee4668562b8e3a0e9e349b18c6"
REQUIRED = {
    "com.silverknight.TemMidlet": {
        ("com.silverknight.TemMidlet", "()V"), ("pauseApp", "()V"),
        ("startApp", "()V"), ("destroyApp", "(Z)V"),
    },
    "com.silverknight.TemCanvas": {
        ("run", "()V"), ("pointerPressed", "(II)V"),
    },
    "Main.GameCanvas": {("update", "()V"), ("paint", "(LCLib/TemGraphics;)V")},
    "CLib.Session_ME$MessageCollector": {("readMessage", "()Lnet/Message;")},
    "Thread_More.LoadMap": {("loadmap", "([B)V")},
    "netcommand.global.ReadMessenge": {
        ("changeMap", "(Lnet/Message;)V"), ("dieMonster", "(Lnet/Message;)V"),
        ("ItemDrop", "(Lnet/Message;)V"), ("GetItemMap", "(Lnet/Message;)V"),
        ("Fire_Object", "(Lnet/Message;BB)V"),
    },
    "netcommand.global.GlobalService": {
        ("buy_item", "(BSS)V"), ("Get_Item_Map", "(SB)V"),
        ("Use_Potion", "(S)V"), ("Dynamic_Menu", "(SBB)V"),
    },
    "GameObjects.Monstervantieu": {
        ("isMonstervantieu", "()Z"), ("getnameOwner", "()Ljava/lang/String;"),
    },
}


def parse_manifest(data):
    """Unfold main-section manifest attributes, including continuation lines."""
    attributes = {}
    current = None
    for line in data.decode("utf-8").splitlines():
        if not line:
            break
        if line.startswith(" "):
            if current is None:
                raise ValueError("Manifest continuation has no preceding attribute")
            attributes[current] += line[1:]
        elif ": " in line:
            current, value = line.split(": ", 1)
            if current in attributes:
                raise ValueError(f"Duplicate manifest attribute: {current}")
            attributes[current] = value
        else:
            raise ValueError("Malformed main manifest attribute")
    return attributes


def inspect_archive(data):
    """Read metadata without extracting entries or executing class initializers."""
    with zipfile.ZipFile(io.BytesIO(data)) as archive:
        entries = archive.infolist()
        if len(entries) > 10000:
            raise ValueError("Archive exceeds the preflight's 10000-entry limit")
        names = set()
        versions = Counter()
        signatures = []
        for entry in entries:
            path = PurePosixPath(entry.filename)
            if (path.is_absolute() or ".." in path.parts
                    or "\\" in entry.filename or ":" in entry.filename):
                raise ValueError(f"Unsafe archive path: {entry.filename}")
            key = str(path).casefold()
            if key in names:
                raise ValueError(f"Duplicate/case-colliding archive path: {entry.filename}")
            names.add(key)
            if entry.filename.endswith(".class"):
                with archive.open(entry) as handle:
                    header = handle.read(8)
                if len(header) != 8 or header[:4] != b"\xca\xfe\xba\xbe":
                    raise ValueError(f"Invalid class header: {entry.filename}")
                versions[str(int.from_bytes(header[6:8], "big"))] += 1
            if re.fullmatch(r"META-INF/[^/]+\.(SF|RSA|DSA|EC)", entry.filename, re.I):
                signatures.append(entry.filename)
        if archive.getinfo("META-INF/MANIFEST.MF").file_size > 65536:
            raise ValueError("Manifest exceeds the preflight's 64 KiB limit")
        manifest = parse_manifest(archive.read("META-INF/MANIFEST.MF"))
        missing = [name for name in REQUIRED if name.replace(".", "/") + ".class"
                   not in archive.namelist()]
    return {"manifest": manifest, "class_major_versions": dict(versions),
            "signature_entries": signatures, "missing_required_classes": sorted(missing)}


def parse_methods(text):
    methods = set()
    name = None
    for raw in text.splitlines():
        line = raw.strip()
        if line.startswith("descriptor:"):
            if name is not None:
                methods.add((name, line.split(":", 1)[1].strip()))
            name = None
        elif "(" in line and line.endswith(";"):
            name = line.split("(", 1)[0].split()[-1]
        elif line:
            name = None
    return methods


def inspect_target(path):
    # This first preflight is deliberately limited to small local game JARs.
    if path.stat().st_size > 32 * 1024 * 1024:
        raise ValueError("Input exceeds this checker's 32 MiB admission limit")
    data = path.read_bytes()
    digest = hashlib.sha256(data).hexdigest()
    report = {"input": str(path), "sha256": digest, "bytes": len(data),
              "recognized_profile": "knightonline-4.0.3-reference" if digest == REFERENCE_SHA256 else None,
              "injection_performed": False}
    report.update(inspect_archive(data))
    checks = []
    # Do not pass unreviewed artifacts to a deep bytecode tool merely because
    # they advertise the same version in their manifest.
    if digest == REFERENCE_SHA256:
        for class_name, requirements in REQUIRED.items():
            process = subprocess.run(
                ["javap", "-p", "-s", "-classpath", str(path), class_name],
                capture_output=True, text=True, timeout=30, check=True)
            observed = parse_methods(process.stdout)
            for name, descriptor in sorted(requirements):
                checks.append({"class": class_name, "method": name,
                               "descriptor": descriptor,
                               "present": (name, descriptor) in observed})
        if hashlib.sha256(path.read_bytes()).hexdigest() != digest:
            raise ValueError("Input changed during inspection")
    report["method_checks"] = checks
    report["structural_requirements_met"] = (
        bool(checks) and all(check["present"] for check in checks)
        and not report["missing_required_classes"])
    report["status"] = (
        "recognized-input-not-patched" if report["structural_requirements_met"]
        else "unsupported-or-mismatching-input")
    report["limitations"] = [
        "Checks archive metadata and method descriptors, not injection byte patterns or CLDC verifier acceptance.",
        "No helper is compiled or inserted and no modified JAR/JAD is produced.",
        "Unknown hashes require a reviewed compatibility profile even when their version labels match.",
        "Signature entries are listed, not cryptographically verified.",
    ]
    return report


def main():
    parser = argparse.ArgumentParser(description=__doc__)
    parser.add_argument("jar", type=Path)
    parser.add_argument("--report", type=Path, help="New JSON path; existing reports are refused")
    args = parser.parse_args()
    try:
        if args.report and (args.report.exists() or not args.report.parent.is_dir()):
            raise ValueError("Report must be a new path with an existing parent directory")
        report = inspect_target(args.jar.resolve(strict=True))
        text = json.dumps(report, indent=2) + "\n"
        if args.report:
            with args.report.open("x", encoding="utf-8") as handle:
                handle.write(text)
        print(text, end="")
        return 0 if report["structural_requirements_met"] else 2
    except (OSError, ValueError, KeyError, zipfile.BadZipFile, subprocess.SubprocessError) as error:
        parser.exit(2, f"Preflight failed: {error}\n")


if __name__ == "__main__":
    raise SystemExit(main())
