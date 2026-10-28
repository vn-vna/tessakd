/*
 * Decompiled with CFR 0.152.
 */
package GameObjects;

import Skill.Monster_Skill;

public class Object_Effect_Skill {
    public short ID;
    public byte tem;
    public int hpShow;
    public int hpLast;
    public int hpPlus;
    public int[] mEffTypePlus = new int[0];
    public int[] mEff_HP_Plus = new int[0];
    public Monster_Skill skillMonster;

    public Object_Effect_Skill(short s, byte by) {
        this.ID = s;
        this.tem = by;
    }

    public Object_Effect_Skill(short s, byte by, Monster_Skill monster_Skill) {
        this.ID = s;
        this.tem = by;
        this.skillMonster = monster_Skill;
    }

    public void setHP(int n, int n2) {
        this.hpShow = n;
        this.hpLast = n2;
    }
}

