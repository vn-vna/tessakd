/*
 * Decompiled with CFR 0.152.
 */
package InterfaceComponents;

import CLib.mFont;
import CLib.mGraphics;
import CLib.mSystem;
import CLib.mVector;
import GameObjects.Item;
import GameObjects.MainBuff;
import GameObjects.MainInfoItem;
import GameObjects.MainItem;
import GameObjects.PetItem;
import GameObjects.Player;
import GameScreen.GameScreen;
import GameScreen.MainScreen;
import GameScreen.TabScreenNew;
import InterfaceComponents.ListNew;
import InterfaceComponents.MainTabNew;
import InterfaceComponents.MsgDialog;
import InterfaceComponents.TabShopNew;
import InterfaceComponents.iCommand;
import Main.GameCanvas;
import Model.AvMain;
import Model.MainTemplateItem;
import Model.T;
import netcommand.global.GlobalService;

public class TabMySeftNew
extends MainTabNew {
    int numW;
    int numH;
    public static int maxSize = 12;
    int h12;
    int w5;
    public static int delta = 0;
    byte idSelect;
    int xStart;
    int yStart;
    public static MainInfoItem[] mItemInfo;
    public static MainInfoItem[] mItemInfoShow;
    int[] mColorInfo;
    mVector vecItemMenu = new mVector("TabMySeftNew vecItemMenu");
    public static String[] meffskill;
    boolean isList = false;
    int maxList;
    int selectList;
    int xList;
    int yList;
    int timeUpdateInfo;
    boolean isShowInfo = false;
    mVector vecListCmd = new mVector("vec List cmd");
    private byte indexTab;
    iCommand cmdChange;
    iCommand cmdChangeEquip;
    iCommand cmdCloseChange;
    iCommand cmdPetInfo;
    iCommand cmdPetFeed;
    iCommand cmdNexTab;
    iCommand cmdReturn;
    iCommand cmdxem;
    ListNew list;
    int wsize = MainTabNew.wOneItem;

    public TabMySeftNew(String string) {
        this.typeTab = 1;
        if (GameCanvas.isSmallScreen) {
            delta = 10;
        }
        this.xBegin = this.xTab + MainTabNew.wOneItem + MainTabNew.wOne5 * 3;
        this.yBegin = this.yTab + GameCanvas.h / 5 + MainTabNew.wOneItem;
        this.h12 = MainTabNew.hblack / 12;
        this.w5 = MainTabNew.wblack / 5;
        this.numW = MainTabNew.wblack / MainTabNew.wOneItem;
        if (this.numW > 6) {
            this.numW = 6;
        }
        this.xStart = this.xBegin + MainTabNew.wblack / 2 - this.numW * MainTabNew.wOneItem / 2 + (GameCanvas.isSmallScreen ? 0 : this.numW / 2);
        this.yStart = this.yBegin + this.h12 * 10 - MainTabNew.wOneItem;
        this.numH = maxSize / this.numW;
        this.nameTab = string;
        if (string == null || string.length() == 0) {
            this.nameTab = "Name Tab";
        }
        this.cmdCloseChange = new iCommand(T.close, -2, this);
        this.cmdBack = new iCommand(T.back, -1, this);
        if (GameCanvas.isTouch) {
            this.cmdBack.caption = T.close;
        }
        this.cmdChange = new iCommand(T.change, 0, this);
        this.cmdChangeEquip = new iCommand(T.equip, 1, this);
        this.cmdPetInfo = new iCommand(T.info, 2, this);
        this.cmdPetFeed = new iCommand(T.choan, 3, this);
        this.cmdNexTab = new iCommand(T.trangbi2, 4, this);
        this.cmdReturn = new iCommand(T.trangbi1, 5, this);
        this.cmdxem = new iCommand(T.xem, 6, this);
        if (!GameCanvas.isTouch) {
            this.left = this.cmdNexTab;
        }
        this.indexTab = 0;
    }

    public void init() {
        this.idSelect = GameCanvas.isTouch ? (byte)-1 : (byte)0;
        this.isList = false;
        this.listContent = null;
        if (!GameCanvas.isTouch) {
            this.right = this.cmdBack;
            this.center = this.cmdChange;
        }
        MainTabNew.timePaintInfo = 0;
        super.init();
    }

    public void backTab() {
        MainTabNew.Focus = 0;
        this.idSelect = GameCanvas.isTouch ? (byte)-1 : (byte)0;
        super.backTab();
    }

    public void commandPointer(int n, int n2) {
        if (this.idSelect == -1 && n != -1) {
            return;
        }
        switch (n) {
            case -1: {
                this.backTab();
                break;
            }
            case 0: {
                mSystem.outz("Command pointer tab myself index = " + n);
                int n3 = MainTemplateItem.mItem_Equip_Tem[this.idSelect + this.indexTab];
                Item item = (Item)Item.VecEquipPlayer.get("" + (this.idSelect + this.indexTab));
                if (item == null) {
                    if (n3 == -2) {
                        return;
                    }
                    if (n3 == -1) {
                        n3 = GameScreen.player.clazz == 2 ? 11 : (GameScreen.player.clazz == 3 ? 10 : 8 + GameScreen.player.clazz);
                    }
                } else {
                    n3 = item.type_Only_Item;
                }
                if (n3 == 14) {
                    if (item != null) {
                        GameCanvas.start_Pet_Info((PetItem)item, (byte)1);
                    }
                    return;
                }
                this.listItemMenu(n3);
                if (this.vecItemMenu != null && this.vecItemMenu.size() > 0) {
                    TabScreenNew.timeRepaint = 10;
                    this.maxList = this.vecItemMenu.size();
                    if (this.maxList > this.numW) {
                        this.maxList = this.numW;
                    }
                    int n4 = this.xStart + this.idSelect % this.numW * this.wsize - this.maxList * this.wsize / 2 + MainTabNew.wOneItem / 2;
                    int n5 = this.yStart + this.idSelect / this.numW * this.wsize + MainTabNew.wOneItem + MainTabNew.wOne5;
                    if (n4 < this.xBegin + MainTabNew.wOne5 * 2) {
                        n4 = this.xBegin + MainTabNew.wOne5 * 2;
                    } else if (n4 + this.maxList * this.wsize + MainTabNew.wOne5 > this.xBegin + MainTabNew.wblack - MainTabNew.wOne5) {
                        n4 = this.xBegin + MainTabNew.wblack - MainTabNew.wOne5 - (this.maxList * this.wsize + MainTabNew.wOne5);
                    }
                    if (n5 + MainTabNew.wOneItem + MainTabNew.wOne5 * 2 > GameCanvas.h - GameCanvas.hCommand / 2) {
                        n5 = GameCanvas.h - GameCanvas.hCommand / 2 - MainTabNew.wOneItem - MainTabNew.wOne5 * 2;
                    }
                    this.xList = n4;
                    this.yList = n5;
                    MainScreen.cameraSub.setAllTo((this.vecItemMenu.size() - this.maxList) * this.wsize, 0, 0, 0);
                    this.list = new ListNew(this.xList, this.yList, this.maxList * this.wsize, this.wsize, 0, 0, (this.vecItemMenu.size() - this.maxList) * this.wsize);
                    this.isList = true;
                    mItemInfo = null;
                    mItemInfoShow = null;
                    if (!GameCanvas.isTouch) {
                        this.center = this.cmdChangeEquip;
                        this.right = !GameCanvas.isTouch ? this.cmdCloseChange : null;
                    }
                    MainTabNew.timePaintInfo = 0;
                    break;
                }
                if (!GameCanvas.isTouch) {
                    this.right = this.cmdBack;
                    this.center = this.cmdChange;
                }
                this.isList = false;
                GameCanvas.start_Ok_Dialog(T.khongcovatphanphuhop);
                break;
            }
            case 1: {
                Item item = (Item)this.vecItemMenu.elementAt(this.selectList);
                if (item.setNameNull()) {
                    return;
                }
                GlobalService.gI().Use_Item((byte)item.Id, this.idSelect);
                MainTabNew.timePaintInfo = 0;
                TabScreenNew.timeRepaint = 10;
                if (!GameCanvas.isTouch) {
                    this.right = this.cmdBack;
                    this.center = this.cmdChange;
                }
                this.isList = false;
                this.vecItemMenu.removeAllElements();
                this.selectList = 0;
                break;
            }
            case -2: {
                MainTabNew.timePaintInfo = 0;
                TabScreenNew.timeRepaint = 10;
                if (!GameCanvas.isTouch) {
                    this.right = this.cmdBack;
                    this.center = this.cmdChange;
                }
                this.isList = false;
                this.vecItemMenu.removeAllElements();
                this.selectList = 0;
                break;
            }
            case 2: {
                Item item = (Item)Item.VecEquipPlayer.get("" + this.idSelect);
                if (item == null || item.ItemCatagory != 9) break;
                GameCanvas.start_Pet_Info((PetItem)item, (byte)1);
                break;
            }
            case 3: {
                Item item = (Item)Item.VecEquipPlayer.get("" + this.idSelect);
                if (item != null && item.ItemCatagory == 9) {
                    GameCanvas.start_Pet_Info((PetItem)item, (byte)1);
                }
                mVector mVector2 = new mVector("TabMySeftNew vecItemMenu");
                TabShopNew tabShopNew = new TabShopNew(Item.VecInvetoryPlayer, 0, T.choan, -1, TabShopNew.INVEN_FOOD_PET);
                tabShopNew.petCur = MsgDialog.pet;
                mVector2.addElement(tabShopNew);
                GameCanvas.foodPet = new TabScreenNew();
                GameCanvas.foodPet.selectTab = 0;
                GameCanvas.foodPet.addMoreTab(mVector2);
                GameCanvas.foodPet.Show(GameCanvas.currentScreen);
                break;
            }
            case 4: {
                this.indexTab = (byte)12;
                if (GameCanvas.isTouch) {
                    this.vecListCmd = this.doMenu(null);
                    this.setPosCmd(this.vecListCmd);
                }
                if (GameCanvas.isTouch && (!mSystem.isj2me || !GameCanvas.isTouch)) break;
                this.left = this.cmdReturn;
                break;
            }
            case 5: {
                this.indexTab = 0;
                if (GameCanvas.isTouch) {
                    this.vecListCmd = this.doMenu(null);
                    this.setPosCmd(this.vecListCmd);
                }
                if (GameCanvas.isTouch && (!mSystem.isj2me || !GameCanvas.isTouch)) break;
                this.left = this.cmdNexTab;
                break;
            }
            case 6: {
                GlobalService.gI().requestinfoMount((byte)0);
            }
        }
        super.commandPointer(n, n2);
    }

    public void paint(mGraphics mGraphics2) {
        int n;
        GameScreen.player.paintShowPlayer(mGraphics2, this.xBegin + this.w5 + delta / 2, this.yBegin + this.h12 * 5 / 2 + 15, 0);
        mGraphics2.setColor(MainTabNew.color[1]);
        if (!GameCanvas.isSmallScreen) {
            mGraphics2.fillRect(this.xBegin + this.w5 / 4, this.yBegin + this.h12 * 6 - this.h12 / 2, this.w5 * 2 - this.w5 / 2 + delta, 1, false);
            mGraphics2.fillRect(this.xBegin + this.w5 * 2 + delta + this.w5 / 4, this.yBegin + this.h12 * 6 - this.h12 / 2, this.w5 * 2 + delta + this.w5 / 2, 1, false);
        }
        mGraphics2.fillRect(this.xBegin + this.w5 * 2 + delta, this.yBegin + this.h12 / 4, 1, this.h12 * 8 - this.h12 / 2, false);
        mFont.tahoma_7_orange.drawString(mGraphics2, "" + GameScreen.player.pointPk, this.xBegin + 15, this.yBegin + this.h12 * 6 - this.h12 / 2 + 4 - (GameCanvas.isSmallScreen ? 8 : 0), 0, false);
        mFont.tahoma_7_orange.drawString(mGraphics2, "" + Player.PointSucKhoe, this.xBegin + 15, this.yBegin + this.h12 * 6 - this.h12 / 2 + GameCanvas.hText + 4 - (GameCanvas.isSmallScreen ? 12 : 0), 0, false);
        mGraphics2.drawImage(MainTabNew.img_pkIcn, this.xBegin + 4, this.yBegin + this.h12 * 6 - this.h12 / 2 + 4 - (GameCanvas.isSmallScreen ? 8 : 0), 0, false);
        mGraphics2.drawImage(MainTabNew.img_skIcn, this.xBegin + 4, this.yBegin + this.h12 * 6 - this.h12 / 2 + GameCanvas.hText + 4 - (GameCanvas.isSmallScreen ? 12 : 0), 0, false);
        if (!GameCanvas.isSmallScreen) {
            for (n = 0; n < 5; ++n) {
                mGraphics2.drawRegion(MainTabNew.imgTab[5], 0, n * 10, 10, 10, 0, this.xBegin + this.w5 * 2 + delta + 10 + n % 3 * this.w5 + 3, this.yBegin + this.h12 * 6 - this.h12 / 2 + 9 + n / 3 * this.h12, 10, false);
                String string = "";
                if (this.isList) {
                    if (mItemInfoShow != null) {
                        string = meffskill[n] + "";
                    }
                } else {
                    string = GameScreen.player.mKhangChar[n];
                }
                mFont.tahoma_7_white.drawString(mGraphics2, string, this.xBegin + this.w5 * 2 + delta + 14 + n % 3 * this.w5, this.yBegin + this.h12 * 6 - this.h12 / 2 + 3 + n / 3 * this.h12, 0, false);
            }
        }
        this.paintArenaPoint(mGraphics2);
        mGraphics2.setColor(MainTabNew.color[4]);
        for (n = 0; n < maxSize; n = (int)((byte)(n + 1))) {
            int n2 = this.xStart + n % this.numW * this.wsize;
            int n3 = this.yStart + n / this.numW * this.wsize;
            Item item = (Item)Item.VecEquipPlayer.get("" + (n + this.indexTab));
            if (item != null) {
                if (item.Id > -1) {
                    item.paintItem(mGraphics2, n2 + MainTabNew.wOneItem / 2, n3 + MainTabNew.wOneItem / 2, MainTabNew.wOneItem, 0, 0);
                } else if (this.indexTab <= 0) {
                    mGraphics2.drawRegion(MainTabNew.imgTab[6], 0, n * 20, 20, 20, 0, n2 + MainTabNew.wOneItem / 2, n3 + MainTabNew.wOneItem / 2, 3, false);
                }
            } else if (this.indexTab <= 0) {
                mGraphics2.drawRegion(MainTabNew.imgTab[6], 0, n * 20, 20, 20, 0, n2 + MainTabNew.wOneItem / 2, n3 + MainTabNew.wOneItem / 2, 3, false);
            }
            mGraphics2.setColor(MainTabNew.color[4]);
            mGraphics2.drawRect(n2, n3, this.wsize, this.wsize, false);
        }
        mGraphics2.setColor(MainTabNew.color[3]);
        if (MainTabNew.Focus == 1 && this.idSelect >= 0) {
            n = this.xStart + this.idSelect % this.numW * this.wsize;
            int n4 = this.yStart + this.idSelect / this.numW * this.wsize;
            mGraphics2.drawRect(n, n4, this.wsize, this.wsize, false);
            mGraphics2.setColor(MainTabNew.color[2]);
            mGraphics2.drawRect(n + 1, n4 + 1, this.wsize - 2, this.wsize - 2, false);
        }
        if (this.isList) {
            if (mItemInfoShow != null) {
                this.paintInfoPlayer(mGraphics2, this.xBegin + this.w5 * 2 + 4 + delta, this.yBegin + 4, mItemInfoShow, this.isShowInfo);
            } else {
                mFont.tahoma_7_white.drawString(mGraphics2, T.danglaydulieu, this.xBegin + this.w5 * 2 + 4 + delta, this.yBegin + 4, 0, false);
            }
        } else {
            this.paintInfoPlayer(mGraphics2, this.xBegin + this.w5 * 2 + 4 + delta, this.yBegin + 4, GameScreen.player.mInfoChar, true);
        }
        if (MainTabNew.Focus == 1) {
            if ((MainTabNew.timePaintInfo > MainTabNew.timeRequest || this.isList && MainTabNew.timePaintInfo > 5) && !GameCanvas.menu2.isShowMenu && GameCanvas.currentDialog == null) {
                this.paintPopupContent(mGraphics2, MainTabNew.longwidth > 0 ? false : this.isList);
            }
            if (this.isList) {
                this.paintList(mGraphics2);
            }
        }
        if (this.vecListCmd != null) {
            for (n = 0; n < this.vecListCmd.size(); ++n) {
                iCommand iCommand2 = (iCommand)this.vecListCmd.elementAt(n);
                iCommand2.paint(mGraphics2, iCommand2.xCmd, iCommand2.yCmd);
            }
        }
    }

    public void paintList(mGraphics mGraphics2) {
        int n = this.vecItemMenu.size();
        int n2 = MainTabNew.wOneItem + MainTabNew.wOne5 * 2;
        if (n2 < 32) {
            n2 = 32;
        }
        AvMain.paintDialog(mGraphics2, this.xList - this.maxList / 2 - MainTabNew.wOne5, this.yList - MainTabNew.wOne5, this.maxList * MainTabNew.wOneItem + MainTabNew.wOne5 * 2, n2, 12);
        mGraphics2.setClip(this.xList + (n == 1 ? 1 : 0), this.yList, this.maxList * this.wsize + 2, MainTabNew.wOneItem + MainTabNew.wOne5 * 2);
        mGraphics2.saveCanvas();
        mGraphics2.ClipRec(this.xList + (n == 1 ? 1 : 0), this.yList, this.maxList * this.wsize + 2, MainTabNew.wOneItem + MainTabNew.wOne5 * 2);
        mGraphics2.translate(-MainScreen.cameraSub.xCam, 0);
        for (int i = 0; i < n; ++i) {
            Item item = (Item)this.vecItemMenu.elementAt(i);
            item.paintItem(mGraphics2, this.xList + i * this.wsize + MainTabNew.wOneItem / 2 + (n == 1 ? 1 : 0), this.yList + MainTabNew.wOneItem / 2, MainTabNew.wOneItem, 0, 0);
        }
        if (n > 0) {
            mGraphics2.setColor(MainTabNew.color[2]);
            mGraphics2.drawRect(this.xList + this.selectList * this.wsize + (n == 1 ? 1 : 0), this.yList + 1, this.wsize, this.wsize, false);
        }
        mGraphics.resetTransAndroid(mGraphics2);
        mGraphics2.restoreCanvas();
        mGraphics2.endClip();
        GameCanvas.resetTrans(mGraphics2);
    }

    public void paintInfoPlayer(mGraphics mGraphics2, int n, int n2, MainInfoItem[] mainInfoItemArray, boolean bl) {
        if (mainInfoItemArray != null) {
            for (int i = 0; i < mainInfoItemArray.length; ++i) {
                int n3;
                Object object;
                MainInfoItem mainInfoItem = mainInfoItemArray[i];
                if (mainInfoItem == null || mainInfoItem.id > 4 && mainInfoItem.id != 40 && mainInfoItem.id != 14 || mainInfoItem.value == 0) continue;
                mFont mFont2 = mFont.tahoma_7_white;
                mFont2 = !bl ? mFont.tahoma_7_black : MainTabNew.setTextColor(Item.colorInfoItem[mainInfoItem.id]);
                String string = Item.nameInfoItem[mainInfoItem.id] + ": " + Item.getPercent(Item.isPercentInfoItem[mainInfoItem.id], mainInfoItem.value);
                mFont2.drawString(mGraphics2, string, n, n2, 0, false);
                int n4 = 0;
                if (GameScreen.player.vecBuff != null && !GameCanvas.isSmallScreen) {
                    for (int j = 0; j < GameScreen.player.vecBuff.size(); ++j) {
                        object = (MainBuff)GameScreen.player.vecBuff.elementAt(j);
                        if (((MainBuff)object).minfo == null) continue;
                        for (n3 = 0; n3 < ((MainBuff)object).minfo.length; ++n3) {
                            if (mainInfoItem.id != ((MainBuff)object).minfo[n3].id) continue;
                            n4 += ((MainBuff)object).minfo[n3].value;
                        }
                    }
                }
                if (n4 != 0) {
                    String string2 = "";
                    object = mFont.tahoma_7_green;
                    if (n4 > 0) {
                        string2 = " +" + Item.getPercent(Item.isPercentInfoItem[mainInfoItem.id], n4);
                    } else {
                        string2 = " " + Item.getPercent(Item.isPercentInfoItem[mainInfoItem.id], n4);
                        object = mFont.tahoma_7_red;
                    }
                    n3 = mFont.tahoma_7_white.getWidth(string);
                    ((mFont)object).drawString(mGraphics2, " " + string2, n + n3, n2, 0, false);
                }
                n2 += GameCanvas.hText;
            }
        }
    }

    public void update() {
        if (MainTabNew.Focus == 1) {
            if (this.listContent != null) {
                this.listContent.moveCamera();
            }
            if (this.isList) {
                if (GameCanvas.isTouch) {
                    this.list.moveCamera();
                } else {
                    MainScreen.cameraSub.UpdateCamera();
                }
            }
            this.updateContent();
        } else {
            MainTabNew.timePaintInfo = 0;
        }
        GameScreen.player.updateEye();
    }

    public void updateContent() {
        if (this.idSelect == -1) {
            return;
        }
        if (MainTabNew.timePaintInfo < MainTabNew.timeRequest + 2) {
            ++MainTabNew.timePaintInfo;
            if (this.isList) {
                if (MainTabNew.timePaintInfo == 5) {
                    this.setPaintInfo();
                }
            } else if (MainTabNew.timePaintInfo == MainTabNew.timeRequest) {
                this.setPaintInfo();
            }
        } else if (this.isList && !this.isShowInfo) {
            if (mItemInfo == null) {
                MainItem mainItem;
                ++this.timeUpdateInfo;
                if (this.timeUpdateInfo % 100 == 3 && (mainItem = (MainItem)this.vecItemMenu.elementAt(this.selectList)) != null) {
                    GlobalService.gI().Item_More_Info(this.idSelect, (byte)mainItem.Id);
                }
            } else {
                this.mColorInfo = new int[mItemInfo.length];
                block0: for (int i = 0; i < mItemInfo.length; ++i) {
                    MainInfoItem mainInfoItem = mItemInfo[i];
                    this.mColorInfo[i] = 0;
                    for (int j = 0; j < GameScreen.player.mInfoChar.length; ++j) {
                        MainInfoItem mainInfoItem2 = GameScreen.player.mInfoChar[i];
                        if (mainInfoItem.id != mainInfoItem2.id) continue;
                        if (mainInfoItem.value > mainInfoItem2.value) {
                            this.mColorInfo[i] = 2;
                            continue block0;
                        }
                        if (mainInfoItem.value >= mainInfoItem2.value) continue block0;
                        this.mColorInfo[i] = 3;
                        continue block0;
                    }
                }
                this.isShowInfo = true;
            }
        }
    }

    public void updatekey() {
        if (MainTabNew.Focus == 1) {
            if (this.isList) {
                if (this.listContent != null) {
                    if (GameCanvas.keyMyHold[2]) {
                        this.listContent.cmtoX = this.listContent.cmtoX > 0 ? (this.listContent.cmtoX -= GameCanvas.hText) : 0;
                        GameCanvas.clearKeyHold(2);
                    } else if (GameCanvas.keyMyHold[8]) {
                        this.listContent.cmtoX = this.listContent.cmtoX < this.listContent.cmxLim ? (this.listContent.cmtoX += GameCanvas.hText) : this.listContent.cmxLim;
                        GameCanvas.clearKeyHold(8);
                    }
                }
                int n = this.selectList--;
                if (GameCanvas.keyMyHold[4]) {
                    GameCanvas.clearKeyHold(4);
                } else if (GameCanvas.keyMyHold[6]) {
                    ++this.selectList;
                    GameCanvas.clearKeyHold(6);
                }
                this.selectList = this.resetSelect(this.selectList, this.vecItemMenu.size() - 1, true);
                if (n != this.selectList) {
                    MainScreen.cameraSub.moveCamera(this.selectList * this.wsize - this.maxList * this.wsize / 2, 0);
                    MainTabNew.timePaintInfo = 0;
                }
            } else {
                byte by = this.idSelect;
                if (this.listContent != null) {
                    if (GameCanvas.keyMyHold[2]) {
                        this.listContent.cmtoX = this.listContent.cmtoX > 0 ? (this.listContent.cmtoX -= GameCanvas.hText) : 0;
                        GameCanvas.clearKeyHold(2);
                    } else if (GameCanvas.keyMyHold[8]) {
                        this.listContent.cmtoX = this.listContent.cmtoX < this.listContent.cmxLim ? (this.listContent.cmtoX += GameCanvas.hText) : this.listContent.cmxLim;
                        GameCanvas.clearKeyHold(8);
                    }
                } else if (GameCanvas.keyMyHold[2]) {
                    if (this.idSelect >= this.numW) {
                        this.idSelect = (byte)(this.idSelect - this.numW);
                    }
                    GameCanvas.clearKeyHold(2);
                } else if (GameCanvas.keyMyHold[8]) {
                    if (this.idSelect < maxSize - this.numW) {
                        this.idSelect = (byte)(this.idSelect + this.numW);
                    }
                    GameCanvas.clearKeyHold(8);
                }
                if (GameCanvas.keyMyHold[4]) {
                    if (this.idSelect % this.numW == 0) {
                        MainTabNew.Focus = 0;
                    } else {
                        this.idSelect = (byte)(this.idSelect - 1);
                    }
                    GameCanvas.clearKeyHold(4);
                } else if (GameCanvas.keyMyHold[6]) {
                    this.idSelect = (byte)(this.idSelect + 1);
                    GameCanvas.clearKeyHold(6);
                }
                if (this.idSelect >= 0) {
                    this.idSelect = (byte)this.resetSelect(this.idSelect, maxSize - 1, false);
                } else {
                    this.idSelect = (byte)-1;
                    this.vecListCmd = null;
                }
                if (by != this.idSelect) {
                    MainTabNew.timePaintInfo = 0;
                    this.listContent = null;
                }
            }
        }
        super.updatekey();
    }

    public void setPaintInfo() {
        if (this.idSelect == -1 && !this.isList) {
            return;
        }
        Item item = null;
        item = this.isList ? (Item)this.vecItemMenu.elementAt(this.selectList) : (Item)Item.VecEquipPlayer.get("" + (this.idSelect + this.indexTab));
        if (item == null) {
            MainTabNew.timePaintInfo = 0;
            return;
        }
        if (item.setNameNull()) {
            MainTabNew.timePaintInfo = 0;
            return;
        }
        if (item.ItemCatagory == 9) {
            MsgDialog.pet = (PetItem)item;
            this.isPet = true;
        } else {
            this.isPet = false;
        }
        int n = 0;
        this.mContent = item.mcontent;
        this.moreInfoconten = item.moreContenGem;
        this.mPlusContent = item.mPlusContent;
        this.mPlusColor = item.mPlusColor;
        this.mcolor = item.mColor;
        this.name = item.itemName;
        this.colorName = item.colorNameItem;
        if (this.isList) {
            mItemInfo = null;
            this.timeUpdateInfo = 0;
            this.isShowInfo = false;
            int n2 = mFont.tahoma_7b_white.getWidth(item.itemName) + 8;
            if (n2 < 40) {
                n2 = 40;
            }
            this.wContent = n2;
        }
        this.listContent = null;
        if (MainTabNew.longwidth > 0) {
            n = this.mContent == null ? ++n : (n += this.mContent.length);
            if (this.mPlusContent != null) {
                n += this.mPlusContent.length;
            }
            if (n * GameCanvas.hText > MainTabNew.hMaxContent) {
                this.listContent = new ListNew(MainTabNew.xlongwidth, MainTabNew.ylongwidth, MainTabNew.longwidth, MainTabNew.hMaxContent, 0, 0, n * GameCanvas.hText - MainTabNew.hMaxContent + 4 * GameCanvas.hText);
            } else if (GameCanvas.isTouch) {
                this.listContent = new ListNew(MainTabNew.xlongwidth, MainTabNew.ylongwidth, MainTabNew.longwidth, MainTabNew.hMaxContent, 0, 0, 4 * GameCanvas.hText);
            }
            return;
        }
        n = this.mContent == null ? ++n : (n += this.mContent.length);
        if (this.mPlusContent != null) {
            n += this.mPlusContent.length;
        }
        if (this.isList) {
            this.yCon = this.yList - GameCanvas.hText * 2 + MainTabNew.wOne5 / 2;
            this.mSubContent = null;
            this.mContent = null;
            this.mPlusContent = null;
            this.xCon = this.xList + this.maxList * MainTabNew.wOneItem / 2 - this.wContent / 2;
        } else {
            this.yCon = this.yStart + this.idSelect / this.numW * MainTabNew.wOneItem - 9 - (n + 1) * GameCanvas.hText;
            this.wContent = item.sizeW;
            this.xCon = this.idSelect % this.numW < 2 ? this.xStart + MainTabNew.wOneItem / 2 + this.idSelect % this.numW * MainTabNew.wOneItem : (this.idSelect % this.numW < 4 ? this.xStart + MainTabNew.wOneItem / 2 + this.idSelect % this.numW * MainTabNew.wOneItem - 45 : this.xStart + MainTabNew.wOneItem / 2 + this.idSelect % this.numW * MainTabNew.wOneItem - 90);
        }
        if (this.yCon + MainScreen.cameraSub.yCam < 2) {
            this.yCon = 2 - MainScreen.cameraSub.yCam;
        }
        if ((n + 1) * GameCanvas.hText > MainTabNew.hMaxContent) {
            this.listContent = new ListNew(this.xCon, this.yCon, this.wContent, MainTabNew.hMaxContent, 0, 0, (n + 1) * GameCanvas.hText - MainTabNew.hMaxContent);
        }
    }

    public void listItemMenu(int n) {
        this.vecItemMenu.removeAllElements();
        for (int i = 0; i < Item.VecInvetoryPlayer.size(); ++i) {
            Item item = (Item)Item.VecInvetoryPlayer.elementAt(i);
            if (item.ItemCatagory != 3 || item.type_Only_Item != n || item.classcharItem != GameScreen.player.clazz && item.classcharItem <= 3) continue;
            this.vecItemMenu.addElement(item);
        }
    }

    public void updatePointer() {
        Object object;
        int n;
        boolean bl = false;
        if (this.isList) {
            if (this.listContent != null && GameCanvas.isPoint(this.listContent.x, this.listContent.y, this.listContent.maxW, this.listContent.maxH)) {
                this.listContent.update_Pos_UP_DOWN();
                bl = true;
            }
            if (GameCanvas.isTouch && !bl) {
                this.list.updatePos_LEFT_RIGHT();
                MainScreen.cameraSub.xCam = this.list.cmx;
            }
            if (GameCanvas.isPointerSelect && !bl) {
                if (GameCanvas.isPoint(this.xList, this.yList, this.wsize * this.maxList, MainTabNew.wOneItem)) {
                    n = (byte)((MainScreen.cameraSub.xCam + GameCanvas.px - this.xList) / this.wsize);
                    if (n >= 0 && n < this.vecItemMenu.size()) {
                        if (n == this.selectList) {
                            this.cmdChangeEquip.perform();
                        } else {
                            this.selectList = n;
                            MainTabNew.timePaintInfo = 0;
                        }
                        this.listContent = null;
                    }
                    GameCanvas.isPointerSelect = false;
                } else if (!GameCanvas.isPoint(0, GameCanvas.h - GameCanvas.hCommand, GameCanvas.w, GameCanvas.hCommand)) {
                    this.cmdCloseChange.perform();
                    GameCanvas.isPointerSelect = false;
                }
            }
        } else {
            if (this.listContent != null && GameCanvas.isPoint(this.listContent.x, this.listContent.y, this.listContent.maxW, this.listContent.maxH)) {
                this.listContent.update_Pos_UP_DOWN();
                bl = true;
            }
            if (GameCanvas.isPointSelect(this.xStart, this.yStart, this.wsize * this.numW, this.wsize * this.numH) && !bl) {
                GameCanvas.isPointerSelect = false;
                n = (byte)((GameCanvas.px - this.xStart) / this.wsize + (GameCanvas.py - this.yStart) / this.wsize * this.numW);
                if (n >= 0 && n < maxSize) {
                    if (n == this.idSelect) {
                        this.cmdChange.perform();
                    } else {
                        this.idSelect = (byte)n;
                        MainTabNew.timePaintInfo = 0;
                        if (this.idSelect >= 0) {
                            object = (Item)Item.VecEquipPlayer.get("" + (this.idSelect + this.indexTab));
                            if (object != null) {
                                if (MainTabNew.longwidth > 0) {
                                    this.vecListCmd = this.doMenu((Item)object);
                                    this.setPosCmd(this.vecListCmd);
                                }
                            } else if (this.indexTab > 0) {
                                this.vecListCmd = this.doMenu((Item)object);
                                this.setPosCmd(this.vecListCmd);
                            }
                        }
                    }
                    this.listContent = null;
                    if (MainTabNew.Focus != 1) {
                        MainTabNew.Focus = 1;
                    }
                }
            }
        }
        if (this.vecListCmd != null) {
            for (n = 0; n < this.vecListCmd.size(); ++n) {
                object = (iCommand)this.vecListCmd.elementAt(n);
                if (object == null) continue;
                ((iCommand)object).updatePointer();
            }
        }
        super.updatePointer();
    }

    public mVector doMenu(Item item) {
        mVector mVector2 = new mVector("TabMySeftNew menu");
        if (this.indexTab <= 0) {
            mVector2.addElement(this.cmdNexTab);
        } else if (this.indexTab > 0) {
            mVector2.addElement(this.cmdReturn);
        }
        if (item != null && item.type_Only_Item == 14) {
            mVector2.addElement(this.cmdPetFeed);
        }
        if (this.idSelect == 9 && this.indexTab == 12) {
            mVector2.addElement(this.cmdxem);
        }
        return mVector2;
    }

    public void paintArenaPoint(mGraphics mGraphics2) {
        if (!GameCanvas.isSmallScreen) {
            int n = 5;
            mGraphics2.drawRegion(MainTabNew.img_arenaIcn, 0, 0, 10, 9, 0, this.xBegin + this.w5 * 2 + delta + 10 + n % 3 * this.w5 + 3, this.yBegin + this.h12 * 6 - this.h12 / 2 + 9 + n / 3 * this.h12, 10, false);
            mFont.tahoma_7_orange.drawString(mGraphics2, "" + Player.PointArena, this.xBegin + this.w5 * 2 + delta + 14 + n % 3 * this.w5, this.yBegin + this.h12 * 6 - this.h12 / 2 + 3 + n / 3 * this.h12, 0, false);
        } else {
            mFont.tahoma_7_orange.drawString(mGraphics2, "" + Player.PointArena, this.xBegin + 15, this.yBegin + this.h12 * 6 - this.h12 / 2 + 2 * GameCanvas.hText + 3 - (GameCanvas.isSmallScreen ? 16 : 0), 0, false);
            mGraphics2.drawImage(MainTabNew.img_arenaIcn, this.xBegin + 4, this.yBegin + this.h12 * 6 - this.h12 / 2 + 2 * GameCanvas.hText + 4 - (GameCanvas.isSmallScreen ? 16 : 0), 0, false);
        }
    }

    static {
        meffskill = new String[5];
    }

    public class infoItem {
        short index;
        MainInfoItem[] mInfo;

        public infoItem(MainInfoItem[] mainInfoItemArray, short s) {
            this.mInfo = mainInfoItemArray;
            this.index = s;
        }
    }
}

