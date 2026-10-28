/*
 * Decompiled with CFR 0.152.
 */
package InterfaceComponents;

import CLib.mFont;
import CLib.mGraphics;
import CLib.mSystem;
import CLib.mVector;
import GameObjects.CatalogyMonster;
import GameObjects.MainMonster;
import GameObjects.MainQuest;
import GameScreen.MainScreen;
import GameScreen.MapScr;
import GameScreen.PaintInfoGameScreen;
import GameScreen.TabScreenNew;
import InterfaceComponents.ListNew;
import InterfaceComponents.MainTabNew;
import InterfaceComponents.Scroll;
import InterfaceComponents.ScrollResult;
import InterfaceComponents.iCommand;
import Main.GameCanvas;
import Model.AvMain;
import Model.T;
import netcommand.global.GlobalService;

public class TabQuest
extends MainTabNew {
    Scroll scr = new Scroll();
    int idSelect;
    int maxSize;
    int hmax;
    static TabQuest me;
    int xdich;
    int wMainTab;
    int xselect;
    int HQuest;
    int tabSelect = 0;
    iCommand cmdView;
    iCommand cmdRead;
    iCommand cmdBack;
    iCommand cmdCancle;
    int xMap;
    int yMap;
    public static String[] nameItemQuest;
    mVector vecListCmd = new mVector("TabQuest vecListCmd");
    MainQuest questCurrent;
    boolean isTran = false;
    int yCambegin = 0;

    public TabQuest(String string) {
        this.typeTab = (byte)4;
        me = this;
        this.nameTab = "";
        this.HQuest = GameCanvas.hText;
        if (GameCanvas.isTouch) {
            this.HQuest = GameCanvas.hCommand;
        }
        this.xBegin = this.xTab + MainTabNew.wOneItem + MainTabNew.wOne5 * 3;
        this.yBegin = this.yTab + GameCanvas.h / 5 + MainTabNew.wOneItem;
        this.init();
        this.cmdView = new iCommand(T.view, 0, this);
        this.cmdRead = new iCommand(T.read, 1, this);
        this.cmdBack = new iCommand(T.back, -1, this);
        this.cmdCancle = new iCommand(T.cancel, 2, this);
        int n = MainTabNew.ylongwidth + this.hSmall;
        int n2 = MainTabNew.xlongwidth;
        this.cmdCancle.setPos(n2 + MainTabNew.longwidth / 2, n - 15, null, this.cmdCancle.caption);
        if (MainTabNew.is320) {
            this.cmdCancle.setPos(this.cmdCancle.xCmd, n - 10, PaintInfoGameScreen.fraButton2, this.cmdCancle.caption);
        }
        if (GameCanvas.isTouch) {
            this.cmdBack.caption = T.close;
        }
    }

    public void init() {
        this.resetTab(true);
        this.xdich = (MainTabNew.wblack - this.wMainTab * 2) / 2;
        if (!GameCanvas.isTouch) {
            this.right = this.cmdBack;
        }
        this.name = null;
        this.mContent = null;
        this.mPlusContent = null;
        this.questCurrent = null;
        this.listContent = null;
        super.init();
    }

    public void backTab() {
        MainTabNew.Focus = 0;
        this.idSelect = 0;
        super.backTab();
    }

    public void commandPointer(int n, int n2) {
        switch (n) {
            case 0: {
                MapScr.gI().Show();
                break;
            }
            case 1: {
                MainQuest mainQuest = this.QuestSelect();
                if (mainQuest == null) break;
                GameCanvas.start_Quest_DialogRead(mainQuest);
                break;
            }
            case -1: {
                this.backTab();
                break;
            }
            case 2: {
                if (this.questCurrent == null) {
                    return;
                }
                GameCanvas.start_Left_Dialog(T.hoihuyQuest + this.questCurrent.name, new iCommand(T.cancel, 3, this));
                break;
            }
            case 3: {
                if (this.questCurrent == null) {
                    return;
                }
                GlobalService.gI().quest((short)this.questCurrent.ID, (byte)(!this.questCurrent.isMain ? 1 : 0), (byte)2);
                mSystem.println("huy nhiem vu ");
                this.resetTab(true);
                GameCanvas.end_Dialog();
            }
        }
        super.commandPointer(n, n2);
    }

    public MainQuest QuestSelect() {
        MainQuest mainQuest;
        block12: {
            int n;
            block11: {
                mainQuest = null;
                n = MainQuest.vecQuestDoing_Main.size();
                if (this.idSelect < 0 || this.idSelect >= n + MainQuest.vecQuestDoing_Sub.size()) {
                    return null;
                }
                if (this.idSelect >= n) break block11;
                mainQuest = (MainQuest)MainQuest.vecQuestDoing_Main.elementAt(this.idSelect);
                mainQuest.strShowDialog = mainQuest.strShortDetail;
                if (mainQuest.typeQuest == 0 || mainQuest.typeQuest == 1) {
                    mainQuest.strShowDialog = mainQuest.strShowDialog + "\n " + T.mucdohoanthanh;
                    for (int i = 0; i < mainQuest.mIdQuest.length; ++i) {
                        String string = "";
                        if (mainQuest.typeQuest == 1) {
                            CatalogyMonster catalogyMonster = MainMonster.getCatalogyMonster(mainQuest.mIdQuest[i]);
                            if (catalogyMonster != null) {
                                string = catalogyMonster.name;
                            }
                        } else if (nameItemQuest != null) {
                            string = nameItemQuest[mainQuest.mIdQuest[i]];
                        }
                        mainQuest.strShowDialog = mainQuest.strShowDialog + "\n " + string + (string.equals("") ? "" : ": ") + mainQuest.mQuestGot[i] + "/" + mainQuest.mtotalQuest[i];
                    }
                }
                break block12;
            }
            mainQuest = (MainQuest)MainQuest.vecQuestDoing_Sub.elementAt(this.idSelect - n);
            mainQuest.strShowDialog = mainQuest.strShortDetail;
            if (mainQuest.typeQuest != 0 && mainQuest.typeQuest != 1) break block12;
            mainQuest.strShowDialog = mainQuest.strShowDialog + "\n " + T.mucdohoanthanh;
            for (int i = 0; i < mainQuest.mIdQuest.length; ++i) {
                String string = "";
                if (mainQuest.typeQuest == 1) {
                    CatalogyMonster catalogyMonster;
                    string = "";
                    if (MainMonster.getCatalogyMonster(mainQuest.mIdQuest[i]) != null && (catalogyMonster = MainMonster.getCatalogyMonster(mainQuest.mIdQuest[i])) != null) {
                        string = catalogyMonster.name;
                    }
                } else {
                    string = nameItemQuest[mainQuest.mIdQuest[i]];
                }
                mainQuest.strShowDialog = mainQuest.strShowDialog + "\n " + string + (string.equals("") ? "" : ": ") + mainQuest.mQuestGot[i] + "/" + mainQuest.mtotalQuest[i];
            }
        }
        return mainQuest;
    }

    public void setPaint() {
        if (MainTabNew.longwidth == 0) {
            return;
        }
        this.vecListCmd.removeAllElements();
        this.questCurrent = this.QuestSelect();
        if (this.questCurrent != null) {
            this.name = this.questCurrent.name;
            this.mContent = mFont.tahoma_7_white.splitFontArray(this.questCurrent.strShowDialog, MainTabNew.longwidth - 8);
            int n = this.questCurrent.strShowDialog.length() * GameCanvas.hText - (MainTabNew.hMaxContent - iCommand.hButtonCmd * 2);
            if (n < 0) {
                n = 0;
            }
            this.listContent = new ListNew(MainTabNew.xlongwidth, MainTabNew.ylongwidth, MainTabNew.longwidth, MainTabNew.hMaxContent, 0, 0, n);
            if (GameCanvas.isTouch) {
                this.vecListCmd.addElement(this.cmdCancle);
            }
            this.mPlusContent = null;
        } else {
            this.name = null;
            this.mContent = null;
            this.mPlusContent = null;
            this.questCurrent = null;
        }
    }

    public void paint(mGraphics mGraphics2) {
        Object object;
        int n;
        int n2 = this.xTab + MainTabNew.wOneItem + MainTabNew.wOne5 * 3 + this.xdich;
        int n3 = this.yTab + GameCanvas.h / 5 + MainTabNew.wOne5;
        if (this.tabSelect == 0) {
            this.paintTabBig(mGraphics2, n2 + this.wMainTab, n3 - 1, this.wMainTab);
            mFont.tahoma_7_white.drawString(mGraphics2, T.mQuest[1], n2 + this.wMainTab + this.wMainTab / 2, n3 + MainTabNew.wOneItem / 2 - 7, 2, false);
        } else {
            this.paintTabBig(mGraphics2, n2, n3 - 1, this.wMainTab);
            mFont.tahoma_7_white.drawString(mGraphics2, T.mQuest[0], n2 + this.wMainTab / 2, n3 + MainTabNew.wOneItem / 2 - 7, 2, false);
            n2 += this.wMainTab;
        }
        this.xselect = n2;
        this.paintTabFocus(mGraphics2, this.xselect, n3 - 1, this.wMainTab);
        mFont mFont2 = mFont.tahoma_7b_white;
        if (MainTabNew.Focus == 0) {
            mFont2 = mFont.tahoma_7_white;
        }
        mFont2.drawString(mGraphics2, T.mQuest[this.tabSelect], this.xselect + this.wMainTab / 2, n3 + MainTabNew.wOneItem / 2 - 7, 2, false);
        mGraphics2.setClip(this.xBegin, this.yBegin, MainTabNew.wblack, MainTabNew.hblack);
        mGraphics2.translate(0, -MainScreen.cameraSub.yCam);
        n3 = this.yBegin + 4;
        n2 = this.xBegin + 4;
        int n4 = 0;
        if (this.maxSize == 0) {
            this.paintRong(mGraphics2, n2 + MainTabNew.wblack / 2 - 4, n3 + MainTabNew.wOne5);
            n3 += this.HQuest;
        } else if (this.tabSelect == 1) {
            for (n = 0; n < MainQuest.vecQuestFinish.size(); ++n) {
                if (MainTabNew.Focus == 1 && this.idSelect == n) {
                    mGraphics2.drawRegion(AvMain.imgSelect, 0, 0, 12, 16, 4, n2 - GameCanvas.gameTick % 3, n3, 0, true);
                }
                object = (MainQuest)MainQuest.vecQuestFinish.elementAt(n);
                mFont.tahoma_7b_white.drawString(mGraphics2, ((MainQuest)object).name, n2 + 18, n3, 0, true);
                if (MainTabNew.Focus == 1 && this.idSelect == n) {
                    for (int i = 0; i < ((MainQuest)object).mstrHelp.length; ++i) {
                        mFont.tahoma_7_white.drawString(mGraphics2, ((MainQuest)object).mstrHelp[i], n2 + 25, n3 += GameCanvas.hText, 0, true);
                    }
                }
                n3 += this.HQuest;
                ++n4;
            }
        } else {
            if (MainQuest.vecQuestDoing_Main.size() > 0) {
                AvMain.Font3dWhite(mGraphics2, T.mainQuest, n2, n3, 0);
                n3 += this.HQuest;
                for (n = 0; n < MainQuest.vecQuestDoing_Main.size(); ++n) {
                    if (MainTabNew.Focus == 1 && this.idSelect == n4) {
                        mGraphics2.drawRegion(AvMain.imgSelect, 0, 0, 12, 16, 4, n2 - GameCanvas.gameTick % 3, n3, 0, false);
                    }
                    object = (MainQuest)MainQuest.vecQuestDoing_Main.elementAt(n);
                    mFont.tahoma_7b_white.drawString(mGraphics2, ((MainQuest)object).name, n2 + 18, n3, 0, false);
                    n3 += this.HQuest;
                    ++n4;
                }
            }
            if (MainQuest.vecQuestDoing_Sub.size() > 0) {
                AvMain.Font3dWhite(mGraphics2, T.subQuest, n2, n3, 0);
                n3 += this.HQuest;
                for (n = 0; n < MainQuest.vecQuestDoing_Sub.size(); ++n) {
                    object = (MainQuest)MainQuest.vecQuestDoing_Sub.elementAt(n);
                    if (MainTabNew.Focus == 1 && this.idSelect == n4) {
                        mGraphics2.drawRegion(AvMain.imgSelect, 0, 0, 12, 16, 4, n2 - GameCanvas.gameTick % 3, n3, 0, false);
                    }
                    mFont.tahoma_7b_white.drawString(mGraphics2, ((MainQuest)object).name, n2 + 18, n3, 0, false);
                    n3 += this.HQuest;
                    ++n4;
                }
            }
        }
        n3 += this.HQuest / 2;
        if (MainTabNew.Focus == 1 && this.idSelect == n4) {
            mGraphics2.setColor(MainTabNew.color[3]);
            mGraphics2.fillRect(this.xBegin + MainTabNew.wblack / 2 - 49, n3 - 1, 98, 18, false);
        }
        this.xMap = this.xBegin + MainTabNew.wblack / 2 - 48;
        this.yMap = n3;
        if (GameCanvas.lowGraphic) {
            MainTabNew.paintRectLowGraphic(mGraphics2, this.xMap, this.yMap, 96, 16, 4);
        } else {
            for (n = 0; n < 4; ++n) {
                mGraphics2.drawRegion(MainTabNew.imgTab[8], 0, 0, 24, 16, 0, this.xMap + 24 * n, this.yMap, 0, false);
            }
        }
        mFont.tahoma_7_white.drawString(mGraphics2, T.viewMap, this.xBegin + MainTabNew.wblack / 2, n3 + 2, 2, false);
        if (MainTabNew.longwidth > 0 && MainTabNew.Focus == 1 && this.name != null) {
            this.paintInfoQuest(mGraphics2);
            if (this.vecListCmd != null) {
                for (n = 0; n < this.vecListCmd.size(); ++n) {
                    object = (iCommand)this.vecListCmd.elementAt(n);
                    ((iCommand)object).paint(mGraphics2, ((iCommand)object).xCmd, ((iCommand)object).yCmd);
                }
            }
        }
        mGraphics2.endClip();
    }

    public void paintInfoQuest(mGraphics mGraphics2) {
        int n;
        int n2 = 4;
        TabScreenNew.timeRepaint = 10;
        GameCanvas.resetTrans(mGraphics2);
        int n3 = MainTabNew.xlongwidth;
        int n4 = n = MainTabNew.ylongwidth;
        MainTabNew.paintNameItem(mGraphics2, n3 + MainTabNew.longwidth / 2, n4 + MainTabNew.wOneItem / 2 - 5, MainTabNew.longwidth, this.name, this.colorName);
        mGraphics2.setClip(n3 + 1, n4 + 1, MainTabNew.longwidth - 2, this.hSmall - 2);
        mGraphics2.saveCanvas();
        mGraphics2.ClipRec(n3, n4 + MainTabNew.wOneItem + 2 - GameCanvas.hText + (MainTabNew.wOneItem - GameCanvas.hText + GameCanvas.hText / 4), MainTabNew.longwidth, MainTabNew.hMaxContent - MainTabNew.wOneItem - 2 - iCommand.hButtonCmd / 2);
        GameCanvas.resetTrans(mGraphics2);
        this.scr.setStyle(this.mContent.length + 2, GameCanvas.hText + 2, n3, (n4 += MainTabNew.wOneItem - GameCanvas.hText + GameCanvas.hText / 4) + MainTabNew.wOneItem + 2 - GameCanvas.hText * 2, MainTabNew.longwidth, MainTabNew.hMaxContent + GameCanvas.hText, true, 1);
        this.scr.setClip(mGraphics2, n3, n4 + MainTabNew.wOneItem + 2 - GameCanvas.hText, MainTabNew.longwidth, MainTabNew.hMaxContent - MainTabNew.wOneItem - 2 - iCommand.hButtonCmd / 2);
        if (this.mContent != null) {
            for (int i = 0; i < this.mContent.length; ++i) {
                if (this.mContent[i] == null) continue;
                mFont mFont2 = null;
                mFont2 = this.mcolor != null ? MainTabNew.setTextColor(this.mcolor[i]) : mFont.tahoma_7_white;
                mFont2.drawString(mGraphics2, this.mContent[i], n3 + n2, n4 + 2 + (i + 1) * GameCanvas.hText, 0, true);
                if (this.mSubContent == null) continue;
                int n5 = mFont2.getWidth(this.mContent[i]) + 5;
                mFont2 = MainTabNew.setTextColor(this.mSubColor[i]);
                mFont2.drawString(mGraphics2, this.mSubContent[i], n3 + n5 + n2, n4 + 2 + (i + 1) * GameCanvas.hText, 0, true);
            }
        }
        mGraphics.resetTransAndroid(mGraphics2);
        mGraphics2.restoreCanvas();
        mGraphics2.endClip();
        GameCanvas.resetTrans(mGraphics2);
    }

    public void paintTabFocus(mGraphics mGraphics2, int n, int n2, int n3) {
        if (GameCanvas.lowGraphic) {
            MainTabNew.paintRectLowGraphic(mGraphics2, n, n2, n3, 32, 2);
        } else {
            for (int i = 0; i <= n3 / 32; ++i) {
                if (i == 0) {
                    mGraphics2.drawImage(MainTabNew.imgTab[9], n, n2, 0, false);
                    continue;
                }
                if (i == n3 / 32) {
                    mGraphics2.drawRegion(MainTabNew.imgTab[9], 0, 0, 32, 32, 2, n + n3 - 32, n2, 0, false);
                    continue;
                }
                mGraphics2.drawImage(MainTabNew.imgTab[2], n + i * 32, n2, 0, false);
            }
        }
    }

    public void paintTabBig(mGraphics mGraphics2, int n, int n2, int n3) {
        if (GameCanvas.lowGraphic) {
            MainTabNew.paintRectLowGraphic(mGraphics2, n, n2, n3, MainTabNew.wOneItem - MainTabNew.wOne5 + 1, 3);
        } else {
            for (int i = 0; i <= n3 / 32; ++i) {
                if (i == 0) {
                    mGraphics2.drawRegion(MainTabNew.imgTab[11], 0, 0, 32, MainTabNew.wOneItem - MainTabNew.wOne5 + 1, 0, n, n2, 0, false);
                    continue;
                }
                if (i == n3 / 32) {
                    mGraphics2.drawRegion(MainTabNew.imgTab[11], 0, 0, 32, MainTabNew.wOneItem - MainTabNew.wOne5 + 1, 2, n + n3 - 32, n2, 0, false);
                    continue;
                }
                mGraphics2.drawRegion(MainTabNew.imgTab[10], 0, 0, 32, MainTabNew.wOneItem - MainTabNew.wOne5 + 1, 0, n + i * 32, n2, 0, false);
            }
        }
    }

    public void update() {
        ScrollResult scrollResult = this.scr.updateKey();
        if (scrollResult.isDowning || scrollResult.isFinish) {
            // empty if block
        }
        if (scrollResult.isFinish || GameCanvas.isKeyPressed(5)) {
            // empty if block
        }
        this.scr.updatecm();
        MainScreen.cameraSub.UpdateCamera();
        super.update();
    }

    public void updatekey() {
        if (MainTabNew.Focus == 1) {
            int n = this.idSelect--;
            if (GameCanvas.keyMyHold[2]) {
                GameCanvas.clearKeyHold(2);
            } else if (GameCanvas.keyMyHold[8]) {
                ++this.idSelect;
                GameCanvas.clearKeyHold(8);
            }
            if (GameCanvas.keyMyHold[4]) {
                if (this.tabSelect == 0) {
                    MainTabNew.Focus = 0;
                    this.idSelect = 0;
                } else {
                    this.tabSelect = 0;
                    this.resetTab(true);
                }
                GameCanvas.clearKeyHold(4);
            } else if (GameCanvas.keyMyHold[6]) {
                if (this.tabSelect == 1) {
                    this.tabSelect = 0;
                    this.resetTab(true);
                } else {
                    this.tabSelect = 1;
                    this.resetTab(true);
                }
                GameCanvas.clearKeyHold(6);
            }
            this.idSelect = this.maxSize > 0 ? this.resetSelect(this.idSelect, this.maxSize, true) : 0;
            this.setPaint();
            if (this.tabSelect == 1) {
                if (!GameCanvas.isTouch) {
                    if (this.idSelect < this.maxSize) {
                        if (this.center != null) {
                            this.center = null;
                        }
                    } else if (this.center != this.cmdView) {
                        this.center = this.cmdView;
                        TabScreenNew.timeRepaint = 10;
                    }
                    if (this.left != null) {
                        this.left = null;
                    }
                }
                if (n != this.idSelect) {
                    this.resetTab(false);
                    if (this.idSelect == this.maxSize) {
                        MainScreen.cameraSub.moveCamera(0, MainScreen.cameraSub.yLimit);
                    } else {
                        MainQuest mainQuest = (MainQuest)MainQuest.vecQuestFinish.elementAt(this.idSelect);
                        MainScreen.cameraSub.moveCamera(0, this.idSelect * this.HQuest + mainQuest.mstrHelp.length * GameCanvas.hText - MainTabNew.hblack / 2);
                    }
                }
            } else if (this.idSelect < this.maxSize) {
                if (!GameCanvas.isTouch) {
                    if (this.center != this.cmdRead) {
                        this.center = this.cmdRead;
                        TabScreenNew.timeRepaint = 10;
                    }
                    if (n != this.idSelect) {
                        MainScreen.cameraSub.moveCamera(0, (this.idSelect + 2) * this.HQuest - MainTabNew.hblack / 2);
                    }
                }
            } else if (this.center != this.cmdView && !GameCanvas.isTouch) {
                this.center = this.cmdView;
                TabScreenNew.timeRepaint = 10;
                if (n != this.idSelect) {
                    MainScreen.cameraSub.moveCamera(0, MainScreen.cameraSub.yLimit);
                }
                if (this.left != null) {
                    this.left = null;
                }
            }
        }
        super.updatekey();
    }

    public void resetTab(boolean bl) {
        TabScreenNew.timeRepaint = 10;
        int n = 0;
        if (this.tabSelect == 0) {
            this.maxSize = MainQuest.vecQuestDoing_Main.size() + MainQuest.vecQuestDoing_Sub.size();
            n = this.maxSize + (MainQuest.vecQuestDoing_Main.size() > 0 ? 1 : 0) + (MainQuest.vecQuestDoing_Sub.size() > 0 ? 1 : 0) + 2;
        } else {
            this.maxSize = MainQuest.vecQuestFinish.size();
            if (this.idSelect < this.maxSize) {
                MainQuest mainQuest = (MainQuest)MainQuest.vecQuestFinish.elementAt(this.idSelect);
                n = this.maxSize + 2 + mainQuest.mstrHelp.length;
            } else {
                n = this.maxSize + 2;
            }
        }
        this.hmax = n * this.HQuest - MainTabNew.hblack + 5;
        if (this.hmax < 0) {
            this.hmax = 0;
        }
        this.wMainTab = MainTabNew.wOneItem * 3;
        if (bl) {
            MainScreen.cameraSub.setAll(0, this.hmax, 0, 0);
        } else {
            MainScreen.cameraSub.yLimit = this.hmax;
        }
    }

    private void paintRong(mGraphics mGraphics2, int n, int n2) {
        mFont.tahoma_7_white.drawString(mGraphics2, T.khongconhiemvu, n, n2, 2, false);
    }

    public void updatePointer() {
        int n;
        int n2;
        if (GameCanvas.isPointerSelect) {
            n2 = this.xTab + MainTabNew.wOneItem + MainTabNew.wOne5 * 3 + this.xdich;
            n = this.yTab + GameCanvas.h / 5 + MainTabNew.wOne5;
            if (GameCanvas.isPoint(n2, n, this.wMainTab, MainTabNew.wOneItem - MainTabNew.wOne5 + 1)) {
                if (this.tabSelect != 0) {
                    this.tabSelect = 0;
                    this.resetTab(true);
                }
                GameCanvas.isPointerSelect = false;
            } else if (GameCanvas.isPoint(n2 + this.wMainTab, n, this.wMainTab, MainTabNew.wOneItem - MainTabNew.wOne5 + 1)) {
                if (this.tabSelect != 1) {
                    this.tabSelect = 1;
                    this.resetTab(true);
                }
                GameCanvas.isPointerSelect = false;
            }
        }
        if (GameCanvas.isPointerSelect && GameCanvas.isPoint(this.xMap, this.yMap - MainScreen.cameraSub.yCam - 4, 96, 24)) {
            GameCanvas.isPointerSelect = false;
            this.cmdView.perform();
        }
        if (this.maxSize > 0) {
            if (GameCanvas.isPointSelect(this.xBegin, this.yBegin, MainTabNew.wblack, MainTabNew.hblack)) {
                if (this.tabSelect == 0) {
                    n2 = this.yBegin + 4 + this.HQuest;
                    n = (GameCanvas.py - n2 + MainScreen.cameraSub.yCam) / this.HQuest;
                    if (MainQuest.vecQuestDoing_Main.size() > 0 && MainQuest.vecQuestDoing_Sub.size() > 0) {
                        if (n == MainQuest.vecQuestDoing_Main.size()) {
                            n = -1;
                        } else if (n > MainQuest.vecQuestDoing_Main.size()) {
                            --n;
                        }
                    }
                    if (n > -1 && n <= this.maxSize) {
                        if (n == this.idSelect) {
                            if (MainTabNew.longwidth == 0 && this.idSelect < this.maxSize) {
                                this.cmdRead.perform();
                            }
                        } else {
                            this.idSelect = n;
                            this.idSelect = this.resetSelect(this.idSelect, this.maxSize, false);
                        }
                        GameCanvas.isPointerSelect = false;
                    }
                    this.setPaint();
                } else {
                    n2 = (GameCanvas.py - this.yBegin + MainScreen.cameraSub.yCam) / this.HQuest;
                    if (n2 < this.idSelect) {
                        this.idSelect = n2;
                    } else if (this.idSelect < this.maxSize) {
                        MainQuest mainQuest = (MainQuest)MainQuest.vecQuestFinish.elementAt(this.idSelect);
                        int n3 = this.yBegin + this.idSelect * this.HQuest + mainQuest.mstrHelp.length * GameCanvas.hText;
                        if (n3 < GameCanvas.py + MainScreen.cameraSub.yCam) {
                            this.idSelect = n2 = (GameCanvas.py + MainScreen.cameraSub.yCam - n3) / this.HQuest + this.idSelect;
                            this.idSelect = this.resetSelect(this.idSelect, this.maxSize, false);
                            GameCanvas.isPointerSelect = false;
                        }
                    }
                    this.setPaint();
                }
            }
            if (GameCanvas.isPointerMove) {
                if (this.hmax > 0 && GameCanvas.pxLast > this.xBegin && GameCanvas.pxLast < this.xBegin + MainTabNew.wblack && GameCanvas.pyLast > this.yBegin && GameCanvas.pyLast < this.yBegin + MainTabNew.hblack) {
                    if (!this.isTran) {
                        this.isTran = true;
                        this.yCambegin = MainScreen.cameraSub.yCam;
                    } else {
                        MainScreen.cameraSub.yTo = this.yCambegin + GameCanvas.pyLast - GameCanvas.py;
                        if (MainScreen.cameraSub.yTo > MainScreen.cameraSub.yLimit) {
                            MainScreen.cameraSub.yTo = MainScreen.cameraSub.yLimit;
                        }
                        if (MainScreen.cameraSub.yTo < 0) {
                            MainScreen.cameraSub.yTo = 0;
                        }
                        TabScreenNew.timeRepaint = 10;
                    }
                }
            } else {
                this.isTran = false;
                this.yCambegin = 0;
            }
            if (this.vecListCmd != null && MainTabNew.Focus == 1 && this.name != null) {
                for (n2 = 0; n2 < this.vecListCmd.size(); ++n2) {
                    iCommand iCommand2 = (iCommand)this.vecListCmd.elementAt(n2);
                    iCommand2.updatePointer();
                }
            }
        }
        super.updatePointer();
    }

    static {
        nameItemQuest = null;
    }
}

