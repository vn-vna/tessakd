/*
 * Decompiled with CFR 0.152.
 */
package GameObjects;

import CLib.mGraphics;
import CLib.mImage;

public class SmallImage {
    public short id;
    public short x;
    public short y;
    public short w;
    public short h;

    public SmallImage(int n, int n2, int n3, int n4, int n5) {
        this.id = (short)n;
        this.x = (short)n2;
        this.y = (short)n3;
        this.w = (short)n4;
        this.h = (short)n5;
    }

    public void paint(mGraphics mGraphics2, mImage mImage2, int n, int n2) {
    }
}

