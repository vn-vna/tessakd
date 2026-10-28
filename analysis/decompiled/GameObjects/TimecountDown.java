/*
 * Decompiled with CFR 0.152.
 */
package GameObjects;

import CLib.mFont;
import CLib.mGraphics;
import CLib.mSystem;
import Main.GameCanvas;
import Model.AvMain;
import Thread_More.LoadMap;

public class TimecountDown {
    short mysecond;
    String tile;
    private long currT;
    private long lastT;
    public boolean wantdestroy;
    int x;
    int y;

    public TimecountDown(int n, String string, int n2, int n3) {
        this.mysecond = (short)n;
        this.tile = string;
        this.x = n2;
        this.y = n3;
        this.currT = mSystem.currentTimeMillis();
        this.lastT = mSystem.currentTimeMillis();
    }

    public void paint(mGraphics mGraphics2) {
        mGraphics2.drawImage(AvMain.imgBackInfo, this.x, this.y + GameCanvas.hText / 2, 3, false);
        mFont.tahoma_7b_white.drawString(mGraphics2, this.tile + " : " + LoadMap.converSecon2hours(this.mysecond), this.x, this.y, 2, false);
    }

    public void update() {
        this.currT = mSystem.currentTimeMillis();
        if (this.currT - this.lastT >= 1000L) {
            this.lastT = mSystem.currentTimeMillis();
            this.mysecond = (short)(this.mysecond - 1);
        }
        if (this.mysecond <= 0) {
            this.wantdestroy = true;
        }
    }
}

