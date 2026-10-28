/*
 * Decompiled with CFR 0.152.
 */
package netcommand;

import CLib.Session_ME;
import java.io.IOException;
import net.ISession;
import net.Message;

public class Cmd_Message {
    public ISession session = Session_ME.gI();
    protected Message m;
    public static final byte MINI_GAME = -91;
    public static final byte NOKIA_PURCHASE_MESSAGE = -76;
    public static final byte GOOGLE_PURCHASE_MESSAGE = -75;
    public static final byte UPDATE_DATA = -57;
    public static final byte NAP_DIAMOND = -56;
    public static final byte CHECK_UPDATE_DATA = -54;
    public static final byte NAP_TIEN = -53;
    public static final byte LOAD_IMAGE_DATA_PART_CHAR = -52;
    public static final byte LOAD_IMAGE = -51;
    public static final byte NPC_BIG = -50;
    public static final byte LOAD_IMAGE_DATA_AUTO_EFF = -49;
    public static final byte NEW_NPC_INFO = -44;
    public static final byte DIALOG_MORE_OPTION_SERVER = -31;
    public static final byte DIALOG_SERVER = -32;
    public static final byte DYNAMIC_MENU = -30;
    public static final byte MONSTER_CAPCHAR = -28;
    public static final byte LOGIN = 1;
    public static final byte LOGIN_FAIL = 2;
    public static final byte MAIN_CHAR_INFO = 3;
    public static final byte OBJECT_MOVE = 4;
    public static final byte CHAR_INFO = 5;
    public static final byte FIRE_PK = 6;
    public static final byte MONSTER_INFO = 7;
    public static final byte PLAYER_EXIT = 8;
    public static final byte FIRE_MONSTER = 9;
    public static final byte MONSTER_FIRE = 10;
    public static final byte USE_ITEM = 11;
    public static final byte CHANGE_MAP = 12;
    public static final byte LIST_CHAR = 13;
    public static final byte SELECT_CHAR = 13;
    public static final byte CREATE_CHAR = 14;
    public static final byte CHAR_WEARING = 15;
    public static final byte CHAR_INVENTORY = 16;
    public static final byte DIE_MONSTER = 17;
    public static final byte DEL_ITEM = 18;
    public static final byte ITEM_DROP = 19;
    public static final byte GET_ITEM_MAP = 20;
    public static final byte ITEM_MORE_INFO = 21;
    public static final byte ADD_BASE_SKILL_POINT = 22;
    public static final byte NPC_INFO = 23;
    public static final byte BUY_ITEM = 24;
    public static final byte ITEM_TEMPLATE = 25;
    public static final byte CATALORY_MONSTER = 26;
    public static final byte CHAT_POPUP = 27;
    public static final byte GET_ITEM_TEM = 28;
    public static final byte LIST_SKILL = 29;
    public static final byte SET_EXP = 30;
    public static final byte GO_HOME = 31;
    public static final byte USE_POTION = 32;
    public static final byte LEVEL_UP = 33;
    public static final byte CHAT_TAB = 34;
    public static final byte FRIEND = 35;
    public static final byte BUY_SELL = 36;
    public static final byte INFO_FROM_SERVER = 37;
    public static final byte REGISTER = 39;
    public static final byte BUFF = 40;
    public static final byte DIE_PLAYER = 41;
    public static final byte PK = 42;
    public static final byte UPDATE_PET_CONTAINER = 44;
    public static final byte PET_EAT = 45;
    public static final byte PARTY = 48;
    public static final byte OTHER_PLAYER_INFO = 49;
    public static final byte EFF_PLUS_TIME = 50;
    public static final byte CHANGE_AREA = 51;
    public static final byte QUEST = 52;
    public static final byte INFO_EASY_SERVER = 53;
    public static final byte UPDATE_STATUS_AREA = 54;
    public static final byte SAVE_RMS = 55;
    public static final byte LIST_SERVER = 56;
    public static final byte LIST_PLAYER_PK = 57;
    public static final byte PLAYER_SUCKHOE = 59;
    public static final byte CHAT_NPC = 60;
    public static final byte NAME_SERVER = 61;
    public static final byte X2_XP = 62;
    public static final byte DELETE_RMS = 63;
    public static final byte HELP_FROM_SERVER = 64;
    public static final byte UPDATE_CHAR_CHEST = 65;
    public static final byte REBUILD_ITEM = 67;
    public static final byte THACH_DAU = 68;
    public static final byte CLAN = 69;
    public static final byte UPDATE_HP_NPC = 70;
    public static final byte CHAT_WORLD = 71;
    public static final byte REPLACE_PLUS_ITEM = 73;
    public static final byte SHOW_NUM_EFF = 74;
    public static final byte EFF_SERVER = 75;
    public static final byte EFF_WEATHER = 76;
    public static final byte REBUILD_WING = 77;
    public static final byte OPEN_BOX = 78;
    public static final byte PET_ATTACK = 84;
    public static final byte MONSTER_DETONATE = 85;
    public static final byte MONSTER_SKILL_INFO = 86;
    public static final byte PET_GAIN_XP = 87;
    public static final byte REMOVE_ACTOR = 90;
    public static final byte IN_APP_PURCHASE = -93;
    public static final byte USE_ITEM_ARENA = -92;
    public static final byte STATUS_ARENA = -94;
    public static final byte MARKKILLER = -95;
    public static final byte SERVER_ADD_NPC = -96;
    public static final byte USE_MOUNT = -97;
    public static final byte USE_SHIP = -98;
    public static final byte LAST_LOGIN = -99;
    public static final byte KHAM_NGOC = -100;
    public static final byte COMPETITION = -101;
    public static final byte MUA_BAN = -102;
    public static final byte INFO_MI_NUONG = -103;
    public static final byte UPDATE_INFO_CLAN_HOLD = -104;
    public static final byte HOP_RAC = -105;
    public static final byte GET_MATERITAL_TEMPLATE = -106;
    public static final byte USE_MATERIAL = -107;
    public static final byte FILL_REC_UPDATE_TIME = -108;
    public static final byte GET_NAP_STORE_APPLE = -109;
    public static final byte SHOW_INFO = -110;

    protected void writeInt(int n) {
        try {
            this.m.writer().writeInt(n);
        }
        catch (IOException iOException) {
            iOException.printStackTrace();
        }
    }

    protected void writeByte(int n) {
        try {
            this.m.writer().writeByte(n);
        }
        catch (IOException iOException) {
            iOException.printStackTrace();
        }
    }

    protected void writeShort(int n) {
        try {
            this.m.writer().writeShort(n);
        }
        catch (IOException iOException) {
            iOException.printStackTrace();
        }
    }

    public void writeUTF(String string) {
        try {
            this.m.writer().writeUTF(string);
        }
        catch (IOException iOException) {
            iOException.printStackTrace();
        }
    }

    protected void writeBoolean(boolean bl) {
        try {
            this.m.writer().writeBoolean(bl);
        }
        catch (IOException iOException) {
            iOException.printStackTrace();
        }
    }

    public void setSession(ISession iSession) {
        this.session = null;
        this.session = iSession;
    }

    public void send() {
        this.session.sendMessage(this.m);
        this.m.cleanup();
    }

    public void init(byte by) {
        this.m = new Message(by);
    }
}

