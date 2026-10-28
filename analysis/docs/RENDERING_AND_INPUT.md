# Rendering technique and game controls

## 1. Graphics stack

```text
MIDP Canvas.paint(javax.microedition.lcdui.Graphics)
  -> TemCanvas.paint()
  -> Main.GameCanvas.paint(TemGraphics)
  -> currentScreen.paint(mGraphics)
  -> map tiles / entities / effects / HUD / dialogs
```

This artifact uses a **2D raster tile-and-sprite renderer**. Its graphics wrapper
delegates to MIDP `Graphics.drawImage`, `drawRegion`, clip, translate, lines, and
filled rectangles. The inspected render path has no 3D scene/mesh pipeline.

Sources: `com/silverknight/TemCanvas.java:50-57`, `Main/GameCanvas.java:269-294`,
`CLib/mGraphics.java:14-118`.

`TemCanvas` extends ordinary `Canvas`, not the MIDP game-package `GameCanvas`.
The similarly named `Main.GameCanvas` is a custom coordinator. Its render loop
uses `repaint()` and `serviceRepaints()` with a 40 ms target interval. No custom
whole-screen backbuffer is visible in that loop; actual Canvas double buffering
depends on the Java ME implementation and has not been measured.

Source: `com/silverknight/TemCanvas.java:19-78`.

## 2. Tiles, camera, and world layering

- Tiles are **24 x 24 pixels** (`LoadMap.wTile = 24`).
- `mapPaint[y * mapW + x]` selects a tile image region.
- `mapType` holds movement classification; it is not the tile's image index.
- Rendering computes the camera-visible tile range plus a margin, then crops
  tiles from `imgTile`/`imgTileWater` with `drawRegion`.
- Water animation uses frame/tile parity and separate tile-water imagery.

Sources: `Thread_More/LoadMap.java:138-170`, `Thread_More/LoadMap.java:273-317`,
`Thread_More/LoadMap.java:377-431`.

World painting translates by negative camera position (plus screen shake).
Entity `ySort = y + ysai`; `CRes.quickSort()` sorts the actor vector. The painter
then merges actors and static map items by vertical position. This produces the
familiar top-down overlap where a lower character covers objects behind it.

Approximate paint order:

1. Camera translation, background and terrain tiles.
2. Map-level overlays/waterfalls/footsteps and low effects.
3. Y-ordered actors merged with map items.
4. Projectiles, additional skill effects, exit labels, high effects and weather.
5. Target marker; reset translation.
6. Minimap, player/target HUD, touch controls, map labels.
7. `Main.GameCanvas` adds dialogs, menus or chat above the screen.

Sources: `GameScreen/GameScreen.java:580-718`,
`GameScreen/GameScreen.java:722-845`, `GameScreen/GameScreen.java:945-995`,
`Model/CRes.java:196-241`, `Main/GameCanvas.java:269-286`.

The camera follows the player unless another camera mode/quest takes control.
`mCamera.UpdateCamera()` smooths movement using integer accumulators and clamps
to map bounds. Read `xCam/yCam`, not just target `xTo/yTo`, for touch conversion.
Sources: `GameScreen/GameScreen.java:979-997`, `Model/mCamera.java:89-125`.

## 3. Characters, text, and assets

Characters are composited from direction/frame-dependent parts and effects:
legs, body, head, hat, eyes, hair, wings, weapons, mounts and cosmetic overrides.
`mTypePartPaintPlayer[Direction]` controls layer order. `CharPartInfo` stores
source rectangles and frame offsets; right-facing regions can use mirroring.
This is sprite composition, not skeletal mesh animation.

Sources: `GameObjects/MainObject.java:992-1085`,
`GameObjects/MainObject.java:1129-1227`, `GameObjects/CharPartInfo.java:20-65`.

Much UI text uses **bitmap fonts**: `mFont` loads font PNGs and metric data, then
draws per-glyph regions. Text visible on screen is not necessarily a native
platform text widget. Source: `CLib/mFont.java:58-85`, `CLib/mFont.java:136-173`.

`mImage` normally resolves image resources under `/x1/` in this artifact because
zoom is forced to 1. Some missing tiles/character assets can be loaded from RMS
or requested from the server. A local JAR asset inventory is not the complete
set of assets the running client may display.

