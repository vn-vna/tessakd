/*
 * Decompiled with CFR 0.152.
 */
package GameScreen;

import CLib.mFont;
import CLib.mGraphics;
import CLib.mSystem;
import CLib.mVector;
import GameObjects.Item;
import GameObjects.MainItem;
import GameScreen.GameScreen;
import GameScreen.MainScreen;
import GameScreen.PaintInfoGameScreen;
import InterfaceComponents.InputDialog;
import InterfaceComponents.ListNew;
import InterfaceComponents.MainTabNew;
import InterfaceComponents.PopupChat;
import InterfaceComponents.iCommand;
import Main.GameCanvas;
import Model.AvMain;
import Model.FrameImage;
import Model.T;
import Model.mCamera;
import netcommand.global.GlobalService;

public class Buy_Sell_Screen
extends MainScreen {
    int wDia;
    int idSelect;
    int idSelectBuy;
    int maxSize;
    int hDia;
    int xDia;
    int yDia;
    int numw;
    int numh;
    public static final byte TYPE_BUY_MOI = 0;
    public static final byte TYPE_BUY_CHAPNHAN = 1;
    public static final byte TYPE_BUY_GOIITEM = 2;
    public static final byte TYPE_BUY_LAYITEM = 3;
    public static final byte TYPE_BUY_KHOA = 4;
    public static final byte TYPE_BUY_GIAODICH = 5;
    public String nameBuy = "";
    public static int[] mRedSell;
    public int typeActionBuySell = -1;
    public int[] moneyBuySell = new int[2];
    public static MainItem[][] mItemOther;
    public byte typeLock = (byte)-1;
    boolean isINVEN_BUY = false;
    MainTabNew maintab;
    int hItem = 0;
    mCamera cameraDia = new mCamera();
    public iCommand cmdGiaodich;
    public iCommand cmdLock;
    public iCommand cmdChuyentien;
    public iCommand cmdchat;
    InputDialog input;
    InputDialog inputChat;
    ListNew list;
    public PopupChat[] mchat = new PopupChat[2];
    public String[] strchat = new String[2];
    boolean isSmall = false;
    boolean isTran = false;
    int yCamBegin = 0;

    public void Show() {
        mSystem.outloi("goi ham show kia");
        this.Show(GameCanvas.currentScreen);
    }

    public void Show(MainScreen mainScreen) {
        this.lastScreen = mainScreen;
        super.Show(mainScreen.lastScreen);
        this.maintab.listContent = null;
    }

    public void commandTab(int n, int n2) {
        switch (n) {
            case 6: {
                GameCanvas.start_Left_Dialog(T.banmuongiaodich, new iCommand(T.buySell, 1, this));
                break;
            }
            case 7: {
                this.setItemBuySell();
                break;
            }
            case 8: {
                GlobalService.gI().Buy_Sell((byte)6, "", (byte)0, (short)0, 0);
                break;
            }
            case 9: {
                this.doMenu();
            }
        }
        GameCanvas.clearKeyHold();
        super.commandTab(n, n2);
    }

    public void commandMenu(int n, int n2) {
        switch (n) {
            case 0: {
                if (this.typeLock == -1) {
                    this.typeLock = 1;
                    this.left = null;
                    this.center = null;
                } else if (this.typeLock == 0) {
                    this.typeLock = (byte)2;
                    this.left = null;
                    this.center = null;
                }
                GlobalService.gI().Buy_Sell((byte)4, "", (byte)0, (short)0, 0);
                MainTabNew.timePaintInfo = 0;
                break;
            }
            case 1: {
                this.input.setinfo(T.nhapsotien, new iCommand(T.xacnhan, 0, this), true, T.buySell);
                GameCanvas.currentDialog = this.input;
                break;
            }
        }
    }

    public void commandPointer(int n, int n2) {
        switch (n) {
            case 0: {
                int n3 = 0;
                try {
                    n3 = Integer.parseInt(this.input.tfInput.getText());
                }
                catch (Exception exception) {
                    n3 = 0;
                }
                if (n3 < 0) break;
                if ((long)n3 <= GameScreen.player.coin) {
                    this.moneyBuySell[0] = n3;
                    GlobalService.gI().Buy_Sell((byte)7, "", (byte)0, (short)0, n3);
                    GameCanvas.end_Dialog();
                    break;
                }
                GameCanvas.start_Ok_Dialog(T.giatrinhapsai);
                break;
            }
            case 1: {
                GlobalService.gI().Buy_Sell((byte)5, "", (byte)0, (short)0, 0);
                GameCanvas.end_Dialog();
                break;
            }
            case 2: {
                String string = this.inputChat.tfInput.getText();
                if (string == null || string.length() == 0) {
                    return;
                }
                GlobalService.gI().Buy_Sell((byte)9, string, (byte)0, (short)0, 0);
                this.strchat[0] = string;
                this.inputChat.tfInput.setText("");
                break;
            }
            case 3: {
                this.inputChat.setinfo(T.trochuyenvoi + this.nameBuy, new iCommand(T.chat, 2, this), false, T.buySell);
                GameCanvas.currentDialog = this.inputChat;
            }
        }
    }

    public void setinfoBuySell(String string) {
        int n;
        GameScreen.player.resetVx_vy();
        this.cmdGiaodich = new iCommand(T.buySell, 6);
        this.cmdLock = new iCommand(T.khoa, 0);
        this.cmdChuyentien = new iCommand(T.chuyentien, 1);
        this.cmdchat = new iCommand(T.chat, 3, this);
        if (!GameCanvas.isTouch) {
            this.center = new iCommand(T.select, 7);
        }
        this.left = new iCommand(T.menu, 9);
        this.right = new iCommand(T.cancel, 8);
        this.wDia = MainTabNew.wOneItem * 8;
        mRedSell = new int[9];
        for (n = 0; n < mRedSell.length; ++n) {
            Buy_Sell_Screen.mRedSell[n] = -1;
        }
        mItemOther = new MainItem[2][];
        for (n = 0; n < mItemOther.length; ++n) {
            Buy_Sell_Screen.mItemOther[n] = new MainItem[9];
        }
        this.moneyBuySell[0] = 0;
        this.moneyBuySell[1] = 0;
        this.input = new InputDialog();
        this.inputChat = new InputDialog();
        this.mchat[0] = null;
        this.mchat[1] = null;
        this.setmIdBuy(null);
        Buy_Sell_Screen.setmItemOther(null, (byte)0, (byte)0);
        this.nameBuy = string;
        this.idSelect = 0;
        this.idSelectBuy = 0;
        this.maxSize = Item.VecInvetoryPlayer.size();
        this.hDia = MainTabNew.wOneItem * 9 + GameCanvas.hCommand / 2 + 2;
        this.xDia = GameCanvas.hw - this.wDia / 2;
        this.yDia = GameCanvas.hh - GameCanvas.hCommand / 2 - this.hDia / 2 + 2;
        if (this.yDia < 0) {
            this.hDia = MainTabNew.wOneItem * 8 + 6;
            n = iCommand.hButtonCmd;
            if (GameCanvas.isTouch) {
                n = 20;
            }
            this.yDia = GameCanvas.hh - this.hDia / 2 - n / 2;
            this.isSmall = true;
        }
        this.numw = (this.wDia - 6) / 32;
        this.numh = (this.hDia - 6) / 32;
        this.cameraDia.setAll(0, ((this.maxSize - 1) / 3 - 6 + 1) * MainTabNew.wOneItem, 0, 0);
        this.list = new ListNew(this.xDia + MainTabNew.wOneItem * 4 - MainTabNew.wOneItem / 2, this.yDia + GameCanvas.hCommand + MainTabNew.wOneItem - MainTabNew.wOneItem / 2, MainTabNew.wOneItem * 4, MainTabNew.wOneItem * 6, 0, 0, this.cameraDia.yLimit);
        if (this.maxSize < 18) {
            this.maxSize = 18;
        }
        this.typeLock = (byte)-1;
        this.maintab = new MainTabNew();
        MainTabNew.timePaintInfo = 0;
        this.setPosCmd();
        if (GameCanvas.isTouch) {
            FrameImage frameImage = new FrameImage(PaintInfoGameScreen.imgOther[1], 25, 25);
            if (this.isSmall) {
                this.cmdchat.setPos(this.xDia + 15, this.yDia + GameCanvas.hCommand / 2 + 1, frameImage, "");
            } else {
                this.cmdchat.setPos(this.xDia + this.wDia - 25, this.yDia + 20, frameImage, "");
            }
        }
    }

    public void setPosCmd() {
        if (GameCanvas.isTouch) {
            if (this.isSmall) {
                if (this.left != null && this.center != null && this.right != null) {
                    this.center.setPos(GameCanvas.hw, this.yDia + this.hDia + 10, PaintInfoGameScreen.fraButton2, this.center.caption);
                    this.left.setPos(GameCanvas.hw - 66, this.yDia + this.hDia + 10, PaintInfoGameScreen.fraButton2, this.left.caption);
                    this.right.setPos(GameCanvas.hw + 66, this.yDia + this.hDia + 10, PaintInfoGameScreen.fraButton2, this.right.caption);
                } else {
                    if (this.left != null) {
                        this.left.setPos(GameCanvas.hw - this.wDia / 4, this.yDia + this.hDia + 10, PaintInfoGameScreen.fraButton2, this.left.caption);
                    }
                    if (this.center != null) {
                        this.center.setPos(GameCanvas.hw, this.yDia + this.hDia + 10, PaintInfoGameScreen.fraButton2, this.center.caption);
                    }
                    if (this.right != null) {
                        this.right.setPos(GameCanvas.hw + this.wDia / 4, this.yDia + this.hDia + 10, PaintInfoGameScreen.fraButton2, this.right.caption);
                    }
                }
            } else if (this.left != null && this.center != null && this.right != null) {
                this.center.setPos(GameCanvas.hw, this.yDia + this.hDia, null, this.center.caption);
                this.left.setPos(GameCanvas.hw - 80, this.yDia + this.hDia, null, this.left.caption);
                this.right.setPos(GameCanvas.hw + 80, this.yDia + this.hDia, null, this.right.caption);
            } else {
                if (this.left != null) {
                    this.left.setPos(GameCanvas.hw - 50, this.yDia + this.hDia, null, this.left.caption);
                }
                if (this.center != null) {
                    this.center.setPos(GameCanvas.hw, this.yDia + this.hDia, null, this.center.caption);
                }
                if (this.right != null) {
                    this.right.setPos(GameCanvas.hw + 50, this.yDia + this.hDia, null, this.right.caption);
                }
            }
        }
    }

    public void paint(mGraphics mGraphics2) {
        int n;
        if (this.lastScreen != null) {
            this.lastScreen.paint(mGraphics2);
        }
        if (GameCanvas.currentScreen != this) {
            return;
        }
        GameCanvas.resetTrans(mGraphics2);
        int n2 = this.yDia;
        int n3 = this.xDia + MainTabNew.wOneItem;
        AvMain.paintDialogNew(mGraphics2, this.xDia, this.yDia, this.wDia, this.hDia, 0);
        if (!this.isSmall) {
            if (GameCanvas.lowGraphic) {
                MainTabNew.paintRectLowGraphic(mGraphics2, GameCanvas.hw - 32, n2 + 8, 64, 14, 2);
            } else {
                for (n = 0; n < 2; ++n) {
                    mGraphics2.drawRegion(MainTabNew.imgTab[2], 0, 0, 32, 14, 0, GameCanvas.hw - 32 + n * 32, n2 + 8, 0, true);
                }
            }
            mFont.tahoma_7b_white.drawString(mGraphics2, T.buySell, GameCanvas.hw, n2 + 9, 2, true);
            n2 = this.yDia + GameCanvas.hCommand;
        }
        if (GameCanvas.isTouch && this.typeLock != 2) {
            this.cmdchat.paint(mGraphics2, this.cmdchat.xCmd, this.cmdchat.yCmd);
        }
        this.paintBuySell(mGraphics2, n3, n2);
        GameCanvas.resetTrans(mGraphics2);
        for (n = 0; n < this.mchat.length; ++n) {
            if (this.mchat[n] == null) continue;
            this.mchat[n].paint(mGraphics2);
        }
        super.paint(mGraphics2);
    }

    public void paintBuySell(mGraphics mGraphics2, int n, int n2) {
        int n3;
        mGraphics2.setColor(-4478311);
        AvMain.paintRectNice(mGraphics2, n + MainTabNew.wOneItem * 3, n2 + MainTabNew.wOneItem, MainTabNew.wOneItem * 3, MainTabNew.wOneItem * 6, (byte)2);
        AvMain.paintRectNice(mGraphics2, n, n2 + MainTabNew.wOneItem, MainTabNew.wOneItem * 3, MainTabNew.wOneItem * 6, (byte)1);
        mGraphics2.drawRect(n, n2 + MainTabNew.wOneItem, MainTabNew.wOneItem * 6, MainTabNew.wOneItem * 6, true);
        mGraphics2.setColor(-4478311);
        int n4 = 0;
        if (GameCanvas.isSmallScreen) {
            n4 = 2;
        }
        mFont.tahoma_7b_black.drawString(mGraphics2, GameScreen.player.name, n + (this.isSmall ? 5 : 0), n2 + MainTabNew.wOneItem / 4, 0, true);
        if (this.typeActionBuySell == 0) {
            mFont.tahoma_7b_red.drawString(mGraphics2, "- Ok -", n + MainTabNew.wOneItem * 3, n2 + MainTabNew.wOneItem / 4, 2, true);
        }
        mFont.tahoma_7b_black.drawString(mGraphics2, this.nameBuy, n, n2 + MainTabNew.wOneItem / 4 + MainTabNew.wOneItem * 7 - n4, 0, true);
        if (this.typeActionBuySell == 1) {
            mFont.tahoma_7b_red.drawString(mGraphics2, "- Ok -", n + MainTabNew.wOneItem * 3, n2 + MainTabNew.wOneItem / 4 + MainTabNew.wOneItem * 7 - n4, 2, true);
        }
        if (this.moneyBuySell[0] > 0) {
            mFont.tahoma_7_black.drawString(mGraphics2, this.moneyBuySell[0] + " $", n + MainTabNew.wOneItem * 6, n2 + MainTabNew.wOneItem / 4, 1, true);
        }
        if (this.moneyBuySell[1] > 0) {
            mFont.tahoma_7_black.drawString(mGraphics2, this.moneyBuySell[1] + " $", n + MainTabNew.wOneItem * 6, n2 + MainTabNew.wOneItem / 4 + MainTabNew.wOneItem * 7 - n4, 1, true);
        }
        mGraphics2.setClip(n, n2 + MainTabNew.wOneItem, MainTabNew.wOneItem * 6, MainTabNew.wOneItem * 6);
        mGraphics2.translate(0, -this.cameraDia.yCam);
        if (this.typeLock == -1 || this.typeLock == 0) {
            for (n3 = 0; n3 < Item.VecInvetoryPlayer.size(); ++n3) {
                MainItem mainItem = (MainItem)Item.VecInvetoryPlayer.elementAt(n3);
                if (mainItem.ItemCatagory == 7) {
                    MainItem mainItem2 = Item.getMaterial(mainItem.Id);
                    if (mainItem2 != null) {
                        mainItem2.paintItem(mGraphics2, n + MainTabNew.wOneItem * 3 + MainTabNew.wOneItem / 2 + n3 % 3 * MainTabNew.wOneItem, n2 + MainTabNew.wOneItem + MainTabNew.wOneItem / 2 + n3 / 3 * MainTabNew.wOneItem, MainTabNew.wOneItem, 0, 0);
                    } else {
                        Item.put_Material(mainItem.Id);
                    }
                } else {
                    mainItem.paintItem(mGraphics2, n + MainTabNew.wOneItem * 3 + MainTabNew.wOneItem / 2 + n3 % 3 * MainTabNew.wOneItem, n2 + MainTabNew.wOneItem + MainTabNew.wOneItem / 2 + n3 / 3 * MainTabNew.wOneItem, MainTabNew.wOneItem, 0, 0);
                }
                if (mainItem.canTrade != 0) continue;
                mGraphics2.drawRegion(AvMain.imgDelaySkill, 0, 0, MainTabNew.wOneItem - 1, MainTabNew.wOneItem - 1, 0, n + MainTabNew.wOneItem * 3 + MainTabNew.wOneItem / 2 + n3 % 3 * MainTabNew.wOneItem, n2 + MainTabNew.wOneItem + MainTabNew.wOneItem / 2 + n3 / 3 * MainTabNew.wOneItem, 3, true);
            }
            mGraphics2.setColor(-2737107);
            for (n3 = 0; n3 < mRedSell.length; ++n3) {
                if (mRedSell[n3] < 0) continue;
                mGraphics2.drawRect(n + MainTabNew.wOneItem * 3 + mRedSell[n3] % 3 * MainTabNew.wOneItem + 3, n2 + MainTabNew.wOneItem + mRedSell[n3] / 3 * MainTabNew.wOneItem + 3, MainTabNew.wOneItem - 6, MainTabNew.wOneItem - 6, true);
            }
        }
        mGraphics2.setColor(-4478311);
        mGraphics2.drawRect(n + MainTabNew.wOneItem * 4, n2 + MainTabNew.wOneItem, MainTabNew.wOneItem, MainTabNew.wOneItem * (this.maxSize / 3), true);
        for (n3 = 0; n3 < this.maxSize / 3 + 1; ++n3) {
            mGraphics2.drawLine(n + MainTabNew.wOneItem * 3, n2 + MainTabNew.wOneItem + MainTabNew.wOneItem * n3, n + MainTabNew.wOneItem * 3 + MainTabNew.wOneItem * 3, n2 + MainTabNew.wOneItem + MainTabNew.wOneItem * n3, true);
        }
        mGraphics2.setColor(-1);
        if (!this.isINVEN_BUY && this.typeLock < 1 && this.idSelect >= 0) {
            mGraphics2.drawRect(n + MainTabNew.wOneItem * (3 + this.idSelect % 3) + 1, n2 + MainTabNew.wOneItem * (this.idSelect / 3 + 1) + 1, MainTabNew.wOneItem - 2, MainTabNew.wOneItem - 2, true);
        }
        GameCanvas.resetTrans(mGraphics2);
        for (n3 = 0; n3 < mItemOther[0].length; ++n3) {
            MainItem mainItem;
            int n5 = n + MainTabNew.wOneItem / 2 + n3 % 3 * MainTabNew.wOneItem;
            int n6 = n2 + MainTabNew.wOneItem + MainTabNew.wOneItem / 2 + n3 / 3 * MainTabNew.wOneItem;
            if (mItemOther[0][n3] != null) {
                if (Buy_Sell_Screen.mItemOther[0][n3].ItemCatagory == 7) {
                    mainItem = Item.getMaterial(Buy_Sell_Screen.mItemOther[0][n3].Id);
                    if (mainItem != null) {
                        if (Buy_Sell_Screen.mItemOther[0][n3].content == null) {
                            Buy_Sell_Screen.mItemOther[0][n3].itemName = mainItem.itemName;
                            mItemOther[0][n3].mMoreContent(mainItem.content);
                            Buy_Sell_Screen.mItemOther[0][n3].content = mainItem.content;
                        }
                    } else {
                        Item.put_Material(Buy_Sell_Screen.mItemOther[0][n3].Id);
                    }
                    mItemOther[0][n3].paintItem(mGraphics2, n5, n6, MainTabNew.wOneItem, 0, 0);
                } else {
                    mItemOther[0][n3].paintItem(mGraphics2, n5, n6, MainTabNew.wOneItem, 0, 0);
                }
            }
            if (this.typeLock > 0) {
                mGraphics2.setColor(-2737107);
                mGraphics2.drawRect(n5 - MainTabNew.wOneItem / 2 + 1, n6 - MainTabNew.wOneItem / 2 + 1, MainTabNew.wOneItem - 2, MainTabNew.wOneItem - 2, true);
            }
            n5 = n + MainTabNew.wOneItem / 2 + n3 % 3 * MainTabNew.wOneItem;
            n6 = n2 + MainTabNew.wOneItem * 4 + MainTabNew.wOneItem / 2 + n3 / 3 * MainTabNew.wOneItem;
            if (mItemOther[1][n3] != null) {
                if (Buy_Sell_Screen.mItemOther[1][n3].ItemCatagory == 7) {
                    mainItem = Item.getMaterial(Buy_Sell_Screen.mItemOther[1][n3].Id);
                    if (mainItem != null) {
                        if (Buy_Sell_Screen.mItemOther[1][n3].content == null) {
                            Buy_Sell_Screen.mItemOther[1][n3].itemName = mainItem.itemName;
                            Buy_Sell_Screen.mItemOther[1][n3].content = mainItem.content;
                            mItemOther[1][n3].mMoreContent(mainItem.content);
                        }
                    } else {
                        Item.put_Material(Buy_Sell_Screen.mItemOther[1][n3].Id);
                    }
                    mItemOther[1][n3].paintItem(mGraphics2, n5, n6, MainTabNew.wOneItem, 0, 0);
                } else {
                    mItemOther[1][n3].paintItem(mGraphics2, n5, n6, MainTabNew.wOneItem, 0, 0);
                }
            }
            if (this.typeLock != 0 && this.typeLock != 2) continue;
            mGraphics2.setColor(-2737107);
            mGraphics2.drawRect(n5 - MainTabNew.wOneItem / 2 + 1, n6 - MainTabNew.wOneItem / 2 + 1, MainTabNew.wOneItem - 2, MainTabNew.wOneItem - 2, true);
        }
        mGraphics2.setColor(-4478311);
        for (n3 = 0; n3 < 3; ++n3) {
            mGraphics2.drawRect(n, n2 + MainTabNew.wOneItem + MainTabNew.wOneItem * 2 * n3, MainTabNew.wOneItem * (3 + (n3 == 3 ? 3 : 0)), MainTabNew.wOneItem, true);
        }
        mGraphics2.drawRect(n + MainTabNew.wOneItem, n2 + MainTabNew.wOneItem, MainTabNew.wOneItem, MainTabNew.wOneItem * 6, true);
        mGraphics2.setColor(0);
        mGraphics2.fillRect(n + MainTabNew.wOneItem * 3, n2 + MainTabNew.wOneItem + 1, 1, MainTabNew.wOneItem * 6 - 1, true);
        mGraphics2.fillRect(n + 1, n2 + MainTabNew.wOneItem * 4, MainTabNew.wOneItem * 3, 1, true);
        mGraphics2.setColor(-1);
        if (this.isINVEN_BUY && this.idSelectBuy >= 0) {
            mGraphics2.drawRect(n + MainTabNew.wOneItem * (this.idSelectBuy % 3) + 1, n2 + MainTabNew.wOneItem * (this.idSelectBuy / 3 + 4) + 1, MainTabNew.wOneItem - 2, MainTabNew.wOneItem - 2, true);
        }
        mGraphics2.endClip();
        if (!GameCanvas.menu2.isShowMenu && GameCanvas.currentDialog == null && MainTabNew.timePaintInfo > MainTabNew.timeRequest) {
            mGraphics2.translate(0, -this.cameraDia.yCam);
            this.maintab.paintContent(mGraphics2, false);
        }
        GameCanvas.resetTrans(mGraphics2);
    }

    public void doMenu() {
        mVector mVector2 = new mVector("Buy_sell_scr menu");
        mVector2.addElement(this.cmdChuyentien);
        mVector2.addElement(this.cmdLock);
        if (!GameCanvas.isTouch) {
            mVector2.addElement(this.cmdchat);
        }
        GameCanvas.menu2.startAt(mVector2, 2, T.select, false, null);
    }

    public void setmIdBuy(MainItem mainItem) {
        if (mainItem == null) {
            for (int i = 0; i < mItemOther[0].length; ++i) {
                Buy_Sell_Screen.mItemOther[0][i] = null;
            }
        } else {
            int n;
            for (n = 0; n < mItemOther[0].length; ++n) {
                if (mItemOther[0][n] == null || Buy_Sell_Screen.mItemOther[0][n].ItemCatagory != mainItem.ItemCatagory || Buy_Sell_Screen.mItemOther[0][n].Id != mainItem.Id) continue;
                GlobalService.gI().Buy_Sell((byte)3, "", (byte)mainItem.ItemCatagory, (short)mainItem.Id, 0);
                Buy_Sell_Screen.mItemOther[0][n] = null;
                Buy_Sell_Screen.mRedSell[n] = -1;
                return;
            }
            for (n = 0; n < mItemOther[0].length; ++n) {
                if (mItemOther[0][n] != null) continue;
                Buy_Sell_Screen.mItemOther[0][n] = mainItem;
                GlobalService.gI().Buy_Sell((byte)2, "", (byte)mainItem.ItemCatagory, (short)mainItem.Id, 0);
                Buy_Sell_Screen.mRedSell[n] = this.idSelect;
                return;
            }
        }
    }

    public void update() {
        int n;
        this.lastScreen.update();
        if (this.maintab.listContent != null) {
            this.maintab.listContent.moveCamera();
        }
        if (GameCanvas.isTouch) {
            this.list.moveCamera();
        } else {
            this.cameraDia.UpdateCamera();
        }
        for (n = 0; n < this.mchat.length; ++n) {
            if (this.mchat[n] == null || !this.mchat[n].setOff()) continue;
            this.mchat[n] = null;
        }
        for (n = 0; n < this.strchat.length; ++n) {
            if (this.strchat[n] == null) continue;
            this.ChatBuySell((byte)n, this.strchat[n]);
            this.strchat[n] = null;
        }
    }

    public void updatekey() {
        int n;
        if (this.typeLock >= 1) {
            this.isINVEN_BUY = true;
        }
        if (this.isINVEN_BUY) {
            n = this.idSelectBuy;
            if (this.maintab.listContent != null) {
                if (GameCanvas.keyMyHold[2]) {
                    this.maintab.listContent.cmtoX = this.maintab.listContent.cmtoX > 0 ? (this.maintab.listContent.cmtoX -= GameCanvas.hText) : 0;
                    GameCanvas.clearKeyHold(2);
                } else if (GameCanvas.keyMyHold[8]) {
                    this.maintab.listContent.cmtoX = this.maintab.listContent.cmtoX < this.maintab.listContent.cmxLim ? (this.maintab.listContent.cmtoX += GameCanvas.hText) : this.maintab.listContent.cmxLim;
                    GameCanvas.clearKeyHold(8);
                }
            } else if (GameCanvas.keyMyHold[2]) {
                this.idSelectBuy -= 3;
                GameCanvas.clearKeyHold(2);
            } else if (GameCanvas.keyMyHold[8]) {
                this.idSelectBuy += 3;
                GameCanvas.clearKeyHold(8);
            }
            if (GameCanvas.keyMyHold[4]) {
                --this.idSelectBuy;
                GameCanvas.clearKeyHold(4);
            } else if (GameCanvas.keyMyHold[6]) {
                if (this.idSelectBuy % 3 == 2 && this.typeLock < 1) {
                    this.isINVEN_BUY = false;
                    MainTabNew.timePaintInfo = 0;
                } else {
                    ++this.idSelectBuy;
                }
                GameCanvas.clearKeyHold(6);
            }
            this.idSelectBuy = this.resetSelect(this.idSelectBuy, 8, false);
            if (this.idSelectBuy != n) {
                MainTabNew.timePaintInfo = 0;
                this.maintab.listContent = null;
            }
        } else {
            n = this.idSelect;
            if (this.maintab.listContent != null) {
                if (GameCanvas.keyMyHold[2]) {
                    this.maintab.listContent.cmtoX = this.maintab.listContent.cmtoX > 0 ? (this.maintab.listContent.cmtoX -= GameCanvas.hText) : 0;
                    GameCanvas.clearKeyHold(2);
                } else if (GameCanvas.keyMyHold[8]) {
                    this.maintab.listContent.cmtoX = this.maintab.listContent.cmtoX < this.maintab.listContent.cmxLim ? (this.maintab.listContent.cmtoX += GameCanvas.hText) : this.maintab.listContent.cmxLim;
                    GameCanvas.clearKeyHold(8);
                }
            } else if (GameCanvas.keyMyHold[2]) {
                this.idSelect -= 3;
                GameCanvas.clearKeyHold(2);
            } else if (GameCanvas.keyMyHold[8]) {
                this.idSelect += 3;
                GameCanvas.clearKeyHold(8);
            }
            if (GameCanvas.keyMyHold[4]) {
                if (this.idSelect % 3 == 0) {
                    this.isINVEN_BUY = true;
                    MainTabNew.timePaintInfo = 0;
                } else {
                    --this.idSelect;
                }
                GameCanvas.clearKeyHold(4);
            } else if (GameCanvas.keyMyHold[6]) {
                ++this.idSelect;
                GameCanvas.clearKeyHold(6);
            }
            if (!GameCanvas.isTouch) {
                this.idSelect = this.resetSelect(this.idSelect, Item.VecInvetoryPlayer.size() - 1, false);
            }
            if (this.idSelect < -1) {
                this.idSelect = -1;
            } else if (this.idSelect > Item.VecInvetoryPlayer.size() - 1) {
                this.idSelect = Item.VecInvetoryPlayer.size();
            }
            if (this.idSelect != n && this.idSelect >= 0) {
                this.cameraDia.moveCamera(0, (this.idSelect / 3 - 3) * MainTabNew.wOneItem);
                MainTabNew.timePaintInfo = 0;
            }
        }
        int n2 = this.idSelect;
        if (this.isINVEN_BUY) {
            n2 = this.idSelectBuy;
        }
        MainItem mainItem = !this.isINVEN_BUY ? (MainItem)Item.VecInvetoryPlayer.elementAt(n2) : mItemOther[1][n2];
        this.updateContent(mainItem);
        super.updatekey();
    }

    public void updatePointer() {
        int n = this.xDia + MainTabNew.wOneItem;
        int n2 = this.yDia + MainTabNew.wOneItem;
        if (!this.isSmall) {
            n2 += GameCanvas.hCommand;
        }
        boolean bl = false;
        if (this.maintab.listContent != null && GameCanvas.isPoint(this.maintab.listContent.x, this.maintab.listContent.y, this.maintab.listContent.maxW, this.maintab.listContent.maxH)) {
            this.maintab.listContent.update_Pos_UP_DOWN();
            bl = true;
        }
        if (!bl) {
            if (GameCanvas.isPointerMove) {
                MainTabNew.timePaintInfo = 0;
            }
            if (GameCanvas.isTouch) {
                this.list.update_Pos_UP_DOWN();
                this.cameraDia.yCam = this.list.cmx;
            }
            if (this.typeLock < 1 && GameCanvas.isPointSelect(n + 3 * MainTabNew.wOneItem, n2, 3 * MainTabNew.wOneItem, 6 * MainTabNew.wOneItem)) {
                int n3 = (GameCanvas.px - (n + 3 * MainTabNew.wOneItem)) / MainTabNew.wOneItem + (this.cameraDia.yCam + GameCanvas.py - n2) / MainTabNew.wOneItem * 3;
                int n4 = 0;
                n4 = Item.VecInvetoryPlayer.size();
                if (this.isINVEN_BUY) {
                    this.idSelect = -1;
                }
                this.isINVEN_BUY = false;
                if (n3 >= 0 && n3 < n4) {
                    GameCanvas.isPointerSelect = false;
                    if (n3 == this.idSelect) {
                        this.setItemBuySell();
                    } else {
                        this.idSelect = n3;
                        MainTabNew.timePaintInfo = 0;
                        this.maintab.listContent = null;
                    }
                } else {
                    this.idSelect = -1;
                    MainTabNew.timePaintInfo = 0;
                    this.maintab.listContent = null;
                }
                GameCanvas.isPointerSelect = false;
            } else if (GameCanvas.isPointSelect(n, n2 + 3 * MainTabNew.wOneItem, 3 * MainTabNew.wOneItem, 3 * MainTabNew.wOneItem)) {
                int n5 = (GameCanvas.px - n) / MainTabNew.wOneItem + (GameCanvas.py - (n2 + 3 * MainTabNew.wOneItem)) / MainTabNew.wOneItem * 3;
                int n6 = 0;
                n6 = 9;
                if (!this.isINVEN_BUY) {
                    MainTabNew.timePaintInfo = 0;
                }
                this.isINVEN_BUY = true;
                if (n5 >= 0 && n5 < n6) {
                    GameCanvas.isPointerSelect = false;
                    if (n5 != this.idSelectBuy) {
                        this.idSelectBuy = n5;
                        MainTabNew.timePaintInfo = 0;
                    }
                } else {
                    this.idSelectBuy = -1;
                    MainTabNew.timePaintInfo = 0;
                }
                GameCanvas.isPointerSelect = false;
            }
        }
        if (GameCanvas.isTouch && this.typeLock != 2) {
            this.cmdchat.updatePointer();
        }
        super.updatePointer();
    }

    public static void setmItemOther(MainItem mainItem, byte by, byte by2) {
        block5: {
            block6: {
                block4: {
                    if (mainItem != null) break block4;
                    for (int i = 0; i < mItemOther.length; ++i) {
                        Buy_Sell_Screen.mItemOther[1][i] = null;
                    }
                    break block5;
                }
                if (by != 3) break block6;
                for (int i = 0; i < mItemOther[by2].length; ++i) {
                    if (mItemOther[by2][i] == null || Buy_Sell_Screen.mItemOther[by2][i].ItemCatagory != mainItem.ItemCatagory || Buy_Sell_Screen.mItemOther[by2][i].Id != mainItem.Id) continue;
                    Buy_Sell_Screen.mItemOther[by2][i] = null;
                    if (by2 == 0) {
                        Buy_Sell_Screen.mRedSell[i] = -1;
                    }
                    return;
                }
                break block5;
            }
            if (by != 2) break block5;
            for (int i = 0; i < mItemOther[by2].length; ++i) {
                if (mItemOther[by2][i] != null) continue;
                Buy_Sell_Screen.mItemOther[by2][i] = mainItem;
                return;
            }
        }
    }

    public void updateContent(MainItem mainItem) {
        if (MainTabNew.timePaintInfo < MainTabNew.timeRequest + 2 && ++MainTabNew.timePaintInfo == MainTabNew.timeRequest) {
            this.setPaintInfo(mainItem);
        }
        if (this.maintab.mContent == null && mainItem != null && mainItem.ItemCatagory != 5) {
            if (mainItem.mcontent == null) {
                if (mainItem.timeupdateMore % 100 == 3) {
                    if (this.maintab.typeTab == 0) {
                        GlobalService.gI().Item_More_Info((byte)0, (byte)mainItem.Id);
                    }
                    ++mainItem.timeupdateMore;
                } else {
                    ++mainItem.timeupdateMore;
                }
            } else {
                this.maintab.mContent = mainItem.mcontent;
                this.maintab.mcolor = mainItem.mColor;
                this.setYCon(mainItem);
            }
        }
    }

    public void setPaintInfo(MainItem mainItem) {
        this.maintab.mContent = null;
        this.maintab.mSubContent = null;
        this.maintab.mPlusContent = null;
        if (mainItem == null) {
            MainTabNew.timePaintInfo = 0;
            return;
        }
        if (mainItem.setNameNull()) {
            MainTabNew.timePaintInfo = 0;
            return;
        }
        this.maintab.wContent = mainItem.sizeW;
        if (this.isINVEN_BUY) {
            this.maintab.xCon = this.xDia + MainTabNew.wOneItem * 4 + 3;
            if (this.maintab.xCon + this.maintab.wContent > GameCanvas.w - 3) {
                this.maintab.xCon = GameCanvas.w - 3 - this.maintab.wContent;
            }
        } else {
            this.maintab.xCon = this.xDia + MainTabNew.wOneItem * 4 - this.maintab.wContent - 3;
            if (this.maintab.xCon < 3) {
                this.maintab.xCon = 3;
            }
        }
        this.setYCon(mainItem);
        this.maintab.name = mainItem.itemName;
        this.maintab.mPlusContent = mainItem.mPlusContent;
        this.maintab.mPlusColor = mainItem.mPlusColor;
        this.maintab.colorName = mainItem.colorNameItem;
    }

    public void setYCon(MainItem mainItem) {
        int n = 1;
        this.maintab.mContent = mainItem.mcontent;
        this.maintab.mcolor = mainItem.mColor;
        if (mainItem.mcontent != null) {
            n += this.maintab.mContent.length;
        }
        if (mainItem.mPlusContent != null) {
            n += mainItem.mPlusContent.length;
        }
        int n2 = this.idSelect;
        int n3 = 0;
        if (this.isINVEN_BUY) {
            n2 = this.idSelectBuy + 9;
            n3 = this.cameraDia.yCam;
        }
        this.maintab.yCon = (n2 / 3 + 1) * MainTabNew.wOneItem - n * GameCanvas.hText + this.yDia + GameCanvas.hCommand + n3;
        if (this.maintab.yCon - this.cameraDia.yCam < 3) {
            this.maintab.yCon = 3 + this.cameraDia.yCam;
        }
        this.maintab.listContent = null;
        if ((n + 1) * GameCanvas.hText > MainTabNew.hMaxContent) {
            this.maintab.listContent = new ListNew(this.maintab.xCon, this.maintab.yCon, this.maintab.wContent, MainTabNew.hMaxContent, 0, 0, (n + 1) * GameCanvas.hText - MainTabNew.hMaxContent);
        }
    }

    public void setItemBuySell() {
        MainItem mainItem = (MainItem)Item.VecInvetoryPlayer.elementAt(this.idSelect);
        if (mainItem.canTrade == 0) {
            GameCanvas.start_Ok_Dialog(T.khongthetraodoi);
            return;
        }
        this.setmIdBuy(mainItem);
    }

    public int tinhThue(int n) {
        int n2 = 0;
        if (n > 5000000) {
            n2 = (n - 5000000) * 30 / 100 + 1250000;
        } else if (n > 5000000) {
            n2 = (n - 5000000) * 30 / 100 + 1250000;
        }
        return n2;
    }

    public void ChatBuySell(byte by, String string) {
        if (string == null || string.length() == 0) {
            return;
        }
        if (this.mchat[by] == null) {
            this.mchat[by] = new PopupChat();
        }
        this.mchat[by].setChat(string, true);
        int n = this.yDia;
        if (!this.isSmall) {
            n = this.yDia + GameCanvas.hCommand;
        }
        int n2 = this.xDia + MainTabNew.wOneItem;
        if (by == 0) {
            this.mchat[by].indexpaint = 1;
            this.mchat[by].updatePos(n2 + MainTabNew.wOneItem, n + MainTabNew.wOneItem + this.mchat[by].h);
        } else {
            this.mchat[by].updatePos(n2 + MainTabNew.wOneItem, n + MainTabNew.wOneItem * 7);
        }
    }
}

