/*
 * Decompiled with CFR 0.152.
 */
package netcommand.global;

import CLib.Session_ME;
import CLib.mFont;
import CLib.mImage;
import CLib.mSystem;
import CLib.mVector;
import GameEffect.AnimateEffect;
import GameEffect.DataEffAuto;
import GameEffect.EffectAuto;
import GameEffect.EffectMonster;
import GameEffect.EffectSkill;
import GameEffect.MainEffect;
import GameObjects.CatalogyMonster;
import GameObjects.CharPartInfo;
import GameObjects.DataEffect;
import GameObjects.DataSkillEff;
import GameObjects.EffectData;
import GameObjects.GameData;
import GameObjects.ImageIcon;
import GameObjects.Item;
import GameObjects.Item_Drop;
import GameObjects.MainBuff;
import GameObjects.MainClan;
import GameObjects.MainInfoItem;
import GameObjects.MainItem;
import GameObjects.MainMonster;
import GameObjects.MainObject;
import GameObjects.MainParty;
import GameObjects.MainQuest;
import GameObjects.MainRMS;
import GameObjects.MainThanhTichClan;
import GameObjects.Mount;
import GameObjects.MountTemplate;
import GameObjects.NPC;
import GameObjects.NPCMini;
import GameObjects.NPCserver;
import GameObjects.ObjectData;
import GameObjects.Object_Effect_Skill;
import GameObjects.Other_Players;
import GameObjects.Pet;
import GameObjects.PetItem;
import GameObjects.Player;
import GameObjects.TimecountDown;
import GameObjects.idSaveImage;
import GameScreen.Buy_Sell_Screen;
import GameScreen.Clan_Screen;
import GameScreen.CreateChar;
import GameScreen.EventScreen;
import GameScreen.GameScreen;
import GameScreen.Info_Other_Player;
import GameScreen.List_Server;
import GameScreen.LoadMapScreen;
import GameScreen.LoginScreen;
import GameScreen.PaintInfoGameScreen;
import GameScreen.SelectCharScreen;
import GameScreen.ShipScr;
import GameScreen.TabScreenNew;
import GameScreen.WorldMapScreen;
import InterfaceComponents.DataRebuildItem;
import InterfaceComponents.MainTabNew;
import InterfaceComponents.MsgChat;
import InterfaceComponents.MsgDialog;
import InterfaceComponents.TabLottery;
import InterfaceComponents.TabMySeftNew;
import InterfaceComponents.TabQuest;
import InterfaceComponents.TabRebuildItem;
import InterfaceComponents.TabShopNew;
import InterfaceComponents.TabSkillsNew;
import InterfaceComponents.iCommand;
import InterfaceComponents.infoShow;
import Main.GameCanvas;
import Main.GameMidlet;
import Main.ImageEffect;
import Main.ImageEffectAuto;
import Map.ItemMap;
import Map.MainItemMap;
import Map.MapBackGround;
import Model.AvMain;
import Model.CRes;
import Model.CharCountDown;
import Model.EffectManager;
import Model.InfocontenNew;
import Model.MainImage;
import Model.MainImageDataPartChar;
import Model.MainTemplateItem;
import Model.Menu2;
import Model.Point;
import Model.T;
import Skill.HotKey;
import Skill.LvSkill;
import Skill.MainListSkill;
import Skill.Monster_Skill;
import Skill.Skill;
import Thread_More.LoadMap;
import Thread_More.MiniMap;
import Thread_More.SaveImageRMS;
import com.silverknight.TemMidlet;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import net.Message;
import netcommand.global.GlobalLogicHandler;
import netcommand.global.GlobalService;

