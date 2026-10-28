/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Graphics
 */
package CLib;

import CLib.mImage;
import Main.GameCanvas;
import Model.AvMain;
import javax.microedition.lcdui.Graphics;

public class mGraphics {
    public Graphics g;
    public static int zoomLevel = 1;
    public static final int HCENTER = 1;
    public static final int VCENTER = 2;
    public static final int LEFT = 4;
    public static final int RIGHT = 8;
    public static final int TOP = 16;
    public static final int BOTTOM = 32;
    public static final int BASELINE = 64;
    public static final int SOLID = 0;
    public static final int DOTTED = 1;
    public static final boolean isTrue = true;
    public static final boolean isFalse = false;
    int wImg;
    int hImg;

    public mGraphics(Graphics graphics) {
        this.g = graphics;
    }

    public mGraphics() {
    }

    public void drawImage(mImage mImage2, int n, int n2, int n3, boolean bl) {
        this.g.drawImage(mImage2.image, n *= zoomLevel, n2 *= zoomLevel, n3);
    }

    public void drawLine(int n, int n2, int n3, int n4, boolean bl) {
        n *= zoomLevel;
        n2 *= zoomLevel;
        n3 *= zoomLevel;
        n4 *= zoomLevel;
        for (int i = 0; i < zoomLevel; ++i) {
            this.g.drawLine(n + i, n2 + i, n3 + i, n4 + i);
            if (i <= 0) continue;
            this.g.drawLine(n + i, n2, n3 + i, n4);
            this.g.drawLine(n, n2 + i, n3, n4 + i);
        }
    }

    public void drawRect(int n, int n2, int n3, int n4, boolean bl) {
        n *= zoomLevel;
        n2 *= zoomLevel;
        n3 *= zoomLevel;
        n4 *= zoomLevel;
        for (int i = 0; i < zoomLevel; ++i) {
            this.g.drawRect(n + i, n2 + i, n3 - i * 2, n4 - i * 2);
        }
    }

    public void drawRegion(mImage mImage2, int n, int n2, int n3, int n4, int n5, int n6, int n7, int n8, boolean bl) {
        this.g.drawRegion(mImage2.image, n *= zoomLevel, n2 *= zoomLevel, n3 *= zoomLevel, n4 *= zoomLevel, n5, n6 *= zoomLevel, n7 *= zoomLevel, n8);
    }

    public void drawRoundRect(int n, int n2, int n3, int n4, int n5, int n6, boolean bl) {
        this.g.drawRoundRect(n *= zoomLevel, n2 *= zoomLevel, n3 *= zoomLevel, n4 *= zoomLevel, n5 *= zoomLevel, n6 *= zoomLevel);
    }

    public void drawString(String string, int n, int n2, int n3, boolean bl) {
        this.g.drawString(string, n *= zoomLevel, n2 *= zoomLevel, n3);
    }

    public void fillRect(int n, int n2, int n3, int n4, boolean bl) {
        this.g.fillRect(n *= zoomLevel, n2 *= zoomLevel, n3 *= zoomLevel, n4 *= zoomLevel);
    }

    public void fillRoundRect(int n, int n2, int n3, int n4, int n5, int n6, boolean bl) {
        this.g.fillRoundRect(n *= zoomLevel, n2 *= zoomLevel, n3 *= zoomLevel, n4 *= zoomLevel, n5 *= zoomLevel, n6 *= zoomLevel);
    }

    public void drawArc(int n, int n2, int n3, int n4, int n5, int n6, boolean bl) {
        this.g.drawArc(n *= zoomLevel, n2 *= zoomLevel, n3 *= zoomLevel, n4 *= zoomLevel, n5, n6);
    }

    public void fillTriangle(int n, int n2, int n3, int n4, int n5, int n6, boolean bl) {
        this.g.fillTriangle(n *= zoomLevel, n2 *= zoomLevel, n3 *= zoomLevel, n4 *= zoomLevel, n5 *= zoomLevel, n6 *= zoomLevel);
    }

    public int getTranslateX() {
        return this.g.getTranslateX() / zoomLevel;
    }

    public int getTranslateY() {
        return this.g.getTranslateY() / zoomLevel;
    }

    public void setClip(int n, int n2, int n3, int n4) {
        this.g.setClip(n *= zoomLevel, n2 *= zoomLevel, n3 *= zoomLevel, n4 *= zoomLevel);
    }

    public void endClip() {
    }

    public void setColor(int n) {
        this.g.setColor(n);
    }

    public void setColor(int n, float f) {
        this.g.setColor(n);
    }

    public void translate(int n, int n2) {
        this.g.translate(n *= zoomLevel, n2 *= zoomLevel);
    }

    public void fillRecAlpla(int n, int n2, int n3, int n4, int n5) {
        this.drawRecAlpa(0, 0, GameCanvas.loadmap.mapW * 24, n2, n5);
        this.drawRecAlpa(0, n2, n, GameCanvas.loadmap.mapH * 24 - n2, n5);
        this.drawRecAlpa(n, n2 + n4, GameCanvas.loadmap.mapW * 24 - n, GameCanvas.loadmap.mapH * 24 - (n2 + n4), n5);
        this.drawRecAlpa(n + n3, n2, GameCanvas.loadmap.mapW * 24 - (n + n3), n4, n5);
    }

    public void drawRecAlpa(int n, int n2, int n3, int n4, int n5) {
        this.setColor(n5);
        this.fillRect(n, n2, n3, n4, false);
    }

    public void fillRect(int n, int n2, int n3, int n4, int n5, int n6, boolean bl) {
        n *= zoomLevel;
        n2 *= zoomLevel;
        n3 *= zoomLevel;
        n4 *= zoomLevel;
        if (this.wImg != mImage.getImageWidth(AvMain.imgRect.image)) {
            this.wImg = mImage.getImageWidth(AvMain.imgRect.image);
        }
        if (this.hImg != mImage.getImageHeight(AvMain.imgRect.image)) {
            this.hImg = mImage.getImageHeight(AvMain.imgRect.image);
        }
        this.setClip(n, n2, n3, n4);
        for (int i = n % this.wImg; i < n3 + this.wImg; i += this.wImg) {
            for (int j = n2 % this.hImg; j < n4 + this.hImg; j += this.hImg) {
                this.drawImage(AvMain.imgRect, n + n3 - i, n2 + n4 - j, 20, true);
            }
        }
        this.setClip(-this.g.getTranslateX(), -this.g.getTranslateY(), GameCanvas.w, GameCanvas.h);
    }

    public void restoreCanvas() {
    }

    public void saveCanvas() {
    }

    public void ClipRec(int n, int n2, int n3, int n4) {
    }

    public static void resetTransAndroid(mGraphics mGraphics2) {
    }

    public void translateAndroid(int n, int n2) {
    }
}

