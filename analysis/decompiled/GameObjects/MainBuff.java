/*
 * Decompiled with CFR 0.152.
 */
package GameObjects;

import CLib.mGraphics;
import CLib.mSystem;
import CLib.mVector;
import GameEffect.EffectSkill;
import GameObjects.MainInfoItem;
import GameObjects.MainObject;
import GameScreen.GameScreen;
import Main.GameCanvas;
import Model.CRes;
import Model.FrameImage;
import Model.Line;
import Model.Point;

public class MainBuff {
    long timeBegin;
    boolean isPaintLast = false;
    boolean isForEver = false;
    int x0;
    int y0;
    int x1000;
    int y1000;
    int timeOff;
    int indexPaint;
    int framebegin;
    long timebuff;
    public boolean isRemove = false;
    public int typeBuff;
    public int typeSub;
    public short idIcon;
    public byte[] frame_Buff = new byte[]{0, 0, 0, 1, 1, 1, 2, 2, 2};
    public byte frBuff;
    public MainInfoItem[] minfo = null;
    public MainInfoItem[] minfotam = null;
    public static final int BUFF_KIEM_1 = 0;
    public static final int BUFF_2KIEM_1 = 1;
    public static final int BUFF_PS_1 = 2;
    public static final int BUFF_SUNG_1 = 3;
    public static final int BUFF_HEAD = 4;
    public static final int BUFF_KIEM_2 = 5;
    public static final int BUFF_2KIEM_2 = 6;
    public static final int BUFF_PS_2 = 7;
    public static final int BUFF_SUNG_2 = 8;
    public static final int BUFF_CRAZY = 9;
    public static final int BUFF_PET = 10;
    public static final byte EFF_BUFF_GOLD = 11;
    public static final byte EFF_BUFF_AMOR = 12;
    public static final byte EFF_BUFF_DAME = 13;
    public static final byte EFF_BUFF_HUTHP = 14;
    FrameImage fraImage;
    mVector vecEff = new mVector("MainBuff VecEff");

    public MainBuff(int n, int n2) {
        this.typeBuff = n;
        this.timebuff = mSystem.currentTimeMillis() + (long)(n2 * 1000);
        switch (this.typeBuff) {
            case 11: {
                this.fraImage = new FrameImage(149);
                break;
            }
            case 12: {
                this.fraImage = new FrameImage(147);
                break;
            }
            case 13: {
                this.fraImage = new FrameImage(146);
                break;
            }
            case 14: {
                this.fraImage = new FrameImage(148);
                break;
            }
        }
    }

    public void settimebuff(long l) {
        this.timebuff = mSystem.currentTimeMillis() + l * 1000L;
    }

    public MainBuff(int n, int n2, int n3) {
        this.typeBuff = n;
        this.typeSub = n3;
        this.timeBegin = GameCanvas.timeNow;
        this.timeOff = n2;
        this.x0 = 0;
        this.y0 = 0;
        this.framebegin = CRes.random(9);
        switch (n) {
            case 0: 
            case 2: 
            case 5: 
            case 7: {
                this.fraImage = new FrameImage(88);
                break;
            }
            case 1: 
            case 3: 
            case 6: 
            case 8: {
                this.fraImage = new FrameImage(89);
                break;
            }
            case 9: {
                this.isPaintLast = true;
                break;
            }
            case 4: {
                this.isPaintLast = true;
                if (n3 == 3) {
                    this.fraImage = new FrameImage(81);
                    this.framebegin = 0;
                } else {
                    this.fraImage = new FrameImage(80);
                    this.framebegin = n3;
                    if (n3 > 3) {
                        --this.framebegin;
                    }
                }
                for (int i = 0; i < 3; ++i) {
                    Point point = new Point();
                    point.x = CRes.random_Am_0(16);
                    point.y = CRes.random_Am_0(10);
                    if (n3 == 3) {
                        point.vx = CRes.random_Am_0(3);
                        point.vy = CRes.random_Am_0(2);
                    }
                    this.vecEff.addElement(point);
                }
                break;
            }
            default: {
                this.isRemove = true;
            }
        }
    }

