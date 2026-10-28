/*
 * Decompiled with CFR 0.152.
 */
package Map;

import CLib.mGraphics;
import CLib.mImage;
import GameScreen.MainScreen;
import Main.GameCanvas;
import Model.CRes;
import Model.Point;
import Thread_More.LoadMap;

public class MapBackGround {
    byte typeMap;
    byte[] mList3;
    int w1;
    int w2;
    int w3;
    int size1;
    int size2;
    int size3;
    int h1;
    int h2;
    int h3;
    int sizeScreen;
    int hBack;
    int hLimit;
    int valueRandom;
    int sizeScreen2;
    int nX;
    Point[] PosCloud;
    mImage[] mImg;
    mImage[] mImgCloud;
    public int color;
    public int colorMini;

    public void setBackGround(byte by, short s) {
        int n;
        this.typeMap = by;
        this.hBack = s;
        if (GameCanvas.lowGraphic) {
            this.color = -9784873;
            this.colorMini = -9784873;
            return;
        }
        switch (this.typeMap) {
            case 1: {
                this.w1 = 256;
                this.size1 = GameCanvas.loadmap.mapW * LoadMap.wTile / this.w1 + 1;
                this.color = -8725531;
                this.colorMini = -8725531;
                this.h1 = 92;
                this.h2 = 85;
                this.h3 = 110;
                this.hLimit = this.hBack - (this.h1 + this.h2 + this.h3);
                this.sizeScreen = GameCanvas.w / this.w1 + 1;
                this.mImg = new mImage[3];
                this.mImg[0] = mImage.createImage("/bg/bg1_0.img");
                this.mImg[1] = mImage.createImage("/bg/bg1_1.img");
                this.mImg[2] = mImage.createImage("/bg/bg1_2.img");
                this.valueRandom = (this.hLimit + 30) / 2;
                break;
            }
            case 2: {
                this.w1 = 120;
                this.size1 = GameCanvas.loadmap.mapW * LoadMap.wTile / this.w1 + 1;
                this.color = -10836481;
                this.colorMini = -11759720;
                this.h1 = 72;
                this.h2 = 28;
                this.h3 = 77;
                this.hLimit = this.hBack - (this.h1 + this.h2 + this.h3);
                this.sizeScreen = GameCanvas.w / this.w1 + 1;
                this.mImg = new mImage[5];
                this.mImg[0] = mImage.createImage("/bg/bg2_0.img");
                this.mImg[1] = mImage.createImage("/bg/bg2_1.img");
                this.mImg[2] = mImage.createImage("/bg/bg2_20.img");
                this.mImg[3] = mImage.createImage("/bg/bg2_21.img");
                this.mImg[4] = mImage.createImage("/bg/bg2_22.img");
                this.mList3 = new byte[this.size1];
                for (n = 0; n < this.size1; ++n) {
                    this.mList3[n] = (byte)CRes.random(3);
                }
                this.valueRandom = 25;
                break;
            }
            case 3: {
                this.w1 = 253;
                this.w2 = 96;
                this.size1 = GameCanvas.loadmap.mapW * LoadMap.wTile / this.w1 + 1;
                this.size2 = GameCanvas.loadmap.mapW * LoadMap.wTile / this.w2 + 1;
                this.color = -9784873;
                this.colorMini = -9784873;
                this.h1 = 108;
                this.h2 = 72;
                this.nX = (this.hBack - 120) / this.h2;
                if (this.nX > 5) {
                    this.nX = 5;
                }
                this.hLimit = this.hBack - (this.h1 + this.h2 * this.nX);
                this.sizeScreen = GameCanvas.w / this.w1 + 1;
                this.sizeScreen2 = GameCanvas.w / this.w2 + 1;
                this.mImg = new mImage[2];
                this.mImg[0] = mImage.createImage("/bg/bg3_0.img");
                this.mImg[1] = mImage.createImage("/bg/bg3_1.img");
                this.valueRandom = (this.hLimit + 30) / 2;
            }
        }
        this.PosCloud = new Point[GameCanvas.loadmap.mapW * LoadMap.wTile / 250 + 1];
        for (n = 0; n < this.PosCloud.length; ++n) {
            this.PosCloud[n] = new Point();
            this.PosCloud[n].x = 125 + CRes.random_Am_0(125) + n * 250;
            this.PosCloud[n].y = this.valueRandom + CRes.random_Am_0(this.valueRandom);
            this.PosCloud[n].vx = -CRes.random(1, 3);
            this.PosCloud[n].frame = CRes.random(0, 2);
        }
        this.mImgCloud = new mImage[2];
        this.mImgCloud[0] = mImage.createImage("/bg/may0.img");
        this.mImgCloud[1] = mImage.createImage("/bg/may1.img");
    }

