/*
 * Decompiled with CFR 0.152.
 */
package InterfaceComponents;

import CLib.Session_ME;
import CLib.mFont;
import CLib.mGraphics;
import CLib.mSound;
import CLib.mSystem;
import CLib.mVector;
import GameEffect.MainEffect;
import GameObjects.AutoGetItem;
import GameObjects.Item;
import GameObjects.MainHelp;
import GameObjects.MainItem;
import GameObjects.MainQuest;
import GameObjects.MainRMS;
import GameObjects.ObjectParty;
import GameObjects.PetItem;
import GameObjects.Player;
import GameScreen.GameScreen;
import GameScreen.LoginScreen;
import GameScreen.PaintInfoGameScreen;
import GameScreen.SelectCharScreen;
import GameScreen.TabScreenNew;
import GameScreen.WorldMapScreen;
import InterfaceComponents.ListNew;
import InterfaceComponents.MainDialog;
import InterfaceComponents.MainTabNew;
import InterfaceComponents.TabQuest;
import InterfaceComponents.TabRebuildItem;
import InterfaceComponents.TabShopNew;
import InterfaceComponents.TabSkillsNew;
import InterfaceComponents.iCommand;
import Main.GameCanvas;
import Main.GameMidlet;
import Model.AvMain;
import Model.CRes;
import Model.FrameImage;
import Model.T;
import Model.mCamera;
import Skill.HotKey;
import Skill.Skill;
import Thread_More.LoadMap;
import com.silverknight.TemMidlet;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import netcommand.global.GlobalLogicHandler;
import netcommand.global.GlobalService;

