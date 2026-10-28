# Item-spawned escort/caravan automation investigation

## 1. User goal and identification status

The user describes a money-making escort activity: use an item to spawn a mob,
then escort it to deliver gems. The supplied official admin guide now confirms
the **Đi buôn merchant/caravan feature**, including follow behavior, gem sources,
Graham turn-in, capacities and a dated timing update. See
[official rules and revised controller](OFFICIAL_MERCHANT_RULES.md).

The matching client subsystem is **`Monstervantieu`** (caravan/escort).
The user's chosen animal, numeric item identity and live menu sequence still
need runtime correlation with that published feature.
There is also a distinct `Minuong` escort-like class; do not conflate the two
just because both have an owner and similar movement animation.

Sources: `GameObjects/Monstervantieu.java:18-79`,
`GameObjects/Minuong.java:18-63`.

## 2. Dedicated caravan actor

`MainMonster.createMonster()` dispatches server catalog `typeMove == 14` to
`new Monstervantieu(...)`. Catalog type 15 instead creates `Minuong`.
Monster catalogs, including movement types and names, are received from the
server through command 26.

Sources: `GameObjects/MainMonster.java:106-150`,
`netcommand/global/ReadMessenge.java:2130-2150`.

Caravan actor facts:

- `typeObject == 1`: it lives in the monster/entity category, not pet category 9.
- Overrides `isMonstervantieu()` to return true.
- Exposes owner with `getnameOwner()`.
- Inherits live HP, max HP, position, destination, action and removal flags.
- Has speed fields updated from server monster-info data.
- Contains template groupings for white/black donkey/camel/ox/elephant variants:
  white IDs 127/129/131/177, black IDs 128/130/132/176. These are creature
  catalog IDs, **not the item ID that summons them**.

Source: `GameObjects/Monstervantieu.java:20-79`.

### Recognizing the user's own escort

The existing player-interaction path tests:

```text
target.isMonstervantieu()
  AND target.getnameOwner().equals(player.name)
```

It opens caravan information for the owner's mob rather than attacking it.
Use that same ownership convention, scoped to the active character/session,
with non-null current actor and completed info loading. If more than one candidate
matches, do not arbitrarily select the nearest; correlate the spawn event with
the item-use request and newly observed actor identities.

Source: `GameObjects/Player.java:957-970`.

## 3. What is server-driven

Incoming monster-info command 7 updates:

```text
entity ID, level, destination, HP/maxHP, skill/state data,
server-control flag, speed, direction, owner string,
lifetime value and color data
```

The owner goes into `MainMonster.nameowner`; `isServerControl` and speed are
explicit fields. Caravan speed initialization is applied when the owner string
is nonempty. Source: `netcommand/global/ReadMessenge.java:895-973`.

Incoming movement updates the monster's `toX/toY` and `isMove`. In the base
update, `isServerControl && isMove` invokes movement toward that received
destination. The caravan subclass supplies animation and local interpolation
speed adjustments.

Sources: `netcommand/global/ReadMessenge.java:540-560`,
`GameObjects/MainMonster.java:195-205`,
`GameObjects/Monstervantieu.java:145-171`.

**Automation should move the player, observe the escort, and pace the route.**
Changing the caravan's local position/speed is not proof of changing its server
position or quest progress. The 3-4-tile speed thresholds in the subclass refer
to the caravan's distance from its own movement destination, **not a verified
player-to-escort leash distance**.

The info parser reads a lifetime value, but `MainMonster.setTimelive()` is empty
and `Monstervantieu` does not override it in this artifact. An accurate delivery
expiry/countdown therefore needs additional server/UI evidence; do not assume
this class supplies one. Source: `GameObjects/MainMonster.java:103-104`.

## 4. Spawn item and caravan interaction

The spawn item has not been identified. The inventory has separate consumable,
material and equipment-use paths; item names/templates may be server-delivered.
Follow the actual item through [inventory action dispatch](INVENTORY_AND_ITEM_ACTIONS.md)
once its name/category/ID is known. Do not use a creature template ID as the item
ID or repeatedly use an item merely because the new actor has not appeared yet.

For an owned caravan, `cmdInfoVantieu` is command index 27 on the game screen.
That handler sends:

```java
GlobalService.gI().Dynamic_Menu((short)-56, (byte)0, (byte)0);
```

Sources: `GameScreen/GameScreen.java:213-216`,
`GameScreen/GameScreen.java:503-505`.

The sentinel **-56 is an argument to command -30**, not the outer network command
and not a demonstrated summon command. The server returns dynamic menu labels,
menu ID and an associated short ID. Selecting an entry sends those current IDs
and its selected index back. Re-read the live menu rather than hardcoding a
universal "complete delivery = entry 0" rule.

