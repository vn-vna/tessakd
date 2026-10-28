/*
 * Decompiled with CFR 0.152.
 */
package Skill;

import GameObjects.MainObject;
import GameObjects.Player;
import InterfaceComponents.TabSkillsNew;
import Model.CRes;
import Skill.Skill;

public class MainListSkill {
    public static MainListSkill me;
    int typeKill;
    int maxSize;
    public static final byte KILL_STONE_DROP_MORE = 5;
    public static final byte KILL_CRACK_EARTH = 6;
    public static final byte KILL_ARROW_RAIN = 14;
    public static final byte KILL_KIEM_LV1 = 15;
    public static final byte KILL_SUNG_LV2 = 18;
    public static final byte KILL_SUNG_LV3 = 19;
    public static final byte KILL_PS_LV2 = 20;
    public static final byte KILL_KIEM_LV3 = 21;
    public static final byte KILL_2KIEM_LV2 = 22;
    public static final byte KILL_KIEM_LV2 = 23;
    public static final byte KILL_SUNG_LV4 = 24;
    public static final byte KILL_2KIEM_LV4 = 27;
    public static final byte KILL_PS_LV1 = 31;
    public static final byte KILL_2KIEM_LV5 = 34;
    public static final byte KILL_NULL = 35;
    public static final byte KILL_2KIEM_TRUNGDOC = 36;
    public static final byte KILL_2KIEM_GAIDOC = 37;
    public static final byte KILL_PS_XUNGKICH = 39;
    public static final byte KILL_SUNG_LASER = 40;
    public static final byte KILL_KIEM_FIRE = 41;
    public static final byte KILL_KIEM_LV6 = 42;
    public static final byte KILL_KIEM_LV7 = 43;
    public static final byte KILL_2KIEM_CRI = 44;
    public static final byte KILL_BUFF = 45;
    public static final byte KILL_PS_CAU_NOILUC = 46;
    public static final byte KILL_PS_DONGDAT = 47;
    public static final byte KILL_PS_ICE_RAIN = 48;
    public static final byte KILL_SUNG_BAO_DAN = 49;
    public static final byte KILL_SUNG_SET_NEW = 50;
    public static final byte KILL_PS_ICE_UP = 51;
    public static final byte KILL_SUNG_RAIN_ROCKET = 52;
    public static final byte KILL_SUNG_RAIN_LIGHTNING = 53;
    public static final byte EFF_KIEMLON_NGU = 54;
    public static final byte EFF_KIEMNHO_TROI = 55;
    public static final byte EFF_PHAP_SU_BANG = 56;
    public static final byte EFF_SUNGNGU = 57;
    public static final byte EFF_CHIEN_BINH_BUFFDAME = 58;
    public static final byte EFF_THICH_KHACH_BUFFHP = 59;
    public static final byte EFF_XA_THU_BUFF_BUFFAMOR = 60;
    public static final byte EFF_PHAP_SU_BUFFGOLD = 61;
    public static final byte KILL_PHAP_SU_115 = 62;
    public static final byte KILL_SUNG_115 = 63;
    public static final byte KILL_BIG_SWORD_115 = 64;
    public static final byte KILL_SMALL_SWORD_115 = 65;
    public static final byte KILL_PHAP_SU_115_2 = 66;
    public static final byte KILL_SUNG_115_2 = 67;
    public static final byte KILL_BIG_SWORD_115_2 = 68;
    public static final byte KILL_SMALL_SWORD_115_2 = 69;
    public static int[][] mSkillAllClasses;
    public static int[][] mBuffAllClasses;
    static int[] mRange;
    static int[] mPlash;
    static int[] mFramePlash;
    public static int[] mEffectKill;

    public static MainListSkill gI() {
        if (me == null) {
            me = new MainListSkill();
        }
        return me;
    }

