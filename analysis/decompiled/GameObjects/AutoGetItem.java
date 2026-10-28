/*
 * Decompiled with CFR 0.152.
 */
package GameObjects;

public class AutoGetItem {
    byte valueColorItem;
    byte isGetMoney;
    byte isGetPotion;
    public static byte POI_NHAT_HET = 0;
    public static byte POI_NHAT_MAU = 1;
    public static byte POI_NHAT_NANG_LUONG = (byte)2;
    public static byte POI_KHONG_NHAT = (byte)3;
    public boolean isremove = false;

    public AutoGetItem(byte by, byte by2, byte by3) {
        this.valueColorItem = by;
        this.isGetMoney = by3;
        this.isGetPotion = by2;
    }
}

