/*
 * Decompiled with CFR 0.152.
 */
package GameEffect;

import CLib.mGraphics;
import GameObjects.MainObject;
import GameScreen.MainScreen;
import Main.GameCanvas;
import Model.FrameImage;

public class MainEffect {
    boolean isPaint = true;
    public int typeEffect = 0;
    public int valueEffect;
    public int timeRemove;
    int fRemove;
    public int x;
    public int y;
    public int toX;
    public int toY;
    public int f;
    public int frame;
    public int ysai;
    public int Direction;
    public int vMax;
    public int vx;
    public int vy;
    public int hOne;
    public int subf;
    public int levelPaint = 0;
    long timeBegin;
    public MainObject objBeKillMain;
    public byte[] nFrame;
    public byte[] nSubFrame;
    public FrameImage fraImgEff;
    public FrameImage fraImgSubEff;
    public FrameImage fraImgSub2Eff;
    public FrameImage fraImgSub3Eff;
    public boolean isRemove;
    public boolean isStop;

    public void paint(mGraphics mGraphics2) {
    }

    public void clearData() {
        if (this.fraImgEff != null) {
            this.fraImgEff.clearData();
            this.fraImgEff = null;
        }
        if (this.fraImgSubEff != null) {
            this.fraImgSubEff.clearData();
            this.fraImgSubEff = null;
        }
        if (this.fraImgSub2Eff != null) {
            this.fraImgSub2Eff.clearData();
            this.fraImgSub2Eff = null;
        }
        if (this.fraImgSub3Eff != null) {
            this.fraImgSub3Eff.clearData();
            this.fraImgSub3Eff = null;
        }
    }

    public void update() {
        this.x += this.vx;
        this.y += this.vy;
    }

    public void setPosition(int n, int n2, int n3, int n4) {
        this.x = n;
        this.y = n2;
        this.toX = n3;
        this.toY = n4;
    }

    public static boolean isInScreen(int n, int n2, int n3, int n4) {
        return n >= MainScreen.cameraMain.xCam - n3 && n <= MainScreen.cameraMain.xCam + GameCanvas.w + n3 && n2 >= MainScreen.cameraMain.yCam - n4 && n2 <= MainScreen.cameraMain.yCam + GameCanvas.h + n4;
    }

    public void setObjFrom(short s, byte by) {
    }

    public void setTimeRemove(short s) {
    }
}

