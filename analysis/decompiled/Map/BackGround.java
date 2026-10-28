/*
 * Decompiled with CFR 0.152.
 */
package Map;

import CLib.mGraphics;
import CLib.mImage;
import Main.GameCanvas;
import Model.CRes;
import Model.FrameImage;
import Model.Vector2;
import Thread_More.LoadMap;

public class BackGround {
    public static final int CLOUD_POS_OFFSET_Y = 10;
    public static mImage mImgSky = new mImage();
    public static mImage mImgSea = new mImage();
    public static mImage[] mImgCloud = new mImage[3];
    public static mImage mImgBoat = new mImage();
    public static mImage mImgFloating = new mImage();
    public static FrameImage mImgSeaAuto;
    public static Vector2[] cloudPos;
    public static Vector2[] floatingPos;
    public static int[] cloudType;
    public static int[] cloudVelo;
    public static int fillSkyH;
    private static int numSkyDraw;
    private static int skyWidth;
    private static int skyHeight;
    private static int numSeaDrawW;
    private static int numSeaDrawH;
    private static int seaWidth;
    private static int seaHeight;
    private static int numCloudDraw;
    private static int cloudWidth;
    private static int cloudHeight;
    public static int[] staticCloudPos;

    public static void init() {
        int n;
        BackGround.LoadBackGround();
        skyWidth = mImage.getImageWidth(BackGround.mImgSky.image);
        skyHeight = mImage.getImageHeight(BackGround.mImgSky.image);
        numSkyDraw = GameCanvas.w / skyWidth;
        seaWidth = mImage.getImageWidth(BackGround.mImgSea.image);
        seaHeight = mImage.getImageHeight(BackGround.mImgSea.image);
        numSeaDrawW = GameCanvas.w / seaWidth;
        numSeaDrawH = (GameCanvas.h - (fillSkyH + skyHeight)) / seaHeight;
        cloudWidth = mImage.getImageWidth(BackGround.mImgCloud[0].image);
        cloudHeight = mImage.getImageHeight(BackGround.mImgCloud[0].image);
        numCloudDraw = GameCanvas.w / cloudWidth;
        for (n = 0; n < cloudPos.length; ++n) {
            BackGround.cloudPos[n] = new Vector2();
            BackGround.cloudPos[n].x = CRes.random(0, GameCanvas.w);
            BackGround.cloudPos[n].y = CRes.random(6) * 10;
            BackGround.cloudType[n] = CRes.random(1, 3);
            BackGround.cloudVelo[n] = CRes.random(0, 2);
        }
        for (n = 0; n < floatingPos.length; ++n) {
            BackGround.floatingPos[n] = new Vector2();
            BackGround.floatingPos[n].x = GameCanvas.hw - 80 + n * 80;
            BackGround.floatingPos[n].y = GameCanvas.h - 50 - n % 2 * 25;
        }
        staticCloudPos = new int[numCloudDraw + 2];
        for (n = 0; n < staticCloudPos.length; ++n) {
            BackGround.staticCloudPos[n] = n * cloudWidth - mImage.getImageWidth(BackGround.mImgCloud[0].image);
        }
    }

    public static void paint(mGraphics mGraphics2) {
        if (mImgSky == null || mImgSea == null || mImgFloating == null) {
            BackGround.LoadBackGround();
        } else {
            BackGround.paint_Sky(mGraphics2);
            BackGround.paint_Boat(mGraphics2);
            BackGround.paint_Sea(mGraphics2);
            BackGround.paint_Cloud(mGraphics2);
        }
        mGraphics2.translate(GameCanvas.hw - GameCanvas.loadmap.mapWLogin * LoadMap.wTile / 2, GameCanvas.h - GameCanvas.loadmap.mapHLogin * LoadMap.wTile);
        GameCanvas.resetTrans(mGraphics2);
    }

    public static void paintLight(mGraphics mGraphics2) {
    }

    public static void paint_Boat(mGraphics mGraphics2) {
        mGraphics2.drawImage(mImgBoat, 10, fillSkyH + skyHeight / 2, 0, false);
    }

    public static void paint_Sky(mGraphics mGraphics2) {
        mGraphics2.setColor(-11835756);
        mGraphics2.fillRect(0, 0, GameCanvas.w, fillSkyH, false);
        for (int i = 0; i < numSkyDraw + 1; ++i) {
            mGraphics2.drawImage(mImgSky, i * skyWidth, fillSkyH, 0, false);
        }
    }

    public static void paint_Sea(mGraphics mGraphics2) {
        int n = fillSkyH + skyHeight;
        for (int i = 0; i < numSeaDrawW + 1; ++i) {
            for (int j = 0; j < numSeaDrawH + 1; ++j) {
                mGraphics2.drawImage(mImgSea, i * seaWidth, j * seaHeight + n, 0, false);
            }
        }
    }

