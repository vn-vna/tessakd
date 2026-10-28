/*
 * Decompiled with CFR 0.152.
 */
package GameScreen;

import CLib.mFont;
import CLib.mGraphics;
import CLib.mHashtable;
import CLib.mSystem;
import CLib.mVector;
import GameObjects.Item;
import GameObjects.MainObject;
import GameObjects.PetItem;
import GameObjects.Player;
import GameScreen.MainScreen;
import GameScreen.PaintInfoGameScreen;
import InterfaceComponents.ListNew;
import InterfaceComponents.MainTabNew;
import InterfaceComponents.TabMySeftNew;
import InterfaceComponents.iCommand;
import Main.GameCanvas;
import Model.T;
import Thread_More.LoadMap;
import netcommand.global.GlobalService;

public class Info_Other_Player
extends MainScreen {
    public static PetItem showPet = new PetItem();
    public static MainObject showObject = new MainObject();
    public static mHashtable vecEquipShow = new mHashtable();
    int xbegin;
    int ybegin;
    int wScreen = 180;
    int numW;
    int numH;
    int hitem;
    int idSelect;
    int xEquip;
    int yEquip;
    iCommand cmdClose;
    iCommand cmdMenu;
    iCommand cmdChat;
    iCommand cmdAddF;
    iCommand cmdParty;
    iCommand cmdBuy_Sell;
    iCommand cmdOkThachDau;
    iCommand cmdThachDau;
    iCommand cmdBuyFastionItem;
    iCommand cmdback;
    iCommand cmdnext;
    MainTabNew maintab = new MainTabNew();
    public static byte VIEW = 0;
    public static byte THACH_DAU = 1;
    public static byte THACH_DAU_INFO = (byte)100;
    byte type;
    int wsize = MainTabNew.wOneItem;
    int indexTab = 0;
    Item itemSelect;

    public void Show(MainScreen mainScreen) {
        super.Show(mainScreen);
        if (!GameCanvas.isTouch) {
            this.idSelect = 0;
            this.itemSelect = (Item)vecEquipShow.get("" + (this.idSelect + this.indexTab));
        } else {
            this.idSelect = -1;
        }
        this.maintab.listContent = null;
        this.itemSelect = null;
    }

    public Info_Other_Player() {
        if (GameCanvas.isSmallScreen) {
            this.wScreen = 160;
        }
        this.hitem = 12;
        this.numW = 6;
        this.numH = 2;
        this.xbegin = GameCanvas.hw - this.wScreen / 2;
        this.ybegin = GameCanvas.hh - this.wScreen / 2;
        if (!GameCanvas.isTouch) {
            this.ybegin -= iCommand.hButtonCmd / 2;
        }
        this.xEquip = this.xbegin + this.wScreen / 2 - this.numW * this.wsize / 2;
        this.yEquip = this.ybegin + this.wScreen / 3 * 2;
        this.cmdClose = new iCommand(T.close, -1);
        this.cmdMenu = new iCommand(T.menu, 0);
        this.cmdChat = new iCommand(T.trochuyen, 0);
        this.cmdAddF = new iCommand(T.addFriend, 1);
        this.cmdParty = new iCommand(T.moiParty, 2);
        this.cmdBuy_Sell = new iCommand(T.buySell, 3);
        this.cmdThachDau = new iCommand(T.thachdau, 4);
        this.cmdOkThachDau = new iCommand(T.chapnhan, 1);
        if (GameCanvas.isTouch) {
            this.cmdOkThachDau.setPos(this.xbegin + this.wScreen / 2, this.ybegin + this.wScreen + iCommand.hButtonCmd / 2, null, this.cmdOkThachDau.caption);
            this.cmdClose.setPos(this.xbegin + this.wScreen - 12, this.ybegin + 10, PaintInfoGameScreen.fraCloseMenu, "");
        }
        this.cmdBuyFastionItem = new iCommand(T.buy, 0, this);
        this.cmdnext = new iCommand(T.trangbi2, 1, this);
        this.cmdback = new iCommand(T.trangbi1, 2, this);
        if (GameCanvas.isTouch) {
            this.cmdnext.setPos(this.xbegin + iCommand.wButtonCmd / 2, this.yEquip - iCommand.hButtonCmd / 2, PaintInfoGameScreen.fraButton2, this.cmdnext.caption);
            this.cmdback.setPos(this.xbegin + iCommand.wButtonCmd / 2, this.yEquip - iCommand.hButtonCmd / 2, PaintInfoGameScreen.fraButton2, this.cmdback.caption);
        }
        if (!GameCanvas.isTouch) {
            this.center = this.cmdnext;
        }
    }

    public void commandTab(int n, int n2) {
        switch (n) {
            case -1: {
                if (this.lastScreen.lastScreen != null) {
                    this.lastScreen.Show(this.lastScreen.lastScreen);
                    break;
                }
                this.lastScreen.Show();
                break;
            }
            case 0: {
                this.doMenu();
                break;
            }
            case 1: {
                GlobalService.gI().Thach_Dau((byte)1, Info_Other_Player.showObject.name);
            }
        }
        super.commandTab(n, n2);
    }

    public void commandPointer(int n, int n2) {
        Item item = (Item)vecEquipShow.get("" + (this.idSelect + this.indexTab));
        switch (n) {
            case 0: {
                if (item == null) break;
                GlobalService.gI().do_Buy_Sell_Item(5, null, "", (short)Info_Other_Player.showObject.ID, item.Id, (byte)0);
                mSystem.println("send buy fastion item to sv " + item.Id + " " + Info_Other_Player.showObject.ID);
                break;
            }
            case 1: {
                this.indexTab = 12;
                if (GameCanvas.isTouch) break;
                this.center = this.cmdback;
                break;
            }
            case 2: {
                this.indexTab = 0;
                if (GameCanvas.isTouch) break;
                this.center = this.cmdnext;
            }
        }
    }

    public void commandMenu(int n, int n2) {
        switch (n) {
            case 1: {
                GlobalService.gI().Friend((byte)0, Info_Other_Player.showObject.name);
                break;
            }
            case 0: {
                GameCanvas.msgchat.addNewChat(Info_Other_Player.showObject.name, "", "", (byte)0, true);
                GameCanvas.start_Chat_Dialog();
                break;
            }
            case 2: {
                GlobalService.gI().Party((byte)1, Info_Other_Player.showObject.name);
                break;
            }
            case 3: {
                GlobalService.gI().Buy_Sell((byte)0, Info_Other_Player.showObject.name, (byte)0, (short)0, 0);
                break;
            }
            case 4: {
                GlobalService.gI().Thach_Dau((byte)0, Info_Other_Player.showObject.name);
                break;
            }
        }
        super.commandMenu(n, n2);
    }

    public void init(byte by) {
        if (GameCanvas.isTouch) {
            this.center = null;
        }
        this.type = by;
        if (by == THACH_DAU) {
            if (!GameCanvas.isTouch) {
                this.right = this.cmdClose;
                this.center = this.cmdOkThachDau;
                this.left = this.cmdMenu;
            } else {
                this.center = this.cmdOkThachDau;
                this.right = this.cmdClose;
            }
        } else {
            this.type = VIEW;
            if (!GameCanvas.isTouch) {
                this.right = this.cmdClose;
                this.left = this.cmdMenu;
            } else {
                this.right = this.cmdClose;
            }
        }
    }

    private void doMenu() {
        mVector mVector2 = new mVector("Info_Other_Player menu");
        mVector2.addElement(this.cmdChat);
        mVector2.addElement(this.cmdAddF);
        if (Player.party == null || Player.party.vecPartys.size() < 5) {
            mVector2.addElement(this.cmdParty);
        }
        mVector2.addElement(this.cmdBuy_Sell);
        if (this.type == VIEW && LoadMap.typeMap != 1) {
            mVector2.addElement(this.cmdThachDau);
        }
        GameCanvas.menu2.startAt(mVector2, 2, T.giaotiep, false, null);
    }

    public void paint(mGraphics mGraphics2) {
        int n;
        int n2;
        this.lastScreen.paint(mGraphics2);
        if (GameCanvas.currentScreen != this) {
            return;
        }
        GameCanvas.resetTrans(mGraphics2);
        int n3 = this.ybegin;
        int n4 = this.xbegin;
        this.paintFormList(mGraphics2, n4, n3, this.wScreen, this.wScreen, Info_Other_Player.showObject.name);
        n4 += 10;
        n3 += GameCanvas.hCommand + 2;
        if (Info_Other_Player.showObject.myClan != null) {
            showObject.paintIconClan(mGraphics2, n4 - 10 + this.wScreen / 2, n3 + 7, -2);
            n3 += this.hitem;
        }
        mFont.tahoma_7_white.drawString(mGraphics2, T.level + Info_Other_Player.showObject.Lv, n4, n3, 0, false);
        mFont.tahoma_7_white.drawString(mGraphics2, "HP: " + Info_Other_Player.showObject.hp + "/" + Info_Other_Player.showObject.maxHp, n4, n3 += this.hitem, 0, false);
        n3 += this.hitem;
        if (showPet != null) {
            showPet.paintShowPet(mGraphics2, this.xbegin + this.wScreen / 2 + 30, this.ybegin + 90 - MainTabNew.wOneItem / 2 + 5, MainTabNew.wOneItem, MainTabNew.wOneItem / 2, 0, 0);
        }
        showObject.paintShowPlayer(mGraphics2, this.xbegin + this.wScreen / 2, this.ybegin + 90, 0);
        for (n2 = 0; n2 < TabMySeftNew.maxSize; n2 = (int)((byte)(n2 + 1))) {
            n = this.xEquip + n2 % this.numW * this.wsize;
            int n5 = this.yEquip + n2 / this.numW * this.wsize;
            Item item = (Item)vecEquipShow.get("" + (n2 + this.indexTab));
            if (item != null) {
                if (item.Id > -1) {
                    item.paintItem(mGraphics2, n + MainTabNew.wOneItem / 2, n5 + MainTabNew.wOneItem / 2, MainTabNew.wOneItem, 0, 0);
                } else if (this.indexTab < 12) {
                    mGraphics2.drawRegion(MainTabNew.imgTab[6], 0, n2 * 20, 20, 20, 0, n + MainTabNew.wOneItem / 2, n5 + MainTabNew.wOneItem / 2, 3, false);
                }
            } else if (this.indexTab < 12) {
                mGraphics2.drawRegion(MainTabNew.imgTab[6], 0, n2 * 20, 20, 20, 0, n + MainTabNew.wOneItem / 2, n5 + MainTabNew.wOneItem / 2, 3, false);
            }
            mGraphics2.setColor(MainTabNew.color[4]);
            mGraphics2.drawRect(n, n5, this.wsize, this.wsize, false);
        }
        mGraphics2.setColor(MainTabNew.color[3]);
        if (this.idSelect >= 0) {
            n2 = this.xEquip + this.idSelect % this.numW * this.wsize;
            n = this.yEquip + this.idSelect / this.numW * this.wsize;
            mGraphics2.drawRect(n2, n, this.wsize, this.wsize, false);
            mGraphics2.setColor(MainTabNew.color[2]);
            mGraphics2.drawRect(n2 + 1, n + 1, this.wsize - 2, this.wsize - 2, false);
        }
        if (GameCanvas.isTouch) {
            if (this.indexTab < 12) {
                this.cmdnext.paint(mGraphics2, this.xbegin + iCommand.wButtonCmd / 2, this.yEquip - iCommand.hButtonCmd / 2);
            } else {
                this.cmdback.paint(mGraphics2, this.xbegin + iCommand.wButtonCmd / 2, this.yEquip - iCommand.hButtonCmd / 2);
            }
        }
        if (!GameCanvas.menu2.isShowMenu && GameCanvas.currentDialog == null && MainTabNew.timePaintInfo > MainTabNew.timeRequest) {
            this.maintab.paintContent(mGraphics2, false);
        }
        super.paint(mGraphics2);
    }

    public void update() {
        if (GameCanvas.isTouch) {
            if (this.indexTab < 12) {
                this.cmdnext.updatePointer();
            } else {
                this.cmdback.updatePointer();
            }
        }
        if (showObject == null) {
            this.cmdClose.perform();
        }
        this.lastScreen.update();
        if (this.maintab.listContent != null) {
            this.maintab.listContent.moveCamera();
        }
        if (this.itemSelect != null) {
            this.updateContent(this.itemSelect);
        }
        if (this.maintab != null) {
            this.maintab.update();
        }
    }

    public void updateContent(Item item) {
        if (MainTabNew.timePaintInfo < MainTabNew.timeRequest + 2 && ++MainTabNew.timePaintInfo == MainTabNew.timeRequest) {
            this.setPaintInfo(item);
        }
        if (this.maintab.mContent == null && item != null && item.ItemCatagory != 5) {
            if (item.mcontent == null) {
                if (item.timeupdateMore % 100 == 3) {
                    if (this.maintab.typeTab == 0) {
                        GlobalService.gI().Item_More_Info((byte)0, (byte)item.Id);
                    }
                    ++item.timeupdateMore;
                } else {
                    ++item.timeupdateMore;
                }
            } else {
                this.maintab.moreInfoconten = item.moreContenGem;
                this.maintab.mContent = item.mcontent;
                this.maintab.mcolor = item.mColor;
                this.setYCon(item);
            }
        }
    }

    public void updatekey() {
        int n = this.idSelect;
        if (this.maintab.listContent != null) {
            if (GameCanvas.keyMyHold[2]) {
                this.maintab.listContent.cmtoX = this.maintab.listContent.cmtoX > 0 ? (this.maintab.listContent.cmtoX -= GameCanvas.hText) : 0;
                GameCanvas.clearKeyHold(2);
            } else if (GameCanvas.keyMyHold[8]) {
                this.maintab.listContent.cmtoX = this.maintab.listContent.cmtoX < this.maintab.listContent.cmxLim ? (this.maintab.listContent.cmtoX += GameCanvas.hText) : this.maintab.listContent.cmxLim;
                GameCanvas.clearKeyHold(8);
            }
        } else if (GameCanvas.keyMyHold[2]) {
            if (this.idSelect >= this.numW) {
                this.idSelect -= this.numW;
            }
            GameCanvas.clearKeyHold(2);
        } else if (GameCanvas.keyMyHold[8]) {
            if (this.idSelect < TabMySeftNew.maxSize - this.numW) {
                this.idSelect += this.numW;
            }
            GameCanvas.clearKeyHold(8);
        }
        if (GameCanvas.keyMyHold[4]) {
            --this.idSelect;
            GameCanvas.clearKeyHold(4);
        } else if (GameCanvas.keyMyHold[6]) {
            ++this.idSelect;
            GameCanvas.clearKeyHold(6);
        }
        if (n != this.idSelect) {
            if (this.idSelect >= 0) {
                this.idSelect = (byte)this.resetSelect(this.idSelect, TabMySeftNew.maxSize - 1, false);
                MainTabNew.timePaintInfo = 0;
                this.itemSelect = (Item)vecEquipShow.get("" + (this.idSelect + this.indexTab));
            } else {
                this.idSelect = -1;
            }
            this.maintab.listContent = null;
        }
        super.updatekey();
    }

    public void setPaintInfo(Item item) {
        if (this.idSelect < 0) {
            MainTabNew.timePaintInfo = 0;
            return;
        }
        this.maintab.mContent = null;
        this.maintab.mSubContent = null;
        this.maintab.mPlusContent = null;
        int n = this.idSelect;
        if (item == null) {
            MainTabNew.timePaintInfo = 0;
            return;
        }
        if (item.setNameNull()) {
            MainTabNew.timePaintInfo = 0;
            return;
        }
        this.maintab.wContent = item.sizeW;
        this.maintab.xCon = this.xEquip + n % this.numW * this.wsize + MainTabNew.wOneItem / 2 - this.maintab.wContent / 2;
        if (this.maintab.xCon < 0) {
            this.maintab.xCon = 0;
        }
        if (this.maintab.xCon + this.maintab.wContent > GameCanvas.w) {
            this.maintab.xCon = GameCanvas.w - this.maintab.wContent;
        }
        this.setYCon(item);
        this.maintab.name = item.itemName;
        this.maintab.mPlusContent = item.mPlusContent;
        this.maintab.mPlusColor = item.mPlusColor;
        this.maintab.colorName = item.colorNameItem;
        this.maintab.moreInfoconten = item.moreContenGem;
    }

    public void setYCon(Item item) {
        int n = 2;
        this.maintab.mContent = item.mcontent;
        this.maintab.moreInfoconten = item.moreContenGem;
        this.maintab.mcolor = item.mColor;
        if (item.mcontent != null) {
            n += item.mcontent.length;
        }
        if (item.mPlusContent != null) {
            n += item.mPlusContent.length;
        }
        int n2 = this.idSelect;
        this.maintab.yCon = this.yEquip + (n2 / this.numW + 1) * this.wsize;
        if (GameCanvas.isTouch) {
            if (this.maintab.yCon + n * GameCanvas.hText > GameCanvas.h) {
                this.maintab.yCon = GameCanvas.h - n * GameCanvas.hText;
            }
        } else if (this.maintab.yCon + n * GameCanvas.hText > GameCanvas.h - iCommand.hButtonCmd) {
            this.maintab.yCon = GameCanvas.h - iCommand.hButtonCmd - n * GameCanvas.hText;
        }
        if (this.type == THACH_DAU && GameCanvas.isTouch && this.maintab.yCon + n * GameCanvas.hText > this.ybegin + this.wScreen) {
            this.maintab.yCon = this.ybegin + this.wScreen - n * GameCanvas.hText;
        }
        this.maintab.listContent = null;
        if ((n + 1) * GameCanvas.hText > MainTabNew.hMaxContent) {
            this.maintab.listContent = new ListNew(this.maintab.xCon, this.maintab.yCon, this.maintab.wContent, MainTabNew.hMaxContent, 0, 0, (n + 1) * GameCanvas.hText - MainTabNew.hMaxContent);
        }
        if (item.can_sell_for_other_player == 1) {
            this.maintab.wContent += iCommand.wButtonCmd / 2;
            this.maintab.cmd = this.cmdBuyFastionItem;
        } else {
            this.maintab.cmd = null;
            this.maintab.xpos_cmd = 0;
            this.maintab.ypos_cmd = 0;
        }
    }

    public void updatePointer() {
        boolean bl = false;
        if (this.maintab.listContent != null && GameCanvas.isPoint(this.maintab.listContent.x, this.maintab.listContent.y, this.maintab.listContent.maxW, this.maintab.listContent.maxH)) {
            this.maintab.listContent.update_Pos_UP_DOWN();
        }
        if (!bl) {
            if (GameCanvas.isPointSelect(this.xEquip, this.yEquip, this.wsize * this.numW, this.wsize * this.numH)) {
                GameCanvas.isPointerSelect = false;
                byte by = (byte)((GameCanvas.px - this.xEquip) / this.wsize + (GameCanvas.py - this.yEquip) / this.wsize * this.numW);
                if (by >= 0 && by < TabMySeftNew.maxSize && by != this.idSelect) {
                    this.idSelect = by;
                    MainTabNew.timePaintInfo = 0;
                    this.itemSelect = (Item)vecEquipShow.get("" + (this.idSelect + this.indexTab));
                    this.maintab.listContent = null;
                }
            }
            if (GameCanvas.isPointerSelect && GameCanvas.isPoint(this.xbegin + this.wScreen / 2 - 25, this.ybegin + 90 - 60, 50, 70)) {
                this.cmdMenu.perform();
                GameCanvas.isPointerSelect = false;
            }
        }
        super.updatePointer();
    }
}

