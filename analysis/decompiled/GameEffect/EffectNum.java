/*
 * Decompiled with CFR 0.152.
 */
package GameEffect;

import CLib.mFont;
import CLib.mGraphics;
import GameEffect.MainEffect;
import InterfaceComponents.MainTabNew;
import Model.AvMain;
import Model.CRes;

public class EffectNum
extends MainEffect {
    public static final byte COLOR_NORMAL = 0;
    public static final byte COLOR_XP = 1;
    public static final byte COLOR_FIRE = 2;
    public static final byte COLOR_PLUS_HP = 3;
    public static final byte COLOR_PLUS_MP = 4;
    public static final byte COLOR_PUT_ITEM = 5;
    public static final byte COLOR_FIRE_PERSON = 6;
    public static final byte COLOR_EFF_CRI = 7;
    public static final byte COLOR_OPTION = 8;
    public static final byte COLOR_EFF_MON_FIRE = 9;
    public static final byte COLOR_DAME_LIGHT = 10;
    public static final byte COLOR_DAME_DRAK = 11;
    String strContent;
    int typeNum;
    int fRemove;
    mFont fontPaint;
    boolean isGravity = false;

    public EffectNum(String string, int n, int n2, int n3) {
        this.isGravity = false;
        this.strContent = string;
        this.x = n;
        this.y = n2 + CRes.random_Am_0(5);
        this.typeNum = n3;
        this.fontPaint = mFont.tahoma_7b_white;
        if (this.typeNum < 0) {
            this.fontPaint = MainTabNew.setTextColorName(-n3);
        } else {
            switch (n3) {
                case 1: {
                    this.fontPaint = mFont.tahoma_7_green;
                    break;
                }
                case 2: 
                case 6: 
                case 7: 
                case 9: {
                    this.isGravity = true;
                    break;
                }
                case 3: {
                    break;
                }
                case 4: {
                    break;
                }
                case 5: {
                    this.fontPaint = mFont.tahoma_7_white;
                    break;
                }
                case 8: {
                    this.fontPaint = MainTabNew.setTextColor(n3);
                    break;
                }
                case 10: {
                    this.fontPaint = mFont.tahoma_7b_yellow;
                    break;
                }
                case 11: {
                    this.fontPaint = mFont.tahoma_7b_violet;
                }
            }
        }
        if (this.isGravity) {
            this.vy = -CRes.random(11, 14);
            this.fRemove = CRes.random(20, 25);
        } else {
            this.vy = -CRes.random(2, 4);
            this.fRemove = CRes.random(24, 32);
        }
    }

    public EffectNum(String string, int n, int n2, int n3, int n4) {
        this.strContent = string;
        this.x = n;
        this.y = n2 + CRes.random_Am_0(5);
        this.typeNum = n3;
        this.fontPaint = mFont.tahoma_7b_white;
        this.vy = -CRes.random(11, 14);
        this.isGravity = true;
        this.fRemove = CRes.random(20, 25);
        if (this.typeNum < 0) {
            this.fontPaint = MainTabNew.setTextColorName(-n4);
        } else {
            switch (n3) {
                case 8: {
                    this.fontPaint = MainTabNew.setTextColor(n4);
                }
            }
        }
    }

    public void paint(mGraphics mGraphics2) {
        switch (this.typeNum) {
            case 2: {
                AvMain.Font3dWhite(mGraphics2, this.strContent, this.x, this.y, 2);
                break;
            }
            case 6: {
                AvMain.Font3dColor(mGraphics2, this.strContent, this.x, this.y, 2, (byte)4);
                break;
            }
            case 7: {
                AvMain.Font3dColor(mGraphics2, this.strContent, this.x, this.y, 2, (byte)2);
                break;
            }
            case 4: {
                AvMain.Font3dColor(mGraphics2, this.strContent, this.x, this.y, 2, (byte)1);
                break;
            }
            case 3: {
                AvMain.Font3dColor(mGraphics2, this.strContent, this.x, this.y, 2, (byte)5);
                break;
            }
            case 1: 
            case 5: {
                this.fontPaint.drawString(mGraphics2, this.strContent, this.x, this.y, 2, false);
                break;
            }
            case 9: {
                AvMain.Font3dColor(mGraphics2, this.strContent, this.x, this.y, 2, (byte)6);
                break;
            }
            default: {
                this.fontPaint.drawString(mGraphics2, this.strContent, this.x, this.y, 2, false);
            }
        }
    }

    public void update() {
        ++this.f;
        if (this.isGravity) {
            ++this.vy;
        }
        super.update();
        if (this.f >= this.fRemove) {
            this.isStop = true;
        }
    }
}

