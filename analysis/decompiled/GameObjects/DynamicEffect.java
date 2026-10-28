/*
 * Decompiled with CFR 0.152.
 */
package GameObjects;

import CLib.mGraphics;
import CLib.mHashtable;
import GameObjects.DataEffect;
import GameObjects.GameData;
import GameObjects.ImageIcon;
import GameObjects.MainObject;

public class DynamicEffect
extends MainObject {
    public static mHashtable ALL_DATA_DYNAMIC_EFFECT = new mHashtable();
    public short id;
    public long timeExist = -1L;
    byte index = 0;
    public byte stop = 0;

    public DynamicEffect(int n) {
        this.id = (short)n;
    }

    public void paint(mGraphics mGraphics2, int n, int n2) {
        ImageIcon imageIcon;
        DataEffect dataEffect = (DataEffect)ALL_DATA_DYNAMIC_EFFECT.get(this.id + "");
        if (dataEffect != null && (imageIcon = GameData.getImgIcon((short)(this.id + 8700), 112)) != null && imageIcon.img != null) {
            byte[] byArray = DynamicEffect.getSequence(this.id);
            dataEffect.paint(mGraphics2, byArray[this.index], n, n2, 0, imageIcon.img);
        }
    }

    public static void setDataEffect(byte[][] byArray) {
        for (int i = 0; i < byArray.length; ++i) {
            DataEffect dataEffect = new DataEffect(byArray[i]);
            ALL_DATA_DYNAMIC_EFFECT.put(i + "", dataEffect);
        }
    }

    public static byte[] getSequence(int n) {
        DataEffect dataEffect = (DataEffect)ALL_DATA_DYNAMIC_EFFECT.get(n + "");
        if (dataEffect != null) {
            return dataEffect.sequence;
        }
        return null;
    }

    public boolean canDestroy() {
        if (this.timeExist == -1L) {
            return false;
        }
        return System.currentTimeMillis() - this.timeExist >= 0L && this.index == 0;
    }

    public void update() {
        try {
            byte[] byArray = DynamicEffect.getSequence(this.id);
            if (byArray != null) {
                this.index = (byte)((this.index + 1) % byArray.length);
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
        if (this.canDestroy()) {
            this.isRemove = true;
        }
    }

    public void paint(mGraphics mGraphics2) {
        ImageIcon imageIcon;
        DataEffect dataEffect = (DataEffect)ALL_DATA_DYNAMIC_EFFECT.get(this.id + "");
        if (dataEffect != null && (imageIcon = GameData.getImgIcon((short)(this.id + 8700), 112)) != null && imageIcon.img != null) {
            byte[] byArray = DynamicEffect.getSequence(this.id);
            dataEffect.paint(mGraphics2, byArray[this.index], this.x, this.y, 0, imageIcon.img);
        }
    }

    public void setPosTo(short s, short s2) {
    }
}

