"""Reproducibly build the Pass-1 navigation mod; never runs the game.

python mod/build.py [input.jar] [--replace] [--fetch-only]
Maven Central dependencies are pinned by version and SHA-256. Their identities
are recorded in the build report. Cached dependencies allow offline builds.
"""
import argparse
import hashlib
import json
import os
from pathlib import Path
import subprocess
import tempfile
import urllib.request
import zipfile

ROOT = Path(__file__).resolve().parent
REFERENCE = "f023b34e0ba0a3dcf44249ed5f90b78f0427deee4668562b8e3a0e9e349b18c6"
ARTIFACTS = {
    "ecj": "org/eclipse/jdt/core/compiler/ecj/4.6.1/ecj-4.6.1.jar",
    "cldc": "org/microemu/cldcapi10/2.0.4/cldcapi10-2.0.4.jar",
    "midp": "org/microemu/midpapi20/2.0.4/midpapi20-2.0.4.jar",
    "file": "org/microemu/microemu-jsr-75/2.0.4/microemu-jsr-75-2.0.4.jar",
    "asm": "org/ow2/asm/asm/9.7.1/asm-9.7.1.jar",
    "tree": "org/ow2/asm/asm-tree/9.7.1/asm-tree-9.7.1.jar",
    "proguard": "net/sf/proguard/proguard-base/6.2.2/proguard-base-6.2.2.jar",
}
LOCKED_SHA256 = {
    "ecj": "9cddda75f4a1b4469e73f44e7b61a3e897d0f657df4797f9106ffe88c4eeade0",
    "cldc": "6b5753c830a966d91cbfcd5cde223a6899c6c69f021a8e38a429cec967bc290d",
    "midp": "b7f6ed5e0344c7000643ecc114e6edff3587e8e77face546af0e12ffd6dbe826",
    "file": "d4372cf344ee03f0261ae58bd3d2becb4aed98272bca7c6bb98749efd11b4601",
    "asm": "8cadd43ac5eb6d09de05faecca38b917a040bb9139c7edeb4cc81c740b713281",
    "tree": "9929881f59eb6b840e86d54570c77b59ce721d104e6dfd7a40978991c2d3b41f",
    "proguard": "7587a3a415d6aea1946f597f4babe710a245f085bac4dc5e7cda653acfc39b03",
}


def run(command, log):
    result = subprocess.run([str(x) for x in command], stdout=subprocess.PIPE,
                            stderr=subprocess.STDOUT, text=True, timeout=180)
    log.write_text(result.stdout, encoding="utf-8")
    if result.returncode:
        print(result.stdout)
        raise RuntimeError(f"Command failed ({result.returncode}); see {log}")


def manifest_bytes(text):
    """Fold UTF-8 manifest attributes without splitting code points."""
    lines = []
    for line in text.rstrip().splitlines():
        raw, continuation = line.encode("utf-8"), False
        while raw:
            size = min(len(raw), 69 if continuation else 70)
            while True:
                try:
                    raw[:size].decode("utf-8")
                    break
                except UnicodeDecodeError:
                    size -= 1
            lines.append((b" " if continuation else b"") + raw[:size])
            raw, continuation = raw[size:], True
    return b"\r\n".join(lines) + b"\r\n\r\n"


def write_new(archive, name, data):
    entry = zipfile.ZipInfo(name, (1980, 1, 1, 0, 0, 0))
    entry.compress_type = zipfile.ZIP_DEFLATED
    entry.create_system = 3
    entry.external_attr = 0o100644 << 16
    archive.writestr(entry, data)


def dependencies():
    cache = ROOT / ".cache"
    cache.mkdir(exist_ok=True)
    result, hashes = {}, {}
    for key, relative in ARTIFACTS.items():
        url = "https://repo.maven.apache.org/maven2/" + relative
        target = cache / relative.split("/")[-1]
        expected = LOCKED_SHA256[key]
        if not target.exists():
            print("Download", target.name, flush=True)
            data = urllib.request.urlopen(url, timeout=60).read(16 * 1024 * 1024)
            if hashlib.sha256(data).hexdigest() != expected:
                raise RuntimeError(f"Checksum mismatch: {target.name}")
            target.write_bytes(data)
        data = target.read_bytes()
        if hashlib.sha256(data).hexdigest() != expected:
            raise RuntimeError(f"Cached dependency checksum mismatch: {target.name}")
        result[key] = target
        hashes[key] = {"url": url, "sha256": hashlib.sha256(data).hexdigest()}
    return result, hashes


