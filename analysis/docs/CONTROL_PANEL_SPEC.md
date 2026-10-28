# Configurable merchant controller and animal-death recovery

## Status and acceptance target

This is an implementation specification, **not an already injected panel**.
The user's requirements are:

- Choose the source location/vendor for buying gems.
- Choose gem types and a quantity for each type.
- Trade without a separately hardcoded automation program per route.
- If the animal dies and recoverable cargo drops, summon a replacement and
  recollect the gems before deciding whether to continue.
- Run on Nokia Asha 311, with visible status and touch controls.

The official guide confirms animal-death cargo recovery. It separately states
that trip expiry deletes cargo. A controller must distinguish those outcomes.
See [the source rules](OFFICIAL_MERCHANT_RULES.md).

## 1. Proposed touch panel

```text
MERCHANT AUTOMATION

Buy from:       [Dylan — Cửa Biển                  v]
Sell to:        [Graham — Thung Lũng Kì Bí          ]
Animal:        [Selected inventory transport item v]

Requested cargo — target amounts, including cargo already loaded
  Saphia:      [ 6 ]
  Ruby:        [ 0 ]
  Diamond:     [ 0 ]
  Total:         6 / known capacity

Recovery
  [x] Recover dropped cargo after animal death
  Replacement animal: [same kind / selected compatible item]
  Maximum replacement attempts: [ 2 ]

Repeat: [one trip / N trips / until stopped]
HP/MP settings: [open existing potion configuration]
Escort protection/heal/speed: [configured trigger policy]

[Validate] [Save] [Start] [Pause] [Stop]
[Record missing route] [Map/cache status]
```

Initial source presets come from the published Dylan locations, but actual
vendor identity, stock item IDs and map routes are discovered/recorded data.
Keep the location names configurable for localization and server differences.
Graham is the documented merchant destination; a future generalized destination
selector needs an independently recorded valid selling flow, not an arbitrary
choice of any NPC on the map.

### Separate configuration from live bindings

Store stable user choices: server/character scope, source key, gem selections,
target quantities, allowed transport items and recovery/repetition settings.

Bind transient values at runtime: current NPC/entity ID, shop/menu IDs, item IDs
and categories from the current stock response, prices, available quantity,
current cargo, trip identity/deadline, and map/area generation.

After reconnect, rebind and validate. Never persist a selected shop-vector index
as if it permanently identified a gem or NPC.

## 2. Quantity and compatibility validation

For each gem type, define the requested number as a **target cargo count**, not
"buy this many extra every time Start is pressed".

```text
remainingToBuy[type] = max(0, targetCount[type] - confirmedCargoCount[type])
```

Validate:

1. Counts are nonnegative integers; each request fits the original positive
   short quantity encoding; total counts do not overflow arithmetic.
2. The selected animal accepts each gem type and has enough free capacity.
3. Fresh stock from the chosen vendor contains the requested goods.
4. Actual price/currency and available funds permit the purchase.
5. Merchant role, displayed area 6 and known route are appropriate.
6. The trip has enough estimated time left, or the panel reports why it cannot
   validate that estimate yet.

Guide defaults: donkey Saphia/6; camel Saphia+Ruby/12; wild ox all three/24.
Treat these as published presets, not permission to ignore changed live stock or
unrecognized animals such as the elephant templates in the JAR.

Use one pending purchase at a time. Correlate the cargo response before issuing
the next quantity, so a delayed response/retry does not buy the order twice.
If the cargo is recovered rather than repurchased, recompute the remainder from
the confirmed replacement-animal cargo, preserving the user's original targets.

### Concrete buy hook

The original inventory-shop quantity UI calls:

```java
GlobalService.gI().buy_item((byte)this.IDTab, (short)item.Id, (short)quantity);
```

Command 24 writes a shop/tab byte, item short and quantity short. This is an
existing quantity-capable routine, but does not establish that every caravan
purchase accepts the same context without the normal NPC/menu flow.

Sources: `InterfaceComponents/TabShopNew.java:450-463`,
`netcommand/global/GlobalService.java:244-255`.

## 3. Separate bag, animal and cargo views

- **Bag:** `Item.VecInvetoryPlayer`; contains summon items and other personal gear.
- **Animal:** current owned caravan actor, HP, position, category/ID and freshness.
- **Cargo:** current animal's goods/capacity and any pending purchase/pickup/sale.

The server-provided caravan list in `npcInfo` subtype 13/UI mode 11 is a cargo
display candidate. It is passed into a shop-style view, not automatically the
ordinary bag vector. Confirm the live response when opening animal inventory.

Sources: `netcommand/global/ReadMessenge.java:1706-1727`,
`netcommand/global/ReadMessenge.java:2002-2013`,
`InterfaceComponents/TabShopNew.java:169-191`.

