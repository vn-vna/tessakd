# Bundled maps and access to other entities

## 1. Is the entire game world inside the JAR?

**The normal world-loading path is server-fed, not a complete self-contained
offline world loaded from the JAR.** The archive includes rendering resources,
map-item templates, tile metadata and a file named `maplogin`. These must not
be confused with complete per-map layouts, actors and travel connections.

Examples visible in [the inventory](../inventory.csv):

- `maplogin`: 193 bytes; its existence alone is not evidence of world-map data.
- `table_item`: 4,604 bytes, read as map-item templates by `LoadMap`.
- `tile_map_info_0` through `tile_map_info_16`: small tile-classification files,
  mostly 1-4 bytes each, not full map collision grids.
- `x1/tilewater*.png`, waterfall imagery, minimap/UI images and related graphics.
- World-map display locations and map-name strings in class data.

The normal loading chain is:

```text
Incoming command 12
  -> ReadMessenge.changeMap()
       map ID
       spawn tile coordinates
       minimap ID and map name
       short map-data length + map-data bytes
       background info and map-object bytes
       exits, area and mode flags
  -> LoadMap.loadmap(receivedBytes)
       width, height, tileset ID, tile values
       derived collision grid
```

Sources: `netcommand/global/ReadMessenge.java:1025-1063`,
`netcommand/global/ReadMessenge.java:1071-1131`,
`Thread_More/LoadMap.java:138-170`, `Thread_More/LoadMap.java:273-317`.

The map-item template table itself can be replaced by data from incoming login
command 1. Missing tileset images can be loaded from RMS or requested from the
server. Thus packaged tile art, loaded map geometry and cached assets have
different lifetimes and sources.

Sources: `Thread_More/LoadMap.java:89-135`,
`Thread_More/LoadMap.java:147-169`,
`netcommand/global/ReadMessenge.java:2687-2699`.

### What a map recorder could capture

An in-JAR observer could record a visited map's received geometry, current
collision grid, dimensions, exits and arrival/departure observations. Store
server identity and a revision/hash because map data can differ by server or
update. Separate raw tile collision from later NPC/object blocking.

This would build a reusable cache and a learned travel graph as maps are visited.
The present artifact alone does not establish a complete graph of every map,
instance, transport or access condition. Picture coordinates/name lists cannot
substitute for those directed connections.

## 2. Is there an exported API for entity tracking?

There is no identified official external plugin/API interface in this artifact
for a separate phone app. There **are useful public Java fields and methods
inside the client**, accessible to helper classes added to the same JAR.

The main entity collection is:

```java
GameScreen.GameScreen.Vecplayers  // CLib.mVector of MainObject-derived entities
```

Useful related entry points:

```java
GameScreen.GameScreen.player
GameScreen.GameScreen.ObjFocus
GameObjects.MainObject.get_Object(int entityId, byte category)
GameObjects.MainObject.get_Item_Object(int entityId, int category)
```

`get_Object()` matches category plus ID, with an NPC-specific ID accessor path.
Do not key an actor table by the numeric ID alone.
Source: `GameObjects/MainObject.java:2138-2157`.

### Category codes

Defined in `MainObject`:

| Code | Category |
| --- | --- |
| 0 | Player |
| 1 | Monster |
| 2 | NPC |
| 3 | Item/equipment |
| 4 | Potion |
| 5 | Quest item |
| 6 | Hair/cosmetic item (`CAT_ITEM_TOC`) |
| 7 | Material |
| 8 | Clan icon |
| 9 | Pet |
| 10 | Mount |

Source: `GameObjects/MainObject.java:55-65`.

These codes are shared across parts of the client. Their definition does not
mean every category is always instantiated as a live world actor. In particular,
an inventory item's ID and an on-ground drop entity ID are different uses.

### State available for each known actor

`ID`, `name`, `typeObject`, `x/y`, `vx/vy`, `hp/maxHp`, `mp/maxMp`, `Lv`,
`Action`, `Direction`, `isRemove`, `isStop`, `typePk`, `pointPk`, `myClan`,
status flags and special actor/monster fields are available in the object model.

Sources: `GameObjects/MainObject.java:98-259`,
`GameObjects/MainObject.java:265-278`.

`typeObject` is the primary classification. `typeBoss`, `typeMonster`,
`typeSpec` and class-specific overrides refine it. For example, category 0 also
has special actors; category alone does not establish that an entity represents
a normal hostile human-controlled player. Use game predicates and combat
evidence as described in [COMBAT.md](COMBAT.md).

## 3. Coverage and freshness limits

`Vecplayers` represents actors the client currently knows. It is **not a list
of every entity on the server or every map**. A known actor may be off-screen;
render culling and network knowledge are separate boundaries.

- Incoming movement can create a placeholder actor and request more details.
- Other actors' movement initially updates `toX/toY`, while animation advances
  `x/y`; distinguish observed destination from current rendered position.
- Actors leave/remove; map changes invalidate the old world.
- A placeholder may have an empty name or incomplete stats/appearance.

Sources: `netcommand/global/ReadMessenge.java:474-563`,
`GameScreen/GameScreen.java:945-960`,
`GameObjects/MainObject.java:2328-2329`,
`GameScreen/GameScreen.java:1608-1615`.

**Proposed tracking facade:** snapshot known actors into compact read-only rows,
keyed by server/session/map-generation/category/ID. Record freshness and whether
details have arrived. Send snapshots/events from the existing game/collector
hooks rather than exposing mutable vectors to an unrelated worker.

This is an extension design; a public HTTP, Bluetooth or external automation API
has not been built or found in the original game.
