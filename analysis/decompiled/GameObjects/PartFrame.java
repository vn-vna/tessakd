/*
 * Decompiled with CFR 0.152.
 */
package GameObjects;

public class PartFrame {
    public short dx;
    public short dy;
    public byte flip;
    public byte onTop = 0;
    public byte idSmallImg;

    public PartFrame(short s, short s2, byte by) {
        this.idSmallImg = by;
        this.dx = s;
        this.dy = s2;
    }
}

