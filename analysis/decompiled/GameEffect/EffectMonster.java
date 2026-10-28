/*
 * Decompiled with CFR 0.152.
 */
package GameEffect;

import CLib.mGraphics;
import CLib.mImage;
import CLib.mSystem;
import CLib.mVector;
import GameObjects.MainMonster;
import GameObjects.MainObject;
import GameScreen.GameScreen;
import Map.ItemMap;
import Model.FrameImage;

public class EffectMonster {
    public static mVector listEffectMonster = new mVector("EffectMonster listEffectMonster");
    FrameImage imgAuto;
    FrameImage imgDie;
    FrameImage imgEff;
    boolean isPaint = true;
    boolean isUpdate = false;
    boolean isPaintDie = true;
    int x;
    int y;
    int w;
    int h;
    int dx;
    int dy;
    int f;
    int idMon;
    int wImg;
    int hImg;
    int fDie;
    int dxDie;
    int dyDie;
    MainObject monster;
    public byte[] nFrame = new byte[]{0, 0, 1, 1, 2, 2, 3, 3};
    public byte[] nFramedie = new byte[]{0, 0, 0, 0, 0, 0, 0, 0};
    public static boolean buffDame = false;
    public static final byte mon_Tower1 = 89;
    public static final byte mon_Tower2 = 90;
    public static final byte mon_Tower3 = 91;
    public static final byte mon_Tower4 = 92;

    public static void addEffectMonster(MainMonster mainMonster) {
        EffectMonster effectMonster = new EffectMonster();
        effectMonster.idMon = mainMonster.catalogyMonster;
        effectMonster.monster = mainMonster;
        if (effectMonster.monster.isMonsterHouse()) {
            if (effectMonster.idMon == 89) {
                effectMonster.imgAuto = new FrameImage(mImage.createImage("/eff/g128.png"), 53, 49);
                effectMonster.imgDie = ItemMap.img_HouseArena_Die[0];
                effectMonster.dx = -3;
                effectMonster.dy = 70;
                effectMonster.dxDie = 0;
                effectMonster.dyDie = -effectMonster.imgDie.frameHeight - 2;
            } else if (effectMonster.idMon == 90) {
                effectMonster.imgAuto = new FrameImage(mImage.createImage("/eff/g129.png"), 44, 44);
                effectMonster.imgDie = ItemMap.img_HouseArena_Die[1];
                effectMonster.dx = 0;
                effectMonster.dy = 30;
                effectMonster.dxDie = 0;
                effectMonster.dyDie = -effectMonster.imgDie.frameHeight;
            } else if (effectMonster.idMon == 91) {
                effectMonster.imgAuto = new FrameImage(mImage.createImage("/eff/g130.png"), 50, 51);
                effectMonster.imgDie = ItemMap.img_HouseArena_Die[2];
                effectMonster.dx = 0;
                effectMonster.dy = 20;
                effectMonster.dxDie = 5;
                effectMonster.dyDie = -effectMonster.imgDie.frameHeight;
            } else if (effectMonster.idMon == 92) {
                effectMonster.imgAuto = new FrameImage(mImage.createImage("/eff/g131.png"), 40, 40);
                effectMonster.imgDie = ItemMap.img_HouseArena_Die[3];
                effectMonster.dx = 22;
                effectMonster.dy = 22;
                effectMonster.dxDie = 5;
                effectMonster.dyDie = -effectMonster.imgDie.frameHeight;
            }
            effectMonster.x = mainMonster.x;
            effectMonster.y = mainMonster.y;
            effectMonster.h = -1;
            effectMonster.w = -1;
            effectMonster.h = mainMonster.hOne;
            effectMonster.wImg = effectMonster.imgAuto.frameWidth;
            effectMonster.hImg = effectMonster.imgAuto.frameHeight;
            effectMonster.isPaint = true;
            effectMonster.isUpdate = true;
            effectMonster.isPaintDie = false;
            effectMonster.f = 0;
            effectMonster.fDie = 0;
            effectMonster.imgEff = new FrameImage(68);
            listEffectMonster.addElement(effectMonster);
        }
    }

    public void setTurnOff() {
        if (this.isPaintDie) {
            this.isPaint = false;
        }
        if (!this.isPaintDie) {
            this.isPaintDie = true;
        }
    }

    public void update() {
        if (this.isUpdate) {
            if (buffDame) {
                if (this.nFrame != new byte[]{0, 0, 1, 1, 2, 2, 3, 3}) {
                    this.nFrame = new byte[]{0, 0, 1, 1, 2, 2, 3, 3};
                }
            } else if (this.nFrame != new byte[]{0, 0, 0, 1, 1, 1, 2, 2, 2, 3, 3, 3}) {
                this.nFrame = new byte[]{0, 0, 0, 1, 1, 1, 2, 2, 2, 3, 3, 3};
            }
            if (this.monster.hp > 0) {
                ++this.f;
                if (this.f > this.nFrame.length - 1) {
                    this.f = 0;
                }
                this.x = this.monster.x;
                this.y = this.monster.y;
            } else {
                this.setTurnOff();
            }
            if (GameScreen.timeArena != 0L && mSystem.currentTimeMillis() - GameScreen.timeArena > 2700000L) {
                this.setTurnOff();
            }
        }
    }

    public void paint(mGraphics mGraphics2) {
        try {
            if (this.isPaint && this.imgAuto != null) {
                if (this.w < 0) {
                    this.w = this.monster.wOne;
                    this.h = this.monster.hOne;
                }
                this.imgAuto.drawFrame(this.nFrame[this.f], this.x - this.wImg / 2 + this.dx, this.y - this.h - this.hImg + this.dy, 0, mGraphics2);
            }
        }
        catch (Exception exception) {
            this.isPaint = false;
            this.isUpdate = false;
            this.isPaintDie = false;
        }
    }

    public void paintDie(mGraphics mGraphics2) {
        try {
            if (this.isPaintDie && this.imgDie != null) {
                if (this.w < 0) {
                    this.w = this.monster.wOne;
                    this.h = this.monster.hOne;
                }
                this.imgDie.drawFrame(this.nFramedie[this.fDie], this.x - this.w / 2 + this.dxDie, this.y + this.dyDie, 0, mGraphics2);
            }
        }
        catch (Exception exception) {
            this.isPaint = false;
            this.isUpdate = false;
            this.isPaintDie = false;
        }
    }
}

