/*
 * Decompiled with CFR 0.152.
 */
package GameObjects;

import CLib.mHashtable;
import CLib.mImage;
import CLib.mVector;
import GameObjects.Item;
import GameObjects.MainMonster;
import GameObjects.Pet;
import GameScreen.GameScreen;
import Main.GameCanvas;
import Model.CRes;
import Model.MainImage;
import Skill.Skill;
import java.util.Enumeration;
import netcommand.global.GlobalService;

public class ObjectData {
    public static final short ITEMMAP = 0;
    public static final short MONSTER = 1000;
    public static final short ITEM = 2000;
    public static final short NPC = 3000;
    public static final short POTION = 4000;
    public static final short QUEST_ITEM = 5000;
    public static final short MATERIAL = 5500;
    public static final short SKILL = 6000;
    public static final short ICON_CLAN = 7000;
    public static final short ICON_ARCHE_CLAN = 9500;
    public static final short CAPCHAR = 9999;
    public static final short ICON_PET = 10000;
    public static final short IMAGE_PET = 10200;
    public static final short IMAGE_TILE = 10400;
    public static final short IMAGE_TILE_WATER = 10500;
    public static final short IMAGE_TILE_SMALL = 10600;
    public static final short IMAGE_MOUNT = 10700;
    public static final short ITEM_2 = 13000;
    public static mVector vecSaveImage = new mVector("ObjectData vecSaveImage");

    public static MainImage getImagePartNPC(short s) {
        MainImage mainImage = (MainImage)GameScreen.HashImageNPC.get("" + s);
        if (mainImage == null) {
            mImage mImage2;
            mainImage = new MainImage();
            GameScreen.HashImageNPC.put("" + s, mainImage);
            String string = "/resLocal/npc/" + s + ".png";
            if (s >= 500) {
                int n = s - 500;
                string = "/resLocal/npc/icon/" + n + ".png";
            }
            mainImage.img = (mImage2 = mImage.createImage(string)) == null ? ObjectData.getFromRms((short)(s + 3000)) : mImage2;
        }
        if (mainImage.img == null) {
            ++mainImage.timeImageNull;
            if (mainImage.timeImageNull >= 200) {
                GlobalService.gI().load_image((short)(s + 3000));
                mainImage.timeImageNull = 0;
            }
        }
        return mainImage;
    }

    public static MainImage getImagePartItemMap(short s) {
        MainImage mainImage = (MainImage)GameScreen.HashImageItemMap.get("" + s);
        if (mainImage == null) {
            mainImage = new MainImage();
            GameScreen.HashImageItemMap.put("" + s, mainImage);
            mImage mImage2 = mImage.createImage("/resLocal/tree/" + s + ".png");
            mainImage.img = mImage2 == null ? ObjectData.getFromRms((short)(s + 0)) : mImage2;
        } else if (!GameCanvas.isTouch) {
            mainImage.count = (int)(GameCanvas.timeNow / 1000L);
        }
        if (mainImage.img == null) {
            ++mainImage.timeImageNull;
            if (mainImage.timeImageNull >= 200) {
                GlobalService.gI().load_image((short)(s + 0));
                mainImage.timeImageNull = 0;
            }
        }
        return mainImage;
    }

    public static MainImage getImagePartMonster(short s) {
        MainImage mainImage = (MainImage)MainMonster.HashImageMonster.get("" + s);
        if (mainImage == null) {
            mainImage = new MainImage();
            MainMonster.HashImageMonster.put("" + s, mainImage);
            mImage mImage2 = mImage.createImage("/resLocal/mons/" + s + ".png");
            mainImage.img = mImage2 == null ? ObjectData.getFromRms((short)(s + 1000)) : mImage2;
        } else if (!GameCanvas.isTouch) {
            mainImage.count = (int)(GameCanvas.timeNow / 1000L);
        }
        if (mainImage.img == null) {
            ++mainImage.timeImageNull;
            if (mainImage.timeImageNull >= 200) {
                GlobalService.gI().load_image((short)(s + 1000));
                mainImage.timeImageNull = 0;
            }
        }
        return mainImage;
    }

