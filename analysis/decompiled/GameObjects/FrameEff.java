/*
 * Decompiled with CFR 0.152.
 */
package GameObjects;

import CLib.mVector;

public class FrameEff {
    public mVector listPart = new mVector();
    public mVector listPartTop = new mVector();
    public mVector listPartBottom = new mVector();
    public byte xShadow;
    public int yShadow;

    public FrameEff(mVector mVector2) {
        this.listPart = mVector2;
    }

    public FrameEff(mVector mVector2, mVector mVector3) {
        this.listPartTop = mVector2;
        this.listPartBottom = mVector3;
    }
}

