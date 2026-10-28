/*
 * Decompiled with CFR 0.152.
 */
package GameEffect;

import CLib.mGraphics;
import CLib.mImage;
import CLib.mVector;
import GameEffect.MainEffect;
import GameScreen.GameScreen;
import GameScreen.MainScreen;
import Main.GameCanvas;
import Model.CRes;
import Model.Point;

public class AnimateEffect
extends MainEffect {
    public static final byte RAIN = 0;
    public static final byte FALLING_LEAVES = 1;
    public static final byte SNOW = 2;
    public static final byte FALLING_FLOWER = 3;
    public static final byte THIEN_THACH = 4;
    public static final byte CLOUD = 5;
    public byte type = 0;
    public int number = 0;
    public int timeLimit;
    public int curTime;
    public int numClound;
    public mVector list = new mVector("AnimateEffect list");
    public boolean isStop;
    private static int wind = 5;
    private static int countWind;
    private static int dirWind;
    public mImage imgEffect;
    public mImage[] imgEffectCloud;
    int wimg;
    int himg;
    int vx;
    int vy;

    public void close() {
    }

    public void stop() {
        this.isStop = true;
    }

    public AnimateEffect(byte by, boolean bl, int n, int n2) {
        this.timeLimit = n2;
        this.curTime = (int)(GameCanvas.timeNow / 1000L);
        this.type = by;
        int n3 = 1;
        switch (by) {
            case 0: {
                break;
            }
            case 1: {
                n3 = 3;
                this.imgEffect = mImage.createImage("/efleaf.img");
                break;
            }
            case 2: {
                n3 = 2;
                this.imgEffect = mImage.createImage("/efsnow.img");
                break;
            }
            case 3: {
                this.imgEffect = mImage.createImage("/efhoa.img");
                break;
            }
            case 4: {
                n3 = 12;
                break;
            }
            case 5: {
                this.isStop = true;
                return;
            }
        }
        this.number = n > 0 ? n : (n == -1 ? GameCanvas.w * GameCanvas.h / (8 * n3 * 200) : (n == -2 ? GameCanvas.w * GameCanvas.h / (6 * n3 * 200) : (n == -3 ? GameCanvas.w * GameCanvas.h / (3 * n3 * 200) : (n == -4 ? GameCanvas.w * GameCanvas.h / (2 * n3 * 200) : 10))));
        if (by != 4) {
            for (int i = 0; i < this.number; ++i) {
                Point point = null;
                if (bl) {
                    point = new Point((MainScreen.cameraMain.xCam - GameCanvas.hw + CRes.random(GameCanvas.w * 2)) * 10, (MainScreen.cameraMain.yCam - GameCanvas.h * 2 + CRes.random(GameCanvas.h * 2)) * 10);
                } else {
                    point = new Point();
                    this.rndPos(point);
                }
                point.h = by == 2 || this.type == 3 ? CRes.random(3) : CRes.random(4);
                if (by == 5) {
                    point.vx = CRes.random(2, 8);
                    point.vy = CRes.random(2, 12);
                }
                point.limitY = 16 + CRes.random(3) * 4;
                point.v = CRes.random(-1, 1);
                point.color = CRes.random(point.limitY);
                point.frame = CRes.random(2);
                point.dis = by == 2 ? (int)((byte)CRes.random(6)) : (by == 1 ? (int)((byte)CRes.random(4)) : (int)((byte)CRes.random(20)));
                this.list.addElement(point);
            }
        }
    }

    public void paint(mGraphics mGraphics2) {
        GameCanvas.resetTrans(mGraphics2);
        mGraphics2.translate(-MainScreen.cameraMain.xCam, -MainScreen.cameraMain.yCam);
        switch (this.type) {
            case 0: {
                this.paintRain(mGraphics2);
                break;
            }
            case 1: {
                this.paintFallingLeaves(mGraphics2);
                break;
            }
            case 3: {
                this.paintFallingFlower(mGraphics2);
                break;
            }
            case 2: {
                this.paintSnow(mGraphics2);
                break;
            }
            case 5: {
                this.paintCloud(mGraphics2);
            }
        }
    }

    private void paintCloud(mGraphics mGraphics2) {
        if (this.imgEffectCloud != null) {
            for (int i = 0; i < this.list.size(); ++i) {
                Point point = (Point)this.list.elementAt(i);
                if (point.x / 10 <= MainScreen.cameraMain.xCam - this.wimg / 2 || point.x / 10 >= MainScreen.cameraMain.xCam + GameCanvas.w + this.wimg / 2 || point.y / 10 <= MainScreen.cameraMain.yCam - this.himg / 2) continue;
                mGraphics2.drawImage(this.imgEffectCloud[point.frame], point.x / 10, point.y / 10, 3, false);
            }
        }
    }

    private void paintSnow(mGraphics mGraphics2) {
        if (this.imgEffect != null) {
            for (int i = 0; i < this.number; ++i) {
                Point point = (Point)this.list.elementAt(i);
                if (point.x / 10 <= MainScreen.cameraMain.xCam || point.x / 10 >= MainScreen.cameraMain.xCam + GameCanvas.w || point.y / 10 <= MainScreen.cameraMain.yCam) continue;
                mGraphics2.drawRegion(this.imgEffect, 0, 7 * point.dis, 7, 7, 0, point.x / 10, point.y / 10, 3, false);
            }
        }
    }

    private void paintFallingFlower(mGraphics mGraphics2) {
        int n = 0;
        if (this.imgEffect != null) {
            for (int i = 0; i < this.number; ++i) {
                Point point = (Point)this.list.elementAt(i);
                if (point.x / 10 <= MainScreen.cameraMain.xCam || point.x / 10 >= MainScreen.cameraMain.xCam + GameCanvas.w || point.y / 10 <= MainScreen.cameraMain.yCam) continue;
                n = 2 - point.h + 1;
                if (n < 2) {
                    n = point.dis / 10;
                }
                mGraphics2.drawRegion(this.imgEffect, 0, n * 10, 10, 10, 0, point.x / 10, point.y / 10, 3, false);
                ++point.dis;
                if (point.dis < 20) continue;
                point.dis = 0;
            }
        }
    }

    private void paintFallingLeaves(mGraphics mGraphics2) {
        if (this.imgEffect != null) {
            for (int i = 0; i < this.number; ++i) {
                Point point = (Point)this.list.elementAt(i);
                if (point.x / 10 <= MainScreen.cameraMain.xCam || point.x / 10 >= MainScreen.cameraMain.xCam + GameCanvas.w || point.y / 10 <= MainScreen.cameraMain.yCam) continue;
                if (CRes.random(6) == 0) {
                    point.dis = (byte)CRes.random(4);
                }
                mGraphics2.drawRegion(this.imgEffect, 0, point.dis * 10, 16, 10, 0, point.x / 10, point.y / 10, 3, false);
            }
        }
    }

    private void paintRain(mGraphics mGraphics2) {
        mGraphics2.setColor(-2236963);
        for (int i = 0; i < this.number; ++i) {
            Point point = (Point)this.list.elementAt(i);
            if (point.x / 10 <= MainScreen.cameraMain.xCam || point.x / 10 >= MainScreen.cameraMain.xCam + GameCanvas.w || point.y / 10 <= MainScreen.cameraMain.yCam) continue;
            mGraphics2.fillRect(point.x / 10, point.y / 10, 1, point.h + 2, false);
        }
    }

    public static void updateWind() {
        int n = 1;
        if (GameCanvas.gameTick % 6 == 3) {
            n = CRes.random(15);
        }
        if (n == 0 && wind == 5) {
            wind = 5 + CRes.random(20);
            countWind = 50 + CRes.random(100);
        }
        if (countWind > 0) {
            --countWind;
        }
        if (countWind == 0 && wind > 5 && GameCanvas.gameTick % 4 == 2) {
            --wind;
        }
    }

    public void update() {
        if (this.timeLimit > 0 && GameCanvas.timeNow / 1000L - (long)this.curTime > (long)this.timeLimit) {
            this.isStop = true;
        }
        switch (this.type) {
            case 0: {
                this.updateRain();
                break;
            }
            case 1: {
                this.updateFallingLeaves();
                break;
            }
            case 3: {
                this.updateFlower();
                break;
            }
            case 2: {
                this.updateSnow();
                break;
            }
            case 4: {
                this.updateThienThach();
                break;
            }
            case 5: {
                this.updateCloud();
            }
        }
    }

    private void updateCloud() {
        for (int i = 0; i < this.list.size(); ++i) {
            Point point = (Point)this.list.elementAt(i);
            point.y += point.vy;
            point.x += point.vx * dirWind;
            if (point.y / 10 >= MainScreen.cameraMain.yCam - GameCanvas.hw && point.y / 10 <= MainScreen.cameraMain.yCam + (GameCanvas.h + GameCanvas.hh) && point.x / 10 >= MainScreen.cameraMain.xCam - GameCanvas.hw && point.x / 10 <= MainScreen.cameraMain.xCam + GameCanvas.w + GameCanvas.hw) continue;
            this.list.removeElement(point);
            --i;
        }
        if (this.timeLimit > 0 && GameCanvas.timeNow / 1000L - (long)this.curTime > (long)this.timeLimit) {
            return;
        }
        if (!this.isStop && CRes.random(350) < this.numClound) {
            Point point = null;
            point = new Point();
            this.rndPos(point);
            point.h = CRes.random(4);
            point.limitY = 16 + CRes.random(3) * 4;
            point.v = CRes.random(-1, 1);
            point.color = CRes.random(point.limitY);
            point.frame = CRes.random(2);
            point.dis = (byte)CRes.random(20);
            point.vx = CRes.random(2, 8);
            point.vy = CRes.random(2, 12);
            this.list.addElement(point);
        }
    }

    private void updateThienThach() {
        if (CRes.random(250) < this.number) {
            int n = CRes.random(1, 3);
            GameScreen.addEffectKill(86, 0, 0, 0, (short)0, (byte)n);
        }
    }

    private void updateSnow() {
        for (int i = 0; i < this.number; ++i) {
            Point point = (Point)this.list.elementAt(i);
            point.y += (point.h + 4) * 3;
            point.x += (point.h + 1) * 2 + wind * dirWind;
            if (point.y / 10 >= MainScreen.cameraMain.yCam - GameCanvas.hw && point.y / 10 <= MainScreen.cameraMain.yCam + (GameCanvas.h + GameCanvas.hh) - (4 - point.h) * 50 && point.x / 10 >= MainScreen.cameraMain.xCam - GameCanvas.hw && point.x / 10 <= MainScreen.cameraMain.xCam + GameCanvas.w + GameCanvas.hw || CRes.random(40) != 0) continue;
            this.rndPos(point);
        }
    }

    private void updateFlower() {
        for (int i = 0; i < this.number; ++i) {
            Point point = (Point)this.list.elementAt(i);
            point.y += (point.h + 2) * 5;
            point.x += (point.h + 1) * 2 + wind * dirWind;
            if (point.y / 10 >= MainScreen.cameraMain.yCam - GameCanvas.hw && point.y / 10 <= MainScreen.cameraMain.yCam + (GameCanvas.h + GameCanvas.hh) - (4 - point.h) * 50 && point.x / 10 >= MainScreen.cameraMain.xCam - GameCanvas.hw && point.x / 10 <= MainScreen.cameraMain.xCam + GameCanvas.w + GameCanvas.hw) continue;
            this.rndPos(point);
        }
    }

    private void updateFallingLeaves() {
        for (int i = 0; i < this.number; ++i) {
            Point point = (Point)this.list.elementAt(i);
            point.y += 15;
            point.x += point.v * 10 + wind * dirWind;
            ++point.color;
            if (point.color >= point.limitY) {
                point.color = 0;
            }
            if (point.y / 10 >= MainScreen.cameraMain.yCam - GameCanvas.hw && point.y / 10 <= MainScreen.cameraMain.yCam + (GameCanvas.h + GameCanvas.hh) - (4 - point.h) * 50 && point.x / 10 >= MainScreen.cameraMain.xCam - GameCanvas.hw && point.x / 10 <= MainScreen.cameraMain.xCam + GameCanvas.w + GameCanvas.hw) continue;
            this.rndPos(point);
        }
    }

    private void updateRain() {
        for (int i = 0; i < this.number; ++i) {
            Point point = (Point)this.list.elementAt(i);
            point.y += (point.h + 1) * 15 + (3 - point.h) * 3;
            ++point.g;
            point.x += (3 - point.h + 1) * 2 + wind * dirWind;
            if (point.y / 10 >= MainScreen.cameraMain.yCam - GameCanvas.hw && point.y / 10 <= MainScreen.cameraMain.yCam + (GameCanvas.h + GameCanvas.hh) - (4 - point.h) * 50 && point.x / 10 >= MainScreen.cameraMain.xCam - GameCanvas.hw && point.x / 10 <= MainScreen.cameraMain.xCam + GameCanvas.w + GameCanvas.hw) continue;
            this.rndPos(point);
        }
    }

    private void rndPos(Point point) {
        if (this.isStop) {
            this.list.removeElement(point);
            this.number = this.list.size();
            if (this.list.size() == 0) {
                this.close();
            }
        } else {
            if (this.type == 5) {
                point.y = (MainScreen.cameraMain.yCam - GameCanvas.hh + CRes.random(GameCanvas.h / 2)) * 10;
                point.x = dirWind > 0 ? (MainScreen.cameraMain.xCam - GameCanvas.hw + CRes.random(GameCanvas.w)) * 10 : (MainScreen.cameraMain.xCam + GameCanvas.hw + CRes.random(GameCanvas.w)) * 10;
                point.frame = CRes.random(2);
                point.vx = CRes.random(2, 8);
                point.vy = CRes.random(2, 12);
            } else {
                point.y = (MainScreen.cameraMain.yCam - GameCanvas.hh + CRes.random(GameCanvas.h * 2)) * 10;
                point.x = (MainScreen.cameraMain.xCam - GameCanvas.hw + CRes.random(GameCanvas.w * 2)) * 10;
            }
            point.h = this.type == 2 || this.type == 3 ? CRes.random(3) : (this.type == 0 ? CRes.random(1, 5) : CRes.random(4));
        }
    }

    static {
        dirWind = CRes.random_Am_0(2);
    }
}

