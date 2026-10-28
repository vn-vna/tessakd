/*
 * Decompiled with CFR 0.152.
 */
package Model;

import CLib.mGraphics;
import CLib.mHashtable;
import CLib.mImage;
import GameEffect.EffectSkill;
import GameObjects.ObjectData;
import Main.ImageEffect;
import Model.FilePack;
import Model.MainImage;

public class FrameImage {
    public int frameWidth;
    public int frameHeight;
    public int nFrame;
    public mImage imgFrame;
    public int Id = -1;
    public int numWidth;
    public int numHeight;
    public static mHashtable HashFrameImageMount = new mHashtable();
    boolean isNgua = false;

    public FrameImage(mImage mImage2, int n, int n2) {
        if (mImage2 != null) {
            this.imgFrame = mImage2;
            this.frameWidth = n;
            this.frameHeight = n2;
            this.nFrame = mImage.getImageHeight(mImage2.image) / n2;
        }
    }

    public void clearData() {
        if (this.imgFrame != null) {
            this.imgFrame.clearData();
        }
    }

    public int getNFrame() {
        return this.nFrame;
    }

    public FrameImage(int n) {
        this.Id = n;
        this.frameWidth = EffectSkill.arrInfoEff[n][0];
        this.frameHeight = EffectSkill.arrInfoEff[n][1];
        this.nFrame = EffectSkill.arrInfoEff[n][2];
    }

    public static FrameImage init(String string, int n, int n2) {
        return new FrameImage(FilePack.getImg(string), n, n2);
    }

    public FrameImage(mImage mImage2, int n, int n2, int n3) {
        mImage mImage3 = mImage2;
        this.numWidth = n;
        this.numHeight = n2;
        this.frameWidth = mImage.getImageWidth(mImage3.image) / n;
        this.frameHeight = mImage.getImageHeight(mImage3.image) / n2;
        this.nFrame = n * n2 - n3;
    }

    public static FrameImage getFrameImageMount(short s, int n, int n2, int n3) {
        FrameImage frameImage = (FrameImage)HashFrameImageMount.get("" + s);
        if (frameImage == null) {
            frameImage = new FrameImage(s, n, n2, n3);
            HashFrameImageMount.put("" + s, frameImage);
        } else if (frameImage.imgFrame == null) {
            MainImage mainImage = ObjectData.getImageMount(s);
            if (mainImage != null && mainImage.img != null) {
                frameImage.imgFrame = mainImage.img;
                frameImage.numWidth = n;
                frameImage.numHeight = n2;
                frameImage.frameWidth = mImage.getImageWidth(frameImage.imgFrame.image) / n;
                frameImage.frameHeight = mImage.getImageHeight(frameImage.imgFrame.image) / n2;
                if (frameImage.imgFrame != null || frameImage.imgFrame.image != null) {
                    frameImage.nFrame = n * n2 - n3;
                }
            }
        } else {
            frameImage.numWidth = n;
            frameImage.numHeight = n2;
            frameImage.frameWidth = mImage.getImageWidth(frameImage.imgFrame.image) / n;
            frameImage.frameHeight = mImage.getImageHeight(frameImage.imgFrame.image) / n2;
            if (frameImage.imgFrame != null || frameImage.imgFrame.image != null) {
                frameImage.nFrame = n * n2 - n3;
            }
        }
        return frameImage;
    }

    public FrameImage(int n, int n2, int n3, int n4, int n5) {
        this.Id = n;
        this.isNgua = true;
        this.numWidth = n2;
        this.numHeight = n3;
        mImage mImage2 = mImage.createImage("/vantieu/bo.png");
        MainImage mainImage = new MainImage(mImage2);
        if (mainImage != null && mainImage.img != null) {
            this.imgFrame = mainImage.img;
            this.frameWidth = mImage.getImageWidth(this.imgFrame.image) / n2;
            this.frameHeight = mImage.getImageHeight(this.imgFrame.image) / n3;
            if (this.imgFrame != null || this.imgFrame.image != null) {
                this.nFrame = n2 * n3 - n4;
            }
        }
    }

    public FrameImage(int n, int n2, int n3, int n4) {
        this.Id = n;
        this.isNgua = true;
        this.numWidth = n2;
        this.numHeight = n3;
        MainImage mainImage = ObjectData.getImageMount((short)n);
        if (mainImage != null && mainImage.img != null) {
            this.imgFrame = mainImage.img;
            this.frameWidth = mImage.getImageWidth(this.imgFrame.image) / n2;
            this.frameHeight = mImage.getImageHeight(this.imgFrame.image) / n3;
            if (this.imgFrame != null || this.imgFrame.image != null) {
                this.nFrame = n2 * n3 - n4;
            }
        }
    }

