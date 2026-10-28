/*
 * Decompiled with CFR 0.152.
 */
package GameObjects;

import CLib.mGraphics;
import CLib.mImage;
import CLib.mSystem;
import GameObjects.MainMonster;
import GameObjects.MainObject;
import GameObjects.ObjectData;
import GameObjects.Object_Effect_Skill;
import GameScreen.GameScreen;
import Main.GameCanvas;
import Model.CRes;
import Model.MainImage;
import Thread_More.LoadMap;
import netcommand.global.GlobalService;

public class MonsterWalk
extends MainMonster {
    static byte[][][] mMon0102 = new byte[][][]{new byte[][]{{0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0}}, new byte[][]{{0, 0, 1, 1, 0, 0, 2, 2}, {1, 1, 0, 0, 2, 2, 0, 0}, {2, 2, 0, 0, 1, 1, 0, 0}}, new byte[][]{{2, 2, 2, 3, 3, 3}, {2, 2, 2, 3, 3, 3}, {2, 2, 2, 3, 3, 3}}, new byte[][]{{4, 4, 4, 4, 4, 4, 4, 4}, {4, 4, 4, 4, 4, 4, 4, 4}, {4, 4, 4, 4, 4, 4, 4, 4}}, new byte[][]{{4, 4, 4, 4, 4, 4, 4, 4}, {4, 4, 4, 4, 4, 4, 4, 4}, {4, 4, 4, 4, 4, 4, 4, 4}}};
    static byte[][][] mMon012 = new byte[][][]{new byte[][]{{0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0}}, new byte[][]{{0, 0, 1, 1, 2, 2}, {1, 1, 0, 0, 2, 2}, {2, 2, 0, 0, 1, 1}}, new byte[][]{{2, 2, 2, 3, 3, 3}, {2, 2, 2, 3, 3, 3}, {2, 2, 2, 3, 3, 3}}, new byte[][]{{4, 4, 4, 4, 4, 4}, {4, 4, 4, 4, 4, 4}, {4, 4, 4, 4, 4, 4}}, new byte[][]{{4, 4, 4, 4, 4, 4}, {4, 4, 4, 4, 4, 4}, {4, 4, 4, 4, 4, 4}}};
    static byte[][][] mMon01 = new byte[][][]{new byte[][]{{0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0}}, new byte[][]{{1, 1, 1, 0, 0, 0}, {1, 1, 1, 0, 0, 0}, {1, 1, 1, 0, 0, 0}}, new byte[][]{{2, 2, 2, 3, 3, 3}, {2, 2, 2, 3, 3, 3}, {2, 2, 2, 3, 3, 3}}, new byte[][]{{4, 4, 4, 4, 4, 4}, {4, 4, 4, 4, 4, 4}, {4, 4, 4, 4, 4, 4}}, new byte[][]{{4, 4, 4, 4, 4, 4}, {4, 4, 4, 4, 4, 4}, {4, 4, 4, 4, 4, 4}}};
    static byte[][][] mMonBossMesdusa = new byte[][][]{new byte[][]{{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}}, new byte[][]{{0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 2}, {1, 1, 1, 1, 0, 0, 0, 0, 2, 2, 2, 2}, {2, 2, 2, 2, 0, 0, 0, 0, 1, 1, 1, 1}}, new byte[][]{{2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3}, {2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3}, {2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3}}, new byte[][]{{4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4}, {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4}, {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4}}, new byte[][]{{4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4}, {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4}, {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4}}};
    static byte[][][] mMonschiemthanh = new byte[][][]{new byte[][]{{4, 4, 4, 4, 5, 5, 5, 5, 4, 4, 4, 4}, {4, 4, 4, 4, 5, 5, 5, 5, 4, 4, 4, 4}, {4, 4, 4, 4, 5, 5, 5, 5, 4, 4, 4, 4}}, new byte[][]{{0, 0, 0, 1, 1, 1, 2, 2, 2, 3, 3, 3}, {0, 0, 0, 1, 1, 1, 2, 2, 2, 3, 3, 3}, {0, 0, 0, 1, 1, 1, 2, 2, 2, 3, 3, 3}}, new byte[][]{{4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4}, {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4}, {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4}}, new byte[][]{{4, 4, 4, 4, 5, 5, 5, 5, 4, 4, 4, 4}, {4, 4, 4, 4, 5, 5, 5, 5, 4, 4, 4, 4}, {4, 4, 4, 4, 5, 5, 5, 5, 4, 4, 4, 4}}, new byte[][]{{4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4}, {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4}, {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4}}};

    public boolean isMonsterHouse() {
        return this.typeMonster == 12;
    }

    public boolean isMonsterGate() {
        return this.typeMonster == 16;
    }

    public boolean isItemBox() {
        return this.typeMonster == 13;
    }

    public MonsterWalk(int n, int n2, int n3, String string, int n4, int n5, int n6, int n7) {
        this.typeObject = 1;
        this.typeMonster = n3;
        this.ID = n;
        this.catalogyMonster = n2;
        this.xAnchor = n4;
        this.yAnchor = n5;
        this.x = n4;
        this.y = n5;
        this.name = string;
        this.maxHp = n6;
        this.hp = n6;
        this.Lv = (short)n7;
        this.MonWater = 1;
        this.Direction = 0;
        this.nFrame = 5;
        this.hOne = -1;
        this.wOne = -1;
        this.vMax = 3;
        this.limitMove = 60;
        this.timeAutoAction = CRes.random(50, 70);
        this.limitAttack = 50;
        this.xsai = 0;
        this.ysai = -2;
        switch (n3) {
            case 0: {
                if (!this.isMoveSlow(this.catalogyMonster)) {
                    this.mAction = mMon012;
                    break;
                }
                this.mAction = mMonBossMesdusa;
                break;
            }
            case 17: {
                this.mAction = mMonschiemthanh;
                break;
            }
            case 2: {
                this.mAction = mMon0102;
                break;
            }
            case 1: {
                this.mAction = mMon01;
                break;
            }
            case 4: {
                this.MonWater = 0;
                this.mAction = mMon012;
                break;
            }
            case 6: {
                this.limitAttack = 80;
                this.mAction = mMon012;
                break;
            }
            case 9: {
                this.MonWater = 0;
                this.mAction = mMon012;
                this.limitAttack = 80;
                break;
            }
            case 12: 
            case 16: 
            case 18: {
                this.mAction = new byte[][][]{new byte[][]{{0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0}}, new byte[][]{{0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0}}, new byte[][]{{0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0}}, new byte[][]{{0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0}}, new byte[][]{{0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0}}};
                this.nFrame = 1;
            }
        }
        this.timeLoadInfo = mSystem.currentTimeMillis();
    }

    public void paint(mGraphics mGraphics2) {
        if (this.StepMovebocap == 1 && (this.catalogyMonster == 103 || this.catalogyMonster == 149)) {
            return;
        }
        if (this.typeMonster == 18) {
            return;
        }
        if (!this.isDie) {
            this.paintDataEff_Top(mGraphics2, this.x, this.y);
            this.paintBuffFirst(mGraphics2);
            MainImage mainImage = ObjectData.getImagePartMonster((short)this.catalogyMonster);
            int n = this.Action;
            if (n > this.mAction.length - 1) {
                n = 0;
            }
            if (this.f > this.mAction[n][this.Direction > 2 ? 2 : this.Direction].length - 1) {
                this.f = 0;
            }
            if (mainImage.img != null) {
                if (this.wOne < 0) {
                    if (this.catalogyMonster <= 92 || this.typeMonster == 16) {
                        this.hOne = mImage.getImageHeight(mainImage.img.image) / this.nFrame;
                        this.wOne = mImage.getImageWidth(mainImage.img.image);
                    } else {
                        this.hOne = mImage.getImageHeight(mainImage.img.image) / 3;
                        this.wOne = mImage.getImageWidth(mainImage.img.image) / 2;
                    }
                }
                int n2 = 0;
                int n3 = this.mAction[n][this.Direction > 2 ? 2 : this.Direction][this.f] * this.hOne;
                if (this.catalogyMonster > 92) {
                    n2 = this.mAction[n][this.Direction > 2 ? 2 : this.Direction][this.f] / 3 * this.wOne;
                    n3 = this.mAction[n][this.Direction > 2 ? 2 : this.Direction][this.f] % 3 * this.hOne;
                }
                this.paintEffauto_Low(mGraphics2, this.x, this.y);
                if (this.isMonsterHouse()) {
                    this.Direction = 0;
                    mGraphics2.drawRegion(mainImage.img, n2, n3, this.wOne, this.hOne, this.Direction > 2 ? 2 : 0, this.x, this.y - this.dy + this.dyWater - this.vyStyleDie, 33, false);
                } else {
                    mGraphics2.drawRegion(mainImage.img, n2, n3, this.wOne, this.hOne, this.Direction > 2 ? 2 : 0, this.x, this.y - this.dy + this.dyWater - this.vyStyleDie, 33, false);
                }
                if (this.isWater && this.dy == 0) {
                    int n4 = 1;
                    mGraphics2.drawRegion(MainObject.water, 0, (n != 0 ? 2 : 0) * 17 + GameCanvas.gameTick / 2 % 2 * 17, 28, 17, 0, this.x + n4, this.y + this.dyWater - 4, 3, false);
                }
                this.canFocusMon = true;
            } else {
                this.canFocusMon = false;
            }
            this.paintIconClan(mGraphics2, this.x - 1, this.y - this.ysai - this.dy + this.dyWater - this.hOne - 20, 2);
            this.paintDataEff_Bot(mGraphics2, this.x, this.y);
            this.paintBuffLast(mGraphics2);
            super.paint(mGraphics2);
        }
    }

    public void checkcollide() {
        if (!this.isBossNew() || GameScreen.player.vx == 0 && GameScreen.player.vy == 0 || GameScreen.player.moveToBoss) {
            GameScreen.player.moveToBoss = false;
            return;
        }
        if (CRes.ktvc(this.x - this.wOne / 3, this.x + this.wOne / 3, GameScreen.player.x - GameScreen.player.wOne / 3, GameScreen.player.x + GameScreen.player.wOne / 3, this.y - this.hOne, this.y - this.hOne / 3, GameScreen.player.y - GameScreen.player.hOne, GameScreen.player.y - GameScreen.player.hOne / 3)) {
            if (GameScreen.player.Action == 1 && !GameScreen.player.moveToBoss) {
                GameScreen.player.moveToBoss = true;
            }
        } else {
            GameScreen.player.moveToBoss = false;
        }
    }

    public void Move_to_Focus() {
        if (this.isMove && this.catalogyMonster == 103) {
            return;
        }
        if (this.typeBoss == 5) {
            return;
        }
        if (this.isBinded || this.isDongBang) {
            return;
        }
        if (this.isServerControl) {
            MainObject mainObject = MainObject.get_Object(this.IDAttack, (byte)0);
            if (mainObject == null) {
                this.isRunAttack = false;
                return;
            }
            if (mainObject.Action != 4) {
                this.toX = mainObject.x;
                this.toY = mainObject.y;
                this.vx = 0;
                this.vy = 0;
                this.Action = 0;
                this.toX = this.x;
                this.toY = this.y;
                if (CRes.random(30) == 0) {
                    this.timeFreeFire = 20;
                }
                this.Direction = this.x > mainObject.x ? 2 : 3;
            }
            return;
        }
        if (this.vecObjskill != null && this.vecObjskill.size() > 0 && !this.isServerControl) {
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
                if (mainObject.Action != 4) {
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
                } else {
                    this.move_to_XY_Normal();
                }
            }
        } else {
            MainObject mainObject = MainObject.get_Object(this.IDAttack, (byte)0);
            if (mainObject == null) {
                this.isRunAttack = false;
                return;
            }
            if (mainObject.Action != 4) {
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
    }

    public void move_to_XY() {
        if (!this.Canmove() || this.isBinded) {
            this.toX = this.x;
            this.toY = this.y;
            return;
        }
        if (this.isMoveOut) {
            return;
        }
        if (CRes.abs(this.x - this.toX) > this.vMax + this.getVmount()) {
            this.vy = 0;
            this.Action = 1;
            if (CRes.abs(this.x - this.toX) > this.vMax + this.getVmount()) {
                if (this.x > this.toX) {
                    this.vx = -(this.vMax + this.getVmount());
                    this.PrevDir = this.Direction;
                    this.Direction = 2;
                } else {
                    this.vx = this.vMax + this.getVmount();
                    this.PrevDir = this.Direction;
                    this.Direction = 3;
                }
            } else {
                this.vx = this.toX - this.x;
            }
        } else if (CRes.abs(this.y - this.toY) > this.vMax + this.getVmount()) {
            this.vx = 0;
            this.Action = 1;
            if (CRes.abs(this.y - this.toY) > this.vMax + this.getVmount()) {
                if (this.y > this.toY) {
                    this.vy = -(this.vMax + this.getVmount());
                    this.PrevDir = this.Direction;
                    this.Direction = 1;
                } else {
                    this.vy = this.vMax + this.getVmount();
                    this.PrevDir = this.Direction;
                    this.Direction = 0;
                }
            } else {
                this.vy = this.toY - this.y;
            }
        } else {
            if (this.isDetonateInDest) {
                GameScreen.addEffectEndKill(43, this.x, this.y);
                LoadMap.timeVibrateScreen = 10;
                this.isStop = true;
            }
            this.vx = 0;
            this.vy = 0;
            if ((this.catalogyMonster == 103 || this.catalogyMonster == 149) && this.StepMovebocap == 0) {
                GameScreen.addEffectEndKill(54, this.x, this.y - 20);
                this.StepMovebocap = 1;
            }
        }
    }

    public void update() {
        long l;
        long l2;
        super.update();
        if (!this.isInfo && (l2 = (l = mSystem.currentTimeMillis()) - this.timeLoadInfo) >= 5000L) {
            this.timeLoadInfo = l;
            GlobalService.gI().monster_info((short)this.ID);
        }
        this.setDie();
        if (!this.Canmove()) {
            return;
        }
        this.updateAction();
        if (this.Action != 4 && this.Action != 3 && this.Action != 2) {
            if (this.isRunAttack && !this.isMonPhoBangDie) {
                if (this.timeFreeFire > 0) {
                    if (!(this.isServerControl || this.isMonsterHouse() && this.isItemBox() && this.isMonsterGate())) {
                        this.autoMoveFire();
                    }
                } else if (!(this.isMonsterHouse() || this.isItemBox() || this.canNotMove() || this.isMonsterGate())) {
                    this.Move_to_Focus();
                }
            } else if (!(this.isServerControl || this.isMonsterHouse() || this.isItemBox() || this.canNotMove() || this.isMonsterGate())) {
                this.auto_Move();
            }
        }
        int n = GameCanvas.loadmap.getTile(this.x + this.vx, this.y + this.vy);
        if (!(this.isServerControl || this.isMonsterHouse() || this.isItemBox() || this.canNotMove())) {
            this.setMove(this.MonWater, n);
        }
    }

    public boolean isMoveSlow(int n) {
        return n == 101 || n == 83 || n == 84;
    }
}