Sources: `netcommand/global/GlobalService.java:281-292`,
`netcommand/global/ReadMessenge.java:2610-2625`, `Model/Menu2.java:722-735`.

The server also exposes a caravan-specific shop path (`SHOP_VANTIEU`): wire/shop
subtype 13 maps to a separate UI mode. This is evidence of a caravan subsystem,
but does not identify this user's spawn item, price or delivery reward.
Source: `netcommand/global/ReadMessenge.java:1706-1727`.

## 5. Protecting the escort

Merchant/robber/knight PK modes have explicit constants 11/12/13 and special
caravan attack rules. White/black caravan variants participate in those rules.
Do not identify a robber or attacker from a monster's appearance alone.

Sources: `GameObjects/MainObject.java:265-267`,
`GameObjects/MainObject.java:437-439`, `GameObjects/Player.java:963-968`.

For attacks on the escort, the relevant event path is different from an attack
on the player:

- `firePK` -> `Fire_Object(message, 0, 0)`: player actor -> player target.
- `fireMonster` -> `Fire_Object(message, 0, 1)`: player actor -> monster target,
  which includes the caravan's category.

Sources: `netcommand/global/ReadMessenge.java:1142-1149`,
`netcommand/global/ReadMessenge.java:3346-3353`,
`netcommand/global/ReadMessenge.java:3419-3462`.

A defender should correlate category+ID with the owned caravan, then evaluate
whether and how to engage the attacker using normal game attackability rules.
Bound the chase to the escort; a long chase can defeat the escort objective.
Exclude the owned mob and other non-target escort actors from generic monster
selection. The existing auto-fight target loop is not an escort-protection AI.

## 6. Failure and completion evidence

Known observation points:

- Caravan HP/dead state via monster info, combat data and `dieMonster()`.
- Disappearance/removal versus map transition; neither alone proves death or
  success.
- Player death/disconnect and new map/area generation.
- Server dynamic menus/dialogs and inventory/currency changes around turn-in.

Source for monster death: `netcommand/global/ReadMessenge.java:1332-1374`.

**Published rules now establish:** follow-with-distance behavior, the merchant
delivery NPC and gem-source locations, a 30-minute trip, and waiting periods
after sale/expiry. **Not established at runtime:** exact spawn acknowledgement,
numeric leash distance, route connectivity, menu IDs/choices, cargo protocol,
current prices/reward amount, or the message sequence proving a successful sale.
A money increase alone may have another cause; disappearance may mean loss,
transfer or success. See the dated-rule distinctions in
[OFFICIAL_MERCHANT_RULES.md](OFFICIAL_MERCHANT_RULES.md).

## 7. Proposed controller

```text
IDLE
  -> VALIDATE_QUEST_ITEM_AND_START
  -> REQUEST_SPAWN_ONCE
  -> WAIT_FOR_OWNED_ESCORT
  -> WALK_ROUTE_SEGMENT <-> WAIT_FOR_ESCORT
  -> WAIT_MAP_READY_AND_REACQUIRE_ESCORT
  -> WALK_ROUTE_SEGMENT ...
  -> DESTINATION_INTERACTION
  -> VERIFY_DELIVERY_RESULT
  -> DONE (or another run if repetition is enabled and eligible)

active state -> DEFEND / HEAL_SELF / PAUSED / RECOVER / FAILED
```

The official guide confirms the caravan follows its owner and stops if the
owner is too far away. Use short lead segments and wait when it lags; measure
the actual allowable separation rather than deriving it from animation code.
At map edges, wait for both world readiness and reacquisition/continuity of the
owned escort before walking off again. Its entity ID may change between maps.

Route movement, defense and death recovery need one shared movement owner.
Keep self-potion use independent but preserve its existing cooldown. Recovery
after player death must first determine whether the caravan survived and can
still be resumed; reusing a saved player checkpoint is not enough by itself.

Start with a **quest recorder**: show owned escort identity/HP/distance,
current map/position, item used and live menu IDs/labels. Capture one normal
delivery's route and result. That provides the concrete data to turn this design
into a reliable quest-specific controller.

## 8. Remaining inputs for the first live route

1. Which animal and Dylan source location the user wants to automate.
2. In-game item name/category/ID and current animal-menu labels, ideally observed
   during one normal delivery.
3. Actual map connections, transition continuity, effective follow range and
   cargo/sale acknowledgements on the user's server.

The official guide already answers the broad feature identity, merchant turn-in
NPC and follow behavior; those no longer need to be asked of the user.