public class ReadMessenge
extends AvMain {
    short idDialog;
    byte typeDialog;
    public static final byte UPDATE_ALL = 0;
    public static final byte ADD_REPLACE = 1;
    public static final byte DELETE = 2;
    public static final byte UPDATE_SIZECHEST = 3;
    public static byte SHOP_ITEM_EVENT = (byte)100;
    public static byte SHOP_POTION = 0;
    public static byte SHOP_ITEM = 1;
    public static byte SHOP_HAIR = (byte)2;
    public static byte CHEST = (byte)3;
    public static byte SHOP_MATERIAL = (byte)4;
    public static byte REBUILD = (byte)5;
    public static byte SHOP_ICONCLAN_FREE = (byte)6;
    public static byte SHOP_ICONCLAN_VIP = (byte)7;
    public static byte SHOP_POTION_CLAN = (byte)8;
    public static byte REPLACE = (byte)9;
    public static byte WING = (byte)10;
    public static byte PET_KEEPER = (byte)11;
    public static byte TAB_HOP_NGUYEN_LIEU = (byte)12;
    public static byte SHOP_VANTIEU = (byte)13;
    public static byte SHOP_KHAM_NGOC = (byte)14;
    public static byte SHOP_GHEP_NGOC = (byte)15;
    public static byte SHOP_DUC_LO = (byte)16;
    public static byte SHOP_OTHER_PLAYER = (byte)17;
    public static byte SHOP_ANY_NGUYEN_LIEU = (byte)18;
    public static byte SHOP_HOP_AN = (byte)19;
    public static byte SHOP_NANG_CAP_MEDAL = (byte)20;
    String nameParty = "";
    String nameBuySell = "";

    public void commandPointer(int n, int n2) {
        switch (n) {
            case 0: {
                GlobalService.gI().Buy_Sell((byte)1, this.nameBuySell, (byte)0, (short)0, 0);
                GameCanvas.end_Dialog();
                break;
            }
            case 1: {
                GlobalService.gI().Party((byte)2, this.nameParty);
                GameCanvas.end_Dialog();
                break;
            }
            case 2: {
                GameCanvas.end_Dialog();
                if (n2 < 0) break;
                GlobalService.gI().dialog_Server(this.idDialog, this.typeDialog, (byte)n2);
                break;
            }
            case 3: {
                MainObject.StepHelpServer = (byte)(MainObject.StepHelpServer + 1);
                if (MainObject.StepHelpServer >= MainObject.strHelpNPC.length - 1) {
                    GameCanvas.menu2.startAt_NPC(null, MainObject.strHelpNPC[MainObject.strHelpNPC.length - 1], n2, (byte)2, false, 2);
                    break;
                }
                mVector mVector2 = new mVector("ReadMessenge menu");
                iCommand iCommand2 = new iCommand(T.next, 3, n2, (AvMain)this);
                mVector2.addElement(iCommand2);
                GameCanvas.menu2.startAt_NPC(mVector2, MainObject.strHelpNPC[MainObject.StepHelpServer], n2, (byte)2, false, 2);
                break;
            }
            case 4: {
                GameCanvas.menu2.doCloseMenu();
                GlobalService.gI().Replace_Item((byte)2, (short)0);
                GameCanvas.start_Wait_Dialog(T.pleaseWait, new iCommand(T.close, -1));
                break;
            }
            case 20: {
                if (mSystem.isIpAppstore()) {
                    TemMidlet.makePurchase(TemMidlet.google_productIds[n2]);
                    break;
                }
                if (TemMidlet.currentIAPStore == 2) {
                    TemMidlet.makePurchase(TemMidlet.google_productIds[n2]);
                    break;
                }
                if (TemMidlet.currentIAPStore != 1) break;
                if (TemMidlet.isBlockNOKIAStore) {
                    GameCanvas.start_Ok_Dialog("Comming soon !");
                    break;
                }
                if (TemMidlet.isBlockNOKIAStore) break;
                GameCanvas.start_Wait_Dialog(T.pleaseWait, new iCommand(T.close, -1));
                TemMidlet.makePurchase(TemMidlet.productIds[0]);
            }
        }
    }

    public void khamNgoc(Message message) {
        try {
            GameCanvas.end_Dialog();
            TabRebuildItem.isBeginEff = 1;
            TabRebuildItem.isNextRebuild = message.reader().readByte();
            TabRebuildItem.contentShow = message.reader().readUTF();
            TabRebuildItem.vecGem.removeAllElements();
            short s = message.reader().readShort();
            byte by = message.reader().readByte();
            if (s != -1 && by == 7) {
                MainItem mainItem = Item.getMaterial(s);
                if (mainItem != null) {
                    TabRebuildItem.itemRe = mainItem;
                } else {
                    TabRebuildItem.itemRe = new MainItem();
                    TabRebuildItem.itemRe.Id = s;
                    TabRebuildItem.itemRe.ItemCatagory = 7;
                }
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public void mainCharInfo(Message message) {
        try {
            int n;
            int n2;
            short s;
            short s2 = message.reader().readShort();
            String string = message.reader().readUTF();
            string = string.toLowerCase();
            int n3 = message.reader().readInt();
            int n4 = message.reader().readInt();
            int n5 = message.reader().readInt();
            int n6 = message.reader().readInt();
            byte by = message.reader().readByte();
            byte by2 = message.reader().readByte();
            byte by3 = message.reader().readByte();
            byte by4 = message.reader().readByte();
            if (GameScreen.player == null) {
                GameScreen.player = new Player(s2, 0, string.toLowerCase(), 50, 50);
            }
            GameScreen.player.setInfo(by, by3, by4);
            byte by5 = message.reader().readByte();
            MainInfoItem[] mainInfoItemArray = new MainInfoItem[by5];
            int n7 = 0;
            for (s = 0; s < GameScreen.player.mKhangChar.length; ++s) {
                GameScreen.player.mKhangChar[s] = "0";
            }
            for (s = 0; s < by5; ++s) {
                mainInfoItemArray[s] = GameMidlet.version.equals("1.1.0") ? new MainInfoItem(message.reader().readUnsignedByte(), message.reader().readUnsignedShort()) : new MainInfoItem(message.reader().readUnsignedByte(), message.reader().readInt());
                if (mainInfoItemArray[s].id >= 16 && mainInfoItemArray[s].id <= 20) {
                    GameScreen.player.mKhangChar[mainInfoItemArray[s].id - 16] = MainItem.getNopercent(mainInfoItemArray[s].value);
                }
                if (mainInfoItemArray[s].id < 23 || mainInfoItemArray[s].id > 26) continue;
                ++n7;
            }
            if (n7 == 0) {
                GameScreen.player.mInfoChar = mainInfoItemArray;
            } else {
                GameScreen.player.mInfoChar = new MainInfoItem[by5 - n7];
                n7 = 0;
                for (s = 0; s < mainInfoItemArray.length; ++s) {
                    if (mainInfoItemArray[s].id >= 23 && mainInfoItemArray[s].id <= 26) continue;
                    GameScreen.player.mInfoChar[n7] = new MainInfoItem(mainInfoItemArray[s].id, mainInfoItemArray[s].value);
                    ++n7;
                }
            }
            GameScreen.player.Lv = message.reader().readShort();
            GameScreen.player.phantramLv = message.reader().readShort();
            Player.diemTiemNang = message.reader().readShort();
            Player.diemKyNang = message.reader().readShort();
            for (s = 0; s < Player.mTiemnang[0].length; ++s) {
                Player.mTiemnang[0][s] = message.reader().readShort();
            }
            for (s = 0; s < Player.mTiemnang[0].length; ++s) {
                Player.mTiemnang[1][s] = message.reader().readShort();
            }
            Player.mCurentLvSkill = new int[Player.MaxSkill];
            for (s = 0; s < Player.MaxSkill; ++s) {
                Player.mCurentLvSkill[s] = message.reader().readByte();
            }
            for (s = 0; s < Player.MaxSkill; ++s) {
                Player.mPlusLvSkill[s] = message.reader().readByte();
            }
            for (s = 0; s < Player.MaxSkill; ++s) {
                n2 = -1;
                boolean bl = true;
                if (Player.mCurentLvSkill[s] > 0) {
                    if (s < 9) {
                        for (n = 0; n < 3; ++n) {
                            HotKey hotKey = Player.mhotkey[Player.levelTab][n];
                            if (hotKey == null) continue;
                            if (n2 == -1) {
                                if (hotKey.type != HotKey.NULL) continue;
                                n2 = n;
                                bl = false;
                                continue;
                            }
                            if (hotKey.type != HotKey.SKILL || hotKey.id != s) continue;
                            n2 = -1;
                            break;
                        }
                    }
                } else {
                    bl = false;
                }
                if (n2 != -1) {
                    Player.mhotkey[Player.levelTab][n2].setHotKey(s, HotKey.SKILL, (byte)0);
                }
                if (bl) break;
            }
            GameScreen.player.typePk = message.reader().readByte();
            GameScreen.player.pointPk = message.reader().readShort();
            Player.maxInven = message.reader().readByte();
            TabShopNew.maxTab = (byte)(Player.maxInven / 42);
            s = message.reader().readShort();
            if (s >= 0) {
                n2 = message.reader().readInt();
                GameScreen.player.myClan = new MainClan(n2, s, message.reader().readUTF(), message.reader().readByte());
            }
            GameScreen.nameSpecialRegion = message.reader().readUTF();
            GameScreen.timeSpRegion = message.reader().readLong();
            Skill.resetContent();
            GameScreen.player.typeObject = 0;
            GameScreen.player.name = string.toLowerCase();
            GameScreen.player.ID = s2;
            GameScreen.player.hp = n3;
            if (GameScreen.player.hp > 0 && GameScreen.player.Action == 4) {
                GameScreen.player.resetAction();
                GameScreen.player.weapon_frame = 0;
                GameScreen.player.Action = 0;
                GameScreen.player.vecBuff.removeAllElements();
                GlobalService.gI().player_move((short)GameScreen.player.x, (short)GameScreen.player.y);
            }
            GameScreen.player.maxHp = n4;
            GameScreen.player.hpEffect = n4 / 10;
            GameScreen.player.mp = n5;
            GameScreen.player.maxMp = n6;
            GameScreen.player.clazz = by2;
            GameScreen.player.weapon_frame = 0;
            if (!Player.isNewPlayer) {
                EventScreen.vecListEvent.removeAllElements();
                EventScreen.vecEventShow.removeAllElements();
                if (!GameScreen.Vecplayers.equals(GameScreen.player)) {
                    GameScreen.addPlayer(GameScreen.player);
                }
                GameScreen.player.init();
                Player.isNewPlayer = true;
                MsgChat.vecChatTab.removeAllElements();
                GameCanvas.msgchat.addNewChat(T.tinden, "", T.thongbaotuserver, (byte)1, false);
                Player.vecPlayerPk = new short[0];
                MainRMS.RequietRMS();
                GlobalService.gI().send_cmd_server((byte)59);
                PaintInfoGameScreen.numMess = 0;
                GameScreen.VecInfoServer.removeAllElements();
                GameScreen.VecInfoChar.removeAllElements();
                GameScreen.infoGame.strInfoServer = null;
                GameScreen.infoGame.strInfoCharCline = null;
                GameScreen.infoGame.strInfoCharServer = null;
                Player.typeX2 = 0;
                if (GameScreen.player.Lv == 1) {
                    Player.isAutoFire = (byte)-1;
                }
                GlobalService.gI().Save_RMS_Server((byte)1, (byte)4, null);
                GlobalService.gI().Save_RMS_Server((byte)1, (byte)3, null);
                TemMidlet.submitPurchase();
            }
            MainTabNew.timePaintInfo = 0;
            n2 = message.reader().readByte();
            short[] sArray = new short[n2];
            for (n = 0; n < n2; ++n) {
                sArray[n] = -1;
                sArray[n] = message.reader().readShort();
            }
            GameScreen.player.idPartFashion = sArray;
            GameCanvas.naptien = message.reader().readByte();
            n = -1;
            try {
                n = message.reader().readShort();
            }
            catch (Exception exception) {
                n = -1;
                exception.printStackTrace();
            }
            GameScreen.player.idMatna = (short)n;
            try {
                byte by6 = message.reader().readByte();
                GameScreen.player.paintMatnaTruocNon = by6 == 1;
            }
            catch (Exception exception) {
                // empty catch block
            }
            int n8 = -1;
            try {
                n8 = message.reader().readShort();
            }
            catch (Exception exception) {
                n8 = -1;
            }
            GameScreen.player.idPhiPhong = (short)n8;
            int n9 = -1;
            try {
                n9 = message.reader().readShort();
            }
            catch (Exception exception) {
                n9 = -1;
            }
            GameScreen.player.idWeaPon = (short)n9;
            int n10 = -1;
            try {
                n10 = message.reader().readShort();
            }
            catch (Exception exception) {
                n10 = -1;
            }
            GameScreen.player.idHorse = (short)n10;
            int n11 = -1;
            try {
                n11 = message.reader().readShort();
            }
            catch (Exception exception) {
                n11 = -1;
            }
            GameScreen.player.idHair = (short)n11;
            int n12 = -1;
            try {
                n12 = message.reader().readShort();
            }
            catch (Exception exception) {
                n11 = -1;
            }
            GameScreen.player.idWing = (short)n12;
            int n13 = -1;
            try {
                n13 = message.reader().readShort();
            }
            catch (Exception exception) {
                n13 = -1;
            }
            GameScreen.player.idName = (short)n13;
            int n14 = -1;
            try {
                n14 = message.reader().readShort();
            }
            catch (Exception exception) {
                // empty catch block
            }
            GameScreen.player.idBody = (short)n14;
            int n15 = -1;
            try {
                n15 = message.reader().readShort();
            }
            catch (Exception exception) {
                // empty catch block
            }
            GameScreen.player.idLeg = (short)n15;
            int n16 = -1;
            try {
                n16 = message.reader().readShort();
            }
            catch (Exception exception) {
                // empty catch block
            }
            GameScreen.player.idBienhinh = (short)n16;
        }
        catch (IOException iOException) {
            iOException.printStackTrace();
        }
    }

    public void objectMove(Message message) {
        try {
            while (message.reader().available() > 0) {
                Other_Players other_Players;
                MainObject mainObject;
                byte by = message.reader().readByte();
                short s = message.reader().readShort();
                short s2 = message.reader().readShort();
                short s3 = message.reader().readShort();
                short s4 = message.reader().readShort();
                byte by2 = message.reader().readByte();
                if (!LoadMapScreen.isNextMap) continue;
                if (by2 != 127) {
                    mainObject = MainObject.get_Object(s2, by);
                    if (mainObject == null) {
                        if (by == 1) {
                            MainMonster.createMonster(s2, s3, s4, s);
                            GlobalService.gI().monster_info(s2);
                            continue;
                        }
                        if (by == 0) {
                            other_Players = new Other_Players(s2, by, "", s3, s4);
                            other_Players.Direction = CRes.random(4);
                            other_Players.setWearingListChar(null);
                            GameScreen.addPlayer(other_Players);
                            GlobalService.gI().char_info(s2);
                            other_Players.timeRePlayerInfo = GameCanvas.timeNow;
                            continue;
                        }
                        if (by != 2) continue;
                        other_Players = new Other_Players(s2, by, "", s3, s4);
                        other_Players.isObject = true;
                        GameScreen.addPlayer(other_Players);
                        GlobalService.gI().new_npc_info(s2);
                        continue;
                    }
                    if (mainObject.isRemove) {
                        return;
                    }
                    if (mainObject == GameScreen.player) {
                        if (CRes.abs(GameScreen.player.x - s3) > 24 || CRes.abs(GameScreen.player.y - s4) > 24) {
                            Player.isLockKey = true;
                            Player.isSendMove = false;
                            GameScreen.player.toX = GameScreen.player.x;
                            GameScreen.player.toY = GameScreen.player.y;
                            GameScreen.player.xStopMove = s3;
                            GameScreen.player.yStopMove = s4;
                            try {
                                GameScreen.player.posTransRoad = GameCanvas.game.updateFindRoad(s3 / LoadMap.wTile, s4 / LoadMap.wTile, GameScreen.player.x / LoadMap.wTile, GameScreen.player.y / LoadMap.wTile, 20);
                            }
                            catch (Exception exception) {
                                mainObject.x = s3;
                                mainObject.y = s4;
                                GameScreen.player.posTransRoad = null;
                                Player.isLockKey = false;
                                Player.isSendMove = true;
                            }
                        }
                        if (GameScreen.player.posTransRoad != null && GameScreen.player.posTransRoad.length < 20) continue;
                        mainObject.x = s3;
                        mainObject.y = s4;
                        GameScreen.player.posTransRoad = null;
                        Player.isLockKey = false;
                        Player.isSendMove = true;
                        continue;
                    }
                    if (mainObject.typeObject == 1) {
                        mainObject.toX = s3;
                        mainObject.toY = s4;
                        mainObject.isMove = true;
                        if (by2 == 126) {
                            if (mainObject.StepMovebocap != 1) {
                                GameScreen.addEffectEndKill(54, mainObject.x, mainObject.y - 20);
                            }
                            mainObject.x = mainObject.toX;
                            mainObject.y = mainObject.toY;
                            mainObject.isMove = false;
                            mainObject.StepMovebocap = (byte)-1;
                            continue;
                        }
                        if (by2 != 125) continue;
                        mainObject.StepMovebocap = 0;
                        continue;
                    }
                    if (mainObject.Action == 2 || mainObject.Action == 4 || mainObject.KillFire != -1) continue;
                    mainObject.toX = s3;
                    mainObject.toY = s4;
                    mainObject.countCharStand = 0;
                    if (Player.party == null) continue;
                    Player.party.setPos(mainObject.name, s3, s4, mainObject.hp, mainObject.maxHp);
                    continue;
                }
                if (by2 != 127) continue;
                mainObject = MainObject.get_Object(s2, by);
                if (mainObject != null) {
                    if (mainObject.isRemove) {
                        return;
                    }
                    mainObject.isMoveOut = true;
                    mainObject.xMoveOut = s3;
                    mainObject.yMoveOut = s4;
                    continue;
                }
                if (by != 0) continue;
                other_Players = new Other_Players(s2, by, "", s3, s4);
                other_Players.Direction = CRes.random(4);
                other_Players.setWearingListChar(null);
                GameScreen.addPlayer(other_Players);
                GlobalService.gI().char_info(s2);
                other_Players.timeRePlayerInfo = GameCanvas.timeNow;
                mainObject.isMoveOut = true;
                mainObject.xMoveOut = s3;
                mainObject.yMoveOut = s4;
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public void newNPCInfo(Message message) {
        try {
            short s = message.reader().readShort();
            String string = message.reader().readUTF();
            int n = message.reader().readInt();
            int n2 = message.reader().readInt();
            short s2 = message.reader().readShort();
            short s3 = message.reader().readShort();
            short s4 = message.reader().readShort();
            byte by = message.reader().readByte();
            byte by2 = message.reader().readByte();
            String string2 = message.reader().readUTF();
            short s5 = message.reader().readShort();
            MainObject mainObject = MainObject.get_Object(s, (byte)2);
            if (mainObject == null || mainObject.isRemove) {
                mSystem.outz("New npc is null");
                return;
            }
            mainObject.name = string;
            mainObject.x = s3;
            mainObject.y = s4;
            mainObject.toX = s3;
            mainObject.toY = s4;
            mainObject.Lv = s2;
            mainObject.maxHp = n;
            mainObject.hp = n2;
            mainObject.hpEffect = n / 10;
            mainObject.imageId = s5;
            mainObject.typeObject = (byte)2;
            if (s5 == -1) {
                int n3;
                short s6;
                short s7;
                byte by3 = message.reader().readByte();
                byte by4 = message.reader().readByte();
                byte by5 = message.reader().readByte();
                mainObject.setInfo(by3, by4, by5);
                byte[] byArray = new byte[12];
                for (s7 = 0; s7 < byArray.length; ++s7) {
                    byArray[s7] = -1;
                }
                s7 = message.reader().readByte();
                for (s6 = 0; s6 < s7; ++s6) {
                    byte by6;
                    n3 = message.reader().readByte();
                    byArray[n3] = by6 = message.reader().readByte();
                }
                mainObject.setWearingListChar(byArray);
                s6 = message.reader().readShort();
                if (s6 != -1) {
                    n3 = message.reader().readInt();
                    String string3 = message.reader().readUTF();
                    byte by7 = message.reader().readByte();
                    mainObject.myClan = new MainClan(n3, s6, string3, by7);
                } else {
                    mainObject.myClan = null;
                }
            } else {
                byte by8 = message.reader().readByte();
                short s8 = message.reader().readShort();
                mainObject.numFrame = by8;
                mainObject.IdBigAvatar = s8;
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void charInfo(Message message) {
        try {
            boolean bl;
            int n;
            int n2;
            int n3;
            int n4;
            short s = message.reader().readShort();
            String string = message.reader().readUTF();
            string = string.toLowerCase();
            short s2 = message.reader().readShort();
            short s3 = message.reader().readShort();
            byte by2 = message.reader().readByte();
            byte by3 = message.reader().readByte();
            byte by4 = message.reader().readByte();
            byte by5 = message.reader().readByte();
            byte by6 = message.reader().readByte();
            short s4 = message.reader().readShort();
            int n5 = message.reader().readInt();
            int n6 = message.reader().readInt();
            byte by7 = message.reader().readByte();
            short s5 = message.reader().readShort();
            MainObject mainObject = MainObject.get_Object(s, (byte)0);
            if (mainObject == null || mainObject.isRemove) {
                return;
            }
            if (by3 == -126 && string.endsWith(",")) {
                mainObject.iscuop = true;
                string = string.substring(0, string.length() - 1);
            }
            mainObject.name = string;
            mainObject.toX = s2;
            mainObject.toY = s3;
            mainObject.clazz = by2;
            mainObject.weapon_frame = 0;
            mainObject.Lv = s4;
            mainObject.maxHp = n6;
            mainObject.hp = n5;
            mainObject.typePk = by7;
            mainObject.hpEffect = n6 / 10;
            mainObject.pointPk = s5;
            mainObject.isBot = by3;
            if (mainObject.isNPC()) {
                mainObject.nameGiaotiep = mainObject.name;
                mainObject.infoObject = T.tuchoi;
                MiniMap.addNPCMini(new NPCMini(mainObject.isBot, s2, s3));
            }
            if (by3 == -126) {
                mainObject.typeSpec = 1;
            }
            mainObject.setInfo(by4, by5, by6);
            if (Player.party != null) {
                Player.party.setObjParty(mainObject);
            }
            if (mainObject.hp <= 0) {
                mainObject.hp = 0;
                mainObject.mp = 0;
                mainObject.resetAction();
                mainObject.Action = 4;
                mainObject.timedie = GameCanvas.timeNow;
            }
            byte[] byArray = new byte[12];
            short[][] sArrayArray = new short[12][];
            for (n4 = 0; n4 < sArrayArray.length; ++n4) {
                sArrayArray[n4] = new short[3];
            }
            for (n4 = 0; n4 < sArrayArray.length; ++n4) {
                for (n3 = 0; n3 < sArrayArray[n4].length; ++n3) {
                    sArrayArray[n4][n3] = -1;
                }
            }
            for (n4 = 0; n4 < byArray.length; ++n4) {
                byArray[n4] = -1;
            }
            n4 = message.reader().readByte();
            mainObject.removeAllEffCharWearing();
            for (n3 = 0; n3 < n4; ++n3) {
                int mainObject2;
                n2 = message.reader().readByte();
                byArray[n2] = n = message.reader().readByte();
                int by = message.reader().readByte();
                for (mainObject2 = 0; mainObject2 < by; ++mainObject2) {
                    sArrayArray[n2][mainObject2] = message.reader().readShort();
                }
                mainObject2 = message.reader().readShort();
                if (mainObject2 == -1) continue;
                if (n2 == 0) {
                    mainObject.addEffectCharWearing(1, mainObject2);
                    continue;
                }
                if (n2 == 2) {
                    mainObject.addEffectCharWearing(0, mainObject2);
                    continue;
                }
                if (n2 != 1) continue;
                mainObject.addEffectCharWearing(2, mainObject2);
            }
            mainObject.setWearingListChar(byArray);
            mainObject.setArrayGemKham(sArrayArray);
            n3 = message.reader().readShort();
            if (n3 >= 0) {
                n2 = message.reader().readInt();
                mainObject.myClan = new MainClan(n2, (short)n3, message.reader().readUTF(), message.reader().readByte());
            } else {
                mainObject.myClan = null;
            }
            n2 = message.reader().readByte();
            if (n2 >= 0) {
                Pet bl2;
                n = message.reader().readByte();
                byte sArray = message.reader().readByte();
                MainObject mainObject2 = GameScreen.findOwner(mainObject);
                if (mainObject2 != null) {
                    GameScreen.Vecplayers.removeElement(mainObject2);
                }
                if ((bl2 = Pet.createPet(mainObject, (short)n2, sArray, (byte)n)) != null) {
                    GameScreen.addPlayer(bl2);
                }
            }
            n = message.reader().readByte();
            short[] sArray = new short[n];
            for (int exception = 0; exception < n; ++exception) {
                sArray[exception] = -1;
                sArray[exception] = message.reader().readShort();
            }
            mainObject.idPartFashion = sArray;
            try {
                mainObject.idImageHenshin = message.reader().readShort();
            }
            catch (Exception by8) {
                // empty catch block
            }
            byte by = message.reader().readByte();
            mainObject.isFootSnow = bl = message.reader().readBoolean();
            mainObject.typeMount = by;
            byte by8 = message.reader().readByte();
            byte by9 = message.reader().readByte();
            mainObject.typefocus = by8;
            mainObject.typefire = by9;
            int n7 = -1;
            try {
                n7 = message.reader().readShort();
            }
            catch (Exception by11) {
                n7 = -1;
            }
            mainObject.idMatna = (short)n7;
            try {
                byte exception = message.reader().readByte();
                mainObject.paintMatnaTruocNon = exception == 1;
            }
            catch (Exception n9) {
                // empty catch block
            }
            int n8 = -1;
            try {
                n8 = message.reader().readShort();
            }
            catch (Exception n10) {
                n8 = -1;
            }
            mainObject.idPhiPhong = (short)n8;
            int n9 = -1;
            try {
                n9 = message.reader().readShort();
            }
            catch (Exception n11) {
                n9 = -1;
            }
            mainObject.idWeaPon = (short)n9;
            int n10 = -1;
            try {
                n10 = message.reader().readShort();
            }
            catch (Exception n12) {
                n10 = -1;
            }
            mainObject.idHorse = (short)n10;
            int n11 = -1;
            try {
                n11 = message.reader().readShort();
            }
            catch (Exception n13) {
                n11 = -1;
            }
            mainObject.idHair = (short)n11;
            int n12 = -1;
            try {
                n12 = message.reader().readShort();
            }
            catch (Exception n14) {
                n12 = -1;
            }
            mainObject.idWing = (short)n12;
            int n13 = -1;
            try {
                n13 = message.reader().readShort();
            }
            catch (Exception n15) {
                n13 = -1;
            }
            mainObject.idName = (short)n13;
            int n14 = -1;
            try {
                n14 = message.reader().readShort();
            }
            catch (Exception n16) {
                // empty catch block
            }
            mainObject.idBody = (short)n14;
            int n15 = -1;
            try {
                n15 = message.reader().readShort();
            }
            catch (Exception n17) {
                // empty catch block
            }
            mainObject.idLeg = (short)n15;
            int n16 = -1;
            try {
                n16 = message.reader().readShort();
            }
            catch (Exception exception) {
                // empty catch block
            }
            mainObject.idBienhinh = (short)n16;
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void monsterInfo(Message message) {
        try {
            String string;
            int n;
            short s;
            short s2 = message.reader().readShort();
            int n2 = message.reader().readUnsignedByte();
            short s3 = message.reader().readShort();
            short s4 = message.reader().readShort();
            int n3 = message.reader().readInt();
            int n4 = message.reader().readInt();
            byte by = message.reader().readByte();
            int n5 = message.reader().readInt();
            MainMonster mainMonster = (MainMonster)MainObject.get_Object(s2, (byte)1);
            if (mainMonster == null || mainMonster.isRemove) {
                return;
            }
            mainMonster.Lv = (short)n2;
            mainMonster.toX = s3;
            mainMonster.toY = s4;
            mainMonster.hp = n3;
            mainMonster.maxHp = n4;
            mainMonster.isInfo = true;
            mainMonster.setLvmonster(n2);
            mainMonster.timeReveice = n5;
            if (n5 == -2) {
                mainMonster.typeBoss = 1;
            }
            if (n5 == -3) {
                String string2 = message.reader().readUTF();
                mainMonster.typeBoss = (byte)2;
                MainObject.vecCapchar.removeAllElements();
                MainObject.strCapchar = "";
                GameScreen.ObjFocus = mainMonster;
                GameCanvas.addInfoChar(T.helpCapchar);
            }
            if (n5 == -4) {
                mainMonster.typeBoss = (byte)3;
                mainMonster.xPhoBang = message.reader().readShort();
                mainMonster.yPhoBang = message.reader().readShort();
            }
            if (n5 == -5) {
                mainMonster.typeBoss = (byte)4;
                mainMonster.xPhoBang = message.reader().readShort();
                mainMonster.yPhoBang = message.reader().readShort();
            }
            if ((s = message.reader().readShort()) >= 0) {
                n = message.reader().readInt();
                mainMonster.myClan = new MainClan(n, s, message.reader().readUTF(), message.reader().readByte());
            } else {
                mainMonster.myClan = null;
            }
            mainMonster.skillDefault = new Monster_Skill(by);
            if (mainMonster.hp <= 0) {
                mainMonster.hp = 0;
                mainMonster.mp = 0;
                if (mainMonster.Action != 4) {
                    MainObject.startDeadFly(mainMonster, GameScreen.player.ID, CRes.random(3));
                }
            }
            if (mainMonster.typeMonster == 7) {
                MiniMap.addMonMini(mainMonster.ID, (byte)1);
                mainMonster.name = message.reader().readUTF();
            }
            n = message.reader().readByte();
            byte by2 = message.reader().readByte();
            byte by3 = message.reader().readByte();
            mainMonster.isServerControl = n == 1;
            mainMonster.vMax = by2;
            mainMonster.Direction = by3;
            mainMonster.nameowner = string = message.reader().readUTF();
            if (!string.equals("") || mainMonster.isMiNuong()) {
                mainMonster.setspeedVantieu(by2);
            }
            long l = message.reader().readLong();
            mainMonster.colorName = message.reader().readByte();
            mainMonster.setTimelive(l);
            EffectMonster.addEffectMonster(mainMonster);
        }
        catch (Exception exception) {
            mSystem.println("loi cho nay ne monsterinfo " + exception.toString());
            exception.printStackTrace();
        }
    }

    public void playerExit(Message message) {
        try {
            MainObject mainObject;
            short s = message.reader().readShort();
            if (s == GameScreen.player.ID) {
                return;
            }
            MainObject mainObject2 = MainObject.get_Object(s, (byte)0);
            if (mainObject2 != null && !mainObject2.isRemove) {
                mainObject = (Pet)MainObject.get_Object(mainObject2.ID, (byte)9);
                if (mainObject != null) {
                    GameScreen.addEffectEndKill(35, mainObject.x, mainObject.y - 20);
                    mainObject.isStop = true;
                }
                GameScreen.addEffectEndKill(35, mainObject2.x, mainObject2.y - 20);
                mainObject2.isStop = true;
            }
            if ((mainObject = MainObject.get_Object(s, (byte)1)) != null && !mainObject.isRemove) {
                GameScreen.addEffectEndKill(35, mainObject.x, mainObject.y - 20);
                mainObject.isStop = true;
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void changeMap(Message message) {
        block24: {
            boolean bl = false;
            try {
                short s;
                GameCanvas.game.wRec = 0;
                GameCanvas.game.isFullScreen = false;
                GameScreen.infoGame.idCharLoiDai1 = (short)-1;
                GameScreen.infoGame.idCharLoiDai2 = (short)-1;
                LoadMapScreen.isNextMap = false;
                if (Player.isAutoFire > -1) {
                    Player.setCurAutoFire();
                }
                if (GameCanvas.currentScreen != GameCanvas.load) {
                    GameCanvas.load.Show();
                }
                GameScreen.Remove_ChangeMap();
                GameScreen.RemoveLoadMap();
                GameCanvas.loadmap.idMap = message.reader().readShort();
                GameCanvas.worldmap.setPosPlayer(GameCanvas.loadmap.idMap);
                GameScreen.infoGame.setCountTimeHS(GameCanvas.loadmap.idMap, mSystem.currentTimeMillis(), 30);
                GameScreen.player.x = message.reader().readShort() * 24;
                GameScreen.player.y = message.reader().readShort() * 24;
                GameScreen.player.xStand = GameScreen.player.x;
                GameScreen.player.yStand = GameScreen.player.y;
                GameScreen.player.resetMove();
                GameScreen.player.posTransRoad = null;
                GameScreen.player.resetAction();
                if (GameScreen.pet != null) {
                    GameScreen.pet.x = GameScreen.player.x;
                    GameScreen.pet.y = GameScreen.player.y;
                    GameScreen.pet.clearWayPoints();
                }
                GameCanvas.loadmap.idMapMini = message.reader().readShort();
                GameCanvas.loadmap.nameMap = message.reader().readUTF();
                short s2 = message.reader().readShort();
                byte[] byArray = null;
                if (s2 > 0) {
                    byArray = new byte[s2];
                    message.reader().read(byArray);
                }
                GameCanvas.loadmap.loadmap(byArray);
                byte by = message.reader().readByte();
                if (by >= 0) {
                    s = message.reader().readShort();
                    GameCanvas.mapBack = new MapBackGround();
                    GameCanvas.mapBack.setBackGround(by, s);
                } else {
                    GameCanvas.mapBack = null;
                }
                s = message.reader().readShort();
                GameCanvas.load.mItemMap = null;
                if (s > 0) {
                    GameCanvas.load.mItemMap = new byte[s];
                    message.reader().read(GameCanvas.load.mItemMap);
                    GameCanvas.loadmap.load_ItemMap(GameCanvas.load.mItemMap);
                }
                GameScreen.player.useShip = false;
                if (GameCanvas.loadmap.idMap == 19) {
                    GameScreen.infoGame.eff = new EffectAuto(50, 1080, 96, 0, 0, 1, 0);
                } else if (GameCanvas.loadmap.idMap == 67) {
                    GameScreen.infoGame.eff = new EffectAuto(50, 360, 672, 0, 0, 1, 0);
                }
                bl = true;
                LoadMap.vecPointChange.removeAllElements();
                int n = message.reader().readByte();
                for (int i = 0; i < n; ++i) {
                    Point point = new Point();
                    point.x = message.reader().readShort();
                    point.y = message.reader().readShort();
                    point.name = message.reader().readUTF();
                    int n2 = 100;
                    if (point.x < n2) {
                        point.dis = 0;
                        point.x2 = point.x - 8;
                        point.y2 = point.y - 18;
                        point.f = 0;
                        point.vx = -1;
                    } else if (point.x > GameCanvas.loadmap.mapW * LoadMap.wTile - n2) {
                        point.dis = 1;
                        point.f = 1;
                        point.x2 = point.x + 8;
                        point.y2 = point.y - 18;
                        point.vx = 1;
                    } else if (point.y < GameCanvas.loadmap.mapW * LoadMap.wTile / 2) {
                        point.y -= 10;
                        point.dis = 2;
                        point.f = 2;
                        point.x2 = point.x;
                        point.y2 = point.y + 10;
                        point.vy = -1;
                    } else {
                        point.dis = 3;
                        point.f = 2;
                        point.x2 = point.x;
                        point.y2 = point.y - 20;
                        point.vy = 1;
                    }
                    LoadMap.vecPointChange.addElement(point);
                }
                try {
                    GameCanvas.minimap.setSize();
                }
                catch (Exception exception) {
                    exception.printStackTrace();
                }
                byte by2 = message.reader().readByte();
                LoadMap.Area = message.reader().readByte();
                GameCanvas.end_Dialog();
                GameCanvas.clearKeyHold();
                GameCanvas.load.isTele = false;
                if (by2 == 1) {
                    GameCanvas.load.isTele = true;
                }
                GameScreen.isInPetArea = (LoadMap.typeMap = message.reader().readByte()) == 3;
                LoadMapScreen.isNextMap = true;
                GameCanvas.timenextLogin = 0L;
                GameCanvas.countLogin = 0L;
                try {
                    GameScreen.isMapLang = message.reader().readBoolean();
                    GameScreen.isShowHoiSinh = message.reader().readBoolean();
                }
                catch (Exception exception) {
                    GameScreen.isShowHoiSinh = false;
                    GameScreen.isMapLang = false;
                }
            }
            catch (Exception exception) {
                exception.printStackTrace();
                if (!bl) break block24;
                LoadMapScreen.isNextMap = true;
            }
        }
    }

    public void fireMonster(Message message) {
        try {
            this.Fire_Object(message, (byte)0, (byte)1);
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void charWearing(Message message) {
        block30: {
            MainObject mainObject = null;
            try {
                byte by;
                byte by2;
                short s;
                short s3;
                short s4;
                byte by5;
                int n;
                int n2;
                int n3;
                short s5 = message.reader().readShort();
                mainObject = MainObject.get_Object(s5, (byte)0);
                if (mainObject == null || mainObject.isRemove) {
                    return;
                }
                int n4 = message.reader().readByte();
                byte[] byArray = new byte[n4];
                for (n3 = 0; n3 < byArray.length; ++n3) {
                    byArray[n3] = -1;
                }
                if (mainObject == GameScreen.player) {
                    Item.VecEquipPlayer.clear();
                    if (GameScreen.pet != null) {
                        GameScreen.Vecplayers.removeElement(GameScreen.pet);
                    }
                    for (n3 = 0; n3 < mainObject.slotGem.length; ++n3) {
                        mainObject.slotGem[n3] = -1;
                    }
                }
                n3 = 0;
                mainObject.removeAllEffCharWearing();
                for (n2 = 0; n2 < n4; ++n2) {
                    n = message.reader().readByte();
                    if (n > -1) {
                        int n5;
                        byte by3;
                        String string = message.reader().readUTF();
                        by5 = message.reader().readByte();
                        byte by6 = message.reader().readByte();
                        s4 = message.reader().readShort();
                        s3 = message.reader().readByte();
                        byte by4 = message.reader().readByte();
                        s = message.reader().readShort();
                        by2 = message.reader().readByte();
                        byArray[n2] = s3;
                        by = message.reader().readByte();
                        MainInfoItem[] by9 = new MainInfoItem[by];
                        for (by3 = 0; by3 < by; ++by3) {
                            n5 = message.reader().readUnsignedByte();
                            int mainInfoItem = message.reader().readInt();
                            by9[by3] = new MainInfoItem(n5, mainInfoItem);
                            if (n != 8 || n5 != 67) continue;
                            n3 = 1;
                        }
                        by3 = message.reader().readByte();
                        MainItem mainItem = MainItem.MainItem_Item(n, string, s4, by4, by2, by5, 3, by9, by6, false, (short)-1, 0L, s, (byte)0, (byte)0, 0, (byte)0, by3);
                        if (mainObject == GameScreen.player) {
                            Item.VecEquipPlayer.put("" + n, mainItem);
                            if (mainItem.moreContenGem.size() > 0) {
                                for (n5 = 0; n5 < mainItem.moreContenGem.size(); ++n5) {
                                    InfocontenNew mainInfoItem = (InfocontenNew)mainItem.moreContenGem.elementAt(n5);
                                    if (mainInfoItem.idimage >= 23 && mainInfoItem.idimage <= 27) {
                                        mainObject.slotGem[n5] = 0;
                                        continue;
                                    }
                                    if (mainInfoItem.idimage >= 28 && mainInfoItem.idimage <= 32) {
                                        mainObject.slotGem[n5] = 1;
                                        continue;
                                    }
                                    if (mainInfoItem.idimage != -1) continue;
                                    mainObject.slotGem[n5] = -1;
                                }
                            }
                        }
                        if (by6 == 1) {
                            for (n5 = 0; n5 < by9.length; ++n5) {
                                MainInfoItem s6 = by9[n5];
                                if (s6.id != 71) continue;
                                mainObject.addEffectCharWearing(2, s6.value);
                            }
                        }
                        if (by6 == 0) {
                            for (n5 = 0; n5 < by9.length; ++n5) {
                                MainInfoItem mainInfoItem = by9[n5];
                                if (mainInfoItem.id != 71) continue;
                                mainObject.addEffectCharWearing(1, mainInfoItem.value);
                            }
                        }
                        if (by6 != 2) continue;
                        for (n5 = 0; n5 < by9.length; ++n5) {
                            MainInfoItem mainInfoItem = by9[n5];
                            if (mainInfoItem.id != 71) continue;
                            mainObject.addEffectCharWearing(0, mainInfoItem.value);
                        }
                        continue;
                    }
                    byArray[n2] = -1;
                }
                mainObject.isFootSnow = n3;
                n2 = 9;
                n = 14;
                byte by7 = message.reader().readByte();
                mSystem.outz("petId=" + by7);
                byte by8 = 0;
                by5 = 6;
                if (by7 > -1) {
                    int n6;
                    int n7;
                    String string = message.reader().readUTF();
                    s4 = message.reader().readByte();
                    s3 = message.reader().readShort();
                    short s2 = message.reader().readShort();
                    s = message.reader().readByte();
                    by8 = by2 = message.reader().readByte();
                    by5 = by = message.reader().readByte();
                    byte by4 = message.reader().readByte();
                    int n8 = message.reader().readInt();
                    short s6 = message.reader().readShort();
                    short s7 = message.reader().readShort();
                    short s8 = message.reader().readShort();
                    short s9 = message.reader().readShort();
                    short s10 = message.reader().readShort();
                    short s11 = message.reader().readShort();
                    short s12 = message.reader().readShort();
                    int n9 = message.reader().readByte();
                    MainInfoItem[] mainInfoItemArray = new MainInfoItem[n9];
                    for (int i = 0; i < n9; ++i) {
                        n7 = message.reader().readUnsignedByte();
                        n6 = message.reader().readInt();
                        int n10 = message.reader().readInt();
                        mainInfoItemArray[i] = new MainInfoItem(n7, n6, n10);
                    }
                    PetItem petItem = new PetItem(by7, string, s, by4, s4, n2, mainInfoItemArray, n, s3, s, by, by2);
                    petItem.setInfoPet(n8, s6, s8, s9, s10, s11, s7, s12, s2);
                    n7 = message.reader().readByte();
                    if (n7 == 1) {
                        n6 = message.reader().readInt();
                        String string2 = message.reader().readUTF();
                        petItem.setTimeUser(n6, Long.parseLong(string2));
                    }
                    if (mainObject == GameScreen.player) {
                        Item.VecEquipPlayer.put("" + by7, petItem);
                    }
                    byArray[5] = s;
                    if (GameCanvas.subDialog != null && GameCanvas.subDialog.type == 14 && MsgDialog.isInven_Equip == 1) {
                        MsgDialog.pet = petItem;
                    }
                }
                mainObject.setWearingEquip(byArray);
                if (mainObject.equals(GameScreen.player)) {
                    Pet pet = null;
                    if (GameScreen.player.pet != -1) {
                        pet = Pet.createPet(GameScreen.player, (short)GameScreen.player.pet, by5, by8);
                    }
                    if ((GameScreen.pet = pet) != null) {
                        GameScreen.addPlayer(GameScreen.pet);
                    }
                }
                byte by10 = message.reader().readByte();
                short[] sArray = new short[by10];
                for (s3 = 0; s3 < by10; ++s3) {
                    sArray[s3] = -1;
                    sArray[s3] = message.reader().readShort();
                }
                mainObject.idPartFashion = sArray;
            }
            catch (Exception exception) {
                mSystem.println("loi char wearing");
                mSystem.outloi(">>>>>>>>>>>> wearing ");
                if (GameScreen.player == null) {
                    return;
                }
                if (mainObject == GameScreen.player) break block30;
                mainObject.isRemove = true;
            }
        }
    }

    public void dieMonster(Message message) {
        try {
            short s = message.reader().readShort();
            short s2 = message.reader().readShort();
            MainObject mainObject = MainObject.get_Object(s2, (byte)1);
            if (GameScreen.help.setStep_Next(1, 2) && s == GameScreen.player.ID) {
                ++GameScreen.help.Next;
                GameScreen.help.setNext();
                Player.isAutoFire = (byte)-1;
            }
            if (mainObject == null || mainObject.isRemove) {
                return;
            }
            if (mainObject.hp != 0) {
                mainObject.hp = 0;
            }
            mainObject.coutEff = 100;
            if (mainObject.Action == 4 || mainObject.isDie) {
                if (mainObject.typeBoss != 0) {
                    mainObject.isRemove = true;
                    if (mainObject.typeBoss == 2) {
                        MainObject.imgCapchar = null;
                    }
                }
                mainObject.timedie = GameCanvas.timeNow;
            } else {
                mainObject.hp = 0;
                if (mainObject.typeBoss != 0) {
                    mainObject.isRemove = true;
                    if (mainObject.typeBoss == 2) {
                        MainObject.imgCapchar = null;
                    }
                } else {
                    MainObject.startDeadFly((MainMonster)mainObject, s, CRes.random(3));
                }
                if (s == GameScreen.player.ID && mainObject.timeReveice >= 0) {
                    MainQuest.updateQuestKillMonster(s2);
                }
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void petAttack(Message message) {
        try {
            int n;
            int n2;
            mSystem.outz("--- Pet Attack Message --- ");
            byte by = message.reader().readByte();
            short s = message.reader().readShort();
            byte by2 = message.reader().readByte();
            int n3 = message.reader().readByte();
            mVector mVector2 = new mVector("ReadMessenge vecObjectBeAttacked");
            for (int i = 0; i < n3; ++i) {
                short s2 = message.reader().readShort();
                n2 = message.reader().readInt();
                n = message.reader().readInt();
                Monster_Skill monster_Skill = new Monster_Skill(14);
                Object_Effect_Skill object_Effect_Skill = new Object_Effect_Skill(s2, by2, monster_Skill);
                object_Effect_Skill.setHP(n2, n);
                MainObject mainObject = MainObject.get_Object(s2, by2);
                if (mainObject == null || mainObject.isRemove) continue;
                mVector2.addElement(object_Effect_Skill);
            }
            Pet pet = (Pet)MainObject.get_Object(s, (byte)9);
            MainObject mainObject = MainObject.get_Object(s, (byte)0);
            n2 = message.reader().readInt();
            n = message.reader().readInt();
            if (mainObject != null) {
                mainObject.hp = n2;
                mainObject.mp = n;
            }
            if (pet != null) {
                pet.vecObjskill = mVector2;
                pet.setState((byte)2);
                pet.initAttackState(by);
            }
        }
        catch (Exception exception) {
            mSystem.println("loi ham pet attack " + exception.toString());
        }
    }

    public void monsterFire(Message message) {
        int n = -1;
        int n2 = -1;
        try {
            n = message.reader().readByte();
            short s = message.reader().readShort();
            MainMonster mainMonster = (MainMonster)MainObject.get_Object(s, (byte)1);
            if (mainMonster == null || mainMonster.isRemove) {
                return;
            }
            boolean bl = mainMonster.isBossNew();
            if (mainMonster.Action == 4) {
                if (mainMonster.timeReveice < 0) {
                    return;
                }
                mainMonster.Reveive();
            }
            mainMonster.isMove = false;
            mainMonster.vx = 0;
            mainMonster.vy = 0;
            if (n == 1) {
                int n3;
                Object object;
                int n4 = message.reader().readInt();
                byte by = message.reader().readByte();
                n2 = message.reader().readByte();
                mainMonster.hp = n4;
                if (n2 <= 0) {
                    return;
                }
                if (mainMonster.vecObjskill != null && mainMonster.vecObjskill.size() > 0) {
                    object = (Object_Effect_Skill)mainMonster.vecObjskill.elementAt(0);
                    mainMonster.IDAttack = ((Object_Effect_Skill)object).ID;
                    if (mainMonster.skillDefault == null) {
                        mainMonster.skillDefault = new Monster_Skill(14);
                    }
                    if (!bl) {
                        ((Object_Effect_Skill)object).skillMonster = mainMonster.skillDefault;
                    }
                    mainMonster.beginFire();
                    mainMonster.beginSkill();
                }
                object = new mVector("ReadMessenge vecBe");
                boolean bl2 = false;
                mVector mVector2 = new mVector("ReadMessenge allPlayerDie");
                for (n3 = 0; n3 < n2; ++n3) {
                    short s2 = message.reader().readShort();
                    int n5 = message.reader().readInt();
                    int n6 = message.reader().readInt();
                    byte by2 = message.reader().readByte();
                    Monster_Skill monster_Skill = new Monster_Skill(by2);
                    Object_Effect_Skill object_Effect_Skill = new Object_Effect_Skill(s2, by, monster_Skill);
                    object_Effect_Skill.setHP(n5, n6);
                    int n7 = message.reader().readByte();
                    object_Effect_Skill.mEffTypePlus = new int[n7];
                    object_Effect_Skill.mEff_HP_Plus = new int[n7];
                    for (int i = 0; i < n7; ++i) {
                        object_Effect_Skill.mEffTypePlus[i] = message.reader().readByte();
                        object_Effect_Skill.mEff_HP_Plus[i] = message.reader().readInt();
                    }
                    MainObject mainObject = MainObject.get_Object(s2, by);
                    if (mainObject == null || mainObject.isRemove) continue;
                    ((mVector)object).addElement(object_Effect_Skill);
                    if (mainObject.hp > 0) {
                        mainObject.hp = n6;
                    }
                    if (mainObject.ID == GameScreen.player.ID) {
                        int n8 = GameScreen.player.maxHp;
                        int n9 = n6 / n8 * 100;
                        GameScreen.player.setPainthit((byte)4, n9 > 20);
                    }
                    if (mainObject.hp > 0) continue;
                    bl2 = true;
                    mVector2.addElement(mainObject);
                }
                mainMonster.vecObjskill = object;
                mainMonster.hp = n4;
                mainMonster.isRunAttack = true;
                mainMonster.timeMaxMoveAttack = 3000;
                mainMonster.timeBeginMoveAttack = GameCanvas.timeNow;
                if (bl2 || mainMonster.typeBoss == 1 || mainMonster.typeBoss == 2) {
                    Object_Effect_Skill object_Effect_Skill = (Object_Effect_Skill)mainMonster.vecObjskill.elementAt(0);
                    mainMonster.IDAttack = object_Effect_Skill.ID;
                    if (mainMonster.skillDefault == null) {
                        mainMonster.skillDefault = new Monster_Skill(14);
                    }
                    if (!bl) {
                        object_Effect_Skill.skillMonster = mainMonster.skillDefault;
                    }
                    mainMonster.beginFire();
                    mainMonster.beginSkill();
                }
                for (n3 = 0; n3 < mVector2.size(); ++n3) {
                    MainObject mainObject = (MainObject)mVector2.elementAt(n3);
                    mainObject.resetAction();
                    mainObject.Action = 4;
                    mainObject.timedie = GameCanvas.timeNow;
                    mainObject.f = 0;
                    GameScreen.addEffectEndKill(11, mainObject.x, mainObject.y);
                }
            } else if (n == 0) {
                if (mainMonster.vecObjskill != null && mainMonster.vecObjskill.size() > 0) {
                    Object_Effect_Skill object_Effect_Skill = (Object_Effect_Skill)mainMonster.vecObjskill.elementAt(0);
                    mainMonster.IDAttack = object_Effect_Skill.ID;
                    if (mainMonster.skillDefault == null) {
                        mainMonster.skillDefault = new Monster_Skill(14);
                    }
                    if (!bl) {
                        object_Effect_Skill.skillMonster = mainMonster.skillDefault;
                    }
                    mainMonster.beginFire();
                    mainMonster.beginSkill();
                }
                mainMonster.isRunAttack = false;
                mainMonster.posTransRoad = null;
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
            mSystem.outloi(n + " istarget");
            mSystem.outloi(n2 + " size");
        }
    }

    public void loadImage(Message message) {
        try {
            short s = message.reader().readShort();
            String string = "" + s;
            byte[] byArray = new byte[message.reader().available()];
            message.reader().read(byArray);
            if (s == 9999) {
                MainObject.imgCapchar = mImage.createImage(byArray, 0, byArray.length, string);
                return;
            }
            if (ObjectData.setIdOK(s)) {
                SaveImageRMS.vecSaveImage.addElement(new idSaveImage(s, byArray));
            }
            mImage mImage2 = mImage.createImage(byArray, 0, byArray.length, string);
            if (s >= 13000) {
                Item.HashImageItem.put("" + (s - 2000), new MainImage(mImage2));
            } else if (s >= 10700) {
                Item.HashImageMount.put("" + (s - 10700), new MainImage(mImage2));
            } else if (s >= 10600) {
                LoadMap.imgTileSmall = mImage2;
                GameCanvas.minimap.setSize();
            } else if (s >= 10500) {
                LoadMap.imgTileWater = mImage2;
            } else if (s >= 10400) {
                LoadMap.imgTile = mImage2;
            } else if (s >= 10200) {
                Pet.HashImagePet.put("" + (s - 10200), new MainImage(mImage2));
            } else if (s >= 10000) {
                Item.HashImagePetIcon.put("" + (s - 10000), new MainImage(mImage2));
            } else if (s >= 9500) {
                Item.HashImageIconArcheClan.put("" + (s - 9500), new MainImage(mImage2));
            } else if (s >= 7000) {
                Item.HashImageIconClan.put("" + (s - 7000), new MainImage(mImage2));
            } else if (s >= 6000) {
                Skill.hashImageSkill.put("" + (s - 6000), new MainImage(mImage2));
            } else if (s >= 5500) {
                Item.HashImageMaterial.put("" + (s - 5500), new MainImage(mImage2));
            } else if (s >= 5000) {
                Item.HashImageQuestItem.put("" + (s - 5000), new MainImage(mImage2));
            } else if (s >= 4000) {
                Item.HashImagePotion.put("" + (s - 4000), new MainImage(mImage2));
            } else if (s >= 3000) {
                GameScreen.HashImageNPC.put("" + (s - 3000), new MainImage(mImage2));
            } else if (s >= 2000) {
                Item.HashImageItem.put("" + (s - 2000), new MainImage(mImage2));
            } else if (s >= 1000) {
                MainMonster.HashImageMonster.put("" + (s - 1000), new MainImage(mImage2));
            } else {
                GameScreen.HashImageItemMap.put("" + s, new MainImage(mImage2));
            }
        }
        catch (Exception exception) {
            mSystem.println("loi ham load img ");
            exception.printStackTrace();
        }
    }

    public void charInventory(Message message) {
        this.update_InVen_Or_Chest(message, Item.VecInvetoryPlayer, (byte)0);
    }

    public static byte getColorNum(short s, int n) {
        for (int i = 0; i < Item.VecInvetoryPlayer.size(); ++i) {
            Item item = (Item)Item.VecInvetoryPlayer.elementAt(i);
            if (item == null || item.ItemCatagory != 7 || item.Id != s || item.numPotion < n) continue;
            return 1;
        }
        return 0;
    }

    public static String getInfoNum(short s, int n) {
        for (int i = 0; i < Item.VecInvetoryPlayer.size(); ++i) {
            Item item = (Item)Item.VecInvetoryPlayer.elementAt(i);
            if (item == null || item.ItemCatagory != 7 || item.Id != s) continue;
            return item.numPotion + "/" + n;
        }
        return "0/" + n;
    }

    public void npcInfo(Message message) {
        try {
            int n = 8;
            String string = "";
            boolean bl = false;
            String string2 = message.reader().readUTF();
            byte by = message.reader().readByte();
            TabShopNew.isTabHopNL = false;
            if (by < 0) {
                MainObject mainObject = MainObject.get_Object(by, (byte)2);
                if (mainObject != null && !mainObject.isRemove) {
                    mainObject.strChatPopup = string2;
                }
            } else {
                MainItem mainItem;
                int n2;
                byte by2;
                int n3;
                short s;
                int n4;
                GameCanvas.start_Ok_Dialog(T.danglaydulieu);
                int n5 = message.reader().readShort();
                mVector mVector2 = new mVector("ReadMessenge vectorInfoNPC");
                byte by5 = TabShopNew.NORMAL;
                byte by6 = TabShopNew.NORMAL;
                if (by == SHOP_OTHER_PLAYER) {
                    bl = true;
                    by6 = TabShopNew.SHOP_STORE_OTHER_PLAYER;
                    for (n4 = 0; n4 < n5; ++n4) {
                        byte by4;
                        byte by7;
                        short s2;
                        s = message.reader().readByte();
                        if (s == 3) {
                            s2 = message.reader().readShort();
                            String string3 = message.reader().readUTF();
                            by7 = message.reader().readByte();
                            by4 = message.reader().readByte();
                            short l = message.reader().readShort();
                            byte by3 = message.reader().readByte();
                            short mainItem17 = message.reader().readShort();
                            byte mainItem7 = message.reader().readByte();
                            n3 = message.reader().readByte();
                            MainInfoItem[] mainInfoItemArray = new MainInfoItem[n3];
                            for (by2 = 0; by2 < n3; ++by2) {
                                int n6 = message.reader().readUnsignedByte();
                                n2 = message.reader().readInt();
                                mainInfoItemArray[by2] = new MainInfoItem(n6, n2);
                            }
                            by2 = message.reader().readByte();
                            mainItem = MainItem.MainItem_Item(s2, string3, l, by3, mainItem7, by7, 3, mainInfoItemArray, by4, false, s2, 0L, mainItem17, (byte)0, (byte)0, 0, by2, (byte)0);
                            mVector2.addElement(mainItem);
                        }
                        if (s == 7) {
                            s2 = message.reader().readShort();
                            short s5 = message.reader().readShort();
                            by7 = message.reader().readByte();
                            by4 = message.reader().readByte();
                            long l = message.reader().readLong();
                            MainItem mainItem2 = Item.getMaterial(s2);
                            if (mainItem2 != null) {
                                MainItem i = MainItem.MainItem_Material(s2, mainItem2.itemName, mainItem2.imageId, 7, l, (byte)0, mainItem2.content, mainItem2.value, mainItem2.typeMaterial, s5, by7, by4);
                                mVector2.addElement(i);
                            } else {
                                MainItem mainItem3 = new MainItem();
                                mainItem3.Id = s2;
                                mainItem3.numPotion = s5;
                                mainItem3.canSell = by7;
                                mainItem3.canTrade = by4;
                                mainItem3.priceItem = l;
                                mainItem3.priceType = 0;
                                mainItem3.ItemCatagory = 7;
                                Item.put_Material(s2);
                                mVector2.addElement(mainItem3);
                            }
                        }
                        if (s != 4) continue;
                        s2 = message.reader().readShort();
                        short s6 = message.reader().readShort();
                        long l = message.reader().readLong();
                        MainTemplateItem by15 = MainTemplateItem.getPotionTem(s2);
                        if (by15 == null) continue;
                        MainItem mainItem4 = new MainItem(by15.ID, by15.IconId, by15.name, by15.contentPotion, s6, 4, l, by15.typePotion, 0, 0, 0);
                        mVector2.addElement(mainItem4);
                    }
                    TabShopNew.idSeller = n4 = message.reader().readShort();
                } else if (by == SHOP_VANTIEU) {
                    by6 = TabShopNew.SHOP_VANTIEU;
                    for (n4 = 0; n4 < n5; ++n4) {
                        s = message.reader().readShort();
                        String string4 = message.reader().readUTF();
                        byte by9 = message.reader().readByte();
                        byte by10 = message.reader().readByte();
                        short s2 = message.reader().readShort();
                        long n15 = message.reader().readLong();
                        short s3 = message.reader().readShort();
                        byte by4 = message.reader().readByte();
                        n3 = message.reader().readByte();
                        MainInfoItem[] mainInfoItemArray = new MainInfoItem[n3];
                        for (by2 = 0; by2 < n3; ++by2) {
                            int n7 = message.reader().readUnsignedByte();
                            n2 = message.reader().readInt();
                            mainInfoItemArray[by2] = new MainInfoItem(n7, n2);
                        }
                        by2 = message.reader().readByte();
                        mainItem = MainItem.MainItem_Item(s, string4, s2, (byte)0, by4, by9, 3, mainInfoItemArray, by10, false, s, n15, s3, (byte)0, (byte)0, 0, by2, (byte)0);
                        mVector2.addElement(mainItem);
                    }
                } else if (by == SHOP_POTION) {
                    for (n4 = 0; n4 < n5; ++n4) {
                        s = message.reader().readShort();
                        MainTemplateItem mainTemplateItem = MainTemplateItem.getPotionTem(s);
                        if (mainTemplateItem == null) continue;
                        MainItem mainItem6 = new MainItem(mainTemplateItem.ID, mainTemplateItem.IconId, mainTemplateItem.name, mainTemplateItem.contentPotion, 1, 4, mainTemplateItem.PricePoition, mainTemplateItem.typePotion, mainTemplateItem.moneyType, 0, 0);
                        mVector2.addElement(mainItem6);
                    }
                } else if (by == SHOP_ITEM || by == WING || by == SHOP_ITEM_EVENT) {
                    if (by == WING) {
                        by6 = TabShopNew.WING;
                    }
                    for (n4 = 0; n4 < n5; ++n4) {
                        s = message.reader().readShort();
                        String string5 = message.reader().readUTF();
                        byte by12 = message.reader().readByte();
                        byte by13 = message.reader().readByte();
                        short item = message.reader().readShort();
                        long l = message.reader().readLong();
                        short s4 = message.reader().readShort();
                        byte by7 = message.reader().readByte();
                        n3 = message.reader().readByte();
                        MainInfoItem[] mainInfoItemArray = new MainInfoItem[n3];
                        for (by2 = 0; by2 < n3; ++by2) {
                            int n8 = message.reader().readUnsignedByte();
                            n2 = message.reader().readInt();
                            mainInfoItemArray[by2] = new MainInfoItem(n8, n2);
                        }
                        by2 = message.reader().readByte();
                        mainItem = MainItem.MainItem_Item(s, string5, item, (byte)0, by7, by12, 3, mainInfoItemArray, by13, false, s, l, s4, (byte)0, (byte)0, 0, by2, (byte)0);
                        if (by == SHOP_ITEM_EVENT) {
                            mainItem.diaHoiEvent = message.reader().readUTF();
                        }
                        mVector2.addElement(mainItem);
                    }
                    if (by == SHOP_ITEM_EVENT) {
                        string = message.reader().readUTF();
                    }
                } else if (by == SHOP_HAIR) {
                    by6 = TabShopNew.TOC;
                    for (n4 = 0; n4 < n5; ++n4) {
                        s = message.reader().readShort();
                        String string6 = message.reader().readUTF();
                        short s9 = message.reader().readShort();
                        long l = message.reader().readLong();
                        byte by8 = message.reader().readByte();
                        int n9 = message.reader().readByte();
                        MainInfoItem[] mainInfoItemArray = new MainInfoItem[n9];
                        for (int i = 0; i < n9; ++i) {
                            n3 = message.reader().readUnsignedByte();
                            int n10 = message.reader().readInt();
                            mainInfoItemArray[i] = new MainInfoItem(n3, n10);
                        }
                        MainItem mainItem5 = MainItem.MainItem_Toc(s, string6, s9, (byte)0, 6, mainInfoItemArray, false, s, l, by8, (byte)0, (byte)0);
                        mVector2.addElement(mainItem5);
                    }
                } else if (by == CHEST) {
                    by5 = TabShopNew.INVEN_AND_CHEST;
                    n = 9;
                    if (Item.VecChestPlayer.size() == 0) {
                        GlobalService.gI().Update_Char_Chest((byte)-1, (short)0, (byte)0, (short)0);
                    }
                    mVector2 = Item.VecChestPlayer;
                } else if (by == PET_KEEPER) {
                    by5 = TabShopNew.INVEN_AND_PET_KEEPER;
                    n = 13;
                    by6 = TabShopNew.INVEN_AND_PET_KEEPER;
                } else if (by == SHOP_MATERIAL) {
                    n = 8;
                    for (n4 = 0; n4 < n5; ++n4) {
                        MainItem mainItem8;
                        s = message.reader().readShort();
                        MainItem mainItem9 = Item.getMaterial(s);
                        if (mainItem9 != null) {
                            mainItem8 = MainItem.MainItem_Material(s, mainItem9.itemName, mainItem9.imageId, 7, mainItem9.priceItem, mainItem9.priceType, mainItem9.content, (short)mainItem9.value, mainItem9.typeMaterial, (short)1, mainItem9.canSell, mainItem9.canTrade);
                            mVector2.addElement(mainItem8);
                            continue;
                        }
                        mainItem8 = new MainItem();
                        mainItem8.Id = s;
                        mainItem8.ItemCatagory = 7;
                        mVector2.addElement(mainItem8);
                    }
                } else if (by == REBUILD || by == TAB_HOP_NGUYEN_LIEU) {
                    by5 = TabShopNew.INVEN_AND_REBUILD;
                    n = 10;
                    if (by == TAB_HOP_NGUYEN_LIEU) {
                        TabShopNew.isTabHopNL = true;
                    }
                } else if (by == SHOP_HOP_AN) {
                    by5 = TabShopNew.SHOP_HOP_AN;
                    n = 10;
                } else if (by == SHOP_NANG_CAP_MEDAL) {
                    by5 = TabShopNew.SHOP_NANG_CAP_MEDEL;
                    n = 10;
                } else if (by == SHOP_ANY_NGUYEN_LIEU) {
                    by5 = TabShopNew.SHOP_ANY_NGUYEN_LIEU;
                    n = 10;
                } else if (by == SHOP_GHEP_NGOC) {
                    by5 = TabShopNew.SHOP_GHEP_NGOC;
                    n = 10;
                } else if (by == SHOP_DUC_LO) {
                    by5 = TabShopNew.SHOP_DUC_LO;
                    n = 10;
                } else if (by == SHOP_KHAM_NGOC) {
                    by5 = TabShopNew.SHOP_KHAM_NGOC;
                    n = 10;
                } else if (by == SHOP_ICONCLAN_FREE || by == SHOP_ICONCLAN_VIP) {
                    n = 8;
                    by6 = TabShopNew.SHOP_BANG;
                    for (n4 = 0; n4 < n5; ++n4) {
                        s = 0;
                        if (by == SHOP_ICONCLAN_VIP) {
                            s = TabShopNew.PriceIconClan;
                        }
                        MainItem mainItem10 = new MainItem(message.reader().readShort(), s);
                        mVector2.addElement(mainItem10);
                    }
                } else if (by == SHOP_POTION_CLAN) {
                    n = 8;
                    by6 = TabShopNew.NORMAL;
                    for (n4 = 0; n4 < n5; ++n4) {
                        s = message.reader().readShort();
                        MainTemplateItem mainTemplateItem = MainTemplateItem.getPotionTem(s);
                        if (mainTemplateItem == null) continue;
                        MainItem mainItem11 = new MainItem(mainTemplateItem.ID, mainTemplateItem.IconId, mainTemplateItem.name, mainTemplateItem.contentPotion, 1, 4, mainTemplateItem.PricePoition, mainTemplateItem.typePotion, mainTemplateItem.moneyType, 0, 0);
                        mVector2.addElement(mainItem11);
                    }
                } else if (by == REPLACE) {
                    by5 = TabShopNew.INVEN_AND_REPLACE;
                    n = 10;
                }
                mVector mVector3 = new mVector("ReadMessenge vec11");
                if (by != SHOP_ICONCLAN_FREE && by != SHOP_ICONCLAN_VIP && by != SHOP_POTION_CLAN) {
                    TabShopNew tabShopNew = new TabShopNew(Item.VecInvetoryPlayer, 0, T.tabhanhtrang, -1, by5);
                    tabShopNew.isShop_Other_Player = bl;
                    mVector3.addElement(tabShopNew);
                }
                MainTabNew mainTabNew = null;
                if (by == REBUILD || by == TAB_HOP_NGUYEN_LIEU) {
                    mainTabNew = new TabRebuildItem(string2, TabRebuildItem.TYPE_REBUILD);
                    boolean bl2 = mainTabNew.isTabHopNguyenLieu = by == TAB_HOP_NGUYEN_LIEU;
                    if (by == TAB_HOP_NGUYEN_LIEU) {
                        TabShopNew.isTabHopNL = true;
                    }
                    TabRebuildItem.itemRe = null;
                    TabRebuildItem.itemPlus = null;
                    TabRebuildItem.itemFree = null;
                    for (int i = 0; i < TabRebuildItem.idMaterial.length; ++i) {
                        Item item;
                        MainItem mainItem12 = Item.getMaterial(TabRebuildItem.idMaterial[i]);
                        int n10 = 0;
                        if (mainItem12 != null && (item = Item.getItemInventory(mainItem12.ItemCatagory, (short)mainItem12.Id)) != null) {
                            n10 = item.numPotion;
                        }
                        TabRebuildItem.numMaterialInven[i] = n10;
                    }
                } else if (by == SHOP_KHAM_NGOC) {
                    mainTabNew = new TabRebuildItem(string2, TabRebuildItem.TYPE_KHAM_NGOC);
                    TabRebuildItem.vecGem.removeAllElements();
                    TabRebuildItem.itemRe = null;
                    TabRebuildItem.itemPlus = null;
                    TabRebuildItem.itemFree = null;
                    for (int i = 0; i < TabRebuildItem.idMaterial.length; ++i) {
                        Item s11;
                        MainItem mainItem13 = Item.getMaterial(TabRebuildItem.idMaterial[i]);
                        int n11 = 0;
                        if (mainItem13 != null && (s11 = Item.getItemInventory(mainItem13.ItemCatagory, (short)mainItem13.Id)) != null) {
                            n11 = s11.numPotion;
                        }
                        TabRebuildItem.numMaterialInven[i] = n11;
                    }
                } else if (by == SHOP_DUC_LO) {
                    mainTabNew = new TabRebuildItem(string2, TabRebuildItem.TYPE_DUC_LO);
                    TabRebuildItem.vecGem.removeAllElements();
                    TabRebuildItem.itemRe = null;
                    TabRebuildItem.itemPlus = null;
                    TabRebuildItem.itemFree = null;
                    for (int i = 0; i < TabRebuildItem.idMaterial.length; ++i) {
                        Item item;
                        MainItem mainItem14 = Item.getMaterial(TabRebuildItem.idMaterial[i]);
                        int n12 = 0;
                        if (mainItem14 != null && (item = Item.getItemInventory(mainItem14.ItemCatagory, (short)mainItem14.Id)) != null) {
                            n12 = item.numPotion;
                        }
                        TabRebuildItem.numMaterialInven[i] = n12;
                    }
                } else if (by == SHOP_HOP_AN) {
                    int n13;
                    mainTabNew = new TabRebuildItem(string2, TabRebuildItem.TYPE_ANY);
                    mainTabNew.isCreate_medal = true;
                    TabRebuildItem.vecGem.removeAllElements();
                    TabRebuildItem.itemRe = null;
                    TabRebuildItem.itemPlus = null;
                    TabRebuildItem.itemFree = null;
                    for (n13 = 0; n13 < TabRebuildItem.idMaterial.length; ++n13) {
                        Item item;
                        MainItem mainItem15 = Item.getMaterial(TabRebuildItem.idMaterial[n13]);
                        int n14 = 0;
                        if (mainItem15 != null && (item = Item.getItemInventory(mainItem15.ItemCatagory, (short)mainItem15.Id)) != null) {
                            n14 = item.numPotion;
                        }
                        TabRebuildItem.numMaterialInven[n13] = n14;
                    }
                    n13 = message.reader().readByte();
                    TabRebuildItem.vecGem.removeAllElements();
                    for (int i = 0; i < n13; ++i) {
                        short s10 = message.reader().readShort();
                        short item = message.reader().readShort();
                        int n11 = 7;
                        if (s10 == -1 || n11 != 7) continue;
                        TabRebuildItem.numColor[i] = ReadMessenge.getColorNum(s10, item);
                        TabRebuildItem.numofGem[i] = ReadMessenge.getInfoNum(s10, item);
                        MainItem mainItem6 = Item.getMaterial(s10);
                        if (mainItem6 != null) {
                            TabRebuildItem.vecGem.addElement(mainItem6);
                        }
                        if (mainItem6 != null) continue;
                        MainItem mainItem7 = new MainItem();
                        mainItem7.Id = s10;
                        mainItem7.ItemCatagory = 7;
                        TabRebuildItem.vecGem.addElement(mainItem7);
                    }
                } else if (by == SHOP_NANG_CAP_MEDAL) {
                    mainTabNew = new TabRebuildItem(string2, TabRebuildItem.TYPE_ANY);
                    mainTabNew.isUPgradeMedal = true;
                    TabRebuildItem.vecGem.removeAllElements();
                    TabRebuildItem.itemRe = null;
                    TabRebuildItem.itemPlus = null;
                    TabRebuildItem.itemFree = null;
                    for (int i = 0; i < TabRebuildItem.idMaterial.length; ++i) {
                        Item item;
                        MainItem mainItem18 = Item.getMaterial(TabRebuildItem.idMaterial[i]);
                        int n16 = 0;
                        if (mainItem18 != null && (item = Item.getItemInventory(mainItem18.ItemCatagory, (short)mainItem18.Id)) != null) {
                            n16 = item.numPotion;
                        }
                        TabRebuildItem.numMaterialInven[i] = n16;
                    }
                } else if (by == SHOP_ANY_NGUYEN_LIEU) {
                    mainTabNew = new TabRebuildItem(string2, TabRebuildItem.TYPE_ANY);
                    TabRebuildItem.vecGem.removeAllElements();
                    TabRebuildItem.itemRe = null;
                    TabRebuildItem.itemPlus = null;
                    TabRebuildItem.itemFree = null;
                    for (int i = 0; i < TabRebuildItem.idMaterial.length; ++i) {
                        Item item;
                        MainItem mainItem19 = Item.getMaterial(TabRebuildItem.idMaterial[i]);
                        int n17 = 0;
                        if (mainItem19 != null && (item = Item.getItemInventory(mainItem19.ItemCatagory, (short)mainItem19.Id)) != null) {
                            n17 = item.numPotion;
                        }
                        TabRebuildItem.numMaterialInven[i] = n17;
                    }
                } else if (by == SHOP_GHEP_NGOC) {
                    mainTabNew = new TabRebuildItem(string2, TabRebuildItem.TYPE_GHEP_NGOC);
                    TabRebuildItem.vecGem.removeAllElements();
                    TabRebuildItem.itemRe = null;
                    TabRebuildItem.itemPlus = null;
                    TabRebuildItem.itemFree = null;
                    for (int i = 0; i < TabRebuildItem.idMaterial.length; ++i) {
                        Item item;
                        MainItem mainItem20 = Item.getMaterial(TabRebuildItem.idMaterial[i]);
                        int n18 = 0;
                        if (mainItem20 != null && (item = Item.getItemInventory(mainItem20.ItemCatagory, (short)mainItem20.Id)) != null) {
                            n18 = item.numPotion;
                        }
                        TabRebuildItem.numMaterialInven[i] = n18;
                    }
                } else if (by == REPLACE) {
                    mainTabNew = new TabRebuildItem(string2, TabRebuildItem.TYPE_REPLACE_PLUS);
                    TabRebuildItem.itemRe = null;
                    TabRebuildItem.itemPlus = null;
                    TabRebuildItem.itemFree = null;
                } else {
                    mainTabNew = new TabShopNew(mVector2, (byte)n, string2, by, by6);
                    if (by == SHOP_ITEM_EVENT) {
                        mainTabNew.setNameCmd(string);
                    }
                }
                mVector3.addElement(mainTabNew);
                GameCanvas.shopNpc = new TabScreenNew();
                GameCanvas.shopNpc.selectTab = by != SHOP_ICONCLAN_FREE && by != SHOP_ICONCLAN_VIP && by != SHOP_POTION_CLAN ? 1 : 0;
                GameCanvas.shopNpc.addMoreTab(mVector3);
                GameCanvas.shopNpc.Show(GameScreen.gI());
                GameCanvas.end_Dialog();
                if (by == REBUILD || by == REPLACE || by == TAB_HOP_NGUYEN_LIEU) {
                    mainTabNew.init();
                }
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void itemTemplate(Message message) {
        try {
            int n;
            int exception;
            int by;
            byte by3;
            int n3;
            short s;
            int n4;
            int n5 = message.reader().readShort();
            for (n4 = 0; n4 < n5; ++n4) {
                s = message.reader().readShort();
                n3 = message.reader().readShort();
                long l = message.reader().readLong();
                String string = message.reader().readUTF();
                String string2 = message.reader().readUTF();
                byte by4 = message.reader().readByte();
                byte by5 = message.reader().readByte();
                by3 = message.reader().readByte();
                short by2 = message.reader().readShort();
                by = message.reader().readBoolean();
                MainTemplateItem s3 = new MainTemplateItem(s, (short)n3, l, string, string2, by4, by5, by3, by2, by != 0);
                MainTemplateItem.hashPotionTem.put("" + s, s3);
            }
            n4 = message.reader().readUnsignedByte();
            Item.nameInfoItem = new String[n4];
            Item.colorInfoItem = new byte[n4];
            Item.isPercentInfoItem = new byte[n4];
            for (s = 0; s < n4; ++s) {
                Item.nameInfoItem[s] = message.reader().readUTF();
                Item.colorInfoItem[s] = message.reader().readByte();
                Item.isPercentInfoItem[s] = message.reader().readByte();
            }
            s = message.reader().readShort();
            for (n3 = 0; n3 < s; ++n3) {
                short s2 = message.reader().readShort();
                exception = message.reader().readShort();
                long l = message.reader().readLong();
                String string = message.reader().readUTF();
                String string3 = message.reader().readUTF();
                by3 = message.reader().readByte();
                byte by2 = message.reader().readByte();
                by = message.reader().readByte();
                short s3 = message.reader().readShort();
                byte by4 = message.reader().readByte();
                byte by5 = message.reader().readByte();
                MainItem mainItem = MainItem.MainItem_Material(s2, string, exception, 7, l, by2, string3, s3, by3, (short)1, by, by4);
                mainItem.setColorName(by5);
                MainTemplateItem.hashMaterialTem.put("" + s2, mainItem);
            }
            TabShopNew.priceSellPotion = message.reader().readShort();
            TabShopNew.priceSellItem = message.reader().readShort();
            TabShopNew.hesoLv = message.reader().readShort();
            TabShopNew.hesoColor = message.reader().readShort();
            TabShopNew.priceSellQuest = message.reader().readShort();
            TabShopNew.maxPriceItem = message.reader().readShort();
            TabShopNew.PriceIconClan = message.reader().readShort();
            TabShopNew.PriceChatWorld = message.reader().readByte();
            LoginScreen.indexInfoLogin = 0;
            n3 = message.reader().readByte();
            mSystem.outz("size pet template = " + n3);
            for (n = 0; n < n3; ++n) {
                exception = message.reader().readShort();
                byte by8 = message.reader().readByte();
                MainTemplateItem.hashPetTem.put("" + exception, new Byte(by8));
            }
            try {
                n = message.reader().readByte();
                MainObject.idMaterialHopNguyenLieu = new short[n];
                for (exception = 0; exception < n; ++exception) {
                    MainObject.idMaterialHopNguyenLieu[exception] = message.reader().readShort();
                }
            }
            catch (Exception exception2) {
                mSystem.println("----Err Readmess:-- itemTemplate");
            }
            byte by9 = 0;
            try {
                PaintInfoGameScreen.paint18plush = by9 = message.reader().readByte();
            }
            catch (Exception n6) {
                PaintInfoGameScreen.paint18plush = 0;
            }
            try {
                int exception3 = message.reader().readByte();
                GameScreen.idmap18 = new short[exception3];
                for (int i = 0; i < exception3; ++i) {
                    GameScreen.idmap18[i] = (short)message.reader().readUnsignedByte();
                }
            }
            catch (Exception exception4) {
                GameScreen.idmap18 = new short[0];
            }
        }
        catch (Exception exception) {
            Session_ME.gI().close();
            mVector mVector2 = new mVector("ReadMessenge vec7");
            if (SelectCharScreen.isSelectOk && GameCanvas.currentScreen != GameCanvas.login) {
                mVector2.addElement(new iCommand(T.again, 0));
            }
            mVector2.addElement(new iCommand(T.exit, 6));
            GameCanvas.start_Select_Dialog(T.disconnect, mVector2);
            exception.printStackTrace();
        }
    }

    public void catalogyMonster(Message message) {
        try {
            byte[] byArray;
            Object object;
            int n;
            Object object2;
            int n2;
            int n3;
            int n4;
            int n5;
            int n6;
            int n7 = message.reader().readShort();
            for (n6 = 0; n6 < n7; ++n6) {
                n5 = message.reader().readShort();
                String string = message.reader().readUTF();
                n4 = message.reader().readUnsignedByte();
                n3 = message.reader().readInt();
                n2 = message.reader().readByte();
                object2 = new CatalogyMonster(n5, n4, n3, n2, string);
                MainMonster.VecCatalogyMonSter.addElement(object2);
            }
            n6 = message.reader().readByte();
            MountTemplate.FRAME_VE_TRUOC = new byte[n6][];
            for (n5 = 0; n5 < n6; ++n5) {
                byte by = message.reader().readByte();
                MountTemplate.FRAME_VE_TRUOC[n5] = new byte[by];
                for (n4 = 0; n4 < by; ++n4) {
                    MountTemplate.FRAME_VE_TRUOC[n5][n4] = message.reader().readByte();
                }
            }
            n6 = message.reader().readByte();
            MountTemplate.DY_CHAR_STAND = new byte[n6][];
            for (n5 = 0; n5 < n6; ++n5) {
                byte by = message.reader().readByte();
                MountTemplate.DY_CHAR_STAND[n5] = new byte[by];
                for (n4 = 0; n4 < by; ++n4) {
                    MountTemplate.DY_CHAR_STAND[n5][n4] = message.reader().readByte();
                }
            }
            n6 = message.reader().readByte();
            MountTemplate.DY_CHAR_MOVE = new byte[n6][];
            for (n5 = 0; n5 < n6; ++n5) {
                byte by = message.reader().readByte();
                MountTemplate.DY_CHAR_MOVE[n5] = new byte[by];
                for (n4 = 0; n4 < by; ++n4) {
                    MountTemplate.DY_CHAR_MOVE[n5][n4] = message.reader().readByte();
                }
            }
            n6 = message.reader().readByte();
            MountTemplate.dx = new byte[n6][];
            for (n5 = 0; n5 < n6; ++n5) {
                byte by = message.reader().readByte();
                MountTemplate.dx[n5] = new byte[by];
                for (n4 = 0; n4 < by; ++n4) {
                    MountTemplate.dx[n5][n4] = message.reader().readByte();
                }
            }
            n6 = message.reader().readByte();
            MountTemplate.dy = new byte[n6][];
            for (n5 = 0; n5 < n6; ++n5) {
                byte by = message.reader().readByte();
                MountTemplate.dy[n5] = new byte[by];
                for (n4 = 0; n4 < by; ++n4) {
                    MountTemplate.dy[n5][n4] = message.reader().readByte();
                }
            }
            n6 = message.reader().readByte();
            MountTemplate.FRAME_MOVE_LR = new byte[n6][];
            for (n5 = 0; n5 < n6; ++n5) {
                byte by = message.reader().readByte();
                MountTemplate.FRAME_MOVE_LR[n5] = new byte[by];
                for (n4 = 0; n4 < by; ++n4) {
                    MountTemplate.FRAME_MOVE_LR[n5][n4] = message.reader().readByte();
                }
            }
            n6 = message.reader().readByte();
            MountTemplate.FRAME_MOVE_DOWN = new byte[n6][];
            for (n5 = 0; n5 < n6; ++n5) {
                byte by = message.reader().readByte();
                MountTemplate.FRAME_MOVE_DOWN[n5] = new byte[by];
                for (n4 = 0; n4 < by; ++n4) {
                    MountTemplate.FRAME_MOVE_DOWN[n5][n4] = message.reader().readByte();
                }
            }
            n6 = message.reader().readByte();
            MountTemplate.FRAME_MOVE_UP = new byte[n6][];
            for (n5 = 0; n5 < n6; ++n5) {
                byte by = message.reader().readByte();
                MountTemplate.FRAME_MOVE_UP[n5] = new byte[by];
                for (n4 = 0; n4 < by; ++n4) {
                    MountTemplate.FRAME_MOVE_UP[n5][n4] = message.reader().readByte();
                }
            }
            n6 = message.reader().readByte();
            MountTemplate.speed = new byte[n6];
            for (n5 = 0; n5 < n6; ++n5) {
                MountTemplate.speed[n5] = message.reader().readByte();
            }
            n5 = message.reader().readByte();
            Item.ATB_Can_Not_Paint = new byte[n5];
            for (n = 0; n < n5; ++n) {
                Item.ATB_Can_Not_Paint[n] = message.reader().readByte();
            }
            try {
                n = message.reader().readByte();
                Pet.mTypemove = new byte[n];
                for (n4 = 0; n4 < n; ++n4) {
                    Pet.mTypemove[n4] = message.reader().readByte();
                }
            }
            catch (Exception exception) {
                Pet.mTypemove = new byte[]{2, 1, 0, 2, 0, 1};
            }
            try {
                byte by = message.reader().readByte();
                MainTemplateItem.mItem_Equip_Tem = new int[by];
                for (n4 = 0; n4 < by; ++n4) {
                    MainTemplateItem.mItem_Equip_Tem[n4] = message.reader().readByte();
                }
            }
            catch (Exception exception) {
                MainTemplateItem.mItem_Equip_Tem = new int[]{-1, 0, 3, 4, 5, 12, 2, 1, 6, 4, 7, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2};
            }
            try {
                byte by = message.reader().readByte();
                MainTemplateItem.mItem_Rotate_Tem_Equip = new int[by];
                for (n4 = 0; n4 < by; ++n4) {
                    MainTemplateItem.mItem_Rotate_Tem_Equip[n4] = message.reader().readByte();
                }
            }
            catch (Exception exception) {
                MainTemplateItem.mItem_Rotate_Tem_Equip = new int[]{1, 7, 6, 2, -1, 4, 8, 10, 0, 0, 0, 0, 5};
            }
            try {
                byte by = message.reader().readByte();
                MainMonster.idBossNew = new short[by];
                for (n4 = 0; n4 < by; ++n4) {
                    MainMonster.idBossNew[n4] = message.reader().readShort();
                }
            }
            catch (Exception exception) {
                MainMonster.idBossNew = new short[]{104, 103, 105, 106, 135};
            }
            try {
                byte by = message.reader().readByte();
                Player.ID_HAIR_NO_HAT = new byte[by];
                for (n4 = 0; n4 < by; ++n4) {
                    Player.ID_HAIR_NO_HAT[n4] = message.reader().readByte();
                }
            }
            catch (Exception exception) {
                Player.ID_HAIR_NO_HAT = null;
            }
            try {
                byte by = message.reader().readByte();
                for (n4 = 0; n4 < by; ++n4) {
                    n3 = message.reader().readByte();
                    n2 = message.reader().readShort();
                    if (n2 <= 0) continue;
                    object2 = new byte[n2];
                    for (int i = 0; i < n2; ++i) {
                        object2[i] = message.reader().readByte();
                    }
                    mVector mVector2 = new mVector();
                    DataEffect dataEffect = new DataEffect((byte[])object2, n3, true);
                    mVector2.addElement(dataEffect);
                    Pet.PET_DATA.put("" + n3, mVector2);
                }
            }
            catch (Exception exception) {
                // empty catch block
            }
            try {
                n4 = message.reader().readShort();
                object = new byte[n4][];
                for (n3 = 0; n3 < n4; ++n3) {
                    n2 = message.reader().readByte();
                    object[n3] = new byte[n2];
                    for (int i = 0; i < n2; ++i) {
                        object[n3][i] = message.reader().readByte();
                    }
                }
            }
            catch (Exception exception) {
                object = null;
            }
            if (object != null) {
                EffectSkill.arrInfoEff = object;
            }
            byte by = 0;
            try {
                by = message.reader().readByte();
                byArray = new byte[by];
                for (n2 = 0; n2 < by; ++n2) {
                    byArray[n2] = message.reader().readByte();
                }
            }
            catch (Exception exception) {
                byArray = null;
            }
            if (byArray != null) {
                MountTemplate.Arr_Fly = byArray;
                by = 0;
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void listChar(Message message) {
        try {
            int n;
            LoginScreen.saveUser_Pass();
            LoginScreen.saveIndexServer();
            GameScreen.player = new Player(0, 0, "unname", 0, 0);
            Item.VecInvetoryPlayer.removeAllElements();
            Item.VecChestPlayer.removeAllElements();
            SelectCharScreen.VecSelectChar.removeAllElements();
            int n2 = message.reader().readByte();
            mVector mVector2 = new mVector("ReadMessenge vList");
            int n3 = n = (GameCanvas.w - 150) / 4;
            for (int i = 0; i < n2; ++i) {
                byte by;
                int n4;
                int n5 = GameCanvas.hh - 25 - 20 * ((i + 1) % 2);
                int n6 = message.reader().readInt();
                String string = message.reader().readUTF();
                byte by2 = message.reader().readByte();
                byte by3 = message.reader().readByte();
                byte by4 = message.reader().readByte();
                byte by5 = message.reader().readByte();
                byte[] byArray = new byte[12];
                for (n4 = 0; n4 < byArray.length; ++n4) {
                    byArray[n4] = -1;
                }
                for (n4 = 0; n4 < by5; ++n4) {
                    byte by6;
                    by = message.reader().readByte();
                    byArray[by] = by6 = message.reader().readByte();
                }
                n4 = message.reader().readShort();
                by = message.reader().readByte();
                message.reader().readByte();
                message.reader().readByte();
                Other_Players other_Players = new Other_Players(n6, 0, string, 0, 0);
                short s = message.reader().readShort();
                if (s >= 0) {
                    other_Players.myClan = new MainClan(0, s, message.reader().readUTF(), message.reader().readByte());
                }
                other_Players.clazz = by;
                other_Players.setInfo(by2, by4, by3);
                other_Players.setWearingListChar(byArray);
                other_Players.Lv = (short)n4;
                other_Players.x = n3 + 25;
                other_Players.y = n5 + 50;
                n3 += 50 + n;
                mVector2.addElement(other_Players);
            }
            GameCanvas.selectChar.setPosPaint(mVector2);
            if (n2 == 0) {
                GameCanvas.createChar = new CreateChar();
                GameCanvas.createChar.Show((byte)0);
            } else {
                GameCanvas.selectChar.Show();
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void npcBig(Message message) {
        try {
            int n = message.reader().readByte();
            for (int i = 0; i < n; ++i) {
                String string = message.reader().readUTF();
                String string2 = message.reader().readUTF();
                byte by = message.reader().readByte();
                byte by2 = message.reader().readByte();
                short s = message.reader().readShort();
                short s2 = message.reader().readShort();
                byte by3 = message.reader().readByte();
                byte by4 = message.reader().readByte();
                byte by5 = message.reader().readByte();
                MainObject mainObject = MainObject.get_Object(by, (byte)2);
                boolean bl = true;
                if (mainObject == null) {
                    mainObject = new NPC(string, string2, by, by2, s, s2, by3, by4, by5);
                } else {
                    bl = false;
                    mainObject.name = string;
                    mainObject.nameGiaotiep = string2;
                    mainObject.imageId = by2;
                    mainObject.x = s;
                    mainObject.y = s2;
                }
                mainObject.isRemove = false;
                mainObject.IdBigAvatar = message.reader().readByte() + 500;
                mainObject.infoObject = message.reader().readUTF();
                mainObject.isPerson = message.reader().readByte();
                mainObject.isShowHP = message.reader().readByte() == 1;
                mainObject.typeObject = (byte)2;
                GameScreen.addPlayer(mainObject);
                MiniMap.addNPCMini(new NPCMini(by, s, s2));
                if (by != -52) continue;
                Mount mount = new Mount(0, 2, s + 48, s2 + 10);
                if (!GameScreen.Vecplayers.contains(mount)) {
                    GameScreen.addPlayer(mount);
                }
                mount = null;
                mount = new Mount(2, 0, s + 43, s2 - 4);
                if (!GameScreen.Vecplayers.contains(mount)) {
                    GameScreen.addPlayer(mount);
                }
                mount = null;
                mount = new Mount(3, 3, s - 18, s2 + 7);
                if (GameScreen.Vecplayers.contains(mount)) continue;
                GameScreen.addPlayer(mount);
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public void onReceiveInfoQuest(Message message) {
        try {
            int n;
            short s;
            int n2;
            byte by = message.reader().readByte();
            if (by < 3) {
                n2 = message.reader().readByte();
                s = 0;
                n = 0;
                byte by2 = 0;
                byte by3 = 0;
                String string = "";
                MainQuest mainQuest = null;
                if (by == 0) {
                    MainQuest.vecQuestList.removeAllElements();
                } else if (by == 1) {
                    MainQuest.vecQuestFinish.removeAllElements();
                } else if (by == 2) {
                    MainQuest.vecQuestDoing_Main.removeAllElements();
                    MainQuest.vecQuestDoing_Sub.removeAllElements();
                }
                block7: for (int n3 = 0; n3 < n2; n3 = (int)((byte)(n3 + 1))) {
                    s = message.reader().readShort();
                    n = message.reader().readBoolean();
                    string = message.reader().readUTF();
                    switch (by) {
                        case 0: {
                            byte by4 = message.reader().readByte();
                            String string2 = message.reader().readUTF();
                            by2 = message.reader().readByte();
                            String string3 = message.reader().readUTF();
                            mainQuest = new MainQuest(s, n != 0, string, by4, string2, by2, string3);
                            MainQuest.vecQuestList.addElement(mainQuest);
                            continue block7;
                        }
                        case 1: {
                            by3 = message.reader().readByte();
                            String string4 = message.reader().readUTF();
                            String string5 = message.reader().readUTF();
                            mainQuest = new MainQuest((int)s, n != 0, string, by3, string4, string5);
                            MainQuest.vecQuestFinish.addElement(mainQuest);
                            continue block7;
                        }
                        case 2: {
                            byte by5;
                            short[] sArray;
                            short[] sArray2;
                            short[] sArray3;
                            by2 = message.reader().readByte();
                            String string6 = message.reader().readUTF();
                            String string7 = message.reader().readUTF();
                            by3 = message.reader().readByte();
                            if (by2 == 0) {
                                byte by6 = message.reader().readByte();
                                sArray3 = new short[by6];
                                sArray2 = new short[by6];
                                sArray = new short[by6];
                                for (by5 = 0; by5 < by6; by5 = (byte)(by5 + 1)) {
                                    sArray3[by5] = message.reader().readShort();
                                    sArray[by5] = message.reader().readShort();
                                    sArray2[by5] = message.reader().readShort();
                                }
                                mainQuest = new MainQuest(s, n != 0, string, string7, by2, string6, by3, sArray3, sArray2, sArray, 0);
                            } else if (by2 == 1) {
                                byte by7 = message.reader().readByte();
                                sArray3 = new short[by7];
                                sArray2 = new short[by7];
                                sArray = new short[by7];
                                for (by5 = 0; by5 < by7; by5 = (byte)(by5 + 1)) {
                                    sArray3[by5] = message.reader().readShort();
                                    sArray[by5] = message.reader().readShort();
                                    sArray2[by5] = message.reader().readShort();
                                }
                                mainQuest = new MainQuest(s, n != 0, string, string7, by2, string6, by3, sArray3, sArray2, sArray, 1);
                            } else if (by2 == 2) {
                                by3 = message.reader().readByte();
                                String string8 = message.reader().readUTF();
                                mainQuest = new MainQuest((int)s, n != 0, string, string7, by3, string8);
                            }
                            if (mainQuest == null) continue block7;
                            if (n) {
                                MainQuest.vecQuestDoing_Main.addElement(mainQuest);
                                continue block7;
                            }
                            MainQuest.vecQuestDoing_Sub.addElement(mainQuest);
                        }
                    }
                }
                if (by != 0 && (by == 1 || by == 2)) {
                    ((MainTabNew)GameCanvas.AllInfo.VecTabScreen.elementAt(4)).init();
                }
            } else {
                n2 = message.reader().readByte();
                s = message.reader().readByte();
            }
            for (n2 = 0; n2 < GameScreen.Vecplayers.size(); ++n2) {
                MainObject mainObject = (MainObject)GameScreen.Vecplayers.elementAt(n2);
                if (mainObject.typeObject != 2) continue;
                if (by == 0) {
                    if (mainObject.typeNPC == 1) {
                        mainObject.typeNPC = 0;
                    }
                } else if (by == 1) {
                    if (mainObject.typeNPC == 3) {
                        mainObject.typeNPC = 0;
                    }
                } else if (by == 2 && mainObject.typeNPC == 2) {
                    mainObject.typeNPC = 0;
                }
                for (n = 0; n < MainQuest.vecQuestList.size(); n += 1) {
                    MainQuest mainQuest = (MainQuest)MainQuest.vecQuestList.elementAt(n);
                    if (mainQuest.idNPC_From != mainObject.ID || mainObject.typeNPC == 3) continue;
                    mainObject.typeNPC = 1;
                }
                for (n = 0; n < MainQuest.vecQuestFinish.size(); n += 1) {
                    MainQuest mainQuest = (MainQuest)MainQuest.vecQuestFinish.elementAt(n);
                    if (mainQuest.idNPC_To != mainObject.ID) continue;
                    mainObject.typeNPC = 3;
                }
                for (n = 0; n < MainQuest.vecQuestDoing_Main.size(); n += 1) {
                    MainQuest mainQuest = (MainQuest)MainQuest.vecQuestDoing_Main.elementAt(n);
                    if (mainQuest.idNPC_To != mainObject.ID || mainObject.typeNPC != 0) continue;
                    mainObject.typeNPC = 2;
                }
                for (n = 0; n < MainQuest.vecQuestDoing_Sub.size(); n += 1) {
                    MainQuest mainQuest = (MainQuest)MainQuest.vecQuestDoing_Sub.elementAt(n);
                    if (mainQuest.idNPC_To != mainObject.ID || mainObject.typeNPC != 0) continue;
                    mainObject.typeNPC = 2;
                }
            }
            for (n2 = 0; n2 < MiniMap.vecNPC_Map.size(); ++n2) {
                MainObject mainObject;
                NPCMini nPCMini = (NPCMini)MiniMap.vecNPC_Map.elementAt(n2);
                if (by == 0) {
                    if (nPCMini.type == 1) {
                        nPCMini.type = 0;
                    }
                } else if (by == 1) {
                    if (nPCMini.type == 3) {
                        nPCMini.type = 0;
                    }
                } else if (by == 2 && nPCMini.type == 2) {
                    nPCMini.type = 0;
                }
                if ((mainObject = MainObject.get_Object(nPCMini.ID, (byte)2)) == null || mainObject.isRemove) continue;
                nPCMini.type = mainObject.typeNPC;
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public void Dynamic_Menu(Message message) {
        try {
            short s = message.reader().readShort();
            byte by = message.reader().readByte();
            int n = message.reader().readByte();
            mVector mVector2 = new mVector("ReadMessenge cmd");
            for (int i = 0; i < n; ++i) {
                iCommand iCommand2 = new iCommand(message.reader().readUTF(), null);
                mVector2.addElement(iCommand2);
            }
            String string = message.reader().readUTF();
            GameCanvas.menu2.setinfoDynamic(mVector2, 2, by, s, string);
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public void Item_More_Info(Message message) {
        try {
            int n = message.reader().readByte();
            TabMySeftNew.mItemInfo = new MainInfoItem[n];
            TabMySeftNew.mItemInfoShow = new MainInfoItem[n];
            for (int i = 0; i < n; ++i) {
                int n2 = message.reader().readUnsignedByte();
                int n3 = message.reader().readInt();
                MainInfoItem mainInfoItem = new MainInfoItem(n2, n3);
                if (mainInfoItem.id >= 16 && mainInfoItem.id <= 20) {
                    TabMySeftNew.meffskill[mainInfoItem.id - 16] = MainItem.getNopercent(mainInfoItem.value);
                }
                TabMySeftNew.mItemInfo[i] = mainInfoItem;
                TabMySeftNew.mItemInfoShow[i] = mainInfoItem;
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public void Set_XP(Message message) {
        try {
            short s = message.reader().readShort();
            MainObject mainObject = MainObject.get_Object(s, (byte)0);
            if (mainObject != null && !mainObject.isRemove) {
                mainObject.phantramLv = message.reader().readShort();
                int n = message.reader().readInt();
                if (!GameCanvas.lowGraphic || mainObject == GameScreen.player) {
                    GameScreen.addEffectNum(n + "xp", mainObject.x, mainObject.y - mainObject.hOne, 1, s);
                }
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public void Level_Up(Message message) {
        try {
            short s = message.reader().readShort();
            MainObject mainObject = MainObject.get_Object(s, (byte)0);
            if (mainObject != null && !mainObject.isRemove) {
                mainObject.Lv = message.reader().readByte();
                GameScreen.addEffectKill(42, s, (byte)0, s, (byte)0, 0, 0);
                if (mainObject == GameScreen.player && GameScreen.help.setStep_Next(5, 8)) {
                    GameScreen.help.NextStep();
                    GameScreen.help.setNext();
                    if (Player.isAutoFire == -1) {
                        Player.isAutoFire = 0;
                    }
                }
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public void Login_Ok(Message message) {
        try {
            short s = message.reader().readShort();
            byte[] byArray = new byte[s];
            message.reader().read(byArray);
            LoadMap.load_Table_Map(byArray);
            GameScreen.vecTimecountDown.removeAllElements();
            PaintInfoGameScreen.idicon = -1;
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public void ItemDrop(Message message) {
        try {
            byte by = message.reader().readByte();
            short s = message.reader().readShort();
            short s2 = message.reader().readShort();
            short s3 = message.reader().readShort();
            String string = message.reader().readUTF();
            byte by2 = 0;
            if (by == 3 || by == 4 || by == 7) {
                by2 = message.reader().readByte();
            }
            short s4 = message.reader().readShort();
            MainObject mainObject = MainObject.get_Object(s, (byte)1);
            if (mainObject != null && !mainObject.isRemove) {
                int n = mainObject.x;
                int n2 = mainObject.y;
                Item_Drop item_Drop = new Item_Drop(s3, by, string, n, n2, s2, by2);
                GameScreen.addPlayer(item_Drop);
            } else {
                MainObject mainObject2 = MainObject.get_Object(s4, (byte)0);
                if (mainObject2 != null) {
                    int n = mainObject2.x;
                    int n3 = mainObject2.y;
                    Item_Drop item_Drop = new Item_Drop(s3, by, string, n, n3, s2, by2);
                    GameScreen.addPlayer(item_Drop);
                    return;
                }
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public void GetItemMap(Message message) {
        try {
            MainObject mainObject;
            byte by = message.reader().readByte();
            short s = message.reader().readShort();
            short s2 = message.reader().readShort();
            MainObject mainObject2 = MainObject.get_Item_Object(s, by);
            if (mainObject2 == null || mainObject2.isRemove) {
                return;
            }
            if (by == 5 && s2 == GameScreen.player.ID) {
                MainQuest.updateQuestGetItem(mainObject2.imageId, mainObject2.name);
            }
            if ((mainObject = MainObject.get_Object(s2, (byte)0)) != null && !mainObject.isRemove) {
                if (!GameCanvas.lowGraphic || mainObject2 == GameScreen.player) {
                    if (mainObject2.typeObject == 3 && mainObject2.colorName > 0) {
                        GameScreen.addEffectNum(mainObject2.name, mainObject.x, mainObject.y - mainObject.hOne / 2, -mainObject2.colorName, s2);
                    } else {
                        GameScreen.addEffectNum(mainObject2.name, mainObject.x, mainObject.y - mainObject.hOne / 2, 5, s2);
                    }
                }
                mainObject2.setVx_Vy_Item(mainObject.x, mainObject.y - mainObject.hOne / 2);
                mainObject2.isRunAttack = true;
            } else {
                mainObject2.isRemove = true;
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public void get_Item_Tem(Message message) {
        try {
            short s = message.reader().readShort();
            String string = message.reader().readUTF();
            byte by = message.reader().readByte();
            byte by2 = message.reader().readByte();
            byte by3 = message.reader().readByte();
            short s2 = message.reader().readShort();
            int n = message.reader().readByte();
            int[] nArray = new int[n];
            byte[] byArray = new byte[n];
            for (int i = 0; i < n; ++i) {
                byArray[i] = message.reader().readByte();
                nArray[i] = message.reader().readInt();
            }
            MainTemplateItem mainTemplateItem = new MainTemplateItem(s, string, by, by2, by3, s2, nArray, byArray);
            MainTemplateItem.hashItemTem.put("" + s, mainTemplateItem);
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public void Skill_List(Message message) {
        try {
            int n;
            int n2;
            MsgDialog.MaxSkillBuff = 0;
            mVector mVector2 = new mVector("ReadMessenge vec14");
            int n3 = message.reader().readByte();
            for (n2 = 0; n2 < n3; ++n2) {
                Skill skill = new Skill();
                skill.Id = message.reader().readByte();
                skill.iconId = message.reader().readByte();
                skill.name = message.reader().readUTF();
                skill.typeSkill = message.reader().readByte();
                if (skill.typeSkill == 1) {
                    MsgDialog.MaxSkillBuff = (byte)(MsgDialog.MaxSkillBuff + 1);
                }
                skill.range = message.reader().readShort();
                skill.detail = message.reader().readUTF();
                skill.typeBuff = message.reader().readByte();
                skill.subEff = message.reader().readByte();
                n = message.reader().readByte();
                skill.mLvSkill = new LvSkill[n];
                for (int i = 0; i < n; ++i) {
                    skill.mLvSkill[i] = new LvSkill();
                    skill.mLvSkill[i].mpLost = message.reader().readShort();
                    skill.mLvSkill[i].LvRe = message.reader().readShort();
                    if (i == 0) {
                        skill.lvMin = skill.mLvSkill[i].LvRe;
                    }
                    skill.mLvSkill[i].delay = message.reader().readInt();
                    skill.mLvSkill[i].timeBuff = message.reader().readInt();
                    skill.mLvSkill[i].per_Sub_Eff = message.reader().readByte();
                    skill.mLvSkill[i].time_Sub_Eff = message.reader().readShort();
                    skill.mLvSkill[i].plus_Hp = message.reader().readShort();
                    skill.mLvSkill[i].plus_Mp = message.reader().readShort();
                    int n4 = message.reader().readByte();
                    skill.mLvSkill[i].minfo = new MainInfoItem[n4];
                    for (int j = 0; j < n4; ++j) {
                        skill.mLvSkill[i].minfo[j] = new MainInfoItem(message.reader().readUnsignedByte(), message.reader().readInt());
                    }
                    skill.mLvSkill[i].nTarget = message.reader().readByte();
                    skill.mLvSkill[i].range_lan = message.reader().readShort();
                }
                skill.performDur = message.reader().readShort();
                skill.typePaint = message.reader().readByte();
                mVector2.addElement(skill);
            }
            mVector2 = CRes.selectionSortIDSkill(mVector2);
            TabSkillsNew.vecPaintSkill = CRes.selectionSortSkill(mVector2);
            MainListSkill.setKill(GameScreen.player.clazz);
            GameScreen.player.tabskills.setXYSkill(TabSkillsNew.vecPaintSkill);
            GameCanvas.load.isLoadSkill = true;
            MsgDialog.Autobuff = mSystem.new_M_Int(MsgDialog.MaxSkillBuff, 3);
            n2 = 0;
            for (n = 0; n < TabSkillsNew.vecPaintSkill.size(); ++n) {
                Skill skill = (Skill)TabSkillsNew.vecPaintSkill.elementAt(n);
                if (skill.typeSkill != 1) continue;
                MsgDialog.Autobuff[n2][0] = skill.Id;
                MsgDialog.Autobuff[n2][1] = 0;
                MsgDialog.Autobuff[n2][2] = n;
                ++n2;
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public void use_Potion(Message message) {
        try {
            byte by;
            TabScreenNew.timeRepaint = 5;
            byte by2 = message.reader().readByte();
            short s = message.reader().readShort();
            short s2 = message.reader().readShort();
            Item item = null;
            MainObject mainObject = MainObject.get_Object(s, by2);
            if (mainObject == null || mainObject.isRemove) {
                return;
            }
            if (s2 != -1 && mainObject == GameScreen.player && (item = Item.getItemInventory(4, s2)) != null) {
                --item.numPotion;
                if (item.numPotion <= 0) {
                    Item.VecInvetoryPlayer.removeElement(item);
                }
            }
            if ((by = message.reader().readByte()) == 0) {
                mainObject.maxHp = message.reader().readInt();
                mainObject.hp = message.reader().readInt();
                int n = message.reader().readInt();
                if (mainObject.hp > 0) {
                    if (mainObject.Action == 4) {
                        mainObject.resetAction();
                        mainObject.Action = 0;
                        mainObject.vecBuff.removeAllElements();
                        if (mainObject == GameScreen.player) {
                            GlobalService.gI().player_move((short)GameScreen.player.x, (short)GameScreen.player.y);
                        }
                    }
                } else {
                    mainObject.hp = 0;
                    mainObject.mp = 0;
                    if (mainObject.Action != 4) {
                        if (by2 == 1) {
                            MainObject.startDeadFly((MainMonster)mainObject, GameScreen.player.ID, CRes.random(3));
                        } else {
                            mainObject.resetAction();
                            mainObject.Action = 4;
                            mainObject.timedie = GameCanvas.timeNow;
                            mainObject.f = 0;
                            GameScreen.addEffectEndKill(11, mainObject.x, mainObject.y);
                        }
                    }
                }
                if (!GameCanvas.lowGraphic || mainObject == GameScreen.player) {
                    if (n > 0) {
                        GameScreen.addEffectNum("+" + n, mainObject.x, mainObject.y - mainObject.hOne / 2, 3, s);
                    } else if (n < 0) {
                        GameScreen.addEffectNum("" + n, mainObject.x, mainObject.y - mainObject.hOne / 2, 3, s);
                    }
                }
            } else if (by == 1) {
                mainObject.maxMp = message.reader().readInt();
                mainObject.mp = message.reader().readInt();
                int n = message.reader().readInt();
                if (!GameCanvas.lowGraphic || mainObject == GameScreen.player) {
                    if (n > 0) {
                        GameScreen.addEffectNum("+" + n, mainObject.x, mainObject.y - mainObject.hOne / 2, 4, s);
                    } else if (n < 0) {
                        GameScreen.addEffectNum("" + n, mainObject.x, mainObject.y - mainObject.hOne / 2, 3, s);
                    }
                }
            } else if (by == 2) {
                mainObject.maxHp = message.reader().readInt();
                mainObject.hp = message.reader().readInt();
                int n = message.reader().readInt();
                if (!GameCanvas.lowGraphic) {
                    if (n > 0) {
                        mSystem.outz("dzo day ---");
                        GameScreen.addEffectNum("+" + n, mainObject.x, mainObject.y - mainObject.hOne / 2, 3, s);
                    } else if (n < 0) {
                        mSystem.outz("hoac dzo day ---");
                        GameScreen.addEffectNum("" + n, mainObject.x, mainObject.y - mainObject.hOne / 2, 6, s);
                    }
                }
            } else if (by == 3) {
                mainObject.hp = message.reader().readInt();
                int n = message.reader().readInt();
                GameScreen.addEffectNum("" + n, mainObject.x, mainObject.y - mainObject.hOne / 2, 9, s);
                GameScreen.addEffectEndKill(0, mainObject.x, mainObject.y + mainObject.vy * 2 / 1000 - 5);
                GameScreen.addEffectEndKill(9, mainObject.x, mainObject.y + mainObject.vy * 2 / 1000 + 5);
                GameScreen.addEffectEndKill(4, mainObject.x, mainObject.y);
            }
            if (Player.party != null) {
                Player.party.setPos(mainObject.name, mainObject.x, mainObject.y, mainObject.hp, mainObject.maxHp);
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public void chatPopup(Message message) {
        try {
            short s = message.reader().readShort();
            byte by = message.reader().readByte();
            MainObject mainObject = MainObject.get_Object(s, by);
            if (mainObject != null && !mainObject.isRemove) {
                String string = message.reader().readUTF();
                if (string == null || string.trim().length() == 0) {
                    return;
                }
                mainObject.strChatPopup = string;
                GameCanvas.msgchat.addNewChat(T.tinden, mainObject.name + ": ", string, (byte)1, false);
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public void chatTab(Message message) {
        try {
            String string = message.reader().readUTF();
            String string2 = message.reader().readUTF();
            if (string2 == null || string2.trim().length() == 0) {
                return;
            }
            if (string.compareTo(T.tinden) == 0) {
                GameCanvas.msgchat.addNewChat(string, T.thongbao + ": ", string2, (byte)1, false);
            } else if (string.compareTo(T.tabThuLinh) == 0) {
                GameCanvas.msgchat.addNewChat(string, "", string2, (byte)1, false);
            } else if (string.compareTo(T.tabBangHoi) == 0) {
                GameCanvas.msgchat.addNewChat(string, "", string2, (byte)0, false);
            } else if (string.compareTo(T.party) == 0) {
                GameCanvas.msgchat.addNewChat(string, "", string2, (byte)0, false);
            } else {
                GameCanvas.msgchat.addNewChat(string, string + ": ", string2, (byte)0, false);
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public void Friend(Message message) {
        try {
            mSystem.outz("friend list");
            String string = T.listFriend;
            byte by = message.reader().readByte();
            if (by == 0) {
                String string2 = message.reader().readUTF();
                GameCanvas.mevent.addEvent(string2, (byte)1, T.loimoikb, 0);
            } else if (by == 4 || by == 1) {
                int n = -1;
                if (by == 4) {
                    string = message.reader().readUTF();
                    n = message.reader().readByte();
                }
                int n2 = 1;
                if (by == 4) {
                    n2 = message.reader().readUnsignedByte();
                }
                mSystem.outz("friend sizelist= " + n2 + " type=" + by);
                mVector mVector2 = new mVector("ReadMessenge vec6");
                for (int i = 0; i < n2; ++i) {
                    short s;
                    int n3;
                    mSystem.outz("friend list i=" + i);
                    String string3 = message.reader().readUTF();
                    mSystem.outz("friendname list i=" + string3);
                    byte by2 = message.reader().readByte();
                    byte by3 = message.reader().readByte();
                    byte by4 = message.reader().readByte();
                    mSystem.outz("friendhair list i=" + by4);
                    short s2 = message.reader().readShort();
                    byte by5 = message.reader().readByte();
                    mSystem.outz("frienditemsize list i=" + by5);
                    byte[] byArray = new byte[12];
                    mSystem.outz("friend11 list i=" + i);
                    for (n3 = 0; n3 < byArray.length; ++n3) {
                        byArray[n3] = -1;
                    }
                    for (n3 = 0; n3 < by5; ++n3) {
                        byte by6 = message.reader().readByte();
                        s = message.reader().readByte();
                        byArray[s] = by6;
                    }
                    mSystem.outz("friend22 list i=" + i);
                    n3 = message.reader().readByte();
                    MainObject mainObject = new MainObject(0, 0, string3, 0, 0);
                    s = message.reader().readShort();
                    if (s >= 0) {
                        mainObject.myClan = new MainClan(0, s, message.reader().readUTF(), message.reader().readByte());
                    }
                    mSystem.outz("friend33 list i=" + i);
                    mainObject.setInfo(by2, by3, by4);
                    mainObject.typeOnline = (byte)n3;
                    mainObject.Lv = s2;
                    mainObject.setWearingListChar(byArray);
                    mainObject.infoObject = T.level + s2;
                    mSystem.outz("friend44 list i=" + i);
                    if (by == 1) {
                        GameCanvas.addInfoChar(mainObject.name + T.chapnhanketban);
                        List_Server.vecMyFriend.addElement(mainObject);
                        if (List_Server.gI().typeList == 0) {
                            List_Server.gI().setMinMax();
                            List_Server.gI().setXmove();
                            List_Server.gI().setCmd();
                        }
                    } else {
                        mVector2.addElement(mainObject);
                    }
                    mSystem.outz("ok i roi i=" + i);
                }
                if (by == 4) {
                    List_Server.vecMyFriend.removeAllElements();
                    List_Server.gI().typeList = 0;
                    List_Server.vecMyFriend = mVector2;
                    List_Server.gI().vecListServer = mVector2;
                    List_Server.gI().setSize((byte)0);
                    List_Server.gI().updateList();
                    List_Server.gI().setMinMax();
                    List_Server.gI().setXmove();
                    List_Server.gI().setCmd();
                    List_Server.gI().nameList = string;
                    List_Server.gI().page = (byte)99;
                    List_Server.isLoadFriend = true;
                    if (GameCanvas.currentScreen != List_Server.gI()) {
                        if (GameCanvas.currentScreen.lastScreen != List_Server.gI()) {
                            List_Server.gI().Show(GameCanvas.currentScreen);
                        } else {
                            List_Server.gI().Show(GameCanvas.game);
                        }
                    } else {
                        GameCanvas.end_Dialog();
                    }
                    mSystem.outz("ok load");
                }
                GameCanvas.end_Dialog();
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public void lastlogout(Message message) {
        try {
            byte by = message.reader().readByte();
            GameCanvas.timenextLogin = mSystem.currentTimeMillis() + (long)(by * 1000);
            GameScreen.player.resetAction();
            Session_ME.gI().close();
            GameScreen.player = new Player(0, 0, "unname", 0, 0);
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public void Login_Fail(Message message) {
        try {
            String string = message.reader().readUTF();
            GameCanvas.start_Ok_Dialog(string);
            byte by = 0;
            try {
                by = message.reader().readByte();
            }
            catch (Exception exception) {
                by = 0;
            }
            if (by == 0) {
                GlobalLogicHandler.isMelogin = false;
            }
            if (by == 1) {
                GlobalLogicHandler.isMelogin = true;
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public void Register(Message message) {
        try {
            boolean bl = message.reader().readBoolean();
            String string = message.reader().readUTF();
            if (bl) {
                GameCanvas.login.section = 0;
                GameCanvas.login.setScreen();
            }
            GameCanvas.start_Ok_Dialog(string);
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public void Party(Message message) {
        try {
            byte by = message.reader().readByte();
            if (by == 0) {
                Player.party = new MainParty(GameScreen.player.name, GameScreen.player.Lv, GameCanvas.loadmap.idMap, LoadMap.getAreaPaint());
                GameCanvas.start_Ok_Dialog(T.vuataonhom);
            } else if (by == 1) {
                this.nameParty = message.reader().readUTF();
                GameCanvas.mevent.addEvent(this.nameParty, (byte)2, T.moivaoParty, 0);
            } else if (by == 2) {
                MainParty mainParty = null;
                int n = message.reader().readByte();
                for (int i = 0; i < n; ++i) {
                    if (i == 0) {
                        mainParty = new MainParty(message.reader().readUTF(), message.reader().readShort(), message.reader().readUnsignedByte(), message.reader().readByte());
                        continue;
                    }
                    mainParty.addObjParty(message.reader().readUTF(), message.reader().readShort(), message.reader().readUnsignedByte(), message.reader().readByte());
                }
                Player.party = mainParty;
                MsgDialog.maxSizeParty = -1;
            } else if (by == 3) {
                GameCanvas.start_Ok_Dialog(T.moikhoinhom);
                Player.party = null;
            } else if (by == 4) {
                GameCanvas.start_Ok_Dialog(T.giaitannhom);
                Player.party = null;
            } else if (by == 5) {
                GameCanvas.start_Ok_Dialog(T.roikhoinhom);
                Player.party = null;
            }
            if (Player.party != null) {
                Player.party.setIsParty();
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public void Buy_Sell(Message message) {
        try {
            byte by = message.reader().readByte();
            if (by == 0) {
                this.nameBuySell = message.reader().readUTF();
                GameCanvas.mevent.addEvent(this.nameBuySell, (byte)3, T.loimoigiaodich, 0);
            } else if (by == 1) {
                String string = message.reader().readUTF();
                GameCanvas.buy_sell = new Buy_Sell_Screen();
                GameCanvas.buy_sell.setinfoBuySell(string);
                GameCanvas.buy_sell.Show(GameCanvas.currentScreen);
            } else if (by == 2 || by == 3) {
                Object object;
                byte by2 = message.reader().readByte();
                byte by3 = message.reader().readByte();
                short s = message.reader().readShort();
                short s2 = message.reader().readShort();
                short s3 = 1;
                byte by4 = 0;
                byte by5 = 0;
                short s4 = 0;
                byte by6 = 0;
                String string = "";
                String string2 = null;
                MainInfoItem[] mainInfoItemArray = null;
                if (by3 == 4) {
                    s3 = message.reader().readShort();
                    object = (MainTemplateItem)MainTemplateItem.hashPotionTem.get("" + s);
                    string2 = ((MainTemplateItem)object).contentPotion;
                    string = ((MainTemplateItem)object).name;
                } else if (by3 == 3) {
                    string = message.reader().readUTF();
                    by4 = message.reader().readByte();
                    by5 = message.reader().readByte();
                    s4 = message.reader().readShort();
                    by6 = message.reader().readByte();
                    int n = message.reader().readByte();
                    mainInfoItemArray = new MainInfoItem[n];
                    for (int i = 0; i < n; ++i) {
                        int n2 = message.reader().readUnsignedByte();
                        int n3 = message.reader().readInt();
                        mainInfoItemArray[i] = new MainInfoItem(n2, n3);
                    }
                } else if (by3 == 7) {
                    s3 = message.reader().readShort();
                    MainItem mainItem = Item.getMaterial(s);
                    if (mainItem != null) {
                        string2 = mainItem.content;
                        string = mainItem.itemName;
                    } else {
                        string2 = "";
                        string = "";
                    }
                }
                object = new MainItem(s, string, s3, s2, by3, string2, mainInfoItemArray, by4, by5, s4, by6, 0, 0);
                Buy_Sell_Screen.setmItemOther((MainItem)object, by, by2);
            } else if (by == 4) {
                GameCanvas.buy_sell.typeLock = message.reader().readByte();
                if (GameCanvas.buy_sell.typeLock == 1) {
                    GameCanvas.buy_sell.center = null;
                    GameCanvas.buy_sell.left = null;
                }
                if (GameCanvas.buy_sell.typeLock == 2) {
                    GameCanvas.buy_sell.center = null;
                    GameCanvas.buy_sell.left = GameCanvas.buy_sell.cmdGiaodich;
                    GameCanvas.buy_sell.setPosCmd();
                }
            } else if (by == 5) {
                byte by7 = message.reader().readByte();
                if (by7 == 0) {
                    GameCanvas.buy_sell.left = null;
                    GameCanvas.buy_sell.right = null;
                    GameCanvas.buy_sell.center = new iCommand(T.cancel, 8);
                    GameCanvas.buy_sell.setPosCmd();
                    GameCanvas.buy_sell.typeActionBuySell = 0;
                    GameCanvas.start_Ok_Dialog(T.xinchogiaodich + GameCanvas.buy_sell.nameBuy + T.xacnhangiaodich);
                } else if (by7 == 1) {
                    GameCanvas.buy_sell.typeActionBuySell = 1;
                } else if (by7 == 2) {
                    if (GameCanvas.buy_sell.lastScreen.lastScreen != null) {
                        GameCanvas.buy_sell.lastScreen.Show(GameCanvas.buy_sell.lastScreen.lastScreen);
                    } else {
                        GameCanvas.buy_sell.lastScreen.Show();
                    }
                    GameCanvas.buy_sell = null;
                    GameCanvas.start_Ok_Dialog(T.giaodichthanhcong);
                }
            } else if (by == 6) {
                if (GameCanvas.buy_sell.lastScreen.lastScreen != null) {
                    GameCanvas.buy_sell.lastScreen.Show(GameCanvas.buy_sell.lastScreen.lastScreen);
                } else {
                    GameCanvas.buy_sell.lastScreen.Show();
                }
                GameCanvas.buy_sell = null;
                GameCanvas.start_Ok_Dialog(T.huygiaodich);
            } else if (by == 7) {
                GameCanvas.buy_sell.moneyBuySell[1] = message.reader().readInt();
            } else if (by == 8) {
                GameCanvas.buy_sell.moneyBuySell[0] = message.reader().readInt();
            } else if (by == 9) {
                String string;
                GameCanvas.buy_sell.strchat[1] = string = message.reader().readUTF();
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public void Buff(Message message) {
        try {
            MainInfoItem[] mainInfoItemArray;
            Object object;
            byte by = message.reader().readByte();
            byte by2 = message.reader().readByte();
            short s = message.reader().readShort();
            byte by3 = message.reader().readByte();
            int n = message.reader().readInt();
            short s2 = message.reader().readShort();
            byte by4 = message.reader().readByte();
            MainObject mainObject = null;
            mainObject = by2 == 6 ? MainObject.get_Object(s, (byte)9) : (by2 == 4 ? MainObject.get_Object(s, (byte)1) : MainObject.get_Object(s, (byte)0));
            MainObject mainObject2 = MainObject.get_Object(s2, by4);
            if (mainObject != null && !mainObject.isRemove && mainObject != GameScreen.player && by == 0 && mainObject.Action != 4 && mainObject.Action != 2) {
                if (mainObject2 != null) {
                    if (by2 == 4 && mainObject2 == mainObject && !mainObject2.isRemove) {
                        GameScreen.addEffectKillSubTime(MainListSkill.mSkillAllClasses[by2][by3], mainObject.ID, mainObject.typeObject, mainObject.ID, mainObject.typeObject, 0, mainObject.hp, (byte)MainListSkill.mBuffAllClasses[by2][by3], 850);
                    }
                } else {
                    object = new mVector("ReadMessenge vec");
                    Object_Effect_Skill object_Effect_Skill = new Object_Effect_Skill((short)mainObject.ID, mainObject.typeObject);
                    ((mVector)object).addElement(object_Effect_Skill);
                    if (by2 == 6) {
                        mainInfoItemArray = (MainInfoItem[])mainObject;
                        mainInfoItemArray.setState((byte)2);
                    }
                    mainObject.ListKillNow.setFireSkill(mainObject, (mVector)object, by3, by2);
                }
            }
            if (mainObject2 != null && !mainObject2.isRemove) {
                mainObject2.addBuff(MainListSkill.mBuffAllClasses[by2][by3], n, 0);
                if (mainObject2 == GameScreen.player && (object = MainBuff.getBuff(MainListSkill.mBuffAllClasses[by2][by3], 0)) != null) {
                    ((MainBuff)object).idIcon = message.reader().readByte();
                    byte by5 = message.reader().readByte();
                    ((MainBuff)object).minfotam = new MainInfoItem[by5];
                    mainInfoItemArray = new MainInfoItem[by5];
                    for (int i = 0; i < mainInfoItemArray.length; ++i) {
                        mainInfoItemArray[i] = new MainInfoItem(message.reader().readUnsignedByte(), message.reader().readInt());
                    }
                    ((MainBuff)object).minfotam = mainInfoItemArray;
                }
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void firePK(Message message) {
        try {
            this.Fire_Object(message, (byte)0, (byte)0);
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void diePlayer(Message message) {
        try {
            short s = message.reader().readShort();
            short s2 = message.reader().readShort();
            short s3 = message.reader().readShort();
            byte by = message.reader().readByte();
            MainObject mainObject = MainObject.get_Object(s, (byte)0);
            MainObject mainObject2 = MainObject.get_Object(s2, by);
            if (mainObject2 != null && !mainObject2.isRemove && by == 0) {
                mainObject2.pointPk = s3;
            }
            if (mainObject == null || mainObject.isRemove || mainObject.Action == 4) {
                return;
            }
            mainObject.hp = 0;
            mainObject.resetAction();
            mainObject.Action = 4;
            mainObject.timedie = GameCanvas.timeNow;
            GameScreen.addEffectEndKill(11, mainObject.x, mainObject.y);
            if (GameScreen.player == mainObject && !GameScreen.infoGame.isMapChienTruong(GameCanvas.loadmap.idMap)) {
                if (LoadMap.typeMap == 1) {
                    GameScreen.gI().center = Player.cmdVeLang;
                    Player.cmdVeLang.setPos(GameCanvas.hw, GameCanvas.h - iCommand.hButtonCmd / 2 - 2, null, Player.cmdVeLang.caption);
                } else if (LoadMap.typeMap == 2) {
                    GameScreen.gI().center = Player.cmdRevice;
                    Player.cmdRevice.setPos(GameCanvas.hw, GameCanvas.h - iCommand.hButtonCmd / 2 - 2, null, Player.cmdRevice.caption);
                } else if (!GameScreen.infoGame.isMapThachdau()) {
                    GameScreen.gI().left = Player.cmdRevice;
                    GameScreen.gI().right = Player.cmdVeLang;
                    Player.cmdRevice.setPos(iCommand.wButtonCmd / 2, GameCanvas.h - iCommand.hButtonCmd / 2 - 2, null, Player.cmdRevice.caption);
                    Player.cmdVeLang.setPos(GameCanvas.w - iCommand.wButtonCmd / 2, GameCanvas.h - iCommand.hButtonCmd / 2 - 2, null, Player.cmdVeLang.caption);
                }
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void pk(Message message) {
        try {
            short s = message.reader().readShort();
            byte by = message.reader().readByte();
            MainObject mainObject = MainObject.get_Object(s, (byte)0);
            if (mainObject != null && !mainObject.isRemove) {
                mainObject.typePk = by;
                if (mainObject == GameScreen.player) {
                    GameScreen.player.pointPk = message.reader().readShort();
                    String string = T.off + " " + T.dosat;
                    if (GameScreen.player.typePk != 0) {
                        string = T.on + " " + T.dosat;
                    }
                    GameScreen.gI().cmdSetDoSat.caption = string;
                    if (by > 0) {
                        GlobalService.gI().Thach_Dau((byte)2, "");
                    }
                }
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public void Fire_Object(Message message, byte by, byte by2) {
        try {
            MainObject mainObject;
            int n;
            int n2;
            int n3;
            int n4;
            if (!LoadMapScreen.isNextMap) {
                return;
            }
            short s = message.reader().readShort();
            MainObject mainObject2 = MainObject.get_Object(s, by);
            if (mainObject2 == null || mainObject2.isRemove) {
                return;
            }
            byte by3 = message.reader().readByte();
            int n5 = message.reader().readByte();
            if (n5 <= 0) {
                return;
            }
            mVector mVector2 = new mVector("ReadMessenge vec5");
            for (n4 = 0; n4 < n5; ++n4) {
                int n6;
                int n7;
                n3 = message.reader().readShort();
                Object_Effect_Skill object_Effect_Skill = new Object_Effect_Skill((short)n3, by2);
                n2 = message.reader().readInt();
                n = message.reader().readInt();
                object_Effect_Skill.setHP(n2, n);
                if (n3 == GameScreen.player.ID) {
                    n7 = GameScreen.player.maxHp;
                    n6 = n2 / n7 * 100;
                    GameScreen.player.setPainthit((byte)4, n6 > 20);
                }
                n7 = message.reader().readByte();
                object_Effect_Skill.mEffTypePlus = new int[n7];
                object_Effect_Skill.mEff_HP_Plus = new int[n7];
                for (n6 = 0; n6 < n7; ++n6) {
                    object_Effect_Skill.mEffTypePlus[n6] = message.reader().readByte();
                    object_Effect_Skill.mEff_HP_Plus[n6] = message.reader().readInt();
                }
                mainObject = MainObject.get_Object(object_Effect_Skill.ID, by2);
                if (mainObject == null || mainObject.isRemove) continue;
                mVector2.addElement(object_Effect_Skill);
            }
            n4 = message.reader().readInt();
            n3 = message.reader().readInt();
            byte by4 = message.reader().readByte();
            n2 = message.reader().readInt();
            if (s == GameScreen.player.ID) {
                for (n = 0; n < mVector2.size(); ++n) {
                    int n8;
                    Object_Effect_Skill object_Effect_Skill = (Object_Effect_Skill)mVector2.elementAt(n);
                    mainObject = MainObject.get_Object(object_Effect_Skill.ID, by2);
                    if (mainObject == null || mainObject.isRemove) continue;
                    int n9 = 2;
                    if (mainObject2 != null && !mainObject2.isRemove && (n8 = EffectSkill.setAddEffKillPlus(object_Effect_Skill.mEffTypePlus, mainObject2, mainObject, object_Effect_Skill.mEff_HP_Plus)) != -1) {
                        n9 = n8;
                    }
                    if (mainObject.ID != GameScreen.player.ID) {
                        if (object_Effect_Skill.hpShow == 0) {
                            n9 = 6;
                            GameScreen.addEffectNum(T.hut, mainObject.x, mainObject.y - mainObject.hOne, n9, mainObject.ID);
                        } else {
                            GameScreen.addEffectNum("-" + object_Effect_Skill.hpShow, mainObject.x, mainObject.y - mainObject.hOne, n9, mainObject.ID);
                        }
                        if (n2 > 0) {
                            GameScreen.addEffectNum("-" + n2, mainObject.x, mainObject.y - mainObject.hOne, by4, mainObject.ID);
                        }
                    }
                    mainObject.hp = object_Effect_Skill.hpLast;
                    if (mainObject.typeObject != 1 || mainObject.hp > 0) continue;
                    MainObject.startDeadFly((MainMonster)mainObject, s, CRes.random(3));
                    if (mainObject.timeReveice < 0) continue;
                    MainQuest.updateQuestKillMonster(mainObject.ID);
                }
            } else if (mainObject2 != null && !mainObject2.isRemove && mVector2.size() > 0) {
                mainObject2.posTransRoad = null;
                mainObject2.vecObjFocusSkill = mVector2;
                mainObject2.ListKillNow.setFireSkill(mainObject2, mVector2, by3, (byte)-1);
            }
            if (mainObject2 != null && !mainObject2.isRemove) {
                mainObject2.hp = n4;
                mainObject2.mp = n3;
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void onInfoMount(Message message) {
        try {
            byte by = message.reader().readByte();
            switch (by) {
                case 0: {
                    Object object;
                    int n;
                    byte by2;
                    String string = message.reader().readUTF();
                    short s = message.reader().readShort();
                    String string2 = message.reader().readUTF();
                    byte by3 = message.reader().readByte();
                    byte[] byArray = new byte[by3];
                    for (by2 = 0; by2 < byArray.length; ++by2) {
                        byArray[by2] = -1;
                    }
                    infoShow.vecEquipShow.clear();
                    for (by2 = 0; by2 < by3; ++by2) {
                        n = message.reader().readByte();
                        if (n > -1) {
                            byte by4;
                            String string3 = message.reader().readUTF();
                            byte by5 = message.reader().readByte();
                            byte by6 = message.reader().readByte();
                            short s2 = message.reader().readShort();
                            byte by7 = message.reader().readByte();
                            byte by8 = message.reader().readByte();
                            short s3 = message.reader().readShort();
                            byte by9 = message.reader().readByte();
                            byArray[by2] = by7;
                            byte by10 = message.reader().readByte();
                            MainInfoItem[] mainInfoItemArray = new MainInfoItem[by10];
                            for (by4 = 0; by4 < by10; ++by4) {
                                int n2 = message.reader().readUnsignedByte();
                                int n3 = message.reader().readInt();
                                mainInfoItemArray[by4] = new MainInfoItem(n2, n3);
                            }
                            by4 = message.reader().readByte();
                            object = MainItem.MainItem_Item(n, string3, s2, by8, by9, by5, 3, mainInfoItemArray, by6, false, (short)-1, 0L, s3, (byte)0, (byte)0, 0, (byte)0, (byte)0);
                            ((MainItem)object).set_can_Sell(by4);
                            infoShow.vecEquipShow.put("" + n, object);
                            continue;
                        }
                        byArray[by2] = -1;
                    }
                    by2 = message.reader().readByte();
                    n = message.reader().readByte();
                    object = new String[n];
                    for (int i = 0; i < n; ++i) {
                        object[i] = message.reader().readUTF();
                    }
                    if (GameCanvas.showMount == null) {
                        GameCanvas.showMount = new infoShow();
                    }
                    GameCanvas.showMount.init(by2);
                    GameCanvas.showMount.name = string;
                    GameCanvas.showMount.lv = s;
                    GameCanvas.showMount.exp = string2;
                    GameCanvas.showMount.info = object;
                    GameCanvas.showMount.Show(GameCanvas.currentScreen);
                }
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void other_player_info(Message message) {
        try {
            Object object;
            byte by;
            byte by2;
            int n;
            short s3;
            byte by3;
            byte by4;
            short s4;
            short s5;
            short s6;
            byte by5;
            short s7;
            MainObject mainObject = new MainObject();
            mainObject.ID = message.reader().readShort();
            mainObject.name = message.reader().readUTF();
            mainObject.clazz = message.reader().readByte();
            mainObject.head = message.reader().readByte();
            mainObject.eye = message.reader().readByte();
            mainObject.hair = message.reader().readByte();
            mainObject.Lv = message.reader().readShort();
            mainObject.hp = message.reader().readInt();
            mainObject.maxHp = message.reader().readInt();
            mainObject.typePk = message.reader().readByte();
            mainObject.pointPk = message.reader().readShort();
            byte by6 = message.reader().readByte();
            byte[] byArray = new byte[by6];
            for (s7 = 0; s7 < byArray.length; ++s7) {
                byArray[s7] = -1;
            }
            Info_Other_Player.vecEquipShow.clear();
            for (s7 = 0; s7 < by6; ++s7) {
                by5 = message.reader().readByte();
                if (by5 > -1) {
                    String string = message.reader().readUTF();
                    s6 = message.reader().readByte();
                    s5 = message.reader().readByte();
                    s4 = message.reader().readShort();
                    by4 = message.reader().readByte();
                    by3 = message.reader().readByte();
                    s3 = message.reader().readShort();
                    n = message.reader().readByte();
                    byArray[s7] = by4;
                    by2 = message.reader().readByte();
                    MainInfoItem[] mainInfoItemArray = new MainInfoItem[by2];
                    for (by = 0; by < by2; ++by) {
                        int s2 = message.reader().readUnsignedByte();
                        int n2 = message.reader().readInt();
                        mainInfoItemArray[by] = new MainInfoItem(s2, n2);
                    }
                    by = message.reader().readByte();
                    object = MainItem.MainItem_Item(by5, string, s4, by3, n, s6, 3, mainInfoItemArray, s5, false, (short)-1, 0L, s3, (byte)0, (byte)0, 0, (byte)0, (byte)0);
                    ((MainItem)object).set_can_Sell(by);
                    Info_Other_Player.vecEquipShow.put("" + by5, object);
                    continue;
                }
                byArray[s7] = -1;
            }
            mainObject.setWearingEquip(byArray);
            s7 = message.reader().readShort();
            if (s7 >= 0) {
                mainObject.myClan = new MainClan(0, s7, message.reader().readUTF(), message.reader().readByte());
                mainObject.myClan.name = message.reader().readUTF();
            }
            if ((by5 = message.reader().readByte()) > -1) {
                object = message.reader().readUTF();
                byte by7 = message.reader().readByte();
                s6 = message.reader().readShort();
                s5 = message.reader().readShort();
                s4 = message.reader().readByte();
                by4 = message.reader().readByte();
                by3 = message.reader().readByte();
                s3 = message.reader().readByte();
                n = message.reader().readInt();
                by2 = message.reader().readShort();
                short s8 = message.reader().readShort();
                by = message.reader().readShort();
                short s = message.reader().readShort();
                short s2 = message.reader().readShort();
                short s9 = message.reader().readShort();
                short s10 = message.reader().readShort();
                int n3 = message.reader().readByte();
                MainInfoItem[] mainInfoItemArray = new MainInfoItem[n3];
                for (int i = 0; i < n3; ++i) {
                    int n4 = message.reader().readUnsignedByte();
                    int n5 = message.reader().readInt();
                    int n6 = message.reader().readInt();
                    mainInfoItemArray[i] = new MainInfoItem(n4, n5, n6);
                }
                PetItem petItem = new PetItem(by5, (String)object, s4, s3, by7, 9, mainInfoItemArray, 14, s6, s4, by3, by4);
                petItem.setInfoPet(n, by2, by, s, s2, s9, s8, s10, s5);
                Info_Other_Player.vecEquipShow.put("" + by5, petItem);
                byArray[5] = s4;
                Info_Other_Player.showPet = petItem;
            } else {
                Info_Other_Player.showPet = null;
            }
            Info_Other_Player.showObject = mainObject;
            byte by8 = message.reader().readByte();
            if (GameCanvas.info_other_player == null) {
                GameCanvas.info_other_player = new Info_Other_Player();
            }
            GameCanvas.info_other_player.init(by8);
            GameCanvas.info_other_player.Show(GameCanvas.currentScreen);
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void eff_plus_time(Message message) {
        try {
            byte by = message.reader().readByte();
            short s = message.reader().readShort();
            MainObject mainObject = MainObject.get_Object(s, by);
            if (mainObject != null && !mainObject.isRemove) {
                byte by2 = message.reader().readByte();
                byte by3 = message.reader().readByte();
                MainBuff.setEffHead(by2, by3, mainObject);
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public void changeArea(Message message) {
        try {
            mSystem.outz("nhan change Area");
            LoadMapScreen.isNextMap = false;
            if (GameCanvas.currentScreen != GameCanvas.load) {
                GameCanvas.load.Show();
            }
            GameScreen.RemoveLoadMap();
            LoadMapScreen.isNextMap = true;
            LoadMap.Area = message.reader().readByte();
            GameScreen.player.x = message.reader().readShort() * 24;
            GameScreen.player.y = message.reader().readShort() * 24;
            GameScreen.player.resetMove();
            GameScreen.player.posTransRoad = null;
            GameScreen.player.resetAction();
            if (GameScreen.pet != null) {
                GameScreen.pet.x = GameScreen.player.x;
                GameScreen.pet.y = GameScreen.player.y;
                GameScreen.pet.clearWayPoints();
            }
            GameCanvas.end_Dialog();
            GameCanvas.clearKeyHold();
            GameCanvas.load.isTele = false;
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void update_Status_Area(Message message) {
        try {
            int n;
            int n2;
            LoadMap.MaxArea = message.reader().readByte();
            LoadMap.mStatusArea = new int[LoadMap.MaxArea];
            String[] stringArray = new String[LoadMap.MaxArea];
            for (n2 = 0; n2 < LoadMap.MaxArea; ++n2) {
                LoadMap.mStatusArea[n2] = message.reader().readByte();
                n = message.reader().readByte();
                if (n == 0) continue;
                LoadMap.mStatusArea[n2] = n;
            }
            for (n2 = 0; n2 < LoadMap.MaxArea; ++n2) {
                stringArray[n2] = message.reader().readUTF();
            }
            GameCanvas.start_Wait_Dialog(T.danglaydulieu, new iCommand(T.close, -1));
            GameScreen.isReArea = false;
            mVector mVector2 = new mVector("ReadMessenge menu4");
            for (n = 0; n < LoadMap.MaxArea; ++n) {
                iCommand iCommand2 = new iCommand(stringArray[n], 13, n, (AvMain)GameScreen.gI());
                iCommand2.setFraCaption(PaintInfoGameScreen.fraStatusArea, 1, LoadMap.mStatusArea[n]);
                mVector2.addElement(iCommand2);
            }
            GameCanvas.menu2.startAt(mVector2, 2, T.khuvuc, false, null);
            GameCanvas.end_Dialog();
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public void remove_Actor(Message message) {
        try {
            byte by = message.reader().readByte();
            short s = message.reader().readShort();
            MainObject mainObject = MainObject.get_Item_Object(s, by);
            if (mainObject != null && !mainObject.isRemove) {
                if (by == 1) {
                    if (mainObject.typeBoss == 4) {
                        byte by2 = message.reader().readByte();
                        if (by2 == 0) {
                            mainObject.isMonPhoBangDie = true;
                            mainObject.vMax = 6;
                        } else {
                            mainObject.isStop = true;
                        }
                    } else if (mainObject.typeBoss == 3) {
                        byte by3 = message.reader().readByte();
                        if (by3 == 0) {
                            mainObject.isMonPhoBangDie = true;
                            mainObject.vMax = 6;
                        } else {
                            mainObject.isStop = true;
                        }
                    } else if (mainObject.typeBoss == 2) {
                        byte by4 = message.reader().readByte();
                        if (by4 == 1) {
                            GameScreen.addEffectKill(80, mainObject.x, mainObject.y - 70, 200, s, (byte)1);
                            mainObject.isStop = true;
                            return;
                        }
                        mainObject.isStop = true;
                        MainObject.imgCapchar = null;
                    } else {
                        mainObject.isStop = true;
                    }
                } else if (by == 2) {
                    byte by5 = message.reader().readByte();
                    if (by5 == 1) {
                        mainObject.isMonPhoBangDie = true;
                        LoadMap.isShowEffAuto = LoadMap.EFF_PHOBANG_END;
                    } else {
                        GameScreen.addEffectEndKill(35, mainObject.x, mainObject.y - 20);
                        mainObject.isStop = true;
                    }
                } else {
                    if (by == 0) {
                        GameScreen.addEffectEndKill(35, mainObject.x, mainObject.y - 20);
                    }
                    mainObject.isStop = true;
                }
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void Save_RMS_Server(Message message) {
        try {
            byte by = message.reader().readByte();
            short s = message.reader().readShort();
            byte[] byArray = null;
            if (s > 0) {
                byArray = new byte[s];
                for (int i = 0; i < byArray.length; ++i) {
                    byArray[i] = message.reader().readByte();
                }
            }
            mSystem.outz("nhan k=" + by + "   di=" + s);
            MainRMS.setLoadRMS(by, byArray);
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void List_Pk(Message message) {
        try {
            int n = message.reader().readByte();
            Player.vecPlayerPk = new short[n];
            for (int i = 0; i < n; ++i) {
                Player.vecPlayerPk[i] = message.reader().readShort();
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public void List_Server(Message message) {
        try {
            int n;
            mSystem.outz("list server");
            String string = "List";
            byte by = message.reader().readByte();
            string = message.reader().readUTF();
            byte by2 = message.reader().readByte();
            int n2 = message.reader().readInt();
            int n3 = message.reader().readUnsignedByte();
            mVector mVector2 = new mVector("ReadMessenge vec8");
            if (by == 3) {
                for (n = 0; n < n3; ++n) {
                    String string2 = message.reader().readUTF();
                    int n4 = message.reader().readInt();
                    short s = message.reader().readShort();
                    String string3 = message.reader().readUTF();
                    String string4 = message.reader().readUTF();
                    MainClan mainClan = new MainClan(n4, s, string3, 122);
                    mainClan.name = string2;
                    mainClan.slogan = string4;
                    mainClan.hang = n2 > n3 && n == n3 - 1 ? n2 : n;
                    mVector2.addElement(mainClan);
                }
            } else {
                for (n = 0; n < n3; ++n) {
                    int n5;
                    String string5 = message.reader().readUTF();
                    byte by3 = message.reader().readByte();
                    byte by4 = message.reader().readByte();
                    byte by5 = message.reader().readByte();
                    short s = message.reader().readShort();
                    byte by6 = message.reader().readByte();
                    byte[] byArray = new byte[12];
                    for (n5 = 0; n5 < byArray.length; ++n5) {
                        byArray[n5] = -1;
                    }
                    for (n5 = 0; n5 < by6; ++n5) {
                        byte by7 = message.reader().readByte();
                        byte by8 = message.reader().readByte();
                        byArray[by8] = by7;
                    }
                    n5 = message.reader().readByte();
                    String string6 = message.reader().readUTF();
                    MainObject mainObject = new MainObject(0, 0, string5, 0, 0);
                    short s2 = message.reader().readShort();
                    if (s2 >= 0) {
                        mainObject.myClan = new MainClan(0, s2, message.reader().readUTF(), message.reader().readByte());
                    }
                    mainObject.setInfo(by3, by4, by5);
                    mainObject.typeOnline = (byte)n5;
                    mainObject.Lv = s;
                    mainObject.setWearingListChar(byArray);
                    mainObject.infoObject = string6;
                    mainObject.hang = n2 > n3 && n == n3 - 1 ? n2 : n;
                    mVector2.addElement(mainObject);
                }
            }
            if (mVector2.size() == 0) {
                return;
            }
            if (by == 1) {
                if (n2 > n3) {
                    MainObject mainObject = new MainObject(0, 0, "...", 0, 0);
                    mainObject.hang = -1;
                    mVector2.insertElementAt(mainObject, n3 - 1);
                }
            } else if (by == 3) {
                if (n2 > n3) {
                    MainClan mainClan = new MainClan(0, -1, "...", 122);
                    mainClan.hang = -1;
                    mVector2.insertElementAt(mainClan, n3 - 1);
                }
            } else if (by == 6 && n2 > n3) {
                MainObject mainObject = new MainObject(0, 0, "...", 0, 0);
                mainObject.hang = -1;
                mVector2.insertElementAt(mainObject, n3 - 1);
            }
            List_Server.gI().vecListServer = mVector2;
            List_Server.gI().setSize(by);
            List_Server.gI().updateList();
            List_Server.gI().setMinMax();
            List_Server.gI().setXmove();
            List_Server.gI().setCmd();
            List_Server.gI().nameList = string;
            List_Server.gI().page = by2;
            if (GameCanvas.currentScreen != List_Server.gI()) {
                if (GameCanvas.currentScreen.lastScreen != List_Server.gI()) {
                    List_Server.gI().Show(GameCanvas.currentScreen);
                } else {
                    List_Server.gI().Show(GameCanvas.game);
                }
            } else {
                GameCanvas.end_Dialog();
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void suckhoe(Message message) {
        try {
            Player.PointSucKhoe = message.reader().readInt();
            Player.PointArena = message.reader().readInt();
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public void chat_npc(Message message) {
        try {
            byte by = message.reader().readByte();
            MainObject mainObject = MainObject.get_Object(by, (byte)2);
            if (mainObject != null && !mainObject.isRemove) {
                mainObject.strChatPopup = message.reader().readUTF();
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public void InfoServer_Download(Message message) {
        try {
            String string = message.reader().readUTF();
            String string2 = message.reader().readUTF();
            byte by = message.reader().readByte();
            if (GameScreen.player != null && GameScreen.player.Action != 2 && GameScreen.player.Action != 4) {
                GameScreen.player.resetAction();
                GameScreen.player.posTransRoad = null;
            }
            if (by == 15) {
                GameCanvas.start_Ok_Dialog(string, by);
            } else if (string2.length() == 0) {
                GameCanvas.start_Ok_Dialog(string);
            } else {
                GameCanvas.start_Download_Dialog(string, string2);
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public void name_server(Message message) {
        try {
            int n;
            int n2;
            int n3;
            int n4 = message.reader().readUnsignedByte();
            String[] stringArray = new String[n4];
            for (n3 = 0; n3 < n4; ++n3) {
                stringArray[n3] = message.reader().readUTF();
            }
            WorldMapScreen.namePos = stringArray;
            n3 = message.reader().readByte();
            String[] stringArray2 = new String[n3];
            for (n2 = 0; n2 < n3; ++n2) {
                stringArray2[n2] = message.reader().readUTF();
            }
            TabQuest.nameItemQuest = stringArray2;
            n2 = message.reader().readByte();
            TabRebuildItem.mNameMaterial = new String[n2];
            TabRebuildItem.idMaterial = new short[n2];
            TabRebuildItem.numMaterialInven = new int[n2];
            for (n = 0; n < n2; ++n) {
                TabRebuildItem.idMaterial[n] = message.reader().readShort();
                MainItem mainItem = Item.getMaterial(TabRebuildItem.idMaterial[n]);
                if (mainItem == null) continue;
                TabRebuildItem.mNameMaterial[n] = mainItem.itemName;
            }
            n = message.reader().readByte();
            TabRebuildItem.dataRebuild = new DataRebuildItem[n];
            for (int i = 0; i < n; ++i) {
                TabRebuildItem.dataRebuild[i] = new DataRebuildItem();
                TabRebuildItem.dataRebuild[i].lv = message.reader().readByte();
                TabRebuildItem.dataRebuild[i].priceCoin = message.reader().readInt();
                TabRebuildItem.dataRebuild[i].priceGold = message.reader().readShort();
                TabRebuildItem.dataRebuild[i].mValue = new byte[4];
                for (int j = 0; j < 4; ++j) {
                    TabRebuildItem.dataRebuild[i].mValue[j] = message.reader().readByte();
                }
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void x2_Xp(Message message) {
        try {
            byte by;
            Player.typeX2 = by = message.reader().readByte();
            Player.timeSetX2 = GameCanvas.timeNow;
            Player.timeX2 = message.reader().readShort();
        }
        catch (IOException iOException) {
            iOException.printStackTrace();
        }
    }

    public void loadImageDataCharPart(Message message) {
        try {
            byte by = message.reader().readByte();
            short s = message.reader().readShort();
            String string = "" + s;
            int n = message.reader().readInt();
            byte[] byArray = new byte[n];
            message.reader().read(byArray);
            int n2 = 0;
            if (message.reader().available() > 0) {
                n2 = message.reader().available();
            }
            byte[] byArray2 = new byte[1];
            if (n2 > 0) {
                byArray2 = new byte[n2];
                message.reader().read(byArray2);
            }
            if (by < 100) {
                mImage mImage2 = mImage.createImage(byArray, 0, 0, string);
                if (by < 50) {
                    MainImageDataPartChar mainImageDataPartChar = (MainImageDataPartChar)CharPartInfo.hashImagePartChar.get(by + "_" + s);
                    if (mainImageDataPartChar == null) {
                        mainImageDataPartChar = new MainImageDataPartChar(mImage2, byArray2);
                        CharPartInfo.hashImagePartChar.put(by + "_" + s, mainImageDataPartChar);
                    } else {
                        mainImageDataPartChar.img = mImage2;
                        mainImageDataPartChar.isData = byArray2;
                    }
                } else {
                    MainImageDataPartChar mainImageDataPartChar = (MainImageDataPartChar)CRes.hashWeapon.get(by - 50 + "_" + s);
                    if (mainImageDataPartChar == null) {
                        mainImageDataPartChar = new MainImageDataPartChar(mImage2, byArray2);
                        CRes.hashWeapon.put(by - 50 + "_" + s, mainImageDataPartChar);
                    } else {
                        mainImageDataPartChar.img = mImage2;
                        mainImageDataPartChar.isData = byArray2;
                    }
                }
            } else if (by == 110) {
                ImageEffect imageEffect = (ImageEffect)ImageEffect.hashImageEff.get("" + s);
                if (imageEffect != null) {
                    imageEffect.timeRemove = GameCanvas.timeNow;
                    imageEffect.img = mImage.createImage(byArray, 0, 0, string);
                }
            } else if (by == 111) {
                DataEffAuto dataEffAuto;
                ImageEffectAuto imageEffectAuto = (ImageEffectAuto)ImageEffectAuto.hashImageEffAuto.get("" + s);
                if (imageEffectAuto != null) {
                    imageEffectAuto.timeRemove = GameCanvas.timeNow;
                    imageEffectAuto.img = mImage.createImage(byArray, 0, 0, string);
                }
                if ((dataEffAuto = (DataEffAuto)EffectAuto.ALL_DATA_EFF_AUTO.get("" + s)) != null) {
                    CRes.saveRMS("data_eff" + s, byArray2);
                    dataEffAuto.timeremove = GameCanvas.timeNow;
                    dataEffAuto.setdata(byArray2);
                }
            } else if (by == 112) {
                EffectData effectData;
                ImageIcon imageIcon = (ImageIcon)GameData.listImgIcon.get("" + (s + GameData.ID_START_SKILL));
                if (imageIcon != null) {
                    imageIcon.timeRemove = GameCanvas.timeNow;
                    imageIcon.img = mImage.createImage(byArray, 0, 0, string);
                }
                if ((effectData = (EffectData)DataSkillEff.ALL_DATA_EFFECT.get("" + (s + GameData.ID_START_SKILL))) != null) {
                    effectData.timeRemove = GameCanvas.timeNow;
                    effectData.setdata(byArray2);
                }
            } else if (by == 113) {
                DataSkillEff dataSkillEff;
                ImageIcon imageIcon = (ImageIcon)GameData.listImgIcon.get("" + (s + GameData.ID_START_SKILL));
                if (imageIcon != null) {
                    imageIcon.timeRemove = GameCanvas.timeNow;
                    imageIcon.img = mImage.createImage(byArray, 0, 0, string);
                }
                if ((dataSkillEff = MainObject.getEffMatNa(s)) != null) {
                    dataSkillEff.loadData(byArray2);
                }
            }
            ++MsgDialog.curupdate;
        }
        catch (Exception exception) {
            mSystem.println("loi ne " + exception.toString());
        }
    }

    public void loadImageDataAutoEff(Message message) {
        try {
            byte by = message.reader().readByte();
            if (by != 100) {
                if (by == 4) {
                    int n;
                    short s = message.reader().readShort();
                    byte[] byArray = new byte[s];
                    for (n = 0; n < byArray.length; ++n) {
                        byArray[n] = message.reader().readByte();
                    }
                    n = message.reader().readShort();
                    int n2 = message.reader().readByte();
                    for (int i = 0; i < n2; ++i) {
                        byte by2;
                        short s2 = message.reader().readShort();
                        MainObject mainObject = GameScreen.findObjByteCat(s2, by2 = message.reader().readByte());
                        if (mainObject == null) continue;
                        EffectAuto effectAuto = new EffectAuto(n, mainObject.x, mainObject.y, byArray);
                        GameScreen.vecHightEffAuto.addElement(effectAuto);
                    }
                } else if (by == 3) {
                    short s = message.reader().readShort();
                    byte by3 = message.reader().readByte();
                    int n = message.reader().readUnsignedByte();
                    MainObject mainObject = MainObject.get_Object(s, by3);
                    if (mainObject != null) {
                        mainObject.removeDataSkillEff(n);
                    }
                } else if (by == 101) {
                    short s = message.reader().readShort();
                    for (int i = 0; i < LoadMap.mItemMap.size(); ++i) {
                        MainItemMap mainItemMap = (MainItemMap)LoadMap.mItemMap.elementAt(i);
                        if (mainItemMap == null || mainItemMap.idActor != s) continue;
                        LoadMap.mItemMap.removeElement(mainItemMap);
                    }
                } else {
                    int n;
                    short s = message.reader().readShort();
                    byte[] byArray = new byte[s];
                    for (n = 0; n < byArray.length; ++n) {
                        byArray[n] = message.reader().readByte();
                    }
                    n = message.reader().readByte();
                    byte by4 = message.reader().readByte();
                    int n3 = message.reader().readUnsignedByte();
                    switch (by) {
                        case 0: {
                            short s3 = message.reader().readShort();
                            byte by5 = message.reader().readByte();
                            byte by6 = message.reader().readByte();
                            short s4 = message.reader().readShort();
                            byte by7 = message.reader().readByte();
                            long l = (long)(s4 * 1000) + mSystem.currentTimeMillis();
                            MainObject mainObject = MainObject.get_Object(s3, by5);
                            if (mainObject == null) break;
                            mainObject.addEffAutoFromsv(n3, mainObject.x, mainObject.y, 0, 0, 0, 0, byArray, by6, l, by7, n, by4);
                            break;
                        }
                        case 1: {
                            Object object;
                            short s5 = message.reader().readShort();
                            short s6 = message.reader().readShort();
                            byte by8 = message.reader().readByte();
                            byte by9 = message.reader().readByte();
                            short s7 = message.reader().readShort();
                            short s8 = message.reader().readShort();
                            byte by10 = message.reader().readByte();
                            if (by9 != 0 && s7 != 0 && (object = MainObject.get_Object(s7, by10)) != null) {
                                ((MainObject)object).hOne = by9;
                            }
                            if (by8 == 3) {
                                object = new EffectAuto(n3, s5, s6, 0, 0, by8, 5, byArray, s8);
                                GameScreen.vecHightEffAuto.addElement(object);
                                break;
                            }
                            if (by10 == 1) {
                                for (int i = 0; i < LoadMap.mItemMap.size(); ++i) {
                                    MainItemMap mainItemMap = (MainItemMap)LoadMap.mItemMap.elementAt(i);
                                    if (mainItemMap == null || mainItemMap.idActor != s7) continue;
                                    return;
                                }
                                EffectAuto effectAuto = new EffectAuto(n3, s5, s6, 0, 0, by8, 0, byArray);
                                effectAuto.setidActor(s7);
                                LoadMap.mItemMap.addElement(effectAuto);
                                GameCanvas.loadmap.setBlockNPC(s5, s6, n, by4);
                                CRes.quickSort1(LoadMap.mItemMap);
                                break;
                            }
                            object = new EffectAuto(n3, s5, s6, 0, 0, by8, 0, byArray);
                            ((EffectAuto)object).setidActor(s7);
                            LoadMap.mItemMap.addElement(object);
                            GameCanvas.loadmap.setBlockNPC(s5, s6, n, by4);
                            CRes.quickSort1(LoadMap.mItemMap);
                            break;
                        }
                        case 2: {
                            short s9 = message.reader().readShort();
                            byte by11 = message.reader().readByte();
                            byte by12 = message.reader().readByte();
                            int n4 = message.reader().readInt();
                            if (by12 == 17) {
                                if (n4 < 0) {
                                    GameScreen.player.addDataEff(n3, byArray, n, by4);
                                    break;
                                }
                                if (n4 <= 0) break;
                                long l = (long)n4 + mSystem.currentTimeMillis();
                                GameScreen.player.addDataEff(n3, byArray, n, by4, l, by12);
                                break;
                            }
                            MainObject mainObject = MainObject.get_Object(s9, by11);
                            if (mainObject == null) break;
                            if (n4 < 0) {
                                mainObject.addDataEff(n3, byArray, n, by4);
                                break;
                            }
                            if (n4 > 0) {
                                long l = (long)n4 + mSystem.currentTimeMillis();
                                mainObject.addDataEff(n3, byArray, n, by4, l, by12);
                                break;
                            }
                            mainObject.addDataEff2(n3, byArray, n, by4, 0L, by12);
                        }
                    }
                }
            } else if (by == 100) {
                MainObject mainObject;
                short s = message.reader().readShort();
                int n = message.reader().readUnsignedByte();
                byte by13 = message.reader().readByte();
                byte by14 = message.reader().readByte();
                if ((by13 == 0 || by13 == 1) && (mainObject = MainObject.get_Object(s, by13)) != null) {
                    if (by14 == 0) {
                        for (int i = 0; i < mainObject.vecEffauto.size(); ++i) {
                            EffectAuto effectAuto = (EffectAuto)mainObject.vecEffauto.elementAt(i);
                            if (effectAuto == null || effectAuto.IDImage != n) continue;
                            mainObject.vecEffauto.removeElement(effectAuto);
                        }
                    } else if (by14 == 1) {
                        for (int i = 0; i < mainObject.veclowEffauto.size(); ++i) {
                            EffectAuto effectAuto = (EffectAuto)mainObject.veclowEffauto.elementAt(i);
                            if (effectAuto == null || effectAuto.IDImage != n) continue;
                            mainObject.veclowEffauto.removeElement(effectAuto);
                        }
                    }
                }
            }
        }
        catch (Exception exception) {
            mSystem.println("loadImageDataAutoEff loi  " + exception.toString());
        }
    }

    public void nap_tien(Message message) {
        try {
            short s = message.reader().readShort();
            int n = message.reader().readByte();
            String[] stringArray = new String[n];
            for (int i = 0; i < n; ++i) {
                stringArray[i] = message.reader().readUTF();
            }
            String string = message.reader().readUTF();
            String string2 = message.reader().readUTF();
            if (s == 1) {
                GameCanvas.end_Dialog();
                GameCanvas.start_More_Input_Dialog(stringArray, new iCommand(string, 0), s, (short)-1, string2);
            } else if (mSystem.isIpAppstore()) {
                mVector mVector2 = new mVector("ReadMessenge vec9");
                for (int i = 0; i < TemMidlet.google_listGems.length; ++i) {
                    iCommand iCommand2 = new iCommand(TemMidlet.google_listGems[i], 20, i, (AvMain)this);
                    iCommand2.setFraCaption(AvMain.fraDiamond, 1, 1);
                    mVector2.addElement(iCommand2);
                }
                GameCanvas.menu2.startAt(mVector2, 2, T.naptien, false, null);
            } else if (TemMidlet.currentIAPStore == 2) {
                mVector mVector3 = new mVector("ReadMessenge vec9");
                for (int i = 0; i < TemMidlet.google_listGems.length; ++i) {
                    iCommand iCommand3 = new iCommand(TemMidlet.google_listGems[i], 20, i, (AvMain)this);
                    iCommand3.setFraCaption(AvMain.fraDiamond, 1, 1);
                    mVector3.addElement(iCommand3);
                }
                GameCanvas.menu2.startAt(mVector3, 2, T.naptien, false, null);
            } else if (TemMidlet.currentIAPStore == 1) {
                mVector mVector4 = new mVector("ReadMessenge vec10");
                for (int i = 0; i < TemMidlet.listGems.length; ++i) {
                    iCommand iCommand4 = new iCommand(TemMidlet.listGems[i], 20, i, (AvMain)this);
                    iCommand4.setFraCaption(AvMain.fraDiamond, 1, 1);
                    mVector4.addElement(iCommand4);
                }
                GameCanvas.menu2.startAt(mVector4, 2, T.naptien, false, null);
            } else if (TemMidlet.currentIAPStore == 0) {
                GameCanvas.end_Dialog();
                GameCanvas.start_More_Input_Dialog(stringArray, new iCommand(string, 0), s, (short)-1, string2);
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public void delete_rms(Message message) {
        try {
            if (GameCanvas.IndexRes != -1) {
                TemMidlet.delRMS();
            }
            byte by = message.reader().readByte();
            byte[] byArray = new byte[]{by};
            CRes.saveRMS("isIndexRes", byArray);
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public void CharChest(Message message) {
        this.update_InVen_Or_Chest(message, Item.VecChestPlayer, (byte)9);
    }

    private PetItem createPetItemFromMessage(Message message) {
        try {
            int n = 9;
            int n2 = 14;
            String string = message.reader().readUTF();
            byte by = message.reader().readByte();
            short s = message.reader().readShort();
            short s2 = message.reader().readShort();
            short s3 = message.reader().readShort();
            byte by2 = message.reader().readByte();
            byte by3 = message.reader().readByte();
            byte by4 = message.reader().readByte();
            byte by5 = message.reader().readByte();
            int n3 = message.reader().readInt();
            short s4 = message.reader().readShort();
            short s5 = message.reader().readShort();
            short s6 = message.reader().readShort();
            short s7 = message.reader().readShort();
            short s8 = message.reader().readShort();
            short s9 = message.reader().readShort();
            short s10 = message.reader().readShort();
            int n4 = message.reader().readByte();
            MainInfoItem[] mainInfoItemArray = new MainInfoItem[n4];
            for (int i = 0; i < n4; ++i) {
                int n5 = message.reader().readUnsignedByte();
                int n6 = message.reader().readInt();
                int n7 = message.reader().readInt();
                mainInfoItemArray[i] = new MainInfoItem(n5, n6, n7);
            }
            PetItem petItem = new PetItem(s, string, by2, by5, by, n, mainInfoItemArray, n2, s2, by2, by4, by3);
            petItem.setInfoPet(n3, s4, s6, s7, s8, s9, s5, s10, s3);
            return petItem;
        }
        catch (Exception exception) {
            return null;
        }
    }

    private MainItem createItemFromMessage(Message message) {
        try {
            int n;
            int n2 = 3;
            String string = message.reader().readUTF();
            byte by2 = message.reader().readByte();
            short s = message.reader().readShort();
            byte by3 = message.reader().readByte();
            short s2 = message.reader().readShort();
            byte by4 = message.reader().readByte();
            short s3 = message.reader().readShort();
            byte by5 = message.reader().readByte();
            byte by6 = message.reader().readByte();
            byte by7 = message.reader().readByte();
            int n3 = message.reader().readByte();
            MainInfoItem[] mainInfoItemArray = new MainInfoItem[n3];
            for (n = 0; n < n3; ++n) {
                int by = message.reader().readUnsignedByte();
                int mainItem = message.reader().readInt();
                mainInfoItemArray[n] = new MainInfoItem(by, mainItem);
            }
            n = message.reader().readInt();
            byte by = message.reader().readByte();
            MainItem mainItem = new MainItem(s, string, s2, by4, by5, by2, n2, mainInfoItemArray, by3, false, -1, 0L, s3, by6, by7, n, 0, by);
            return mainItem;
        }
        catch (Exception exception) {
            return null;
        }
    }

    public void UpdatePetContainer(Message message) {
        try {
            byte by = message.reader().readByte();
            byte by2 = message.reader().readByte();
            byte by3 = message.reader().readByte();
            byte by4 = 0;
            mVector mVector2 = Item.VecPetContainer;
            if (by2 == 0) {
                MainTemplateItem.removeUpdateItemVec(by3, mVector2);
                if (by3 == 3) {
                    by4 = message.reader().readByte();
                    int n = message.reader().readByte();
                    for (int i = 0; i < n; ++i) {
                        MainItem mainItem = this.createItemFromMessage(message);
                        mVector2.addElement(mainItem);
                    }
                } else if (by3 == 9) {
                    PetItem petItem;
                    int n;
                    by4 = message.reader().readByte();
                    int n2 = message.reader().readByte();
                    for (n = 0; n < n2; ++n) {
                        petItem = this.createPetItemFromMessage(message);
                        mVector2.addElement(petItem);
                    }
                    if (GameScreen.isInPetArea) {
                        for (n = 0; n < Item.VecPetContainer.size(); ++n) {
                            petItem = (PetItem)mVector2.elementAt(n);
                            Pet pet = (Pet)MainObject.get_Object(petItem.Id, (byte)9);
                            if (GameCanvas.subDialog != null && GameCanvas.subDialog.type == 14 && MsgDialog.isInven_Equip == 0) {
                                mSystem.outz(" Id item pet in MsgDialog = " + MsgDialog.pet.Id);
                                if (MsgDialog.pet.Id == petItem.Id) {
                                    MsgDialog.pet = petItem;
                                }
                            }
                            if (pet != null) continue;
                            Pet pet2 = Pet.createPet((short)petItem.type, petItem.Id, petItem.nFrameImgPet, petItem.petImageId);
                            GameScreen.addPlayer(pet2);
                        }
                    }
                }
            } else if (by2 == 1) {
                if (by3 == 3) {
                    by4 = message.reader().readByte();
                    MainItem mainItem = this.createItemFromMessage(message);
                    Item item = Item.getItemVec(by4, (short)mainItem.Id, mVector2);
                    if (item == null) {
                        mVector2.addElement(mainItem);
                    } else {
                        mVector2.setElementAt(mainItem, mVector2.indexOf(item));
                    }
                } else if (by3 == 9) {
                    by4 = message.reader().readByte();
                    PetItem petItem = this.createPetItemFromMessage(message);
                    Item item = Item.getItemVec(by4, (short)petItem.Id, mVector2);
                    if (item == null) {
                        mVector2.addElement(petItem);
                    } else {
                        mVector2.setElementAt(petItem, mVector2.indexOf(item));
                    }
                    if (GameScreen.isInPetArea) {
                        Pet pet = Pet.createPet((short)petItem.type, petItem.Id, petItem.nFrameImgPet, petItem.petImageId);
                        GameScreen.addPlayer(pet);
                    }
                }
            } else if (by2 == 2) {
                Pet pet;
                PetItem petItem;
                short s;
                by4 = message.reader().readByte();
                Item item = Item.getItemVec(by4, s = message.reader().readShort(), mVector2);
                if (item != null) {
                    mVector2.removeElement(item);
                }
                if ((petItem = (PetItem)item) != null && petItem.type >= 0 && (pet = (Pet)MainObject.get_Item_Object(petItem.Id, 9)) != null) {
                    GameScreen.Vecplayers.removeElement(pet);
                }
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public void Dialog_server(Message message) {
        try {
            GameCanvas.end_Dialog();
            this.idDialog = message.reader().readShort();
            this.typeDialog = message.reader().readByte();
            String string = message.reader().readUTF();
            mVector mVector2 = new mVector("ReadMessenge vec4");
            mVector2.addElement(new iCommand("Ok", 2, 1, (AvMain)this));
            mVector2.addElement(new iCommand(T.cancel, 2, 0, (AvMain)this));
            GameCanvas.start_Select_Dialog(string, mVector2);
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public void update_InVen_Or_Chest(Message message, mVector mVector2, byte by) {
        block56: {
            try {
                byte by2;
                byte by3;
                byte by4;
                block57: {
                    block59: {
                        int n;
                        block58: {
                            mSystem.outz("<<<<<<<<< update inventory >>>>>>>>>>>>");
                            MainTemplateItem.isload = false;
                            if (by == 9) {
                                Player.maxChest = message.reader().readByte();
                            }
                            by4 = message.reader().readByte();
                            by3 = message.reader().readByte();
                            if (by == 0) {
                                long l = message.reader().readLong();
                                n = message.reader().readInt();
                                GameScreen.player.coin = l;
                                GameScreen.player.gold = n;
                            }
                            by2 = 0;
                            if (by4 == 0) {
                                MainTemplateItem.removeUpdateItemVec(by3, mVector2);
                                if (by3 == 4) {
                                    by2 = message.reader().readByte();
                                    byte by5 = message.reader().readByte();
                                    for (n = 0; n < by5; ++n) {
                                        short s = message.reader().readShort();
                                        short s2 = message.reader().readShort();
                                        byte by6 = message.reader().readByte();
                                        byte by7 = message.reader().readByte();
                                        MainTemplateItem mainTemplateItem = (MainTemplateItem)MainTemplateItem.hashPotionTem.get("" + s);
                                        mSystem.outz("content=" + mainTemplateItem.contentPotion);
                                        MainItem mainItem = new MainItem(s, mainTemplateItem.IconId, mainTemplateItem.name, mainTemplateItem.contentPotion, s2, by2, mainTemplateItem.PricePoition, mainTemplateItem.typePotion, mainTemplateItem.moneyType, by6, by7);
                                        mVector2.addElement(mainItem);
                                    }
                                    if (by == 0) {
                                        MainItem.setAddHotKey((byte)1, false);
                                        MainItem.setAddHotKey((byte)0, false);
                                    }
                                } else if (by3 == 3) {
                                    by2 = message.reader().readByte();
                                    byte by8 = message.reader().readByte();
                                    mSystem.outz("size item=" + by8);
                                    for (n = 0; n < by8; ++n) {
                                        int n2;
                                        byte by9;
                                        int n3;
                                        String string = message.reader().readUTF();
                                        byte by10 = message.reader().readByte();
                                        short s = message.reader().readShort();
                                        byte by11 = message.reader().readByte();
                                        short s3 = message.reader().readShort();
                                        byte by12 = message.reader().readByte();
                                        short s4 = message.reader().readShort();
                                        byte by13 = message.reader().readByte();
                                        byte by14 = message.reader().readByte();
                                        byte by15 = message.reader().readByte();
                                        int n4 = message.reader().readByte();
                                        MainInfoItem[] mainInfoItemArray = new MainInfoItem[n4];
                                        for (n3 = 0; n3 < n4; ++n3) {
                                            by9 = message.reader().readUnsignedByte();
                                            n2 = message.reader().readInt();
                                            mainInfoItemArray[n3] = new MainInfoItem(by9, n2);
                                        }
                                        n3 = message.reader().readInt();
                                        by9 = message.reader().readByte();
                                        n2 = message.reader().readByte();
                                        int n5 = -1;
                                        String string2 = "-1";
                                        if (n2 == 1) {
                                            n5 = message.reader().readInt();
                                            string2 = message.reader().readUTF();
                                        }
                                        byte by16 = message.reader().readByte();
                                        MainItem mainItem = null;
                                        mainItem = new MainItem(s, string, s3, by12, by13, by10, by2, mainInfoItemArray, by11, false, -1, 0L, s4, by14, by15, n3, 0, by9, n5, Long.parseLong(string2));
                                        mainItem.setCanShell_notCanTrade(by16);
                                        mVector2.addElement(mainItem);
                                    }
                                } else if (by3 == 5) {
                                    by2 = message.reader().readByte();
                                    byte by17 = message.reader().readByte();
                                    mSystem.outz("size item quest=" + by17);
                                    for (n = 0; n < by17; ++n) {
                                        short s = message.reader().readShort();
                                        String string = message.reader().readUTF();
                                        short s5 = message.reader().readShort();
                                        String string3 = T.questitem;
                                        byte by18 = message.reader().readByte();
                                        byte by19 = message.reader().readByte();
                                        MainItem mainItem = new MainItem(s, string, (int)s5, string3, by18, by19);
                                        mVector2.addElement(mainItem);
                                    }
                                } else if (by3 == 7) {
                                    by2 = message.reader().readByte();
                                    byte by20 = message.reader().readByte();
                                    mSystem.outz("size material=" + by20);
                                    for (n = 0; n < by20; ++n) {
                                        MainItem mainItem;
                                        short s = message.reader().readShort();
                                        short s6 = message.reader().readShort();
                                        byte by21 = message.reader().readByte();
                                        byte by22 = message.reader().readByte();
                                        MainItem mainItem2 = Item.getMaterial(s);
                                        if (mainItem2 != null) {
                                            mainItem = MainItem.MainItem_Material(s, mainItem2.itemName, mainItem2.imageId, 7, mainItem2.priceItem, mainItem2.priceType, mainItem2.content, mainItem2.value, mainItem2.typeMaterial, s6, by21, by22);
                                            mVector2.addElement(mainItem);
                                            continue;
                                        }
                                        mainItem = new MainItem();
                                        mainItem.Id = s;
                                        mainItem.numPotion = s6;
                                        mainItem.canSell = by21;
                                        mainItem.canTrade = by22;
                                        mainItem.ItemCatagory = 7;
                                        Item.put_Material(s);
                                        mVector2.addElement(mainItem);
                                    }
                                }
                                if (by == 0 && by3 != 3) {
                                    TabShopNew.isSortInven = true;
                                }
                                break block56;
                            }
                            if (by4 != 1) break block57;
                            if (by3 != 4) break block58;
                            by2 = message.reader().readByte();
                            short s = message.reader().readShort();
                            n = message.reader().readShort();
                            byte by23 = message.reader().readByte();
                            byte by24 = message.reader().readByte();
                            Item item = Item.getItemVec(by2, s, mVector2);
                            if (item != null) {
                                item.numPotion = n;
                                item.canSell = by23;
                                item.canTrade = by24;
                            } else {
                                MainTemplateItem mainTemplateItem = (MainTemplateItem)MainTemplateItem.hashPotionTem.get("" + s);
                                MainItem mainItem = new MainItem(s, mainTemplateItem.IconId, mainTemplateItem.name, mainTemplateItem.contentPotion, n, by2, mainTemplateItem.PricePoition, mainTemplateItem.typePotion, mainTemplateItem.moneyType, by23, by24);
                                mVector2.addElement(mainItem);
                            }
                            if (by != 0) break block59;
                            MainItem.setAddHotKey((byte)1, false);
                            MainItem.setAddHotKey((byte)0, false);
                            if (!GameScreen.help.setStep_Next(1, 5)) break block59;
                            ++GameScreen.help.Next;
                            GameScreen.help.setNext();
                            break block59;
                        }
                        if (by3 == 3) {
                            int n6;
                            by2 = message.reader().readByte();
                            String string = message.reader().readUTF();
                            n = message.reader().readByte();
                            short s = message.reader().readShort();
                            Item item = Item.getItemVec(by2, s, mVector2);
                            byte by25 = message.reader().readByte();
                            short s7 = message.reader().readShort();
                            byte by26 = message.reader().readByte();
                            short s8 = message.reader().readShort();
                            byte by27 = message.reader().readByte();
                            byte by28 = message.reader().readByte();
                            byte by29 = message.reader().readByte();
                            int n7 = message.reader().readByte();
                            MainInfoItem[] mainInfoItemArray = new MainInfoItem[n7];
                            for (n6 = 0; n6 < n7; ++n6) {
                                mainInfoItemArray[n6] = new MainInfoItem(message.reader().readUnsignedByte(), message.reader().readInt());
                            }
                            n6 = message.reader().readInt();
                            byte by30 = message.reader().readByte();
                            byte by31 = message.reader().readByte();
                            int n8 = -1;
                            String string4 = "-1";
                            if (by31 == 1) {
                                n8 = message.reader().readInt();
                                string4 = message.reader().readUTF();
                            }
                            byte by32 = message.reader().readByte();
                            MainItem mainItem = MainItem.MainItem_Item(s, string, s7, by26, by27, n, by2, mainInfoItemArray, by25, false, (short)-1, 0L, s8, by28, by29, n6, (byte)0, by30, n8, Long.parseLong(string4));
                            mainItem.setCanShell_notCanTrade(by32);
                            if (item == null) {
                                mVector2.addElement(mainItem);
                            } else {
                                mVector2.setElementAt(mainItem, mVector2.indexOf(item));
                            }
                        } else if (by3 == 9) {
                            by2 = message.reader().readByte();
                            PetItem petItem = this.createPetItemFromMessage(message);
                            Item item = Item.getItemVec(by2, (short)petItem.Id, mVector2);
                            if (item == null) {
                                mVector2.addElement(petItem);
                            } else {
                                mSystem.outz("replace pet");
                                mVector2.setElementAt(petItem, mVector2.indexOf(item));
                            }
                        } else if (by3 == 5) {
                            by2 = message.reader().readByte();
                            short s = message.reader().readShort();
                            String string = message.reader().readUTF();
                            short s9 = message.reader().readShort();
                            String string5 = T.questitem;
                            byte by33 = message.reader().readByte();
                            byte by34 = message.reader().readByte();
                            Item item = Item.getItemVec(by2, s, mVector2);
                            if (item == null) {
                                MainItem mainItem = new MainItem(s, string, (int)s9, string5, by33, by34);
                                mVector2.addElement(mainItem);
                            } else {
                                item.numPotion = s9;
                                item.canSell = by33;
                                item.canTrade = by34;
                            }
                        } else if (by3 == 7) {
                            MainItem mainItem;
                            by2 = message.reader().readByte();
                            short s = message.reader().readShort();
                            n = message.reader().readShort();
                            byte by35 = message.reader().readByte();
                            byte by36 = message.reader().readByte();
                            Item item = Item.getItemVec(by2, s, mVector2);
                            if (item != null) {
                                item.numPotion = n;
                                item.canSell = by35;
                                item.canTrade = by36;
                            } else {
                                MainItem mainItem3 = Item.getMaterial(s);
                                if (mainItem3 != null) {
                                    item = MainItem.MainItem_Material(s, mainItem3.itemName, mainItem3.imageId, 7, mainItem3.priceItem, mainItem3.priceType, mainItem3.content, mainItem3.canTrade, mainItem3.typeMaterial, (short)n, by35, by36);
                                    mVector2.addElement(item);
                                } else {
                                    mainItem = new MainItem();
                                    mainItem.Id = s;
                                    mainItem.numPotion = n;
                                    mainItem.canSell = by35;
                                    mainItem.canTrade = by36;
                                    mainItem.ItemCatagory = 7;
                                    Item.put_Material(s);
                                    mVector2.addElement(mainItem);
                                }
                            }
                            if (by == 0) {
                                for (int i = 0; i < TabRebuildItem.idMaterial.length; ++i) {
                                    Item item2;
                                    mainItem = Item.getMaterial(TabRebuildItem.idMaterial[i]);
                                    int n9 = 0;
                                    if (mainItem != null && (item2 = Item.getItemInventory(mainItem.ItemCatagory, (short)mainItem.Id)) != null) {
                                        n9 = item2.numPotion;
                                    }
                                    TabRebuildItem.numMaterialInven[i] = n9;
                                }
                            }
                        }
                    }
                    if (by == 0) {
                        if (GameScreen.help.Step == 1 && GameScreen.help.Next == 5) {
                            ++GameScreen.help.Next;
                            GameScreen.help.setNext();
                        }
                        if (by3 != 3) {
                            TabShopNew.isSortInven = true;
                        }
                    }
                    break block56;
                }
                if (by4 == 2) {
                    short s;
                    by2 = message.reader().readByte();
                    Item item = Item.getItemVec(by2, s = message.reader().readShort(), mVector2);
                    if (item != null) {
                        mVector2.removeElement(item);
                    }
                    if (by == 0) {
                        if (by3 == 4) {
                            MainItem.setAddHotKey((byte)1, false);
                            MainItem.setAddHotKey((byte)0, false);
                        } else if (by3 == 7 && by == 0) {
                            for (int i = 0; i < TabRebuildItem.idMaterial.length; ++i) {
                                Item item3;
                                MainItem mainItem = Item.getMaterial(TabRebuildItem.idMaterial[i]);
                                int n = 0;
                                if (mainItem != null && (item3 = Item.getItemInventory(mainItem.ItemCatagory, (short)mainItem.Id)) != null) {
                                    n = item3.numPotion;
                                }
                                TabRebuildItem.numMaterialInven[i] = n;
                            }
                        }
                    }
                }
            }
            catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        MainTemplateItem.isload = true;
        MainTabNew.timePaintInfo = 0;
    }

    public void Rebuild_Item(Message message) {
        try {
            byte by = message.reader().readByte();
            if (by == 0) {
                short s = message.reader().readShort();
                byte by2 = message.reader().readByte();
                if (by2 == 3) {
                    Item item = Item.getItemInventory(by2, s);
                    if (item != null && TabRebuildItem.isBeginEff == 0) {
                        TabRebuildItem.itemRe = MainItem.MainItem_Item(s, item.itemNameExcludeLv, item.imageId, item.tier, item.colorNameItem, item.classcharItem, by2, item.mInfo, item.type_Only_Item, false, item.IdTem, item.priceItem, (short)item.LvItem, item.canSell, item.canTrade, 0, (byte)0, (byte)0);
                    }
                } else {
                    TabRebuildItem.isLucky = true;
                }
                TabRebuildItem.tilemaymanafter = TabRebuildItem.isBeginEff == 0 ? (TabRebuildItem.tilemayman = message.reader().readUTF()) : message.reader().readUTF();
            } else if (by == 1) {
                byte by3 = message.reader().readByte();
                if (by3 == 3) {
                    TabRebuildItem.itemRe = null;
                } else {
                    TabRebuildItem.isLucky = false;
                }
                TabRebuildItem.tilemaymanafter = TabRebuildItem.isBeginEff == 0 ? (TabRebuildItem.tilemayman = message.reader().readUTF()) : message.reader().readUTF();
            } else if (by == 2) {
                GameCanvas.end_Dialog();
                TabRebuildItem.isBeginEff = 1;
                TabRebuildItem.isNextRebuild = message.reader().readByte();
                TabRebuildItem.contentShow = message.reader().readUTF();
                mSystem.outloi("thanh cong hay that bai=" + TabRebuildItem.isNextRebuild);
            } else if (by == 3) {
                GameCanvas.end_Dialog();
                String string = message.reader().readUTF();
                GameCanvas.menu2.startAt_NPC(null, string, Menu2.IdNPCLast, (byte)2, false, 0);
            } else if (by == 4) {
                short s = message.reader().readShort();
                short s2 = message.reader().readShort();
                String string = message.reader().readUTF();
                byte by4 = message.reader().readByte();
                Item item = Item.getItemInventory(by4, s);
                if (item != null) {
                    short s3;
                    MainItem mainItem;
                    TabRebuildItem.itemRe = MainItem.MainItem_Item(s, item.itemNameExcludeLv, item.imageId, item.tier, item.colorNameItem, item.classcharItem, by4, item.mInfo, item.type_Only_Item, false, item.IdTem, item.priceItem, (short)item.LvItem, item.canSell, item.canTrade, 0, (byte)0, (byte)0);
                    TabRebuildItem.itemRe.itemClone = null;
                    TabRebuildItem.itemRe.infoHop = mSystem.split(string, "|");
                    MainItem mainItem2 = Item.getMaterial(TabRebuildItem.itemRe.Id);
                    if (mainItem2 != null) {
                        TabRebuildItem.itemRe.itemName = mainItem2.itemName;
                    }
                    if ((mainItem = Item.getMaterial(s3 = s2)) != null) {
                        if (mainItem.isMaterialHopNguyenLieu()) {
                            TabRebuildItem.itemRe.itemClone = mainItem.clone();
                        }
                    } else {
                        MainItem mainItem3 = new MainItem();
                        mainItem3.Id = s3;
                        mainItem3.ItemCatagory = 7;
                        TabRebuildItem.itemRe.itemClone = mainItem3;
                    }
                }
            } else if (by == 5) {
                GameCanvas.end_Dialog();
                TabRebuildItem.isBeginEff = 1;
                TabRebuildItem.isNextRebuild = message.reader().readByte();
                TabRebuildItem.contentShow = message.reader().readUTF();
            } else if (by == 6) {
                TabRebuildItem.itemRe = null;
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public void ReplacePlusItem(Message message) {
        try {
            byte by = message.reader().readByte();
            if (by == 0) {
                short s = message.reader().readShort();
                byte by2 = message.reader().readByte();
                Item item = Item.getItemInventory(3, s);
                if (item != null) {
                    if (by2 == 1) {
                        TabRebuildItem.itemPlus = MainItem.MainItem_Item(s, item.itemNameExcludeLv, item.imageId, item.tier, item.colorNameItem, item.classcharItem, 3, item.mInfo, item.type_Only_Item, false, item.IdTem, item.priceItem, (short)item.LvItem, item.canSell, item.canTrade, 0, (byte)0, (byte)0);
                    } else if (by2 == 0) {
                        TabRebuildItem.itemFree = MainItem.MainItem_Item(s, item.itemNameExcludeLv, item.imageId, item.tier, item.colorNameItem, item.classcharItem, 3, item.mInfo, item.type_Only_Item, false, item.IdTem, item.priceItem, (short)item.LvItem, item.canSell, item.canTrade, 0, (byte)0, (byte)0);
                    }
                } else {
                    mSystem.outz("k le no null=");
                }
            } else if (by == 1) {
                GameCanvas.end_Dialog();
                short s = message.reader().readShort();
                if (TabRebuildItem.itemPlus == null || TabRebuildItem.itemFree == null) {
                    return;
                }
                mVector mVector2 = new mVector("ReadMessenge menu3");
                mVector2.addElement(new iCommand(T.replace, 4, this));
                GameCanvas.menu2.startAt_NPC(mVector2, T.hoichuyendo + TabRebuildItem.itemPlus.itemName + T.qua + TabRebuildItem.itemFree.itemName + "? " + T.phi + ": " + s + " " + T.gem + ".", Menu2.IdNPCLast, (byte)2, false, 0);
            } else if (by == 2) {
                GameCanvas.end_Dialog();
                TabRebuildItem.contentShow = message.reader().readUTF();
                TabRebuildItem.isBeginEff = 1;
            } else if (by == 3) {
                GameCanvas.end_Dialog();
                String string = message.reader().readUTF();
                GameCanvas.menu2.startAt_NPC(null, string, Menu2.IdNPCLast, (byte)2, false, 0);
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public void Thach_Dau(Message message) {
        try {
            byte by = message.reader().readByte();
            if (by == 0) {
                String string = message.reader().readUTF();
                short s = message.reader().readShort();
                GameCanvas.mevent.addEvent(string, (byte)4, T.loimoithachdau, s);
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public void Help_From_Server(Message message) {
        try {
            byte by = message.reader().readByte();
            MainObject mainObject = MainObject.get_Object(by, (byte)2);
            if (mainObject == null || mainObject.isRemove) {
                return;
            }
            String string = message.reader().readUTF();
            MainObject.strHelpNPC = mFont.split(string, "\b");
            MainObject.StepHelpServer = 0;
            mVector mVector2 = new mVector("ReadMessenge menu2");
            iCommand iCommand2 = new iCommand(T.next, 3, by, (AvMain)this);
            mVector2.addElement(iCommand2);
            GameCanvas.menu2.startAt_NPC(mVector2, MainObject.strHelpNPC[0], by, (byte)2, false, 2);
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public void Clan(Message message) {
        block36: {
            try {
                byte by = message.reader().readByte();
                String string = "";
                String string2 = "";
                long l = 0L;
                int n = 0;
                byte by2 = 122;
                short s = 0;
                block1 : switch (by) {
                    case 10: {
                        string = message.reader().readUTF();
                        GameCanvas.mevent.addEvent(string, (byte)5, T.loimoivaoclan, 0);
                        break;
                    }
                    case 14: {
                        string = message.reader().readUTF();
                        short s2 = message.reader().readShort();
                        by2 = message.reader().readByte();
                        l = message.reader().readLong();
                        n = message.reader().readInt();
                        GameCanvas.start_Ok_Dialog(T.thanhvien + ": " + string + " " + T.level + s2 + "\n" + T.chucvu + MainClan.getNameChucVu(by2) + "\n" + T.gop + T.coin + ": " + l + "\n" + T.gop + T.gem + ": " + n);
                        break;
                    }
                    case 15: {
                        byte by3 = message.reader().readByte();
                        byte by4 = message.reader().readByte();
                        if (by4 == 0) {
                            int n2 = message.reader().readInt();
                            s = message.reader().readShort();
                            string2 = message.reader().readUTF();
                            string = message.reader().readUTF();
                            short s3 = message.reader().readShort();
                            short s4 = message.reader().readShort();
                            short s5 = message.reader().readShort();
                            short s6 = message.reader().readShort();
                            short s7 = message.reader().readShort();
                            String string3 = message.reader().readUTF();
                            String string4 = message.reader().readUTF();
                            String string5 = message.reader().readUTF();
                            l = message.reader().readLong();
                            n = message.reader().readInt();
                            byte by5 = message.reader().readByte();
                            MainThanhTichClan[] mainThanhTichClanArray = null;
                            if (by5 > 0) {
                                mainThanhTichClanArray = new MainThanhTichClan[by5];
                                for (int i = 0; i < mainThanhTichClanArray.length; ++i) {
                                    mainThanhTichClanArray[i] = new MainThanhTichClan(message.reader().readByte(), message.reader().readByte(), message.reader().readUTF());
                                }
                            }
                            if (by3 == 0) {
                                if (GameScreen.player.myClan == null) {
                                    GameScreen.player.myClan = new MainClan(n2, s, string2, 122);
                                }
                                GameScreen.player.myClan.setInfoClan(string, s3, s4, s5, s6, s7, string4, string5, string3, l, n, mainThanhTichClanArray);
                                if (GameCanvas.clan == null) {
                                    GameCanvas.clan = new Clan_Screen();
                                }
                                GameCanvas.clan.setInfoClan(GameScreen.player.myClan, by3);
                                GameCanvas.clan.Show(GameCanvas.currentScreen);
                            } else {
                                MainClan mainClan = new MainClan(n2, s, string2, 122);
                                mainClan.setInfoClan(string, s3, s4, s5, s6, s7, string4, string5, string3, l, n, mainThanhTichClanArray);
                                if (GameCanvas.clan == null) {
                                    GameCanvas.clan = new Clan_Screen();
                                }
                                GameCanvas.clan.setInfoClan(mainClan, by3);
                                GameCanvas.clan.Show(GameCanvas.currentScreen);
                            }
                            GameCanvas.end_Dialog();
                            break;
                        }
                        if (by4 == 1) {
                            if (GameScreen.player.myClan == null) break;
                            GameScreen.player.myClan.coin = message.reader().readLong();
                            GameScreen.player.myClan.gold = message.reader().readInt();
                            break;
                        }
                        if (by4 == 2) {
                            if (GameScreen.player.myClan == null) break;
                            GameScreen.player.myClan.noiquy = message.reader().readUTF();
                            Clan_Screen.isUpdateThongTin = true;
                            break;
                        }
                        if (by4 != 3 || GameScreen.player.myClan == null) break;
                        GameScreen.player.myClan.slogan = message.reader().readUTF();
                        Clan_Screen.isUpdateThongTin = true;
                        break;
                    }
                    case 19: {
                        short s8 = message.reader().readShort();
                        String string6 = message.reader().readUTF();
                        int n3 = message.reader().readInt();
                        if (n3 != -1) {
                            string = message.reader().readUTF();
                            string2 = message.reader().readUTF();
                            s = message.reader().readShort();
                            by2 = message.reader().readByte();
                        }
                        MainObject mainObject = null;
                        if (s8 != 32000 && (mainObject = MainObject.get_Object(s8, (byte)0)) != null) {
                            if (n3 == -1) {
                                if (mainObject.myClan != null) {
                                    mainObject.myClan.isRemove = true;
                                }
                            } else if (mainObject.myClan == null) {
                                mainObject.myClan = new MainClan(n3, s, string2, by2);
                            } else {
                                mainObject.myClan.IdClan = n3;
                                mainObject.myClan.IdIcon = s;
                                mainObject.myClan.shortName = string2.toUpperCase();
                                mainObject.myClan.chucvu = by2;
                            }
                        }
                        if (GameCanvas.currentScreen != List_Server.gI() || List_Server.gI().typeList != 4) break;
                        for (int i = 0; i < List_Server.gI().vecListServer.size(); ++i) {
                            MainObject mainObject2 = (MainObject)List_Server.gI().vecListServer.elementAt(i);
                            if (mainObject2.name.compareTo(string6) != 0) continue;
                            if (mainObject2.myClan != null) {
                                if (n3 == -1) {
                                    mainObject2.myClan.chucvu = (byte)121;
                                    break block1;
                                }
                                mainObject2.myClan.chucvu = by2;
                                break block1;
                            }
                            break block36;
                        }
                        break;
                    }
                    case 20: {
                        String string7 = message.reader().readUTF();
                        GameCanvas.start_Ok_Dialog(string7);
                        break;
                    }
                    case 21: {
                        byte by6 = message.reader().readByte();
                        if (by6 == 3) {
                            Item.VecClanInvetory.removeAllElements();
                            int n4 = message.reader().readShort();
                            for (int i = 0; i < n4; ++i) {
                                short s9 = message.reader().readShort();
                                short s10 = message.reader().readShort();
                                MainTemplateItem mainTemplateItem = (MainTemplateItem)MainTemplateItem.hashPotionTem.get("" + s9);
                                MainItem mainItem = new MainItem(s9, mainTemplateItem.IconId, mainTemplateItem.name, mainTemplateItem.contentPotion, s10, 4, mainTemplateItem.PricePoition, mainTemplateItem.typePotion, mainTemplateItem.moneyType, 0, 0);
                                Item.VecClanInvetory.addElement(mainItem);
                            }
                            mVector mVector2 = new mVector("ReadMessenge vec3");
                            TabShopNew tabShopNew = new TabShopNew(Item.VecClanInvetory, 12, T.tabBangHoi, -1, TabShopNew.NORMAL);
                            mVector2.addElement(tabShopNew);
                            GameCanvas.shopNpc = new TabScreenNew();
                            GameCanvas.shopNpc.selectTab = 0;
                            GameCanvas.shopNpc.addMoreTab(mVector2);
                            GameCanvas.shopNpc.Show(GameScreen.gI());
                            GameCanvas.end_Dialog();
                            break;
                        }
                        if (by6 == 2) {
                            short s11 = message.reader().readShort();
                            Item item = Item.getItemVec(4, s11, Item.VecClanInvetory);
                            if (item != null) {
                                Item.VecClanInvetory.removeElement(item);
                            }
                            break;
                        }
                        if (by6 != 0) break;
                        short s12 = message.reader().readShort();
                        short s13 = message.reader().readShort();
                        Item item = Item.getItemVec(4, s12, Item.VecClanInvetory);
                        if (item != null) {
                            item.numPotion = s13;
                            break;
                        }
                        MainTemplateItem mainTemplateItem = (MainTemplateItem)MainTemplateItem.hashPotionTem.get("" + s12);
                        MainItem mainItem = new MainItem(s12, mainTemplateItem.IconId, mainTemplateItem.name, mainTemplateItem.contentPotion, s13, 4, mainTemplateItem.PricePoition, mainTemplateItem.typePotion, mainTemplateItem.moneyType, 0, 0);
                        Item.VecClanInvetory.addElement(mainItem);
                        break;
                    }
                    case 22: {
                        if (GameScreen.player.myClan == null) break;
                        GameScreen.player.myClan.typeX2 = message.reader().readByte();
                        GameScreen.player.myClan.timeX2 = message.reader().readShort();
                    }
                }
            }
            catch (Exception exception) {
                // empty catch block
            }
        }
    }

    public void updateHpNPC(Message message) {
        try {
            byte by = message.reader().readByte();
            short s = message.reader().readShort();
            short s2 = message.reader().readShort();
            byte by2 = message.reader().readByte();
            MainObject mainObject = MainObject.get_Object(by2, (byte)2);
            if (mainObject != null && !mainObject.isRemove) {
                mainObject.hp = s;
                mainObject.maxHp = s2;
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public void Dialog_More_server(Message message) {
        try {
            int n;
            GameCanvas.end_Dialog();
            this.idDialog = message.reader().readShort();
            this.typeDialog = message.reader().readByte();
            String string = message.reader().readUTF();
            int n2 = message.reader().readByte();
            String[] stringArray = new String[n2];
            String[] stringArray2 = new String[n2];
            byte by = 1;
            byte[] byArray = new byte[n2];
            for (n = 0; n < n2; ++n) {
                stringArray[n] = message.reader().readUTF();
                byArray[n] = message.reader().readByte();
                stringArray2[n] = "";
            }
            try {
                for (n = 0; n < n2; ++n) {
                    stringArray2[n] = message.reader().readUTF();
                }
                by = message.reader().readByte();
            }
            catch (Exception exception) {
                // empty catch block
            }
            GameCanvas.start_More_Input_Dialog(stringArray, new iCommand("OK", 1), this.typeDialog, this.idDialog, string, stringArray2, by);
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public void InfoEasyFromServer(Message message) {
        try {
            String string = message.reader().readUTF();
            byte by = message.reader().readByte();
            if (by == 0) {
                GameCanvas.addInfoChar(string);
            } else if (by == 1) {
                GameCanvas.addInfoCharServer(string);
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public void EffFormServer(Message message) {
        try {
            byte by = message.reader().readByte();
            byte by2 = message.reader().readByte();
            short s = message.reader().readShort();
            short s2 = message.reader().readShort();
            byte by3 = message.reader().readByte();
            short s3 = message.reader().readShort();
            MainObject mainObject = MainObject.get_Object(s, by2);
            mSystem.outz("Effect from server type = " + mainObject.name);
            if (by >= 0 && mainObject != null && !mainObject.isRemove) {
                switch (by) {
                    case 0: {
                        GameScreen.addEffectEndKill(38, mainObject.x, mainObject.y - 20);
                        break;
                    }
                    case 1: {
                        for (int i = 0; i < EffectManager.hiEffects.size(); ++i) {
                            MainEffect mainEffect = (MainEffect)EffectManager.hiEffects.elementAt(i);
                            if (mainEffect.typeEffect != 85 || mainEffect.objBeKillMain == null || mainEffect.objBeKillMain.ID != s) continue;
                            if (s2 == 0) {
                                mainEffect.isStop = true;
                            }
                            return;
                        }
                        GameScreen.addEffectKill(85, mainObject.x, mainObject.y, s2, s, by2);
                        break;
                    }
                    case 2: {
                        for (int i = 0; i < EffectManager.hiEffects.size(); ++i) {
                            MainEffect mainEffect = (MainEffect)EffectManager.hiEffects.elementAt(i);
                            if (mainEffect.typeEffect != 87 || mainEffect.objBeKillMain == null || mainEffect.objBeKillMain.ID != s || s2 != 0) continue;
                            mainEffect.isStop = true;
                            return;
                        }
                        GameScreen.addEffectKill(87, mainObject.x, mainObject.y, s2, s, by2);
                        break;
                    }
                    case 3: {
                        GameScreen.addEffectEndKill(43, mainObject.x, mainObject.y);
                        if (by3 <= 0) break;
                        LoadMap.timeVibrateScreen = by3;
                        break;
                    }
                    case 4: {
                        GameScreen.checkAddEff(94, mainObject.x, mainObject.y, s2, s, by2, s3, (byte)1, false);
                        if (by3 <= 0) break;
                        LoadMap.timeVibrateScreen = by3;
                        break;
                    }
                    case 5: {
                        GameScreen.checkAddEff(100, mainObject.x, mainObject.y, s2, s, by2, s3, (byte)1, false);
                        if (by3 <= 0) break;
                        LoadMap.timeVibrateScreen = by3;
                        break;
                    }
                    case 6: {
                        GameScreen.checkAddEff(101, mainObject.x, mainObject.y, s2, s, by2, s3, (byte)1, false);
                        if (by3 <= 0) break;
                        LoadMap.timeVibrateScreen = by3;
                        break;
                    }
                    case 7: {
                        GameScreen.checkAddEff(102, mainObject.x, mainObject.y, s2, s, by2, s3, (byte)1, false);
                        if (by3 <= 0) break;
                        LoadMap.timeVibrateScreen = by3;
                        break;
                    }
                    case 8: {
                        GameScreen.checkAddEff(107, mainObject.x, mainObject.y, s2, s, by2, s3, (byte)1, false);
                        if (by3 <= 0) break;
                        LoadMap.timeVibrateScreen = by3;
                        break;
                    }
                    case 9: {
                        mainObject.beginFire();
                        GameScreen.checkAddEff(103, mainObject.x, mainObject.y, s2, s, by2, s3, (byte)1, false);
                        if (by3 <= 0) break;
                        LoadMap.timeVibrateScreen = by3;
                        break;
                    }
                    case 10: {
                        mainObject.addnewBuff(12, s2);
                        if (by3 <= 0) break;
                        LoadMap.timeVibrateScreen = by3;
                        break;
                    }
                    case 11: {
                        mainObject.addnewBuff(13, s2);
                        if (by3 <= 0) break;
                        LoadMap.timeVibrateScreen = by3;
                        break;
                    }
                    case 12: {
                        mainObject.addnewBuff(11, s2);
                        if (by3 <= 0) break;
                        LoadMap.timeVibrateScreen = by3;
                        break;
                    }
                    case 13: {
                        mainObject.addnewBuff(14, s2);
                        if (by3 <= 0) break;
                        LoadMap.timeVibrateScreen = by3;
                    }
                }
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public void EffWeather(Message message) {
        try {
            byte by = message.reader().readByte();
            if (by == -1) {
                for (int i = 0; i < GameScreen.vecWeather.size(); ++i) {
                    AnimateEffect animateEffect = (AnimateEffect)GameScreen.vecWeather.elementAt(i);
                    if (animateEffect.type != 0 && animateEffect.type != 2 && animateEffect.type != 1 && animateEffect.type != 4) continue;
                    animateEffect.stop();
                }
            } else {
                short s = message.reader().readShort();
                short s2 = message.reader().readShort();
                if (s2 == 0) {
                    for (int i = 0; i < GameScreen.vecWeather.size(); ++i) {
                        AnimateEffect animateEffect = (AnimateEffect)GameScreen.vecWeather.elementAt(i);
                        if (animateEffect.type != by) continue;
                        animateEffect.isStop = true;
                        return;
                    }
                } else if (by == 0 || by == 2 || by == 1 || by == 4) {
                    for (int i = 0; i < GameScreen.vecWeather.size(); ++i) {
                        AnimateEffect animateEffect = (AnimateEffect)GameScreen.vecWeather.elementAt(i);
                        if (animateEffect.type != by) continue;
                        animateEffect.isStop = true;
                        break;
                    }
                    GameScreen.AddEffWeather(by, true, s, s2);
                }
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public void Num_Eff(Message message) {
        try {
            byte by = message.reader().readByte();
            short s = message.reader().readShort();
            String string = message.reader().readUTF();
            byte by2 = message.reader().readByte();
            MainObject mainObject = MainObject.get_Object(s, by);
            if (mainObject != null && !mainObject.isRemove) {
                GameScreen.addEffectNum(string, mainObject.x, mainObject.y - mainObject.hOne / 2, 8, by2, s);
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public void Rebuild_Wing(Message message) {
        try {
            byte by = message.reader().readByte();
            if (by == 0) {
                int n;
                int n2;
                TabRebuildItem.isChetao = message.reader().readInt();
                TabRebuildItem.nameWing = message.reader().readUTF();
                TabRebuildItem.priceWing = message.reader().readInt();
                TabRebuildItem.lvReWing = message.reader().readShort();
                TabRebuildItem.timeUseWing = message.reader().readInt();
                int n3 = message.reader().readByte();
                TabRebuildItem.dataWing = new DataRebuildItem[n3];
                for (n2 = 0; n2 < n3; ++n2) {
                    short s = message.reader().readShort();
                    n = message.reader().readShort();
                    TabRebuildItem.dataWing[n2] = new DataRebuildItem(s, (short)n);
                }
                TabRebuildItem.numWingMaterialInven = new int[n3];
                for (n2 = 0; n2 < TabRebuildItem.dataWing.length; ++n2) {
                    Item item;
                    MainItem mainItem = Item.getMaterial(TabRebuildItem.dataWing[n2].id);
                    n = 0;
                    if (mainItem != null && (item = Item.getItemInventory(mainItem.ItemCatagory, (short)mainItem.Id)) != null) {
                        n = item.numPotion;
                    }
                    TabRebuildItem.numWingMaterialInven[n2] = n;
                }
            } else if (by == 1) {
                String string = message.reader().readUTF();
                TabRebuildItem.itemWing = null;
                mVector mVector2 = new mVector("ReadMessenge vec12");
                TabShopNew tabShopNew = new TabShopNew(Item.VecInvetoryPlayer, 0, T.tabhanhtrang, -1, TabShopNew.INVEN_AND_WING);
                mVector2.addElement(tabShopNew);
                TabRebuildItem tabRebuildItem = null;
                tabRebuildItem = new TabRebuildItem(string, TabRebuildItem.TYPE_REBUILD_WING);
                mVector2.addElement(tabRebuildItem);
                GameCanvas.shopNpc = new TabScreenNew();
                GameCanvas.shopNpc.selectTab = 1;
                GameCanvas.shopNpc.addMoreTab(mVector2);
                GameCanvas.shopNpc.Show(GameScreen.gI());
                GameCanvas.end_Dialog();
                ((MainTabNew)tabRebuildItem).init();
            } else if (by == 2) {
                short s = message.reader().readShort();
                if (s == -1) {
                    TabRebuildItem.itemWing = null;
                } else {
                    Item item = Item.getItemInventory(3, s);
                    if (item != null && TabRebuildItem.isBeginEff == 0) {
                        TabRebuildItem.itemWing = MainItem.MainItem_Item(s, item.itemNameExcludeLv, item.imageId, item.tier, item.colorNameItem, item.classcharItem, 3, item.mInfo, item.type_Only_Item, false, item.IdTem, item.priceItem, (short)item.LvItem, item.canSell, item.canTrade, 0, (byte)0, (byte)0);
                    }
                }
            } else if (by == 5) {
                GameCanvas.end_Dialog();
                TabRebuildItem.contentShow = message.reader().readUTF();
                TabRebuildItem.idWingOk = message.reader().readShort();
                TabRebuildItem.isBeginEff = 1;
            } else if (by == 4) {
                GameCanvas.end_Dialog();
                String string = message.reader().readUTF();
                GameCanvas.menu2.startAt_NPC(null, string, Menu2.IdNPCLast, (byte)2, false, 0);
            } else if (by == 6) {
                TabRebuildItem.itemWing = null;
                TabRebuildItem.dataWing = null;
                TabRebuildItem.priceWing = 0;
                TabRebuildItem.timeUseWing = 0;
                TabRebuildItem.nameWing = "";
                TabRebuildItem.isChetao = 0;
                TabRebuildItem.lvReWing = 0;
                TabRebuildItem.idWingOk = 0;
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public void Open_Box(Message message) {
        mSystem.outz("<<<<<<<<< mo ruong >>>>>>>>>>>>");
        try {
            String string = message.reader().readUTF();
            int n = message.reader().readByte();
            MainItem[] mainItemArray = new MainItem[n];
            for (int i = 0; i < n; ++i) {
                String string2 = message.reader().readUTF();
                short by = message.reader().readShort();
                int n2 = message.reader().readInt();
                byte by2 = message.reader().readByte();
                byte by3 = message.reader().readByte();
                byte by4 = message.reader().readByte();
                mainItemArray[i] = new MainItem(string2, by, n2, by2, by3, by4);
            }
            String string3 = message.reader().readUTF();
            byte by5 = message.reader().readByte();
            byte by = message.reader().readByte();
            if (n == 0) {
                return;
            }
            GameCanvas.start_Open_Box(string, string3, mainItemArray, by5, by);
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public void UpdateDataAndroid(Message message) {
        mSystem.outz("--------- Nhan message -57");
        try {
            GameCanvas.IndexCharPar = message.reader().readShort();
            short s = message.reader().readShort();
            GameCanvas.start_Update_Data();
            MsgDialog.curupdate = 0;
            MsgDialog.maxupdate = s;
            mSystem.outz("==========" + GameCanvas.IndexCharPar);
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void SoSanhDataAndroid(Message message) {
        mSystem.outz(" -------------------- Nhan message - 54");
        try {
            GameCanvas.IndexCharPar = message.reader().readShort();
            int n = message.reader().readShort();
            int n2 = 0;
            for (int i = 0; i < n; ++i) {
                byte by = message.reader().readByte();
                short s = message.reader().readShort();
                int n3 = message.reader().readInt();
                byte[] byArray = null;
                try {
                    boolean bl = false;
                    byArray = CRes.loadRMS("img_data_char_" + by + "_" + s);
                    if (byArray == null) {
                        bl = true;
                    } else {
                        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byArray);
                        DataInputStream dataInputStream = new DataInputStream(byteArrayInputStream);
                        short s2 = dataInputStream.readShort();
                        if (n3 != s2) {
                            bl = true;
                        }
                    }
                    if (!bl) continue;
                    GlobalService.gI().load_image_data_part_char(by, s);
                    ++n2;
                    continue;
                }
                catch (Exception exception) {
                    // empty catch block
                }
            }
            if (n2 > 0) {
                GameCanvas.start_Update_Data();
                MsgDialog.curupdate = 0;
                MsgDialog.maxupdate = n2;
            }
            mSystem.outz("==========" + GameCanvas.IndexCharPar);
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public void useMount(Message message) {
        try {
            byte by = message.reader().readByte();
            switch (by) {
                case 0: {
                    byte by2 = message.reader().readByte();
                    short s = message.reader().readShort();
                    MainObject mainObject = MainObject.get_Object(s, (byte)0);
                    if (mainObject == null) break;
                    mainObject.typeMount = by2;
                    break;
                }
                case 1: {
                    byte by3 = message.reader().readByte();
                    byte by4 = message.reader().readByte();
                    short s = message.reader().readShort();
                    short s2 = message.reader().readShort();
                    MainObject mainObject = MainObject.get_Object(s, (byte)1);
                    if (mainObject == null) break;
                    mainObject.setEffectauto(by4, s2, by3);
                    break;
                }
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public void monsterSkillInfo(Message message) {
        try {
            int n = message.reader().readByte();
            mSystem.outz("size = " + n);
            for (int i = 0; i < n; ++i) {
                byte by = message.reader().readByte();
                byte by2 = message.reader().readByte();
                short s = message.reader().readShort();
                Monster_Skill.hashMonsterSkillInfo.put("" + by, new Short(s));
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public void monsterDetonate(Message message) {
        try {
            byte by = message.reader().readByte();
            byte by2 = message.reader().readByte();
            short s = message.reader().readShort();
            short s2 = message.reader().readShort();
            short s3 = message.reader().readShort();
            byte by3 = message.reader().readByte();
            byte by4 = message.reader().readByte();
            byte by5 = message.reader().readByte();
            byte by6 = message.reader().readByte();
            byte by7 = message.reader().readByte();
            byte by8 = message.reader().readByte();
            MainMonster mainMonster = (MainMonster)MainObject.get_Object(s, by2);
            block1 : switch (by) {
                case 0: {
                    mainMonster.vMax = 8;
                    mainMonster.toX = s2;
                    mainMonster.toY = s3;
                    mainMonster.isDetonateInDest = true;
                    break;
                }
                case 1: {
                    switch (by3) {
                        case 0: {
                            GameScreen.addEffectEndKill(38, mainMonster.x, mainMonster.y - 20);
                            break;
                        }
                        case 1: {
                            GameScreen.addEffectKill(85, mainMonster.x, mainMonster.y, by4, s, by2);
                            break;
                        }
                        case 2: {
                            GameScreen.addEffectKill(87, mainMonster.x, mainMonster.y, by4, s, by2);
                            break;
                        }
                        case 3: {
                            GameScreen.addEffectEndKill(36, mainMonster.x, mainMonster.y);
                        }
                    }
                    mainMonster.x = s2;
                    mainMonster.y = s3;
                    switch (by5) {
                        case 0: {
                            GameScreen.addEffectEndKill(38, mainMonster.x, mainMonster.y - 20);
                            break block1;
                        }
                        case 1: {
                            GameScreen.addEffectKill(85, mainMonster.x, mainMonster.y, by6, s, by2);
                            break block1;
                        }
                        case 2: {
                            GameScreen.addEffectKill(87, mainMonster.x, mainMonster.y, by6, s, by2);
                            break block1;
                        }
                        case 3: {
                            mainMonster.isDetonateInDest = true;
                        }
                    }
                    break;
                }
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void petGainXP(Message message) {
        try {
            short s = message.reader().readShort();
            short s2 = message.reader().readShort();
            short s3 = message.reader().readShort();
            int n = message.reader().readInt();
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public void receiveLotteryReward(Message message) {
        try {
            byte by = message.reader().readByte();
            if (by == 0) {
                Item.VecLotteryReward.removeAllElements();
                int n = message.reader().readByte();
                for (int i = 0; i < n; ++i) {
                    short s;
                    byte by2 = message.reader().readByte();
                    if (by2 == 3) {
                        String string = message.reader().readUTF();
                        s = message.reader().readByte();
                        short s2 = message.reader().readShort();
                        byte by3 = message.reader().readByte();
                        short s3 = message.reader().readShort();
                        byte by4 = message.reader().readByte();
                        short s4 = message.reader().readShort();
                        byte by5 = message.reader().readByte();
                        int n2 = message.reader().readByte();
                        MainInfoItem[] mainInfoItemArray = new MainInfoItem[n2];
                        for (int j = 0; j < n2; ++j) {
                            int n3 = message.reader().readUnsignedByte();
                            int n4 = message.reader().readInt();
                            mainInfoItemArray[j] = new MainInfoItem(n3, n4);
                        }
                        MainItem mainItem = new MainItem(s2, string, s3, by4, by5, s, by2, mainInfoItemArray, by3, false, -1, 0L, s4, 0, 0, -1, 0, 0);
                        Item.VecLotteryReward.addElement(mainItem);
                        continue;
                    }
                    if (by2 == 4) {
                        short s5 = message.reader().readShort();
                        s = message.reader().readShort();
                        MainTemplateItem mainTemplateItem = (MainTemplateItem)MainTemplateItem.hashPotionTem.get("" + s5);
                        MainItem mainItem = new MainItem(s5, mainTemplateItem.IconId, mainTemplateItem.name, mainTemplateItem.contentPotion, s, 4, mainTemplateItem.PricePoition, mainTemplateItem.typePotion, mainTemplateItem.moneyType, 0, 0);
                        Item.VecLotteryReward.addElement(mainItem);
                        continue;
                    }
                    if (by2 != 7) continue;
                    short s6 = message.reader().readShort();
                    s = message.reader().readShort();
                    MainItem mainItem = Item.getMaterial(s6);
                    if (mainItem != null) {
                        MainItem mainItem2 = MainItem.MainItem_Material(s6, mainItem.itemName, mainItem.imageId, 7, mainItem.priceItem, mainItem.priceType, mainItem.content, mainItem.value, mainItem.typeMaterial, s, (byte)0, (byte)0);
                        Item.VecLotteryReward.addElement(mainItem2);
                        continue;
                    }
                    MainItem mainItem3 = new MainItem();
                    mainItem3.Id = s6;
                    mainItem3.ItemCatagory = 7;
                    mainItem3.numPotion = s;
                    Item.VecLotteryReward.addElement(mainItem3);
                }
                mVector mVector2 = new mVector("ReadMessenge vec13");
                TabLottery tabLottery = new TabLottery(T.moly, Item.VecLotteryReward, 0);
                mVector2.addElement(tabLottery);
                GameCanvas.shopNpc = new TabScreenNew();
                GameCanvas.shopNpc.selectTab = 0;
                GameCanvas.shopNpc.addMoreTab(mVector2);
                GameCanvas.shopNpc.Show(GameScreen.gI());
                GameCanvas.end_Dialog();
            } else if (by == 1) {
                byte by6 = message.reader().readByte();
                byte by7 = message.reader().readByte();
                TabLottery.changeSection((byte)1);
                TabLottery.setLuckyNumber(by7);
                TabLottery.isReady = false;
                mSystem.outz("itemIndex = " + by6);
                mSystem.outz("posIndex = " + by7);
            } else if (by == 2) {
                byte by8 = message.reader().readByte();
                if (by8 == 1) {
                    byte by9 = message.reader().readByte();
                    byte by10 = message.reader().readByte();
                    TabLottery.setLuckyNumber(by10);
                    TabLottery.isWin = true;
                } else {
                    byte by11 = message.reader().readByte();
                    byte by12 = message.reader().readByte();
                    TabLottery.setLuckyNumber(by11);
                    TabLottery.isWin = false;
                }
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void writeUserAccountInfoToRMS(Message message) {
        try {
            String string = message.reader().readUTF();
            String string2 = message.reader().readUTF();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            dataOutputStream.writeUTF(string);
            dataOutputStream.writeUTF(string2);
            CRes.saveRMS("user_pass", byteArrayOutputStream.toByteArray());
            dataOutputStream.close();
            LoginScreen.userSv = string;
            LoginScreen.tfusername.setText(string);
            LoginScreen.tfpassword.setText(string2);
            LoginScreen.username = string;
            LoginScreen.password = string2;
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public void UpdateInfoArena(Message message) {
        try {
            byte by = message.reader().readByte();
            byte by2 = message.reader().readByte();
            short s = message.reader().readShort();
            byte by3 = message.reader().readByte();
            GameScreen.infoGame.setSttArena(by, by2, s);
            if (by3 == 0) {
                long l;
                GameScreen.timeArena = l = message.reader().readLong();
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void updateMarkKiller(Message message) {
        try {
            byte by = message.reader().readByte();
            if (by == 0) {
                short s = message.reader().readShort();
                short s2 = message.reader().readShort();
                MainObject mainObject = MainObject.get_Object(s, (byte)0);
                if (mainObject != null) {
                    mainObject.markKiller = s2;
                }
            } else if (by == 1) {
                ItemMap.isPaintDieHouseArena = true;
            } else if (by == 2) {
                short s = message.reader().readShort();
                byte by2 = message.reader().readByte();
                MainObject mainObject = MainObject.get_Object(s, (byte)1);
                if (mainObject != null && by2 != 0) {
                    mainObject.addeffAuto(by2, mainObject.x, mainObject.y, 0, 0, by2 == 57 ? 2 : 0, 0);
                }
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public void useItemArena(Message message) {
        try {
            byte by = message.reader().readByte();
            switch (by) {
                case 0: {
                    byte by2 = message.reader().readByte();
                    if (by2 == 1) {
                        EffectMonster.buffDame = true;
                        break;
                    }
                    EffectMonster.buffDame = false;
                    break;
                }
                case 1: {
                    short s = message.reader().readShort();
                    short s2 = message.reader().readShort();
                    MainObject mainObject = MainObject.get_Object(s2, (byte)0);
                    if (mainObject == null) break;
                    mainObject.idImageHenshin = s;
                    break;
                }
                case 2: {
                    byte by3 = message.reader().readByte();
                    short s = message.reader().readShort();
                    MainObject mainObject = MainObject.get_Object(s, (byte)0);
                    if (mainObject == null) break;
                    if (by3 == 1) {
                        mainObject.isPaint_No = true;
                        break;
                    }
                    mainObject.isPaint_No = false;
                    break;
                }
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public void npcServer(Message message) {
        try {
            int n;
            int n2;
            int n3;
            short s = message.reader().readShort();
            short s2 = message.reader().readShort();
            short s3 = message.reader().readShort();
            short s4 = message.reader().readShort();
            byte by = message.reader().readByte();
            byte by2 = message.reader().readByte();
            byte by3 = message.reader().readByte();
            byte by4 = message.reader().readByte();
            byte by5 = message.reader().readByte();
            String string = message.reader().readUTF();
            String string2 = message.reader().readUTF();
            byte by6 = message.reader().readByte();
            byte by7 = message.reader().readByte();
            byte[] byArray = new byte[by7];
            for (n3 = 0; n3 < byArray.length; ++n3) {
                byArray[n3] = (byte)message.reader().readShort();
            }
            n3 = message.reader().readShort();
            byte[] byArray2 = new byte[n3];
            for (n2 = 0; n2 < byArray2.length; ++n2) {
                byArray2[n2] = message.reader().readByte();
            }
            n2 = message.reader().readByte();
            byte[] byArray3 = new byte[n2];
            for (n = 0; n < byArray3.length; ++n) {
                byArray3[n] = message.reader().readByte();
            }
            n = message.reader().readByte();
            for (int i = 0; i < GameScreen.Vecplayers.size(); ++i) {
                MainObject mainObject = (MainObject)GameScreen.Vecplayers.elementAt(i);
                if (!mainObject.isNPC_server() || mainObject.ID != n) continue;
                mainObject.setInfo(by6, s, s2, by3, by4, by5, string, string2, s3, s4, by, by2, byArray, byArray2, byArray3);
                return;
            }
            NPCserver nPCserver = new NPCserver(by6, s, s2, by3, by4, by5, string, string2, s3, s4, by, by2, byArray, byArray2, byArray3);
            nPCserver.ID = n;
            GameScreen.Vecplayers.addElement(nPCserver);
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void useShip(Message message) {
        try {
            byte by = message.reader().readByte();
            short s = message.reader().readShort();
            GameScreen.player.useShip = true;
            if (by == 0) {
                GameScreen.infoGame.isShipMove = true;
                ShipScr.gI().setShipScr(s);
            } else if (by == 2) {
                ShipScr.gI().isSpeed = true;
            }
        }
        catch (IOException iOException) {
            iOException.printStackTrace();
        }
    }

    public void ThachDau(Message message) {
        try {
            byte by = message.reader().readByte();
            switch (by) {
                case 0: {
                    short s = message.reader().readShort();
                    int n = message.reader().readInt();
                    int n2 = message.reader().readInt();
                    MainObject mainObject = MainObject.get_Object(s, (byte)0);
                    if (mainObject == null) break;
                    mainObject.mp = n;
                    mainObject.maxMp = n2;
                    break;
                }
                case 1: {
                    byte by2 = message.reader().readByte();
                    PaintInfoGameScreen.timeThachdau = mSystem.currentTimeMillis() / 1000L + (long)by2;
                    break;
                }
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public void StoreInfo(Message message) {
        try {
            byte by = message.reader().readByte();
            switch (by) {
                case 1: {
                    short s = message.reader().readShort();
                    String string = message.reader().readUTF();
                    MainObject mainObject = GameScreen.findChar(s);
                    if (mainObject == null) break;
                    mainObject.setNameStore(string);
                    break;
                }
                case 2: {
                    short s = message.reader().readShort();
                    MainObject mainObject = GameScreen.findChar(s);
                    if (GameScreen.player != null && s == GameScreen.player.ID) {
                        Item.VecItem_Sell_in_store.removeAllElements();
                        Item.VecItemSell.removeAllElements();
                    }
                    if (mainObject == null) break;
                    mainObject.removeNameStore();
                    break;
                }
                case 3: {
                    Item.VecItemSell.removeAllElements();
                    int n = message.reader().readByte();
                    for (int i = 0; i < n; ++i) {
                        byte by2;
                        byte by3;
                        short s;
                        byte by4 = message.reader().readByte();
                        if (by4 == 3) {
                            byte by5;
                            s = message.reader().readShort();
                            String string = message.reader().readUTF();
                            by3 = message.reader().readByte();
                            by2 = message.reader().readByte();
                            short s2 = message.reader().readShort();
                            byte by6 = message.reader().readByte();
                            short s3 = message.reader().readShort();
                            byte by7 = message.reader().readByte();
                            byte by8 = message.reader().readByte();
                            MainInfoItem[] mainInfoItemArray = new MainInfoItem[by8];
                            for (by5 = 0; by5 < by8; ++by5) {
                                int n2 = message.reader().readUnsignedByte();
                                int n3 = message.reader().readInt();
                                mainInfoItemArray[by5] = new MainInfoItem(n2, n3);
                            }
                            by5 = message.reader().readByte();
                            MainItem mainItem = MainItem.MainItem_Item(s, string, s2, by6, by7, by3, 3, mainInfoItemArray, by2, false, s, 0L, s3, (byte)0, (byte)0, 0, by5, (byte)0);
                            mainItem.isSell = true;
                            Item.VecItemSell.addElement(mainItem);
                        }
                        if (by4 == 7) {
                            s = message.reader().readShort();
                            short s4 = message.reader().readShort();
                            by3 = message.reader().readByte();
                            by2 = message.reader().readByte();
                            long l = message.reader().readLong();
                            MainItem mainItem = Item.getMaterial(s);
                            if (mainItem != null) {
                                MainItem mainItem2 = MainItem.MainItem_Material(s, mainItem.itemName, mainItem.imageId, 7, l, (byte)0, mainItem.content, mainItem.value, mainItem.typeMaterial, s4, by3, by2);
                                mainItem2.isSell = true;
                                Item.VecItemSell.addElement(mainItem2);
                            } else {
                                MainItem mainItem3 = new MainItem();
                                mainItem3.Id = s;
                                mainItem3.numPotion = s4;
                                mainItem3.canSell = by3;
                                mainItem3.canTrade = by2;
                                mainItem3.priceItem = l;
                                mainItem3.priceType = 0;
                                mainItem3.isSell = true;
                                mainItem3.ItemCatagory = 7;
                                Item.put_Material(s);
                                Item.VecItemSell.addElement(mainItem3);
                            }
                        }
                        if (by4 != 4) continue;
                        s = message.reader().readShort();
                        short s5 = message.reader().readShort();
                        long l = message.reader().readInt();
                        MainTemplateItem mainTemplateItem = MainTemplateItem.getPotionTem(s);
                        if (mainTemplateItem == null) continue;
                        MainItem mainItem = new MainItem(mainTemplateItem.ID, mainTemplateItem.IconId, mainTemplateItem.name, mainTemplateItem.contentPotion + ". " + T.price + " " + MainItem.getDotNumber(l) + " " + T.coin, s5, 4, l, mainTemplateItem.typePotion, mainTemplateItem.moneyType, 0, 0);
                        Item.VecItemSell.addElement(mainItem);
                    }
                    break;
                }
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public void MiNuongInfo(Message message) {
        try {
            byte by = message.reader().readByte();
            switch (by) {
                case 0: {
                    short s = message.reader().readShort();
                    String string = message.reader().readUTF();
                    MainObject mainObject = GameScreen.findMonster(s);
                    if (mainObject == null) break;
                    mainObject.SetnameOwner(string);
                    break;
                }
                case 1: {
                    short s = message.reader().readShort();
                    short s2 = message.reader().readShort();
                    GameScreen.infoGame.idCharLoiDai1 = s;
                    GameScreen.infoGame.idCharLoiDai2 = s2;
                    break;
                }
                case 2: {
                    short s = message.reader().readShort();
                    short s3 = message.reader().readShort();
                    short s4 = message.reader().readShort();
                    short s5 = message.reader().readShort();
                    int n = message.reader().readByte();
                    MainObject mainObject = GameScreen.findObj(s);
                    if (mainObject == null) break;
                    for (int i = 0; i < n; ++i) {
                        short s6 = message.reader().readShort();
                        int n2 = message.reader().readInt();
                        MainObject mainObject2 = GameScreen.findObj(s6);
                        if (mainObject2 == null) continue;
                        GameScreen.startNewMagicBeam(15, mainObject, mainObject2, mainObject.x, mainObject.y, n2, s3, s4, s5);
                    }
                    break;
                }
                case 3: {
                    break;
                }
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public void infoclanChiemthanh(Message message) {
        try {
            byte by = message.reader().readByte();
            switch (by) {
                case 0: {
                    int n = message.reader().readInt();
                    String string = message.reader().readUTF();
                    PaintInfoGameScreen.idicon = n;
                    PaintInfoGameScreen.nameclan = string;
                    break;
                }
                case 1: {
                    GameScreen.vecTimecountDown.removeAllElements();
                    int n = message.reader().readByte();
                    for (int i = 0; i < n; ++i) {
                        short s = message.reader().readShort();
                        String string = message.reader().readUTF();
                        if (s <= 0) continue;
                        TimecountDown timecountDown = new TimecountDown(s, string, GameCanvas.w / 2, GameCanvas.hText + 10 + i * GameCanvas.hText);
                        GameScreen.vecTimecountDown.addElement(timecountDown);
                    }
                    break;
                }
                case 2: {
                    int n = message.reader().readByte();
                    if (n > 0) {
                        GameScreen.textServer = new String[n];
                        for (int i = 0; i < n; ++i) {
                            GameScreen.textServer[i] = message.reader().readUTF();
                        }
                        break;
                    }
                    GameScreen.textServer = null;
                    break;
                }
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public void onHopRac(Message message) {
        try {
            byte by = message.reader().readByte();
            switch (by) {
                case 0: {
                    TabRebuildItem.vecGem.removeAllElements();
                    int n = message.reader().readByte();
                    short s = message.reader().readShort();
                    for (int i = 0; i < n; ++i) {
                        MainItem mainItem = Item.getMaterial(s);
                        if (mainItem != null) {
                            TabRebuildItem.vecGem.addElement(mainItem);
                            continue;
                        }
                        MainItem mainItem2 = new MainItem();
                        mainItem2.Id = s;
                        TabRebuildItem.itemRe.ItemCatagory = 7;
                        TabRebuildItem.vecGem.addElement(mainItem2);
                    }
                    break;
                }
                case 1: {
                    TabRebuildItem.vecGem.removeAllElements();
                    break;
                }
                case 2: {
                    GameCanvas.end_Dialog();
                    TabRebuildItem.isBeginEff = 1;
                    TabRebuildItem.isNextRebuild = message.reader().readByte();
                    TabRebuildItem.contentShow = message.reader().readUTF();
                    TabRebuildItem.vecGem.removeAllElements();
                    short s = message.reader().readShort();
                    byte by2 = message.reader().readByte();
                    if (s == -1 || by2 != 7) break;
                    MainItem mainItem = Item.getMaterial(s);
                    if (mainItem != null) {
                        TabRebuildItem.itemRe = mainItem;
                        break;
                    }
                    TabRebuildItem.itemRe = new MainItem();
                    TabRebuildItem.itemRe.Id = s;
                    TabRebuildItem.itemRe.ItemCatagory = 7;
                    break;
                }
                case 3: {
                    int n;
                    int n2;
                    GameCanvas.end_Dialog();
                    TabRebuildItem.isBeginEff = 1;
                    TabRebuildItem.itemRe = null;
                    TabRebuildItem.isNextRebuild = message.reader().readByte();
                    TabRebuildItem.contentShow = message.reader().readUTF();
                    TabRebuildItem.vecGem.removeAllElements();
                    if (TabRebuildItem.isNextRebuild != 3) break;
                    byte by4 = message.reader().readByte();
                    String string = message.reader().readUTF();
                    byte by5 = message.reader().readByte();
                    short s = message.reader().readShort();
                    byte by6 = message.reader().readByte();
                    short s2 = message.reader().readShort();
                    byte by7 = message.reader().readByte();
                    short s3 = message.reader().readShort();
                    byte by8 = message.reader().readByte();
                    byte by9 = message.reader().readByte();
                    byte by10 = message.reader().readByte();
                    int n3 = message.reader().readByte();
                    MainInfoItem[] mainInfoItemArray = new MainInfoItem[n3];
                    for (n2 = 0; n2 < n3; ++n2) {
                        int by3 = message.reader().readUnsignedByte();
                        n = message.reader().readInt();
                        mainInfoItemArray[n2] = new MainInfoItem(by3, n);
                    }
                    n2 = message.reader().readInt();
                    byte by2 = message.reader().readByte();
                    n = message.reader().readByte();
                    int n4 = -1;
                    String string2 = "-1";
                    if (n == 1) {
                        n4 = message.reader().readInt();
                        string2 = message.reader().readUTF();
                    }
                    byte by3 = message.reader().readByte();
                    MainItem mainItem = null;
                    mainItem = new MainItem(s, string, s2, by7, by8, by5, by4, mainInfoItemArray, by6, false, -1, 0L, s3, by9, by10, n2, 0, by2, n4, Long.parseLong(string2));
                    mainItem.setCanShell_notCanTrade(by3);
                    TabRebuildItem.itemRe = mainItem;
                    break;
                }
                case 4: {
                    int n = message.reader().readByte();
                    TabRebuildItem.vecGem.removeAllElements();
                    for (int i = 0; i < n; ++i) {
                        short s = message.reader().readShort();
                        short s4 = message.reader().readShort();
                        int n5 = 7;
                        if (s == -1 || n5 != 7) continue;
                        TabRebuildItem.numColor[i] = ReadMessenge.getColorNum(s, s4);
                        TabRebuildItem.numofGem[i] = ReadMessenge.getInfoNum(s, s4);
                        MainItem mainItem = Item.getMaterial(s);
                        if (mainItem != null) {
                            TabRebuildItem.vecGem.addElement(mainItem);
                            continue;
                        }
                        MainItem mainItem3 = new MainItem();
                        mainItem3.Id = s;
                        mainItem3.ItemCatagory = 7;
                        TabRebuildItem.vecGem.addElement(mainItem3);
                    }
                    break;
                }
                case 5: {
                    GameCanvas.end_Dialog();
                    TabRebuildItem.isBeginEff = 1;
                    TabRebuildItem.itemRe = null;
                    TabRebuildItem.isNextRebuild = message.reader().readByte();
                    TabRebuildItem.contentShow = message.reader().readUTF();
                    TabRebuildItem.vecGem.removeAllElements();
                    short s = message.reader().readShort();
                    Item item = Item.getItemInventory(3, s);
                    if (item == null) break;
                    TabRebuildItem.itemRe = MainItem.MainItem_Item(s, item.itemNameExcludeLv, item.imageId, item.tier, item.colorNameItem, item.classcharItem, 3, item.mInfo, item.type_Only_Item, false, item.IdTem, item.priceItem, (short)item.LvItem, item.canSell, item.canTrade, 0, (byte)0, (byte)0);
                    break;
                }
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public void Material_Template(Message message) {
        try {
            short s = message.reader().readShort();
            short s2 = message.reader().readShort();
            long l = message.reader().readLong();
            String string = message.reader().readUTF();
            String string2 = message.reader().readUTF();
            byte by = message.reader().readByte();
            byte by2 = message.reader().readByte();
            byte by3 = message.reader().readByte();
            short s3 = message.reader().readShort();
            byte by4 = message.reader().readByte();
            byte by5 = message.reader().readByte();
            MainItem mainItem = (MainItem)MainTemplateItem.hashMaterialTem.get(s + "");
            if (mainItem != null) {
                mainItem.setinfo(s, string, s2, 7, l, by2, string2, s3, by, (short)1, by3, by4);
                mainItem.setColorName(by5);
            }
            for (int i = 0; i < Item.VecInvetoryPlayer.size(); ++i) {
                MainItem mainItem2 = (MainItem)Item.VecInvetoryPlayer.elementAt(i);
                if (mainItem2 == null || mainItem2.ItemCatagory != 7 || mainItem2.Id != s) continue;
                mainItem2.setinfo(s, string, s2, 7, l, by2, string2, s3, by, (short)1, by3, by4);
                mainItem2.setColorName(by5);
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public void onFillRec_Time(Message message) {
        try {
            byte by = message.reader().readByte();
            switch (by) {
                case 0: {
                    GameCanvas.game.isFullScreen = false;
                    GameCanvas.game.colorRec = message.reader().readInt();
                    GameCanvas.game.xRec = message.reader().readShort();
                    GameCanvas.game.yRec = message.reader().readShort();
                    GameCanvas.game.wRec = message.reader().readShort();
                    GameCanvas.game.hRec = message.reader().readShort();
                    break;
                }
                case 1: {
                    GameCanvas.game.colorRec = message.reader().readInt();
                    GameCanvas.game.isFullScreen = true;
                    break;
                }
                case 2: {
                    byte by2 = message.reader().readByte();
                    short s = message.reader().readShort();
                    short s2 = message.reader().readShort();
                    String string = message.reader().readUTF();
                    byte by3 = 0;
                    if (by2 == 3 || by2 == 4 || by2 == 7) {
                        by3 = message.reader().readByte();
                    }
                    short s3 = message.reader().readShort();
                    short s4 = message.reader().readShort();
                    Item_Drop item_Drop = new Item_Drop(s2, by2, string, s3, s4, s, by3);
                    GameScreen.addPlayer(item_Drop);
                    break;
                }
                case 3: {
                    int n = message.reader().readInt();
                    GameCanvas.game.imgCombo = null;
                    if (n > 0) {
                        byte[] byArray = new byte[n];
                        if (n > 0) {
                            for (int i = 0; i < n; ++i) {
                                byArray[i] = message.reader().readByte();
                            }
                            GameCanvas.game.imgCombo = mImage.createImage(byArray, 0, byArray.length, "");
                            GameCanvas.game.isCombo = true;
                        }
                        break;
                    }
                    GameCanvas.game.imgCombo = null;
                    GameScreen.player.timeCombo = -1;
                    break;
                }
                case 4: {
                    GameScreen.player.resetCoolDown();
                    GameScreen.player.timeCombo = message.reader().readInt();
                    break;
                }
                case 5: {
                    byte by4 = message.reader().readByte();
                    if (by4 != 1) break;
                    this.onContinueFire();
                    break;
                }
                case 6: {
                    int n = message.reader().readInt();
                    String string = message.reader().readUTF();
                    if (n > 0) {
                        long l = mSystem.currentTimeMillis() + (long)(n * 1000);
                        GameScreen.charcountdonw = new CharCountDown(l, string);
                    }
                    if (n >= 0) break;
                    GameScreen.charcountdonw = null;
                    break;
                }
                case 7: {
                    short s = message.reader().readShort();
                    short s5 = message.reader().readShort();
                    short s6 = message.reader().readShort();
                    DataSkillEff dataSkillEff = new DataSkillEff(s, (int)s5, (int)s6, null);
                    GameScreen.vecDataeff.addElement(dataSkillEff);
                    break;
                }
                case 8: {
                    short s = message.reader().readShort();
                    byte by5 = message.reader().readByte();
                    short s7 = message.reader().readShort();
                    if (by5 == 0) {
                        MainObject mainObject = GameScreen.findChar(s7);
                        if (mainObject != null) {
                            DataSkillEff dataSkillEff = new DataSkillEff(s, mainObject.x, mainObject.y, null);
                            GameScreen.vecDataeff.addElement(dataSkillEff);
                        }
                    } else {
                        MainObject mainObject = GameScreen.findMonster(s7);
                        if (mainObject != null) {
                            DataSkillEff dataSkillEff = new DataSkillEff(s, mainObject.x, mainObject.y, null);
                            GameScreen.vecDataeff.addElement(dataSkillEff);
                        }
                    }
                    break;
                }
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public void onUpSkill(Message message) {
    }

    public void onContinueFire() {
        try {
            GameScreen.ObjFocus = null;
            GameScreen.player.nextMonster();
            GameScreen.player.dofire_();
        }
        catch (Exception exception) {
            // empty catch block
        }
    }
}

