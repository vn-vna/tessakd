/*
 * Decompiled with CFR 0.152.
 */
package InterfaceComponents;

import CLib.mFont;
import CLib.mGraphics;
import CLib.mSystem;
import GameScreen.GameScreen;
import GameScreen.PaintInfoGameScreen;
import InterfaceComponents.MainDialog;
import InterfaceComponents.iCommand;
import Main.GameCanvas;
import Model.AvMain;
import Model.T;

public class Info_Quayso
extends MainDialog {
    private long currTime;
    private long maxTime;
    private int percenTime = 0;
    private int Height = 22;
    private int h = 15;
    private int timesv = 0;
    private byte indexcolor;
    private byte indexcolorpercen;
    private int percenWin = 0;
    private String[] infopaint;
    private String texpercen = "";
    iCommand cmdClose = new iCommand(T.close, -1, this);
    private int[] color = new int[]{-15665338, -329963, -357611, -261612, -211511};

    public void setInfo(String[] stringArray, int n, int n2, String string) {
        this.timesv = n;
        this.percenWin = n2;
        this.texpercen = string;
        this.infopaint = stringArray;
        this.currTime = this.maxTime = mSystem.currentTimeMillis() / 1000L + (long)this.timesv;
        this.hDia = this.infopaint.length * this.Height;
        this.wDia = GameCanvas.w - 20;
        if (this.wDia > 200) {
            this.wDia = 200;
        }
        this.xDia = GameCanvas.hw - this.wDia / 2;
        this.yDia = GameCanvas.h - GameCanvas.hCommand * 2 - this.hDia - 5;
        this.cmdClose.setPos(GameCanvas.hw, this.yDia + this.hDia - iCommand.hButtonCmd / 2 - 2, PaintInfoGameScreen.fraButton, this.cmdClose.caption);
        this.indexcolorpercen = n2 >= 75 ? (byte)3 : (n2 >= 50 ? (byte)2 : (n2 >= 25 ? (byte)1 : 0));
    }

    public void commandPointer(int n, int n2) {
        switch (n) {
            case -1: {
                GameCanvas.end_Dialog();
            }
        }
        super.commandPointer(n, n2);
    }

    public void paint(mGraphics mGraphics2) {
        AvMain.paintDialog(mGraphics2, this.xDia, this.yDia, this.wDia, this.hDia, 2);
        for (int i = 0; i < this.infopaint.length; ++i) {
            int n;
            int n2 = mFont.tahoma_7b_white.getWidth(this.infopaint[0]);
            mFont.tahoma_7b_white.drawString(mGraphics2, this.infopaint[i], this.xDia + 10, this.yDia + 10 + i * this.h, 0, false);
            if (i == 0) {
                n = 0;
                n = (int)(this.currTime * (long)(this.wDia - n2) / (long)this.timesv);
                if (n <= 7) {
                    n = 7;
                }
                if (this.currTime <= 0L) {
                    n = 0;
                }
                mGraphics2.setColor(-8967919);
                mGraphics2.fillRect(this.xDia + n2 - n2 / 4 + 5, this.yDia + 7 + i * this.h, this.wDia - n2, 14, false);
                mGraphics2.setColor(-526425);
                mGraphics2.fillRect(this.xDia + n2 - n2 / 4 + 5 + 1, this.yDia + 7 + i * this.h + 1, this.wDia - n2 - 2, 12, false);
                mGraphics2.setColor(-8952235);
                mGraphics2.fillRect(this.xDia + n2 - n2 / 4 + 5 + 2, this.yDia + 7 + i * this.h + 2, this.wDia - n2 - 4, 10, false);
                if (this.percenTime <= 15) {
                    this.indexcolor = GameCanvas.gameTick % 2 == 0 ? (byte)3 : (byte)4;
                }
                mGraphics2.setColor(this.color[this.indexcolor]);
                if (this.percenTime > 0) {
                    mGraphics2.fillRect(this.xDia + n2 - n2 / 4 + 5 + 3, this.yDia + 7 + i * this.h + 3, n - 6, 8, false);
                }
                mFont.tahoma_7b_black.drawString(mGraphics2, GameScreen.converSecon2minutes((int)this.currTime), this.xDia + n2 - n2 / 4 + 5 + 2 + (this.wDia - n2 - 4) / 2, this.yDia + 7 + i * this.h + 1, 2, false);
            }
            if (i != 2) continue;
            n = 0;
            n = this.percenWin * (this.wDia - n2) / 100;
            if (n <= 0) {
                n = 7;
            }
            if (this.percenWin <= 0) {
                n = 0;
            }
            mGraphics2.setColor(-8967919);
            mGraphics2.fillRect(this.xDia + n2 - n2 / 4 + 5, this.yDia + 7 + i * this.h, this.wDia - n2, 14, false);
            mGraphics2.setColor(-526425);
            mGraphics2.fillRect(this.xDia + n2 - n2 / 4 + 5 + 1, this.yDia + 7 + i * this.h + 1, this.wDia - n2 - 2, 12, false);
            mGraphics2.setColor(-8952235);
            mGraphics2.fillRect(this.xDia + n2 - n2 / 4 + 5 + 2, this.yDia + 7 + i * this.h + 2, this.wDia - n2 - 4, 10, false);
            mGraphics2.setColor(this.color[this.indexcolorpercen]);
            mGraphics2.fillRect(this.xDia + n2 - n2 / 4 + 5 + 3, this.yDia + 7 + i * this.h + 3, n - 6, 8, false);
            mFont.tahoma_7b_black.drawString(mGraphics2, this.texpercen, this.xDia + n2 - n2 / 4 + 5 + 2 + (this.wDia - n2 - 4) / 2, this.yDia + 7 + i * this.h + 1, 2, false);
        }
        this.cmdClose.paint(mGraphics2, GameCanvas.hw, this.yDia + this.hDia - iCommand.hButtonCmd);
    }

    public void update() {
        if (this.currTime > 0L) {
            this.currTime = this.maxTime - mSystem.currentTimeMillis() / 1000L;
        }
        this.percenTime = (int)(this.currTime * 100L / (long)this.timesv);
        this.indexcolor = this.percenTime >= 75 ? (byte)0 : (this.percenTime >= 50 ? (byte)1 : (this.percenTime >= 25 ? (byte)2 : (byte)3));
        if (GameCanvas.isTouch) {
            this.cmdClose.updatePointer();
        } else if (GameCanvas.keyMyPressed[5]) {
            this.cmdClose.perform();
            GameCanvas.keyMyPressed[5] = false;
        }
        super.update();
    }
}

