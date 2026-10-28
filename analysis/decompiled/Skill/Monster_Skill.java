/*
 * Decompiled with CFR 0.152.
 */
package Skill;

import CLib.mHashtable;

public class Monster_Skill {
    public byte typeSkill;
    public byte typeSub;
    public byte nPlash;
    public int range = 0;
    public static final byte S_NEAR = 50;
    public static final byte S_FAR = 0;
    public static final byte S_BUFF = 2;
    public static final byte S_BOSS_CAY_1 = 71;
    public static final byte S_BOSS_CAY_2 = 72;
    public static final byte S_BOSS_ONG_1 = 73;
    public static final byte S_BOSS_ONG_2 = 74;
    public static final byte S_BOSS_CUA_1 = 75;
    public static final byte S_BOSS_CUA_2 = 76;
    public static final byte S_BOSS_SOI_1 = 77;
    public static final byte S_BOSS_SOI_2 = 14;
    public static final byte S_BOSS_NGUOIDA_1 = 78;
    public static final byte S_BOSS_NGUOIDA_2 = 79;
    public static final byte S_BOSS_DE_1 = 93;
    public static final byte S_BOSS_DE_2 = 94;
    public static final byte S_BOSS_NOEL_1 = 113;
    public static final byte S_BOSS_NOEL_2 = 114;
    public static final byte S_BOSS_NOEL_3 = 115;
    public static final byte S_BOSS_CHIEM_THANH = 124;
    public static final byte S_TRU_THANH_1 = 125;
    public static final byte S_TRU_THANH_2 = 126;
    public static final byte S_Tower1 = 95;
    public static final byte S_Tower2 = 96;
    public static final byte S_Tower3 = 97;
    public static final byte S_Tower4 = 98;
    public static final byte S_Medusa1 = 99;
    public static final byte S_Medusa2 = 100;
    public static final byte S_BOSS_34_Dam_Tung = 104;
    public static final byte S_BOSS_84 = 103;
    public static final byte S_BOSS_34_Laser = 105;
    public static final byte S_BOSS_34_Laser_Lan = 106;
    public static final byte S_BOSS_84_Ston_Drop_More = 108;
    public static final byte SUB_VATLY = 0;
    public static final byte SUB_BANG = 1;
    public static final byte SUB_LUA = 2;
    public static final byte SUB_DIEN = 3;
    public static final byte SUB_DOC = 4;
    public static mHashtable hashMonsterSkillInfo = new mHashtable();

