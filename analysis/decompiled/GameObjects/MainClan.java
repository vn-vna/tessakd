/*
 * Decompiled with CFR 0.152.
 */
package GameObjects;

import GameObjects.MainThanhTichClan;
import Model.T;

public class MainClan {
    public short IdIcon;
    public int IdClan;
    public String shortName;
    public String name;
    public String slogan;
    public String nameThuLinh;
    public String noiquy;
    public int numMem;
    public int maxMem;
    public int Lv;
    public int hang;
    public int gold;
    public int ptLv;
    public long coin;
    public byte chucvu = 0;
    public byte typeX2;
    public short timeX2 = 0;
    public MainThanhTichClan[] mthanhtich = null;
    public static final byte THU_LINH = 127;
    public static final byte PHO_CHI_HUY = 126;
    public static final byte DAI_HIEP_SI = 125;
    public static final byte HIEP_SI_CAO_QUI = 124;
    public static final byte HIEP_SI_DANH_DU = 123;
    public static final byte THANH_VIEN_MOI = 122;
    public static final byte DA_ROI_CLAN = 121;
    public static final byte XIN_GIA_NHAP = 0;
    public static final byte TIM_CLAN_NAME = 1;
    public static final byte THONG_BAO = 2;
    public static final byte ACCEP_JOIN_CLAN = 3;
    public static final byte PHONG_CAP = 4;
    public static final byte HA_CAP = 5;
    public static final byte GOP_XU = 6;
    public static final byte GOP_NGOC = 7;
    public static final byte NEXT_ICON_LIST = 8;
    public static final byte INFO_CLAN = 9;
    public static final byte INVITE_CLAN = 10;
    public static final byte OK_INVITE_CLAN = 11;
    public static final byte DELETE_CLAN = 12;
    public static final byte MEMBER_LIST = 13;
    public static final byte MEMBER_DETAIL = 14;
    public static final byte CLAN_DETAIL = 15;
    public static final byte CLAN_SLOGAN = 16;
    public static final byte CLAN_NOIQUY = 17;
    public static final byte REMOVE_MEM = 18;
    public static final byte UPDATE_INFO_CLAN_OBJ = 19;
    public static final byte ERROR_CREATE_NAME = 20;
    public static final byte INVENTORY_CLAN = 21;
    public static final byte X2_XP_CLAN = 22;
    public boolean isRemove = false;

    public MainClan(int n, short s, String string, byte by) {
        this.IdClan = n;
        this.IdIcon = s;
        this.shortName = string.toUpperCase();
        this.chucvu = by;
    }

    public void setInfoClan(String string, short s, short s2, short s3, short s4, short s5, String string2, String string3, String string4, long l, int n, MainThanhTichClan[] mainThanhTichClanArray) {
        this.name = string;
        this.Lv = s;
        this.ptLv = s2;
        this.hang = s3;
        this.numMem = s4;
        this.maxMem = s5;
        this.slogan = string2;
        this.noiquy = string3;
        this.nameThuLinh = string4;
        this.coin = l;
        this.gold = n;
        this.mthanhtich = mainThanhTichClanArray;
    }

    public boolean setAddMem() {
        return this.chucvu == 127 || this.chucvu == 126;
    }

    public int getEffChucVu() {
        if (this.chucvu == 127) {
            return 13;
        }
        if (this.chucvu == 126) {
            return 7;
        }
        return -1;
    }

    public boolean getChucNang() {
        return this.chucvu == 127 || this.chucvu == 126 || this.chucvu == 125;
    }

    public static String getNameChucVu(byte by) {
        if (by >= 121 && by <= 127) {
            return T.mChucVuClan[127 - by];
        }
        return "";
    }
}

