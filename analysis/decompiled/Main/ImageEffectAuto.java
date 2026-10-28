/*
 * Decompiled with CFR 0.152.
 */
package Main;

import CLib.mHashtable;
import CLib.mImage;
import GameEffect.EffectAuto;
import Main.GameCanvas;
import java.util.Enumeration;
import netcommand.global.GlobalService;

public class ImageEffectAuto {
    public static mHashtable hashImageEffAuto = new mHashtable();
    public long timeRemove;
    int imageId;
    public mImage img;

    public ImageEffectAuto(int n) {
        this.imageId = n;
        this.img = mImage.createImage("/effauto/eff_" + n + ".png");
        if (this.img == null || this.img.image == null) {
            this.img = EffectAuto.getEffectAutoImgFromRMS(n);
        }
        this.timeRemove = GameCanvas.timeNow;
    }

    public static void setRemove() {
        Enumeration enumeration = hashImageEffAuto.keys();
        while (enumeration.hasMoreElements()) {
            String string = (String)enumeration.nextElement();
            ImageEffectAuto imageEffectAuto = (ImageEffectAuto)hashImageEffAuto.get(string);
            if ((GameCanvas.timeNow - imageEffectAuto.timeRemove) / 1000L <= 60L) continue;
            if (imageEffectAuto.img != null) {
                imageEffectAuto.img.clearData();
            }
            hashImageEffAuto.remove(string);
        }
    }

    public static mImage setImage(int n) {
        ImageEffectAuto imageEffectAuto = (ImageEffectAuto)hashImageEffAuto.get("" + n);
        if (imageEffectAuto == null) {
            imageEffectAuto = new ImageEffectAuto(n);
            hashImageEffAuto.put("" + n, imageEffectAuto);
            if (imageEffectAuto == null || imageEffectAuto.img == null) {
                GlobalService.gI().load_image_data_part_char((byte)111, (short)n);
            }
        } else {
            imageEffectAuto.timeRemove = GameCanvas.timeNow;
        }
        return imageEffectAuto.img;
    }

    public static mImage setImageMatna(int n) {
        ImageEffectAuto imageEffectAuto = (ImageEffectAuto)hashImageEffAuto.get("" + n);
        if (imageEffectAuto == null) {
            imageEffectAuto = new ImageEffectAuto(n);
            hashImageEffAuto.put("" + n, imageEffectAuto);
            if (imageEffectAuto == null || imageEffectAuto.img == null) {
                GlobalService.gI().load_image_data_part_char((byte)7, (short)n);
            }
        } else {
            imageEffectAuto.timeRemove = GameCanvas.timeNow;
        }
        return imageEffectAuto.img;
    }

    public static void SetRemoveAll() {
        Enumeration enumeration = hashImageEffAuto.keys();
        while (enumeration.hasMoreElements()) {
            String string = (String)enumeration.nextElement();
            Object object = hashImageEffAuto.get(string);
            ImageEffectAuto imageEffectAuto = null;
            if (object != null) {
                imageEffectAuto = (ImageEffectAuto)object;
            }
            if (imageEffectAuto == null || imageEffectAuto.img == null) continue;
            imageEffectAuto.img.clearData();
        }
        hashImageEffAuto.clear();
    }
}

