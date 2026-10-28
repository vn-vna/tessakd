/*
 * Decompiled with CFR 0.152.
 */
package GameScreen;

import CLib.mGraphics;
import Main.GameCanvas;
import Model.AvMain;
import Model.mCamera;

public class MainScreen
extends AvMain {
    MainScreen mainScreen;
    public MainScreen lastScreen;
    public static mCamera cameraMain;
    public static mCamera cameraSub;
    public String name = "";

    public void Show() {
        GameCanvas.clearKeyPressed();
        GameCanvas.currentScreen = this;
        GameCanvas.end_Dialog();
        GameCanvas.menu2.isShowMenu = false;
    }

    public void Show(MainScreen mainScreen) {
        if (mainScreen != null) {
            this.lastScreen = mainScreen;
        }
        GameCanvas.clearKeyPressed();
        GameCanvas.currentScreen = this;
        GameCanvas.end_Dialog();
        GameCanvas.menu2.isShowMenu = false;
    }

    public void paint(mGraphics mGraphics2) {
        super.paint(mGraphics2);
    }

    public void update() {
    }

    public void keyPress(int n) {
    }

    public void keyBack() {
    }

    public boolean isGameScr() {
        return false;
    }
}