def main():
    parser = argparse.ArgumentParser(description=__doc__)
    parser.add_argument("input", nargs="?", type=Path, default=ROOT.parent / "KnightOnline_v403.jar")
    parser.add_argument("--replace", action="store_true", help="Replace this tool's generated release files")
    parser.add_argument("--fetch-only", action="store_true")
    args = parser.parse_args()
    original = args.input.resolve(strict=True)
    if hashlib.sha256(original.read_bytes()).hexdigest() != REFERENCE:
        raise SystemExit("Unsupported input digest: only the reviewed 4.0.3 reference is supported")
    deps, hashes = dependencies()
    if args.fetch_only:
        print(json.dumps(hashes, indent=2))
        return
    dist = ROOT / "dist"
    dist.mkdir(exist_ok=True)
    output = dist / "KnightOnline_v403-nav1.jar"
    if output.exists() and not args.replace:
        raise SystemExit("Output already exists; use --replace for generated release files")
    build = ROOT / "build"
    build.mkdir(exist_ok=True)
    work = Path(tempfile.mkdtemp(prefix="run-", dir=build))
    desktop, helper = work / "desktop", work / "helper"
    desktop.mkdir()
    helper.mkdir()
    asm = os.pathsep.join(str(deps[k]) for k in ("asm", "tree"))
    run(["javac", "-cp", asm, "-d", desktop, ROOT / "injector" / "Injector.java",
         ROOT / "injector" / "VerifyClasses.java"], work / "injector-compile.log")
    platform = os.pathsep.join(str(deps[k]) for k in ("cldc", "midp", "file"))
    sources = sorted((ROOT / "src").rglob("*.java"))
    run(["java", "-jar", deps["ecj"], "-encoding", "UTF-8", "-source", "1.3", "-target", "1.1", "-g:none",
         "-bootclasspath", platform, "-classpath", original, "-d", helper] + sources,
        work / "helper-compile.log")
    # Pure parser/navigation tests run against Java SE equivalents of CLDC APIs.
    tests = work / "tests"
    tests.mkdir()
    core = [ROOT / "src" / "knav" / name for name in ("MapPacket.java", "NavLogic.java", "CaptureFormat.java", "NavGraph.java", "GateNotice.java", "FolderPaths.java")]
    core_tests = [ROOT / "tests" / "CoreTests.java", ROOT / "tests" / "GraphTests.java"]
    run(["javac", "-d", tests] + core + core_tests, work / "tests-compile.log")
    fixture = work / "fixture.knav"
    run(["java", "-ea", "-cp", tests, "CoreTests", fixture], work / "tests.log")
    run(["java", "-ea", "-cp", tests, "GraphTests"], work / "graph-tests.log")
    run(["python", ROOT / "read_capture.py", fixture], work / "capture-roundtrip.log")
    run(["python", ROOT / "tests" / "test_capture.py", fixture], work / "capture-tests.log")
    storage_tests = work / "storage-tests"
    storage_tests.mkdir()
    storage_sources = sorted((ROOT / "tests" / "stubs").rglob("*.java"))
    run(["javac", "-d", storage_tests] + core + storage_sources
        + core_tests + [ROOT / "src" / "knav" / "NavStore.java", ROOT / "src" / "knav" / "NavExport.java",
           ROOT / "tests" / "StoreTests.java", ROOT / "tests" / "ExportTests.java"], work / "storage-tests-compile.log")
    stored_fixture = work / "storage-fixture.knav"
    run(["java", "-ea", "-cp", storage_tests, "StoreTests", stored_fixture], work / "storage-tests.log")
    run(["python", ROOT / "tests" / "test_capture.py", stored_fixture], work / "storage-roundtrip-tests.log")
    export_root = work / "export-tests"
    run(["java", "-ea", "-cp", storage_tests, "ExportTests", export_root], work / "file-export-tests.log")
    completed_exports = list((export_root / "Map Exports").glob("*.knav"))
    if len(completed_exports) != 1:
        raise RuntimeError("File export test did not produce one completed capture")
    run(["python", ROOT / "tests" / "test_capture.py", completed_exports[0]], work / "file-export-roundtrip-tests.log")
    transform_cp = str(desktop) + os.pathsep + asm
    changed = work / "changed.jar"
    run(["java", "-cp", transform_cp, "Injector", "patch", original, helper, changed], work / "patch.log")
    # Preverify changed/helper code; expose private synthetic accessors in the
    # original library and exclude duplicate definitions of replaced classes.
    with zipfile.ZipFile(changed) as edits, zipfile.ZipFile(original) as source:
        changed_set = set(edits.namelist())
    preverified = work / "preverified.jar"
    config = work / "preverify.pro"
    lines = ["-injars '" + str(changed) + "'", "-outjars '" + str(preverified) + "'"]
    replaced = sorted(n for n in changed_set if not n.startswith("knav/"))
    lines.append("-libraryjars '" + str(original) + "'(" + ",".join("!" + n for n in replaced) + ")")
    for library in [deps["cldc"], deps["midp"], deps["file"]]:
        lines.append("-libraryjars '" + str(library) + "'")
    lines += ["-dontshrink", "-dontoptimize", "-dontobfuscate", "-microedition", "-target 1.1",
              "-dontskipnonpubliclibraryclasses", "-dontskipnonpubliclibraryclassmembers",
              # Expected original-library -> replaced Canvas dependency; no
              # renaming/shrinking/optimization occurs, and descriptors stay fixed.
              "-dontwarn com.silverknight.TemMidlet",
              "-keep class * { *; }", "-keepattributes Exceptions,InnerClasses,EnclosingMethod,Synthetic"]
    config.write_text("\n".join(lines) + "\n", encoding="utf-8")
    run(["java", "-jar", deps["proguard"], "@" + str(config)], work / "preverify.log")
    with zipfile.ZipFile(original) as source, zipfile.ZipFile(preverified) as replacement:
        overrides = {n: replacement.read(n) for n in changed_set}
        changed_names = sorted(n for n in overrides if not n.startswith("knav/"))
        manifest = source.read("META-INF/MANIFEST.MF").decode("utf-8")
        manifest = manifest.replace("MIDlet-Name: KnightOnline Midlet Suite", "MIDlet-Name: KnightOnline Nav Pass1")
        manifest = manifest.replace("MIDlet-Jar-URL: KnightOnline.jar", "MIDlet-Jar-URL: " + output.name)
        manifest = manifest.rstrip() + "\r\nKNAV-Version: 1.1.1\r\nMIDlet-Permissions-Opt: javax.microedition.io.Connector.file.write,javax.microedition.io.Connector.file.read\r\n\r\n"
        marker = {"schema": 1, "feature": "navigation-pass1-survey", "mod_version": "1.1.1", "original_sha256": REFERENCE,
                  "changed_classes": changed_names, "helper_classes": sorted(n for n in overrides if n.startswith("knav/"))}
        staged = work / "release.jar"
        with zipfile.ZipFile(staged, "w", zipfile.ZIP_DEFLATED) as release:
            for entry in source.infolist():
                if entry.filename in overrides or entry.filename == "META-INF/MANIFEST.MF":
                    continue
                release.writestr(entry, source.read(entry))
            write_new(release, "META-INF/MANIFEST.MF", manifest_bytes(manifest))
            for name, data in sorted(overrides.items()):
                write_new(release, name, data)
            write_new(release, "META-INF/KNAV.JSON", json.dumps(marker, sort_keys=True).encode())
    run(["java", "-cp", transform_cp, "Injector", "verify", staged], work / "verify.log")
    run(["java", "-Xverify:all", "-cp", desktop, "VerifyClasses", staged,
         deps["cldc"], deps["midp"], deps["file"]], work / "jvm-verify.log")
    with zipfile.ZipFile(original) as source, zipfile.ZipFile(staged) as release:
        unchanged = 0
        for name in source.namelist():
            if name not in changed_names and name != "META-INF/MANIFEST.MF":
                if source.read(name) != release.read(name):
                    raise RuntimeError("Unexpected changed original member: " + name)
                unchanged += 1
        for name in overrides:
            if int.from_bytes(release.read(name)[6:8], "big") != 45:
                raise RuntimeError("Unexpected class version: " + name)
    if hashlib.sha256(original.read_bytes()).hexdigest() != REFERENCE:
        raise RuntimeError("Original input changed during build")
    os.replace(staged, output)
    jad = dist / (output.stem + ".jad")
    # Binary write avoids Windows translating already-canonical CRLF into CRCRLF.
    jad_data = (manifest.rstrip() + "\r\nMIDlet-Jar-Size: " + str(output.stat().st_size) + "\r\n").encode("utf-8")
    if b"\r" in jad_data.replace(b"\r\n", b"") or b"\n" in jad_data.replace(b"\r\n", b""):
        raise RuntimeError("Generated JAD must use canonical CRLF line endings")
    jad.write_bytes(jad_data)
    report = {"input_sha256": REFERENCE, "output_sha256": hashlib.sha256(output.read_bytes()).hexdigest(),
              "output": str(output), "work": str(work), "dependencies": hashes,
              "unchanged_original_members": unchanged, "patch": marker,
              "preverification": "ProGuard 6.2.2 -microedition -target 1.1",
              "core_tests": "passed", "java_python_export_roundtrip": "passed",
              "rms_storage_tests": "passed (in-memory test double, not phone RMS)",
              "frontier_and_gate_tests": "passed", "folder_path_tests": "passed (Asha drive and KEmulator virtual/raw URLs)",
              "file_export_tests": "passed (file API test doubles, including silent KEmulator-style rename failure)",
              "capture_schema": 2, "kemulator_execution": "not tested",
              "jvm_metadata_verification": "passed (no initialization)", "phone_execution": "not tested"}
    (dist / "build-report.json").write_text(json.dumps(report, indent=2) + "\n", encoding="utf-8")
    print(json.dumps(report, indent=2))


if __name__ == "__main__":
    main()