Do not infer cargo counts by counting bag gems, or assume a bag inventory update
acknowledges a cargo purchase. Preserve incomplete/freshness status explicitly.

## 4. Animal death and recollection

Proposed recovery sequence:

```text
OWNED_ANIMAL_DIED
  -> SNAPSHOT_CARGO_LOSS_AND_LOCATION
  -> CONFIRM_TRIP_HAS_NOT_EXPIRED
  -> FIND_COMPATIBLE_REPLACEMENT_IN_BAG
  -> SUMMON_ONCE
  -> WAIT_FOR_NEW_OWNED_ANIMAL
  -> IDENTIFY_RECOVERABLE_GEM_DROPS
  -> MOVE_WITHIN_PICKUP_RANGE
  -> REQUEST_PICKUP_ONE_AT_A_TIME
  -> VERIFY_NEW_ANIMAL_CARGO
  -> RESUME_ROUTE / RETURN_WITH_PARTIAL_CARGO / STOP_WITH_REASON
```

This recovery is different from replacing a living loaded animal. The latter
causes the old animal to be cancelled and cargo to drop, according to the guide;
it should not happen as an incidental retry.

Keep the old trip deadline separate from the per-animal ability journal. The
guide makes protection/heal/speed once-per-animal actions: a confirmed new animal
needs its own ability record, while reconnecting to the same animal must not
reset its consumed actions. Rebind available actions from the current menu/server
state. A new animal instance is not proof of a fresh 30-minute trip.

### Drop and pickup hooks

`ItemDrop()` creates `Item_Drop` actors with a separate ground entity ID,
category, image/template reference, name and position. The original protocol
uses `Get_Item_Map(short groundEntityId, byte category)` for a pickup request.
`GetItemMap()` identifies the picking actor and animates/removes the drop.

Sources: `netcommand/global/ReadMessenge.java:2701-2759`,
`GameObjects/Item_Drop.java:22-48`,
`netcommand/global/GlobalService.java:343-353`.

These are useful generic hooks. We have **not yet identified which category and
template IDs represent this server's spilled trading gems**, or which cargo
message confirms loading them. The official gameplay capability is established;
the exact wire mapping remains to be recorded.

Correlate candidate drops with gem metadata, the dead animal/location, known
pre-loss cargo and current trip generation. World entity ID is not a summon-item
or bag-item ID. Do not interpret generic auto-loot success as confirmation that
the replacement animal carries the recovered goods.

Other players can collect cargo, drops can disappear, and the animal may be
temporarily missing during a map transition. Missing actor != death;
missing drop != recovered cargo; pickup-animation event != confirmed full load.

## 5. Runtime controller

```text
STOPPED -> VALIDATING -> WAIT_ELIGIBILITY -> TRAVEL_TO_SOURCE
  -> SUMMONING -> BUYING -> VERIFY_CARGO -> ESCORTING
  -> ARRIVING -> SELLING -> VERIFY_COMPLETION -> COOLDOWN

ESCORTING -> ANIMAL_DEATH_RECOVERY -> VERIFY_CARGO -> ESCORTING
any active state -> USER_PAUSED / DISCONNECTED / BLOCKED / EXPIRED
```

Only one component owns movement: travel, escort catch-up, pickup recovery,
defense or player-death recovery. Use bounded chase/progress/retry behavior.
Keep HP/MP automation on its existing cooldowns. Trip expiry stops recollection
of presumed deleted cargo and enters the appropriate observed wait state.

Sell confirmation must correlate live menu/result state and cargo/proceeds;
do not equate walking near Graham with a completed trip. Cooldown eligibility
comes from the server where possible, with the published timings as expectations.

## 6. Asha UI and persistence implementation

A standard MIDP `Form` with `ChoiceGroup`, numeric `TextField`s and `Command`s
is a practical initial touch panel. It can return to the original `TemCanvas`.
An in-game status/stop button should remain available during operation.

Opening a separate Form hides the Canvas but does not automatically stop this
game's update thread. `TemCanvas.run()` continues looping; `pauseApp()` is empty.
Pause controller actions, clear its owned movement intent and release device
requests while configuring. Keep networking alive and display elapsed trip time;
opening the panel cannot pause a server-side delivery deadline.

Sources: `com/silverknight/TemCanvas.java:59-77`,
`com/silverknight/TemMidlet.java:64-72`, `Main/GameCanvas.java:300-336`.

UI callbacks enqueue configuration/action changes for the game-update owner;
they do not directly issue shop transactions from another thread.

Use separately versioned local RMS records for preferences, route/cache index,
and resumable trip information. Avoid the game's server-backed settings blobs.
The game's `CheckDelRMS()` allows deleting most names, including new extension
records by default. A clear-cache/RMS operation can therefore erase the new
data unless the integration deliberately preserves its namespace or provides
an export/import strategy. Source: `Model/CRes.java:643-645`.
