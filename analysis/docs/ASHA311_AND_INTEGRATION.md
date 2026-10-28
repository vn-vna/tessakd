# Nokia Asha 311 and practical extension design

## 1. Target and scope

The user specifically owns a **Nokia Asha 311**. It is a Series 40 full-touch
device with a nominal **240 x 400** display. This is a different runtime target
from Symbian/S60 or the later Asha 501 platform. The model/platform/display facts
are externally documented; this session has not queried the actual phone or
its firmware. [Model reference](https://en.wikipedia.org/wiki/Nokia_Asha_311).

This JAR declares CLDC 1.0 and MIDP 2.0. It detects pointer support, sizes the
Canvas at runtime and has a touch-specific in-game UI. Those are the relevant
starting paths for this device. Do not infer that multi-touch hardware means
this game's ordinary MIDP pointer callbacks expose multiple contacts.

Sources: `com/silverknight/TemCanvas.java:29-44`,
`Main/GameCanvas.java:169-178`, `Main/GameCanvas.java:706-746`.

## 2. How to add automation on the phone

**Recommended design: desktop bytecode patcher + an in-JAR Java ME helper.**

```text
Original KnightOnline_v403.jar
   -> hash-checked patching on PC
   -> add helper classes + narrow calls at known methods
   -> compatible compile/preverification/package checks
   -> separately named patched artifact
   -> install and test on Asha 311
```

Standard MIDP/CLDC does not provide the desktop JVM's Java-agent attach API or
a portable API for a separate MIDlet to read another game's object fields and
inject its input. `Class.forName()`/`newInstance()` where available are not a
general cross-application code-injection mechanism. Emulator instrumentation
can help analysis, but is not evidence of a deployable attachment method on S40.

This recommendation avoids rebuilding every CFR-produced source file. Existing
classes contain decompiler artifacts; targeted bytecode edits can preserve the
rest of the artifact while adding a small helper. The helper's toolchain still
needs to be established and proven on this phone.

### Packaging constraints

- The original 202 classes have major version 45. Modern desktop-Java default
  bytecode is incompatible. Build helper code against the actual CLDC/MIDP
  APIs and supported class format; avoid unsupported Java SE dependencies.
- CLDC preverification and its stack-map requirements must be handled by the
  selected bytecode pipeline. Do not assume a desktop JVM verification pass or
  ordinary modern ASM frame recomputation proves CLDC compatibility.
- Bytecode insertion needs matching method descriptors, valid exception ranges,
  stack/local limits and preserved attributes. Locate hooks structurally and
  pin the input SHA-256; source line numbers are documentation, not patch offsets.
- Keep Nokia vendor dependencies in optional adapter classes so absence/linkage
  failure does not prevent the core game/helper from loading. Compile against
  SDK API stubs; do not bundle emulator implementations as phone platform APIs.
- Modified packaging can change installation identity, trust prompts, JAD size
  requirements and access to existing RMS. Test coexistence/upgrade behavior
  before assuming the saved account and route state migrate automatically.

## 3. Suggested helper boundaries (proposal)

| Component | Responsibility |
| --- | --- |
| `AutomationController` | One bounded decision step, current mode and cancellation |
| `GameStateView` | Current character/server/map/area identity and readiness |
| `Navigator` | Validated local paths, progress tracking and map-edge actions |
| `RecoveryController` | User checkpoint, death/respawn stages and activity restoration |
| `CombatController` | Hostility policy, target selection and normal skill invocation |
| `DeviceAdapter` | Optional brightness/keep-awake capability and lifecycle |
| `AutomationOverlay` | Touch-accessible status, enable/pause/cancel |

These are responsibilities, not a requirement to create a large framework.
Use fixed-size records/queues and reuse buffers on a memory-constrained phone.
Read `Runtime.freeMemory()`/`totalMemory()` on-device if needed; the phone's
advertised system RAM is not the game's measured Java heap budget.

### Narrow hook candidates

| Location | Hook purpose |
| --- | --- |
| `Main.GameCanvas.update()`, after normal input dispatch and before screen update | Drain observer events; choose one short action when eligible |
| After screen update | Observe stable progression; update status without a second action owner |
| `ReadMessenge.changeMap()` before overwriting old map/position, then after successful completion | Transition snapshot, generation increment, route invalidation |
| `ReadMessenge.diePlayer()` after validating the victim is the current player | Death event and death location |
| Successfully parsed `Fire_Object()` for PvP events | Recent-attacker observation |
| Canvas pointer/key entry points | Physical manual input cancels/pauses automation |
| Final screen-space paint stage | Read-only overlay |
| MIDlet / Canvas foreground lifecycle | Suspend work, clear synthetic input, release device requests |

Sources: `Main/GameCanvas.java:269-336`,
`netcommand/global/ReadMessenge.java:1007-1138`,
`netcommand/global/ReadMessenge.java:3355-3388`,
`netcommand/global/ReadMessenge.java:3419-3503`.

`TemMidlet.pauseApp()` is currently empty and `TemCanvas.run()` loops forever.
Do not mistake this source for a guarantee that S40 permits background gameplay.
An extension should handle `pauseApp()` and Canvas `hideNotify()/showNotify()`
and stop its own actions on loss of foreground.
Source: `com/silverknight/TemMidlet.java:64-72`,
`com/silverknight/TemCanvas.java:59-77`.

### Threading and reentrancy

Networking calls the message handler on the collector thread. The game loop
and MIDP input/paint callbacks can be on other threads. Proposed new observers
should publish small immutable events to a bounded synchronized queue; controller
actions run on the game-update thread, not in `paint()` or a newly added busy loop.

`GlobalService` inherits a shared mutable message field (`Cmd_Message.m`):
`init -> write fields -> send` is a transaction, not thread-safe merely because
`Session_ME` eventually serializes stream writes. Existing handlers also make
service calls. Adding single-threaded automation avoids another command worker
but does not prove all existing command construction is synchronized. If new
observations expose collisions, serialize complete service transactions at the
responsible layer rather than locking just the outgoing byte write.

Sources: `CLib/Session_ME.java:178-187`, `CLib/Session_ME.java:70-103`,
`netcommand/Cmd_Message.java:11-13`, `netcommand/Cmd_Message.java:177-184`.

## 4. Backlight, brightness and keep-awake are different requests

| Capability | Candidate | What is established |
| --- | --- | --- |
| Standard backlight flash | `Display.flashBacklight(int durationMs)` | MIDP 2.0 API; foreground-only; returns false if unsupported/background; device may limit/override duration |
| Nokia light setting | `com.nokia.mid.ui.DeviceControl.setLights(0, level)` | Used by established Nokia-UI adapters; actual levels/duration/firmware effect on this Asha untested |
| Inactivity reset | `DeviceControl.resetUserInactivityTime()` | Symbol exists in a J2ME compatibility implementation, but phone/API availability and semantics are unverified |
| Keep game visible and lit | Periodic, foreground-gated device request if testing shows it works | A design to test, not a standard indefinite wake lock |
| Continue while minimized/locked | No verified mechanism from this artifact or this phone | Backlight requests do not grant execution privileges |

Primary standard source:
[Oracle MIDP 2.0 Display specification](https://docs.oracle.com/javame/config/cldc/ref-impl/midp2.0/jsr118/javax/microedition/lcdui/Display.html#flashBacklight(int)).

That method specifies a **flashing effect**, not an unconditional brightness
or always-on-display contract. It returns immediately, rejects negative durations,
and may be overridden by device policy. Do not promise permanent illumination
from one `flashBacklight(Integer.MAX_VALUE)` call.

Nokia candidate references (implementation examples, not Asha-311 conformance):

- [J2ME Polish DeviceControl adapter](https://github.com/Enough-Software/j2mepolish/blob/master/enough-polish-j2me/source/src/de/enough/polish/util/DeviceControl.java)
- [J2ME Polish Nokia controller](https://github.com/Enough-Software/j2mepolish/blob/master/enough-polish-j2me/source/src/de/enough/polish/util/devicecontrol/NokiaDeviceController.java)
- [J2ME-Loader Nokia API compatibility class](https://github.com/nikita36078/J2ME-Loader/blob/master/app/src/main/java/com/nokia/mid/ui/DeviceControl.java)

The Polish source includes device-specific backlight workarounds and repeated
requests. The emulator class has **empty `setLights` and inactivity-reset
bodies**, so successful execution there would not prove hardware control.
The original game decompilation contains no found `setLights`, `flashBacklight`,
or Canvas visibility overrides in the inspected sources.

## 5. Small device capability probe to build first

Before combining device control with the game, use a small test MIDlet built
against the intended Nokia SDK and run it on the actual Asha 311:

1. Display runtime platform/configuration/profile strings, Canvas dimensions,
   pointer support and Java memory figures. Record firmware separately.
2. Offer independent touch buttons for standard flash, Nokia light-setting,
   normal/default mode and stop. Capture errors and boolean results.
3. Test brightness levels individually and observe elapsed time until dim/off;
   do not interpret a void method returning as a successful physical change.
4. Repeat while visible, after idle dimming, with keyguard, after a system
   overlay, after hide/show and after exit/restart.
5. Only if supported, test bounded periodic renewal and choose a refresh interval
   from measured timeout rather than running it every game frame.

An adapter should expose capability/result state, avoid repeated exception loops,
stop renewal when disabled/hidden, and restore normal system behavior where the
verified API permits. Setting level 0 can force darkness on some implementations;
do not universally label it "restore previous user brightness".

The user's phrase "always on display" is treated here as **keep the foreground
game screen illuminated**. A lock-screen clock/ambient-display feature is a
different requirement and is not provided by this game's Canvas.

## 6. Implementation sequence

1. Read-only in-game HUD: map/area, position, HP, action, focus, generation.
2. Touch cancel/pause and lifecycle gating; verify baseline controls still work.
3. Validated local move-to-point with manual cancellation and no-progress stop.
4. Record map transitions and build a small known-good route graph.
5. Save checkpoint; observe death and restore route after confirmed respawn.
6. Add recent-attacker observations and bounded retaliation through normal skills.
7. Integrate only the device-control options confirmed by the standalone probe.

This ordering supplies observable milestones before combining travel, combat,
respawn, persistence and hardware timing in one loop.