    public static void paint_Cloud(mGraphics mGraphics2) {
        for (int i = 0; i < 4; ++i) {
            mGraphics2.drawImage(mImgCloud[cloudType[i]], (int)BackGround.cloudPos[i].x, (int)BackGround.cloudPos[i].y, 0, false);
        }
    }

    public static void paint_StaticCloud(mGraphics mGraphics2) {
        int n = GameCanvas.h - cloudHeight;
        for (int i = 0; i < staticCloudPos.length; ++i) {
            mGraphics2.drawImage(mImgCloud[0], staticCloudPos[i], n, 0, false);
        }
    }

    public static void paint_CloudOnLogo(mGraphics mGraphics2) {
        for (int i = 4; i < cloudPos.length; ++i) {
            mGraphics2.drawImage(mImgCloud[cloudType[i]], (int)BackGround.cloudPos[i].x, (int)BackGround.cloudPos[i].y, 0, false);
        }
    }

    public static void paint_FloatingPlatform(mGraphics mGraphics2) {
        for (int i = 0; i < floatingPos.length; ++i) {
            mGraphics2.drawImage(mImgFloating, (int)BackGround.floatingPos[i].x, (int)BackGround.floatingPos[i].y, 3, false);
        }
    }

    public static void updateSky() {
        int n;
        for (n = 0; n < cloudPos.length; ++n) {
            BackGround.cloudVelo[n] = 1;
            BackGround.cloudPos[n].x += (float)cloudVelo[n];
            if (!(BackGround.cloudPos[n].x > (float)GameCanvas.w)) continue;
            BackGround.cloudPos[n].x = -mImage.getImageWidth(BackGround.mImgCloud[1].image);
            BackGround.cloudPos[n].y = CRes.random(6) * 10;
            BackGround.cloudType[n] = CRes.random(1, 3);
            BackGround.cloudVelo[n] = CRes.random(1, 2);
        }
        n = -1;
        int n2 = -1;
        for (int i = 0; i < staticCloudPos.length; ++i) {
            int n3 = i;
            staticCloudPos[n3] = staticCloudPos[n3] + 2;
            if (staticCloudPos[i] <= GameCanvas.w) continue;
            int n4 = i + 1;
            if (n4 > staticCloudPos.length - 1) {
                n4 = 0;
            }
            if (n != -1) continue;
            n = i;
            n2 = n4;
        }
        if (n != -1 && n2 != -1) {
            BackGround.staticCloudPos[n] = staticCloudPos[n2] - cloudWidth;
            n = -1;
            n2 = -1;
        }
    }

    public static void LoadBackGround() {
        mImgSky = mImage.createImage("/bg/sky.img");
        mImgSea = mImage.createImage("/bg/sea.img");
        for (int i = 0; i < mImgCloud.length; ++i) {
            BackGround.mImgCloud[i] = mImage.createImage("/bg/cloud" + i + ".img");
        }
        mImgBoat = mImage.createImage("/bg/boat.img");
        mImgFloating = mImage.createImage("/bg/floating.img");
        if (mImgSeaAuto == null) {
            mImgSeaAuto = new FrameImage(mImage.createImage("/bg/seabg.png"), 24, 24);
        }
    }

    public static void LoadImgCloud() {
        for (int i = 0; i < mImgCloud.length; ++i) {
            if (mImgCloud[i] != null) continue;
            BackGround.mImgCloud[i] = mImage.createImage("/bg/cloud" + i + ".img");
        }
    }

    public static void paint_SeaCloud(mGraphics mGraphics2) {
        for (int i = 0; i < 4; ++i) {
            mGraphics2.drawImage(mImgCloud[cloudType[i]], (int)BackGround.cloudPos[i].x, (int)BackGround.cloudPos[i].y + GameCanvas.h / 2, 0, false);
        }
    }

    public static void paint_SeaAuto(mGraphics mGraphics2) {
        for (int i = 0; i < GameCanvas.w / 24 + 1; ++i) {
            for (int j = 0; j < GameCanvas.h / 24 + 1; ++j) {
                mImgSeaAuto.drawFrame(GameCanvas.gameTick % 14 < 7 ? 1 : 0, i * 24, j * 24, 0, mGraphics2);
            }
        }
    }

    static {
        cloudPos = new Vector2[7];
        floatingPos = new Vector2[3];
        cloudType = new int[7];
        cloudVelo = new int[7];
        fillSkyH = 70;
        staticCloudPos = new int[3];
    }
}

