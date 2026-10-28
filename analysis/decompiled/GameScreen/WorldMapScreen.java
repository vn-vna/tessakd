/*
 * Decompiled with CFR 0.152.
 */
package GameScreen;

import CLib.mFont;
import CLib.mGraphics;
import GameScreen.MainScreen;
import GameScreen.PaintInfoGameScreen;
import InterfaceComponents.iCommand;
import Main.GameCanvas;
import Model.AvMain;
import Model.CRes;
import Model.FrameImage;
import Model.T;
import Model.mCamera;

public class WorldMapScreen
extends MainScreen {
    int x;
    int y;
    int dy;
    int timeSetPos = 0;
    public static byte idMyPos = (byte)5;
    public int xPoint;
    public int yPoint;
    public int idPoint;
    public int xplayer;
    public int yplayer;
    int[][] mPosPoint = new int[][]{{222, 263}, {266, 213}, {266, 213}, {238, 205}, {225, 197}, {201, 195}, {201, 195}, {329, 182}, {317, 161}, {305, 151}, {305, 151}, {362, 189}, {387, 195}, {415, 191}, {415, 191}, {372, 165}, {386, 148}, {390, 134}, {345, 161}};
    public static String[] namePos = null;
    public static FrameImage fraMyPos;
    mCamera cam = new mCamera();
    int beginx;
    int beginy;
    int xafter;
    int yafter;
    boolean ismove;

    public void Show(MainScreen mainScreen) {
        GameCanvas.start_Ok_Dialog(T.lockMap);
    }

    public void Show() {
        this.Show(GameCanvas.currentScreen);
    }

    public WorldMapScreen() {
        this.x = (GameCanvas.w - 480) / 2;
        this.y = (GameCanvas.h - 320) / 2;
        if (this.x < 0) {
            this.x = 0;
        }
        if (this.y < 0) {
            this.y = 0;
        }
        this.cam.setAll(480 - GameCanvas.w, 320 - GameCanvas.h, 0, 0);
        this.xPoint = this.mPosPoint[1][0];
        this.yPoint = this.mPosPoint[1][1];
        this.cam.xCam = this.xPoint - GameCanvas.hw;
        this.cam.yCam = this.yPoint - GameCanvas.hh;
        if (this.cam.xCam < 0) {
            this.cam.xCam = 0;
        }
        if (this.cam.xCam > this.cam.xLimit) {
            this.cam.xCam = this.cam.xLimit;
        }
        if (this.cam.yCam < 0) {
            this.cam.yCam = 0;
        }
        if (this.cam.yCam > this.cam.yLimit) {
            this.cam.yCam = this.cam.yLimit;
        }
        this.cam.xTo = this.cam.xCam;
        this.cam.yTo = this.cam.yCam;
        iCommand iCommand2 = new iCommand(T.close, -1);
        if (!GameCanvas.isTouch) {
            this.left = new iCommand(T.select, 0);
        } else {
            iCommand2.setPos(PaintInfoGameScreen.fraBack.frameWidth / 2, GameCanvas.h - PaintInfoGameScreen.fraBack.frameHeight / 2, PaintInfoGameScreen.fraBack, iCommand2.caption);
        }
        this.right = iCommand2;
    }

    public void setPosPlayer(int n) {
    }

    public void commandTab(int n, int n2) {
        switch (n) {
            case -1: {
                if (this.lastScreen == GameCanvas.AllInfo) {
                    this.lastScreen.Show(this.lastScreen.lastScreen);
                    break;
                }
                this.lastScreen.Show();
                break;
            }
        }
        super.commandTab(n, n2);
    }

    public void commandPointer(int n, int n2) {
        switch (n) {
            default: 
        }
        super.commandPointer(n, n2);
    }

    public void paint(mGraphics mGraphics2) {
        mGraphics2.setColor(-11181978);
        mGraphics2.fillRect(0, 0, GameCanvas.w, GameCanvas.h, false);
        mGraphics2.translate(-this.cam.xCam, -this.cam.yCam);
        mGraphics2.drawImage(AvMain.imgWorldMap, this.x, this.y, 0, false);
        if (this.timeSetPos == -1 && (!GameCanvas.isTouch || GameCanvas.isPointerDown || GameCanvas.isPointerMove)) {
            mFont.tahoma_7b_black.drawString(mGraphics2, namePos[this.idPoint], this.cam.xCam + GameCanvas.w - 5, this.cam.yCam + 4, 1, false);
            mFont.tahoma_7b_white.drawString(mGraphics2, namePos[this.idPoint], this.cam.xCam + GameCanvas.w - 4, this.cam.yCam + 5, 1, false);
        }
        fraMyPos.drawFrame(GameCanvas.gameTick / 2 % WorldMapScreen.fraMyPos.nFrame, this.x + this.xplayer - 1, this.y + this.yplayer - 1, 0, 3, mGraphics2);
        if (!GameCanvas.isTouch) {
            mGraphics2.drawImage(AvMain.imgSelect, this.x + this.xPoint, this.y + this.yPoint - this.dy, 33, false);
        }
        if (!GameCanvas.menu2.isShowMenu && GameCanvas.currentDialog == null) {
            if (GameCanvas.isTouch) {
                super.paint(mGraphics2);
            } else {
                this.paintCmd_OnlyText(mGraphics2);
            }
        }
    }

    public void update() {
        this.updateKey();
        if (this.timeSetPos > 0) {
            --this.timeSetPos;
            if (this.timeSetPos == 1) {
                for (int i = 0; i < this.mPosPoint.length; ++i) {
                    if (CRes.abs(this.mPosPoint[i][0] - this.xPoint) > 6 || CRes.abs(this.mPosPoint[i][1] - this.yPoint) > 6) continue;
                    this.xPoint = this.mPosPoint[i][0];
                    this.yPoint = this.mPosPoint[i][1];
                    this.idPoint = i;
                    this.timeSetPos = -1;
                    break;
                }
            }
        }
        this.dy = this.timeSetPos < 0 ? GameCanvas.gameTick % 4 : 0;
        this.cam.UpdateCamera();
    }

    public void updateKey() {
        if (GameCanvas.keyMyHold[4] || GameCanvas.keyMyHold[24]) {
            this.xPoint = this.xPoint > 10 ? (this.xPoint -= 5) : 5;
            this.timeSetPos = 3;
            if (this.cam.xLimit > 0) {
                this.cam.xTo -= 5;
            }
        } else if (GameCanvas.keyMyHold[6] || GameCanvas.keyMyHold[26]) {
            this.xPoint = this.xPoint < 470 ? (this.xPoint += 5) : 475;
            this.timeSetPos = 3;
            if (this.cam.xLimit > 0) {
                this.cam.xTo += 5;
            }
        } else if (GameCanvas.keyMyHold[2] || GameCanvas.keyMyHold[22]) {
            this.yPoint = this.yPoint > 10 ? (this.yPoint -= 5) : 5;
            this.timeSetPos = 3;
            if (this.cam.yLimit > 0) {
                this.cam.yTo -= 5;
            }
        } else if (GameCanvas.keyMyHold[8] || GameCanvas.keyMyHold[28]) {
            this.yPoint = this.yPoint < 315 ? (this.yPoint += 5) : 320;
            this.timeSetPos = 3;
            if (this.cam.yLimit > 0) {
                this.cam.yTo += 5;
            }
        }
        super.updatekey();
    }

    public void updatePointer() {
        super.updatePointer();
        if (GameCanvas.isPointerMove) {
            if (!this.ismove) {
                this.beginx = GameCanvas.px;
                this.beginy = GameCanvas.py;
                this.xafter = this.cam.xCam;
                this.yafter = this.cam.yCam;
                this.ismove = true;
            } else {
                this.cam.xCam = this.xafter - (GameCanvas.px - this.beginx);
                this.cam.yCam = this.yafter - (GameCanvas.py - this.beginy);
                if (this.cam.xCam < 0) {
                    this.cam.xCam = 0;
                }
                if (this.cam.xCam > this.cam.xLimit) {
                    this.cam.xCam = this.cam.xLimit;
                }
                if (this.cam.yCam < 0) {
                    this.cam.yCam = 0;
                }
                if (this.cam.yCam > this.cam.yLimit) {
                    this.cam.yCam = this.cam.yLimit;
                }
                this.cam.xTo = this.cam.xCam;
                this.cam.yTo = this.cam.yCam;
            }
        }
        if (GameCanvas.isPointerRelease || !GameCanvas.isPointerMove) {
            this.ismove = false;
            this.beginx = 0;
            this.beginy = 0;
        }
        if (GameCanvas.isPointerDown || GameCanvas.isPointerMove) {
            this.xPoint = GameCanvas.px + this.cam.xCam - this.x;
            this.yPoint = GameCanvas.py + this.cam.yCam - this.y;
        }
        for (int i = 0; i < this.mPosPoint.length; ++i) {
            if (CRes.abs(this.mPosPoint[i][0] - this.xPoint) > 8 || CRes.abs(this.mPosPoint[i][1] - this.yPoint) > 8) continue;
            this.idPoint = i;
            this.timeSetPos = -1;
            return;
        }
        this.timeSetPos = 3;
    }
}