    public static MainImage getImageItem(short s) {
        MainImage mainImage = (MainImage)Item.HashImageItem.get("" + s);
        if (mainImage == null) {
            mainImage = new MainImage();
            Item.HashImageItem.put("" + s, mainImage);
            mImage mImage2 = mImage.createImage("/resLocal/icon/icon_" + s + ".png");
            mainImage.img = mImage2 == null ? ObjectData.getFromRms((short)(s + 2000)) : mImage2;
        }
        if (mainImage.img == null) {
            ++mainImage.timeImageNull;
            if (mainImage.timeImageNull >= 200) {
                GlobalService.gI().load_image((short)(s + 2000));
                mainImage.timeImageNull = 0;
            }
        }
        return mainImage;
    }

    public static MainImage getImagePotion(short s) {
        MainImage mainImage = (MainImage)Item.HashImagePotion.get("" + s);
        if (mainImage == null) {
            mainImage = new MainImage();
            Item.HashImagePotion.put("" + s, mainImage);
            String string = "/resLocal/potion/potionIcon_" + (s + 1 >= 10 ? s + 1 + "" : "0" + (s + 1)) + ".png";
            mImage mImage2 = mImage.createImage(string);
            mainImage.img = mImage2 == null ? ObjectData.getFromRms((short)(s + 4000)) : mImage2;
        }
        if (mainImage.img == null) {
            ++mainImage.timeImageNull;
            if (mainImage.timeImageNull >= 200) {
                GlobalService.gI().load_image((short)(s + 4000));
                mainImage.timeImageNull = 0;
            }
        }
        return mainImage;
    }

    public static MainImage getImageQuestItem(short s) {
        MainImage mainImage = (MainImage)Item.HashImageQuestItem.get("" + s);
        if (mainImage == null) {
            mainImage = new MainImage();
            Item.HashImageQuestItem.put("" + s, mainImage);
            mImage mImage2 = mImage.createImage("/resLocal/iconq/" + s + ".png");
            mainImage.img = mImage2 == null ? ObjectData.getFromRms((short)(s + 5000)) : mImage2;
        }
        if (mainImage.img == null) {
            ++mainImage.timeImageNull;
            if (mainImage.timeImageNull >= 200) {
                GlobalService.gI().load_image((short)(s + 5000));
                mainImage.timeImageNull = 0;
            }
        }
        return mainImage;
    }

    public static MainImage getImageMaterial(short s) {
        MainImage mainImage = (MainImage)Item.HashImageMaterial.get("" + s);
        if (mainImage == null) {
            mainImage = new MainImage();
            Item.HashImageMaterial.put("" + s, mainImage);
            mImage mImage2 = mImage.createImage("/resLocal/material/" + s + ".png");
            mainImage.img = mImage2 == null ? ObjectData.getFromRms((short)(s + 5500)) : mImage2;
        }
        if (mainImage.img == null) {
            ++mainImage.timeImageNull;
            if (mainImage.timeImageNull >= 200) {
                GlobalService.gI().load_image((short)(s + 5500));
                mainImage.timeImageNull = 0;
            }
        }
        return mainImage;
    }

    public static MainImage getImageSkill(short s) {
        MainImage mainImage = (MainImage)Skill.hashImageSkill.get("" + s);
        if (mainImage == null) {
            mainImage = new MainImage();
            Skill.hashImageSkill.put("" + s, mainImage);
            String string = s < 10 ? "/resLocal/skill/iconSkill_0" + s + ".png" : "/resLocal/skill/iconSkill_" + s + ".png";
            mImage mImage2 = mImage.createImage(string);
            mainImage.img = mImage2 == null ? ObjectData.getFromRms((short)(s + 6000)) : mImage2;
        }
        if (mainImage.img == null) {
            ++mainImage.timeImageNull;
            if (mainImage.timeImageNull >= 200) {
                GlobalService.gI().load_image((short)(s + 6000));
                mainImage.timeImageNull = 0;
            }
        }
        return mainImage;
    }

    public static MainImage getImageIconClan(short s) {
        MainImage mainImage = (MainImage)Item.HashImageIconClan.get("" + s);
        if (mainImage == null) {
            mImage mImage2;
            mainImage = new MainImage();
            Item.HashImageIconClan.put("" + s, mainImage);
            String string = "/resLocal/iconclan/" + s + ".png";
            if (s >= 500) {
                int n = s - 500;
                string = "/resLocal/iconclan/shop/" + n + ".png";
            }
            mainImage.img = (mImage2 = mImage.createImage(string)) == null ? ObjectData.getFromRms((short)(s + 7000)) : mImage2;
        } else if (!GameCanvas.isTouch) {
            mainImage.count = (int)(GameCanvas.timeNow / 1000L);
        }
        if (mainImage.img == null) {
            ++mainImage.timeImageNull;
            if (mainImage.timeImageNull >= 200) {
                GlobalService.gI().load_image((short)(s + 7000));
                mainImage.timeImageNull = 0;
            }
        }
        return mainImage;
    }

