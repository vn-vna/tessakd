/*
 * Decompiled with CFR 0.152.
 */
package GameScreen;

import CLib.mFont;
import CLib.mGraphics;
import CLib.mImage;
import GameScreen.GameScreen;
import GameScreen.MainScreen;
import InterfaceComponents.MainTabNew;
import InterfaceComponents.iCommand;
import Main.GameCanvas;
import Model.AvMain;
import Model.CRes;
import Model.T;
import Thread_More.MiniMap;

public class MapScr
extends MainScreen {
    private static MapScr instance;
    boolean modeCurrentMap;
    public static mImage imgMap;
    public static int mapW;
    public static int mapH;
    public static int mfx;
    public static int mfy;
    public static int mpoint;
    public static int tick3;
    public static int mcmtoX;
    public static int mcmtoY;
    public static int mcmvx;
    public static int mcmvy;
    public static int mcmdx;
    public static int mcmdy;
    public static int mcmx;
    public static int mcmy;
    public static int mcmxLim;
    public static int mcmyLim;
    public static int taskmapId;
    private static int dx;
    private static int dy;
    public static int cmdH;
    public static int TOP_CENTER;
    public static int TOP_LEFT;
    public static int TOP_RIGHT;
    public static int BOTTOM_HCENTER;
    public static int BOTTOM_LEFT;
    public static int BOTTOM_RIGHT;
    public static int VCENTER_HCENTER;
    public static int VCENTER_LEFT;
    public static int[] x;
    public static int[] y;
    public static String[] mapNames;
    int maxPX;
    int maxPY;
    int xM;
    int yM;
    boolean trans = false;
    int lastX;
    int lastY;

    public MapScr() {
        this.center = new iCommand(T.close, 0, this);
    }

    protected void resetCMLim() {
        int n = GameCanvas.loadmap.idMap;
        if (n > x.length) {
            n = x.length - 1;
        }
        int n2 = mImage.getImageWidth(MiniMap.imgMiniMap.image);
        this.xM = (GameCanvas.w - n2) / 2;
        this.yM = (GameCanvas.h - 20 - mImage.getImageHeight(MiniMap.imgMiniMap.image)) / 2;
        if (this.xM < 0) {
            this.xM = 0;
        }
        if (this.yM < 0) {
            this.yM = 0;
        }
        if (this.modeCurrentMap) {
            mcmxLim = n2 + 20 - GameCanvas.w;
            mcmyLim = mImage.getImageHeight(MiniMap.imgMiniMap.image) + 40 - GameCanvas.h;
            this.maxPX = n2 + 20;
            this.maxPY = n2 + 40;
            if (this.maxPY < GameCanvas.h - 26) {
                this.maxPY = GameCanvas.h - 26;
            }
            if (this.maxPX < GameCanvas.w) {
                this.maxPX = GameCanvas.w;
            }
            mfx = this.xM + GameScreen.player.x / 12;
            mfy = this.yM + GameScreen.player.y / 12;
        } else {
            mcmxLim = 340 - GameCanvas.w;
            mcmyLim = 340 - GameCanvas.h;
            mfx = x[n] + dx;
            mfy = y[n] + dy;
            this.maxPX = 330 + dx;
            this.maxPY = 310 + dy;
        }
        this.maxPX -= 10;
        this.maxPY -= 10;
        if (mcmxLim < 0) {
            mcmxLim = 0;
        }
        if (mcmyLim < 0) {
            mcmyLim = 0;
        }
        mcmy = 0;
        mcmx = 0;
        mcmtoY = 0;
        mcmtoX = 0;
        mcmtoX = mfx - GameCanvas.hw;
        mcmtoY = mfy - GameCanvas.hh;
    }

    public static MapScr gI() {
        if (instance == null) {
            instance = new MapScr();
        }
        return instance;
    }

    public void Show() {
        super.Show();
        this.center = new iCommand(T.close, 0, this);
        if (imgMap == null) {
            imgMap = mImage.createImage("/wm.png");
            mapW = mImage.getImageWidth(MapScr.imgMap.image);
            mapH = mImage.getImageWidth(MapScr.imgMap.image);
        }
        if (GameCanvas.w > mapW) {
            dx = GameCanvas.w / 2 - mapW / 2;
        }
        if (GameCanvas.h > mapH) {
            dy = GameCanvas.h / 2 - mapH / 2;
        }
        this.resetCMLim();
        MapScr.findMapNearestPoint();
        System.gc();
        mapNames = T.mapName;
    }

    public void paint(mGraphics mGraphics2) {
        int n;
        int n2;
        mGraphics2.setColor(0);
        mGraphics2.fillRect(0, 0, GameCanvas.w, GameCanvas.h, false);
        mGraphics2.translate(10, 10);
        mGraphics2.translate(-mcmx, -mcmy);
        if (GameCanvas.w > mapW && GameCanvas.h > mapH) {
            mGraphics2.drawImage(imgMap, GameCanvas.hw, GameCanvas.hh, VCENTER_HCENTER, false);
        } else if (GameCanvas.w > mapW) {
            mGraphics2.drawImage(imgMap, GameCanvas.hw, 0, TOP_CENTER, false);
        } else if (GameCanvas.h > mapH) {
            mGraphics2.drawImage(imgMap, 0, GameCanvas.hh, VCENTER_LEFT, false);
        } else {
            mGraphics2.drawImage(imgMap, 0, 0, 0, false);
        }
        if (GameCanvas.loadmap.idMap < mapNames.length && GameCanvas.loadmap.idMap >= 0) {
            n2 = 0;
            if (x[GameCanvas.loadmap.idMap] != 1 || y[GameCanvas.loadmap.idMap] != 1) {
                n2 = x[GameCanvas.loadmap.idMap] < 100 ? 0 : (x[GameCanvas.loadmap.idMap] > 200 ? 1 : 2);
                mGraphics2.drawRegion(AvMain.imgFocusMap, 0, GameCanvas.gameTick % 3 * 10, 10, 10, 0, x[GameCanvas.loadmap.idMap] + dx, y[GameCanvas.loadmap.idMap] + dy, 3, false);
            }
            n = 0;
            if (x[GameCanvas.loadmap.idMap] != 1 || y[GameCanvas.loadmap.idMap] != 1) {
                n = y[GameCanvas.loadmap.idMap] - 20;
                mFont.tahoma_7b_black.drawString(mGraphics2, mapNames[GameCanvas.loadmap.idMap], x[GameCanvas.loadmap.idMap] + dx + 1, y[GameCanvas.loadmap.idMap] + dy - 20 + 1, n2, false);
                mFont.tahoma_7b_yellow.drawString(mGraphics2, mapNames[GameCanvas.loadmap.idMap], x[GameCanvas.loadmap.idMap] + dx, y[GameCanvas.loadmap.idMap] + dy - 20, n2, false);
            }
            if (mpoint >= 0 && (taskmapId < 0 && GameCanvas.loadmap.idMap != mpoint || taskmapId >= 0 && mpoint != taskmapId)) {
                n2 = x[mpoint] < 100 ? 0 : (x[mpoint] > 200 ? 1 : 2);
                int n3 = x[mpoint];
                int n4 = y[mpoint] - 20;
                if (n4 > n && n4 - n < 30) {
                    n4 += 40;
                }
                if (n4 < n && n - n4 < 20) {
                    n4 -= 5;
                }
                mFont.tahoma_7b_black.drawString(mGraphics2, mapNames[mpoint], n3 + dx + 1, n4 + dy + 1, n2, false);
                mFont.tahoma_7b_yellow.drawString(mGraphics2, mapNames[mpoint], n3 + dx, n4 + dy, n2, false);
            }
        }
        if (!GameCanvas.isTouch) {
            mGraphics2.drawRegion(MainTabNew.imgTab[5], 0, 0, 10, 10, 0, mfx - 2, mfy, 0, false);
        } else if (mpoint >= 0) {
            n2 = x[mpoint] - 9;
            n = y[mpoint];
            mGraphics2.drawRegion(MainTabNew.imgTab[5], 0, 0, 10, 10, 0, n2 + dx, n + dy, 0, false);
        }
        mGraphics2.translate(-mGraphics2.getTranslateX(), -mGraphics2.getTranslateY());
        super.paint(mGraphics2);
    }

    public void updatekey() {
        super.updatekey();
        if (++tick3 > 10000) {
            tick3 = 0;
        }
        if (mcmx != mcmtoX || mcmy != mcmtoY) {
            mcmvx = mcmtoX - mcmx << 1;
            mcmvy = mcmtoY - mcmy << 1;
            mcmdx &= 0xF;
            mcmy += (mcmdy += mcmvy) >> 4;
            mcmdy &= 0xF;
            if ((mcmx += (mcmdx += mcmvx) >> 4) < 0) {
                mcmx = 0;
            }
            if (mcmx > mcmxLim) {
                mcmx = mcmxLim;
            }
            if (mcmy < 0) {
                mcmy = 0;
            }
            if (mcmy > mcmyLim) {
                mcmy = mcmyLim;
            }
        }
        boolean bl = false;
        if (GameCanvas.keyMyHold[2]) {
            if ((mfy -= 4) < dy - 10) {
                mfy = dy - 10;
            }
            bl = true;
        }
        if (GameCanvas.keyMyHold[8]) {
            if ((mfy += 4) > this.maxPY) {
                mfy = this.maxPY;
            }
            bl = true;
        }
        if (GameCanvas.keyMyHold[4]) {
            if ((mfx -= 4) < dx - 10) {
                mfx = dx - 10;
            }
            bl = true;
        }
        if (GameCanvas.keyMyHold[6]) {
            if ((mfx += 4) > this.maxPX) {
                mfx = this.maxPX;
            }
            bl = true;
        }
        if (bl) {
            mcmtoX = mfx - GameCanvas.hw;
            mcmtoY = mfy - GameCanvas.hh;
            MapScr.findMapNearestPoint();
        }
        if (GameCanvas.isPointerClick && GameCanvas.py < GameCanvas.h - cmdH) {
            GameCanvas.isPointerClick = false;
            this.trans = true;
            this.lastX = GameCanvas.px;
            this.lastY = GameCanvas.py;
        } else if (GameCanvas.isPointerDown && this.trans) {
            mcmtoY -= GameCanvas.py - this.lastY;
            if ((mcmtoX -= GameCanvas.px - this.lastX) < 0) {
                mcmtoX = 0;
            }
            if (mcmtoY < 0) {
                mcmtoY = 0;
            }
            if (mcmtoX > mcmxLim) {
                mcmtoX = mcmxLim;
            }
            if (mcmtoY > mcmyLim) {
                mcmtoY = mcmyLim;
            }
            mcmx = mcmtoX;
            mcmy = mcmtoY;
            this.lastX = GameCanvas.px;
            this.lastY = GameCanvas.py;
        }
        if (GameCanvas.isPointerRelease) {
            int n = GameCanvas.pxLast - GameCanvas.px;
            int n2 = GameCanvas.pyLast - GameCanvas.py;
            if (n < 10 && n2 < 10) {
                mfx = mcmx + GameCanvas.pxLast - 8;
                mfy = mcmy + GameCanvas.pyLast - 8;
                MapScr.findMapNearestPoint();
            }
            this.trans = false;
            GameCanvas.isPointerRelease = false;
        }
        if (GameCanvas.isTouch && GameCanvas.w >= 320) {
            this.center.xCmd = GameCanvas.w / 2 - 35;
        }
    }

    private static void findMapNearestPoint() {
        mpoint = -1;
        int n = 10;
        if (!GameCanvas.isTouch) {
            n = 13;
        }
        for (int i = 0; i < x.length; ++i) {
            if (CRes.abs(mfx - (x[i] + dx)) >= n || CRes.abs(mfy - (y[i] + dy)) >= n) continue;
            mpoint = i;
            break;
        }
    }

    public void commandPointer(int n, int n2) {
        switch (n) {
            case 0: {
                imgMap = null;
                GameScreen.gI().Show();
                break;
            }
            case 1: {
                this.modeCurrentMap = !this.modeCurrentMap;
                this.resetCMLim();
                break;
            }
        }
    }

    static {
        imgMap = null;
        dx = 0;
        dy = 0;
        cmdH = 22;
        TOP_CENTER = 17;
        TOP_LEFT = 20;
        TOP_RIGHT = 24;
        BOTTOM_HCENTER = 33;
        BOTTOM_LEFT = 36;
        BOTTOM_RIGHT = 40;
        VCENTER_HCENTER = 3;
        VCENTER_LEFT = 6;
        x = new int[]{108, 111, 131, 93, 76, 58, 39, 102, 83, 66, 49, 121, 135, 154, 160, 100, 94, 80, 128, 73, 100, 88, 53, 83, 73, 127, 109, 89, 72, 171, 172, 185, 202, 134, 117, 157, 135, 99, 102, 122, 139, 156, 47, 44, 28, 23, 0, 47, 87, 0, 133, 159, 23, 207, 207, 239, 226, 207, 207, 168, 182, 217, 23, 71, 24, 88, 25, 66, 99, 40, 47, 28, 64, 47, 47, 67, 83, 98, 111, 128, 0, 0, 117, 219, 191, 219, 241, 218, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
        y = new int[]{247, 233, 220, 235, 236, 238, 237, 216, 212, 216, 205, 210, 204, 203, 182, 196, 180, 172, 183, 117, 144, 130, 134, 146, 134, 162, 160, 160, 160, 141, 125, 114, 116, 145, 144, 147, 128, 118, 98, 96, 88, 75, 149, 165, 165, 181, 0, 118, 224, 0, 235, 59, 200, 201, 187, 162, 167, 129, 143, 171, 171, 166, 217, 57, 62, 60, 83, 92, 72, 92, 69, 43, 42, 35, 18, 14, 8, 14, 28, 27, 0, 0, 132, 116, 96, 71, 94, 94, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
    }
}

