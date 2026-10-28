# Navigation, map transitions, death, and return-to-point

## 1. Coordinate spaces and state

| Value | Meaning |
| --- | --- |
| `GameScreen.player.x/y` | World pixel coordinates of the active character |
| `player.toX/toY` | Current movement segment destination, in pixels |
| `player.xStopMove/yStopMove` | Optional exact final pixel point used by path following |
| `player.posTransRoad` | Packed tile path (`short[]`) |
| `player.countAutoMove` | Cursor into the path |
| `GameCanvas.loadmap.idMap` | Current map identity |
| `LoadMap.Area` | Zero-based area/channel; displayed as `Area + 1` |
| `LoadMap.mapType` | Flattened collision grid, indexed by `tileY * mapW + tileX` |
| `LoadMap.vecPointChange` | Current-map exits/transition markers received from the server |

Sources: `GameObjects/MainObject.java:100-209`,
`Thread_More/LoadMap.java:426-435`, `Thread_More/LoadMap.java:486-488`,
`netcommand/global/ReadMessenge.java:1071-1122`.

Tile size is **24 pixels**. A tile center is `(tile * 24) + 12`.
The command-12 spawn is encoded as two shorts and multiplied by 24; ordinary
movement packets use pixel positions. Preserve this distinction.
Source: `netcommand/global/ReadMessenge.java:1025-1033`.

## 2. Existing local pathfinder

Signature:

```java
short[] GameScreen.updateFindRoad(
    int destinationTileX, int destinationTileY,
    int startTileX, int startTileY, int searchLimit)
```

This names the parameters by their use; the decompiled names are `n` through
`n5`. It marks the **destination** first, explores four orthogonal neighbors
with distance-based frontier selection, then traces a descending numbered path
from the start back toward the destination. It is a bounded heuristic grid
search, not a verified general-purpose shortest-path implementation.

Source: `GameScreen/GameScreen.java:1440-1586`.

### Important return conventions

- `null` can mean start and destination are already within one tile, not just
  failure. Callers sometimes use direct short movement for this case.
- Several failure/budget exits return an allocated **zero-filled array**, often
  of length `searchLimit + 1`. Existing callers reject arrays longer than their
  limit. Non-null therefore does not prove a usable route.
- Search has an iteration cap of 1000 and rejects reconstructed lengths >=127.
- The scratch grid stores byte distances. Path entries are shorts with tile X
  in the high byte and tile Y in the low byte.
- `Player` decodes the two coordinates through signed byte casts. This is not
  an unlimited 16-bit-per-axis path format.
- The routine uses shared `mapFind` and screen fields; do not run simultaneous
  searches from multiple new threads.

Sources: `GameScreen/GameScreen.java:1451-1475`,
`GameScreen/GameScreen.java:1477-1481`, `GameScreen/GameScreen.java:1543-1573`,
`GameObjects/Player.java:400-424`.

**Proposed route validation:** validate map generation, bounds and walkability;
handle the adjacent-point case explicitly; reject over-limit sentinel paths;
check decoded nodes, adjacency and endpoint; establish the current cursor and
final point; stop/replan on timeout or server correction. Do not install a
zero-filled failure path and start moving toward tile `(0, 0)`.

The original search subtracts `cmxMini/cmyMini` offsets before working on the
grid. Their current use should remain consistent with the returned path. A
future rewritten pathfinder should use clearly defined global tile coordinates.

## 3. Path following and server reconciliation

`Player.updatePlayer()` follows `posTransRoad`, stepping when within 6 pixels
of a segment target, converting nodes to tile centers, and calling
`move_to_XY()`. That method chooses axis-aligned velocities; `setMove(true)`
performs collision/near-obstacle handling and the base update integrates them.

Sources: `GameObjects/Player.java:400-451`,
`GameObjects/Player.java:1471-1558`, `GameObjects/MainObject.java:2175-2263`,
`GameObjects/MainObject.java:1825-1827`.

Existing movement sends `player_move(short x, short y)` around a **250 ms**
time gate, with additional stop/distance conditions. Outgoing command 4 has
two short pixel coordinates. Leave movement pacing with this pipeline.
Sources: `GameObjects/Player.java:435-451`,
`netcommand/global/GlobalService.java:73-83`.

Incoming command 4 may correct the local player if the position discrepancy
exceeds 24 pixels. It can lock normal input, suppress outgoing movement,
install a correction path, or snap to the server position and clear the path.
Source: `netcommand/global/ReadMessenge.java:474-538`.

A navigator must yield to correction; repeatedly rewriting `x/y` or replacing
the correction route would cause conflict with the client's reconciliation.

## 4. Cross-map travel

The inspected JAR contains a local-map pathfinder, a map viewer, and received
exit markers. It does **not establish an existing arbitrary `goToMap(id)`
service**. `WorldMapScreen.Show()` shows a locked-map dialog, its player-position
method is empty, and `MapScr`'s inspected commands close/toggle the viewer.