    public void paint(mGraphics mGraphics2) {
        int n;
        if (GameCanvas.lowGraphic) {
            if (MainScreen.cameraMain.yCam <= this.hBack) {
                mGraphics2.setColor(this.color);
                mGraphics2.fillRect(MainScreen.cameraMain.xCam, MainScreen.cameraMain.yCam, GameCanvas.w, GameCanvas.h, false);
            }
            return;
        }
        int n2 = 0;
        int n3 = 0;
        int n4 = 0;
        int n5 = 0;
        switch (this.typeMap) {
            case 1: {
                n2 = MainScreen.cameraMain.xCam / this.w1;
                n4 = n2 + this.sizeScreen + 1;
                if (n4 > this.size1) {
                    n4 = this.size1;
                }
                if (MainScreen.cameraMain.yCam <= this.hLimit) {
                    mGraphics2.setColor(this.color);
                    mGraphics2.fillRect(MainScreen.cameraMain.xCam, MainScreen.cameraMain.yCam, GameCanvas.w, this.hLimit, false);
                }
                for (n = n2; n < n4; ++n) {
                    if (MainScreen.cameraMain.yCam <= this.h1 + this.hLimit) {
                        mGraphics2.drawImage(this.mImg[0], n * this.w1, this.hLimit, 0, false);
                    }
                    if (MainScreen.cameraMain.yCam <= this.h2 + this.h1 + this.hLimit) {
                        mGraphics2.drawImage(this.mImg[1], n * this.w1, this.h1 + this.hLimit, 0, false);
                    }
                    if (MainScreen.cameraMain.yCam > this.h2 + this.h1 + this.h3 + this.hLimit) continue;
                    mGraphics2.drawImage(this.mImg[2], n * this.w1, this.h1 + this.h2 + this.hLimit, 0, false);
                }
                break;
            }
            case 2: {
                n2 = MainScreen.cameraMain.xCam / this.w1;
                if (n2 < 0) {
                    n2 = 0;
                }
                if ((n4 = n2 + this.sizeScreen + 1) > this.size1) {
                    n4 = this.size1;
                }
                if (MainScreen.cameraMain.yCam <= this.hLimit) {
                    mGraphics2.setColor(this.color);
                    mGraphics2.fillRect(MainScreen.cameraMain.xCam, MainScreen.cameraMain.yCam, GameCanvas.w, this.hLimit, false);
                }
                for (n = n2; n < n4; ++n) {
                    if (MainScreen.cameraMain.yCam <= this.h1 + this.hLimit) {
                        mGraphics2.drawImage(this.mImg[0], n * this.w1, this.hLimit, 0, false);
                    }
                    if (MainScreen.cameraMain.yCam <= this.h2 + this.h1 + this.hLimit) {
                        mGraphics2.drawImage(this.mImg[1], n * this.w1, this.h1 + this.hLimit, 0, false);
                    }
                    if (MainScreen.cameraMain.yCam > this.h2 + this.h1 + this.h3 + this.hLimit) continue;
                    mGraphics2.drawImage(this.mImg[2 + this.mList3[n]], n * this.w1, this.h1 + this.h2 + this.hLimit, 0, false);
                }
                break;
            }
            case 3: {
                if (MainScreen.cameraMain.yCam <= this.hLimit) {
                    mGraphics2.setColor(this.color);
                    mGraphics2.fillRect(MainScreen.cameraMain.xCam, MainScreen.cameraMain.yCam, GameCanvas.w, this.hLimit, false);
                }
                if ((n3 = MainScreen.cameraMain.xCam / this.w2) < 0) {
                    n3 = 0;
                }
                if ((n5 = n3 + this.sizeScreen2 + 1) > this.size2) {
                    n5 = this.size2;
                }
                for (n = n3; n < n5; ++n) {
                    for (int i = 0; i < this.nX; ++i) {
                        mGraphics2.drawImage(this.mImg[1], n * this.w2, this.hLimit + this.h1 + i * this.h2, 0, false);
                    }
                }
                n2 = MainScreen.cameraMain.xCam / this.w1;
                if (n2 < 0) {
                    n2 = 0;
                }
                if ((n4 = n2 + this.sizeScreen + 1) > this.size1) {
                    n4 = this.size1;
                }
                for (n = n2; n < n4; ++n) {
                    if (MainScreen.cameraMain.yCam > this.h1 + this.hLimit) continue;
                    mGraphics2.drawImage(this.mImg[0], n * this.w1, this.hLimit, 0, false);
                }
                break;
            }
        }
        for (n = 0; n < this.PosCloud.length; ++n) {
            if (MainScreen.cameraMain.yCam - 10 > this.PosCloud[n].y) continue;
            mGraphics2.drawImage(this.mImgCloud[this.PosCloud[n].frame], this.PosCloud[n].x, this.PosCloud[n].y, 6, false);
        }
    }

    public void updateCloud() {
        if (GameCanvas.lowGraphic) {
            return;
        }
        for (int i = 0; i < this.PosCloud.length; ++i) {
            this.PosCloud[i].x += this.PosCloud[i].vx;
            if (this.PosCloud[i].x >= -80) continue;
            this.PosCloud[i].x = GameCanvas.loadmap.mapW * LoadMap.wTile + CRes.random_Am_0(125);
            this.PosCloud[i].y = this.valueRandom + CRes.random_Am_0(this.valueRandom);
            this.PosCloud[i].vx = -CRes.random(1, 3);
            this.PosCloud[i].frame = CRes.random(0, 2);
        }
    }
}

