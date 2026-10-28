/*
 * Decompiled with CFR 0.152.
 */
package GameScreen;

import CLib.Session_ME;
import CLib.mFont;
import CLib.mGraphics;
import CLib.mImage;
import CLib.mSound;
import CLib.mSystem;
import CLib.mVector;
import GameObjects.Item;
import GameObjects.MainClan;
import GameObjects.MainRMS;
import GameObjects.ObjectData;
import GameObjects.Other_Players;
import GameObjects.Player;
import GameScreen.CreateChar;
import GameScreen.GameScreen;
import GameScreen.LoadMapScreen;
import GameScreen.LoginScreen;
import GameScreen.MainScreen;
import GameScreen.PaintInfoGameScreen;
import InterfaceComponents.MsgDialog;
import InterfaceComponents.iCommand;
import Main.GameCanvas;
import Map.BackGround;
import Model.AvMain;
import Model.MainImage;
import Model.T;
import netcommand.global.GlobalLogicHandler;
import netcommand.global.GlobalService;

public class SelectCharScreen
extends MainScreen {
    int wRectChar = 50;
    int hRectChar = 80;
    int selectChar;
    int wCenter;
    int frame;
    public static mVector VecSelectChar = new mVector("PaintInfoGameScr VecSelectChar");
    int[][] mShadow = mSystem.new_M_Int(3, 2);
    boolean isSelect = false;
    boolean isSend = false;
    public static int IDCHAR = -1;
    public static boolean isSelectOk = false;
    public static boolean reSelect = false;
    public static boolean Canselect = false;
    int[] mplash = new int[0];
    iCommand cmdExit;
    int direction = 0;
    public byte[] frameicon = new byte[]{0, 1, 2, 1};
    public byte frameClan = 0;
    Other_Players objSelect = null;
    int timeSelect = 0;

    public SelectCharScreen() {
        this.wCenter = (GameCanvas.w - this.wRectChar * 3) / 4;
        this.init();
    }

    public void Show() {
        mSound.stopSoundAll();
        if (LoginScreen.MusicRandom == 0) {
            mSound.playMus(0, mSound.volumeMusic, true);
        } else {
            mSound.playMus(1, mSound.volumeMusic, true);
        }
        this.timeSelect = 0;
        this.objSelect = null;
        this.isSelect = false;
        this.isSend = false;
        Player.isNewPlayer = false;
        GameScreen.Vecplayers.removeAllElements();
        super.Show();
        isSelectOk = false;
        GameCanvas.saveImage.start();
        GameScreen.infoGame.setNameServer(mSystem.listServer[GameCanvas.IndexServer][0]);
        GameCanvas.countLogin = 0L;
    }

    public void commandTab(int n, int n2) {
        switch (n) {
            case 0: {
                this.doSelect();
                break;
            }
            case 1: {
                GameCanvas.login.Show();
                Session_ME.gI().close();
                GameScreen.player = new Player(0, 0, "unname", 0, 0);
            }
        }
        super.commandTab(n, n2);
    }

    public void init() {
        if (!GameCanvas.isTouch) {
            this.center = new iCommand(T.select, 0);
        }
        this.cmdExit = new iCommand(T.exit, 1);
        if (GameCanvas.isTouch) {
            this.cmdExit.setPos(PaintInfoGameScreen.fraBack.frameWidth / 2, GameCanvas.h - PaintInfoGameScreen.fraBack.frameHeight / 2, PaintInfoGameScreen.fraBack, this.cmdExit.caption);
        }
        this.left = this.cmdExit;
    }

    public void setCaptionCmd() {
        this.cmdExit.caption = T.exit;
    }

    public void paint(mGraphics mGraphics2) {
        BackGround.paint(mGraphics2);
        if (LoginScreen.logo != null) {
            mGraphics2.drawImage(LoginScreen.logo, GameCanvas.hw, GameCanvas.hh - 60, 3, false);
        }
        BackGround.paint_CloudOnLogo(mGraphics2);
        GameScreen.infoGame.paintNameServer(mGraphics2, mSystem.listServer[GameCanvas.IndexServer][0]);
        BackGround.paint_FloatingPlatform(mGraphics2);
        BackGround.paint_StaticCloud(mGraphics2);
        for (int i = 0; i < 3; ++i) {
            if (i < VecSelectChar.size()) {
                Other_Players other_Players = (Other_Players)VecSelectChar.elementAt(i);
                other_Players.paintPlayer(mGraphics2, -1);
                mFont.tahoma_7b_black.drawString(mGraphics2, T.level + other_Players.Lv, other_Players.x, other_Players.y - other_Players.hOne - 10, 2, false);
                this.paintName(mGraphics2, other_Players.name, other_Players.x, other_Players.y - other_Players.hOne, i, other_Players.myClan);
                continue;
            }
            this.paintName(mGraphics2, T.createChar, this.mShadow[i][0], this.mShadow[i][1], i, null);
        }
        BackGround.paintLight(mGraphics2);
        super.paint(mGraphics2);
        if (reSelect) {
            mGraphics2.setColor(0);
            mGraphics2.fillRect(0, 0, GameCanvas.w, GameCanvas.h, false);
            if (LoginScreen.logo != null) {
                mGraphics2.drawImage(LoginScreen.logo, GameCanvas.hw, GameCanvas.hh - 16, 3, false);
                mGraphics2.drawRegion(AvMain.imgLoadImg, 0, GameCanvas.gameTick % 12 * 16, 16, 16, 0, GameCanvas.hw, GameCanvas.hh + mImage.getImageHeight(LoginScreen.logo.image) / 2 - 9, 3, false);
            }
        }
        if (PaintInfoGameScreen.paint18plush == 0) {
            if (!mSystem.isIphone) {
                mGraphics2.drawImage(AvMain.img18Plus, 0, 0, 0, false);
            }
        } else if (PaintInfoGameScreen.paint18plush == 1) {
            PaintInfoGameScreen.paintinfo18plush(mGraphics2);
        }
        if (LoginScreen.isPaintHotLine) {
            mFont.tahoma_7_yellow.drawString(mGraphics2, T.HotLine, GameCanvas.w, 0, 1, false);
            mGraphics2.setColor(16514362);
            mGraphics2.fillRect(GameCanvas.w - LoginScreen.wLine + (mSystem.isIphone ? 14 : 0), 10, LoginScreen.wLine, 1, false);
        }
    }

    public void update() {
        GameScreen.infoGame.updateInfoChar();
        if (LoginScreen.hShowServer < 20 && (LoginScreen.hShowServer += 4) > 20) {
            LoginScreen.hShowServer = 20;
        }
        if (this.timeSelect > 0) {
            ++this.timeSelect;
            if (this.timeSelect == 18) {
                this.objSelect.f = 0;
                this.objSelect.Action = 1;
            }
        }
        if (!GameCanvas.isTouch) {
            this.center.caption = this.selectChar > VecSelectChar.size() - 1 ? T.create : T.select;
        }
        if (MsgDialog.isAutologin) {
            this.autoSelect();
        }
        for (int i = 0; i < VecSelectChar.size(); ++i) {
            Other_Players other_Players = (Other_Players)VecSelectChar.elementAt(i);
            if (other_Players.Action == 2) {
                other_Players.PlashNow.update(other_Players);
            } else if (other_Players.Action == 1) {
                ++other_Players.f;
                if (other_Players.f > other_Players.A_Move.length - 1) {
                    other_Players.f = 0;
                }
                other_Players.frame = other_Players.A_Move[other_Players.f];
                if (this.isSelect && !this.isSend) {
                    Other_Players other_Players2 = (Other_Players)VecSelectChar.elementAt(this.selectChar);
                    MainRMS.isLoadShowAuto = true;
                    IDCHAR = other_Players2.ID;
                    GlobalService.gI().select_char((byte)0, IDCHAR);
                    LoadMapScreen.isNextMap = false;
                    GameCanvas.load.Show();
                    this.isSend = true;
                    GameScreen.help.Step = -1;
                    GlobalService.gI().Save_RMS_Server((byte)1, (byte)2, null);
                    GlobalService.gI().Save_RMS_Server((byte)1, (byte)1, null);
                    reSelect = false;
                    GlobalLogicHandler.timeReconnect = 0L;
                    GlobalLogicHandler.isMelogin = false;
                    GlobalLogicHandler.isDisConect = false;
                    MsgDialog.isAutologin = false;
                }
                other_Players.y += other_Players.vy;
            } else {
                other_Players.updateActionPerson();
            }
            other_Players.updateEye();
        }
        BackGround.updateSky();
        if (reSelect && Canselect) {
            this.doSelect();
            Canselect = false;
        }
    }

    public void autoSelect() {
        Other_Players other_Players = (Other_Players)VecSelectChar.elementAt(this.selectChar);
        MainRMS.isLoadShowAuto = true;
        IDCHAR = other_Players.ID;
        GlobalService.gI().select_char((byte)0, IDCHAR);
        LoadMapScreen.isNextMap = false;
        GameCanvas.load.Show();
        this.isSend = true;
        GameScreen.help.Step = -1;
        GlobalService.gI().Save_RMS_Server((byte)1, (byte)2, null);
        GlobalService.gI().Save_RMS_Server((byte)1, (byte)1, null);
        reSelect = false;
        MsgDialog.isAutologin = false;
    }

    public void updatekey() {
        if (reSelect) {
            return;
        }
        if (!this.isSelect) {
            if (GameCanvas.keyMyHold[4]) {
                --this.selectChar;
                GameCanvas.clearKeyHold();
            } else if (GameCanvas.keyMyHold[6]) {
                ++this.selectChar;
                GameCanvas.clearKeyHold();
            }
            this.selectChar = this.resetSelect(this.selectChar, 2, true);
            super.updatekey();
        }
    }

    public void setPosPaint(mVector mVector2) {
        VecSelectChar.removeAllElements();
        if (mVector2 != null) {
            VecSelectChar = mVector2;
        }
        for (int i = 0; i < 3; ++i) {
            int n = GameCanvas.hw - 80 + i * 80;
            int n2 = GameCanvas.h - 60 - i % 2 * 25;
            if (i < VecSelectChar.size()) {
                Other_Players other_Players = (Other_Players)VecSelectChar.elementAt(i);
                int n3 = GameCanvas.hw - 80 + i * 80;
                int n4 = GameCanvas.h - 60 - i % 2 * 25;
                other_Players.x = n3;
                other_Players.y = n4;
            }
            this.mShadow[i][0] = n;
            this.mShadow[i][1] = n2;
        }
    }

    private void paintName(mGraphics mGraphics2, String string, int n, int n2, int n3, MainClan mainClan) {
        mFont.tahoma_7b_black.drawString(mGraphics2, string, n - 1, n2 - 25, 2, false);
        mFont.tahoma_7b_black.drawString(mGraphics2, string, n + 1, n2 - 25, 2, false);
        mFont.tahoma_7b_black.drawString(mGraphics2, string, n - 1, n2 - 24, 2, false);
        mFont.tahoma_7b_black.drawString(mGraphics2, string, n + 1, n2 - 24, 2, false);
        mFont.tahoma_7b_black.drawString(mGraphics2, string, n - 1, n2 - 23, 2, false);
        mFont.tahoma_7b_black.drawString(mGraphics2, string, n + 1, n2 - 23, 2, false);
        mFont.tahoma_7b_black.drawString(mGraphics2, string, n, n2 - 25, 2, false);
        mFont.tahoma_7b_black.drawString(mGraphics2, string, n, n2 - 23, 2, false);
        mFont.tahoma_7b_white.drawString(mGraphics2, string, n, n2 - 24, 2, false);
        if (mainClan != null) {
            MainImage mainImage = ObjectData.getImageIconClan(mainClan.IdIcon);
            if (mainImage.img != null) {
                int n4 = (mFont.tahoma_7b_white.getWidth(mainClan.shortName) + 11) / 2;
                if (mainImage.img != null) {
                    if (mImage.getImageHeight(mainImage.img.image) / 18 == 3) {
                        if (GameCanvas.gameTick % 6 == 0) {
                            int n5 = this.frameicon.length;
                            if (n5 == 0) {
                                n5 = 1;
                            }
                            this.frameClan = (byte)((this.frameClan + 1) % n5);
                        }
                        mGraphics2.drawRegion(mainImage.img, 0, this.frameicon[this.frameClan] * 18, 18, 18, 0, n - n4 + 6, n2 - 32, 3, true);
                    } else {
                        mGraphics2.drawImage(mainImage.img, n - n4 + 6, n2 - 32, 3, false);
                    }
                    Item.eff_UpLv.paintUpgradeEffect(n - n4 + 6, n2 - 32 - 1, mainClan.getEffChucVu(), 14, mGraphics2, 0);
                }
                mFont.tahoma_7b_white.drawString(mGraphics2, mainClan.shortName, n - n4 + 15, n2 - 32 - 6, 0, false);
                n2 -= 18;
            }
        }
        if (n3 == this.selectChar && !this.isSelect && !GameCanvas.isTouch) {
            mGraphics2.drawImage(AvMain.imgSelect, n, n2 - 35 + GameCanvas.gameTick % 5, 3, false);
        }
    }

    public void updatePointer() {
        if (reSelect) {
            return;
        }
        if (GameCanvas.isPointerSelect && !this.isSelect) {
            for (int i = 0; i < this.mShadow.length; ++i) {
                if (!GameCanvas.isPoint(this.mShadow[i][0] - 5, this.mShadow[i][1] - 65, 50, 90)) continue;
                this.selectChar = i;
                this.doSelect();
                this.selectChar = this.resetSelect(this.selectChar, 2, true);
                GameCanvas.isPointerSelect = false;
                break;
            }
        }
        super.updatePointer();
    }

    public void doSelect() {
        if (this.selectChar > VecSelectChar.size() - 1) {
            mSound.playSound(41, mSound.volumeSound);
            GameCanvas.createChar = new CreateChar();
            GameCanvas.createChar.Show((byte)this.selectChar);
        } else {
            this.isSelect = true;
            this.timeSelect = 1;
            this.objSelect = (Other_Players)VecSelectChar.elementAt(this.selectChar);
            this.objSelect.Action = 2;
            this.objSelect.f = 0;
            this.objSelect.beginFire();
            this.objSelect.PlashNow.setPlash(this.objSelect.clazz == 3 ? 16 : 1);
            this.objSelect.Direction = 0;
            if (this.objSelect.clazz == 2 || this.objSelect.clazz == 3) {
                mSound.playSound(16, mSound.volumeSound);
            } else {
                mSound.playSound(6, mSound.volumeSound);
            }
        }
    }

    public void keyBack() {
        this.cmdExit.perform();
    }
}

