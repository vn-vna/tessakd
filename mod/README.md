# KnightOnline navigation survey 1.1.1 — Pass 1

This is a real, offline-injected **test build** for the reviewed
`KnightOnline_v403.jar`, targeting **Nokia Asha 311 and Windows KEMulator**.
The current user's character is level 104; the mod reads the actual character
level rather than hardcoding 104 or assuming every entrance is open.

## Installable outputs

- [dist/KnightOnline_v403-nav1.jar](dist/KnightOnline_v403-nav1.jar)
- [dist/KnightOnline_v403-nav1.jad](dist/KnightOnline_v403-nav1.jad)
- [dist/build-report.json](dist/build-report.json)

The suite name is **KnightOnline Nav Pass1** to support a separate test
installation. Its saved account/RMS may be isolated from the original suite;
enter the account normally if needed. Install using the matching JAD/JAR pair
where the phone supports it. The original input JAR is never modified.

**Asha/KEMulator live execution has not been tested here.** Compilation, Java ME
preverification, bytecode hook checks, metadata-only JVM verification and
offline parser/export tests pass. These do not prove the Asha firmware's file
permissions or any particular server's portal trigger behavior.

## Using the mod

1. Log in and enter the game normally. Maps are recorded automatically as
   command-12 data arrives, including during manual travel.
2. Tap **NAV** at the top center of the screen.
3. Select a transport point from the captured raw exit list.
4. Choose **Go through exit**. The controller uses the game's native pathfinder
   and ordinary directional movement, approaches from inside the map, and
   attempts to cross the marker.
5. For automatic investigation, choose **Survey remaining maps**. It first tries
   untried walking exits, then uses known directed routes to backtrack toward a
   map that still has an untried exit. **There is no total-hop cutoff or hop-budget
   setting.** It continues until no reachable untried transport points remain,
   unless stopped/paused or a device/data limit prevents recording.
6. Touching elsewhere or pressing a physical key pauses navigation. Reopening
   the panel, hiding the Canvas, player death, dialogs/chat and timeouts also
   stop or yield control. Server correction paths retain priority.
7. Open NAV and select **Export maps**. A portable drive/folder browser opens.
   Open a drive/virtual root, browse subfolders, then choose **Use this folder**.
   **Up**, **Drives / roots**, and **Cancel** are available. Allow file-access
   permission when asked. **Choose export folder** selects a folder without
   exporting; **Export typed URL** is a fallback for manually entered URLs.
8. A successful export writes `knav-<timestamp>.knav`. An incomplete/failed
   transfer can leave `.knav.part`; success is reported only after the final
   file exists with the expected byte count.

### Asha and KEMulator filesystem behavior

The picker uses JSR-75 roots exposed by the runtime; it is an in-game Java ME
browser, not a Windows-only native dialog. Asha can expose phone/memory-card
drives. KEMulator can expose a **virtual filesystem**, rather than every actual
Windows drive. Enable file access in the emulator if it is disabled.

