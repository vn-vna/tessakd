/*
 * Decompiled with CFR 0.152.
 */
package Map;

import CLib.mGraphics;
import CLib.mImage;
import GameObjects.ObjectData;
import GameScreen.MainScreen;
import Main.GameCanvas;
import Model.MainImage;

public class MainItemMap {
    public byte TypeItem;
    public static final byte ITEM_MAP = 0;
    public static final byte EFF_MAP = 1;
    public static final byte EFF_FROM_SV = 2;
    public static final byte EFF_SKILL_FROM_SV = 3;
    public static final byte EFF_MAT_NA = 4;
    public short IDItem;
    public short IDImage;
    public int dx;
    public int dy;
    public int x;
    public int y;
    public int wOne;
    public int hOne;
    public short idActor;
    public int[][] Block;
    mImage img;

    public MainItemMap() {
    }

    public void setDataEff(byte[] byArray) {
    }

    public MainItemMap(short s, short s2, int n, int n2, int[][] nArray) {
        this.IDItem = s;
        this.IDImage = s2;
        this.dx = n;
        this.dy = n2;
        this.Block = nArray;
    }

    public void paint(mGraphics mGraphics2) {
    }

    public void update() {
    }

    public boolean isInScreen() {
        if (this.hOne == 0 || this.wOne == 0) {
            MainImage mainImage = ObjectData.getImagePartItemMap(this.IDItem);
            if (mainImage.img != null) {
                this.wOne = mImage.getImageWidth(mainImage.img.image);
                this.hOne = mImage.getImageHeight(mainImage.img.image);
            }
        }
        return this.x + this.dx + this.wOne >= MainScreen.cameraMain.xCam && this.x + this.dx - this.wOne <= MainScreen.cameraMain.xCam + GameCanvas.w && this.y + this.dy + this.hOne >= MainScreen.cameraMain.yCam && this.y + this.dy - this.hOne <= MainScreen.cameraMain.yCam + GameCanvas.h;
    }
}

