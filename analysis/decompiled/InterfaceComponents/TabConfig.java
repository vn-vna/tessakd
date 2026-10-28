/*
 * Decompiled with CFR 0.152.
 */
package InterfaceComponents;

import Build_Usa.Usa_Server;
import CLib.Main;
import CLib.Session_ME;
import CLib.mFont;
import CLib.mGraphics;
import CLib.mSound;
import CLib.mSystem;
import CLib.mVector;
import GameObjects.MainObject;
import GameObjects.MainRMS;
import GameObjects.Player;
import GameScreen.GameScreen;
import GameScreen.MainScreen;
import GameScreen.PaintInfoGameScreen;
import InterfaceComponents.InputDialog;
import InterfaceComponents.MainList;
import InterfaceComponents.MainTabNew;
import InterfaceComponents.MsgDialog;
import InterfaceComponents.TabShopNew;
import InterfaceComponents.iCommand;
import Main.GameCanvas;
import Main.GameMidlet;
import Model.AvMain;
import Model.CRes;
import Model.T;
import Thread_More.MiniMapFull_Screen;
import com.silverknight.TemMidlet;
import netcommand.global.GlobalLogicHandler;
import netcommand.global.GlobalService;

public class TabConfig
extends MainTabNew {
    mVector vecConfig = new mVector("TabConfig vecConfig");
    int w2;
    int wFocus;
    int idSelect;
    int hItem;
    iCommand cmdSelect;
    iCommand cmdlogout;
    iCommand cmdSetAuto;
    iCommand cmdHelp;
    iCommand cmdSetting;
    iCommand cmdChucNang;
    iCommand cmdShowFullMini;
    iCommand cmdChatWorld;
    iCommand cmdDiamond;
    iCommand cmdSoundSetting;
    iCommand cmdVongquay;
    iCommand cmdkhac;
    iCommand cmdHieuUng;
    iCommand cmdtaitho;
    public static iCommand cmdEvent;
    public static iCommand cmdKeypad;
    public static iCommand cmdShowClan;
    public static iCommand cmdXuongNgua;
    public static iCommand cmdChangeScreen;
    MainList list;
    InputDialog inputWorld;
    String textWorld = "";

    public TabConfig(String string, mVector mVector2, byte by) {
        this.typeTab = by;
        this.nameTab = string;
        this.vecConfig = mVector2;
        this.xBegin = this.xTab + MainTabNew.wOneItem + MainTabNew.wOne5 * 3;
        this.yBegin = this.yTab + GameCanvas.h / 5 + MainTabNew.wOneItem;
        this.w2 = MainTabNew.wblack / 2;
        this.wFocus = MainTabNew.wblack - MainTabNew.wOne5 * 2;
        if (this.wFocus > 130) {
            this.wFocus = 130;
        }
        this.hItem = GameCanvas.hCommand;
        if (GameCanvas.isTouch) {
            this.hItem = 28;
        }
        this.cmdBack = new iCommand(T.back, -1, this);
        this.cmdSelect = new iCommand(T.select, 0, this);
        this.cmdlogout = new iCommand(T.logout, 4, this);
        cmdKeypad = new iCommand(T.chuyensang, 7, this);
        this.cmdHelp = new iCommand(T.help, 10, this);
        this.cmdSetting = new iCommand(T.auto, 11, this);
        this.cmdChucNang = new iCommand(T.chucnang, 12, this);
        cmdEvent = new iCommand(T.mevent, 13, this);
        cmdShowClan = new iCommand(T.clan, 14, this);
        this.cmdShowFullMini = new iCommand(T.minimap, 15, this);
        this.cmdChatWorld = new iCommand(T.textkenhthegioi, 16, this);
        this.cmdDiamond = new iCommand(T.naptien + " " + T.gem, 19, this);
        cmdXuongNgua = new iCommand(T.textXuongNgua + "", 37, this);
        if (mSound.isEnableSound) {
            this.cmdSoundSetting = new iCommand(T.SetMusic, 36, this);
        }
        this.cmdVongquay = new iCommand(T.annguoichoi, 40, this);
        this.cmdkhac = new iCommand(T.khac, 45, this);
        this.cmdHieuUng = new iCommand(T.tathieuung, 46, this);
        this.cmdtaitho = new iCommand(T.taitho, 47, this);
        this.cmdtaitho.caption = PaintInfoGameScreen.taitho == 0 ? T.taitho : T.antaitho;
        this.init();
    }

    public void init() {
        mVector mVector2 = new mVector("TabConfig mcmdTest");
        if (GameCanvas.isTouch) {
            if (GameScreen.help.Step >= 0) {
                mVector2.addElement(GameScreen.gI().cmdEndHelp);
            }
            mVector2.addElement(this.cmdHelp);
            if (GameScreen.player.myClan != null) {
                mVector2.addElement(cmdShowClan);
            }
            if (Player.party != null) {
                mVector2.addElement(GameScreen.gI().cmdParty);
            }
            mVector2.addElement(this.cmdChatWorld);
            if (mSound.isEnableSound) {
                mVector2.addElement(this.cmdSoundSetting);
            }
            if (Main.isPC) {
                mVector2.addElement(cmdChangeScreen);
            }
            if (GameScreen.player.typeMount != -1) {
                mVector2.addElement(cmdXuongNgua);
            }
        } else if (this.typeTab == 7) {
            String string = T.off + " " + T.dosat;
            if (GameScreen.player.typePk != 0) {
                string = T.on + " " + T.dosat;
            }
            GameScreen.gI().cmdSetDoSat.caption = string;
            mVector2.addElement(GameScreen.gI().cmdSetPk);
            mVector2.addElement(GameScreen.gI().cmdSetDoSat);
            mVector2.addElement(this.cmdSetting);
            if (GameScreen.help.Step >= 0) {
                mVector2.addElement(GameScreen.gI().cmdEndHelp);
            }
            mVector2.addElement(this.cmdHelp);
            if (GameScreen.player.Lv >= 10) {
                mVector2.addElement(this.cmdDiamond);
            }
            mVector2.addElement(this.cmdlogout);
        } else if (this.typeTab == 11) {
            mVector2.addElement(GameScreen.gI().cmdListFriend);
            TabConfig.cmdEvent.caption = T.mevent;
            if (PaintInfoGameScreen.numMess > 0) {
                TabConfig.cmdEvent.caption = T.mevent + "*";
            }
            mVector2.addElement(cmdEvent);
            if (GameScreen.player.myClan != null) {
                mVector2.addElement(cmdShowClan);
            }
            if (Player.party != null) {
                mVector2.addElement(GameScreen.gI().cmdParty);
            }
            mVector2.addElement(GameScreen.gI().cmdChangeMap);
            mVector2.addElement(this.cmdShowFullMini);
            mVector2.addElement(this.cmdChatWorld);
            if (GameScreen.player.typeMount != -1) {
                mVector2.addElement(cmdXuongNgua);
            }
        }
        mVector2.addElement(this.cmdVongquay);
        mVector2.addElement(this.cmdkhac);
        mVector2.addElement(this.cmdHieuUng);
        if (!mSystem.isj2me) {
            mVector2.addElement(this.cmdtaitho);
        }
        this.vecConfig = mVector2;
        int n = this.vecConfig.size() * this.hItem - MainTabNew.hblack;
        if (n < 0) {
            n = 0;
        }
        this.idSelect = 0;
        if (!GameCanvas.isTouch) {
            this.right = this.cmdBack;
            this.left = this.cmdSelect;
        } else {
            this.idSelect = -1;
        }
        this.list = new MainList(this.xBegin, this.yBegin, MainTabNew.wblack, MainTabNew.hblack, this.hItem, this.vecConfig.size(), n, this.idSelect, this.vecConfig);
        MainScreen.cameraSub.setAll(0, n, 0, 0);
        super.init();
    }

    public void backTab() {
        MainTabNew.Focus = 0;
        this.idSelect = 0;
        super.backTab();
    }

    public void commandPointer(int n, int n2) {
        switch (n) {
            case -1: {
                this.backTab();
                break;
            }
            case 0: {
                iCommand iCommand2;
                if (this.idSelect <= -1 || (iCommand2 = (iCommand)this.vecConfig.elementAt(this.idSelect)) == null) break;
                if (iCommand2.action != null) {
                    iCommand2.action.perform();
                    break;
                }
                if (iCommand2.Pointer != null) {
                    iCommand2.Pointer.commandPointer((int)iCommand2.indexMenu, iCommand2.subIndex);
                    break;
                }
                GameCanvas.currentScreen.commandMenu(iCommand2.indexMenu, iCommand2.subIndex);
                break;
            }
            case 4: {
                GameScreen.player.resetAction();
                Session_ME.gI().close();
                GameCanvas.login.Show();
                GameScreen.player = new Player(0, 0, "unname", 0, 0);
                GlobalLogicHandler.timeReconnect = 0L;
                GlobalLogicHandler.isMelogin = false;
                GlobalLogicHandler.isDisConect = false;
                MsgDialog.isAutologin = false;
                break;
            }
            case 6: {
                GameMidlet.destroy();
                break;
            }
            case 7: {
                PaintInfoGameScreen.isLevelPoint = !PaintInfoGameScreen.isLevelPoint;
                MainRMS.setSaveTouch();
                TabConfig.cmdKeypad.caption = PaintInfoGameScreen.isLevelPoint ? T.chuyensang + T.keypad : T.chuyensang + T.touch;
                GameCanvas.isPointerSelect = false;
                PaintInfoGameScreen.setPosTouch();
                break;
            }
            case 9: {
                GlobalService.gI().set_Pk((byte)n2);
                break;
            }
            case 10: {
                GameCanvas.start_Show_Dialog(T.strhelp, T.help);
                break;
            }
            case 11: {
                GameScreen.gI().doMenuAuto();
                break;
            }
            case 13: {
                GameCanvas.mevent.init();
                GameCanvas.mevent.Show(GameCanvas.currentScreen);
                break;
            }
            case 14: {
                if (GameScreen.player.myClan == null) break;
                GlobalService.gI().ChucNang_Clan((byte)15, GameScreen.player.myClan.IdClan);
                GameCanvas.start_Wait_Dialog(T.danglaydulieu, new iCommand(T.close, -1));
                break;
            }
            case 15: {
                MiniMapFull_Screen.gI().Show();
                break;
            }
            case 16: {
                this.inputWorld = new InputDialog();
                this.inputWorld.setinfo(T.nhapnoidung, new iCommand(T.chat, 17, this), false, T.textkenhthegioi);
                GameCanvas.currentDialog = this.inputWorld;
                break;
            }
            case 17: {
                this.textWorld = this.inputWorld.tfInput.getText();
                if (this.textWorld == null || this.textWorld.length() <= 0) break;
                GameCanvas.start_Left_Dialog(T.kenhthegioi + " (" + T.phi + TabShopNew.PriceChatWorld + " " + T.gem + ")" + T.noidungnhusau + "\n" + this.textWorld, new iCommand(T.chat, 18, this));
                break;
            }
            case 18: {
                GlobalService.gI().Chat_World(this.textWorld);
                GameCanvas.end_Dialog();
                break;
            }
            case 19: {
                if (mSystem.isIpAppstore()) {
                    mVector mVector2 = new mVector("Menu2 vec2");
                    for (int i = 0; i < TemMidlet.google_listGems.length; ++i) {
                        iCommand iCommand3 = new iCommand(TemMidlet.google_listGems[i], 20, i, (AvMain)this);
                        iCommand3.setFraCaption(AvMain.fraDiamond, 1, 1);
                        mVector2.addElement(iCommand3);
                    }
                    GameCanvas.menu2.startAt(mVector2, 2, T.naptien, false, null);
                    break;
                }
                if (TemMidlet.currentIAPStore == 1) {
                    mVector mVector3 = new mVector("TabConfig vec");
                    for (int i = 0; i < TemMidlet.listGems.length; ++i) {
                        iCommand iCommand4 = new iCommand(TemMidlet.listGems[i], 20, i, (AvMain)this);
                        iCommand4.setFraCaption(AvMain.fraDiamond, 1, 1);
                        mVector3.addElement(iCommand4);
                    }
                    GameCanvas.menu2.startAt(mVector3, 2, T.naptien, false, null);
                    break;
                }
                if (TemMidlet.currentIAPStore == 2) {
                    if (Usa_Server.isUsa_server) {
                        if (GameCanvas.naptien > 0) {
                            mVector mVector4 = new mVector("Menu2 vec2");
                            for (int i = 0; i < TemMidlet.google_listGems.length; ++i) {
                                iCommand iCommand5 = new iCommand(TemMidlet.google_listGems[i], 20, i, (AvMain)this);
                                iCommand5.setFraCaption(AvMain.fraDiamond, 1, 1);
                                mVector4.addElement(iCommand5);
                            }
                            GameCanvas.menu2.startAt(mVector4, 2, T.naptien, false, null);
                            return;
                        }
                        if (GameCanvas.naptien <= 0) {
                            GameCanvas.start_Ok_Dialog(T.hetlannap);
                            return;
                        }
                    }
                    mVector mVector5 = new mVector("TabConfig vec2");
                    for (int i = 0; i < TemMidlet.google_listGems.length; ++i) {
                        iCommand iCommand6 = new iCommand(TemMidlet.google_listGems[i], 20, i, (AvMain)this);
                        iCommand6.setFraCaption(AvMain.fraDiamond, 1, 1);
                        mVector5.addElement(iCommand6);
                    }
                    GameCanvas.menu2.startAt(mVector5, 2, T.naptien, false, null);
                    break;
                }
                GlobalService.gI().send_cmd_server((byte)-56);
                GameCanvas.start_Ok_Dialog(T.pleaseWait);
                break;
            }
            case 20: {
                if (mSystem.isIpAppstore()) {
                    TemMidlet.makePurchase(TemMidlet.google_productIds[n2]);
                    break;
                }
                if (TemMidlet.currentIAPStore == 1) {
                    if (TemMidlet.isBlockNOKIAStore) {
                        GameCanvas.start_Ok_Dialog("Comming soon !");
                        break;
                    }
                    if (TemMidlet.isBlockNOKIAStore) break;
                    GameCanvas.start_Wait_Dialog(T.pleaseWait, new iCommand(T.close, -1));
                    TemMidlet.makePurchase(TemMidlet.productIds[0]);
                    break;
                }
                if (TemMidlet.currentIAPStore != 2) break;
                TemMidlet.makePurchase(TemMidlet.google_productIds[n2]);
                break;
            }
            case 36: {
                GameCanvas.start_Volume_Dialog();
                break;
            }
            case 37: {
                mVector mVector6 = new mVector("TabConfig vec3");
                mVector6.addElement(new iCommand(T.yes, 38, this));
                mVector6.addElement(new iCommand(T.cancel, 39, this));
                GameCanvas.start_Select_Dialog(T.textHoiXuongNgua, mVector6);
                break;
            }
            case 38: {
                GlobalService.gI().useMount((byte)-1);
                this.closeDialog();
                break;
            }
            case 39: {
                this.closeDialog();
                break;
            }
            case 40: {
                if (!mSystem.isj2me) {
                    if (!GameScreen.isHideOderPlayer) {
                        mVector mVector7 = new mVector();
                        iCommand iCommand7 = new iCommand(T.yes, 41, this);
                        iCommand iCommand8 = new iCommand(T.no, 42, this);
                        mVector7.addElement(iCommand7);
                        mVector7.addElement(iCommand8);
                        GameCanvas.start_Select_Dialog(T.hoiannguoichoi, mVector7);
                        break;
                    }
                    mVector mVector8 = new mVector();
                    iCommand iCommand9 = new iCommand(T.yes, 43, this);
                    iCommand iCommand10 = new iCommand(T.no, 42, this);
                    mVector8.addElement(iCommand9);
                    mVector8.addElement(iCommand10);
                    GameCanvas.start_Select_Dialog(T.hoihienguoichoi, mVector8);
                    break;
                }
                if (!GameScreen.isHideOderPlayer && !GameScreen.isHideFullOderPlayer) {
                    mVector mVector9 = new mVector();
                    iCommand iCommand11 = new iCommand(T.hide, 41, this);
                    iCommand iCommand12 = new iCommand(T.hidefull, 44, this);
                    mVector9.addElement(iCommand11);
                    mVector9.addElement(iCommand12);
                    GameCanvas.menu2.startAt(mVector9, 2, T.annguoichoi, false, null);
                    break;
                }
                mVector mVector10 = new mVector();
                iCommand iCommand13 = new iCommand(T.yes, 43, this);
                iCommand iCommand14 = new iCommand(T.no, 42, this);
                mVector10.addElement(iCommand13);
                mVector10.addElement(iCommand14);
                GameCanvas.start_Select_Dialog(T.hoihienguoichoi, mVector10);
                break;
            }
            case 41: {
                GameScreen.isHideOderPlayer = true;
                this.cmdVongquay.caption = T.hiennguoichoi;
                GameCanvas.end_Dialog();
                break;
            }
            case 42: {
                GameCanvas.end_Dialog();
                break;
            }
            case 43: {
                GameScreen.isHideOderPlayer = false;
                GameScreen.isHideFullOderPlayer = false;
                this.cmdVongquay.caption = T.annguoichoi;
                GameCanvas.end_Dialog();
                break;
            }
            case 44: {
                GameScreen.isHideFullOderPlayer = true;
                this.cmdVongquay.caption = T.hiennguoichoi;
                GameCanvas.end_Dialog();
                break;
            }
            case 45: {
                GlobalService.gI().request_LotteryItems((byte)5, (byte)0);
                break;
            }
            case 46: {
                if (MainObject.hideEff == 0) {
                    MainObject.hideEff = 1;
                    this.cmdHieuUng.caption = T.bathieuung;
                    break;
                }
                MainObject.hideEff = 0;
                this.cmdHieuUng.caption = T.tathieuung;
                break;
            }
            case 47: {
                if (PaintInfoGameScreen.taitho == 0) {
                    PaintInfoGameScreen.taitho = 1;
                    this.cmdtaitho.caption = T.antaitho;
                    PaintInfoGameScreen.xPointMove = 85;
                    PaintInfoGameScreen.mPosOther[1][0] = 38;
                } else {
                    PaintInfoGameScreen.taitho = 0;
                    this.cmdtaitho.caption = T.taitho;
                    PaintInfoGameScreen.xPointMove = 55;
                    PaintInfoGameScreen.mPosOther[1][0] = 8;
                }
                PaintInfoGameScreen.reloadmove();
                CRes.save_TaiTho();
            }
        }
    }

    public void closeDialog() {
        if (GameCanvas.currentDialog != null) {
            GameCanvas.currentDialog = null;
        } else {
            GameCanvas.subDialog = null;
        }
    }

    public void paint(mGraphics mGraphics2) {
        mGraphics2.setClip(this.xBegin, this.yBegin, MainTabNew.wblack, MainTabNew.hblack);
        mGraphics2.saveCanvas();
        mGraphics2.ClipRec(this.xBegin, this.yBegin, MainTabNew.wblack, MainTabNew.hblack);
        mGraphics2.translate(0, -this.list.cmx);
        if (MainTabNew.Focus == 1 && this.idSelect > -1) {
            this.paintFocus(mGraphics2);
        }
        for (int i = 0; i < this.vecConfig.size(); ++i) {
            iCommand iCommand2 = (iCommand)this.vecConfig.elementAt(i);
            mFont.tahoma_7b_white.drawString(mGraphics2, iCommand2.caption, this.xBegin + this.w2, this.yBegin + this.hItem / 2 + i * this.hItem - 6, 2, true);
            if (i >= this.vecConfig.size() - 1) continue;
            mGraphics2.setColor(MainTabNew.color[4]);
            mGraphics2.fillRect(this.xBegin + 8, this.yBegin + (i + 1) * this.hItem - 1, MainTabNew.wblack - 16, 1, true);
        }
        mGraphics.resetTransAndroid(mGraphics2);
        mGraphics2.restoreCanvas();
        mGraphics2.endClip();
    }

    public void paintFocus(mGraphics mGraphics2) {
        mGraphics2.setColor(MainTabNew.color[5]);
        mGraphics2.fillRect(this.xBegin + this.w2 - this.wFocus / 2 - 1, this.yBegin + this.idSelect * this.hItem + this.hItem / 2 - 11, this.wFocus + 2, 22, false);
        if (GameCanvas.lowGraphic) {
            MainTabNew.paintRectLowGraphic(mGraphics2, this.xBegin + this.w2 - this.wFocus / 2, this.yBegin + this.idSelect * this.hItem + this.hItem / 2 - 10, this.wFocus, 20, 4);
        } else {
            for (int i = 0; i <= this.wFocus / 24; ++i) {
                int n = this.xBegin + this.w2 - this.wFocus / 2 + 24 * i;
                if (i == this.wFocus / 24) {
                    n = this.xBegin + this.w2 + this.wFocus / 2 - 24;
                }
                mGraphics2.drawRegion(MainTabNew.imgTab[8], 0, 0, 24, 20, 0, n, this.yBegin + this.idSelect * this.hItem + this.hItem / 2 - 10, 0, false);
            }
        }
    }

    public void update() {
        MainScreen.cameraSub.UpdateCamera();
        if (MainTabNew.Focus == 1) {
            if (GameCanvas.menu2.isShowMenu || GameCanvas.currentDialog != null || GameCanvas.subDialog != null || GameCanvas.currentScreen != GameCanvas.AllInfo) {
                return;
            }
            this.list.updateMenu();
            this.idSelect = this.list.value;
            if (this.idSelect >= this.vecConfig.size()) {
                this.idSelect = -1;
                this.list.value = -1;
            }
        }
    }

    public void updatekey() {
        if (MainTabNew.Focus == 1 && (GameCanvas.keyMyHold[4] || GameCanvas.keyMyHold[6])) {
            MainTabNew.Focus = 0;
            this.idSelect = 0;
            GameCanvas.clearKeyHold(4);
            GameCanvas.clearKeyHold(6);
        }
        super.updatekey();
    }
}

