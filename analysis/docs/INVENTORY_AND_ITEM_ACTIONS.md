# Inventory listing, potion automation, use, discard and sell

## 1. Capability summary

| Desired feature | Client support found | Integration work |
| --- | --- | --- |
| List bag contents | `Item.VecInvetoryPlayer` and lookup helpers | Snapshot/export selected fields |
| List equipped items | `Item.VecEquipPlayer` hashtable | Separate equipment view |
| List chest contents | `Item.VecChestPlayer` | Requires loaded chest data and normal access context |
| Auto HP/MP | Already implemented in `Player.updatePlayer()` | Expose settings or add a richer selection policy |
| Use consumable | `GlobalService.Use_Potion(short)` | Validate subtype, cooldown, count and current state |
| Equip/use item | UI chooses category-specific path | Preserve slot/category semantics |
| Use material | `GlobalService.doUseMaterial(short)` | Reuse only for supported material types |
| Discard unwanted item | `delete_Item(category, id, 0)` through normal inventory UI | Define exact filters and verify server result |
| Sell unwanted item | Same routine, mode 1, from NPC-shop flow | Preserve shop context and sell eligibility |

These are original internal calls, not an official external API. Listing/actions
are feasible from a helper inserted into the game. Server acceptance remains
authoritative.

## 2. Enumerating inventory and item identity

Original fields preserve some spelling mistakes:

```java
GameObjects.Item.VecInvetoryPlayer  // bag: CLib.mVector
GameObjects.Item.VecEquipPlayer    // equipped: CLib.mHashtable
GameObjects.Item.VecChestPlayer    // chest: CLib.mVector
```

`Item.getItemInventory(int category, short id)` searches by **both category and
ID**. `getItemVec()` and `getItemChest()` follow the same model. Source:
`GameObjects/Item.java:630-654`.

A read-only traversal inside the game's context can inspect each `Item`:

```java
for (int i = 0; i < Item.VecInvetoryPlayer.size(); i++) {
    Item item = (Item) Item.VecInvetoryPlayer.elementAt(i);
    // Copy ID/category, name, quantity and attributes into a snapshot.
    // Do not issue inventory mutations while traversing this live vector.
}
```

Available fields include:

| Field | Meaning/use |
| --- | --- |
| `Id`, `ItemCatagory` | Operation identity; not the vector index |
| `IdTem`, `imageId` | Template and image identifiers; not interchangeable with `Id` |
| `itemName`, `itemNameExcludeLv` | Display names, potentially incomplete/localized |
| `numPotion` | Stack quantity field reused for potions, materials and quest items |
| `typePotion` | Potion subtype: 0 HP, 1 MP; other values have other behavior |
| `colorNameItem`, `tier` | Color/rarity-style code and upgrade tier |
| `LvItem`, `classcharItem`, `type_Only_Item` | Requirement/category-specific equipment information |
| `mInfo` | Attribute IDs/values, where supplied |
| `isLock`, `canSell`, `canTrade` | Distinct item flags; not equivalent capabilities |
| `Selling`, `can_sell_for_other_player` | Additional store/trading state |
| `timeUse`, fashion-time fields | Time-limited item information |

Sources: `GameObjects/Item.java:37-105`,
`GameObjects/MainItem.java:22-35`, `GameObjects/MainItem.java:99-158`,
`GameObjects/MainItem.java:231-259`.

Do not treat an equipment record's default `numPotion` value as its stack size.
Normalize quantity by category. Keep template IDs separate from instance IDs,
and re-resolve `(category, Id)` before an action because vectors can be sorted
and item records can be replaced by server updates.

## 3. Where bag contents come from

Incoming command **16** calls `charInventory()` and then
`update_InVen_Or_Chest(message, Item.VecInvetoryPlayer, 0)`.
The parser contains update modes:

- **0:** replace a category's contents, not necessarily the entire bag.
- **1:** add/update an item; some records are replaced with a new object.
- **2:** remove an item matched by category and short ID.

Its player-inventory prefix also updates coin/gold. Potion names, effects and
icons can depend on separately loaded template data. Some material records are
initially placeholders while a template is requested.

Sources: `netcommand/global/ReadMessenge.java:1598-1600`,
`netcommand/global/ReadMessenge.java:4531-4573`,
`netcommand/global/ReadMessenge.java:4628-4678`,
`netcommand/global/ReadMessenge.java:4713-4719`,
`netcommand/global/ReadMessenge.java:4799-4829`.

The parser's `MainTemplateItem.isload` flag is set true at method exit even
after its caught exceptions. It is not by itself proof that every category or
metadata record has loaded successfully. Track received categories and a local
inventory revision if the helper needs a reliable snapshot.

## 4. HP/MP automation is already present

`Player.updatePlayer()` checks, while not dead, every fifth game tick:

```text
HP percent below MsgDialog.mHPMP[0]
  -> HP potion hotkey at mhotkey[levelTab][4]
  -> lookup category 4 item
  -> potion cooldown <= 0
  -> Use_Potion(item.Id)
  -> start local 2000 ms cooldown

MP percent below MsgDialog.mHPMP[1]
  -> MP potion hotkey at mhotkey[levelTab][3]
  -> corresponding lookup/cooldown/use
```