    public static MainImage getImageIconArCheClan(short s) {
        MainImage mainImage = (MainImage)Item.HashImageIconArcheClan.get("" + s);
        if (mainImage == null) {
            mainImage = new MainImage();
            Item.HashImageIconArcheClan.put("" + s, mainImage);
            mImage mImage2 = mImage.createImage("/resLocal/iconclan/huyhieu/" + s + ".png");
            mainImage.img = mImage2 == null ? ObjectData.getFromRms((short)(s + 9500)) : mImage2;
        }
        if (mainImage.img == null) {
            ++mainImage.timeImageNull;
            if (mainImage.timeImageNull >= 200) {
                GlobalService.gI().load_image((short)(s + 9500));
                mainImage.timeImageNull = 0;
            }
        }
        return mainImage;
    }

    public static MainImage getImageIconPet(short s) {
        MainImage mainImage = (MainImage)Item.HashImagePetIcon.get("" + s);
        if (mainImage == null) {
            mainImage = new MainImage();
            Item.HashImagePetIcon.put("" + s, mainImage);
            mImage mImage2 = mImage.createImage("/resLocal/pet/icon/icon_pet_" + s + ".png");
            mainImage.img = mImage2 == null ? ObjectData.getFromRms((short)(s + 10000)) : mImage2;
        }
        return mainImage;
    }

    public static MainImage getImagePet(short s) {
        MainImage mainImage = (MainImage)Pet.HashImagePet.get("" + s);
        if (mainImage == null) {
            mainImage = new MainImage();
            Pet.HashImagePet.put("" + s, mainImage);
            mImage mImage2 = mImage.createImage("/resLocal/pet/" + s + ".png");
            mainImage.img = mImage2 == null ? ObjectData.getFromRms((short)(s + 10200)) : mImage2;
        }
        if (mainImage.img == null) {
            ++mainImage.timeImageNull;
            if (mainImage.timeImageNull >= 200) {
                GlobalService.gI().load_image((short)(s + 10200));
                mainImage.timeImageNull = 0;
            }
        }
        return mainImage;
    }

    public static mImage getFromRms(short s) {
        mImage mImage2 = null;
        String string = "image" + s;
        byte[] byArray = null;
        if (ObjectData.setIdOK(s)) {
            byArray = CRes.loadRMS("image" + s);
        }
        if (byArray == null) {
            GlobalService.gI().load_image(s);
            return mImage2;
        }
        try {
            mImage2 = mImage.createImage(byArray, 0, byArray.length, string);
        }
        catch (Exception exception) {
            GlobalService.gI().load_image(s);
            return null;
        }
        return mImage2;
    }

    public static boolean setIdOK(short s) {
        return s >= 4000 && s < 5000 || s >= 3000 && s < 3500 || s >= 6000 && s < 7000;
    }

    public static void setToRms(byte[] byArray, short s) {
        try {
            CRes.saveRMS("image" + s, byArray);
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public static void checkDelHash(mHashtable mHashtable2) {
        Enumeration enumeration = mHashtable2.keys();
        while (enumeration.hasMoreElements()) {
            String string = (String)enumeration.nextElement();
            MainImage mainImage = (MainImage)mHashtable2.get(string);
            if (GameCanvas.timeNow / 1000L - (long)mainImage.count <= 300L) continue;
            if (mainImage.img != null) {
                mainImage.img.clearData();
            }
            mHashtable2.remove(string);
        }
    }

    public static MainImage getImageMount(short s) {
        MainImage mainImage = (MainImage)Item.HashImageMount.get("" + s);
        if (mainImage == null) {
            mainImage = new MainImage();
            Item.HashImageMount.put("" + s, mainImage);
            mImage mImage2 = mImage.createImage("/resLocal/mount/" + s + ".png");
            mainImage.img = mImage2 == null ? ObjectData.getFromRms((short)(s + 10700)) : mImage2;
        }
        if (mainImage.img == null) {
            ++mainImage.timeImageNull;
            if (mainImage.timeImageNull >= 200) {
                GlobalService.gI().load_image((short)(s + 10700));
                mainImage.timeImageNull = 0;
            }
        }
        return mainImage;
    }
}

