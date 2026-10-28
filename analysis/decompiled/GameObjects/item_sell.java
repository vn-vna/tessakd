/*
 * Decompiled with CFR 0.152.
 */
package GameObjects;

public class item_sell {
    public int price;
    public short id;
    public short soluuong;
    public byte cat;

    public item_sell(short s, int n) {
        this.id = s;
        this.price = n;
    }

    public item_sell() {
    }

    public item_sell(short s, int n, int n2) {
        this.id = s;
        this.price = n;
        this.soluuong = (short)n2;
    }

    public item_sell(short s, int n, int n2, int n3) {
        this.id = s;
        this.price = n;
        this.soluuong = (short)n2;
        this.cat = (byte)n3;
    }
}

