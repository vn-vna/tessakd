# KnightOnline 4.0.3 reverse-engineering workspace

The original `../KnightOnline_v403.jar` is the reference artifact.

## Navigation Pass 1 implementation

An actual injected navigation/capture test build is now available under
[../mod/](../mod/README.md), with installable JAR/JAD outputs in `mod/dist/`,
the offline injector/build sources, and a `.knav` -> JSON/SVG export reader.
See that README for installation, supported transports, offline proof and the
remaining real-phone validation. Saved-map import/refinement and A* are later passes.

Survey 1.1 targets both Asha 311 and KEMulator: export folder selection,
directed frontier backtracking, level-scoped blocked exits and separate
NPC-world transition records. The user's current character level is 104;
the mod reads the runtime level rather than assuming it.

- `extracted/`: unchanged JAR members, including class files and game resources.
- `decompiled/`: CFR-generated Java for inspection; not original source.
- `inventory.csv`: archive paths, sizes, CRC32 values, and content SHA-256 values.
- `artifact.json`: original artifact identity, manifest, and inventory totals.
- `FINDINGS.md`: initial entry points, subsystem map, and outstanding questions.
- `bytecode/`: JDK `javap -c -p` disassembly of selected startup/network classes.
- `verification.json`: extraction hash checks, class versions, and CFR diagnostics.
- `decompile.log` and `decompiled/summary.txt`: decompiler output/summary.
- `evidence.json`: exported REA inventory/extraction evidence.

Start with [FINDINGS.md](FINDINGS.md).

## Detailed automation documentation

The follow-up investigation targets the user's **Nokia Asha 311** and maps the
systems needed for travel, return-after-death, hostile-player response and
display control. Open [docs/README.md](docs/README.md) for the detailed guide,
including source citations, integration proposals and device-specific unknowns.

The [generic trading panel specification](docs/CONTROL_PANEL_SPEC.md) covers
vendor/gem/quantity choices and replacement-animal cargo recovery. The
[map capture and injector design](docs/MAP_CAPTURE_AND_INJECTOR.md) explains
how server-fed maps can be observed and how an offline JAR transformer would
integrate a Java ME helper. A read-only input checker is available:

```powershell
python tools/check_injection_target.py KnightOnline_v403.jar
```

This checker reports compatibility facts; it does not produce a patched JAR.
The actual navigation injector is `python mod/build.py` (see the implementation above).

## Reproduce

Requires Python 3.9+, a JDK with `java` and `javap` on PATH, and
[CFR 0.152](https://www.benf.org/other/cfr/cfr-0.152.jar).
The publisher's MD5 for that CFR release is `8a85ada8cec494121246805a5562b82b`;
the actual decompiler SHA-256 used here is recorded in `artifact.json`.

From the project directory, on a fresh workspace with no `analysis/extracted/`:

```powershell
python tools/prepare.py --decompiler "C:/path/to/cfr-0.152.jar"
```

The script refuses an existing extraction to avoid mixing artifacts. To verify
the existing extraction and regenerate selected bytecode dumps:

```powershell
python tools/prepare.py --verify-only
python tools/check_docs.py
```

This is an inspection workspace, not a rebuildable Java ME project. The JDK
provides bytecode inspection tools; a MIDP/CLDC emulator or compatible runtime
will be needed to run the game. Java ME APIs were not supplied to CFR in this pass.
