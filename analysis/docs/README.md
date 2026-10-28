# KnightOnline 4.0.3 — automation-oriented technical documentation

**Implementation update:** [Navigation Pass 1](../../mod/README.md) now contains
a built injected JAR, touch panel, exit-walk navigator, map/transition recorder
and file exporter. The trading controller below remains a specification; saved-map
import/refinement and A* navigation are planned follow-up passes.
Survey 1.1 additionally provides a portable folder browser, graph-based
remaining-exit backtracking and level/NPC-boundary-aware records for both Asha
and KEMulator. See the implementation README for the current controls/format.

Target hardware: **Nokia Asha 311**, supplied by the user. The requested end goal
is an in-game automation extension: travel to a map, return to a saved point
after death/village respawn, respond to hostile players, and control the display.

## Reading order

1. [Login, user data, and persistence](LOGIN_AND_DATA.md)
2. [Rendering and game controls](RENDERING_AND_INPUT.md)
3. [Navigation, map transitions, death, and recovery](NAVIGATION_AND_RECOVERY.md)
4. [Combat and hostile-player identification](COMBAT.md)
5. [Asha 311 device controls and integration design](ASHA311_AND_INTEGRATION.md)
6. [Evidence, bytecode checks, and remaining questions](EVIDENCE_AND_GAPS.md)
7. [Bundled maps and entity access](MAPS_AND_ENTITY_ACCESS.md)
8. [Inventory and item actions](INVENTORY_AND_ITEM_ACTIONS.md)
9. [Item-spawned escort/caravan quest investigation](ESCORT_CARAVAN.md)
10. [Official merchant guide and revised trading controller](OFFICIAL_MERCHANT_RULES.md)
11. [Generic control panel and cargo recovery specification](CONTROL_PANEL_SPEC.md)
12. [Server-fed map capture and JAR injector design](MAP_CAPTURE_AND_INJECTOR.md)

Start with the [integration design](ASHA311_AND_INTEGRATION.md) for the practical
recommendation: add a small helper to a separately patched JAR and reuse the
client's movement/skill pipeline. Standard Java ME does not provide a portable
desktop-style agent-attachment mechanism for another running MIDlet.

## What this pass establishes

| Requested area | Result |
| --- | --- |
| Login flow | Traced from input through handshake, login, character list/selection, and map-ready gates |
| Rendering | MIDP Canvas/Graphics, 24-pixel tiles, camera transforms, Y-sorted sprites, layered character parts |
| User data | Runtime state owners, important incoming updates, local RMS formats, and server-stored settings identified |
| Controls | Key-index map, touch coordinate conversion, input priority, movement and hotkey calls documented |
| Go to a map | Local pathfinder identified; cross-map topology and exit-trigger behavior remain incomplete |
| Return after death | Death/respawn hooks and reset behavior mapped; a persistent recovery design is proposed |
| Fight murderer players | Existing attackability rules and actor/target events mapped; exact desired hostile policy needs selection |
| Backlight / keep awake | API candidates and a phone test plan documented; no Asha firmware behavior has been measured |
| List actors and classify them | Current-scene vector, category constants, identity lookups and freshness limits mapped |
| List/use/discard inventory items | Bag fields, auto-potion loop, category-specific use and distinct discard/sell modes traced |
| Automate gem-delivery escort | Official merchant rules matched to caravan hooks; exact live item IDs, map connections, cargo/menu protocol and result signals still need correlation |
| Generic buying panel | Source/gem/quantity configuration, validation and pending-transaction model specified |
| Animal-death cargo recovery | Replacement/recollection state flow specified; exact dropped-gem/cargo mapping still needs recording |
| Input-JAR injector | Runnable profile/descriptor preflight added; bytecode transformer and phone-compatible helper build remain future milestones |

## Evidence conventions

- **Static observation:** directly visible in this artifact's bytecode/decompilation.
- **Interpretation:** an explanation of the observed code, not proof of server behavior.
- **Proposal:** a design for the future extension, not code already installed.
- **Unverified:** needs the phone, an emulator, a runtime trace, or server data.

All `Package/Class.java:line-range` citations refer to the unchanged CFR sources
under [../decompiled/](../decompiled/). The original artifact SHA-256 is
`f023b34e0ba0a3dcf44249ed5f90b78f0427deee4668562b8e3a0e9e349b18c6`.
Disassembly for key classes is under [../bytecode/](../bytecode/).

The initial investigations produced the documentation and bytecode exports below.
The linked navigation implementation supersedes the earlier "preflight only"
status for that feature; real-phone/server execution remains untested here.
