/*
 * Decompiled with CFR 0.152.
 */
package InterfaceComponents;

import CLib.TField;
import CLib.mFont;
import CLib.mGraphics;
import CLib.mHashtable;
import CLib.mImage;
import CLib.mSystem;
import CLib.mVector;
import GameObjects.Item;
import GameObjects.MainItem;
import GameObjects.MainObject;
import GameObjects.ObjectData;
import GameObjects.PetItem;
import GameObjects.Player;
import GameObjects.item_sell;
import GameScreen.GameScreen;
import GameScreen.MainScreen;
import GameScreen.PaintInfoGameScreen;
import GameScreen.TabScreenNew;
import InterfaceComponents.InputDialog;
import InterfaceComponents.ListNew;
import InterfaceComponents.MainTabNew;
import InterfaceComponents.MsgDialog;
import InterfaceComponents.TabRebuildItem;
import InterfaceComponents.TabSkillsNew;
import InterfaceComponents.iCommand;
import Main.GameCanvas;
import Model.AvMain;
import Model.CRes;
import Model.FrameImage;
import Model.MainImage;
import Model.MainTemplateItem;
import Model.Menu2;
import Model.T;
import Skill.HotKey;
import netcommand.global.GlobalService;
import netcommand.global.ReadMessenge;

public class TabShopNew
extends MainTabNew {
    public static byte NORMAL = 0;
    public static byte TOC = 1;
    public static byte INVEN_AND_CHEST = (byte)2;
    public static byte INVEN_AND_REBUILD = (byte)3;
    public static byte SHOP_BANG = (byte)4;
    public static byte INVEN_BANG = (byte)5;
    public static byte INVEN_AND_REPLACE = (byte)6;
    public static byte INVEN_AND_WING = (byte)7;
    public static byte WING = (byte)7;
    public static byte INVEN_AND_PET_KEEPER = (byte)8;
    public static byte INVEN_FOOD_PET = (byte)9;
    public static byte INVEN_LOTTERY = (byte)10;
    public static byte SHOP_VANTIEU = (byte)11;
    public static byte SHOP_KHAM_NGOC = (byte)12;
    public static byte SHOP_GHEP_NGOC = (byte)13;
    public static byte SHOP_DUC_LO = (byte)14;
    public static byte SHOP_STORE_OTHER_PLAYER = (byte)15;
    public static byte SHOP_ANY_NGUYEN_LIEU = (byte)16;
    public static byte SHOP_HOP_AN = (byte)17;
    public static byte SHOP_NANG_CAP_MEDEL = (byte)18;
    public static byte UPDATE = (byte)-1;
    public static byte GET_CHEST = 0;
    public static byte SET_CHEST = 1;
    public static byte UPDATE_PET_EAT = (byte)2;
    public static byte WITHDRAW_PET = 0;
    public static byte DEPOSIT_PET = 1;
    public static boolean isTabHopNL;
    public static short priceSellPotion;
    public static short priceSellItem;
    public static short hesoLv;
    public static short hesoColor;
    public static short priceSellQuest;
    public static short maxPriceItem;
    public static short PriceIconClan;
    public static short PriceChatWorld;
    public static boolean isSortInven;
    int IDTab;
    int numW = 6;
    int numH = 6;
    int numHPaint;
    int maxSize = 60;
    int idSelect = 0;
    int hcmd = 0;
    InputDialog inputWorld;
    String textWorld = "";
    mVector vecShop = new mVector("TabShopNew vecShop");
    public static int IdTabSave;
    public PetItem petCur = null;
    public byte numofGem;
    public static short idSeller;
    public boolean isShop_Other_Player = false;
    private byte currentTab;
    public static byte maxTab;
    public static mHashtable allidNgocKham;
    iCommand cmdHoiMua;
    iCommand cmdSelect;
    iCommand cmdBuyPotion;
    iCommand cmdMua;
    iCommand cmdHoiXoaItem;
    iCommand cmdXoaItem;
    iCommand cmdMenuInven;
    iCommand cmdSetKey;
    iCommand cmdHoiSell;
    iCommand cmdSell;
    iCommand cmdGetChest;
    iCommand cmdSetChest;
    iCommand cmdGetPotionChest;
    iCommand cmdSetPotionChest;
    iCommand cmdSellMore;
    iCommand cmdRebuild;
    iCommand cmdUnRebuild;
    iCommand cmdNextIcon;
    iCommand cmdReplace;
    iCommand cmdWing;
    iCommand cmdCreateWing;
    iCommand cmdDepositPet;
    iCommand cmdWithdrawPet;
    iCommand cmdFoodPet;
    iCommand cmdPetInfo;
    iCommand cmdPetFeed;
    iCommand cmdSelectReward;
    iCommand cmdHopNguyeLieu;
    iCommand cmdMua1;
    iCommand cmdMua10;
    iCommand cmdMua30;
    iCommand cmdKhamgoc;
    iCommand cmdDuclo;
    iCommand cmdGhepNgoc;
    iCommand cmdBovao;
    iCommand cmdLayra;
    iCommand cmdMuaNhieu;
    iCommand cmdHoidaugia;
    iCommand cmdDaugia;
    iCommand cmdHoanThanh;
    iCommand cmdOkSell;
    iCommand cmdBuyItemOtherplayer;
    iCommand cmdhuy;
    iCommand cmdNghiban;
    iCommand cmdChatWorld;
    iCommand cmdlaynguyenlieura;
    iCommand cmdHoprac;
    iCommand cmdlayracra;
    iCommand cmdbanNguyenLieu;
    iCommand cmdHopAn;
    iCommand cmdNextInven;
    iCommand cmdKhacItem;
    InputDialog inputDialog;
    ListNew list;
    mVector vecListCmd = new mVector("list cmd");
    byte isTypeShop = 0;
    String nameCmdBuy = "";
    boolean isShop;

    public TabShopNew(mVector mVector2, byte by, String string, int n, byte by2) {
        this.typeTab = by;
        this.isShop = false;
        this.xBegin = this.xTab + MainTabNew.wOneItem + MainTabNew.wOne5 * 3;
        this.yBegin = this.yTab + GameCanvas.h / 5 + MainTabNew.wOneItem + 1;
        this.numW = MainTabNew.wblack / MainTabNew.wOneItem;
        this.numHPaint = MainTabNew.hblack / MainTabNew.wOneItem;
        this.maxSize = mVector2.size();
        if (this.maxSize % this.numW != 0) {
            this.maxSize += this.numW - this.maxSize % this.numW;
        }
        if (this.typeTab == 0 || this.typeTab == 16) {
            this.vecShop = Item.VecInvetoryPlayer;
            if (this.maxSize < Player.maxInven) {
                this.maxSize = Player.maxInven;
            }
        } else if (this.typeTab == 15) {
            this.vecShop = Item.VecItemSell;
        } else if (this.typeTab == 9) {
            this.vecShop = Item.VecChestPlayer;
            if (this.maxSize < Player.maxChest) {
                this.maxSize = Player.maxChest;
            }
        } else if (this.typeTab == 13) {
            this.vecShop = Item.VecPetContainer;
            if (this.maxSize < Player.maxPet) {
                this.maxSize = Player.maxChest;
            }
        } else if (this.typeTab == 12) {
            this.vecShop = Item.VecClanInvetory;
        } else {
            this.vecShop = mVector2;
            IdTabSave = n;
            this.isShop = true;
        }
        this.maxSize = 42;
        this.currentTab = 0;
        maxTab = (byte)(Player.maxInven / 42);
        if (this.typeTab == 13) {
            maxTab = (byte)(Player.maxPet / 42);
        }
        if (this.isShop) {
            this.maxSize = this.vecShop.size();
        }
        this.numH = (this.maxSize - 1) / this.numW + 1;
        this.nameTab = string;
        this.IDTab = n;
        this.isTypeShop = by2;
        if (by2 == SHOP_KHAM_NGOC) {
            this.numofGem = (byte)2;
        }
        if (by2 == SHOP_GHEP_NGOC || by2 == SHOP_ANY_NGUYEN_LIEU) {
            this.numofGem = (byte)5;
        }
        if (string == null || string.length() == 0) {
            this.nameTab = "Name Tab";
        }
        this.cmdHoiMua = new iCommand(T.buy, 0, this);
        this.cmdSelect = new iCommand(T.use, 6, this);
        this.cmdBack = new iCommand(T.back, -1, this);
        if (GameCanvas.isTouch) {
            this.cmdBack.caption = T.close;
        }
        this.cmdBuyPotion = new iCommand(T.buy, 2, this);
        this.cmdMua = new iCommand(T.buy, 3, this);
        this.cmdHoiXoaItem = new iCommand(T.vucbo, 4, this);
        this.cmdXoaItem = new iCommand(T.vucbo, 5, this);
        this.cmdMenuInven = new iCommand(T.select, 7, this);
        this.cmdSetKey = new iCommand(T.setKey, 8, this);
        this.cmdHoiSell = new iCommand(T.sell, 11, this);
        this.cmdSell = new iCommand(T.sell, 10, this);
        this.cmdGetChest = new iCommand(T.layra, 13, this);
        this.cmdSetChest = new iCommand(T.catvao, 14, this);
        this.cmdGetPotionChest = new iCommand(T.layra, 15, this);
        this.cmdSetPotionChest = new iCommand(T.catvao, 16, this);
        this.cmdSellMore = new iCommand(T.sellmore, 17, this);
        this.cmdRebuild = new iCommand(T.dapdo, 20, this);
        this.cmdUnRebuild = new iCommand(T.layra, 21, this);
        this.cmdNextIcon = new iCommand(T.next, 22, this);
        this.cmdReplace = new iCommand(T.replace, 23, this);
        this.cmdWing = new iCommand(T.nangcap, 24, this);
        this.cmdCreateWing = new iCommand(T.taoCanh, 3, this);
        this.cmdDepositPet = new iCommand(T.aptrung, 25, this);
        this.cmdWithdrawPet = new iCommand(T.use, 26, this);
        this.cmdFoodPet = new iCommand(T.choan, 27, this);
        this.cmdPetInfo = new iCommand(T.info, 28, this);
        this.cmdPetFeed = new iCommand(T.choan, 29, this);
        this.cmdHopNguyeLieu = new iCommand(T.hopThanh, 30, this);
        this.cmdMua1 = new iCommand(T.buy + " 1", 31, this);
        this.cmdMua10 = new iCommand(T.buy + " 10", 32, this);
        this.cmdMua30 = new iCommand(T.buy + " 30", 33, this);
        this.cmdKhamgoc = new iCommand(T.khamNgoc, 36, this);
        this.cmdBovao = new iCommand(T.bovao, 37, this);
        this.cmdGhepNgoc = new iCommand(T.bovao, 38, this);
        this.cmdLayra = new iCommand(T.layra, 39, this);
        this.cmdMuaNhieu = new iCommand(T.muaNhieu, 0, this);
        this.cmdDuclo = new iCommand(T.DucLo, 40, this);
        this.cmdHoidaugia = new iCommand(T.daugia, 41, this);
        this.cmdDaugia = new iCommand(T.daugia, 42, this);
        this.cmdHoanThanh = new iCommand(T.hoanthanh, 44, this);
        this.cmdOkSell = new iCommand(T.hoanthanh, 45, this);
        this.cmdBuyItemOtherplayer = new iCommand(T.buy, 46, this);
        this.cmdhuy = new iCommand(T.cancel, 47, this);
        this.cmdNghiban = new iCommand(T.NghiBan, 48, this);
        this.cmdChatWorld = new iCommand(T.text2kenhthegioi, 49, this);
        this.cmdHoprac = new iCommand(T.hoprac, 52, this);
        this.cmdlayracra = new iCommand(T.layra, 53, this);
        this.cmdHopAn = new iCommand(T.bovao, 54, this);
        this.cmdbanNguyenLieu = new iCommand(T.daugia, 55, this);
        this.cmdNextInven = new iCommand(T.tabhanhtrang, 56, this);
        this.cmdKhacItem = new iCommand(T.khac, 57, this);
        this.init();
    }

    public void setNameCmd(String string) {
        this.nameCmdBuy = string;
        this.cmdHoiMua.caption = string;
        this.cmdMua.caption = string;
    }

    public void init() {
        this.idSelect = GameCanvas.isTouch ? -1 : 0;
        MainTabNew.timePaintInfo = 0;
        int n = 0;
        if (this.typeTab == 0 || this.typeTab == 16) {
            if (isSortInven) {
                Item.VecInvetoryPlayer = CRes.selectionSortInven(Item.VecInvetoryPlayer);
                isSortInven = false;
            }
            n = Item.VecInvetoryPlayer.size();
        } else {
            n = this.typeTab == 15 ? Item.VecItemSell.size() : (this.typeTab == 9 ? Item.VecChestPlayer.size() : (this.typeTab == 12 ? Item.VecClanInvetory.size() : (this.typeTab == 13 ? Item.VecPetContainer.size() : this.vecShop.size())));
        }
        MainScreen.cameraSub.setAll(0, this.numH * MainTabNew.wOneItem - MainTabNew.hblack - MainTabNew.wOne5 + 8, 0, 0);
        this.list = new ListNew(this.xBegin, this.yBegin, MainTabNew.wblack, MainTabNew.hblack, 0, 0, MainScreen.cameraSub.yLimit);
        this.listContent = null;
        if (n == 0) {
            MainTabNew.Focus = 0;
        } else if (!GameCanvas.isTouch) {
            if (this.typeTab == 8) {
                this.center = this.isTypeShop == SHOP_BANG ? this.cmdMenuInven : (this.isTypeShop == WING ? this.cmdCreateWing : this.cmdHoiMua);
            } else if (this.typeTab == 9 || this.typeTab == 15) {
                if (Item.VecChestPlayer.size() > 0) {
                    this.center = this.cmdMenuInven;
                }
            } else if (this.typeTab == 12) {
                if (Item.VecClanInvetory.size() > 0) {
                    this.center = this.cmdMenuInven;
                }
            } else if (this.typeTab == 0 || this.typeTab == 16) {
                if (Item.VecInvetoryPlayer.size() > 0) {
                    this.center = this.cmdMenuInven;
                }
            } else if (this.typeTab == 13 && Item.VecPetContainer.size() > 0) {
                this.center = this.cmdMenuInven;
            }
            this.right = this.cmdBack;
        }
        this.currentTab = 0;
        super.init();
    }

    public void backTab() {
        MainTabNew.Focus = 0;
        this.idSelect = 0;
        super.backTab();
    }

    public void commandPointer(int n, Object object) {
        Object var3_3 = null;
        mSystem.outz("commandPointer index = " + n);
        mVector mVector2 = this.typeTab == 15 ? Item.VecItemSell : (this.typeTab == 0 || this.typeTab == 16 ? Item.VecInvetoryPlayer : (this.typeTab == 9 ? Item.VecChestPlayer : (this.typeTab == 13 ? Item.VecPetContainer : (this.typeTab == 12 ? Item.VecClanInvetory : this.vecShop))));
        if (this.idSelect < 0 && n > 0) {
            return;
        }
        if (MainTabNew.longwidth == 0) {
            MainTabNew.timePaintInfo = 0;
        }
        switch (n) {
            case 43: {
                MainItem mainItem = (MainItem)mVector2.elementAt(this.idSelect);
                if (mainItem != null) {
                    item_sell item_sell2 = (item_sell)object;
                    int n2 = 0;
                    short s = 1;
                    try {
                        if (item_sell2 != null) {
                            n2 = item_sell2.price;
                            s = item_sell2.soluuong;
                        }
                    }
                    catch (Exception exception) {
                        GameCanvas.start_Ok_Dialog(T.nhapsai);
                        n2 = 0;
                        return;
                    }
                    short s2 = (short)mainItem.Id;
                    item_sell item_sell3 = new item_sell(s2, n2, s, mainItem.ItemCatagory);
                    Item.VecItem_Sell_in_store.addElement(item_sell3);
                    if (mainItem.ItemCatagory == 4) {
                        MainItem mainItem2 = mainItem.clonePotion();
                        mainItem2.numPotion = s;
                        Item.VecItemSell.addElement(mainItem2);
                    } else if (mainItem.ItemCatagory == 7) {
                        MainItem mainItem3 = mainItem.cloneNguyenLieu();
                        mainItem3.numPotion = s;
                        Item.VecItemSell.addElement(mainItem3);
                    } else {
                        Item.VecItemSell.addElement(mainItem);
                    }
                }
                if (this.vecListCmd != null) {
                    this.vecListCmd.removeAllElements();
                }
                GameCanvas.end_Dialog();
                break;
            }
            case 41: {
                break;
            }
        }
        if (object != null) {
            object = null;
        }
    }

    public void commandPointer(int n, int n2) {
        Item item = null;
        mSystem.outz("commandPointer index = " + n);
        mVector mVector2 = this.typeTab == 15 ? Item.VecItemSell : (this.typeTab == 0 || this.typeTab == 16 ? Item.VecInvetoryPlayer : (this.typeTab == 9 ? Item.VecChestPlayer : (this.typeTab == 13 ? Item.VecPetContainer : (this.typeTab == 12 ? Item.VecClanInvetory : this.vecShop))));
        if (this.idSelect < 0 && n > 0) {
            return;
        }
        if (MainTabNew.longwidth == 0) {
            MainTabNew.timePaintInfo = 0;
        }
        switch (n) {
            case -1: {
                this.backTab();
                break;
            }
            case 0: {
                item = (Item)mVector2.elementAt(this.idSelect);
                if (item == null || item.setNameNull()) {
                    return;
                }
                if (this.isTypeShop == SHOP_STORE_OTHER_PLAYER && (!GameCanvas.isTouch || GameCanvas.isTouch && mSystem.isj2me)) {
                    this.cmdBuyItemOtherplayer.perform();
                    return;
                }
                if (item.diaHoiEvent != "") {
                    GameCanvas.start_Left_Dialog(item.diaHoiEvent, this.cmdMua);
                    return;
                }
                if (item.ItemCatagory == 4 || item.ItemCatagory == 7) {
                    this.inputDialog = new InputDialog();
                    this.inputDialog.setinfoSmallNew(T.nhapsoluongcanmua, this.cmdBuyPotion, true, -1, item.priceItem, T.buySell, item.getPriceType());
                    GameCanvas.currentDialog = this.inputDialog;
                    break;
                }
                if (item.ItemCatagory == 3) {
                    this.cmdMua.caption = T.buy;
                    GameCanvas.start_Left_Dialog(T.hoiBuy + "1 " + item.itemName + ". " + T.voigia + item.priceItem + item.getPriceType() + "?", this.cmdMua);
                    break;
                }
                if (item.ItemCatagory == 6) {
                    if (item.priceItem > 0L) {
                        this.cmdMua.caption = T.buy;
                        GameCanvas.start_Left_Dialog(T.hoiBuy + item.itemName + ". " + T.voigia + item.priceItem + item.getPriceType() + "?", this.cmdMua);
                        break;
                    }
                    if (item.imageId == GameScreen.player.hair) {
                        GameCanvas.start_Ok_Dialog(T.dangdungtocnay);
                        break;
                    }
                    this.cmdMua.caption = T.use;
                    GameCanvas.start_Left_Dialog(T.dungitem, this.cmdMua);
                    break;
                }
                if (item.ItemCatagory != 8) break;
                this.cmdMua.caption = T.select;
                GameCanvas.start_Left_Dialog(T.hoichoniconclan, this.cmdMua);
                break;
            }
            case 1: {
                Item item2 = (Item)mVector2.elementAt(this.idSelect);
                if (item2 == null) {
                    return;
                }
                GameCanvas.start_Left_Dialog(T.dungitem, new iCommand(T.use, 6, this));
                break;
            }
            case 2: {
                MainItem mainItem = (MainItem)mVector2.elementAt(this.idSelect);
                if (mainItem == null) {
                    return;
                }
                int n3 = 1;
                try {
                    n3 = Integer.parseInt(this.inputDialog.tfInput.getText());
                }
                catch (Exception exception) {
                    n3 = 1;
                }
                GlobalService.gI().buy_item((byte)this.IDTab, (short)mainItem.Id, (short)n3);
                GameCanvas.end_Dialog();
                break;
            }
            case 3: 
            case 31: {
                item = (MainItem)mVector2.elementAt(this.idSelect);
                if (item == null || item.setNameNull()) {
                    return;
                }
                GlobalService.gI().buy_item((byte)this.IDTab, (short)item.Id, (short)1);
                GameCanvas.end_Dialog();
                break;
            }
            case 4: {
                item = (MainItem)mVector2.elementAt(this.idSelect);
                if (item == null || item.setNameNull()) {
                    return;
                }
                GameCanvas.start_Left_Dialog(T.hoiDelItem + item.itemName + "?", this.cmdXoaItem);
                break;
            }
            case 5: {
                item = (MainItem)mVector2.elementAt(this.idSelect);
                if (item == null || item.setNameNull()) {
                    return;
                }
                GlobalService.gI().delete_Item((byte)item.ItemCatagory, (short)item.Id, (byte)0);
                MainTabNew.timePaintInfo = 0;
                GameCanvas.end_Dialog();
                break;
            }
            case 6: {
                MainItem mainItem = (MainItem)mVector2.elementAt(this.idSelect);
                if (mainItem == null) {
                    return;
                }
                if (mainItem.ItemCatagory == 5) {
                    GameCanvas.start_Ok_Dialog(T.khongthedung);
                    return;
                }
                if (mainItem.ItemCatagory == 4) {
                    if (mainItem.typePotion > 1 || Player.timeDelayPotion[mainItem.typePotion].value <= 0) {
                        if (GameScreen.help.setStep_Next(2, 4) && mainItem.typePotion == 0) {
                            ++GameScreen.help.Next;
                            GameScreen.help.setNext();
                        }
                        if (mainItem.typePotion >= 2 || (mainItem.typePotion != 0 || GameScreen.player.hp != GameScreen.player.maxHp) && (mainItem.typePotion != 1 || GameScreen.player.mp != GameScreen.player.maxMp)) {
                            if (mainItem.typePotion == Item.ID_TEM_VE_MUA_BAN) {
                                if (!GameScreen.player.isSelling()) {
                                    GameScreen.gI().doSellItem();
                                }
                            } else {
                                GlobalService.gI().Use_Potion((short)mainItem.Id);
                                if (mainItem.typePotion < 2) {
                                    Player.timeDelayPotion[mainItem.typePotion].value = 2000;
                                    Player.timeDelayPotion[mainItem.typePotion].limit = 2000;
                                    Player.timeDelayPotion[mainItem.typePotion].timebegin = mSystem.currentTimeMillis();
                                }
                            }
                        }
                    }
                } else {
                    int n4 = 0;
                    if (mainItem.type_Only_Item < 12) {
                        n4 = MainTemplateItem.mItem_Rotate_Tem_Equip[mainItem.type_Only_Item];
                    }
                    if (n4 == -1) {
                        MainImage mainImage;
                        Object object;
                        Object object2;
                        mVector mVector3 = new mVector("TabShopNew menu");
                        iCommand iCommand2 = new iCommand(T.leftRing, 12, 0, (AvMain)this);
                        Item item3 = (Item)Item.VecEquipPlayer.get("3");
                        if (item3 != null && (object2 = ObjectData.getImageItem((short)item3.imageId)) != null && ((MainImage)object2).img != null) {
                            if (((MainImage)object2).w == 0 || ((MainImage)object2).h == 0) {
                                ((MainImage)object2).h = (short)mImage.getImageHeight(((MainImage)object2).img.image);
                                ((MainImage)object2).w = (short)mImage.getImageWidth(((MainImage)object2).img.image);
                            }
                            object = new FrameImage(((MainImage)object2).img, ((MainImage)object2).w, ((MainImage)object2).h);
                            iCommand2.setFraCaption((FrameImage)object);
                        }
                        object2 = new iCommand(T.rightRing, 12, 1, (AvMain)this);
                        object = (Item)Item.VecEquipPlayer.get("9");
                        if (object != null && (mainImage = ObjectData.getImageItem((short)((Item)object).imageId)) != null && mainImage.img != null) {
                            if (mainImage.w == 0 || mainImage.h == 0) {
                                mainImage.h = (short)mImage.getImageHeight(mainImage.img.image);
                                mainImage.w = (short)mImage.getImageWidth(mainImage.img.image);
                            }
                            FrameImage frameImage = new FrameImage(mainImage.img, mainImage.w, mainImage.h);
                            ((iCommand)object2).setFraCaption(frameImage);
                        }
                        mVector3.addElement(iCommand2);
                        mVector3.addElement(object2);
                        GameCanvas.menu2.startAt(mVector3, 2, T.Ring, false, null);
                    } else {
                        if (mainItem.ItemCatagory == 7) {
                            GlobalService.gI().doUseMaterial((short)mainItem.Id);
                        } else {
                            GlobalService.gI().Use_Item((byte)mainItem.Id, (byte)n4);
                        }
                        if (GameScreen.help.setStep_Next(3, 4)) {
                            ++GameScreen.help.Next;
                            GameScreen.help.setNext();
                        }
                    }
                }
                MainTabNew.timePaintInfo = 0;
                if (GameScreen.help.setStep_Next(2, 5) || GameScreen.help.setStep_Next(3, 5)) break;
                GameCanvas.end_Dialog();
                break;
            }
            case 7: {
                item = (Item)mVector2.elementAt(this.idSelect);
                if (item == null) {
                    return;
                }
                mVector mVector4 = new mVector("TabShopNew menu2");
                mVector4 = this.doMenu(item);
                String string = T.item;
                if (this.isTypeShop == SHOP_BANG) {
                    string = T.iconclan;
                }
                GameCanvas.menu2.startAt(mVector4, 2, string, false, null);
                if (!GameScreen.help.setStep_Next(2, 9) && !GameScreen.help.setStep_Next(2, 4) && !GameScreen.help.setStep_Next(3, 4)) break;
                Menu2.isHelp = true;
                break;
            }
            case 8: {
                mVector mVector5 = new mVector("TabShopNew menu3");
                for (int i = 0; i < 5; ++i) {
                    iCommand iCommand3 = !GameCanvas.isTouch ? (TField.isQwerty ? new iCommand(T.phim + PaintInfoGameScreen.mValueChar[i], 9, i, (AvMain)this) : new iCommand(T.phim + PaintInfoGameScreen.mValueHotKey[i], 9, i, (AvMain)this)) : new iCommand(T.oso + (i + 1), 9, i, (AvMain)this);
                    mVector5.addElement(iCommand3);
                }
                GameCanvas.menu2.startAt(mVector5, 2, T.setKey, false, null);
                if (GameScreen.help.Step < 0 || !GameScreen.help.setStep_Next(2, 9)) break;
                GameScreen.help.Next = 10;
                Menu2.isHelp = true;
                break;
            }
            case 9: {
                item = (MainItem)mVector2.elementAt(this.idSelect);
                if (item == null || item.typePotion > 1) {
                    return;
                }
                if (n2 == 2) {
                    GameCanvas.start_Ok_Dialog(T.khongganmauvaophimnay);
                    return;
                }
                Player.mhotkey[Player.levelTab][n2].setHotKey(item.Id, HotKey.POTION, item.typePotion);
                TabSkillsNew.saveSkill();
                if (GameScreen.help.Step < 0 || !GameScreen.help.setStep_Next(2, 9) && !GameScreen.help.setStep_Next(2, 10)) break;
                GameScreen.help.Next = 11;
                GameScreen.help.setNext();
                break;
            }
            case 10: {
                item = (MainItem)mVector2.elementAt(this.idSelect);
                if (item == null || item.setNameNull()) {
                    return;
                }
                GlobalService.gI().delete_Item((byte)item.ItemCatagory, (short)item.Id, (byte)1);
                if (GameCanvas.isTouch) {
                    this.idSelect = -1;
                }
                MainTabNew.timePaintInfo = 0;
                GameCanvas.end_Dialog();
                break;
            }
            case 11: {
                item = (MainItem)mVector2.elementAt(this.idSelect);
                if (item == null || item.setNameNull()) {
                    return;
                }
                int n5 = 0;
                if (item.ItemCatagory == 3) {
                    n5 = (1 + item.LvItem / hesoLv) * priceSellItem * (100 + item.colorNameItem * hesoColor) / 100;
                    if (n5 > maxPriceItem) {
                        n5 = maxPriceItem;
                    }
                } else {
                    n5 = item.ItemCatagory == 5 ? item.numPotion * priceSellQuest : priceSellPotion;
                }
                if (item.ItemCatagory == 3) {
                    if (item.colorNameItem == 0) {
                        n5 = 1;
                    }
                    if (item.colorNameItem == 1) {
                        n5 = 3;
                    }
                }
                GameCanvas.start_Left_Dialog(T.hoisell + item.itemName + "? " + T.voigia + n5 + " " + T.coin + ".", this.cmdSell);
                break;
            }
            case 12: {
                item = (MainItem)mVector2.elementAt(this.idSelect);
                if (item == null) break;
                if (n2 == 0) {
                    GlobalService.gI().Use_Item((byte)item.Id, (byte)3);
                    break;
                }
                if (n2 != 1) break;
                GlobalService.gI().Use_Item((byte)item.Id, (byte)9);
                break;
            }
            case 13: {
                item = (MainItem)mVector2.elementAt(this.idSelect);
                if (item != null) {
                    int n6 = 1;
                    if (item.ItemCatagory == 4 || item.ItemCatagory == 7) {
                        try {
                            n6 = Integer.parseInt(this.inputDialog.tfInput.getText());
                        }
                        catch (Exception exception) {
                            n6 = 1;
                        }
                    }
                    GlobalService.gI().Update_Char_Chest(GET_CHEST, (short)item.Id, (byte)item.ItemCatagory, (short)n6);
                    if (GameCanvas.isTouch) {
                        this.idSelect = -1;
                    }
                }
                GameCanvas.end_Dialog();
                break;
            }
            case 14: {
                item = (MainItem)mVector2.elementAt(this.idSelect);
                if (item != null) {
                    int n7 = 1;
                    if (item.ItemCatagory == 4 || item.ItemCatagory == 7) {
                        try {
                            n7 = Integer.parseInt(this.inputDialog.tfInput.getText());
                        }
                        catch (Exception exception) {
                            n7 = 1;
                        }
                    }
                    GlobalService.gI().Update_Char_Chest(SET_CHEST, (short)item.Id, (byte)item.ItemCatagory, (short)n7);
                    if (GameCanvas.isTouch) {
                        this.idSelect = -1;
                    }
                }
                GameCanvas.end_Dialog();
                break;
            }
            case 15: {
                item = (MainItem)mVector2.elementAt(this.idSelect);
                if (item == null || item.ItemCatagory != 4 && item.ItemCatagory != 7) break;
                this.inputDialog = new InputDialog();
                this.inputDialog.setinfo(T.nhapsoluongcanlay, this.cmdGetChest, true, T.chest);
                GameCanvas.currentDialog = this.inputDialog;
                break;
            }
            case 16: {
                item = (MainItem)mVector2.elementAt(this.idSelect);
                if (item == null || item.ItemCatagory != 4 && item.ItemCatagory != 7) break;
                this.inputDialog = new InputDialog();
                this.inputDialog.setinfo(T.nhapsoluongcancat, this.cmdSetChest, true, T.tabhanhtrang);
                GameCanvas.currentDialog = this.inputDialog;
                break;
            }
            case 17: {
                mVector mVector6 = new mVector("TabShopNew menusell");
                mVector6.addElement(new iCommand(T.banhettrang, 19, 0, (AvMain)this));
                mVector6.addElement(new iCommand(T.banhetxanh, 19, 1, (AvMain)this));
                GameCanvas.menu2.startAt(mVector6, 2, T.sell, false, null);
                break;
            }
            case 18: {
                for (int i = 0; i < Item.VecInvetoryPlayer.size(); ++i) {
                    MainItem mainItem = (MainItem)Item.VecInvetoryPlayer.elementAt(i);
                    if (n2 == 0) {
                        if (mainItem.ItemCatagory != 3 || mainItem.colorNameItem != 0 || mainItem.tier != 0) continue;
                        GlobalService.gI().delete_Item((byte)mainItem.ItemCatagory, (short)mainItem.Id, (byte)1);
                        continue;
                    }
                    if (n2 != 1 || mainItem.ItemCatagory != 3 || mainItem.colorNameItem != 1 || mainItem.tier != 0) continue;
                    GlobalService.gI().delete_Item((byte)mainItem.ItemCatagory, (short)mainItem.Id, (byte)1);
                }
                if (GameCanvas.isTouch) {
                    this.idSelect = -1;
                }
                GameCanvas.end_Dialog();
                break;
            }
            case 19: {
                int n8 = 0;
                for (int i = 0; i < Item.VecInvetoryPlayer.size(); ++i) {
                    MainItem mainItem = (MainItem)Item.VecInvetoryPlayer.elementAt(i);
                    if (n2 == 0) {
                        if (mainItem.ItemCatagory != 3 || mainItem.colorNameItem != 0) continue;
                        ++n8;
                        continue;
                    }
                    if (n2 != 1 || mainItem.ItemCatagory != 3 || mainItem.colorNameItem != 1) continue;
                    ++n8;
                }
                if (n8 == 0) {
                    if (n2 == 0) {
                        GameCanvas.start_Ok_Dialog(T.khongcontrang);
                        break;
                    }
                    if (n2 != 1) break;
                    GameCanvas.start_Ok_Dialog(T.khongconxanh);
                    break;
                }
                if (n2 == 0) {
                    GameCanvas.start_Left_Dialog(T.hoisell + n8 + T.dotrang, new iCommand(T.sell, 18, 0, (AvMain)this));
                    break;
                }
                if (n2 != 1) break;
                GameCanvas.start_Left_Dialog(T.hoisell + n8 + T.doxanh, new iCommand(T.sell, 18, 1, (AvMain)this));
                break;
            }
            case 20: {
                item = (MainItem)Item.VecInvetoryPlayer.elementAt(this.idSelect);
                if (item == null) break;
                GlobalService.gI().Rebuild_Item((byte)0, (short)item.Id, (byte)item.ItemCatagory);
                if (MainTabNew.longwidth <= 0) break;
                this.idSelect = -1;
                MainTabNew.timePaintInfo = 0;
                break;
            }
            case 21: {
                item = (MainItem)Item.VecInvetoryPlayer.elementAt(this.idSelect);
                if (item == null) break;
                byte by = MainObject.isMaHopNguyenLieu(item.Id) ? (byte)6 : 1;
                GlobalService.gI().Rebuild_Item(by, (short)item.Id, (byte)item.ItemCatagory);
                if (MainTabNew.longwidth <= 0) break;
                this.idSelect = -1;
                MainTabNew.timePaintInfo = 0;
                break;
            }
            case 22: {
                GlobalService.gI().NextClan((byte)8);
                GameCanvas.start_Wait_Dialog(T.pleaseWait, new iCommand(T.close, -1));
                break;
            }
            case 23: {
                item = (MainItem)Item.VecInvetoryPlayer.elementAt(this.idSelect);
                if (item == null) break;
                GlobalService.gI().Replace_Item((byte)0, (short)item.Id);
                break;
            }
            case 24: {
                item = (MainItem)Item.VecInvetoryPlayer.elementAt(this.idSelect);
                if (item == null) break;
                GlobalService.gI().Rebuild_Wing((byte)2, 0, (short)item.Id);
                break;
            }
            case 25: {
                mSystem.outz("Deposit pet ------- ");
                item = (Item)mVector2.elementAt(this.idSelect);
                if (item != null) {
                    int n9 = 1;
                    GlobalService.gI().Update_Pet_Container(DEPOSIT_PET, (short)item.Id, (byte)item.ItemCatagory, (short)n9);
                    mSystem.println("ap trung " + item.Id + " " + item.ItemCatagory + " " + n9);
                    if (GameCanvas.isTouch) {
                        this.idSelect = -1;
                    }
                }
                GameCanvas.end_Dialog();
                break;
            }
            case 26: {
                mSystem.outz("withdraw pet ------- ");
                item = (Item)mVector2.elementAt(this.idSelect);
                if (item != null) {
                    boolean bl = true;
                    GlobalService.gI().Update_Pet_Container(WITHDRAW_PET, (short)item.Id, (byte)item.ItemCatagory, (short)(bl ? 1 : 0));
                    if (GameCanvas.isTouch) {
                        this.idSelect = -1;
                    }
                } else {
                    mSystem.println("null cmnr");
                }
                GameCanvas.end_Dialog();
                break;
            }
            case 27: {
                if (this.petCur == null) {
                    return;
                }
                item = (Item)mVector2.elementAt(this.idSelect);
                GlobalService.gI().Pet_Eat((short)this.petCur.Id, (short)item.Id, (byte)item.ItemCatagory, MsgDialog.isInven_Equip);
                break;
            }
            case 28: {
                item = (Item)mVector2.elementAt(this.idSelect);
                if (item == null || item.ItemCatagory != 9) break;
                GameCanvas.start_Pet_Info((PetItem)item, (byte)0);
                break;
            }
            case 29: {
                item = (Item)Item.VecPetContainer.elementAt(this.idSelect);
                if (item != null && item.ItemCatagory == 9) {
                    GameCanvas.start_Pet_Info((PetItem)item, (byte)0);
                }
                mVector mVector7 = new mVector("TabShopNew vectab");
                TabShopNew tabShopNew = new TabShopNew(Item.VecInvetoryPlayer, 0, T.choan, -1, INVEN_FOOD_PET);
                tabShopNew.petCur = MsgDialog.pet;
                mVector7.addElement(tabShopNew);
                GameCanvas.foodPet = new TabScreenNew();
                GameCanvas.foodPet.selectTab = 0;
                GameCanvas.foodPet.addMoreTab(mVector7);
                GameCanvas.foodPet.Show(GameCanvas.currentScreen);
                break;
            }
            case 30: {
                item = (MainItem)Item.VecInvetoryPlayer.elementAt(this.idSelect);
                if (item == null) break;
                GlobalService.gI().Rebuild_Item((byte)4, (short)item.Id, (byte)item.ItemCatagory);
                if (MainTabNew.longwidth <= 0) break;
                this.idSelect = -1;
                MainTabNew.timePaintInfo = 0;
                break;
            }
            case 32: {
                item = (MainItem)mVector2.elementAt(this.idSelect);
                if (item == null || item.setNameNull()) {
                    return;
                }
                mVector mVector8 = new mVector("TabShopNew menu4");
                String string = T.hoiBuy + " " + 10 + " " + item.itemName + " " + T.voigia + " " + item.priceItem * 10L + " " + (item.priceType == 0 ? T.coin : T.gem);
                mVector8.addElement(new iCommand(T.yes, 34, 10, (AvMain)this));
                mVector8.addElement(new iCommand(T.cancel, 35, this));
                GameCanvas.start_Select_Dialog(string, mVector8);
                break;
            }
            case 33: {
                item = (MainItem)mVector2.elementAt(this.idSelect);
                if (item == null || item.setNameNull()) {
                    return;
                }
                mVector mVector9 = new mVector("TabShopNew menu5");
                String string = T.hoiBuy + " " + 30 + " " + item.itemName + " " + T.voigia + " " + item.priceItem * 30L + " " + (item.priceType == 0 ? T.coin : T.gem);
                mVector9.addElement(new iCommand(T.yes, 34, 30, (AvMain)this));
                mVector9.addElement(new iCommand(T.cancel, 35, this));
                GameCanvas.start_Select_Dialog(string, mVector9);
                break;
            }
            case 34: {
                item = (MainItem)mVector2.elementAt(this.idSelect);
                if (item == null || item.setNameNull()) {
                    return;
                }
                GlobalService.gI().buy_item((byte)this.IDTab, (short)item.Id, (short)n2);
                GameCanvas.end_Dialog();
                break;
            }
            case 35: {
                GameCanvas.end_Dialog();
                break;
            }
            case 36: 
            case 40: {
                MainItem mainItem = (MainItem)Item.VecInvetoryPlayer.elementAt(this.idSelect);
                if (mainItem == null) break;
                TabRebuildItem.itemRe = mainItem;
                TabRebuildItem.countSetpaintinfo = 1;
                if (MainTabNew.longwidth <= 0) break;
                this.idSelect = -1;
                MainTabNew.timePaintInfo = 0;
                break;
            }
            case 37: {
                MainItem mainItem = (MainItem)Item.VecInvetoryPlayer.elementAt(this.idSelect);
                if (mainItem == null) break;
                if (TabRebuildItem.vecGem.size() > this.numofGem) {
                    GameCanvas.start_Ok_Dialog(T.khongbothem);
                    return;
                }
                TabRebuildItem.addGem(mainItem);
                if (MainTabNew.longwidth <= 0) break;
                this.idSelect = -1;
                MainTabNew.timePaintInfo = 0;
                break;
            }
            case 38: {
                TabRebuildItem.vecGem.removeAllElements();
                TabRebuildItem.itemRe = null;
                MainItem mainItem = (MainItem)Item.VecInvetoryPlayer.elementAt(this.idSelect);
                if (mainItem == null) break;
                if (mainItem.numPotion < this.numofGem) {
                    GameCanvas.start_Ok_Dialog(T.hetNgoc);
                    return;
                }
                for (int i = 0; i < this.numofGem; ++i) {
                    TabRebuildItem.addGem(mainItem);
                    if (MainTabNew.longwidth <= 0) continue;
                    this.idSelect = -1;
                    MainTabNew.timePaintInfo = 0;
                }
                break;
            }
            case 39: {
                item = (MainItem)mVector2.elementAt(this.idSelect);
                if (item == null) {
                    return;
                }
                if (this.isTypeShop == SHOP_KHAM_NGOC) {
                    if (!TabRebuildItem.isKham(item)) break;
                    TabRebuildItem.removeKhamNgoc(item);
                    break;
                }
                if (this.isTypeShop != SHOP_GHEP_NGOC) break;
                TabRebuildItem.vecGem.removeAllElements();
                break;
            }
            case 41: {
                this.inputDialog = new InputDialog();
                String[] stringArray = new String[]{T.nhapgiaban};
                this.inputDialog.setinfo(stringArray, this.cmdDaugia, (short)-1, (short)0, T.daugia);
                GameCanvas.currentDialog = this.inputDialog;
                break;
            }
            case 42: {
                MainItem mainItem = (MainItem)mVector2.elementAt(this.idSelect);
                if (mainItem == null) {
                    return;
                }
                int n10 = 1;
                int n11 = 1;
                String[] stringArray = this.inputDialog.getarrayText();
                if (stringArray != null) {
                    try {
                        if (stringArray.length == 1) {
                            if (stringArray[0] != null) {
                                n10 = Integer.parseInt(stringArray[0]);
                            }
                        } else if (stringArray.length == 2) {
                            if (stringArray[0] != null) {
                                n10 = Integer.parseInt(stringArray[0]);
                            }
                            if (stringArray[1] != null) {
                                n11 = Integer.parseInt(stringArray[1]);
                            }
                        }
                    }
                    catch (Exception exception) {
                        GameCanvas.start_Ok_Dialog(T.nhapsai);
                        n10 = 1;
                        return;
                    }
                }
                String string = T.hoidaugia + mainItem.itemName + " " + T.voigia + " " + MainItem.getDotNumber(n10) + " " + T.coin + " ?";
                if (n11 > 1) {
                    string = T.hoidaugia + " " + n11 + " " + mainItem.itemName + " " + T.voigia + " " + MainItem.getDotNumber(n10) + " " + T.coin + " ?";
                }
                item_sell item_sell2 = new item_sell();
                item_sell2.price = n10;
                item_sell2.soluuong = (short)n11;
                mVector mVector10 = new mVector();
                mVector10.addElement(new iCommand(T.yes, 43, item_sell2, (AvMain)this));
                mVector10.addElement(new iCommand(T.cancel, 35, this));
                GameCanvas.start_Select_Dialog(string, mVector10);
                break;
            }
            case 44: {
                this.inputDialog = new InputDialog();
                this.inputDialog.setinfoSmallNew(T.nhapSlogan, this.cmdOkSell, false, -1, 0L, "");
                GameCanvas.currentDialog = this.inputDialog;
                break;
            }
            case 45: {
                GlobalService.gI().do_Buy_Sell_Item(0, Item.VecItem_Sell_in_store, this.inputDialog.tfInput.getText(), (short)0, 0, (byte)0);
                GameCanvas.end_Dialog();
                if (this.vecListCmd == null) break;
                this.vecListCmd.removeAllElements();
                this.vecListCmd.addElement(this.cmdNghiban);
                this.setPosCmd(this.vecListCmd);
                break;
            }
            case 46: {
                MainItem mainItem = (MainItem)mVector2.elementAt(this.idSelect);
                if (mainItem == null) break;
                if (mainItem.ItemCatagory == 3) {
                    GlobalService.gI().do_Buy_Sell_Item(2, null, "", idSeller, mainItem.Id, (byte)mainItem.ItemCatagory);
                    break;
                }
                if (mainItem.ItemCatagory == 7) {
                    GlobalService.gI().do_Buy_Sell_Item(2, null, "", idSeller, mainItem.Id, (byte)mainItem.ItemCatagory);
                    break;
                }
                if (mainItem.ItemCatagory != 4) break;
                GlobalService.gI().do_Buy_Sell_Item(2, null, "", idSeller, mainItem.Id, (byte)mainItem.ItemCatagory);
                break;
            }
            case 47: {
                Item.VecItem_Sell_in_store.removeAllElements();
                Item.VecItemSell.removeAllElements();
                break;
            }
            case 48: {
                GlobalService.gI().do_Buy_Sell_Item(4, null, "", (short)0, 0, (byte)0);
                break;
            }
            case 49: {
                this.inputWorld = new InputDialog();
                this.inputWorld.setinfo(T.nhapnoidung, new iCommand(T.chat, 50, this), false, T.textkenhthegioi);
                GameCanvas.currentDialog = this.inputWorld;
                break;
            }
            case 50: {
                this.textWorld = this.inputWorld.tfInput.getText();
                if (this.textWorld == null || this.textWorld.length() <= 0) break;
                GameCanvas.start_Left_Dialog(T.kenhthegioi + " (" + T.phi + PriceChatWorld + " " + T.gem + ")" + T.noidungnhusau + "\n" + this.textWorld, new iCommand(T.chat, 51, this));
                break;
            }
            case 51: {
                GlobalService.gI().Chat_World(this.textWorld);
                GameCanvas.end_Dialog();
                break;
            }
            case 52: {
                MainItem mainItem = (MainItem)Item.VecInvetoryPlayer.elementAt(this.idSelect);
                if (mainItem != null) {
                    if (TabRebuildItem.itemRe != null) {
                        TabRebuildItem.itemRe = null;
                    }
                    GlobalService.gI().Hop_rac((byte)0, (short)mainItem.Id, (byte)mainItem.ItemCatagory);
                }
                this.vecListCmd.removeAllElements();
                break;
            }
            case 53: {
                MainItem mainItem = (MainItem)Item.VecInvetoryPlayer.elementAt(this.idSelect);
                if (mainItem != null) {
                    GlobalService.gI().Hop_rac((byte)1, (short)mainItem.Id, (byte)mainItem.ItemCatagory);
                }
                this.vecListCmd.removeAllElements();
                break;
            }
            case 54: {
                MainItem mainItem = (MainItem)Item.VecInvetoryPlayer.elementAt(this.idSelect);
                if (mainItem != null) {
                    TabRebuildItem.itemRe = mainItem;
                    GlobalService.gI().Hop_rac((byte)0, (short)mainItem.Id, (byte)mainItem.ItemCatagory);
                }
                this.vecListCmd.removeAllElements();
                break;
            }
            case 55: {
                this.inputDialog = new InputDialog();
                String[] stringArray = new String[]{T.nhapgiaban, T.soluong};
                this.inputDialog.setinfo(stringArray, this.cmdDaugia, (short)-1, (short)0, T.daugia);
                GameCanvas.currentDialog = this.inputDialog;
                break;
            }
            case 56: {
                this.currentTab = (byte)(this.currentTab + 1);
                if (this.currentTab > maxTab - 1) {
                    this.currentTab = 0;
                }
                if (GameCanvas.isTouch) break;
                if (this.currentTab == 0) {
                    this.idSelect = 0;
                }
                if (this.currentTab != 1) break;
                this.idSelect = 42;
                break;
            }
            case 57: {
                MainItem mainItem = (MainItem)mVector2.elementAt(this.idSelect);
                if (mainItem == null) break;
                GlobalService.gI().DoKhacItem((byte)6, (byte)mainItem.ItemCatagory, (short)mainItem.Id);
            }
        }
    }

    public mVector doMenu(Item item) {
        mVector mVector2 = new mVector("TabShopNew menu7");
        if (this.typeTab == 9 && (maxTab = (byte)(Player.maxChest / 42)) == 1 && Item.VecChestPlayer.size() > 42) {
            maxTab = (byte)2;
        }
        if (this.typeTab == 0) {
            maxTab = (byte)(Player.maxInven / 42);
        }
        if (this.typeTab != 8 && maxTab > 1) {
            mVector2.addElement(this.cmdNextInven);
        }
        if (this.isTypeShop == SHOP_NANG_CAP_MEDEL) {
            mVector2.addElement(this.cmdHopAn);
            return mVector2;
        }
        if (this.isTypeShop == SHOP_HOP_AN) {
            return mVector2;
        }
        if (this.isTypeShop == SHOP_NANG_CAP_MEDEL) {
            return mVector2;
        }
        if (GameScreen.player.isSelling()) {
            mVector2.addElement(this.cmdNghiban);
            mVector2.addElement(this.cmdChatWorld);
            return mVector2;
        }
        if (this.isTypeShop == SHOP_STORE_OTHER_PLAYER) {
            mVector2.addElement(this.cmdBuyItemOtherplayer);
            return mVector2;
        }
        if (this.typeTab == 15) {
            mVector2.addElement(this.cmdHoanThanh);
            mVector2.addElement(this.cmdhuy);
            return mVector2;
        }
        if (this.typeTab == 16) {
            if (Item.VecItemSell.size() > 0) {
                for (int i = 0; i < Item.VecItemSell.size(); ++i) {
                    MainItem mainItem = (MainItem)Item.VecItemSell.elementAt(i);
                    if (mainItem.Id != item.Id) continue;
                    return mVector2;
                }
            }
            if (item.ItemCatagory == 3) {
                mVector2.addElement(this.cmdHoidaugia);
            } else {
                mVector2.addElement(this.cmdbanNguyenLieu);
            }
        } else if (this.typeTab == 0 && !this.isShop_Other_Player) {
            if (this.isTypeShop == SHOP_STORE_OTHER_PLAYER) {
                mVector2.addElement(this.cmdBuyItemOtherplayer);
            } else if (this.isTypeShop == SHOP_DUC_LO) {
                if (item.ItemCatagory == 7 && item.typeMaterial == Item.TYPE_BUA_HUYEN_BI) {
                    mVector2.addElement(this.cmdBovao);
                }
                if (item.ItemCatagory == 3) {
                    mVector2.addElement(this.cmdDuclo);
                }
            } else if (this.isTypeShop == SHOP_ANY_NGUYEN_LIEU) {
                if (item.ItemCatagory == 7) {
                    if (TabRebuildItem.vecGem.size() > 0) {
                        MainItem mainItem = (MainItem)TabRebuildItem.vecGem.elementAt(0);
                        if (mainItem.Id == item.Id) {
                            mVector2.addElement(this.cmdlayracra);
                            return mVector2;
                        }
                    }
                    mVector2.addElement(this.cmdHoprac);
                }
            } else if (this.isTypeShop == SHOP_GHEP_NGOC) {
                if (item.ItemCatagory == 7 && item.typeMaterial == Item.TYPE_NGOC_KHAM) {
                    if (!TabRebuildItem.isKham(item)) {
                        mVector2.addElement(this.cmdGhepNgoc);
                    }
                    if (TabRebuildItem.isKham(item)) {
                        mVector2.addElement(this.cmdLayra);
                    }
                }
            } else if (this.isTypeShop == SHOP_KHAM_NGOC) {
                if (item.ItemCatagory == 3) {
                    if (!(isTabHopNL || TabRebuildItem.itemRe != null && TabRebuildItem.itemRe.Id == item.Id && TabRebuildItem.itemRe.ItemCatagory == item.ItemCatagory)) {
                        mVector2.addElement(this.cmdKhamgoc);
                    }
                } else if (item.ItemCatagory == 7 && item.typeMaterial == 49) {
                    mVector2.addElement(this.cmdBovao);
                    if (TabRebuildItem.isKham(item)) {
                        mVector2.addElement(this.cmdLayra);
                    }
                }
            } else if (this.isTypeShop == INVEN_AND_REBUILD) {
                if (item.ItemCatagory == 3) {
                    if (!isTabHopNL) {
                        if (TabRebuildItem.itemRe == null || TabRebuildItem.itemRe.Id != item.Id || TabRebuildItem.itemRe.ItemCatagory != item.ItemCatagory) {
                            mVector2.addElement(this.cmdRebuild);
                        } else {
                            mVector2.addElement(this.cmdUnRebuild);
                        }
                    }
                } else if (item.ItemCatagory == 7) {
                    if (item.typeMaterial == 11) {
                        if (!isTabHopNL) {
                            if (!TabRebuildItem.isLucky) {
                                TabRebuildItem.itemDaMayMan = item;
                                mVector2.addElement(this.cmdRebuild);
                            } else {
                                mVector2.addElement(this.cmdUnRebuild);
                            }
                        }
                    } else if (MainObject.isMaHopNguyenLieu((short)item.Id) && isTabHopNL) {
                        if (TabRebuildItem.itemRe == null || TabRebuildItem.itemRe.Id != item.Id || TabRebuildItem.itemRe.ItemCatagory != item.ItemCatagory) {
                            mVector2.addElement(this.cmdHopNguyeLieu);
                        } else {
                            mVector2.addElement(this.cmdUnRebuild);
                        }
                    }
                }
            } else if (this.isTypeShop == INVEN_AND_REPLACE) {
                if (item.ItemCatagory == 3) {
                    mVector2.addElement(this.cmdReplace);
                }
            } else if (this.isTypeShop == INVEN_AND_CHEST) {
                if (item.ItemCatagory != 5) {
                    if (item.ItemCatagory == 4 || item.ItemCatagory == 7) {
                        mVector2.addElement(this.cmdSetPotionChest);
                    } else {
                        mVector2.addElement(this.cmdSetChest);
                    }
                }
            } else if (this.isTypeShop == INVEN_AND_PET_KEEPER) {
                if (item.ItemCatagory == 3 && item.type_Only_Item == 14) {
                    mVector2.addElement(this.cmdDepositPet);
                }
            } else if (this.isTypeShop == INVEN_AND_WING) {
                if (item.ItemCatagory == 3 && item.type_Only_Item == 7) {
                    mVector2.addElement(this.cmdWing);
                }
            } else if (this.isTypeShop == INVEN_FOOD_PET) {
                mVector2.addElement(this.cmdFoodPet);
            } else {
                if (GameCanvas.currentScreen == GameCanvas.shopNpc && item.canSell == 1) {
                    mVector2.addElement(this.cmdHoiSell);
                    if (item.ItemCatagory == 3) {
                        mVector2.addElement(this.cmdSellMore);
                    }
                }
                if (GameCanvas.currentScreen != GameCanvas.shopNpc) {
                    mVector2.addElement(this.cmdSelect);
                    if (item.ItemCatagory == 4 && item.typePotion < 2) {
                        mVector2.addElement(this.cmdSetKey);
                    }
                }
                if (GameCanvas.currentScreen == GameCanvas.AllInfo) {
                    mVector2.addElement(this.cmdHoiXoaItem);
                }
            }
        } else if (this.typeTab == 9) {
            if (item.ItemCatagory == 4 || item.ItemCatagory == 7) {
                mVector2.addElement(this.cmdGetPotionChest);
            } else {
                mVector2.addElement(this.cmdGetChest);
            }
        } else if (this.typeTab != 15) {
            if (this.typeTab == 13) {
                if (this.isTypeShop == INVEN_AND_PET_KEEPER && item.ItemCatagory == 9) {
                    mVector2.addElement(this.cmdPetFeed);
                    mVector2.addElement(this.cmdWithdrawPet);
                }
            } else if (this.typeTab == 12) {
                mVector2.addElement(this.cmdSelect);
            } else {
                mVector mVector3;
                this.cmdHoiMua.caption = T.buy;
                if (this.IDTab == ReadMessenge.SHOP_ITEM_EVENT) {
                    this.cmdHoiMua.caption = this.nameCmdBuy;
                }
                if (item.ItemCatagory == 6 && item.priceItem <= 0L) {
                    this.cmdHoiMua.caption = T.use;
                }
                if (this.isTypeShop == WING) {
                    mVector2.addElement(this.cmdCreateWing);
                } else if (!(this.isTypeShop == SHOP_VANTIEU || this.isShop_Other_Player || (item = (Item)(mVector3 = this.vecShop).elementAt(this.idSelect)) == null && item.setNameNull())) {
                    if (item.ItemCatagory == 4 || item.ItemCatagory == 7) {
                        mVector2.addElement(this.cmdMua1);
                        mVector2.addElement(this.cmdMua10);
                        mVector2.addElement(this.cmdMua30);
                        mVector2.addElement(this.cmdMuaNhieu);
                    } else {
                        mVector2.addElement(this.cmdHoiMua);
                    }
                }
                if (this.isTypeShop == SHOP_BANG) {
                    this.cmdHoiMua.caption = T.select;
                    mVector2.addElement(this.cmdNextIcon);
                }
            }
        }
        if (this.typeTab == 0) {
            mVector2.addElement(this.cmdKhacItem);
        }
        return mVector2;
    }

    public void paint(mGraphics mGraphics2) {
        item_sell item_sell2;
        Object object;
        int n;
        mVector mVector2 = this.typeTab == 15 ? Item.VecItemSell : (this.typeTab == 0 || this.typeTab == 16 ? Item.VecInvetoryPlayer : (this.typeTab == 9 ? Item.VecChestPlayer : (this.typeTab == 13 ? Item.VecPetContainer : (this.typeTab == 12 ? Item.VecClanInvetory : this.vecShop))));
        GameCanvas.resetTrans(mGraphics2);
        mGraphics2.setClip(this.xBegin - MainTabNew.wOne5, this.yBegin, MainTabNew.wblack + MainTabNew.wOne5 * 2, MainTabNew.hblack - MainTabNew.wOne5 / 2 + 1);
        mGraphics2.saveCanvas();
        mGraphics2.ClipRec(this.xBegin - MainTabNew.wOne5, this.yBegin, MainTabNew.wblack + MainTabNew.wOne5 * 2, MainTabNew.hblack - MainTabNew.wOne5 / 2 + 1);
        mGraphics2.translate(-MainScreen.cameraSub.xCam, -MainScreen.cameraSub.yCam);
        int n2 = mVector2.size();
        if (n2 > 42) {
            n2 = 42;
        }
        if (this.isShop) {
            n2 = this.vecShop.size();
        }
        int n3 = n2 * this.currentTab;
        for (n = 0; n < n2; ++n) {
            MainItem mainItem;
            object = (Item)mVector2.elementAt(n + n3);
            if (object == null) continue;
            if (((Item)object).ItemCatagory == 7) {
                MainItem mainItem2 = Item.getMaterial(((Item)object).Id);
                if (mainItem2 != null) {
                    ((Item)object).setinfo(mainItem2.itemName, mainItem2.imageId, 7, ((Item)object).priceItem, ((Item)object).priceType, mainItem2.content, (short)mainItem2.value, mainItem2.typeMaterial, (short)1, mainItem2.canSell, mainItem2.canTrade);
                    ((Item)object).setColorName(mainItem2.colorNameItem);
                    ((Item)object).paintItem(mGraphics2, this.xBegin + MainTabNew.wOneItem / 2 + n % this.numW * MainTabNew.wOneItem, this.yBegin + MainTabNew.wOneItem / 2 + n / this.numW * MainTabNew.wOneItem, MainTabNew.wOneItem, 0, 0);
                } else {
                    Item.put_Material(((Item)object).Id);
                }
            } else {
                ((Item)object).paintItem(mGraphics2, this.xBegin + MainTabNew.wOneItem / 2 + n % this.numW * MainTabNew.wOneItem, this.yBegin + MainTabNew.wOneItem / 2 + n / this.numW * MainTabNew.wOneItem, MainTabNew.wOneItem, 0, 0);
            }
            if (((Item)object).timeUse > 0) {
                mGraphics2.drawRegion(AvMain.imgDelaySkill, 0, 0, MainTabNew.wOneItem - 1, MainTabNew.wOneItem - 1, 0, this.xBegin + MainTabNew.wOneItem / 2 + n % this.numW * MainTabNew.wOneItem, this.yBegin + MainTabNew.wOneItem / 2 + n / this.numW * MainTabNew.wOneItem, 3, true);
            }
            if (this.typeTab == 16 && !GameScreen.player.isSelling() && Item.VecItemSell.size() > 0) {
                for (int i = 0; i < Item.VecItemSell.size(); ++i) {
                    mainItem = (MainItem)Item.VecItemSell.elementAt(i);
                    if (mainItem.Id != ((Item)object).Id) continue;
                    mGraphics2.setColor(-2737107);
                    mGraphics2.drawRect(this.xBegin + MainTabNew.wOneItem / 2 + n % this.numW * MainTabNew.wOneItem - MainTabNew.wOneItem / 2 + 4, this.yBegin + MainTabNew.wOneItem / 2 + n / this.numW * MainTabNew.wOneItem - MainTabNew.wOneItem / 2 + 4, MainTabNew.wOneItem - 8, MainTabNew.wOneItem - 8, true);
                }
            }
            if (this.isTypeShop == SHOP_NANG_CAP_MEDEL || this.isTypeShop == SHOP_ANY_NGUYEN_LIEU || this.isTypeShop == SHOP_KHAM_NGOC || this.isTypeShop == SHOP_GHEP_NGOC || this.isTypeShop == SHOP_DUC_LO) {
                if (TabRebuildItem.itemRe != null && ((Item)object).Id == TabRebuildItem.itemRe.Id && ((Item)object).ItemCatagory == TabRebuildItem.itemRe.ItemCatagory) {
                    mGraphics2.setColor(-397307);
                    mGraphics2.drawRect(this.xBegin + MainTabNew.wOneItem / 2 + n % this.numW * MainTabNew.wOneItem - MainTabNew.wOneItem / 2 + 4, this.yBegin + MainTabNew.wOneItem / 2 + n / this.numW * MainTabNew.wOneItem - MainTabNew.wOneItem / 2 + 4, MainTabNew.wOneItem - 8, MainTabNew.wOneItem - 8, true);
                }
                if (TabRebuildItem.vecGem.size() > 0) {
                    for (int i = 0; i < TabRebuildItem.vecGem.size(); ++i) {
                        mainItem = (MainItem)TabRebuildItem.vecGem.elementAt(i);
                        mGraphics2.setColor(-397307);
                        if (mainItem == null || ((Item)object).typeMaterial != Item.TYPE_NGOC_KHAM && ((Item)object).typeMaterial != Item.TYPE_BUA_HUYEN_BI && this.isTypeShop != SHOP_ANY_NGUYEN_LIEU || ((Item)object).Id != mainItem.Id || ((Item)object).ItemCatagory != mainItem.ItemCatagory) continue;
                        mGraphics2.drawRect(this.xBegin + MainTabNew.wOneItem / 2 + n % this.numW * MainTabNew.wOneItem - MainTabNew.wOneItem / 2 + 4, this.yBegin + MainTabNew.wOneItem / 2 + n / this.numW * MainTabNew.wOneItem - MainTabNew.wOneItem / 2 + 4, MainTabNew.wOneItem - 8, MainTabNew.wOneItem - 8, true);
                    }
                }
            } else if (this.isTypeShop == INVEN_AND_REBUILD) {
                if (TabRebuildItem.itemRe != null && (((Item)object).Id == TabRebuildItem.itemRe.Id && ((Item)object).ItemCatagory == TabRebuildItem.itemRe.ItemCatagory || ((Item)object).ItemCatagory == 7 && ((Item)object).typeMaterial == 11 && TabRebuildItem.isLucky)) {
                    mGraphics2.setColor(-397307);
                    mGraphics2.drawRect(this.xBegin + MainTabNew.wOneItem / 2 + n % this.numW * MainTabNew.wOneItem - MainTabNew.wOneItem / 2 + 4, this.yBegin + MainTabNew.wOneItem / 2 + n / this.numW * MainTabNew.wOneItem - MainTabNew.wOneItem / 2 + 4, MainTabNew.wOneItem - 8, MainTabNew.wOneItem - 8, true);
                }
            } else if (this.isTypeShop == INVEN_AND_REPLACE) {
                if (TabRebuildItem.itemFree != null && ((Item)object).Id == TabRebuildItem.itemFree.Id && ((Item)object).ItemCatagory == TabRebuildItem.itemFree.ItemCatagory) {
                    mGraphics2.setColor(-397307);
                    mGraphics2.drawRect(this.xBegin + MainTabNew.wOneItem / 2 + n % this.numW * MainTabNew.wOneItem - MainTabNew.wOneItem / 2 + 4, this.yBegin + MainTabNew.wOneItem / 2 + n / this.numW * MainTabNew.wOneItem - MainTabNew.wOneItem / 2 + 4, MainTabNew.wOneItem - 8, MainTabNew.wOneItem - 8, true);
                }
                if (TabRebuildItem.itemPlus != null && ((Item)object).Id == TabRebuildItem.itemPlus.Id && ((Item)object).ItemCatagory == TabRebuildItem.itemPlus.ItemCatagory) {
                    mGraphics2.setColor(-2737107);
                    mGraphics2.drawRect(this.xBegin + MainTabNew.wOneItem / 2 + n % this.numW * MainTabNew.wOneItem - MainTabNew.wOneItem / 2 + 4, this.yBegin + MainTabNew.wOneItem / 2 + n / this.numW * MainTabNew.wOneItem - MainTabNew.wOneItem / 2 + 4, MainTabNew.wOneItem - 8, MainTabNew.wOneItem - 8, true);
                }
            } else if (this.isTypeShop == INVEN_AND_WING && TabRebuildItem.itemWing != null && ((Item)object).Id == TabRebuildItem.itemWing.Id && ((Item)object).ItemCatagory == TabRebuildItem.itemWing.ItemCatagory) {
                mGraphics2.setColor(-397307);
                mGraphics2.drawRect(this.xBegin + MainTabNew.wOneItem / 2 + n % this.numW * MainTabNew.wOneItem - MainTabNew.wOneItem / 2 + 4, this.yBegin + MainTabNew.wOneItem / 2 + n / this.numW * MainTabNew.wOneItem - MainTabNew.wOneItem / 2 + 4, MainTabNew.wOneItem - 8, MainTabNew.wOneItem - 8, true);
            }
            if (((Item)object).ItemCatagory != 4 || ((Item)object).typePotion >= 2 || this.typeTab != 0 || Player.timeDelayPotion[((Item)object).typePotion].value <= 0) continue;
            mGraphics2.drawRegion(AvMain.imgDelaySkill, 0, 0, MainTabNew.wOneItem - 1, MainTabNew.wOneItem - 1, 0, this.xBegin + MainTabNew.wOneItem / 2 + n % this.numW * MainTabNew.wOneItem, this.yBegin + MainTabNew.wOneItem / 2 + n / this.numW * MainTabNew.wOneItem, 3, false);
        }
        mGraphics2.setColor(MainTabNew.color[1]);
        mGraphics2.drawRect(this.xBegin, this.yBegin, MainTabNew.wblack, MainTabNew.wOneItem * this.numH, true);
        for (n = 0; n < this.numW / 2; ++n) {
            mGraphics2.drawRect(this.xBegin + MainTabNew.wOneItem + n * MainTabNew.wOneItem * 2, this.yBegin, MainTabNew.wOneItem, MainTabNew.wOneItem * this.numH, true);
        }
        for (n = 0; n < this.numH / 2; ++n) {
            mGraphics2.drawRect(this.xBegin, this.yBegin + MainTabNew.wOneItem + n * MainTabNew.wOneItem * 2, MainTabNew.wblack, MainTabNew.wOneItem, true);
        }
        if (this.idSelect > -1 && MainTabNew.Focus == 1) {
            mGraphics2.setColor(MainTabNew.color[2]);
            mGraphics2.drawRect(this.xBegin + (this.idSelect - this.currentTab * 42) % this.numW * MainTabNew.wOneItem + 1, this.yBegin + (this.idSelect - this.currentTab * 42) / this.numW * MainTabNew.wOneItem + 1, MainTabNew.wOneItem - 2, MainTabNew.wOneItem - 2, true);
            mGraphics2.setColor(MainTabNew.color[3]);
            mGraphics2.drawRect(this.xBegin + (this.idSelect - this.currentTab * 42) % this.numW * MainTabNew.wOneItem, this.yBegin + (this.idSelect - this.currentTab * 42) / this.numW * MainTabNew.wOneItem, MainTabNew.wOneItem, MainTabNew.wOneItem, true);
        }
        mGraphics.resetTransAndroid(mGraphics2);
        mGraphics2.restoreCanvas();
        mGraphics2.endClip();
        if (!GameCanvas.menu2.isShowMenu && GameCanvas.currentDialog == null && GameCanvas.subDialog == null && MainTabNew.Focus == 1 && MainTabNew.timePaintInfo > MainTabNew.timeRequest) {
            this.paintPopupContent(mGraphics2, false);
            if (this.vecListCmd != null) {
                for (n = 0; n < this.vecListCmd.size(); ++n) {
                    object = (iCommand)this.vecListCmd.elementAt(n);
                    ((iCommand)object).paint(mGraphics2, ((iCommand)object).xCmd, ((iCommand)object).yCmd);
                }
            }
        }
        if (GameScreen.help.Step >= 0) {
            Item item = null;
            if (this.idSelect > -1 && MainTabNew.Focus == 1) {
                item = (Item)mVector2.elementAt(this.idSelect);
            }
            if (!GameCanvas.menu2.isShowMenu && GameCanvas.currentDialog == null && GameCanvas.subDialog == null) {
                GameScreen.help.itemTabHelp(mGraphics2, item, this.typeTab);
            }
        }
        if (!GameCanvas.isSmallScreen && this.isTypeShop == TOC) {
            Item item = null;
            if (this.idSelect > -1 && MainTabNew.Focus == 1) {
                item = (Item)mVector2.elementAt(this.idSelect);
            }
            int n4 = GameScreen.player.hair;
            if (item != null) {
                n4 = item.imageId;
            }
            this.paintHairShop(mGraphics2, n4);
        }
        if (!GameScreen.player.isSelling() && this.typeTab == 15 && this.idSelect >= 0 && this.idSelect < Item.VecItem_Sell_in_store.size() && (item_sell2 = (item_sell)Item.VecItem_Sell_in_store.elementAt(this.idSelect)) != null) {
            mFont.tahoma_7_yellow.drawString(mGraphics2, T.price + ": " + MainItem.getDotNumber(item_sell2.price) + " " + T.coin, this.xBegin, this.yBegin + MainTabNew.wOneItem * 6, 0, false);
        }
    }

    public void update() {
        if (this.typeTab != NORMAL && this.typeTab != 16 && this.typeTab != 9 && this.typeTab != 13 && this.currentTab != 0) {
            this.currentTab = 0;
        }
        if (GameScreen.help.Step > 0) {
            GameScreen.help.updateHelp();
        }
        if (MainTabNew.Focus == 1) {
            if (this.listContent != null) {
                this.listContent.moveCamera();
            }
            if (GameCanvas.isTouch) {
                this.list.moveCamera();
            } else {
                MainScreen.cameraSub.UpdateCamera();
            }
            mVector mVector2 = this.typeTab == 15 ? Item.VecItemSell : (this.typeTab == 0 || this.typeTab == 16 ? Item.VecInvetoryPlayer : (this.typeTab == 9 ? Item.VecChestPlayer : (this.typeTab == 12 ? Item.VecClanInvetory : this.vecShop)));
            if (mVector2.size() == 0) {
                MainTabNew.Focus = 0;
                return;
            }
            if (this.idSelect >= mVector2.size()) {
                if (GameCanvas.isTouch) {
                    this.idSelect = -1;
                    this.vecListCmd = null;
                } else {
                    this.idSelect = mVector2.size() - 1;
                }
            }
            if (this.typeTab != SHOP_BANG) {
                this.updateContent(mVector2, this.idSelect);
            }
        } else {
            MainTabNew.timePaintInfo = 0;
        }
        if (this.currentTab == 0 && this.idSelect > 42 && !this.isShop) {
            this.idSelect = -1;
        }
    }

    public void updateContent(mVector mVector2, int n) {
        if (MainTabNew.timePaintInfo < MainTabNew.timeRequest + 2 && ++MainTabNew.timePaintInfo == MainTabNew.timeRequest) {
            this.setPaintInfo();
        }
        if (this.mContent == null && n >= 0 && n < mVector2.size()) {
            Item item = (Item)mVector2.elementAt(n);
            if (item.ItemCatagory != 5) {
                if (item.mcontent == null) {
                    if (item.timeupdateMore % 100 == 3) {
                        if (this.typeTab == 0 || this.typeTab == 16) {
                            GlobalService.gI().Item_More_Info((byte)0, (byte)item.Id);
                        }
                        ++item.timeupdateMore;
                    } else {
                        ++item.timeupdateMore;
                    }
                } else {
                    this.moreInfoconten = item.moreContenGem;
                    this.mContent = item.mcontent;
                    this.mcolor = item.mColor;
                    this.setYCon(item);
                }
            }
        }
    }

    public void updatekey() {
        mVector mVector2 = this.typeTab == 15 ? Item.VecItemSell : (this.typeTab == 0 || this.typeTab == 16 ? Item.VecInvetoryPlayer : (this.typeTab == 9 ? Item.VecChestPlayer : (this.typeTab == 12 ? Item.VecClanInvetory : this.vecShop)));
        if (MainTabNew.Focus == 1) {
            int n = this.idSelect;
            boolean bl = false;
            if (this.listContent != null) {
                if (GameCanvas.keyMyHold[2]) {
                    this.listContent.cmtoX = this.listContent.cmtoX > 0 ? (this.listContent.cmtoX -= GameCanvas.hText) : 0;
                    GameCanvas.clearKeyHold(2);
                } else if (GameCanvas.keyMyHold[8]) {
                    this.listContent.cmtoX = this.listContent.cmtoX < this.listContent.cmxLim ? (this.listContent.cmtoX += GameCanvas.hText) : this.listContent.cmxLim;
                    GameCanvas.clearKeyHold(8);
                }
            } else if (GameCanvas.keyMyHold[2]) {
                this.idSelect -= this.numW;
                GameCanvas.clearKeyHold(2);
                bl = true;
            } else if (GameCanvas.keyMyHold[8]) {
                this.idSelect += this.numW;
                GameCanvas.clearKeyHold(8);
                bl = true;
            }
            if (GameCanvas.keyMyHold[4]) {
                if (this.idSelect % this.numW == 0 && this.currentTab == 0) {
                    MainTabNew.Focus = 0;
                } else {
                    --this.idSelect;
                }
                if (this.currentTab == 1 && this.idSelect == 41) {
                    this.currentTab = (byte)(this.currentTab - 1);
                }
                if (this.currentTab == 2 && this.idSelect == 82) {
                    this.currentTab = (byte)(this.currentTab - 1);
                }
                GameCanvas.clearKeyHold(4);
                bl = true;
            } else if (GameCanvas.keyMyHold[6]) {
                ++this.idSelect;
                GameCanvas.clearKeyHold(6);
                if (maxTab > 1 && this.idSelect > 0 && this.idSelect % 42 == 0) {
                    this.currentTab = (byte)(this.currentTab + 1);
                    if (this.currentTab > maxTab - 1) {
                        this.currentTab = 0;
                        this.idSelect = 0;
                    }
                }
                bl = true;
            }
            if (bl) {
                this.listContent = null;
                this.idSelect = this.resetSelect(this.idSelect, mVector2.size() - 1, false);
                if (!(GameCanvas.isTouch || this.typeTab != 0 && this.typeTab != 16 && this.typeTab != 9 && this.typeTab != 15)) {
                    this.center = this.cmdMenuInven;
                }
                TabScreenNew.timeRepaint = 10;
            }
            if (n != this.idSelect) {
                MainScreen.cameraSub.moveCamera(0, (this.idSelect / this.numW - 1) * MainTabNew.wOneItem);
                MainTabNew.timePaintInfo = 0;
            }
        }
        super.updatekey();
    }

    public void setPaintInfo() {
        try {
            MainItem mainItem;
            this.mContent = null;
            this.mSubContent = null;
            this.mPlusContent = null;
            mVector mVector2 = this.typeTab == 15 ? Item.VecItemSell : (this.typeTab == 0 || this.typeTab == 16 ? Item.VecInvetoryPlayer : (this.typeTab == 9 ? Item.VecChestPlayer : (this.typeTab == 12 ? Item.VecClanInvetory : this.vecShop)));
            if (this.idSelect >= mVector2.size() || this.idSelect < 0) {
                if (this.idSelect > mVector2.size() - 1) {
                    this.idSelect = mVector2.size() - 1;
                }
                MainTabNew.timePaintInfo = 0;
                return;
            }
            Item item = (Item)mVector2.elementAt(this.idSelect);
            if (item != null && item.setNameNull()) {
                MainTabNew.timePaintInfo = 0;
                return;
            }
            if (item.ItemCatagory == 9) {
                MsgDialog.pet = (PetItem)item;
                this.isPet = true;
            } else {
                this.isPet = false;
            }
            this.name = item.itemName;
            this.colorName = item.colorNameItem;
            if (item.ItemCatagory == 7 && (mainItem = Item.getMaterial(item.Id)) != null) {
                this.colorName = mainItem.colorNameItem;
                if (this.typeTab == 15 && item.isSell) {
                    this.mPlusContent = item.mPlusContent;
                    this.mPlusColor = item.mPlusColor;
                }
            }
            this.moreInfoconten = item.moreContenGem;
            if (item.ItemCatagory == 3 || this.typeTab == 8) {
                this.mPlusContent = item.mPlusContent;
                this.mPlusColor = item.mPlusColor;
            }
            this.listContent = null;
            if (MainTabNew.longwidth > 0) {
                int n = 1;
                this.mContent = item.mcontent;
                this.mcolor = item.mColor;
                if (item.mcontent != null) {
                    n += this.mContent.length;
                }
                if (item.mPlusContent != null) {
                    n += item.mPlusContent.length;
                }
                if (n * GameCanvas.hText > MainTabNew.hMaxContent - this.hcmd) {
                    this.listContent = new ListNew(MainTabNew.xlongwidth, MainTabNew.ylongwidth, MainTabNew.longwidth, MainTabNew.hMaxContent, 0, 0, n * GameCanvas.hText - (MainTabNew.hMaxContent - this.hcmd));
                } else if (GameCanvas.isTouch) {
                    this.listContent = new ListNew(MainTabNew.xlongwidth, MainTabNew.ylongwidth, MainTabNew.longwidth, MainTabNew.hMaxContent, 0, 0, 0);
                }
                this.sosanh(item);
                return;
            }
            this.wContent = item.sizeW;
            this.setYCon(item);
            this.xCon = this.idSelect % this.numW < 2 ? this.xBegin + MainTabNew.wOneItem / 2 + this.idSelect % this.numW * MainTabNew.wOneItem : (this.idSelect % this.numW < 5 ? this.xBegin + MainTabNew.wOneItem / 2 + this.idSelect % this.numW * MainTabNew.wOneItem - this.wContent / 2 : this.xBegin + MainTabNew.wOneItem / 2 + this.idSelect % this.numW * MainTabNew.wOneItem - this.wContent);
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public void setYCon(Item item) {
        int n = 0;
        if (MainScreen.cameraSub.yCam > 0) {
            n = MainScreen.cameraSub.yCam / MainTabNew.wOneItem;
        }
        int n2 = 1;
        this.mContent = item.mcontent;
        this.moreInfoconten = item.moreContenGem;
        this.mcolor = item.mColor;
        if (item.mcontent != null) {
            n2 += this.mContent.length;
        }
        if (item.mPlusContent != null) {
            n2 += item.mPlusContent.length;
        }
        if (this.idSelect / this.numW < this.numHPaint / 2 + n) {
            this.yCon = this.yBegin + (this.idSelect / this.numW + 1) * MainTabNew.wOneItem + 2;
            if (this.yCon - MainScreen.cameraSub.yCam + (n2 + 1) * GameCanvas.hText > GameCanvas.h - (GameCanvas.hCommand - 5)) {
                this.yCon = GameCanvas.h - (GameCanvas.hCommand - 5) - ((n2 + 1) * GameCanvas.hText - MainScreen.cameraSub.yCam);
            }
        } else if (mSystem.isj2me) {
            int n3 = this.idSelect;
            if (this.currentTab > 0) {
                n3 -= 42;
            }
            this.yCon = this.yBegin + (n3 / this.numW + 1) * MainTabNew.wOneItem + 2;
            if (this.yCon - MainScreen.cameraSub.yCam + (n2 + 1) * GameCanvas.hText > GameCanvas.h - (GameCanvas.hCommand - 5)) {
                this.yCon = GameCanvas.h - (GameCanvas.hCommand - 5) - ((n2 + 1) * GameCanvas.hText - MainScreen.cameraSub.yCam);
            }
        } else {
            this.yCon = this.yBegin + (this.idSelect / this.numW + 1) * MainTabNew.wOneItem - 7 - n2 * GameCanvas.hText - MainScreen.cameraSub.yCam;
            if (this.yCon - MainScreen.cameraSub.yCam < 6) {
                this.yCon = 6 + MainScreen.cameraSub.yCam;
            }
        }
        if (mSystem.isj2me && this.yCon >= GameCanvas.h / 4) {
            this.yCon = GameCanvas.h / 2;
        }
        if ((n2 + 1) * GameCanvas.hText > MainTabNew.hMaxContent - this.hcmd) {
            this.listContent = new ListNew(this.xCon, this.yCon, this.wContent, MainTabNew.hMaxContent, 0, 0, (n2 + 1) * GameCanvas.hText - (MainTabNew.hMaxContent - this.hcmd));
        }
        this.sosanh(item);
    }

    public void sosanh(Item item) {
        if (this.typeTab == 0 && item.ItemCatagory == 3 && (item.classcharItem == 4 || item.classcharItem == GameScreen.player.clazz) && item.type_Only_Item < 12) {
            int n;
            Object object;
            int n2;
            int n3;
            byte by = (byte)MainTemplateItem.mItem_Rotate_Tem_Equip[item.type_Only_Item];
            Object object2 = null;
            if (by == -1) {
                n3 = 0;
                n2 = 0;
                Item item2 = (Item)Item.VecEquipPlayer.get("3");
                object = (Item)Item.VecEquipPlayer.get("9");
                if (item2 == null) {
                    object2 = object;
                } else if (object == null) {
                    object2 = item2;
                } else {
                    for (n = 0; n < item2.mInfo.length; ++n) {
                        n3 += item2.mInfo[n].value;
                    }
                    for (n = 0; n < ((Item)object).mInfo.length; ++n) {
                        n2 += ((Item)object).mInfo[n].value;
                    }
                    object2 = n3 >= n2 ? object : item2;
                }
            } else {
                object2 = (Item)Item.VecEquipPlayer.get("" + by);
            }
            if (object2 != null && ((Item)object2).type_Only_Item == item.type_Only_Item && this.mContent != null && ((Item)object2).moreContenGem.size() <= 0) {
                this.mSubContent = new String[this.mContent.length];
                this.mSubColor = new int[this.mContent.length];
                block2: for (n3 = 0; n3 < this.mSubContent.length; ++n3) {
                    this.mSubContent[n3] = "";
                    this.mSubColor[n3] = 0;
                    for (n2 = 0; n2 < ((Item)object2).mInfo.length; ++n2) {
                        if (item.mInfo[n3].id != ((Item)object2).mInfo[n2].id) continue;
                        int n4 = item.mInfo[n3].value - ((Item)object2).mInfo[n2].value;
                        if (n4 >= 0) {
                            this.mSubColor[n3] = 5;
                            this.mSubContent[n3] = "+";
                        } else {
                            this.mSubContent[n3] = "-";
                            this.mSubColor[n3] = 6;
                        }
                        int n5 = n3;
                        this.mSubContent[n5] = this.mSubContent[n5] + Item.getPercent(Item.isPercentInfoItem[item.mInfo[n3].id], CRes.abs(n4));
                        object = Item.nameInfoItem[item.mInfo[n3].id] + ": " + Item.getPercent(Item.isPercentInfoItem[item.mInfo[n3].id], item.mInfo[n3].value);
                        n = mFont.tahoma_7_black.getWidth((String)object + " " + this.mSubContent[n3]);
                        if (this.wContent >= n + 10) continue block2;
                        this.wContent = n + 10;
                        continue block2;
                    }
                }
            }
        }
    }

    public void updatePointer() {
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
        if (GameCanvas.isPointSelect(this.xBegin, this.yBegin, this.numW * MainTabNew.wOneItem, MainTabNew.hblack - MainTabNew.wOne5 / 2) && !bl) {
            n = (GameCanvas.px - this.xBegin) / MainTabNew.wOneItem + (GameCanvas.py - this.yBegin + MainScreen.cameraSub.yCam) / MainTabNew.wOneItem * this.numW;
            int n2 = 0;
            n2 = this.typeTab == 15 ? Item.VecItemSell.size() : (this.typeTab == 0 || this.typeTab == 16 ? Item.VecInvetoryPlayer.size() : (this.typeTab == 9 ? Item.VecChestPlayer.size() : (this.typeTab == 12 ? Item.VecClanInvetory.size() : (this.typeTab == 13 ? Item.VecPetContainer.size() : this.vecShop.size()))));
            if (n >= 0 && n < n2) {
                GameCanvas.isPointerSelect = false;
                int n3 = this.idSelect;
                if (this.currentTab == 1) {
                    n3 -= 42;
                }
                if (n == n3) {
                    if (MainTabNew.longwidth == 0) {
                        if (this.typeTab == 0 || this.typeTab == 16 || this.typeTab == 9 || this.typeTab == 15 || this.typeTab == 12 || this.typeTab == 8 && this.isTypeShop == SHOP_BANG || this.typeTab == 13) {
                            this.cmdMenuInven.perform();
                        } else if (this.isTypeShop == WING) {
                            this.cmdCreateWing.perform();
                        } else {
                            this.cmdHoiMua.perform();
                        }
                    }
                } else {
                    MainTabNew.timePaintInfo = 0;
                    this.idSelect = n + 42 * this.currentTab;
                    if (this.currentTab == 0 && this.idSelect == 42) {
                        this.idSelect = -1;
                    }
                    if (this.currentTab == 1 && this.idSelect == 84) {
                        this.idSelect = -1;
                    }
                    if (this.currentTab == 3 && this.idSelect == 126) {
                        this.idSelect = -1;
                    }
                    if (this.isShop) {
                        this.idSelect = n;
                    }
                    this.cmdListBig();
                    this.listContent = null;
                }
                if (MainTabNew.Focus != 1) {
                    MainTabNew.Focus = 1;
                }
            } else {
                this.idSelect = -1;
                MainTabNew.timePaintInfo = 0;
                this.listContent = null;
            }
        }
        if (this.vecListCmd != null && MainTabNew.Focus == 1 && MainTabNew.timePaintInfo > MainTabNew.timeRequest) {
            for (n = 0; n < this.vecListCmd.size(); ++n) {
                iCommand iCommand2 = (iCommand)this.vecListCmd.elementAt(n);
                iCommand2.updatePointer();
            }
        }
        super.updatePointer();
    }

    public void cmdListBig() {
        if (MainTabNew.longwidth > 0 && GameCanvas.isTouch) {
            this.hcmd = 0;
            mVector mVector2 = this.typeTab == 15 ? Item.VecItemSell : (this.typeTab == 0 || this.typeTab == 16 ? Item.VecInvetoryPlayer : (this.typeTab == 9 ? Item.VecChestPlayer : this.vecShop));
            if (this.idSelect >= 0 && this.idSelect < mVector2.size()) {
                Item item = (Item)mVector2.elementAt(this.idSelect);
                this.vecListCmd = this.doMenu(item);
                this.setPosCmd(this.vecListCmd);
                this.hcmd = (this.vecListCmd.size() + 1) / 2 * iCommand.hButtonCmd;
            }
        }
    }

    public void setVecShop(mVector mVector2) {
        this.vecShop.removeAllElements();
        this.vecShop = mVector2;
    }

    static {
        priceSellQuest = 1;
        PriceChatWorld = 0;
        isSortInven = false;
        IdTabSave = -1;
        idSeller = 0;
        allidNgocKham = new mHashtable();
    }
}