The reviewed [KEMulator nnmod filesystem source](https://github.com/shinovon/KEmulator/blob/main/src/midp/javax/microedition/io/file/FileConnectionImpl.java)
maps `file:///root/` under `Emulator.getUserPath()/file/root`; the exact physical
user-data location depends on the emulator version/configuration. The root list
comes from its [FileSystemRegistry implementation](https://github.com/shinovon/KEmulator/blob/main/src/midp/javax/microedition/io/file/FileSystemRegistryEn.java).
This source also uses unescaped filenames: the picker detects its public
FileConnection implementation and preserves spaces, Unicode, and literal `%`
in that mode, while retaining normal escaped URLs for the phone.

Load the newly rebuilt JAR/JAD in KEMulator and restart the MIDlet; an already
running game does not hot-reload the changed helper classes. No emulator process
was found on this connected host, so the actual running emulator/version has
not been inspected or controlled.

### Level restrictions and separated/NPC-only worlds

An exit attempt that fails is recorded with the current character, level and
reason. Recognized English/Vietnamese level-denial notices are retained as
observations, not converted into guessed exact minimum levels. Such exits are
skipped for that character at that level; a different level/account can retry.
Use **Retry blocked exits** after a quest/role change or to retry a temporary
obstacle without waiting for a level change. Ordinary prompts requiring a choice
pause the mod rather than automatically accepting purchases or NPC actions.

The survey traverses only observed **walking** links. It does not assume that
reverse links exist or that disconnected worlds can be reached by walking.
When the reachable walking frontier is exhausted, its status says so; this is
not a claim that every map/world in the game has been discovered.

### Exploration contract and loop handling

Explore transport **points**, not just map IDs: try each reachable point, save
the resulting connection/map, backtrack to remaining points, and finish only
when the reachable frontier is empty. Do not replace this completion condition
with an arbitrary count of map switches.

Successful/tried point identity uses server, map, area and raw coordinates;
display names are metadata, so translated/changed labels do not reopen points.
An exit that loads the same map is recorded as a self-loop when the received
map load belongs to the current crossing attempt. Capture waits for the native
message handler to finish, so unchanged map IDs cannot commit an arrival early.
The search's visited set
prevents cycling through already-explored links while backtracking. Per-exit
timeouts and level-scoped failures still avoid getting stuck at an unusable point.

For an NPC-only transfer (such as the user's high-level Thành Phố Cảng route):

1. Open **Mark next NPC transfer** before using the NPC.
2. Enter an identifying note and, if known, a minimum level (0 means unknown).
   A requirement described as strictly above level 100 would be entered as 101;
   this is user-supplied metadata, not a server-verified rule table.
3. Arm the mark and use the NPC manually within two minutes.
4. The resulting map change is saved as an **NPC-only** link. Unmarked manual
   or forced changes are saved as unclassified links, not inferred walking links.
5. Start **Survey remaining maps** in the newly entered walking region.

NPC links and their level annotations are exported, but are never automatically
used as walking shortcuts. Arbitrary NPC menu/ship automation is still outside
this pass. **Cancel NPC mark** disarms an unused mark.

**Export does not clear the cache.** After copying/validating an export, the
panel's **Clear recordings** command clears only this mod's RMS record store.
The current live map may immediately be recorded again. The original game's
own broad clear-RMS function can also erase this mod's cache; export first.

## What is recorded

- Latest observed map packet and collision snapshot per server/map/area key.
- Original command-12 payload, including terrain, object data and raw exit
  coordinates/labels; the game message stream is not consumed or replaced.
- Observed map-switch connections correlated with the selected exit, including
  arrival position and area. These are observations, not a claim that an edge
  has been proved bidirectional or permanently available.
- Current-session capture-gap/rejection counters.
- Character/level-scoped blocked-exit observations, and separate NPC/unclassified
  transition records, including optional user-entered level requirements.

The collision snapshot can include dynamic object/NPC blocking; it is labeled
as such rather than presented as a permanent static collision map. Tile IDs,
tileset identity and raw payload remain available for later refinement.

Bounds: 192 stored map/area keys, 512 current walking links, 384 blocked-exit
observations, 256 external transitions, and a 2 MiB RMS
budget, 65,535-byte map packets, 32 exits/map, 100 native path steps, two stalled
path replans, 60 seconds per navigation attempt and 12 seconds for crossing.
Export/clear in batches if the cache fills. A single-slot incoming queue retains
the latest map; overruns/rejections are reported. Diagnostic counters describe
the current process, not an exhaustive history across phone restarts.

This is a bounded reachable-frontier survey, **not complete automatic exploration
of every world map**. It can backtrack only over observed directed walking links.
Interior/NPC transports are not invoked. A failed border crossing is logged as
blocked/unknown at the attempted level and survey mode chooses another reachable
frontier. Native-pathfinder failures are not falsely labeled proven level gates.
Area-only changes delivered by command 51 do not carry a new terrain packet.
This first pass refuses to navigate with a captured area that no longer matches;
cross a map boundary normally to obtain a fresh command-12 capture, then select
the next exit. Broader live refinement of such observations belongs to Pass 2.

## Read exports on the PC

Copy the completed `.knav` file from the phone, then:

```powershell
python mod/read_capture.py "E:/captures/knav-123456789.knav"
python mod/read_capture.py "E:/captures/knav-123456789.knav" --output "analysis/my-map-survey"
```

The output folder must not already exist and its parent must exist. The reader
validates the file and creates `maps.json` plus collision/exit SVGs. SVG colors:
light = walkable; dark = blocked; blue = water; amber = other/unknown; red dots
= raw transport markers. The JSON preserves raw packets as Base64 with SHA-256.
Map snapshots, walking edges, external/NPC links and blocked attempts remain
separate collections. `walking_components` describes weak components of the
observed walking graph, not guaranteed mutual reachability or authoritative
game-defined dimension identifiers.

The `.knav` format is versioned: `KNAV`, unsigned-short schema 2, Java `writeUTF`
profile digest, int record count, then int-length-prefixed records. Record types:
1 = map snapshot, 2 = walking transition, 3 = current-session capture diagnostics,
4 = NPC/unclassified transition, 5 = character/level-scoped blocked exit. The
updated Python reader also accepts old schema-1 exports. Existing map/walking
RMS records can be reopened; their payload formats are unchanged.
The canonical writer is [src/knav/CaptureFormat.java](src/knav/CaptureFormat.java).
Strings are Java **modified UTF-8**; the Python reader handles nulls and surrogate
pairs rather than assuming ordinary UTF-8.

## Rebuild/inject another copy of this exact input

Prerequisites: Python 3.9+ and JDK 21 (`java`, `javac`) on PATH.

```powershell
python mod/build.py "KnightOnline_v403.jar"
# Replace only the generated release files on a subsequent build:
python mod/build.py "KnightOnline_v403.jar" --replace
```

The first build downloads version/SHA-256-pinned dependencies from Maven Central
into `.cache/`. Cached dependencies allow offline builds. No global SDK, Maven
or Java configuration is changed. Build logs and the Java-generated test export
are retained in a fresh `build/run-*` directory each time.

Toolchain:

- Eclipse ECJ 4.6.1: helper source 1.3, target 1.1 / class major 45.
- MicroEmulator 2.0.4 CLDC 1.0/MIDP 2.0 API stubs, with the optional JSR-75
  interfaces for export. **Platform/emulator implementations are not packaged
  into the phone game.**
- ASM 9.7.1: transforms exactly `Main.GameCanvas`, `TemCanvas`, and
  `Session_ME$MessageCollector` plus `GlobalMessageHandler` at reviewed
  descriptors/instruction patterns. The dispatcher hook confirms native packet
  processing before captured arrivals are committed.
- ProGuard 6.2.2: preverification with `-microedition -target 1.1`, with no
  shrinking, optimization or obfuscation. Private original synthetic accessors
  are visible as library members. The expected original MIDlet -> replaced
  Canvas library/program dependency is narrowly exempted; unresolved references
  in the actual changed/helper program are not globally suppressed.

Only input SHA-256
`f023b34e0ba0a3dcf44249ed5f90b78f0427deee4668562b8e3a0e9e349b18c6`
is supported. Unknown versions/already-modded inputs fail rather than receiving
unreviewed bytecode edits. Other versions need a reviewed profile.

The build checks each untouched member's original bytes, class versions and
inserted hook counts. `-Xverify:all` loads class metadata without running game
class initializers. Java SE verification is additional evidence, not a substitute
for CLDC/phone testing. The existing game's platform compatibility is preserved
as the reference baseline, not reconstructed by recompiling its CFR output.

## Test scope

The build runs:

- Java map-packet fixtures: dimensions, optional tail, modified UTF, spawn/area,
  rejected truncation/malformed geometry.
- Native-path result checks: failure sentinel arrays, blocked cells, invalid
  jumps, approach side and movement direction.
- Directed-graph frontier search/backtracking, missing reverse edges, disconnected
  NPC-only worlds, character/level restrictions and retry behavior.
- Same-map self-loops and renamed transport labels, plus a 110-map ring that
  completes 220 crossings and then stops with no untried points remaining.
- Folder paths for Nokia drives and KEMulator virtual roots, raw/escaped names,
  spaces, literal percent signs, Unicode and parent/root navigation.
- Conservative English/Vietnamese level-denial parsing and non-denial prompts.
  Notices are matched to the character, current level, selected exit and a short
  time window so a stale denial is not applied to another route or a level-up.
- Java-writer -> Python-reader map/transition/diagnostic roundtrip.
- Python negative tests for truncation, unknown records, trailing data, and
  escaped SVG labels.
- Actual cache/export code against an in-memory RMS test double: repeated-map
  replacement, graph reloading, level-block records/retry, NPC separation,
  edge tracking, export/clear exclusion, cache quota and isolation
  from the original game's record namespace. The resulting export also passes
  the Python reader tests. Test doubles are not packaged into the phone JAR.
- Actual file-export worker against a filesystem test double: folders containing
  spaces, completed export/rename, silent rename failure, permission denial and
  duplicate-export prevention. Its completed file passes the same Python reader
  tests. This validates code behavior, not the actual emulator/phone permissions.
- Post-preverification hook counts and metadata verification of all game/helper
  classes, followed by original-member preservation checks.

No live-server login, physical phone installation, real transport crossing,
KEMulator UI or JSR-75 storage permission has been exercised by these offline tests.

## On-phone acceptance checklist

1. Baseline login and ordinary touch movement still work; NAV opens/closes.
2. One manually crossed transport point records both maps.
3. One selected exit navigates and stops after the new map is ready.
4. Touch cancellation, death, foreground loss and server correction behave correctly.
5. Export creates a complete `.knav` in the chosen directory; PC conversion shows
   the expected maps, raw exits and transition direction.
6. Try remaining-map survey at a branch/dead end and verify it backtracks to a
   reachable untried exit. Test blocked exits and the explicit retry command.
7. Mark one real NPC transfer, enter the separated world manually, then survey
   there and verify the export keeps that transfer distinct from walking edges.

## Pass 2 and Pass 3

**Pass 2:** import presaved navigation geometry into a separate cache; match
server/profile/map identity; refine from current server terrain/collision data;
plan over the observed directed map graph. Do not replay old server packets into
the live game to pretend it has switched maps.

**Pass 3:** add precise pixel checkpoints and bounded A* over the refined local
grid, with tile-center/endpoint rules, dynamic blockers, movement reconciliation
and replanning. Keep native movement/server acknowledgement as the execution
layer. The current Pass 1 intentionally uses the existing bounded pathfinder.

Neither saved-map import nor the A* controller is implemented in this release.
