/*
 * Decompiled with CFR 0.152.
 */
package GameObjects;

import CLib.mGraphics;
import CLib.mImage;
import GameObjects.MainMonster;
import GameObjects.ObjectData;
import GameScreen.GameScreen;
import Main.GameCanvas;
import Model.CRes;
import Model.MainImage;
import Thread_More.LoadMap;

public class MonsterQuest
extends MainMonster {
    int fpos = 0;
    boolean isReveiceQuest = false;
    static byte[] mFrameImg = new byte[]{0, 1, 2, 3, 4, 5, 5, 5, 5, 4, 3, 2, 1, 0};
    static byte[][] mPosImg = new byte[][]{{0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, -1, -1, 0, 0, 1, 1}};
    int test = 0;
    int hRe;

    public MonsterQuest(int n, int n2, int n3, String string, int n4, int n5, int n6, int n7) {
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
        this.nFrame = 6;
        this.hOne = -1;
        this.wOne = -1;
        this.ysai = -2;
    }

    public void paint(mGraphics mGraphics2) {
        if (!this.isDie) {
            MainImage mainImage = ObjectData.getImagePartMonster((short)this.catalogyMonster);
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
                int n = 0;
                int n2 = mFrameImg[this.f / 3] * this.hOne;
                if (this.catalogyMonster > 92) {
                    n = mFrameImg[this.f / 3] / 3 * this.wOne;
                    n2 = mFrameImg[this.f / 3] % 3 * this.hOne;
                }
                if (this.isReveiceQuest) {
                    if (this.timeTanHinh > this.test) {
                        mGraphics2.drawRegion(mainImage.img, n, n2, this.wOne, this.hRe, 0, this.x, this.y - this.hOne / 2, 33, false);
                        mGraphics2.drawRegion(mainImage.img, n, n2 + this.hOne - this.hRe, this.wOne, this.hRe, 0, this.x, this.y - this.hOne / 2 + this.hRe, 33, false);
                    }
                } else {
                    mGraphics2.drawRegion(mainImage.img, n, n2, this.wOne, this.hOne, 0, this.x + mPosImg[0][this.fpos / 3], this.y + mPosImg[1][this.fpos / 3], 33, false);
                }
            }
            if (this.Action != 4 && !this.isReveiceQuest) {
                this.paintName(mGraphics2, 0);
            }
        }
    }

    public void update() {
        this.setDie();
        if (this.Action != 4) {
            if (this.isReveiceQuest) {
                ++this.timeTanHinh;
                this.hRe += 2;
                if (this.hRe >= (this.hOne - 1) / 2) {
                    this.isReveiceQuest = false;
                    this.timeTanHinh = 0;
                    this.hRe = 10;
                }
            }
            ++this.f;
            if (this.f / 3 > mFrameImg.length - 1) {
                this.f = 0;
            }
            ++this.fpos;
            if (this.fpos / 3 > mPosImg[0].length - 1) {
                this.fpos = 0;
            }
        }
        super.update();
    }

    public void setDie() {
        if (this.Action == 4) {
            if (CRes.random(3) == 1) {
                if (CRes.random(2) == 1) {
                    LoadMap.timeVibrateScreen = 103;
                }
                int n = CRes.random(1, 3);
                for (int i = 0; i < n; ++i) {
                    int n2 = CRes.random_Am_0(20);
                    int n3 = CRes.random_Am_0(30);
                    GameScreen.addEffectEndKill(36, this.x + n2, this.y + n3 - this.hOne / 2);
                    if (CRes.random(3) != 1) continue;
                    GameScreen.addEffectEndKill(9, this.x + n2, this.y + n3 - this.hOne / 2);
                }
            }
            if (this.timeReveice >= 0 && (GameCanvas.timeNow - this.timedie) / 1000L > (long)(this.timeReveice - 1)) {
                this.Reveive();
                this.isReveiceQuest = true;
                this.timeTanHinh = 0;
                this.hRe = 10;
                GameScreen.addEffectKill(81, this.x, this.y - 20, 200, (short)0, (byte)0);
                GameScreen.addEffectEndKill(39, this.x, this.y - this.hOne / 2);
            }
        } else if (this.hp <= 0) {
            this.hp = 0;
            this.Action = 4;
            this.resetXY();
            this.timedie = GameCanvas.timeNow;
        }
    }
}

