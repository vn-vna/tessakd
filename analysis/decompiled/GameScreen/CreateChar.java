/*
 * Decompiled with CFR 0.152.
 */
package GameScreen;

import CLib.Session_ME;
import CLib.TField;
import CLib.mFont;
import CLib.mGraphics;
import CLib.mSound;
import CLib.mSystem;
import CLib.mVector;
import GameObjects.MainObject;
import GameObjects.Other_Players;
import GameObjects.Player;
import GameScreen.GameScreen;
import GameScreen.LoginScreen;
import GameScreen.MainScreen;
import GameScreen.PaintInfoGameScreen;
import GameScreen.SelectCharScreen;
import InterfaceComponents.iCommand;
import Main.GameCanvas;
import Map.BackGround;
import Model.AvMain;
import Model.CRes;
import Model.T;
import netcommand.global.GlobalService;

public class CreateChar
extends MainScreen {
    int wRectChar = 180;
    int hRectChar = 160;
    TField tfNameChar;
    public static mVector VecDefaultChar = new mVector("CreateChar VecDefaultchar");
    public static final int SELECT_CLASS = 0;
    int frame;
    int timeChangeClass;
    int fFocus;
    int selectClass = 0;
    int indexChar = 0;
    byte[] mselect;
    byte[][] mCreateChar = mSystem.new_M_Byte(4, 3);
    byte[][] mEye = new byte[][]{{8, 9}, {10, 11}};
    byte[][] mHair = new byte[][]{{0, 1}, {2, 3}};
    int numHair = 2;
    int numEye = 2;
    byte index = 0;
    mVector vecCmd = new mVector("CreateChar veccmd");
    iCommand cmdclass;
    iCommand cmdtoc;
    iCommand cmdmat;
    iCommand cmddau;
    iCommand cmdBack;
    int direction;

    public void Show(byte by) {
        mSound.stopSoundAll();
        if (LoginScreen.MusicRandom == 0) {
            mSound.playMus(0, mSound.volumeMusic, true);
        } else {
            mSound.playMus(1, mSound.volumeMusic, true);
        }
        this.index = by;
        super.Show();
    }

    public CreateChar() {
        if (GameCanvas.isTouch) {
            this.hRectChar = 182;
            this.wRectChar = 200;
        }
        this.init();
    }

    public void init() {
        int n;
        VecDefaultChar.removeAllElements();
        this.center = new iCommand(T.create, 0);
        this.cmdBack = new iCommand(T.back, 1);
        if (GameCanvas.isTouch) {
            n = iCommand.wButtonCmd / 2;
            if (n < this.wRectChar / 4) {
                n = this.wRectChar / 4;
            }
            this.center.setPos(GameCanvas.hw - n, GameCanvas.hh + this.hRectChar / 2 + 4, null, this.center.caption);
            this.cmdBack.setPos(GameCanvas.hw + n, GameCanvas.hh + this.hRectChar / 2 + 4, null, this.cmdBack.caption);
        }
        this.left = this.cmdBack;
        for (n = 0; n < 4; ++n) {
            Other_Players other_Players = new Other_Players(n, 0, "", 0, 0);
            other_Players.clazz = (byte)n;
            other_Players.leg = n;
            other_Players.body = n;
            other_Players.hat = -1;
            other_Players.head = 0;
            other_Players.hair = n;
            other_Players.EyeMain = other_Players.eye = 8 + n;
            other_Players.Direction = 0;
            other_Players.wing = -1;
            other_Players.x = this.wRectChar / 5 + n * this.wRectChar / 4;
            other_Players.y = this.hRectChar / 5 * 2;
            other_Players.weaponType = 0;
            VecDefaultChar.addElement(other_Players);
        }
        this.mselect = new byte[4];
        this.indexChar = CRes.random(4);
        this.selectClass = 0;
        MainScreen.cameraSub.setAll(VecDefaultChar.size() * this.wRectChar / 4, 0, this.indexChar * this.wRectChar / 4, 0);
        this.tfNameChar = new TField(GameCanvas.hw - this.wRectChar / 2 + this.wRectChar / 5 - 25, GameCanvas.hh - this.hRectChar / 2 + this.hRectChar / 2 + 10, 60);
        if (!GameCanvas.isTouch) {
            this.tfNameChar.setFocus(true);
        } else {
            this.cmdclass = new iCommand(T.mClass[this.indexChar], 0, this);
            this.vecCmd.addElement(this.cmdclass);
            Other_Players other_Players = (Other_Players)VecDefaultChar.elementAt(this.indexChar);
            this.cmdtoc = new iCommand(T.mCreateChar_HAIR[this.indexChar / 2][other_Players.hair % this.numHair], 1, this);
            this.vecCmd.addElement(this.cmdtoc);
            int n2 = other_Players.eye;
            if (n2 < 8) {
                n2 = other_Players.EyeMain;
            }
            this.cmdmat = new iCommand(T.mCreateChar_EYE_FACE[0][n2 - 8], 2, this);
            this.vecCmd.addElement(this.cmdmat);
            this.cmddau = new iCommand(T.mCreateChar_EYE_FACE[1][other_Players.head], 3, this);
            this.vecCmd.addElement(this.cmddau);
            int n3 = GameCanvas.hw - this.wRectChar / 2;
            int n4 = GameCanvas.hh - this.hRectChar / 2 - GameCanvas.hCommand / 2;
            for (int i = 0; i < this.vecCmd.size(); ++i) {
                iCommand iCommand2 = (iCommand)this.vecCmd.elementAt(i);
                iCommand2.setPos(n3 + this.wRectChar / 3 * 2, n4 + this.hRectChar / 5 * (i + 1) + 5, PaintInfoGameScreen.fraButton2, iCommand2.caption);
            }
        }
    }

    public void commandPointer(int n, int n2) {
        switch (n) {
            case 0: {
                this.selectClass = 0;
                ++this.indexChar;
                this.timeChangeClass = 0;
                if (this.indexChar == 4) {
                    MainScreen.cameraSub.xCam = -this.wRectChar / 4;
                }
                this.indexChar = this.resetSelect(this.indexChar, T.mClass.length - 1, true);
                MainScreen.cameraSub.xTo = this.indexChar * this.wRectChar / 4;
                this.cmdclass.caption = T.mClass[this.indexChar];
                Other_Players other_Players = (Other_Players)VecDefaultChar.elementAt(this.indexChar);
                this.cmdtoc.caption = T.mCreateChar_HAIR[this.indexChar / 2][other_Players.hair % this.numHair];
                break;
            }
            case 1: 
            case 2: 
            case 3: {
                this.selectClass = n;
                this.editChar(1);
            }
        }
    }

    public void commandTab(int n, int n2) {
        switch (n) {
            case 0: {
                String string = this.tfNameChar.getText();
                if (string.length() < 6) {
                    GameCanvas.start_Ok_Dialog(T.nameMin6char);
                    break;
                }
                Other_Players other_Players = (Other_Players)VecDefaultChar.elementAt(this.indexChar);
                GlobalService.gI().create_char(other_Players.clazz, string, (byte)other_Players.hair, (byte)other_Players.EyeMain, (byte)other_Players.head, this.index);
                GameCanvas.start_Wait_Dialog(T.pleaseWait, null);
                break;
            }
            case 1: {
                if (SelectCharScreen.VecSelectChar.size() == 0) {
                    GameCanvas.login.Show();
                    Session_ME.gI().close();
                    GameScreen.player = new Player(0, 0, "unname", 0, 0);
                    break;
                }
                GameCanvas.selectChar.Show();
            }
        }
        super.commandTab(n, n2);
    }

    public void paint(mGraphics mGraphics2) {
        Object object;
        int n;
        BackGround.paint(mGraphics2);
        BackGround.paintLight(mGraphics2);
        AvMain.paintTabNew(mGraphics2, GameCanvas.hw - this.wRectChar / 2, GameCanvas.hh - this.hRectChar / 2 - GameCanvas.hCommand / 2, this.wRectChar, this.hRectChar, true, (byte)0);
        mGraphics2.translate(GameCanvas.hw - this.wRectChar / 2, GameCanvas.hh - this.hRectChar / 2 - GameCanvas.hCommand / 2);
        Other_Players other_Players = (Other_Players)VecDefaultChar.elementAt(this.indexChar);
        for (n = 0; n < T.textCreateChar.length; ++n) {
            mFont.tahoma_7b_black.drawString(mGraphics2, T.textCreateChar[n], this.wRectChar / 3 * 2, this.hRectChar / 5 * n + this.hRectChar / 10, 2, false);
        }
        if (!GameCanvas.isTouch) {
            AvMain.FontBorderColor(mGraphics2, T.mClass[this.indexChar], this.wRectChar / 3 * 2, this.hRectChar / 5, 2, 2);
            AvMain.FontBorderColor(mGraphics2, T.mCreateChar_HAIR[this.indexChar / 2][other_Players.hair % this.numHair], this.wRectChar / 3 * 2, this.hRectChar / 5 * 2, 2, 2);
            n = other_Players.eye;
            if (n < 8) {
                n = other_Players.EyeMain;
            }
            AvMain.FontBorderColor(mGraphics2, T.mCreateChar_EYE_FACE[0][n - 8], this.wRectChar / 3 * 2, this.hRectChar / 5 * 3, 2, 2);
            AvMain.FontBorderColor(mGraphics2, T.mCreateChar_EYE_FACE[1][other_Players.head], this.wRectChar / 3 * 2, this.hRectChar / 5 * 4, 2, 2);
        }
        if (!GameCanvas.isTouch) {
            mGraphics2.drawRegion(MainObject.focus, 0, 0, 11, 9, 5, this.wRectChar / 3 * 2 - 35 - this.fFocus / 2 % 4, this.hRectChar / 5 * (this.selectClass + 1) + 4, 3, false);
            mGraphics2.drawRegion(MainObject.focus, 0, 0, 11, 9, 6, this.wRectChar / 3 * 2 + 35 + this.fFocus / 2 % 4, this.hRectChar / 5 * (this.selectClass + 1) + 4, 3, false);
        }
        mFont.tahoma_7_black.drawString(mGraphics2, T.namePlayer, this.wRectChar / 5 + 4, this.hRectChar / 2 + 5, 2, false);
        mGraphics2.setClip(8, 2, this.wRectChar / 5 * 2 - 12, this.hRectChar - 4);
        mGraphics.resetTransAndroid(mGraphics2);
        mGraphics2.saveCanvas();
        mGraphics2.translateAndroid(GameCanvas.hw - this.wRectChar / 2, GameCanvas.hh - this.hRectChar / 2 - GameCanvas.hCommand / 2);
        mGraphics2.ClipRec(8, 2, this.wRectChar / 5 * 2 - 12, this.hRectChar - 4);
        mGraphics2.translate(-MainScreen.cameraSub.xCam, 0);
        for (n = 0; n < VecDefaultChar.size(); ++n) {
            object = (Other_Players)VecDefaultChar.elementAt(n);
            if (n == 0) {
                ((MainObject)object).paintShowPlayer(mGraphics2, ((MainObject)object).x + this.wRectChar / 4 * VecDefaultChar.size(), ((MainObject)object).y, ((MainObject)object).Direction);
            }
            if (n == VecDefaultChar.size() - 1) {
                ((MainObject)object).paintShowPlayer(mGraphics2, ((MainObject)object).x - this.wRectChar / 4 * VecDefaultChar.size(), ((MainObject)object).y, ((MainObject)object).Direction);
            }
            ((MainObject)object).paintPlayer(mGraphics2, -1);
            if (((MainObject)object).Action == 2) {
                ((MainObject)object).PlashNow.update((MainObject)object);
            } else if (((MainObject)object).Action == 1) {
                ++((MainObject)object).f;
                if (((MainObject)object).f > ((MainObject)object).A_Move.length - 1) {
                    ((MainObject)object).f = 0;
                }
                ((MainObject)object).frame = ((MainObject)object).A_Move[((MainObject)object).f];
            } else {
                ((MainObject)object).updateActionPerson();
            }
            ((MainObject)object).updateEye();
        }
        mGraphics.resetTransAndroid(mGraphics2);
        mGraphics2.restoreCanvas();
        GameCanvas.resetTrans(mGraphics2);
        for (n = 0; n < this.vecCmd.size(); ++n) {
            object = (iCommand)this.vecCmd.elementAt(n);
            ((iCommand)object).paint(mGraphics2, ((iCommand)object).xCmd, ((iCommand)object).yCmd);
        }
        this.tfNameChar.paint(mGraphics2);
        mGraphics2.endClip();
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
        super.paint(mGraphics2);
    }

    public void update() {
        if (LoginScreen.hShowServer < 20 && (LoginScreen.hShowServer += 4) > 20) {
            LoginScreen.hShowServer = 20;
        }
        if (this.timeChangeClass == 0) {
            Other_Players other_Players = (Other_Players)VecDefaultChar.elementAt(this.indexChar);
            other_Players.Direction = 0;
        } else if (this.timeChangeClass == 30 || this.timeChangeClass == 10) {
            Other_Players other_Players = (Other_Players)VecDefaultChar.elementAt(this.indexChar);
            other_Players.Action = 2;
            other_Players.f = 0;
            other_Players.beginFire();
            other_Players.PlashNow.setPlash(other_Players.clazz);
        } else if (this.timeChangeClass == 50) {
            Other_Players other_Players = (Other_Players)VecDefaultChar.elementAt(this.indexChar);
            other_Players.Action = 1;
            other_Players.f = 0;
        } else if (this.timeChangeClass > 80) {
            Other_Players other_Players = (Other_Players)VecDefaultChar.elementAt(this.indexChar);
            this.timeChangeClass = 1;
            other_Players.Action = 0;
            other_Players.f = 0;
            ++other_Players.Direction;
            other_Players.Direction %= 4;
        }
        ++this.timeChangeClass;
        this.tfNameChar.update();
        ++this.fFocus;
        MainScreen.cameraSub.UpdateCameraCreate();
        MainScreen.cameraSub.UpdateCameraCreate();
        BackGround.updateSky();
    }

    public void updatekey() {
        if (GameCanvas.keyMyHold[4]) {
            if (this.selectClass == 0) {
                --this.indexChar;
                this.timeChangeClass = 0;
            } else {
                this.editChar(-1);
            }
            GameCanvas.clearKeyHold(4);
            this.tfNameChar.caretPos = this.tfNameChar.getText().length() + 1;
        } else if (GameCanvas.keyMyHold[6]) {
            if (this.selectClass == 0) {
                ++this.indexChar;
                this.timeChangeClass = 0;
            } else {
                this.editChar(1);
            }
            GameCanvas.clearKeyHold(6);
            this.tfNameChar.caretPos = this.tfNameChar.getText().length() + 1;
        } else if (GameCanvas.keyMyHold[2]) {
            --this.selectClass;
            GameCanvas.clearKeyHold(2);
        } else if (GameCanvas.keyMyHold[8]) {
            ++this.selectClass;
            GameCanvas.clearKeyHold(8);
        }
        this.selectClass = this.resetSelect(this.selectClass, T.textCreateChar.length - 1, true);
        if (this.indexChar == 4) {
            MainScreen.cameraSub.xCam = -this.wRectChar / 4;
        } else if (this.indexChar == -1) {
            MainScreen.cameraSub.xCam = 4 * this.wRectChar / 4;
        }
        this.indexChar = this.resetSelect(this.indexChar, T.mClass.length - 1, true);
        MainScreen.cameraSub.xTo = this.indexChar * this.wRectChar / 4;
        this.right = this.tfNameChar.isFocused() ? this.tfNameChar.setCmdClear() : null;
        if (GameCanvas.keyMyPressed[3]) {
            this.direction = (this.direction + 1) % 4;
        }
        super.updatekey();
    }

    public void editChar(int n) {
        Other_Players other_Players = (Other_Players)VecDefaultChar.elementAt(this.indexChar);
        switch (this.selectClass) {
            case 1: {
                other_Players.hair += n;
                other_Players.hair = this.indexChar / this.numHair * this.numHair + this.resetSelect(other_Players.hair - this.indexChar / this.numHair * this.numHair, this.numHair - 1, true);
                if (!GameCanvas.isTouch) break;
                this.cmdtoc.caption = T.mCreateChar_HAIR[this.indexChar / 2][other_Players.hair % this.numHair];
                break;
            }
            case 2: {
                other_Players.eye += n;
                other_Players.EyeMain = other_Players.eye = 8 + this.indexChar / this.numEye * this.numEye + this.resetSelect(other_Players.eye - 8 - this.indexChar / this.numEye * this.numEye, this.numEye - 1, true);
                if (!GameCanvas.isTouch) break;
                int n2 = other_Players.eye;
                if (n2 < 8) {
                    n2 = other_Players.EyeMain;
                }
                this.cmdmat.caption = T.mCreateChar_EYE_FACE[0][n2 - 8];
                break;
            }
            case 3: {
                other_Players.head += n;
                other_Players.head = this.resetSelect(other_Players.head, T.mCreateChar_EYE_FACE[1].length - 1, true);
                if (!GameCanvas.isTouch) break;
                this.cmddau.caption = T.mCreateChar_EYE_FACE[1][other_Players.head];
            }
        }
    }

    public void keyPress(int n) {
        if (this.tfNameChar.isFocused()) {
            this.tfNameChar.keyPressed(n);
        }
        super.keyPress(n);
    }

    public void updatePointer() {
        this.tfNameChar.updatePoiter();
        for (int i = 0; i < this.vecCmd.size(); ++i) {
            iCommand iCommand2 = (iCommand)this.vecCmd.elementAt(i);
            iCommand2.updatePointer();
        }
        super.updatePointer();
    }

    public void keyBack() {
        this.cmdBack.perform();
    }
}