public class MsgDialog
extends MainDialog {
    MsgDialog LastDia;
    String status;
    String link = "";
    String nameShow;
    int wStatus;
    public static int maxSizeParty;
    public int maxSizeList;
    int fWait = 0;
    int IdQuest;
    int typeQuest;
    int idSelect;
    int idSelectBuy;
    int idCommand;
    int sizeParty;
    byte main_sub_quest;
    boolean isWaiting = false;
    boolean isSpec = false;
    public static byte MaxSkillBuff;
    public static final byte NORMAL = 0;
    public static final byte QUEST = 1;
    public static final byte PARTY = 2;
    public static final byte LIST_PARTY = 3;
    public static final byte AUTO_HP_MP = 4;
    public static final byte HELP = 5;
    public static final byte SHOW = 6;
    public static final byte AUTO_GET_ITEM = 7;
    public static final byte AUTO_BUFF = 8;
    public static final byte DIA_TIME = 9;
    public static final byte SHOW_CHANGE_ITEM = 10;
    public static final byte SHOW_OPEN_BOX = 11;
    public static final byte UPDATE_DATA = 12;
    public static final byte SET_VOLUME = 13;
    public static final byte PET_INFO = 14;
    public static final byte Info_arena = 15;
    mVector cmdList = new mVector("MsgChat cmdList");
    iCommand cmdClose = new iCommand(T.close, -1, this);
    iCommand cmdCancleQuest = new iCommand(T.cancel, 10, this);
    iCommand cmdchucnang;
    iCommand cmdHelp;
    iCommand cmdPet;
    public static FrameImage fraWaiting;
    mCamera cameraDia = new mCamera();
    ListNew list;
    int hItem = 0;
    int hWait;
    int hSpe = 0;
    public static int hPlus;
    public static int timePaintParty;
    MainQuest quest;
    public static int isUutien;
    public static int[] mHPMP;
    int archor = -1;
    byte[] mvalueItem = new byte[3];
    public static byte[] mvalueVolume;
    public static mVector vecListEvent;
    int min;
    int max;
    int hbutton;
    int wbuff = 0;
    public static int[][] Autobuff;
    mFont fontDia = mFont.tahoma_7_white;
    byte isLottery;
    int sizeButtonQuest = 1;
    short timeDia = 0;
    long timeset = 0L;
    MainItem[] datanguyenlieu;
    MainItem sanpham;
    int StepShow = 0;
    int timeShow;
    int[][] posItemNguyenlieu;
    MainItem[] itemsanpham;
    int wsizeBox;
    int indexShow1 = 0;
    int indexShow2 = 0;
    byte isFullEff = 0;
    public static final byte OPEN_BOX = 0;
    public static final byte BOX_QUEST = 1;
    public static int maxupdate;
    public static int curupdate;
    public static int wUpdate;
    public static int hUpdate;
    public static PetItem pet;
    public static byte isInven_Equip;
    public static final byte INVEN = 0;
    public static final byte EQUIP = 1;
    int xBegin;
    int w2cmd;
    boolean isTran = false;
    int yCamBegin = 0;
    public static boolean isAutologin;

    public void commandTab(int n, int n2) {
        GameCanvas.clearKeyHold();
        GameCanvas.clearKeyPressed();
        switch (n) {
            case -1: 
            case 7: {
                this.closeDialog();
                GlobalLogicHandler.timeReconnect = 0L;
                GlobalLogicHandler.isDisConect = false;
                GlobalLogicHandler.isMelogin = false;
                isAutologin = false;
                break;
            }
            case 6: {
                GameScreen.player.resetPlayer();
                GameCanvas.login.Show();
                this.closeDialog();
                GlobalLogicHandler.timeReconnect = 0L;
                GlobalLogicHandler.isDisConect = false;
                GlobalLogicHandler.isMelogin = false;
                isAutologin = false;
                break;
            }
            case 0: {
                if (GameCanvas.currentScreen != GameCanvas.login && GameCanvas.currentScreen != GameCanvas.load) {
                    if (SelectCharScreen.isSelectOk) {
                        GameCanvas.login.Show();
                        byte[] byArray = CRes.loadRMS("user_pass");
                        if (byArray != null) {
                            try {
                                LoginScreen.loadUser_Pass();
                            }
                            catch (Exception exception) {
                                // empty catch block
                            }
                            GameCanvas.connect();
                            GlobalService.gI().login(LoginScreen.tfusername.getText(), LoginScreen.tfpassword.getText(), GameMidlet.version, "0", "0", "0", SelectCharScreen.IDCHAR, LoadMap.Area);
                            GameScreen.player.resetPlayer();
                            if (WorldMapScreen.namePos == null || TabQuest.nameItemQuest == null) {
                                GlobalService.gI().send_cmd_server((byte)61);
                            }
                            this.closeDialog();
                        } else {
                            GameCanvas.login.Show();
                            this.closeDialog();
                        }
                    }
                } else {
                    if (GameCanvas.currentScreen != GameCanvas.login) {
                        GameCanvas.login.Show();
                    }
                    this.closeDialog();
                }
                GlobalLogicHandler.timeReconnect = 0L;
                GlobalLogicHandler.isDisConect = false;
                GlobalLogicHandler.isMelogin = false;
                isAutologin = false;
                break;
            }
            case 2: {
                GlobalService.gI().quest((short)this.IdQuest, this.main_sub_quest, (byte)this.typeQuest);
                this.closeDialog();
                break;
            }
            case 1: {
                this.closeDialog();
                break;
            }
            case 3: {
                GameCanvas.worldmap.Show(GameCanvas.game);
                this.closeDialog();
                break;
            }
            case 4: {
                this.setMenuParty();
                break;
            }
            case 5: {
                Player.isAutoHPMP = true;
                Player.mhotkey[Player.levelTab][4].type = HotKey.NULL;
                Player.mhotkey[Player.levelTab][3].type = HotKey.NULL;
                MainItem.setAddHotKey((byte)1, isUutien != 0);
                MainItem.setAddHotKey((byte)0, isUutien != 0);
                MainRMS.setSaveAuto();
                TabSkillsNew.saveSkill();
                this.closeDialog();
                break;
            }
            case 8: {
                if (Player.party == null) {
                    return;
                }
                if (GameScreen.player.name.compareTo(Player.party.nameMain) == 0) {
                    this.setChucNangParty();
                    break;
                }
                mVector mVector2 = new mVector("MsgChat menu");
                iCommand iCommand2 = new iCommand(T.leave, 8, this);
                mVector2.addElement(iCommand2);
                iCommand iCommand3 = new iCommand(T.chatParty, 15, this);
                mVector2.addElement(iCommand3);
                GameCanvas.menu2.startAt(mVector2, 2, T.chucnang, false, null);
                break;
            }
            case 9: {
                int n3 = -1;
                if (this.mvalueItem[0] < (byte)(T.mValueAutoItem[0].length - 1)) {
                    n3 = this.mvalueItem[0];
                }
                Player.autoItem = new AutoGetItem((byte)n3, this.mvalueItem[1], this.mvalueItem[2]);
                MainRMS.setSaveAuto();
                this.closeDialog();
                break;
            }
            case 10: {
                if (this.idSelect < 0 || this.idSelect >= MaxSkillBuff) break;
                this.setAutoBuff(this.idSelect);
                break;
            }
            case 11: {
                MsgDialog.setMusic();
                this.closeDialog();
                break;
            }
            case 15: {
                this.closeDialog();
                GlobalService.gI().arena((byte)n);
                break;
            }
            case 16: {
                this.closeDialog();
                GameScreen.player.resetAction();
                Session_ME.gI().close();
                GameCanvas.login.Show();
                GameScreen.player = new Player(0, 0, "unname", 0, 0);
                SelectCharScreen.reSelect = false;
                SelectCharScreen.Canselect = false;
                break;
            }
            default: {
                this.closeDialog();
            }
        }
    }

    public void commandPointer(int n, int n2) {
        GameCanvas.clearKeyHold();
        GameCanvas.clearKeyPressed();
        ObjectParty objectParty = null;
        switch (n) {
            case -1: {
                this.closeDialog();
                break;
            }
            case 6: {
                if (Player.party == null) {
                    return;
                }
                objectParty = (ObjectParty)Player.party.vecPartys.elementAt(this.idSelect);
                GlobalService.gI().Party((byte)3, objectParty.name);
                break;
            }
            case 7: {
                this.closeDialog();
                GlobalService.gI().Party((byte)4, "");
                break;
            }
            case 8: {
                this.closeDialog();
                GlobalService.gI().Party((byte)5, "");
                break;
            }
            case 9: {
                if (Player.party == null) {
                    return;
                }
                objectParty = (ObjectParty)Player.party.vecPartys.elementAt(this.idSelect);
                GlobalService.gI().Friend((byte)0, objectParty.name);
                break;
            }
            case 2: {
                if (Player.party == null) {
                    return;
                }
                if (this.idSelect < 0 || this.idSelect >= Player.party.vecPartys.size()) break;
                objectParty = (ObjectParty)Player.party.vecPartys.elementAt(this.idSelect);
                GameCanvas.start_Left_Dialog(T.hoivaonhom + objectParty.name + "?", new iCommand(T.gianhap, 0, this));
                break;
            }
            case 3: {
                if (Player.party == null) {
                    return;
                }
                if (this.idSelect < 0 || this.idSelect >= Player.party.vecPartys.size()) break;
                GameCanvas.start_Left_Dialog(T.hoilapnhom, new iCommand(T.gianhap, 1, this));
                break;
            }
            case 10: {
                if (this.quest == null) {
                    return;
                }
                GameCanvas.start_Left_Dialog(T.hoihuyQuest + this.quest.name, new iCommand(T.cancel, 11, this));
                break;
            }
            case 11: {
                if (this.quest == null) {
                    return;
                }
                GlobalService.gI().quest((short)this.quest.ID, (byte)(!this.quest.isMain ? 1 : 0), (byte)2);
                TabQuest.me.resetTab(true);
                if (GameScreen.help.setStep_Next(9, 0)) break;
                this.closeDialog();
                this.closeDialog();
                break;
            }
            case 12: {
                if (this.link.length() <= 0) break;
                TemMidlet.openUrl(this.link);
                break;
            }
            case 14: {
                if (Player.party == null) {
                    return;
                }
                objectParty = (ObjectParty)Player.party.vecPartys.elementAt(this.idSelect);
                GameCanvas.msgchat.addNewChat(objectParty.name, "", "", (byte)0, true);
                GameCanvas.start_Chat_Dialog();
                break;
            }
            case 15: {
                GameCanvas.msgchat.addNewChat(T.party, "", "", (byte)0, true);
                GameCanvas.start_Chat_Dialog();
                break;
            }
            case 16: {
                mVector mVector2 = new mVector("MsgChat vec");
                TabShopNew tabShopNew = new TabShopNew(Item.VecInvetoryPlayer, 0, T.choan, -1, TabShopNew.INVEN_FOOD_PET);
                tabShopNew.petCur = pet;
                mVector2.addElement(tabShopNew);
                GameCanvas.foodPet = new TabScreenNew();
                GameCanvas.foodPet.selectTab = 0;
                GameCanvas.foodPet.addMoreTab(mVector2);
                GameCanvas.foodPet.Show(GameCanvas.currentScreen);
            }
        }
    }

    public void beginDia() {
        GameScreen.player.resetVx_vy();
        this.left = null;
        this.right = null;
        this.center = null;
        this.cmdList.removeAllElements();
    }

    public void setinfo(String string, iCommand iCommand2, boolean bl) {
        int n;
        this.beginDia();
        if (iCommand2 == null) {
            GameCanvas.currentDialog = null;
        }
        this.isWaiting = false;
        this.isSpec = false;
        this.type = 0;
        this.wDia = GameCanvas.w - 30;
        if (this.wDia > 200) {
            this.wDia = 200;
        }
        this.cmdList = new mVector("MsgChat cmdlist2");
        this.cmdList.addElement(iCommand2);
        if (!bl) {
            this.cmdList.addElement(this.cmdClose);
        }
        if (this.wDia < (n = this.cmdList.size()) * iCommand.wButtonCmd + (n - 1) * 10 + 10) {
            this.wDia = n * iCommand.wButtonCmd + (n - 1) * 10 + 10;
        }
        if (this.wDia > GameCanvas.w) {
            this.wDia = GameCanvas.w;
        }
        this.strinfo = this.fontDia.splitFontArray(string, this.wDia - 20);
        this.hDia = 15 * this.strinfo.length + hPlus;
        this.xDia = GameCanvas.hw - this.wDia / 2;
        this.yDia = GameCanvas.h - GameCanvas.hCommand * 2 - this.hDia - 5;
        this.numw = (this.wDia - 6) / 32;
        this.numh = (this.hDia - 6) / 32;
        this.setPosCmdNew(0);
    }

    public void setinfoDownload(String string, String string2, boolean bl) {
        this.beginDia();
        this.link = string2;
        this.isWaiting = false;
        this.isSpec = false;
        this.type = 0;
        this.wDia = GameCanvas.w - 30;
        if (this.wDia > 200) {
            this.wDia = 200;
        }
        this.cmdList = new mVector("MsgChat cmdlist3");
        iCommand iCommand2 = new iCommand(T.yes, 12, this);
        this.cmdList.addElement(iCommand2);
        this.cmdList.addElement(this.cmdClose);
        int n = this.cmdList.size();
        if (this.wDia < n * iCommand.wButtonCmd + (n - 1) * 10 + 10) {
            this.wDia = n * iCommand.wButtonCmd + (n - 1) * 10 + 10;
        }
        if (this.wDia > GameCanvas.w) {
            this.wDia = GameCanvas.w;
        }
        this.strinfo = this.fontDia.splitFontArray(string, this.wDia - 20);
        this.hDia = 15 * this.strinfo.length + hPlus;
        this.xDia = GameCanvas.hw - this.wDia / 2;
        this.yDia = GameCanvas.h - GameCanvas.hCommand * 2 - this.hDia - 5;
        this.numw = (this.wDia - 6) / 32;
        this.numh = (this.hDia - 6) / 32;
        this.setPosCmdNew(0);
    }

    public void setinfoWait(String string, iCommand iCommand2) {
        this.beginDia();
        this.isWaiting = true;
        this.isSpec = false;
        this.type = 0;
        this.wDia = GameCanvas.w - 30;
        if (this.wDia > 200) {
            this.wDia = 200;
        }
        this.cmdList = new mVector("MsgChat cmdList7");
        this.hWait = 0;
        if (iCommand2 != null) {
            this.cmdList.addElement(iCommand2);
            this.hWait = iCommand.hButtonCmd;
        }
        this.strinfo = this.fontDia.splitFontArray(string, this.wDia - 20);
        this.hDia = 15 * this.strinfo.length + hPlus + this.hWait;
        this.xDia = GameCanvas.hw - this.wDia / 2;
        this.yDia = GameCanvas.h - GameCanvas.hCommand * 2 - this.hDia - 5;
        this.numw = (this.wDia - 6) / 32;
        this.numh = (this.hDia - 6) / 32;
        this.setPosCmdNew(0);
    }

    public void setinfo(String string, mVector mVector2) {
        this.beginDia();
        if (mVector2 == null || mVector2.size() <= 0) {
            GameCanvas.currentDialog = null;
        }
        this.isWaiting = false;
        this.isSpec = false;
        this.type = 0;
        this.cmdList = mVector2;
        this.wDia = GameCanvas.w - 30;
        if (this.wDia > 200) {
            this.wDia = 200;
        }
        int n = this.cmdList.size();
        if (this.wDia < 2 * iCommand.wButtonCmd + 10) {
            this.wDia = 2 * iCommand.wButtonCmd + 10;
        }
        if (this.wDia > GameCanvas.w) {
            this.wDia = GameCanvas.w;
        }
        int n2 = 0;
        if (this.cmdList.size() > 2) {
            n2 = iCommand.hButtonCmd;
        }
        this.strinfo = this.fontDia.splitFontArray(string, this.wDia - 20);
        this.hDia = 15 * this.strinfo.length + hPlus + n2;
        this.xDia = GameCanvas.hw - this.wDia / 2;
        this.yDia = GameCanvas.h - GameCanvas.hCommand * 2 - this.hDia - 5 + (n > 2 ? iCommand.hButtonCmd + 5 : 0);
        this.numw = (this.wDia - 6) / 32;
        this.numh = (this.hDia - 6) / 32;
        this.setPosCmdNew(0);
    }

    public void setinfoQuest(String string, String string2, int n, int n2, byte by) {
        this.beginDia();
        this.hSpe = 2;
        this.IdQuest = n;
        this.typeQuest = n2;
        this.main_sub_quest = by;
        this.isSpec = true;
        iCommand iCommand2 = new iCommand(T.nhan, 2);
        if (n2 == 1) {
            iCommand2.caption = T.tra;
            this.cmdList.addElement(iCommand2);
        } else {
            this.cmdList.addElement(iCommand2);
            iCommand iCommand3 = new iCommand(T.close, 1);
            if (by == 1) {
                this.cmdList.addElement(iCommand3);
            }
        }
        this.status = string2;
        this.wStatus = mFont.tahoma_7b_white.getWidth(string2) + 20;
        this.isWaiting = false;
        this.type = 1;
        this.sizeButtonQuest = 1;
        this.wDia = GameCanvas.w / 5 * 4;
        if (this.wDia > 200) {
            this.wDia = 200;
        }
        this.strinfo = mFont.tahoma_7_white.splitFontArray(string, this.wDia - 20);
        this.hDia = GameCanvas.hText * (this.strinfo.length + 1) + hPlus + 20 + (this.cmdList.size() > 2 ? iCommand.hButtonCmd + 5 : 0);
        if (this.hDia > GameCanvas.h / 2 + 10) {
            this.hDia = GameCanvas.h / 2 + 10;
        }
        this.cameraDia.setAll(0, GameCanvas.hText * (this.strinfo.length + 1) + 30 - this.hDia, 0, 0);
        this.xDia = GameCanvas.hw - this.wDia / 2;
        this.yDia = GameCanvas.hh - GameCanvas.hCommand - this.hDia / 2;
        this.numw = (this.wDia - 6) / 32;
        this.numh = (this.hDia - 6) / 32;
        this.setPosCmdNew(0);
    }

    public void setinfoQuestRead(MainQuest mainQuest) {
        this.beginDia();
        this.hSpe = 2;
        this.status = mainQuest.name;
        this.quest = mainQuest;
        this.isSpec = true;
        this.wStatus = mFont.tahoma_7b_white.getWidth(this.status) + 20;
        this.isWaiting = false;
        this.type = 1;
        this.sizeButtonQuest = 2;
        this.wDia = GameCanvas.w / 5 * 4;
        if (this.wDia > 200) {
            this.wDia = 200;
        }
        iCommand iCommand2 = new iCommand(T.viewMap, 3);
        this.cmdList.addElement(iCommand2);
        this.cmdList.addElement(this.cmdCancleQuest);
        this.cmdList.addElement(this.cmdClose);
        if (!GameCanvas.isTouch) {
            iCommand iCommand3;
            this.right = iCommand3 = new iCommand(T.back, -1, this);
        }
        this.strinfo = mFont.tahoma_7_white.splitFontArray(mainQuest.strShowDialog, this.wDia - 20);
        this.hDia = GameCanvas.hText * (this.strinfo.length + 1) + hPlus + 15 + (this.cmdList.size() > 2 ? iCommand.hButtonCmd + 5 : 0);
        if (!GameCanvas.isTouch && this.hDia > GameCanvas.h * 6 / 7) {
            this.hDia = GameCanvas.h * 6 / 7;
        }
        this.cameraDia.setAll(0, GameCanvas.hText * (this.strinfo.length + 1) + 45 - this.hDia + iCommand.hButtonCmd * this.sizeButtonQuest, 0, 0);
        this.xDia = GameCanvas.hw - this.wDia / 2;
        this.yDia = GameCanvas.hh - this.hDia / 2;
        this.numw = (this.wDia - 6) / 32;
        this.numh = (this.hDia - 6) / 32;
        this.setPosCmdNew(4);
    }

    public void setinfoParty() {
        this.beginDia();
        timePaintParty = 0;
        this.isWaiting = false;
        this.isSpec = true;
        this.type = 2;
        this.hItem = GameCanvas.hCommand + 5;
        this.wDia = GameCanvas.w / 4 * 3;
        if (this.wDia > 180) {
            this.wDia = 180;
        }
        maxSizeParty = Player.party != null ? Player.party.vecPartys.size() : 0;
        this.hDia = this.hItem * maxSizeParty + hPlus - 10 + GameCanvas.hCommand;
        iCommand iCommand2 = new iCommand(T.giaotiep, 4);
        this.cmdchucnang = new iCommand(T.chucnang, 8);
        this.xDia = GameCanvas.hw - this.wDia / 2;
        this.yDia = GameCanvas.hh - GameCanvas.hCommand - this.hDia / 2 + (GameCanvas.isTouch ? GameCanvas.hCommand : 0);
        iCommand iCommand3 = this.cmdClose;
        if (GameCanvas.isTouch) {
            this.cmdList.addElement(this.cmdchucnang);
            this.setPosCmdNew(4);
            iCommand3.setPos(this.xDia + this.wDia - 12, this.yDia + 10, PaintInfoGameScreen.fraCloseMenu, "");
            this.cmdList.addElement(iCommand3);
        } else {
            this.cmdList.addElement(this.cmdchucnang);
            this.cmdList.addElement(iCommand2);
            this.setPosCmdNew(0);
            this.right = iCommand3;
        }
        this.numw = (this.wDia - 6) / 32;
        this.numh = (this.hDia - 6) / 32;
    }

    public void setinfoAutoHP_MP() {
        this.beginDia();
        timePaintParty = 0;
        this.isWaiting = false;
        this.isSpec = false;
        this.type = 4;
        this.hItem = GameCanvas.hCommand;
        this.wDia = GameCanvas.w;
        if (this.wDia > 220) {
            this.wDia = 220;
        }
        this.hDia = this.hItem * 3 + hPlus + GameCanvas.hCommand - 5;
        if (!GameCanvas.isTouch) {
            this.hDia -= iCommand.hButtonCmd;
        }
        iCommand iCommand2 = new iCommand("Ok", 5);
        this.xDia = GameCanvas.hw - this.wDia / 2;
        this.yDia = GameCanvas.hh - GameCanvas.hCommand - this.hDia / 2;
        iCommand iCommand3 = this.cmdClose;
        if (GameCanvas.isTouch) {
            iCommand3.setPos(this.xDia + this.wDia - 12, this.yDia + 10, PaintInfoGameScreen.fraCloseMenu, "");
            this.cmdList.addElement(iCommand3);
            iCommand2.setPos(this.xDia + this.wDia / 2, this.yDia + this.hDia - iCommand.hButtonCmd + 7 - this.hSpe, null, iCommand2.caption);
            this.cmdList.addElement(iCommand2);
        } else {
            this.right = iCommand3;
            this.left = iCommand2;
        }
        this.numw = (this.wDia - 6) / 32;
        this.numh = (this.hDia - 6) / 32;
    }

    public void setinfoAutoGetItem() {
        this.beginDia();
        timePaintParty = 0;
        this.isWaiting = false;
        this.isSpec = false;
        this.type = 7;
        this.hItem = GameCanvas.hCommand;
        this.wDia = GameCanvas.w;
        if (this.wDia > 220) {
            this.wDia = 220;
        }
        this.hDia = this.hItem * 3 + hPlus - 5 + GameCanvas.hCommand;
        if (!GameCanvas.isTouch) {
            this.hDia -= iCommand.hButtonCmd;
        }
        iCommand iCommand2 = new iCommand("Ok", 9);
        this.xDia = GameCanvas.hw - this.wDia / 2;
        this.yDia = GameCanvas.hh - GameCanvas.hCommand - this.hDia / 2;
        iCommand iCommand3 = this.cmdClose;
        if (GameCanvas.isTouch) {
            iCommand3.setPos(this.xDia + this.wDia - 12, this.yDia + 10, PaintInfoGameScreen.fraCloseMenu, "");
            this.cmdList.addElement(iCommand3);
            iCommand2.setPos(this.xDia + this.wDia / 2, this.yDia + this.hDia - iCommand.hButtonCmd + 7 - this.hSpe, null, iCommand2.caption);
            this.cmdList.addElement(iCommand2);
        } else {
            this.right = iCommand3;
            this.left = iCommand2;
        }
        this.numw = (this.wDia - 6) / 32;
        this.numh = (this.hDia - 6) / 32;
    }

    public void setinfoVolume() {
        this.beginDia();
        timePaintParty = 0;
        this.isWaiting = false;
        this.isSpec = false;
        this.type = 13;
        this.hItem = GameCanvas.hCommand;
        this.wDia = GameCanvas.w;
        if (this.wDia > 180) {
            this.wDia = 180;
        }
        this.hDia = this.hItem * 2 + hPlus - 5 + GameCanvas.hCommand;
        if (!GameCanvas.isTouch) {
            this.hDia -= iCommand.hButtonCmd;
        }
        iCommand iCommand2 = new iCommand("Ok", 11);
        this.xDia = GameCanvas.hw - this.wDia / 2;
        this.yDia = GameCanvas.hh - this.hDia / 2;
        iCommand iCommand3 = this.cmdClose;
        if (GameCanvas.isTouch) {
            iCommand3.setPos(this.xDia + this.wDia - 12, this.yDia + 10, PaintInfoGameScreen.fraCloseMenu, "");
            this.cmdList.addElement(iCommand3);
            iCommand2.setPos(this.xDia + this.wDia / 2, this.yDia + this.hDia - iCommand.hButtonCmd + 7 - this.hSpe, null, iCommand2.caption);
            this.cmdList.addElement(iCommand2);
        } else {
            this.right = iCommand3;
            this.left = iCommand2;
        }
        this.numw = (this.wDia - 6) / 32;
        this.numh = (this.hDia - 6) / 32;
    }

    public void setDiaHelp(String string, iCommand iCommand2, int n, int n2, int n3, boolean bl, int n4) {
        this.beginDia();
        if (iCommand2 == null) {
            GameCanvas.currentDialog = null;
        }
        this.isWaiting = false;
        this.isSpec = false;
        this.type = 5;
        this.wDia = n4;
        this.cmdHelp = iCommand2;
        this.cmdList = new mVector("MsgChat cmdlist");
        this.cmdHelp.setPos(GameCanvas.hw, GameCanvas.h - iCommand.hButtonCmd / 2, null, this.cmdHelp.caption);
        this.cmdList.addElement(this.cmdHelp);
        this.strinfo = mFont.tahoma_7_white.splitFontArray(string, this.wDia - 4);
        this.hDia = GameCanvas.hText * this.strinfo.length;
        this.xDia = n;
        this.yDia = n2;
        if (n3 == 5 || n3 == 3 || n3 == 4 || n3 == 6) {
            this.yDia += this.hDia;
        }
        if (n3 == 8) {
            this.xDia -= this.wDia;
        }
        this.archor = n3;
        this.numw = (this.wDia - 6) / 32;
        this.numh = (this.hDia - 6) / 32;
    }

    public void setinfoSHOW(String string, iCommand iCommand2, boolean bl, String string2) {
        int n;
        this.beginDia();
        if (iCommand2 == null) {
            GameCanvas.currentDialog = null;
        }
        this.nameShow = string2;
        this.isWaiting = false;
        this.isSpec = false;
        this.type = 6;
        this.wDia = GameCanvas.w - 30;
        if (this.wDia > 200) {
            this.wDia = 200;
        }
        this.cmdList = new mVector("MsgChat cmdlist4");
        this.cmdList.addElement(iCommand2);
        if (!bl) {
            this.cmdList.addElement(this.cmdClose);
        }
        if (!GameCanvas.isTouch) {
            iCommand iCommand3 = new iCommand(T.back, -1, this);
            this.right = iCommand2;
        }
        if (this.wDia < (n = this.cmdList.size()) * iCommand.wButtonCmd + (n - 1) * 10 + 10) {
            this.wDia = n * iCommand.wButtonCmd + (n - 1) * 10 + 10;
        }
        if (this.wDia > GameCanvas.w) {
            this.wDia = GameCanvas.w;
        }
        this.strinfo = mFont.tahoma_7_white.splitFontArray(string, this.wDia - 20);
        this.hDia = GameCanvas.hText * (this.strinfo.length + 1) + iCommand.hButtonCmd + 20;
        if (this.hDia > GameCanvas.h - GameCanvas.hCommand) {
            this.hDia = GameCanvas.h - GameCanvas.hCommand;
            this.xDia = GameCanvas.hw - this.wDia / 2;
            this.yDia = GameCanvas.hh - this.hDia / 2;
            this.list = new ListNew(this.xDia, this.yDia, this.wDia, this.hDia, 0, 0, GameCanvas.hText * (this.strinfo.length + 1) + iCommand.hButtonCmd + 20 - this.hDia);
        } else {
            this.xDia = GameCanvas.hw - this.wDia / 2;
            this.yDia = GameCanvas.hh - this.hDia / 2;
            this.list = new ListNew(this.xDia, this.yDia, this.wDia, this.hDia, 0, 0, 0);
        }
        this.numw = (this.wDia - 6) / 32;
        this.numh = (this.hDia - 6) / 32;
        this.setPosCmdNew(GameCanvas.isTouch ? 4 : 0);
    }

    public void setInfoAutoBuff() {
        this.beginDia();
        timePaintParty = 0;
        this.isWaiting = false;
        this.isSpec = false;
        this.type = 8;
        this.hItem = GameCanvas.hCommand;
        this.wDia = MaxSkillBuff * 60;
        if (this.wDia > 220) {
            this.wDia = 220;
        }
        this.wbuff = this.wDia / MaxSkillBuff;
        this.hDia = this.hItem * 2 + GameCanvas.hCommand;
        this.xDia = GameCanvas.hw - this.wDia / 2;
        this.yDia = GameCanvas.hh - GameCanvas.hCommand - this.hDia / 2;
        if (GameCanvas.isTouch) {
            this.cmdClose.setPos(this.xDia + this.wDia - 12, this.yDia + 10, PaintInfoGameScreen.fraCloseMenu, "");
            this.cmdList.addElement(this.cmdClose);
        } else {
            iCommand iCommand2;
            this.left = iCommand2 = new iCommand(T.select, 10);
            this.right = this.cmdClose;
        }
        this.numw = (this.wDia - 6) / 32;
        this.numh = (this.hDia - 6) / 32;
    }

    public void setInfoTime(String string, short s) {
        this.beginDia();
        this.isWaiting = false;
        this.isSpec = false;
        this.type = 9;
        this.timeDia = s;
        this.timeset = GameCanvas.timeNow;
        this.wDia = GameCanvas.w - 30;
        if (this.wDia > 200) {
            this.wDia = 200;
        }
        this.cmdList = new mVector("MsgChat cmdlist5");
        this.cmdList.addElement(this.cmdClose);
        this.strinfo = this.fontDia.splitFontArray(string, this.wDia - 20);
        this.hDia = 15 * this.strinfo.length + hPlus + this.hWait;
        this.xDia = GameCanvas.hw - this.wDia / 2;
        this.yDia = GameCanvas.h - GameCanvas.hCommand * 2 - this.hDia - 5;
        this.numw = (this.wDia - 6) / 32;
        this.numh = (this.hDia - 6) / 32;
        this.setPosCmdNew(0);
    }

    public void setShowChangeItem(String string, String string2, MainItem[] mainItemArray, MainItem mainItem) {
        TabRebuildItem.vecEffRe.removeAllElements();
        this.beginDia();
        this.isWaiting = false;
        this.isSpec = false;
        this.nameShow = string;
        this.datanguyenlieu = mainItemArray;
        this.sanpham = mainItem;
        this.StepShow = 0;
        this.timeShow = 0;
        this.type = 10;
        this.wDia = GameCanvas.w - 30;
        if (this.wDia > 240) {
            this.wDia = 240;
        }
        this.cmdList = new mVector("MsgDiaLog cmdList2");
        this.strinfo = this.fontDia.splitFontArray(string2, this.wDia - 20);
        this.hDia = 15 * this.strinfo.length + hPlus + 50;
        this.xDia = GameCanvas.hw - this.wDia / 2;
        this.yDia = GameCanvas.hh - this.hDia / 2;
        this.numw = (this.wDia - 6) / 32;
        this.numh = (this.hDia - 6) / 32;
        this.posItemNguyenlieu = new int[mainItemArray.length + 1][2];
        int n = 0;
        if (mainItemArray.length > 4) {
            n = 30;
        } else if (mainItemArray.length > 2) {
            n = 15;
        }
        for (int i = 0; i < this.posItemNguyenlieu.length; ++i) {
            if (i == this.posItemNguyenlieu.length - 1) {
                this.posItemNguyenlieu[i][0] = this.xDia + this.wDia / 2;
                this.posItemNguyenlieu[i][1] = this.yDia + this.hDia - GameCanvas.hCommand - 35;
                continue;
            }
            this.posItemNguyenlieu[i][0] = this.xDia + this.wDia / 6 + i % 2 * (this.wDia / 3) * 2;
            this.posItemNguyenlieu[i][1] = this.yDia + this.hDia - GameCanvas.hCommand - 35 + n - i / 2 * 30;
            TabRebuildItem.addEffectEnd_ReBuild_ss(34, this.posItemNguyenlieu[i][0], this.posItemNguyenlieu[i][1]);
        }
    }

    public void setShowOpenBox(String string, MainItem[] mainItemArray, String string2, byte by, byte by2) {
        TabRebuildItem.vecEffRe.removeAllElements();
        this.beginDia();
        this.isWaiting = false;
        this.isSpec = false;
        this.isLottery = by2;
        this.isFullEff = by;
        this.nameShow = string;
        this.itemsanpham = mainItemArray;
        this.StepShow = 0;
        this.timeShow = 0;
        this.indexShow1 = 0;
        this.indexShow2 = 0;
        this.type = 11;
        this.wDia = GameCanvas.w - 30;
        if (this.wDia > 240) {
            this.wDia = 240;
        }
        this.strinfo = string2 != null ? this.fontDia.splitFontArray(string2, this.wDia - 20) : null;
        this.cmdList = new mVector("MsgDiaLog cmdList3");
        this.hDia = hPlus + 60;
        if (this.strinfo != null) {
            this.hDia += this.strinfo.length * GameCanvas.hText;
        }
        this.xDia = GameCanvas.hw - this.wDia / 2;
        this.yDia = GameCanvas.hh - this.hDia / 2;
        this.numw = (this.wDia - 6) / 32;
        this.numh = (this.hDia - 6) / 32;
        this.wsizeBox = 55;
        if (this.wsizeBox * (mainItemArray.length - 1) > this.wDia - 30) {
            this.wsizeBox = 30;
        }
        int n = 0;
        if (mainItemArray.length % 2 == 0) {
            n = this.wsizeBox / 2;
        }
        n += (mainItemArray.length - 1) / 2 * this.wsizeBox;
        this.posItemNguyenlieu = new int[this.itemsanpham.length][2];
        for (int i = 0; i < this.posItemNguyenlieu.length; ++i) {
            this.posItemNguyenlieu[i][0] = this.xDia + this.wDia / 2 - n + i * this.wsizeBox;
            this.posItemNguyenlieu[i][1] = this.yDia + this.hDia - GameCanvas.hCommand - 45;
        }
    }

    public void setUpdateData() {
        this.nameShow = T.update;
        curupdate = 0;
        maxupdate = 20;
        this.beginDia();
        this.isWaiting = false;
        this.isSpec = false;
        this.type = 12;
        this.wDia = GameCanvas.w - 30;
        if (this.wDia > 240) {
            this.wDia = 240;
        }
        wUpdate = this.wDia / 4 * 3;
        hUpdate = 16;
        this.cmdList = new mVector("MsgDiaLog cmdList4");
        this.strinfo = this.fontDia.splitFontArray(T.updateData, this.wDia - 20);
        this.hDia = 30 + hPlus;
        this.xDia = GameCanvas.hw - this.wDia / 2;
        this.yDia = GameCanvas.hh - this.hDia / 2;
        this.numw = (this.wDia - 6) / 32;
        this.numh = (this.hDia - 6) / 32;
    }

    public void setPetInfo(PetItem petItem, byte by) {
        if (by != -1) {
            isInven_Equip = by;
        }
        pet = petItem;
        this.nameShow = petItem.itemName;
        this.hItem = GameCanvas.hText;
        this.beginDia();
        this.isWaiting = false;
        this.isSpec = false;
        this.type = 14;
        this.wDia = GameCanvas.w - 30;
        hUpdate = 8;
        if (this.wDia > 200) {
            this.wDia = 200;
        }
        wUpdate = this.wDia / 2;
        this.cmdPet = new iCommand(T.choan, 16, this);
        this.cmdList = new mVector("MsgDiaLog cmdList");
        this.cmdList.addElement(this.cmdPet);
        int n = (7 + petItem.mcontent.length) * GameCanvas.hText + hPlus + 10;
        if (n > GameCanvas.h - GameCanvas.hCommand * 2) {
            this.hDia = GameCanvas.h - GameCanvas.hCommand * 2;
            this.xDia = GameCanvas.hw - this.wDia / 2;
            this.yDia = GameCanvas.hh - this.hDia / 2;
            this.list = new ListNew(this.xDia, this.yDia, this.wDia, this.hDia, 0, 0, n - this.hDia);
        } else {
            this.hDia = n;
            this.xDia = GameCanvas.hw - this.wDia / 2;
            this.yDia = GameCanvas.hh - this.hDia / 2;
            this.list = new ListNew(this.xDia, this.yDia, this.wDia, this.hDia, 0, 0, 0);
        }
        this.numw = (this.wDia - 6) / 32;
        this.numh = (this.hDia - 6) / 32;
        if (GameCanvas.isTouch) {
            this.setPosCmdNew(4);
        }
        this.cmdList.addElement(this.cmdClose);
        if (GameCanvas.isTouch) {
            this.cmdClose.setPos(this.xDia + this.wDia - 12, this.yDia + 10, PaintInfoGameScreen.fraCloseMenu, "");
        } else {
            this.setPosCmdNew(0);
        }
    }

    public void setPosCmdNew(int n) {
        this.idCommand = 0;
        if (this.cmdList.size() > 0) {
            int n2 = this.cmdList.size();
            if (n2 == 1) {
                this.xBegin = this.xDia + this.wDia / 2;
                this.w2cmd = 0;
            } else if (n2 == 2) {
                this.w2cmd = 10;
                this.xBegin = this.xDia + this.wDia / 2 - this.w2cmd / 2 - iCommand.wButtonCmd / 2;
            } else {
                this.w2cmd = 10;
                this.xBegin = this.xDia + this.wDia / 2 - this.w2cmd / 2 - iCommand.wButtonCmd / 2;
            }
            for (int i = 0; i < n2; ++i) {
                iCommand iCommand2 = (iCommand)this.cmdList.elementAt(i);
                iCommand2.isSelect = false;
                if (n2 == 3 && i == 2) {
                    iCommand2.setPos(this.xDia + this.wDia / 2, this.yDia + this.hDia - iCommand.hButtonCmd - (n2 - 1) / 2 * (iCommand.hButtonCmd + 5) + 7 - this.hSpe + i / 2 * (iCommand.hButtonCmd + 5) - (this.isSpec ? 4 : 0) + n, null, iCommand2.caption);
                } else {
                    iCommand2.setPos(this.xBegin + i % 2 * (iCommand.wButtonCmd + this.w2cmd), this.yDia + this.hDia - iCommand.hButtonCmd - (n2 - 1) / 2 * (iCommand.hButtonCmd + 5) + 7 - this.hSpe + i / 2 * (iCommand.hButtonCmd + 5) - (this.isSpec ? 4 : 0) + n, null, iCommand2.caption);
                }
                if (i != 0) continue;
                iCommand2.isSelect = true;
            }
        }
    }

    public void paint(mGraphics mGraphics2) {
        int n;
        GameCanvas.resetTrans(mGraphics2);
        switch (this.type) {
            case 0: {
                AvMain.paintDialog(mGraphics2, this.xDia, this.yDia, this.wDia, this.hDia, 12);
                for (n = 0; n < this.strinfo.length; ++n) {
                    this.fontDia.drawString(mGraphics2, this.strinfo[n], GameCanvas.w / 2, this.yDia + 12 + n * 15, 2, true);
                }
                if (!this.isWaiting) break;
                fraWaiting.drawFrame(this.fWait % MsgDialog.fraWaiting.nFrame, GameCanvas.hw, this.yDia + 25 + this.strinfo.length * 15, 0, 3, mGraphics2);
                break;
            }
            case 1: {
                AvMain.paintTabNew(mGraphics2, this.xDia, this.yDia, this.wDia, this.hDia, true, (byte)0);
                if (GameCanvas.lowGraphic) {
                    MainTabNew.paintRectLowGraphic(mGraphics2, GameCanvas.hw - 32, this.yDia + 11, 64, 14, 2);
                } else {
                    for (n = 0; n < 2; ++n) {
                        mGraphics2.drawRegion(MainTabNew.imgTab[2], 0, 0, 32, 14, 0, GameCanvas.hw - 32 + n * 32, this.yDia + 11, 0, true);
                    }
                }
                mFont.tahoma_7b_white.drawString(mGraphics2, T.quest, GameCanvas.hw, this.yDia + 12, 2, false);
                mFont.tahoma_7b_black.drawString(mGraphics2, this.status, this.xDia + 10, this.yDia + 27, 0, true);
                mGraphics2.setClip(this.xDia + 3, this.yDia + 39, this.wDia - 6, this.hDia - 55 - iCommand.hButtonCmd * this.sizeButtonQuest);
                mGraphics2.saveCanvas();
                mGraphics2.ClipRec(this.xDia + 3, this.yDia + 39, this.wDia - 6, this.hDia - 55 - iCommand.hButtonCmd * this.sizeButtonQuest);
                mGraphics2.translate(0, -this.cameraDia.yCam);
                for (n = 0; n < this.strinfo.length; ++n) {
                    mFont.tahoma_7_black.drawString(mGraphics2, this.strinfo[n], this.xDia + 11, this.yDia + 27 + (n + 1) * GameCanvas.hText, 0, true);
                }
                mGraphics.resetTransAndroid(mGraphics2);
                mGraphics2.restoreCanvas();
                mGraphics2.endClip();
                if (GameCanvas.isTouch) break;
                this.paintCmd_OnlyText(mGraphics2);
                break;
            }
            case 2: {
                int n2;
                this.paintFormList(mGraphics2, this.xDia, this.yDia, this.wDia, this.hDia, T.party);
                int n3 = this.yDia + GameCanvas.hCommand + 3;
                n = 2;
                if (Player.party != null) {
                    if (!GameCanvas.isTouch || timePaintParty > 0) {
                        mGraphics2.setColor(-4873075);
                        mGraphics2.fillRect(this.xDia + 9, n3 - 2 + this.idSelect * this.hItem, this.wDia - 17, this.hItem - 1, true);
                    }
                    if (Player.party != null) {
                        for (n2 = 0; n2 < Player.party.vecPartys.size(); ++n2) {
                            ObjectParty objectParty = (ObjectParty)Player.party.vecPartys.elementAt(n2);
                            if (objectParty.name.compareTo(Player.party.nameMain) == 0) {
                                AvMain.Font3dColorAndColor(mGraphics2, objectParty.name + " " + T.Lv + objectParty.Lv, this.xDia + 11, n3, 0, (byte)7, (byte)0);
                            } else {
                                mFont.tahoma_7b_white.drawString(mGraphics2, objectParty.name + " " + T.Lv + objectParty.Lv, this.xDia + 11, n3, 0, true);
                            }
                            if (objectParty.name.compareTo(GameScreen.player.name) == 0) {
                                objectParty.hp = GameScreen.player.hp;
                                objectParty.maxhp = GameScreen.player.maxHp;
                            }
                            mGraphics2.setColor(0);
                            mGraphics2.fillRect(this.xDia + 11, n3 + 14 - n, 42, 4, true);
                            mGraphics2.setColor(-8714722);
                            mGraphics2.fillRect(this.xDia + 12, n3 + 15 - n, 40, 2, true);
                            mGraphics2.setColor(-579511);
                            mGraphics2.fillRect(this.xDia + 12, n3 + 15 - n, 40 * objectParty.hp / objectParty.maxhp, 2, true);
                            String string = "map";
                            if (WorldMapScreen.namePos != null && objectParty.idMap < WorldMapScreen.namePos.length) {
                                string = WorldMapScreen.namePos[objectParty.idMap];
                            }
                            mFont.tahoma_7_white.drawString(mGraphics2, string + " - " + T.Area + (objectParty.idArea + 1), this.xDia + 11, n3 + 20 - n * 2, 0, true);
                            n3 += this.hItem;
                            if (n2 >= Player.party.vecPartys.size() - 1) continue;
                            mGraphics2.setColor(AvMain.color[4]);
                            mGraphics2.fillRect(this.xDia + 12, n3 - 3, this.wDia - 24, 1, true);
                        }
                    }
                } else {
                    mFont.tahoma_7_black.drawString(mGraphics2, T.noParty, this.xDia + this.wDia / 2, n3, 2, true);
                }
                super.paint(mGraphics2);
                break;
            }
            case 4: {
                int n4;
                this.paintFormList(mGraphics2, this.xDia, this.yDia, this.wDia, this.hDia, T.auto);
                int n2 = this.yDia + this.hItem + 11;
                int n5 = this.xDia + 30 - (GameCanvas.isTouch ? 10 : 0);
                mGraphics2.setColor(MainTabNew.color[0]);
                for (int i = 0; i < T.mAuto.length; ++i) {
                    mFont.tahoma_7_white.drawString(mGraphics2, T.mAuto[i], n5, n2, 0, true);
                    n4 = mFont.tahoma_7_black.getWidth(T.mAuto[0]);
                    mGraphics2.fillRect(n5 + n4, n2 - 3, 35, 18, true);
                    mFont.tahoma_7_white.drawString(mGraphics2, "  " + mHPMP[i] + "%", n5 + 3 + n4, n2, 0, true);
                    n2 += this.hItem;
                }
                mFont.tahoma_7_white.drawString(mGraphics2, T.mUtien[isUutien], n5, n2, 0, true);
                if (!GameCanvas.isTouch) {
                    mGraphics2.drawRegion(AvMain.imgSelect, 0, 0, 12, 16, 4, n5 - 4 + GameCanvas.gameTick % 3, this.yDia + this.hItem + 17 + this.idSelect * this.hItem, 10, true);
                }
                GameCanvas.resetTrans(mGraphics2);
                this.paintCmd(mGraphics2);
                break;
            }
            case 5: {
                MainHelp.paintPopup(mGraphics2, this.xDia, this.yDia, this.wDia, this.hDia, this.archor, this.strinfo);
                break;
            }
            case 6: {
                this.paintFormList(mGraphics2, this.xDia, this.yDia, this.wDia, this.hDia, this.nameShow);
                mGraphics2.setClip(this.xDia, this.yDia + GameCanvas.hCommand + 2, this.wDia, this.hDia - GameCanvas.hCommand - iCommand.hButtonCmd - 8);
                mGraphics2.saveCanvas();
                mGraphics2.ClipRec(this.xDia, this.yDia + GameCanvas.hCommand + 2, this.wDia, this.hDia - GameCanvas.hCommand - iCommand.hButtonCmd - 8);
                mGraphics2.translate(0, -this.list.cmx);
                for (int i = 0; i < this.strinfo.length; ++i) {
                    mFont.tahoma_7_white.drawString(mGraphics2, this.strinfo[i], this.xDia + 8, this.yDia + GameCanvas.hCommand + 2 + i * GameCanvas.hText, 0, true);
                }
                mGraphics.resetTransAndroid(mGraphics2);
                mGraphics2.restoreCanvas();
                mGraphics2.endClip();
                if (GameCanvas.isTouch) break;
                this.paintCmd_OnlyText(mGraphics2);
                break;
            }
            case 7: {
                int n6;
                int n7;
                this.paintFormList(mGraphics2, this.xDia, this.yDia, this.wDia, this.hDia, T.auto);
                int n8 = this.yDia + this.hItem + 11;
                int n4 = this.xDia + 30 - (GameCanvas.isTouch ? 10 : 0);
                mGraphics2.setColor(MainTabNew.color[3]);
                for (n7 = 0; n7 < T.mAutoItem.length; ++n7) {
                    mFont.tahoma_7b_white.drawString(mGraphics2, T.mAutoItem[n7], n4, n8, 0, true);
                    n6 = mFont.tahoma_7b_white.getWidth(T.mAutoItem[n7]);
                    mFont.tahoma_7_white.drawString(mGraphics2, "< " + T.mValueAutoItem[n7][this.mvalueItem[n7]] + " >", n4 + 3 + n6, n8, 0, true);
                    n8 += this.hItem;
                }
                if (!GameCanvas.isTouch) {
                    mGraphics2.drawRegion(AvMain.imgSelect, 0, 0, 12, 16, 4, n4 - 4 + GameCanvas.gameTick % 3, this.yDia + this.hItem + 17 + this.idSelect * this.hItem, 10, true);
                }
                GameCanvas.resetTrans(mGraphics2);
                this.paintCmd(mGraphics2);
                break;
            }
            case 8: {
                int n9;
                this.paintFormList(mGraphics2, this.xDia, this.yDia, this.wDia, this.hDia, T.auto);
                int n7 = this.yDia + GameCanvas.hCommand + this.hItem;
                int n6 = this.xDia + this.wbuff / 2;
                if (!GameCanvas.isTouch) {
                    mGraphics2.setColor(-1054968);
                    mGraphics2.fillRect(n6 - 12 + this.idSelect * this.wbuff, n7 - 12, 24, 24, true);
                }
                for (n9 = 0; n9 < MaxSkillBuff; ++n9) {
                    Skill skill = (Skill)TabSkillsNew.vecPaintSkill.elementAt(Autobuff[n9][2]);
                    skill.paint(mGraphics2, n6, n7, 3);
                    if (Autobuff[n9][1] == 0) {
                        mGraphics2.drawRegion(AvMain.imgDelaySkill, 0, 0, 20, 20, 0, n6, n7, 3, true);
                    }
                    n6 += this.wbuff;
                }
                GameCanvas.resetTrans(mGraphics2);
                this.paintCmd(mGraphics2);
                break;
            }
            case 9: {
                int n9;
                AvMain.paintDialog(mGraphics2, this.xDia, this.yDia, this.wDia, this.hDia, 12);
                for (n9 = 0; n9 < this.strinfo.length; ++n9) {
                    if (n9 == this.strinfo.length - 1) {
                        this.fontDia.drawString(mGraphics2, this.strinfo[n9] + " " + (this.timeDia > 0 ? this.timeDia + "" : "") + "'.", GameCanvas.w / 2, this.yDia + 12 + n9 * 15, 2, true);
                        continue;
                    }
                    this.fontDia.drawString(mGraphics2, this.strinfo[n9], GameCanvas.w / 2, this.yDia + 12 + n9 * 15, 2, true);
                }
                break;
            }
            case 10: {
                int n9;
                this.paintFormList(mGraphics2, this.xDia, this.yDia, this.wDia, this.hDia, this.nameShow);
                if (this.StepShow < 2) {
                    for (n9 = 0; n9 < this.posItemNguyenlieu.length; ++n9) {
                        mGraphics2.drawImage(AvMain.imgHotKey, this.posItemNguyenlieu[n9][0], this.posItemNguyenlieu[n9][1], 3, true);
                    }
                    if (this.StepShow == 0) {
                        for (n9 = 0; n9 < this.datanguyenlieu.length; ++n9) {
                            if (this.timeShow < 4 || this.datanguyenlieu[n9] == null) continue;
                            this.datanguyenlieu[n9].paintItem(mGraphics2, this.posItemNguyenlieu[n9][0], this.posItemNguyenlieu[n9][1], 21, 1, 0);
                        }
                    }
                } else if (this.StepShow == 2) {
                    for (n9 = 0; n9 < this.strinfo.length; ++n9) {
                        mFont.tahoma_7_white.drawString(mGraphics2, this.strinfo[n9], this.xDia + 8, this.yDia + GameCanvas.hCommand + 2 + n9 * GameCanvas.hText, 0, true);
                    }
                    n9 = this.posItemNguyenlieu.length - 1;
                    if (this.sanpham != null) {
                        this.sanpham.paintItem(mGraphics2, this.posItemNguyenlieu[n9][0], this.posItemNguyenlieu[n9][1], 21, 1, 0);
                    }
                    mGraphics2.drawImage(AvMain.imgHotKey, this.posItemNguyenlieu[n9][0], this.posItemNguyenlieu[n9][1], 3, true);
                }
                for (n9 = 0; n9 < TabRebuildItem.vecEffRe.size(); ++n9) {
                    MainEffect mainEffect = (MainEffect)TabRebuildItem.vecEffRe.elementAt(n9);
                    mainEffect.paint(mGraphics2);
                }
                break;
            }
            case 11: {
                int n9;
                this.paintFormList(mGraphics2, this.xDia, this.yDia, this.wDia, this.hDia, this.nameShow);
                if (this.strinfo != null) {
                    for (n9 = 0; n9 < this.strinfo.length; ++n9) {
                        mFont.tahoma_7_white.drawString(mGraphics2, this.strinfo[n9], this.xDia + 8, this.yDia + GameCanvas.hCommand + 2 + n9 * GameCanvas.hText, 0, true);
                    }
                }
                for (n9 = 0; n9 < this.posItemNguyenlieu.length; ++n9) {
                    if (this.indexShow1 != -1 && n9 > this.indexShow1) continue;
                    mGraphics2.drawImage(AvMain.imgHotKey, this.posItemNguyenlieu[n9][0], this.posItemNguyenlieu[n9][1], 3, true);
                }
                for (n9 = 0; n9 < this.itemsanpham.length; ++n9) {
                    if (this.itemsanpham[n9].canSell == 0) continue;
                    this.itemsanpham[n9].paintItem(mGraphics2, this.posItemNguyenlieu[n9][0], this.posItemNguyenlieu[n9][1], 21, 1, 0);
                    if (this.isLottery == 0) {
                        if (this.itemsanpham[n9].ItemCatagory != 3) continue;
                        MainTabNew.setTextColorName(this.itemsanpham[n9].colorNameItem).drawString(mGraphics2, this.itemsanpham[n9].itemName, this.posItemNguyenlieu[n9][0], this.posItemNguyenlieu[n9][1] + 14, 2, true);
                        continue;
                    }
                    if (n9 != 1) continue;
                    MainTabNew.setTextColorName(this.itemsanpham[n9].colorNameItem).drawString(mGraphics2, this.itemsanpham[n9].itemName, this.posItemNguyenlieu[n9][0], this.posItemNguyenlieu[n9][1] + 14, 2, true);
                }
                for (n9 = 0; n9 < TabRebuildItem.vecEffRe.size(); ++n9) {
                    MainEffect mainEffect = (MainEffect)TabRebuildItem.vecEffRe.elementAt(n9);
                    mainEffect.paint(mGraphics2);
                }
                break;
            }
            case 12: {
                int n9;
                this.paintFormList(mGraphics2, this.xDia, this.yDia, this.wDia, this.hDia, this.nameShow);
                for (n9 = 0; n9 < this.strinfo.length; ++n9) {
                    mFont.tahoma_7_white.drawString(mGraphics2, this.strinfo[n9], this.xDia + 8, this.yDia + GameCanvas.hCommand + 4 + n9 * GameCanvas.hText, 0, true);
                }
                n9 = this.xDia + this.wDia / 2 - wUpdate / 2;
                int n10 = hUpdate - 10;
                int n3 = this.yDia + this.hDia - n10 - 25;
                int n11 = n3 - 5;
                mGraphics2.setColor(2698542);
                mGraphics2.fillRect(n9 - 4, n11 + 15, wUpdate + 2, n10, true);
                mGraphics2.fillRect(n9 - 4 + 1, n11 + 14, wUpdate, 1, true);
                mGraphics2.fillRect(n9 - 4 + 1, n11 + 15 + n10, wUpdate, 1, true);
                mGraphics2.setColor(0x2E3233);
                mGraphics2.fillRect(n9 - 4 + 1, n11 + 15, wUpdate, n10, true);
                int n12 = 0;
                if (maxupdate > 0 && curupdate > 0) {
                    n12 = curupdate * wUpdate / maxupdate;
                    if (n12 <= 0) {
                        n12 = 1;
                    } else if (n12 > wUpdate) {
                        n12 = wUpdate;
                    }
                    mGraphics2.setColor(-6437568);
                    mGraphics2.fillRect(n9 - 4 + 1, n11 + 15, n12, n10, true);
                }
                int n13 = curupdate * 100 / maxupdate;
                mFont.tahoma_7b_white.drawString(mGraphics2, n13 + "%", this.xDia + this.wDia / 2, n11, 2, true);
                break;
            }
            case 13: {
                int n14;
                int n15;
                this.paintFormList(mGraphics2, this.xDia, this.yDia, this.wDia, this.hDia, T.SetMusic);
                int n16 = this.yDia + this.hItem + 11;
                int n17 = this.xDia + 30 - (GameCanvas.isTouch ? 10 : 0);
                mGraphics2.setColor(MainTabNew.color[3]);
                for (n15 = 0; n15 < T.mVolume.length; ++n15) {
                    mFont.tahoma_7b_white.drawString(mGraphics2, T.mVolume[n15], n17, n16, 0, true);
                    n14 = mFont.tahoma_7b_white.getWidth(T.mVolume[n15]);
                    mFont.tahoma_7_white.drawString(mGraphics2, "< " + (mvalueVolume[n15] == 0 ? T.on : T.off) + " >", n17 + 3 + n14, n16, 0, true);
                    n16 += this.hItem;
                }
                if (!GameCanvas.isTouch) {
                    mGraphics2.drawRegion(AvMain.imgSelect, 0, 0, 12, 16, 4, n17 - 4 + GameCanvas.gameTick % 3, this.yDia + this.hItem + 17 + this.idSelect * this.hItem, 10, true);
                }
                GameCanvas.resetTrans(mGraphics2);
                this.paintCmd(mGraphics2);
                break;
            }
            case 14: {
                int n18;
                if (pet == null) {
                    return;
                }
                this.paintFormList(mGraphics2, this.xDia, this.yDia, this.wDia, this.hDia, this.nameShow);
                int n15 = this.yDia + this.hItem * 2;
                int n14 = this.xDia + 10;
                mGraphics2.setClip(this.xDia, this.yDia + GameCanvas.hCommand + 2, this.wDia, this.hDia - GameCanvas.hCommand - iCommand.hButtonCmd - 8);
                mGraphics2.saveCanvas();
                mGraphics2.ClipRec(this.xDia, this.yDia + GameCanvas.hCommand + 2, this.wDia, this.hDia - GameCanvas.hCommand - iCommand.hButtonCmd - 8);
                mGraphics2.translate(0, -this.list.cmx);
                pet.paintShowPet(mGraphics2, n14 + MainTabNew.wOneItem / 2, n15 + MainTabNew.wOneItem / 2 + MainTabNew.wOneItem / 4, MainTabNew.wOneItem, MainTabNew.wOneItem / 2, 0, 1);
                mFont.tahoma_7_white.drawString(mGraphics2, T.level + MsgDialog.pet.LvItem + " + " + MsgDialog.pet.experience / 10 + "," + MsgDialog.pet.experience % 10 + "%", n14 + 40, n15, 0, true);
                int n19 = MsgDialog.pet.age / 24;
                int n20 = MsgDialog.pet.age % 24;
                mFont.tahoma_7_white.drawString(mGraphics2, T.tuoi + n19 + "d " + n20 + "h", n14 + 40, n15 += GameCanvas.hText, 0, true);
                MainTabNew.setTextColor(Item.colorInfoItem[MsgDialog.pet.petAttack.id]).drawString(mGraphics2, Item.nameInfoItem[MsgDialog.pet.petAttack.id] + ": " + MsgDialog.pet.petAttack.value + "-" + MsgDialog.pet.petAttack.maxDam, n14 + 40, n15 += GameCanvas.hText, 0, true);
                mFont.tahoma_7_white.drawString(mGraphics2, T.choan, n14 + 8, n15 += this.hItem, 0, true);
                this.paintStatus(mGraphics2, MsgDialog.pet.growpoint, n14 + 65, n15 + 1, MsgDialog.pet.maxgrow);
                n15 += this.hItem;
                for (n18 = 0; n18 < T.mKynangPet.length; ++n18) {
                    mFont.tahoma_7_white.drawString(mGraphics2, T.mKynangPet[n18], n14 + 8, n15, 0, true);
                    this.paintStatus(mGraphics2, MsgDialog.pet.mvaluetiemnang[n18], n14 + 65, n15 + 1, MsgDialog.pet.maxtiemnang);
                    n15 += this.hItem;
                }
                for (n18 = 0; n18 < MsgDialog.pet.mInfo.length; ++n18) {
                    if (MsgDialog.pet.mInfo[n18].id <= 6) continue;
                    String string = Item.nameInfoItem[MsgDialog.pet.mInfo[n18].id] + ": " + Item.getPercent(Item.isPercentInfoItem[MsgDialog.pet.mInfo[n18].id], MsgDialog.pet.mInfo[n18].value);
                    MainTabNew.setTextColor(Item.colorInfoItem[MsgDialog.pet.mInfo[n18].id]).drawString(mGraphics2, string, n14 + 8, n15, 0, true);
                    n15 += this.hItem;
                }
                mGraphics.resetTransAndroid(mGraphics2);
                mGraphics2.restoreCanvas();
                mGraphics2.endClip();
                GameCanvas.resetTrans(mGraphics2);
                this.paintCmd(mGraphics2);
            }
        }
        GameCanvas.resetTrans(mGraphics2);
        if (this.cmdList != null) {
            for (n = 0; n < this.cmdList.size(); ++n) {
                iCommand iCommand2 = (iCommand)this.cmdList.elementAt(n);
                iCommand2.paint(mGraphics2, iCommand2.xCmd, iCommand2.yCmd);
            }
        }
    }

    public void paintStatus(mGraphics mGraphics2, int n, int n2, int n3, int n4) {
        mGraphics2.setColor(-14078674);
        mGraphics2.fillRect(n2 - 4, n3 + 1, wUpdate + 2, hUpdate, true);
        mGraphics2.fillRect(n2 - 4 + 1, n3, wUpdate, 1, true);
        mGraphics2.fillRect(n2 - 4 + 1, n3 + 1 + hUpdate, wUpdate, 1, true);
        mGraphics2.setColor(-13749709);
        mGraphics2.fillRect(n2 - 4 + 1, n3 + 1, wUpdate, hUpdate, true);
        int n5 = 0;
        if (n * 100 / n4 > 0) {
            n5 = n * 100 / n4 * wUpdate / 100;
            if (n5 <= 0) {
                n5 = 1;
            } else if (n5 > wUpdate) {
                n5 = wUpdate;
            }
            mGraphics2.setColor(-6437568);
            mGraphics2.fillRect(n2 - 4 + 1, n3 + 1, n5, hUpdate, true);
        }
        mFont.tahoma_7b_white.drawString(mGraphics2, n + "", n2 + wUpdate / 2, n3, 2, true);
    }

    public void update() {
        Object object;
        if (this.isWaiting) {
            ++this.fWait;
            if (this.fWait > 1200) {
                if (Session_ME.gI().isConnected() && GameCanvas.currentScreen != GameCanvas.login && GameCanvas.currentScreen != GameCanvas.load) {
                    GameCanvas.start_Ok_Dialog(T.thulai);
                } else {
                    object = new mVector("MsgDiaLog vec");
                    if (SelectCharScreen.isSelectOk && GameCanvas.currentScreen != GameCanvas.login) {
                        ((mVector)object).addElement(new iCommand(T.again, 0));
                    }
                    ((mVector)object).addElement(new iCommand(T.exit, 6));
                    GameCanvas.start_Select_Dialog(T.disconnect, (mVector)object);
                    GlobalLogicHandler.isDisConect = true;
                    GlobalLogicHandler.timeReconnect = mSystem.currentTimeMillis() + 30000L;
                }
            }
        }
        if (this.type == 6 || this.type == 14) {
            this.list.moveCamera();
        }
        if (this.type == 6) {
            Player.timeStand = 1;
        }
        if (GlobalLogicHandler.isDisConect && GlobalLogicHandler.timeReconnect - mSystem.currentTimeMillis() <= 0L) {
            this.relogin();
        }
        this.updatekey();
        this.updatePointer();
        if (this.type == 2) {
            if (Player.party != null) {
                if (Player.party.vecPartys.size() != maxSizeParty || maxSizeParty == -1) {
                    maxSizeParty = Player.party.vecPartys.size();
                    this.hDia = this.hItem * maxSizeParty + hPlus - 10 + GameCanvas.hCommand;
                    this.yDia = GameCanvas.hh - GameCanvas.hCommand - this.hDia / 2 + (GameCanvas.isTouch ? GameCanvas.hCommand : 0);
                    this.numh = (this.hDia - 6) / 32;
                    this.cmdList.removeAllElements();
                    object = new iCommand(T.giaotiep, 4);
                    this.cmdchucnang = new iCommand(T.leave, 8);
                    if (GameScreen.player.name.compareTo(Player.party.nameMain) == 0) {
                        this.cmdchucnang.caption = T.chucnang;
                    }
                    iCommand iCommand2 = this.cmdClose;
                    if (GameCanvas.isTouch) {
                        this.cmdList.addElement(this.cmdchucnang);
                        this.setPosCmdNew(4);
                        iCommand2.setPos(this.xDia + this.wDia - 12, this.yDia + 10, PaintInfoGameScreen.fraCloseMenu, "");
                        this.cmdList.addElement(iCommand2);
                    } else {
                        this.cmdList.addElement(this.cmdchucnang);
                        this.cmdList.addElement(object);
                        this.setPosCmdNew(4);
                        this.right = iCommand2;
                    }
                }
                if (timePaintParty > 0) {
                    --timePaintParty;
                }
            } else {
                this.cmdList.removeAllElements();
                object = this.cmdClose;
                if (GameCanvas.isTouch) {
                    ((iCommand)object).setPos(this.xDia + this.wDia - 6, this.yDia + 4, PaintInfoGameScreen.fraCloseMenu, "");
                    this.cmdList.addElement(object);
                } else {
                    this.right = object;
                }
            }
        } else if (this.type == 3 || this.type == 6 || this.type == 14) {
            this.cameraDia.UpdateCamera();
        } else if (this.type == 9) {
            if ((GameCanvas.timeNow - this.timeset) / 1000L > 1L) {
                this.timeset += 1000L;
                this.timeDia = (short)(this.timeDia - 1);
                if (this.timeDia <= 0) {
                    this.cmdClose.perform();
                }
            }
        } else if (this.type == 10) {
            ++this.timeShow;
            if (this.StepShow == 0) {
                if (this.timeShow == 30) {
                    this.timeShow = 0;
                    this.StepShow = 1;
                }
            } else if (this.StepShow == 1) {
                if (this.timeShow == 1) {
                    mSound.playSound(29, mSound.volumeSound);
                    for (int i = 0; i < this.posItemNguyenlieu.length - 1; ++i) {
                        TabRebuildItem.addEffectEndRebuild(41, this.posItemNguyenlieu[i][0], this.posItemNguyenlieu[i][1], this.posItemNguyenlieu[this.posItemNguyenlieu.length - 1][0], this.posItemNguyenlieu[this.posItemNguyenlieu.length - 1][1], 1);
                    }
                }
                if (this.timeShow >= 16) {
                    mSound.playSound(26, mSound.volumeSound);
                    this.StepShow = 2;
                    this.timeShow = 0;
                    this.cmdList.addElement(this.cmdClose);
                    this.setPosCmdNew(0);
                    TabRebuildItem.addEffectEnd_ReBuild_ss(33, this.posItemNguyenlieu[this.posItemNguyenlieu.length - 1][0], this.posItemNguyenlieu[this.posItemNguyenlieu.length - 1][1]);
                    TabRebuildItem.addEffectEnd_ReBuild_ss(34, this.posItemNguyenlieu[this.posItemNguyenlieu.length - 1][0], this.posItemNguyenlieu[this.posItemNguyenlieu.length - 1][1]);
                }
            } else if (this.StepShow == 2) {
                // empty if block
            }
            for (int i = 0; i < TabRebuildItem.vecEffRe.size(); ++i) {
                MainEffect mainEffect = (MainEffect)TabRebuildItem.vecEffRe.elementAt(i);
                mainEffect.update();
                if (!mainEffect.isStop) continue;
                TabRebuildItem.vecEffRe.removeElement(mainEffect);
            }
        } else if (this.type == 11) {
            ++this.timeShow;
            if (this.StepShow == 0) {
                if (this.indexShow1 >= 0 && this.timeShow % 5 == 1) {
                    TabRebuildItem.addEffectEnd_ReBuild_ss(34, this.posItemNguyenlieu[this.indexShow1][0], this.posItemNguyenlieu[this.indexShow1][1]);
                    this.indexShow1 = this.indexShow1 < this.itemsanpham.length - 1 ? ++this.indexShow1 : -1;
                }
                if (this.timeShow > 5 && this.timeShow % 5 == 1) {
                    if (this.isFullEff == 0) {
                        TabRebuildItem.addEffectEnd_ReBuild_ss(33, this.posItemNguyenlieu[this.indexShow2][0], this.posItemNguyenlieu[this.indexShow2][1]);
                    }
                    this.itemsanpham[this.indexShow2].canSell = 1;
                    if (this.indexShow2 < this.itemsanpham.length - 1) {
                        ++this.indexShow2;
                        mSound.playSound(29, mSound.volumeSound);
                    } else {
                        mSound.playSound(29, mSound.volumeSound);
                        this.indexShow2 = -1;
                        this.indexShow1 = -1;
                        this.timeShow = 0;
                        this.StepShow = 1;
                        this.cmdList.addElement(this.cmdClose);
                        this.setPosCmdNew(0);
                    }
                }
            }
            for (int i = 0; i < TabRebuildItem.vecEffRe.size(); ++i) {
                MainEffect mainEffect = (MainEffect)TabRebuildItem.vecEffRe.elementAt(i);
                mainEffect.update();
                if (!mainEffect.isStop) continue;
                TabRebuildItem.vecEffRe.removeElement(mainEffect);
            }
        } else if (this.type == 12) {
            if (Session_ME.gI().isConnected()) {
                if (curupdate >= maxupdate && GameCanvas.gameTick % 40 == 20) {
                    GameCanvas.start_Ok_Dialog(T.updateok);
                }
            } else {
                GameCanvas.start_Center_Dialog_Only(T.disconnect, new iCommand(T.exit, -1));
            }
        }
    }

    public void updatekey() {
        if (this.type == 1) {
            this.cameraDia.UpdateCamera();
            if (GameCanvas.keyMyHold[2]) {
                this.cameraDia.yTo -= GameCanvas.hText;
                if (this.cameraDia.yTo < 0) {
                    this.cameraDia.yTo = 0;
                }
                GameCanvas.clearKeyHold(2);
            } else if (GameCanvas.keyMyHold[8]) {
                this.cameraDia.yTo += GameCanvas.hText;
                if (this.cameraDia.yTo > this.cameraDia.yLimit) {
                    this.cameraDia.yTo = this.cameraDia.yLimit;
                }
                GameCanvas.clearKeyHold(8);
            }
        } else if (this.type == 2) {
            if (Player.party != null) {
                if (GameCanvas.keyMyHold[2]) {
                    --this.idSelect;
                    GameCanvas.clearKeyHold(2);
                } else if (GameCanvas.keyMyHold[8]) {
                    ++this.idSelect;
                    GameCanvas.clearKeyHold(8);
                }
                this.idSelect = this.resetSelect(this.idSelect, Player.party.vecPartys.size() - 1, true);
            } else {
                this.left = null;
            }
        } else if (this.type == 6 || this.type == 14) {
            if (GameCanvas.keyMyHold[2]) {
                this.list.cmtoX -= GameCanvas.hText;
                GameCanvas.clearKeyHold(2);
            } else if (GameCanvas.keyMyHold[8]) {
                this.list.cmtoX += GameCanvas.hText;
                GameCanvas.clearKeyHold(8);
            }
            if (this.list.cmtoX > this.list.cmxLim) {
                this.list.cmtoX = this.list.cmxLim;
            }
            if (this.list.cmtoX < 0) {
                this.list.cmtoX = 0;
            }
        } else if (this.type == 4) {
            if (GameCanvas.keyMyHold[2]) {
                --this.idSelect;
                GameCanvas.clearKeyHold(2);
            } else if (GameCanvas.keyMyHold[8]) {
                ++this.idSelect;
                GameCanvas.clearKeyHold(8);
            }
            this.idSelect = this.resetSelect(this.idSelect, 2, true);
            if (GameCanvas.keyMyHold[4]) {
                switch (this.idSelect) {
                    case 0: {
                        if (mHPMP[0] <= 10) break;
                        mHPMP[0] = mHPMP[0] - 10;
                        break;
                    }
                    case 1: {
                        if (mHPMP[1] <= 10) break;
                        mHPMP[1] = mHPMP[1] - 10;
                        break;
                    }
                    case 2: {
                        if (isUutien != 1) break;
                        isUutien = 0;
                    }
                }
                GameCanvas.clearKeyHold(4);
            } else if (GameCanvas.keyMyHold[6]) {
                switch (this.idSelect) {
                    case 0: {
                        if (mHPMP[0] >= 90) break;
                        mHPMP[0] = mHPMP[0] + 10;
                        break;
                    }
                    case 1: {
                        if (mHPMP[1] >= 90) break;
                        mHPMP[1] = mHPMP[1] + 10;
                        break;
                    }
                    case 2: {
                        if (isUutien != 0) break;
                        isUutien = 1;
                    }
                }
                GameCanvas.clearKeyHold(6);
            }
        } else if (this.type == 7) {
            if (GameCanvas.keyMyHold[2]) {
                --this.idSelect;
                GameCanvas.clearKeyHold(2);
            } else if (GameCanvas.keyMyHold[8]) {
                ++this.idSelect;
                GameCanvas.clearKeyHold(8);
            }
            this.idSelect = this.resetSelect(this.idSelect, 2, true);
            if (GameCanvas.keyMyHold[4]) {
                if (this.mvalueItem[this.idSelect] == 0) {
                    this.mvalueItem[this.idSelect] = (byte)(T.mValueAutoItem[this.idSelect].length - 1);
                } else {
                    int n = this.idSelect;
                    this.mvalueItem[n] = (byte)(this.mvalueItem[n] - 1);
                }
                GameCanvas.clearKeyHold(4);
            } else if (GameCanvas.keyMyHold[6]) {
                if (this.mvalueItem[this.idSelect] == (byte)(T.mValueAutoItem[this.idSelect].length - 1)) {
                    this.mvalueItem[this.idSelect] = 0;
                } else {
                    int n = this.idSelect;
                    this.mvalueItem[n] = (byte)(this.mvalueItem[n] + 1);
                }
                GameCanvas.clearKeyHold(6);
            }
        } else if (this.type == 13) {
            if (GameCanvas.keyMyHold[2]) {
                --this.idSelect;
                GameCanvas.clearKeyHold(2);
            } else if (GameCanvas.keyMyHold[8]) {
                ++this.idSelect;
                GameCanvas.clearKeyHold(8);
            }
            this.idSelect = this.resetSelect(this.idSelect, 1, true);
            if (GameCanvas.keyMyHold[4]) {
                if (this.mvalueItem[this.idSelect] == 0) {
                    this.mvalueItem[this.idSelect] = 1;
                } else {
                    int n = this.idSelect;
                    this.mvalueItem[n] = (byte)(this.mvalueItem[n] - 1);
                }
                GameCanvas.clearKeyHold(4);
            } else if (GameCanvas.keyMyHold[6]) {
                if (this.mvalueItem[this.idSelect] == 1) {
                    this.mvalueItem[this.idSelect] = 0;
                } else {
                    int n = this.idSelect;
                    this.mvalueItem[n] = (byte)(this.mvalueItem[n] + 1);
                }
                GameCanvas.clearKeyHold(6);
            }
        } else if (this.type == 8) {
            if (GameCanvas.keyMyHold[4]) {
                --this.idSelect;
                GameCanvas.clearKeyHold(4);
            } else if (GameCanvas.keyMyHold[6]) {
                ++this.idSelect;
                GameCanvas.clearKeyHold(6);
            }
            this.idSelect = this.resetSelect(this.idSelect, MaxSkillBuff - 1, true);
        }
        if (this.cmdList != null) {
            int n = this.cmdList.size();
            if (!GameCanvas.isTouch && n > 0) {
                iCommand iCommand2;
                int n2 = this.idCommand--;
                if (GameCanvas.keyMyHold[4]) {
                    GameCanvas.clearKeyHold(4);
                } else if (GameCanvas.keyMyHold[6]) {
                    ++this.idCommand;
                    GameCanvas.clearKeyHold(6);
                }
                this.idCommand = this.resetSelect(this.idCommand, n - 1, false);
                if (this.typeQuest == 2 && (iCommand2 = (iCommand)this.cmdList.elementAt(this.idCommand)) == this.cmdClose) {
                    this.idCommand = 0;
                }
                if (n2 != this.idCommand) {
                    for (int i = 0; i < n; ++i) {
                        iCommand iCommand3 = (iCommand)this.cmdList.elementAt(i);
                        iCommand3.isSelect = i == this.idCommand;
                    }
                }
            }
        }
        if (GameCanvas.keyMyHold[5]) {
            GameCanvas.clearKeyHold(5);
            if (this.cmdList != null && this.idCommand < this.cmdList.size()) {
                ((iCommand)this.cmdList.elementAt(this.idCommand)).perform();
            }
        }
        super.updatekey();
    }

    public void updatePointer() {
        int n;
        int n2;
        if (this.type == 2) {
            if (Player.party != null && GameCanvas.isPointSelect(this.xDia, this.yDia + GameCanvas.hCommand, this.wDia, this.hDia - GameCanvas.hCommand) && (n2 = (GameCanvas.py - (this.yDia + GameCanvas.hCommand)) / this.hItem) >= 0 && n2 <= Player.party.vecPartys.size() - 1) {
                mSound.playSound(42, mSound.volumeSound);
                this.idSelect = n2;
                this.idSelect = this.resetSelect(this.idSelect, Player.party.vecPartys.size() - 1, false);
                timePaintParty = 3;
                this.setMenuParty();
                GameCanvas.isPointerSelect = false;
            }
        } else if (this.type == 4) {
            n2 = this.yDia + this.hItem + 11;
            n = this.xDia + 30 - (GameCanvas.isTouch ? 10 : 0) + mFont.tahoma_7_black.getWidth(T.mAuto[0]);
            if (GameCanvas.isPointSelect(n, n2 - 5, 40, 20)) {
                mSound.playSound(42, mSound.volumeSound);
                mHPMP[0] = mHPMP[0] < 90 ? mHPMP[0] + 10 : 10;
                GameCanvas.isPointerSelect = false;
            } else if (GameCanvas.isPointSelect(n, n2 + this.hItem - 5, 40, 20)) {
                mSound.playSound(42, mSound.volumeSound);
                mHPMP[1] = mHPMP[1] < 90 ? mHPMP[1] + 10 : 10;
                GameCanvas.isPointerSelect = false;
            } else if (GameCanvas.isPointSelect(this.xDia + 30 - (GameCanvas.isTouch ? 10 : 0), n2 + this.hItem * 2, 120, 20)) {
                mSound.playSound(42, mSound.volumeSound);
                isUutien = isUutien == 0 ? 1 : 0;
                GameCanvas.isPointerSelect = false;
            }
        } else if (this.type == 7) {
            n = this.xDia;
            n2 = this.yDia + this.hItem + 11;
            if (GameCanvas.isPointSelect(n, n2 - 4, this.wDia, 20)) {
                mSound.playSound(42, mSound.volumeSound);
                this.mvalueItem[0] = this.mvalueItem[0] == (byte)(T.mValueAutoItem[0].length - 1) ? (byte)0 : (byte)(this.mvalueItem[0] + 1);
                GameCanvas.isPointerSelect = false;
            } else if (GameCanvas.isPointSelect(n, n2 + this.hItem - 4, this.wDia, 20)) {
                mSound.playSound(42, mSound.volumeSound);
                this.mvalueItem[1] = this.mvalueItem[1] == (byte)(T.mValueAutoItem[1].length - 1) ? (byte)0 : (byte)(this.mvalueItem[1] + 1);
                GameCanvas.isPointerSelect = false;
            } else if (GameCanvas.isPointSelect(n, n2 + this.hItem * 2, this.wDia, 20)) {
                mSound.playSound(42, mSound.volumeSound);
                this.mvalueItem[2] = this.mvalueItem[2] == (byte)(T.mValueAutoItem[2].length - 1) ? (byte)0 : (byte)(this.mvalueItem[2] + 1);
                GameCanvas.isPointerSelect = false;
            }
        } else if (this.type == 13) {
            n = this.xDia;
            n2 = this.yDia + this.hItem + 11;
            if (GameCanvas.isPointSelect(n, n2 - 4, this.wDia, 20)) {
                mSound.playSound(42, mSound.volumeSound);
                MsgDialog.mvalueVolume[0] = mvalueVolume[0] == 1 ? (byte)0 : (byte)(mvalueVolume[0] + 1);
                GameCanvas.isPointerSelect = false;
            } else if (GameCanvas.isPointSelect(n, n2 + this.hItem - 4, this.wDia, 20)) {
                mSound.playSound(42, mSound.volumeSound);
                MsgDialog.mvalueVolume[1] = mvalueVolume[1] == 1 ? (byte)0 : (byte)(mvalueVolume[1] + 1);
                GameCanvas.isPointerSelect = false;
            }
        } else if (this.type == 6 || this.type == 14) {
            this.list.update_Pos_UP_DOWN();
        } else if (this.type == 8) {
            n2 = this.yDia + GameCanvas.hCommand + this.hItem;
            n = this.xDia + this.wbuff / 2;
            for (int i = 0; i < MaxSkillBuff; ++i) {
                if (!GameCanvas.isPointSelect(n + i * this.wbuff - 20, n2 - 20, 40, 40)) continue;
                mSound.playSound(42, mSound.volumeSound);
                GameCanvas.isPointerSelect = false;
                this.setAutoBuff(i);
                break;
            }
        } else if (this.type == 5 && GameCanvas.isPointSelect(0, 0, GameCanvas.w, GameCanvas.h)) {
            this.cmdHelp.perform();
            GameCanvas.isPointerSelect = false;
        }
        if (this.cmdList != null) {
            for (n2 = 0; n2 < this.cmdList.size(); ++n2) {
                iCommand iCommand2 = (iCommand)this.cmdList.elementAt(n2);
                iCommand2.updatePointer();
            }
        }
    }

    public void setMenuParty() {
        if (Player.party == null) {
            return;
        }
        ObjectParty objectParty = (ObjectParty)Player.party.vecPartys.elementAt(this.idSelect);
        if (objectParty.name.compareTo(GameScreen.player.name) == 0) {
            return;
        }
        mVector mVector2 = new mVector("MsgDiaLog menu");
        iCommand iCommand2 = new iCommand(T.addFriend, 9, this);
        mVector2.addElement(iCommand2);
        iCommand iCommand3 = new iCommand(T.trochuyen, 14, this);
        mVector2.addElement(iCommand3);
        if (GameScreen.player.name.compareTo(Player.party.nameMain) == 0) {
            String string = objectParty.name;
            if (string.length() > 8) {
                string = string.substring(0, 7) + "...";
            }
            iCommand iCommand4 = new iCommand(T.yeucau + string + " " + T.leave, 6, this);
            mVector2.addElement(iCommand4);
        }
        GameCanvas.menu2.startAt(mVector2, 2, objectParty.name, false, null);
    }

    private void setChucNangParty() {
        iCommand iCommand2;
        Object object;
        if (Player.party == null) {
            return;
        }
        ObjectParty objectParty = (ObjectParty)Player.party.vecPartys.elementAt(this.idSelect);
        mVector mVector2 = new mVector("MsgChat menu2");
        if (objectParty.name.compareTo(GameScreen.player.name) != 0) {
            object = objectParty.name;
            if (((String)object).length() > 8) {
                object = ((String)object).substring(0, 7) + "...";
            }
            iCommand2 = new iCommand(T.yeucau + " " + (String)object + " " + T.leave, 6, this);
            mVector2.addElement(iCommand2);
        }
        object = new iCommand(T.leave, 8, this);
        mVector2.addElement(object);
        iCommand2 = new iCommand(T.mainCancle, 7, this);
        mVector2.addElement(iCommand2);
        iCommand iCommand3 = new iCommand(T.chatParty, 15, this);
        mVector2.addElement(iCommand3);
        GameCanvas.menu2.startAt(mVector2, 2, T.chucnang, false, null);
    }

    public void closeDialog() {
        if (GameCanvas.currentDialog != null) {
            GameCanvas.currentDialog = null;
        } else {
            GameCanvas.subDialog = null;
        }
    }

    public void setAutoBuff(int n) {
        if (Autobuff[n][1] == 0) {
            if (Player.mCurentLvSkill[Autobuff[n][0]] > 0) {
                MsgDialog.Autobuff[n][1] = 1;
                Player.isAutoBuff = 1;
            } else {
                GameCanvas.start_Ok_Dialog(T.chuahoc);
            }
        } else {
            MsgDialog.Autobuff[n][1] = 0;
            Player.isAutoBuff = 0;
            for (int i = 0; i < Autobuff.length; ++i) {
                if (Autobuff[i][1] != 1) continue;
                Player.isAutoBuff = 1;
                break;
            }
        }
        MainRMS.setSaveAuto();
    }

    public void relogin() {
        GlobalLogicHandler.isDisConect = true;
        GlobalLogicHandler.timeReconnect = mSystem.currentTimeMillis() + 30000L;
        if (GameCanvas.currentScreen.isGameScr()) {
            if (SelectCharScreen.isSelectOk) {
                GameCanvas.login.Show();
                byte[] byArray = CRes.loadRMS("user_pass");
                if (byArray != null) {
                    try {
                        LoginScreen.loadUser_Pass();
                    }
                    catch (Exception exception) {
                        // empty catch block
                    }
                    GameCanvas.connect();
                    GlobalService.gI().login(LoginScreen.tfusername.getText(), LoginScreen.tfpassword.getText(), GameMidlet.version, "0", "0", "0", SelectCharScreen.IDCHAR, LoadMap.Area);
                    isAutologin = true;
                    GameScreen.player.resetPlayer();
                    if (WorldMapScreen.namePos == null || TabQuest.nameItemQuest == null) {
                        GlobalService.gI().send_cmd_server((byte)61);
                    }
                    this.closeDialog();
                } else {
                    GameCanvas.login.Show();
                    this.closeDialog();
                }
            }
        } else {
            byte[] byArray = CRes.loadRMS("user_pass");
            if (byArray != null) {
                try {
                    LoginScreen.loadUser_Pass();
                }
                catch (Exception exception) {
                    // empty catch block
                }
                GameCanvas.connect();
                GlobalService.gI().login(LoginScreen.tfusername.getText(), LoginScreen.tfpassword.getText(), GameMidlet.version, "0", "0", "0", SelectCharScreen.IDCHAR, LoadMap.Area);
                isAutologin = true;
                GameScreen.player.resetPlayer();
                if (WorldMapScreen.namePos == null || TabQuest.nameItemQuest == null) {
                    GlobalService.gI().send_cmd_server((byte)61);
                }
                this.closeDialog();
            } else {
                GameCanvas.login.Show();
                this.closeDialog();
            }
        }
        GameCanvas.start_Wait_Dialog(T.dangdangnhap, new iCommand(T.close, 7));
        GameCanvas.countLogin = mSystem.currentTimeMillis();
    }

    public static void setMusic() {
        if (mvalueVolume != null) {
            mSound.isMusic = mvalueVolume[0] == 0;
            boolean bl = mSound.isSound = mvalueVolume[1] == 0;
            if (!mSound.isMusic) {
                mSound.pauseCurMusic();
            } else if (LoginScreen.MusicRandom == 0) {
                mSound.playMus(0, mSound.volumeMusic, true);
            } else {
                mSound.playMus(1, mSound.volumeMusic, true);
            }
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            try {
                dataOutputStream.writeByte(mvalueVolume[0]);
                dataOutputStream.writeByte(mvalueVolume[1]);
                CRes.saveRMS("isVolume", byteArrayOutputStream.toByteArray());
                dataOutputStream.close();
            }
            catch (Exception exception) {
                // empty catch block
            }
        }
    }

    static {
        MaxSkillBuff = 0;
        hPlus = 52;
        timePaintParty = 0;
        isUutien = 0;
        mHPMP = new int[]{50, 50};
        mvalueVolume = null;
        vecListEvent = new mVector("MsgDiaLog vecListEvent");
        isInven_Equip = 0;
        isAutologin = false;
    }
}

