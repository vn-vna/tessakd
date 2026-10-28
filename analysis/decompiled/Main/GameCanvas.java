/*
 * Decompiled with CFR 0.152.
 */
package Main;

import Build_Indo.IndoServer;
import Build_Usa.Usa_Server;
import CLib.Session_ME;
import CLib.TField;
import CLib.TemGraphics;
import CLib.mFont;
import CLib.mGraphics;
import CLib.mSystem;
import CLib.mVector;
import GameObjects.MainItem;
import GameObjects.MainQuest;
import GameObjects.PetItem;
import GameScreen.Buy_Sell_Screen;
import GameScreen.Clan_Screen;
import GameScreen.CreateChar;
import GameScreen.EventScreen;
import GameScreen.GameScreen;
import GameScreen.Info_Other_Player;
import GameScreen.List_Ip_Server_Screen;
import GameScreen.List_Server;
import GameScreen.LoadMapScreen;
import GameScreen.LoginScreen;
import GameScreen.LogoScreen;
import GameScreen.MainScreen;
import GameScreen.MapScr;
import GameScreen.PaintInfoGameScreen;
import GameScreen.SelectCharScreen;
import GameScreen.StoryScreen;
import GameScreen.TabScreenNew;
import GameScreen.WorldMapScreen;
import InterfaceComponents.ChatTextField;
import InterfaceComponents.Info_Quayso;
import InterfaceComponents.InputDialog;
import InterfaceComponents.MainDialog;
import InterfaceComponents.MainTabNew;
import InterfaceComponents.MsgChat;
import InterfaceComponents.MsgDialog;
import InterfaceComponents.TabShopNew;
import InterfaceComponents.iCommand;
import InterfaceComponents.infoShow;
import Main.CreateImageStatic;
import Map.MapBackGround;
import Model.CRes;
import Model.Menu2;
import Model.Position;
import Model.T;
import Model.mCamera;
import Skill.MainListSkill;
import Thread_More.LoadMap;
import Thread_More.MiniMap;
import Thread_More.MiniMapFull_Screen;
import Thread_More.SaveImageRMS;
import com.silverknight.TemCanvas;
import com.silverknight.TemMidlet;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import netcommand.global.GlobalService;
import netcommand.global.ReadMessenge;

public class GameCanvas {
    TemCanvas mainCanvas;
    public static MainScreen currentScreen;
    public static LogoScreen logo;
    public static LoginScreen login;
    public static GameScreen game;
    public static LoadMapScreen load;
    public static WorldMapScreen worldmap;
    public static StoryScreen story;
    public static MsgChat msgchat;
    public static EventScreen mevent;
    public static SelectCharScreen selectChar;
    public static CreateChar createChar;
    public static List_Ip_Server_Screen listIp;
    public static Info_Other_Player info_other_player;
    public static infoShow showMount;
    public static Clan_Screen clan;
    public static MiniMapFull_Screen fullMiniMap;
    public static T t;
    mGraphics g = new mGraphics();
    public static Menu2 menu2;
    public static LoadMap loadmap;
    public static MiniMap minimap;
    public static GameCanvas instance;
    public static MainDialog currentDialog;
    public static MainDialog subDialog;
    public static ReadMessenge readMessenge;
    public static MainTabNew maintab;
    public static TabScreenNew AllInfo;
    public static TabScreenNew shopNpc;
    public static TabScreenNew foodPet;
    public static TabScreenNew TabEvent;
    public static Buy_Sell_Screen buy_sell;
    public static MapBackGround mapBack;
    public static SaveImageRMS saveImage;
    public static boolean isTouch;
    public static boolean lowGraphic;
    public static boolean isSmallScreen;
    public static boolean isVNLanguage;
    public static byte device;
    public static String stringPackageName;
    public static byte IndexRes;
    public static byte IndexServer;
    public static short IndexCharPar;
    public static long timenextLogin;
    public static int naptien;
    public static int[] portServer;
    public static int[] langServer;
    public static long countLogin;
    public static String linkIP;
    public static boolean isBB;
    public static boolean isPointerDown;
    public static boolean isPointerRelease;
    public static boolean isPointerSelect;
    public static boolean isPointerMove;
    public static boolean isPointerClick;
    public static boolean isPointerEnd;
    public static int w;
    public static int h;
    public static int hw;
    public static int hh;
    public static int hCommand;
    public static int hText;
    public static int hFontBorder;
    public static int hFontF;
    public static int hNormal;
    public static int wCommand;
    public static int hLoad;
    public static int px;
    public static int py;
    public static int pxLast;
    public static int pyLast;
    public static int gameTick;
    public static long timeNow;
    public static boolean[] keyMyPressed;
    public static boolean[] keyMyReleased;
    public static boolean[] keyMyHold;
    public static int keyAsciiPress;
    public static mVector listPoint;
    public static boolean istest;
    public static long countTips;
    public static int iTips;

