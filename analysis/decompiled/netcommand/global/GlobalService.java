/*
 * Decompiled with CFR 0.152.
 */
package netcommand.global;

import CLib.TField;
import CLib.mGraphics;
import CLib.mSystem;
import CLib.mVector;
import GameObjects.MainObject;
import GameObjects.Object_Effect_Skill;
import GameObjects.item_sell;
import GameScreen.GameScreen;
import GameScreen.LoginScreen;
import GameScreen.PaintInfoGameScreen;
import InterfaceComponents.iCommand;
import Main.GameCanvas;
import Model.T;
import Skill.ListSkill;
import netcommand.Cmd_Message;
import netcommand.global.GlobalLogicHandler;

public class GlobalService
extends Cmd_Message {
    protected static GlobalService instance;
    long timeCheck;

    public static GlobalService gI() {
        if (instance == null) {
            instance = new GlobalService();
        }
        return instance;
    }

    public void login(String string, String string2, String string3, String string4, String string5, String string6, int n, byte by) {
        this.init((byte)1);
        try {
            this.m.writer().writeUTF(string);
            this.m.writer().writeUTF(string2);
            this.m.writer().writeUTF(string3);
            this.m.writer().writeUTF(string4);
            this.m.writer().writeUTF(string5);
            this.m.writer().writeUTF(string6);
            this.m.writer().writeByte(mGraphics.zoomLevel);
            this.m.writer().writeByte(GameCanvas.device);
            this.m.writer().writeInt(n);
            this.m.writer().writeByte(by);
            this.m.writer().writeByte((byte)(TField.isQwerty ? 1 : 0));
            this.m.writer().writeByte(GameCanvas.IndexRes);
            this.m.writer().writeByte(LoginScreen.indexInfoLogin);
            this.m.writer().writeByte(GameCanvas.lowGraphic ? 1 : 0);
            this.m.writer().writeShort(GameCanvas.IndexCharPar);
            this.m.writer().writeUTF(GameCanvas.stringPackageName);
            GlobalLogicHandler.isDisConect = false;
            GlobalLogicHandler.timeReconnect = 0L;
            GlobalLogicHandler.isMelogin = false;
        }
        catch (Exception exception) {
            // empty catch block
        }
        this.send();
        if (n == -1) {
            GameCanvas.start_Wait_Dialog(T.dangdangnhap, new iCommand(T.close, 7));
            GameCanvas.countLogin = mSystem.currentTimeMillis();
        } else {
            GameCanvas.start_Wait_Dialog(T.dangketnoilai, new iCommand(T.close, 7));
        }
    }

    public void infoTranpost() {
    }

    public void player_move(short s, short s2) {
        this.init((byte)4);
        try {
            this.m.writer().writeShort(s);
            this.m.writer().writeShort(s2);
        }
        catch (Exception exception) {
            // empty catch block
        }
        this.send();
    }

    public void char_info(short s) {
        this.init((byte)5);
        try {
            this.m.writer().writeShort(s);
        }
        catch (Exception exception) {
            // empty catch block
        }
        this.send();
    }

    public void requestInapPurchare(byte by, String string, byte by2) {
        this.init((byte)-93);
        try {
            this.m.writer().writeByte(by);
            this.m.writer().writeUTF(string);
            this.m.writer().writeByte(by2);
        }
        catch (Exception exception) {
            // empty catch block
        }
        this.send();
    }

    public void new_npc_info(short s) {
        this.init((byte)-44);
        try {
            this.m.writer().writeShort(s);
        }
        catch (Exception exception) {
            // empty catch block
        }
        this.send();
    }

    public void monster_info(short s) {
        this.init((byte)7);
        try {
            this.m.writer().writeShort(s);
        }
        catch (Exception exception) {
            // empty catch block
        }
        this.send();
    }

    public void Ok_Change_Map() {
        this.init((byte)12);
        this.send();
    }

    public void fire_monster(mVector mVector2, byte by) {
        this.init((byte)9);
        try {
            this.m.writer().writeByte(by);
            this.m.writer().writeByte(mVector2.size());
            for (int i = 0; i < mVector2.size(); ++i) {
                Object_Effect_Skill object_Effect_Skill = (Object_Effect_Skill)mVector2.elementAt(i);
                this.m.writer().writeShort(object_Effect_Skill.ID);
            }
            ListSkill.doSetTimeAtt();
        }
        catch (Exception exception) {
            // empty catch block
        }
        this.send();
    }

    public void nap_tien(short s, String[] stringArray) {
        this.init((byte)-53);
        try {
            this.m.writer().writeShort(s);
            this.m.writer().writeByte(stringArray.length);
            for (int i = 0; i < stringArray.length; ++i) {
                this.m.writer().writeUTF(stringArray[i]);
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
        this.send();
    }

    public void load_image_data_part_char(byte by, short s) {
        if (GameCanvas.currentScreen == GameCanvas.selectChar) {
            // empty if block
        }
        this.init((byte)-52);
        try {
            this.m.writer().writeByte(by);
            this.m.writer().writeShort(s);
        }
        catch (Exception exception) {
            // empty catch block
        }
        this.send();
    }

    public void load_image_data_auto_eff(short s) {
        this.init((byte)-49);
        try {
            this.m.writer().writeShort(s);
        }
        catch (Exception exception) {
            // empty catch block
        }
        this.send();
    }

    public void load_image(short s) {
        this.init((byte)-51);
        try {
            this.m.writer().writeShort(s);
        }
        catch (Exception exception) {
            // empty catch block
        }
        this.send();
    }

    public void create_char(byte by, String string, byte by2, byte by3, byte by4, byte by5) {
        this.init((byte)14);
        try {
            this.m.writer().writeByte(by);
            this.m.writer().writeUTF(string);
            this.m.writer().writeByte(by2);
            this.m.writer().writeByte(by3);
            this.m.writer().writeByte(by4);
        }
        catch (Exception exception) {
            // empty catch block
        }
        this.send();
    }

    public void select_char(byte by, int n) {
        this.init((byte)13);
        try {
            this.m.writer().writeByte(by);
            this.m.writer().writeInt(n);
        }
        catch (Exception exception) {
            // empty catch block
        }
        this.send();
        GameCanvas.start_Wait_Dialog(T.pleaseWait, null);
    }

    public void getlist_from_npc(byte by) {
        this.init((byte)23);
        try {
            this.m.writer().writeByte(by);
        }
        catch (Exception exception) {
            // empty catch block
        }
        this.send();
    }

    public void buy_item(byte by, short s, short s2) {
        this.init((byte)24);
        try {
            this.m.writer().writeByte(by);
            this.m.writer().writeShort(s);
            this.m.writer().writeShort(s2);
        }
        catch (Exception exception) {
            // empty catch block
        }
        this.send();
    }

    public void quest(short s, byte by, byte by2) {
        this.init((byte)52);
        try {
            this.m.writer().writeShort(s);
            this.m.writer().writeByte(by2);
            this.m.writer().writeByte(by);
        }
        catch (Exception exception) {
            // empty catch block
        }
        this.send();
        if (GameScreen.help.setStep_Next(0, -5)) {
            ++GameScreen.help.Next;
            GameScreen.help.SaveStep((byte)0, (byte)0);
            GameScreen.help.setNext();
        } else if (GameScreen.help.setStep_Next(8, 10)) {
            GameCanvas.end_Dialog();
            GameScreen.help.p = null;
            GameScreen.help.NextStep();
            GameScreen.help.setNext();
            GameScreen.help.SaveStep();
        }
    }

    public void Dynamic_Menu(short s, byte by, byte by2) {
        mSystem.outz("Gui command -30 idNPC = " + s + " , idMenu = " + by + " , index = " + by2);
        this.init((byte)-30);
        try {
            this.m.writer().writeShort(s);
            this.m.writer().writeByte(by);
            this.m.writer().writeByte(by2);
        }
        catch (Exception exception) {
            // empty catch block
        }
        this.send();
    }

    public void Item_More_Info(byte by, byte by2) {
        this.init((byte)21);
        try {
            this.m.writer().writeByte(by);
            this.m.writer().writeByte(by2);
        }
        catch (Exception exception) {
            // empty catch block
        }
        this.send();
    }

    public void Use_Item(byte by, byte by2) {
        this.init((byte)11);
        try {
            this.m.writer().writeByte(by);
            this.m.writer().writeByte(by2);
        }
        catch (Exception exception) {
            // empty catch block
        }
        this.send();
    }

    public void Use_Potion(short s) {
        this.init((byte)32);
        try {
            this.m.writer().writeShort(s);
        }
        catch (Exception exception) {
            // empty catch block
        }
        this.send();
    }

    public void delete_Item(byte by, short s, byte by2) {
        this.init((byte)18);
        try {
            this.m.writer().writeByte(by);
            this.m.writer().writeShort(s);
            this.m.writer().writeByte(by2);
        }
        catch (Exception exception) {
            // empty catch block
        }
        this.send();
    }

    public void Get_Item_Map(short s, byte by) {
        this.init((byte)20);
        try {
            this.m.writer().writeShort(s);
            this.m.writer().writeByte(by);
        }
        catch (Exception exception) {
            // empty catch block
        }
        this.send();
    }

    public void Add_Base_Skill_Point(byte by, byte by2, short s) {
        this.init((byte)22);
        try {
            this.m.writer().writeByte(by);
            this.m.writer().writeByte(by2);
            this.m.writer().writeShort(s);
        }
        catch (Exception exception) {
            // empty catch block
        }
        this.send();
    }

    public void Add_Base_Skill_Point(byte by, byte by2) {
        this.init((byte)22);
        try {
            this.m.writer().writeByte(by);
            this.m.writer().writeByte(by2);
        }
        catch (Exception exception) {
            // empty catch block
        }
        this.send();
    }

    public void getItemTem(short s) {
        this.init((byte)28);
        try {
            this.m.writer().writeShort(s);
        }
        catch (Exception exception) {
            // empty catch block
        }
        this.send();
    }

    public void gohome(byte by) {
        this.init((byte)31);
        try {
            this.m.writer().writeByte(by);
        }
        catch (Exception exception) {
            // empty catch block
        }
        this.send();
    }

    public void chatPopup(String string) {
        if (string == null || string.trim().length() == 0) {
            return;
        }
        this.init((byte)27);
        try {
            this.m.writer().writeUTF(string);
        }
        catch (Exception exception) {
            // empty catch block
        }
        this.send();
        GameCanvas.msgchat.addNewChat(T.tinden, GameScreen.player.name + ": ", string, (byte)1, false);
    }

    public void chatTab(String string, String string2) {
        if (string == null || string2 == null) {
            return;
        }
        this.init((byte)34);
        try {
            this.m.writer().writeUTF(string);
            this.m.writer().writeUTF(string2);
        }
        catch (Exception exception) {
            // empty catch block
        }
        this.send();
    }

    public void Friend(byte by, String string) {
        this.init((byte)35);
        try {
            this.m.writer().writeByte(by);
            this.m.writer().writeUTF(string);
        }
        catch (Exception exception) {
            // empty catch block
        }
        this.send();
    }

    public void Register(String string, String string2) {
        this.init((byte)39);
        try {
            this.m.writer().writeUTF(string);
            this.m.writer().writeUTF(string2);
        }
        catch (Exception exception) {
            // empty catch block
        }
        this.send();
    }

    public void Party(byte by, String string) {
        this.init((byte)48);
        try {
            this.m.writer().writeByte(by);
            if (by != 0 && by != 5 && by != 4) {
                this.m.writer().writeUTF(string);
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
        this.send();
    }

    public void Buy_Sell(byte by, String string, byte by2, short s, int n) {
        this.init((byte)36);
        try {
            this.m.writer().writeByte(by);
            switch (by) {
                case 0: 
                case 1: {
                    this.m.writer().writeUTF(string);
                    break;
                }
                case 2: 
                case 3: {
                    this.m.writer().writeByte(by2);
                    this.m.writer().writeShort(s);
                    break;
                }
                case 7: {
                    this.m.writer().writeInt(n);
                    break;
                }
                case 9: {
                    this.m.writer().writeUTF(string);
                    break;
                }
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
        this.send();
    }

    public void BuffMore(byte by, byte by2, mVector mVector2) {
        this.init((byte)40);
        try {
            this.m.writer().writeByte(by);
            this.m.writer().writeByte(by2);
            this.m.writer().writeByte(mVector2.size());
            for (int i = 0; i < mVector2.size(); ++i) {
                Object_Effect_Skill object_Effect_Skill = (Object_Effect_Skill)mVector2.elementAt(i);
                this.m.writer().writeShort(object_Effect_Skill.ID);
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
        this.send();
    }

    public void fire_Pk(mVector mVector2, byte by) {
        this.init((byte)6);
        try {
            this.m.writer().writeByte(by);
            this.m.writer().writeByte(mVector2.size());
            for (int i = 0; i < mVector2.size(); ++i) {
                Object_Effect_Skill object_Effect_Skill = (Object_Effect_Skill)mVector2.elementAt(i);
                this.m.writer().writeShort(object_Effect_Skill.ID);
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
        this.send();
    }

    public void set_Pk(byte by) {
        this.init((byte)42);
        try {
            this.m.writer().writeByte(by);
        }
        catch (Exception exception) {
            // empty catch block
        }
        this.send();
    }

    public void Re_Info_Other_Object(String string, byte by) {
        this.init((byte)49);
        try {
            this.m.writer().writeUTF(string);
            this.m.writer().writeByte(by);
        }
        catch (Exception exception) {
            // empty catch block
        }
        this.send();
    }

    public void Change_Area(byte by) {
        this.init((byte)51);
        try {
            this.m.writer().writeByte(by);
        }
        catch (Exception exception) {
            // empty catch block
        }
        this.send();
    }

    public void Request_Area() {
        this.init((byte)54);
        this.send();
    }

    public void Save_RMS_Server(byte by, byte by2, byte[] byArray) {
        this.init((byte)55);
        try {
            this.m.writer().writeByte(by);
            this.m.writer().writeByte(by2);
            int n = 0;
            if (byArray != null) {
                n = byArray.length;
            }
            this.m.writer().writeShort(n);
            for (int i = 0; i < n; ++i) {
                this.m.writer().writeByte(byArray[i]);
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
        this.send();
    }

    public void set_Page(byte by) {
        this.init((byte)56);
        try {
            this.m.writer().writeByte(by);
        }
        catch (Exception exception) {
            // empty catch block
        }
        this.send();
    }

    public void chat_npc(byte by) {
        this.init((byte)60);
        try {
            this.m.writer().writeByte(by);
        }
        catch (Exception exception) {
            // empty catch block
        }
        this.send();
    }

    public void Mon_Capchar(byte by) {
        this.init((byte)-28);
        try {
            this.m.writer().writeByte(PaintInfoGameScreen.mValueHotKey[by]);
            mSystem.outloi("so goi len" + by);
        }
        catch (Exception exception) {
            // empty catch block
        }
        this.send();
        int n = MainObject.vecCapchar.size();
        if (n == 5) {
            MainObject.vecCapchar.removeElementAt(0);
        }
        if (!GameCanvas.isTouch && TField.isQwerty) {
            MainObject.vecCapchar.addElement("" + PaintInfoGameScreen.mValueChar[by]);
        } else {
            MainObject.vecCapchar.addElement("" + PaintInfoGameScreen.mValueHotKey[by]);
        }
        MainObject.strCapchar = "";
        for (int i = 0; i < MainObject.vecCapchar.size(); ++i) {
            MainObject.strCapchar = MainObject.strCapchar + MainObject.vecCapchar.elementAt(i);
        }
    }

    public void Update_Char_Chest(byte by, short s, byte by2, short s2) {
        this.init((byte)65);
        try {
            this.m.writer().writeByte(by);
            this.m.writer().writeShort(s);
            this.m.writer().writeByte(by2);
            this.m.writer().writeShort(s2);
        }
        catch (Exception exception) {
            // empty catch block
        }
        this.send();
    }

    public void Update_Pet_Container(byte by, short s, byte by2, short s2) {
        mSystem.outz("Send desposit/withdraw pet message");
        this.init((byte)44);
        try {
            this.m.writer().writeByte(by);
            this.m.writer().writeShort(s);
            this.m.writer().writeByte(by2);
            this.m.writer().writeShort(s2);
        }
        catch (Exception exception) {
            // empty catch block
        }
        this.send();
    }

    public void dialog_Server(short s, byte by, byte by2) {
        this.init((byte)-32);
        try {
            this.m.writer().writeShort(s);
            this.m.writer().writeByte(by);
            this.m.writer().writeByte(by2);
        }
        catch (Exception exception) {
            // empty catch block
        }
        this.send();
    }

    public void send_cmd_server(byte by) {
        this.init(by);
        this.send();
    }

    public void Rebuild_Item(byte by, short s, byte by2) {
        this.init((byte)67);
        try {
            this.m.writer().writeByte(by);
            this.m.writer().writeShort(s);
            this.m.writer().writeByte(by2);
        }
        catch (Exception exception) {
            // empty catch block
        }
        this.send();
    }

    public void KhamNgoc(byte by, int n, int n2, int n3, int n4) {
        this.init((byte)-100);
        try {
            switch (by) {
                case 0: {
                    this.m.writer().writeByte(by);
                    this.m.writer().writeShort(n);
                    this.m.writer().writeShort(n2);
                    this.m.writer().writeShort(n3);
                    this.m.writer().writeShort(n4);
                    break;
                }
                case 1: {
                    this.m.writer().writeByte(by);
                    this.m.writer().writeShort(n);
                    break;
                }
                case 2: {
                    this.m.writer().writeByte(by);
                    this.m.writer().writeShort(n);
                    this.m.writer().writeShort(n2);
                    break;
                }
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
        this.send();
    }

    public void Replace_Item(byte by, short s) {
        this.init((byte)73);
        try {
            this.m.writer().writeByte(by);
            this.m.writer().writeShort(s);
        }
        catch (Exception exception) {
            // empty catch block
        }
        this.send();
    }

    public void useMount(byte by) {
        this.init((byte)-97);
        try {
            this.m.writer().writeByte(by);
        }
        catch (Exception exception) {
            // empty catch block
        }
        this.send();
    }

    public void Thach_Dau(byte by, String string) {
        this.init((byte)68);
        try {
            this.m.writer().writeByte(by);
            this.m.writer().writeUTF(string);
        }
        catch (Exception exception) {
            // empty catch block
        }
        this.send();
    }

    public void NextClan(byte by) {
        this.init((byte)69);
        try {
            this.m.writer().writeByte(by);
        }
        catch (Exception exception) {
            // empty catch block
        }
        this.send();
    }

    public void InvenClan(byte by) {
        this.init((byte)69);
        try {
            this.m.writer().writeByte(by);
        }
        catch (Exception exception) {
            // empty catch block
        }
        this.send();
    }

    public void Add_And_AnS_MemClan(byte by, String string) {
        this.init((byte)69);
        try {
            this.m.writer().writeByte(by);
            this.m.writer().writeUTF(string);
        }
        catch (Exception exception) {
            // empty catch block
        }
        this.send();
    }

    public void ChucNang_Clan(byte by, int n) {
        this.init((byte)69);
        try {
            this.m.writer().writeByte(by);
            this.m.writer().writeInt(n);
        }
        catch (Exception exception) {
            // empty catch block
        }
        this.send();
    }

    public void gop_Xu_Luong_Clan(byte by, int n) {
        this.init((byte)69);
        try {
            this.m.writer().writeByte(by);
            this.m.writer().writeInt(n);
        }
        catch (Exception exception) {
            // empty catch block
        }
        this.send();
    }

    public void info_Mem_Clan(byte by, String string) {
        this.init((byte)69);
        try {
            this.m.writer().writeByte(by);
            this.m.writer().writeUTF(string);
        }
        catch (Exception exception) {
            // empty catch block
        }
        this.send();
    }

    public void PhongCap_Clan(byte by, byte by2, String string) {
        this.init((byte)69);
        try {
            this.m.writer().writeByte(by);
            this.m.writer().writeByte(by2);
            this.m.writer().writeUTF(string);
        }
        catch (Exception exception) {
            // empty catch block
        }
        this.send();
    }

    public void Delete_Mem_Clan(byte by, String string) {
        this.init((byte)69);
        try {
            this.m.writer().writeByte(by);
            this.m.writer().writeUTF(string);
        }
        catch (Exception exception) {
            // empty catch block
        }
        this.send();
    }

    public void Change_Slo_NoiQuy_Clan(byte by, String string) {
        this.init((byte)69);
        try {
            this.m.writer().writeByte(by);
            this.m.writer().writeUTF(string);
        }
        catch (Exception exception) {
            // empty catch block
        }
        this.send();
    }

    public void Chat_World(String string) {
        this.init((byte)71);
        try {
            this.m.writer().writeUTF(string);
        }
        catch (Exception exception) {
            // empty catch block
        }
        this.send();
    }

    public void Rebuild_Wing(byte by, int n, short s) {
        this.init((byte)77);
        try {
            this.m.writer().writeByte(by);
            this.m.writer().writeInt(n);
            this.m.writer().writeShort(s);
        }
        catch (Exception exception) {
            // empty catch block
        }
        this.send();
    }

    public void UpdateData() {
        this.init((byte)-57);
        try {
            this.m.writer().writeByte(GameCanvas.IndexCharPar);
        }
        catch (Exception exception) {
            // empty catch block
        }
        this.send();
    }

    public void Pet_Eat(short s, short s2, byte by, byte by2) {
        this.init((byte)45);
        try {
            this.m.writer().writeShort(s);
            this.m.writer().writeShort(s2);
            this.m.writer().writeByte(by);
            this.m.writer().writeByte(by2);
        }
        catch (Exception exception) {
            // empty catch block
        }
        this.send();
    }

    public void Update_Pet_Eat(byte by, short s) {
        this.init((byte)44);
        try {
            this.m.writer().writeByte(by);
            this.m.writer().writeShort(s);
        }
        catch (Exception exception) {
            // empty catch block
        }
        this.send();
        mSystem.outz("cho an pet");
    }

    public void doPaymentNokia(String string) {
        this.init((byte)-76);
        try {
            this.m.writer().writeUTF(string);
        }
        catch (Exception exception) {
            // empty catch block
        }
        this.send();
    }

    public void request_LotteryItems(byte by, byte by2) {
        this.init((byte)-91);
        try {
            this.m.writer().writeByte(by);
            if (by == 1) {
                this.m.writer().writeByte(by2);
            } else if (by == 2) {
                this.m.writer().writeByte(by2);
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
        this.send();
    }

    public void DoKhacItem(byte by, byte by2, short s) {
        this.init((byte)-91);
        try {
            this.m.writer().writeByte(by);
            this.m.writer().writeByte(by2);
            this.m.writer().writeShort(s);
        }
        catch (Exception exception) {
            // empty catch block
        }
        this.send();
    }

    public void sendMoreServerInfo(short s, short s2, String[] stringArray) {
        this.init((byte)-31);
        try {
            this.m.writer().writeShort(s);
            this.m.writer().writeShort(s2);
            int n = stringArray.length;
            this.m.writer().writeByte(n);
            for (int i = 0; i < n; ++i) {
                this.m.writer().writeUTF(stringArray[i]);
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        this.send();
    }

    public void sendMoreServerInfo(short s, short s2, mVector mVector2) {
        this.init((byte)-31);
        try {
            this.m.writer().writeShort(s);
            this.m.writer().writeShort(s2);
            int n = mVector2.size();
            this.m.writer().writeByte(n);
            for (int i = 0; i < n; ++i) {
                String string = (String)mVector2.elementAt(i);
                this.m.writer().writeUTF(string);
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        this.send();
    }

    public void arena(byte by) {
        this.init((byte)37);
        try {
            this.m.writer().writeByte(by);
        }
        catch (Exception exception) {
            // empty catch block
        }
        this.send();
    }

    public void useShip(byte by) {
        this.init((byte)-98);
        try {
            this.m.writer().writeByte(by);
        }
        catch (Exception exception) {
            // empty catch block
        }
        this.send();
    }

    public void doSendThachDau(byte by, String string) {
        this.init((byte)-101);
        try {
            this.m.writer().writeByte(by);
            this.m.writer().writeUTF(string);
        }
        catch (Exception exception) {
            // empty catch block
        }
        this.send();
    }

    public void do_Buy_Sell_Item(int n, mVector mVector2, String string, short s, int n2, byte by) {
        this.init((byte)-102);
        try {
            this.m.writer().writeByte(n);
            switch (n) {
                case 0: {
                    this.m.writer().writeByte(mVector2.size());
                    for (int i = 0; i < mVector2.size(); ++i) {
                        item_sell item_sell2 = (item_sell)mVector2.elementAt(i);
                        if (item_sell2 == null) continue;
                        this.m.writer().writeShort(item_sell2.id);
                        this.m.writer().writeInt(item_sell2.price);
                        this.m.writer().writeShort(item_sell2.soluuong);
                        this.m.writer().writeByte(item_sell2.cat);
                    }
                    this.m.writer().writeUTF(string);
                    break;
                }
                case 1: {
                    this.m.writer().writeShort(s);
                    break;
                }
                case 2: {
                    this.m.writer().writeShort(n2);
                    this.m.writer().writeShort(s);
                    this.m.writer().writeByte(by);
                    break;
                }
                case 5: {
                    this.m.writer().writeShort(s);
                    this.m.writer().writeShort(n2);
                }
            }
            this.send();
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public void RequestInfo_MiNuong(byte by, short s) {
        this.init((byte)-103);
        try {
            this.m.writer().writeByte(by);
            switch (by) {
                case 0: {
                    this.m.writer().writeShort(s);
                    break;
                }
                case 1: {
                    break;
                }
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
        this.send();
    }

    public void RequestMaterialTemplate(short s) {
        this.init((byte)-106);
        try {
            this.m.writer().writeShort(s);
        }
        catch (Exception exception) {
            // empty catch block
        }
        this.send();
    }

    public void Hop_rac(byte by, short s, byte by2) {
        this.init((byte)-105);
        try {
            this.m.writer().writeByte(by);
            this.m.writer().writeShort(s);
            this.m.writer().writeByte(by2);
        }
        catch (Exception exception) {
            // empty catch block
        }
        this.send();
    }

    public void Hop_rac(byte by) {
        this.init((byte)-105);
        try {
            this.m.writer().writeByte(by);
        }
        catch (Exception exception) {
            // empty catch block
        }
        this.send();
    }

    public void doUseMaterial(short s) {
        this.init((byte)-107);
        try {
            this.m.writer().writeShort(s);
        }
        catch (Exception exception) {
            // empty catch block
        }
        this.send();
    }

    public void doNapple(byte by, String string, String string2) {
        this.init((byte)-109);
        try {
            this.m.writer().writeByte(by);
            this.m.writer().writeUTF(string);
            this.m.writer().writeUTF(string2);
        }
        catch (Exception exception) {
            // empty catch block
        }
        this.send();
    }

    public void requestinfoMount(byte by) {
        this.init((byte)-110);
        try {
            this.m.writer().writeByte(by);
        }
        catch (Exception exception) {
            // empty catch block
        }
        this.send();
    }

    public void doSendNewInapp(String string, String string2, String string3) {
        this.init((byte)-75);
        try {
            this.m.writer().writeUTF(string);
            this.m.writer().writeUTF(string2);
            this.m.writer().writeUTF(string3);
        }
        catch (Exception exception) {
            // empty catch block
        }
        this.send();
    }
}

