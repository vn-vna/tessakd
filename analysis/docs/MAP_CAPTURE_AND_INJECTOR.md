# Getting server-fed maps and building a JAR injector

**Implementation update:** the [Navigation Pass 1 build](../../mod/README.md)
now implements offline bytecode injection, selected-exit navigation, RMS recording,
JSR-75 export and a PC capture reader. This document describes the original design;
the implementation README is authoritative for its actual supported behavior.

## 1. Map access: observe the client's normal data path

Server-hosted does not mean inaccessible to an in-client helper. During normal
map loading, the server sends data that the client must decode and hold to
render and move. We can observe those existing in-memory values and messages.

```text
TCP bytes
  -> Session_ME.MessageCollector.readMessage()
  -> decode command/header/payload using the session key
  -> new Message(command, decodedPayload)
  -> GlobalMessageHandler
  -> ReadMessenge.changeMap()
  -> LoadMap.loadmap(rawMapBytes)
  -> mapPaint / mapType / dimensions / exits / objects
```

Sources: `CLib/Session_ME.java:225-264`,
`netcommand/global/ReadMessenge.java:1007-1138`,
`Thread_More/LoadMap.java:138-170`, `Thread_More/LoadMap.java:273-317`.

**No public URL/API to download every map has been identified.** A capture hook
provides maps as the server sends them, usually when visited. It is not proof
that unvisited maps can be enumerated or entered without the normal conditions.

## 2. Three useful capture levels

### A. Decoded map packet

Copy a selected command-12 payload after XOR decoding, before constructing or
dispatching `Message`. Store its identity/length plus a session/map-sequence
record. This preserves map metadata, raw geometry bytes, objects, raw exits,
area and optional fields in the format actually received by this version.

Filter explicitly for wanted message types; do not indiscriminately record
login/account traffic. Keep collector hooks short: bounded copies/queue entries
only. Persist or export elsewhere, not during socket reads.

An incoming `Message.getData()` is **not a valid substitute**: its incoming
constructor initializes an input stream, while `getData()` reads the outgoing
`os` field, which remains null for received messages.
Source: `net/Message.java:33-45`.

### B. Terrain blob

`LoadMap.loadmap(byte[])` directly receives the terrain byte array. Hooking this
method avoids reimplementing session encryption just to capture geometry.
Retain a copy plus map identity/generation and a parse-success state. A method
entry only proves an attempted load, not a successfully ready world.

### C. Parsed navigation snapshot

After a successful map load, copy:

```text
server identity + observed content revision
map ID, name, area, generation
tile size, width, height
base terrain bytes / tile layout
base collision classification
separate object/NPC collision contributions
raw exits and observed crossing actions
NPC/vendor observations
```

`mapPaint` selects tile imagery. `mapType` is a collision/navigation
classification, initially derived from terrain and later modified by map objects
and NPC blocking. Do not permanently bake transient actors into a supposedly
immutable terrain grid. Keep render assets separate: a route planner need not
load every texture into memory.

Sources: `Thread_More/LoadMap.java:278-317`,
`Thread_More/LoadMap.java:447-482`.

## 3. A map cache is not a route graph

The command-12 exits contain x/y/name information, not a reliable destination
ID for each exit. World-map display coordinates do not establish connections.

Record a transition as a directed edge:

```text
sourceMapId + sourceAreaPolicy
rawExitPosition / NPC identity
required interaction or crossing movement
destinationMapId + observed arrival
role/quest/transport constraints, observation revision
```

Associate successful map receipt with the pending crossing action, distinguishing
ordinary travel from death, reconnect, forced warp or manual teleport. A return
edge must be observed independently if the connection might be one-way.

With one manually recorded delivery route, the controller can replay its known
connections and use local pathfinding within each map. A generic panel can list
multiple vendor routes as the cache/graph grows; it should show "route not yet
recorded" for unsupported destinations rather than pretending it knows them.

## 4. Phone storage and export

Proposed first storage: bounded, versioned RMS cache with one current-map working
set and indexed records for saved route maps. Budget total bytes and handle
quota/full-store failures. Include schema/profile/server/content identity so a
cache from another build/server is not silently reused.

Data export can later use a confirmed device capability: file access when
JSR-75/permissions are available, or an explicitly configured transfer path.
Neither a filesystem capability nor an external upload endpoint is currently
implemented/verified. RMS is local storage, not automatically a PC-visible file.

See [panel persistence](CONTROL_PANEL_SPEC.md) for the game's existing cache-clear
behavior that can delete extension records.

## 5. What "injector" should mean for the Asha 311

Use **offline JAR transformation on the PC**, followed by installation of the
modified game. A separate ordinary S40 MIDlet cannot portably attach to the
original game's Java objects like a desktop instrumentation agent.

The injector takes an input JAR, selects a reviewed compatibility profile,
copies existing resources/classes, inserts helper classes, and transforms a
small set of existing methods to call the helper. It writes a separate output
JAR and, where needed, a matching JAD. The original stays the reference input.

The control panel can be generic across recorded routes. Compatibility with
arbitrary game versions is a different problem: support it with explicit
profiles, not a universal string/hex replacement.

## 6. Proposed build and packaging pipeline

