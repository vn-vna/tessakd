/*
 * Decompiled with CFR 0.152.
 */
package Model;

import GameScreen.GameScreen;
import Main.GameCanvas;

public class mCamera {
    public static mCamera instance;
    public int xCam;
    public int yCam;
    public int xTo;
    public int yTo;
    public int xLimit;
    public int yLimit;
    public long timeDelay;
    int cmvx;
    int cmdx;
    int cmvy;
    int cmdy;

    public void setAll(int n, int n2, int n3, int n4) {
        this.xLimit = n;
        this.yLimit = n2;
        if (this.yLimit < 0) {
            this.yLimit = 0;
        }
        if (this.xLimit < 0) {
            this.xLimit = 0;
        }
        if (n3 > n) {
            n3 = n;
        }
        if (n3 < 0) {
            n3 = 0;
        }
        if (n4 > n2) {
            n4 = n2;
        }
        if (n4 < 0) {
            n4 = 0;
        }
        this.xCam = n3;
        this.yCam = n4;
        this.xTo = n3;
        this.yTo = n4;
    }

    public void setAllTo(int n, int n2, int n3, int n4) {
        this.xLimit = n;
        this.yLimit = n2;
        if (this.yLimit < 0) {
            this.yLimit = 0;
        }
        if (this.xLimit < 0) {
            this.xLimit = 0;
        }
        this.xTo = n3;
        this.yTo = n4;
    }

    public void setCamera(int n, int n2) {
        this.xCam = n;
        this.yCam = n2;
        this.xTo = n;
        this.yTo = n2;
    }

    public void setCameraWithLim(int n, int n2) {
        if (n < 0) {
            n = 0;
        }
        if (n > this.xLimit) {
            n = this.xLimit;
        }
        if (n2 < 0) {
            n2 = 0;
        }
        if (n2 > this.yLimit) {
            n2 = this.yLimit;
        }
        this.xCam = n;
        this.yCam = n2;
        this.xTo = n;
        this.yTo = n2;
    }

    public void moveCamera(int n, int n2) {
        this.xTo = n;
        this.yTo = n2;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public void UpdateCamera() {
        if (GameScreen.player.useShip) {
            if (GameCanvas.loadmap.idMap != 19) return;
            if (this.yCam == 0) return;
            this.yTo = 0;
        }
        if (this.xCam != this.xTo) {
            this.cmvx = this.xTo - this.xCam << 1;
            this.cmdx += this.cmvx;
            this.xCam += this.cmdx >> 4;
            this.cmdx &= 0xF;
            if (this.xCam < 0) {
                this.xCam = 0;
            }
            if (this.xCam > this.xLimit) {
                this.xCam = this.xLimit;
            }
        }
        if (this.yCam == this.yTo) return;
        this.cmvy = this.yTo - this.yCam << 1;
        this.cmdy += this.cmvy;
        this.yCam += this.cmdy >> 4;
        this.cmdy &= 0xF;
        if (this.yCam < 0) {
            this.yCam = 0;
        }
        if (this.yCam <= this.yLimit) return;
        this.yCam = this.yLimit;
    }

    public void UpdateCameraCreate() {
        if (this.xCam != this.xTo) {
            this.cmvx = this.xTo - this.xCam << 1;
            this.cmdx += this.cmvx;
            this.xCam += this.cmdx >> 4;
            this.cmdx &= 0xF;
        }
        if (this.yCam != this.yTo) {
            this.cmvy = this.yTo - this.yCam << 1;
            this.cmdy += this.cmvy;
            this.yCam += this.cmdy >> 4;
            this.cmdy &= 0xF;
            if (this.yCam < 0) {
                this.yCam = 0;
            }
            if (this.yCam > this.yLimit) {
                this.yCam = this.yLimit;
            }
        }
    }
}

