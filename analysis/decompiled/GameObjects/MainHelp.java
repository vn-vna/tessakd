/*
 * Decompiled with CFR 0.152.
 */
package GameObjects;

import Build_Indo.IndoServer;
import CLib.mFont;
import CLib.mGraphics;
import CLib.mSystem;
import CLib.mVector;
import GameObjects.Item;
import GameObjects.MainItem;
import GameObjects.Player;
import GameScreen.GameScreen;
import GameScreen.PaintInfoGameScreen;
import GameScreen.TabScreenNew;
import InterfaceComponents.MainTabNew;
import InterfaceComponents.MsgDialog;
import InterfaceComponents.PopupChat;
import InterfaceComponents.TabMySeftNew;
import InterfaceComponents.iCommand;
import Main.GameCanvas;
import Model.AvMain;
import Model.CRes;
import Model.FrameImage;
import Model.Point;
import Model.T;
import Thread_More.LoadMap;
import Thread_More.MiniMap;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;

public class MainHelp
extends AvMain {
    public int Step = -1;
    public int Next = -5;
    public int idTabHelp = -2;
    public int tickBegin = 0;
    public int timeReset = 0;
    iCommand cmdNext = new iCommand(T.next, 0, this);
    iCommand cmdyes = new iCommand(T.yes, 0, this);
    public static final byte FRIST = 0;
    public static final byte LAST = 1;
    public static final byte POINT_FRAME = 0;
    public static final byte POPUP_HAND = 1;
    public static final byte POS_NULL = -1;
    public static final byte POS_LEFT = 0;
    public static final byte POS_RIGHT = 1;
    public static final byte POS_CENTER = 2;
    public static final byte POS_TOP_LEFT = 3;
    public static final byte POS_TOP_RIGHT = 4;
    public static final byte POS_TOP_CENTER = 5;
    public static final byte POS_TOP_NULL = 6;
    public static final byte POS_LEFT_CENTER = 7;
    public static final byte POS_RIGHT_CENTER = 8;
    public Point p;
    public FrameImage fra;
    String[] strpopup;
    public static int[] color = new int[]{-13427660, -16788};
    boolean isStepp5;

    public void setCaptionCmd() {
        this.cmdNext.caption = T.next;
        this.cmdyes.caption = T.yes;
    }

    public void commandPointer(int n, int n2) {
        switch (n) {
            case 0: {
                ++this.Next;
                this.setNext();
                break;
            }
            case 1: {
                GameCanvas.end_Dialog_Help();
                this.p = null;
                this.Step = -1;
                this.Next = 0;
                this.setNext();
                this.SaveStep();
            }
        }
    }

    public void loadBeginGame(byte[] byArray) {
        if (byArray == null) {
            this.Step = 0;
            this.Next = -5;
        }
    }

    public void LoadStep(byte[] byArray) {
        this.p = null;
        if (byArray == null) {
            this.Step = 0;
            this.Next = -5;
            return;
        }
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byArray);
        DataInputStream dataInputStream = new DataInputStream(byteArrayInputStream);
        try {
            this.Step = dataInputStream.readByte();
            this.Next = dataInputStream.readByte();
        }
        catch (Exception exception) {
            this.Step = -1;
            this.Next = 0;
        }
        if (GameCanvas.currentScreen != GameCanvas.load) {
            this.setNext();
        }
    }

    public void SaveStep() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        try {
            dataOutputStream.writeByte(this.Step);
            dataOutputStream.writeByte(this.Next);
            CRes.saveRMSName((byte)1, byteArrayOutputStream.toByteArray());
            dataOutputStream.close();
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public void SaveStep(byte by, byte by2) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        try {
            dataOutputStream.writeByte(by);
            dataOutputStream.writeByte(by2);
            CRes.saveRMSName((byte)1, byteArrayOutputStream.toByteArray());
            dataOutputStream.close();
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public void SaveBegin() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        try {
            dataOutputStream.writeByte(0);
            CRes.saveRMSName((byte)2, byteArrayOutputStream.toByteArray());
            dataOutputStream.close();
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public void NextStep() {
        ++this.Step;
        this.Next = 0;
    }

    public void setNext() {
        if (GameCanvas.loadmap.idMap != 0) {
            return;
        }
        if (GameScreen.isFinishHelp) {
            return;
        }
        this.cmdNext.caption = T.next;
        this.idTabHelp = -2;
        if (this.isSave()) {
            this.SaveStep();
        }
        if (!GameCanvas.isVNLanguage && !IndoServer.isIndoSv) {
            if (this.Step == 0 && this.Next == 1) {
                this.Step = 0;
                this.Next = 2;
                this.SaveStep();
            }
            if (this.Step == 1 && this.Next == 8) {
                this.Step = 1;
                this.Next = 9;
                this.SaveStep();
            }
            if (this.Step == 2 && this.Next == 6) {
                this.Step = 2;
                this.Next = 7;
                this.SaveStep();
            }
            if (this.Step == 2 && this.Next == 11) {
                this.Step = 4;
                this.Next = 9;
                this.SaveStep();
            }
            if (this.Step == 7 && this.Next == 1) {
                this.Step = 8;
                this.Next = 0;
                this.SaveStep();
            }
            if (this.Step == 6 && this.Next == 1) {
                this.Step = 6;
                this.Next = 2;
                this.SaveStep();
            }
        }
        String[][] stringArray = T.mHelp;
        if (GameCanvas.isTouch) {
            block0 : switch (this.Step) {
                case 0: {
                    if (this.Next == 2) {
                        GameCanvas.end_Dialog_Help();
                        if (this.Next < T.mHelpPoint[this.Step].length && T.mHelpPoint[this.Step][this.Next].length() > 0) {
                            MsgDialog msgDialog = new MsgDialog();
                            msgDialog.setDiaHelp(T.mHelpPoint[this.Step][this.Next], this.cmdNext, PaintInfoGameScreen.xPointMove - 45, PaintInfoGameScreen.yPointMove - PaintInfoGameScreen.wArrowMove - 5, 2, false, 90);
                            GameCanvas.currentDialog = msgDialog;
                        }
                        return;
                    }
                    if (this.Next != 3) break;
                    stringArray = T.mHelpPoint;
                    break;
                }
                case 1: {
                    if (this.Next == 0) {
                        GameCanvas.end_Dialog_Help();
                        if (this.Next < T.mHelpPoint[this.Step].length && T.mHelpPoint[this.Step][this.Next].length() > 0) {
                            MsgDialog msgDialog = new MsgDialog();
                            msgDialog.setDiaHelp(T.mHelpPoint[this.Step][this.Next], this.cmdNext, PaintInfoGameScreen.xPointKill - 40, PaintInfoGameScreen.yPointKill - 40, 2, false, 80);
                            GameCanvas.currentDialog = msgDialog;
                        }
                        return;
                    }
                    if (this.Next == 8) {
                        GameCanvas.end_Dialog_Help();
                        if (this.Next < T.mHelpPoint[this.Step].length && T.mHelpPoint[this.Step][this.Next].length() > 0) {
                            MsgDialog msgDialog = new MsgDialog();
                            msgDialog.setDiaHelp(T.mHelpPoint[this.Step][this.Next], this.cmdNext, PaintInfoGameScreen.mPosOther[0][0], PaintInfoGameScreen.mPosOther[0][1] + 22, 3, false, 90);
                            GameCanvas.currentDialog = msgDialog;
                        }
                        return;
                    }
                    if (this.Next != 9) break;
                    GameCanvas.end_Dialog_Help();
                    if (this.Next < T.mHelpPoint[this.Step].length && T.mHelpPoint[this.Step][this.Next].length() > 0) {
                        this.p = new Point();
                        this.p.x = PaintInfoGameScreen.mPosOther[0][0];
                        this.p.w = 80;
                        this.strpopup = mFont.tahoma_7_black.splitFontArray(T.mHelpPoint[this.Step][this.Next], this.p.w);
                        this.p.h = this.strpopup.length * GameCanvas.hText;
                        this.p.y = PaintInfoGameScreen.mPosOther[0][1] + 22 + this.p.h;
                        this.p.fRe = 3;
                        this.p.dis = 1;
                        this.p.frame = 1;
                    }
                    return;
                }
                case 2: {
                    switch (this.Next) {
                        case 2: 
                        case 3: 
                        case 7: {
                            stringArray = T.mHelpPoint;
                            break block0;
                        }
                        case 4: 
                        case 9: 
                        case 10: {
                            GameCanvas.end_Dialog_Help();
                            if (this.Next < T.mHelpPoint[this.Step].length && T.mHelpPoint[this.Step][this.Next].length() > 0) {
                                this.p = new Point();
                                this.p.x = MainTabNew.gI().xTab + MainTabNew.wOneItem + MainTabNew.wOne5 * 4;
                                this.p.w = 80;
                                this.strpopup = mFont.tahoma_7_black.splitFontArray(T.mHelpPoint[this.Step][this.Next], this.p.w);
                                this.p.h = this.strpopup.length * GameCanvas.hText;
                                this.p.y = MainTabNew.gI().yTab + GameCanvas.h / 5 + MainTabNew.wOneItem * 2 + this.p.h;
                                this.p.fRe = 3;
                                this.p.dis = 1;
                                this.p.frame = 1;
                            }
                            this.idTabHelp = 0;
                            return;
                        }
                    }
                    break;
                }
                case 3: {
                    switch (this.Next) {
                        case 4: {
                            if (!GameCanvas.isVNLanguage && !IndoServer.isIndoSv) {
                                GameCanvas.end_Dialog_Help();
                                int n = 3;
                                for (int i = 0; i < Item.VecInvetoryPlayer.size(); ++i) {
                                    MainItem mainItem = (MainItem)Item.VecInvetoryPlayer.elementAt(i);
                                    if (mainItem.ItemCatagory != 3) continue;
                                    n = i + 1;
                                    break;
                                }
                                mSystem.println("index " + n);
                                if (this.Next < T.mHelpPoint[this.Step].length && T.mHelpPoint[this.Step][this.Next].length() > 0) {
                                    this.p = new Point();
                                    this.p.x = MainTabNew.gI().xTab + MainTabNew.wOneItem * n - 40 + MainTabNew.wOne5 * 4;
                                    this.p.w = 80;
                                    this.strpopup = mFont.tahoma_7_black.splitFontArray(T.mHelpPoint[this.Step][this.Next], this.p.w);
                                    this.p.h = this.strpopup.length * GameCanvas.hText;
                                    this.p.y = MainTabNew.gI().yTab + GameCanvas.h / 5 + MainTabNew.wOneItem * 2 + this.p.h;
                                    this.p.fRe = 5;
                                    this.p.dis = 1;
                                    this.p.frame = 1;
                                }
                                this.idTabHelp = 0;
                            } else {
                                GameCanvas.end_Dialog_Help();
                                if (this.Next < T.mHelpPoint[this.Step].length && T.mHelpPoint[this.Step][this.Next].length() > 0) {
                                    this.p = new Point();
                                    this.p.x = MainTabNew.gI().xTab + MainTabNew.wOneItem * 3 - 40 + MainTabNew.wOne5 * 4;
                                    this.p.w = 80;
                                    this.strpopup = mFont.tahoma_7_black.splitFontArray(T.mHelpPoint[this.Step][this.Next], this.p.w);
                                    this.p.h = this.strpopup.length * GameCanvas.hText;
                                    this.p.y = MainTabNew.gI().yTab + GameCanvas.h / 5 + MainTabNew.wOneItem * 2 + this.p.h;
                                    this.p.fRe = 5;
                                    this.p.dis = 1;
                                    this.p.frame = 1;
                                }
                                this.idTabHelp = 0;
                            }
                            return;
                        }
                        case 8: {
                            stringArray = T.mHelpPoint;
                        }
                    }
                    break;
                }
                case 4: {
                    if (this.Next != 9) break;
                    GameCanvas.end_Dialog_Help();
                    if (this.Next < T.mHelpPoint[this.Step].length && T.mHelpPoint[this.Step][this.Next].length() > 0) {
                        this.p = new Point();
                        this.p.x = TabScreenNew.xback;
                        this.p.y = TabScreenNew.yback - iCommand.hButtonCmd;
                        this.p.w = 80;
                        this.strpopup = mFont.tahoma_7_black.splitFontArray(T.mHelpPoint[this.Step][this.Next], this.p.w);
                        this.p.h = this.strpopup.length * GameCanvas.hText;
                        this.p.fRe = 0;
                        this.p.dis = 1;
                        this.p.frame = 1;
                    }
                    this.idTabHelp = -1;
                    return;
                }
                case 5: {
                    switch (this.Next) {
                        case 1: {
                            GameCanvas.end_Dialog_Help();
                            if (this.Next < T.mHelpPoint[this.Step].length && T.mHelpPoint[this.Step][this.Next].length() > 0) {
                                MsgDialog msgDialog = new MsgDialog();
                                msgDialog.setDiaHelp(T.mHelpPoint[this.Step][this.Next], this.cmdNext, GameCanvas.w - 96, GameCanvas.minimap.maxY * MiniMap.wMini + 16, 5, false, 90);
                                GameCanvas.currentDialog = msgDialog;
                            }
                            return;
                        }
                        case 2: {
                            GameCanvas.end_Dialog_Help();
                            if (this.Next < T.mHelpPoint[this.Step].length && T.mHelpPoint[this.Step][this.Next].length() > 0) {
                                MsgDialog msgDialog = new MsgDialog();
                                msgDialog.setDiaHelp(T.mHelpPoint[this.Step][this.Next], this.cmdNext, GameCanvas.w - 96, GameCanvas.minimap.maxY * MiniMap.wMini + 16, 6, false, 90);
                                GameCanvas.currentDialog = msgDialog;
                            }
                            GameCanvas.minimap.setPoint(51, 2, GameCanvas.loadmap.idMap);
                            return;
                        }
                        case 4: {
                            GameCanvas.end_Dialog_Help();
                            if (this.Next < T.mHelpPoint[this.Step].length && T.mHelpPoint[this.Step][this.Next].length() > 0) {
                                MsgDialog msgDialog = new MsgDialog();
                                msgDialog.setDiaHelp(T.mHelpPoint[this.Step][this.Next], this.cmdNext, GameCanvas.w - GameCanvas.minimap.maxX * MiniMap.wMini - 96, 45, 4, false, 90);
                                GameCanvas.currentDialog = msgDialog;
                            }
                            return;
                        }
                        case 5: {
                            GameCanvas.end_Dialog_Help();
                            if (this.Next < T.mHelpPoint[this.Step].length && T.mHelpPoint[this.Step][this.Next].length() > 0) {
                                MsgDialog msgDialog = new MsgDialog();
                                msgDialog.setDiaHelp(T.mHelpPoint[this.Step][this.Next], this.cmdNext, PaintInfoGameScreen.mPosOther[3][0] - 96 + 25, PaintInfoGameScreen.mPosOther[3][1] - 10, 1, false, 90);
                                GameCanvas.currentDialog = msgDialog;
                            }
                            return;
                        }
                        case 6: {
                            GameCanvas.end_Dialog_Help();
                            if (this.Next < T.mHelpPoint[this.Step].length && T.mHelpPoint[this.Step][this.Next].length() > 0) {
                                MsgDialog msgDialog = new MsgDialog();
                                msgDialog.setDiaHelp(T.mHelpPoint[this.Step][this.Next], this.cmdNext, PaintInfoGameScreen.mPosOther[2][0] - 96 + 25, PaintInfoGameScreen.mPosOther[2][1] - 10, 1, false, 90);
                                GameCanvas.currentDialog = msgDialog;
                            }
                            return;
                        }
                        case 7: {
                            GameCanvas.end_Dialog_Help();
                            if (this.Next < T.mHelpPoint[this.Step].length && T.mHelpPoint[this.Step][this.Next].length() > 0) {
                                MsgDialog msgDialog = new MsgDialog();
                                msgDialog.setDiaHelp(T.mHelpPoint[this.Step][this.Next], this.cmdNext, PaintInfoGameScreen.mPosOther[1][0], PaintInfoGameScreen.mPosOther[1][1] + 45, 3, false, 90);
                                GameCanvas.currentDialog = msgDialog;
                            }
                            return;
                        }
                    }
                    break;
                }
                case 6: {
                    if (this.Next != 2) break;
                    GameCanvas.end_Dialog_Help();
                    if (this.Next < T.mHelpPoint[this.Step].length && T.mHelpPoint[this.Step][this.Next].length() > 0) {
                        this.p = new Point();
                        this.p.x = PaintInfoGameScreen.mPosOther[0][0];
                        this.p.w = 80;
                        this.strpopup = mFont.tahoma_7_black.splitFontArray(T.mHelpPoint[this.Step][this.Next], this.p.w);
                        this.p.h = this.strpopup.length * GameCanvas.hText;
                        this.p.y = PaintInfoGameScreen.mPosOther[0][1] + this.p.h + 22;
                        this.p.fRe = 3;
                        this.p.dis = 1;
                        this.p.frame = 1;
                    }
                    return;
                }
                case 7: {
                    switch (this.Next) {
                        case 6: 
                        case 9: {
                            stringArray = T.mHelpPoint;
                        }
                    }
                    break;
                }
                case 8: {
                    if (this.Next != 7) break;
                    stringArray = T.mHelpPoint;
                    break;
                }
                case 9: {
                    if (this.Next != 1 && this.Next != 5) break;
                    stringArray = T.mHelpPoint;
                }
            }
        }
        switch (this.Step) {
            case 0: {
                this.Step0(stringArray);
                break;
            }
            case 1: {
                this.Step1(stringArray);
                break;
            }
            case 2: {
                this.Step2(stringArray);
                break;
            }
            case 3: {
                this.Step3(stringArray);
                break;
            }
            case 4: {
                this.Step4(stringArray);
                break;
            }
            case 5: {
                this.Step5(stringArray);
                break;
            }
            case 6: {
                this.Step6(stringArray);
                break;
            }
            case 7: {
                this.Step7(stringArray);
                break;
            }
            case 8: {
                this.Step8(stringArray);
                break;
            }
            case 9: {
                this.Step9(stringArray);
            }
        }
    }

    public boolean isSave() {
        switch (this.Step) {
            case 0: {
                if (this.Next != 0) break;
                return true;
            }
            case 1: {
                if (this.Next != 1 && this.Next != 9) break;
                return true;
            }
            case 2: {
                if (this.Next != 4 && this.Next != 9) break;
                return true;
            }
            case 3: {
                if (this.Next != 4 && this.Next != 8) break;
                return true;
            }
            case 5: {
                if (this.Next != 0 && this.Next != 8) break;
                return true;
            }
            case 6: {
                if (this.Next != 2 && this.Next != 0) break;
                return true;
            }
            case 7: {
                if (this.Next != 6 && this.Next != 9) break;
                return true;
            }
            case 8: {
                if (this.Next != 7 && this.Next != 10) break;
                return true;
            }
            case 9: {
                if (this.Next != 1 && this.Next != 7) break;
                return true;
            }
        }
        return false;
    }

    public boolean setStep_Next(int n, int n2) {
        return this.Step == n && this.Next == n2;
    }

    public void updateHelp() {
        if (this.Step >= 0) {
            if (this.timeReset > 0) {
                --this.timeReset;
                if (this.timeReset == 1) {
                    if (GameCanvas.currentDialog == null && GameCanvas.subDialog == null) {
                        this.setNext();
                    } else {
                        this.timeReset = 20;
                    }
                }
            }
            switch (this.Step) {
                case 0: {
                    if (this.Next == -4 || this.Next == -2) {
                        ++this.tickBegin;
                        if (GameCanvas.currentDialog != null) {
                            GameCanvas.end_Dialog_Help();
                            this.timeReset = -1;
                        }
                        if (GameScreen.player.posTransRoad == null && this.tickBegin > 10) {
                            ++this.Next;
                            this.setNext();
                        }
                    }
                    if (this.Next == -3) {
                        if (GameCanvas.currentDialog != null) {
                            GameCanvas.end_Dialog_Help();
                        }
                        ++this.tickBegin;
                        if (this.tickBegin > 60) {
                            ++this.Next;
                            this.setNext();
                            this.tickBegin = 0;
                        }
                    }
                    if (this.Next != 5 || this.p == null || CRes.abs(GameScreen.player.x - this.p.x) >= 10 || CRes.abs(GameScreen.player.y - this.p.y) >= 10) break;
                    this.p = null;
                    this.NextStep();
                    this.setNext();
                    break;
                }
                case 2: {
                    if (GameCanvas.menu2.isShowMenu || this.Next != 10) break;
                    this.Next = 9;
                    break;
                }
                case 5: {
                    if (this.Next != 0) break;
                    ++this.tickBegin;
                    if (this.tickBegin < 20) break;
                    ++this.Next;
                    this.setNext();
                }
            }
        }
    }

    public void paintHelpFrist(mGraphics mGraphics2) {
        if (this.Step != 2 && this.Step != 3 && this.Step != 4 && this.Step != 7 && this.Step != 8 && (this.Step != 9 || this.Next == 0) && this.p != null && this.p.dis == 0 && this.fra != null) {
            this.fra.drawFrame(GameCanvas.gameTick / 2 % this.fra.nFrame, this.p.x, this.p.y, 0, 3, mGraphics2);
        }
    }

    public void paintHelpLast(mGraphics mGraphics2) {
        if (this.Step != 2 && this.Step != 3 && this.Step != 4 && this.Step != 7 && this.Step != 8 && (this.Step != 9 || this.Next == 0) && this.p != null && this.p.dis == 1) {
            if (this.p.frame == 0) {
                if (this.fra != null) {
                    this.fra.drawFrame(GameCanvas.gameTick / 2 % this.fra.nFrame, this.p.x, this.p.y, 0, 3, mGraphics2);
                }
            } else if (this.p.frame == 1) {
                MainHelp.paintPopup(mGraphics2, this.p.x, this.p.y, this.p.w, this.p.h, this.p.fRe, this.strpopup);
            }
        }
    }

    public int itemMenuHelp() {
        switch (this.Step) {
            case 1: {
                if (this.Next != 9) break;
                return 0;
            }
            case 2: {
                if (this.Next == 4) {
                    return 0;
                }
                if (this.Next == 9) {
                    return 1;
                }
                if (this.Next != 10) break;
                return 4;
            }
            case 3: {
                if (this.Next != 4) break;
                return 0;
            }
            case 6: {
                if (this.Next != 2) break;
                return 0;
            }
        }
        return -1;
    }

    public void itemTabHelp(mGraphics mGraphics2, Item item, byte by) {
        if (this.idTabHelp != by && this.idTabHelp != -1) {
            return;
        }
        switch (this.Step) {
            case 2: {
                if (!(GameCanvas.isVNLanguage || IndoServer.isIndoSv || this.Next != 4 && this.Next != 9)) {
                    return;
                }
                if (this.Next != 4 && this.Next != 9 || item == null || item.ItemCatagory != 4 || item.typePotion != 0 || this.p == null) break;
                GameCanvas.resetTrans(mGraphics2);
                MainHelp.paintPopup(mGraphics2, this.p.x, this.p.y, this.p.w, this.p.h, this.p.fRe, this.strpopup);
                break;
            }
            case 3: {
                if (this.Next == 4 && item != null && item.ItemCatagory == 3 && item.type_Only_Item == 6 && this.p != null) {
                    GameCanvas.resetTrans(mGraphics2);
                    MainHelp.paintPopup(mGraphics2, this.p.x, this.p.y, this.p.w, this.p.h, this.p.fRe, this.strpopup);
                }
                if (this.Next != 8 || this.p == null) break;
                GameCanvas.resetTrans(mGraphics2);
                MainHelp.paintPopup(mGraphics2, this.p.x, this.p.y, this.p.w, this.p.h, this.p.fRe, this.strpopup);
                break;
            }
            case 4: {
                if (this.Next != 9 || this.p == null) break;
                GameCanvas.resetTrans(mGraphics2);
                MainHelp.paintPopup(mGraphics2, this.p.x, this.p.y, this.p.w, this.p.h, this.p.fRe, this.strpopup);
                break;
            }
            case 7: {
                if (this.Next != 6 && this.Next != 9 || this.p == null) break;
                GameCanvas.resetTrans(mGraphics2);
                MainHelp.paintPopup(mGraphics2, this.p.x, this.p.y, this.p.w, this.p.h, this.p.fRe, this.strpopup);
                break;
            }
            case 8: {
                if (this.Next != 7 || this.p == null) break;
                GameCanvas.resetTrans(mGraphics2);
                MainHelp.paintPopup(mGraphics2, this.p.x, this.p.y, this.p.w, this.p.h, this.p.fRe, this.strpopup);
                break;
            }
            case 9: {
                if (this.Next != 1 || this.p == null) break;
                GameCanvas.resetTrans(mGraphics2);
                MainHelp.paintPopup(mGraphics2, this.p.x, this.p.y, this.p.w, this.p.h, this.p.fRe, this.strpopup);
            }
        }
    }

    public static void paintPopup(mGraphics mGraphics2, int n, int n2, int n3, int n4, int n5, String[] stringArray) {
        int n6 = n2 - n4;
        int n7 = n;
        mGraphics2.setColor(color[0]);
        mGraphics2.fillRect(n7 - 3, n6, n3 + 6, n4, false);
        mGraphics2.fillRect(n7, n6 - 3, n3, n4 + 6, false);
        mGraphics2.setColor(color[1]);
        mGraphics2.fillRect(n7 - 2, n6 - 2, n3 + 4, n4 + 4, false);
        mGraphics2.drawRegion(PopupChat.mPopup[0], 0, 0, 3, 3, 0, n7 - 3, n6 - 3, 0, false);
        mGraphics2.drawRegion(PopupChat.mPopup[0], 0, 3, 3, 3, 0, n7 + n3, n6 - 3, 0, false);
        mGraphics2.drawRegion(PopupChat.mPopup[0], 0, 9, 3, 3, 0, n7 - 3, n6 + n4, 0, false);
        mGraphics2.drawRegion(PopupChat.mPopup[0], 0, 6, 3, 3, 0, n7 + n3, n6 + n4, 0, false);
        for (int i = 0; i < stringArray.length; ++i) {
            mFont.tahoma_7_black.drawString(mGraphics2, stringArray[i], n7 + n3 / 2, n6 + 1 + i * GameCanvas.hText, 2, false);
        }
        switch (n5) {
            case 0: {
                mGraphics2.drawImage(AvMain.imgSelect, n, n2 + 2 + GameCanvas.gameTick / 2 % 4, 0, false);
                break;
            }
            case 1: {
                mGraphics2.drawImage(AvMain.imgSelect, n + n3, n2 + 2 + GameCanvas.gameTick / 2 % 4, 24, false);
                break;
            }
            case 2: {
                if (!(GameCanvas.isVNLanguage || IndoServer.isIndoSv || GameScreen.help.Step != 8 || GameScreen.help.Next != 3 && GameScreen.help.Next != 2)) {
                    return;
                }
                mGraphics2.drawImage(AvMain.imgSelect, n + n3 / 2, n2 + 2 + GameCanvas.gameTick / 2 % 4, 17, false);
                break;
            }
            case 3: {
                mGraphics2.drawRegion(AvMain.imgSelect, 0, 0, 12, 16, 1, n, n2 - n4 - 20 + 2 + GameCanvas.gameTick / 2 % 4, 0, false);
                break;
            }
            case 4: {
                mGraphics2.drawRegion(AvMain.imgSelect, 0, 0, 12, 16, 1, n + n3, n2 - n4 - 20 + 2 + GameCanvas.gameTick / 2 % 4, 24, false);
                break;
            }
            case 5: {
                mGraphics2.drawRegion(AvMain.imgSelect, 0, 0, 12, 16, 1, n + n3 / 2, n2 - n4 - 20 + 2 + GameCanvas.gameTick / 2 % 4, 17, false);
                break;
            }
            case 7: {
                mGraphics2.drawRegion(AvMain.imgSelect, 0, 0, 12, 16, 5, n + GameCanvas.gameTick / 2 % 4, n2 - n4 / 2, 10, false);
                break;
            }
            case 8: {
                mGraphics2.drawRegion(AvMain.imgSelect, 0, 0, 12, 16, 4, n + n3 + 1 + GameCanvas.gameTick / 2 % 4, n2 - n4 / 2, 6, false);
            }
        }
    }

    public void Step0(String[][] stringArray) {
        switch (this.Next) {
            case -5: {
                break;
            }
            case -4: {
                this.tickBegin = 0;
                this.SaveBegin();
                Player.isLockKey = true;
                GameScreen.player.posTransRoad = null;
                GameScreen.player.resetAction();
                GameScreen.player.toX = GameScreen.player.x;
                GameScreen.player.toY = GameScreen.player.y;
                GameScreen.player.xStopMove = 0;
                GameScreen.player.yStopMove = 0;
                GameScreen.player.posTransRoad = GameCanvas.game.updateFindRoad(10, 16, GameScreen.player.x / LoadMap.wTile, GameScreen.player.y / LoadMap.wTile, 40);
                break;
            }
            case -3: {
                Player.isLockKey = true;
                GameScreen.player.posTransRoad = null;
                GameScreen.player.resetAction();
                GameScreen.player.Direction = 2;
                GameScreen.player.strChatPopup = "...";
                this.tickBegin = 0;
                break;
            }
            case -2: {
                this.tickBegin = 0;
                Player.isLockKey = true;
                GameScreen.player.resetAction();
                GameScreen.player.toX = GameScreen.player.x;
                GameScreen.player.toY = GameScreen.player.y;
                GameScreen.player.xStopMove = 0;
                GameScreen.player.yStopMove = 0;
                GameScreen.player.posTransRoad = GameCanvas.game.updateFindRoad(24 + CRes.random_Am_0(3), 21 + CRes.random_Am_0(3), GameScreen.player.x / LoadMap.wTile, GameScreen.player.y / LoadMap.wTile, 40);
                break;
            }
            case -1: {
                mVector mVector2 = new mVector("MainHelp menu");
                mVector2.addElement(this.cmdyes);
                iCommand iCommand2 = new iCommand(T.no, 1, this);
                mVector2.addElement(iCommand2);
                GameCanvas.start_Select_Dialog(T.hoihelp, mVector2);
                break;
            }
            case 4: {
                int n;
                int n2;
                int n3;
                if (Player.isLockKey) {
                    Player.isLockKey = false;
                }
                if (!Player.isSendMove) {
                    Player.isSendMove = true;
                }
                if (this.Next < stringArray[this.Step].length && stringArray[this.Step][this.Next].length() > 0) {
                    n3 = GameCanvas.w - 30;
                    if (n3 > 200) {
                        n3 = 200;
                    }
                    n2 = GameCanvas.hw - n3 / 2;
                    n = GameCanvas.h - GameCanvas.hCommand * 2;
                    MsgDialog msgDialog = new MsgDialog();
                    msgDialog.setDiaHelp(stringArray[this.Step][this.Next], this.cmdNext, n2, n, -1, false, n3);
                    GameCanvas.currentDialog = msgDialog;
                }
                n3 = 80;
                n2 = 0;
                n = 0;
                do {
                    ++n2;
                    int n4 = GameScreen.player.x + CRes.random_Am(40, n3);
                    int n5 = GameScreen.player.y + CRes.random_Am(40, n3);
                    int n6 = GameCanvas.loadmap.getTile(n4, n5);
                    if (GameScreen.setIsInScreen(n4, n5) && n6 != -1 && n6 != 1) {
                        n = 1;
                        this.p = new Point();
                        this.p.x = n4;
                        this.p.y = n5;
                        this.p.dis = 0;
                        this.p.frame = 0;
                        this.fra = PaintInfoGameScreen.fraFocusIngame;
                    }
                    if (n2 <= 10) continue;
                    n3 += 10;
                    n2 = 0;
                } while (n == 0);
                break;
            }
            case 5: {
                GameCanvas.end_Dialog_Help();
                break;
            }
            default: {
                GameCanvas.end_Dialog_Help();
                this.dialogCenter(stringArray);
            }
        }
    }

    public void Step1(String[][] stringArray) {
        switch (this.Next) {
            case 2: {
                GameCanvas.end_Dialog_Help();
                break;
            }
            case 5: {
                GameCanvas.end_Dialog_Help();
                break;
            }
            case 9: {
                GameCanvas.end_Dialog_Help();
                if (this.Next >= stringArray[this.Step].length || stringArray[this.Step][this.Next].length() <= 0) break;
                this.p = new Point();
                this.p.x = 3;
                this.p.y = GameCanvas.h - GameCanvas.hCommand - 14;
                this.p.w = 80;
                this.strpopup = mFont.tahoma_7_black.splitFontArray(stringArray[this.Step][this.Next], this.p.w);
                this.p.h = this.strpopup.length * GameCanvas.hText;
                this.p.fRe = 2;
                this.p.dis = 1;
                this.p.frame = 1;
                break;
            }
            default: {
                if (this.Next >= stringArray[this.Step].length || stringArray[this.Step][this.Next].length() <= 0) break;
                int n = GameCanvas.w - 30;
                if (n > 200) {
                    n = 200;
                }
                int n2 = GameCanvas.hw - n / 2;
                int n3 = GameCanvas.h - GameCanvas.hCommand * 2;
                MsgDialog msgDialog = new MsgDialog();
                msgDialog.setDiaHelp(stringArray[this.Step][this.Next], this.cmdNext, n2, n3, -1, false, n);
                GameCanvas.currentDialog = msgDialog;
            }
        }
    }

    private void Step2(String[][] stringArray) {
        switch (this.Next) {
            case 0: {
                GameCanvas.end_Dialog_Help();
                this.leftTab(0, stringArray);
                break;
            }
            case 1: {
                GameCanvas.end_Dialog_Help();
                if (this.Next >= stringArray[this.Step].length || stringArray[this.Step][this.Next].length() <= 0) break;
                MsgDialog msgDialog = new MsgDialog();
                msgDialog.setDiaHelp(stringArray[this.Step][this.Next], this.cmdNext, MainTabNew.gI().xTab + MainTabNew.wOneItem + MainTabNew.wOne5 * 3, MainTabNew.gI().yTab + GameCanvas.h / 5 + MainTabNew.wOneItem * 2 + 22, 5, false, 90);
                GameCanvas.currentDialog = msgDialog;
                break;
            }
            case 2: 
            case 3: 
            case 7: 
            case 8: {
                GameCanvas.end_Dialog_Help();
                if (this.Next >= stringArray[this.Step].length || stringArray[this.Step][this.Next].length() <= 0) break;
                MsgDialog msgDialog = new MsgDialog();
                msgDialog.setDiaHelp(stringArray[this.Step][this.Next], this.cmdNext, MainTabNew.gI().xTab + MainTabNew.wOneItem + MainTabNew.wOne5 * 4, MainTabNew.gI().yTab + GameCanvas.h / 5 + MainTabNew.wOneItem * 2 + 22, 3, false, 90);
                GameCanvas.currentDialog = msgDialog;
                break;
            }
            case 4: {
                GameCanvas.end_Dialog_Help();
                if (this.Next < stringArray[this.Step].length && stringArray[this.Step][this.Next].length() > 0) {
                    this.p = new Point();
                    this.p.x = GameCanvas.hw - 40;
                    this.p.y = GameCanvas.h - GameCanvas.hCommand - 14;
                    this.p.w = 80;
                    this.strpopup = mFont.tahoma_7_black.splitFontArray(stringArray[this.Step][this.Next], this.p.w);
                    this.p.h = this.strpopup.length * GameCanvas.hText;
                    this.p.fRe = 2;
                    this.p.dis = 1;
                    this.p.frame = 1;
                }
                this.idTabHelp = 0;
                break;
            }
            case 5: 
            case 6: {
                GameCanvas.end_Dialog_Help();
                if (this.Next >= stringArray[this.Step].length || stringArray[this.Step][this.Next].length() <= 0) break;
                MsgDialog msgDialog = new MsgDialog();
                msgDialog.setDiaHelp(stringArray[this.Step][this.Next], this.cmdNext, GameCanvas.hw - 45, MainTabNew.gI().yTab + GameCanvas.h / 5 + MainTabNew.wOneItem * 2 + 22, 6, false, 90);
                GameCanvas.currentDialog = msgDialog;
                break;
            }
            case 9: {
                GameCanvas.end_Dialog_Help();
                if (this.Next < stringArray[this.Step].length && stringArray[this.Step][this.Next].length() > 0) {
                    this.p = new Point();
                    this.p.x = GameCanvas.hw - 40;
                    this.p.y = GameCanvas.h - GameCanvas.hCommand - 14;
                    this.p.w = 80;
                    this.strpopup = mFont.tahoma_7_black.splitFontArray(stringArray[this.Step][this.Next], this.p.w);
                    this.p.h = this.strpopup.length * GameCanvas.hText;
                    this.p.fRe = 2;
                    this.p.dis = 1;
                    this.p.frame = 1;
                }
                this.idTabHelp = 0;
                break;
            }
            case 10: {
                this.idTabHelp = 0;
                break;
            }
            case 11: 
            case 12: {
                GameCanvas.end_Dialog_Help();
                if (this.Next >= stringArray[this.Step].length || stringArray[this.Step][this.Next].length() <= 0) break;
                MsgDialog msgDialog = new MsgDialog();
                msgDialog.setDiaHelp(stringArray[this.Step][this.Next], this.cmdNext, MainTabNew.gI().xTab + MainTabNew.wOneItem * 2 + MainTabNew.wOne5 * 4, MainTabNew.gI().yTab + GameCanvas.h / 5 + MainTabNew.wOneItem * 2 + 22, 3, false, 90);
                GameCanvas.currentDialog = msgDialog;
                break;
            }
            case 13: {
                this.NextStep();
                this.setNext();
                break;
            }
        }
    }

    public void Step3(String[][] stringArray) {
        switch (this.Next) {
            case 0: 
            case 1: 
            case 2: 
            case 3: {
                GameCanvas.end_Dialog_Help();
                if (this.Next >= stringArray[this.Step].length || stringArray[this.Step][this.Next].length() <= 0) break;
                MsgDialog msgDialog = new MsgDialog();
                msgDialog.setDiaHelp(stringArray[this.Step][this.Next], this.cmdNext, MainTabNew.gI().xTab + MainTabNew.wOneItem * 3 - 40 + MainTabNew.wOne5 * 4, MainTabNew.gI().yTab + GameCanvas.h / 5 + MainTabNew.wOneItem * 2 + 22, 5, false, 90);
                GameCanvas.currentDialog = msgDialog;
                break;
            }
            case 4: {
                GameCanvas.end_Dialog_Help();
                if (this.Next < stringArray[this.Step].length && stringArray[this.Step][this.Next].length() > 0) {
                    this.p = new Point();
                    this.p.x = GameCanvas.hw - 40;
                    this.p.y = GameCanvas.h - GameCanvas.hCommand - 14;
                    this.p.w = 80;
                    this.strpopup = mFont.tahoma_7_black.splitFontArray(stringArray[this.Step][this.Next], this.p.w);
                    this.p.h = this.strpopup.length * GameCanvas.hText;
                    this.p.fRe = 2;
                    this.p.dis = 1;
                    this.p.frame = 1;
                }
                this.idTabHelp = 0;
                break;
            }
            case 5: 
            case 6: {
                if (this.Next >= stringArray[this.Step].length || stringArray[this.Step][this.Next].length() <= 0) break;
                MsgDialog msgDialog = new MsgDialog();
                msgDialog.setDiaHelp(stringArray[this.Step][this.Next], this.cmdNext, GameCanvas.hw - 45, MainTabNew.gI().yTab + GameCanvas.h / 5 + MainTabNew.wOneItem * 2 + 22, 6, false, 90);
                GameCanvas.currentDialog = msgDialog;
                break;
            }
            case 7: {
                GameCanvas.end_Dialog_Help();
                this.leftTab(1, stringArray);
                break;
            }
            case 8: {
                GameCanvas.end_Dialog_Help();
                if (this.Next < stringArray[this.Step].length && stringArray[this.Step][this.Next].length() > 0) {
                    this.p = new Point();
                    this.p.x = MainTabNew.gI().xTab + MainTabNew.wOne5 + MainTabNew.wOne5 / 2;
                    this.p.w = 80;
                    this.strpopup = mFont.tahoma_7_black.splitFontArray(stringArray[this.Step][this.Next], this.p.w);
                    this.p.h = this.strpopup.length * GameCanvas.hText;
                    this.p.y = MainTabNew.gI().yTab + GameCanvas.h / 5 + MainTabNew.wOneItem * 2 + 16 + this.p.h;
                    this.p.fRe = 3;
                    this.p.dis = 1;
                    this.p.frame = 1;
                }
                this.idTabHelp = -1;
                break;
            }
        }
    }

    public void Step4(String[][] stringArray) {
        switch (this.Next) {
            case 0: 
            case 4: 
            case 5: 
            case 6: 
            case 7: 
            case 8: {
                if (this.Next >= stringArray[this.Step].length || stringArray[this.Step][this.Next].length() <= 0) break;
                MsgDialog msgDialog = new MsgDialog();
                msgDialog.setDiaHelp(stringArray[this.Step][this.Next], this.cmdNext, GameCanvas.hw - 45, MainTabNew.gI().yTab + GameCanvas.h / 5 + MainTabNew.wOneItem * 2 + 22, 6, false, 90);
                GameCanvas.currentDialog = msgDialog;
                break;
            }
            case 1: {
                if (this.Next >= stringArray[this.Step].length || stringArray[this.Step][this.Next].length() <= 0) break;
                MsgDialog msgDialog = new MsgDialog();
                msgDialog.setDiaHelp(stringArray[this.Step][this.Next], this.cmdNext, MainTabNew.gI().xTab + MainTabNew.wOneItem + MainTabNew.wOne5 * 3 + MainTabNew.wblack / 5 * 2 + TabMySeftNew.delta + 16, MainTabNew.gI().yTab + GameCanvas.h / 5 + MainTabNew.wOneItem * 2 + MainTabNew.hblack / 12 * 8 / 2, 7, false, 90);
                GameCanvas.currentDialog = msgDialog;
                break;
            }
            case 2: {
                if (this.Next >= stringArray[this.Step].length || stringArray[this.Step][this.Next].length() <= 0) break;
                MsgDialog msgDialog = new MsgDialog();
                msgDialog.setDiaHelp(stringArray[this.Step][this.Next], this.cmdNext, MainTabNew.gI().xTab + MainTabNew.wOneItem + MainTabNew.wOne5 * 3 + MainTabNew.wblack / 5 * 2 + TabMySeftNew.delta - 16, MainTabNew.gI().yTab + GameCanvas.h / 5 + MainTabNew.wOneItem * 2 + MainTabNew.hblack / 12 * 8 / 2, 8, false, 90);
                GameCanvas.currentDialog = msgDialog;
                break;
            }
            case 3: {
                if (this.Next >= stringArray[this.Step].length || stringArray[this.Step][this.Next].length() <= 0) break;
                MsgDialog msgDialog = new MsgDialog();
                msgDialog.setDiaHelp(stringArray[this.Step][this.Next], this.cmdNext, MainTabNew.gI().xTab + MainTabNew.wblack / 2, MainTabNew.gI().yTab + GameCanvas.h / 5 + MainTabNew.wOneItem + MainTabNew.hblack - MainTabNew.wOneItem * 2 - MainTabNew.wOne5 - 20, 2, false, 90);
                GameCanvas.currentDialog = msgDialog;
                break;
            }
            case 9: {
                GameCanvas.end_Dialog_Help();
                if (this.Next < stringArray[this.Step].length && stringArray[this.Step][this.Next].length() > 0) {
                    this.p = new Point();
                    this.p.x = GameCanvas.w - 83;
                    this.p.y = GameCanvas.h - GameCanvas.hCommand - 14;
                    this.p.w = 80;
                    this.strpopup = mFont.tahoma_7_black.splitFontArray(stringArray[this.Step][this.Next], this.p.w);
                    this.p.h = this.strpopup.length * GameCanvas.hText;
                    this.p.fRe = 2;
                    this.p.dis = 1;
                    this.p.frame = 1;
                }
                this.idTabHelp = -1;
                break;
            }
        }
    }

    public void Step5(String[][] stringArray) {
        switch (this.Next) {
            case 0: {
                if (!GameCanvas.isVNLanguage && !IndoServer.isIndoSv) break;
                GameCanvas.end_Dialog_Help();
                this.p = null;
                GameScreen.player.strChatPopup = T.timduongtoilang;
                this.tickBegin = 0;
                break;
            }
            case 1: {
                GameCanvas.end_Dialog_Help();
                if (this.Next >= stringArray[this.Step].length || stringArray[this.Step][this.Next].length() <= 0) break;
                MsgDialog msgDialog = new MsgDialog();
                msgDialog.setDiaHelp(stringArray[this.Step][this.Next], this.cmdNext, GameCanvas.w - 96, GameCanvas.h - 23 - GameCanvas.minimap.maxY * MiniMap.wMini - 16, 2, false, 90);
                GameCanvas.currentDialog = msgDialog;
                break;
            }
            case 2: {
                GameCanvas.end_Dialog_Help();
                if (this.Next < stringArray[this.Step].length && stringArray[this.Step][this.Next].length() > 0) {
                    MsgDialog msgDialog = new MsgDialog();
                    msgDialog.setDiaHelp(stringArray[this.Step][this.Next], this.cmdNext, GameCanvas.w - 96, GameCanvas.h - 23 - GameCanvas.minimap.maxY * MiniMap.wMini - 16, -1, false, 90);
                    GameCanvas.currentDialog = msgDialog;
                }
                GameCanvas.minimap.setPoint(51, 2, GameCanvas.loadmap.idMap);
                break;
            }
            case 3: {
                GameCanvas.end_Dialog_Help();
                if (this.Next >= stringArray[this.Step].length || stringArray[this.Step][this.Next].length() <= 0) break;
                MsgDialog msgDialog = new MsgDialog();
                msgDialog.setDiaHelp(stringArray[this.Step][this.Next], this.cmdNext, 3, 60, 5, false, 90);
                GameCanvas.currentDialog = msgDialog;
                break;
            }
            case 4: {
                GameCanvas.end_Dialog_Help();
                if (this.Next >= stringArray[this.Step].length || stringArray[this.Step][this.Next].length() <= 0) break;
                MsgDialog msgDialog = new MsgDialog();
                msgDialog.setDiaHelp(stringArray[this.Step][this.Next], this.cmdNext, GameCanvas.w - 93, 45, 5, false, 90);
                GameCanvas.currentDialog = msgDialog;
                break;
            }
            case 5: {
                GameCanvas.end_Dialog_Help();
                if (this.Next >= stringArray[this.Step].length || stringArray[this.Step][this.Next].length() <= 0) break;
                MsgDialog msgDialog = new MsgDialog();
                msgDialog.setDiaHelp(stringArray[this.Step][this.Next], this.cmdNext, GameCanvas.w - 93, GameCanvas.h - GameCanvas.hCommand, 2, false, 90);
                GameCanvas.currentDialog = msgDialog;
                break;
            }
            case 6: {
                GameCanvas.end_Dialog_Help();
                if (this.Next >= stringArray[this.Step].length || stringArray[this.Step][this.Next].length() <= 0) break;
                MsgDialog msgDialog = new MsgDialog();
                msgDialog.setDiaHelp(stringArray[this.Step][this.Next], this.cmdNext, GameCanvas.hw - 45, GameCanvas.h - GameCanvas.hCommand - 14 - 25, 2, false, 90);
                GameCanvas.currentDialog = msgDialog;
                break;
            }
            case 7: {
                GameCanvas.end_Dialog_Help();
                this.dialogCenter(stringArray);
                break;
            }
            case 8: {
                if (!GameCanvas.isVNLanguage && !IndoServer.isIndoSv) {
                    if (this.isStepp5) break;
                    GameCanvas.end_Dialog_Help();
                    this.p = null;
                    GameScreen.player.strChatPopup = T.timduongtoilang;
                    this.tickBegin = 0;
                    GameCanvas.end_Dialog_Help();
                    this.isStepp5 = true;
                    break;
                }
                GameCanvas.end_Dialog_Help();
                break;
            }
        }
    }

    public void Step6(String[][] stringArray) {
        switch (this.Next) {
            case 0: 
            case 1: {
                this.dialogCenter(stringArray);
                break;
            }
            case 2: {
                GameCanvas.end_Dialog_Help();
                this.PopupLeft(stringArray);
                break;
            }
        }
    }

    public void Step7(String[][] stringArray) {
        switch (this.Next) {
            case 0: {
                GameCanvas.end_Dialog_Help();
                this.leftTab(2, stringArray);
                break;
            }
            case 1: 
            case 2: 
            case 3: {
                GameCanvas.end_Dialog_Help();
                if (this.Next >= stringArray[this.Step].length || stringArray[this.Step][this.Next].length() <= 0) break;
                MsgDialog msgDialog = new MsgDialog();
                int n = 20;
                if (GameCanvas.isTouch) {
                    n = 24;
                }
                msgDialog.setDiaHelp(stringArray[this.Step][this.Next], this.cmdNext, MainTabNew.gI().xTab + MainTabNew.wblack / 2, n * T.mKyNang.length + MainTabNew.gI().yTab + GameCanvas.h / 5 + MainTabNew.wOneItem + 20, 5, false, 90);
                GameCanvas.currentDialog = msgDialog;
                break;
            }
            case 4: {
                GameCanvas.end_Dialog_Help();
                if (this.Next >= stringArray[this.Step].length || stringArray[this.Step][this.Next].length() <= 0) break;
                MsgDialog msgDialog = new MsgDialog();
                int n = 20;
                if (GameCanvas.isTouch) {
                    n = 24;
                }
                msgDialog.setDiaHelp(stringArray[this.Step][this.Next], this.cmdNext, MainTabNew.gI().xTab + MainTabNew.wblack / 2, n * T.mKyNang.length + MainTabNew.gI().yTab + GameCanvas.h / 5 + MainTabNew.wOneItem, 2, false, 90);
                GameCanvas.currentDialog = msgDialog;
                break;
            }
            case 5: 
            case 7: 
            case 8: {
                GameCanvas.end_Dialog_Help();
                if (this.Next >= stringArray[this.Step].length || stringArray[this.Step][this.Next].length() <= 0) break;
                MsgDialog msgDialog = new MsgDialog();
                msgDialog.setDiaHelp(stringArray[this.Step][this.Next], this.cmdNext, GameCanvas.hw - 45, MainTabNew.gI().yTab + GameCanvas.h / 5 + MainTabNew.wOneItem * 2 + 22, 6, false, 90);
                GameCanvas.currentDialog = msgDialog;
                break;
            }
            case 6: {
                GameCanvas.end_Dialog_Help();
                if (this.Next < stringArray[this.Step].length && stringArray[this.Step][this.Next].length() > 0) {
                    int n = MainTabNew.gI().xTab + MainTabNew.wblack / 4 + 30;
                    int n2 = MainTabNew.gI().yTab + GameCanvas.hText + GameCanvas.h / 5 + 4 + (GameCanvas.isTouch ? 4 : 0);
                    this.p = new Point();
                    this.p.x = n;
                    this.p.w = 80;
                    this.strpopup = mFont.tahoma_7_black.splitFontArray(stringArray[this.Step][this.Next], this.p.w);
                    this.p.h = this.strpopup.length * GameCanvas.hText;
                    this.p.y = n2;
                    this.p.fRe = 2;
                    this.p.dis = 1;
                    this.p.frame = 1;
                }
                this.idTabHelp = 2;
                break;
            }
            case 9: {
                GameCanvas.end_Dialog_Help();
                if (this.Next < stringArray[this.Step].length && stringArray[this.Step][this.Next].length() > 0) {
                    this.p = new Point();
                    this.p.x = MainTabNew.gI().xTab + MainTabNew.wOne5 + MainTabNew.wOne5 / 2;
                    this.p.w = 80;
                    this.strpopup = mFont.tahoma_7_black.splitFontArray(stringArray[this.Step][this.Next], this.p.w);
                    this.p.h = this.strpopup.length * GameCanvas.hText;
                    this.p.y = MainTabNew.gI().yTab + GameCanvas.h / 5 + MainTabNew.wOneItem + MainTabNew.wOneItem * 3 + 16 + this.p.h;
                    this.p.fRe = 3;
                    this.p.dis = 1;
                    this.p.frame = 1;
                }
                this.idTabHelp = -1;
                break;
            }
        }
    }

    private void Step8(String[][] stringArray) {
        switch (this.Next) {
            case 0: {
                if (!GameCanvas.isVNLanguage && !IndoServer.isIndoSv) {
                    GameCanvas.end_Dialog_Help();
                    this.leftTab(3, stringArray);
                    GameCanvas.AllInfo.selectTab = 3;
                    break;
                }
                GameCanvas.end_Dialog_Help();
                if (this.Next >= stringArray[this.Step].length || stringArray[this.Step][this.Next].length() <= 0) break;
                MsgDialog msgDialog = new MsgDialog();
                msgDialog.setDiaHelp(stringArray[this.Step][this.Next], this.cmdNext, GameCanvas.hw - 45, MainTabNew.gI().yTab + GameCanvas.h / 5 + MainTabNew.wOneItem * 2 + 22, 6, false, 90);
                GameCanvas.currentDialog = msgDialog;
                break;
            }
            case 4: {
                if (!GameCanvas.isVNLanguage && !IndoServer.isIndoSv) {
                    GameCanvas.end_Dialog_Help();
                    this.Step = -1;
                    this.Next = 0;
                    this.SaveStep();
                    break;
                }
                GameCanvas.end_Dialog_Help();
                if (this.Next >= stringArray[this.Step].length || stringArray[this.Step][this.Next].length() <= 0) break;
                MsgDialog msgDialog = new MsgDialog();
                msgDialog.setDiaHelp(stringArray[this.Step][this.Next], this.cmdNext, GameCanvas.hw - 45, MainTabNew.gI().yTab + GameCanvas.h / 5 + MainTabNew.wOneItem * 2 + 22, 6, false, 90);
                GameCanvas.currentDialog = msgDialog;
                break;
            }
            case 5: 
            case 6: 
            case 8: 
            case 9: {
                GameCanvas.end_Dialog_Help();
                if (this.Next >= stringArray[this.Step].length || stringArray[this.Step][this.Next].length() <= 0) break;
                MsgDialog msgDialog = new MsgDialog();
                msgDialog.setDiaHelp(stringArray[this.Step][this.Next], this.cmdNext, GameCanvas.hw - 45, MainTabNew.gI().yTab + GameCanvas.h / 5 + MainTabNew.wOneItem * 2 + 22, 6, false, 90);
                GameCanvas.currentDialog = msgDialog;
                break;
            }
            case 1: {
                if (!GameCanvas.isVNLanguage && !IndoServer.isIndoSv) {
                    GameCanvas.end_Dialog_Help();
                    this.leftTab(4, stringArray);
                    GameCanvas.AllInfo.selectTab = 4;
                    break;
                }
                GameCanvas.end_Dialog_Help();
                if (this.Next >= stringArray[this.Step].length || stringArray[this.Step][this.Next].length() <= 0) break;
                MsgDialog msgDialog = new MsgDialog();
                msgDialog.setDiaHelp(stringArray[this.Step][this.Next], this.cmdNext, MainTabNew.gI().xTab + MainTabNew.wOne5 + MainTabNew.wblack / 2 + MainTabNew.wblack / 8, MainTabNew.gI().yTab + GameCanvas.h / 5 + MainTabNew.wOneItem + MainTabNew.hblack / 2, 7, false, 90);
                GameCanvas.currentDialog = msgDialog;
                break;
            }
            case 2: {
                if (!GameCanvas.isVNLanguage && !IndoServer.isIndoSv) {
                    GameCanvas.end_Dialog_Help();
                    if (this.Next >= stringArray[this.Step].length || stringArray[this.Step][this.Next].length() <= 0) break;
                    MsgDialog msgDialog = new MsgDialog();
                    msgDialog.setDiaHelp(stringArray[this.Step][this.Next], this.cmdNext, MainTabNew.gI().xTab + MainTabNew.wOne5 + MainTabNew.wblack / 2 + MainTabNew.wblack / 8, MainTabNew.gI().yTab + GameCanvas.h / 5 + MainTabNew.wOneItem + MainTabNew.hblack / 2, 2, false, 90);
                    GameCanvas.currentDialog = msgDialog;
                    break;
                }
                GameCanvas.end_Dialog_Help();
                if (this.Next >= stringArray[this.Step].length || stringArray[this.Step][this.Next].length() <= 0) break;
                MsgDialog msgDialog = new MsgDialog();
                msgDialog.setDiaHelp(stringArray[this.Step][this.Next], this.cmdNext, MainTabNew.gI().xTab + MainTabNew.wOne5 + MainTabNew.wblack / 2 + MainTabNew.wblack / 8, MainTabNew.gI().yTab + GameCanvas.h / 5 + MainTabNew.wOneItem + MainTabNew.hblack / 2, 7, false, 90);
                GameCanvas.currentDialog = msgDialog;
                break;
            }
            case 3: {
                if (!GameCanvas.isVNLanguage && !IndoServer.isIndoSv) {
                    GameCanvas.end_Dialog_Help();
                    if (this.Next >= stringArray[this.Step].length || stringArray[this.Step][this.Next].length() <= 0) break;
                    MsgDialog msgDialog = new MsgDialog();
                    msgDialog.setDiaHelp(stringArray[this.Step][this.Next], this.cmdNext, MainTabNew.gI().xTab + MainTabNew.wOne5 + MainTabNew.wblack / 4 * 3, MainTabNew.gI().yTab + GameCanvas.h / 5 + MainTabNew.wOneItem + MainTabNew.hblack / 2, 2, false, 90);
                    GameCanvas.currentDialog = msgDialog;
                    break;
                }
                GameCanvas.end_Dialog_Help();
                if (this.Next >= stringArray[this.Step].length || stringArray[this.Step][this.Next].length() <= 0) break;
                MsgDialog msgDialog = new MsgDialog();
                msgDialog.setDiaHelp(stringArray[this.Step][this.Next], this.cmdNext, MainTabNew.gI().xTab + MainTabNew.wOne5 + MainTabNew.wblack / 4 * 3, MainTabNew.gI().yTab + GameCanvas.h / 5 + MainTabNew.wOneItem + MainTabNew.hblack / 2, 8, false, 90);
                GameCanvas.currentDialog = msgDialog;
                break;
            }
            case 7: {
                GameCanvas.end_Dialog_Help();
                if (this.Next < stringArray[this.Step].length && stringArray[this.Step][this.Next].length() > 0) {
                    this.p = new Point();
                    this.p.x = MainTabNew.gI().xTab + MainTabNew.wOne5 + MainTabNew.wOne5 / 2 + MainTabNew.wblack / 8 * 3;
                    this.p.w = 80;
                    this.strpopup = mFont.tahoma_7_black.splitFontArray(stringArray[this.Step][this.Next], this.p.w);
                    this.p.h = this.strpopup.length * GameCanvas.hText;
                    this.p.y = MainTabNew.gI().yTab + GameCanvas.h / 5 + MainTabNew.wOneItem;
                    this.p.fRe = -1;
                    this.p.dis = 1;
                    this.p.frame = 1;
                }
                this.idTabHelp = 3;
                break;
            }
            case 10: {
                GameCanvas.end_Dialog_Help();
            }
        }
    }

    public void Step9(String[][] stringArray) {
        switch (this.Next) {
            case 0: {
                GameCanvas.end_Dialog_Help();
                this.dialogCenter(stringArray);
                break;
            }
            case 1: {
                GameCanvas.end_Dialog_Help();
                if (this.Next < stringArray[this.Step].length && stringArray[this.Step][this.Next].length() > 0) {
                    this.p = new Point();
                    this.p.x = MainTabNew.gI().xTab + MainTabNew.wOne5 + MainTabNew.wOne5 / 2;
                    this.p.w = 80;
                    this.strpopup = mFont.tahoma_7_black.splitFontArray(stringArray[this.Step][this.Next], this.p.w);
                    this.p.h = this.strpopup.length * GameCanvas.hText;
                    this.p.y = MainTabNew.gI().yTab + GameCanvas.h / 5 + MainTabNew.wOneItem * 5 + 16 + this.p.h;
                    this.p.fRe = 3;
                    this.p.dis = 1;
                    this.p.frame = 1;
                }
                this.idTabHelp = -1;
                break;
            }
            case 2: 
            case 5: 
            case 6: {
                GameCanvas.end_Dialog_Help();
                if (this.Next >= stringArray[this.Step].length || stringArray[this.Step][this.Next].length() <= 0) break;
                MsgDialog msgDialog = new MsgDialog();
                msgDialog.setDiaHelp(stringArray[this.Step][this.Next], this.cmdNext, GameCanvas.hw - 45, MainTabNew.gI().yTab + GameCanvas.h / 5 + MainTabNew.wOneItem * 2 + 22, 6, false, 90);
                GameCanvas.currentDialog = msgDialog;
                break;
            }
            case 3: {
                GameCanvas.end_Dialog_Help();
                if (this.Next >= stringArray[this.Step].length || stringArray[this.Step][this.Next].length() <= 0) break;
                MsgDialog msgDialog = new MsgDialog();
                msgDialog.setDiaHelp(stringArray[this.Step][this.Next], this.cmdNext, MainTabNew.gI().xTab + MainTabNew.wOneItem + MainTabNew.wblack / 4 - 45, MainTabNew.gI().yTab + MainTabNew.wOneItem + GameCanvas.h / 5 + 22, 5, false, 90);
                GameCanvas.currentDialog = msgDialog;
                break;
            }
            case 4: {
                GameCanvas.end_Dialog_Help();
                if (this.Next >= stringArray[this.Step].length || stringArray[this.Step][this.Next].length() <= 0) break;
                MsgDialog msgDialog = new MsgDialog();
                msgDialog.setDiaHelp(stringArray[this.Step][this.Next], this.cmdNext, MainTabNew.gI().xTab + MainTabNew.wOneItem + MainTabNew.wblack / 4 * 3 - 45, MainTabNew.gI().yTab + MainTabNew.wOneItem + GameCanvas.h / 5 + 22, 5, false, 90);
                GameCanvas.currentDialog = msgDialog;
                break;
            }
            case 7: {
                GameCanvas.end_Dialog_Help();
                this.Step = -1;
                this.Next = 0;
                this.SaveStep();
            }
        }
    }

    public void PopupLeft(String[][] stringArray) {
        if (this.Next < stringArray[this.Step].length && stringArray[this.Step][this.Next].length() > 0) {
            this.p = new Point();
            this.p.x = 3;
            this.p.y = GameCanvas.h - GameCanvas.hCommand - 14;
            this.p.w = 80;
            this.strpopup = mFont.tahoma_7_black.splitFontArray(stringArray[this.Step][this.Next], this.p.w);
            this.p.h = this.strpopup.length * GameCanvas.hText;
            this.p.fRe = 2;
            this.p.dis = 1;
            this.p.frame = 1;
        }
    }

    public void dialogCenter(String[][] stringArray) {
        if (this.Next < stringArray[this.Step].length && stringArray[this.Step][this.Next].length() > 0) {
            int n = GameCanvas.w - 30;
            if (n > 200) {
                n = 200;
            }
            int n2 = GameCanvas.hw - n / 2;
            int n3 = GameCanvas.h - GameCanvas.hCommand * 2;
            MsgDialog msgDialog = new MsgDialog();
            msgDialog.setDiaHelp(stringArray[this.Step][this.Next], this.cmdNext, n2, n3, -1, false, n);
            GameCanvas.currentDialog = msgDialog;
        }
    }

    public void leftTab(int n, String[][] stringArray) {
        GameCanvas.end_Dialog_Help();
        if (this.Next < stringArray[this.Step].length && stringArray[this.Step][this.Next].length() > 0) {
            MsgDialog msgDialog = new MsgDialog();
            msgDialog.setDiaHelp(stringArray[this.Step][this.Next], this.cmdNext, MainTabNew.gI().xTab + MainTabNew.wOne5 + MainTabNew.wOne5 / 2, MainTabNew.gI().yTab + GameCanvas.h / 5 + MainTabNew.wOneItem + 16 + n * MainTabNew.wOneItem, 3, false, 90);
            GameCanvas.currentDialog = msgDialog;
        }
    }
}