The condition is **strictly below**, not below-or-equal. At the nominal 25 FPS,
checking every five ticks is approximately 200 ms; actual timing depends on
device load. HP and MP have separate cooldown entries.

Source: `GameObjects/Player.java:295-314`.

The UI sets `Player.isAutoHPMP`, clears potion hotkey types, runs the potion
selection routine, and saves auto/hotkey settings. Default thresholds are
50/50; UI adjustment paths cover 10-90 in ten-percent steps.

Sources: `InterfaceComponents/MsgDialog.java:222-230`,
`InterfaceComponents/MsgDialog.java:1592-1616`,
`InterfaceComponents/MsgDialog.java:1960-1960`,
`GameObjects/MainItem.java:380-395`.

The existing selection routine compares numeric item IDs for its priority;
do not assume that guarantees the strongest or most efficient potion. Confirm
the selected hotkeys and counts after settings/inventory load. A richer policy
could reserve a minimum stock and select by known effect/requirement data.

### Actual use request and acknowledgement

`GlobalService.Use_Potion(short id)` sends command **32** with a short item ID.
Incoming `use_Potion()` identifies an actor/category and potion ID. For the
local actor it decrements the bag count and removes an exhausted stack; it also
updates HP/MP according to the message subtype.

Sources: `netcommand/global/GlobalService.java:319-328`,
`netcommand/global/ReadMessenge.java:2858-2879`,
`netcommand/global/ReadMessenge.java:2911-2921`.

An extension should share the existing cooldown and track in-flight actions,
then observe server updates rather than optimistically subtracting quantities
again. Require valid positive maxima before percentage calculations and a
ready living character. Avoid running two independent auto-potion loops.

## 5. Other item-use paths

The ordinary inventory `Use` action dispatches by category:

- Quest items (5) are refused in this path.
- Potions/consumables (4) use `Use_Potion`, with subtype-specific exceptions.
- Materials (7) can use `doUseMaterial(short id)`, command **-107**.
- Equipment uses `Use_Item(byte itemId, byte equipSlot)` after mapping its
  `type_Only_Item` to an equipment slot. Rings prompt for slot 3 or 9.

Sources: `InterfaceComponents/TabShopNew.java:494-568`,
`InterfaceComponents/TabShopNew.java:656-665`,
`netcommand/global/GlobalService.java:307-328`,
`netcommand/global/GlobalService.java:1140-1149`.

**There is no universal `Use_Item(category, fullItemId)` signature.** That
particular routine writes two bytes, and its callers use item ID then slot.
The material/potion routines use shorts. Preserve actual caller behavior and
ID widths rather than routing every item through the function's English name.

## 6. Discard versus sell

Confirmed original call chain:

```text
inventory discard menu -> confirmation -> TabShopNew command 5
  -> delete_Item(item.ItemCatagory, item.Id, 0)

NPC-shop sell -> TabShopNew command 10
  -> delete_Item(item.ItemCatagory, item.Id, 1)
```

Sources: `InterfaceComponents/TabShopNew.java:476-492`,
`InterfaceComponents/TabShopNew.java:618-654`,
`InterfaceComponents/TabShopNew.java:1271-1285`.

Command **18** outgoing contains:

```text
byte category
short itemId
byte mode     // 0 discard, 1 sell, as established by normal UI callers
```

Source: `netcommand/global/GlobalService.java:330-341`.

There is **no quantity argument**. Single-unit versus full-stack behavior needs
an observed server result; do not invent a `discard(category, id, quantity)`
packet from this method. Likewise the UI's discard action is not proof that it
creates a recoverable drop on the ground. Selling is offered in the NPC-shop
context with `canSell == 1`; calling its packet elsewhere does not establish
that the server will accept it.

The client also has bulk selling for color-code 0 or 1 equipment with `tier == 0`.
This is a useful existing filtering example, not a general unwanted-item policy.
Source: `InterfaceComponents/TabShopNew.java:723-739`.

### Proposed unwanted-item policy

Use an explicit predicate over category, item/template ID, color, upgrade tier,
requirements and known flags. Keep equipped items separate, protect user-pinned
items and reserved potion stock, and skip incomplete/unknown metadata. For
example: "only unupgraded equipment of these color codes, excluding these IDs"
is more precise than "anything not matching my class".

Copy candidate identities from the bag; re-resolve and re-evaluate each one
before sending. Handle one pending mutation at a time and wait for a matching
inventory result or timeout. Incoming command-16 updates can remove/replace
records; there is no need to remove an item locally to simulate success.

Chest transfer is a separate routine with an explicit quantity parameter;
do not copy that format into discard. Source:
`InterfaceComponents/TabShopNew.java:667-705`.

## 7. Proposed helper-facing API

These names describe a facade we could build, not symbols exported by the game:

```text
Inventory.snapshot()                 -> copied item rows + revision/freshness
Inventory.find(category, id)          -> revalidated current row
Inventory.configureAutoPotion(...)   -> existing settings/cooldown integration
Inventory.requestUse(category, id)    -> appropriate native action path
Inventory.requestDiscard(category,id)-> original mode-0 request + result tracking
Inventory.requestSell(category,id)   -> normal shop context + mode-1 request
```

The first useful inventory milestone is a read-only on-screen list showing
ID/category/name/count/rarity/tier and the selected HP/MP potion slots. That
verifies field meanings on this server before enabling rules that consume or
remove items.
