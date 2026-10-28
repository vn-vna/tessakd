/*
 * Decompiled with CFR 0.152.
 */
package GameScreen;

import Build_Indo.IndoServer;
import Build_Usa.Usa_Server;
import CLib.Main;
import CLib.TField;
import CLib.mFont;
import CLib.mGraphics;
import CLib.mImage;
import CLib.mSound;
import CLib.mSystem;
import CLib.mVector;
import GameScreen.List_Ip_Server_Screen;
import GameScreen.LogoScreen;
import GameScreen.MainScreen;
import GameScreen.PaintInfoGameScreen;
import GameScreen.WorldMapScreen;
import InterfaceComponents.InputDialog;
import InterfaceComponents.MsgDialog;
import InterfaceComponents.TabQuest;
import InterfaceComponents.iCommand;
import Main.GameCanvas;
import Main.GameMidlet;
import Map.BackGround;
import Model.AvMain;
import Model.CRes;
import Model.T;
import com.silverknight.TemMidlet;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import netcommand.global.GlobalLogicHandler;
import netcommand.global.GlobalService;

public class LoginScreen
extends MainScreen {
    public static final byte LOGIN = 0;
    public static final byte USER_INFO = 1;
    public static final byte SERVER_INFO = 2;
    public int hpaint = 0;
    public int section = 0;
    public int hitem = 10;
    int hBorder;
    int wBorder;
    int yBorder;
    public static boolean isAutoLogin = true;
    public static boolean isPaintHotLine = true;
    public static byte isServer = 0;
    public static TField tfusername;
    public static TField tfpassword;
    public static String username;
    public static String password;
    public static int hShowServer;
    public static byte indexInfoLogin;
    static String[] server;
    public static mImage logo;
    public static String userSv;
    String[] strhelpregister;
    public static String ip;
    InputDialog input;
    public static int MusicRandom;
    public static int wLine;
    private iCommand cmdLogin;
    private iCommand cmdThoat;
    private iCommand cmdChooseServer;
    private iCommand cmdQuickPlay;
    private iCommand cmdNewPlay;
    private iCommand cmdPlay;
    private iCommand cmdUserAccount;
    private iCommand cmdOK;
    private iCommand cmdSubMenu;
    private iCommand cmdChangePassword;
    private iCommand cmdGraphicsSetting;
    private iCommand cmdSoundSetting;
    private iCommand cmdNokiaInfo;
    private iCommand cmdHotLine;
    private iCommand cmdCumSv;
    int t = 0;
    private int yLoginBox;
    private int wBoxText;
    private int hBoxText;
    private boolean isPaintLogo = true;

    public LoginScreen() {
        this.init();
    }

    public void Show() {
        try {
            BackGround.init();
            mSound.stopSoundAll();
            MusicRandom = CRes.random(2);
            if (MusicRandom == 0) {
                mSound.playMus(0, mSound.volumeMusic, true);
            } else {
                mSound.playMus(1, mSound.volumeMusic, true);
            }
            super.Show();
            if (isAutoLogin) {
                Object object;
                byte[] byArray = CRes.loadRMS("local_server");
                if (byArray != null) {
                    object = new ByteArrayInputStream(byArray);
                    DataInputStream dataInputStream = new DataInputStream((InputStream)object);
                    try {
                        isServer = dataInputStream.readByte();
                    }
                    catch (IOException iOException) {
                        iOException.printStackTrace();
                    }
                }
                if ((object = CRes.loadRMS("user_pass")) != null) {
                    try {
                        LoginScreen.loadUser_Pass();
                    }
                    catch (IOException iOException) {
                        iOException.printStackTrace();
                    }
                    GameCanvas.connect();
                    if (username.length() > 0) {
                        this.login(username, password);
                    } else if (tfusername.getText().length() > 0) {
                        this.login(tfusername.getText(), tfpassword.getText());
                    }
                }
            } else {
                byte[] byArray = CRes.loadRMS("user_pass");
                if (byArray != null) {
                    try {
                        LoginScreen.loadUser_Pass();
                    }
                    catch (IOException iOException) {
                        iOException.printStackTrace();
                    }
                }
            }
            isAutoLogin = false;
            this.section = 0;
            this.setScreen();
            if (mSystem.isIphone) {
                this.cmdCumSv = new iCommand(T.chonsv, 25, this);
                this.cmdCumSv.setPos(40, GameCanvas.h - 15, PaintInfoGameScreen.fraButton, this.cmdCumSv.caption);
                if (mSystem.ID_REGION == -1) {
                    this.showCumSv();
                }
            }
            if (mSystem.Android) {
                this.cmdCumSv = new iCommand(T.chonsv, 26, this);
                this.cmdCumSv.setPos(40, GameCanvas.h - 15, PaintInfoGameScreen.fraButton, this.cmdCumSv.caption);
                if (mSystem.ID_REGION == -1) {
                    this.showCumSv2();
                }
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public void showCumSv2() {
        mVector mVector2 = new mVector();
        for (int i = 0; i < T.list_sv2.length; ++i) {
            iCommand iCommand2 = new iCommand(T.list_sv2[i], 27, i, (AvMain)this);
            mVector2.addElement(iCommand2);
        }
        GameCanvas.menu2.startAt_Sv(mVector2, 2, T.chonsv, false, null);
    }

    public void showCumSv() {
        mVector mVector2 = new mVector();
        for (int i = 0; i < T.list_sv.length; ++i) {
            iCommand iCommand2 = new iCommand(T.list_sv[i], 24, i, (AvMain)this);
            mVector2.addElement(iCommand2);
        }
        GameCanvas.menu2.startAt_Sv(mVector2, 2, T.chonsv, false, null);
    }

    private void login(String string, String string2) {
        if (tfusername.getText().intern() == "doiip" && tfpassword.getText().equals("master")) {
            GameCanvas.linkIP = "http://hiepsionline.com/srvip2/";
            GameCanvas.start_Ok_Dialog("Da doi ip thanh cong");
            return;
        }
        GlobalService.gI().login(string, string2, GameMidlet.version, "0", "0", "0", -1, (byte)-1);
        if (WorldMapScreen.namePos == null || TabQuest.nameItemQuest == null) {
            GlobalService.gI().send_cmd_server((byte)61);
        }
    }

    public void init() {
        if (GameCanvas.h < 240) {
            this.hpaint = 10;
        }
        this.yBorder = GameCanvas.hh - 30 + this.hpaint;
        if (GameCanvas.h - this.yBorder < 160) {
            this.yBorder = GameCanvas.h - 160 + this.hpaint + 20;
        }
        if (GameCanvas.isTouch) {
            this.wBorder = GameCanvas.hw + 40;
            this.hitem = 10;
            this.yLoginBox = GameCanvas.hh - 50;
        } else {
            this.wBorder = GameCanvas.w / 2;
        }
        wLine = mFont.tahoma_7_yellow.getWidth(T.HotLine);
        if (IndoServer.isIndoSv || Usa_Server.isUsa_server) {
            isPaintHotLine = false;
        }
        if (GameCanvas.langServer[GameCanvas.IndexServer] != 0) {
            isPaintHotLine = false;
        }
        if (this.wBorder < 130) {
            this.wBorder = 130 + GameCanvas.hw / 2;
        }
        if (GameCanvas.isTouch) {
            tfusername = new TField(GameCanvas.hw - this.wBorder / 2 + 15, this.yLoginBox + this.hitem + this.hitem / 2, this.wBorder - 30);
            tfpassword = new TField(GameCanvas.hw - this.wBorder / 2 + 15, this.yLoginBox + this.hitem * 2 + this.hitem / 2 + LoginScreen.tfusername.height, this.wBorder - 30, 40);
        } else {
            tfusername = new TField(GameCanvas.hw - this.wBorder / 2 + 15, this.yBorder + this.hitem + this.hitem / 2, this.wBorder - 30);
            tfpassword = new TField(GameCanvas.hw - this.wBorder / 2 + 15, this.yBorder + this.hitem * 2 + this.hitem / 2 + LoginScreen.tfusername.height, this.wBorder - 30);
        }
        tfusername.setStringNull(T.username);
        tfpassword.setStringNull(T.password);
        tfpassword.setIputType(2);
        if (!GameCanvas.isTouch) {
            tfusername.setFocus(true);
        }
        this.cmdLogin = new iCommand(T.choi_daco_TK, 0);
        this.cmdQuickPlay = new iCommand(T.choimoi, 1);
        this.cmdNewPlay = new iCommand(T.choimoi, 1);
        this.cmdOK = new iCommand("OK", 2);
        this.cmdSubMenu = new iCommand(T.menu, 3);
        this.cmdPlay = new iCommand(T.choi_daco_TK, 4);
        this.cmdUserAccount = new iCommand(T.daco_TK, 5, this);
        this.cmdChooseServer = new iCommand(T.maychu + ": " + mSystem.listServer[GameCanvas.IndexServer][0], 6, this);
        this.cmdChangePassword = new iCommand(T.quenpass, 7, this);
        this.cmdGraphicsSetting = new iCommand(T.cauhinhthap, 8, this);
        this.cmdSoundSetting = new iCommand(T.SetMusic, 9, this);
        this.cmdHotLine = new iCommand(T.HotLine, 23, this);
        if (TemMidlet.currentIAPStore == 1) {
            this.cmdNokiaInfo = new iCommand(T.about, 21, this);
        }
        this.cmdThoat = new iCommand(T.exit + " Game", -1, this);
        if (GameCanvas.h < 240) {
            this.hpaint = -15;
        }
        this.strhelpregister = mFont.tahoma_7_black.splitFontArray(T.texthelpRegister, this.wBorder - 20);
        this.setHeightBorder(false);
    }

    public void setCaptionCmd() {
        tfusername.setStringNull(T.username);
        tfpassword.setStringNull(T.password);
        LoginScreen.tfusername.cmdClear.caption = T.del;
        LoginScreen.tfpassword.cmdClear.caption = T.del;
        this.cmdLogin.caption = T.choi_daco_TK;
        this.cmdQuickPlay.caption = T.choimoi;
        this.cmdNewPlay.caption = T.choimoi;
        this.cmdPlay.caption = T.choi_daco_TK;
        this.cmdOK.caption = "OK";
        if (!GameCanvas.isTouch) {
            this.cmdSubMenu.caption = T.menu;
        }
        this.cmdThoat.caption = T.exit;
        this.cmdChangePassword.caption = T.quenpass;
        this.cmdGraphicsSetting.caption = T.cauhinhthap;
        this.cmdSoundSetting.caption = T.SetMusic;
        this.setCmdCap();
        if (TemMidlet.currentIAPStore == 1) {
            this.cmdNokiaInfo.caption = T.about;
        }
        this.strhelpregister = mFont.tahoma_7_black.splitFontArray(T.texthelpRegister, this.wBorder - 20);
    }

    public void setPosCmd() {
        int n;
        int n2 = this.yBorder + this.hBorder - iCommand.hButtonCmd / 2 - this.hitem;
        if (n2 + iCommand.hButtonCmd / 2 > GameCanvas.h) {
            n2 = GameCanvas.h - iCommand.hButtonCmd / 2;
        }
        if ((n = this.yLoginBox + this.hBorder - iCommand.hButtonCmd / 2 - this.hitem) + iCommand.hButtonCmd / 2 > GameCanvas.h) {
            n = GameCanvas.h - iCommand.hButtonCmd / 2;
        }
        this.wBoxText = 160;
        this.hBoxText = 25;
        if (GameCanvas.isTouch) {
            this.cmdOK.setPos(GameCanvas.hw - iCommand.wButtonCmd / 2 - 5, n, null, this.cmdOK.caption);
            int n3 = LoginScreen.tfpassword.x + LoginScreen.tfpassword.width - AvMain.fraFogetPass.frameWidth / 2 - 10;
            this.cmdChangePassword.setPos(n3, n - 37, AvMain.fraFogetPass, this.cmdChangePassword.caption);
            this.cmdChangePassword.setFraCaption(AvMain.fraFogetPass, 2, 0);
            this.cmdNewPlay.setPos(GameCanvas.hw + iCommand.wButtonCmd / 2 + 5, n, null, this.cmdNewPlay.caption);
            this.cmdPlay.setPos_BoxText(GameCanvas.hw - this.wBoxText / 2, n2 - 80, null, this.cmdPlay.caption, this.wBoxText, this.hBoxText);
            this.cmdLogin.setPos_BoxText(GameCanvas.hw - this.wBoxText / 2, n2 - 80, null, this.cmdLogin.caption, this.wBoxText, this.hBoxText);
            this.cmdQuickPlay.setPos_BoxText(GameCanvas.hw - this.wBoxText / 2, n2 - 80, null, this.cmdQuickPlay.caption, this.wBoxText, this.hBoxText);
            this.cmdUserAccount.setPos_BoxText(GameCanvas.hw - this.wBoxText / 2, n2 - 45, null, this.cmdUserAccount.caption, this.wBoxText, this.hBoxText);
            this.cmdChooseServer.setPos_BoxText(GameCanvas.hw - this.wBoxText / 2, n2 - 10, null, this.cmdChooseServer.caption, this.wBoxText, this.hBoxText);
        } else {
            this.wBoxText = 140;
            this.cmdUserAccount.setPos_BoxText(GameCanvas.hw - this.wBoxText / 2, n2 - 60, null, this.cmdUserAccount.caption, this.wBoxText, this.hBoxText);
            this.cmdChooseServer.setPos_BoxText(GameCanvas.hw - this.wBoxText / 2, n2 - 20, null, this.cmdChooseServer.caption, this.wBoxText, this.hBoxText);
        }
    }

    public void paint(mGraphics mGraphics2) {
        BackGround.paint(mGraphics2);
        BackGround.paint_StaticCloud(mGraphics2);
        if (this.isPaintLogo) {
            if (this.section != 1 && logo != null) {
                if (GameCanvas.isTouch) {
                    mGraphics2.drawImage(logo, GameCanvas.hw, GameCanvas.hh - GameCanvas.hh / 2, 3, false);
                } else {
                    mGraphics2.drawImage(logo, GameCanvas.hw, GameCanvas.hh - 60 - GameCanvas.hh / 8, 3, false);
                }
            }
            BackGround.paint_CloudOnLogo(mGraphics2);
            BackGround.paintLight(mGraphics2);
            switch (this.section) {
                case 0: {
                    this.cmdUserAccount.paintbutton(mGraphics2, this.cmdUserAccount.xCmd, this.cmdUserAccount.yCmd);
                    this.cmdChooseServer.paintbutton(mGraphics2, this.cmdChooseServer.xCmd, this.cmdChooseServer.yCmd);
                    this.cmdUserAccount.paintCaptionImage(mGraphics2, this.cmdUserAccount.xCmd, this.cmdUserAccount.yCmd - 6, 2);
                    this.cmdChooseServer.paintCaptionImage(mGraphics2, this.cmdChooseServer.xCmd, this.cmdChooseServer.yCmd - 6, 2);
                    break;
                }
                case 1: {
                    if (GameCanvas.isTouch) {
                        AvMain.paintTabNew(mGraphics2, GameCanvas.hw - this.wBorder / 2, this.yLoginBox - 5, this.wBorder, this.hBorder + 5, true, (byte)14);
                    } else {
                        AvMain.paintTabNew(mGraphics2, GameCanvas.hw - this.wBorder / 2, this.yBorder - 5, this.wBorder, this.hBorder + 5, true, (byte)14);
                    }
                    tfusername.paint(mGraphics2);
                    tfpassword.paint(mGraphics2);
                    this.cmdChangePassword.paintImage(mGraphics2, this.cmdChangePassword.xCmd, this.cmdChangePassword.yCmd - 6, 3, 1);
                    break;
                }
            }
        }
        GameCanvas.resetTrans(mGraphics2);
        if (!mSystem.isIP_TrucTiep) {
            mFont.tahoma_7_yellow.drawString(mGraphics2, "version: " + GameMidlet.version, GameCanvas.w - 2, 2, 1, false);
        } else {
            mFont.tahoma_7_yellow.drawString(mGraphics2, "in-house test version: " + GameMidlet.version, GameCanvas.w - 2, 2, 1, false);
        }
        if (PaintInfoGameScreen.paint18plush == 0) {
            if (!mSystem.isIphone) {
                mGraphics2.drawImage(AvMain.img18Plus, 0, 0, 0, false);
            }
        } else if (PaintInfoGameScreen.paint18plush == 1) {
            PaintInfoGameScreen.paintinfo18plush(mGraphics2);
        }
        if (isPaintHotLine) {
            mFont.tahoma_7_yellow.drawString(mGraphics2, T.HotLine, GameCanvas.w, 15, 1, false);
            mGraphics2.setColor(16514362);
            mGraphics2.fillRect(GameCanvas.w - wLine + (mSystem.isIphone ? 14 : 0), 25, wLine, 1, false);
        }
        if (mSystem.isIphone || mSystem.Android && this.cmdCumSv != null) {
            this.cmdCumSv.paint(mGraphics2, 40, GameCanvas.h - 15);
        }
        super.paint(mGraphics2);
    }

    public void update() {
        if (hShowServer < 20 && (hShowServer += 4) > 20) {
            hShowServer = 20;
        }
        if (this.section != 0) {
            tfusername.update();
            tfpassword.update();
        } else {
            this.setCmdCap();
        }
        this.isPaintLogo = !GameCanvas.menu2.isShowMenu;
        BackGround.updateSky();
    }

    public void updatekey() {
        if (GameCanvas.keyMyHold[8]) {
            if (tfusername.isFocused()) {
                tfusername.setFocus(false);
                if (!GameCanvas.isTouch) {
                    tfpassword.setFocus(true);
                }
            } else if (tfpassword.isFocused()) {
                if (!GameCanvas.isTouch) {
                    tfusername.setFocus(true);
                }
                tfpassword.setFocus(false);
            }
            GameCanvas.clearKeyHold(8);
        } else if (GameCanvas.keyMyHold[2]) {
            if (tfusername.isFocused()) {
                tfusername.setFocus(false);
                if (!GameCanvas.isTouch) {
                    tfpassword.setFocus(true);
                }
            } else if (tfpassword.isFocused()) {
                if (!GameCanvas.isTouch) {
                    tfusername.setFocus(true);
                }
                tfpassword.setFocus(false);
            }
            GameCanvas.clearKeyHold(2);
        }
        if (!GameCanvas.isTouch) {
            this.right = tfusername.isFocused() ? tfusername.setCmdClear() : (tfpassword.isFocused() ? tfpassword.setCmdClear() : null);
        }
        super.updatekey();
    }

    public void updatePointer() {
        if (mSystem.isIphone || mSystem.Android && this.cmdCumSv != null) {
            this.cmdCumSv.updatePointer();
        }
        if (this.section != 0) {
            tfusername.updatePoiter();
            tfpassword.updatePoiter();
        }
        if (this.section == 1) {
            this.cmdChangePassword.updatePointer();
        } else if (this.section == 0) {
            this.cmdChooseServer.updatePointer();
            this.cmdUserAccount.updatePointer();
        } else if (this.section == 2) {
            // empty if block
        }
        super.updatePointer();
    }

    public void keyPress(int n) {
        if (tfusername.isFocused()) {
            tfusername.keyPressed(n);
        } else if (tfpassword.isFocused()) {
            tfpassword.keyPressed(n);
        }
        super.keyPress(n);
    }

    public static void saveUser_Pass() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        try {
            dataOutputStream.writeUTF(tfusername.getText());
            dataOutputStream.writeUTF(tfpassword.getText());
            CRes.saveRMS("user_pass", byteArrayOutputStream.toByteArray());
            dataOutputStream.close();
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public static void saveIndexServer() {
        try {
            byte[] byArray = new byte[]{GameCanvas.IndexServer};
            CRes.saveRMS("isIndexServer", byArray);
            LogoScreen.saveSelectedServer();
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public static void loadUser_Pass() throws IOException {
        byte[] byArray = CRes.loadRMS("user_pass");
        if (byArray == null) {
            return;
        }
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byArray);
        DataInputStream dataInputStream = new DataInputStream(byteArrayInputStream);
        tfusername.setText(dataInputStream.readUTF());
        tfpassword.setText(dataInputStream.readUTF());
        String string = tfusername.getText();
        if (string.length() >= 10) {
            string = string.substring(0, 10);
        }
        if (string.compareTo("knightauto") == 0) {
            username = tfusername.getText();
            password = tfpassword.getText();
            tfusername.setText("");
            tfpassword.setText("");
        }
    }

    public void doMenu() {
        mVector mVector2 = new mVector();
        if (this.section == 0) {
            if (!GameCanvas.isTouch) {
                mVector2.addElement(this.cmdUserAccount);
                mVector2.addElement(this.cmdChooseServer);
                mVector2.addElement(this.cmdChangePassword);
                mVector2.addElement(this.cmdHotLine);
            }
            this.cmdGraphicsSetting.caption = GameCanvas.lowGraphic ? T.off + T.cauhinhthap : T.on + T.cauhinhthap;
            mVector2.addElement(this.cmdGraphicsSetting);
            if (TemMidlet.currentIAPStore == 1) {
                mVector2.addElement(this.cmdNokiaInfo);
            }
        } else if (this.section == 1 || this.section == 2) {
            // empty if block
        }
        mVector2.addElement(this.cmdThoat);
        GameCanvas.menu2.startAt(mVector2, 2, T.menuChinh, false, null);
    }

    public void setScreen() {
        switch (this.section) {
            case 0: {
                if (GameCanvas.h < 240) {
                    this.hpaint = 10;
                }
                tfusername.setFocus(Main.isPC);
                tfpassword.setFocus(false);
                this.left = !GameCanvas.isTouch ? this.cmdSubMenu : null;
                if (tfusername.getText().length() > 0) {
                    this.center = this.cmdLogin;
                    break;
                }
                if (username.length() > 0) {
                    this.center = this.cmdPlay;
                    break;
                }
                this.center = this.cmdQuickPlay;
                break;
            }
            case 1: {
                if (!GameCanvas.isTouch) {
                    tfusername.setFocus(true);
                    tfpassword.setFocus(false);
                }
                this.left = this.cmdNewPlay;
                this.center = this.cmdOK;
                this.setHeightBorder(false);
                if (GameCanvas.h < 240) {
                    this.hpaint = 10;
                }
                hShowServer = 0;
                break;
            }
            case 2: {
                this.left = null;
                this.center = null;
            }
        }
    }

    public void setHeightBorder(boolean bl) {
        this.hBorder = LoginScreen.tfusername.height * 2 + this.hitem * 4;
        this.hBorder = GameCanvas.isTouch ? (this.hBorder += iCommand.hButtonCmd + 3) : (this.hBorder += GameCanvas.hh / 8);
        if (bl) {
            this.hBorder += this.strhelpregister.length * GameCanvas.hText;
        }
        this.setPosCmd();
    }

    protected void doRegister() {
        if (tfusername.getText().equals("")) {
            GameCanvas.start_Ok_Dialog(T.chuanhapsdt);
            return;
        }
        if (tfpassword.getText().equals("")) {
            GameCanvas.start_Ok_Dialog(T.chuanhapmk);
            return;
        }
        if (tfusername.getText().length() < 5) {
            GameCanvas.start_Ok_Dialog(T.nameMin6char);
            return;
        }
        boolean bl = false;
        String string = null;
        try {
            long l = Long.parseLong(tfusername.getText());
            if (tfusername.getText().length() < 8 || tfusername.getText().length() > 12 || !tfusername.getText().startsWith("0") && tfusername.getText().startsWith("84")) {
                string = T.sdtkhople;
            }
            bl = true;
        }
        catch (Exception exception) {
            if (tfusername.getText().indexOf("@") == -1 || tfusername.getText().indexOf(".") == -1) {
                string = T.emailkhople;
            }
            bl = false;
        }
        if (string != null) {
            GameCanvas.start_Ok_Dialog(string);
        }
    }

    public void keyBack() {
        this.cmdThoat.perform();
    }

    public void commandTab(int n, int n2) {
        switch (n) {
            case 0: {
                GameCanvas.connect();
                this.login(tfusername.getText(), tfpassword.getText());
                GlobalLogicHandler.timeReconnect = 0L;
                GlobalLogicHandler.isMelogin = false;
                GlobalLogicHandler.isDisConect = false;
                MsgDialog.isAutologin = false;
                break;
            }
            case 1: {
                GameCanvas.connect();
                this.login("1", "1");
                break;
            }
            case 2: {
                this.section = 0;
                this.setScreen();
                break;
            }
            case 3: {
                this.doMenu();
                break;
            }
            case 4: {
                GameCanvas.connect();
                this.login(username, password);
            }
        }
    }

    public void commandMenu(int n, int n2) {
        super.commandMenu(n, n2);
    }

    public void commandPointer(int n, int n2) {
        switch (n) {
            case -1: {
                GameCanvas.start_Left_Dialog(T.hoithoat, new iCommand(T.exit, 10, this));
                break;
            }
            case 5: {
                this.section = 1;
                this.setScreen();
                GlobalLogicHandler.timeReconnect = 0L;
                GlobalLogicHandler.isMelogin = false;
                GlobalLogicHandler.isDisConect = false;
                MsgDialog.isAutologin = false;
                break;
            }
            case 6: {
                GameCanvas.listIp = new List_Ip_Server_Screen();
                GameCanvas.listIp.Show();
                GlobalLogicHandler.timeReconnect = 0L;
                GlobalLogicHandler.isMelogin = false;
                GlobalLogicHandler.isDisConect = false;
                MsgDialog.isAutologin = false;
                break;
            }
            case 7: {
                String string = "";
                string = IndoServer.isIndoSv ? "http://ksatriaonline.indonaga.com/forum/app/index.php?for=event&do=changepass" : (GameCanvas.IndexServer == 2 ? "http://forum.knightageonline.com/app/index.php?for=event&do=resetpass&lang=en" : (Usa_Server.isUsa_server ? "http://knightandmagic.com/forum/app/index.php?for=event&do=resetpass" : "http://forum.knightageonline.com/app/index.php?for=event&do=resetpass&lang=store"));
                GameCanvas.start_Download_Dialog(T.lienhe, string);
                break;
            }
            case 8: {
                GameCanvas.lowGraphic = !GameCanvas.lowGraphic;
                byte by = (byte)(GameCanvas.lowGraphic ? 1 : 0);
                byte[] byArray = new byte[]{by};
                try {
                    CRes.saveRMS("isLowDevice", byArray);
                }
                catch (Exception exception) {
                    exception.printStackTrace();
                }
                break;
            }
            case 9: {
                GameCanvas.start_Volume_Dialog();
                break;
            }
            case 10: {
                GameMidlet.destroy();
                break;
            }
            case 21: {
                GameCanvas.start_Left_Dialog(T.textabout1 + GameMidlet.version + "\n" + T.textabout2, new iCommand(T.nokiaprivacy, 22, this));
                break;
            }
            case 22: {
                TemMidlet.openUrl("http://teamobi.com/terms.htm");
                break;
            }
            case 23: {
                TemMidlet.instance.call(T.numberHotLine);
                break;
            }
            case 24: {
                if (n2 == 0) {
                    mSystem.listServer = mSystem.listServer_VN;
                    GameCanvas.t = new T();
                    IndoServer.isIndoSv = false;
                    Usa_Server.isUsa_server = false;
                    GameCanvas.IndexServer = (byte)5;
                    mSystem.ID_REGION = 0;
                }
                if (n2 == 1) {
                    IndoServer.isIndoSv = true;
                    Usa_Server.isUsa_server = false;
                    IndoServer.setLinkIp();
                    mSystem.ID_REGION = 1;
                    GameCanvas.IndexServer = 1;
                }
                if (n2 == 2) {
                    Usa_Server.isUsa_server = true;
                    IndoServer.isIndoSv = false;
                    Usa_Server.setLinkIp();
                    mSystem.ID_REGION = 2;
                    GameCanvas.IndexServer = 0;
                }
                CRes.saveID_REGION();
                LogoScreen.setChangeLang();
                this.setCmdCap();
                this.cmdCumSv.caption = T.chonsv;
                break;
            }
            case 25: {
                this.showCumSv();
                break;
            }
            case 26: {
                this.showCumSv2();
                break;
            }
            case 27: {
                if (n2 == 0) {
                    mSystem.listServer = mSystem.listServer_VN;
                    GameCanvas.t = new T();
                    IndoServer.isIndoSv = false;
                    Usa_Server.isUsa_server = false;
                    GameCanvas.IndexServer = (byte)4;
                    mSystem.ID_REGION = 0;
                }
                if (n2 == 1) {
                    Usa_Server.isUsa_server = true;
                    IndoServer.isIndoSv = false;
                    Usa_Server.setLinkIp();
                    mSystem.ID_REGION = 1;
                    GameCanvas.IndexServer = 0;
                }
                CRes.saveID_REGION();
                LogoScreen.setChangeLang();
                this.setCmdCap();
                this.cmdCumSv.caption = T.chonsv;
            }
        }
        super.commandPointer(n, n2);
    }

    private void setCmdCap() {
        if (mSystem.isIphone && mSystem.listServer.length - 1 < GameCanvas.IndexServer) {
            return;
        }
        if (this.cmdChooseServer.caption != T.maychu + ": " + mSystem.listServer[GameCanvas.IndexServer][0]) {
            this.cmdChooseServer.caption = T.maychu + ": " + mSystem.listServer[GameCanvas.IndexServer][0];
        }
        if (GameCanvas.isTouch) {
            if (tfusername.getText().length() > 0) {
                if (this.cmdUserAccount.caption != T.doi_TK_khac) {
                    this.cmdUserAccount.caption = T.doi_TK_khac;
                }
                if (tfusername.getText().length() > 13) {
                    String string = "";
                    string = !tfusername.getText().equals(userSv) ? T.choi_daco_TK + ": " + tfusername.getText().substring(0, 12) + "..." : T.choimoi;
                    if (this.cmdLogin.caption != string) {
                        this.cmdLogin.caption = string;
                    }
                    if (this.cmdPlay.caption != string) {
                        this.cmdPlay.caption = string;
                    }
                } else if (this.cmdLogin.caption != T.choi_daco_TK + ": " + tfusername.getText()) {
                    this.cmdLogin.caption = T.choi_daco_TK + ": " + tfusername.getText();
                    this.cmdPlay.caption = T.choi_daco_TK + ": " + tfusername.getText();
                }
            } else if (username.length() > 0) {
                if (this.cmdUserAccount.caption != T.doi_TK_khac) {
                    this.cmdUserAccount.caption = T.doi_TK_khac;
                }
            } else if (this.cmdUserAccount.caption != T.daco_TK) {
                this.cmdUserAccount.caption = T.daco_TK;
            }
        } else if (tfusername.getText().length() > 0) {
            if (tfusername.getText().length() > 8) {
                String string = T.username + ": " + tfusername.getText().substring(0, 8) + "...";
                if (this.cmdUserAccount.caption != string) {
                    this.cmdUserAccount.caption = string;
                }
            } else if (this.cmdUserAccount.caption != T.username + ": " + tfusername.getText()) {
                this.cmdUserAccount.caption = T.username + ": " + tfusername.getText();
            }
        } else if (username.length() > 0) {
            if (this.cmdUserAccount.caption != T.doi_TK_khac) {
                this.cmdUserAccount.caption = T.doi_TK_khac;
            }
        } else if (this.cmdUserAccount.caption != T.daco_TK) {
            this.cmdUserAccount.caption = T.daco_TK;
        }
    }

    public void checkLoginAgain(byte by) {
        block8: {
            try {
                byte[] byArray;
                username = tfusername.getText();
                password = tfpassword.getText();
                this.center = username.length() > 0 ? this.cmdPlay : this.cmdQuickPlay;
                byte[] byArray2 = CRes.loadRMS("isIndexServer");
                byte by2 = by;
                if (byArray2 != null) {
                    by2 = byArray2[0];
                }
                if (by2 != by || (byArray = CRes.loadRMS("user_pass")) == null) break block8;
                try {
                    LoginScreen.loadUser_Pass();
                }
                catch (IOException iOException) {
                    iOException.printStackTrace();
                }
                GameCanvas.connect();
                if (username.length() > 0) {
                    this.login(username, password);
                } else if (tfusername.getText().length() > 0) {
                    this.login(tfusername.getText(), tfpassword.getText());
                }
            }
            catch (Exception exception) {
                // empty catch block
            }
        }
    }

    static {
        username = "";
        password = "";
        hShowServer = 0;
        indexInfoLogin = 1;
        server = new String[]{"anh Tr\u00ed", "ku Ng\u00e2n", "Server"};
        ip = null;
        MusicRandom = 0;
    }
}

