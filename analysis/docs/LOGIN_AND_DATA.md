# Login, user state, and persistence

## 1. End-to-end login flow

```text
TemMidlet constructor
  -> TemCanvas -> Main.GameCanvas -> LogoScreen
  -> cached server list + HTTP refresh
  -> LoginScreen.Show()
  -> user presses login / saved-account play / quick play
  -> GameCanvas.connect() starts asynchronous TCP connection
  -> LoginScreen.login() queues login packet
  -> NetworkInit opens streams, starts sender + collector, sends -40
  -> collector receives -40, installs the key
  -> sender may now transmit queued login
  -> server sends initialization messages, including character list
  -> listChar() -> SelectCharScreen or CreateChar
  -> select_char(0, characterId), command 13
  -> server sends character state, skills/settings, and map information
  -> LoadMapScreen readiness checks
  -> GameScreen.Show() -> command 12 acknowledgement
```

Sources: `com/silverknight/TemMidlet.java:55-72`,
`GameScreen/LogoScreen.java:36-79`, `GameScreen/LoginScreen.java:598-627`,
`Main/GameCanvas.java:794-810`, `CLib/Session_ME.java:54-68`,
`CLib/Session_ME.java:178-223`, `CLib/Session_ME.java:282-300`,
`CLib/Session_ME.java:364-375`.

This is a dependency map. Exact server packet arrival order is not established
by static code. In particular, **command 1 alone does not mean world-ready**.

### Server identity

`LogoScreen.applyServerList()` parses `name:host:port:language` entries separated
by commas. It uses saved host identity to preserve selection across reordered
lists. `GameCanvas.connect()` normally reads host/port by `IndexServer`; a
non-null `LoginScreen.ip` uses fixed port 19129 instead.

Sources: `GameScreen/LogoScreen.java:95-160`, `Main/GameCanvas.java:794-810`.

For future saved automation state, use server host and port plus character ID.
A list index alone can refer to a different server after a list refresh.

## 2. Login packet layout

Command **1**, written by `GlobalService.login()`:

| Order | Encoding | Value from the normal login call |
| --- | --- | --- |
| 1 | `writeUTF` | Username |
| 2 | `writeUTF` | Password |
| 3 | `writeUTF` | `GameMidlet.version`, currently `4.0.3` |
| 4-6 | Three `writeUTF` values | `"0"`, `"0"`, `"0"`; further meanings not established |
| 7 | byte | `mGraphics.zoomLevel` |
| 8 | byte | `GameCanvas.device` |
| 9 | int | `-1` for ordinary login; method also supports reconnect state |
| 10 | byte | `-1` in the ordinary call |
| 11 | byte | QWERTY flag |
| 12 | byte | `IndexRes` |
| 13 | byte | `LoginScreen.indexInfoLogin` |
| 14 | byte | Low-graphics flag |
| 15 | short | `IndexCharPar` |
| 16 | `writeUTF` | `stringPackageName` |

Sources: `GameScreen/LoginScreen.java:184-194`,
`netcommand/global/GlobalService.java:35-68`.

Java `writeUTF` uses length-prefixed **modified UTF-8**, not an arbitrary UTF-8
byte string. Numeric stream fields are big-endian before transport XOR.
There is no password hash step in this serialization routine; XOR belongs to
the transport layer, not the account-field format.

## 3. Responses and character selection

| Incoming command | Handler / effect |
| --- | --- |
| 1 | `Login_Ok`: reads a short length plus bytes; loads map-item templates |
| 2 | `Login_Fail` |
| 13 | `listChar`: resets placeholder player/inventory, parses character previews, switches screen |
| 3 | `mainCharInfo`: updates the selected character's runtime data |
| 29 | Skill list; eventually sets `GameCanvas.load.isLoadSkill` |
| 55 | Server settings blob; dispatched by subtype through `MainRMS` |
| 12 | `changeMap`: updates map, spawn, exits, and loading flags |

Sources: `netcommand/Cmd_Message.java:30-84`,
`netcommand/global/ReadMessenge.java:2340-2400`,
`netcommand/global/ReadMessenge.java:2687-2699`,
`netcommand/global/ReadMessenge.java:2790-2842`,
`netcommand/global/ReadMessenge.java:3823-3839`.

`listChar()` reads character IDs as **32-bit ints**. Command 13 selection writes
a byte operation followed by a **32-bit ID**. The active-character command 3
reads its leading entity ID as a **16-bit short**. Preserve these directional
field sizes; do not generalize a single ID width for every packet.

Selection normally waits for a short selection animation before sending.
`autoSelect()` is already present, but uses the screen's current selected slot.
An extension should select a known character identity rather than blindly
calling it immediately when the list is empty or belongs to a new login.

Sources: `GameScreen/SelectCharScreen.java:156-227`,
`netcommand/global/GlobalService.java:220-229`,
`netcommand/global/ReadMessenge.java:231-246`.

### World-ready gate

`LoadMapScreen.update()` requires `isNextMap && isLoadSkill &&
MainRMS.isLoadBegin`, plus a sufficiently small pending image-save queue or
45 seconds of load time. It can enter a tutorial/story screen instead of the
ordinary game. It resets movement/action before showing the game, then marks
`SelectCharScreen.isSelectOk` and sends `Ok_Change_Map()`.