Sources: `com/silverknight/TemCanvas.java:40-44`, `CLib/mImage.java:71-91`,
`Thread_More/LoadMap.java:147-169`, `GameObjects/CharPartInfo.java:74-158`.

Several wrapper method names overstate their implementation: `drawRecAlpa()`
fills an opaque rectangle; `saveCanvas()` and `restoreCanvas()` are empty here.
Inspect the Java ME wrapper before assuming Android-like alpha or canvas stacks.
Source: `CLib/mGraphics.java:120-165`.

## 4. Update and input priority

`Main.GameCanvas.update()` advances the tick, periodically refreshes `timeNow`,
routes modal/menu/chat input, then **always calls `currentScreen.update()`**.
Opening a dialog therefore does not necessarily pause world simulation.

Sources: `Main/GameCanvas.java:300-336`, `GameScreen/GameScreen.java:1085-1101`.

Automation must independently gate actions when a dialog, menu, chat, tutorial,
shop or other interaction owns control. A hook immediately before
`currentScreen.update()` is a candidate place to run a short decision step after
normal input handling; it must still inspect all those states.

`GameScreen.update()` updates entities and effects and sorts actors.
`Player.update()` handles collision/animation, local auto behavior, cooldowns,
then calls the base update that integrates `x += vx; y += vy`.
Sources: `GameScreen/GameScreen.java:888-968`, `GameObjects/Player.java:235-282`,
`GameObjects/MainObject.java:1825-1843`.

## 5. Key map

`mapKeyPress()` translates raw codes into shared pressed/held arrays:

| Raw key | Internal indices | Normal interpretation |
| --- | --- | --- |
| -1 / -2 / -3 / -4 | 2 / 8 / 4 / 6 | Up / down / left / right |
| -5 or 10 | 5 | Fire/confirm |
| ASCII `0` through `9` | 20 through 29 | Numeric keys |
| `*` / `#` | 10 / 11 | Chat/event shortcuts in the player input path |
| -6 / -7 | 12 / 13 | Softkeys |
| QWERTY R/T/Y/U/I | 21/23/25/27/29 | Hotkeys, if QWERTY mode enabled |

Sources: `Main/GameCanvas.java:534-624`, `Main/GameCanvas.java:628-699`.

Movement recognizes directional indices and numeric 2/8/4/6. Player hotkeys
use numeric **1, 3, 5, 7, 9** for slots 0-4. Slot 2 also responds to fire/confirm.
Sources: `Main/GameCanvas.java:842-864`, `GameObjects/Player.java:652-763`.

On the **Asha 311**, physical numeric/softkey shortcuts should not be the sole
way to enable or cancel automation. Prefer a touch-accessible control.

## 6. Touch coordinates and actions

```text
physical touch -> divide by zoomLevel -> logical px/py
logical px/py + camera xCam/yCam -> world pixel x/y
world pixels / 24 -> tile coordinates
```

`pointerPressed`, `pointerDragged`, and `pointerReleased` maintain shared flags,
movement thresholds and positions. `PaintInfoGameScreen` checks controls,
finds nearby objects, sets `ObjFocus`, calls `setPointFocus()` for interactions,
or computes a path with `updateFindRoad()` for ground taps. The path is staged
in `posTam` and committed after a short `timeMove` delay.

Sources: `Main/GameCanvas.java:706-746`,
`GameScreen/PaintInfoGameScreen.java:1462-1557`.

For precise automation, direct state-aware movement/skill calls avoid brittle
screen-coordinate tapping. For manual-cancel detection, observe real key/touch
entry points before the normal handlers clear flags. Do not classify internally
generated control state as fresh physical input.

## 7. Overlay proposal

Draw a small status/cancel button after resetting transform/clip to screen
coordinates. Display automation mode, target map/point, and last stop reason.
Keep overlay painting read-only: no pathfinding, RMS writes, network sends or
sleeping from `paint()`. Some existing animation counters advance during paint,
so suppressing game paints wholesale can alter behavior and is not a proven
background/headless technique.

Use actual `getWidth()/getHeight()` values and pointer capability at runtime.
The Asha's nominal 240 x 400 display does not justify assuming a fixed drawable
area under every orientation, system overlay, or virtual-keypad configuration.
