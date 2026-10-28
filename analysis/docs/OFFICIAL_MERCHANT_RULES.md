# Official merchant/caravan guide — rules and automation implications

## Source and authority

- Official forum: [Hướng Dẫn - Chức Năng ĐI BUÔN](https://forum.knightageonline.com/app/index.php?for=forum&do=list&uid=1873839&sz=15&p=0)
- Author shown: **Tiên Cánh — Admin**.
- Retrieved: **2026-10-28**.
- The first admin post contains older feature instructions and an explicit
  adjustment effective **21 September 2022**. This document summarizes that
  admin post; it does not adopt player comments as rules.

This establishes published gameplay rules. It is not a runtime observation of
the user's server or proof that every current rule is implemented by this exact
4.0.3 client. Numeric item IDs, menu IDs, prices and map connections are not
given in the article.

## 1. Confirmed activity and merchant workflow

The described activity is **Đi buôn**: merchant trading/escorting goods. It is
not merely a standard kill/collect quest in `MainQuest`.

Published merchant flow:

1. Buy and wear merchant equipment from **Graham** in **Thung Lũng Kì Bí**
   (Mysterious Valley).
2. Use a merchant transport animal and buy permitted gems from **Dylan**.
3. Lead the animal back to **Graham** and sell the cargo.

The older notes say caravan animals and Dylan are available for this activity
in **area 6**, with that area restricted to merchant/robber/knight roles.
Area is a channel within a map, not map ID 6. The client displays `Area + 1`,
so displayed area 6 corresponds to stored value 5 in this representation;
confirm the current area response before issuing a transition.
Client reference: `Thread_More/LoadMap.java:486-488`.

### Published merchant animals and cargo

| Animal (guide name) | Minimum level in older notes | Allowed cargo | Capacity | Player-robbery statement |
| --- | --- | --- | --- | --- |
| Lừa vàng — golden donkey | 40 | Saphia | 6 gems | Cannot be attacked/robbed by other players |
| Lạc Đà vàng — golden camel | 61 | Saphia and Ruby | 12 gems in total | Can be attacked/robbed |
| Bò Rừng vàng — golden wild ox | 81 | Saphia, Ruby and diamonds | 24 gems in total | Can be attacked/robbed |

The article does not describe elephants, although the JAR's caravan class has
elephant templates. Do not extrapolate capacity or level requirements from the
class's white/black template groupings. Guide gold/brown names and code
white/black predicates are different naming layers.

### Dylan locations in the guide

| Location | Published stock |
| --- | --- |
| Cửa Biển | Saphia |
| Vách Đá Cheo Leo | Saphia and Ruby |
| Cổng Địa Ngục | Saphia, Ruby and diamonds |

Keep these official location names until correlated with actual map IDs on the
user's server. Similar English map names or world-map picture positions do not
establish route connectivity.

## 2. Follow behavior and loss/recovery rules

The admin post explicitly states:

- The animal follows its owner within a limited distance. If the owner moves
  too far away, it stops following.
- When the owner logs out, the animal remains standing there.
- If the animal dies, another transport animal can be used to try to recover
  the dropped gems.
- A loaded animal replaced by another transport animal is cancelled and its
  gems fall at that location; the cargo must be picked up again.
- Goods can be collected by eligible nearby players with a transport animal.

Consequences for automation:

- Use short lead segments and wait for the animal to catch up; merely holding
  forward at maximum player speed will not reliably complete the delivery.
- Measure a working follow distance on the phone/server; the article gives no
  numeric leash radius. The 3-4-tile code constants concern interpolation to the
  animal's own movement target, not that radius.
- Reconnect needs to reacquire the surviving animal before considering another
  summon. Missing from the current scene is not proof that it was destroyed.
- Replacing a loaded animal is a cargo-loss/recovery transition, not a harmless
  retry or a demonstrated way to restart the trip timer.
- Player death recovery must inspect both animal and cargo state. Returning
  only to a saved player position is insufficient.

## 3. Caravan actions: cargo, protection, heal, speed

The article lists animal-menu functions:

| Function | Published effect | Published limit |
| --- | --- | --- |
| Hành trang | View carried trading gems | Cargo is associated with the animal |
| Bảo hộ | Protect animal and owner from all attacks for 1 minute | Once per animal |
| Hồi máu | Fully heal animal and owner | Once per animal |
| Tăng tốc | Increase animal movement speed and permitted owner distance | Once per animal |

These are **not the same as ordinary bag HP/MP potions**. Treat each one-shot
action as a pending transaction and mark it consumed only after corroborating
server/menu/effect state. Persist/recover the state with the animal/trip identity;
a reconnect should not blindly assume the actions are unused.

Client correlation:

- Owned caravan interaction invokes `cmdInfoVantieu`.
- That sends `Dynamic_Menu(-56, 0, 0)` as arguments to outer command -30.
- Incoming dynamic menus provide live labels/IDs and selected-entry responses.
- `npcInfo` has a caravan-specific `SHOP_VANTIEU` wire subtype 13, mapped to
  UI mode 11 and a server-provided item vector. This is a strong candidate for
  the cargo display; correlate it with the real animal menu before treating it
  as a complete cargo snapshot.

Sources: `GameObjects/Player.java:957-961`,
`GameScreen/GameScreen.java:503-505`,
`netcommand/global/ReadMessenge.java:1706-1727`,
`netcommand/global/ReadMessenge.java:2002-2013`,
`netcommand/global/ReadMessenge.java:2610-2625`.

Do not assume `Item.VecInvetoryPlayer` contains the animal's trading cargo.
The extension needs separate **bag**, **escort**, and **cargo** state views.

## 4. Threats and related roles

The guide also documents:

- Robber equipment from **Frank** in **Hang Dơi** (Bat Cave).
- Robbers acquire gems by robbery rather than buying them and sell to Frank.
- Robber animal variants are brown; merchants and robbers cannot use each
  other's transport animals.
- Knights protect merchants and fight robbers. Player-robber kills can produce
  badges; NPC-robber kills do not have that same badge reward.
- NPC robbers are generated roughly once a minute during trade activity; the
  guide says they die if they do not fight anyone for one minute. It also says
  they are present in Bat Cave and attack merchants/knights entering there.
- Merchants/robbers can attack/rob one another under the documented role rules.

The golden donkey's published player-robbery protection does not eliminate
NPC-robber handling. Do not turn ordinary farming auto-fire loose during trade:
the owned escort itself is category 1, and prolonged combat chasing can separate
the player from the animal.

Client role constants 11/12/13 match the merchant/robber/knight grouping, and
player-versus-monster combat events can identify attacks on a category-1 caravan.
Sources: `GameObjects/MainObject.java:265-267`,
`GameObjects/Player.java:963-968`,
`netcommand/global/ReadMessenge.java:1142-1149`.

## 5. Timing: keep the dated update separate

### Earlier notes

The older section states the animal lives for 30 minutes from use. It also
describes role/score penalties and cargo loss on animal replacement/death.

### Explicit adjustment effective 21 September 2022

The admin post adds:

- A personal trade trip lasts **30 minutes**; on expiry, **all cargo is deleted**.
- Trip timing begins with the **first use of a transport animal**.
- A successful trip is completed by **selling the cargo**; wait **1 minute**
  before starting another trip.
- An unfinished trip that expires requires a **3-minute wait** before another
  trip. The post does not fully specify every early-abort/other failure case.
- The corresponding timing adjustments also apply to robbers.

For the controller, maintain a trip deadline separately from any particular
animal's lifetime. Use server confirmations where available and a conservative
local elapsed-time estimate otherwise. Replacing/summoning another animal is not
evidence that a fresh 30 minutes is available. Confirm repeat eligibility instead
of sending another summon request every frame during the waiting period.

The guide's instruction to clear RMS for updated price display is user-facing
historical advice; no saved data was cleared during this investigation.

## 6. Profit is not simply gross sale value

The guide's older notes describe a reduced sale value for goods not bought by
the merchant personally (reduced **by one third**, not necessarily to one third),
and a **50% sale-value reduction between 00:00 and 06:59**. The applicable server
clock/time zone and current application of these rules remain unmeasured.

Record actual purchase costs, transport cost, sale proceeds and elapsed time.
Do not hardcode a profit figure from capacity alone. If switching roles, the
guide says the old role's accumulated points are lost; role changes should not
be used as an incidental automation setup step.

## 7. Revised automation flow

```text
CHECK_REPEAT_ELIGIBILITY
  -> VERIFY_MERCHANT_ROLE_AND_AREA_6
  -> TRAVEL_TO_SELECTED_DYLAN
  -> SUMMON_ONCE_AND_IDENTIFY_OWN_ANIMAL
  -> BUY_ALLOWED_GEMS
  -> VERIFY_CARGO_AND_TRIP_DEADLINE
  -> LEAD_SHORT_SEGMENT <-> WAIT_FOR_ESCORT
       [map transition -> reacquire escort, confirm area/cargo]
       [threat -> bounded defense / one-shot protection or heal]
  -> ARRIVE_AT_GRAHAM
  -> SELL_CARGO_THROUGH_CURRENT_MENU
  -> VERIFY_SALE_AND_TRIP_COMPLETION
  -> WAIT_REPEAT_ELIGIBILITY
```

The exact ordering of walking to Dylan and summoning can be optimized after
observing the start restrictions; do not consume the timed animal earlier than
needed. The movement graph, follow distance, item IDs, cargo messages, one-shot
menu choices and sale acknowledgement still need runtime correlation.

**Suggested first implementation target:** the published golden-donkey/Saphia
route, if the user's level and server offer it. Its smaller capacity and stated
player-robbery protection reduce the first controller's complexity. Measure net
profit and verify live rules before selecting a higher-capacity route.
