/*
 * Decompiled with CFR 0.152.
 */
package Model;

import CLib.mFont;
import CLib.mGraphics;
import CLib.mSystem;
import Model.AvMain;

public class CharCountDown {
    private long maxTime;
    private int w;
    public boolean WantDestroy;
    public String tile = "";
    long timesv;
    int timeper;
    int per;

    public CharCountDown(long l, String string) {
        this.tile = string;
        this.maxTime = l;
        this.w = 60;
        this.timeper = (int)(this.maxTime - mSystem.currentTimeMillis());
    }

    public void paint(mGraphics mGraphics2, int n, int n2) {
        int n3 = 0;
        long l = this.maxTime - mSystem.currentTimeMillis();
        n3 = (int)(60L - l * (long)this.w / (long)this.timeper);
        mGraphics2.drawRegion(AvMain.imgcolorhpmp_back, 0, 0, 62, 9, 0, n, n2, 0, false);
        mGraphics2.drawRegion(AvMain.imgcolorhpmp, 0, 7, n3, 7, 0, n + 1, n2 + 1, 0, false);
        mFont.tahoma_7_black.drawString(mGraphics2, this.tile, n + this.w / 2 + 1, n2 - 10 + 1 - 3, 2, false);
        mFont.tahoma_7_white.drawString(mGraphics2, this.tile, n + this.w / 2, n2 - 10 - 3, 2, false);
        mFont.tahoma_7b_white.drawString(mGraphics2, "" + this.per + "%", n + this.w / 2, n2 - 1, 2, false);
    }

    public void update() {
        long l = this.maxTime - mSystem.currentTimeMillis();
        this.per = (int)(100L - l * 100L / (long)this.timeper);
        if (this.maxTime - mSystem.currentTimeMillis() <= 0L) {
            this.WantDestroy = true;
        }
    }
}

