/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Canvas
 *  javax.microedition.lcdui.Graphics
 */
package com.silverknight;

import CLib.TemGraphics;
import CLib.mGraphics;
import CLib.mSystem;
import Main.GameCanvas;
import Thread_More.Load_Data_And_Img;
import java.util.Vector;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;

public class TemCanvas
extends Canvas
implements Runnable {
    public static TemCanvas instance;
    public static GameCanvas gamecanvas;
    public static TemGraphics tem;
    public static int wMain;
    public static int hMain;
    public static Vector listPoint;

    public TemCanvas() {
        instance = this;
        this.setFullScreenMode(true);
        hMain = this.getHeight();
        wMain = this.getWidth();
        this.checkZoomLevel(wMain, hMain);
        GameCanvas.isTouch = this.hasPointerEvents();
        gamecanvas = new GameCanvas();
        new Thread(Load_Data_And_Img.load).start();
    }

    private void checkZoomLevel(int n, int n2) {
        mGraphics.zoomLevel = 1;
        wMain = (wMain + mGraphics.zoomLevel - 1) / mGraphics.zoomLevel;
        hMain = (hMain + mGraphics.zoomLevel - 1) / mGraphics.zoomLevel;
    }

    public void start() {
        new Thread(this).start();
    }

    protected void paint(Graphics graphics) {
        TemCanvas.tem.g = graphics;
        gamecanvas.paint(tem);
    }

    protected void update() {
        gamecanvas.update();
    }

    public void run() {
        while (true) {
            long l = mSystem.currentTimeMillis();
            this.update();
            this.repaint();
            this.serviceRepaints();
            long l2 = mSystem.currentTimeMillis() - l;
            try {
                if (l2 < 40L) {
                    Thread.sleep(40L - l2);
                    continue;
                }
                mSystem.outloi(l2 + "  >>>>>>>>");
                Thread.sleep(1L);
                GameCanvas.timeNow = mSystem.currentTimeMillis();
            }
            catch (InterruptedException interruptedException) {
            }
        }
    }

    protected void keyPressed(int n) {
        gamecanvas.keyPressed(n);
    }

    protected void keyReleased(int n) {
        gamecanvas.keyReleased(n);
    }

    protected void pointerDragged(int n, int n2) {
        gamecanvas.pointerDragged(n, n2);
    }

    protected void pointerPressed(int n, int n2) {
        gamecanvas.pointerPressed(n, n2);
    }

    protected void pointerReleased(int n, int n2) {
        gamecanvas.pointerReleased(n, n2);
    }

    static {
        tem = new TemGraphics();
    }
}

