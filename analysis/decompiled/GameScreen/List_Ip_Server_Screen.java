/*
 * Decompiled with CFR 0.152.
 */
package GameScreen;

import Build_Indo.IndoServer;
import Build_Usa.Usa_Server;
import CLib.Session_ME;
import CLib.mFont;
import CLib.mGraphics;
import CLib.mSystem;
import CLib.mVector;
import GameScreen.LoginScreen;
import GameScreen.LogoScreen;
import GameScreen.MainScreen;
import GameScreen.PaintInfoGameScreen;
import InterfaceComponents.MainList;
import InterfaceComponents.iCommand;
import Main.GameCanvas;
import Map.BackGround;
import Model.AvMain;
import Model.T;
import Model.TE;
import Model.TIndo;
import Model.TSpain;

public class List_Ip_Server_Screen
extends MainScreen {
    int x;
    int y;
    int w;
    int h;
    int idSelect;
    int hItem;
    MainList list;
    String serverListStatus;
    mVector vecChoice = new mVector("List_Ip_Server_Screen vecchoice");

    public List_Ip_Server_Screen() {
        boolean bl = LogoScreen.refreshServerList();
        this.serverListStatus = LogoScreen.serverListSource + ":" + mSystem.listServer.length + (bl ? "" : "!");
        this.hItem = GameCanvas.hCommand;
        this.w = GameCanvas.w - 20;
        if (this.w > 180) {
            this.w = 180;
        }
        this.h = mSystem.listServer.length * this.hItem;
        int n = 0;
        if (this.h > GameCanvas.h / 5 * 4) {
            n = this.h - GameCanvas.h / 5 * 4;
            this.h = GameCanvas.h / 5 * 4;
        }
        this.x = GameCanvas.hw - this.w / 2;
        this.y = GameCanvas.hh - this.h / 2 + this.hItem / 2;
        this.vecChoice.removeAllElements();
        for (int i = 0; i < mSystem.listServer.length; ++i) {
            this.vecChoice.addElement(new iCommand(mSystem.listServer[i][0], 0, i, (AvMain)this));
        }
        if (GameCanvas.isTouch) {
            this.idSelect = -1;
        }
        this.list = new MainList(this.x, this.y, this.w, this.h, this.hItem, mSystem.listServer.length, n, this.idSelect, this.vecChoice);
    }

    public void checkIPSV() {
    }

    public void commandPointer(int n, int n2) {
        switch (n) {
            case 0: {
                if (Session_ME.gI().connected) {
                    Session_ME.gI().close();
                }
                GameCanvas.IndexServer = (byte)n2;
                LoginScreen.saveIndexServer();
                GameCanvas.login.Show();
                boolean bl = GameCanvas.isVNLanguage;
                boolean bl2 = GameCanvas.isVNLanguage = GameCanvas.langServer[GameCanvas.IndexServer] == 0;
                if (GameCanvas.isVNLanguage != bl) {
                    LogoScreen.setChangeLang();
                    GameCanvas.start_Ok_Dialog(T.TchangSv);
                }
                LoginScreen.isPaintHotLine = GameCanvas.isVNLanguage;
                if (IndoServer.isIndoSv) {
                    LoginScreen.isPaintHotLine = false;
                    GameCanvas.t = new TIndo();
                    LogoScreen.setChangeLang();
                    if (mSystem.isIphone) {
                        // empty if block
                    }
                }
                if (Usa_Server.isUsa_server) {
                    LoginScreen.isPaintHotLine = false;
                    if (GameCanvas.IndexServer == 0) {
                        GameCanvas.t = new TE();
                    } else if (GameCanvas.IndexServer == 1) {
                        GameCanvas.t = new TSpain();
                    }
                    LogoScreen.setChangeLang();
                }
                GameCanvas.login.checkLoginAgain(GameCanvas.IndexServer);
            }
        }
    }

    public void paint(mGraphics mGraphics2) {
        BackGround.paint(mGraphics2);
        if (PaintInfoGameScreen.paint18plush == 0) {
            if (!mSystem.isIphone) {
                mGraphics2.drawImage(AvMain.img18Plus, 0, 0, 0, false);
            }
        } else if (PaintInfoGameScreen.paint18plush == 1) {
            PaintInfoGameScreen.paintinfo18plush(mGraphics2);
        }
        BackGround.paintLight(mGraphics2);
        this.paintFormList(mGraphics2, this.x, this.y - this.hItem, this.w, this.h + this.hItem, T.listserver + " [" + this.serverListStatus + "]");
        mGraphics2.setClip(this.x, this.y, this.w, this.h);
        mGraphics2.saveCanvas();
        mGraphics2.ClipRec(this.x, this.y, this.w, this.h);
        mGraphics2.translate(0, -this.list.cmx);
        if (this.idSelect > -1) {
            this.paintFocus(mGraphics2);
        }
        for (int i = 0; i < this.vecChoice.size(); ++i) {
            iCommand iCommand2 = (iCommand)this.vecChoice.elementAt(i);
            mFont.tahoma_7b_white.drawString(mGraphics2, iCommand2.caption, this.x + this.w / 2, this.y + this.hItem / 2 + i * this.hItem - 6, 2, true);
            if (i >= this.vecChoice.size() - 1) continue;
            mGraphics2.setColor(AvMain.color[4]);
            mGraphics2.fillRect(this.x + 8, this.y + (i + 1) * this.hItem - 1, this.w - 16, 1, true);
        }
        mGraphics.resetTransAndroid(mGraphics2);
        mGraphics2.restoreCanvas();
        mGraphics2.endClip();
    }

    public void paintFocus(mGraphics mGraphics2) {
        mGraphics2.setColor(-3689060);
        mGraphics2.fillRect(this.x + 5, this.y + this.idSelect * this.hItem + this.hItem / 2 - 11, this.w - 10, 22, false);
    }

    public void update() {
        this.list.updateMenu();
        this.idSelect = this.list.value;
        if (this.idSelect >= this.vecChoice.size()) {
            this.idSelect = -1;
            this.list.value = -1;
        }
        BackGround.updateSky();
    }
}

