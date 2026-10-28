# Evidence, verification, and remaining questions

## 1. Identity and scope

Reference artifact: `KnightOnline_v403.jar`, 1,469,112 bytes.

```text
SHA-256 f023b34e0ba0a3dcf44249ed5f90b78f0427deee4668562b8e3a0e9e349b18c6
```

This documentation applies to that artifact. Embedded version labels do not
prove another JAR with the same filename/version has identical hooks or behavior.
The phone model is user-supplied; actual firmware and runtime behavior are not
observed. No game session or server connection was launched for this pass.

## 2. Local evidence files

- [Artifact metadata](../artifact.json): source identity and CFR invocation.
- [Inventory](../inventory.csv): original member paths, sizes, CRC32 and SHA-256.
- [Verification](../verification.json): all 930 extracted files match their
  recorded hashes; original JAR matches; all 202 classes have major version 45.
- [Decompiled sources](../decompiled/): 195 Java files, with seven nested or
  anonymous class files incorporated into enclosing sources.
- [Bytecode](../bytecode/): `javap -classpath <original JAR> -c -p` exports for
  19 classes. These exports inspect bytecode; they do not invoke the game.
- [REA evidence](../evidence.json): earlier archive inspection/inventory/extraction.

The REA evidence describes its own archive observations. The CFR, `javap`,
documentation and local verification steps are separate observations and are
not presented as new REA-produced analysis.

## 3. Focused bytecode cross-checks

The following high-impact details were compared with the original class
disassembly in this pass:

| Finding | Bytecode evidence |
| --- | --- |
| Login writes six UTF strings followed by the documented device/config fields | `netcommand.global.GlobalService.txt`, `login`, offsets 5-216 |
| Movement command writes two shorts | Same file, `player_move`, offsets 0-24 |
| Character preview IDs are ints and login resets the placeholder player | `netcommand.global.ReadMessenge.txt`, `listChar`, offsets 6-20 and 102-109 |
| Map update overwrites map ID and tile-based spawn and clears the path | Same file, `changeMap`, offsets 65-179 |
| Death handler reads short/short/short/byte and sets HP 0 / action 4 | Same file, `diePlayer`, offsets 0-119 |
| Built-in auto-fire requires monster category 1 | `GameObjects.Player.txt`, `setAutoFire`, offsets 15-94 |
| Path decode uses high-byte X and low-byte Y, each cast to byte | Same file, `updatePlayer`, offsets 1223-1279 |
| Final exact point is used only when both xStopMove and yStopMove are positive | Same method, offsets 1176-1217 |
| Path reconstruction packs `(x << 8) + y`; >=127 failure returns an allocated array | `GameScreen.GameScreen.txt`, `updateFindRoad`, offsets 871-911 |
| New-map auto-fire reset and loading transition occur before map overwrite | `netcommand.global.ReadMessenge.txt`, `changeMap`, offsets 30-75 |
| Potion request uses a short ID; item-use request uses two bytes | `netcommand.global.GlobalService.txt`, `Use_Potion`, offsets 0-14; `Use_Item`, offsets 0-25 |
| Discard/sell serialization is category byte, ID short, mode byte, without quantity | Same file, `delete_Item`, offsets 0-36 |
| Normal discard passes mode 0 and normal sale mode 1 | `InterfaceComponents.TabShopNew.txt`, `commandPointer(int,int)`, offsets 1054-1068 and 2263-2277 |
| Inventory lookup matches ID and category, not display position | `GameObjects.Item.txt`, `getItemInventory`, offsets 23-40 |

Offsets here are **method-relative bytecode offsets**, not file offsets or patch
locations. The broader render/state/control descriptions are supported by cited
decompiled call sites and selected bytecode searches, not an exhaustive formal
comparison of every decompiled method.

## 4. Known decompilation issues

- CFR explicitly warns about exception handling in `TemMidlet.connectHTTP()`
  and `GameObjects.DataEffect`.
- `LogoScreen.applyServerList()` renders an integer array/cast incorrectly.
  The saved original disassembly uses `newarray int` and `iastore`.
- `Session_ME.MessageCollector` reconstructs a stream-read result as a byte
  variable; the original read returns an int and stores it with `istore`.
- There are additional odd nested-class references and inferred local types.
  Tool exit code 0 is not a compilation/behavior-equivalence guarantee.

Keep the CFR output unchanged as a reading baseline. Before implementing a
hook or fixing an apparent bug, inspect the relevant original bytecode.

## 5. Open questions that affect implementation

| Question | Why it matters | Next evidence |
| --- | --- | --- |
| Actual Asha 311 firmware, Java API availability, and usable heap? | Select SDK/optional adapter and memory budget | Standalone phone diagnostic MIDlet |
| Does setLights work, at what levels and for how long? | Brightness and keep-awake implementation | Physical observation on the phone, including hide/show and lock states |
| Is inactivity reset exported/usable by this firmware? | Optional keep-awake strategy | Compile/link/probe against the selected Nokia SDK/device |
| Which compiler/patcher/preverifier produces a compatible modified JAR? | Cannot deploy a desktop-Java helper by assumption | Minimal no-op hook, package validation, emulator and phone launch |
| What precisely triggers each exit/crossing? | Walking to a marker is not proof of a completed transition | Record movement and resulting command-12 events |
| Which map connects to which exit/NPC/transport? | Multi-map route planning and recovery | Record directed connections; map/server data if supplied |
| What are revival costs and timing on the user's server/maps? | Choose village versus revive policy | Observe normal UI and server responses |
| Does murderer mean recent attacker, last killer, or any open-PK player? | Different target selection and chase policy | User preference plus combat traces |
| Do entity IDs persist across map/area/login boundaries? | Avoid attacking the wrong actor after ID reuse | Runtime comparison; scope IDs to a generation until proved otherwise |
| How do system screens affect this game's update thread? | Foreground gating and device requests | Observe lifecycle/time progression on Asha |

The map viewer's location arrays and language strings are not a verified map
connection graph. `markKiller`, PK score, attackability and personal aggression
are separate signals. Server execution order and acceptance of commands remain
outside a static-client-only conclusion.

## 6. Verification commands

From the project directory:

```powershell
python tools/prepare.py --verify-only
python tools/check_docs.py
```

The first verifies hashes and regenerates bytecode exports. The second checks
documentation source paths/line ranges and local file links. Link/range validity
does not prove a behavioral interpretation; the focused review above supplies
the additional bytecode checks for the most consequential details.

## 7. Definition of the next useful milestone

**Read-only instrumented HUD on the Asha 311**, with map, area, coordinates,
character identity, HP, action, focus and foreground/readiness state. It needs a
visible touch cancel control and must preserve ordinary login and movement.

Once that works, prove a single-map move-to-point before adding cross-map travel,
death recovery or retaliation. Device-light testing can be done independently
in a small capability probe before integration.
