# KnightOnline 4.0.3 — initial reverse-engineering findings

Follow-up: [automation-oriented documentation](docs/README.md) now expands the
login/data, rendering/input, navigation/recovery, combat and Nokia Asha 311
integration analysis. This file remains the initial artifact overview.

## 1. Reference artifact and completed work

| Property | Observed value |
| --- | --- |
| File | `KnightOnline_v403.jar` |
| Size | 1,469,112 bytes |
| SHA-256 | `f023b34e0ba0a3dcf44249ed5f90b78f0427deee4668562b8e3a0e9e349b18c6` |
| MIDlet version | `4.0.3` |
| Runtime declarations | CLDC 1.0, MIDP 2.0 |
| Entry MIDlet | `com.silverknight.TemMidlet` |
| Archive contents | 930 files and 61 directory entries |
| Classes | 202 class files, all with class major version 45 |
| Other files | 728, including assets and manifest |
| Decompiled output | 195 Java files; seven nested/anonymous class files are folded into their containing sources |

Manifest authority: `extracted/META-INF/MANIFEST.MF:1-9`.
Counts and hashes: `artifact.json`, `inventory.csv`, `verification.json`.

The original artifact hash and all 930 extracted file hashes were verified.
CFR 0.152 finished with exit code 0, an empty `decompile.log`, and no failed-method
entries in its `summary.txt`. This establishes successful tool output, not source
equivalence or successful recompilation. The game was not executed in this pass.

## 2. Startup and display loop

```text
Java ME runtime instantiates com.silverknight.TemMidlet
  -> constructor creates TemCanvas
       -> fullscreen Canvas; detects dimensions and pointer support
       -> creates Main.GameCanvas
            -> loads fonts, images, saved options, and screen objects
            -> creates LogoScreen and shows it
       -> starts Load_Data_And_Img background worker
  -> installs GlobalMessageHandler on Session_ME
  -> sets the current Display to TemCanvas
  -> starts TemCanvas thread
       -> update -> repaint -> serviceRepaints -> sleep
```

Important detail: initialization occurs in the MIDlet constructor, rather than
`startApp()`. `startApp()` and `pauseApp()` are empty in this artifact.

- `decompiled/com/silverknight/TemMidlet.java:55-72`
- `decompiled/com/silverknight/TemCanvas.java:29-98`
- `decompiled/Main/GameCanvas.java:148-256`

The loop targets a 40 ms frame interval (approximately 25 FPS when work fits
within that interval). Key presses/releases and pointer presses/drags/releases
are forwarded to `Main.GameCanvas`. This is static behavior, not a measured FPS.

`LogoScreen` reads an RMS-cached server list and attempts an HTTP refresh during
construction. After more than 60 updates its `update()` shows the login screen.
See `decompiled/GameScreen/LogoScreen.java:36-79`.

## 3. Where to read each system

Paths below are relative to `decompiled/`.

| System | Primary starting points |
| --- | --- |
| Java ME lifecycle/display | `com/silverknight/TemMidlet.java`, `com/silverknight/TemCanvas.java` |
| Screen orchestration and input state | `Main/GameCanvas.java` |
| Server selection and login | `GameScreen/LogoScreen.java`, `GameScreen/LoginScreen.java`, `GameScreen/List_Server.java` |
| Character selection/creation | `GameScreen/SelectCharScreen.java`, `GameScreen/CreateChar.java` |
| World rendering and updates | `GameScreen/GameScreen.java` (`paint`: line 580; `update`: line 888) |
| Entities, inventory, quests | `GameObjects/`, including `MainObject.java`, `MainItem.java`, `MainQuest.java` |
| Skills and visual effects | `Skill/`, `GameEffect/` |
| UI tabs, chat, dialogs | `InterfaceComponents/` |
| Map loading/backgrounds | `Thread_More/LoadMap.java`, `Map/` |
| Asset loading | `Model/CRes.java`, `Model/FilePack.java`, `CLib/mImage.java` |
| Transport and packet framing | `CLib/Session_ME.java`, `CLib/mSocket.java`, `net/Message.java` |
| Outgoing packet serialization | `netcommand/global/GlobalService.java` |
| Incoming command dispatch | `netcommand/global/GlobalMessageHandler.java` |
| Incoming payload parsing | `netcommand/global/ReadMessenge.java` |
| Local storage | `com/silverknight/TemMidlet.java`, `GameObjects/MainRMS.java` |
| Language/platform variants | `Model/T.java`, `Model/TE.java`, `Model/TIndo.java`, `Model/TSpain.java`, `Build_Indo/`, `Build_Usa/` |

Names are mostly descriptive in this artifact. Local variable names and types
are often decompiler reconstructions.

## 4. Networking: initial static map

### Server discovery

`GameCanvas.linkIP` defaults to `http://knightageonline.com/srvip/`
(`Main/GameCanvas.java:917`). Regional helpers contain separate URLs.
`TemMidlet.connectHTTP()` performs an HTTP GET and reads UTF-8 response data.

`LogoScreen.applyServerList()` parses comma-separated entries with at least four
colon-separated fields:

```text
display-name:host:port:language,display-name:host:port:language,...
```

It populates server names/hosts, integer ports, and language flags, and saves
the list in RMS. Language flag 0 selects the Vietnamese-language path.
Sources: `GameScreen/LogoScreen.java:95-150` and the corresponding
`bytecode/GameScreen.LogoScreen.txt`.

