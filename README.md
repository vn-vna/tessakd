# tessakd — KnightOnline Java ME research and navigation mod

Reverse-engineering workspace and an offline JAR injector for
`KnightOnline_v403.jar`. Targets **Nokia Asha 311** and **Windows KEMulator**.

## Current build: navigation survey 1.1.1

- [Installable mod JAR](mod/dist/KnightOnline_v403-nav1.jar)
- [Matching JAD](mod/dist/KnightOnline_v403-nav1.jad)
- [Build report and artifact hashes](mod/dist/build-report.json)
- [Usage, export format, limits and test details](mod/README.md)

**NAV → Survey remaining maps** explores reachable untried transport points,
records maps/connections, and backtracks through known directed links. There is
no total-hop cutoff. Transport identities use map/area and coordinates, so
cycles, same-map return portals and translated labels do not create endless
exploration. Level restrictions and NPC-only world entrances are tracked
separately. Export opens a portable JSR-75 folder browser.

Compilation, Java ME preverification, graph/loop tests, export/cache tests and
metadata-only JVM verification pass. **Actual Asha/KEMulator/server execution
still needs validation.** Reload the MIDlet when replacing a build.

## Continue on another PC

```sh
git clone https://github.com/vn-vna/tessakd.git
cd tessakd
```

Read [HANDOFF.md](HANDOFF.md) for the latest decisions and next steps. To rebuild,
install **Python 3.9+** and **JDK 21**, with `python`, `java`, and `javac` on PATH:

```sh
python mod/build.py KnightOnline_v403.jar --replace
```

The first build downloads version/SHA-256-pinned ECJ, Java ME API stubs, ASM and
ProGuard from Maven Central. They are cached locally; Maven/Ant/Gradle and a
global Java ME SDK installation are not required. The original game is not
executed by the build or offline tests.

To inspect a completed `.knav` export copied from the phone/emulator:

```sh
python mod/read_capture.py captures/example.knav --output analysis/my-survey
```

The destination must be new and its parent must exist. It produces `maps.json`
and SVG collision/transport previews. Both schema-1 and schema-2 captures can be
read. Runtime captures are ignored by Git unless deliberately added.

## Layout

| Path | Purpose |
| --- | --- |
| `KnightOnline_v403.jar` | Original, hash-pinned reference input |
| `mod/src/knav/` | Java ME runtime helper |
| `mod/injector/` | Desktop ASM transformer and class verifier |
| `mod/tests/` | Parser/graph/storage/export regressions and isolated test doubles |
| `mod/build.py` | Reproducible compilation, injection, preverification and packaging |
| `mod/read_capture.py` | PC capture validator / JSON and SVG export reader |
| `mod/dist/` | Current installable JAR/JAD and historical build report |
| `analysis/docs/` | Login, controls, maps, combat, inventory and merchant-system research |
| `analysis/decompiled/` | CFR reading baseline; not a rebuildable game project |
| `analysis/bytecode/` | Selected original `javap` disassemblies |
| `analysis/evidence.json` | Original REA archive inspection evidence |
| `tools/` | Archive preparation, input preflight and documentation checks |

`analysis/extracted/`, dependency caches, intermediate runs and Python bytecode
are intentionally not versioned. The original JAR contains the input needed
to recreate extracted assets. Research metadata and build reports can contain
paths from the machine that produced them; those are historical provenance,
not hardcoded paths required to build on another PC.

## Additional checks

```sh
python tools/check_docs.py
python tools/check_injection_target.py KnightOnline_v403.jar
python -m unittest discover -s tools -p test_check_injection_target.py -v
```

The build itself runs the navigation/export regressions. To regenerate archive
extraction and CFR sources, follow [analysis/README.md](analysis/README.md).
