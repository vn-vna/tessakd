/*
 * Decompiled with CFR 0.152.
 */
package Skill;

public class HotKey {
    public short id;
    public short idIconPotion;
    public byte type;
    public byte typePotion;
    public static byte NULL = (byte)-1;
    public static byte SKILL = 0;
    public static byte POTION = 1;

    public void setHotKey(int n, int n2, byte by) {
        this.id = (short)n;
        this.type = (byte)n2;
        this.typePotion = by;
    }

    public void setIdIcon(short s) {
        this.idIconPotion = s;
    }
}

