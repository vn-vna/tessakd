/*
 * Decompiled with CFR 0.152.
 */
package GameEffect;

import CLib.mGraphics;
import CLib.mImage;
import CLib.mSystem;
import GameEffect.EffectSkill;
import Main.GameCanvas;
import Main.ImageEffect;
import Model.CRes;

public class EffectCharWearing {
    private byte f;
    private byte f1;
    private byte FrameEffect;
    private byte FrameEffect1;
    public int idImage;
    public byte type;
    private int frameWidth;
    private int frameHeight;
    public static final byte TYPE_HAT_WEARING = 0;
    public static final byte TYPE_ARMOR_WEARING = 1;
    public static final byte TYPE_TROUSERS_WEARING = 2;
    private long timepaint;
    private int timerepaint;
    private int dx;
    private int dy;
    public static byte[][] Frame = new byte[][]{{0, 1, 2, 3, 4, 3, 2, 1, 0, 1, 2, 3, 4, 3, 2, 1, 0, 1, 2, 3, 4, 3, 2, 1, 0, 1, 2, 3, 4}, {0, 0, 0, 1, 1, 1, 2, 2, 2, 3, 3, 3, 0, 0, 0, 1, 1, 1, 2, 2, 2, 3, 3, 3}, {0, 0, 1, 1, 2, 2, 1, 3, 3, 3, 0, 0, 1, 1, 2, 2, 1, 3, 3, 3}};
    int pospaint;

    public EffectCharWearing(byte by, int n) {
        this.idImage = n;
        this.type = by;
        this.timepaint = mSystem.currentTimeMillis();
        switch (this.type) {
            case 0: {
                this.dx = 5;
                this.dy = -25;
                this.timerepaint = 1;
                break;
            }
            case 1: {
                this.dx = 0;
                this.dy = -12;
                this.timerepaint = 2;
                break;
            }
            case 2: {
                this.dx = 5;
                this.dy = 0;
                this.timerepaint = 3;
            }
        }
    }

    public void paint(mGraphics mGraphics2, int n, int n2) {
        mImage mImage2 = ImageEffect.setImage(this.idImage);
        if (mImage2 != null && mImage2.image != null) {
            if (this.frameWidth == 0 || this.frameHeight == 0) {
                this.frameWidth = EffectSkill.arrInfoEff[this.idImage][0];
                this.frameHeight = EffectSkill.arrInfoEff[this.idImage][1];
            }
            if (this.timepaint - mSystem.currentTimeMillis() < 0L) {
                switch (this.type) {
                    case 0: {
                        if (this.pospaint == 0) {
                            mGraphics2.drawRegion(mImage2, 0, this.FrameEffect * this.frameHeight, this.frameWidth, this.frameHeight, 0, n + this.dx, n2 + this.dy, 3, false);
                            break;
                        }
                        mGraphics2.drawRegion(mImage2, 0, this.FrameEffect1 * this.frameHeight, this.frameWidth, this.frameHeight, 0, n - this.dx, n2 + this.dy, 3, false);
                        break;
                    }
                    case 1: {
                        mGraphics2.drawRegion(mImage2, 0, this.FrameEffect * this.frameHeight, this.frameWidth, this.frameHeight, 0, n + this.dx, n2 + this.dy, 3, false);
                        break;
                    }
                    case 2: {
                        mGraphics2.drawRegion(mImage2, 0, this.FrameEffect * this.frameHeight, this.frameWidth, this.frameHeight, 0, n + this.dx, n2 + this.dy, 3, false);
                        mGraphics2.drawRegion(mImage2, 0, this.FrameEffect1 * this.frameHeight, this.frameWidth, this.frameHeight, 0, n - this.dx, n2 + this.dy, 3, false);
                    }
                }
            }
        }
    }

    public void update() {
        if (this.timepaint - mSystem.currentTimeMillis() < 0L) {
            switch (this.type) {
                case 0: {
                    if (GameCanvas.gameTick % 2 == 0) {
                        this.f = (byte)(this.f + 1);
                    }
                    if (this.f > Frame[this.type].length - 1) {
                        this.f = 0;
                        this.timerepaint = CRes.random(10);
                        this.timepaint = (long)(this.timerepaint * 1000) + mSystem.currentTimeMillis();
                        this.pospaint = this.timerepaint % 2 == 0 ? 0 : 1;
                    }
                    this.FrameEffect = Frame[this.type][this.f];
                    if (GameCanvas.gameTick % 4 == 0) {
                        this.f1 = (byte)(this.f1 + 1);
                    }
                    if (this.f1 > Frame[this.type].length - 1) {
                        this.f1 = 0;
                    }
                    this.FrameEffect1 = Frame[this.type][this.f1];
                    break;
                }
                case 1: {
                    this.f = (byte)(this.f + 1);
                    if (this.f > Frame[this.type].length - 1) {
                        this.f = 0;
                        this.timerepaint = CRes.random(10);
                        this.timepaint = (long)(this.timerepaint * 1000) + mSystem.currentTimeMillis();
                    }
                    this.FrameEffect = Frame[this.type][this.f];
                    break;
                }
                case 2: {
                    if (GameCanvas.gameTick % 2 == 0) {
                        this.f = (byte)(this.f + 1);
                    }
                    if (this.f > Frame[this.type].length - 1) {
                        this.f = 0;
                        this.timerepaint = CRes.random(10);
                        this.timepaint = (long)(this.timerepaint * 1000) + mSystem.currentTimeMillis();
                    }
                    this.FrameEffect = Frame[this.type][this.f];
                    if (GameCanvas.gameTick % 3 == 0) {
                        this.f1 = (byte)(this.f1 + 1);
                    }
                    if (this.f1 > Frame[this.type].length - 1) {
                        this.f1 = 0;
                    }
                    this.FrameEffect1 = Frame[this.type][this.f1];
                }
            }
        }
    }
}

