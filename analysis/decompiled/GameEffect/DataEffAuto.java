/*
 * Decompiled with CFR 0.152.
 */
package GameEffect;

public class DataEffAuto {
    public byte[] data;
    public short id;
    public long timeremove;

    public DataEffAuto(int n) {
        this.id = (short)n;
    }

    public void setdata(byte[] byArray) {
        this.data = byArray;
    }
}