    public static void loadIndexEffKill() {
        mEffectKill = new int[mPlash.length];
        MainListSkill.mEffectKill[5] = 11;
        MainListSkill.mEffectKill[6] = 12;
        MainListSkill.mEffectKill[14] = 20;
        MainListSkill.mEffectKill[15] = 21;
        MainListSkill.mEffectKill[18] = 22;
        MainListSkill.mEffectKill[19] = 23;
        MainListSkill.mEffectKill[20] = 25;
        MainListSkill.mEffectKill[21] = 26;
        MainListSkill.mEffectKill[22] = 27;
        MainListSkill.mEffectKill[23] = 28;
        MainListSkill.mEffectKill[24] = 31;
        MainListSkill.mEffectKill[27] = 34;
        MainListSkill.mEffectKill[31] = 38;
        MainListSkill.mEffectKill[34] = 40;
        MainListSkill.mEffectKill[35] = 38;
        MainListSkill.mEffectKill[36] = 46;
        MainListSkill.mEffectKill[37] = 47;
        MainListSkill.mEffectKill[39] = 49;
        MainListSkill.mEffectKill[40] = 51;
        MainListSkill.mEffectKill[41] = 52;
        MainListSkill.mEffectKill[42] = 53;
        MainListSkill.mEffectKill[43] = 54;
        MainListSkill.mEffectKill[44] = 55;
        MainListSkill.mEffectKill[45] = 57;
        MainListSkill.mEffectKill[46] = 59;
        MainListSkill.mEffectKill[47] = 60;
        MainListSkill.mEffectKill[48] = 61;
        MainListSkill.mEffectKill[49] = 62;
        MainListSkill.mEffectKill[50] = 82;
        MainListSkill.mEffectKill[51] = 64;
        MainListSkill.mEffectKill[52] = 65;
        MainListSkill.mEffectKill[53] = 66;
        MainListSkill.mEffectKill[54] = 110;
        MainListSkill.mEffectKill[55] = 112;
        MainListSkill.mEffectKill[56] = 109;
        MainListSkill.mEffectKill[57] = 111;
        MainListSkill.mEffectKill[58] = 0;
        MainListSkill.mEffectKill[59] = 0;
        MainListSkill.mEffectKill[60] = 0;
        MainListSkill.mEffectKill[61] = 0;
        MainListSkill.mEffectKill[62] = 119;
        MainListSkill.mEffectKill[63] = 118;
        MainListSkill.mEffectKill[64] = 116;
        MainListSkill.mEffectKill[65] = 117;
        MainListSkill.mEffectKill[66] = 123;
        MainListSkill.mEffectKill[67] = 122;
        MainListSkill.mEffectKill[68] = 120;
        MainListSkill.mEffectKill[69] = 121;
    }

    public static void setKill(int n) {
        Player.mKillPlayer = new int[TabSkillsNew.vecPaintSkill.size()];
        for (int i = 0; i < Player.mKillPlayer.length; ++i) {
            Player.mKillPlayer[i] = mSkillAllClasses[n][i];
        }
    }

    public int setDirection(MainObject mainObject, MainObject mainObject2) {
        int n = mainObject.x - mainObject2.x;
        int n2 = mainObject.y - mainObject2.y;
        int n3 = CRes.abs(n) > CRes.abs(n2) ? (n > 0 ? 2 : 3) : (n2 > 0 ? 1 : 0);
        return n3;
    }

    public static int getRange(int n) {
        Skill skill = MainListSkill.getSkillFormId(n);
        if (skill != null) {
            return skill.range;
        }
        return 50;
    }

    public static Skill getSkillFormId(int n) {
        for (int i = 0; i < TabSkillsNew.vecPaintSkill.size(); ++i) {
            Skill skill = (Skill)TabSkillsNew.vecPaintSkill.elementAt(i);
            if (skill.Id != n) continue;
            return skill;
        }
        return null;
    }

    static {
        mSkillAllClasses = new int[][]{{15, 23, 41, 21, 5, 42, 43, 42, 5, 0, 0, 0, 0, 45, 45, 0, 0, 54, 58, 64, 68}, {15, 44, 36, 22, 37, 27, 34, 27, 14, 0, 0, 0, 0, 45, 45, 0, 0, 55, 60, 65, 69}, {31, 46, 20, 39, 51, 6, 48, 47, 48, 0, 0, 0, 0, 45, 45, 0, 0, 56, 61, 62, 66}, {31, 19, 18, 24, 40, 49, 50, 52, 53, 0, 0, 0, 0, 45, 45, 0, 0, 57, 59, 63, 67}, {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 70, 70, 70, 70}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 45}};
        mBuffAllClasses = new int[][]{{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 0, 0, 0, 0, 0, 9, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 6, 0, 0, 0, 0, 0, 0, 9, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 7, 0, 0, 0, 0, 0, 0, 9, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 8, 0, 0, 0, 0, 0, 0, 9, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 28, 29, 30, 31}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10}};
        mRange = new int[]{200, 60, 200, 50, 200, 110, 110, 70, 100, 200, 200, 200, 200, 200, 110, 60, 30, 110, 110, 110, 110, 110, 75, 60, 110, 100, 120, 110, 160, 140, 120, 70, 60, 100, 110, 50, 60, 110, 110, 110, 110, 60, 110, 110, 60, 120, 110, 110, 110, 110, 110, 110, 110, 110, 110, 110, 110, 110, 110, 110, 110, 110, 110, 110, 110, 110, 110, 110, 110, 110};
        mPlash = new int[]{0, 0, 0, 0, 0, 5, 7, 0, 0, 0, 0, 0, 0, 0, 7, 0, 0, 0, 16, 16, 1, 5, 1, 1, 11, 2, 5, 4, 10, 7, 0, 0, 0, 12, 7, 14, 1, 5, 0, 5, 6, 1, 7, 7, 1, 9, 1, 7, 2, 13, 11, 5, 10, 10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        mFramePlash = new int[]{5, 5, 5, 5, 5, 11, 15, 5, 5, 5, 5, 5, 5, 5, 15, 5, 5, 5, 9, 9, 9, 11, 9, 9, 11, 5, 5, 9, 5, 5, 5, 5, 5, 5, 15, 5, 9, 11, 5, 11, 11, 9, 15, 15, 9, 5, 9, 15, 15, 11, 11, 11, 20, 20, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5};
    }
}

