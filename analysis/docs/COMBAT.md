# Combat and hostile-player automation

## 1. Existing automation is primarily monster-oriented

The player already supports auto HP/MP use, loot filters, buffs, and auto-fire.
`setAutoFire()` rejects targets whose `typeObject != 1`, and `nextMonster()`
searches for monsters. Merely assigning a player to `GameScreen.ObjFocus` while
enabling that loop can cause it to choose a monster instead.

Sources: `GameObjects/Player.java:295-314`, `GameObjects/Player.java:452-504`,
`GameObjects/Player.java:507-531`, `GameObjects/Player.java:575-649`.

Auto-fire modes are not a boolean: values -1, 0 and 1 interact with
`isCurAutoFire`, manual input, current origin, stamina and transition resets.
`setCurAutoFire()` sets 0 or -1 depending on that preference.
Sources: `GameObjects/Player.java:1298-1322`,
`GameObjects/Player.java:1403-1407`, `GameObjects/Player.java:1566-1568`.

## 2. Normal player attack chain

```text
valid GameScreen.ObjFocus
  -> Player.setActionHotKey(slot, manualFlag)
     -> skill learned? cooldown expired? enough MP? valid action/target?
     -> Player.setFirePlayer(target)
     -> Player.setStartSkill(skillId, manualFlag)
     -> ListSkill.setFireSkill(...)
        -> range / move-into-range / animation / local cost and delay
        -> updateEffSkill() at the appropriate animation frame
        -> sendSkill()
           -> fire_monster(...) for category 1
           -> fire_Pk(...) for category 0
```

Sources: `GameObjects/Player.java:948-1107`, `Skill/ListSkill.java:37-151`,
`Skill/ListSkill.java:190-224`, `Skill/ListSkill.java:401-410`.

Outgoing command **6** serializes skill byte, target-count byte and short target
IDs. Command **9** is the monster variant. Skill data includes range, MP cost,
cooldown, perform duration and multi-target range/count. A future extension
should use the normal hotkey/skill pipeline rather than just repeating packet 6.

Sources: `netcommand/global/GlobalService.java:519-533`,
`netcommand/global/GlobalService.java:136-150`,
`netcommand/global/ReadMessenge.java:2790-2835`.

`ListSkill.canAttack()` uses `limitTimeAtt`, initialized to 600 ms. Other timing
gates use skill cooldowns, animation frames and `GlobalCountdown`. The 600 ms
test is not proof that every skill can legally execute every 600 ms, and the
hotkey path should not be assumed to enforce every scheduler-level gate itself.
Source: `Skill/ListSkill.java:190-211`, `Skill/ListSkill.java:391-428`.

## 3. Which player is attackable?

`Player.setFirePlayer(MainObject)` is the central predicate, but not a pure
permission table. It contains early exceptions and may show a message for some
non-player cases. Pass a non-null, current entity: it dereferences `canfire()`
before its later null check.

Observed branches include:

- Special `canfire()` and sacred-fire cases before ordinary map rules.
- Village/safe-map restriction through `loadmap.mapLang()`.
- Stun/freeze/dead-state restrictions, with some special-case ordering.
- Team-color PK modes 1-9 prevent same-color targeting in relevant branches.
- Clan restrictions for certain special actors.
- PK-score threshold and special merchant/robber/knight modes 11/12/13.
- Mode 10 uses membership in `Player.vecPlayerPk` for one branch.
- `typePk == 0` participates in ordinary open-PK logic.

Source: `GameObjects/Player.java:1216-1295`.

The UI's open-PK toggle sends `set_Pk(0)` to enable it and `set_Pk(-1)` to
disable it. The incoming command-42 handler updates `typePk`, local score and
caption. This establishes the role of mode 0 better than translating its name
alone. Sources: `GameScreen/GameScreen.java:404-410`,
`GameScreen/GameScreen.java:488-491`,
`netcommand/global/ReadMessenge.java:3394-3417`.

**Attackable is not the same as hostile.** The normal predicate can allow more
players than the user intends to fight.

## 4. Signals available for retaliation

| Signal | Meaning established by code | Limit |
| --- | --- | --- |
| Incoming 6 / `firePK` -> `Fire_Object` | Actor ID, skill, target IDs and resulting combat data | Actor must be known; ordinary handler ignores events before map-ready |
| Incoming 41 / `diePlayer` | Victim, related source/category, PK points, dead state | Insufficient by itself to reconstruct every preceding attack |
| Incoming 42 / `pk` | Actor PK-mode update | Not proof that this actor attacked the user |
| Incoming 57 / `List_Pk` | Replaces list of short player IDs used by mode-10 logic | Do not label this a universal murderer list |
| Incoming -95 / `updateMarkKiller` | Several subtypes; subtype 0 sets an actor's `markKiller` | Other subtypes concern arena objects/effects; not a universal criminal flag |

Sources: `netcommand/global/GlobalMessageHandler.java:273-315`,
`netcommand/global/ReadMessenge.java:3346-3447`,
`netcommand/global/ReadMessenge.java:3842-3852`,
`netcommand/global/ReadMessenge.java:5817-5839`.

The `markKiller` value is also painted as arena HUD information. Do not implement
`markKiller > 0 => murder offender` from the English identifier.
Source: `GameScreen/PaintInfoGameScreen.java:1817-1861`.

### Combat event shape useful for an observer

`Fire_Object(message, actorCategory, targetCategory)` reads actor short ID,
skill byte, target-count byte, then each target's short ID, two int HP-related
values and additional-effect arrays. It finally reads actor HP/MP and more
effect information. `firePK` passes categories `(0, 0)`.

Sources: `netcommand/global/ReadMessenge.java:3346-3353`,
`netcommand/global/ReadMessenge.java:3419-3503`.

**Proposed observation:** after a successful parse, record an immutable event
when a different player actor includes the active player's ID in the target
set. Include map/area/generation and time. Distinguish an attack attempt from
confirmed damage: a miss, zero damage, buff/debuff, or area effect needs explicit
classification rather than a simple `hp changed` rule.

## 5. Retaliation policy proposal

Make the policy explicit and separately configurable:

1. **Recent attacker**: the player that targeted/damaged this character within
   a short window, in the current map generation.
2. **Last killer**: retain source identity from a death event as information;
   reacquire/validate it on a later map rather than keeping a stale reference.
3. **Open-PK player nearby**: broader opt-in policy based on mode, score and
   context, not equivalent to personal retaliation.
4. **Named list**: user-chosen identities, scoped to server; display names and
   transient entity IDs are not interchangeable permanent identities.

Recommended default: recent-attacker retaliation with bounded chase radius and
time. Each step rechecks that the target is current, alive, visible/known,
not self, and attackable through the normal predicate. Recheck clan/team rules,
status, MP and cooldown, and let skill movement own its chase path.

Pause competing monster auto-fire, loot movement and travel while retaliation
owns control. Drop the target on death, disappearance, foreground loss,
disconnect, map/area transition, expiry or user cancellation. Restore previous
activity only after revalidating its state and origin.

This pass maps hooks and policy choices; it does not establish server-side
damage formulas, every mode's gameplay meaning, or a working player-fight bot.
