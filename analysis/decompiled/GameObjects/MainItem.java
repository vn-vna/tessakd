/*
 * Decompiled with CFR 0.152.
 */
package GameObjects;

import CLib.mFont;
import CLib.mGraphics;
import GameObjects.Item;
import GameObjects.MainInfoItem;
import GameObjects.MainObject;
import GameObjects.Player;
import Main.GameCanvas;
import Model.CRes;
import Model.T;
import Skill.HotKey;

public class MainItem
extends Item {
    public static final int FOOT = 8;
    public static final int ATB_FOOT_SNOW = 67;

    public MainItem(int n, int n2, String string, String string2, int n3, int n4, long l, byte by, byte by2, byte by3, byte by4) {
        this.Id = n;
        this.imageId = n2;
        this.itemName = string;
        this.typePotion = by;
        this.numPotion = n3;
        this.ItemCatagory = n4;
        this.priceItem = l;
        this.priceType = by2;
        this.canSell = by3;
        this.canTrade = by4;
        this.mMoreContent(string2);
        this.IndexSort = 0;
    }

    public MainItem clonePotion() {
        MainItem mainItem = new MainItem();
        mainItem.Id = this.Id;
        mainItem.imageId = this.imageId;
        mainItem.itemName = this.itemName;
        mainItem.typePotion = this.typePotion;
        mainItem.numPotion = this.numPotion;
        mainItem.ItemCatagory = this.ItemCatagory;
        mainItem.priceItem = this.priceItem;
        mainItem.priceType = this.priceType;
        mainItem.canSell = this.priceType;
        mainItem.canTrade = this.priceType;
        if (this.content != null) {
            mainItem.mMoreContent(this.content);
        }
        this.IndexSort = 0;
        return mainItem;
    }

    public void mMoreContent(String string) {
        super.mMoreContent(string);
    }

    public MainItem() {
    }

    public MainItem clone() {
        MainItem mainItem = new MainItem();
        mainItem.itemNameExcludeLv = this.itemNameExcludeLv;
        mainItem.itemName = this.itemName;
        mainItem.imageId = this.imageId;
        mainItem.Id = this.Id;
        mainItem.tier = this.tier;
        mainItem.colorNameItem = this.colorNameItem;
        mainItem.classcharItem = this.classcharItem;
        mainItem.ItemCatagory = this.ItemCatagory;
        mainItem.mInfo = this.mInfo;
        mainItem.type_Only_Item = this.type_Only_Item;
        mainItem.IdTem = this.IdTem;
        mainItem.priceItem = this.priceItem;
        mainItem.LvItem = this.LvItem;
        mainItem.canSell = this.canSell;
        mainItem.canTrade = this.canTrade;
        mainItem.timeUse = this.timeUse;
        mainItem.isLock = this.isLock;
        mainItem.timeDem = this.timeDem;
        mainItem.numPotion = this.numPotion;
        mainItem.priceType = this.priceType;
        mainItem.mInfo = this.mInfo;
        mainItem.IndexSort = this.IndexSort;
        mainItem.infoHop = new String[]{"", ""};
        return mainItem;
    }

    public static MainItem MainItem_Item(int n, String string, int n2, byte by, int n3, int n4, int n5, MainInfoItem[] mainInfoItemArray, int n6, boolean bl, short s, long l, short s2, byte by2, byte by3, int n7, byte by4, byte by5) {
        return new MainItem(n, string, n2, by, n3, n4, n5, mainInfoItemArray, n6, bl, s, l, s2, by2, by3, n7, by4, by5);
    }

    public static MainItem MainItem_Item(int n, String string, int n2, byte by, int n3, int n4, int n5, MainInfoItem[] mainInfoItemArray, int n6, boolean bl, short s, long l, short s2, byte by2, byte by3, int n7, byte by4, byte by5, int n8, long l2) {
        return new MainItem(n, string, n2, by, n3, n4, n5, mainInfoItemArray, n6, bl, s, l, s2, by2, by3, n7, by4, by5, n8, l2);
    }

    public MainItem(int n, String string, int n2, byte by, int n3, int n4, int n5, MainInfoItem[] mainInfoItemArray, int n6, boolean bl, short s, long l, short s2, byte by2, byte by3, int n7, byte by4, byte by5, int n8, long l2) {
        this.itemNameExcludeLv = string;
        this.itemName = string;
        this.imageId = n2;
        this.Id = n;
        this.tier = by;
        if (this.tier > 0) {
            this.itemName = this.itemName + " +" + this.tier;
        }
        this.colorNameItem = n3;
        this.classcharItem = n4;
        this.ItemCatagory = n5;
        this.mInfo = mainInfoItemArray;
        this.type_Only_Item = n6;
        this.IdTem = s;
        this.priceItem = l;
        this.LvItem = s2;
        this.canSell = by2;
        this.canTrade = by3;
        this.timeUse = n7;
        this.isLock = by5;
        this.timeDefaultItemFashion = n8;
        this.currentTimeItemFashion = l2;
        this.timeDem = n7 > 0 ? GameCanvas.timeNow : 0L;
        this.priceType = by4;
        if (this.mInfo != null && this.mInfo.length > 0 || this.priceItem > 0L || this.timeDem > 0L) {
            if (this.mInfo != null && this.mInfo.length > 0) {
                mainInfoItemArray = CRes.selectionSort(mainInfoItemArray);
            }
            if (!bl) {
                this.setContentItem();
            } else {
                this.setContentTem();
            }
        }
        this.IndexSort = (byte)10;
    }

    public MainItem(int n, String string, int n2, byte by, int n3, int n4, int n5, MainInfoItem[] mainInfoItemArray, int n6, boolean bl, short s, long l, short s2, byte by2, byte by3, int n7, byte by4, byte by5) {
        this.itemNameExcludeLv = string;
        this.itemName = string;
        this.imageId = n2;
        this.Id = n;
        this.tier = by;
        if (this.tier > 0) {
            this.itemName = this.itemName + " +" + this.tier;
        }
        this.colorNameItem = n3;
        this.classcharItem = n4;
        this.ItemCatagory = n5;
        this.mInfo = mainInfoItemArray;
        this.type_Only_Item = n6;
        this.IdTem = s;
        this.priceItem = l;
        this.LvItem = s2;
        this.canSell = by2;
        this.canTrade = by3;
        this.timeUse = n7;
        this.isLock = by5;
        this.timeDem = n7 > 0 ? GameCanvas.timeNow : 0L;
        this.priceType = by4;
        if (this.mInfo != null && this.mInfo.length > 0 || this.priceItem > 0L || this.timeDem > 0L) {
            if (this.mInfo != null && this.mInfo.length > 0) {
                mainInfoItemArray = CRes.selectionSort(mainInfoItemArray);
            }
            if (!bl) {
                this.setContentItem();
            } else {
                this.setContentTem();
            }
        }
        this.IndexSort = (byte)10;
        this.infoHop = new String[]{"", ""};
    }

    public static MainItem MainItem_Toc(int n, String string, int n2, byte by, int n3, MainInfoItem[] mainInfoItemArray, boolean bl, short s, long l, byte by2, byte by3, byte by4) {
        return new MainItem(n, string, n2, by, n3, mainInfoItemArray, bl, s, l, by2, by3, by4);
    }

    public MainItem(int n, String string, int n2, byte by, int n3, MainInfoItem[] mainInfoItemArray, boolean bl, short s, long l, byte by2, byte by3, byte by4) {
        this.itemName = string;
        this.imageId = n2;
        this.Id = n;
        this.tier = by;
        this.ItemCatagory = n3;
        this.mInfo = mainInfoItemArray;
        this.IdTem = s;
        this.priceItem = l;
        this.priceType = by2;
        this.canSell = by3;
        this.canTrade = by4;
        if (this.mInfo != null) {
            mainInfoItemArray = CRes.selectionSort(mainInfoItemArray);
            this.setContentHair();
        }
        this.IndexSort = 1;
    }

    public MainItem(int n, long l) {
        this.Id = n;
        this.priceItem = l;
        this.priceType = 1;
        this.ItemCatagory = 8;
        this.itemName = T.iconclan;
        this.mMoreContent(T.contentclan);
        this.IndexSort = 1;
    }

    public static MainItem MainItem_Material(int n, String string, int n2, int n3, long l, byte by, String string2, int n4, byte by2, short s, byte by3, byte by4) {
        return new MainItem(n, string, n2, n3, l, by, string2, n4, by2, s, by3, by4);
    }

    public MainItem cloneNguyenLieu() {
        MainItem mainItem = new MainItem();
        mainItem.itemName = this.itemName;
        mainItem.imageId = this.imageId;
        mainItem.Id = this.Id;
        mainItem.ItemCatagory = this.ItemCatagory;
        mainItem.priceItem = this.priceItem;
        mainItem.priceType = this.priceType;
        mainItem.value = this.value;
        mainItem.canSell = this.canSell;
        mainItem.canTrade = this.canTrade;
        mainItem.content = this.content;
        mainItem.typeMaterial = this.typeMaterial;
        if (this.content != null) {
            mainItem.mMoreContent(this.content);
        }
        mainItem.IndexSort = (byte)2;
        return mainItem;
    }

    public MainItem(int n, String string, int n2, int n3, long l, byte by, String string2, int n4, byte by2, short s, byte by3, byte by4) {
        this.itemName = string;
        this.imageId = n2;
        this.Id = n;
        this.ItemCatagory = n3;
        this.priceItem = l;
        this.priceType = by;
        this.value = n4;
        this.numPotion = s;
        this.canSell = by3;
        this.canTrade = by4;
        this.content = string2;
        this.typeMaterial = by2;
        if (string2 != null) {
            this.mMoreContent(string2);
        }
        this.IndexSort = (byte)2;
    }

    public MainItem(int n, String string, int n2, String string2, byte by, byte by2) {
        this.Id = n;
        this.imageId = n;
        this.itemName = string;
        this.numPotion = n2;
        this.ItemCatagory = 5;
        this.canSell = by;
        this.canTrade = by2;
        this.mMoreContent(string2);
        this.IndexSort = (byte)3;
    }

    public MainItem(int n, String string, int n2, int n3, byte by, String string2, MainInfoItem[] mainInfoItemArray, byte by2, byte by3, short s, byte by4, byte by5, byte by6) {
        this.Id = n;
        this.itemName = string;
        this.numPotion = n2;
        this.imageId = n3;
        this.ItemCatagory = by;
        this.colorNameItem = by2;
        this.mInfo = mainInfoItemArray;
        this.classcharItem = by3;
        this.LvItem = s;
        this.canSell = by5;
        this.canTrade = by6;
        this.tier = by4;
        if (this.tier > 0) {
            this.itemName = this.itemName + " +" + this.tier;
        }
        if (string2 != null) {
            this.mMoreContent(string2);
        } else {
            this.setContentItem();
        }
        this.IndexSort = (byte)4;
    }

    public MainItem(String string, int n, int n2, byte by, byte by2, byte by3) {
        this.colorNameItem = by3;
        this.itemName = string;
        this.numPotion = n2;
        this.imageId = n;
        this.ItemCatagory = by;
        this.tier = by2;
        if (this.tier > 0) {
            this.itemName = this.itemName + " +" + this.tier;
        }
    }

    public void setContentHair() {
        int n;
        if (this.itemName == null) {
            this.itemName = null;
            return;
        }
        int n2 = this.mInfo.length;
        this.mcontent = new String[n2];
        this.mColor = new int[n2];
        int n3 = mFont.tahoma_7b_white.getWidth(this.itemName);
        if (n3 > this.sizeW - 10) {
            this.sizeW = n3 + 10;
        }
        for (n = 0; n < n2; ++n) {
            MainInfoItem mainInfoItem = this.mInfo[n];
            this.mcontent[n] = Item.nameInfoItem[mainInfoItem.id] + ": " + Item.getPercent(Item.isPercentInfoItem[mainInfoItem.id], mainInfoItem.value);
            this.mColor[n] = Item.colorInfoItem[mainInfoItem.id];
            n3 = mFont.tahoma_7_black.getWidth(this.mcontent[n]);
            if (n3 <= this.sizeW - 10) continue;
            this.sizeW = n3 + 10;
        }
        n = 0;
        if (++n > 0) {
            this.mPlusContent = new String[n];
            this.mPlusColor = new int[n];
            n = 0;
            if (this.priceItem > 0L) {
                this.mPlusContent[n] = T.price + ": " + this.priceItem + this.getPriceType();
                this.mPlusColor[n] = 2;
                ++n;
            } else {
                this.mPlusContent[n] = T.dasohuu;
                this.mPlusColor[n] = 2;
                ++n;
            }
        } else {
            this.mPlusContent = null;
            this.mPlusColor = null;
        }
    }

    public static String getDotNumber(long l) {
        String string = "" + l;
        int n = string.length() / 3;
        if (string.length() % 3 == 0) {
            --n;
        }
        for (int i = 0; i < n; ++i) {
            int n2 = string.length() - (i + 1) * 3 - i;
            string = string.substring(0, n2) + "." + string.substring(n2, string.length());
        }
        return string;
    }

    public static String getNopercent(int n) {
        if (n % 100 == 0) {
            return n / 100 + "";
        }
        if (n % 10 == 0) {
            return n / 100 + "." + n % 100 / 10;
        }
        return n / 100 + "." + n % 100 / 10 + "" + n % 10;
    }

    public void paint(mGraphics mGraphics2) {
    }

    public void paintColorItem(mGraphics mGraphics2, int n, int n2, int n3) {
    }

    public void paintItem(mGraphics mGraphics2, int n, int n2, int n3, int n4, int n5) {
        super.paintItem(mGraphics2, n, n2, n3, n4, n5);
    }

    public void paintItem_not_num(mGraphics mGraphics2, int n, int n2, int n3, int n4, int n5) {
        super.paintItem_notnum(mGraphics2, n, n2, n3, n4, n5);
    }

    public void paintItemNew(mGraphics mGraphics2, int n, int n2, int n3, int n4, int n5) {
        super.paintItemNew(mGraphics2, n, n2, n3, n4, n5);
    }

    public static void setAddHotKey(byte by, boolean bl) {
        HotKey hotKey = null;
        if (by == 0) {
            hotKey = Player.mhotkey[Player.levelTab][4];
        } else if (by == 1) {
            hotKey = Player.mhotkey[Player.levelTab][3];
        }
        if (hotKey == null || hotKey.type != HotKey.NULL) {
            hotKey = new HotKey();
            hotKey.type = HotKey.NULL;
        }
        for (int i = 0; i < Item.VecInvetoryPlayer.size(); ++i) {
            MainItem mainItem = (MainItem)Item.VecInvetoryPlayer.elementAt(i);
            if (mainItem.ItemCatagory != 4 || mainItem.typePotion != by || !(hotKey.id < mainItem.Id && bl || hotKey.id > mainItem.Id && !bl) && hotKey.type != HotKey.NULL) continue;
            hotKey.setHotKey(mainItem.Id, HotKey.POTION, by);
        }
    }

    public void set_can_Sell(byte by) {
        this.can_sell_for_other_player = by;
    }

    public void setCanShell_notCanTrade(byte by) {
        this.CanShell_CanNotTrade = by;
    }

    public boolean isMaterialHopNguyenLieu() {
        return this.ItemCatagory == 7 && MainObject.isMaHopNguyenLieu(this.Id);
    }
}