    public void paint(mGraphics mGraphics2, int n, int n2) {
        if (this.isRemove) {
            return;
        }
        switch (this.typeBuff) {
            case 0: 
            case 1: 
            case 5: 
            case 6: 
            case 10: {
                if (this.fraImage == null) {
                    return;
                }
                this.fraImage.drawFrameEffectSkill(2 - (GameCanvas.gameTick + this.framebegin) / 3 % this.fraImage.nFrame, n + this.x0, n2 + this.y0, 0, 3, mGraphics2);
                break;
            }
            case 2: 
            case 3: 
            case 7: 
            case 8: {
                if (this.fraImage == null) {
                    return;
                }
                this.fraImage.drawFrameEffectSkill((GameCanvas.gameTick + this.framebegin) / 3 % this.fraImage.nFrame, n + this.x0, n2 + this.y0, 0, 3, mGraphics2);
                break;
            }
            case 4: {
                if (this.fraImage == null || this.vecEff == null) {
                    return;
                }
                for (int i = 0; i < this.vecEff.size(); ++i) {
                    Point point = (Point)this.vecEff.elementAt(i);
                    if (point == null) continue;
                    this.fraImage.drawFrameEffectSkill(this.framebegin * 3 + point.f % this.fraImage.nFrame, n + point.x, n2 + point.y, 0, 3, mGraphics2);
                }
                break;
            }
            case 9: {
                for (int i = this.vecEff.size() - 1; i >= 0; --i) {
                    Line line = (Line)this.vecEff.elementAt(i);
                    if (line == null) continue;
                    int n3 = 0;
                    n3 = EffectSkill.colorStar[0][line.idColor];
                    mGraphics2.setColor(n3);
                    mGraphics2.drawLine(line.x0, line.y0, line.x1, line.y1, false);
                    if (!line.is2Line) continue;
                    mGraphics2.drawLine(line.x0 + 1, line.y0, line.x1 + 1, line.y1, false);
                }
                this.x1000 = n;
                this.y1000 = n2;
                break;
            }
            case 11: 
            case 12: 
            case 13: 
            case 14: {
                if (this.fraImage == null) break;
                this.fraImage.drawFrameEffectSkill(this.frame_Buff[this.frBuff], n, n2 - 2, 0, 3, mGraphics2);
            }
        }
    }

    public void update() {
        if (!this.isRemove) {
            if (!this.isForEver) {
                if (this.typeBuff == 4) {
                    if (CRes.random(2) == 0) {
                        Point point = new Point();
                        point.x = CRes.random_Am_0(16);
                        point.y = CRes.random_Am_0(10);
                        if (this.typeSub == 3) {
                            point.vx = CRes.random_Am_0(3);
                            point.vy = CRes.random_Am_0(2);
                        }
                        this.vecEff.addElement(point);
                    }
                    for (int i = 0; i < this.vecEff.size(); ++i) {
                        Point point = (Point)this.vecEff.elementAt(i);
                        point.update();
                        if (point.f < 3) continue;
                        this.vecEff.removeElement(point);
                        --i;
                    }
                } else if (this.typeBuff == 9) {
                    if (GameCanvas.gameTick % 2 == 0) {
                        this.create_Line_NHANBAN_LV2();
                    }
                    for (int i = 0; i < this.vecEff.size(); ++i) {
                        Line line = (Line)this.vecEff.elementAt(i);
                        line.update();
                        if (line.f < line.fRe) continue;
                        this.vecEff.removeElement(line);
                        --i;
                    }
                } else if (this.typeBuff > 10 && this.typeBuff < 15) {
                    this.frBuff = (byte)(this.frBuff + 1);
                    if (this.frBuff > this.frame_Buff.length - 1) {
                        this.frBuff = 0;
                    }
                    if (this.timebuff - mSystem.currentTimeMillis() < 0L) {
                        this.isRemove = true;
                    }
                }
                if (GameCanvas.gameTick % 10 == 0 && this.typeBuff < 11 && GameCanvas.timeNow - this.timeBegin > (long)this.timeOff) {
                    this.isRemove = true;
                }
            }
            if (this.minfotam != null) {
                this.minfo = this.minfotam;
                this.minfotam = null;
            }
        }
    }

    public static void setEffHead(int n, int n2, MainObject mainObject) {
        for (int i = 0; i < mainObject.vecBuff.size(); ++i) {
            MainBuff mainBuff = (MainBuff)mainObject.vecBuff.elementAt(i);
            if (mainBuff.typeBuff != 4 || mainBuff.typeSub != n) continue;
            mainBuff.timeBegin = GameCanvas.timeNow;
            mainBuff.timeOff = n2 * 1000;
            return;
        }
        mainObject.addBuff(4, n2 * 1000, n);
    }

    public static MainBuff getBuff(int n, int n2) {
        for (int i = 0; i < GameScreen.player.vecBuff.size(); ++i) {
            MainBuff mainBuff = (MainBuff)GameScreen.player.vecBuff.elementAt(i);
            if (mainBuff.typeBuff != n || mainBuff.typeSub != n2) continue;
            return mainBuff;
        }
        return null;
    }

    public void create_Line_NHANBAN_LV2() {
        int n = CRes.random(1, 4);
        for (int i = 0; i < n; ++i) {
            Line line = new Line();
            int n2 = CRes.random(3, 12);
            int n3 = 0;
            if (n2 <= 5) {
                line.fRe = 16;
                n3 = 2;
            } else if (n2 <= 8) {
                n3 = 4;
                line.fRe = 12;
            } else {
                n3 = 5;
                line.fRe = 9;
            }
            line.is2Line = CRes.random(5) == 0;
            int n4 = this.x1000 + CRes.random_Am_0(15);
            int n5 = this.y1000 - CRes.random_Am_0(10);
            line.setLine(n4, n5, n4, n5 - n2, 0, -n3, line.is2Line);
            line.idColor = CRes.random(3);
            this.vecEff.addElement(line);
        }
    }

    public void clearData() {
        if (this.fraImage != null) {
            this.fraImage.clearData();
        }
    }
}

