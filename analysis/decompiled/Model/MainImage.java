/*
 * Decompiled with CFR 0.152.
 */
package Model;

import CLib.mImage;

public class MainImage {
    public mImage img;
    public short w;
    public short h;
    public int count = -1;
    public int timeImageNull = 0;

    public MainImage() {
    }

    public MainImage(mImage mImage2) {
        this.img = mImage2;
        this.count = 0;
        this.w = (short)mImage.getImageWidth(mImage2.image);
        this.h = (short)mImage.getImageWidth(mImage2.image);
    }
}