    public GameCanvas() {
        DataInputStream dataInputStream;
        ByteArrayInputStream byteArrayInputStream;
        byte[] byArray;
        instance = this;
        this.mainCanvas = TemMidlet.temCanvas;
        device = 0;
        stringPackageName = mSystem.getPackageName();
        TField.setVendorTypeMode(0);
        this.getSize();
        CRes.load_TaiTho();
        mFont.loadmFont();
        Usa_Server.setLinkIp();
        IndoServer.setLinkIp();
        if (w < 200 || h < 200) {
            isSmallScreen = true;
            if (LoginScreen.indexInfoLogin == 1) {
                LoginScreen.indexInfoLogin = (byte)2;
            }
            iCommand.wButtonCmd = 56;
        }
        wCommand = 36;
        if (isTouch) {
            wCommand = 40;
            listPoint = new mVector("GameCanvas listPoint");
            iCommand.hButtonCmd = 30;
            iCommand.wButtonCmd = 80;
            MsgDialog.hPlus = 58;
        } else if (isSmallScreen) {
            wCommand = 30;
        }
        saveImage = new SaveImageRMS();
        if (CRes.loadRMS("isQty") != null) {
            TField.isQwerty = true;
        }
        try {
            byArray = CRes.loadRMS("isLowDevice");
            if (byArray != null) {
                byteArrayInputStream = new ByteArrayInputStream(byArray);
                dataInputStream = new DataInputStream(byteArrayInputStream);
                byte by = dataInputStream.readByte();
                lowGraphic = by == 1;
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
        try {
            byArray = CRes.loadRMS("isIndexRes");
            if (byArray != null) {
                byteArrayInputStream = new ByteArrayInputStream(byArray);
                dataInputStream = new DataInputStream(byteArrayInputStream);
                IndexRes = dataInputStream.readByte();
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
        try {
            IndexCharPar = 0;
        }
        catch (Exception exception) {
            // empty catch block
        }
        IndexServer = 0;
        try {
            byArray = CRes.loadRMS("isIndexServer");
            if (byArray != null) {
                IndexServer = byArray[0];
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
        if (IndoServer.isIndoSv) {
            IndexServer = 1;
            try {
                byArray = CRes.loadRMS("isIndexServer");
                if (byArray != null) {
                    IndexServer = byArray[0];
                }
            }
            catch (Exception exception) {
                // empty catch block
            }
        }
        if (Usa_Server.isUsa_server) {
            IndexServer = 0;
        }
        CreateImageStatic.LoadImage();
        MainScreen.cameraMain = new mCamera();
        MainScreen.cameraSub = new mCamera();
        loadmap = new LoadMap();
        minimap = new MiniMap();
        login = new LoginScreen();
        game = new GameScreen();
        load = new LoadMapScreen();
        worldmap = new WorldMapScreen();
        readMessenge = new ReadMessenge();
        maintab = new MainTabNew();
        AllInfo = new TabScreenNew();
        TabEvent = new TabScreenNew();
        msgchat = new MsgChat();
        selectChar = new SelectCharScreen();
        mevent = new EventScreen();
        MainListSkill.loadIndexEffKill();
        logo = new LogoScreen();
        logo.Show();
        StoryScreen.setTypeStory();
    }

    public static void loadCaptionCmd() {
        login.setCaptionCmd();
        game.setCaptionCmd();
        AllInfo.setCaptionCmd();
        msgchat.setCaptionCmd();
        selectChar.setCaptionCmd();
        mevent.setCaptionCmd();
        GameScreen.help.setCaptionCmd();
    }

    public void paint(TemGraphics temGraphics) {
        this.g.g = temGraphics.g;
        currentScreen.paint(this.g);
        if (this.setShowEvent()) {
            GameScreen.infoGame.paintShowEvent(this.g);
            GameScreen.infoGame.paintInfoChar(this.g);
        }
        if (subDialog != null) {
            subDialog.paint(this.g);
        }
        if (currentDialog != null) {
            currentDialog.paint(this.g);
        } else if (GameCanvas.menu2.isShowMenu) {
            menu2.paintMenu(this.g);
        } else if (ChatTextField.isShow) {
            ChatTextField.gI().paint(this.g);
        }
        GameCanvas.resetTrans(this.g);
        if (hLoad > 0) {
            this.g.setColor(0);
            this.g.fillRect(0, 0, w, h, false);
        }
        if (istest) {
            mFont.tahoma_7_black.drawString(this.g, "sv test", 10, h - 10, 0, false);
        }
    }

    public boolean setShowEvent() {
        return currentScreen == TabEvent || currentScreen == game || currentScreen == AllInfo || currentScreen == shopNpc || currentScreen == foodPet || currentScreen == worldmap || currentScreen == buy_sell || currentScreen == info_other_player || currentScreen == mevent || currentScreen == clan || currentScreen == MiniMapFull_Screen.gI() || currentScreen == List_Server.gI();
    }

    public void update() {
        if (++gameTick > 10000) {
            gameTick = 0;
        }
        if (gameTick % 5 == 0) {
            timeNow = mSystem.currentTimeMillis();
        }
        if (hLoad > 0) {
            hLoad -= h / 10;
        }
        if (this.setShowEvent()) {
            GameScreen.infoGame.updateEvent();
            GameScreen.infoGame.updateInfoServer();
            GameScreen.infoGame.updateInfoChar();
            GameScreen.infoGame.updateInfoCharServer();
        }
        if (currentDialog != null) {
            currentDialog.update();
        } else if (GameCanvas.menu2.isShowMenu) {
            menu2.updateMenu();
            menu2.updateMenuKey();
        } else if (subDialog != null) {
            subDialog.update();
        } else if (ChatTextField.isShow) {
            ChatTextField.gI().updatekey();
            ChatTextField.gI().updatePointer();
        } else {
            currentScreen.updatekey();
            currentScreen.updatePointer();
        }
        currentScreen.update();
        isPointerClick = false;
        if (GameScreen.timeResetCam > 0 && --GameScreen.timeResetCam == 0) {
            GameScreen.isMoveCamera = false;
        }
        this.updateCountTick();
    }

    public static void resetTrans(mGraphics mGraphics2) {
        mGraphics2.translate(-mGraphics2.getTranslateX(), -mGraphics2.getTranslateY());
        mGraphics2.translate(0, 0);
        mGraphics2.setClip(0, 0, w, h);
    }

    public static void start_Ok_Dialog(String string) {
        MsgDialog msgDialog = new MsgDialog();
        msgDialog.setinfo(string, new iCommand("Ok", -1), true);
        currentDialog = msgDialog;
    }

    public static void start_Info_Quayso_Dialog() {
        Info_Quayso info_Quayso = new Info_Quayso();
        info_Quayso.setInfo(T.info_Quayso, 60, 1, "1.01%");
        currentDialog = info_Quayso;
    }

    public static void start_Ok_Dialog(String string, byte by) {
        MsgDialog msgDialog = new MsgDialog();
        msgDialog.setinfo(string, new iCommand("Ok", by), true);
        currentDialog = msgDialog;
    }

    public static void start_Download_Dialog(String string, String string2) {
        MsgDialog msgDialog = new MsgDialog();
        msgDialog.setinfoDownload(string, string2, false);
        currentDialog = msgDialog;
    }

    public static void start_Show_Dialog(String string, String string2) {
        MsgDialog msgDialog = new MsgDialog();
        msgDialog.setinfoSHOW(string, new iCommand(T.close, -1), true, string2);
        currentDialog = msgDialog;
    }

    public static void start_Wait_Dialog(String string, iCommand iCommand2) {
        MsgDialog msgDialog = new MsgDialog();
        msgDialog.setinfoWait(string, iCommand2);
        currentDialog = msgDialog;
    }

    public static void start_Select_Dialog(String string, mVector mVector2) {
        MsgDialog msgDialog = new MsgDialog();
        msgDialog.setinfo(string, mVector2);
        currentDialog = msgDialog;
    }

    public static void start_Center_Dialog_Only(String string, iCommand iCommand2) {
        MsgDialog msgDialog = new MsgDialog();
        msgDialog.setinfo(string, iCommand2, true);
        currentDialog = msgDialog;
    }

    public static void start_Left_Dialog(String string, iCommand iCommand2) {
        MsgDialog msgDialog = new MsgDialog();
        msgDialog.setinfo(string, iCommand2, false);
        currentDialog = msgDialog;
    }

    public static void start_Quest_Dialog(String string, String string2, int n, int n2, byte by) {
        MsgDialog msgDialog = new MsgDialog();
        msgDialog.setinfoQuest(string, string2, n, n2, by);
        subDialog = msgDialog;
    }

    public static void start_Quest_DialogRead(MainQuest mainQuest) {
        MsgDialog msgDialog = new MsgDialog();
        msgDialog.setinfoQuestRead(mainQuest);
        subDialog = msgDialog;
    }

    public static void start_Party_Dialog() {
        MsgDialog msgDialog = new MsgDialog();
        msgDialog.setinfoParty();
        subDialog = msgDialog;
    }

    public static void start_Auto_HPMP_Dialog() {
        MsgDialog msgDialog = new MsgDialog();
        msgDialog.setinfoAutoHP_MP();
        currentDialog = msgDialog;
    }

    public static void start_Auto_Item_Dialog() {
        MsgDialog msgDialog = new MsgDialog();
        msgDialog.setinfoAutoGetItem();
        currentDialog = msgDialog;
    }

    public static void start_Volume_Dialog() {
        MsgDialog msgDialog = new MsgDialog();
        msgDialog.setinfoVolume();
        currentDialog = msgDialog;
    }

    public static void start_Auto_Buff() {
        MsgDialog msgDialog = new MsgDialog();
        msgDialog.setInfoAutoBuff();
        subDialog = msgDialog;
    }

    public static void start_Chat_Dialog() {
        msgchat.checkRemoveText();
        msgchat.init();
        subDialog = msgchat;
    }

    public static void start_More_Input_Dialog(String[] stringArray, iCommand iCommand2, short s, short s2, String string) {
        InputDialog inputDialog = new InputDialog();
        inputDialog.setinfo(stringArray, iCommand2, s, s2, string);
        subDialog = inputDialog;
    }

    public static void start_More_Input_Dialog(String[] stringArray, iCommand iCommand2, short s, short s2, String string, String[] stringArray2, byte by) {
        GameCanvas.end_Dialog();
        InputDialog inputDialog = new InputDialog();
        inputDialog.setinfo(stringArray, iCommand2, s, s2, string, stringArray2, by);
        subDialog = inputDialog;
    }

    public static void start_Time_Dialog(String string, short s) {
        MsgDialog msgDialog = new MsgDialog();
        msgDialog.setInfoTime(string, s);
        subDialog = msgDialog;
    }

    public static void start_Change_Item(String string, String string2, MainItem[] mainItemArray, MainItem mainItem) {
        MsgDialog msgDialog = new MsgDialog();
        msgDialog.setShowChangeItem(string, string2, mainItemArray, mainItem);
        currentDialog = msgDialog;
    }

    public static void start_Open_Box(String string, String string2, MainItem[] mainItemArray, byte by, byte by2) {
        if (string2 != null && string2.trim().length() == 0) {
            string2 = null;
        }
        MsgDialog msgDialog = new MsgDialog();
        msgDialog.setShowOpenBox(string, mainItemArray, string2, by, by2);
        currentDialog = msgDialog;
    }

    public static void start_Update_Data() {
        MsgDialog msgDialog = new MsgDialog();
        msgDialog.setUpdateData();
        currentDialog = msgDialog;
    }

    public static void start_Pet_Info(PetItem petItem, byte by) {
        MsgDialog msgDialog = new MsgDialog();
        msgDialog.setPetInfo(petItem, by);
        subDialog = msgDialog;
        if (petItem != null) {
            GlobalService.gI().Update_Pet_Eat(TabShopNew.UPDATE_PET_EAT, (short)petItem.Id);
        }
    }

    public static void end_Dialog() {
        currentDialog = null;
        subDialog = null;
        GameCanvas.clearKeyHold();
        GameCanvas.clearKeyPressed();
        if (GameScreen.help.Step >= 0) {
            GameScreen.help.timeReset = 20;
        }
    }

    public static void end_Dialog_Help() {
        currentDialog = null;
    }

    public static void addInfoCharServer(String string) {
        GameCanvas.resetTip();
        if (string != null && string.length() > 0) {
            GameScreen.VecInfoServer.addElement(string);
        }
    }

    public static void addInfoChar(String string) {
        if (string != null && string.length() > 0) {
            if (mFont.tahoma_7_black.getWidth(string) > 140) {
                GameCanvas.addInfoCharCline(string);
            } else {
                GameScreen.infoGame.strInfoCharCline = string;
                GameScreen.infoGame.ydInfoChar = 10;
                GameScreen.infoGame.timeInfoCharCline = 0;
            }
        }
    }

    public static void addInfoCharCline(String string) {
        if (string != null && string.length() > 0) {
            GameScreen.VecInfoChar.addElement(string);
        }
    }

    public void mapKeyPress(int n) {
        PaintInfoGameScreen.timeDoNotClick = timeNow;
        PaintInfoGameScreen.isShowInGame = true;
        if (currentDialog != null) {
            currentDialog.keypress(n);
        } else if (subDialog != null) {
            subDialog.keypress(n);
        } else if (ChatTextField.isShow) {
            ChatTextField.gI().keyPressed(n);
        } else {
            currentScreen.keyPress(n);
        }
        if (TField.isQwerty) {
            if (keyAsciiPress == 114 || keyAsciiPress == 82) {
                GameCanvas.keyMyHold[21] = true;
                GameCanvas.keyMyPressed[21] = true;
            } else if (keyAsciiPress == 116 || keyAsciiPress == 84) {
                GameCanvas.keyMyHold[23] = true;
                GameCanvas.keyMyPressed[23] = true;
            } else if (keyAsciiPress == 121 || keyAsciiPress == 89) {
                GameCanvas.keyMyHold[25] = true;
                GameCanvas.keyMyPressed[25] = true;
            } else if (keyAsciiPress == 117 || keyAsciiPress == 85) {
                GameCanvas.keyMyHold[27] = true;
                GameCanvas.keyMyPressed[27] = true;
            } else if (keyAsciiPress == 105 || keyAsciiPress == 73) {
                GameCanvas.keyMyHold[29] = true;
                GameCanvas.keyMyPressed[29] = true;
            }
        }
        switch (n) {
            case -1: {
                GameCanvas.keyMyHold[2] = true;
                GameCanvas.keyMyPressed[2] = true;
                return;
            }
            case -2: {
                GameCanvas.keyMyHold[8] = true;
                GameCanvas.keyMyPressed[8] = true;
                return;
            }
            case -3: {
                GameCanvas.keyMyHold[4] = true;
                GameCanvas.keyMyPressed[4] = true;
                return;
            }
            case -4: {
                GameCanvas.keyMyHold[6] = true;
                GameCanvas.keyMyPressed[6] = true;
                return;
            }
            case -5: 
            case 10: {
                GameCanvas.keyMyHold[5] = true;
                GameCanvas.keyMyPressed[5] = true;
                return;
            }
            case 48: 
            case 49: 
            case 50: 
            case 51: 
            case 52: 
            case 53: 
            case 54: 
            case 55: 
            case 56: 
            case 57: {
                GameCanvas.keyMyHold[n - 28] = true;
                GameCanvas.keyMyPressed[n - 28] = true;
                return;
            }
            case 42: {
                GameCanvas.keyMyHold[10] = true;
                GameCanvas.keyMyPressed[10] = true;
                return;
            }
            case 35: {
                GameCanvas.keyMyHold[11] = true;
                GameCanvas.keyMyPressed[11] = true;
                return;
            }
            case -6: {
                GameCanvas.keyMyHold[12] = true;
                GameCanvas.keyMyPressed[12] = true;
                return;
            }
            case -7: {
                GameCanvas.keyMyHold[13] = true;
                GameCanvas.keyMyPressed[13] = true;
                return;
            }
        }
    }

    public void mapKeyRelease(int n) {
        switch (n) {
            case -1: {
                GameCanvas.keyMyHold[2] = false;
                GameCanvas.keyMyPressed[2] = false;
                return;
            }
            case -2: {
                GameCanvas.keyMyHold[8] = false;
                GameCanvas.keyMyPressed[8] = false;
                return;
            }
            case -3: {
                GameCanvas.keyMyHold[4] = false;
                GameCanvas.keyMyPressed[4] = false;
                return;
            }
            case -4: {
                GameCanvas.keyMyHold[6] = false;
                GameCanvas.keyMyPressed[6] = false;
                return;
            }
            case -5: 
            case 10: {
                GameCanvas.keyMyHold[5] = false;
                GameCanvas.keyMyPressed[5] = false;
                return;
            }
            case 48: 
            case 49: 
            case 50: 
            case 51: 
            case 52: 
            case 53: 
            case 54: 
            case 55: 
            case 56: 
            case 57: {
                GameCanvas.keyMyHold[n - 28] = false;
                return;
            }
            case 42: {
                GameCanvas.keyMyHold[10] = false;
                return;
            }
            case 35: {
                GameCanvas.keyMyHold[11] = false;
                return;
            }
            case -6: {
                GameCanvas.keyMyHold[12] = false;
                return;
            }
            case -7: {
                GameCanvas.keyMyHold[13] = false;
                return;
            }
        }
    }

    public void keyPressed(int n) {
        if (TField.isQwerty && (n >= 48 && n <= 57 || n >= 65 && n <= 122 || n == 10 || n == 8 || n == 13 || n == 32)) {
            keyAsciiPress = n;
        }
        this.mapKeyPress(n);
    }

    public void keyReleased(int n) {
        if (TField.isQwerty) {
            keyAsciiPress = 0;
        }
        this.mapKeyRelease(n);
    }

    public static boolean isKeyPressed(int n) {
        return keyMyPressed[n];
    }

    public void pointerDragged(int n, int n2) {
        px = n / mGraphics.zoomLevel;
        py = n2 / mGraphics.zoomLevel;
        if (isPointerMove) {
            listPoint.addElement(new Position(n, n2));
        } else if (CRes.abs(px - pxLast) >= 15 || CRes.abs(py - pyLast) >= 15) {
            isPointerMove = true;
        }
    }

    public void pointerPressed(int n, int n2) {
        PaintInfoGameScreen.timeDoNotClick = timeNow;
        PaintInfoGameScreen.isShowInGame = true;
        isPointerDown = true;
        isPointerMove = false;
        isPointerSelect = false;
        isPointerRelease = false;
        isPointerEnd = false;
        if (currentScreen == MapScr.gI()) {
            isPointerClick = true;
        }
        pxLast = n / mGraphics.zoomLevel;
        pyLast = n2 / mGraphics.zoomLevel;
        px = n / mGraphics.zoomLevel;
        py = n2 / mGraphics.zoomLevel;
    }

    public void pointerReleased(int n, int n2) {
        if (!isPointerMove && !isPointerEnd) {
            isPointerSelect = true;
        }
        GameCanvas.clearKeyHold();
        GameCanvas.clearKeyPressed();
        isPointerDown = false;
        isPointerRelease = true;
        isPointerMove = false;
        isPointerClick = true;
        isPointerEnd = false;
        px = n / mGraphics.zoomLevel;
        py = n2 / mGraphics.zoomLevel;
    }

    public static void clearKeyPressed() {
        isPointerRelease = false;
        isPointerDown = false;
        for (int i = 0; i < 30; ++i) {
            GameCanvas.keyMyPressed[i] = false;
        }
    }

    public static void clearKeyPressed(int n) {
        isPointerRelease = false;
        isPointerDown = false;
        GameCanvas.keyMyPressed[n] = false;
    }

    public static void clearKeyHold() {
        isPointerRelease = false;
        isPointerDown = false;
        for (int i = 0; i < 30; ++i) {
            GameCanvas.keyMyHold[i] = false;
        }
    }

    public static void clearKeyHold(int n) {
        isPointerRelease = false;
        isPointerDown = false;
        GameCanvas.keyMyHold[n] = false;
    }

    public static void clearKeyReleased() {
        isPointerDown = false;
        isPointerRelease = false;
        for (int i = 0; i < 30; ++i) {
            GameCanvas.keyMyReleased[i] = false;
        }
    }

    public static void clearAll() {
        GameCanvas.clearKeyHold();
        GameCanvas.clearKeyPressed();
        GameCanvas.clearKeyReleased();
        isPointerSelect = false;
        isPointerMove = false;
        isPointerClick = false;
        isPointerDown = false;
    }

    public static void connect() {
        if (!Session_ME.gI().isConnected()) {
            if (LoginScreen.ip != null) {
                Session_ME.gI().connect(LoginScreen.ip, 19129);
            } else {
                if (IndexServer < 0 || IndexServer >= mSystem.listServer.length || IndexServer >= portServer.length) {
                    return;
                }
                String string = mSystem.listServer[IndexServer][1];
                int n = portServer[IndexServer];
                if (istest) {
                    string = "192.168.1.188";
                }
                Session_ME.gI().connect(string, n);
            }
        }
    }

    public void getSize() {
        w = TemCanvas.wMain;
        h = TemCanvas.hMain;
        hw = w / 2;
        hh = h / 2;
    }

    public static int getSecond() {
        return (int)(mSystem.currentTimeMillis() / 1000L);
    }

    public static boolean isPointer(int n, int n2, int n3, int n4) {
        if (!isPointerDown && !isPointerRelease) {
            return false;
        }
        return GameCanvas.isPoint(n, n2, n3, n4);
    }

    public static boolean isPointSelect(int n, int n2, int n3, int n4) {
        if (!isPointerSelect) {
            return false;
        }
        return GameCanvas.isPoint(n, n2, n3, n4);
    }

    public static boolean isPoint(int n, int n2, int n3, int n4) {
        return px >= n && px <= n + n3 && py >= n2 && py <= n2 + n4;
    }

    public static boolean isPointLast(int n, int n2, int n3, int n4) {
        return pxLast >= n && pxLast <= n + n3 && pyLast >= n2 && pyLast <= n2 + n4;
    }

    public static boolean keyMove(int n) {
        switch (n) {
            case 1: {
                if (!keyMyHold[2] && !keyMyHold[22]) break;
                return true;
            }
            case 0: {
                if (!keyMyHold[8] && !keyMyHold[28]) break;
                return true;
            }
            case 2: {
                if (!keyMyHold[4] && !keyMyHold[24]) break;
                return true;
            }
            case 3: {
                if (!keyMyHold[6] && !keyMyHold[26]) break;
                return true;
            }
        }
        return false;
    }

    public void clearKeyMove(int n) {
        switch (n) {
            case 1: {
                GameCanvas.keyMyHold[2] = false;
                GameCanvas.keyMyHold[22] = false;
                break;
            }
            case 0: {
                GameCanvas.keyMyHold[8] = false;
                GameCanvas.keyMyHold[28] = false;
                break;
            }
            case 2: {
                GameCanvas.keyMyHold[4] = false;
                GameCanvas.keyMyHold[24] = false;
                break;
            }
            case 3: {
                GameCanvas.keyMyHold[6] = false;
                GameCanvas.keyMyHold[26] = false;
            }
        }
    }

    public void updateCountTick() {
        if (countTips != 0L && mSystem.currentTimeMillis() - countTips >= 600000L) {
            iTips = CRes.random(T.mTips.length);
            GameCanvas.addInfoCharServer(T.Tips + T.mTips[iTips]);
        }
    }

    public static void resetTip() {
        countTips = mSystem.currentTimeMillis();
        iTips = 0;
    }

    static {
        t = new T();
        menu2 = new Menu2();
        lowGraphic = false;
        isSmallScreen = false;
        isVNLanguage = true;
        device = 0;
        stringPackageName = "";
        IndexRes = (byte)-1;
        IndexServer = 0;
        IndexCharPar = (short)-1;
        portServer = new int[]{19129, 19129, 19129, 19129, 19129, 19129, 19129, 19129};
        langServer = new int[]{0, 0, 1, 0, 0, 0, 0, 0};
        countLogin = 0L;
        linkIP = "http://knightageonline.com/srvip/";
        isBB = false;
        isPointerDown = false;
        isPointerRelease = false;
        isPointerSelect = false;
        isPointerMove = false;
        isPointerClick = false;
        isPointerEnd = false;
        hCommand = 25;
        hText = 14;
        hLoad = 0;
        timeNow = 0L;
        keyMyPressed = new boolean[30];
        keyMyReleased = new boolean[30];
        keyMyHold = new boolean[30];
        istest = false;
        countTips = 0L;
        iTips = 0;
    }
}

