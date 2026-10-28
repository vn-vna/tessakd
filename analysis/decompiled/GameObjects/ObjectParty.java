/*
 * Decompiled with CFR 0.152.
 */
package GameObjects;

public class ObjectParty {
    public short Lv;
    public String name;
    public byte idArea;
    public int idMap;
    public int x;
    public int y;
    public int hp;
    public int maxhp;
    public boolean isRemove = false;
    public boolean ischeck = true;

    public ObjectParty(String string, short s, int n, byte by) {
        this.idArea = by;
        this.idMap = n;
        this.name = string;
        this.Lv = s;
    }
}

