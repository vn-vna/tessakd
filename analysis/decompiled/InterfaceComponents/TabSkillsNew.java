/*
 * Decompiled with CFR 0.152.
 */
package InterfaceComponents;

import CLib.TField;
import CLib.mFont;
import CLib.mGraphics;
import CLib.mSound;
import CLib.mSystem;
import CLib.mVector;
import GameEffect.EffectEnd;
import GameEffect.EffectSkill;
import GameEffect.MainEffect;
import GameObjects.MainItem;
import GameObjects.Player;
import GameScreen.GameScreen;
import GameScreen.MainScreen;
import GameScreen.PaintInfoGameScreen;
import GameScreen.TabScreenNew;
import InterfaceComponents.InputDialog;
import InterfaceComponents.ListNew;
import InterfaceComponents.MainTabNew;
import InterfaceComponents.iCommand;
import Main.GameCanvas;
import Model.AvMain;
import Model.CRes;
import Model.T;
import Skill.HotKey;
import Skill.Skill;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import netcommand.global.GlobalService;

public class TabSkillsNew
extends MainTabNew {
    public static int[][] XYpaint;
    int sizeKill = 22;
    int idSelect;
    int hcmd;
    iCommand cmdSetPoint;
    iCommand cmdSendSetPoint;
    iCommand cmdSendSetPointOne;
    iCommand cmdSetKey;
    iCommand cmdMenu;
    iCommand cmdupskill;
    InputDialog inputDialog;
    ListNew list;
    mVector vecListCmd;
    public static mVector vecPaintSkill;
    boolean isSaveSkill = false;
    public mVector vecEffRe = new mVector();
    int[] stylePaint = new int[]{1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1, -1};
    int hKill = 4;
    int w8;

    public TabSkillsNew(String string) {
        this.typeTab = (byte)3;
        this.nameTab = string;
        this.xBegin = this.xTab + MainTabNew.wOneItem + MainTabNew.wOne5 * 3 + MainTabNew.wblack % 8 / 2;
        this.yBegin = this.yTab + GameCanvas.h / 5 + MainTabNew.wOneItem;
        XYpaint = mSystem.new_M_Int(Player.MaxSkill, 2);
        int n = this.yBegin + MainTabNew.wOneItem / 2 + GameCanvas.hText;
        for (int i = 0; i < Player.MaxSkill; ++i) {
            if (i == 0) {
                TabSkillsNew.XYpaint[i][0] = this.xBegin + MainTabNew.wblack / 2;
                TabSkillsNew.XYpaint[i][1] = n;
                n += this.sizeKill * 2;
                continue;
            }
            TabSkillsNew.XYpaint[i][1] = n;
            if (i % 2 == 1) {
                TabSkillsNew.XYpaint[i][0] = this.xBegin + MainTabNew.wblack / 4;
                continue;
            }
            TabSkillsNew.XYpaint[i][0] = this.xBegin + MainTabNew.wblack / 4 * 3;
            n += this.sizeKill * 2;
        }
        this.init();
        this.cmdBack = new iCommand(T.back, -1, this);
        if (GameCanvas.isTouch) {
            this.cmdBack.caption = T.close;
        }
        this.cmdSetPoint = new iCommand(T.setPoint, 0, this);
        this.cmdSendSetPoint = new iCommand(T.cong, 1, this);
        this.cmdSendSetPointOne = new iCommand(T.cong, 2, this);
        this.cmdSetKey = new iCommand(T.setKey, 3, this);
        this.cmdMenu = new iCommand(T.select, 5, this);
        this.cmdupskill = new iCommand(T.nangcap, 6, this);
        this.w8 = MainTabNew.wblack / 8;
        this.vecEffRe.removeAllElements();
    }

    public void init() {
        MainTabNew.timePaintInfo = 0;
        int n = XYpaint[Player.MaxSkill - 1][1] - this.yBegin + this.sizeKill - MainTabNew.hblack + 5;
        MainScreen.cameraSub.setAll(0, n, 0, 0);
        this.list = new ListNew(this.xBegin, this.yBegin + GameCanvas.hText + 2, MainTabNew.wblack, MainTabNew.hblack - GameCanvas.hText - 2, 0, 0, MainScreen.cameraSub.yLimit);
        if (!GameCanvas.isTouch) {
            this.right = this.cmdBack;
            this.center = this.cmdMenu;
        }
        this.idSelect = GameCanvas.isTouch ? -1 : 0;
        this.listContent = null;
    }

    public void backTab() {
        if (this.isSaveSkill) {
            TabSkillsNew.saveSkill();
            this.isSaveSkill = false;
        }
        MainTabNew.timePaintInfo = 0;
        MainTabNew.Focus = 0;
        this.idSelect = GameCanvas.isTouch ? -1 : 0;
        super.backTab();
    }

    public void commandPointer(int n, int n2) {
        if ((this.idSelect == -1 || this.idSelect > vecPaintSkill.size() - 1) && n != -1) {
            return;
        }
        switch (n) {
            case -1: {
                this.backTab();
                break;
            }
            case 0: {
                Skill skill = (Skill)vecPaintSkill.elementAt(this.idSelect);
                if (skill.lvMin > GameScreen.player.Lv) {
                    GameCanvas.start_Ok_Dialog(T.capdochuadu);
                    return;
                }
                if (Player.diemKyNang == 1) {
                    GameCanvas.start_Left_Dialog(T.cong1kynang + skill.name, this.cmdSendSetPointOne);
                    break;
                }
                if (Player.diemKyNang <= 0) break;
                int n3 = Player.diemKyNang;
                if (n3 > 10 - Player.mCurentLvSkill[skill.Id]) {
                    n3 = 10 - Player.mCurentLvSkill[skill.Id];
                }
                this.inputDialog = new InputDialog();
                this.inputDialog.setinfo(T.nhapsodiem + skill.name + " (" + T.nhohonhoacbang + n3 + ")", this.cmdSendSetPoint, true, T.tabkynang);
                GameCanvas.currentDialog = this.inputDialog;
                break;
            }
            case 1: {
                short s = 0;
                try {
                    s = Short.parseShort(this.inputDialog.tfInput.getText());
                }
                catch (Exception exception) {
                    s = 0;
                }
                if (s < 1) {
                    return;
                }
                GlobalService.gI().Add_Base_Skill_Point((byte)1, (byte)((Skill)TabSkillsNew.vecPaintSkill.elementAt((int)this.idSelect)).Id, s);
                if (GameScreen.help.setStep_Next(8, 7)) {
                    ++GameScreen.help.Next;
                    GameScreen.help.setNext();
                    break;
                }
                GameCanvas.end_Dialog();
                break;
            }
            case 2: {
                GameCanvas.end_Dialog();
                GlobalService.gI().Add_Base_Skill_Point((byte)1, (byte)((Skill)TabSkillsNew.vecPaintSkill.elementAt((int)this.idSelect)).Id, (short)1);
                if (GameScreen.help.setStep_Next(8, 7)) {
                    ++GameScreen.help.Next;
                    GameScreen.help.setNext();
                }
                MainTabNew.timePaintInfo = 0;
                break;
            }
            case 3: {
                mVector mVector2 = new mVector("TabSkillsNew menu");
                for (int i = 0; i < 5; ++i) {
                    iCommand iCommand2 = null;
                    iCommand2 = !GameCanvas.isTouch ? (TField.isQwerty ? new iCommand(T.phim + PaintInfoGameScreen.mValueChar[i], 4, i, (AvMain)this) : new iCommand(T.phim + PaintInfoGameScreen.mValueHotKey[i], 4, i, (AvMain)this)) : new iCommand(T.oso + (i + 1), 4, i, (AvMain)this);
                    mVector2.addElement(iCommand2);
                }
                GameCanvas.menu2.startAt(mVector2, 2, T.setKey, false, null);
                break;
            }
            case 4: {
                Skill skill = (Skill)vecPaintSkill.elementAt(this.idSelect);
                Player.mhotkey[Player.levelTab][n2].setHotKey(skill.Id, HotKey.SKILL, (byte)0);
                if (GameCanvas.isTouch) {
                    TabSkillsNew.saveSkill();
                    break;
                }
                this.isSaveSkill = true;
                break;
            }
            case 5: {
                if (this.idSelect == -1) {
                    return;
                }
                mVector mVector3 = new mVector("TabSkillsNew vecmenu");
                mVector3 = this.doMenu();
                if (mVector3 == null || mVector3.size() <= 0) break;
                GameCanvas.menu2.startAt(mVector3, 2, T.kynang, false, null);
                break;
            }
            case 6: {
                GlobalService.gI().Add_Base_Skill_Point((byte)2, (byte)((Skill)TabSkillsNew.vecPaintSkill.elementAt((int)this.idSelect)).Id);
                this.vecEffRe.removeAllElements();
            }
        }
        super.commandPointer(n, n2);
    }

    public void upgraderesult(int n) {
        if (n == 0) {
            mSound.playSound(27, mSound.volumeSound);
            this.vecEffRe.removeAllElements();
            this.addEffectEnd_ReBuild_ss(11, XYpaint[this.idSelect][0], XYpaint[this.idSelect][1] + this.sizeKill / 2);
            this.addEffectRebuild(69, XYpaint[this.idSelect][0], XYpaint[this.idSelect][1] + this.sizeKill / 2, 300);
            this.addEffectRebuild(69, XYpaint[this.idSelect][0], XYpaint[this.idSelect][1] + this.sizeKill / 2, 300);
            GameCanvas.addInfoChar(T.thatbai);
        } else {
            mSound.playSound(26, mSound.volumeSound);
            this.vecEffRe.removeAllElements();
            this.addEffectEnd_ReBuild_ss(32, XYpaint[this.idSelect][0], XYpaint[this.idSelect][1] + this.sizeKill / 2);
            this.addEffectEnd_ReBuild_ss(33, XYpaint[this.idSelect][0], XYpaint[this.idSelect][1] + this.sizeKill / 2);
            this.addEffectEnd_ReBuild_ss(34, XYpaint[this.idSelect][0], XYpaint[this.idSelect][1] + this.sizeKill / 2);
            GameCanvas.addInfoChar(T.thanhcong);
        }
    }

    public void addEffectEnd_ReBuild_ss(int n, int n2, int n3) {
        EffectEnd effectEnd = new EffectEnd(n, n2, n3);
        this.vecEffRe.addElement(effectEnd);
    }

    public void addEffectRebuild(int n, int n2, int n3, int n4) {
        EffectSkill effectSkill = new EffectSkill(n, n2, n3, n4, 0, 0);
        this.vecEffRe.addElement(effectSkill);
    }

    public void setXYSkill(mVector mVector2) {
        XYpaint = null;
        this.xBegin = this.xTab + MainTabNew.wOneItem + MainTabNew.wOne5 * 3 + MainTabNew.wblack % 8 / 2;
        this.yBegin = this.yTab + GameCanvas.h / 5 + MainTabNew.wOneItem;
        XYpaint = mSystem.new_M_Int(Player.MaxSkill, 2);
        int n = this.yBegin + MainTabNew.wOneItem / 2 + GameCanvas.hText;
        for (int i = 0; i < mVector2.size(); ++i) {
            Skill skill = (Skill)mVector2.elementAt(i);
            if (skill == null) continue;
            if (i == 0) {
                TabSkillsNew.XYpaint[i][0] = this.xBegin + MainTabNew.wblack / 2;
                TabSkillsNew.XYpaint[i][1] = n;
                n += this.sizeKill * 2;
                continue;
            }
            TabSkillsNew.XYpaint[i][1] = n;
            if (skill.typePaint == 0) {
                TabSkillsNew.XYpaint[i][0] = this.xBegin + MainTabNew.wblack / 4;
                continue;
            }
            if (skill.typePaint != 1) continue;
            TabSkillsNew.XYpaint[i][0] = this.xBegin + MainTabNew.wblack / 4 * 3;
            if (i >= mVector2.size() - 2) continue;
            n += this.sizeKill * 2;
        }
    }

    public void paint(mGraphics mGraphics2) {
        Object object;
        int n;
        mFont.tahoma_7_white.drawString(mGraphics2, T.diemkynang + Player.diemKyNang, this.xBegin + 2, this.yBegin + 3, 0, true);
        mGraphics2.setClip(this.xBegin, this.yBegin + GameCanvas.hText + 2, MainTabNew.wblack, MainTabNew.hblack - 2 - GameCanvas.hText);
        mGraphics2.saveCanvas();
        mGraphics2.ClipRec(this.xBegin, this.yBegin + GameCanvas.hText + 2, MainTabNew.wblack, MainTabNew.hblack - 2 - GameCanvas.hText);
        mGraphics2.translate(0, -MainScreen.cameraSub.yCam);
        for (n = 0; n < vecPaintSkill.size(); ++n) {
            this.paintLine(mGraphics2, n);
            object = (Skill)vecPaintSkill.elementAt(n);
            if (object == null) continue;
            ((Skill)object).paint(mGraphics2, XYpaint[n][0], XYpaint[n][1] + this.sizeKill / 2, 3);
            if (n == this.idSelect && MainTabNew.Focus == 1) {
                mGraphics2.drawImage(AvMain.imgSelect_1, XYpaint[n][0] - this.sizeKill / 2 - 2, XYpaint[n][1] - 2, 0, true);
            }
            if (((Skill)object).lvMin > GameScreen.player.Lv) {
                mGraphics2.drawRegion(AvMain.imgDelaySkill, 0, 0, 20, 20, 0, XYpaint[n][0], XYpaint[n][1] + this.sizeKill / 2, 3, true);
            }
            if (n == 0) continue;
            AvMain.Font3dWhite(mGraphics2, Player.mCurentLvSkill[((Skill)object).Id] + "", XYpaint[n][0] + this.sizeKill / 2 + 4, XYpaint[n][1] + this.sizeKill / 2 - 12, 0);
            if (Player.mPlusLvSkill[((Skill)object).Id] <= 0) continue;
            AvMain.Font3dColor(mGraphics2, "+" + Player.mPlusLvSkill[((Skill)object).Id], XYpaint[n][0] + this.sizeKill / 2 + 4, XYpaint[n][1] + this.sizeKill - 12, 0, (byte)1);
        }
        for (n = 0; n < this.vecEffRe.size(); ++n) {
            object = (MainEffect)this.vecEffRe.elementAt(n);
            ((MainEffect)object).paint(mGraphics2);
        }
        mGraphics.resetTransAndroid(mGraphics2);
        mGraphics2.restoreCanvas();
        mGraphics2.endClip();
        if ((!GameCanvas.menu2.isShowMenu && GameCanvas.currentDialog == null || MainTabNew.longwidth > 0) && MainTabNew.Focus == 1 && MainTabNew.timePaintInfo > MainTabNew.timeRequest) {
            this.paintPopupContent(mGraphics2, false);
            if (this.vecListCmd != null) {
                for (n = 0; n < this.vecListCmd.size(); ++n) {
                    object = (iCommand)this.vecListCmd.elementAt(n);
                    ((iCommand)object).paint(mGraphics2, ((iCommand)object).xCmd, ((iCommand)object).yCmd);
                }
            }
        }
    }

    public void paintLine(mGraphics mGraphics2, int n) {
        mGraphics2.setColor(MainTabNew.color[6]);
        if (this.stylePaint[n] == 0) {
            mGraphics2.fillRect(XYpaint[n][0], XYpaint[n][1] + this.sizeKill, 1, this.hKill * 2 + this.sizeKill, true);
        } else if (this.stylePaint[n] == 1) {
            mGraphics2.fillRect(XYpaint[n][0], XYpaint[n][1] + this.sizeKill, 1, this.sizeKill / 2, true);
            mGraphics2.fillRect(XYpaint[n][0] - 2 * this.w8, XYpaint[n][1] + this.sizeKill + this.sizeKill / 2, this.w8 * 4 + 1, 1, true);
            mGraphics2.fillRect(XYpaint[n][0] - 2 * this.w8, XYpaint[n][1] + this.sizeKill + this.sizeKill / 2 + 1, 1, this.sizeKill / 2, true);
            mGraphics2.fillRect(XYpaint[n][0] + 2 * this.w8, XYpaint[n][1] + this.sizeKill + this.sizeKill / 2 + 1, 1, this.sizeKill / 2, true);
        } else if (this.stylePaint[n] == 2) {
            mGraphics2.fillRect(XYpaint[n][0], XYpaint[n][1] + this.sizeKill, 1, this.hKill / 2, true);
            mGraphics2.fillRect(XYpaint[n][0] - this.w8, XYpaint[n][1] + this.sizeKill + this.hKill / 2, this.w8 * 2 + 1, 1, true);
            mGraphics2.fillRect(XYpaint[n][0] - this.w8, XYpaint[n][1] + this.sizeKill + this.hKill / 2 + 1, 1, this.hKill / 2 + 4, true);
            mGraphics2.fillRect(XYpaint[n][0] + this.w8, XYpaint[n][1] + this.sizeKill + this.hKill / 2 + 1, 1, this.hKill / 2 + 4, true);
        }
    }

    public void update() {
        try {
            for (int i = 0; i < this.vecEffRe.size(); ++i) {
                MainEffect mainEffect = (MainEffect)this.vecEffRe.elementAt(i);
                mainEffect.update();
                if (!mainEffect.isStop) continue;
                this.vecEffRe.removeElement(mainEffect);
            }
            if (MainTabNew.Focus == 1) {
                if (this.listContent != null) {
                    this.listContent.moveCamera();
                }
                if (MainTabNew.timePaintInfo < MainTabNew.timeRequest + 2 && ++MainTabNew.timePaintInfo == MainTabNew.timeRequest) {
                    this.setPaintInfo();
                }
                if (GameCanvas.isTouch) {
                    this.list.moveCamera();
                } else {
                    MainScreen.cameraSub.UpdateCamera();
                    if (this.yCon < MainScreen.cameraSub.yCam + 4) {
                        this.yCon = MainScreen.cameraSub.yCam + 4;
                    }
                }
            } else {
                MainTabNew.timePaintInfo = 0;
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void updatekey() {
        super.updatekey();
        if (MainTabNew.Focus == 1) {
            int n = this.idSelect;
            if (GameCanvas.keyMyHold[2] || GameCanvas.keyMyHold[8] || GameCanvas.keyMyHold[4] || GameCanvas.keyMyHold[6]) {
                TabScreenNew.timeRepaint = 10;
                if (this.idSelect == -1) {
                    GameCanvas.clearKeyHold();
                    this.idSelect = 0;
                    return;
                }
            }
            if (this.listContent != null) {
                if (GameCanvas.keyMyHold[2]) {
                    this.listContent.cmtoX = this.listContent.cmtoX > 0 ? (this.listContent.cmtoX -= GameCanvas.hText) : 0;
                    GameCanvas.clearKeyHold(2);
                } else if (GameCanvas.keyMyHold[8]) {
                    this.listContent.cmtoX = this.listContent.cmtoX < this.listContent.cmxLim ? (this.listContent.cmtoX += GameCanvas.hText) : this.listContent.cmxLim;
                    GameCanvas.clearKeyHold(8);
                }
            } else if (GameCanvas.keyMyHold[2]) {
                this.idSelect -= 2;
                if (this.idSelect < 0) {
                    this.idSelect = 0;
                }
                TabScreenNew.timeRepaint = 10;
                GameCanvas.clearKeyHold(2);
            } else if (GameCanvas.keyMyHold[8]) {
                TabScreenNew.timeRepaint = 10;
                this.idSelect += 2;
                if (this.idSelect > vecPaintSkill.size() - 1) {
                    this.idSelect = vecPaintSkill.size() - 1;
                }
                GameCanvas.clearKeyHold(8);
            }
            if (GameCanvas.keyMyHold[4]) {
                TabScreenNew.timeRepaint = 10;
                if (this.idSelect % 2 == 1 || this.idSelect == 0) {
                    MainTabNew.Focus = 0;
                    this.idSelect = 0;
                } else {
                    --this.idSelect;
                }
                GameCanvas.clearKeyHold(4);
            } else if (GameCanvas.keyMyHold[6]) {
                TabScreenNew.timeRepaint = 10;
                if (this.idSelect < vecPaintSkill.size() - 1) {
                    ++this.idSelect;
                }
                GameCanvas.clearKeyHold(6);
            }
            if (this.idSelect < 0) {
                this.idSelect = -1;
                return;
            }
            this.idSelect = this.resetSelect(this.idSelect, vecPaintSkill.size() - 1, false);
            Skill skill = null;
            if (this.idSelect != n && this.idSelect >= 0 && this.idSelect <= vecPaintSkill.size() - 1) {
                this.listContent = null;
                this.cmdListBig();
                MainTabNew.timePaintInfo = 0;
                MainScreen.cameraSub.moveCamera(0, XYpaint[this.idSelect][1] - this.yBegin - MainTabNew.hblack / 2);
                skill = (Skill)vecPaintSkill.elementAt(this.idSelect);
            }
            if (!GameCanvas.isTouch && skill != null) {
                if (this.center == null) {
                    if (skill.lvMin <= GameScreen.player.Lv && (Player.mCurentLvSkill[skill.Id] != 0 || Player.diemKyNang > 0)) {
                        this.center = this.cmdMenu;
                    }
                } else if (skill.lvMin > GameScreen.player.Lv || Player.mCurentLvSkill[skill.Id] == 0 && Player.diemKyNang == 0) {
                    this.center = null;
                }
            }
        }
    }

    public void setPaintInfo() {
        int n;
        if (this.idSelect >= Player.mKillPlayer.length || this.idSelect == -1) {
            MainTabNew.timePaintInfo = 0;
            return;
        }
        Skill skill = (Skill)vecPaintSkill.elementAt(this.idSelect);
        if (skill == null) {
            this.mContent = null;
            this.mPlusContent = null;
            this.listContent = null;
            return;
        }
        this.name = skill.name;
        if (Player.mCurentLvSkill[skill.Id] > 0) {
            this.name = this.name + " Lv" + (Player.mCurentLvSkill[skill.Id] + Player.mPlusLvSkill[skill.Id]);
        }
        this.mContent = skill.getContent();
        this.mPlusContent = null;
        this.listContent = null;
        if (MainTabNew.longwidth > 0) {
            int n2 = this.mContent.length;
            if (n2 * GameCanvas.hText > MainTabNew.hMaxContent - this.hcmd) {
                this.listContent = new ListNew(MainTabNew.xlongwidth, MainTabNew.ylongwidth, MainTabNew.longwidth, MainTabNew.hMaxContent, 0, 0, n2 * GameCanvas.hText - (MainTabNew.hMaxContent - this.hcmd));
            } else if (GameCanvas.isTouch) {
                this.listContent = new ListNew(MainTabNew.xlongwidth, MainTabNew.ylongwidth, MainTabNew.longwidth, MainTabNew.hMaxContent - this.hcmd, 0, 0, 0);
            }
            this.cmdListBig();
            return;
        }
        int n3 = XYpaint[this.idSelect][0];
        this.xCon = n3 - 45;
        if (this.xCon < 2) {
            this.xCon = 2;
        } else if (this.xCon + 92 > GameCanvas.w) {
            this.xCon = GameCanvas.w - 92;
        }
        this.wContent = 130;
        int n4 = XYpaint[this.idSelect][1] - MainScreen.cameraSub.yCam;
        this.yCon = n4 < this.yBegin + MainTabNew.hblack / 2 ? n4 + this.sizeKill + 2 + MainScreen.cameraSub.yCam : n4 + 1 - GameCanvas.hText * (this.mContent.length + 1) - this.sizeKill / 2 + MainScreen.cameraSub.yCam;
        if (this.yCon - MainScreen.cameraSub.yCam + (this.mContent.length + 1) * GameCanvas.hText + 8 > GameCanvas.h - GameCanvas.hCommand) {
            this.yCon = GameCanvas.h - GameCanvas.hCommand - ((this.mContent.length + 1) * GameCanvas.hText + 8 - MainScreen.cameraSub.yCam);
        }
        if (this.yCon < MainScreen.cameraSub.yCam) {
            this.yCon = MainScreen.cameraSub.yCam;
        }
        if ((n = this.mContent.length) * GameCanvas.hText > MainTabNew.hMaxContent) {
            this.listContent = new ListNew(this.xCon, this.yCon, this.wContent, MainTabNew.hMaxContent, 0, 0, n * GameCanvas.hText - MainTabNew.hMaxContent);
        }
    }

    public void updatePointer() {
        Object object;
        int n;
        boolean bl = false;
        if (this.listContent != null && GameCanvas.isPoint(this.listContent.x, this.listContent.y, this.listContent.maxW, this.listContent.maxH)) {
            this.listContent.update_Pos_UP_DOWN();
            bl = true;
        }
        if (GameCanvas.isTouch && !bl) {
            this.list.update_Pos_UP_DOWN();
            MainScreen.cameraSub.yCam = this.list.cmx;
        }
        if (GameCanvas.isPointSelect(this.xBegin, this.yBegin, MainTabNew.wblack, MainTabNew.hblack) && !bl) {
            for (n = 0; n < XYpaint.length; ++n) {
                if (!GameCanvas.isPoint(XYpaint[n][0] - this.sizeKill / 2 - 2, XYpaint[n][1] - 2 - MainScreen.cameraSub.yCam, this.sizeKill + 4, this.sizeKill + 4)) continue;
                if (n != this.idSelect) {
                    this.listContent = null;
                    this.idSelect = n;
                    MainTabNew.timePaintInfo = 0;
                } else if (MainTabNew.longwidth == 0) {
                    if (this.idSelect == -1) {
                        return;
                    }
                    object = new mVector("TabSkillsNew vecmenu2");
                    object = this.doMenu();
                    if (object != null && ((mVector)object).size() > 0) {
                        GameCanvas.menu2.startAt((mVector)object, 2, T.kynang, false, null);
                    }
                }
                GameCanvas.isPointerSelect = false;
                this.cmdListBig();
                break;
            }
            if (MainTabNew.longwidth == 0) {
                MainTabNew.timePaintInfo = 0;
            }
            GameCanvas.isPointerSelect = false;
        }
        if (this.vecListCmd != null && MainTabNew.Focus == 1 && MainTabNew.timePaintInfo > MainTabNew.timeRequest) {
            for (n = 0; n < this.vecListCmd.size(); ++n) {
                object = (iCommand)this.vecListCmd.elementAt(n);
                ((iCommand)object).updatePointer();
            }
        }
        super.updatePointer();
    }

    public mVector doMenu() {
        mVector mVector2 = new mVector("TabSkillsNew menu2");
        Skill skill = null;
        skill = (Skill)vecPaintSkill.elementAt(this.idSelect);
        if (this.idSelect < 0 && this.idSelect >= vecPaintSkill.size()) {
            return mVector2;
        }
        if (Player.diemKyNang > 0 && this.idSelect > 0 && Player.mCurentLvSkill[skill.Id] < 10 && GameScreen.player.Lv >= skill.lvMin) {
            mVector2.addElement(this.cmdSetPoint);
        }
        if (skill.typeSkill != 2 && Player.mCurentLvSkill[skill.Id] > 0) {
            if (mVector2.size() == 0 && MainTabNew.longwidth == 0) {
                this.cmdSetKey.perform();
                return null;
            }
            mVector2.addElement(this.cmdSetKey);
        }
        mVector2.addElement(this.cmdupskill);
        this.hcmd = (mVector2.size() + 1) / 2 * iCommand.hButtonCmd;
        return mVector2;
    }

    public static void saveSkill() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        try {
            for (int i = 0; i < Player.mhotkey.length; ++i) {
                for (int j = 0; j < Player.mhotkey[0].length; ++j) {
                    HotKey hotKey = Player.mhotkey[i][j];
                    dataOutputStream.writeShort(hotKey.id);
                    dataOutputStream.writeByte(hotKey.type);
                    dataOutputStream.writeByte(hotKey.typePotion);
                }
            }
            CRes.saveRMSName((byte)0, byteArrayOutputStream.toByteArray());
            dataOutputStream.close();
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public void cmdListBig() {
        if (MainTabNew.longwidth > 0 && GameCanvas.isTouch && this.idSelect >= 0) {
            this.vecListCmd = this.doMenu();
            this.setPosCmd(this.vecListCmd);
        }
    }

    public static void loadSkill(byte[] byArray) {
        block10: {
            if (byArray == null) {
                for (int i = 0; i < Player.mhotkey[0].length; ++i) {
                    Player.mhotkey[0][i] = new HotKey();
                    Player.mhotkey[1][i] = new HotKey();
                    Player.mhotkey[2][i] = new HotKey();
                    if (i == 2) {
                        Player.mhotkey[0][i].setHotKey(0, HotKey.SKILL, (byte)0);
                        Player.mhotkey[1][i].setHotKey(0, HotKey.SKILL, (byte)0);
                        Player.mhotkey[2][i].setHotKey(0, HotKey.SKILL, (byte)0);
                        continue;
                    }
                    Player.mhotkey[0][i].setHotKey(0, HotKey.NULL, (byte)0);
                    Player.mhotkey[1][i].setHotKey(0, HotKey.NULL, (byte)0);
                    Player.mhotkey[2][i].setHotKey(0, HotKey.NULL, (byte)0);
                }
                if (GameScreen.player.Lv > 1) {
                    MainItem.setAddHotKey((byte)1, false);
                    MainItem.setAddHotKey((byte)0, false);
                }
                return;
            }
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byArray);
            DataInputStream dataInputStream = new DataInputStream(byteArrayInputStream);
            try {
                for (int i = 0; i < Player.mhotkey.length; ++i) {
                    for (int j = 0; j < Player.mhotkey[0].length; ++j) {
                        Player.mhotkey[i][j] = new HotKey();
                        Player.mhotkey[i][j].setHotKey(dataInputStream.readShort(), dataInputStream.readByte(), dataInputStream.readByte());
                    }
                }
            }
            catch (Exception exception) {
                for (int i = 0; i < Player.mhotkey[0].length; ++i) {
                    Player.mhotkey[0][i] = new HotKey();
                    Player.mhotkey[1][i] = new HotKey();
                    Player.mhotkey[2][i] = new HotKey();
                    if (i == 2) {
                        Player.mhotkey[0][i].setHotKey(0, HotKey.SKILL, (byte)0);
                        Player.mhotkey[1][i].setHotKey(0, HotKey.SKILL, (byte)0);
                        Player.mhotkey[2][i].setHotKey(0, HotKey.SKILL, (byte)0);
                        continue;
                    }
                    Player.mhotkey[0][i].setHotKey(0, HotKey.NULL, (byte)0);
                    Player.mhotkey[1][i].setHotKey(0, HotKey.NULL, (byte)0);
                    Player.mhotkey[2][i].setHotKey(0, HotKey.NULL, (byte)0);
                }
                if (GameScreen.player.Lv <= 1) break block10;
                MainItem.setAddHotKey((byte)1, false);
                MainItem.setAddHotKey((byte)0, false);
            }
        }
    }

    static {
        vecPaintSkill = new mVector("TabSkillsNew vecPaintSkill");
    }
}

