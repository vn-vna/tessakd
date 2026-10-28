/*
 * Decompiled with CFR 0.152.
 */
package GameObjects;

import CLib.mHashtable;
import CLib.mImage;
import CLib.mSystem;
import CLib.mVector;
import GameObjects.ImageIcon;
import Main.GameCanvas;
import Model.FrameImage;
import java.util.Enumeration;
import netcommand.global.GlobalService;

public class GameData {
    public static int ID_START_SKILL = 0;
    private static GameData me;
    public static FrameImage imgSkillIcon;
    public static mVector imgHorse;
    public static mHashtable listImgIcon;
    private boolean loadData = false;
    static byte idWait;

    public static GameData gI() {
        return me == null ? (me = new GameData()) : me;
    }

    public void getImage() {
        if (!this.loadData) {
            GameData.loadImgPotion();
            GameData.loadImgGemItem();
            this.loadData = true;
        }
    }

    public static void loadImgPotion() {
    }

    private static void createImgHorse(byte[] byArray, byte[][] byArray2) {
    }

    public static void saveImgSkill(int n, byte[] byArray) {
    }

    public static void saveImgGem(byte by, byte[] byArray) {
    }

    public static void loadImgGemItem() {
    }

    public static void setIndexWait() {
    }

    public static ImageIcon getImgIcon(short s, int n) {
        ImageIcon imageIcon = (ImageIcon)listImgIcon.get(s + "");
        int n2 = s - ID_START_SKILL;
        if (imageIcon == null) {
            imageIcon = new ImageIcon();
            imageIcon.id = s;
            imageIcon.isLoad = true;
            listImgIcon.put(s + "", imageIcon);
            if (imageIcon.img == null && s >= ID_START_SKILL) {
                try {
                    imageIcon.img = mImage.createImage("/imgDataEff/" + n2 + ".png");
                }
                catch (Exception exception) {
                    // empty catch block
                }
            }
            if (imageIcon.img == null && mSystem.currentTimeMillis() / 1000L - imageIcon.timeGetBack > 30L) {
                GlobalService.gI().load_image_data_part_char((byte)n, (short)n2);
            }
        } else {
            imageIcon.timeRemove = GameCanvas.timeNow;
        }
        return imageIcon;
    }

    public static void RequestImgandData(short s, int n) {
        ImageIcon imageIcon = (ImageIcon)listImgIcon.get(s + "");
        int n2 = s - ID_START_SKILL;
        if (imageIcon == null) {
            imageIcon = new ImageIcon();
            imageIcon.id = s;
            imageIcon.isLoad = true;
            listImgIcon.put(s + "", imageIcon);
            if (imageIcon.img == null && s >= ID_START_SKILL) {
                try {
                    imageIcon.img = mImage.createImage("/imgDataEff/" + n2 + ".png");
                }
                catch (Exception exception) {
                    // empty catch block
                }
            }
            if (imageIcon.img == null && mSystem.currentTimeMillis() / 1000L - imageIcon.timeGetBack > 30L) {
                GlobalService.gI().load_image_data_part_char((byte)n, (short)n2);
            }
            imageIcon.timeRemove = (int)(mSystem.currentTimeMillis() / 1000L);
        }
    }

    public static void SetRemove() {
        Enumeration enumeration = listImgIcon.keys();
        while (enumeration.hasMoreElements()) {
            String string = (String)enumeration.nextElement();
            ImageIcon imageIcon = (ImageIcon)listImgIcon.get(string);
            if ((GameCanvas.timeNow - imageIcon.timeRemove) / 1000L <= 60L) continue;
            if (imageIcon.img != null) {
                imageIcon.img.clearData();
            }
            listImgIcon.remove(string);
        }
    }

    static {
        imgHorse = new mVector();
        listImgIcon = new mHashtable();
        idWait = 0;
    }
}

