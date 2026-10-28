/*
 * Decompiled with CFR 0.152.
 */
package GameObjects;

import CLib.mGraphics;
import CLib.mHashtable;
import CLib.mImage;
import CLib.mSystem;
import CLib.mVector;
import GameObjects.CatalogyMonster;
import GameObjects.MainObject;
import GameObjects.Minuong;
import GameObjects.MonsterBox;
import GameObjects.MonsterFly;
import GameObjects.MonsterQuest;
import GameObjects.MonsterWalk;
import GameObjects.Monsterplus;
import GameObjects.Monstervantieu;
import GameObjects.ObjectData;
import GameObjects.Object_Effect_Skill;
import GameScreen.GameScreen;
import Main.GameCanvas;
import Model.CRes;
import Model.MainImage;
import Thread_More.LoadMap;

public class MainMonster
extends MainObject {
    public static final int MONSTER_MOVE_012 = 0;
    public static final int MONSTER_MOVE_01 = 1;
    public static final int MONSTER_MOVE_0102 = 2;
    public static final int MONSTER_MOVE_FLY_012 = 3;
    public static final int MONSTER_MOVE_WATER = 4;
    public static final int MONSTER_MOVE_FLY_0102 = 5;
    public static final int MONSTER_SEN_CHUA = 6;
    public static final int MONSTER_QUEST = 7;
    public static final int MONSTER_BO_CHUA = 8;
    public static final int MONSTER_BACH_TUOC_TRANG = 9;
    public static final int MONSTER_MOVE_FLY_012_SLOW = 10;
    public static final int MONSTER_HOUSE = 12;
    public static final int MONSTER_BOX = 13;
    public static final int MONSTER_VANTIEU = 14;
    public static final int MONSTER_MI_NUONG = 15;
    public static final int MONSTER_Boss_Medusa = 101;
    public static final int MONSTER_Boss_DeVang = 83;
    public static final int MONSTER_Boss_DeBac = 84;
    public static final int MONSTER_GATE = 16;
    public static final int MONSTER_WALK_CAN_NOT_MOVE = 17;
    public static final int MONSTER_PAINT_BY_EFFECT_AUTO = 18;
    public static final int MONSTER_FLY_CAN_NOT_MOVE = 19;
    byte[][][] mAction;
    public int timeAutoAction;
    public int catalogyMonster;
    public int timeRemove;
    public int limitMove;
    public int limitAttack;
    public int timeFreeFire;
    public int xAnchor;
    public int yAnchor;
    public int xPhoBang;
    public int yPhoBang;
    int vxDie;
    int vyDie;
    int vyStyleDie;
    int vyStyleStand;
    int imageId;
    public static mHashtable HashImageMonster = new mHashtable();
    public static mVector VecCatalogyMonSter = new mVector("MainMonster VecCatalogyMonSter");
    public int MonWater;
    public int frameDie;
    public int timeBienmat;
    int wAvatar;
    int hAvatar;
    int nFrame;
    int hRegion;
    public int timeMaxMoveAttack;
    public int timeRemoveGhost = 0;
    public long timeBeginMoveAttack;
    public mVector vecObjskill = null;
    public byte loopAtack;
    public String nameowner = "";
    public static short[] idBossNew = new short[]{104, 103, 105, 106, 135};
    int count;

    public void setIDeffect(byte by) {
    }

    public void setLvmonster(int n) {
    }

    public void setPaintCicle(boolean bl) {
    }

    public boolean isBossNew() {
        for (int i = 0; i < idBossNew.length; ++i) {
            if (this.catalogyMonster != idBossNew[i]) continue;
            return true;
        }
        return false;
    }

    public void setTimelive(long l) {
    }

    public static void createMonster(int n, int n2, int n3, int n4) {
        CatalogyMonster catalogyMonster = MainMonster.getCatalogyMonster(n4);
        if (catalogyMonster == null) {
            mSystem.println("khong lay dc catalogy " + n4);
        }
        switch (catalogyMonster.typeMove) {
            case 0: 
            case 1: 
            case 2: 
            case 4: 
            case 6: 
            case 9: {
                GameScreen.addPlayer(new MonsterWalk(n, catalogyMonster.id, catalogyMonster.typeMove, catalogyMonster.name, n2, n3, catalogyMonster.maxHp, catalogyMonster.lv));
                break;
            }
            case 3: 
            case 5: 
            case 8: 
            case 10: {
                GameScreen.addPlayer(new MonsterFly(n, catalogyMonster.id, catalogyMonster.typeMove, catalogyMonster.name, n2, n3, catalogyMonster.maxHp, catalogyMonster.lv));
                break;
            }
            case 7: {
                GameScreen.addPlayer(new MonsterQuest(n, catalogyMonster.id, catalogyMonster.typeMove, catalogyMonster.name, n2, n3, catalogyMonster.maxHp, catalogyMonster.lv));
                break;
            }
            case 12: {
                GameScreen.addPlayer(new MonsterWalk(n, catalogyMonster.id, 12, catalogyMonster.name, n2, n3, catalogyMonster.maxHp, catalogyMonster.lv));
                break;
            }
            case 13: {
                if (catalogyMonster.id == 110) {
                    GameScreen.addPlayer(new Monsterplus(n, catalogyMonster.id, 13, catalogyMonster.name, n2, n3, catalogyMonster.maxHp, catalogyMonster.lv));
                    break;
                }
                GameScreen.addPlayer(new MonsterBox(n, catalogyMonster.id, 13, catalogyMonster.name, n2, n3, catalogyMonster.maxHp, catalogyMonster.lv));
                break;
            }
            case 14: {
                GameScreen.addPlayer(new Monstervantieu(n, catalogyMonster.id, 14, catalogyMonster.name, n2, n3, catalogyMonster.maxHp, catalogyMonster.lv));
                break;
            }
            case 15: {
                GameScreen.addPlayer(new Minuong(n, catalogyMonster.id, 14, catalogyMonster.name, n2, n3, catalogyMonster.maxHp, catalogyMonster.lv));
                break;
            }
            case 16: {
                GameScreen.addPlayer(new MonsterWalk(n, catalogyMonster.id, 16, catalogyMonster.name, n2, n3, catalogyMonster.maxHp, catalogyMonster.lv));
                break;
            }
            case 17: {
                GameScreen.addPlayer(new MonsterWalk(n, catalogyMonster.id, 17, catalogyMonster.name, n2, n3, catalogyMonster.maxHp, catalogyMonster.lv));
                break;
            }
            case 19: {
                GameScreen.addPlayer(new MonsterFly(n, catalogyMonster.id, 19, catalogyMonster.name, n2, n3, catalogyMonster.maxHp, catalogyMonster.lv));
                break;
            }
            case 18: {
                GameScreen.addPlayer(new MonsterWalk(n, catalogyMonster.id, 18, catalogyMonster.name, n2, n3, catalogyMonster.maxHp, catalogyMonster.lv));
                break;
            }
            default: {
                GameScreen.addPlayer(new MonsterWalk(n, catalogyMonster.id, 0, catalogyMonster.name, n2, n3, catalogyMonster.maxHp, catalogyMonster.lv));
            }
        }
    }

    public void paintAvatarFocus(mGraphics mGraphics2, int n, int n2) {
        MainImage mainImage = ObjectData.getImagePartMonster((short)this.catalogyMonster);
        if (mainImage.img != null) {
            if (this.wAvatar <= 0) {
                if (this.wOne < 0) {
                    this.hOne = mImage.getImageHeight(mainImage.img.image) / this.nFrame;
                    this.wOne = mImage.getImageWidth(mainImage.img.image);
                }
                this.wAvatar = this.wOne;
                this.hAvatar = this.hOne;
                if (this.wAvatar > 26) {
                    this.wAvatar = 26;
                }
                if (this.hAvatar > 26) {
                    this.hAvatar = 26;
                }
            }
            mGraphics2.drawRegion(mainImage.img, this.wOne / 2 - this.wAvatar / 2, 0, this.wAvatar, this.hAvatar, 0, n, n2, 3, false);
        }
    }

    public void update() {
        if (this.isDie && this.Action != 4) {
            this.Action = 4;
            this.timedie = GameCanvas.timeNow;
        }
        if (this.isServerControl && this.isMove) {
            this.move_to_XY();
        }
        this.updateDataEffect();
        super.update();
    }

    public void paint(mGraphics mGraphics2) {
        this.paintDongBang(mGraphics2);
        this.paintEffauto(mGraphics2, this.x, this.y);
    }

    public void updateAction() {
        if (this.isMoveOut) {
            return;
        }
        ++this.f;
        if (this.f > this.mAction[this.Action][this.Direction > 2 ? 2 : this.Direction].length - 1) {
            this.f = 0;
            if (this.Action == 3) {
                this.Action = 0;
                this.vx = 0;
                this.vy = 0;
            }
            if (this.Action == 2) {
                if (!this.isBossNew()) {
                    this.Action = 0;
                    this.vx = 0;
                    this.vy = 0;
                } else {
                    if (this.loopAtack >= 0) {
                        this.loopAtack = (byte)(this.loopAtack - 1);
                    }
                    if (this.loopAtack <= 0) {
                        this.Action = 0;
                        this.vx = 0;
                        this.vy = 0;
                    }
                }
            }
        }
        if (this.Action == 1 && this.vx == 0 && this.vy == 0) {
            this.Action = 0;
            this.f = 0;
        }
        if (this.timeFreeFire > 0) {
            --this.timeFreeFire;
        }
        if (!(!this.isDie && this.Action != 4 || this.typeBoss != 3 && this.typeBoss != 4)) {
            ++this.timeRemove;
            if (this.timeRemove > 600) {
                this.isRemove = true;
            }
        }
    }

    public static CatalogyMonster getCatalogyMonster(int n) {
        for (int i = 0; i < VecCatalogyMonSter.size(); ++i) {
            CatalogyMonster catalogyMonster = (CatalogyMonster)VecCatalogyMonSter.elementAt(i);
            if (catalogyMonster.id != n) continue;
            return catalogyMonster;
        }
        return null;
    }

    public void auto_Move() {
        if (this.typeBoss == 5) {
            return;
        }
        if (this.isMonsterHouse() || this.isItemBox()) {
            return;
        }
        if (this.isStun || this.isBinded || this.isDongBang || this.isSleep || this.isno || this.isMoveOut) {
            return;
        }
        if (this.typeBoss == 3 || this.typeBoss == 4) {
            this.toX = this.xPhoBang;
            this.toY = this.yPhoBang;
            this.move_to_XY();
            if (MainObject.getDistance(this.x, this.y, this.toX, this.toY) <= LoadMap.wTile / 2 && this.isMonPhoBangDie) {
                if (this.typeBoss == 3) {
                    GameScreen.addEffectEndKill(36, this.x, this.y - this.hOne / 2);
                    LoadMap.timeVibrateScreen = 103;
                } else if (this.typeBoss == 4) {
                    for (int i = 0; i < 3; ++i) {
                        int n = this.hOne;
                        int n2 = this.wOne;
                        if (this.hOne <= 1) {
                            n = 40;
                        }
                        if (n2 <= 1) {
                            n2 = 40;
                        }
                        GameScreen.addEffectEndKill(36, this.x + CRes.random_Am_0(n2 / 2), this.y - this.hOne / 2 + CRes.random_Am_0(n / 2));
                    }
                    LoadMap.timeVibrateScreen = 110;
                }
                this.isRemove = true;
                this.isMonPhoBangDie = false;
            }
        } else if (MainObject.getDistance(this.x, this.y, this.xAnchor, this.yAnchor) > this.limitMove + this.limitMove / 2) {
            if (!MainObject.isInScreen(this) && !this.setIsInScreen(this.xAnchor, this.yAnchor, this.wOne, this.hOne)) {
                this.x = this.xAnchor;
                this.y = this.yAnchor;
                this.toX = this.xAnchor;
                this.toY = this.yAnchor;
            } else {
                this.toX = this.xAnchor;
                this.toY = this.yAnchor;
                this.move_to_XY();
            }
        } else {
            if (!MainObject.isInScreen(this) && !this.setIsInScreen(this.xAnchor, this.yAnchor, this.wOne, this.hOne)) {
                this.x = this.xAnchor;
                this.y = this.yAnchor;
                this.toX = this.xAnchor;
                this.toY = this.yAnchor;
                return;
            }
            ++this.time;
            if (this.Action != 4) {
                if (this.timeStand > 0) {
                    this.time = 0;
                    this.Action = 0;
                    this.vx = 0;
                    this.vy = 0;
                    --this.timeStand;
                } else if (MainObject.getDistance(this.x + this.vx, this.y + this.vy, GameScreen.player.x, GameScreen.player.y) < 50) {
                    if (this.Action == 1) {
                        if (this.time > this.timeAutoAction / 2 || CRes.random(12) == 0 || MainObject.getDistance(this.x + this.vx, this.y + this.vy, this.xAnchor, this.yAnchor) >= this.limitMove - this.vMax) {
                            this.time = 0;
                            this.Action = 0;
                            this.vx = 0;
                            this.vy = 0;
                            this.Direction = this.x > GameScreen.player.x ? 2 : 3;
                        }
                    } else if (this.Action == 0 || CRes.random(30) == 0) {
                        this.vx = 0;
                        this.vy = 0;
                        if (this.time > this.timeAutoAction) {
                            this.time = 0;
                            this.Action = 1;
                            this.Direction = CRes.random(4);
                            this.setSpeedInDirection(this.vMax - 2);
                        }
                        this.Direction = this.x > GameScreen.player.x ? 2 : 3;
                    }
                } else if (this.Action == 1) {
                    if (this.time > this.timeAutoAction || CRes.random(16) == 0 || MainObject.getDistance(this.x + this.vx, this.y + this.vy, this.xAnchor, this.yAnchor) >= this.limitMove - this.vMax) {
                        this.time = 0;
                        this.Action = 0;
                        this.vx = 0;
                        this.vy = 0;
                    }
                } else if (this.Action == 0) {
                    this.vx = 0;
                    this.vy = 0;
                    if (this.time > this.timeAutoAction / 2 || CRes.random(12) == 0) {
                        this.time = 0;
                        this.Action = 1;
                        this.Direction = CRes.random(4);
                        this.setSpeedInDirection(this.vMax);
                    }
                }
                if (MainObject.getDistance(this.x, this.y, this.xAnchor, this.yAnchor) > this.limitMove) {
                    int n;
                    int n3 = CRes.abs(this.x - this.xAnchor);
                    this.Direction = n3 > (n = CRes.abs(this.y - this.yAnchor)) ? (this.x > this.xAnchor ? 2 : 3) : (this.y > this.yAnchor ? 1 : 0);
                    this.setSpeedInDirection(this.vMax);
                }
            }
        }
    }

    public void autoMoveFire() {
        if (this.typeBoss == 5) {
            return;
        }
        ++this.time;
        if (this.Action != 4) {
            if (this.Action == 1) {
                if (this.time > this.timeAutoAction || CRes.random(16) == 0) {
                    this.time = 0;
                    this.Action = 0;
                    this.vx = 0;
                    this.vy = 0;
                    MainObject mainObject = MainObject.get_Object(this.IDAttack, (byte)0);
                    if (mainObject != null) {
                        this.Direction = this.x > mainObject.x ? 2 : 3;
                    }
                }
            } else if (this.Action == 0) {
                this.vx = 0;
                this.vy = 0;
                if (this.time > this.timeAutoAction / 2 || CRes.random(12) == 0) {
                    this.time = 0;
                    this.Action = 1;
                    this.Direction = CRes.random(4);
                    this.setSpeed(this.vMax);
                }
            }
        }
    }

    public void Move_to_Focus() {
        if (this.typeBoss == 5) {
            return;
        }
        if (this.isBinded) {
            return;
        }
        if (this.isDongBang) {
            return;
        }
        if (this.vecObjskill != null && this.vecObjskill.size() > 0) {
            Object_Effect_Skill object_Effect_Skill = (Object_Effect_Skill)this.vecObjskill.elementAt(0);
            if (GameCanvas.timeNow - this.timeBeginMoveAttack > (long)this.timeMaxMoveAttack) {
                this.IDAttack = object_Effect_Skill.ID;
                object_Effect_Skill.skillMonster = this.skillDefault;
                this.beginFire();
                this.beginSkill();
            } else {
                MainObject mainObject = MainObject.get_Object(object_Effect_Skill.ID, object_Effect_Skill.tem);
                if (mainObject == null) {
                    this.isRunAttack = false;
                    return;
                }
                this.toX = mainObject.x;
                this.toY = mainObject.y;
                int n = object_Effect_Skill.skillMonster.range;
                if (MainObject.getDistance(this.x + this.vx, this.y + this.vy, mainObject.x, mainObject.y) <= n) {
                    this.IDAttack = object_Effect_Skill.ID;
                    this.beginFire();
                    this.beginSkill();
                } else if (CRes.abs(this.x - this.toX) >= 4 || CRes.abs(this.y - this.toY) >= 4) {
                    this.move_to_XY_Normal();
                }
            }
        } else {
            MainObject mainObject = MainObject.get_Object(this.IDAttack, (byte)0);
            if (mainObject == null) {
                this.isRunAttack = false;
                return;
            }
            this.toX = mainObject.x;
            this.toY = mainObject.y;
            if (MainObject.getDistance(this.x + this.vx, this.y + this.vy, mainObject.x, mainObject.y) <= this.limitAttack) {
                this.vx = 0;
                this.vy = 0;
                this.Action = 0;
                this.toX = this.x;
                this.toY = this.y;
                if (CRes.random(30) == 0) {
                    this.timeFreeFire = 20;
                }
                this.Direction = this.x > mainObject.x ? 2 : 3;
            } else if (CRes.abs(this.x - this.toX) >= 4 || CRes.abs(this.y - this.toY) >= 4) {
                this.move_to_XY_Normal();
            }
        }
    }

    public void Move_To_Player_Skill() {
        if (this.vecObjskill != null && this.vecObjskill.size() > 0) {
            Object_Effect_Skill object_Effect_Skill = (Object_Effect_Skill)this.vecObjskill.elementAt(0);
            if (GameCanvas.timeNow - this.timeBeginMoveAttack > (long)this.timeMaxMoveAttack) {
                this.IDAttack = object_Effect_Skill.ID;
                object_Effect_Skill.skillMonster = this.skillDefault;
                this.beginFire();
                this.beginSkill();
            } else {
                MainObject mainObject = MainObject.get_Object(object_Effect_Skill.ID, object_Effect_Skill.tem);
                if (mainObject == null) {
                    this.isRunAttack = false;
                    return;
                }
                this.toX = mainObject.x;
                this.toY = mainObject.y;
                if (MainObject.getDistance(this.x + this.vx, this.y + this.vy, mainObject.x, mainObject.y) <= object_Effect_Skill.skillMonster.range) {
                    this.IDAttack = object_Effect_Skill.ID;
                    this.beginFire();
                    this.beginSkill();
                } else if (CRes.abs(this.x - this.toX) >= 4 || CRes.abs(this.y - this.toY) >= 4) {
                    this.move_to_XY_Normal();
                }
            }
        }
    }

    public void Reveive() {
        this.ReveiceMonster();
        this.isRunAttack = false;
        this.resetXY();
        this.frameDie = 0;
        this.timeFreeFire = 0;
        this.timeBienmat = 5;
        this.f = 0;
        this.Action = 0;
        this.time = 0;
        this.isDie = false;
        this.vxDie = 0;
        this.vyDie = 0;
        this.hp = this.maxHp;
        this.vecBuff.removeAllElements();
        this.vyStyleStand = 0;
        this.vyStyleDie = 0;
    }

    public void setDie() {
        if (this.Action == 4) {
            ++this.frameDie;
            if (GameCanvas.gameTick % 10 == 0 && this.timeReveice >= 0 && this.isDie && (GameCanvas.timeNow - this.timedie) / 1000L > (long)this.timeReveice) {
                this.Reveive();
            }
            if (!this.isDie) {
                this.x += this.vxDie;
                this.y += this.vyDie;
                if (this.vyStyleDie > 0) {
                    this.vyStyleDie -= 3;
                    if (this.vyStyleDie <= 0 && this.vyStyleStand > 2) {
                        this.vyStyleStand -= 2;
                        this.vyStyleDie = this.vyStyleStand;
                        if (CRes.abs(this.vxDie) > 1) {
                            this.vxDie -= this.vxDie / CRes.abs(this.vxDie);
                        }
                        if (CRes.abs(this.vyDie) > 1) {
                            this.vyDie -= this.vyDie / CRes.abs(this.vyDie);
                        }
                    }
                } else {
                    this.vxDie >>= 1;
                    this.vyDie >>= 1;
                }
                if (this.frameDie >= this.timeBienmat) {
                    GameScreen.addEffectEndKill(11, this.x, this.y - this.hOne / 4);
                    if (!this.isMonsterHouse() || !this.isItemBox()) {
                        this.isDie = true;
                    } else if (this.coutEff < -10) {
                        this.isDie = true;
                    }
                }
            }
            if (this.isMonsterHouse() || this.isItemBox() || this.typeMonster == 16) {
                --this.coutEff;
                if (this.coutEff > 0 && this.timeFreeMove < 70) {
                    int n;
                    int n2;
                    int n3;
                    ++this.timeFreeMove;
                    if (CRes.random(3) == 1) {
                        if (CRes.random(2) == 1) {
                            LoadMap.timeVibrateScreen = 103;
                        }
                        n3 = CRes.random(1, 3);
                        for (n2 = 0; n2 < n3; ++n2) {
                            n = CRes.random_Am_0(25);
                            int n4 = CRes.random_Am_0(30);
                            GameScreen.addEffectEndKill(36, this.x + n, this.y + n4 - this.hOne / 3 + 10);
                            if (CRes.random(3) != 1) continue;
                            GameScreen.addEffectEndKill(9, this.x + n, this.y + n4 - this.hOne / 3 + 10);
                        }
                    }
                    if (this.timeFreeMove >= 70) {
                        for (n3 = 0; n3 < 6; ++n3) {
                            n2 = CRes.random_Am_0(25);
                            n = CRes.random_Am_0(30);
                            GameScreen.addEffectEndKill(36, this.x + n2, this.y + n - this.hOne / 3 + 10);
                            if (CRes.random(3) != 1) continue;
                            GameScreen.addEffectEndKill(9, this.x + n2, this.y + n - this.hOne / 2 + 10);
                        }
                        this.isRemove = true;
                    }
                }
            }
        } else if (this.hp <= 0) {
            this.hp = 0;
            this.Action = 4;
            this.timedie = GameCanvas.timeNow;
            this.resetXY();
        }
    }

    public void ReveiceMonster() {
        boolean bl;
        int n = 0;
        if (this.typeMonster == 7) {
            this.x = this.xAnchor;
            this.y = this.yAnchor;
            return;
        }
        do {
            this.x = this.xAnchor + CRes.random_Am_0(48);
            this.y = this.yAnchor + CRes.random_Am_0(48);
            int n2 = GameCanvas.loadmap.getTile(this.x, this.y);
            boolean bl2 = bl = n2 != 1 && n2 != -1;
            if (++n <= 15) continue;
            bl = true;
            this.x = this.xAnchor;
            this.y = this.yAnchor;
        } while (!bl);
    }

    public void startDeadFly(int n, int n2, int n3, int n4) {
        this.timedie = GameCanvas.timeNow;
        this.Action = 4;
        this.vx = 0;
        this.vy = 0;
        this.vxDie = n;
        this.vyDie = n2;
        this.vyStyleDie = n4;
        this.vyStyleStand = n4;
        this.timeBienmat = n3;
        this.isDie = false;
        if (this.isMonsterHouse() || this.isItemBox() || this.typeMonster == 16) {
            this.vxDie = 0;
            this.vyDie = 0;
            this.vyStyleDie = 0;
            this.vyStyleStand = 0;
        }
    }

    public void resetV() {
        this.vx = 0;
        this.vy = 0;
        this.toX = this.x;
        this.toY = this.y;
    }

    public void startDie() {
        if (this.vecObjskill != null && this.vecObjskill.size() > 0) {
            Object_Effect_Skill object_Effect_Skill = (Object_Effect_Skill)this.vecObjskill.elementAt(0);
            object_Effect_Skill.skillMonster = this.skillDefault;
        }
        this.beginSkill();
    }

    public void beginSkill() {
        if (this.vecObjskill != null && this.vecObjskill.size() > 0) {
            Object_Effect_Skill object_Effect_Skill = (Object_Effect_Skill)this.vecObjskill.elementAt(0);
            if (object_Effect_Skill != null && object_Effect_Skill.skillMonster != null) {
                if (object_Effect_Skill.skillMonster.typeSkill == 124) {
                    GameScreen.StartEffect_Chiemthanh(object_Effect_Skill.skillMonster.typeSkill, this.ID, (byte)1, this.vecObjskill, object_Effect_Skill.skillMonster.typeSub);
                } else {
                    GameScreen.addEffectKill(object_Effect_Skill.skillMonster.typeSkill, this.ID, (byte)1, this.vecObjskill, object_Effect_Skill.skillMonster.typeSub);
                }
            }
            this.vecObjskill = null;
        }
    }

    public void beginFire() {
        this.Action = 2;
        this.loopAtack = (byte)CRes.random(3, 5);
        this.resetAction();
        this.f = 0;
    }

    public void addiconClan() {
    }

    public boolean canNotMove() {
        return this.typeMonster == 17 || this.typeMonster == 19 || this.typeMonster == 18;
    }
}