    public Monster_Skill(byte by) {
        switch (by) {
            case 0: 
            case 2: 
            case 4: 
            case 6: 
            case 8: 
            case 10: 
            case 12: {
                this.typeSkill = (byte)50;
                this.typeSub = (byte)(by / 2);
                this.nPlash = 1;
                break;
            }
            case 1: 
            case 3: 
            case 5: 
            case 7: 
            case 9: 
            case 11: 
            case 13: {
                this.typeSkill = (byte)50;
                this.typeSub = (byte)(by / 2);
                this.nPlash = (byte)2;
                break;
            }
            case 14: 
            case 16: 
            case 18: 
            case 20: 
            case 22: 
            case 24: 
            case 26: {
                this.typeSkill = 0;
                this.typeSub = (byte)((by - 14) / 2);
                this.nPlash = 1;
                break;
            }
            case 15: 
            case 17: 
            case 19: 
            case 21: 
            case 23: 
            case 25: 
            case 27: {
                this.typeSkill = 0;
                this.typeSub = (byte)((by - 14) / 2);
                this.nPlash = (byte)2;
                break;
            }
            case 28: 
            case 30: {
                this.typeSkill = (byte)2;
                this.typeSub = (byte)((by - 28) / 2);
                this.nPlash = 1;
                break;
            }
            case 29: 
            case 31: {
                this.typeSkill = (byte)2;
                this.typeSub = (byte)((by - 28) / 2);
                this.nPlash = (byte)2;
                break;
            }
            case 32: {
                this.typeSkill = (byte)71;
                this.typeSub = 0;
                this.nPlash = 1;
                break;
            }
            case 33: {
                this.typeSkill = (byte)72;
                this.typeSub = 0;
                this.nPlash = 1;
                break;
            }
            case 34: {
                this.typeSkill = (byte)73;
                this.typeSub = 0;
                this.nPlash = 1;
                break;
            }
            case 35: {
                this.typeSkill = (byte)74;
                this.typeSub = 0;
                this.nPlash = 1;
                break;
            }
            case 36: {
                this.typeSkill = (byte)75;
                this.typeSub = 0;
                this.nPlash = 1;
                break;
            }
            case 37: {
                this.typeSkill = (byte)76;
                this.typeSub = 0;
                this.nPlash = 1;
                break;
            }
            case 38: {
                this.typeSkill = (byte)77;
                this.typeSub = 0;
                this.nPlash = 1;
                break;
            }
            case 39: {
                this.typeSkill = (byte)14;
                this.typeSub = 0;
                this.nPlash = 1;
                break;
            }
            case 40: {
                this.typeSkill = (byte)78;
                this.typeSub = 0;
                this.nPlash = 1;
                break;
            }
            case 41: {
                this.typeSkill = (byte)79;
                this.typeSub = 0;
                this.nPlash = 1;
                break;
            }
            case 44: {
                this.typeSkill = (byte)93;
                this.typeSub = 0;
                this.nPlash = 1;
                break;
            }
            case 45: {
                this.typeSkill = (byte)94;
                this.typeSub = 0;
                this.nPlash = 1;
                break;
            }
            case 46: {
                this.typeSkill = (byte)95;
                this.typeSub = 0;
                this.nPlash = 1;
                break;
            }
            case 47: {
                this.typeSkill = (byte)96;
                this.typeSub = 0;
                this.nPlash = 1;
                break;
            }
            case 48: {
                this.typeSkill = (byte)97;
                this.typeSub = 0;
                this.nPlash = 1;
                break;
            }
            case 49: {
                this.typeSkill = (byte)98;
                this.typeSub = 0;
                this.nPlash = 1;
                break;
            }
            case 50: {
                this.typeSkill = (byte)99;
                this.typeSub = 0;
                this.nPlash = 1;
                break;
            }
            case 51: {
                this.typeSkill = (byte)100;
                this.typeSub = 0;
                this.nPlash = 1;
                break;
            }
            case 54: {
                this.typeSkill = (byte)104;
                this.typeSub = 0;
                this.nPlash = 1;
                break;
            }
            case 55: {
                this.typeSkill = (byte)105;
                this.typeSub = 0;
                this.nPlash = 1;
                break;
            }
            case 56: {
                this.typeSkill = (byte)106;
                this.typeSub = 0;
                this.nPlash = 1;
                break;
            }
            case 59: {
                this.typeSkill = (byte)108;
                this.typeSub = 0;
                this.nPlash = 1;
                break;
            }
            case 60: {
                break;
            }
            case 61: {
                this.typeSkill = (byte)113;
                this.typeSub = 0;
                this.nPlash = 1;
                break;
            }
            case 62: {
                this.typeSkill = (byte)114;
                this.typeSub = 0;
                this.nPlash = 1;
                break;
            }
            case 63: {
                this.typeSkill = (byte)115;
                this.typeSub = 0;
                this.nPlash = 1;
                break;
            }
            case 64: {
                this.typeSkill = (byte)124;
                this.typeSub = 0;
                this.nPlash = 1;
                break;
            }
            case 65: {
                this.typeSkill = (byte)125;
                this.typeSub = 0;
                this.nPlash = 1;
                break;
            }
            case 66: {
                this.typeSkill = (byte)126;
                this.typeSub = 0;
                this.nPlash = 1;
            }
        }
        this.getRange(by);
    }

    public void getRange(int n) {
        try {
            this.range = Integer.parseInt(hashMonsterSkillInfo.get("" + n).toString());
        }
        catch (Exception exception) {
            this.range = 40;
        }
    }
}

