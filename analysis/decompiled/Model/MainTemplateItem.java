/*
 * Decompiled with CFR 0.152.
 */
package Model;

import CLib.mHashtable;
import CLib.mVector;
import GameObjects.Item;
import netcommand.global.GlobalService;

public class MainTemplateItem {
    public int ID;
    public int IconId;
    public String name;
    int timenull;
    public static boolean isload = true;
    public int typeItem;
    public int idPartItem;
    public int classItem;
    public int[] mValueItem;
    public byte[] mValueName;
    public int sellPotion;
    public int valuePotion;
    public byte moneyType;
    public long PricePoition;
    public byte typePotion;
    public String contentPotion;
    public byte typeSell;
    public byte typeTrade;
    public static final byte TYPE_AO = 0;
    public static final byte TYPE_QUAN = 1;
    public static final byte TYPE_NON = 2;
    public static final byte TYPE_GANGTAY = 3;
    public static final byte TYPE_NHAN = 4;
    public static final byte TYPE_DAYCHUYEN = 5;
    public static final byte TYPE_GIAY = 6;
    public static final byte TYPE_CANH = 7;
    public static final byte TYPE_VUKHI1 = 8;
    public static final byte TYPE_VUKHI2 = 9;
    public static final byte TYPE_VUKHI3 = 10;
    public static final byte TYPE_VUKHI4 = 11;
    public static final byte TYPE_PET = 14;
    public static final byte I_VUKHI = 0;
    public static final byte I_AO = 1;
    public static final byte I_GANGTAY = 2;
    public static final byte I_NHAN1 = 3;
    public static final byte I_DAYCHUYEN = 4;
    public static final byte I_PET = 5;
    public static final byte I_NON = 6;
    public static final byte I_QUAN = 7;
    public static final byte I_GIAY = 8;
    public static final byte I_NHAN2 = 9;
    public static final byte I_CANH = 10;
    public static final byte I_HEAD = 12;
    public static final byte I_EYE = 13;
    public static final byte I_HAIR = 14;
    boolean canTrade = false;
    public static int[] mItem_Equip_Tem = new int[]{-1, 0, 3, 4, 5, 12, 2, 1, 6, 4, 7, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2};
    public static int[] mItem_Rotate_Tem_Equip = new int[]{1, 7, 6, 2, -1, 4, 8, 10, 0, 0, 0, 0, 5};
    public static mHashtable hashPotionTem = new mHashtable();
    public static mHashtable hashMaterialTem = new mHashtable();
    public static mHashtable hashMaterialTem2 = new mHashtable();
    public static mHashtable hashItemTem = new mHashtable();
    public static mHashtable hashPetTem = new mHashtable();

    public MainTemplateItem() {
    }

    public MainTemplateItem(short s, String string, byte by, byte by2, byte by3, short s2, int[] nArray, byte[] byArray) {
        this.ID = s;
        this.IconId = s2;
        this.name = string;
        this.typeItem = by;
        this.idPartItem = by2;
        this.classItem = by3;
        this.mValueItem = nArray;
        this.mValueName = byArray;
    }

    public MainTemplateItem(short s, short s2, long l, String string, String string2, byte by, byte by2, byte by3, short s3, boolean bl) {
        this.ID = s;
        this.IconId = s2;
        this.PricePoition = l;
        this.name = string;
        this.contentPotion = string2;
        this.typePotion = by;
        this.moneyType = by2;
        this.sellPotion = by3;
        this.valuePotion = s3;
        this.canTrade = bl;
    }

    public static void removeUpdateItemInventory(int n) {
        for (int i = 0; i < Item.VecInvetoryPlayer.size(); ++i) {
            Item item = (Item)Item.VecInvetoryPlayer.elementAt(i);
            if (item.ItemCatagory != n) continue;
            Item.VecInvetoryPlayer.removeElement(item);
            --i;
        }
    }

    public static void removeUpdateItemChest(int n) {
        for (int i = 0; i < Item.VecChestPlayer.size(); ++i) {
            Item item = (Item)Item.VecChestPlayer.elementAt(i);
            if (item.ItemCatagory != n) continue;
            Item.VecInvetoryPlayer.removeElement(item);
            --i;
        }
    }

    public static void removeUpdateItemVec(int n, mVector mVector2) {
        for (int i = 0; i < mVector2.size(); ++i) {
            Item item = (Item)mVector2.elementAt(i);
            if (item.ItemCatagory != n) continue;
            mVector2.removeElement(item);
            --i;
        }
    }

    public static MainTemplateItem getItemTem(short s) {
        MainTemplateItem mainTemplateItem = (MainTemplateItem)hashItemTem.get("" + s);
        if (mainTemplateItem == null) {
            mainTemplateItem = new MainTemplateItem();
            hashItemTem.put("" + s, mainTemplateItem);
            GlobalService.gI().getItemTem(s);
        }
        if (mainTemplateItem.name == null) {
            ++mainTemplateItem.timenull;
            if (mainTemplateItem.timenull >= 200) {
                GlobalService.gI().getItemTem(s);
                mainTemplateItem.timenull = 0;
            }
        }
        return mainTemplateItem;
    }

    public static MainTemplateItem getPotionTem(short s) {
        MainTemplateItem mainTemplateItem = (MainTemplateItem)hashPotionTem.get("" + s);
        return mainTemplateItem;
    }
}