`CLib/mSystem.java:11-14` also contains built-in regional server arrays. These
are embedded strings, not evidence that any endpoint is currently operational.

### Transport and handshake

- `mSocket` uses MIDP `Connector.open("socket://" + host + ":" + port)`.
- `Session_ME.NetworkInit.doConnect()` creates sender/collector threads and sends
  command **-40** to request key setup.
- The collector treats incoming command **-40** as the key response. It reads a
  key length and key bytes, then cumulatively XORs adjacent key bytes.
- Once setup is complete, command, length, and payload bytes use a repeating
  XOR key with separate advancing read/write positions.

Sources: `CLib/mSocket.java:19-25`, `CLib/Session_ME.java:70-127,178-264,364-374`.

Ordinary packets have a one-byte command, a two-byte length, and a payload.
The receive path also supports four-byte lengths for commands -51, -52, -54,
and the command-126 wrapper. Important bytecode detail: those four length bytes
are read into an array first, then passed through the advancing XOR decoder in
array-index order **3, 2, 1, 0**. Do not assume the ordinary header decoder can
be reused unchanged for this branch.

### Some command mappings already located

| Command | Outgoing example / incoming dispatch |
| --- | --- |
| 1 | Login request / `Login_Ok` |
| 2 | Incoming `Login_Fail` |
| 3 | Incoming `mainCharInfo` |
| 4 | `player_move`: two short coordinates / incoming `objectMove` |
| 5 | Character-info request / incoming `charInfo` |
| 7 | Monster-info request / incoming `monsterInfo` |
| 9 | Attack request / incoming `fireMonster` |
| 12 | Map-change acknowledgement / incoming `changeMap` |
| 13 | Character selection request |
| 14 | Character creation request |
| 16 | Incoming `charInventory` |
| -51 | Image request / incoming `loadImage` |
| -52 | Character-part image-data request / incoming `loadImageDataCharPart` |

Sources: `netcommand/global/GlobalService.java:35-229` and
`netcommand/global/GlobalMessageHandler.java:45-190`. These are directional
command mappings; matching numbers do not imply matching payload formats.
The full protocol and server-side game rules have not yet been reconstructed.

## 5. Local saves and assets

- `TemMidlet.saveRMS()` / `loadRMS()` use Java ME RecordStore record 1. Before
  saving and after loading, every byte is bitwise inverted. See
  `com/silverknight/TemMidlet.java:83-114`.
- `CLib/mImage.java:71-84` rewrites `.img` requests to `.png` and loads resources
  under `/x` + zoom level. `TemCanvas` currently sets zoom level to 1, making
  `extracted/x1/` the primary image tree for this path.
- A separate encoded-image helper XORs bytes with 3, but the normal image loader
  rewrites `.img` before testing that extension. Its presence alone does not
  establish that normal images need decoding.
- `Model/FilePack.java:39-76,91-100` describes a custom packed-resource format:
  a file-count byte, per-entry name length/name/unsigned-short data length,
  then concatenated payload data. Names and the entire combined payload use a
  repeating 13-byte XOR sequence. Pack numeric fields pass through unchanged.
  No individual pack has been decoded or validated in this pass.
- Packaged resources include `maplogin`, `item_map_login`, `table_item`,
  `tile_map_info_*`, `weapon/`, `wps/`, and `x1/`. Their detailed data formats
  remain to be mapped against their readers.

## 6. Decompiler limitations to keep in view

Java ME libraries were not supplied on CFR's dependency classpath. The generated
sources report unresolved `javax.microedition.*` APIs; this is expected for the
desktop JDK used for inspection.

Two explicit CFR behavior-change warnings appear in `TemMidlet.connectHTTP()`
and `GameObjects/DataEffect.java`. Other reconstruction issues are visible even
without a warning: `LogoScreen.applyServerList()` incorrectly renders an integer
array as `Object[]` and an integer assignment with a `String` cast; the bytecode
clearly uses `newarray int` and `iastore`. `Session_ME.MessageCollector` also
renders the integer return from a stream read as a byte variable. Consult the
saved bytecode before treating these as game bugs or making patches.

This workspace is readable decompilation, not yet a verified rebuildable project.
No emulator compatibility or live server availability has been tested.

## 7. Next investigation choices

1. **Recommended: trace login through entering the world.** Document selected
   server -> connect -> key handshake -> login -> character selection -> map
   loading, including exact incoming/outgoing payload fields.
2. **Inspect visual assets.** Decode a representative FilePack and map image/map
   records to their loaders; produce sprite previews and a resource index.
3. **Run under a Java ME emulator.** Select a compatible MIDP/CLDC runtime,
   observe startup/input/scaling, and record any missing APIs or network failures.
4. **Work toward a rebuild or port.** Supply Java ME API dependencies, correct
   decompiler artifacts against bytecode, then establish a build/preverification
   pipeline and behavior checks.

## REA evidence

The exported `evidence.json` retains the REA artifact evidence from this pass.
The custom inventory and CFR/`javap` results are separate local observations,
recorded in their own files rather than presented as REA-produced conclusions.

- Artifact inspection: `ev_5b6ee6d2a81aac374580858b2b4d698868fd4926b4415ab3958b0b71847699b1`
- Artifact inventory: `ev_0333e201e4e0d82a7684f2aa36a0aaa55444252ec4dc93863898f7aa0d207528`
- REA extraction: `ev_185c56cc27634aff6b34475349485661d7886bcbef2338f11e1d9933045a4f70`