Sources: `GameScreen/WorldMapScreen.java:40-45`,
`GameScreen/WorldMapScreen.java:85-107`, `GameScreen/MapScr.java:305-317`.

Command 12 outgoing (`Ok_Change_Map`) has no destination payload: it is a map
load acknowledgement. Command 51 (`Change_Area`) changes area/channel, not an
arbitrary map ID. Sources: `netcommand/global/GlobalService.java:131-134`,
`netcommand/global/GlobalService.java:558-567`.

`changeMap()` reads exit **x, y, name** values. There is no destination-map ID in
those three fields. The painter adjusts some marker positions for presentation.
Keep the raw exit coordinates in any future route recorder; do not derive
travel topology from world-map picture coordinates or translated labels alone.
Source: `netcommand/global/ReadMessenge.java:1071-1105`.

**Proposal: two navigation layers.**

1. A map/exit graph chooses the next map connection.
2. The local navigator walks toward a validated approach point, performs the
   observed crossing/NPC/transport action, then waits for server-confirmed map
   change and readiness before processing the next connection.

Record each connection as source map, exit identity/raw position, entry action,
destination map, area policy, and observed arrival point. Connections may be
directed and gated by level, quest, transport or time. The exit trigger and graph
need runtime recording or additional map/server data before auto-travel is complete.

## 5. What map changes reset

`ReadMessenge.changeMap()`:

1. Clears `LoadMapScreen.isNextMap`, resets/suspends the built-in auto-fire mode,
   and shows the loading screen.
2. Removes old map objects and actor state.
3. Overwrites map ID and spawn position, clears `posTransRoad`, and resets
   movement/action.
4. Loads terrain/map items, exits, area and type flags.
5. Sets `isNextMap`; optionally reads `isMapLang` and `isShowHoiSinh` booleans.

Then `LoadMapScreen.update()` may reset the path again before entering play.
Sources: `netcommand/global/ReadMessenge.java:1007-1138`,
`GameScreen/LoadMapScreen.java:73-108`.

`isNextMap == true` means map receipt reached a stage; it is not by itself proof
that skill/settings/tutorial/UI loading has completed. Every new map generation
invalidates old route arrays and actor references.

## 6. Death and revival

Incoming command **41** (`diePlayer`) reads:

```text
short victimEntityId
short relatedSourceEntityId
short sourcePkPoints
byte  sourceEntityCategory
```

The source identity is associated with the killer/attacker by its use and should
be corroborated by combat events. The handler sets the victim's HP to zero,
resets action, sets `Action = 4`, records `timedie`, and installs revive/go-home
commands depending on map mode. Independently, `Player.updatePlayer()` notices
`hp <= 0` and applies the dead state.

Sources: `netcommand/global/ReadMessenge.java:3355-3388`,
`GameObjects/Player.java:332-370`.

Two outgoing command-31 variants are exposed by the normal UI:

- `gohome(1)`: the revive command (`cmdRevice`).
- `gohome(0)`: village return, after a confirmation dialog (`cmdVeLang`).

The byte alone does not tell us the server's cost, eligibility, delay or result.
Source: `GameObjects/Player.java:185-203`,
`netcommand/global/GlobalService.java:391-400`.

Command 3 can restore positive HP and reset a dead action. A village transition
may arrive through command 12. Do not assume either a timer or clicking revive
alone proves the character is alive and ready on the intended map.
Source: `netcommand/global/ReadMessenge.java:315-341`.

## 7. Return-to-point proposal

Save a **checkpoint independent of the current movement path**:

```text
schemaVersion
serverHost, serverPort, selectedCharacterId
targetMapId, preferredArea
targetPixelX, targetPixelY
desiredActivity (idle / monster-fight / other)
checkpointRevision
```

The authoritative checkpoint should be user-set or captured from stable living
game state. A death event can record a separate death location. Capture before
the next map packet overwrites map/position, and never overwrite the checkpoint
with the village spawn. The existing `lastX/lastY` and auto-fire origin are not
a cross-map persistent checkpoint service.

Suggested state machine:

```text
IDLE -> TRAVEL_TO_MAP -> TRAVEL_TO_POINT -> ACTIVE
ACTIVE/TRAVEL -> DEAD -> WAIT_RESPAWN -> WAIT_WORLD_READY
  -> TRAVEL_TO_MAP -> TRAVEL_TO_POINT -> RESTORE_ACTIVITY
any state -> PAUSED (manual input / foreground loss / modal / disconnect)
any route state -> BLOCKED (missing connection / repeated no-progress)
```

Choose one movement owner at a time: recovery, travel, retaliation chase, or
ordinary auto-fight. Rate-limit replanning; track progress by position and map
generation; retain retry budgets and a visible stop reason. Preserve any
original auto-fire preference and restore it only after reaching the checkpoint.
Built-in auto-fire can otherwise pull the character toward its own origin.

Do not force the same area number if unavailable. Prefer area only when the
server advertises it and the user wants channel-specific recovery.
