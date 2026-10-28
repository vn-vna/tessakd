# Handoff — navigation survey 1.1.1

## Goal and user requirements

Build an injected automation helper for KnightOnline 4.0.3 on **Nokia Asha 311
and Windows KEMulator**. The user's current character is level **104**; read the
live level rather than hardcoding it.

The immediate feature is map investigation/navigation:

1. **Pass 1:** traverse transport points, record map data/connections and export
   to a chosen folder. Implemented as a test build; live validation remains.
2. **Pass 2:** load presaved maps, refine against in-game data and navigate using
   the recorded map graph. Saved-file import/refinement is not implemented yet.
3. **Pass 3:** precise checkpoints and A* over the refined collision grid.

The longer-term goal is merchant/caravan automation: configurable gem source,
gem types/quantities, escort pacing, animal replacement and spilled-cargo
recovery, defensive combat, and device-light options. These remain documented
designs, not implemented trading behavior.

## Important correction: exploration completion

**Keep exploring until no reachable untried transport points remain. Do not
stop after an arbitrary number of map switches.** The previous 100/200-hop
setting was explicitly rejected by the user and removed.

Visited/tried point identity is server + map + area + raw coordinates. Labels
are metadata. Same-map portals are valid self-loops. Known links may be crossed
again to backtrack toward unexplored points, but must not become new frontier
work. The regression suite includes a 110-map ring with 220 crossings and
termination, self-loops, renamed labels and historical blocked-record retry.

## Current runnable artifacts

- `mod/dist/KnightOnline_v403-nav1.jar`
- `mod/dist/KnightOnline_v403-nav1.jad`
- `mod/dist/build-report.json`

Input SHA-256:
`f023b34e0ba0a3dcf44249ed5f90b78f0427deee4668562b8e3a0e9e349b18c6`

Current output SHA-256:
`908b8b7da2774a014b8b9b345289fecc1e07998639b6a9dc4a5947ffac92fd01`

The injector supports only this reviewed input. Do not inject an unknown or
already modified JAR using guessed byte offsets. The helper compiles through
ECJ for class major 45 and is preverified with ProGuard `-microedition`.

## Implementation entry points

- `mod/src/knav/NavMod.java`: original-loop adapter, transport attempts,
  recording, cancellation and survey progression.
- `NavGraph.java`: directed frontier search/backtracking and per-character,
  per-level blocked observations.
- `MapPacket.java`: reviewed command-12 schema.
- `CaptureFormat.java`: canonical versioned `.knav` writer (current schema 2).
- `NavStore.java`: bounded RMS map/graph/block/external-link storage; opens old
  map/walking records and clears all historical retry records for an actor.
- `NavPanel.java`, `FolderPicker.java`, `FolderPaths.java`, `NavExport.java`:
  touch panel, root/folder selection, KEMulator raw-URL compatibility, verified
  file finalization and optional JSR-75 export.
- `mod/injector/Injector.java`: patches four original classes at structural
  anchors; `VerifyClasses.java` checks class metadata without initialization.

Patched classes: `Main.GameCanvas`, `com.silverknight.TemCanvas`,
`CLib.Session_ME$MessageCollector`, and
`netcommand.global.GlobalMessageHandler`.

The decoder hook copies selected payloads without consuming the game's stream.
The dispatcher completion hook prevents a same-map arrival from being committed
before the original handler has processed the map. Incoming map data is matched
to its navigation attempt before recording a loop connection.

## Validation completed

The latest build passed:

- Legacy Java ME compilation and CLDC preverification.
- Graph/cycle/frontier/gate and native-path validation tests.
- Map/transition/block/NPC/diagnostic serialization and Java-to-Python roundtrip.
- Schema-1 backward-compatibility and malformed-capture tests.
- Cache/export code using in-memory RMS and filesystem test doubles, including
  permission denial and silent KEMulator-style rename failure.
- Hook-count checks, metadata-only `-Xverify:all` for all 222 game/helper classes,
  class-version checks and byte-for-byte preservation of untouched input members.
- Documentation citation/link checks.
- Canonical CRLF JAD output across build hosts (binary write avoids Windows
  CRCRLF translation); the installed JAR hash above is unchanged by this fix.

**Not yet tested:** live game login with this build, actual transport crossings,
the actual KEMulator UI/version, or Asha file permissions. No connected emulator
process was found during the prior runtime check. Test doubles are not included
in the phone JAR.

## Next actions

1. Reload the current JAR/JAD in KEMulator. Test **NAV → Export maps** and the
   drive/folder browser, then a normal transport and a small cyclic region.
2. Test **Survey remaining maps** at a branching/dead-end map: it should
   backtrack and continue until the reachable frontier is empty.
3. Obtain a real `.knav` capture and any stopped-status messages. The PC reader
   exports JSON/SVGs for diagnosing exact map/exit/collision behavior.
4. Verify on the Asha 311 once emulator behavior is confirmed.
5. Implement saved-map import/live refinement, then precise checkpoints/A*.

## Constraints to preserve

- High-level worlds such as the user's Thành Phố Cảng example can require NPC
  entry. **Mark next NPC transfer** records a manually performed transfer and
  optional user-entered minimum level, but these links are never auto-walked.
- The survey covers reachable transport points in the observed walking graph,
  not every NPC-only or inaccessible world. Do not infer reverse links.
- Command-51 area-only updates carry no fresh terrain; this pass requires a
  fresh boundary/command-12 capture if the live area differs from the snapshot.
- Native pathfinding remains bounded to 100 steps; A* is a later pass. Keep
  failure reasons distinct from proven level restrictions.
- There is no total-hop limit, but storage budgets and per-attempt timeouts
  remain. Full-cache/data errors are not reported as successful completion.
- File export uses the runtime's JSR-75 filesystem. KEMulator may expose only
  virtual roots; it is not unrestricted native Windows filesystem access.
- Preserve original server-correction paths and normal packet pacing. UI and
  network callbacks should not introduce concurrent command construction.
- The original `GlobalService` uses a shared mutable outgoing message field.
- Existing `.knav` exports may contain dynamic collision observations, not a
  definitive static collision grid. Do not replay old packets into the game
  to fake server-confirmed map transitions.

See `mod/README.md` and `analysis/docs/README.md` for detailed instructions and
research, including the official merchant guide supplied by the user.
