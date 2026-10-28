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
import Main.GameCanvas;
import Model.AvMain;
import Model.CRes;
import Model.MainImage;
import netcommand.global.GlobalService;

public class MonsterFly
extends MainMonster {
    mImage img;
    int ydieFly = 0;
    static byte[][][] mMonFly_012 = new byte[][][]{new byte[][]{{0, 0, 1, 1, 2, 2}, {1, 1, 0, 0, 2, 2}, {2, 2, 0, 0, 1, 1}}, new byte[][]{{0, 0, 1, 1, 2, 2}, {1, 1, 0, 0, 2, 2}, {2, 2, 0, 0, 1, 1}}, new byte[][]{{2, 2, 2, 3, 3, 3}, {2, 2, 2, 3, 3, 3}, {2, 2, 2, 3, 3, 3}}, new byte[][]{{4, 4, 4, 4, 4, 4}, {4, 4, 4, 4, 4, 4}, {4, 4, 4, 4, 4, 4}}, new byte[][]{{4, 4, 4, 4, 4, 4}, {4, 4, 4, 4, 4, 4}, {4, 4, 4, 4, 4, 4}}};
    static byte[][][] mMonFly_0102 = new byte[][][]{new byte[][]{{0, 0, 1, 1, 0, 0, 2, 2}, {1, 1, 0, 0, 2, 2, 0, 0}, {2, 2, 0, 0, 1, 1, 0, 0}}, new byte[][]{{0, 0, 1, 1, 0, 0, 2, 2}, {1, 1, 0, 0, 2, 2, 0, 0}, {2, 2, 0, 0, 1, 1, 0, 0}}, new byte[][]{{2, 2, 2, 2, 3, 3, 3, 3}, {2, 2, 2, 2, 3, 3, 3, 3}, {2, 2, 2, 2, 3, 3, 3, 3}}, new byte[][]{{4, 4, 4, 4, 4, 4, 4, 4}, {4, 4, 4, 4, 4, 4, 4, 4}, {4, 4, 4, 4, 4, 4, 4, 4}}, new byte[][]{{4, 4, 4, 4, 4, 4, 4, 4}, {4, 4, 4, 4, 4, 4, 4, 4}, {4, 4, 4, 4, 4, 4, 4, 4}}};
    static byte[][][] mMonFly_012_slow = new byte[][][]{new byte[][]{{0, 0, 1, 1, 2, 2}, {1, 1, 0, 0, 2, 2}, {2, 2, 0, 0, 1, 1}}, new byte[][]{{0, 0, 1, 1, 2, 2}, {1, 1, 0, 0, 2, 2}, {2, 2, 0, 0, 1, 1}}, new byte[][]{{2, 2, 2, 3, 3, 3}, {2, 2, 2, 3, 3, 3}, {2, 2, 2, 3, 3, 3}}, new byte[][]{{4, 4, 4, 4, 4, 4}, {4, 4, 4, 4, 4, 4}, {4, 4, 4, 4, 4, 4}}, new byte[][]{{4, 4, 4, 4, 4, 4}, {4, 4, 4, 4, 4, 4}, {4, 4, 4, 4, 4, 4}}};

    public MonsterFly(int n, int n2, int n3, String string, int n4, int n5, int n6, int n7) {
        this.coutEff = 0;
        this.typeMonster = n3;
        this.typeObject = 1;
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
        this.Direction = 0;
        this.MonWater = 0;
        this.hOne = -1;
        this.wOne = -1;
        this.nFrame = 5;
        this.vMax = 3;
        this.limitMove = 70;
        this.xsai = 0;
        this.ysai = -2;
        this.timeAutoAction = CRes.random(60, 85);
        this.limitAttack = 50;
        switch (n3) {
            case 5: 
            case 19: {
                this.mAction = mMonFly_0102;
                break;
            }
            case 3: {
                this.mAction = mMonFly_012;
                break;
            }
            case 8: {
                this.mAction = mMonFly_012;
                this.limitAttack = 80;
                break;
            }
            case 10: {
                this.mAction = mMonFly_012_slow;
            }
        }
        this.timeLoadInfo = mSystem.currentTimeMillis();
    }

    public void paint(mGraphics mGraphics2) {
        if (!this.isDie) {
            if (this.typeBoss == 2 && MainObject.imgCapchar != null) {
                AvMain.Font3dWhite(mGraphics2, MainObject.strCapchar, this.x, this.y - this.dy - this.ydieFly - this.hOne - 20 - 30, 2);
                mGraphics2.drawImage(MainObject.imgCapchar, this.x, this.y - this.dy - this.vyStyleDie - this.hOne - 20, 33, false);
            }
            this.paintDataEff_Top(mGraphics2, this.x, this.y);
            this.paintEffauto_Low(mGraphics2, this.x, this.y);
            this.paintBuffFirst(mGraphics2);
            MainImage mainImage = ObjectData.getImagePartMonster((short)this.catalogyMonster);
            int n = this.Action;
            if (n > this.mAction.length - 1) {
                n = 0;
            }
            if (this.f > this.mAction[n][this.Direction > 2 ? 2 : this.Direction].length - 1) {
                this.f = 0;
            }
            mGraphics2.drawImage(MainObject.shadow, this.x, this.y - this.dy - this.ydieFly, 3, false);
            if (mainImage.img != null) {
                if (this.wOne < 0) {
                    if (this.catalogyMonster <= 92) {
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
                mGraphics2.drawRegion(mainImage.img, n2, n3, this.wOne, this.hOne, this.Direction > 2 ? 2 : 0, this.x, this.y - 8 - this.dy - this.vyStyleDie, 33, false);
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

    public void update() {
        long l;
        long l2;
        super.update();
        if (!this.isInfo && (l2 = (l = mSystem.currentTimeMillis()) - this.timeLoadInfo) >= 5000L) {
            this.timeLoadInfo = l;
            GlobalService.gI().monster_info((short)this.ID);
        }
        this.updateDataEffect();
        this.setDie();
        if (this.Action == 4) {
            this.ydieFly += 3;
            if (this.ydieFly > 11) {
                this.ydieFly = 11;
            }
        } else {
            this.ydieFly = 0;
        }
        if (!this.Canmove() || this.isBinded) {
            return;
        }
        this.updateAction();
        if (this.Action != 4 && this.Action != 3 && this.Action != 2) {
            if (this.isRunAttack && !this.isMonPhoBangDie) {
                if (this.timeFreeFire > 0) {
                    if (!this.isServerControl) {
                        this.autoMoveFire();
                    }
                } else if (!this.canNotMove()) {
                    this.Move_to_Focus();
                }
            } else if (!this.isServerControl) {
                this.auto_Move();
            }
        }
        int n = GameCanvas.loadmap.getTile(this.x + this.vx, this.y + this.vy);
        if (!this.isServerControl) {
            this.setMove(this.MonWater, n);
        }
        if (this.typeBoss == 2) {
            this.updateImgCapchar();
        }
    }

    public void updateImgCapchar() {
        if (MainObject.imgCapchar == null) {
            if (this.timeCapchar > 0) {
                --this.timeCapchar;
            }
            if (this.timeCapchar <= 0) {
                mSystem.outz("lay hinh capchar");
                GlobalService.gI().load_image((short)9999);
                this.timeCapchar = 120;
            }
        }
    }
}

