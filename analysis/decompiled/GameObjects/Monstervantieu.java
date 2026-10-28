/*
 * Decompiled with CFR 0.152.
 */
package GameObjects;

import CLib.mFont;
import CLib.mGraphics;
import CLib.mImage;
import CLib.mSystem;
import GameObjects.MainMonster;
import GameObjects.MainObject;
import GameObjects.ObjectData;
import Main.GameCanvas;
import Model.MainImage;
import Thread_More.LoadMap;
import netcommand.global.GlobalService;

public class Monstervantieu
extends MainMonster {
    public static final int ID_TEMPLATE_LUA_TRANG = 127;
    public static final int ID_TEMPLATE_LUA_DEN = 128;
    public static final int ID_TEMPLATE_LAC_DA_TRANG = 129;
    public static final int ID_TEMPLATE_LAC_DA_DEN = 130;
    public static final int ID_TEMPLATE_BO_TRANG = 131;
    public static final int ID_TEMPLATE_BO_DEN = 132;
    public static final int ID_TEMPLATE_VOI_TRANG = 177;
    public static final int ID_TEMPLATE_VOI_DEN = 176;
    public byte Maxspeed;
    byte typemove = 0;
    public byte[][][] FRAMEMOVE = new byte[][][]{new byte[][]{{7, 7, 7, 8, 8, 8, 9, 9, 9, 6, 6, 6, 6}, {12, 12, 12, 13, 13, 13, 14, 14, 14, 11, 11, 11, 11}, {2, 2, 2, 3, 3, 3, 4, 4, 4, 1, 1, 1, 1}, {2, 2, 2, 3, 3, 3, 4, 4, 4, 1, 1, 1, 1}}, new byte[][]{{7, 7, 7, 7, 8, 8, 8, 8, 9, 9, 9, 9, 6, 6, 6, 6}, {12, 12, 12, 12, 13, 13, 13, 13, 14, 14, 14, 14, 11, 11, 11, 11}, {2, 2, 2, 2, 3, 3, 3, 3, 4, 4, 4, 4, 1, 1, 1, 1}, {2, 2, 2, 2, 3, 3, 3, 3, 4, 4, 4, 4, 1, 1, 1, 1}}, new byte[][]{{7, 7, 7, 7, 8, 8, 8, 8, 9, 9, 9, 9, 6, 6, 6, 6}, {12, 12, 12, 12, 13, 13, 13, 13, 14, 14, 14, 14, 11, 11, 11, 11}, {2, 2, 2, 2, 3, 3, 3, 3, 4, 4, 4, 4, 1, 1, 1, 1}, {2, 2, 2, 2, 3, 3, 3, 3, 4, 4, 4, 4, 1, 1, 1, 1}}};
    public byte[][] FRAMESTAND = new byte[][]{{5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6}, {10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}};
    public byte[] dysai = new byte[]{-11, -7, -7};
    byte numW;
    byte numH;
    byte vTam = 0;
    byte xwater = 0;

    public Monstervantieu(int n, int n2, int n3, String string, int n4, int n5, int n6, int n7) {
        this.typeMonster = n3;
        this.ID = n;
        this.catalogyMonster = n2;
        this.typeObject = 1;
        this.name = string;
        this.x = n4;
        this.y = n5;
        this.hOne = -1;
        this.wOne = -1;
        this.maxHp = n6;
        this.Lv = (short)n7;
        this.typemove = this.getTypemove();
        this.Action = 0;
        this.MonWater = 0;
        this.numW = (byte)3;
        this.numH = (byte)5;
        this.nFrame = this.numW * this.numH;
        this.timeLoadInfo = mSystem.currentTimeMillis();
    }

    public boolean isMonsterVantieuTrang() {
        return this.catalogyMonster == 129 || this.catalogyMonster == 131 || this.catalogyMonster == 127 || this.catalogyMonster == 177;
    }

    public boolean isMonsterVantieuDen() {
        return this.catalogyMonster == 128 || this.catalogyMonster == 130 || this.catalogyMonster == 132 || this.catalogyMonster == 176;
    }

    public boolean isMonstervantieu() {
        return true;
    }

    public void setspeedVantieu(int n) {
        this.vMax = n;
        this.Maxspeed = (byte)n;
        this.vTam = (byte)(n + 1);
    }

    public String getnameOwner() {
        return this.nameowner;
    }

    protected byte getTypemove() {
        switch (this.catalogyMonster) {
            case 127: 
            case 128: {
                return 0;
            }
            case 129: 
            case 130: {
                return 1;
            }
            case 131: 
            case 132: 
            case 176: 
            case 177: {
                return 2;
            }
        }
        return 0;
    }

    public void paint(mGraphics mGraphics2) {
        MainImage mainImage = ObjectData.getImagePartMonster((short)this.catalogyMonster);
        if (mainImage != null && !this.isDongBang && mainImage.img != null) {
            this.xwater = this.Direction == 3 ? (byte)-2 : (this.Direction == 2 ? (byte)2 : (byte)0);
            if (this.wOne < 0) {
                this.wOne = mImage.getImageWidth(mainImage.img.image) / this.numW;
                this.hOne = mImage.getImageHeight(mainImage.img.image) / this.numH;
                this.yEffAuto = this.hOne / 2;
            }
            this.paintDataEff_Top(mGraphics2, this.x, this.y);
            if (this.frameMount >= 0 && this.frameMount < this.nFrame) {
                int n = 0;
                int n2 = 0;
                n = this.frameMount / this.numH * this.wOne;
                n2 = this.frameMount % this.numH * this.hOne;
                if (this.isWater) {
                    mGraphics2.drawRegion(mainImage.img, n, n2, this.wOne, this.hOne, this.Direction > 2 ? 2 : 0, this.x, this.y - this.ysai - this.dy + this.dyWater, 33, true);
                    mGraphics2.drawRegion(MainObject.water, 0, (this.Action != 0 ? 2 : 0) * 17 + GameCanvas.gameTick / 2 % 2 * 17, 28, 17, 0, this.x + this.xwater, this.y + this.dyWater - 8, 3, false);
                } else {
                    MainObject.paintShadow(mGraphics2, this.Direction, this.x + this.xwater, this.y - this.ysai + this.dysai[this.typemove]);
                    mGraphics2.drawRegion(mainImage.img, n, n2, this.wOne, this.hOne, this.Direction > 2 ? 2 : 0, this.x, this.y - this.ysai - this.dy + this.dyWater, 33, true);
                }
            }
        }
        this.paintDataEff_Bot(mGraphics2, this.x, this.y);
        mFont.tahoma_7_white.drawString(mGraphics2, this.nameowner, this.x, this.y - this.hOne - 3 - (this.isDongBang ? 5 : 0), 2, false);
        super.paint(mGraphics2);
    }

    public void updateMonsterAction() {
        this.fMount = (byte)(this.fMount + 1);
        if (this.Action == 0) {
            if (this.fMount > this.FRAMESTAND[this.Direction].length - 1) {
                this.fMount = 0;
            }
            this.frameMount = this.FRAMESTAND[this.Direction][this.fMount];
        } else if (this.Action == 1) {
            if (this.fMount > this.FRAMEMOVE[this.typemove][this.Direction].length - 1) {
                this.fMount = 0;
            }
            this.frameMount = this.FRAMEMOVE[this.typemove][this.Direction][this.fMount];
        }
    }

    public void update() {
        long l;
        long l2;
        super.update();
        this.updateMonsterAction();
        this.updateDataEffect();
        int n = GameCanvas.loadmap.getTile(this.x + this.vx, this.y + this.vy);
        this.setMove(this.MonWater, n);
        if (!this.Canmove() || this.isBinded) {
            return;
        }
        if (!this.isInfo && (l2 = (l = mSystem.currentTimeMillis()) - this.timeLoadInfo) >= 5000L) {
            this.timeLoadInfo = l;
            GlobalService.gI().monster_info((short)this.ID);
        }
        if (this.vx == 0 && this.vy == 0) {
            this.Action = 0;
        }
        if (MainObject.getDistance(this.x, this.y, this.toX, this.toY) >= LoadMap.wTile * 3 && MainObject.getDistance(this.x, this.y, this.toX, this.toY) <= LoadMap.wTile * 4) {
            this.Maxspeed = this.vTam;
        }
        if (MainObject.getDistance(this.x, this.y, this.toX, this.toY) < LoadMap.wTile * 3) {
            this.vMax = (byte)(this.vTam - 1);
        }
        if (this.Maxspeed > 1) {
            this.vMax = this.frameMount == 3 || this.frameMount == 8 || this.frameMount == 13 ? this.Maxspeed - 1 : (int)this.Maxspeed;
        }
    }
}

