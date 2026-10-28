/*
 * Decompiled with CFR 0.152.
 */
package Model;

import Build_Usa.Usa_Server;
import CLib.Main;
import CLib.Session_ME;
import CLib.mFont;
import CLib.mGraphics;
import CLib.mSound;
import CLib.mSystem;
import CLib.mVector;
import GameObjects.Item;
import GameObjects.MainHelp;
import GameObjects.MainItem;
import GameObjects.MainObject;
import GameObjects.Player;
import GameScreen.GameScreen;
import GameScreen.PaintInfoGameScreen;
import InterfaceComponents.MainTabNew;
import InterfaceComponents.RunWord;
import InterfaceComponents.TabConfig;
import InterfaceComponents.iCommand;
import Main.GameCanvas;
import Model.AvMain;
import Model.CRes;
import Model.T;
import com.silverknight.TemMidlet;
import netcommand.global.GlobalService;

public class Menu2
extends AvMain {
    public boolean isShowMenu;
    public mVector menuItems;
    public int menuSelectedItem;
    public int SelectFocus;
    public int menuX;
    public int menuY;
    public int menuW;
    public int menuH;
    public int menuTemY;
    public int hPlus;
    public static int cmtoX;
    public static int cmx;
    public static int cmdy;
    public static int cmvy;
    public static int cmxLim;
    public static int xc;
    int pos;
    int sizeMenu;
    String nameMenu = "";
    String[] mStrTalk;
    public RunWord runText;
    public static boolean isHelp;
    public static boolean isGiaotiep;
    public static boolean isPaint;
    public static boolean isLoadData;
    public static byte isNPCMenu;
    public static final byte NORMAL = 0;
    public static final byte NPC_MENU = 1;
    public static final byte QUICK_MENU = 2;
    mVector vecFocus;
    public static MainObject objSelect;
    int IdMenu;
    int IdNpc;
    public static int IdNPCLast;
    byte typeO;
    byte timeShow = 0;
    int hShow;
    int maxShow;
    boolean disableClose;
    public boolean isSv = false;
    int archorRunText;
    boolean isPosPoint = false;
    iCommand cmdChangeScreen;
    iCommand cmdTouch;
    public iCommand cmdNgua;
    int xBegin;
    int w2cmd;
    public int w;
    int pa = 0;
    boolean trans = false;
    private int pointerDownTime;
    private int pointerDownFirstX;
    private int[] pointerDownLastX = new int[3];
    private boolean pointerIsDowning;
    private boolean isDownWhenRunning;
    private int waitToPerform;
    private int cmRun;
    int cmvx;
    int cmdx;

    public void startAt(mVector mVector2, int n, String string, boolean bl, mVector mVector3) {
        isLoadData = false;
        this.isSv = false;
        this.waitToPerform = 0;
        this.runText = null;
        this.right = null;
        isNPCMenu = 0;
        isGiaotiep = bl;
        this.vecFocus = mVector3;
        this.SelectFocus = 0;
        if (isGiaotiep && (this.vecFocus == null || this.vecFocus.size() == 0)) {
            return;
        }
        this.nameMenu = string;
        isHelp = false;
        this.disableClose = false;
        this.menuSelectedItem = 0;
        this.pos = n;
        if (mVector2 == null || mVector2.size() == 0) {
            return;
        }
        this.menuItems = mVector2;
        this.isShowMenu = true;
        if (n == -1) {
            this.menuItems.addElement(new iCommand(T.close, 1, this));
            this.hPlus = 0;
            this.menuW = 60;
            this.menuH = 60;
            for (int i = 0; i < mVector2.size(); ++i) {
                iCommand iCommand2 = (iCommand)mVector2.elementAt(i);
                int n2 = mFont.tahoma_7_yellow.getWidth(iCommand2.caption);
                if (n2 <= this.menuW - 8) continue;
                iCommand2.subCaption = mFont.tahoma_7b_yellow.splitFontArray(iCommand2.caption, this.menuW - 8);
            }
            this.w = mVector2.size() * this.menuW - 1;
            if (this.w > GameCanvas.w - 2) {
                this.w = GameCanvas.w - 2;
            }
            this.menuX = GameCanvas.hw - this.w / 2;
            if (this.menuX < 1) {
                this.menuX = 1;
            }
            this.menuY = GameCanvas.h - this.menuH - (GameCanvas.hCommand + 1);
            if (GameCanvas.isTouch) {
                this.menuY -= 3;
            }
            this.menuY += 27;
            this.menuTemY = this.menuY;
            cmxLim = this.menuItems.size() * this.menuW - GameCanvas.w;
            if (cmxLim < 0) {
                cmxLim = 0;
            }
            cmtoX = 0;
            cmx = 0;
            xc = 50;
        } else {
            if (isGiaotiep) {
                objSelect = (MainObject)this.vecFocus.elementAt(0);
            }
            this.menuW = GameCanvas.hCommand;
            if (GameCanvas.isTouch) {
                this.menuW = 32;
            }
            this.sizeMenu = GameCanvas.h / 4 * 3 / this.menuW - (bl ? 2 : 1);
            if (GameCanvas.isTouch) {
                ++this.sizeMenu;
            }
            this.w = GameCanvas.w / 3;
            if (this.w < mFont.tahoma_7b_white.getWidth(string) + 30) {
                this.w = mFont.tahoma_7b_white.getWidth(string) + 30;
            }
            this.hPlus = GameCanvas.hCommand;
            if (bl) {
                this.hPlus += this.menuW;
            }
            int n3 = 120;
            int n4 = 30;
            if (bl) {
                n4 = 50;
            }
            for (int i = 0; i < mVector2.size(); ++i) {
                iCommand iCommand3 = (iCommand)mVector2.elementAt(i);
                int n5 = mFont.tahoma_7b_white.getWidth(iCommand3.caption) + n4;
                if (n5 <= n3) continue;
                n3 = n5;
            }
            if (this.w < n3) {
                this.w = n3;
            }
            if (this.w > GameCanvas.w) {
                this.w = GameCanvas.w;
            }
            cmtoX = 0;
            cmx = 0;
            iCommand iCommand4 = null;
            iCommand4 = GameCanvas.isTouch ? new iCommand(T.close, 1, this) : new iCommand(T.back, 1, this);
            if (mVector2.size() > this.sizeMenu) {
                this.menuH = this.sizeMenu * this.menuW + 8;
                cmxLim = (mVector2.size() - this.sizeMenu) * this.menuW;
            } else {
                this.menuH = mVector2.size() * this.menuW + 8;
                cmxLim = 0;
            }
            this.setPos();
            this.menuTemY = this.menuY;
            if (iCommand4 != null) {
                if (GameCanvas.isTouch) {
                    iCommand4.setPos(this.menuX + this.w - 11, this.menuY - this.hPlus + GameCanvas.hCommand / 2 - 2, PaintInfoGameScreen.fraCloseMenu, "");
                }
                this.right = iCommand4;
            }
        }
        if (GameCanvas.isTouch) {
            this.menuSelectedItem = -1;
        }
        isLoadData = true;
        this.resetBegin();
    }

    public void startAt_Sv(mVector mVector2, int n, String string, boolean bl, mVector mVector3) {
        this.isSv = true;
        isLoadData = false;
        this.waitToPerform = 0;
        this.runText = null;
        this.right = null;
        isNPCMenu = 0;
        isGiaotiep = bl;
        this.vecFocus = mVector3;
        this.SelectFocus = 0;
        if (isGiaotiep && (this.vecFocus == null || this.vecFocus.size() == 0)) {
            return;
        }
        this.nameMenu = string;
        isHelp = false;
        this.disableClose = false;
        this.menuSelectedItem = 0;
        this.pos = n;
        if (mVector2 == null || mVector2.size() == 0) {
            return;
        }
        this.menuItems = mVector2;
        this.isShowMenu = true;
        if (n == -1) {
            this.menuItems.addElement(new iCommand(T.close, 1, this));
            this.hPlus = 0;
            this.menuW = 60;
            this.menuH = 60;
            for (int i = 0; i < mVector2.size(); ++i) {
                iCommand iCommand2 = (iCommand)mVector2.elementAt(i);
                int n2 = mFont.tahoma_7_yellow.getWidth(iCommand2.caption);
                if (n2 <= this.menuW - 8) continue;
                iCommand2.subCaption = mFont.tahoma_7b_yellow.splitFontArray(iCommand2.caption, this.menuW - 8);
            }
            this.w = mVector2.size() * this.menuW - 1;
            if (this.w > GameCanvas.w - 2) {
                this.w = GameCanvas.w - 2;
            }
            this.menuX = GameCanvas.hw - this.w / 2;
            if (this.menuX < 1) {
                this.menuX = 1;
            }
            this.menuY = GameCanvas.h - this.menuH - (GameCanvas.hCommand + 1);
            if (GameCanvas.isTouch) {
                this.menuY -= 3;
            }
            this.menuY += 27;
            this.menuTemY = this.menuY;
            cmxLim = this.menuItems.size() * this.menuW - GameCanvas.w;
            if (cmxLim < 0) {
                cmxLim = 0;
            }
            cmtoX = 0;
            cmx = 0;
            xc = 50;
        } else {
            if (isGiaotiep) {
                objSelect = (MainObject)this.vecFocus.elementAt(0);
            }
            this.menuW = GameCanvas.hCommand;
            if (GameCanvas.isTouch) {
                this.menuW = 32;
            }
            this.sizeMenu = GameCanvas.h / 4 * 3 / this.menuW - (bl ? 2 : 1);
            if (GameCanvas.isTouch) {
                ++this.sizeMenu;
            }
            this.w = GameCanvas.w / 3;
            if (this.w < mFont.tahoma_7b_white.getWidth(string) + 30) {
                this.w = mFont.tahoma_7b_white.getWidth(string) + 30;
            }
            this.hPlus = GameCanvas.hCommand;
            if (bl) {
                this.hPlus += this.menuW;
            }
            int n3 = 120;
            int n4 = 30;
            if (bl) {
                n4 = 50;
            }
            for (int i = 0; i < mVector2.size(); ++i) {
                iCommand iCommand3 = (iCommand)mVector2.elementAt(i);
                int n5 = mFont.tahoma_7b_white.getWidth(iCommand3.caption) + n4;
                if (n5 <= n3) continue;
                n3 = n5;
            }
            if (this.w < n3) {
                this.w = n3;
            }
            if (this.w > GameCanvas.w) {
                this.w = GameCanvas.w;
            }
            cmtoX = 0;
            cmx = 0;
            iCommand iCommand4 = null;
            iCommand4 = GameCanvas.isTouch ? new iCommand(T.close, 1, this) : new iCommand(T.back, 1, this);
            if (mVector2.size() > this.sizeMenu) {
                this.menuH = this.sizeMenu * this.menuW + 8;
                cmxLim = (mVector2.size() - this.sizeMenu) * this.menuW;
            } else {
                this.menuH = mVector2.size() * this.menuW + 8;
                cmxLim = 0;
            }
            this.setPos();
            this.menuTemY = this.menuY;
            if (iCommand4 != null) {
                if (GameCanvas.isTouch) {
                    iCommand4.setPos(this.menuX + this.w - 11, this.menuY - this.hPlus + GameCanvas.hCommand / 2 - 2, PaintInfoGameScreen.fraCloseMenu, "");
                }
                this.right = iCommand4;
            }
        }
        if (GameCanvas.isTouch) {
            this.menuSelectedItem = -1;
        }
        isLoadData = true;
        this.resetBegin();
    }

    public void startAt_Map_World(mVector mVector2, int n, String string, boolean bl, mVector mVector3) {
        isLoadData = false;
        this.isSv = false;
        this.waitToPerform = 0;
        this.runText = null;
        this.right = null;
        isNPCMenu = 0;
        isGiaotiep = bl;
        this.vecFocus = mVector3;
        this.SelectFocus = 0;
        if (isGiaotiep && (this.vecFocus == null || this.vecFocus.size() == 0)) {
            return;
        }
        this.nameMenu = string;
        isHelp = false;
        this.disableClose = false;
        this.menuSelectedItem = 0;
        this.pos = n;
        if (mVector2 == null || mVector2.size() == 0) {
            return;
        }
        this.menuItems = mVector2;
        this.isShowMenu = true;
        if (n == -1) {
            this.menuItems.addElement(new iCommand(T.close, 1, this));
            this.hPlus = 0;
            this.menuW = 60;
            this.menuH = 60;
            for (int i = 0; i < mVector2.size(); ++i) {
                iCommand iCommand2 = (iCommand)mVector2.elementAt(i);
                int n2 = mFont.tahoma_7_yellow.getWidth(iCommand2.caption);
                if (n2 <= this.menuW - 8) continue;
                iCommand2.subCaption = mFont.tahoma_7b_yellow.splitFontArray(iCommand2.caption, this.menuW - 8);
            }
            this.w = mVector2.size() * this.menuW - 1;
            if (this.w > GameCanvas.w - 2) {
                this.w = GameCanvas.w - 2;
            }
            this.menuX = GameCanvas.hw - this.w / 2;
            if (this.menuX < 1) {
                this.menuX = 1;
            }
            this.menuY = GameCanvas.h - this.menuH - (GameCanvas.hCommand + 1);
            if (GameCanvas.isTouch) {
                this.menuY -= 3;
            }
            this.menuY += 27;
            this.menuTemY = this.menuY;
            cmxLim = this.menuItems.size() * this.menuW - GameCanvas.w;
            if (cmxLim < 0) {
                cmxLim = 0;
            }
            cmtoX = 0;
            cmx = 0;
            xc = 50;
        } else {
            if (isGiaotiep) {
                objSelect = (MainObject)this.vecFocus.elementAt(0);
            }
            this.menuW = GameCanvas.hCommand;
            if (GameCanvas.isTouch) {
                this.menuW = 32;
            }
            this.sizeMenu = GameCanvas.h / 4 * 3 / this.menuW - (bl ? 2 : 1);
            if (GameCanvas.isTouch) {
                ++this.sizeMenu;
            }
            this.w = GameCanvas.w / 3;
            if (this.w < mFont.tahoma_7b_white.getWidth(string) + 30) {
                this.w = mFont.tahoma_7b_white.getWidth(string) + 30;
            }
            this.hPlus = GameCanvas.hCommand;
            if (bl) {
                this.hPlus += this.menuW;
            }
            int n3 = 120;
            int n4 = 30;
            if (bl) {
                n4 = 50;
            }
            for (int i = 0; i < mVector2.size(); ++i) {
                iCommand iCommand3 = (iCommand)mVector2.elementAt(i);
                int n5 = mFont.tahoma_7b_white.getWidth(iCommand3.caption) + n4;
                if (n5 <= n3) continue;
                n3 = n5;
            }
            if (this.w < n3) {
                this.w = n3;
            }
            if (this.w > GameCanvas.w) {
                this.w = GameCanvas.w;
            }
            cmtoX = 0;
            cmx = 0;
            iCommand iCommand4 = null;
            iCommand4 = GameCanvas.isTouch ? new iCommand(T.close, 1, this) : new iCommand(T.back, 1, this);
            if (mVector2.size() > this.sizeMenu) {
                this.menuH = this.sizeMenu * this.menuW + 8;
                cmxLim = (mVector2.size() - this.sizeMenu) * this.menuW;
            } else {
                this.menuH = mVector2.size() * this.menuW + 8;
                cmxLim = 0;
            }
            this.setPos();
            this.menuTemY = this.menuY;
            if (iCommand4 != null) {
                if (GameCanvas.isTouch) {
                    iCommand4.setPos(this.menuX + this.w - 11, this.menuY - this.hPlus + GameCanvas.hCommand / 2 - 2, PaintInfoGameScreen.fraCloseMenu, "");
                }
                this.right = iCommand4;
            }
        }
        if (GameCanvas.isTouch) {
            this.menuSelectedItem = -1;
        }
        isLoadData = true;
        this.resetBegin();
    }

    public void setinfoDynamic(mVector mVector2, int n, int n2, int n3, String string) {
        isLoadData = false;
        this.isSv = false;
        this.waitToPerform = 0;
        this.right = null;
        this.runText = null;
        isGiaotiep = false;
        this.vecFocus = null;
        if (mVector2 == null) {
            return;
        }
        this.nameMenu = string;
        isHelp = false;
        isNPCMenu = 0;
        this.menuSelectedItem = 0;
        this.IdMenu = n2;
        this.IdNpc = n3;
        this.pos = n;
        this.disableClose = false;
        this.isShowMenu = true;
        this.menuItems = new mVector("Menu2 menuItem2");
        this.menuW = GameCanvas.hCommand;
        if (GameCanvas.isTouch) {
            this.menuW = 32;
        }
        this.sizeMenu = GameCanvas.h / 4 * 3 / this.menuW - 1;
        if (GameCanvas.isTouch) {
            ++this.sizeMenu;
        }
        this.w = GameCanvas.w / 3;
        this.hPlus = this.menuW;
        int n4 = 120;
        if (n4 < mFont.tahoma_7b_white.getWidth(string) + 30) {
            n4 = mFont.tahoma_7b_white.getWidth(string) + 30;
        }
        for (int i = 0; i < mVector2.size(); ++i) {
            iCommand iCommand2 = (iCommand)mVector2.elementAt(i);
            iCommand iCommand3 = new iCommand(iCommand2.caption, 2, this);
            int n5 = mFont.tahoma_7b_white.getWidth(iCommand2.caption) + 20;
            if (n5 > n4) {
                n4 = n5;
            }
            this.menuItems.addElement(iCommand3);
        }
        iCommand iCommand4 = null;
        iCommand4 = GameCanvas.isTouch ? new iCommand(T.close, 1, this) : new iCommand(T.back, 1, this);
        this.w = n4;
        if (this.w > GameCanvas.w) {
            this.w = GameCanvas.w;
        }
        if (this.menuItems.size() > this.sizeMenu) {
            this.menuH = this.sizeMenu * this.menuW + 8;
            cmxLim = (this.menuItems.size() - this.sizeMenu) * this.menuW;
        } else {
            this.menuH = this.menuItems.size() * this.menuW + 8;
            cmxLim = 0;
        }
        cmtoX = 0;
        cmx = 0;
        this.setPos();
        this.menuTemY = this.menuY;
        if (iCommand4 != null) {
            if (GameCanvas.isTouch) {
                iCommand4.setPos(this.menuX + this.w - 11, this.menuY - this.hPlus + GameCanvas.hCommand / 2 - 2, PaintInfoGameScreen.fraCloseMenu, "");
            }
            this.right = iCommand4;
        }
        if (GameCanvas.isTouch) {
            this.menuSelectedItem = -1;
        }
        isLoadData = true;
        this.resetBegin();
    }

    public void startAt_NPC(mVector mVector2, String string, int n, byte by, boolean bl, int n2) {
        isLoadData = false;
        this.isSv = false;
        this.waitToPerform = 0;
        this.right = null;
        isNPCMenu = 1;
        this.SelectFocus = 0;
        this.nameMenu = string;
        isHelp = false;
        this.disableClose = false;
        isGiaotiep = false;
        this.IdNpc = n;
        IdNPCLast = n;
        this.typeO = by;
        this.archorRunText = n2;
        this.menuSelectedItem = 0;
        this.menuItems = mVector2 == null || mVector2.size() == 0 ? new mVector("Menu2 menuItem3") : mVector2;
        this.isShowMenu = true;
        this.menuW = GameCanvas.hCommand;
        if (GameCanvas.isTouch) {
            this.menuW = 32;
        }
        this.sizeMenu = 0;
        this.w = GameCanvas.w - 10;
        if (this.w > 300) {
            this.w = 300;
        }
        this.mStrTalk = mFont.tahoma_7_black.splitFontArray(string, this.w - 20);
        this.hPlus = GameCanvas.hCommand;
        cmtoX = 0;
        cmx = 0;
        int n3 = this.mStrTalk.length;
        if (!bl) {
            this.menuItems.addElement(new iCommand(T.close, 1, this));
        } else if (n3 == 1) {
            n3 = 2;
        }
        this.menuH = (n3 + 2) * GameCanvas.hText + ((this.menuItems.size() - 1) / 2 + 1) * (iCommand.hButtonCmd + 5) + 5;
        cmxLim = 0;
        this.menuX = GameCanvas.hw - this.w / 2;
        this.menuTemY = this.menuY = GameCanvas.h - this.menuH - 10;
        this.runText = new RunWord();
        this.runText.startDialogChain(string, 0, this.menuX + 10, this.menuY + 10 + GameCanvas.hText, this.w - 20, mFont.tahoma_7_white);
        this.setPosNPC();
        if (GameCanvas.isTouch) {
            this.menuSelectedItem = -1;
        }
        isLoadData = true;
        this.resetBegin();
    }

    public void setAt_Quick() {
        iCommand iCommand2;
        this.isPosPoint = PaintInfoGameScreen.isLevelPoint;
        this.timeShow = 1;
        isLoadData = false;
        this.isSv = false;
        this.waitToPerform = 0;
        this.right = null;
        isNPCMenu = (byte)2;
        this.SelectFocus = 0;
        this.nameMenu = "";
        isHelp = false;
        this.disableClose = false;
        isGiaotiep = false;
        this.menuSelectedItem = 0;
        this.w = GameCanvas.w - 40;
        this.isShowMenu = true;
        this.menuW = 40;
        cmtoX = 0;
        cmx = 0;
        this.hShow = 0;
        this.menuH = 40;
        this.maxShow = (byte)(this.w / this.menuH);
        this.menuItems = new mVector("Menu2 menuItems");
        iCommand iCommand3 = new iCommand(T.auto, 3, 2, (AvMain)this);
        iCommand iCommand4 = new iCommand(T.dosat, 4, 4, (AvMain)this);
        iCommand iCommand5 = new iCommand(T.setPk, 5, 3, (AvMain)this);
        iCommand iCommand6 = new iCommand(T.mevent, 6, 0, (AvMain)this);
        iCommand iCommand7 = new iCommand(T.listFriend, 7, 1, (AvMain)this);
        this.cmdNgua = new iCommand(T.TuseNgua, 14, 10, (AvMain)this);
        if (Main.isPC) {
            this.cmdChangeScreen = new iCommand(T.changeScrennSmall, 8, 5, (AvMain)this);
            this.cmdChangeScreen.caption = Main.level == 1 ? T.normalScreen : (mGraphics.zoomLevel == 1 ? T.normalScreen : T.changeScrennSmall);
        } else {
            this.cmdTouch = new iCommand(T.touch + "/" + T.keypad, 8, 5, (AvMain)this);
        }
        iCommand iCommand8 = new iCommand(T.naptien, 11, 9, (AvMain)this);
        iCommand iCommand9 = new iCommand(T.logout, 12, 8, (AvMain)this);
        this.menuItems.addElement(this.cmdNgua);
        this.menuItems.addElement(iCommand6);
        this.menuItems.addElement(iCommand7);
        if (GameScreen.player.myClan != null) {
            iCommand2 = new iCommand(T.clan, 9, 6, (AvMain)this);
            this.menuItems.addElement(iCommand2);
        }
        if (Player.party != null) {
            iCommand2 = new iCommand(T.party, 10, 7, (AvMain)this);
            this.menuItems.addElement(iCommand2);
        }
        this.menuItems.addElement(iCommand3);
        this.menuItems.addElement(iCommand5);
        if (!mSystem.isHideNaptien()) {
            this.menuItems.addElement(iCommand8);
        }
        this.menuItems.addElement(iCommand4);
        if (Main.isPC) {
            this.menuItems.addElement(this.cmdChangeScreen);
        } else {
            this.menuItems.addElement(this.cmdTouch);
        }
        this.menuItems.addElement(iCommand9);
        if (this.maxShow > this.menuItems.size()) {
            this.maxShow = this.menuItems.size();
        }
        this.w = this.maxShow * this.menuH;
        cmxLim = 0;
        this.menuX = !this.isPosPoint ? GameCanvas.hw - this.w / 2 : 20;
        this.menuTemY = this.menuY = GameCanvas.h - 40;
        if (GameCanvas.isTouch) {
            this.menuSelectedItem = -1;
        }
        isLoadData = true;
        for (int i = 0; i < this.menuItems.size(); ++i) {
            iCommand iCommand10 = (iCommand)this.menuItems.elementAt(i);
            iCommand10.setPos_ShowName(this.menuX + this.menuH / 2 + i * this.menuH, this.menuY + this.menuH / 2, PaintInfoGameScreen.mfraIconQuick[iCommand10.subIndex], iCommand10.caption, 0, -32);
        }
        cmxLim = (this.menuItems.size() - this.maxShow) * this.menuH;
        this.menuSelectedItem = -1;
        this.resetBegin();
    }

    public void resetBegin() {
        for (int i = 0; i < this.pointerDownLastX.length; ++i) {
            this.pointerDownLastX[i] = 0;
        }
        this.pointerDownTime = 0;
        this.pointerDownFirstX = 0;
        this.pointerIsDowning = false;
        this.isDownWhenRunning = false;
        this.waitToPerform = 0;
        this.cmRun = 0;
    }

    public void setPosNPC() {
        int n = this.menuItems.size();
        if (n == 1) {
            this.xBegin = this.menuX + this.w / 2;
            this.w2cmd = 0;
        } else if (n == 2) {
            this.w2cmd = 20;
            this.xBegin = this.menuX + this.w / 2 - this.w2cmd / 2 - iCommand.wButtonCmd / 2;
        } else {
            this.w2cmd = 20;
            this.xBegin = this.menuX + this.w / 2 - this.w2cmd / 2 - iCommand.wButtonCmd / 2;
        }
        for (int i = 0; i < n; ++i) {
            iCommand iCommand2 = (iCommand)this.menuItems.elementAt(i);
            if (n == 3 && i == 2) {
                iCommand2.setPos(this.menuX + this.w / 2, this.menuY + this.menuH - iCommand.hButtonCmd - (n - 1) / 2 * (iCommand.hButtonCmd + 5) + 7 + i / 2 * (iCommand.hButtonCmd + 5), null, iCommand2.caption);
            } else {
                iCommand2.setPos(this.xBegin + i % 2 * (iCommand.wButtonCmd + this.w2cmd), this.menuY + this.menuH - iCommand.hButtonCmd - (n - 1) / 2 * (iCommand.hButtonCmd + 5) + 7 + i / 2 * (iCommand.hButtonCmd + 5), null, iCommand2.caption);
            }
            if (i != 0) continue;
            iCommand2.isSelect = true;
        }
    }

    public void setPos() {
        switch (this.pos) {
            case 0: {
                this.menuX = 2;
                this.menuY = GameCanvas.h - GameCanvas.hCommand - this.menuH - 2;
                if (!GameCanvas.isTouch) break;
                this.menuY += GameCanvas.hCommand;
                break;
            }
            case 1: {
                this.menuX = GameCanvas.w - this.w - 2;
                this.menuY = GameCanvas.h - GameCanvas.hCommand - this.menuH - 2;
                if (!GameCanvas.isTouch) break;
                this.menuY += GameCanvas.hCommand;
                break;
            }
            case 2: 
            case 4: {
                this.menuX = GameCanvas.hw - this.w / 2;
                this.menuY = GameCanvas.h / 2 - this.menuH / 2 - 2 + this.menuW / 2 + 6;
                break;
            }
            case 3: {
                this.menuX = 2;
                this.menuY = 2;
            }
        }
    }

    public void commandPointer(int n, int n2) {
        mVector mVector2 = Item.VecInvetoryPlayer;
        switch (n) {
            case 0: {
                this.isShowMenu = false;
                iCommand iCommand2 = (iCommand)this.menuItems.elementAt(this.menuSelectedItem);
                this.perform(iCommand2);
                break;
            }
            case 2: {
                GlobalService.gI().Dynamic_Menu((short)this.IdNpc, (byte)this.IdMenu, (byte)this.menuSelectedItem);
                this.isShowMenu = false;
                GameCanvas.isPointerSelect = false;
                break;
            }
            case 3: {
                this.isShowMenu = false;
                GameScreen.gI().doMenuAuto();
                break;
            }
            case 4: {
                this.isShowMenu = false;
                GameScreen.gI().cmdSetDoSat.perform();
                break;
            }
            case 5: {
                this.isShowMenu = false;
                GameScreen.gI().cmdSetPk.perform();
                break;
            }
            case 6: {
                this.isShowMenu = false;
                TabConfig.cmdEvent.perform();
                break;
            }
            case 7: {
                GameScreen.gI().cmdListFriend.perform();
                break;
            }
            case 8: {
                if (Main.isPC) {
                    GameCanvas.start_Left_Dialog(T.changeSizeScreen, new iCommand(T.select, 13, this));
                    break;
                }
                TabConfig.cmdKeypad.perform();
                break;
            }
            case 9: {
                TabConfig.cmdShowClan.perform();
                break;
            }
            case 10: {
                GameScreen.gI().cmdParty.perform();
                break;
            }
            case 11: {
                if (mSystem.isIpAppstore()) {
                    mVector mVector3 = new mVector("Menu2 vec2");
                    for (int i = 0; i < TemMidlet.google_listGems.length; ++i) {
                        iCommand iCommand3 = new iCommand(TemMidlet.google_listGems[i], 20, i, (AvMain)this);
                        iCommand3.setFraCaption(AvMain.fraDiamond, 1, 1);
                        mVector3.addElement(iCommand3);
                    }
                    GameCanvas.menu2.startAt(mVector3, 2, T.naptien, false, null);
                    break;
                }
                if (TemMidlet.currentIAPStore == 1) {
                    mVector mVector4 = new mVector("Menu2 vec");
                    for (int i = 0; i < TemMidlet.listGems.length; ++i) {
                        iCommand iCommand4 = new iCommand(TemMidlet.listGems[i], 20, i, (AvMain)this);
                        iCommand4.setFraCaption(AvMain.fraDiamond, 1, 1);
                        mVector4.addElement(iCommand4);
                    }
                    GameCanvas.menu2.startAt(mVector4, 2, T.naptien, false, null);
                    break;
                }
                if (TemMidlet.currentIAPStore == 2) {
                    if (Usa_Server.isUsa_server) {
                        if (GameCanvas.naptien > 0) {
                            mVector mVector5 = new mVector("Menu2 vec2");
                            for (int i = 0; i < TemMidlet.google_listGems.length; ++i) {
                                iCommand iCommand5 = new iCommand(TemMidlet.google_listGems[i], 20, i, (AvMain)this);
                                iCommand5.setFraCaption(AvMain.fraDiamond, 1, 1);
                                mVector5.addElement(iCommand5);
                            }
                            GameCanvas.menu2.startAt(mVector5, 2, T.naptien, false, null);
                            return;
                        }
                        if (GameCanvas.naptien <= 0) {
                            GameCanvas.start_Ok_Dialog(T.hetlannap);
                            return;
                        }
                    }
                    mVector mVector6 = new mVector("Menu2 vec2");
                    for (int i = 0; i < TemMidlet.google_listGems.length; ++i) {
                        iCommand iCommand6 = new iCommand(TemMidlet.google_listGems[i], 20, i, (AvMain)this);
                        iCommand6.setFraCaption(AvMain.fraDiamond, 1, 1);
                        mVector6.addElement(iCommand6);
                    }
                    GameCanvas.menu2.startAt(mVector6, 2, T.naptien, false, null);
                    break;
                }
                GlobalService.gI().send_cmd_server((byte)-56);
                GameCanvas.start_Ok_Dialog(T.pleaseWait);
                break;
            }
            case 12: {
                GameScreen.player.resetAction();
                Session_ME.gI().close();
                GameCanvas.login.Show();
                GameScreen.player = new Player(0, 0, "unname", 0, 0);
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
            case 1: {
                this.doCloseMenu();
                break;
            }
            case 13: {
                if (mGraphics.zoomLevel > 1) {
                    // empty if block
                }
                Main.exit();
                break;
            }
            case 14: {
                mVector mVector7 = new mVector("Menu2 vecngua");
                this.isShowMenu = false;
                if (GameScreen.player.typeMount != -1) {
                    TabConfig.cmdXuongNgua.perform();
                    break;
                }
                for (int i = 0; i < mVector2.size(); ++i) {
                    MainItem mainItem = (MainItem)mVector2.elementAt(i);
                    if (mainItem == null || mainItem.ItemCatagory != 4 || !this.isNgua((short)mainItem.Id)) continue;
                    mVector7.addElement(mainItem);
                }
                if (mVector7.size() > 0) {
                    GameScreen.gI().doMenuUseNgua(mVector7);
                    break;
                }
                GameCanvas.start_Ok_Dialog(T.khongcongua);
            }
        }
        super.commandPointer(n, n2);
    }

    public boolean isNgua(short s) {
        return s == 62 || s == 63 || s == 64 || s == 65 || s == 66;
    }

    public void updateMenuKey() {
        if (!isPaint) {
            return;
        }
        if (!this.isShowMenu) {
            return;
        }
        boolean bl = false;
        if (isGiaotiep) {
            if (GameCanvas.keyMyPressed[2]) {
                bl = true;
                --this.menuSelectedItem;
                if (this.menuSelectedItem < 0) {
                    this.menuSelectedItem = this.menuItems.size() - 1;
                }
                GameCanvas.clearKeyPressed(2);
            } else if (GameCanvas.keyMyPressed[8]) {
                bl = true;
                ++this.menuSelectedItem;
                if (this.menuSelectedItem > this.menuItems.size() - 1) {
                    this.menuSelectedItem = 0;
                }
                GameCanvas.clearKeyPressed(8);
            }
            int n = this.SelectFocus--;
            if (GameCanvas.keyMyPressed[4]) {
                GameCanvas.clearKeyPressed(4);
            }
            if (GameCanvas.keyMyPressed[6]) {
                ++this.SelectFocus;
                GameCanvas.clearKeyPressed(6);
            }
            this.SelectFocus = this.resetSelect(this.SelectFocus, this.vecFocus.size() - 1, true);
            if (this.SelectFocus != n) {
                objSelect = (MainObject)this.vecFocus.elementAt(this.SelectFocus);
            }
        } else if (isNPCMenu == 1) {
            int n = this.menuSelectedItem;
            if (GameCanvas.keyMyHold[4] || GameCanvas.keyMyHold[2]) {
                --this.menuSelectedItem;
                GameCanvas.clearKeyHold(4);
                GameCanvas.clearKeyHold(2);
            } else if (GameCanvas.keyMyHold[6] || GameCanvas.keyMyHold[8]) {
                ++this.menuSelectedItem;
                GameCanvas.clearKeyHold(6);
                GameCanvas.clearKeyHold(8);
            }
            this.menuSelectedItem = this.resetSelect(this.menuSelectedItem, this.menuItems.size() - 1, false);
            if (n != this.menuSelectedItem) {
                for (int i = 0; i < this.menuItems.size(); ++i) {
                    iCommand iCommand2 = (iCommand)this.menuItems.elementAt(i);
                    iCommand2.isSelect = i == this.menuSelectedItem;
                }
            }
            if (GameCanvas.keyMyHold[5]) {
                GameCanvas.clearKeyHold(5);
                if (this.menuSelectedItem < this.menuItems.size() && this.menuSelectedItem >= 0) {
                    ((iCommand)this.menuItems.elementAt(this.menuSelectedItem)).perform();
                }
            }
        } else if (isNPCMenu == 0) {
            if (GameCanvas.keyMyPressed[2] || GameCanvas.keyMyPressed[4]) {
                bl = true;
                --this.menuSelectedItem;
                if (this.menuSelectedItem < 0) {
                    this.menuSelectedItem = this.menuItems.size() - 1;
                }
                GameCanvas.clearKeyPressed(2);
                GameCanvas.clearKeyPressed(4);
            } else if (GameCanvas.keyMyPressed[8] || GameCanvas.keyMyPressed[6]) {
                bl = true;
                ++this.menuSelectedItem;
                if (this.menuSelectedItem > this.menuItems.size() - 1) {
                    this.menuSelectedItem = 0;
                }
                GameCanvas.clearKeyPressed(6);
                GameCanvas.clearKeyPressed(8);
            }
        }
        if (bl) {
            cmtoX = this.pos == -1 ? this.menuSelectedItem * this.menuW + this.menuW - GameCanvas.w / 2 : (this.menuSelectedItem + 1) * this.menuW - this.menuH / 2;
            if (cmtoX > cmxLim) {
                cmtoX = cmxLim;
            }
            if (cmtoX < 0) {
                cmtoX = 0;
            }
            if (this.menuSelectedItem == this.menuItems.size() - 1 || this.menuSelectedItem == 0) {
                cmx = cmtoX;
            }
        }
        if (isNPCMenu == 0) {
            if (this.pos == -1) {
                this.updatePos_LEFT_RIGHT();
            } else {
                this.update_Pos_UP_DOWN();
            }
            if (GameCanvas.isPointerSelect && !GameCanvas.isPoint(this.menuX - 5, this.menuTemY - 5 - this.hPlus, this.w + 10, this.menuH + 10 + this.hPlus) && !this.isSv) {
                this.doCloseMenu();
            }
        } else if (isNPCMenu == 2) {
            this.updatePos_LEFT_RIGHT();
            if (GameCanvas.isPointerSelect && !GameCanvas.isPoint(this.menuX - 5, this.menuY - 5, this.w + 10, this.menuH + 10)) {
                this.timeShow = (byte)-1;
            }
        }
        super.updatekey();
    }

    public void updatePos_LEFT_RIGHT() {
        int n;
        int n2;
        if (GameCanvas.isPointerDown) {
            if (!this.pointerIsDowning && GameCanvas.isPointer(this.menuX, this.menuY, this.w, this.menuH)) {
                for (n2 = 0; n2 < this.pointerDownLastX.length; ++n2) {
                    this.pointerDownLastX[0] = GameCanvas.px;
                }
                this.pointerDownFirstX = GameCanvas.px;
                this.pointerIsDowning = true;
                this.isDownWhenRunning = this.cmRun != 0;
                this.cmRun = 0;
            } else if (this.pointerIsDowning) {
                ++this.pointerDownTime;
                if (this.pointerDownTime > 5 && this.pointerDownFirstX == GameCanvas.px && !this.isDownWhenRunning) {
                    this.pointerDownFirstX = -1000;
                    this.menuSelectedItem = (cmtoX + GameCanvas.px - this.menuX) / this.menuW;
                }
                if ((n2 = GameCanvas.px - this.pointerDownLastX[0]) != 0 && this.menuSelectedItem != -1) {
                    this.menuSelectedItem = -1;
                }
                for (n = this.pointerDownLastX.length - 1; n > 0; --n) {
                    this.pointerDownLastX[n] = this.pointerDownLastX[n - 1];
                }
                this.pointerDownLastX[0] = GameCanvas.px;
                if ((cmtoX -= n2) < 0) {
                    cmtoX = 0;
                }
                if (cmtoX > cmxLim) {
                    cmtoX = cmxLim;
                }
                if (cmx < 0 || cmx > cmxLim) {
                    n2 /= 2;
                }
                cmx -= n2;
            }
        }
        if (GameCanvas.isPointerClick && this.pointerIsDowning) {
            n2 = GameCanvas.px - this.pointerDownLastX[0];
            GameCanvas.isPointerClick = false;
            if (CRes.abs(n2) < 20 && CRes.abs(GameCanvas.px - this.pointerDownFirstX) < 20 && !this.isDownWhenRunning) {
                this.cmRun = 0;
                cmtoX = cmx;
                this.pointerDownFirstX = -1000;
                this.menuSelectedItem = (cmtoX + GameCanvas.px - this.menuX) / this.menuW;
                this.pointerDownTime = 0;
                this.waitToPerform = 1;
            } else if (this.menuSelectedItem != -1 && this.pointerDownTime > 5) {
                this.pointerDownTime = 0;
                this.waitToPerform = 1;
            } else if (this.menuSelectedItem == -1 && !this.isDownWhenRunning) {
                if (cmx < 0) {
                    cmtoX = 0;
                } else if (cmx > cmxLim) {
                    cmtoX = cmxLim;
                } else {
                    n = GameCanvas.px - this.pointerDownLastX[0] + (this.pointerDownLastX[0] - this.pointerDownLastX[1]) + (this.pointerDownLastX[1] - this.pointerDownLastX[2]);
                    n = n > 10 ? 10 : (n < -10 ? -10 : 0);
                    this.cmRun = -n * 100;
                }
            }
            this.pointerIsDowning = false;
            this.pointerDownTime = 0;
            GameCanvas.isPointerClick = false;
        }
        if (GameCanvas.isPointerRelease && this.pointerIsDowning) {
            this.pointerIsDowning = false;
        }
    }

    private void update_Pos_UP_DOWN() {
        int n;
        iCommand iCommand2;
        if (GameCanvas.keyMyPressed[5]) {
            GameCanvas.clearKeyHold();
            GameCanvas.clearKeyPressed();
            this.doCloseMenu();
            iCommand2 = (iCommand)this.menuItems.elementAt(this.menuSelectedItem);
            this.perform(iCommand2);
        } else if (GameCanvas.keyMyPressed[12]) {
            GameCanvas.clearKeyHold();
            GameCanvas.clearKeyPressed();
            this.doCloseMenu();
            iCommand2 = (iCommand)this.menuItems.elementAt(this.menuSelectedItem);
            this.perform(iCommand2);
        }
        if (GameCanvas.isPointerSelect && isGiaotiep) {
            int n2 = this.SelectFocus--;
            if (GameCanvas.isPoint(this.menuX + 13 - 14, this.menuTemY - this.hPlus + GameCanvas.hCommand + this.menuW / 2 - 14, 28, 28)) {
                GameCanvas.isPointerSelect = false;
            }
            if (GameCanvas.isPoint(this.menuX + this.w - 13 - 14, this.menuTemY - this.hPlus + GameCanvas.hCommand + this.menuW / 2 - 14, 28, 28)) {
                ++this.SelectFocus;
                GameCanvas.isPointerSelect = false;
            }
            this.SelectFocus = this.resetSelect(this.SelectFocus, this.vecFocus.size() - 1, true);
            if (this.SelectFocus != n2) {
                objSelect = (MainObject)this.vecFocus.elementAt(this.SelectFocus);
            }
        }
        if (GameCanvas.isPointerDown) {
            if (!this.pointerIsDowning && GameCanvas.isPointer(this.menuX, this.menuY, this.w, this.menuH)) {
                for (int i = 0; i < this.pointerDownLastX.length; ++i) {
                    this.pointerDownLastX[0] = GameCanvas.py;
                }
                this.pointerDownFirstX = GameCanvas.py;
                this.pointerIsDowning = true;
                this.isDownWhenRunning = this.cmRun != 0;
                this.cmRun = 0;
            } else if (this.pointerIsDowning) {
                int n3;
                ++this.pointerDownTime;
                if (this.pointerDownTime > 5 && this.pointerDownFirstX == GameCanvas.py && !this.isDownWhenRunning) {
                    this.pointerDownFirstX = -1000;
                    this.menuSelectedItem = (cmtoX + GameCanvas.py - this.menuY) / this.menuW;
                }
                if ((n3 = GameCanvas.py - this.pointerDownLastX[0]) != 0 && this.menuSelectedItem != -1) {
                    this.menuSelectedItem = -1;
                }
                for (n = this.pointerDownLastX.length - 1; n > 0; --n) {
                    this.pointerDownLastX[n] = this.pointerDownLastX[n - 1];
                }
                this.pointerDownLastX[0] = GameCanvas.py;
                if ((cmtoX -= n3) < 0) {
                    cmtoX = 0;
                }
                if (cmtoX > cmxLim) {
                    cmtoX = cmxLim;
                }
                if (cmx < 0 || cmx > cmxLim) {
                    n3 /= 2;
                }
                cmx -= n3;
            }
        }
        if (GameCanvas.isPointerClick && this.pointerIsDowning) {
            int n4 = GameCanvas.py - this.pointerDownLastX[0];
            GameCanvas.isPointerClick = false;
            if (CRes.abs(n4) < 20 && CRes.abs(GameCanvas.py - this.pointerDownFirstX) < 20 && !this.isDownWhenRunning && GameCanvas.isPointerSelect) {
                this.cmRun = 0;
                cmtoX = cmx;
                this.pointerDownFirstX = -1000;
                this.menuSelectedItem = (cmtoX + GameCanvas.py - this.menuY) / this.menuW;
                this.pointerDownTime = 0;
                this.waitToPerform = 1;
            } else if (this.menuSelectedItem != -1 && this.pointerDownTime > 5) {
                this.pointerDownTime = 0;
                this.waitToPerform = 1;
            } else if (this.menuSelectedItem == -1 && !this.isDownWhenRunning) {
                if (cmx < 0) {
                    cmtoX = 0;
                } else if (cmx > cmxLim) {
                    cmtoX = cmxLim;
                } else {
                    n = GameCanvas.py - this.pointerDownLastX[0] + (this.pointerDownLastX[0] - this.pointerDownLastX[1]) + (this.pointerDownLastX[1] - this.pointerDownLastX[2]);
                    n = n > 10 ? 10 : (n < -10 ? -10 : 0);
                    this.cmRun = -n * 100;
                }
            }
            this.pointerIsDowning = false;
            this.pointerDownTime = 0;
            GameCanvas.isPointerClick = false;
        }
        if (GameCanvas.isPointerRelease && this.pointerIsDowning) {
            this.pointerIsDowning = false;
        }
    }

    public void moveCamera() {
        if (this.cmRun != 0 && !this.pointerIsDowning) {
            if ((cmtoX += this.cmRun / 100) < 0) {
                cmtoX = 0;
            } else if (cmtoX > cmxLim) {
                cmtoX = cmxLim;
            } else {
                cmx = cmtoX;
            }
            this.cmRun = this.cmRun * 9 / 10;
            if (this.cmRun < 100 && this.cmRun > -100) {
                this.cmRun = 0;
            }
        }
        if (cmx != cmtoX && !this.pointerIsDowning) {
            this.cmvx = cmtoX - cmx << 2;
            this.cmdx += this.cmvx;
            cmx += this.cmdx >> 4;
            this.cmdx &= 0xF;
        }
    }

    public void paintMenu(mGraphics mGraphics2) {
        GameCanvas.resetTrans(mGraphics2);
        if (!isLoadData) {
            return;
        }
        if (isNPCMenu == 1) {
            this.paint_NPC_MENU(mGraphics2);
        } else if (isNPCMenu == 0) {
            int n;
            AvMain.paintDialog(mGraphics2, this.menuX - 6, this.menuTemY - this.hPlus - 6, this.w + 12, this.menuH + this.hPlus + 12, 0);
            AvMain.paintRectNice(mGraphics2, this.menuX, this.menuTemY, this.w, this.menuH, (byte)2);
            mFont.tahoma_7b_black.drawString(mGraphics2, this.nameMenu, this.menuX + this.w / 2, this.menuTemY - this.hPlus + GameCanvas.hCommand / 4, 2, true);
            if (isGiaotiep) {
                if (GameCanvas.lowGraphic) {
                    MainTabNew.paintRectLowGraphic(mGraphics2, this.menuX, this.menuY - this.hPlus + GameCanvas.hCommand + 2, this.w, this.menuW - 4, 1);
                } else {
                    for (n = 0; n <= this.w / 32; ++n) {
                        if (n < this.w / 32) {
                            mGraphics2.drawRegion(MainTabNew.imgTab[1], 0, 0, 32, this.menuW - 4, 0, this.menuX + n * 32, this.menuTemY - this.hPlus + GameCanvas.hCommand + 2, 0, true);
                            continue;
                        }
                        mGraphics2.drawRegion(MainTabNew.imgTab[1], 0, 0, 32, this.menuW - 4, 0, this.menuX + this.w - 32, this.menuTemY - this.hPlus + GameCanvas.hCommand + 2, 0, true);
                    }
                }
                if (this.vecFocus.size() > 1) {
                    mGraphics2.drawRegion(MainTabNew.imgTab[7], 0, 16, 13, 8, 6, this.menuX + 8 - GameCanvas.gameTick % 3, this.menuTemY - this.hPlus + GameCanvas.hCommand + this.menuW / 2, 3, true);
                    mGraphics2.drawRegion(MainTabNew.imgTab[7], 0, 24, 13, 8, 6, this.menuX + this.w - 8 + GameCanvas.gameTick % 3, this.menuTemY - this.hPlus + GameCanvas.hCommand + this.menuW / 2, 3, true);
                }
                AvMain.FontBorderColor(mGraphics2, Menu2.objSelect.name, this.menuX + this.w / 2 + 13, this.menuTemY - this.hPlus + GameCanvas.hCommand + this.menuW / 4 + 3, 2, 2);
                n = mFont.tahoma_7b_black.getWidth(Menu2.objSelect.name) / 2 + 1;
                objSelect.paintAvatarFocus(mGraphics2, this.menuX + this.w / 2 - n - 2, this.menuTemY - this.hPlus + GameCanvas.hCommand + this.menuW / 4 + 8);
            }
            if (!isPaint) {
                return;
            }
            if (this.pos == -1) {
                mGraphics2.setClip(this.menuX + 2, this.menuTemY + 2, this.w - 4, this.menuH - 4);
                mGraphics2.saveCanvas();
                mGraphics2.ClipRec(this.menuX + 2, this.menuTemY + 2, this.w - 4, this.menuH - 4);
                mGraphics2.translate(-cmx, 0);
                for (n = 0; n < this.menuItems.size(); ++n) {
                    String[] stringArray;
                    if (n == this.menuSelectedItem) {
                        mGraphics2.setColor(AvMain.color[3]);
                        mGraphics2.fillRoundRect(this.menuX + n * this.menuW + 3, this.menuTemY + 3, this.menuW - 8, this.menuH - 6, 6, 6, true);
                    }
                    if ((stringArray = ((iCommand)this.menuItems.elementAt((int)n)).subCaption) == null) {
                        stringArray = new String[]{((iCommand)this.menuItems.elementAt((int)n)).caption};
                    }
                    int n2 = this.menuTemY + (this.menuH - stringArray.length * 14) / 2 + 1;
                    for (int i = 0; i < stringArray.length; ++i) {
                        if (n == this.menuSelectedItem) {
                            mFont.tahoma_7b_white.drawString(mGraphics2, stringArray[i], this.menuX + n * this.menuW + this.menuW / 2 - 1, n2 + i * 14, 2, true);
                            continue;
                        }
                        mFont.tahoma_7b_black.drawString(mGraphics2, stringArray[i], this.menuX + n * this.menuW + this.menuW / 2 - 1, n2 + i * 14, 2, true);
                    }
                }
                mGraphics.resetTransAndroid(mGraphics2);
                mGraphics2.restoreCanvas();
                mGraphics2.endClip();
            } else {
                int n3;
                int n4;
                mGraphics2.setClip(this.menuX + 3, this.menuY + 3, this.w - 6, this.menuH - 6);
                mGraphics2.saveCanvas();
                mGraphics2.ClipRec(this.menuX + 3, this.menuY + 3, this.w - 6, this.menuH - 6);
                mGraphics2.translate(0, -cmx);
                mGraphics2.setColor(AvMain.color[4]);
                if (this.pos == 2 || this.pos == 4) {
                    for (n = 0; n < this.menuItems.size() - 1; ++n) {
                        mGraphics2.setColor(AvMain.color[4]);
                        mGraphics2.fillRect(this.menuX + 8, this.menuY + 3 + this.menuW + n * this.menuW, this.w - 16, 1, true);
                    }
                }
                if ((n = cmx / this.menuW - 1) < 0) {
                    n = 0;
                }
                if ((n4 = n + this.sizeMenu + 2) > this.menuItems.size() && (n = (n4 = this.menuItems.size()) - this.sizeMenu - 2) < 0) {
                    n = 0;
                }
                if (this.menuSelectedItem > -1) {
                    this.paintSelect(mGraphics2, this.menuX + 3, this.menuY + 3 + this.menuSelectedItem * this.menuW, this.w - 6, this.menuW + 1);
                }
                for (n3 = n; n3 < n4; ++n3) {
                    iCommand iCommand2 = (iCommand)this.menuItems.elementAt(n3);
                    if (this.pos == 2) {
                        iCommand2.paintCaptionImageMenu(mGraphics2, this.menuX + this.w / 2, this.menuY + 6 + this.menuW / 4 + n3 * this.menuW, 2);
                        continue;
                    }
                    if (this.pos == 0 || this.pos == 3) {
                        iCommand2.paintCaptionImageMenu(mGraphics2, this.menuX + 6, this.menuY + 6 + this.menuW / 4 + n3 * this.menuW, 0);
                        continue;
                    }
                    if (this.pos == 1) {
                        iCommand2.paintCaptionImageMenu(mGraphics2, this.menuX + this.w - 6, this.menuY + 6 + this.menuW / 4 + n3 * this.menuW, 1);
                        continue;
                    }
                    if (this.pos != 4) continue;
                    iCommand2.paintCaptionImageMenu(mGraphics2, this.menuX + 10, this.menuY + 6 + this.menuW / 4 + n3 * this.menuW, 0);
                }
                mGraphics.resetTransAndroid(mGraphics2);
                mGraphics2.restoreCanvas();
                mGraphics2.endClip();
                GameCanvas.resetTrans(mGraphics2);
                if (GameScreen.help.Step >= 0 && isHelp && (n3 = GameScreen.help.itemMenuHelp()) >= 0) {
                    MainHelp.paintPopup(mGraphics2, this.menuX - 6 - 70, this.menuY + 16 + n3 * this.menuW - GameCanvas.hText, 70, GameCanvas.hText, -1, T.helpMenu);
                    mGraphics2.drawRegion(AvMain.imgSelect, 0, 0, 12, 16, 4, this.menuX + 4 + GameCanvas.gameTick / 2 % 4, this.menuY + 14 + n3 * this.menuW, 10, true);
                }
                if (!this.isSv) {
                    this.paintCmd(mGraphics2);
                }
            }
        } else if (isNPCMenu == 2) {
            iCommand iCommand3;
            int n;
            if (this.isPosPoint) {
                mGraphics2.drawRegion(PaintInfoGameScreen.imgOther[4], 0, 0, 20, 16, 0, this.menuX - 20, this.menuY + this.menuH - 16, 0, true);
                mGraphics2.drawRegion(PaintInfoGameScreen.imgOther[4], 20, 0, 20, 16, 0, this.menuX + this.hShow, this.menuY + this.menuH - 16, 0, true);
                if (this.hShow == this.menuH) {
                    mGraphics2.drawRegion(PaintInfoGameScreen.imgBackQuick, 20, 0, 20, 40, 2, this.menuX, this.menuY, 0, true);
                    mGraphics2.drawRegion(PaintInfoGameScreen.imgBackQuick, 20, 0, 20, 40, 0, this.menuX + 20, this.menuY, 0, true);
                } else {
                    for (n = 0; n < this.hShow; n += this.menuH) {
                        if (n == 0) {
                            mGraphics2.drawRegion(PaintInfoGameScreen.imgBackQuick, 0, 0, 40, 40, 2, this.menuX, this.menuY, 0, true);
                            continue;
                        }
                        if (n + this.menuH >= this.hShow) {
                            mGraphics2.drawRegion(PaintInfoGameScreen.imgBackQuick, 0, 0, 40, 40, 0, this.menuX + n, this.menuY, 0, true);
                            continue;
                        }
                        mGraphics2.drawRegion(PaintInfoGameScreen.imgBackQuick, 0, 20, 40, 40, 0, this.menuX + n, this.menuY, 0, true);
                    }
                }
                mGraphics2.setClip(this.menuX + 5, this.menuY - 20, this.hShow - 10, this.menuH + 20);
                mGraphics2.saveCanvas();
                mGraphics2.ClipRec(this.menuX + 5, this.menuY - 20, this.hShow - 10, this.menuH + 20);
            } else {
                mGraphics2.drawRegion(PaintInfoGameScreen.imgOther[4], 0, 0, 20, 16, 0, this.menuX + this.w / 2 - this.hShow / 2 - 20, this.menuY + this.menuH - 16, 0, true);
                mGraphics2.drawRegion(PaintInfoGameScreen.imgOther[4], 20, 0, 20, 16, 0, this.menuX + this.w / 2 + this.hShow / 2, this.menuY + this.menuH - 16, 0, true);
                if (this.hShow == this.menuH) {
                    mGraphics2.drawRegion(PaintInfoGameScreen.imgBackQuick, 20, 0, 20, 40, 2, this.menuX + this.w / 2 - 20, this.menuY, 0, true);
                    mGraphics2.drawRegion(PaintInfoGameScreen.imgBackQuick, 20, 0, 20, 40, 0, this.menuX + this.w / 2, this.menuY, 0, true);
                } else {
                    for (n = 0; n < this.hShow; n += this.menuH) {
                        if (n == 0) {
                            mGraphics2.drawRegion(PaintInfoGameScreen.imgBackQuick, 0, 0, 40, 40, 2, this.menuX + this.w / 2 - this.hShow / 2 + n, this.menuY, 0, true);
                            continue;
                        }
                        if (n + this.menuH >= this.hShow) {
                            mGraphics2.drawRegion(PaintInfoGameScreen.imgBackQuick, 0, 0, 40, 40, 0, this.menuX + this.w / 2 - this.hShow / 2 + n, this.menuY, 0, true);
                            continue;
                        }
                        mGraphics2.drawRegion(PaintInfoGameScreen.imgBackQuick, 0, 20, 40, 40, 0, this.menuX + this.w / 2 - this.hShow / 2 + n, this.menuY, 0, true);
                    }
                }
                mGraphics2.saveCanvas();
                mGraphics2.setClip(this.menuX + this.w / 2 - this.hShow / 2 + 5, this.menuY - 20, this.hShow - 10, this.menuH + 20);
                mGraphics2.ClipRec(this.menuX + this.w / 2 - this.hShow / 2 + 5, this.menuY - 20, this.hShow - 10, this.menuH + 20);
            }
            mGraphics2.translate(-cmx, 0);
            for (n = 0; n < this.menuItems.size(); ++n) {
                iCommand3 = (iCommand)this.menuItems.elementAt(n);
                iCommand3.paint(mGraphics2, iCommand3.xCmd, iCommand3.yCmd);
            }
            GameCanvas.resetTrans(mGraphics2);
            mGraphics2.translate(-cmx, 0);
            for (n = 0; n < this.menuItems.size(); ++n) {
                iCommand3 = (iCommand)this.menuItems.elementAt(n);
                iCommand3.paintClickCaption(mGraphics2, iCommand3.xCmd, iCommand3.yCmd, 2);
            }
            mGraphics.resetTransAndroid(mGraphics2);
            mGraphics2.restoreCanvas();
            mGraphics2.endClip();
        }
    }

    private void paint_NPC_MENU(mGraphics mGraphics2) {
        int n = this.menuX + 6;
        int n2 = this.menuY + 8;
        AvMain.paintDialog(mGraphics2, this.menuX, this.menuTemY, this.w, this.menuH, 12);
        MainObject mainObject = MainObject.get_Object(this.IdNpc, this.typeO);
        if (mainObject == null) {
            return;
        }
        mainObject.paintBigAvatar(mGraphics2, this.menuX + this.w - 10, this.menuY);
        AvMain.Font3dWhite(mGraphics2, mainObject.name, n + 10, n2, 0);
        if (this.runText != null) {
            this.runText.paintText(mGraphics2, this.archorRunText);
        }
        GameCanvas.resetTrans(mGraphics2);
        for (int i = 0; i < this.menuItems.size(); ++i) {
            iCommand iCommand2 = (iCommand)this.menuItems.elementAt(i);
            iCommand2.paint(mGraphics2, iCommand2.xCmd, iCommand2.yCmd);
        }
    }

    public void doCloseMenu() {
        this.isShowMenu = false;
        this.isSv = false;
        GameCanvas.isPointerSelect = false;
        GameCanvas.isPointerClick = false;
        GameCanvas.isPointerEnd = true;
    }

    public void updateMenu() {
        int n;
        if (this.timeShow > 0) {
            this.timeShow = (byte)(this.timeShow + 1);
            if (this.hShow < this.w) {
                this.hShow += this.menuH;
                if (this.hShow >= this.w) {
                    this.hShow = this.w;
                    this.timeShow = 0;
                }
            }
        } else if (this.timeShow < 0) {
            this.timeShow = (byte)(this.timeShow - 1);
            if (this.hShow > 0) {
                this.hShow -= this.menuH;
                if (this.hShow <= 0) {
                    this.hShow = 0;
                    this.timeShow = 0;
                    this.doCloseMenu();
                }
            }
        }
        if (!isLoadData) {
            return;
        }
        this.moveCamera();
        if (isNPCMenu == 1) {
            if (this.runText != null) {
                this.runText.updateDlg();
            }
            for (n = 0; n < this.menuItems.size(); ++n) {
                iCommand iCommand2 = (iCommand)this.menuItems.elementAt(n);
                iCommand2.updatePointer();
            }
        } else if (isNPCMenu == 2 && !GameCanvas.isPointerMove && this.timeShow == 0) {
            for (n = 0; n < this.menuItems.size(); ++n) {
                iCommand iCommand3 = (iCommand)this.menuItems.elementAt(n);
                iCommand3.updatePointerShow(cmx, 0);
            }
        }
        if (this.menuTemY > this.menuY) {
            n = this.menuTemY - this.menuY >> 1;
            if (n < 1) {
                n = 1;
            }
            this.menuTemY -= n;
        }
        if (xc != 0 && (xc >>= 1) < 0) {
            xc = 0;
        }
        if (this.waitToPerform > 0) {
            --this.waitToPerform;
            if (this.waitToPerform == 0) {
                this.isShowMenu = false;
                if (this.menuSelectedItem >= 0) {
                    iCommand iCommand4 = (iCommand)this.menuItems.elementAt(this.menuSelectedItem);
                    this.perform(iCommand4);
                    GameCanvas.clearKeyHold();
                    GameCanvas.clearKeyPressed();
                    GameCanvas.isPointerEnd = true;
                    GameCanvas.isPointerSelect = false;
                }
            }
        }
        super.updatePointer();
    }

    public void perform(iCommand iCommand2) {
        if (iCommand2 != null) {
            if (iCommand2.action != null) {
                iCommand2.action.perform();
            } else if (iCommand2.Pointer != null) {
                iCommand2.Pointer.commandPointer((int)iCommand2.indexMenu, iCommand2.subIndex);
            } else {
                GameCanvas.currentScreen.commandMenu(iCommand2.indexMenu, iCommand2.subIndex);
            }
            GameCanvas.isPointerSelect = false;
            mSound.playSound(42, mSound.volumeSound);
        }
    }

    static {
        isHelp = false;
        isGiaotiep = false;
        isPaint = true;
        isLoadData = true;
        objSelect = null;
    }
}

