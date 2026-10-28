/*
 * Decompiled with CFR 0.152.
 */
package InterfaceComponents;

import CLib.mFont;
import CLib.mGraphics;
import CLib.mSystem;
import CLib.mVector;
import GameObjects.Item;
import GameObjects.Player;
import GameScreen.MainScreen;
import GameScreen.TabScreenNew;
import InterfaceComponents.ListNew;
import InterfaceComponents.MainTabNew;
import InterfaceComponents.iCommand;
import Main.GameCanvas;
import Model.AvMain;
import Model.T;

public class TabEvent
extends MainTabNew {
    public static int[][] XYpaint;
    int sizeKill = 22;
    int idSelect;
    int hcmd;
    iCommand cmdNhanthuong;
    ListNew list;
    mVector vecListCmd;
    public static mVector vecItem;
    boolean isSaveSkill = false;
    public int MaxItem = 21;
    int[] stylePaint = new int[]{1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1, -1};
    int hKill = 4;
    int w8;

    public TabEvent(String string) {
        int n;
        this.MaxItem = Item.VecInvetoryPlayer.size();
        this.typeTab = (byte)-1;
        this.nameTab = string;
        this.xBegin = this.xTab + MainTabNew.wOneItem + MainTabNew.wOne5 * 3 + MainTabNew.wblack % 8 / 2;
        this.yBegin = this.yTab + GameCanvas.h / 5 + MainTabNew.wOneItem;
        XYpaint = mSystem.new_M_Int(this.MaxItem, 2);
        int n2 = this.yBegin + MainTabNew.wOneItem / 2 + GameCanvas.hText;
        for (n = 0; n < this.MaxItem; ++n) {
            if (n == 0) {
                TabEvent.XYpaint[n][0] = this.xBegin + MainTabNew.wblack / 2;
                TabEvent.XYpaint[n][1] = n2;
                n2 += this.sizeKill * 2;
                continue;
            }
            TabEvent.XYpaint[n][1] = n2;
            if (n % 2 == 1) {
                TabEvent.XYpaint[n][0] = this.xBegin + MainTabNew.wblack / 4;
                continue;
            }
            TabEvent.XYpaint[n][0] = this.xBegin + MainTabNew.wblack / 4 * 3;
            n2 += this.sizeKill * 2;
        }
        this.cmdBack = new iCommand(T.close, -1, this);
        this.cmdNhanthuong = new iCommand(T.nhan, 6, this);
        this.w8 = MainTabNew.wblack / 8;
        vecItem = Item.VecInvetoryPlayer;
        this.init();
        if (!GameCanvas.isTouch) {
            this.center = this.cmdNhanthuong;
        }
        this.stylePaint = new int[this.MaxItem];
        for (n = 0; n < this.MaxItem; ++n) {
            this.stylePaint[n] = 0;
        }
        this.stylePaint[0] = 1;
        this.stylePaint[this.MaxItem - 1] = -1;
        this.stylePaint[this.MaxItem - 2] = -1;
    }

    public void init() {
        MainTabNew.timePaintInfo = 0;
        int n = XYpaint[this.MaxItem - 1][1] - this.yBegin + this.sizeKill - MainTabNew.hblack + 5;
        MainScreen.cameraSub.setAll(0, n, 0, 0);
        this.list = new ListNew(this.xBegin, this.yBegin + GameCanvas.hText + 2, MainTabNew.wblack, MainTabNew.hblack - GameCanvas.hText - 2, 0, 0, MainScreen.cameraSub.yLimit);
        if (!GameCanvas.isTouch) {
            this.right = this.cmdBack;
        }
        this.idSelect = GameCanvas.isTouch ? -1 : 0;
        this.listContent = null;
    }

    public void backTab() {
        MainTabNew.timePaintInfo = 0;
        this.idSelect = GameCanvas.isTouch ? -1 : 0;
        super.backTab();
    }

    public void commandPointer(int n, int n2) {
        switch (n) {
            case -1: {
                this.backTab();
                break;
            }
            case 6: {
                mSystem.println("nh\u1eadn " + this.idSelect);
            }
        }
        super.commandPointer(n, n2);
    }

    public void paint(mGraphics mGraphics2) {
        int n;
        mFont.tahoma_7_white.drawString(mGraphics2, T.diemkynang + Player.diemKyNang, this.xBegin + 2, this.yBegin + 3, 0, true);
        if ((!GameCanvas.menu2.isShowMenu && GameCanvas.currentDialog == null || MainTabNew.longwidth > 0) && MainTabNew.timePaintInfo > MainTabNew.timeRequest && this.vecListCmd != null) {
            for (n = 0; n < this.vecListCmd.size(); ++n) {
                iCommand iCommand2 = (iCommand)this.vecListCmd.elementAt(n);
                iCommand2.paint(mGraphics2, iCommand2.xCmd, iCommand2.yCmd);
            }
        }
        mGraphics2.setClip(this.xBegin, this.yBegin + GameCanvas.hText + 2, MainTabNew.wblack, MainTabNew.hblack - 2 - GameCanvas.hText);
        mGraphics2.saveCanvas();
        mGraphics2.ClipRec(this.xBegin, this.yBegin + GameCanvas.hText + 2, MainTabNew.wblack, MainTabNew.hblack - 2 - GameCanvas.hText);
        mGraphics2.translate(0, -MainScreen.cameraSub.yCam);
        n = this.MaxItem;
        for (int i = 0; i < n; ++i) {
            this.paintLine(mGraphics2, i);
            mGraphics2.setColor(-8688548);
            mGraphics2.fillRect(XYpaint[i][0] - this.sizeKill / 2 - 2 + 2, XYpaint[i][1] - 2 + 2, 22, 22, true);
            Item item = (Item)vecItem.elementAt(i);
            if (item != null) {
                item.paintItem(mGraphics2, XYpaint[i][0] - this.sizeKill / 2 - 2 + 13, XYpaint[i][1] - 2 + 13, 22, 0, 0);
            }
            mGraphics2.drawImage(AvMain.imgHotKey, XYpaint[i][0] - this.sizeKill / 2 - 2 + 2, XYpaint[i][1] - 2 + 2, 0, true);
            if (i != this.idSelect || MainTabNew.Focus != 1) continue;
            mGraphics2.drawImage(AvMain.imgSelect_1, XYpaint[i][0] - this.sizeKill / 2 - 2, XYpaint[i][1] - 2, 0, true);
        }
        mGraphics.resetTransAndroid(mGraphics2);
        mGraphics2.restoreCanvas();
        mGraphics2.endClip();
        if ((!GameCanvas.menu2.isShowMenu && GameCanvas.currentDialog == null || MainTabNew.longwidth > 0) && MainTabNew.timePaintInfo > MainTabNew.timeRequest) {
            this.paintPopupContent(mGraphics2, false);
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
        int n = this.idSelect;
        if (GameCanvas.keyMyHold[5]) {
            System.out.println("nhan thuong " + this.idSelect);
            GameCanvas.clearKeyHold();
        }
        if (GameCanvas.keyMyHold[13]) {
            this.backTab();
            GameCanvas.game.Show();
            GameCanvas.clearKeyHold();
        }
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
            if (this.idSelect > vecItem.size() - 1) {
                this.idSelect = vecItem.size() - 1;
            }
            GameCanvas.clearKeyHold(8);
        }
        if (GameCanvas.keyMyHold[4]) {
            TabScreenNew.timeRepaint = 10;
            --this.idSelect;
            if (this.idSelect <= 0) {
                this.idSelect = 0;
            }
            GameCanvas.clearKeyHold(4);
        } else if (GameCanvas.keyMyHold[6]) {
            TabScreenNew.timeRepaint = 10;
            if (this.idSelect < vecItem.size() - 1) {
                ++this.idSelect;
            }
            GameCanvas.clearKeyHold(6);
        }
        if (this.idSelect < 0) {
            this.idSelect = -1;
            return;
        }
        this.idSelect = this.resetSelect(this.idSelect, this.MaxItem - 1, false);
        Item item = null;
        if (this.idSelect != n && this.idSelect >= 0 && this.idSelect <= vecItem.size() - 1) {
            this.listContent = null;
            this.cmdListBig();
            MainTabNew.timePaintInfo = 0;
            MainScreen.cameraSub.moveCamera(0, XYpaint[this.idSelect][1] - this.yBegin - MainTabNew.hblack / 2);
            item = (Item)vecItem.elementAt(this.idSelect);
        }
        super.updatekey();
    }

    public void setPaintInfo() {
        try {
            int n;
            if (this.idSelect >= vecItem.size() || this.idSelect == -1) {
                MainTabNew.timePaintInfo = 0;
                return;
            }
            this.mContent = null;
            this.mSubContent = null;
            this.mPlusContent = null;
            if (this.idSelect >= this.MaxItem || this.idSelect == -1) {
                MainTabNew.timePaintInfo = 0;
                return;
            }
            Item item = (Item)vecItem.elementAt(this.idSelect);
            if (item == null) {
                this.mContent = null;
                this.mPlusContent = null;
                this.listContent = null;
                return;
            }
            this.name = item.itemName;
            this.mContent = item.mcontent;
            this.mcolor = item.mColor;
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
            return;
        }
        catch (Exception exception) {
            return;
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
        if (this.vecListCmd != null && MainTabNew.timePaintInfo > MainTabNew.timeRequest) {
            for (n = 0; n < this.vecListCmd.size(); ++n) {
                object = (iCommand)this.vecListCmd.elementAt(n);
                ((iCommand)object).updatePointer();
            }
        }
        super.updatePointer();
    }

    public mVector doMenu() {
        mVector mVector2 = new mVector("TabSkillsNew menu2");
        mVector2.addElement(this.cmdNhanthuong);
        this.hcmd = (mVector2.size() + 1) / 2 * iCommand.hButtonCmd;
        return mVector2;
    }

    public void cmdListBig() {
        if (MainTabNew.longwidth > 0 && GameCanvas.isTouch && this.idSelect >= 0) {
            this.vecListCmd = this.doMenu();
            this.setPosCmd(this.vecListCmd);
        }
    }

    static {
        vecItem = new mVector();
    }
}

