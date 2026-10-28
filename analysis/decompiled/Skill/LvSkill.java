/*
 * Decompiled with CFR 0.152.
 */
package Skill;

import GameObjects.MainInfoItem;
import GameScreen.GameScreen;
import Model.T;

public class LvSkill {
    public short mpLost;
    public short time_Sub_Eff;
    public short plus_Hp;
    public short plus_Mp;
    public short LvRe;
    public short range_lan;
    public int delay;
    public int timeBuff;
    public byte per_Sub_Eff;
    public byte nTarget;
    public MainInfoItem[] minfo;

    public int getdelay() {
        if (GameScreen.player.timeCombo > 0) {
            return GameScreen.player.timeCombo;
        }
        return this.delay;
    }

    public String[] getinfo() {
        String[] stringArray = null;
        int n = 0;
        if (this.mpLost > 0) {
            ++n;
        }
        if (this.timeBuff > 0) {
            ++n;
        }
        if (this.per_Sub_Eff > 0) {
            ++n;
        }
        if (this.time_Sub_Eff > 0) {
            ++n;
        }
        if (this.plus_Hp > 0) {
            ++n;
        }
        if (this.plus_Mp > 0) {
            ++n;
        }
        if (this.delay > 0) {
            ++n;
        }
        stringArray = new String[n];
        n = 0;
        if (this.mpLost > 0) {
            stringArray[n++] = T.nangluong + this.mpLost;
        }
        if (this.delay > 0) {
            stringArray[n++] = T.delay + this.delay / 1000 + "s";
        }
        if (this.timeBuff > 0) {
            stringArray[n++] = T.timebuff + this.timeBuff / 1000 + "s";
        }
        if (this.per_Sub_Eff > 0) {
            stringArray[n++] = T.hieuung + this.per_Sub_Eff;
        }
        if (this.time_Sub_Eff > 0) {
            stringArray[n++] = T.timehieuung + this.time_Sub_Eff / 1000 + "s";
        }
        if (this.plus_Hp > 0) {
            stringArray[n++] = T.tanghpdung + this.plus_Hp / 10 + "%";
        }
        if (this.plus_Mp > 0) {
            stringArray[n++] = T.tangmpdung + this.plus_Mp / 10 + "%";
        }
        return stringArray;
    }
}

