/*
 * Decompiled with CFR 0.152.
 */
package InterfaceComponents;

public class DataRebuildItem {
    public short id;
    public short valueWing;
    public byte lv;
    public byte cat;
    public int priceCoin;
    public int priceGold;
    public byte[] mValue;

    public DataRebuildItem() {
    }

    public DataRebuildItem(byte by, int n, int n2, byte[] byArray) {
        this.lv = by;
        this.priceCoin = n;
        this.priceGold = n2;
        this.mValue = byArray;
    }

    public DataRebuildItem(short s, short s2) {
        this.id = s;
        this.valueWing = s2;
    }

    public DataRebuildItem(short s, byte by) {
        this.id = s;
        this.cat = by;
    }
}