Source: `GameScreen/LoadMapScreen.java:64-108`.

**Proposed automation readiness:** current screen is the game; player exists
with a verified selected-character identity; map data and loading generation
are settled; connected; alive; no blocking modal/tutorial/correction state.
`isConnected()` or a non-null player object alone is insufficient.

## 4. Runtime user data ownership

| Data | Owner | Important updates |
| --- | --- | --- |
| Active character | `GameScreen.player` (`Player` extends `MainObject`) | Command 3 replaces identity/stats; login resets the placeholder |
| Position / movement | `x`, `y`, `vx`, `vy`, `toX`, `toY`, `posTransRoad` | Local movement; command 4 corrections; command 12 spawn |
| HP/MP and maxima | Player fields | Command 3; combat/potion/status updates |
| Level / progress | `Lv`, `phantramLv`, stat/skill arrays | Command 3 and XP/level handlers |
| Inventory / chest | `Item.VecInvetoryPlayer`, `Item.VecChestPlayer` | Command 16 and inventory/chest parsers |
| Currency | `player.coin`, `player.gold` | Inventory update prefix: `readLong`, then `readInt` |
| Current map | `GameCanvas.loadmap.idMap`, `nameMap` | Command 12 |
| Current area/channel | `LoadMap.Area` | Command 12/area messages; presentation is `Area + 1` |
| Other actors / current target | `GameScreen.Vecplayers`, `GameScreen.ObjFocus` | Movement/info/combat packets and local focus selection |
| PK/team/clan | `typePk`, `pointPk`, `myClan`, `Player.vecPlayerPk` | Commands 3, 5, 42, 57 and others |
| Hotkeys/cooldowns | `Player.mhotkey`, `timeDelaySkill`, `timeDelayPotion` | Skill/settings loading and local skill execution |

Sources: `GameObjects/MainObject.java:98-259`, `GameObjects/Player.java:45-102`,
`netcommand/global/ReadMessenge.java:226-370`,
`netcommand/global/ReadMessenge.java:1598-1600`,
`netcommand/global/ReadMessenge.java:4531-4553`, `Thread_More/LoadMap.java:486-488`.

These are a client-side view of game state, not the server database. A local
field edit is not evidence that the server accepted a movement, skill, or stat.

## 5. Local RMS versus server-stored settings

### Local records

`TemMidlet.saveRMS()` inverts every byte (`~byte`) and writes RecordStore record 1.
`loadRMS()` reverses that transform. It is reversible encoding, not encryption.

| Record name | Decoded content / usage |
| --- | --- |
| `user_pass` | Two `writeUTF` values: account and password |
| `isIndexServer` | Server index byte |
| `selectedServerHost` | Host string as UTF-8 bytes |
| `listServer` | `writeUTF` server-list text |
| `isLowDevice` | Low-graphics flag byte |
| `isIndexRes` | Resource-selection byte |
| `isQty` | Presence is used to enable QWERTY mode |

Sources: `com/silverknight/TemMidlet.java:83-114`,
`GameScreen/LoginScreen.java:444-487`, `GameScreen/LoginScreen.java:664-674`,
`GameScreen/LogoScreen.java:153-160`, `GameScreen/LogoScreen.java:187-197`,
`Main/GameCanvas.java:179-205`.

Command **31 incoming** can also supply two UTF strings and overwrite the saved
account. **31 outgoing** is the separate go-home/revive operation. Direction
matters. Source: `netcommand/global/ReadMessenge.java:5779-5798`.

### Settings named RMS that are actually sent to the server

`CRes.saveRMSName()` calls command **55** (`Save_RMS_Server`), not RecordStore.
Subtypes in `MainRMS`: 0 skill/hotkey, 1 help progress, 2 begin-game progress,
3 auto settings, 4 touch/display-of-auto-info settings.

Command 55 outgoing: operation byte, subtype byte, short length, data.
Incoming: subtype byte, short length, data. Auto subtype 3 contains HP/MP-enable
and thresholds, auto-loot-enable/filter bytes, auto-buff mode and per-buff flags.

Sources: `Model/CRes.java:622-624`, `GameObjects/MainRMS.java:21-159`,
`netcommand/global/GlobalService.java:574-592`.

**Proposal:** store new route/checkpoint preferences in a separately versioned
local record with server+character identity. Do not append unknown fields to the
existing server-settings blob. Log state transitions and IDs, not credentials.

## 6. Disconnect handling

`GlobalLogicHandler.onDisconnect()` closes the session, offers retry depending
on selection/screen state, sets a 30-second reconnect timestamp in eligible
states, and resets player action/auto-fire. Village state disables that timer.
`LoginScreen.checkLoginAgain()` checks saved server index and account before
queuing another login; other dialog/timer paths govern when it runs.

Sources: `netcommand/global/GlobalLogicHandler.java:35-60`,
`GameScreen/LoginScreen.java:808-837`, `GameScreen/LoadMapScreen.java:109-125`.

Preserve recovery intent across reconnect, but clear transient targets/paths
and wait for the correct character and world-ready generation again.
