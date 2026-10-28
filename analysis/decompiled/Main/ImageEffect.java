/*
 * Decompiled with CFR 0.152.
 */
package Main;

import CLib.mHashtable;
import CLib.mImage;
import Main.GameCanvas;
import java.util.Enumeration;
import netcommand.global.GlobalService;

public class ImageEffect {
    public static mHashtable hashImageEff = new mHashtable();
    public static long lastTime = 0L;
    public long timeRemove;
    public int imageId;
    public mImage img;

    public ImageEffect(int n) {
        this.imageId = n;
        this.img = mImage.createImage("/eff/g" + n + ".png");
        if (this.img == null || this.img.image == null) {
            this.img = ImageEffect.getEffectImgFromRMS(n);
        }
        this.timeRemove = GameCanvas.timeNow;
    }

    public ImageEffect() {
    }

    public ImageEffect(int n, mImage mImage2) {
        this.imageId = n;
        this.img = mImage2;
        this.timeRemove = GameCanvas.timeNow;
    }

    public static mImage setImage(int n) {
        ImageEffect imageEffect = (ImageEffect)hashImageEff.get("" + n);
        if (imageEffect == null) {
            imageEffect = new ImageEffect(n);
            hashImageEff.put("" + n, imageEffect);
        } else {
            imageEffect.timeRemove = GameCanvas.timeNow;
        }
        return imageEffect.img;
    }

    public static void SetRemove() {
        Enumeration enumeration = hashImageEff.keys();
        while (enumeration.hasMoreElements()) {
            String string = (String)enumeration.nextElement();
            ImageEffect imageEffect = (ImageEffect)hashImageEff.get(string);
            if ((GameCanvas.timeNow - imageEffect.timeRemove) / 1000L <= 60L) continue;
            if (imageEffect.img != null) {
                imageEffect.img.clearData();
            }
            hashImageEff.remove(string);
        }
    }

    public static void SetRemoveAll() {
        Enumeration enumeration = hashImageEff.keys();
        while (enumeration.hasMoreElements()) {
            String string = (String)enumeration.nextElement();
            Object object = hashImageEff.get(string);
            ImageEffect imageEffect = null;
            if (object != null) {
                imageEffect = (ImageEffect)object;
            }
            if (imageEffect == null || imageEffect.img == null) continue;
            imageEffect.img.clearData();
        }
        hashImageEff.clear();
    }

    public static mImage getEffectImgFromRMS(int n) {
        mImage mImage2 = new mImage();
        lastTime = GameCanvas.timeNow;
        GlobalService.gI().load_image_data_part_char((byte)110, (short)n);
        return mImage2;
    }
}

