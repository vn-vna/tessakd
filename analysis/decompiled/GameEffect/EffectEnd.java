/*
 * Decompiled with CFR 0.152.
 */
package GameEffect;

import CLib.mGraphics;
import CLib.mSystem;
import CLib.mVector;
import GameEffect.EffectAuto;
import GameEffect.MainEffect;
import GameObjects.MainObject;
import GameScreen.GameScreen;
import InterfaceComponents.TabRebuildItem;
import Main.GameCanvas;
import Model.CRes;
import Model.FrameImage;
import Model.Point;

public class EffectEnd
extends MainEffect {
    public static final byte END_NUC_DAT = 0;
    public static final byte END_EFF_WATER_BIG = 1;
    public static final byte END_FAR_NEAR = 2;
    public static final byte END_THIEN_THACH = 3;
    public static final byte END_KILL_PS_LV2 = 4;
    public static final byte END_KILL_2KIEM_LV2 = 5;
    public static final byte END_KILL_2KIEM_LV2_BEGIN = 6;
    public static final byte END_KILL_SUNG_LV1 = 7;
    public static final byte END_ROCK = 9;
    public static final byte END_KILL_PS_LV1 = 10;
    public static final byte END_DIE_MONSTER = 11;
    public static final byte END_KILL_SUNG_LV2 = 12;
    public static final byte END_KILL_SUNG_LV2_BEGIN = 13;
    public static final byte END_PHAO_HOA = 14;
    public static final byte END_PHAO_BANG = 15;
    public static final byte END_WATER_ROCK = 19;
    public static final byte END_EFF_LV_UP = 21;
    public static final byte END_EFF_2KIEM_DOC = 24;
    public static final byte END_EFF_KIEM_LV6 = 25;
    public static final byte END_KIEM_NUC_DAT_2 = 26;
    public static final byte END_ICE_BIG = 27;
    public static final byte END_ICE_UP = 28;
    public static final byte END_XUYEN_GIAP = 29;
    public static final byte END_ROCK_FIRE_BIG = 30;
    public static final byte END_REBUILD = 31;
    public static final byte END_LEVEL_UP_REBUILD = 32;
    public static final byte END_EFF_LV_UP_REBUILD = 33;
    public static final byte END_EFF_FINISH_REBUILD = 34;
    public static final byte END_EFF_REMOVE_OBJ = 35;
    public static final byte END_EFF_REMOVE_MON_PHO_BANG = 36;
    public static final byte END_REPLACE_PLUS = 37;
    public static final byte END_NHANBAN_REVEICE = 38;
    public static final byte END_HUT_HP_MON_PHO_BANG = 39;
    public static final byte END_EFF_NAM_DAT = 40;
    public static final byte END_EFF_A_DEN_B = 41;
    public static final byte END_EFF_OPEN_BOX = 42;
    public static final byte END_EFF_DETONATE = 43;
    public static final byte END_EFF_POISON_NOVA = 44;
    public static final byte END_EFF_TORNADO = 45;
    public static final byte END_EFF_CAY_DAT = 46;
    public static final byte END_EFF_NUT_DAT_BIND = 47;
    public static final byte END_EFF_Medusa_ATK = 48;
    public static final byte END_EFF_DONG_BANG = 49;
    public static final byte END_EFF_Medusa_VongTron = 50;
    public static final byte END_PET_BAT = 51;
    public static final byte END_PET_EAGLE = 52;
    public static final byte END_PET_OWL = 53;
    public static final byte END_EFF_MOVE_BOSS = 54;
    public static final byte END_MOUNT_DUST = 55;
    public static final byte END_FOOT_SNOW = 56;
    public static final byte END_ENY = 57;
    public static final byte END_MO_TO = 58;
    mVector VecEffEnd = new mVector("EffectEnd VecEffEnd");
    mVector VecSubEffEnd = new mVector("EffectEnd VecSubEffEnd");
    int x1000;
    int y1000;
    int lT_Arc;
    int gocT_Arc;
    Point pRebuild;
    public byte[] nFrame;
    int trans = 0;
    public long timeRemove;
    public int timeDeley = 2000;
    public EffectAuto effAuto;
    byte typeSub;
    int numEffReplace = 0;
    int plusGoc;

    public EffectEnd(int n, int n2, int n3) {
        this.f = -1;
        this.typeEffect = n;
        this.x = n2;
        this.y = n3;
        switch (n) {
            case 0: {
                this.fraImgEff = new FrameImage(53);
                this.fRemove = 6;
                break;
            }
            case 51: {
                this.fraImgEff = new FrameImage(140);
                this.fRemove = 6;
                break;
            }
            case 1: {
                this.fraImgEff = new FrameImage(29);
                this.fRemove = 3;
                break;
            }
            case 2: {
                this.fraImgEff = new FrameImage(18);
                this.fRemove = 4;
                break;
            }
            case 3: {
                this.fraImgEff = new FrameImage(27);
                this.fRemove = 15;
                for (int i = 0; i < 7; ++i) {
                    Point point = new Point();
                    point.x = n2 + CRes.random_Am_0(22);
                    point.y = n3 + CRes.random_Am_0(8);
                    point.dis = CRes.random(this.fraImgEff.nFrame);
                    this.VecEffEnd.addElement(point);
                }
                break;
            }
            case 4: {
                this.fraImgEff = new FrameImage(4);
                this.fRemove = 5;
                break;
            }
            case 5: {
                this.fraImgEff = new FrameImage(51);
                this.fRemove = 6;
                break;
            }
            case 6: {
                this.fraImgEff = new FrameImage(52);
                this.fRemove = 4;
                break;
            }
            case 7: {
                this.fraImgEff = new FrameImage(54);
                this.fRemove = 4;
                break;
            }
            case 9: {
                if (GameCanvas.lowGraphic) {
                    this.removeEff();
                    return;
                }
                this.fraImgEff = new FrameImage(56);
                this.fRemove = CRes.random(11, 15);
                int n4 = CRes.random(3, 7);
                for (int i = 0; i < n4; ++i) {
                    Point point = new Point();
                    point.x = n2 + CRes.random_Am_0(5);
                    point.y = n3 + CRes.random_Am_0(7);
                    point.frame = CRes.random(this.fraImgEff.nFrame);
                    point.dis = CRes.random(2);
                    point.vy = -CRes.random(6, 9);
                    point.vx = CRes.random(1, 4);
                    if (i % 2 == 0) {
                        point.vx = -point.vx;
                    }
                    this.VecEffEnd.addElement(point);
                }
                break;
            }
            case 10: {
                this.fraImgEff = new FrameImage(58);
                this.fRemove = 4;
                break;
            }
            case 11: {
                this.fraImgEff = new FrameImage(42);
                this.fRemove = 9;
                break;
            }
            case 12: {
                this.fraImgEff = new FrameImage(60);
                this.fRemove = 4;
                break;
            }
            case 13: {
                this.fraImgEff = new FrameImage(9);
                this.fRemove = 4;
                break;
            }
            case 14: 
            case 15: {
                if (GameCanvas.lowGraphic) {
                    this.removeEff();
                    return;
                }
                int n5 = 0;
                if (n == 14) {
                    this.fraImgEff = new FrameImage(1);
                    n5 = CRes.random(7, 12);
                } else {
                    this.fraImgEff = new FrameImage(5);
                    n5 = CRes.random(7, 12);
                }
                this.fRemove = 12;
                for (int i = 0; i < n5; ++i) {
                    Point point = new Point();
                    point.x = n2 + CRes.random_Am_0(4);
                    point.y = n3 + CRes.random_Am_0(5);
                    point.frame = CRes.random(this.fraImgEff.nFrame);
                    point.vy = -CRes.random(5, 9);
                    point.vx = CRes.random(0, 3);
                    if (i % 2 == 0) {
                        point.vx = -point.vx;
                    }
                    this.VecEffEnd.addElement(point);
                }
                break;
            }
            case 26: {
                this.levelPaint = -1;
                int n6 = CRes.random(2);
                this.fraImgEff = n6 == 0 ? new FrameImage(90) : new FrameImage(13);
                this.fRemove = CRes.random(74, 85);
                this.fRemove_Low(20);
                break;
            }
            case 19: {
                if (GameCanvas.lowGraphic) {
                    this.removeEff();
                    return;
                }
                this.fraImgEff = new FrameImage(28);
                this.frame = CRes.random(2) == 0 ? 0 : 2;
                this.fRemove = 2;
                break;
            }
            case 21: 
            case 33: {
                int n7;
                if (this.typeEffect == 33) {
                    this.fraImgEff = new FrameImage(2);
                    n7 = 30;
                    this.fRemove = 30;
                } else {
                    this.fraImgEff = new FrameImage(66);
                    n7 = 40;
                    this.fRemove = 12;
                }
                for (int i = 0; i < n7; ++i) {
                    Point point = new Point();
                    point.x = n2 + CRes.random_Am_0(10);
                    point.y = n3 - CRes.random_Am_0(8);
                    point.frame = CRes.random(this.fraImgEff.nFrame);
                    point.vy = -CRes.random(3, 11);
                    point.vx = this.typeEffect == 33 ? CRes.random(0, 4) : CRes.random(0, 3);
                    if (i % 2 == 0) {
                        point.vx = -point.vx;
                    }
                    this.VecEffEnd.addElement(point);
                }
                break;
            }
            case 24: {
                this.fraImgEff = new FrameImage(12);
                this.fRemove = CRes.random(18, 23);
                this.levelPaint = -1;
                break;
            }
            case 25: 
            case 28: 
            case 40: {
                if (this.typeEffect == 25) {
                    this.fraImgEff = new FrameImage(86);
                } else if (this.typeEffect == 28) {
                    this.fraImgEff = new FrameImage(96);
                } else if (this.typeEffect == 40) {
                    int n8 = GameCanvas.loadmap.getTile(n2, n3);
                    if (n8 == 2 || n8 == -1) {
                        return;
                    }
                    this.fraImgEff = new FrameImage(19);
                }
                this.levelPaint = -1;
                this.fRemove = CRes.random(74, 85);
                this.fRemove_Low(20);
                break;
            }
            case 27: 
            case 30: {
                this.fRemove = 20;
                this.levelPaint = -1;
                if (this.typeEffect == 27) {
                    this.fraImgEff = new FrameImage(92);
                } else if (this.typeEffect == 30) {
                    this.fraImgEff = new FrameImage(115);
                }
                int n9 = 0;
                while (n9 < 3) {
                    Point point = new Point();
                    point.x = n2 + CRes.random_Am_0(3);
                    point.y = n3 + CRes.random_Am_0(3);
                    point.vx = CRes.random(2) == 0 ? (n9 % 2 == 0 ? CRes.random(3) : -CRes.random(3)) : CRes.random_Am_0(3);
                    point.vy = -5;
                    point.fRe = CRes.random(6, 12);
                    point.frame = n9++;
                    this.VecEffEnd.addElement(point);
                }
                break;
            }
            case 29: {
                this.fraImgEff = new FrameImage(105);
                this.fRemove = CRes.random(11, 15);
                int n10 = CRes.random(3, 6);
                for (int i = 0; i < n10; ++i) {
                    Point point = new Point();
                    point.x = n2 + CRes.random_Am_0(5);
                    point.y = n3 + CRes.random_Am_0(7);
                    point.frame = CRes.random(this.fraImgEff.nFrame);
                    point.dis = CRes.random(2);
                    point.vy = -CRes.random(6, 9);
                    point.vx = CRes.random(1, 4);
                    if (i % 2 == 0) {
                        point.vx = -point.vx;
                    }
                    this.VecEffEnd.addElement(point);
                }
                break;
            }
            case 32: {
                this.create_Level_up();
                break;
            }
            case 34: {
                this.fraImgEff = new FrameImage(14);
                this.fRemove = 18;
                break;
            }
            case 54: {
                this.fraImgEff = new FrameImage(144);
                this.fRemove = 10;
                break;
            }
            case 35: {
                this.fraImgEff = new FrameImage(122);
                this.fRemove = 6;
                break;
            }
            case 36: {
                this.fraImgEff = new FrameImage(4);
                this.fRemove = 10;
                break;
            }
            case 38: {
                this.fraImgEff = new FrameImage(65);
                this.fraImgSubEff = new FrameImage(122);
                this.fraImgSub2Eff = new FrameImage(14);
                this.fRemove = 12;
                break;
            }
            case 39: {
                this.fRemove = 55;
                break;
            }
            case 42: {
                this.fraImgEff = new FrameImage(7);
                this.fraImgSubEff = new FrameImage(2);
                this.fRemove = 20;
                this.gocT_Arc = 0;
                for (int i = 0; i < 8; ++i) {
                    int n11 = CRes.random(4, 6);
                    Point point = new Point();
                    point.x = n2 * 1000;
                    point.y = n3 * 1000;
                    point.vx = CRes.cos(CRes.fixangle(this.gocT_Arc)) * n11;
                    point.vy = CRes.sin(CRes.fixangle(this.gocT_Arc)) * n11;
                    this.gocT_Arc += 45;
                    this.VecEffEnd.addElement(point);
                }
                break;
            }
            case 43: {
                this.fraImgEff = new FrameImage(124);
                this.fRemove = 10;
                break;
            }
            case 44: {
                this.fraImgEff = new FrameImage(70);
                this.fRemove = 4;
                break;
            }
            case 45: {
                this.fraImgEff = new FrameImage(63);
                this.fRemove = 8;
                break;
            }
            case 46: {
                this.fraImgEff = new FrameImage(86);
                this.levelPaint = -1;
                this.fRemove = 6;
                break;
            }
            case 48: {
                this.fraImgEff = new FrameImage(134);
                this.fRemove = 8;
            }
        }
    }

    public EffectEnd(int n, int n2, int n3, int n4, byte by) {
        this.f = -1;
        this.typeEffect = n;
        this.x = n2;
        this.y = n3;
        this.Direction = n4;
        switch (n) {
            case 55: {
                this.nFrame = new byte[]{0, 0, 0, 1, 1, 1, 2, 2, 2};
                int n5 = GameCanvas.loadmap.getTile(n2, n3);
                if (n5 == 2 || n5 == -1) {
                    return;
                }
                this.fraImgEff = new FrameImage(145);
                this.levelPaint = by;
                break;
            }
            case 56: {
                this.fraImgEff = new FrameImage(152);
                this.levelPaint = by;
                this.fRemove = CRes.random(20, 30);
                this.fRemove_Low(20);
                break;
            }
            case 58: {
                this.fraImgEff = new FrameImage(164);
                this.levelPaint = by;
                this.fRemove = CRes.random(20, 30);
                this.fRemove_Low(20);
            }
        }
    }

    public EffectEnd(int n, int n2, int n3, int n4) {
        this.f = -1;
        this.typeEffect = n;
        this.x = n3;
        this.y = n4;
        this.fraImgEff = new FrameImage(n2);
        this.fRemove = this.fraImgEff.getNFrame();
    }

    public EffectEnd(int n, int n2, int n3, long l) {
        this.f = -1;
        this.typeEffect = n;
        this.x = n2;
        this.y = n3;
        switch (n) {
            case 47: {
                this.timeRemove = l + (long)this.timeDeley;
                this.fraImgEff = new FrameImage(86);
                this.levelPaint = -1;
                this.fRemove = CRes.random(74, 85);
                this.fRemove_Low(20);
                break;
            }
            case 49: {
                this.timeRemove = l;
                this.effAuto = new EffectAuto(51, n2, n3, 0, 0, 0, 0);
                this.levelPaint = -1;
                this.fRemove = 6;
                break;
            }
            case 50: {
                this.timeRemove = l;
                this.fraImgEff = new FrameImage(137);
                this.levelPaint = -1;
                this.fRemove = 12;
            }
        }
    }

    public EffectEnd(int n, int n2, int n3, byte by) {
        this.f = -1;
        this.typeEffect = n;
        this.typeSub = by;
        this.x = n2;
        this.y = n3;
    }

    public EffectEnd(int n, int n2, int n3, int n4, int n5, int n6) {
        this.typeEffect = n;
        this.x = n2;
        this.y = n3;
        this.toX = n4;
        this.toY = n5;
        switch (n) {
            case 31: {
                this.create_Arc_Big_Small();
                break;
            }
            case 37: {
                this.fraImgEff = new FrameImage(7);
                this.fraImgSubEff = new FrameImage(2);
                this.fRemove = 100;
                this.lT_Arc = 40;
                this.gocT_Arc = 0;
                this.numEffReplace = n6;
                this.plusGoc = 360 / n6;
                break;
            }
            case 41: {
                this.fraImgEff = new FrameImage(7);
                this.fraImgSubEff = new FrameImage(2);
                this.fRemove = 18;
            }
        }
    }

    public void paint(mGraphics mGraphics2) {
        try {
            switch (this.typeEffect) {
                case 0: 
                case 2: 
                case 5: 
                case 11: 
                case 35: 
                case 36: 
                case 51: 
                case 54: 
                case 57: {
                    if (this.fraImgEff == null) break;
                    this.fraImgEff.drawFrameEffectSkill(this.f / 2 % this.fraImgEff.nFrame, this.x, this.y, 0, 3, mGraphics2);
                    break;
                }
                case 34: {
                    if (this.fraImgEff == null) break;
                    this.fraImgEff.drawFrameEffectSkill(this.f / 3 % this.fraImgEff.nFrame, this.x, this.y, 0, 3, mGraphics2);
                    break;
                }
                case 3: {
                    for (int i = 0; i < this.VecEffEnd.size(); ++i) {
                        Point point = (Point)this.VecEffEnd.elementAt(i);
                        if (this.fraImgEff == null) continue;
                        this.fraImgEff.drawFrameEffectSkill((point.f + this.f) / 2 % this.fraImgEff.nFrame, point.x, point.y, 0, 3, mGraphics2);
                    }
                    break;
                }
                case 1: 
                case 4: 
                case 6: 
                case 7: 
                case 10: 
                case 12: 
                case 13: 
                case 44: 
                case 45: {
                    if (this.fraImgEff == null) break;
                    this.fraImgEff.drawFrameEffectSkill(this.f % this.fraImgEff.nFrame, this.x, this.y, 0, 3, mGraphics2);
                    break;
                }
                case 9: 
                case 27: 
                case 29: 
                case 30: {
                    for (int i = 0; i < this.VecEffEnd.size(); ++i) {
                        Point point = (Point)this.VecEffEnd.elementAt(i);
                        if (this.fraImgEff == null) continue;
                        this.fraImgEff.drawFrameEffectSkill(point.frame, point.x, point.y, 0, 3, mGraphics2);
                    }
                    break;
                }
                case 14: 
                case 15: 
                case 21: {
                    for (int i = 0; i < this.VecEffEnd.size(); ++i) {
                        Point point = (Point)this.VecEffEnd.elementAt(i);
                        if (this.f < this.fRemove / 3 * 2) {
                            if (this.fraImgEff == null) continue;
                            this.fraImgEff.drawFrameEffectSkill(point.frame, point.x, point.y, 0, 3, mGraphics2);
                            continue;
                        }
                        if (this.fraImgEff == null) continue;
                        this.fraImgEff.drawFrameEffectSkill(point.frame == 0 ? 3 : point.frame, point.x, point.y, 0, 3, mGraphics2);
                    }
                    break;
                }
                case 33: {
                    for (int i = 0; i < this.VecEffEnd.size(); ++i) {
                        Point point = (Point)this.VecEffEnd.elementAt(i);
                        if (this.fraImgEff == null) continue;
                        this.fraImgEff.drawFrameEffectSkill((point.frame + point.f / 2) % this.fraImgEff.nFrame, point.x, point.y, 0, 3, mGraphics2);
                    }
                    break;
                }
                case 26: {
                    if (this.f < 2) {
                        if (this.fraImgEff == null) break;
                        this.fraImgEff.drawFrameEffectSkill(this.f, this.x, this.y, 0, 3, mGraphics2);
                        break;
                    }
                    if (this.f < this.fRemove - 4) {
                        if (this.fraImgEff == null) break;
                        this.fraImgEff.drawFrameEffectSkill(2, this.x, this.y, 0, 3, mGraphics2);
                        break;
                    }
                    if (this.f >= this.fRemove || this.fraImgEff == null) break;
                    this.fraImgEff.drawFrameEffectSkill((this.fRemove - 1 - this.f) / 2, this.x, this.y, 0, 3, mGraphics2);
                    break;
                }
                case 19: {
                    if (this.fraImgEff == null) break;
                    this.fraImgEff.drawFrameEffectSkill(this.frame + this.f % this.fraImgEff.nFrame, this.x - this.fraImgEff.frameWidth / 2, this.y - this.fraImgEff.frameHeight / 2, 0, 0, mGraphics2);
                    break;
                }
                case 24: {
                    int n = 0;
                    if (this.f > this.fRemove - 5) {
                        n = (this.f - (this.fRemove - 4)) / 2;
                    }
                    if (n >= 3 || this.fraImgEff == null) break;
                    this.fraImgEff.drawFrameEffectSkill(n, this.x, this.y, 0, 3, mGraphics2);
                    break;
                }
                case 25: 
                case 28: 
                case 40: 
                case 46: {
                    if (this.f > this.fRemove) break;
                    int n = 0;
                    if (this.f < 2) {
                        n = this.f;
                    }
                    n = this.f > this.fRemove - 5 ? (this.fRemove - 1 - this.f) / 2 : 2;
                    if (this.fraImgEff != null) {
                        this.fraImgEff.drawFrameEffectSkill(n, this.x, this.y, 0, 3, mGraphics2);
                    }
                    break;
                }
                case 31: {
                    for (int i = 0; i < this.VecEffEnd.size(); ++i) {
                        Point point = (Point)this.VecEffEnd.elementAt(i);
                        if (this.fraImgSubEff == null) continue;
                        this.fraImgSubEff.drawFrameEffectSkill(point.f / 2, point.x, point.y, 0, 3, mGraphics2);
                    }
                    if (this.lT_Arc <= 5 || this.fraImgEff == null) break;
                    this.fraImgEff.drawFrameEffectSkill(this.pRebuild.v, (this.pRebuild.x2 + this.pRebuild.vx) / 1000, (this.pRebuild.y2 + this.pRebuild.vy) / 1000, 0, 3, mGraphics2);
                    break;
                }
                case 32: {
                    if (this.fraImgSubEff == null) break;
                    this.fraImgSubEff.drawFrameEffectSkill(0, this.x, this.y - (this.f - this.fRemove / 2) * 2, 0, 3, mGraphics2);
                    break;
                }
                case 37: 
                case 41: 
                case 42: {
                    Point point;
                    int n;
                    for (n = 0; n < this.VecEffEnd.size(); ++n) {
                        point = (Point)this.VecEffEnd.elementAt(n);
                        if (this.fraImgEff == null) continue;
                        this.fraImgEff.drawFrameEffectSkill(point.f / 2 % this.fraImgEff.nFrame, point.x / 1000, point.y / 1000, 0, 3, mGraphics2);
                    }
                    for (n = 0; n < this.VecSubEffEnd.size(); ++n) {
                        point = (Point)this.VecSubEffEnd.elementAt(n);
                        if (this.fraImgSubEff == null) continue;
                        this.fraImgSubEff.drawFrameEffectSkill(point.f / 2, point.x, point.y, 0, 3, mGraphics2);
                    }
                    break;
                }
                case 38: {
                    if (this.f < 6) {
                        if (this.fraImgEff != null) {
                            this.fraImgEff.drawFrameEffectSkill(this.f, this.x, this.y, 0, 3, mGraphics2);
                        }
                        if (this.fraImgSubEff == null) break;
                        this.fraImgSubEff.drawFrameEffectSkill(2 - this.f / 2, this.x, this.y, 0, 33, mGraphics2);
                        break;
                    }
                    if (this.fraImgSub2Eff == null) break;
                    this.fraImgSub2Eff.drawFrameEffectSkill(this.f - 6, this.x, this.y - 20, 0, 3, mGraphics2);
                    break;
                }
                case 43: {
                    if (this.fraImgEff == null) break;
                    this.fraImgEff.drawFrameEffectSkill(this.f / 2 % this.fraImgEff.nFrame, this.x, this.y, 0, 3, mGraphics2);
                    break;
                }
                case 47: {
                    if (this.timeRemove - mSystem.currentTimeMillis() < 0L) break;
                    long l = (this.timeRemove - mSystem.currentTimeMillis()) / 1000L;
                    int n = 0;
                    n = l >= 2L ? 2 : (l >= 1L ? 1 : 0);
                    if (this.fraImgEff != null) {
                        this.fraImgEff.drawFrameEffectSkill(n, this.x, this.y, 0, 3, mGraphics2);
                    }
                    break;
                }
                case 49: {
                    if (this.effAuto == null) break;
                    this.effAuto.paint(mGraphics2);
                    break;
                }
                case 48: 
                case 50: {
                    if (this.fraImgEff == null) break;
                    this.fraImgEff.drawFrameEffectSkill(this.f / 2 % this.fraImgEff.nFrame, this.x, this.y, 0, 3, mGraphics2);
                    break;
                }
                case 58: {
                    if (this.f > this.fRemove) break;
                    int n = 0;
                    if (this.f < 2) {
                        n = this.f;
                    }
                    n = this.f > this.fRemove - 5 ? (this.fRemove - 1 - this.f) / 2 : 2;
                    if (this.fraImgEff != null) {
                        this.fraImgEff.drawFrameEffectSkill(0, this.x, this.y, this.trans, 3, mGraphics2);
                    }
                    break;
                }
                case 56: {
                    if (this.f > this.fRemove) break;
                    int n = 0;
                    if (this.f < 2) {
                        n = this.f;
                    }
                    n = this.f > this.fRemove - 5 ? (this.fRemove - 1 - this.f) / 2 : 2;
                    if (this.fraImgEff != null) {
                        this.fraImgEff.drawFrameEffectSkill(n, this.x, this.y, this.trans, 3, mGraphics2);
                    }
                    break;
                }
                case 55: {
                    try {
                        if (this.fraImgEff != null) {
                            this.fraImgEff.drawFrameEffectSkill(this.nFrame[this.f], this.x, this.y, this.trans, 3, mGraphics2);
                        }
                    }
                    catch (Exception exception) {
                        mSystem.println("err mountdust f:" + this.nFrame.length + "  " + this.f);
                    }
                    break;
                }
            }
        }
        catch (Exception exception) {
            mSystem.println("/ " + this.typeEffect + exception.toString());
        }
    }

    public void update() {
        ++this.f;
        ++this.subf;
        switch (this.typeEffect) {
            case 0: 
            case 1: 
            case 2: 
            case 4: 
            case 5: 
            case 6: 
            case 7: 
            case 10: 
            case 11: 
            case 12: 
            case 13: 
            case 19: 
            case 24: 
            case 25: 
            case 26: 
            case 28: 
            case 34: 
            case 35: 
            case 40: 
            case 44: 
            case 46: 
            case 51: 
            case 54: 
            case 57: {
                if (this.f < this.fRemove) break;
                this.removeEff();
                break;
            }
            case 58: {
                if (this.Direction == 2) {
                    this.trans = 4;
                } else if (this.Direction == 3) {
                    this.trans = 7;
                }
                if (this.Direction == 1) {
                    this.trans = 0;
                }
                if (this.Direction == 0) {
                    this.trans = 3;
                }
                if (this.f < this.fRemove) break;
                this.removeEff();
                break;
            }
            case 56: {
                if (this.Direction == 2) {
                    this.trans = 4;
                } else if (this.Direction == 3) {
                    this.trans = 7;
                }
                if (this.Direction == 1) {
                    this.trans = 0;
                }
                if (this.Direction == 0) {
                    this.trans = 3;
                }
                if (this.f < this.fRemove) break;
                this.removeEff();
                break;
            }
            case 3: {
                for (int i = 0; i < this.VecEffEnd.size(); ++i) {
                    Point point = (Point)this.VecEffEnd.elementAt(i);
                    if (CRes.random(3) != 0) continue;
                    point.x = this.x + CRes.random_Am_0(22);
                    point.y = this.y + CRes.random_Am_0(8);
                    point.f = CRes.random(this.fraImgEff.nFrame);
                }
                if (this.f < this.fRemove) break;
                this.removeEff();
                break;
            }
            case 9: 
            case 14: 
            case 15: 
            case 29: {
                for (int i = 0; i < this.VecEffEnd.size(); ++i) {
                    Point point = (Point)this.VecEffEnd.elementAt(i);
                    point.update();
                    ++point.vy;
                    if (this.f != this.fRemove || !GameScreen.isWater(point.x, point.y)) continue;
                    GameScreen.addEffectEndKill(19, point.x, point.y);
                }
                if (this.f < this.fRemove) break;
                this.removeEff();
                break;
            }
            case 21: {
                for (int i = 0; i < this.VecEffEnd.size(); ++i) {
                    Point point = (Point)this.VecEffEnd.elementAt(i);
                    point.update();
                    if (point.vy < 0) {
                        ++point.vy;
                        continue;
                    }
                    point.vy = 0;
                    point.vx = 0;
                }
                if (this.f < this.fRemove) break;
                this.removeEff();
                break;
            }
            case 27: 
            case 30: {
                for (int i = 0; i < this.VecEffEnd.size(); ++i) {
                    Point point = (Point)this.VecEffEnd.elementAt(i);
                    ++point.f;
                    if (point.f < point.fRe) {
                        point.x += point.vx;
                        point.y += point.vy;
                    }
                    ++point.vy;
                }
                if (this.f <= this.fRemove) break;
                this.removeEff();
                break;
            }
            case 31: {
                for (int i = 0; i < this.VecEffEnd.size(); ++i) {
                    Point point = (Point)this.VecEffEnd.elementAt(i);
                    ++point.f;
                    if (point.f / 2 < 4) continue;
                    this.VecEffEnd.removeElement(point);
                    --i;
                }
                this.pRebuild.v = this.f / 2 % 2;
                if (this.f <= 4) break;
                if (this.lT_Arc > 5) {
                    this.pRebuild.f += 14;
                    this.pRebuild.vy = CRes.sin(CRes.fixangle(this.pRebuild.f % 360)) * this.lT_Arc;
                    this.pRebuild.vx = CRes.cos(CRes.fixangle(this.pRebuild.f % 360)) * this.lT_Arc;
                    if (this.f % 2 == 0) {
                        --this.lT_Arc;
                        this.pRebuild.f += 14;
                    }
                    Point point = new Point();
                    point.x = (this.pRebuild.x2 + this.pRebuild.vx) / 1000 + CRes.random_Am(-1, 2);
                    point.y = (this.pRebuild.y2 + this.pRebuild.vy) / 1000 + CRes.random_Am(-1, 2);
                    this.VecEffEnd.addElement(point);
                    break;
                }
                if (this.VecEffEnd.size() != 0) break;
                this.removeEff();
                break;
            }
            case 32: {
                if (this.f >= this.fRemove) {
                    this.removeEff();
                }
                if (this.f >= this.fRemove / 2) break;
                for (int i = 0; i < this.VecEffEnd.size(); ++i) {
                    Point point = (Point)this.VecEffEnd.elementAt(i);
                    if (CRes.random(3) != 0) continue;
                    if (point.fRe == 1) {
                        point.fRe = 0;
                        continue;
                    }
                    point.fRe = 1;
                    point.frame = CRes.random(4);
                }
                break;
            }
            case 33: {
                for (int i = 0; i < this.VecEffEnd.size(); ++i) {
                    Point point = (Point)this.VecEffEnd.elementAt(i);
                    point.update();
                    ++point.vy;
                }
                if (this.f < this.fRemove) break;
                this.removeEff();
                break;
            }
            case 36: {
                if (this.f == 1) {
                    GameScreen.addEffectEndKill(14, this.x, this.y);
                }
                if (this.f < this.fRemove) break;
                this.removeEff();
                break;
            }
            case 37: {
                int n;
                Point point;
                if (this.f % 3 == 0 && this.VecEffEnd.size() < this.numEffReplace && this.f < 50) {
                    Point point2 = new Point();
                    point2.x = this.x * 1000;
                    point2.y = this.y * 1000;
                    point2.vx = CRes.cos(CRes.fixangle(this.gocT_Arc)) * 4;
                    point2.vy = CRes.sin(CRes.fixangle(this.gocT_Arc)) * 4;
                    point2.x2 = this.toX * 1000;
                    point2.y2 = this.toY * 1000;
                    this.gocT_Arc += this.plusGoc;
                    this.VecEffEnd.addElement(point2);
                    if (this.VecEffEnd.size() == this.numEffReplace) {
                        this.f = 50;
                    }
                }
                if (this.f == 80) {
                    for (int i = 0; i < this.VecEffEnd.size(); ++i) {
                        point = (Point)this.VecEffEnd.elementAt(i);
                        point.vx = (point.x2 - point.x) / 8;
                        point.vy = (point.y2 - point.y) / 8;
                        point.f = 100;
                    }
                }
                for (n = 0; n < this.VecEffEnd.size(); ++n) {
                    point = (Point)this.VecEffEnd.elementAt(n);
                    point.update();
                    if (point.f % 3 == 0) {
                        Point point3 = new Point();
                        point3.x = point.x / 1000 + CRes.random_Am(5, 12);
                        point3.y = point.y / 1000 + CRes.random_Am(5, 12);
                        this.VecSubEffEnd.addElement(point3);
                    }
                    if (point.f == 10) {
                        point.vx = 0;
                        point.vy = 0;
                    }
                    if (point.f <= 108) continue;
                    this.VecEffEnd.removeElement(point);
                    --n;
                }
                for (n = 0; n < this.VecSubEffEnd.size(); ++n) {
                    point = (Point)this.VecSubEffEnd.elementAt(n);
                    ++point.f;
                    if (point.f / 2 < 4) continue;
                    this.VecSubEffEnd.removeElement(point);
                    --n;
                }
                if (this.f <= 80 || this.VecEffEnd.size() != 0) break;
                TabRebuildItem.addEffectEnd_ReBuild_ss(32, this.toX, this.toY - 11);
                TabRebuildItem.addEffectEnd_ReBuild_ss(33, this.toX, this.toY);
                TabRebuildItem.addEffectEnd_ReBuild_ss(34, this.toX, this.toY);
                this.removeEff();
                break;
            }
            case 38: {
                if (this.f < this.fRemove) break;
                this.removeEff();
                break;
            }
            case 39: {
                if (CRes.random(2) == 0) {
                    GameScreen.addEffectKill(83, this.x, this.y, 400, (short)-1, (byte)0);
                }
                if (this.f < this.fRemove) break;
                this.removeEff();
                break;
            }
            case 41: {
                Point point;
                int n;
                if (this.f == 1) {
                    Point point4 = new Point();
                    point4.x = this.x * 1000;
                    point4.y = this.y * 1000;
                    point4.x2 = this.toX * 1000;
                    point4.y2 = this.toY * 1000;
                    point4.vx = 0;
                    point4.vy = 0;
                    this.VecEffEnd.addElement(point4);
                }
                for (n = 0; n < this.VecEffEnd.size(); ++n) {
                    point = (Point)this.VecEffEnd.elementAt(n);
                    point.update();
                    if (point.f == 4) {
                        point.vx = (point.x2 - point.x) / 12;
                        point.vy = (point.y2 - point.y) / 12;
                    }
                    if (point.f % 3 == 0) {
                        Point point5 = new Point();
                        point5.x = point.x / 1000 + CRes.random_Am(5, 12);
                        point5.y = point.y / 1000 + CRes.random_Am(5, 12);
                        this.VecSubEffEnd.addElement(point5);
                    }
                    if (point.f != 16) continue;
                    point.vx = 0;
                    point.vy = 0;
                    this.VecEffEnd.removeElement(point);
                    --n;
                }
                for (n = 0; n < this.VecSubEffEnd.size(); ++n) {
                    point = (Point)this.VecSubEffEnd.elementAt(n);
                    ++point.f;
                    if (point.f / 2 < 4) continue;
                    this.VecSubEffEnd.removeElement(point);
                    --n;
                }
                if (this.f < this.fRemove || this.VecSubEffEnd.size() != 0 || this.VecEffEnd.size() != 0) break;
                this.removeEff();
                break;
            }
            case 42: {
                Point point;
                int n;
                for (n = 0; n < this.VecEffEnd.size(); ++n) {
                    point = (Point)this.VecEffEnd.elementAt(n);
                    point.update();
                    if (point.f % 3 == 0) {
                        Point point6 = new Point();
                        point6.x = point.x / 1000 + CRes.random_Am(5, 12);
                        point6.y = point.y / 1000 + CRes.random_Am(5, 12);
                        this.VecSubEffEnd.addElement(point6);
                    }
                    if (point.f != this.fRemove) continue;
                    point.vx = 0;
                    point.vy = 0;
                    this.VecEffEnd.removeElement(point);
                    --n;
                }
                for (n = 0; n < this.VecSubEffEnd.size(); ++n) {
                    point = (Point)this.VecSubEffEnd.elementAt(n);
                    ++point.f;
                    if (point.f / 2 < 4) continue;
                    this.VecSubEffEnd.removeElement(point);
                    --n;
                }
                if (this.f < this.fRemove || this.VecEffEnd.size() != 0 || this.VecSubEffEnd.size() != 0) break;
                this.removeEff();
                break;
            }
            case 43: {
                if (this.f == 1) {
                    GameScreen.addEffectEndKill(14, this.x, this.y);
                }
                if (this.f < this.fRemove) break;
                this.removeEff();
                break;
            }
            case 45: {
                if (this.f == 1) {
                    GameScreen.addEffectEndKill(9, this.x, this.y + 20);
                    GameScreen.addEffectEndKill(46, this.x, this.y + 20);
                }
                if (this.f < this.fRemove) break;
                this.removeEff();
                break;
            }
            case 49: {
                if (this.effAuto != null) {
                    this.effAuto.update();
                }
                if (this.timeRemove - mSystem.currentTimeMillis() >= 0L) break;
                this.removeEff();
                GameScreen.addEffectEndKill(15, this.x, this.y);
                break;
            }
            case 47: 
            case 50: {
                if (this.timeRemove - mSystem.currentTimeMillis() >= 0L) break;
                this.removeEff();
                break;
            }
            case 48: {
                if (this.f < this.fRemove) break;
                this.removeEff();
                break;
            }
            case 55: {
                if (this.f < 0) {
                    this.f = 0;
                }
                if (this.Direction == 2) {
                    this.trans = 0;
                } else if (this.Direction == 3) {
                    this.trans = 2;
                }
                if (this.Direction == 1) {
                    this.trans = 3;
                }
                if (this.Direction == 0) {
                    this.trans = 0;
                }
                if (this.f <= this.nFrame.length - 1) break;
                this.f = 0;
                this.removeEff();
            }
        }
    }

    public void removeEff() {
        this.isStop = true;
        this.isRemove = true;
    }

    private void create_Arc_Big_Small() {
        this.fraImgEff = new FrameImage(7);
        this.fraImgSubEff = new FrameImage(2);
        this.x1000 = this.x * 1000;
        this.y1000 = this.y * 1000;
        this.fRemove = 15;
        this.lT_Arc = MainObject.getDistance(this.toX, this.toY, this.x, this.y);
        this.gocT_Arc = CRes.angle(this.x - this.toX, this.y - this.toY);
        this.pRebuild = new Point(this.toX * 1000, this.toY * 1000);
        this.pRebuild.x2 = this.toX * 1000;
        this.pRebuild.y2 = this.toY * 1000;
        this.pRebuild.f = this.gocT_Arc;
        this.pRebuild.vy = CRes.sin(CRes.fixangle(this.pRebuild.f % 360)) * this.lT_Arc;
        this.pRebuild.vx = CRes.cos(CRes.fixangle(this.pRebuild.f % 360)) * this.lT_Arc;
        this.pRebuild.v = 0;
    }

    private void create_Level_up() {
        this.fraImgSubEff = new FrameImage(67);
        this.fRemove = 23;
    }

    public void fRemove_Low(int n) {
        if (GameCanvas.lowGraphic) {
            this.fRemove = CRes.random(n - 4, n + 5);
        }
    }
}

