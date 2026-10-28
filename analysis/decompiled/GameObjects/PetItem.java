/*
 * Decompiled with CFR 0.152.
 */
package GameObjects;

import CLib.mGraphics;
import CLib.mImage;
import CLib.mVector;
import GameObjects.DataEffect;
import GameObjects.Item;
import GameObjects.MainInfoItem;
import GameObjects.MainObject;
import GameObjects.ObjectData;
import GameObjects.Pet;
import Main.GameCanvas;
import Model.CRes;
import Model.MainImage;

public class PetItem
extends Item {
    public byte petImageId;
    public byte nFrameImgPet;
    public int type;
    public short experience;
    public int age;
    public int frame;
    public short growpoint;
    public short maxgrow;
    public short spirit;
    public short health;
    public short agility;
    public short strength;
    public short maxtiemnang;
    public MainInfoItem petAttack;
    public short[] mvaluetiemnang;
    public boolean isPetTool;

    public PetItem() {
    }

    public PetItem(int n, String string, int n2, int n3, int n4, int n5, MainInfoItem[] mainInfoItemArray, int n6, short s, int n7, byte by, byte by2) {
        this.itemNameExcludeLv = string;
        this.itemName = string;
        this.imageId = n2;
        this.Id = n;
        this.tier = 0;
        if (this.tier > 0) {
            this.itemName = this.itemName + " +" + this.tier;
        }
        this.colorNameItem = n3;
        this.classcharItem = n4;
        this.ItemCatagory = n5;
        this.mInfo = mainInfoItemArray;
        this.type_Only_Item = n6;
        this.IdTem = (short)-1;
        this.priceItem = 0L;
        this.LvItem = s;
        this.canSell = 0;
        this.canTrade = 0;
        this.timeUse = 0;
        this.timeDem = this.timeUse > 0 ? GameCanvas.timeNow : 0L;
        this.priceType = 0;
        this.type = n7;
        if ((mVector)Pet.PET_DATA.get("" + by2) != null) {
            this.isPetTool = true;
        }
        this.nFrameImgPet = by;
        this.petImageId = by2;
        if (this.mInfo != null && this.mInfo.length > 0 || this.priceItem > 0L || this.timeDem > 0L) {
            if (this.mInfo != null && this.mInfo.length > 0) {
                mainInfoItemArray = CRes.selectionSort(mainInfoItemArray);
            }
            this.setContentItem();
        }
        this.IndexSort = (byte)10;
        if (this.mInfo != null && this.mInfo.length > 0) {
            for (int i = 0; i < this.mInfo.length; ++i) {
                MainInfoItem mainInfoItem = this.mInfo[i];
                if (mainInfoItem.id > 6 || mainInfoItem.value <= 0) continue;
                this.petAttack = mainInfoItem;
            }
        }
    }

    public void setInfoPetOld(int n, String string, int n2, int n3, int n4, int n5, MainInfoItem[] mainInfoItemArray, int n6, short s, int n7, byte by, byte by2) {
        this.itemNameExcludeLv = string;
        this.itemName = string;
        this.imageId = n2;
        this.Id = n;
        this.tier = 0;
        if (this.tier > 0) {
            this.itemName = this.itemName + " +" + this.tier;
        }
        this.colorNameItem = n3;
        this.classcharItem = n4;
        this.ItemCatagory = n5;
        this.mInfo = mainInfoItemArray;
        this.type_Only_Item = n6;
        this.IdTem = (short)-1;
        this.priceItem = 0L;
        this.LvItem = s;
        this.canSell = 0;
        this.canTrade = 0;
        this.timeUse = 0;
        this.timeDem = this.timeUse > 0 ? GameCanvas.timeNow : 0L;
        this.priceType = 0;
        this.type = n7;
        this.nFrameImgPet = by;
        this.petImageId = by2;
        if (this.mInfo != null && this.mInfo.length > 0 || this.priceItem > 0L || this.timeDem > 0L) {
            if (this.mInfo != null && this.mInfo.length > 0) {
                mainInfoItemArray = CRes.selectionSort(mainInfoItemArray);
            }
            this.setContentItem();
        }
        this.IndexSort = (byte)10;
        if (this.mInfo != null && this.mInfo.length > 0) {
            for (int i = 0; i < this.mInfo.length; ++i) {
                MainInfoItem mainInfoItem = this.mInfo[i];
                if (mainInfoItem.id > 6 || mainInfoItem.value <= 0) continue;
                this.petAttack = mainInfoItem;
            }
        }
    }

    public void setTimeUser(int n, long l) {
        this.timeDefaultItemFashion = n;
        this.currentTimeItemFashion = l;
    }

    public void setInfoPet(int n, short s, short s2, short s3, short s4, short s5, short s6, short s7, short s8) {
        this.age = n;
        this.growpoint = s;
        this.maxgrow = s6;
        this.strength = s2;
        this.agility = s3;
        this.health = s4;
        this.spirit = s5;
        this.maxtiemnang = s7;
        this.experience = s8;
        this.mvaluetiemnang = new short[4];
        this.mvaluetiemnang[0] = this.strength;
        this.mvaluetiemnang[1] = this.agility;
        this.mvaluetiemnang[2] = this.health;
        this.mvaluetiemnang[3] = this.spirit;
    }

    public void paintItem(mGraphics mGraphics2, int n, int n2, int n3, int n4, int n5) {
        super.paintItem(mGraphics2, n, n2, n3, n4, n5);
    }

    public void paintShowPet(mGraphics mGraphics2, int n, int n2, int n3, int n4, int n5, int n6) {
        MainImage mainImage = ObjectData.getImagePet(this.petImageId);
        if (this.isPetTool) {
            if (mainImage.img != null) {
                int n7 = 0;
                if (this.isFly()) {
                    n7 = 15;
                }
                mGraphics2.drawImage(MainObject.shadow, n, n2 + 10, 3, true);
                this.paintPetTool(mGraphics2, mainImage, n, n2 + n7);
                this.doChangeFrmaeBoss();
            }
        } else {
            if (this.type != 2) {
                mGraphics2.drawImage(MainObject.shadow, n, n2 + 10, 3, true);
                n2 -= 10;
            }
            if (mainImage.img != null) {
                int n8 = mImage.getImageHeight(mainImage.img.image) / this.nFrameImgPet;
                int n9 = mImage.getImageWidth(mainImage.img.image) / 2;
                mGraphics2.drawRegion(mainImage.img, n9 * (this.getframe(n6) / 3), n8 * (this.getframe(n6) % 3), n9, n8, 0, n, n2 + n4, 33, true);
            }
        }
    }

    public void doChangeFrmaeBoss() {
        mVector mVector2 = new mVector();
        mVector mVector3 = (mVector)Pet.PET_DATA.get("" + this.petImageId);
        if (mVector3 != null) {
            mVector2 = mVector3;
        }
        DataEffect dataEffect = null;
        if (mVector2.size() > 0) {
            dataEffect = (DataEffect)mVector2.elementAt(0);
            int n = 0;
            this.frame = (byte)((this.frame + 1) % dataEffect.getAnim((int)n, (int)0).frame.length);
        }
    }

    public void paintPetTool(mGraphics mGraphics2, MainImage mainImage, int n, int n2) {
        mVector mVector2 = new mVector();
        mVector mVector3 = (mVector)Pet.PET_DATA.get("" + this.petImageId);
        if (mVector3 != null) {
            mVector2 = mVector3;
        }
        if (mVector2.size() == 0) {
            return;
        }
        try {
            DataEffect dataEffect = (DataEffect)mVector2.elementAt(0);
            if (dataEffect != null) {
                int n3 = 0;
                int n4 = 0;
                mVector mVector4 = (mVector)Pet.PET_DATA.get("" + this.petImageId);
                if (mVector4 != null && mainImage != null && mainImage.img != null) {
                    dataEffect.paintPet(mGraphics2, dataEffect.getFrame(this.frame, n3, 0), n, n2 - n4, 0, mainImage.img);
                }
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public int getframe(int n) {
        switch (this.type) {
            case 0: {
                return Pet.mOwl[n][0][GameCanvas.gameTick % Pet.mOwl[n][0].length];
            }
            case 1: {
                return Pet.mBat[n][0][GameCanvas.gameTick % Pet.mBat[n][0].length];
            }
            case 2: {
                return Pet.mWoftAnimFrame[n][0][GameCanvas.gameTick % Pet.mWoftAnimFrame[n][0].length];
            }
            case 3: {
                return Pet.mEagle[n][0][GameCanvas.gameTick % Pet.mEagle[n][0].length];
            }
        }
        return Pet.mElfAnimFrame[n][0][GameCanvas.gameTick % Pet.mElfAnimFrame[n][0].length];
    }

    public boolean isFly() {
        DataEffect dataEffect;
        mVector mVector2 = new mVector();
        mVector mVector3 = (mVector)Pet.PET_DATA.get("" + this.petImageId);
        if (mVector3 != null) {
            mVector2 = mVector3;
        }
        return (dataEffect = (DataEffect)mVector2.elementAt(0)) != null && dataEffect.isFly <= -5;
    }
}