    public void drawFrameNew(int n, int n2, int n3, int n4, int n5, mGraphics mGraphics2) {
        if (n >= 0 && n < this.nFrame) {
            try {
                int n6 = 0;
                int n7 = n * this.frameHeight;
                n6 = n / this.numHeight * this.frameWidth;
                n7 = n % this.numHeight * this.frameHeight;
                mGraphics2.drawRegion(this.imgFrame, n6, n7, this.frameWidth, this.frameHeight, n4, n2, n3, n5, true);
            }
            catch (Exception exception) {
                // empty catch block
            }
        }
    }

    public void drawFrame(int n, int n2, int n3, int n4, int n5, mGraphics mGraphics2) {
        if (this.imgFrame == null) {
            mImage mImage2 = ImageEffect.setImage(this.Id);
            if (mImage2 != null) {
                this.imgFrame = mImage2;
            }
            if (mImage2.image != null) {
                this.nFrame = mImage.getImageHeight(this.imgFrame.image) / this.frameHeight;
            }
        } else if (n >= 0 && n < this.nFrame && this.imgFrame != null && this.imgFrame.image != null) {
            mGraphics2.drawRegion(this.imgFrame, 0, n * this.frameHeight, this.frameWidth, this.frameHeight, n4, n2, n3, n5, true);
        }
    }

    public void drawFrameEffectSkill(int n, int n2, int n3, int n4, int n5, mGraphics mGraphics2) {
        mImage mImage2 = ImageEffect.setImage(this.Id);
        if (mImage2 != null && mImage2.image != null) {
            int n6;
            if (n > this.nFrame) {
                n = this.nFrame;
            }
            if ((n6 = n * this.frameHeight) > this.frameHeight * (this.nFrame - 1) || n6 < 0) {
                n6 = this.frameHeight * (this.nFrame - 1);
            }
            mGraphics2.drawRegion(mImage2, 0, n6, this.frameWidth, this.frameHeight, n4, n2, n3, n5, true);
        }
    }

    public void drawFrameEffectSkill1(int n, int n2, int n3, int n4, mGraphics mGraphics2) {
        mImage mImage2 = ImageEffect.setImage(this.Id);
        if (mImage2 != null && mImage2.image != null) {
            int n5;
            if (n > this.nFrame) {
                n = this.nFrame;
            }
            if ((n5 = n * this.frameHeight) > this.frameHeight * (this.nFrame - 1) || n5 < 0) {
                n5 = this.frameHeight * (this.nFrame - 1);
            }
            mGraphics2.drawRegion(mImage2, 0, n5, this.frameWidth, this.frameHeight, n4, n2, n3, 0, true);
        }
    }

    public void drawFrame(int n, int n2, int n3, int n4, mGraphics mGraphics2) {
        if (this.imgFrame == null) {
            mImage mImage2 = ImageEffect.setImage(this.Id);
            if (mImage2 != null) {
                this.imgFrame = mImage2;
            }
            if (this.imgFrame.image != null) {
                this.nFrame = mImage.getImageHeight(this.imgFrame.image) / this.frameHeight;
            }
        } else if (n >= 0 && n < this.nFrame && this.imgFrame.image != null) {
            mGraphics2.drawRegion(this.imgFrame, 0, n * this.frameHeight, this.frameWidth, this.frameHeight, n4, n2, n3, 0, true);
        }
    }

    public void drawFrameXY(int n, int n2, int n3, int n4, mGraphics mGraphics2) {
        if (this.imgFrame == null) {
            mImage mImage2 = ImageEffect.setImage(this.Id);
            if (mImage2 != null) {
                this.imgFrame = mImage2;
            }
            if (this.imgFrame.image != null) {
                this.nFrame = mImage.getImageHeight(this.imgFrame.image) / this.frameHeight;
            }
        } else if (n >= 0 && n < this.nFrame && this.imgFrame.image != null) {
            mGraphics2.drawRegion(this.imgFrame, n * this.frameWidth, n2 * this.frameHeight, this.frameWidth, this.frameHeight, 0, n3, n4, 0, true);
        }
    }
}