```text
input.jar
  -> inspect ZIP, manifest, identity and candidate symbols
  -> select supported version/profile
  -> match exact hook descriptors AND structural bytecode anchors
  -> add profile-compatible helper classes
  -> transform selected methods with a bytecode library
  -> compile/preverify changed/helper classes against CLDC/MIDP APIs
  -> package new JAR, update manifest/JAD metadata
  -> record patch manifest and check unchanged members
  -> emulator launch and real Asha 311 validation
```

Recommended implementation split:

- **Desktop transform:** Java with a pinned bytecode library such as ASM,
  invoked by a small CLI; the existing Python inspection tools can orchestrate
  checks/reports if useful.
- **Phone runtime:** compact Java ME helper classes, compiled for the actual
  supported CLDC/MIDP format and preverified. Helper artifacts can be built once
  per profile and bundled with the injector.
- **Profiles:** target digest, manifest entry, required descriptors, exact
  method/field/instruction patterns and expected insertion counts.

The desktop tool may run on a modern JDK. That does not make modern compiler
output valid for the phone: the original classes are major version 45. A
configured legacy-compatible compiler, Java ME API jars and CLDC preverifier
are needed. Existing environment inspection found Java/Javac/Javap 21 on PATH;
no `preverify`, Maven, Gradle or Ant command was found there. An appropriate
legacy SDK/compiler toolchain has not yet been configured or verified.

Do not compile the entire CFR output as the injection strategy. Preserve
unchanged class bytes and use the original JAR plus platform API stubs as
dependencies for helper compilation and verification.

## 7. Candidate hooks in this artifact

| Original method | Proposed added call |
| --- | --- |
| `Main.GameCanvas.update()V` | Drain events and take one eligible controller step before normal screen update |
| `Main.GameCanvas.paint(LCLib/TemGraphics;)V` | Screen-space status and touch-menu affordance |
| `TemCanvas.pointerPressed(II)V` | Manual-input/cancel or panel-button detection |
| `TemMidlet` / Canvas lifecycle | Foreground state and device-request cleanup |
| `Session_ME$MessageCollector.readMessage()Lnet/Message;` | Capture selected decoded payloads |
| `LoadMap.loadmap([B)V` | Capture terrain bytes and successful parsed navigation state |
| `ReadMessenge.changeMap(Lnet/Message;)V` | Transition generation and previous/new state |
| `ReadMessenge.dieMonster(Lnet/Message;)V` | Owned-animal death candidate |
| `ReadMessenge.ItemDrop(Lnet/Message;)V` | Spilled-goods/drop candidates |
| `ReadMessenge.GetItemMap(Lnet/Message;)V` | Pickup-actor result candidate |

The hook matcher must use actual bytecode owners/descriptors and control-flow
context, not decompiled line numbers. A method descriptor match alone does not
locate the correct instruction or establish compatibility. Lifecycle methods
may need adding to a class where no override exists.

Keep additional control on one game-update owner. Collector/UI callbacks
publish bounded events; paint remains read-only. The original `GlobalService`
constructs requests through a shared mutable message field, so do not introduce
an independent worker concurrently building commands.

## 8. CLDC verification, signatures and installation

An actual transformer must preserve/recompute method limits, branch targets,
exception tables and the **CLDC-compatible stack-map/preverification format**.
Modern Java SE `StackMapTable` generation alone is not proof of CLDC acceptance.
Have the selected Java ME preverifier process the changed/helper output with
the full platform/application dependency classpath.

Refuse duplicate/ambiguous members, unknown profiles, existing helper namespace
collisions and accidental double injection. Record input/output hashes, helper
version, changed members and insertion counts. Preserve unrelated member payloads.

If an input has signatures, modifying it invalidates those signatures; the tool
must explicitly report/repackage the result as appropriate or use a valid
signing workflow. It must not claim the result retains the original trust.
JAD size/hash/signature metadata must match the actual output. This artifact's
preflight found no `.SF/.RSA/.DSA/.EC` signature entries; that is an inventory
observation, not a blanket certificate/trust verdict.

For a coexisting installation, select a distinct test suite identity and JAD
metadata. That may isolate its RMS from the original. Test data migration
separately; do not assume an automatically shared saved account or route cache.

## 9. Initial preflight and subsequent navigation implementation

[../../tools/check_injection_target.py](../../tools/check_injection_target.py)
accepts a JAR and checks archive metadata, class headers, profile identity and
required Java method descriptors using `javap -p -s`. Unknown hashes are reported
as unsupported even if their manifest says version 4.0.3.

```powershell
python tools/check_injection_target.py KnightOnline_v403.jar
# Optional new report destination; existing reports are refused:
python tools/check_injection_target.py KnightOnline_v403.jar --report analysis/new-preflight.json
```

At the initial design stage the reference input passed **21 method checks across 8 classes**. The saved
[preflight report](../injector-preflight.json) explicitly says
`injection_performed: false`. It is a first building block, not an automation
build, a full structural hook verifier or a Java ME compatibility certificate.
The separate `mod/build.py` navigation implementation now performs the actual
transformation/preverification and writes a test JAR/JAD. This does not change
the preflight checker's read-only scope.

Next executable milestones:

1. Configure compiler/API stubs/preverification and prove an installed no-op hook.
2. Add the read-only touch panel/HUD and map recorder; prove normal login/input.
3. Record one vendor/cargo/sale flow and complete route on the phone/emulator.
4. Enable bounded movement and quantity-aware purchases.
5. Exercise animal-death replacement/recollection separately from expiry.
6. Add repeated trips and the device options confirmed by hardware tests.
