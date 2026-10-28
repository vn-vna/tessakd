/*
 * Decompiled with CFR 0.152.
 */
package GameEffect;

import CLib.mGraphics;
import CLib.mImage;
import CLib.mSound;
import CLib.mSystem;
import CLib.mVector;
import GameEffect.MainEffect;
import GameObjects.MainMonster;
import GameObjects.MainObject;
import GameObjects.Object_Effect_Skill;
import GameScreen.GameScreen;
import GameScreen.LoadMapScreen;
import GameScreen.MainScreen;
import Main.GameCanvas;
import Main.ImageEffect;
import Model.AvMain;
import Model.CRes;
import Model.FrameImage;
import Model.Line;
import Model.Point;
import Model.Point_Focus;
import Model.T;
import Thread_More.LoadMap;
import netcommand.global.GlobalService;

public class EffectSkill
extends MainEffect {
    public static final byte EFF_NORMAL = 0;
    public static final byte EFF_SPEED = 1;
    public static final byte EFF_STAR = 6;
    public static final byte EFF_LIGHTING = 10;
    public static final byte EFF_STONE_DROP_MORE = 11;
    public static final byte EFF_CRACK_EARTH = 12;
    public static final byte EFF_ARROW_RAIN = 20;
    public static final byte EFF_KIEM_AND_2KIEM_LV1 = 21;
    public static final byte EFF_SUNG_LV2 = 22;
    public static final byte EFF_SUNG_LV3 = 23;
    public static final byte EFF_PS_LV2 = 25;
    public static final byte EFF_KIEM_LV3 = 26;
    public static final byte EFF_2KIEM_LV2 = 27;
    public static final byte EFF_KIEM_LV2 = 28;
    public static final byte EFF_STAR_LINE_IN = 29;
    public static final byte EFF_STAR_POINT_IN = 30;
    public static final byte EFF_SUNG_LV4 = 31;
    public static final byte EFF_2KIEM_LV4 = 34;
    public static final byte EFF_PS_AND_SUNG_LV1 = 38;
    public static final byte EFF_2KIEM_LV5 = 40;
    public static final byte EFF_XP = 41;
    public static final byte EFF_LEVEL_UP = 42;
    public static final byte EFF_LINE_LEVEL_UP = 43;
    public static final byte EFF_2KIEM_TRUNG_DOC = 46;
    public static final byte EFF_2KIEM_GAI_DOC = 47;
    public static final byte EFF_PS_XUNGKICH = 49;
    public static final byte EFF_MONSTER_NEAR = 50;
    public static final byte EFF_SUNG_LASER = 51;
    public static final byte EFF_KIEM_FIRE = 52;
    public static final byte EFF_KIEM_LV6 = 53;
    public static final byte EFF_KIEM_LV7 = 54;
    public static final byte EFF_2KIEM_CRI = 55;
    public static final byte EFF_BUFF = 56;
    public static final byte EFF_BUFF_POINT_IN = 57;
    public static final byte EFF_TELEPORT = 58;
    public static final byte EFF_PS_CAU_NOILUC = 59;
    public static final byte EFF_PS_DONGDAT = 60;
    public static final byte EFF_PS_ICE_RAIN = 61;
    public static final byte EFF_SUNG_BAO_DAN = 62;
    public static final byte EFF_SUNG_DAY_DAN = 63;
    public static final byte EFF_PS_ICE_UP = 64;
    public static final byte EFF_SUNG_RAIN_ROCKET = 65;
    public static final byte EFF_SUNG_RAIN_LIGHTNING = 66;
    public static final byte EFF_PHAN_DAMAGE = 67;
    public static final byte EFF_FOCUS = 68;
    public static final byte EFF_SMALL_SMOKE = 69;
    public static final byte EFF_MON_BUFF = 70;
    public static final byte EFF_BOSS_CAY_1 = 71;
    public static final byte EFF_BOSS_CAY_2 = 72;
    public static final byte EFF_BOSS_ONG_1 = 73;
    public static final byte EFF_BOSS_ONG_2 = 74;
    public static final byte EFF_BOSS_CUA_1 = 75;
    public static final byte EFF_BOSS_CUA_2 = 76;
    public static final byte EFF_BOSS_SOI_1 = 77;
    public static final byte EFF_BOSS_SOI_2 = 14;
    public static final byte EFF_BOSS_DA_1 = 78;
    public static final byte EFF_BOSS_DA_2 = 79;
    public static final byte EFF_KILL_GHOST = 80;
    public static final byte EFF_PHO_BANG = 81;
    public static final byte EFF_SUNG_SET_NEW = 82;
    public static final byte EFF_HUT_MP_HP = 83;
    public static final byte EFF_STAR_LINE_IN_SLOW = 84;
    public static final byte EFF_NHANBAN_LV2 = 85;
    public static final byte EFF_SERVER_THIEN_THACH = 86;
    public static final byte EFF_BUFF_MO_BANGHOI = 87;
    public static final byte EFF_DAN_FOCUS = 88;
    public static final byte EFF_HUT_MP = 89;
    public static final byte EFF_ICE_NOVA = 90;
    public static final byte EFF_POISON_NOVA = 91;
    public static final byte EFF_HUT_HP = 92;
    public static final byte EFF_BOSS_DE_1 = 93;
    public static final byte EFF_BOSS_DE_2 = 94;
    public static final byte EFF_Tower_1 = 95;
    public static final byte EFF_Tower_2 = 96;
    public static final byte EFF_Tower_3 = 97;
    public static final byte EFF_Tower_4 = 98;
    public static final byte EFF_BOSS_Medusa1 = 99;
    public static final byte EFF_BOSS_Medusa2 = 100;
    public static final byte EFF_PET_OWL = 101;
    public static final byte EFF_PET_EAGLE = 102;
    public static final byte EFF_BOSS_84_NO = 103;
    public static final byte EFF_BOSS_34_DAM_TUNG = 104;
    public static final byte EFF_BOSS_34_LASER = 105;
    public static final byte EFF_BOSS_34_LASERLAN = 106;
    public static final byte EFF_BOSS_NO = 107;
    public static final byte EFF_BOSS_84_MUNTI = 108;
    public static final byte EFF_PHAPSU_BANG = 109;
    public static final byte EFF_KIEM_LON_NGU = 110;
    public static final byte EFF_SUNG_NGU = 111;
    public static final byte EFF_KIEM_NHO_TROI = 112;
    public static final byte EFF_BOSS_NOEL_1 = 113;
    public static final byte EFF_BOSS_NOEL_2 = 114;
    public static final byte EFF_BOSS_NOEL_3 = 115;
    public static final int EFF_BIG_SWORD_115 = 116;
    public static final int EFF_SMALL_SWORD_115 = 117;
    public static final int EFF_SUNG_115 = 118;
    public static final int EFF_PHAPSU_115 = 119;
    public static final int EFF_BIG_SWORD_115_2 = 120;
    public static final int EFF_SMALL_SWORD_115_2 = 121;
    public static final int EFF_SUNG_115_2 = 122;
    public static final int EFF_PHAPSU_115_2 = 123;
    public static final int EFF_PHAO_THANH = 124;
    public static final int EFF_TRU_THANH_1 = 125;
    public static final int EFF_TRU_THANH_2 = 126;
    public static final byte SUB_STAR_LINE_IN_KIEM = 0;
    public static final byte SUB_STAR_LINE_IN_SUNG = 1;
    public static final byte SUB_STAR_LINE_IN_BUFF_0 = 2;
    public static final byte SUB_STAR_LINE_IN_2KIEM = 3;
    public static final byte SUB_STAR_LINE_IN_PS = 4;
    public static final byte SUB_BUFF_LUA_1 = 0;
    public static final byte SUB_BUFF_DOC_1 = 1;
    public static final byte SUB_BUFF_BANG_1 = 2;
    public static final byte SUB_BUFF_DIEN_1 = 3;
    public static final byte SUB_BUFF_OTHER_MAIN = 10;
    public static final byte SUB_BUFF_OTHER_SUB = 11;
    public static byte[][] frameBuff = new byte[][]{{0, 0, 0, 1, 1, 1, 2, 2, 2}, {0, 0, 0, 1, 1, 1, 2, 2, 2}, {0, 0, 0, 1, 1, 1, 2, 2, 2}, {0, 0, 0, 1, 1, 1, 2, 2, 2}, {0, 0, 0, 1, 1, 1, 2, 2, 2}};
    public static byte[][] arrInfoEff = new byte[][]{new byte[0], {5, 5, 4}, {9, 9, 4}, {20, 20, 4}, {32, 32, 5}, {7, 7, 4}, {8, 8, 4}, {25, 25, 2}, {22, 30, 4}, {14, 15, 4}, {8, 8, 4}, {24, 19, 3}, {19, 13, 3}, {24, 24, 3}, {32, 32, 6}, {36, 30, 3}, {23, 32, 8}, {20, 20, 4}, {32, 32, 2}, {41, 17, 3}, {32, 14, 3}, {48, 48, 3}, {21, 34, 3}, {12, 12, 5}, {38, 38, 3}, new byte[0], {36, 30, 3}, {32, 31, 3}, {12, 12, 4}, {40, 31, 3}, {20, 18, 3}, {12, 13, 4}, {14, 14, 4}, {30, 37, 3}, new byte[0], new byte[0], {37, 40, 4}, {50, 24, 4}, {9, 12, 3}, new byte[0], {23, 33, 2}, {32, 32, 3}, {24, 24, 5}, new byte[0], new byte[0], {14, 11, 3}, new byte[0], {15, 15, 3}, {14, 11, 3}, new byte[0], {24, 35, 3}, {70, 70, 3}, {50, 46, 4}, {62, 64, 3}, {38, 38, 4}, new byte[0], {10, 10, 2}, {25, 25, 3}, {32, 32, 4}, {32, 32, 4}, {30, 32, 4}, {50, 50, 4}, new byte[0], {46, 50, 4}, new byte[0], {42, 27, 6}, {5, 5, 4}, {59, 17, 1}, {17, 23, 4}, new byte[0], {50, 50, 4}, {31, 31, 2}, {34, 38, 3}, new byte[0], {21, 17, 9}, {32, 22, 3}, new byte[0], new byte[0], new byte[0], new byte[0], {10, 10, 18}, {17, 10, 3}, new byte[0], {27, 32, 3}, new byte[0], {19, 45, 1}, {50, 26, 3}, {24, 30, 5}, {32, 14, 3}, {32, 14, 3}, {24, 14, 3}, {8, 5, 2}, {16, 16, 3}, new byte[0], {30, 14, 2}, {25, 45, 1}, {53, 28, 3}, {12, 12, 3}, {14, 14, 4}, {14, 14, 4}, {14, 14, 4}, {14, 14, 4}, {14, 14, 4}, {12, 12, 5}, {12, 12, 5}, {12, 11, 4}, {50, 25, 4}, {50, 25, 4}, {50, 25, 4}, {50, 25, 4}, {50, 25, 4}, {32, 30, 6}, {28, 15, 4}, {88, 60, 3}, {31, 31, 2}, {16, 16, 3}, {7, 7, 4}, {19, 45, 1}, {55, 55, 3}, {55, 55, 3}, {55, 55, 3}, {55, 55, 3}, {24, 40, 3}, {13, 13, 3}, {59, 64, 4}, {25, 25, 3}, {13, 13, 3}, {36, 30, 4}, {53, 24, 4}, {44, 44, 4}, {50, 51, 4}, {40, 40, 4}, {31, 23, 3}, {26, 24, 3}, {32, 32, 4}, {32, 24, 3}, {29, 20, 3}, {32, 32, 6}, {22, 19, 8}, {23, 25, 3}, {16, 21, 3}, {8, 8, 4}, {20, 15, 2}, {30, 20, 3}, {64, 57, 4}, {20, 20, 3}, {24, 16, 3}, {30, 20, 3}, {22, 16, 3}, {25, 17, 3}, {30, 30, 4}, {30, 30, 4}, {16, 16, 2}, {7, 7, 4}, {9, 9, 4}, {13, 13, 5}, {7, 7, 4}, {9, 9, 4}, {13, 13, 5}, {7, 7, 4}, {9, 9, 4}, {13, 13, 5}, {16, 16, 4}, {13, 22, 1}};
    long time;
    public int subType;
    public int indexSound;
    public boolean ispaintArena = true;
    int vXTam;
    int vYTam;
    int x1000;
    int y1000;
    int vX1000;
    int vY1000;
    int xEff;
    int yEff;
    int angle;
    int R;
    int size1;
    int[][] mTamgiac;
    int lT_Arc;
    int gocT_Arc;
    int r;
    public static byte countSkillArena;
    public static int[][] colorStar;
    int[] colorpaint;
    static int[][] colorBuffMain;
    int colorBuff;
    int indexColorStar;
    int timedelay;
    int ysai;
    byte timeAddNum;
    mVector VecEff = new mVector("EffectSkill VecEff");
    mVector VecSubEff = new mVector("EffectSkill VecSubEff");
    byte[] mpaintone_three = new byte[]{4, 3, 2, 1, 0, 7, 6, 5};
    byte[] mpaintone_Bullet = new byte[]{12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0, 23, 22, 21, 20, 19, 18, 17, 16, 15, 14, 13};
    byte[] mImageBullet = new byte[]{0, 0, 2, 1, 1, 2, 0, 0, 2, 1, 1, 2, 0, 0, 2, 1, 1, 2, 0, 0, 2, 1, 1, 2};
    byte[] mXoayBullet = new byte[]{2, 2, 3, 3, 3, 4, 5, 5, 5, 5, 5, 1, 0, 0, 0, 0, 0, 7, 6, 6, 6, 6, 6, 2};
    int gocArrow = 0;
    int frameArrow = 0;
    int xline;
    int yline;
    int yFly;
    int ydArchor;
    int yArchor;
    int indexLan;
    MainObject objKill;
    public mVector vecObjBeKill;
    boolean isEff = false;
    boolean ispaintsleep;
    int indexAdd = 0;
    int dxTower;
    int dyTower;
    int xdichTower;
    int ydichTower;
    int delay;
    public byte[] arr_R = new byte[]{40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40};
    public int[] arr_radian = new int[]{0, 30, 60, 90, 120, 150, 180, 210, 240, 270, 300, 330};
    public int xto;
    public int yto;
    public int dx;
    public int dy;
    public int nPosition;
    public int xO = 0;
    public int yO = 0;
    public int dem;
    public int radian;
    public int frame;
    public int effect;
    public static int[][] posx;
    public static int[][] posy;
    private byte indexpost;
    public int Xsleep;
    public int vxSleep;
    public int Ysleep;
    public int ystun;
    int test = 0;
    public int frSleep = 0;
    public int[] frameSleep = new int[]{0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1};
    public byte frStun = 0;
    public int[] frameStun = new int[]{0, 0, 0, 1, 1, 1, 2, 2, 2};
    mVector vectam;
    int life = 0;
    int va;
    public boolean isSpeedUp = false;
    int savex;
    int savey;

    public EffectSkill(int n, int n2, int n3, int n4, byte by) {
        GameScreen.addEffectEndKill(14, n, n2);
        GameScreen.addEffectEndKill(14, n + 24, n2);
        GameScreen.addEffectEndKill(14, n - 24, n2);
        GameScreen.addEffectEndKill(57, n, n2);
        this.fraImgEff = new FrameImage(162);
        this.indexpost = by;
        this.x = n;
        this.y = n2;
        this.xto = n3;
        this.yto = n4;
        this.vx = this.xto == n ? 0 : (this.xto > n ? 1 : -1);
        this.vy = -5;
        this.typeEffect = 124;
    }

    public EffectSkill(int n, Object_Effect_Skill object_Effect_Skill, mVector mVector2, int n2) {
        if (!LoadMapScreen.isNextMap) {
            return;
        }
        this.subType = n2;
        this.isStop = false;
        this.isRemove = false;
        this.set_New_Effect(n, object_Effect_Skill, mVector2);
    }

    public EffectSkill(int n, Object_Effect_Skill object_Effect_Skill, mVector mVector2, int n2, boolean bl) {
        if (!LoadMapScreen.isNextMap) {
            return;
        }
        this.subType = n2;
        this.ispaintArena = bl;
        this.isStop = false;
        this.isRemove = false;
        this.set_New_Effect(n, object_Effect_Skill, mVector2);
    }

    private void set_New_Effect(int n, Object_Effect_Skill object_Effect_Skill, mVector mVector2) {
        int n2;
        if (mVector2 == null || mVector2.size() == 0) {
            return;
        }
        this.vecObjBeKill = mVector2;
        this.indexSound = -1;
        this.f = -1;
        this.isPaint = true;
        this.ysai = 0;
        this.typeEffect = n;
        this.time = GameCanvas.timeNow;
        this.objKill = MainObject.get_Object(object_Effect_Skill.ID, object_Effect_Skill.tem);
        Object_Effect_Skill object_Effect_Skill2 = (Object_Effect_Skill)this.vecObjBeKill.elementAt(0);
        this.objBeKillMain = MainObject.get_Object(object_Effect_Skill2.ID, object_Effect_Skill2.tem);
        this.isEff = false;
        if (this.objKill == null || this.objBeKillMain == null) {
            return;
        }
        if (this.typeEffect == 92 || this.typeEffect == 89) {
            this.x = this.objBeKillMain.x;
            this.y = this.objBeKillMain.y - this.objBeKillMain.hOne / 2;
            this.toX = this.objKill.x;
            this.toY = this.objKill.y - this.objKill.hOne / 2;
        } else {
            this.x = this.objKill.x;
            this.y = this.objKill.y - this.objKill.hOne / 2;
            this.toX = this.objBeKillMain.x;
            this.toY = this.objBeKillMain.y - this.objBeKillMain.hOne / 2;
        }
        if (this.typeEffect != 10 && this.typeEffect != 66 && this.typeEffect != 30 && this.typeEffect != 29 && this.typeEffect != 107) {
            this.objKill.Direction = this.Direction = EffectSkill.setDirection(this.objKill, this.objBeKillMain);
        } else {
            this.Direction = this.objKill.Direction;
        }
        this.timeAddNum = (byte)-1;
        if (this.typeEffect != 41 && this.objKill == GameScreen.player) {
            this.isEff = true;
        }
        if (!MainObject.isInScreen(this.objBeKillMain) && !MainObject.isInScreen(this.objKill)) {
            this.isStop = true;
            return;
        }
        switch (n) {
            case 0: {
                this.fRemove = 60;
                switch (this.subType) {
                    case 0: {
                        this.fraImgEff = new FrameImage(101);
                        break;
                    }
                    case 1: {
                        this.fraImgEff = new FrameImage(98);
                        break;
                    }
                    case 2: {
                        this.fraImgEff = new FrameImage(100);
                        break;
                    }
                    case 3: {
                        this.fraImgEff = new FrameImage(99);
                        break;
                    }
                    case 4: {
                        this.fraImgEff = new FrameImage(32);
                        break;
                    }
                    case 5: {
                        this.fraImgEff = new FrameImage(102);
                        break;
                    }
                    default: {
                        this.fraImgEff = new FrameImage(32);
                    }
                }
                this.vMax = 8000;
                this.createNormal();
                break;
            }
            case 41: {
                this.fRemove = 60;
                this.fraImgEff = this.subType == 0 ? new FrameImage(103) : new FrameImage(104);
                this.vMax = 12000;
                this.createXP();
                break;
            }
            case 1: 
            case 38: 
            case 59: {
                this.vMax = 14;
                if (n == 38) {
                    this.indexSound = this.objKill.clazz == 2 ? 2 : 1;
                }
                if (n == 1) {
                    this.fraImgEff = new FrameImage(32);
                }
                if (n == 59) {
                    this.indexSound = 13;
                    this.y += 10;
                    this.fraImgEff = new FrameImage(17);
                    this.fraImgSubEff = new FrameImage(24);
                }
                int n3 = this.toX - this.x;
                int n4 = this.toY - this.y;
                this.create_Speed(n3, n4, null);
                if (n != 59) break;
                this.timeAddNum = (byte)this.fRemove;
                break;
            }
            case 6: {
                this.isEff = true;
                this.create_Star();
                break;
            }
            case 10: {
                if (this.objBeKillMain != null) {
                    this.toX = this.objBeKillMain.x;
                    this.toY = this.objBeKillMain.y;
                }
                this.fRemove = 10;
                this.timeAddNum = (byte)7;
                this.createLighting(this.toX + CRes.random_Am_0(20), MainScreen.cameraMain.yCam - 5, this.toX, this.toY, true);
                if (this.objKill == GameScreen.player) {
                    mSound.playSound(32, mSound.volumeSound);
                } else {
                    GameScreen.addSoundEff(32);
                }
                GameScreen.addEffectEndKill(40, this.toX, this.toY + 10);
                break;
            }
            case 11: 
            case 108: 
            case 114: {
                this.indexSound = 5;
                if (this.typeEffect == 114) {
                    this.fraImgEff = new FrameImage(24);
                    this.fraImgSubEff = new FrameImage(7);
                    this.fraImgSub2Eff = new FrameImage(9);
                } else {
                    this.fraImgEff = new FrameImage(30);
                    this.fraImgSubEff = new FrameImage(31);
                    this.fraImgSub2Eff = new FrameImage(40);
                }
                this.vx = this.vMax = 14;
                for (n2 = 0; n2 < this.vecObjBeKill.size(); ++n2) {
                    MainObject mainObject;
                    Object_Effect_Skill object_Effect_Skill3 = (Object_Effect_Skill)this.vecObjBeKill.elementAt(n2);
                    if (object_Effect_Skill3 == null || (mainObject = MainObject.get_Object(object_Effect_Skill3.ID, object_Effect_Skill3.tem)) == null) continue;
                    Point point = this.create_Stone_Drop(mainObject.x, mainObject.y);
                    this.VecEff.addElement(point);
                }
                break;
            }
            case 12: {
                mSound.playSound(32, mSound.volumeSound);
                this.indexSound = 15;
                this.create_Crack_Earth();
                break;
            }
            case 14: 
            case 115: {
                mSound.playSound(37, mSound.volumeSound);
                this.timeAddNum = (byte)18;
                if (this.typeEffect == 14) {
                    this.create_Fire_Arc();
                    break;
                }
                this.create_Ice_Arc();
                break;
            }
            case 20: 
            case 113: {
                this.indexSound = 11;
                this.fraImgEff = new FrameImage(91);
                this.setBeginKill(5);
                if (this.objBeKillMain != null) {
                    this.x1000 = this.objBeKillMain.x - 70;
                    this.toX = this.objBeKillMain.x;
                    this.toY = this.objBeKillMain.y;
                } else {
                    this.x1000 = this.toX - 70;
                }
                this.y1000 = MainScreen.cameraMain.yCam - CRes.random(10, 20);
                this.vMax = 18;
                this.fRemove = 20;
                this.timeAddNum = (byte)11;
                this.create_Arrow_Rain();
                break;
            }
            case 21: {
                this.indexSound = this.objKill.clazz == 0 ? 3 : 0;
                if (this.objBeKillMain != null) {
                    this.x = this.objBeKillMain.x;
                    this.y = this.objBeKillMain.y - this.objKill.hOne / 2;
                } else {
                    this.x = this.toX;
                    this.y = this.toY;
                }
                this.frame = CRes.random(4);
                do {
                    this.frameArrow = CRes.random(4);
                } while (this.frame == this.frameArrow);
                this.fraImgEff = new FrameImage(118 + this.frame);
                this.fraImgSubEff = new FrameImage(118 + this.frameArrow);
                this.fRemove = 6;
                break;
            }
            case 22: 
            case 31: {
                this.create_Sung_LV2_LV4();
                break;
            }
            case 23: {
                this.indexSound = 12;
                this.create_Sung_Lv3();
                break;
            }
            case 25: {
                this.indexSound = 13;
                this.create_PS_LV2_3_5();
                break;
            }
            case 26: {
                this.indexSound = 5;
                this.create_Kiem_Lv3();
                break;
            }
            case 27: {
                this.create_Kill_2Kiem_Lv2();
                break;
            }
            case 28: {
                this.indexSound = 4;
                this.create_Kiem_Lv2();
                break;
            }
            case 29: {
                this.isEff = true;
                this.indexColorStar = this.objKill != null ? (this.objKill.clazz == 1 ? 1 : 0) : 0;
                this.setPosLineIn(this.subType);
                this.x1000 = this.x * 1000;
                this.y1000 = this.objKill != null ? (this.y - this.objKill.hOne / 2) * 1000 : this.toY * 1000;
                this.fRemove = 2;
                this.vMax = 5;
                this.xline = 10;
                this.yline = 20;
                this.create_Star_Line_In(this.vMax, this.xline, this.yline);
                break;
            }
            case 30: {
                this.isEff = true;
                this.setPosLineIn(this.subType);
                this.x1000 = this.x * 1000;
                this.y1000 = (this.y - this.objKill.hOne / 2) * 1000;
                this.fraImgEff = this.objKill.clazz == 3 ? new FrameImage(6) : new FrameImage(10);
                this.fRemove = 5;
                this.create_Star_Point_In();
                break;
            }
            case 34: {
                this.fraImgEff = new FrameImage(33);
                this.fraImgSubEff = new FrameImage(18);
                this.fRemove = 57;
                if (this.objKill == null || this.objBeKillMain == null) {
                    this.isRemove = true;
                    return;
                }
                this.objKill.isTanHinh = true;
                this.objKill.timeTanHinh = 0;
                if (this.objKill.isMainChar()) {
                    GameScreen.player.lastX = GameScreen.player.x;
                    GameScreen.player.lastY = GameScreen.player.y;
                }
                this.create_2Kiem_Lv4(0, this.objBeKillMain);
                this.indexLan = 1;
                break;
            }
            case 40: {
                this.indexSound = 10;
                this.create_2Kiem_Lv5();
                break;
            }
            case 42: {
                this.isEff = true;
                this.indexSound = 26;
                this.create_Level_up();
                break;
            }
            case 43: {
                this.isEff = true;
                this.fraImgEff = new FrameImage(50);
                this.fRemove = 11;
                this.y += this.objKill.hOne / 2;
                for (n2 = 0; n2 < 10; ++n2) {
                    Point point = new Point();
                    int n5 = CRes.random(0, 180);
                    point.x = 17 * CRes.cos(n5) / 1000;
                    point.y = 15 * CRes.sin(n5) / 1000 - 5;
                    point.fRe = CRes.random(2);
                    point.frame = CRes.random(4);
                    point.limitY = 60;
                    this.VecEff.addElement(point);
                }
                break;
            }
            case 46: {
                this.indexSound = 7;
                this.fraImgEff = new FrameImage(70);
                this.fraImgSubEff = new FrameImage(72);
                this.fRemove = 15;
                if (this.objBeKillMain == null) break;
                this.toX = this.objBeKillMain.x;
                this.toY = this.objBeKillMain.y;
                break;
            }
            case 47: 
            case 112: {
                this.indexSound = 8;
                this.create_2Kiem_GaiDoc();
                break;
            }
            case 49: {
                this.indexSound = 14;
                this.fraImgEff = new FrameImage(23);
                this.fraImgSubEff = new FrameImage(24);
                this.create_PS_Xungkich();
                if (this.objBeKillMain == null) break;
                this.toX = this.objBeKillMain.x;
                this.toY = this.objBeKillMain.y - this.objBeKillMain.hOne / 2;
                break;
            }
            case 50: {
                this.create_Monster();
                if (this.objBeKillMain == null || this.objBeKillMain.typeObject != 9) break;
                this.indexSound = 25;
                break;
            }
            case 125: {
                this.y = this.objKill.y - 100;
                this.timeAddNum = (byte)3;
                this.fraImgEff = new FrameImage(21);
                this.fraImgSubEff = new FrameImage(59);
                this.fRemove = 16;
                this.R = 240;
                this.angle = 0;
                this.size1 = 4;
                break;
            }
            case 126: {
                this.y = this.objKill.y - 120;
                this.x -= 12;
                this.timeAddNum = (byte)3;
                this.fraImgEff = new FrameImage(21);
                this.fraImgSubEff = new FrameImage(59);
                this.fRemove = 16;
                this.R = 240;
                this.angle = 0;
                this.size1 = 4;
                break;
            }
            case 106: {
                this.timeAddNum = (byte)3;
                this.fraImgEff = new FrameImage(21);
                this.fraImgSubEff = new FrameImage(59);
                this.fRemove = 16;
                this.R = 240;
                this.y -= 15;
                this.x -= 6;
                this.angle = 0;
                this.size1 = 4;
                this.setBeginKill(10);
                break;
            }
            case 105: {
                this.timeAddNum = (byte)3;
                this.fraImgEff = new FrameImage(21);
                this.fraImgSubEff = new FrameImage(60);
                this.fRemove = 16;
                this.y -= 12;
                this.setBeginKill(10);
                break;
            }
            case 51: {
                this.timeAddNum = (byte)3;
                this.fraImgEff = new FrameImage(21);
                this.fraImgSubEff = new FrameImage(59);
                this.fRemove = 16;
                this.y += 7;
                this.setBeginKill(10);
                break;
            }
            case 52: 
            case 54: {
                if (n == 52) {
                    this.indexSound = 4;
                } else if (n == 54) {
                    this.indexSound = 5;
                }
                this.fraImgEff = new FrameImage(83);
                this.fraImgSubEff = new FrameImage(68);
                this.fRemove = 15;
                if (this.objBeKillMain == null) break;
                this.toX = this.objBeKillMain.x;
                this.toY = this.objBeKillMain.y;
                break;
            }
            case 53: 
            case 78: 
            case 104: {
                if (n == 53) {
                    this.indexSound = 6;
                    this.fraImgEff = new FrameImage(85);
                } else if (n == 78 || n == 104) {
                    mSound.playSound(32, mSound.volumeSound);
                    this.fraImgEff = new FrameImage(117);
                    if (n == 104) {
                        this.objBeKillMain.jum();
                    }
                }
                this.fraImgSubEff = new FrameImage(53);
                this.fRemove = 20;
                for (n2 = 0; n2 < this.vecObjBeKill.size(); ++n2) {
                    MainObject mainObject;
                    Point point = new Point();
                    Object_Effect_Skill object_Effect_Skill4 = (Object_Effect_Skill)this.vecObjBeKill.elementAt(n2);
                    if (object_Effect_Skill4 == null || (mainObject = MainObject.get_Item_Object(object_Effect_Skill4.ID, object_Effect_Skill4.tem)) == null) continue;
                    point.x = mainObject.x;
                    point.y = mainObject.y + mainObject.ysai;
                    this.VecEff.addElement(point);
                }
                break;
            }
            case 55: {
                this.indexSound = 7;
                this.fraImgEff = new FrameImage(51);
                this.fRemove = 12;
                if (this.objBeKillMain != null) {
                    this.toX = this.objBeKillMain.x;
                    this.toY = this.objBeKillMain.y - this.objBeKillMain.hOne / 2;
                }
                this.timeAddNum = (byte)5;
                break;
            }
            case 56: {
                this.fraImgEff = this.subType == 10 ? new FrameImage(29) : new FrameImage(22);
                this.y = this.objKill.y;
                this.fRemove = 6;
                this.isEff = true;
                break;
            }
            case 57: {
                this.indexSound = this.objKill.clazz == 2 || this.objKill.clazz == 3 ? 30 : 31;
                this.isEff = true;
                this.x1000 = this.x * 1000;
                this.y1000 = this.y * 1000;
                this.create_Buff_Point_In();
                break;
            }
            case 58: {
                mSound.playSound(45, mSound.volumeSound);
                this.isEff = true;
                this.y = this.objKill.y + this.objKill.ysai;
                this.fraImgEff = new FrameImage(65);
                this.fraImgSubEff = new FrameImage(94);
                this.fRemove = 8;
                break;
            }
            case 60: {
                this.indexSound = 16;
                LoadMap.timeVibrateScreen = 16;
                for (n2 = 0; n2 < this.vecObjBeKill.size(); ++n2) {
                    MainObject mainObject;
                    Object_Effect_Skill object_Effect_Skill5 = (Object_Effect_Skill)this.vecObjBeKill.elementAt(n2);
                    if (object_Effect_Skill5 == null || (mainObject = MainObject.get_Object(object_Effect_Skill5.ID, object_Effect_Skill5.tem)) == null) continue;
                    Point point = new Point();
                    point.x = mainObject.x;
                    point.y = mainObject.y + mainObject.ysai;
                    this.VecEff.addElement(point);
                }
                this.fRemove = 20;
                break;
            }
            case 61: 
            case 77: {
                if (n == 61) {
                    this.indexSound = this.vecObjBeKill.size() > 1 ? 16 : 15;
                    this.fraImgEff = new FrameImage(71);
                    this.fraImgSubEff = new FrameImage(5);
                    this.fraImgSub2Eff = new FrameImage(92);
                } else if (n == 77) {
                    this.fraImgEff = new FrameImage(114);
                    this.fraImgSubEff = new FrameImage(116);
                    this.fraImgSub2Eff = new FrameImage(115);
                }
                this.vx = this.vMax = 22;
                for (n2 = 0; n2 < this.vecObjBeKill.size(); ++n2) {
                    MainObject mainObject;
                    Object_Effect_Skill object_Effect_Skill6 = (Object_Effect_Skill)this.vecObjBeKill.elementAt(n2);
                    if (object_Effect_Skill6 == null || (mainObject = MainObject.get_Object(object_Effect_Skill6.ID, object_Effect_Skill6.tem)) == null) continue;
                    Point point = this.create_ICE_Drop(mainObject.x, mainObject.y);
                    point.dis = CRes.random(2);
                    point.vecEffPoint = new mVector("EffectSkill vecEffPoint");
                    int n6 = CRes.random(3, 7);
                    for (int i = 0; i < n6; ++i) {
                        Point point2 = new Point();
                        point2.x = point.x + CRes.random_Am_0(20);
                        point2.y = point.y - 10 - CRes.random(35);
                        point2.dis = 0;
                        if (CRes.random(6) == 0) {
                            point2.dis = 1;
                        } else {
                            point2.frame = CRes.random(4);
                        }
                        point.vecEffPoint.addElement(point2);
                    }
                    this.VecEff.addElement(point);
                }
                break;
            }
            case 62: {
                this.indexSound = 20;
                this.y += 5;
                this.fRemove = 20;
                this.timeAddNum = (byte)10;
                this.fraImgEff = new FrameImage(47);
                this.vMax = 22;
                if (this.objBeKillMain == null) break;
                this.toX = this.objBeKillMain.x;
                this.toY = this.objBeKillMain.y - this.objBeKillMain.hOne / 2;
                break;
            }
            case 63: {
                this.y += 8;
                this.fraImgEff = new FrameImage(74);
                if (this.objBeKillMain != null) {
                    this.toX = this.objBeKillMain.x;
                    this.toY = this.objBeKillMain.y - this.objBeKillMain.hOne / 2;
                }
                this.fRemove = 20;
                this.timeAddNum = (byte)10;
                this.vMax = 16;
                break;
            }
            case 109: 
            case 110: {
                this.indexSound = 16;
                this.indexSound = 14;
                this.f = 0;
                this.fraImgEff = this.typeEffect == 109 ? new FrameImage(92) : new FrameImage(115);
                n2 = (short)(this.toX - this.x);
                short s = (short)(this.toY - 0 - this.y);
                this.angle = (short)CRes.angle(n2, s);
                int n7 = (Math.abs(n2) + Math.abs(s)) / 20;
                if (n7 < 2) {
                    n7 = 2;
                }
                for (int i = 0; i < n7; ++i) {
                    Point point = new Point();
                    point.x = this.x + i * n2 / n7;
                    point.y = this.y + i * s / n7;
                    this.VecEff.addElement(point);
                }
                break;
            }
            case 64: {
                this.indexSound = 14;
                this.f = -1;
                this.fraImgEff = new FrameImage(95);
                this.fraImgSubEff = new FrameImage(53);
                this.fRemove = 20;
                for (int i = 0; i < this.vecObjBeKill.size(); ++i) {
                    MainObject mainObject;
                    Point point = new Point();
                    Object_Effect_Skill object_Effect_Skill7 = (Object_Effect_Skill)this.vecObjBeKill.elementAt(i);
                    if (object_Effect_Skill7 == null || (mainObject = MainObject.get_Item_Object(object_Effect_Skill7.ID, object_Effect_Skill7.tem)) == null) continue;
                    point.x = mainObject.x;
                    point.y = mainObject.y + mainObject.ysai;
                    this.VecEff.addElement(point);
                }
                break;
            }
            case 73: {
                this.y += 8;
                if (n == 73) {
                    mSound.playSound(34, mSound.volumeSound);
                    this.fraImgEff = new FrameImage(48);
                    this.fraImgSubEff = new FrameImage(38);
                }
                this.vMax = 20;
                this.fRemove = 40;
                break;
            }
            case 65: {
                this.indexSound = 23;
                this.fRemove = 2;
                this.isEff = true;
                break;
            }
            case 66: {
                this.indexSound = 22;
                this.objKill.Direction = 1;
                this.fRemove = 2;
                this.isEff = true;
                break;
            }
            case 67: {
                int n4;
                int n3;
                this.isEff = false;
                this.fraImgEff = new FrameImage(21);
                this.fraImgSubEff = new FrameImage(6);
                this.x1000 = this.x;
                this.y1000 = this.y;
                this.vMax = 18;
                if (this.objBeKillMain != null) {
                    n3 = this.objBeKillMain.x - this.x;
                    n4 = this.objBeKillMain.y - this.objBeKillMain.hOne / 2 - this.y;
                } else {
                    n3 = this.toX - this.x;
                    n4 = this.toY - this.y;
                }
                this.create_Speed(n3, n4, null);
                break;
            }
            case 68: {
                this.x = this.objBeKillMain.x;
                this.y = this.subType == 0 ? this.objBeKillMain.y - this.objBeKillMain.hOne / 2 : this.objBeKillMain.y - this.objBeKillMain.hOne - 8;
                this.timeAddNum = (byte)20;
                this.isEff = true;
                if (this.subType == 0) {
                    this.fraImgEff = new FrameImage(111);
                    this.fRemove = 6;
                    break;
                }
                if (this.subType != 1) break;
                this.fraImgEff = new FrameImage(112);
                this.fRemove = 8;
                break;
            }
            case 69: {
                this.fRemove = 16;
                this.isEff = true;
                this.fraImgEff = new FrameImage(9);
                this.vx = CRes.random_Am_0(2);
                this.vy = -2;
                this.y -= CRes.random(this.objKill.hOne / 2);
                break;
            }
            case 70: {
                this.isEff = true;
                this.x1000 = this.x * 1000;
                this.y1000 = this.y * 1000;
                if (this.subType >= 28) {
                    this.subType -= 28;
                }
                this.create_Mon_Buff();
                break;
            }
            case 71: 
            case 75: {
                if (n == 71) {
                    mSound.playSound(32, mSound.volumeSound);
                    this.fraImgEff = new FrameImage(36);
                    this.fraImgSubEff = new FrameImage(53);
                } else {
                    mSound.playSound(35, mSound.volumeSound);
                    this.fraImgEff = new FrameImage(61);
                }
                this.fRemove = 16;
                for (int i = 0; i < this.vecObjBeKill.size(); ++i) {
                    MainObject mainObject;
                    Point point = new Point();
                    Object_Effect_Skill object_Effect_Skill8 = (Object_Effect_Skill)this.vecObjBeKill.elementAt(i);
                    if (object_Effect_Skill8 == null || (mainObject = MainObject.get_Item_Object(object_Effect_Skill8.ID, object_Effect_Skill8.tem)) == null) continue;
                    point.x = mainObject.x;
                    point.y = mainObject.y + mainObject.ysai;
                    this.VecEff.addElement(point);
                }
                break;
            }
            case 72: 
            case 74: {
                int n4;
                int n3;
                this.vMax = 14;
                if (n == 72) {
                    mSound.playSound(34, mSound.volumeSound);
                    this.fraImgEff = new FrameImage(45);
                    this.fraImgSubEff = new FrameImage(37);
                } else if (n == 74) {
                    mSound.playSound(34, mSound.volumeSound);
                    this.fraImgEff = new FrameImage(63);
                }
                for (int i = 0; i < this.vecObjBeKill.size(); ++i) {
                    MainObject mainObject;
                    Object_Effect_Skill object_Effect_Skill9 = (Object_Effect_Skill)this.vecObjBeKill.elementAt(i);
                    if (object_Effect_Skill9 == null || (mainObject = MainObject.get_Item_Object(object_Effect_Skill9.ID, object_Effect_Skill9.tem)) == null) continue;
                    n3 = mainObject.x - this.x;
                    n4 = mainObject.y - mainObject.hOne / 2 - this.y;
                    Point_Focus point_Focus = new Point_Focus();
                    point_Focus = this.create_Speed_More(n3, n4, point_Focus, mainObject);
                    point_Focus.x = this.x;
                    point_Focus.y = this.y;
                    int n8 = CRes.angle(n3, n4);
                    point_Focus.frame = this.setFrameAngle(n8);
                    this.VecEff.addElement(point_Focus);
                }
                this.fRemove = 10;
                break;
            }
            case 76: {
                mSound.playSound(36, mSound.volumeSound);
                this.levelPaint = -1;
                this.fraImgSubEff = new FrameImage(113);
                this.fraImgEff = new FrameImage(81);
                this.fRemove = 20;
                this.timeAddNum = (byte)13;
                this.vMax = 5;
                for (int i = 0; i < 16; ++i) {
                    Point point = new Point();
                    point.x = this.x * 1000;
                    point.y = this.y * 1000;
                    point.vx = 2 * CRes.cos(225 * i / 10) * this.vMax;
                    point.vy = CRes.sin(225 * i / 10) * this.vMax;
                    point.f = 0;
                    this.VecEff.addElement(point);
                }
                break;
            }
            case 79: {
                this.fRemove = 1;
                this.isEff = true;
                break;
            }
            case 82: 
            case 111: {
                this.indexSound = 21;
                int n9 = CRes.angle(this.toX - this.x, this.toY - this.y);
                int n10 = CRes.abs(MainObject.getDistance(this.x, this.y, this.toX, this.toY)) + 30;
                this.x1000 = this.x + CRes.cos(n9) * n10 / 1000;
                this.y1000 = this.y + CRes.sin(n9) * n10 / 1000;
                this.fRemove = 10;
                this.timeAddNum = (byte)7;
                this.setBeginKill(0);
                this.createLighting(this.x, this.y - this.hOne / 2, this.x1000, this.y1000, true);
                if (this.objKill == GameScreen.player) {
                    mSound.playSound(32, mSound.volumeSound);
                }
                GameScreen.addEffectEndKill(40, this.x1000, this.y1000 + 10);
                break;
            }
            case 88: {
                this.fRemove = 60;
                this.fraImgEff = new FrameImage(97);
                this.fraImgSubEff = new FrameImage(9);
                this.vMax = 8000;
                this.createDanFocus();
                this.frame = this.setFrameAngle(this.gocT_Arc);
                break;
            }
            case 89: {
                this.fRemove = 60;
                this.fraImgEff = new FrameImage(10);
                this.fraImgSubEff = new FrameImage(10);
                this.vMax = 8000;
                this.createDanFocus();
                this.frame = this.setFrameAngle(this.gocT_Arc);
                break;
            }
            case 92: {
                this.fRemove = 60;
                this.fraImgEff = new FrameImage(141);
                this.fraImgSubEff = new FrameImage(141);
                this.vMax = 8000;
                this.createDanFocus();
                this.frame = this.setFrameAngle(this.gocT_Arc);
                break;
            }
            case 90: {
                this.timeAddNum = (byte)18;
                this.create_Nova();
                if (this.objBeKillMain == null || this.objBeKillMain.typeObject != 9) break;
                this.indexSound = 36;
                break;
            }
            case 91: {
                this.timeAddNum = (byte)18;
                this.create_Poison_Nova();
                if (this.objBeKillMain == null || this.objBeKillMain.typeObject != 9) break;
                this.indexSound = 36;
                break;
            }
            case 93: {
                mSound.playSound(34, mSound.volumeSound);
                this.fraImgEff = new FrameImage(63);
                this.fraImgSubEff = new FrameImage(107);
                this.fRemove = 60;
                for (int i = 0; i < this.vecObjBeKill.size(); ++i) {
                    MainObject mainObject;
                    Point point = new Point();
                    Object_Effect_Skill object_Effect_Skill10 = (Object_Effect_Skill)this.vecObjBeKill.elementAt(i);
                    if (object_Effect_Skill10 == null || (mainObject = MainObject.get_Item_Object(object_Effect_Skill10.ID, object_Effect_Skill10.tem)) == null) continue;
                    point.x = this.x;
                    point.y = this.y + this.ysai;
                    this.VecEff.addElement(point);
                }
                break;
            }
            case 95: {
                this.indexSound = 21;
                int n11 = CRes.angle(this.toX - this.x, this.toY - this.y);
                int n12 = CRes.abs(MainObject.getDistance(this.x, this.y, this.toX, this.toY));
                this.x1000 = this.x + CRes.cos(n11) * n12 / 1000;
                this.y1000 = this.y + CRes.sin(n11) * n12 / 1000;
                this.fRemove = 10;
                this.timeAddNum = (byte)7;
                this.setBeginKill(0);
                this.dxTower = 0;
                this.dyTower = 50;
                this.createLighting(this.x - this.dxTower, this.y - this.hOne / 2 - this.dyTower, this.x1000, this.y1000, true);
                if (this.objKill == GameScreen.player) {
                    mSound.playSound(32, mSound.volumeSound);
                }
                GameScreen.addEffectEndKill(40, this.x1000, this.y1000 + 20);
                break;
            }
            case 96: {
                this.fRemove = 60;
                this.fraImgEff = new FrameImage(132);
                this.fraImgSubEff = new FrameImage(9);
                this.vMax = 8000;
                this.createDanFocus();
                this.frame = this.setFrameAngle(this.gocT_Arc);
                mSound.playSound(57, mSound.volumeSound);
                this.dxTower = 4;
                this.dyTower = -50;
                this.x += this.dxTower;
                this.y += this.dyTower;
                break;
            }
            case 97: {
                this.timeAddNum = (byte)3;
                this.fraImgEff = new FrameImage(21);
                this.fraImgSubEff = new FrameImage(59);
                this.fRemove = 16;
                this.dxTower = 0;
                this.dyTower = -86;
                this.x += this.dxTower;
                this.y += this.dyTower;
                break;
            }
            case 98: {
                this.create_FireBall_Tower();
                break;
            }
            case 99: {
                if (this.objKill.Direction == 3) {
                    this.dxTower = -10;
                    this.dyTower = -20;
                } else {
                    this.dxTower = 10;
                    this.dyTower = -20;
                }
                this.create_Sung_Medusa();
                GameScreen.addEffectEndKill(48, this.objKill.x + this.dxTower, this.objKill.y + this.dyTower);
            }
        }
        if (!this.isEff && this.objKill != null && this.objKill.typeObject != 1) {
            for (n2 = 0; n2 < this.vecObjBeKill.size(); ++n2) {
                Object_Effect_Skill object_Effect_Skill11 = (Object_Effect_Skill)this.vecObjBeKill.elementAt(n2);
                if (object_Effect_Skill11 == null) {
                    this.vecObjBeKill.removeElement(object_Effect_Skill11);
                    --n2;
                    continue;
                }
                MainObject mainObject = MainObject.get_Object(object_Effect_Skill11.ID, object_Effect_Skill11.tem);
                if (mainObject == null) {
                    this.vecObjBeKill.removeElement(object_Effect_Skill11);
                    --n2;
                } else if (mainObject.Action != 4) {
                    mainObject.hp = object_Effect_Skill11.hpLast;
                    if (mainObject.hp <= 0 && mainObject != GameScreen.player) {
                        mainObject.resetAction();
                        mainObject.Action = 4;
                        mainObject.timedie = GameCanvas.timeNow;
                        GameScreen.addEffectEndKill(11, mainObject.x, mainObject.y);
                    }
                }
                if (this.vecObjBeKill.size() != 0) continue;
                this.isStop = true;
            }
        }
        if (this.indexSound >= 0) {
            if (this.objKill == GameScreen.player) {
                mSound.playSound(this.indexSound, mSound.volumeSound);
            } else {
                GameScreen.addSoundEff(this.indexSound);
            }
        }
    }

    public EffectSkill(int n, int n2, int n3, int n4, short s, byte n5) {
        this.typeEffect = n;
        this.x = n2;
        this.y = n3;
        this.timeRemove = n4;
        this.time = GameCanvas.timeNow;
        switch (n) {
            case 29: {
                this.isEff = true;
                this.indexColorStar = CRes.random(2);
                this.x1000 = n2 * 1000;
                this.y1000 = n3 * 1000;
                this.fRemove = CRes.random(4, 6);
                this.vMax = 5;
                this.xline = 10;
                this.yline = 20;
                this.create_Star_Line_In(this.vMax, this.xline, this.yline);
                break;
            }
            case 69: {
                this.fRemove = 16;
                this.isEff = true;
                this.fraImgEff = new FrameImage(9);
                this.vx = CRes.random_Am_0(2);
                this.vy = -2;
                break;
            }
            case 80: {
                this.fRemove = 16;
                this.isEff = true;
                this.fraImgEff = new FrameImage(16);
                this.objBeKillMain = MainObject.get_Object(s, (byte)1);
                if (this.objBeKillMain != null) break;
                return;
            }
            case 81: {
                this.fRemove = 30;
                this.isEff = true;
                this.create_Fire_Arc();
                break;
            }
            case 83: {
                this.isEff = true;
                this.fRemove = 60;
                int n6 = CRes.random(3);
                if (n6 == 0) {
                    this.fraImgEff = new FrameImage(103);
                } else if (n6 == 1) {
                    this.fraImgEff = new FrameImage(104);
                } else if (n6 == 2) {
                    this.fraImgEff = new FrameImage(23);
                }
                this.toX = this.x;
                this.toY = this.y;
                this.x += CRes.random_Am(24, 48);
                this.y += CRes.random_Am(24, 48);
                this.createHut_Mp_Hp();
                break;
            }
            case 84: {
                this.isEff = true;
                this.indexColorStar = CRes.random(2);
                this.x1000 = n2 * 1000;
                this.y1000 = n3 * 1000;
                this.fRemove = CRes.random(6, 8);
                this.vMax = 2;
                this.xline = 10;
                this.yline = 20;
                this.create_Star_Line_In(this.vMax, this.xline, this.yline);
                break;
            }
            case 85: {
                this.timeRemove = n4 * 1000;
                this.isEff = true;
                this.objBeKillMain = MainObject.get_Object(s, (byte)n5);
                if (this.objBeKillMain == null || this.objBeKillMain.isRemove || this.objBeKillMain.isStop) {
                    this.removeEff();
                    return;
                }
                this.indexColorStar = CRes.random(2);
                this.x1000 = n2;
                this.y1000 = n3;
                this.fRemove = 5;
                break;
            }
            case 86: {
                this.fraImgEff = new FrameImage(30);
                this.fraImgSubEff = new FrameImage(31);
                this.fraImgSub2Eff = new FrameImage(40);
                this.isEff = true;
                for (int i = 0; i < n5; ++i) {
                    this.vx = this.vMax = CRes.random(5, 10);
                    Point point = this.create_Stone_Drop(MainScreen.cameraMain.xCam + 20 + CRes.random(GameCanvas.w * 5 / 4), MainScreen.cameraMain.yCam + 20 + CRes.random(GameCanvas.h * 5 / 4));
                    this.VecEff.addElement(point);
                }
                break;
            }
            case 87: {
                this.fraImgEff = new FrameImage(11);
                this.objBeKillMain = MainObject.get_Object(s, (byte)n5);
                if (this.objBeKillMain == null || this.objBeKillMain.isRemove || this.objBeKillMain.isStop) {
                    this.removeEff();
                    return;
                }
                n2 = this.objBeKillMain.x;
                n3 = this.objBeKillMain.y - this.objBeKillMain.hOne / 2;
                this.isEff = true;
                this.fRemove = 10;
                break;
            }
            case 102: {
                this.objBeKillMain = MainObject.get_Object(s, (byte)n5);
                if (this.objBeKillMain == null) {
                    return;
                }
                n2 = this.objBeKillMain.x;
                n3 = this.objBeKillMain.y - 25;
                this.objBeKillMain.vx = 0;
                this.objBeKillMain.vy = 0;
                this.objBeKillMain.toX = n2;
                this.objBeKillMain.toY = n3;
                this.ystun = this.objBeKillMain.typeObject == 1 ? 0 : 17;
                this.objBeKillMain.isStun = true;
                this.objBeKillMain.timeStun = mSystem.currentTimeMillis() + (long)(n4 * 1000);
                break;
            }
            case 103: {
                this.objBeKillMain = MainObject.get_Object(s, (byte)n5);
                if (this.objBeKillMain == null) {
                    return;
                }
                this.fraImgEff = new FrameImage(27);
                this.r = 72;
                this.timedelay = 20;
                this.ysai = 40;
                this.objBeKillMain.isnoBoss84 = true;
                this.objBeKillMain.timenoBoss84 = mSystem.currentTimeMillis() + (long)(n4 * 1000);
                break;
            }
            case 107: {
                this.objBeKillMain = MainObject.get_Object(s, (byte)n5);
                if (this.objBeKillMain == null) {
                    return;
                }
                this.isEff = true;
                this.indexColorStar = CRes.random(2);
                n2 = this.objBeKillMain.x;
                n3 = this.objBeKillMain.y - 15;
                this.x1000 = n2 * 1000;
                this.y1000 = n3 * 1000;
                this.objBeKillMain.toX = n2;
                this.objBeKillMain.toY = n3;
                this.fRemove = CRes.random(4, 6);
                this.vMax = 5;
                this.xline = 10;
                this.yline = 20;
                this.create_Star_Line_In(this.vMax, this.xline, this.yline);
                this.objBeKillMain.isno = true;
                this.objBeKillMain.timeno = mSystem.currentTimeMillis() + (long)(n4 * 1000);
                break;
            }
            case 101: {
                this.objBeKillMain = MainObject.get_Object(s, (byte)n5);
                if (this.objBeKillMain == null) {
                    return;
                }
                this.ispaintsleep = false;
                this.delay = 10;
                n2 = this.objBeKillMain.x;
                n3 = this.objBeKillMain.y;
                this.objBeKillMain.vx = 0;
                this.objBeKillMain.vy = 0;
                this.objBeKillMain.toX = n2;
                this.objBeKillMain.toY = n3;
                this.objBeKillMain.isSleep = true;
                this.objBeKillMain.timeSleep = mSystem.currentTimeMillis() + (long)(n4 * 1000);
                break;
            }
            case 94: {
                this.nFrame = new byte[]{0, 0, 0, 1, 1, 1, 2, 2, 2, 3, 3, 3};
                mSound.playSound(32, mSound.volumeSound);
                this.fraImgEff = new FrameImage(127);
                this.fraImgSubEff = new FrameImage(53);
                this.fRemove = 60;
                this.objBeKillMain = MainObject.get_Object(s, (byte)n5);
                if (this.objBeKillMain == null) {
                    return;
                }
                n2 = this.objBeKillMain.x;
                n3 = this.objBeKillMain.y;
                this.objBeKillMain.vx = 0;
                this.objBeKillMain.vy = 0;
                this.objBeKillMain.toX = n2;
                this.objBeKillMain.toY = n3;
                this.objBeKillMain.isBinded = true;
                this.objBeKillMain.timeBind = mSystem.currentTimeMillis() + (long)(n4 * 1000);
                LoadMap.timeVibrateScreen = 103;
                break;
            }
            case 100: {
                mSound.playSound(32, mSound.volumeSound);
                this.fRemove = 60;
                this.objBeKillMain = MainObject.get_Object(s, (byte)n5);
                if (this.objBeKillMain == null) {
                    return;
                }
                n2 = this.objBeKillMain.x;
                n3 = this.objBeKillMain.y;
                this.objBeKillMain.vx = 0;
                this.objBeKillMain.vy = 0;
                this.objBeKillMain.toX = n2;
                this.objBeKillMain.toY = n3;
                this.objBeKillMain.isDongBang = true;
                this.objBeKillMain.timeDongBang = mSystem.currentTimeMillis() + (long)(n4 * 1000);
                this.levelPaint = -1;
                this.fRemove = 6;
            }
        }
    }

    public EffectSkill(MainObject mainObject, int n) {
        this.typeEffect = n;
        this.x = mainObject.x;
        this.y = mainObject.y;
        switch (n) {
            case 14: {
                this.isEff = true;
                this.create_Fire_Arc();
            }
        }
    }

    public void paint(mGraphics mGraphics2) {
        if (mSystem.isj2me && !this.ispaintArena) {
            return;
        }
        try {
            this.test = 10;
            if (this.isRemove) {
                return;
            }
            switch (this.typeEffect) {
                case 124: {
                    if (this.fraImgEff == null) {
                        return;
                    }
                    this.fraImgEff.drawFrameEffectSkill(this.f / 2 % this.fraImgEff.nFrame, this.x, this.y, 0, 3, mGraphics2);
                    break;
                }
                case 0: 
                case 1: 
                case 80: {
                    if (this.fraImgEff == null) {
                        return;
                    }
                    this.fraImgEff.drawFrameEffectSkill(this.f / 2 % this.fraImgEff.nFrame, this.x, this.y, 0, 3, mGraphics2);
                    break;
                }
                case 59: {
                    if (this.fraImgEff == null) {
                        return;
                    }
                    if (this.f < this.fRemove) {
                        this.fraImgEff.drawFrameEffectSkill(this.f / 2 % this.fraImgEff.nFrame, this.x, this.y, 0, 3, mGraphics2);
                        break;
                    }
                    this.fraImgSubEff.drawFrameEffectSkill((this.f - this.fRemove) / 2 % this.fraImgSubEff.nFrame, this.toX, this.toY, 0, 3, mGraphics2);
                    break;
                }
                case 109: 
                case 110: {
                    this.fraImgEff.drawFrameEffectSkill(this.f / 2 % this.fraImgEff.nFrame, this.x, this.y, 0, 3, mGraphics2);
                    break;
                }
                case 103: {
                    for (int i = 0; i < this.arr_R.length; ++i) {
                        this.fraImgEff.drawFrameEffectSkill(this.f / 2 % this.fraImgEff.nFrame, CRes.cos(this.arr_radian[i]) * this.arr_R[i] / 1024 + this.x + 2, CRes.sin(this.arr_radian[i]) * this.arr_R[i] / 1024 + this.y - this.ysai - 20, 0, 3, mGraphics2);
                        if (this.timedelay > 0) continue;
                        mGraphics2.drawImage(MainObject.shadow, CRes.cos(this.arr_radian[i]) * this.arr_R[i] / 1024 + this.x + 2, CRes.sin(this.arr_radian[i]) * this.arr_R[i] / 1024 + this.y - 10, 3, false);
                    }
                    break;
                }
                case 6: {
                    if (GameCanvas.gameTick % 2 != 0 || this.mTamgiac == null || this.mTamgiac.length <= 0) break;
                    for (int i = 0; i < this.mTamgiac.length; ++i) {
                        mGraphics2.setColor(this.colorpaint[i / 2]);
                        mGraphics2.fillTriangle(this.x1000 / 1000, this.y1000 / 1000, this.mTamgiac[i][0] / 1000, this.mTamgiac[i][1] / 1000, this.mTamgiac[i][2] / 1000, this.mTamgiac[i][3] / 1000, false);
                    }
                    break;
                }
                case 29: 
                case 84: 
                case 107: {
                    for (int i = 0; i < this.VecEff.size(); ++i) {
                        Line line = (Line)this.VecEff.elementAt(i);
                        if (line == null) continue;
                        int n = 0;
                        if (i / 2 < this.colorpaint.length) {
                            n = this.colorpaint[i / 2];
                        }
                        mGraphics2.setColor(n);
                        mGraphics2.drawLine(line.x0 / 1000, line.y0 / 1000, line.x1 / 1000, line.y1 / 1000, false);
                        if (!line.is2Line) continue;
                        mGraphics2.drawLine(line.x0 / 1000 + 1, line.y0 / 1000, line.x1 / 1000 + 1, line.y1 / 1000, false);
                    }
                    break;
                }
                case 111: {
                    Point point;
                    Point point2;
                    int n;
                    if (this.f % 5 >= 2 && this.f % 5 != 3) break;
                    for (n = 1; n < this.VecEff.size(); ++n) {
                        point2 = (Point)this.VecEff.elementAt(n - 1);
                        point = (Point)this.VecEff.elementAt(n);
                        mGraphics2.setColor(16514254);
                        mGraphics2.drawLine(point2.x / 1000, point2.y / 1000 - 1, point.x / 1000, point.y / 1000 - 1, false);
                        mGraphics2.drawLine(point2.x / 1000, point2.y / 1000 + 1, point.x / 1000, point.y / 1000 + 1, false);
                        mGraphics2.drawLine(point2.x / 1000, point2.y / 1000 + 2, point.x / 1000, point.y / 1000 + 2, false);
                        mGraphics2.setColor(16514254);
                        mGraphics2.drawLine(point2.x / 1000, point2.y / 1000, point.x / 1000, point.y / 1000, false);
                    }
                    for (n = 1; n < this.VecSubEff.size(); ++n) {
                        point2 = (Point)this.VecSubEff.elementAt(n - 1);
                        point = (Point)this.VecSubEff.elementAt(n);
                        if (point.x == -1 || point2.x == -1) continue;
                        mGraphics2.setColor(16514254);
                        mGraphics2.drawLine(point2.x / 1000, point2.y / 1000, point.x / 1000, point.y / 1000, false);
                        mGraphics2.drawLine(point2.x / 1000, point2.y / 1000 + 1, point.x / 1000, point.y / 1000 + 1, false);
                    }
                    break;
                }
                case 10: 
                case 82: {
                    Point point;
                    Point point3;
                    int n;
                    if (this.f % 5 >= 2 && this.f % 5 != 3) break;
                    for (n = 1; n < this.VecEff.size(); ++n) {
                        point3 = (Point)this.VecEff.elementAt(n - 1);
                        point = (Point)this.VecEff.elementAt(n);
                        mGraphics2.setColor(-985352);
                        mGraphics2.drawLine(point3.x / 1000, point3.y / 1000 - 1, point.x / 1000, point.y / 1000 - 1, false);
                        mGraphics2.drawLine(point3.x / 1000, point3.y / 1000 + 1, point.x / 1000, point.y / 1000 + 1, false);
                        mGraphics2.drawLine(point3.x / 1000, point3.y / 1000 + 2, point.x / 1000, point.y / 1000 + 2, false);
                        mGraphics2.setColor(-5708810);
                        mGraphics2.drawLine(point3.x / 1000, point3.y / 1000, point.x / 1000, point.y / 1000, false);
                    }
                    for (n = 1; n < this.VecSubEff.size(); ++n) {
                        point3 = (Point)this.VecSubEff.elementAt(n - 1);
                        point = (Point)this.VecSubEff.elementAt(n);
                        if (point.x == -1 || point3.x == -1) continue;
                        mGraphics2.setColor(-985352);
                        mGraphics2.drawLine(point3.x / 1000, point3.y / 1000, point.x / 1000, point.y / 1000, false);
                        mGraphics2.drawLine(point3.x / 1000, point3.y / 1000 + 1, point.x / 1000, point.y / 1000 + 1, false);
                    }
                    break;
                }
                case 11: 
                case 86: 
                case 108: 
                case 114: {
                    Point point;
                    int n;
                    if (this.fraImgEff == null) {
                        return;
                    }
                    for (n = 0; n < this.VecSubEff.size(); ++n) {
                        point = (Point)this.VecSubEff.elementAt(n);
                        if (point.f / 2 > 3) continue;
                        this.fraImgSubEff.drawFrameEffectSkill(point.f / 2, point.x, point.y, 0, 3, mGraphics2);
                    }
                    for (n = 0; n < this.VecEff.size(); ++n) {
                        point = (Point)this.VecEff.elementAt(n);
                        if (this.f >= point.f) continue;
                        this.fraImgSub2Eff.drawFrameEffectSkill(CRes.random(2), point.x - 10, point.y + point.vy * this.f / 1000 - 13, 0, 3, mGraphics2);
                        this.fraImgEff.drawFrameEffectSkill(point.v / 2 % this.fraImgEff.nFrame, point.x, point.y + point.vy * this.f / 1000, 0, 3, mGraphics2);
                    }
                    break;
                }
                case 12: {
                    Point point;
                    Point point4;
                    int n;
                    if (this.fRemove < -1) {
                        this.fRemove = -1;
                    }
                    if (this.fRemove == 0) break;
                    int n2 = this.VecEff.size() / this.fRemove + 2;
                    int n3 = n2 * this.f;
                    if (n3 > this.VecEff.size()) {
                        n3 = this.VecEff.size();
                    }
                    for (n = 1; n < n3; ++n) {
                        point4 = (Point)this.VecEff.elementAt(n - 1);
                        point = (Point)this.VecEff.elementAt(n);
                        mGraphics2.setColor(0);
                        if (this.f <= this.fRemove - 1) {
                            mGraphics2.drawLine(point4.x / 1000 - this.xline, point4.y / 1000 - this.yline, point.x / 1000 - this.xline, point.y / 1000 - this.yline, false);
                            mGraphics2.drawLine(point4.x / 1000 + this.xline, point4.y / 1000 + this.yline, point.x / 1000 + this.xline, point.y / 1000 + this.yline, false);
                        }
                        if (this.f <= this.fRemove - 2) {
                            mGraphics2.drawLine(point4.x / 1000 + 2 * this.xline, point4.y / 1000 + 2 * this.yline, point.x / 1000 + 2 * this.xline, point.y / 1000 + 2 * this.yline, false);
                        }
                        mGraphics2.setColor(-12636649);
                        mGraphics2.drawLine(point4.x / 1000, point4.y / 1000, point.x / 1000, point.y / 1000, false);
                    }
                    if (this.f <= this.fRemove - 2) {
                        n2 = this.VecSubEff.size() / this.fRemove + 2;
                        n3 = n2 * this.f;
                        if (n3 > this.VecSubEff.size()) {
                            n3 = this.VecSubEff.size();
                        }
                        for (n = 1; n < n3; ++n) {
                            point4 = (Point)this.VecSubEff.elementAt(n - 1);
                            point = (Point)this.VecSubEff.elementAt(n);
                            if (point.x == -1 || point4.x == -1) continue;
                            mGraphics2.setColor(0);
                            mGraphics2.drawLine(point4.x / 1000, point4.y / 1000, point.x / 1000, point.y / 1000, false);
                            mGraphics2.drawLine(point4.x / 1000 + this.xline, point4.y / 1000 + this.yline, point.x / 1000 + this.xline, point.y / 1000 + this.yline, false);
                        }
                    }
                    break;
                }
                case 14: 
                case 81: 
                case 115: {
                    if (this.fraImgEff == null) {
                        return;
                    }
                    for (int i = 0; i < this.VecEff.size(); ++i) {
                        Point point = (Point)this.VecEff.elementAt(i);
                        if (point.f == 0) {
                            this.fraImgEff.drawFrameEffectSkill(this.f % this.fraImgEff.nFrame, point.x / 1000, point.y / 1000, 0, 3, mGraphics2);
                            continue;
                        }
                        if (point.f <= 0) continue;
                        this.fraImgSubEff.drawFrameEffectSkill((point.f - 1) / 2 % this.fraImgSubEff.nFrame, point.x / 1000, point.y / 1000 - 4 - point.f, 0, 3, mGraphics2);
                    }
                    break;
                }
                case 20: 
                case 113: {
                    if (this.fraImgEff == null) {
                        return;
                    }
                    for (int i = 0; i < this.VecEff.size(); ++i) {
                        Point point = (Point)this.VecEff.elementAt(i);
                        if (point == null || point.isRemove) continue;
                        if (point.dis == 0) {
                            if (this.typeEffect == 20) {
                                mGraphics2.setColor(-5324012);
                            } else {
                                mGraphics2.setColor(10549488);
                            }
                            mGraphics2.drawLine(point.x, point.y, point.x + 6, point.y + 8, false);
                            mGraphics2.drawLine(point.x, point.y, point.x + 5, point.y + 8, false);
                            continue;
                        }
                        if (point.dis != 1 || point.f >= 2 || this.typeEffect == 113) continue;
                        this.fraImgEff.drawFrameEffectSkill1(point.f, point.x, point.y - point.vy / 2, 0, mGraphics2);
                    }
                    break;
                }
                case 21: {
                    if (this.fraImgEff == null) {
                        return;
                    }
                    this.fraImgEff.drawFrameEffectSkill(this.f / 2 % 3, this.x, this.y, 0, 3, mGraphics2);
                    this.fraImgSubEff.drawFrameEffectSkill(this.f / 2 % 3, this.x, this.y, 0, 3, mGraphics2);
                    break;
                }
                case 22: 
                case 31: 
                case 88: {
                    if (this.fraImgEff == null) {
                        return;
                    }
                    if (this.typeEffect == 31 || this.typeEffect == 88) {
                        for (int i = 0; i < this.VecEff.size(); ++i) {
                            Point point = (Point)this.VecEff.elementAt(i);
                            this.fraImgSubEff.drawFrameEffectSkill(point.f / 2 % this.fraImgSubEff.nFrame, point.x, point.y, this.frameArrow, 3, mGraphics2);
                        }
                    }
                    if (this.f >= this.fRemove) break;
                    this.paint_Bullet(mGraphics2, this.fraImgEff, this.frame, this.x, this.y, false);
                    break;
                }
                case 23: {
                    if (this.fraImgEff == null) {
                        return;
                    }
                    for (int i = 0; i < this.VecEff.size(); ++i) {
                        Point point = (Point)this.VecEff.elementAt(i);
                        this.paint_Bullet(mGraphics2, this.fraImgEff, this.frame, point.x, point.y, false);
                    }
                    break;
                }
                case 25: {
                    if (this.fraImgEff == null) {
                        return;
                    }
                    for (int i = 0; i < this.VecEff.size(); ++i) {
                        Point point = (Point)this.VecEff.elementAt(i);
                        if (point == null) continue;
                        this.fraImgSubEff.drawFrameEffectSkill(point.f % this.fraImgSubEff.nFrame, point.x, point.y, this.frameArrow, 3, mGraphics2);
                    }
                    if (this.typeEffect != 25 || this.f >= this.fRemove) break;
                    this.paint_Bullet(mGraphics2, this.fraImgEff, this.frame, this.x, this.y, false);
                    break;
                }
                case 26: {
                    if (this.fraImgEff == null) {
                        return;
                    }
                    for (int i = 0; i < this.VecEff.size(); ++i) {
                        Point point = (Point)this.VecEff.elementAt(i);
                        if (point.f >= 7) continue;
                        this.fraImgEff.drawFrameEffectSkill(point.f / 2 % this.fraImgEff.nFrame, point.x, point.y, 0, 3, mGraphics2);
                    }
                    if (this.f < this.fRemove || this.f > this.fRemove + 5) break;
                    this.fraImgSubEff.drawFrameEffectSkill((this.f - this.fRemove) / 2 % this.fraImgSubEff.nFrame, this.toX, this.toY - 5, 0, 3, mGraphics2);
                    break;
                }
                case 27: {
                    for (int i = 0; i < this.VecEff.size(); ++i) {
                        Point point = (Point)this.VecEff.elementAt(i);
                        if (point.f >= 3) continue;
                        this.fraImgEff.drawFrameEffectSkill(point.f % this.fraImgEff.nFrame, point.x, point.y, 0, 3, mGraphics2);
                    }
                    break;
                }
                case 28: {
                    if (this.fraImgEff == null) {
                        return;
                    }
                    this.fraImgEff.drawFrameEffectSkill(this.f / 2 % this.fraImgEff.nFrame, this.x, this.y - this.fraImgEff.frameHeight, 0, 17, mGraphics2);
                    break;
                }
                case 30: {
                    for (int i = 0; i < this.VecEff.size(); ++i) {
                        Point point = (Point)this.VecEff.elementAt(i);
                        if (point == null || point.f >= 5) continue;
                        if (point.f < 4) {
                            this.fraImgEff.drawFrameEffectSkill(point.f % this.fraImgEff.nFrame, point.x / 1000, point.y / 1000, 0, 3, mGraphics2);
                            continue;
                        }
                        this.fraImgEff.drawFrameEffectSkill(3, point.x / 1000, point.y / 1000, 0, 3, mGraphics2);
                    }
                    break;
                }
                case 34: {
                    if (this.fraImgEff == null) {
                        return;
                    }
                    for (int i = 0; i < this.VecEff.size(); ++i) {
                        Point point = (Point)this.VecEff.elementAt(i);
                        int n = 0;
                        if (point.vx < 0) {
                            n = 2;
                        }
                        this.fraImgEff.drawFrameEffectSkill(point.f % this.fraImgEff.nFrame, point.x, point.y, n, 3, mGraphics2);
                        if (point.f % 3 == 2 || this.objBeKillMain == null) continue;
                        this.fraImgSubEff.drawFrameEffectSkill(point.f % this.fraImgSubEff.nFrame, this.objBeKillMain.x, this.objBeKillMain.y - this.objBeKillMain.hOne / 2, 0, 3, mGraphics2);
                    }
                    break;
                }
                case 40: {
                    Point point;
                    int n;
                    if (this.fraImgEff == null) {
                        return;
                    }
                    for (n = 0; n < this.VecEff.size(); ++n) {
                        point = (Point)this.VecEff.elementAt(n);
                        if (point == null) continue;
                        int n4 = (point.x + point.vx) / 1000;
                        int n5 = (point.y + point.vy) / 1000;
                        if (this.f / 2 % 2 == 0) {
                            this.paint_Bullet(mGraphics2, this.fraImgEff, point.frame, n4, n5, false);
                            continue;
                        }
                        this.paint_Bullet(mGraphics2, this.fraImgSub2Eff, point.frame, n4, n5, false);
                    }
                    for (n = 0; n < this.VecSubEff.size(); ++n) {
                        point = (Point)this.VecSubEff.elementAt(n);
                        this.fraImgSubEff.drawFrameEffectSkill(this.f % this.fraImgSubEff.nFrame, point.x / 1000, point.y / 1000, 0, 3, mGraphics2);
                    }
                    break;
                }
                case 41: 
                case 83: {
                    for (int i = 0; i < this.VecEff.size(); ++i) {
                        Point point = (Point)this.VecEff.elementAt(i);
                        this.fraImgEff.drawFrameEffectSkill(point.f % this.fraImgEff.nFrame, point.x, point.y, 0, 3, mGraphics2);
                    }
                    break;
                }
                case 42: {
                    this.fraImgEff.drawFrameEffectSkill(this.f / 4 % this.fraImgEff.nFrame, this.x, this.y, 0, 3, mGraphics2);
                    if (this.f < this.fRemove / 2) {
                        for (int i = 0; i < this.VecEff.size(); ++i) {
                            Point point = (Point)this.VecEff.elementAt(i);
                            mGraphics2.setColor(-1);
                            if (point.frame == 1) {
                                mGraphics2.setColor(-7309104);
                            }
                            if (point.fRe == 1) {
                                mGraphics2.drawLine(this.x + point.x, this.y + point.y, this.x + point.x, this.y + point.y - point.limitY, false);
                            }
                            if (point.frame != 2) continue;
                            mGraphics2.setColor(-7309104);
                            mGraphics2.drawLine(this.x + point.x + 1, this.y + point.y, this.x + point.x + 1, this.y + point.y - point.limitY, false);
                        }
                        break;
                    }
                    this.fraImgSubEff.drawFrameEffectSkill(0, this.x, this.y - 50 - (this.f - this.fRemove / 2) * 2, 0, 3, mGraphics2);
                    break;
                }
                case 43: {
                    this.fraImgEff.drawFrameEffectSkill(this.f / 2 % this.fraImgEff.nFrame, this.x, this.y - 10, 0, 3, mGraphics2);
                    for (int i = 0; i < this.VecEff.size(); ++i) {
                        Point point = (Point)this.VecEff.elementAt(i);
                        mGraphics2.setColor(-1);
                        if (point.frame == 1) {
                            mGraphics2.setColor(-7309104);
                        }
                        if (point.fRe == 1) {
                            mGraphics2.drawLine(this.x + point.x, this.y + point.y, this.x + point.x, this.y + point.y - point.limitY, false);
                        }
                        if (point.frame != 2) continue;
                        mGraphics2.setColor(-7309104);
                        mGraphics2.drawLine(this.x + point.x + 1, this.y + point.y, this.x + point.x + 1, this.y + point.y - point.limitY, false);
                    }
                    break;
                }
                case 46: {
                    if (this.fraImgEff == null) {
                        return;
                    }
                    if (this.f < 3) {
                        if (this.objBeKillMain == null) break;
                        this.fraImgEff.drawFrameEffectSkill(this.f % this.fraImgEff.nFrame, this.toX, this.toY - this.objBeKillMain.hOne / 2, 0, 3, mGraphics2);
                        break;
                    }
                    this.fraImgSubEff.drawFrameEffectSkill(this.f / 2 % this.fraImgSubEff.nFrame, this.toX, this.toY + 5, CRes.random(2) == 0 ? 0 : 2, 33, mGraphics2);
                    break;
                }
                case 112: {
                    for (int i = 0; i < this.VecEff.size(); ++i) {
                        Point point = (Point)this.VecEff.elementAt(i);
                        if (point == null || this.f < point.fRe || this.f > this.fRemove - 3 + point.fRe) continue;
                        this.fraImgSubEff.drawFrameEffectSkill((this.f / 3 + point.fRe) % this.fraImgSubEff.nFrame, point.x / 1000, point.y / 1000 + 4, point.dis, 33, mGraphics2);
                    }
                    break;
                }
                case 47: {
                    for (int i = 0; i < this.VecEff.size(); ++i) {
                        Point point = (Point)this.VecEff.elementAt(i);
                        if (point == null || this.f < point.fRe || this.f > this.fRemove - 3 + point.fRe) continue;
                        this.fraImgEff.drawFrameEffectSkill(point.frame, point.x / 1000, point.y / 1000, point.dis, 33, mGraphics2);
                        this.fraImgSubEff.drawFrameEffectSkill((this.f / 3 + point.fRe) % this.fraImgSubEff.nFrame, point.x / 1000, point.y / 1000 + 4, point.dis, 33, mGraphics2);
                    }
                    break;
                }
                case 49: {
                    if (this.fraImgEff == null) {
                        return;
                    }
                    if (this.f < 6) {
                        this.fraImgSubEff.drawFrameEffectSkill(this.f / 2, this.x1000, this.y1000, 0, 3, mGraphics2);
                    }
                    for (int i = 0; i < this.VecEff.size(); ++i) {
                        Point point = (Point)this.VecEff.elementAt(i);
                        this.fraImgEff.drawFrameEffectSkill(point.f % this.fraImgEff.nFrame, point.x, point.y, 0, 3, mGraphics2);
                    }
                    if (this.f < this.fRemove || this.f >= this.fRemove + 6) break;
                    this.fraImgSubEff.drawFrameEffectSkill((this.f - this.fRemove) / 2, this.toX, this.toY, 0, 3, mGraphics2);
                    break;
                }
                case 50: {
                    if (this.f >= 3 || this.fraImgEff == null) break;
                    this.fraImgEff.drawFrameEffectSkill(this.f % this.fraImgEff.nFrame, this.x, this.y, this.frameArrow, this.frame, mGraphics2);
                    break;
                }
                case 125: {
                    for (int i = 0; i < this.size1; ++i) {
                        if (i == this.size1 - 1) {
                            mGraphics2.setColor(720469);
                        } else {
                            mGraphics2.setColor(-1);
                        }
                        mGraphics2.drawLine(this.x, this.y, this.x1000 + i * this.vX1000, this.y1000 + i * this.vY1000, false);
                        mGraphics2.drawLine(this.x - i * this.vX1000, this.y - i * this.vY1000, this.x1000 - i * this.vX1000, this.y1000 - i * this.vY1000, false);
                    }
                    break;
                }
                case 126: {
                    for (int i = 0; i < this.size1; ++i) {
                        if (i == this.size1 - 1) {
                            mGraphics2.setColor(15771896);
                        } else {
                            mGraphics2.setColor(-1);
                        }
                        mGraphics2.drawLine(this.x, this.y, this.x1000 + i * this.vX1000, this.y1000 + i * this.vY1000, false);
                        mGraphics2.drawLine(this.x - i * this.vX1000, this.y - i * this.vY1000, this.x1000 - i * this.vX1000, this.y1000 - i * this.vY1000, false);
                    }
                    break;
                }
                case 106: {
                    int n;
                    if (this.fraImgEff == null) {
                        return;
                    }
                    this.fraImgEff.drawFrameEffectSkill(this.f % this.fraImgEff.nFrame, this.x, this.y, 0, 3, mGraphics2);
                    for (n = 0; n < this.size1; ++n) {
                        if (n == this.size1 - 1) {
                            mGraphics2.setColor(720469);
                        } else {
                            mGraphics2.setColor(-1);
                        }
                        mGraphics2.drawLine(this.x + n * this.vX1000, this.y + n * this.vY1000, this.x1000 + n * this.vX1000, this.y1000 + n * this.vY1000, false);
                        mGraphics2.drawLine(this.x - n * this.vX1000, this.y - n * this.vY1000, this.x1000 - n * this.vX1000, this.y1000 - n * this.vY1000, false);
                    }
                    for (n = 0; n < this.VecEff.size(); ++n) {
                        Point point = (Point)this.VecEff.elementAt(n);
                        if (point == null) continue;
                        this.fraImgSubEff.drawFrameEffectSkill(point.f % this.fraImgSubEff.nFrame, point.x, point.y, 0, 3, mGraphics2);
                    }
                    break;
                }
                case 105: {
                    int n;
                    if (this.fraImgEff == null) {
                        return;
                    }
                    if (this.f < this.fRemove) {
                        this.fraImgEff.drawFrameEffectSkill(this.f % this.fraImgEff.nFrame, this.x, this.y, 0, 3, mGraphics2);
                        if (this.f > 8) {
                            n = this.fRemove - this.f;
                            if (n < 1) {
                                n = 1;
                            }
                            for (int i = 0; i < n; ++i) {
                                if (i == n - 1) {
                                    mGraphics2.setColor(720469);
                                } else {
                                    mGraphics2.setColor(-1);
                                }
                                mGraphics2.drawLine(this.x + i * this.vX1000, this.y + i * this.vY1000, this.x1000 + i * this.vX1000, this.y1000 + i * this.vY1000, false);
                                mGraphics2.drawLine(this.x - i * this.vX1000, this.y - i * this.vY1000, this.x1000 - i * this.vX1000, this.y1000 - i * this.vY1000, false);
                            }
                        }
                    }
                    for (n = 0; n < this.VecEff.size(); ++n) {
                        Point point = (Point)this.VecEff.elementAt(n);
                        if (point == null) continue;
                        this.fraImgSubEff.drawFrameEffectSkill(point.f % this.fraImgSubEff.nFrame, point.x, point.y, 0, 3, mGraphics2);
                    }
                    break;
                }
                case 51: {
                    int n;
                    if (this.fraImgEff == null) {
                        return;
                    }
                    if (this.f < this.fRemove) {
                        this.fraImgEff.drawFrameEffectSkill(this.f % this.fraImgEff.nFrame, this.x, this.y, 0, 3, mGraphics2);
                        if (this.f > 8) {
                            n = this.fRemove - this.f;
                            if (n < 1) {
                                n = 1;
                            }
                            for (int i = 0; i < n; ++i) {
                                if (i == n - 1) {
                                    mGraphics2.setColor(-65536);
                                } else {
                                    mGraphics2.setColor(-1);
                                }
                                mGraphics2.drawLine(this.x + i * this.vX1000, this.y + i * this.vY1000, this.x1000 + i * this.vX1000, this.y1000 + i * this.vY1000, false);
                                mGraphics2.drawLine(this.x - i * this.vX1000, this.y - i * this.vY1000, this.x1000 - i * this.vX1000, this.y1000 - i * this.vY1000, false);
                            }
                        }
                    }
                    for (n = 0; n < this.VecEff.size(); ++n) {
                        Point point = (Point)this.VecEff.elementAt(n);
                        if (point == null) continue;
                        this.fraImgSubEff.drawFrameEffectSkill(point.f % this.fraImgSubEff.nFrame, point.x, point.y, 0, 3, mGraphics2);
                    }
                    break;
                }
                case 52: 
                case 54: {
                    if (this.fraImgEff == null) {
                        return;
                    }
                    this.fraImgEff.drawFrameEffectSkill(this.f / 2 % this.fraImgEff.nFrame, this.toX, this.toY, CRes.random(2) == 0 ? 0 : 2, 33, mGraphics2);
                    for (int i = 0; i < this.VecEff.size(); ++i) {
                        Point point = (Point)this.VecEff.elementAt(i);
                        this.fraImgSubEff.drawFrameEffectSkill(point.f / 2 % this.fraImgSubEff.nFrame, point.x, point.y, 0, 3, mGraphics2);
                    }
                    break;
                }
                case 53: 
                case 64: 
                case 78: 
                case 104: {
                    if (this.fraImgEff == null) {
                        return;
                    }
                    for (int i = 0; i < this.VecEff.size(); ++i) {
                        Point point = (Point)this.VecEff.elementAt(i);
                        if (this.f > this.fRemove) continue;
                        int n = 0;
                        int n6 = 3;
                        if (this.f < 2) {
                            n = this.f;
                        }
                        if (this.f > this.fRemove - 5) {
                            n = this.fRemove - this.f;
                            n6 = 5;
                        } else {
                            n = 2;
                        }
                        mImage mImage2 = ImageEffect.setImage(this.fraImgEff.Id);
                        if (mImage2 != null && mImage2.image != null) {
                            mGraphics2.drawRegion(mImage2, 0, 0, this.fraImgEff.frameWidth, this.fraImgEff.frameHeight / n6 * (n + 1), 0, point.x, point.y, 33, false);
                        }
                        if (this.f >= 3) continue;
                        this.fraImgSubEff.drawFrameEffectSkill(this.f, point.x, point.y + 10, 0, 33, mGraphics2);
                    }
                    break;
                }
                case 55: {
                    if (this.fraImgEff == null) {
                        return;
                    }
                    if (this.f < 6) {
                        this.fraImgEff.drawFrameEffectSkill(this.f / 2, this.toX, this.toY, 0, 3, mGraphics2);
                    }
                    for (int i = 0; i < this.VecEff.size(); ++i) {
                        if (i >= this.VecEff.size()) continue;
                        Line line = (Line)this.VecEff.elementAt(i);
                        mGraphics2.setColor(-7);
                        if (line.idColor == 1) {
                            mGraphics2.setColor(-462656);
                        }
                        mGraphics2.drawLine(line.x0, line.y0, line.x1, line.y1, false);
                        if (!line.is2Line) continue;
                        mGraphics2.setColor(-466864);
                        mGraphics2.drawLine(line.x0 + 1, line.y0 - 1, line.x1 + 1, line.y1 - 1, false);
                    }
                    break;
                }
                case 56: {
                    if (this.subType == 10) {
                        this.fraImgEff.drawFrameEffectSkill(this.f / 2 % this.fraImgEff.nFrame, this.x, this.y + 5, 0, 33, mGraphics2);
                        break;
                    }
                    if (this.subType != 11) break;
                    this.fraImgEff.drawFrameEffectSkill(this.f % this.fraImgEff.nFrame, this.x, this.y + 5, 0, 33, mGraphics2);
                    break;
                }
                case 57: 
                case 70: {
                    for (int i = this.VecEff.size() - 1; i >= 0; --i) {
                        Line line = (Line)this.VecEff.elementAt(i);
                        if (line == null || line.f >= 6) continue;
                        mGraphics2.setColor(this.colorpaint[i]);
                        mGraphics2.drawLine(line.x0 / 1000, line.y0 / 1000, line.x1 / 1000, line.y1 / 1000, false);
                        if (!line.is2Line) continue;
                        mGraphics2.drawLine(line.x0 / 1000 + 1, line.y0 / 1000 + 1, line.x1 / 1000 + 1, line.y1 / 1000, false);
                    }
                    break;
                }
                case 58: {
                    if (this.f < 2) {
                        mGraphics2.setColor(-1);
                        mGraphics2.fillRect(this.x - 9, MainScreen.cameraMain.yCam, 18, (this.y - MainScreen.cameraMain.yCam) / 2 * (this.f + 1), false);
                        mGraphics2.setColor(-7309104);
                        mGraphics2.fillRect(this.x - 10, MainScreen.cameraMain.yCam, 2, (this.y - MainScreen.cameraMain.yCam) / 2 * (this.f + 1), false);
                        mGraphics2.fillRect(this.x + 9, MainScreen.cameraMain.yCam, 2, (this.y - MainScreen.cameraMain.yCam) / 2 * (this.f + 1), false);
                    } else if (this.f < 8) {
                        mGraphics2.setColor(-1);
                        mGraphics2.fillRect(this.x - 9 + (this.f - 2) / 2 * 3, MainScreen.cameraMain.yCam, 18 - (this.f - 2) / 2 * 6, this.y - MainScreen.cameraMain.yCam, false);
                        mGraphics2.setColor(-7309104);
                        mGraphics2.fillRect(this.x - 10 + (this.f - 2) / 2 * 3, MainScreen.cameraMain.yCam, 2, this.y - MainScreen.cameraMain.yCam, false);
                        mGraphics2.fillRect(this.x + 9 - (this.f - 2) / 2 * 3, MainScreen.cameraMain.yCam, 2, this.y - MainScreen.cameraMain.yCam, false);
                    }
                    if (this.f > 1 && this.f < 11) {
                        this.fraImgEff.drawFrameEffectSkill(3 + (this.f - 2) / 3, this.x, this.y + 5, 0, 33, mGraphics2);
                    }
                    for (int i = 0; i < this.VecEff.size(); ++i) {
                        Point point = (Point)this.VecEff.elementAt(i);
                        this.fraImgSubEff.drawFrameEffectSkill(this.f < 8 ? 0 : 1, point.x, point.y + 4, 0, 33, mGraphics2);
                    }
                    break;
                }
                case 61: 
                case 77: {
                    if (this.fraImgEff == null) {
                        return;
                    }
                    for (int i = 0; i < this.VecEff.size(); ++i) {
                        Point point = (Point)this.VecEff.elementAt(i);
                        if (this.f >= point.f) continue;
                        for (int j = 0; j < point.vecEffPoint.size(); ++j) {
                            Point point5 = (Point)point.vecEffPoint.elementAt(j);
                            if (point5.dis == 1) {
                                this.fraImgSub2Eff.drawFrameEffectSkill(0, point5.x, point5.y + point.vy * this.f / 1000, 0, 3, mGraphics2);
                                continue;
                            }
                            this.fraImgSubEff.drawFrameEffectSkill(point5.frame, point5.x, point5.y + point.vy * this.f / 1000, 0, 3, mGraphics2);
                        }
                        this.fraImgEff.drawFrameEffectSkill(point.dis, point.x, point.y + point.vy * this.f / 1000, 0, 3, mGraphics2);
                    }
                    break;
                }
                case 62: {
                    if (this.fraImgEff == null) {
                        return;
                    }
                    for (int i = 0; i < this.VecEff.size(); ++i) {
                        Point point = (Point)this.VecEff.elementAt(i);
                        if (point.f <= 0) continue;
                        this.paint_Bullet(mGraphics2, this.fraImgEff, point.frame, point.x, point.y, false);
                    }
                    break;
                }
                case 63: {
                    if (this.fraImgEff == null) {
                        return;
                    }
                    for (int i = 0; i < this.VecEff.size(); ++i) {
                        Point point = (Point)this.VecEff.elementAt(i);
                        if (point.f <= 0) continue;
                        this.paint_Bullet(mGraphics2, this.fraImgEff, point.frame, point.x, point.y, false);
                    }
                    break;
                }
                case 73: {
                    Point point;
                    int n;
                    if (this.fraImgEff == null) {
                        return;
                    }
                    for (n = 0; n < this.VecSubEff.size(); ++n) {
                        point = (Point)this.VecSubEff.elementAt(n);
                        this.fraImgSubEff.drawFrameEffectSkill(point.f / 2 % this.fraImgSubEff.nFrame, point.x, point.y, this.frameArrow, 3, mGraphics2);
                    }
                    for (n = 0; n < this.VecEff.size(); ++n) {
                        point = (Point)this.VecEff.elementAt(n);
                        if (point.f <= 0) continue;
                        this.paint_Bullet(mGraphics2, this.fraImgEff, point.frame, point.x, point.y, false);
                    }
                    break;
                }
                case 67: {
                    if (this.fraImgEff == null) {
                        return;
                    }
                    this.fraImgEff.drawFrameEffectSkill(this.f / 2, this.x1000, this.y1000, 0, 3, mGraphics2);
                    if (this.f >= this.fRemove || this.fraImgSubEff == null) break;
                    this.fraImgSubEff.drawFrameEffectSkill(this.f % this.fraImgSubEff.nFrame, this.x, this.y, 0, 3, mGraphics2);
                    break;
                }
                case 68: {
                    if (this.f >= this.fRemove) break;
                    if (this.subType == 0) {
                        this.fraImgEff.drawFrameEffectSkill(this.f, this.x, this.y, 0, 3, mGraphics2);
                        break;
                    }
                    this.fraImgEff.drawFrameEffectSkill(this.f / 2, this.x, this.y, 0, 3, mGraphics2);
                    break;
                }
                case 69: {
                    this.fraImgEff.drawFrameEffectSkill(this.f / 4 % this.fraImgEff.nFrame, this.x, this.y, 0, 3, mGraphics2);
                    break;
                }
                case 71: {
                    if (this.f < 6) {
                        this.fraImgSubEff.drawFrameEffectSkill(this.f / 2, this.x, this.y + this.objKill.hOne / 2, 0, 33, mGraphics2);
                    }
                    for (int i = 0; i < this.VecEff.size(); ++i) {
                        Point point = (Point)this.VecEff.elementAt(i);
                        if (this.f > this.fRemove) continue;
                        int n = 0;
                        n = this.f < 3 ? this.f : (this.f > this.fRemove - 3 ? this.fRemove - this.f : CRes.random(2, 4));
                        this.fraImgEff.drawFrameEffectSkill(n, point.x, point.y, 0, 33, mGraphics2);
                        if (this.f >= 6) continue;
                        this.fraImgSubEff.drawFrameEffectSkill(this.f / 2, point.x, point.y + 10, 0, 33, mGraphics2);
                    }
                    break;
                }
                case 72: {
                    if (this.fraImgEff == null) {
                        return;
                    }
                    if (this.f < this.fRemove) {
                        this.fraImgSubEff.drawFrameEffectSkill(this.f / 2 % this.fraImgSubEff.nFrame, this.x, this.y, 0, 3, mGraphics2);
                    }
                    for (int i = 0; i < this.VecEff.size(); ++i) {
                        Point_Focus point_Focus = (Point_Focus)this.VecEff.elementAt(i);
                        this.paint_Bullet(mGraphics2, this.fraImgEff, point_Focus.frame, point_Focus.x, point_Focus.y, false);
                    }
                    break;
                }
                case 74: {
                    if (this.fraImgEff == null) {
                        return;
                    }
                    if (this.f < this.fRemove) {
                        this.fraImgEff.drawFrameEffectSkill(this.f / 2 % this.fraImgEff.nFrame, this.x, this.y + this.objKill.hOne / 2, 0, 33, mGraphics2);
                    }
                    for (int i = 0; i < this.VecEff.size(); ++i) {
                        Point_Focus point_Focus = (Point_Focus)this.VecEff.elementAt(i);
                        this.fraImgEff.drawFrameEffectSkill(this.f / 2 % this.fraImgEff.nFrame, point_Focus.x, point_Focus.y, this.frameArrow, 33, mGraphics2);
                    }
                    break;
                }
                case 75: {
                    if (this.fraImgEff == null) {
                        return;
                    }
                    for (int i = 0; i < this.VecEff.size(); ++i) {
                        Point point = (Point)this.VecEff.elementAt(i);
                        if (this.f > this.fRemove) continue;
                        int n = 0;
                        n = this.f < 5 ? this.f / 2 : (n > this.fRemove - 5 ? (this.fRemove - this.f) / 2 : CRes.random(2, 4));
                        this.fraImgEff.drawFrameEffectSkill(n, point.x, point.y, 0, 33, mGraphics2);
                    }
                    break;
                }
                case 76: {
                    if (this.fraImgEff == null) {
                        return;
                    }
                    if (this.f < 6) {
                        this.fraImgSubEff.drawFrameEffectSkill(this.f / 2 % this.fraImgSubEff.nFrame, this.x, this.y, 0, 3, mGraphics2);
                    }
                    for (int i = 0; i < this.VecEff.size(); ++i) {
                        Point point = (Point)this.VecEff.elementAt(i);
                        this.fraImgEff.drawFrameEffectSkill(CRes.random(3), point.x / 1000 + CRes.random_Am_0(5), point.y / 1000 + CRes.random_Am_0(5), 0, 3, mGraphics2);
                    }
                    break;
                }
                case 85: {
                    if (this.objBeKillMain == null || this.objBeKillMain.isRemove || this.objBeKillMain.isTanHinh || this.objBeKillMain.isStop) break;
                    for (int i = this.VecEff.size() - 1; i >= 0; --i) {
                        Line line = (Line)this.VecEff.elementAt(i);
                        if (line == null) continue;
                        int n = 0;
                        n = colorStar[0][line.idColor];
                        mGraphics2.setColor(n);
                        mGraphics2.drawLine(line.x0, line.y0, line.x1, line.y1, false);
                        if (!line.is2Line) continue;
                        mGraphics2.drawLine(line.x0 + 1, line.y0, line.x1 + 1, line.y1, false);
                    }
                    break;
                }
                case 87: {
                    this.fraImgEff.drawFrameEffectSkill(this.f / 2 % this.fraImgEff.nFrame, this.x, this.y, 0, 3, mGraphics2);
                    break;
                }
                case 89: 
                case 92: {
                    if (this.fraImgEff == null) {
                        return;
                    }
                    if (this.typeEffect == 89 || this.typeEffect == 92) {
                        for (int i = 0; i < this.VecEff.size(); ++i) {
                            Point point = (Point)this.VecEff.elementAt(i);
                            this.fraImgSubEff.drawFrameEffectSkill(point.f / 2 % this.fraImgSubEff.nFrame, point.x, point.y, this.frameArrow, 3, mGraphics2);
                        }
                    }
                    if (this.f >= this.fRemove) break;
                    this.paint_Bullet(mGraphics2, this.fraImgEff, this.frame, this.x, this.y, false);
                    break;
                }
                case 90: {
                    this.paint_Ice_Nova_Effect(mGraphics2);
                    break;
                }
                case 91: {
                    this.paint_Poison_Nova_Effect(mGraphics2);
                    break;
                }
                case 93: {
                    if (this.f < 6) {
                        this.fraImgSubEff.drawFrameEffectSkill(this.f / 2, this.x, this.y + this.objKill.hOne / 2, 0, 33, mGraphics2);
                    }
                    for (int i = 0; i < this.VecEff.size(); ++i) {
                        Point point = (Point)this.VecEff.elementAt(i);
                        if (this.f > this.fRemove) continue;
                        int n = 0;
                        n = this.f < 3 ? this.f : (this.f > this.fRemove - 3 ? this.fRemove - this.f : CRes.random(2, 4));
                        this.fraImgEff.drawFrameEffectSkill(n, point.x, point.y, 0, 33, mGraphics2);
                    }
                    break;
                }
                case 101: {
                    if (!this.ispaintsleep) break;
                    AvMain.imgSleep.drawFrameEffectSkill(this.frameSleep[this.frSleep], this.x - 8, this.y - (17 + (this.objBeKillMain.typeMount != -1 ? this.objBeKillMain.dyMount : 0)), 0, 33, mGraphics2);
                    break;
                }
                case 102: {
                    AvMain.imgStun.drawFrameEffectSkill(this.frameStun[this.frStun], this.x, this.y - (this.ystun + (this.objBeKillMain.typeMount != -1 ? this.objBeKillMain.dyMount : 0)), 0, 33, mGraphics2);
                    break;
                }
                case 94: {
                    if (this.f < 0) {
                        return;
                    }
                    if (this.fraImgEff == null || this.objBeKillMain == null) break;
                    this.fraImgEff.drawFrameEffectSkill(this.nFrame[this.f], this.objBeKillMain.x + 2, this.objBeKillMain.y + 8, 0, 33, mGraphics2);
                    break;
                }
                case 95: {
                    Point point;
                    Point point6;
                    int n;
                    if (this.f % 5 >= 2 && this.f % 5 != 3) break;
                    for (n = 1; n < this.VecEff.size(); ++n) {
                        point6 = (Point)this.VecEff.elementAt(n - 1);
                        point = (Point)this.VecEff.elementAt(n);
                        mGraphics2.setColor(-5498433);
                        mGraphics2.drawLine(point6.x / 1000, point6.y / 1000 - 1, point.x / 1000, point.y / 1000 - 1, false);
                        mGraphics2.drawLine(point6.x / 1000, point6.y / 1000 + 1, point.x / 1000, point.y / 1000 + 1, false);
                        mGraphics2.drawLine(point6.x / 1000, point6.y / 1000 + 2, point.x / 1000, point.y / 1000 + 2, false);
                        mGraphics2.setColor(-1660952);
                        mGraphics2.drawLine(point6.x / 1000, point6.y / 1000, point.x / 1000, point.y / 1000, false);
                    }
                    for (n = 1; n < this.VecSubEff.size(); ++n) {
                        point6 = (Point)this.VecSubEff.elementAt(n - 1);
                        point = (Point)this.VecSubEff.elementAt(n);
                        if (point.x == -1 || point6.x == -1) continue;
                        mGraphics2.setColor(-5498433);
                        mGraphics2.drawLine(point6.x / 1000, point6.y / 1000, point.x / 1000, point.y / 1000, false);
                        mGraphics2.drawLine(point6.x / 1000, point6.y / 1000 + 1, point.x / 1000, point.y / 1000 + 1, false);
                    }
                    break;
                }
                case 96: {
                    if (this.fraImgEff == null) {
                        return;
                    }
                    if (this.typeEffect == 96) {
                        for (int i = 0; i < this.VecEff.size(); ++i) {
                            Point point = (Point)this.VecEff.elementAt(i);
                            this.fraImgSubEff.drawFrameEffectSkill(point.f / 2 % this.fraImgSubEff.nFrame, point.x, point.y, this.frameArrow, 3, mGraphics2);
                        }
                    }
                    if (this.f >= this.fRemove) break;
                    this.paint_Bullet(mGraphics2, this.fraImgEff, this.frame, this.x, this.y, false);
                    break;
                }
                case 97: {
                    int n;
                    if (this.fraImgEff == null) {
                        return;
                    }
                    if (this.f < this.fRemove) {
                        this.fraImgEff.drawFrameEffectSkill(this.f % this.fraImgEff.nFrame, this.x, this.y, 0, 3, mGraphics2);
                        if (this.f > 8) {
                            n = this.fRemove - this.f;
                            if (n < 1) {
                                n = 1;
                            }
                            for (int i = 0; i < n; ++i) {
                                if (i == n - 1) {
                                    mGraphics2.setColor(-2560);
                                } else {
                                    mGraphics2.setColor(-1);
                                }
                                mGraphics2.drawLine(this.x + i * this.vX1000, this.y + i * this.vY1000, this.xdichTower + i * this.vX1000, this.ydichTower + i * this.vY1000, false);
                                mGraphics2.drawLine(this.x - i * this.vX1000, this.y - i * this.vY1000, this.xdichTower - i * this.vX1000, this.ydichTower - i * this.vY1000, false);
                            }
                        }
                    }
                    for (n = 0; n < this.VecEff.size(); ++n) {
                        Point point = (Point)this.VecEff.elementAt(n);
                        if (point == null) continue;
                        this.fraImgSubEff.drawFrameEffectSkill(point.f % this.fraImgSubEff.nFrame, point.x, point.y, 0, 3, mGraphics2);
                    }
                    break;
                }
                case 98: {
                    if (this.fraImgEff == null) {
                        return;
                    }
                    if (this.typeEffect == 98) {
                        for (int i = 0; i < this.VecEff.size(); ++i) {
                            Point point = (Point)this.VecEff.elementAt(i);
                            this.fraImgSubEff.drawFrameEffectSkill(point.f / 2 % this.fraImgSubEff.nFrame, point.x, point.y, this.frameArrow, 3, mGraphics2);
                        }
                    }
                    if (this.f >= this.fRemove) break;
                    this.paint_Bullet(mGraphics2, this.fraImgEff, this.frame, this.x, this.y, false);
                    break;
                }
                case 99: {
                    if (this.fraImgEff == null) {
                        return;
                    }
                    if (this.f >= this.fRemove) break;
                    this.paint_Bullet(mGraphics2, this.fraImgEff, this.frame, this.x, this.y, false);
                    break;
                }
            }
        }
        catch (Exception exception) {
            this.removeEff();
        }
        this.test = 0;
    }

    public void updateEffectCharWearing() {
    }

    public void update() {
        ++this.f;
        ++this.subf;
        try {
            boolean bl;
            if (this.objBeKillMain != null) {
                this.xEff = this.objBeKillMain.x;
                this.yEff = this.objBeKillMain.y - this.objBeKillMain.hOne / 2;
            }
            if (this.f == this.timeAddNum && this.timeAddNum > 0) {
                bl = true;
                if (mSystem.isj2me && GameScreen.infoGame.ismapHouse(GameCanvas.loadmap.idMap)) {
                    bl = false;
                }
                if (!this.isEff && bl) {
                    this.addNum();
                }
                this.SetAddSoundTimeAdd();
            }
            bl = true;
            switch (this.typeEffect) {
                case 124: {
                    this.x += this.vx;
                    this.y += this.vy;
                    if (this.vy <= 5) {
                        ++this.vy;
                    }
                    if (this.y + this.vy <= this.yto) break;
                    this.isRemove = true;
                    LoadMap.timeVibrateScreen = 103;
                    int n = 16;
                    this.removeEff();
                    for (int i = 0; i < n; ++i) {
                        int n2 = 24 * posx[this.indexpost][i] + CRes.random(10, 100);
                        int n3 = 24 * posy[this.indexpost][i] + CRes.random(10, 100);
                        GameScreen.addEffectEndKill(36, n2, n3);
                        GameScreen.addEffectEndKill(9, n2, n3);
                    }
                    break;
                }
                case 0: {
                    this.updateAngleNormal();
                    break;
                }
                case 1: 
                case 22: 
                case 38: {
                    if (MainObject.getDistance(this.x, this.toX, this.y, this.toY) > 8 && this.f < this.fRemove) break;
                    if (this.typeEffect == 22) {
                        if (this.objBeKillMain != null) {
                            GameScreen.addEffectEndKill(7, this.toX, this.toY + this.objBeKillMain.hOne / 4);
                        }
                    } else if (this.typeEffect == 38) {
                        GameScreen.addEffectEndKill(2, this.toX, this.toY);
                    }
                    this.removeEff();
                    break;
                }
                case 109: 
                case 110: {
                    Point point;
                    if (this.VecEff.size() > 0 && (point = (Point)this.VecEff.elementAt(0)) != null) {
                        this.x = point.x;
                        this.y = point.y;
                        this.VecEff.removeElement(point);
                    }
                    if (this.VecEff.size() > 0) break;
                    this.removeEff();
                    if (this.typeEffect == 109) {
                        GameScreen.addEffectEndKill(15, this.toX, this.toY + this.objBeKillMain.hOne / 4);
                        break;
                    }
                    GameScreen.addEffectEndKill(29, this.x + 3, this.y + this.vy * this.f / 1000 + 10);
                    break;
                }
                case 59: {
                    if (this.f < this.fRemove + 6) break;
                    this.removeEff();
                    break;
                }
                case 31: {
                    if (this.f < this.fRemove && this.f > 1) {
                        Point point = new Point();
                        point.x = this.x - this.vx;
                        point.y = this.y - this.vy;
                        this.VecEff.addElement(point);
                    } else if (this.VecEff.size() == 0 && this.f >= this.fRemove) {
                        this.removeEff();
                    }
                    if (this.f == this.fRemove && this.objBeKillMain != null) {
                        GameScreen.addEffectEndKill(36, this.toX, this.toY + this.objBeKillMain.hOne / 4);
                        GameScreen.addEffectEndKill(14, this.toX, this.toY + this.objBeKillMain.hOne / 4);
                    }
                    for (int i = 0; i < this.VecEff.size(); ++i) {
                        Point point = (Point)this.VecEff.elementAt(i);
                        ++point.f;
                        if (point.f / 2 <= 3) continue;
                        this.VecEff.removeElement(point);
                        --i;
                    }
                    break;
                }
                case 6: {
                    if (this.f < this.fRemove) break;
                    this.removeEff();
                    break;
                }
                case 111: {
                    if (this.f < this.fRemove) break;
                    this.removeEff();
                    break;
                }
                case 10: {
                    if (this.f < this.fRemove) break;
                    this.removeEff();
                    break;
                }
                case 82: {
                    if (this.f < this.fRemove) break;
                    this.removeEff();
                    break;
                }
                case 11: 
                case 86: 
                case 108: 
                case 114: {
                    Point point;
                    int n;
                    for (n = 0; n < this.VecEff.size(); ++n) {
                        point = (Point)this.VecEff.elementAt(n);
                        if (this.f == point.f) {
                            if (this.typeEffect == 11) {
                                LoadMap.timeVibrateScreen = 103;
                            }
                            if (this.typeEffect != 114) {
                                GameScreen.addEffectEndKill(0, point.x, point.y + point.vy * this.f / 1000 - 5);
                                GameScreen.addEffectEndKill(9, point.x, point.y + point.vy * this.f / 1000 + 5);
                                GameScreen.addEffectEndKill(26, point.x, point.y + point.vy * this.f / 1000 + 10);
                                continue;
                            }
                            GameScreen.addEffectEndKill(49, point.x, point.y + point.vy * this.f / 1000 + 10);
                            GameScreen.addEffectEndKill(28, point.x, point.y + point.vy * this.f / 1000 + 10);
                            continue;
                        }
                        if (this.f >= point.f) continue;
                        point.x += point.vx;
                        bl = false;
                        if (CRes.random(2) != 0) continue;
                        Point point2 = new Point();
                        point2.x = point.x - point.vx;
                        point2.y = point.y + point.vy * (this.f - 1) / 1000;
                        this.VecSubEff.addElement(point2);
                    }
                    for (n = 0; n < this.VecSubEff.size(); ++n) {
                        point = (Point)this.VecSubEff.elementAt(n);
                        ++point.f;
                        if (point.f / 2 <= 3 || this.test != 0) continue;
                        this.VecSubEff.removeElement(point);
                        --n;
                    }
                    if (!bl) break;
                    this.removeEff();
                    break;
                }
                case 12: {
                    if (this.f < this.fRemove) break;
                    this.removeEff();
                    break;
                }
                case 14: 
                case 81: 
                case 115: {
                    for (int i = 0; i < this.VecEff.size(); ++i) {
                        Point point = (Point)this.VecEff.elementAt(i);
                        if (point.f == 0) {
                            int n;
                            bl = false;
                            if (this.f >= this.fRemove) {
                                point.f = 1;
                                continue;
                            }
                            point.x += point.vx;
                            point.y += point.vy;
                            if (this.typeEffect != 14 && this.typeEffect != 115 && this.f <= 6 || (n = GameCanvas.loadmap.getTile(point.x / 1000, point.y / 1000)) != -1 && n != 1 && MainEffect.isInScreen(point.x / 1000, point.y / 1000, this.fraImgEff.frameWidth, this.fraImgEff.frameHeight)) continue;
                            point.f = 1;
                            continue;
                        }
                        if (point.f <= 0) continue;
                        ++point.f;
                        if ((point.f - 1) / 2 >= this.fraImgSubEff.nFrame) {
                            point.f = -1;
                            this.VecEff.removeElement(point);
                        }
                        bl = false;
                    }
                    if (!bl) break;
                    this.removeEff();
                    break;
                }
                case 20: 
                case 113: {
                    for (int i = 0; i < this.VecEff.size(); ++i) {
                        Point point = (Point)this.VecEff.elementAt(i);
                        if (point.dis == 0) {
                            point.update();
                            if (point.f < point.fRe) continue;
                            point.dis = 1;
                            point.f = 0;
                            if (GameCanvas.lowGraphic || CRes.random(4) != 0) continue;
                            GameScreen.addEffectEndKill(this.typeEffect == 20 ? 24 : 10, point.x, point.y);
                            continue;
                        }
                        if (point.dis != 1) continue;
                        ++point.f;
                        if (point.f < 2 || this.test != 0) continue;
                        this.VecEff.removeElement(point);
                        --i;
                    }
                    if (this.f < this.fRemove) {
                        if (GameCanvas.lowGraphic && GameCanvas.gameTick % 2 != 0) break;
                        this.y1000 = MainScreen.cameraMain.yCam - CRes.random(10, 20);
                        this.create_Arrow_Rain();
                        break;
                    }
                    if (this.VecEff.size() != 0) break;
                    this.removeEff();
                    break;
                }
                case 21: {
                    if (this.f < this.fRemove) break;
                    this.removeEff();
                    break;
                }
                case 26: {
                    for (int i = 0; i < this.VecEff.size(); ++i) {
                        Point point = (Point)this.VecEff.elementAt(i);
                        ++point.f;
                        if (point.f < 7) continue;
                        this.VecEff.removeElement(point);
                        --i;
                    }
                    if (this.f <= this.fRemove) {
                        Point point = new Point();
                        point.x = this.x;
                        point.y = this.y;
                        this.VecEff.addElement(point);
                        break;
                    }
                    if (this.f == this.fRemove + 1 && this.typeEffect == 26) {
                        LoadMap.timeVibrateScreen = 103;
                    }
                    if (this.VecEff.size() != 0 || this.f <= this.fRemove + 5) break;
                    this.removeEff();
                    break;
                }
                case 27: {
                    int n;
                    for (n = 0; n < this.VecEff.size(); ++n) {
                        Point point = (Point)this.VecEff.elementAt(n);
                        ++point.f;
                        if (point.f < 3) continue;
                        this.VecEff.removeElement(point);
                        --n;
                    }
                    if (this.f < this.fRemove) {
                        if (this.f == 0) {
                            GameScreen.addEffectEndKill(6, this.x, this.y);
                            this.setSendMove(this.x, this.toX, this.y, this.toY);
                        }
                        Point point = new Point();
                        point.x = this.x;
                        point.y = this.y - 10;
                        this.VecEff.addElement(point);
                    } else if (this.objKill != null) {
                        if (this.f == this.fRemove) {
                            if (this.objKill.Action != 4 && this.objKill.Action != 3) {
                                this.objKill.Action = 2;
                                this.objKill.fplash = 6;
                                this.objKill.frame = 5;
                            }
                        } else if (this.f == this.fRemove + 5 && this.objKill.Action == 2) {
                            this.f = 0;
                            this.objKill.Action = 0;
                        }
                        if (this.objKill.isTanHinh) {
                            this.objKill.isTanHinh = false;
                        }
                        if (this.VecEff.size() == 0) {
                            if (this.objKill.Action == 2) {
                                this.f = 0;
                                this.objKill.Action = 0;
                            }
                            this.removeEff();
                            n = GameCanvas.loadmap.getTile(this.objKill.x, this.objKill.y);
                            this.objKill.isWater = n == 2;
                        }
                    }
                    if (this.f != this.timeAddNum || this.objBeKillMain == null) break;
                    GameScreen.addEffectEndKill(5, this.objBeKillMain.x, this.objBeKillMain.y - this.objBeKillMain.hOne / 2);
                    break;
                }
                case 28: {
                    if (this.f >= this.fRemove) {
                        this.removeEff();
                    }
                    if (this.f != 0) break;
                    GameScreen.addEffectEndKill(9, this.toX, this.toY);
                    if (!GameScreen.isWater(this.toX, this.toY)) break;
                    GameScreen.addEffectEndKill(1, this.toX, this.toY);
                    break;
                }
                case 23: {
                    int n;
                    for (n = 0; n < this.VecEff.size(); ++n) {
                        Point point = (Point)this.VecEff.elementAt(n);
                        point.update();
                        if (point.f < point.fRe) continue;
                        this.VecEff.removeElement(point);
                        --n;
                    }
                    if (this.f >= this.fRemove && this.VecEff.size() == 0) {
                        if (this.objBeKillMain != null) {
                            GameScreen.addEffectEndKill(12, this.toX, this.toY + this.objBeKillMain.hOne / 4);
                        }
                        this.removeEff();
                    }
                    if (this.f != 0) break;
                    n = this.x;
                    int n4 = this.y;
                    switch (this.Direction) {
                        case 1: {
                            n4 -= 20;
                            break;
                        }
                        case 0: {
                            n4 += 20;
                            break;
                        }
                        case 2: {
                            n -= 20;
                            break;
                        }
                        case 3: {
                            n += 20;
                            break;
                        }
                    }
                    GameScreen.addEffectEndKill(13, n, n4);
                    break;
                }
                case 25: {
                    if (this.f == this.fRemove && this.objBeKillMain != null) {
                        if (this.typeEffect == 25) {
                            GameScreen.addEffectEndKill(10, this.toX, this.toY);
                            GameScreen.addEffectEndKill(15, this.toX, this.toY + this.objBeKillMain.hOne / 4);
                        } else {
                            GameScreen.addEffectEndKill(4, this.toX, this.toY);
                            GameScreen.addEffectEndKill(14, this.toX, this.toY + this.objBeKillMain.hOne / 4);
                        }
                    }
                    if (this.f < this.fRemove) {
                        Point point = new Point();
                        point.x = this.x;
                        point.y = this.y;
                        this.VecEff.addElement(point);
                    } else if (this.VecEff.size() == 0 && this.f >= this.fRemove) {
                        this.removeEff();
                    }
                    for (int i = 0; i < this.VecEff.size(); ++i) {
                        Point point = (Point)this.VecEff.elementAt(i);
                        ++point.f;
                        if (point.f <= 3) continue;
                        this.VecEff.removeElement(point);
                        ++point.f;
                        --i;
                    }
                    break;
                }
                case 29: 
                case 84: {
                    for (int i = 0; i < this.VecEff.size(); ++i) {
                        Line line = (Line)this.VecEff.elementAt(i);
                        line.update();
                        if (this.f < this.fRemove || this.test != 0) continue;
                        this.VecEff.removeElement(line);
                        --i;
                    }
                    if (this.f < this.fRemove || this.test != 0) break;
                    if (GameCanvas.timeNow - this.time >= (long)this.timeRemove) {
                        if (this.test == 0) {
                            this.VecEff.removeAllElements();
                        }
                        this.removeEff();
                        break;
                    }
                    this.fRemove = CRes.random(4, 6);
                    this.f = 0;
                    this.create_Star_Line_In(this.vMax, this.xline, this.yline);
                    break;
                }
                case 30: {
                    for (int i = 0; i < this.VecEff.size(); ++i) {
                        Point point = (Point)this.VecEff.elementAt(i);
                        if (point.f < 5) {
                            point.update();
                            point.vx += point.vx / 5;
                            point.vy += point.vy / 5;
                            continue;
                        }
                        if (this.test != 0) continue;
                        this.VecEff.removeElement(point);
                        --i;
                    }
                    if (this.VecEff.size() != 0) break;
                    if (GameCanvas.timeNow - this.time > (long)this.timeRemove) {
                        this.removeEff();
                        break;
                    }
                    this.create_Star_Point_In();
                    break;
                }
                case 34: {
                    Object object;
                    for (int i = 0; i < this.VecEff.size(); ++i) {
                        object = (Point)this.VecEff.elementAt(i);
                        ((Point)object).update();
                        if (((Point)object).f < 3) continue;
                        this.VecEff.removeElement(object);
                        --i;
                    }
                    if (this.f == 3 && this.objBeKillMain != null) {
                        this.create_2Kiem_Lv4(180, this.objBeKillMain);
                    }
                    if (this.f == 6) {
                        if (this.objKill != null) {
                            this.indexSound = 9;
                            if (this.objKill == GameScreen.player) {
                                mSound.playSound(this.indexSound, mSound.volumeSound);
                            } else {
                                GameScreen.addSoundEff(this.indexSound);
                            }
                        }
                        if (this.indexLan >= this.vecObjBeKill.size()) {
                            this.f = 56;
                        } else {
                            MainObject mainObject = null;
                            if (this.indexLan < this.vecObjBeKill.size()) {
                                this.f = 0;
                                do {
                                    object = (Object_Effect_Skill)this.vecObjBeKill.elementAt(this.indexLan);
                                    mainObject = MainObject.get_Item_Object(((Object_Effect_Skill)object).ID, ((Object_Effect_Skill)object).tem);
                                    if (mainObject != null) {
                                        if (mainObject.Action == 4) {
                                            mainObject = null;
                                        } else {
                                            this.objBeKillMain = mainObject;
                                            if (this.objBeKillMain != null) {
                                                this.create_2Kiem_Lv4(0, this.objBeKillMain);
                                            }
                                        }
                                    }
                                    ++this.indexLan;
                                } while (mainObject == null && this.indexLan < this.vecObjBeKill.size());
                            }
                            if (this.indexLan >= this.vecObjBeKill.size() && mainObject == null) {
                                this.f = 56;
                            }
                        }
                    }
                    if (this.f == this.fRemove - 1 && this.objKill != null) {
                        switch (this.objKill.Direction) {
                            case 1: {
                                this.objKill.Direction = 0;
                                break;
                            }
                            case 0: {
                                this.objKill.Direction = 1;
                                break;
                            }
                            case 2: {
                                this.objKill.Direction = 3;
                                break;
                            }
                            case 3: {
                                this.objKill.Direction = 2;
                            }
                        }
                        if (this.objKill.Action != 4 && this.objKill.Action != 3) {
                            this.objKill.Action = 2;
                            this.objKill.fplash = 6;
                            this.objKill.frame = 5;
                            this.objKill.weapon_frame = 6;
                        }
                        this.objKill.isTanHinh = false;
                    }
                    if (this.f < this.fRemove || this.VecEff.size() != 0) break;
                    this.removeEff();
                    if (this.objKill == null || this.objKill.Action != 2) break;
                    this.f = 0;
                    this.objKill.Action = 0;
                    if (!this.objKill.isMainChar()) break;
                    GameScreen.player.x = GameScreen.player.lastX;
                    GameScreen.player.y = GameScreen.player.lastY;
                    GameScreen.player.resetAction();
                    GlobalService.gI().player_move((short)GameScreen.player.x, (short)GameScreen.player.y);
                    break;
                }
                case 40: {
                    Point point;
                    int n;
                    if (this.f <= 4) {
                        if (this.objBeKillMain != null && (this.toX != this.objBeKillMain.x || this.toY != this.objBeKillMain.y - this.objBeKillMain.hOne / 2) && this.objBeKillMain.Action != 4) {
                            for (n = 0; n < this.VecEff.size(); ++n) {
                                point = (Point)this.VecEff.elementAt(n);
                                this.toX = this.objBeKillMain.x;
                                this.toY = this.objBeKillMain.y - this.objBeKillMain.hOne / 2;
                                point.x = this.toX * 1000;
                                point.y = this.toY * 1000;
                                point.g = CRes.fixangle(point.g + 5);
                                point.vx = CRes.sin(CRes.fixangle(point.g)) * this.lT_Arc;
                                point.vy = CRes.cos(CRes.fixangle(point.g)) * this.lT_Arc;
                                int n5 = CRes.angle(-point.vx, -point.vy);
                                point.frame = this.setFrameAngle(n5);
                            }
                        }
                    } else {
                        for (n = 0; n < this.VecEff.size(); ++n) {
                            point = (Point)this.VecEff.elementAt(n);
                            if (this.f % 3 == 0) {
                                point.vx /= 2;
                                point.vy /= 2;
                            }
                            if (this.f <= 7) continue;
                            this.VecEff.removeAllElements();
                            GameScreen.addEffectEndKill(6, this.toX, this.toY);
                        }
                    }
                    for (n = 0; n < this.VecSubEff.size(); ++n) {
                        point = (Point)this.VecSubEff.elementAt(n);
                        point.update();
                        if (this.f <= 16) continue;
                        this.VecSubEff.removeAllElements();
                        this.removeEff();
                    }
                    break;
                }
                case 41: {
                    this.updateAngleXP();
                    for (int i = 0; i < this.VecEff.size(); ++i) {
                        Point point = (Point)this.VecEff.elementAt(i);
                        ++point.f;
                        if (point.f <= 4) continue;
                        this.VecEff.removeElement(point);
                        --i;
                    }
                    if (this.f < this.fRemove || this.VecEff.size() != 0) break;
                    this.removeEff();
                    break;
                }
                case 42: {
                    if (this.objKill != null) {
                        this.x = this.objKill.x;
                        this.y = this.objKill.y;
                    }
                    if (this.f == 0 && this.objKill != null) {
                        GameScreen.addEffectKill(43, this.objKill.ID, this.objKill.typeObject, this.vecObjBeKill);
                    }
                    if (this.f >= this.fRemove) {
                        this.removeEff();
                    }
                    if (this.f == this.fRemove / 2) {
                        GameScreen.addEffectEndKill(21, this.x, this.y);
                    }
                    if (this.f >= this.fRemove / 2) break;
                    for (int i = 0; i < this.VecEff.size(); ++i) {
                        Point point = (Point)this.VecEff.elementAt(i);
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
                case 43: {
                    if (this.objKill != null) {
                        this.x = this.objKill.x;
                        this.y = this.objKill.y;
                    }
                    if (this.f >= this.fRemove) {
                        this.removeEff();
                    }
                    for (int i = 0; i < this.VecEff.size(); ++i) {
                        Point point = (Point)this.VecEff.elementAt(i);
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
                case 46: {
                    if (this.objBeKillMain != null) {
                        if ((CRes.abs(this.toX - this.objBeKillMain.x) > 2 || CRes.abs(this.toY - this.objBeKillMain.y) > 2) && CRes.random(3) == 0) {
                            GameScreen.addEffectEndKill(24, this.toX, this.toY - 5);
                        }
                        this.toX = this.objBeKillMain.x;
                        this.toY = this.objBeKillMain.y;
                    }
                    if (this.f < this.fRemove) break;
                    this.removeEff();
                    break;
                }
                case 47: 
                case 112: {
                    for (int i = 0; i < this.VecEff.size(); ++i) {
                        Point point = (Point)this.VecEff.elementAt(i);
                        if (this.f != point.fRe) continue;
                        GameScreen.addEffectEndKill(26, point.x / 1000, point.y / 1000 - 5);
                    }
                    if (this.f < this.fRemove) break;
                    this.removeEff();
                    break;
                }
                case 49: {
                    for (int i = 0; i < this.VecEff.size(); ++i) {
                        Point point = (Point)this.VecEff.elementAt(i);
                        ++point.f;
                        if (point.f < 5) continue;
                        this.VecEff.removeElement(point);
                        --i;
                    }
                    if (this.f < this.fRemove) {
                        Point point = new Point();
                        point.x = this.x;
                        point.y = this.y;
                        this.VecEff.addElement(point);
                        break;
                    }
                    if (this.VecEff.size() != 0 || this.f < this.fRemove + 6) break;
                    this.removeEff();
                    break;
                }
                case 50: {
                    if (this.f >= 6) {
                        this.removeEff();
                    }
                    if (this.f == -1 || this.f >= 6) {
                        return;
                    }
                    if (this.objKill == null) break;
                    if (this.f < 3) {
                        this.objKill.x += this.x1000;
                        this.objKill.y += this.y1000;
                        break;
                    }
                    if (this.f >= 6) break;
                    this.objKill.x -= this.x1000;
                    this.objKill.y -= this.y1000;
                    break;
                }
                case 125: 
                case 126: {
                    int n;
                    this.x1000 = CRes.cos(this.angle) * this.R / 1024 + this.x;
                    this.y1000 = CRes.sin(this.angle) * this.R / 1024 + this.y;
                    Point point = new Point();
                    point.x = this.x1000;
                    point.y = this.y1000;
                    this.VecEff.addElement(point);
                    this.angle += 20;
                    if (this.angle >= 359) {
                        --this.size1;
                        this.angle = 359;
                    }
                    if (this.size1 <= 0) {
                        this.removeEff();
                        for (n = 0; n < this.vecObjBeKill.size(); ++n) {
                            Object_Effect_Skill object_Effect_Skill = (Object_Effect_Skill)this.vecObjBeKill.elementAt(n);
                            if (object_Effect_Skill == null) continue;
                            MainObject mainObject = MainObject.get_Item_Object(object_Effect_Skill.ID, object_Effect_Skill.tem);
                            GameScreen.addEffectEndKill(36, mainObject.x, mainObject.y);
                            GameScreen.addEffectEndKill(9, mainObject.x, mainObject.y);
                        }
                    }
                    n = 2;
                    if (this.objBeKillMain != null && this.objKill != null) {
                        n = this.objKill.Direction = EffectSkill.setDirection(this.objKill, this.objBeKillMain);
                    }
                    if (n == 2 || n == 3) {
                        this.vY1000 = 1;
                        this.vX1000 = 0;
                    } else {
                        this.vY1000 = 0;
                        this.vX1000 = 1;
                    }
                    int n6 = 320;
                    if (n6 < GameCanvas.h) {
                        n6 = GameCanvas.h;
                    }
                    if (n6 < GameCanvas.w) {
                        n6 = GameCanvas.w;
                    }
                    this.R = this.angle > 60 && this.angle < 120 ? this.y + CRes.sin(60) * n6 / 1000 : this.x + CRes.cos(0) * n6 / 1000;
                    for (int i = 0; i < this.VecEff.size(); ++i) {
                        Point point3 = (Point)this.VecEff.elementAt(i);
                        ++point3.f;
                        if (point3.f < 4) continue;
                        this.VecEff.removeElement(point3);
                        --i;
                    }
                    if (this.angle > 0 && this.angle < 90) {
                        this.objKill.Direction = 3;
                        break;
                    }
                    this.objKill.Direction = 2;
                    break;
                }
                case 106: {
                    this.x1000 = CRes.cos(this.angle) * this.R / 1024 + this.x;
                    this.y1000 = CRes.sin(this.angle) * this.R / 1024 + this.y;
                    Point point = new Point();
                    point.x = this.x1000;
                    point.y = this.y1000;
                    this.VecEff.addElement(point);
                    this.angle += 20;
                    if (this.angle >= 180) {
                        --this.size1;
                        this.angle = 180;
                    }
                    if (this.size1 <= 0) {
                        this.removeEff();
                    }
                    int n = 2;
                    if (this.objBeKillMain != null && this.objKill != null) {
                        n = this.objKill.Direction = EffectSkill.setDirection(this.objKill, this.objBeKillMain);
                    }
                    if (n == 2 || n == 3) {
                        this.vY1000 = 1;
                        this.vX1000 = 0;
                    } else {
                        this.vY1000 = 0;
                        this.vX1000 = 1;
                    }
                    int n7 = 320;
                    if (n7 < GameCanvas.h) {
                        n7 = GameCanvas.h;
                    }
                    if (n7 < GameCanvas.w) {
                        n7 = GameCanvas.w;
                    }
                    this.R = this.angle > 60 && this.angle < 120 ? this.y + CRes.sin(60) * n7 / 1000 : this.x + CRes.cos(0) * n7 / 1000;
                    for (int i = 0; i < this.VecEff.size(); ++i) {
                        Point point4 = (Point)this.VecEff.elementAt(i);
                        ++point4.f;
                        if (point4.f < 4) continue;
                        this.VecEff.removeElement(point4);
                        --i;
                    }
                    if (this.angle > 0 && this.angle < 90) {
                        this.objKill.Direction = 3;
                        break;
                    }
                    this.objKill.Direction = 2;
                    break;
                }
                case 51: 
                case 105: {
                    if (this.f >= this.fRemove && this.VecEff.size() == 0) {
                        this.removeEff();
                    }
                    if (this.f < this.fRemove) {
                        if (this.f == 8) {
                            int n = 2;
                            if (this.objBeKillMain != null && this.objKill != null) {
                                n = this.objKill.Direction = EffectSkill.setDirection(this.objKill, this.objBeKillMain);
                            }
                            if (n == 2 || n == 3) {
                                this.vY1000 = 1;
                                this.vX1000 = 0;
                            } else {
                                this.vY1000 = 0;
                                this.vX1000 = 1;
                            }
                            if (this.objBeKillMain != null && this.objKill != null) {
                                int n8 = CRes.angle(this.objBeKillMain.x - this.objKill.x, this.objBeKillMain.y - this.objKill.y);
                                int n9 = 320;
                                if (n9 < GameCanvas.h) {
                                    n9 = GameCanvas.h;
                                }
                                if (n9 < GameCanvas.w) {
                                    n9 = GameCanvas.w;
                                }
                                this.x1000 = this.x + CRes.cos(n8) * n9 / 1000;
                                this.y1000 = this.y + CRes.sin(n8) * n9 / 1000;
                                this.toX = this.objBeKillMain.x;
                                this.toY = this.objBeKillMain.y - this.objBeKillMain.hOne / 2;
                            }
                        }
                        if (this.f > 8 && this.f % 3 == 2) {
                            Point point = new Point();
                            point.x = this.toX + CRes.random_Am_0(5);
                            point.y = this.toY + CRes.random_Am_0(5);
                            this.VecEff.addElement(point);
                        }
                    }
                    for (int i = 0; i < this.VecEff.size(); ++i) {
                        Point point = (Point)this.VecEff.elementAt(i);
                        ++point.f;
                        if (point.f < 4) continue;
                        this.VecEff.removeElement(point);
                        --i;
                    }
                    break;
                }
                case 52: 
                case 54: {
                    if (this.typeEffect == 54) {
                        if (this.f == 0) {
                            GameScreen.addEffectEndKill(3, this.toX, this.toY - 10);
                        }
                    } else if (this.typeEffect == 52 && this.f == 0) {
                        GameScreen.addEffectEndKill(14, this.toX, this.toY);
                    }
                    if (this.f < this.fRemove && this.objBeKillMain != null) {
                        if ((CRes.abs(this.toX - this.objBeKillMain.x) > 2 || CRes.abs(this.toY - this.objBeKillMain.y) > 2) && CRes.random(3) == 0) {
                            Point point = new Point();
                            point.x = this.toX;
                            point.y = this.toY - 5;
                            this.VecEff.addElement(point);
                        }
                        this.toX = this.objBeKillMain.x;
                        this.toY = this.objBeKillMain.y;
                    }
                    if (this.f >= this.fRemove && this.VecEff.size() == 0) {
                        this.removeEff();
                    }
                    for (int i = 0; i < this.VecEff.size(); ++i) {
                        Point point = (Point)this.VecEff.elementAt(i);
                        ++point.f;
                        if (point.f / 2 < 4) continue;
                        this.VecEff.removeElement(point);
                        --i;
                    }
                    break;
                }
                case 53: 
                case 64: 
                case 71: 
                case 75: 
                case 78: 
                case 104: {
                    for (int i = 0; i < this.VecEff.size(); ++i) {
                        Point point = (Point)this.VecEff.elementAt(i);
                        if (this.f == 1) {
                            LoadMap.timeVibrateScreen = 103;
                            if (this.typeEffect == 75) {
                                GameScreen.addEffectEndKill(15, point.x, point.y);
                            } else {
                                GameScreen.addEffectEndKill(9, point.x, point.y);
                            }
                            if (this.typeEffect == 64) {
                                GameScreen.addEffectEndKill(15, point.x, point.y);
                                GameScreen.addEffectEndKill(28, point.x, point.y - 2);
                            } else if (this.typeEffect == 53 || this.typeEffect == 78 || this.typeEffect == 104) {
                                GameScreen.addEffectEndKill(25, point.x, point.y - 2);
                            }
                        }
                        ++point.f;
                    }
                    if (this.f < this.fRemove) break;
                    this.removeEff();
                    break;
                }
                case 55: {
                    int n;
                    for (n = 0; n < this.VecEff.size(); ++n) {
                        Line line = (Line)this.VecEff.elementAt(n);
                        line.update();
                        if (line.f <= line.fRe) continue;
                        this.VecEff.removeElement(line);
                        --n;
                    }
                    if (this.f >= this.fRemove) {
                        if (this.VecEff.size() != 0) break;
                        this.removeEff();
                        break;
                    }
                    n = 20;
                    if (this.objBeKillMain != null && !this.objBeKillMain.isRemove && !this.objBeKillMain.isStop && this.objBeKillMain.Action != 4) {
                        this.toX = this.objBeKillMain.x;
                        this.toY = this.objBeKillMain.y - this.objBeKillMain.hOne / 2;
                        n = this.objBeKillMain.hOne / 2;
                    }
                    int n10 = CRes.random(2, 4);
                    for (int i = 0; i < n10; ++i) {
                        int n11 = CRes.random(10, 24);
                        int n12 = CRes.random_Am_0(15);
                        int n13 = CRes.random_Am(6, 12);
                        Line line = new Line();
                        if (this.Direction == 2 || this.Direction == 3) {
                            line.x0 = this.toX + n12;
                            line.x1 = line.x0 + (n12 > 0 ? n11 : -n11);
                            line.y0 = line.y1 = this.toY + CRes.random_Am_0(n + 10);
                            line.vx = n13;
                        } else {
                            line.y0 = this.toY + n12;
                            line.y1 = line.y0 + (n12 > 0 ? n11 : -n11);
                            line.x0 = line.x1 = this.toX + CRes.random_Am_0(n + 10);
                            line.vy = n13;
                        }
                        line.fRe = CRes.random(2, 5);
                        line.is2Line = CRes.random(5) == 0;
                        line.idColor = CRes.random(3);
                        this.VecEff.addElement(line);
                    }
                    break;
                }
                case 56: {
                    if (this.f < this.fRemove) break;
                    this.removeEff();
                    break;
                }
                case 57: 
                case 70: {
                    if (this.objKill != null && this.objKill.Action != 4) {
                        this.objKill.Action = 2;
                        this.objKill.vx = 0;
                        this.objKill.vy = 0;
                    }
                    for (int i = 0; i < this.VecEff.size(); ++i) {
                        Line line = (Line)this.VecEff.elementAt(i);
                        line.update();
                        line.update();
                        line.vx += line.vx / 5;
                        line.vy += line.vy / 5;
                        if (line.f < 6 || this.test != 0) continue;
                        this.VecEff.removeAllElements();
                        --i;
                    }
                    if (this.VecEff.size() != 0) break;
                    if (GameCanvas.timeNow - this.time > (long)this.timeRemove) {
                        this.removeEff();
                        break;
                    }
                    if (this.typeEffect == 57) {
                        this.create_Buff_Point_In();
                        break;
                    }
                    if (this.typeEffect != 70) break;
                    this.create_Mon_Buff();
                    break;
                }
                case 58: {
                    int n;
                    if (this.f < this.fRemove) {
                        if (this.f % 3 == 1) {
                            n = MainScreen.cameraMain.yCam + (this.y - MainScreen.cameraMain.yCam) % 30 - 30;
                            for (int i = 0; i < 2; ++i) {
                                Point point = new Point();
                                point.x = this.x;
                                point.y = n + i * 3;
                                point.vy = 30;
                                this.VecEff.addElement(point);
                            }
                        }
                        if (this.f >= this.fRemove - 3 && this.objKill != null) {
                            this.objKill.isTanHinh = !this.objKill.isTanHinh;
                        }
                    } else if (this.objKill != null && this.objKill.isTanHinh) {
                        this.objKill.isTanHinh = false;
                    }
                    for (n = 0; n < this.VecEff.size(); ++n) {
                        Point point = (Point)this.VecEff.elementAt(n);
                        point.update();
                        if (point.y <= this.y) continue;
                        this.VecEff.removeElement(point);
                        --n;
                    }
                    if (this.f < this.fRemove || this.VecEff.size() != 0) break;
                    if (this.objKill != null) {
                        this.objKill.isTanHinh = false;
                    }
                    this.removeEff();
                    break;
                }
                case 60: {
                    if (this.f == 0) {
                        for (int i = 0; i < this.VecEff.size(); ++i) {
                            Point point = (Point)this.VecEff.elementAt(i);
                            GameScreen.addEffectEndKill(9, point.x, point.y);
                            GameScreen.addEffectEndKill(25, point.x, point.y - 2);
                        }
                    }
                    if (this.f < this.fRemove) break;
                    this.removeEff();
                    break;
                }
                case 61: 
                case 77: {
                    for (int i = 0; i < this.VecEff.size(); ++i) {
                        Point point = (Point)this.VecEff.elementAt(i);
                        for (int j = 0; j < point.vecEffPoint.size(); ++j) {
                            Point point5 = (Point)point.vecEffPoint.elementAt(j);
                            point5.x += point.vx;
                        }
                        if (this.f == point.f) {
                            LoadMap.timeVibrateScreen = 105;
                            if (this.objKill != null) {
                                if (this.objKill == GameScreen.player) {
                                    mSound.playSound(32, mSound.volumeSound);
                                } else {
                                    GameScreen.addSoundEff(32);
                                }
                            }
                            GameScreen.addEffectEndKill(40, point.x, point.y + point.vy * this.f / 1000 - 4 + 10);
                            if (this.typeEffect == 61) {
                                GameScreen.addEffectEndKill(27, point.x + 3, point.y + point.vy * this.f / 1000 + 10);
                                GameScreen.addEffectEndKill(15, point.x, point.y + point.vy * this.f / 1000);
                                GameScreen.addEffectEndKill(0, point.x, point.y + point.vy * this.f / 1000);
                                continue;
                            }
                            if (this.typeEffect != 77) continue;
                            mSound.playSound(32, mSound.volumeSound);
                            GameScreen.addEffectEndKill(30, point.x + 3, point.y + point.vy * this.f / 1000 + 10);
                            GameScreen.addEffectEndKill(14, point.x, point.y + point.vy * this.f / 1000);
                            GameScreen.addEffectEndKill(0, point.x, point.y + point.vy * this.f / 1000);
                            continue;
                        }
                        if (this.f >= point.f) continue;
                        point.x += point.vx;
                        bl = false;
                    }
                    if (!bl) break;
                    this.removeEff();
                    break;
                }
                case 62: {
                    int n;
                    for (n = 0; n < this.VecEff.size(); ++n) {
                        Point point = (Point)this.VecEff.elementAt(n);
                        point.update();
                        if (point.f < point.fRe) continue;
                        this.VecEff.removeElement(point);
                        --n;
                    }
                    if (this.f >= this.fRemove && this.VecEff.size() == 0) {
                        this.removeEff();
                    }
                    if (this.f % 3 == 0 && this.f < this.fRemove) {
                        n = this.x;
                        int n14 = this.y;
                        switch (this.Direction) {
                            case 1: {
                                n14 -= 20;
                                break;
                            }
                            case 0: {
                                n14 += 20;
                                break;
                            }
                            case 2: {
                                n -= 20;
                                break;
                            }
                            case 3: {
                                n += 20;
                                break;
                            }
                        }
                        GameScreen.addEffectEndKill(13, n, n14);
                        this.create_Sung_BaoDan();
                    }
                    if (this.f % 5 != 4 || this.f >= this.fRemove || this.objBeKillMain == null || this.objBeKillMain.isStop || this.objBeKillMain.isRemove || MainObject.getDistance(this.objBeKillMain.x, this.objBeKillMain.y - this.objBeKillMain.hOne / 2, this.toX, this.toY) > 30) break;
                    GameScreen.addEffectEndKill(12, this.xEff, this.yEff + this.objBeKillMain.hOne / 4);
                    break;
                }
                case 63: {
                    int n;
                    for (n = 0; n < this.VecEff.size(); ++n) {
                        Point point = (Point)this.VecEff.elementAt(n);
                        point.update();
                        if (point.f < point.fRe) continue;
                        this.VecEff.removeElement(point);
                        --n;
                    }
                    if (this.f >= this.fRemove && this.VecEff.size() == 0) {
                        this.removeEff();
                    }
                    if (this.f < this.fRemove) {
                        if (this.objBeKillMain == null) {
                            if (this.f < this.fRemove) {
                                this.f = this.fRemove;
                            }
                        } else {
                            n = this.x;
                            int n15 = this.y;
                            switch (this.Direction) {
                                case 1: {
                                    n15 -= 20;
                                    break;
                                }
                                case 0: {
                                    n15 += 20;
                                    break;
                                }
                                case 2: {
                                    n -= 20;
                                    break;
                                }
                                case 3: {
                                    n += 20;
                                    break;
                                }
                            }
                            if (this.f % 3 == 0) {
                                GameScreen.addEffectEndKill(13, n, n15);
                            }
                            this.create_Sung_DAY_DAN(this.toX, this.toY);
                        }
                    }
                    if (this.f % 5 != 4 || this.f >= this.fRemove || this.objBeKillMain == null) break;
                    GameScreen.addEffectEndKill(7, this.toX, this.toY + this.objBeKillMain.hOne / 4);
                    break;
                }
                case 73: {
                    Object object;
                    int n;
                    for (n = 0; n < this.VecSubEff.size(); ++n) {
                        object = (Point)this.VecSubEff.elementAt(n);
                        ++((Point)object).f;
                        if (((Point)object).f / 2 <= 3) continue;
                        this.VecSubEff.removeElement(object);
                        --n;
                    }
                    for (n = 0; n < this.VecEff.size(); ++n) {
                        object = (Point)this.VecEff.elementAt(n);
                        ((Point)object).update();
                        if (((Point)object).f >= ((Point)object).fRe) {
                            GameScreen.addEffectEndKill(12, ((Point)object).x, ((Point)object).y + 10);
                            this.VecEff.removeElement(object);
                            --n;
                            continue;
                        }
                        if (((Point)object).f <= 1) continue;
                        Point point = new Point();
                        point.x = ((Point)object).x - ((Point)object).vx;
                        point.y = ((Point)object).y - ((Point)object).vy;
                        this.VecSubEff.addElement(point);
                    }
                    if (this.f >= this.fRemove) {
                        if (this.VecEff.size() != 0 || this.VecSubEff.size() != 0) break;
                        this.removeEff();
                        break;
                    }
                    if (this.f % 4 != 0 || this.indexLan >= this.vecObjBeKill.size()) break;
                    if (this.indexLan >= this.vecObjBeKill.size()) {
                        this.f = this.fRemove;
                        break;
                    }
                    MainObject mainObject = null;
                    if (this.indexLan < this.vecObjBeKill.size()) {
                        this.f = 0;
                        do {
                            if ((object = (Object_Effect_Skill)this.vecObjBeKill.elementAt(this.indexLan)) != null && (mainObject = MainObject.get_Item_Object(((Object_Effect_Skill)object).ID, ((Object_Effect_Skill)object).tem)) != null) {
                                if (mainObject.Action == 4) {
                                    mainObject = null;
                                } else {
                                    if (this.objKill != null) {
                                        this.objKill.Direction = this.Direction = EffectSkill.setDirection(this.objKill, mainObject);
                                        this.create_Sung_DAY_DAN(mainObject.x, mainObject.y - mainObject.hOne / 2);
                                        this.objKill.fplash = 11;
                                    }
                                    int n16 = this.x;
                                    int n17 = this.y;
                                    switch (this.Direction) {
                                        case 1: {
                                            n17 -= 20;
                                            break;
                                        }
                                        case 0: {
                                            n17 += 20;
                                            break;
                                        }
                                        case 2: {
                                            n16 -= 20;
                                            break;
                                        }
                                        case 3: {
                                            n16 += 20;
                                            break;
                                        }
                                    }
                                    GameScreen.addEffectEndKill(13, n16, n17);
                                }
                            }
                            ++this.indexLan;
                        } while (mainObject == null && this.indexLan < this.vecObjBeKill.size());
                    }
                    if (this.indexLan >= this.vecObjBeKill.size() && mainObject == null) {
                        this.f = this.fRemove;
                    }
                    break;
                }
                case 66: {
                    for (int i = 0; i < this.vecObjBeKill.size(); ++i) {
                        Object_Effect_Skill object_Effect_Skill = (Object_Effect_Skill)this.vecObjBeKill.elementAt(i);
                        MainObject mainObject = MainObject.get_Item_Object(object_Effect_Skill.ID, object_Effect_Skill.tem);
                        if (mainObject == null) continue;
                        if (this.objKill != null) {
                            GameScreen.addEffectKill(10, this.objKill.ID, this.objKill.typeObject, object_Effect_Skill.ID, object_Effect_Skill.tem, object_Effect_Skill.hpShow, object_Effect_Skill.hpLast);
                        }
                        GameScreen.addEffectEndKill(0, mainObject.x, mainObject.y + 5);
                    }
                    this.removeEff();
                    break;
                }
                case 67: {
                    if (this.f <= 7 || this.f < this.fRemove) break;
                    this.removeEff();
                    break;
                }
                case 68: {
                    if (this.f >= this.fRemove) {
                        this.removeEff();
                        break;
                    }
                    if (this.objBeKillMain == null || this.objBeKillMain.isStop || this.objBeKillMain.isRemove) break;
                    if (this.objBeKillMain.Action == 4) {
                        this.f = this.fRemove;
                        break;
                    }
                    this.x = this.objBeKillMain.x;
                    if (this.subType == 0) {
                        this.y = this.objBeKillMain.y - this.objBeKillMain.hOne / 2;
                        break;
                    }
                    this.y = this.objBeKillMain.y - this.objBeKillMain.hOne - 8;
                    break;
                }
                case 69: {
                    if (this.f < this.fRemove) break;
                    this.removeEff();
                    break;
                }
                case 72: 
                case 74: {
                    for (int i = 0; i < this.VecEff.size(); ++i) {
                        Point_Focus point_Focus = (Point_Focus)this.VecEff.elementAt(i);
                        point_Focus.update_Vx_Vy();
                        if (point_Focus.f < point_Focus.fRe) continue;
                        GameScreen.addEffectEndKill(12, point_Focus.x, point_Focus.y + 10);
                        this.VecEff.removeElement(point_Focus);
                        --i;
                    }
                    if (this.VecEff.size() != 0 || this.f < this.fRemove) break;
                    this.removeEff();
                    break;
                }
                case 76: {
                    for (int i = 0; i < this.VecEff.size(); ++i) {
                        Point point = (Point)this.VecEff.elementAt(i);
                        point.x += point.vx;
                        point.y += point.vy;
                        int n = GameCanvas.loadmap.getTile(point.x / 1000, point.y / 1000);
                        if (n != -1 && n != 1 && MainEffect.isInScreen(point.x / 1000, point.y / 1000, this.fraImgEff.frameWidth, this.fraImgEff.frameHeight)) continue;
                        this.VecEff.removeElement(point);
                        --i;
                    }
                    if (this.VecEff.size() != 0 && this.f < this.fRemove) break;
                    this.removeEff();
                    break;
                }
                case 79: {
                    if (this.f == 0) {
                        for (int i = 0; i < this.vecObjBeKill.size(); ++i) {
                            Object_Effect_Skill object_Effect_Skill = (Object_Effect_Skill)this.vecObjBeKill.elementAt(i);
                            if (object_Effect_Skill == null) {
                                this.vecObjBeKill.removeElement(object_Effect_Skill);
                                --i;
                                continue;
                            }
                            GameScreen.addEffectKill(12, this.objKill.ID, this.objKill.typeObject, object_Effect_Skill.ID, object_Effect_Skill.tem, object_Effect_Skill.hpShow, object_Effect_Skill.hpLast);
                        }
                    }
                    if (this.f < this.fRemove) break;
                    this.removeEff();
                    break;
                }
                case 80: {
                    if (this.objBeKillMain != null && !this.objBeKillMain.isStop && !this.objBeKillMain.isRemove) {
                        if (this.f < this.fRemove) {
                            if (this.f < 10) {
                                this.x = this.objBeKillMain.x;
                                this.y = this.objBeKillMain.y - 70;
                                break;
                            }
                            this.vy += 2;
                            if (this.f > 12) {
                                this.objBeKillMain.Action = 3;
                            }
                            this.objBeKillMain.vx = 0;
                            this.objBeKillMain.vy = 0;
                            break;
                        }
                        this.objBeKillMain.Action = 4;
                        this.objBeKillMain.hp = 0;
                        GameScreen.addEffectEndKill(11, this.objBeKillMain.x, this.objBeKillMain.y);
                        this.removeEff();
                        break;
                    }
                    this.removeEff();
                    break;
                }
                case 83: {
                    this.updateAngleHut_Mp_Hp();
                    for (int i = 0; i < this.VecEff.size(); ++i) {
                        Point point = (Point)this.VecEff.elementAt(i);
                        ++point.f;
                        if (point.f <= 4) continue;
                        this.VecEff.removeElement(point);
                        --i;
                    }
                    if (this.f < this.fRemove || this.VecEff.size() != 0) break;
                    this.removeEff();
                    break;
                }
                case 85: {
                    for (int i = 0; i < this.VecEff.size(); ++i) {
                        Line line = (Line)this.VecEff.elementAt(i);
                        line.update();
                        if (line.f < line.fRe) continue;
                        this.VecEff.removeElement(line);
                        --i;
                    }
                    if (this.objBeKillMain != null && !this.objBeKillMain.isRemove && this.objBeKillMain.Action != 4 && !this.objBeKillMain.isStop) {
                        this.x1000 = this.objBeKillMain.x;
                        this.y1000 = this.objBeKillMain.y;
                        if (this.timeRemove > 0 && GameCanvas.timeNow - this.time >= (long)this.timeRemove) {
                            this.removeEff();
                            break;
                        }
                        if (GameCanvas.gameTick % 2 != 0) break;
                        this.create_Line_NHANBAN_LV2();
                        break;
                    }
                    if (this.f <= 20) break;
                    this.removeEff();
                    break;
                }
                case 87: {
                    if (this.objBeKillMain != null && !this.objBeKillMain.isRemove && this.objBeKillMain.Action != 4 && !this.objBeKillMain.isStop) {
                        this.x = this.objBeKillMain.x;
                        this.y = this.objBeKillMain.y - this.objBeKillMain.hOne / 2;
                        if (this.timeRemove <= 0 || GameCanvas.timeNow - this.time < (long)this.timeRemove) break;
                        this.removeEff();
                        break;
                    }
                    if (this.f <= 20) break;
                    this.removeEff();
                    break;
                }
                case 88: {
                    if (this.f < this.fRemove) {
                        this.updateAngleXP();
                        this.frame = this.setFrameAngle(this.gocT_Arc);
                    }
                    if (this.VecEff.size() == 0 && this.f > this.fRemove) {
                        this.removeEff();
                    }
                    for (int i = 0; i < this.VecEff.size(); ++i) {
                        Point point = (Point)this.VecEff.elementAt(i);
                        ++point.f;
                        if (point.f / 2 <= 3) continue;
                        this.VecEff.removeElement(point);
                        --i;
                    }
                    if (this.f != this.fRemove) break;
                    GameScreen.addEffectEndKill(36, this.x, this.y - 15);
                    GameScreen.addEffectEndKill(14, this.x, this.y - 15);
                    break;
                }
                case 65: {
                    for (int i = 0; i < this.vecObjBeKill.size(); ++i) {
                        Object_Effect_Skill object_Effect_Skill = (Object_Effect_Skill)this.vecObjBeKill.elementAt(i);
                        MainObject mainObject = MainObject.get_Item_Object(object_Effect_Skill.ID, object_Effect_Skill.tem);
                        if (mainObject == null || this.objKill == null) continue;
                        GameScreen.addEffectKill(88, this.objKill.ID, this.objKill.typeObject, object_Effect_Skill.ID, object_Effect_Skill.tem, object_Effect_Skill.hpShow, object_Effect_Skill.hpLast);
                    }
                    this.removeEff();
                    break;
                }
                case 89: {
                    if (this.f < this.fRemove) {
                        this.updateAngleXP();
                        this.frame = this.setFrameAngle(this.gocT_Arc);
                    }
                    if (this.VecEff.size() == 0 && this.f > this.fRemove) {
                        this.isStop = true;
                        this.isRemove = true;
                    }
                    for (int i = 0; i < this.VecEff.size(); ++i) {
                        Point point = (Point)this.VecEff.elementAt(i);
                        ++point.f;
                        if (point.f / 2 <= 3) continue;
                        this.VecEff.removeElement(point);
                        --i;
                    }
                    break;
                }
                case 92: {
                    if (this.f < this.fRemove) {
                        this.updateAngleXP();
                        this.frame = this.setFrameAngle(this.gocT_Arc);
                    }
                    if (this.VecEff.size() == 0 && this.f > this.fRemove) {
                        this.isStop = true;
                        this.isRemove = true;
                    }
                    for (int i = 0; i < this.VecEff.size(); ++i) {
                        Point point = (Point)this.VecEff.elementAt(i);
                        ++point.f;
                        if (point.f / 2 <= 3) continue;
                        this.VecEff.removeElement(point);
                        --i;
                    }
                    break;
                }
                case 90: {
                    this.update_Nova_Effect();
                    break;
                }
                case 91: {
                    this.update_Nova_Effect();
                    break;
                }
                case 93: {
                    this.update_Boss_De1();
                    break;
                }
                case 102: {
                    if (this.objBeKillMain != null) {
                        this.x = this.objBeKillMain.x;
                        this.y = this.objBeKillMain.y - 25;
                    }
                    this.frStun = (byte)(this.frStun + 1);
                    if (this.frStun > this.frameStun.length - 1) {
                        this.frStun = 0;
                    }
                    if (this.objBeKillMain.isStun && this.objBeKillMain != null && (this.objBeKillMain == null || !this.objBeKillMain.isRemove)) break;
                    this.isRemove = true;
                    break;
                }
                case 103: {
                    if (this.objBeKillMain != null) {
                        this.x = this.objBeKillMain.x;
                        this.y = this.objBeKillMain.y;
                    }
                    if (this.timedelay >= 0) {
                        --this.timedelay;
                    }
                    if (this.timedelay <= 0) {
                        this.ysai = 10;
                        for (int i = 0; i < this.arr_R.length; ++i) {
                            int n = i;
                            this.arr_R[n] = (byte)(this.arr_R[n] + (byte)(this.r / 2));
                            int n18 = i;
                            this.arr_radian[n18] = this.arr_radian[n18] + 5;
                            if (this.arr_radian[i] < 360) continue;
                            this.arr_radian[i] = 0;
                        }
                        this.r /= 2;
                        if (this.r <= 0) {
                            this.r = 0;
                        }
                    }
                    if (this.objBeKillMain.isnoBoss84 && this.objBeKillMain != null && this.objBeKillMain.hp > 0 && (this.objBeKillMain == null || !this.objBeKillMain.isRemove)) break;
                    this.isRemove = true;
                    break;
                }
                case 107: {
                    for (int i = 0; i < this.VecEff.size(); ++i) {
                        Line line = (Line)this.VecEff.elementAt(i);
                        line.update();
                        if (this.f < this.fRemove || this.test != 0) continue;
                        this.VecEff.removeElement(line);
                        --i;
                    }
                    this.create_Star_Line_In(this.vMax, this.xline, this.yline);
                    if (MainObject.getDistance(this.objBeKillMain.x, this.objBeKillMain.y, GameScreen.player.x, GameScreen.player.y) <= 240) {
                        LoadMap.timeVibrateScreen = 3;
                    }
                    if (this.objBeKillMain.isno && this.objBeKillMain != null && this.objBeKillMain.hp > 0 && (this.objBeKillMain == null || !this.objBeKillMain.isRemove)) break;
                    this.isRemove = true;
                    break;
                }
                case 101: {
                    ++this.frSleep;
                    if (this.frSleep > this.frameSleep.length - 1) {
                        this.frSleep = 0;
                    }
                    if (this.delay >= 0) {
                        --this.delay;
                    }
                    if (this.delay < 0) {
                        this.ispaintsleep = true;
                    }
                    if (this.objBeKillMain.isSleep && this.objBeKillMain != null && this.objBeKillMain.hp > 0 && (this.objBeKillMain == null || !this.objBeKillMain.isRemove)) break;
                    this.isRemove = true;
                    break;
                }
                case 94: {
                    if (this.nFrame == null) {
                        this.nFrame = new byte[]{0, 0, 0, 1, 1, 1, 2, 2, 2, 3, 3, 3};
                    }
                    if (this.f > this.nFrame.length - 1) {
                        this.f = this.nFrame.length - 6;
                    }
                    this.update_Boss_De2();
                    break;
                }
                case 95: {
                    if (this.f < this.fRemove) break;
                    this.removeEff();
                    break;
                }
                case 96: {
                    if (this.f < this.fRemove) {
                        this.updateAngleXP();
                        this.frame = this.setFrameAngle(this.gocT_Arc);
                    }
                    if (this.VecEff.size() == 0 && this.f > this.fRemove) {
                        this.removeEff();
                    }
                    for (int i = 0; i < this.VecEff.size(); ++i) {
                        Point point = (Point)this.VecEff.elementAt(i);
                        ++point.f;
                        if (point.f / 2 <= 3) continue;
                        this.VecEff.removeElement(point);
                        --i;
                    }
                    if (this.f != this.fRemove || this.objBeKillMain == null) break;
                    GameScreen.addEffectEndKill(45, this.objBeKillMain.x, this.objBeKillMain.y - 20);
                    break;
                }
                case 97: {
                    if (this.f >= this.fRemove && this.VecEff.size() == 0) {
                        this.removeEff();
                    }
                    if (this.f < this.fRemove) {
                        if (this.f == 8) {
                            int n = 2;
                            if (this.objBeKillMain != null && this.objKill != null) {
                                n = this.objKill.Direction = EffectSkill.setDirection(this.objKill, this.objBeKillMain);
                            }
                            if (n == 2 || n == 3) {
                                this.vY1000 = 1;
                                this.vX1000 = 0;
                            } else {
                                this.vY1000 = 0;
                                this.vX1000 = 1;
                            }
                            if (this.objBeKillMain != null && this.objKill != null) {
                                int n19 = CRes.angle(this.objBeKillMain.x - this.objKill.x, this.objBeKillMain.y - (this.objKill.y - this.objKill.hOne / 2 + 10));
                                int n20 = 320;
                                if (n20 < GameCanvas.h) {
                                    n20 = GameCanvas.h;
                                }
                                if (n20 < GameCanvas.w) {
                                    n20 = GameCanvas.w;
                                }
                                this.x1000 = this.x + CRes.cos(n19) * n20 / 1000;
                                this.y1000 = this.y + CRes.sin(n19) * n20 / 1000;
                                this.toX = this.objBeKillMain.x;
                                this.toY = this.objBeKillMain.y - this.objBeKillMain.hOne / 2;
                                this.xdichTower = this.objBeKillMain.x;
                                this.ydichTower = this.objBeKillMain.y;
                                GameScreen.addEffectEndKill(0, this.xdichTower, this.ydichTower - this.objBeKillMain.hOne / 2);
                                GameScreen.addEffectEndKill(26, this.xdichTower, this.ydichTower);
                            }
                        }
                        if (this.f > 8 && this.f % 3 == 2) {
                            Point point = new Point();
                            point.x = this.toX + CRes.random_Am_0(5);
                            point.y = this.toY + CRes.random_Am_0(5);
                            this.VecEff.addElement(point);
                        }
                    }
                    for (int i = 0; i < this.VecEff.size(); ++i) {
                        Point point = (Point)this.VecEff.elementAt(i);
                        ++point.f;
                        if (point.f < 4) continue;
                        this.VecEff.removeElement(point);
                        --i;
                    }
                    break;
                }
                case 98: {
                    if (this.f < this.fRemove && this.f > 1) {
                        Point point = new Point();
                        point.x = this.x - this.vx;
                        point.y = this.y - this.vy;
                        this.VecEff.addElement(point);
                    } else if (this.VecEff.size() == 0 && this.f >= this.fRemove) {
                        this.removeEff();
                    }
                    if (this.f == this.fRemove && this.objBeKillMain != null) {
                        GameScreen.addEffectEndKill(26, this.toX, this.toY + this.objBeKillMain.hOne / 2);
                        GameScreen.addEffectEndKill(14, this.toX, this.toY + this.objBeKillMain.hOne / 2);
                        GameScreen.addEffectEndKill(30, this.toX, this.toY + this.objBeKillMain.hOne / 2 + 10);
                    }
                    for (int i = 0; i < this.VecEff.size(); ++i) {
                        Point point = (Point)this.VecEff.elementAt(i);
                        ++point.f;
                        if (point.f / 2 <= 3) continue;
                        this.VecEff.removeElement(point);
                        --i;
                    }
                    break;
                }
                case 99: {
                    if (this.f < this.fRemove && this.f > 1) {
                        Point point = new Point();
                        point.x = this.x - this.vx;
                        point.y = this.y - this.vy;
                        this.VecEff.addElement(point);
                    } else if (this.VecEff.size() == 0 && this.f >= this.fRemove) {
                        this.removeEff();
                    }
                    if (this.f == this.fRemove && this.objBeKillMain != null) {
                        GameScreen.addEffectEndKill(48, this.toX, this.toY + this.objBeKillMain.hOne / 4);
                    }
                    for (int i = 0; i < this.VecEff.size(); ++i) {
                        Point point = (Point)this.VecEff.elementAt(i);
                        ++point.f;
                        if (point.f / 2 <= 3) continue;
                        this.VecEff.removeElement(point);
                        --i;
                    }
                    break;
                }
                case 100: {
                    this.update_Boss_Medusa2();
                }
            }
        }
        catch (Exception exception) {
            mSystem.println("Loi update Eff " + this.typeEffect + "  f=" + this.f);
            exception.printStackTrace();
            this.removeEff();
        }
        super.update();
        if (this.f > 200 && this.timeRemove == 0) {
            this.removeEff();
        }
    }

    private void SetAddSoundTimeAdd() {
        if (this.objKill == null) {
            return;
        }
        if (this.typeEffect == 27) {
            this.indexSound = 9;
            if (this.objKill == GameScreen.player) {
                mSound.playSound(this.indexSound, mSound.volumeSound);
            } else {
                GameScreen.addSoundEff(this.indexSound);
            }
        } else if (this.typeEffect == 51) {
            this.indexSound = 19;
            if (this.objKill == GameScreen.player) {
                mSound.playSound(this.indexSound, mSound.volumeSound);
            } else {
                GameScreen.addSoundEff(this.indexSound);
            }
        } else if (this.typeEffect == 125 || this.typeEffect == 126 || this.typeEffect == 97 || this.typeEffect == 106 || this.typeEffect == 105) {
            this.indexSound = 13;
            if (this.objKill == GameScreen.player) {
                mSound.playSound(this.indexSound, mSound.volumeSound);
            } else {
                GameScreen.addSoundEff(this.indexSound);
            }
        } else if (this.typeEffect == 62) {
            this.indexSound = 20;
            if (this.objKill == GameScreen.player) {
                mSound.playSound(this.indexSound, mSound.volumeSound);
            } else {
                GameScreen.addSoundEff(this.indexSound);
            }
        }
    }

    public void endUpdate() {
    }

    public static int setDirection(MainObject mainObject, MainObject mainObject2) {
        int n = mainObject.x - mainObject2.x;
        int n2 = mainObject.y - mainObject2.y;
        int n3 = CRes.abs(n) > CRes.abs(n2) ? (n > 0 ? 2 : 3) : (n2 > 0 ? 1 : 0);
        return n3;
    }

    public int setDirection(int n, int n2) {
        int n3 = CRes.abs(n) > CRes.abs(n2) ? (n > 0 ? 2 : 3) : (n2 > 0 ? 1 : 0);
        return n3;
    }

    private void setBeginKill(int n) {
        switch (this.Direction) {
            case 1: {
                this.y -= 10 + n;
                break;
            }
            case 0: {
                this.y += 10 + n;
                break;
            }
            case 2: {
                this.x -= 10 + n;
                break;
            }
            case 3: {
                this.x += 10 + n;
                break;
            }
        }
    }

    private void setBeginKillXY1000(int n) {
        switch (this.Direction) {
            case 1: {
                this.y1000 -= 10 + n;
                break;
            }
            case 0: {
                this.y1000 += 10 + n;
                break;
            }
            case 2: {
                this.x1000 -= 10 + n;
                break;
            }
            case 3: {
                this.x1000 += 10 + n;
                break;
            }
        }
    }

    public int setFrameAngle(int n) {
        int n2;
        if (n <= 15 || n > 345) {
            n2 = 12;
        } else {
            int n3 = (n - 15) / 15 + 1;
            if (n3 > 24) {
                n3 = 24;
            }
            n2 = this.mpaintone_Bullet[n3];
        }
        return n2;
    }

    private void setPosLineIn(int n) {
        block0 : switch (n) {
            case 0: {
                switch (this.Direction) {
                    case 1: {
                        this.x += 8;
                        this.y += 40;
                        break;
                    }
                    case 0: {
                        this.x -= 8;
                        this.y += 20;
                        break;
                    }
                    case 2: {
                        this.x += 15;
                        this.y += 20;
                        break;
                    }
                    case 3: {
                        this.x -= 15;
                        this.y += 20;
                    }
                }
                break;
            }
            case 1: {
                switch (this.Direction) {
                    case 1: {
                        this.y += 10;
                        break;
                    }
                    case 0: {
                        this.y += 45;
                        break;
                    }
                    case 2: {
                        this.x -= 16;
                        this.y += 30;
                        break;
                    }
                    case 3: {
                        this.x += 18;
                        this.y += 30;
                    }
                }
                break;
            }
            case 3: {
                switch (this.Direction) {
                    case 1: {
                        this.x += 5;
                        this.y += 45;
                        break;
                    }
                    case 0: {
                        this.x += 8;
                        this.y += 25;
                        break;
                    }
                    case 2: {
                        this.x += 13;
                        this.y += 35;
                        break;
                    }
                    case 3: {
                        this.x -= 13;
                        this.y += 35;
                    }
                }
                break;
            }
            case 4: {
                switch (this.Direction) {
                    case 1: {
                        this.x += 15;
                        this.y += 33;
                        break block0;
                    }
                    case 0: {
                        this.x -= 15;
                        this.y += 17;
                        break block0;
                    }
                    case 2: {
                        this.x += 3;
                        this.y += 11;
                        break block0;
                    }
                    case 3: {
                        this.x -= 3;
                        this.y += 11;
                    }
                }
            }
        }
    }

    public void removeEff() {
        if (this.typeEffect == 103) {
            return;
        }
        if (this.ispaintArena && mSystem.isj2me) {
            countSkillArena = (byte)(countSkillArena - 1);
        }
        if (this.VecEff.size() > 0) {
            this.VecEff.removeAllElements();
        }
        if (this.VecSubEff.size() > 0) {
            this.VecSubEff.removeAllElements();
        }
        boolean bl = true;
        if (mSystem.isj2me && GameScreen.infoGame.ismapHouse(GameCanvas.loadmap.idMap)) {
            bl = false;
        }
        if (this.timeAddNum == -1 && bl) {
            this.addNum();
        }
        this.isStop = true;
        this.isRemove = true;
        this.f = -1;
    }

    public void addNum() {
        if (this.objKill != null && this.objKill.ID != GameScreen.player.ID && GameScreen.infoGame.isMapchienthanh()) {
            return;
        }
        for (int i = 0; i < this.vecObjBeKill.size(); ++i) {
            MainObject mainObject;
            Object_Effect_Skill object_Effect_Skill = (Object_Effect_Skill)this.vecObjBeKill.elementAt(i);
            if (object_Effect_Skill == null || (mainObject = MainObject.get_Object(object_Effect_Skill.ID, object_Effect_Skill.tem)) == null || mainObject == null) continue;
            if (!this.isEff) {
                int n = 6;
                if (object_Effect_Skill.hpShow == 0) {
                    if (mainObject == GameScreen.player) {
                        n = 7;
                    }
                    GameScreen.addEffectNum(T.hut, mainObject.x, mainObject.y - mainObject.hOne, n, object_Effect_Skill.ID);
                } else {
                    int n2;
                    if (this.objKill != null && (n2 = EffectSkill.setAddEffKillPlus(object_Effect_Skill.mEffTypePlus, this.objKill, mainObject, object_Effect_Skill.mEff_HP_Plus)) != -1) {
                        n = n2;
                    }
                    if (this.typeEffect == 41) {
                        GameScreen.addEffectNum("+" + object_Effect_Skill.hpShow, mainObject.x, mainObject.y - mainObject.hOne, this.subType == 0 ? 3 : 4, object_Effect_Skill.ID);
                    } else {
                        GameScreen.addEffectNum("-" + object_Effect_Skill.hpShow, mainObject.x, mainObject.y - mainObject.hOne, n, object_Effect_Skill.ID);
                    }
                }
                if (mainObject.typeObject == 1) {
                    mainObject.hp = object_Effect_Skill.hpLast;
                    if (mainObject.hp <= 0) {
                        if (this.objKill != null) {
                            MainObject.startDeadFly((MainMonster)mainObject, this.objKill.ID, CRes.random(3));
                        }
                        mainObject.resetAction();
                        mainObject.Action = 4;
                        mainObject.timedie = GameCanvas.timeNow;
                        GameScreen.addEffectEndKill(11, mainObject.x, mainObject.y);
                    }
                }
            }
            if (this.typeEffect == 41 || this.typeEffect == 58 || this.typeEffect == 42 || object_Effect_Skill.hpShow <= 0 && (this.objKill == null || this.objKill != GameScreen.player)) continue;
            if (mainObject.typeObject == 1) {
                if (mainObject.Action == 3) continue;
                if (!mainObject.isServerControl) {
                    mainObject.resetAction();
                }
                mainObject.Action = 3;
                mainObject.f = 0;
                continue;
            }
            if (mainObject.typeObject != 0) continue;
            if (mainObject.eye != 3) {
                if (this.objKill != null && this.objKill.typeObject == 0 && mainObject.hp > mainObject.maxHp / 2 && object_Effect_Skill.hpShow <= mainObject.maxHp / 20) {
                    mainObject.eye = 4;
                    mainObject.timeEye = -6;
                } else {
                    mainObject.eye = 2;
                    mainObject.timeEye = 0;
                }
            }
            if (CRes.random(5) == 0) {
                mainObject.dy = -CRes.random(2, 5);
            }
            if (mainObject != GameScreen.player || CRes.random(3) != 0) continue;
            mSound.playSound(38, mSound.volumeSound);
        }
        if (!this.isEff && this.objKill != null && this.objKill.typeObject != 9 && this.objKill.hp <= 0 && this.objKill.Action != 4) {
            this.objKill.resetAction();
            this.objKill.Action = 4;
            GameScreen.addEffectEndKill(11, this.objKill.x, this.objKill.y);
        }
    }

    private void createXP() {
        if (this.objKill == null) {
            this.gocT_Arc = 0;
        } else {
            switch (this.objKill.Direction) {
                case 0: {
                    this.gocT_Arc = 0;
                    break;
                }
                case 1: {
                    this.gocT_Arc = 180;
                    break;
                }
                case 2: {
                    this.gocT_Arc = 90;
                    break;
                }
                case 3: {
                    this.gocT_Arc = 270;
                }
            }
        }
        this.va = 6144;
        this.vx = 0;
        this.vy = 0;
        this.life = 0;
        this.vX1000 = this.va * CRes.cos(this.gocT_Arc) >> 10;
        this.vY1000 = this.va * CRes.sin(this.gocT_Arc) >> 10;
    }

    private void createHut_Mp_Hp() {
        switch (CRes.random(4)) {
            case 0: {
                this.gocT_Arc = 0;
                break;
            }
            case 1: {
                this.gocT_Arc = 180;
                break;
            }
            case 2: {
                this.gocT_Arc = 90;
                break;
            }
            case 3: {
                this.gocT_Arc = 270;
            }
        }
        this.va = 6144;
        this.vx = 0;
        this.vy = 0;
        this.life = 0;
        this.vX1000 = this.va * CRes.cos(this.gocT_Arc) >> 10;
        this.vY1000 = this.va * CRes.sin(this.gocT_Arc) >> 10;
    }

    public void createNormal() {
        if (this.objKill == null) {
            this.gocT_Arc = 0;
        } else {
            switch (this.objKill.Direction) {
                case 0: {
                    this.gocT_Arc = 90;
                    break;
                }
                case 1: {
                    this.gocT_Arc = 270;
                    break;
                }
                case 2: {
                    this.gocT_Arc = 180;
                    break;
                }
                case 3: {
                    this.gocT_Arc = 0;
                }
            }
        }
        this.va = 4096;
        this.vx = 0;
        this.vy = 0;
        this.life = 0;
        this.vX1000 = this.va * CRes.cos(this.gocT_Arc) >> 10;
        this.vY1000 = this.va * CRes.sin(this.gocT_Arc) >> 10;
    }

    public void createDanFocus() {
        switch (CRes.random(4)) {
            case 0: {
                this.gocT_Arc = 90;
                break;
            }
            case 1: {
                this.gocT_Arc = 270;
                break;
            }
            case 2: {
                this.gocT_Arc = 180;
                break;
            }
            case 3: {
                this.gocT_Arc = 0;
            }
        }
        this.va = 4096;
        this.vx = 0;
        this.vy = 0;
        this.life = 0;
        this.vX1000 = this.va * CRes.cos(this.gocT_Arc) >> 10;
        this.vY1000 = this.va * CRes.sin(this.gocT_Arc) >> 10;
    }

    public Point_Focus create_Speed(int n, int n2, Point_Focus point_Focus) {
        if (n2 == 0) {
            n2 = 1;
        }
        if (n == 0) {
            n = 1;
        }
        int n3 = 0;
        int n4 = 0;
        int n5 = MainObject.getDistance(n, n2) / this.vMax;
        if (n5 == 0) {
            n5 = 1;
        }
        n3 = n / n5;
        n4 = n2 / n5;
        if (CRes.abs(n3) > CRes.abs(n)) {
            n3 = n;
        }
        if (CRes.abs(n4) > CRes.abs(n2)) {
            n4 = n2;
        }
        if (this.objBeKillMain != null) {
            this.toX = this.objBeKillMain.x;
            this.toY = this.objBeKillMain.y - this.objBeKillMain.hOne / 2;
        }
        if (point_Focus != null) {
            point_Focus.vx = n3;
            point_Focus.vy = n4;
            point_Focus.toX = this.toX;
            point_Focus.toY = this.toY;
            point_Focus.fRe = n5;
        } else {
            this.fRemove = n5;
            this.vx = n3;
            this.vy = n4;
        }
        return point_Focus;
    }

    public Point_Focus create_Speed_More(int n, int n2, Point_Focus point_Focus, MainObject mainObject) {
        if (n2 == 0) {
            n2 = 1;
        }
        if (n == 0) {
            n = 1;
        }
        int n3 = 0;
        int n4 = 0;
        int n5 = MainObject.getDistance(n, n2) / this.vMax;
        if (n5 == 0) {
            n5 = 1;
        }
        n3 = n / n5;
        n4 = n2 / n5;
        if (CRes.abs(n3) > CRes.abs(n)) {
            n3 = n;
        }
        if (CRes.abs(n4) > CRes.abs(n2)) {
            n4 = n2;
        }
        this.toX = mainObject.x;
        this.toY = mainObject.y - mainObject.hOne / 2;
        if (point_Focus != null) {
            point_Focus.vx = n3;
            point_Focus.vy = n4;
            point_Focus.toX = this.toX;
            point_Focus.toY = this.toY;
            point_Focus.fRe = n5;
        } else {
            this.fRemove = n5;
            this.vx = n3;
            this.vy = n4;
        }
        return point_Focus;
    }

    public Point_Focus create_Speed_noToXY(int n, int n2, Point_Focus point_Focus) {
        if (n2 == 0) {
            n2 = 1;
        }
        if (n == 0) {
            n = 1;
        }
        int n3 = 0;
        int n4 = 0;
        int n5 = MainObject.getDistance(n, n2) / this.vMax;
        if (n5 == 0) {
            n5 = 1;
        }
        n3 = n / n5;
        n4 = n2 / n5;
        if (CRes.abs(n3) > CRes.abs(n)) {
            n3 = n;
        }
        if (CRes.abs(n4) > CRes.abs(n2)) {
            n4 = n2;
        }
        if (point_Focus != null) {
            point_Focus.vx = n3;
            point_Focus.vy = n4;
            point_Focus.toX = this.toX;
            point_Focus.toY = this.toY;
            point_Focus.fRe = n5;
        } else {
            this.fRemove = n5;
            this.vx = n3;
            this.vy = n4;
        }
        return point_Focus;
    }

    public void create_Speed_Kiem_LV5(int n, int n2) {
        if (n2 == 0) {
            n2 = 1;
        }
        if (n == 0) {
            n = 1;
        }
        int n3 = 0;
        int n4 = 0;
        int n5 = MainObject.getDistance(n, n2) / this.vMax;
        if (n5 == 0) {
            n5 = 1;
        }
        n3 = n / n5;
        n4 = n2 / n5;
        if (CRes.abs(n3) > CRes.abs(n)) {
            n3 = n;
        }
        if (CRes.abs(n4) > CRes.abs(n2)) {
            n4 = n2;
        }
        this.fRemove = n5;
        this.vx = n3;
        this.vy = n4;
    }

    private void create_Star() {
        int n;
        this.x1000 = this.objBeKillMain.x * 1000;
        this.y1000 = this.objBeKillMain.y * 1000 - this.objBeKillMain.hOne / 2 * 1000;
        this.colorpaint = new int[3];
        for (n = 0; n < 3; ++n) {
            this.colorpaint[n] = colorStar[0][CRes.random(3)];
        }
        this.fRemove = CRes.random(5, 9);
        this.mTamgiac = mSystem.new_M_Int(6, 4);
        for (n = 0; n < 3; ++n) {
            int n2 = CRes.random(5 + 60 * n, 55 + 60 * n);
            int n3 = CRes.random(20, 30);
            this.mTamgiac[n * 2][0] = CRes.sin(CRes.fixangle(n2)) * n3 + this.x1000;
            this.mTamgiac[n * 2][1] = CRes.cos(CRes.fixangle(n2)) * n3 + this.y1000;
            int n4 = n2 + CRes.random_Am(4, 12);
            int n5 = n3 + CRes.random_Am(3, 10);
            this.mTamgiac[n * 2][2] = CRes.sin(CRes.fixangle(n4)) * n5 + this.x1000;
            this.mTamgiac[n * 2][3] = CRes.cos(CRes.fixangle(n4)) * n5 + this.y1000;
            this.mTamgiac[n * 2 + 1][0] = CRes.sin(CRes.fixangle(n2 += 180)) * (n3 += CRes.random_Am(3, 10)) + this.x1000;
            this.mTamgiac[n * 2 + 1][1] = CRes.cos(CRes.fixangle(n2)) * n3 + this.y1000;
            n5 = n3 + CRes.random_Am(3, 10);
            this.mTamgiac[n * 2 + 1][2] = CRes.sin(CRes.fixangle(n4 += 180)) * n5 + this.x1000;
            this.mTamgiac[n * 2 + 1][3] = CRes.cos(CRes.fixangle(n4)) * n5 + this.y1000;
        }
    }

    private void createLighting(int n, int n2, int n3, int n4, boolean bl) {
        int n5 = 0;
        int n6 = 0;
        int n7 = 0;
        int n8 = MainObject.getDistance(n, n2, n3, n4) / 15 + CRes.random(3);
        int n9 = CRes.random(2);
        n6 = CRes.angle(n3 - n, n4 - n2);
        int n10 = 20;
        for (int i = 0; i < n8; ++i) {
            Point point = new Point();
            if (i == 0) {
                point.x = n * 1000;
                point.y = n2 * 1000;
            } else {
                Point point2 = (Point)this.VecEff.elementAt(i - 1);
                n10 = 20 + CRes.random_Am_0(10);
                int n11 = MainObject.getDistance(point2.x / 1000, point2.y / 1000, n3, n4);
                if ((n11 < 25 || i == n8 - 1) && bl) {
                    point.x = n3 * 1000;
                    point.y = n4 * 1000;
                    this.VecEff.addElement(point);
                    break;
                }
                n7 = n6;
                n7 = bl ? (CRes.abs(n5) > 50 ? (n5 > 0 ? (n7 -= CRes.random(5, 50)) : (n7 += CRes.random(5, 50))) : (i % 2 == n9 ? (n7 -= CRes.random(5, 50)) : (n7 += CRes.random(5, 50)))) : (CRes.abs(n5) > 50 ? (n5 > 0 ? (n7 -= CRes.random(5, 50)) : (n7 += CRes.random(5, 50))) : (i % 2 == n9 ? (n7 -= CRes.random(10, 40)) : (n7 += CRes.random(10, 40))));
                n5 += n7 - n6;
                n7 = CRes.fixangle(n7);
                point.x = point2.x + CRes.cos(n7) * n10;
                point.y = point2.y + CRes.sin(n7) * n10;
                if (CRes.random(6) < 5) {
                    this.VecSubEff.addElement(point);
                    Point point3 = new Point();
                    point3 = point;
                    int n12 = 10;
                    int n13 = n7;
                    int n14 = CRes.random(3, 7);
                    for (int j = 0; j < n14; ++j) {
                        n12 = 5 + CRes.random_Am_0(3);
                        n13 = n7;
                        n13 = j % 2 == n9 ? (n13 -= CRes.random(10, 40)) : (n13 += CRes.random(10, 40));
                        n13 = CRes.fixangle(n13);
                        Point point4 = new Point();
                        point4.x = point3.x + CRes.cos(n13) * n12;
                        point4.y = point3.y + CRes.sin(n13) * n12;
                        this.VecSubEff.addElement(point4);
                        point3 = point4;
                    }
                    point3.x = -1;
                    this.VecSubEff.addElement(point3);
                }
            }
            this.VecEff.addElement(point);
        }
    }

    public Point create_Stone_Drop(int n, int n2) {
        Point point = new Point();
        int n3 = CRes.random(0, 40);
        int n4 = CRes.random(20, 50);
        this.x = MainScreen.cameraMain.xCam - n3;
        int n5 = CRes.abs(this.x - n);
        this.fRemove = CRes.abs(n5 / this.vx);
        if (this.fRemove == 0) {
            this.fRemove = 1;
        }
        this.y = n2 - n5 * 1000 / CRes.tan(n4);
        int n6 = CRes.abs(this.y - n2);
        this.vY1000 = n6 * 1000 / this.fRemove;
        point.y = this.y;
        point.x = this.x;
        point.vx = this.vx;
        point.vy = this.vY1000;
        point.f = this.fRemove;
        return point;
    }

    public Point create_ICE_Drop(int n, int n2) {
        Point point = new Point();
        this.x = n;
        this.y = MainScreen.cameraMain.yCam;
        int n3 = CRes.abs(this.y - n2);
        this.fRemove = n3 / this.vMax;
        if (this.fRemove == 0) {
            this.fRemove = 1;
        }
        this.vY1000 = n3 * 1000 / this.fRemove;
        point.y = this.y;
        point.x = this.x;
        point.vx = 0;
        point.vy = this.vY1000;
        point.f = this.fRemove;
        return point;
    }

    private void create_Crack_Earth() {
        LoadMap.timeVibrateScreen = CRes.random(6, 12);
        this.levelPaint = -1;
        this.fRemove = 20;
        this.timeAddNum = (byte)8;
        if (this.objBeKillMain != null) {
            this.toX = this.objBeKillMain.x;
            this.toY = this.objBeKillMain.y - this.objBeKillMain.hOne / 2;
        }
        if (CRes.abs(this.x - this.toX) > CRes.abs(this.y - this.toY)) {
            this.xline = 0;
            this.yline = 1;
        } else {
            this.xline = 1;
            this.yline = 0;
        }
        int n = MainObject.getDistance(this.x, this.y, this.toX, this.toY);
        int n2 = CRes.angle(this.toX - this.x, this.toY - this.y);
        int n3 = this.x + CRes.cos(n2) * (n += 24) / 1000;
        int n4 = this.y + CRes.sin(n2) * n / 1000;
        this.createLighting(this.x, this.y, n3, n4, false);
    }

    private void create_Nova() {
        this.fraImgEff = new FrameImage(57);
        this.fraImgSubEff = new FrameImage(3);
        this.fRemove = 14;
        this.vMax = 6;
        for (int i = 0; i < 16; ++i) {
            Point point = new Point();
            point.x = this.x * 1000;
            point.y = this.y * 1000;
            point.vx = 2 * CRes.cos(225 * i / 10) * this.vMax;
            point.vy = 1 * CRes.sin(225 * i / 10) * this.vMax;
            point.f = 0;
            this.VecEff.addElement(point);
            int n = (this.x + point.vx) * 100 - this.x;
            int n2 = (this.y + point.vy) * 100 - this.y;
            int n3 = CRes.angle(n, n2);
            point.frame = this.setFrameAngle(n3);
        }
    }

    private void create_Poison_Nova() {
        this.fraImgEff = new FrameImage(125);
        this.fraImgSubEff = new FrameImage(3);
        this.fRemove = 14;
        this.vMax = 5;
        for (int i = 0; i < 16; ++i) {
            Point point = new Point();
            point.x = this.x * 1000;
            point.y = this.y * 1000;
            point.vx = 2 * CRes.cos(225 * i / 10) * this.vMax;
            point.vy = CRes.sin(225 * i / 10) * this.vMax;
            point.f = 0;
            this.VecEff.addElement(point);
            int n = (this.x + point.vx) * 100 - this.x;
            int n2 = (this.y + point.vy) * 100 - this.y;
            int n3 = CRes.angle(n, n2);
            point.frame = this.setFrameAngle(n3);
        }
    }

    private void create_Fire_Arc() {
        this.fraImgEff = new FrameImage(41);
        this.fraImgSubEff = new FrameImage(42);
        this.fRemove = 25;
        this.vMax = 5;
        for (int i = 0; i < 16; ++i) {
            Point point = new Point();
            point.x = this.x * 1000;
            point.y = this.y * 1000;
            point.vx = 2 * CRes.cos(225 * i / 10) * this.vMax;
            point.vy = 1 * CRes.sin(225 * i / 10) * this.vMax;
            point.f = 0;
            this.VecEff.addElement(point);
        }
    }

    private void create_Ice_Arc() {
        this.fraImgEff = new FrameImage(29);
        this.fraImgSubEff = new FrameImage(42);
        this.fRemove = 25;
        this.vMax = 5;
        for (int i = 0; i < 16; ++i) {
            Point point = new Point();
            point.x = this.x * 1000;
            point.y = this.y * 1000;
            point.vx = 2 * CRes.cos(225 * i / 10) * this.vMax;
            point.vy = 1 * CRes.sin(225 * i / 10) * this.vMax;
            point.f = 0;
            this.VecEff.addElement(point);
        }
    }

    private void create_Arrow_Rain() {
        if (this.f == -1) {
            this.VecEff.removeAllElements();
        }
        for (int i = 0; i < this.vecObjBeKill.size(); ++i) {
            MainObject mainObject;
            Object_Effect_Skill object_Effect_Skill = (Object_Effect_Skill)this.vecObjBeKill.elementAt(i);
            if (object_Effect_Skill == null || (mainObject = MainObject.get_Object(object_Effect_Skill.ID, object_Effect_Skill.tem)) == null) continue;
            int n = CRes.random(1, 4);
            if (GameCanvas.lowGraphic) {
                n = CRes.random(1, 3);
            }
            for (int j = 0; j < n; ++j) {
                this.vMax = CRes.random(14, 18);
                Point point = new Point();
                point.x = mainObject.x - 70 + CRes.random_Am_0(15);
                point.y = this.y1000;
                int n2 = mainObject.x - point.x + CRes.random_Am_0(30);
                int n3 = mainObject.y - point.y + 8 + CRes.random_Am_0(10);
                if (n3 / this.vMax > 8) {
                    this.vMax = n3 / 8;
                }
                Point_Focus point_Focus = new Point_Focus();
                point_Focus = this.create_Speed(n2, n3, point_Focus);
                point.vx = point_Focus.vx;
                point.vy = point_Focus.vy;
                point.fRe = point_Focus.fRe;
                point.f = 0;
                this.VecEff.addElement(point);
            }
        }
    }

    public void create_Kill_2Kiem_Lv2() {
        int n;
        this.savex = this.objKill.x;
        this.savey = this.objKill.y;
        if (this.objKill != null) {
            this.y += this.objKill.hOne / 2;
            this.objKill.isTanHinh = true;
            this.objKill.timeTanHinh = 0;
        }
        this.fraImgEff = new FrameImage(50);
        this.vMax = 20;
        int n2 = 0;
        int n3 = 0;
        if (this.objBeKillMain != null) {
            n2 = this.objBeKillMain.x - this.x;
            n3 = this.objBeKillMain.y - this.y;
        } else {
            n2 = this.toX - this.x;
            n3 = this.toY - this.y;
        }
        int n4 = MainObject.getDistance(n2, n3);
        int n5 = CRes.angle(n2, n3);
        int n6 = n4;
        this.toX = this.x + n6 * CRes.cos(n5) / 1000;
        this.toY = this.y + n6 * CRes.sin(n5) / 1000;
        int n7 = this.toX;
        int n8 = this.toY;
        do {
            this.toX = n7;
            this.toY = n8;
            if (n6 > 120) {
                n = -1;
                continue;
            }
            n7 = this.x + (n6 += 10) * CRes.cos(n5) / 1000;
            n8 = this.y + n6 * CRes.sin(n5) / 1000;
            n = GameCanvas.loadmap.getTile(this.toX, this.toY);
        } while (n != -1 && n != 1);
        n2 = this.toX - this.x;
        n3 = this.toY - this.y;
        if (n3 == 0) {
            n3 = 1;
        }
        if (n2 == 0) {
            n2 = 1;
        }
        int n9 = 0;
        int n10 = 0;
        int n11 = MainObject.getDistance(n2, n3) / this.vMax;
        if (n11 == 0) {
            n11 = 1;
        }
        n9 = n2 / n11;
        n10 = n3 / n11;
        if (CRes.abs(n9) > CRes.abs(n2)) {
            n9 = n2;
        }
        if (CRes.abs(n10) > CRes.abs(n3)) {
            n10 = n3;
        }
        this.vx = n9;
        this.vy = n10;
        this.fRemove = n11;
        if (this.fRemove > 0) {
            this.timeAddNum = (byte)(this.fRemove / 2);
        }
        if (this.objKill != null && this.objBeKillMain != null) {
            this.toX = this.objKill.x + (this.fRemove - 1) * n9;
            this.toY = this.objBeKillMain != null ? this.objKill.y + this.objBeKillMain.hOne / 4 + (this.fRemove - 1) * n10 : this.objKill.y + (this.fRemove - 1) * n10;
        }
    }

    private void create_Star_Line_In(int n, int n2, int n3) {
        int n4;
        if (this.f == -1) {
            this.VecEff.removeAllElements();
        }
        int n5 = 4;
        this.colorpaint = new int[n5];
        if (n3 <= n2) {
            n3 = n2 + 1;
        }
        for (n4 = 0; n4 < n5; ++n4) {
            this.colorpaint[n4] = CRes.random(2) == 0 ? colorStar[this.indexColorStar][CRes.random(3)] : colorStar[this.indexColorStar][2];
        }
        for (n4 = 0; n4 < n5; ++n4) {
            Line line = new Line();
            int n6 = 180 / n5 + 180 / n5 * n4 - 5;
            int n7 = 5 + 180 / n5 * n4;
            if (n6 <= n7) {
                n6 = n7 + 1;
            }
            int n8 = CRes.random(n2, n3);
            int n9 = CRes.random(n, n + 3);
            int n10 = CRes.random(n7, n6);
            int n11 = CRes.random(13, 23);
            boolean bl = CRes.random(4) == 0;
            n10 = CRes.fixangle(n10);
            line.setLine(this.x1000 - CRes.sin(n10) * (n8 + n11), this.y1000 - CRes.cos(n10) * (n8 + n11), this.x1000 - CRes.sin(n10) * n11, this.y1000 - CRes.cos(n10) * n11, CRes.sin(n10) * n9, CRes.cos(n10) * n9, bl);
            this.VecEff.addElement(line);
            line = new Line();
            n10 += 180 + CRes.random_Am(2, 5);
            n10 = CRes.fixangle(n10);
            line.setLine(this.x1000 - CRes.sin(n10) * (n8 + n11), this.y1000 - CRes.cos(n10) * (n8 + n11), this.x1000 - CRes.sin(n10) * n11, this.y1000 - CRes.cos(n10) * n11, CRes.sin(n10) * n9, CRes.cos(n10) * n9, bl);
            this.VecEff.addElement(line);
        }
    }

    private void create_Line_NHANBAN_LV2() {
        int n = CRes.random(1, 3);
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
            int n4 = this.x1000 + CRes.random_Am_0(15);
            int n5 = this.y1000 - CRes.random_Am_0(10);
            line.setLine(n4, n5, n4, n5 - n2, 0, -n3, false);
            line.idColor = CRes.random(3);
            this.VecEff.addElement(line);
        }
    }

    private void create_Star_Point_In() {
        int n = CRes.random(0, 45);
        for (int i = 0; i < 8; ++i) {
            Point point = new Point();
            int n2 = 4;
            int n3 = n + 360 * i / 8;
            int n4 = 30;
            point.x = this.x1000 - CRes.sin(CRes.fixangle(n3)) * n4;
            point.y = this.y1000 - CRes.cos(CRes.fixangle(n3)) * n4;
            point.vx = CRes.sin(CRes.fixangle(n3)) * n2;
            point.vy = CRes.cos(CRes.fixangle(n3)) * n2;
            this.VecEff.addElement(point);
        }
    }

    private void create_Buff_Point_In() {
        int n = this.subType;
        if (n >= 9) {
            n = 4;
        } else if (n > 3 && n > 4 && n < 9) {
            n -= 5;
        }
        this.colorBuff = colorBuffMain[n][CRes.random(4)];
        this.colorpaint = new int[12];
        int n2 = CRes.random(0, 30);
        for (int i = 0; i < 12; ++i) {
            this.colorpaint[i] = colorBuffMain[n][CRes.random(3)];
            Line line = new Line();
            int n3 = 5;
            int n4 = n2 + 360 * i / 12;
            int n5 = CRes.random(20, 40);
            if (CRes.random(3) == 0) {
                n3 = 8;
                n5 = CRes.random(40, 55);
            }
            line.vx = CRes.sin(CRes.fixangle(n4)) * n3;
            line.vy = CRes.cos(CRes.fixangle(n4)) * n3;
            line.x0 = this.x1000 - CRes.sin(CRes.fixangle(n4)) * n5;
            line.y0 = this.y1000 - CRes.cos(CRes.fixangle(n4)) * n5;
            line.y1 = this.y1000 - CRes.cos(CRes.fixangle(n4)) * (n5 + 6);
            line.x1 = this.x1000 - CRes.sin(CRes.fixangle(n4)) * (n5 + 6);
            line.is2Line = CRes.random(3) == 0;
            this.VecEff.addElement(line);
        }
    }

    private void create_Mon_Buff() {
        int n = this.subType;
        if (n > 3 && n > 4 && n < 9) {
            n -= 5;
        }
        this.colorBuff = colorBuffMain[n][CRes.random(4)];
        this.colorpaint = new int[12];
        int n2 = CRes.random(0, 30);
        for (int i = 0; i < 12; ++i) {
            this.colorpaint[i] = colorBuffMain[n][CRes.random(3)];
            Line line = new Line();
            int n3 = 4;
            int n4 = n2 + 360 * i / 12;
            int n5 = CRes.random(10, 25);
            if (CRes.random(3) == 0) {
                n3 = 6;
                n5 = CRes.random(20, 40);
            }
            line.vx = CRes.sin(CRes.fixangle(n4)) * n3;
            line.vy = CRes.cos(CRes.fixangle(n4)) * n3;
            line.x0 = this.x1000 - CRes.sin(CRes.fixangle(n4)) * n5;
            line.y0 = this.y1000 - CRes.cos(CRes.fixangle(n4)) * n5;
            line.y1 = this.y1000 - CRes.cos(CRes.fixangle(n4)) * (n5 + 6);
            line.x1 = this.x1000 - CRes.sin(CRes.fixangle(n4)) * (n5 + 6);
            line.is2Line = CRes.random(3) == 0;
            this.VecEff.addElement(line);
        }
    }

    public void create_2Kiem_Lv4(int n, MainObject mainObject) {
        int n2 = 0;
        int n3 = 0;
        if (mainObject != null) {
            n2 = this.x - mainObject.x;
            n3 = this.y - (mainObject.y - mainObject.hOne / 2);
        } else {
            n2 = this.x - this.toX;
            n3 = this.y - this.toY;
        }
        int n4 = CRes.fixangle(CRes.angle(n2, n3) + n);
        int n5 = CRes.fixangle(CRes.angle(-n2, -n3) + n);
        int n6 = 50;
        int n7 = 30;
        Point point = new Point();
        if (mainObject != null) {
            point.x = mainObject.x + n6 * CRes.cos(n4) / 1000;
            point.y = mainObject.y - mainObject.hOne / 2 + n6 * CRes.sin(n4) / 1000;
        } else {
            point.x = this.toX + n6 * CRes.cos(n4) / 1000;
            point.y = this.toY + n6 * CRes.sin(n4) / 1000;
        }
        point.vx = n7 * CRes.cos(n5) / 1000;
        point.vy = n7 * CRes.sin(n5) / 1000;
        this.VecEff.addElement(point);
    }

    public void create_2Kiem_Lv3() {
        this.fraImgEff = new FrameImage(15);
        this.fraImgSubEff = new FrameImage(24);
        this.fraImgSub2Eff = new FrameImage(26);
        if (this.objBeKillMain != null) {
            this.toX = this.objBeKillMain.x;
            this.toY = this.objBeKillMain.y - this.objBeKillMain.hOne / 2;
        }
        this.vMax = 16;
        this.fRemove = 6;
        switch (this.Direction) {
            case 1: {
                this.frame = 18;
                break;
            }
            case 0: {
                this.frame = 6;
                break;
            }
            case 2: {
                this.frame = 0;
                break;
            }
            case 3: {
                this.frame = 12;
            }
        }
        int n = 0;
        int n2 = 14;
        int n3 = 18;
        for (int i = 0; i < 3; ++i) {
            Point point = new Point();
            switch (this.Direction) {
                case 1: {
                    if (i == 0) {
                        point.y = this.y - 25;
                        point.x = this.x;
                        break;
                    }
                    if (i == 1) {
                        point.y = this.y - 25 + n3;
                        point.x = this.x + n2;
                        break;
                    }
                    if (i != 2) break;
                    point.y = this.y - 25 + n3;
                    point.x = this.x - n2;
                    break;
                }
                case 0: {
                    if (i == 0) {
                        point.y = this.y + 25;
                        point.x = this.x;
                        break;
                    }
                    if (i == 1) {
                        point.y = this.y + 25 - n3;
                        point.x = this.x + n2;
                        break;
                    }
                    if (i != 2) break;
                    point.y = this.y + 25 - n3;
                    point.x = this.x - n2;
                    break;
                }
                case 2: {
                    if (i == 0) {
                        point.y = this.y;
                        point.x = this.x - 25;
                        break;
                    }
                    if (i == 1) {
                        point.y = this.y + n2;
                        point.x = this.x - 25 + n3;
                        break;
                    }
                    if (i != 2) break;
                    point.y = this.y - n2;
                    point.x = this.x - 25 + n3;
                    break;
                }
                case 3: {
                    if (i == 0) {
                        point.y = this.y;
                        point.x = this.x + 25;
                        break;
                    }
                    if (i == 1) {
                        point.y = this.y + n2;
                        point.x = this.x + 25 - n3;
                        break;
                    }
                    if (i != 2) break;
                    point.y = this.y - n2;
                    point.x = this.x + 25 - n3;
                }
            }
            Point_Focus point_Focus = new Point_Focus();
            this.create_Speed(this.toX - point.x, this.toY - point.y, point_Focus);
            point.vx = point_Focus.vx;
            point.vy = point_Focus.vy;
            point.fRe = point_Focus.fRe;
            if (i == 0) {
                n = this.setFrameAngle(CRes.angle(this.toX - point.x, this.toY - point.y));
            }
            point.frame = n;
            point.dis = CRes.random(4);
            this.VecEff.addElement(point);
        }
        this.setBeginKill(5);
        this.y += 10;
    }

    void create_2Kiem_Lv5() {
        if (this.objBeKillMain != null) {
            this.toX = this.objBeKillMain.x;
            this.toY = this.objBeKillMain.y - this.objBeKillMain.hOne / 2;
        }
        this.fRemove = 20;
        this.vMax = 20;
        this.fraImgEff = new FrameImage(15);
        this.fraImgSubEff = new FrameImage(17);
        this.fraImgSub2Eff = new FrameImage(26);
        this.lT_Arc = 32;
        int n = CRes.random(360);
        for (int i = 0; i < 5; ++i) {
            Point point = new Point();
            point.x = this.toX * 1000;
            point.y = this.toY * 1000;
            point.g = CRes.fixangle(n + i * 72);
            point.vx = CRes.sin(CRes.fixangle(point.g)) * this.lT_Arc;
            point.vy = CRes.cos(CRes.fixangle(point.g)) * this.lT_Arc;
            int n2 = CRes.angle(-point.vx, -point.vy);
            point.frame = this.setFrameAngle(n2);
            this.VecEff.addElement(point);
            Point point2 = new Point();
            point2.x = point.x + point.vx;
            point2.y = point.y + point.vy;
            point2.vx = CRes.sin(CRes.fixangle(point.g + 180)) * 5;
            point2.vy = CRes.cos(CRes.fixangle(point.g + 180)) * 5;
            this.VecSubEff.addElement(point2);
        }
    }

    private void create_Kiem_Lv2() {
        LoadMap.timeVibrateScreen = 103;
        if (this.objBeKillMain != null) {
            this.x = this.objBeKillMain.x;
            this.y = this.objBeKillMain.y + this.objBeKillMain.hOne / 4;
        } else {
            this.x = this.toX;
            this.y = this.toY;
        }
        this.fraImgEff = new FrameImage(53);
        this.vMax = 4;
        this.fRemove = 6;
        if (this.fRemove > 0) {
            this.timeAddNum = (byte)(this.fRemove / 2);
        }
        if (this.objBeKillMain != null) {
            this.toX = this.objBeKillMain.x;
            this.toY = this.objBeKillMain.y - this.objBeKillMain.hOne / 4;
        }
    }

    private void create_Kiem_Lv3() {
        this.y += 10;
        if (this.Direction == 1) {
            this.y -= 5;
        }
        this.fraImgEff = new FrameImage(8);
        this.fraImgSubEff = new FrameImage(53);
        this.vMax = 14;
        this.setBeginKill(10);
        int n = 0;
        int n2 = 0;
        if (this.objBeKillMain != null) {
            n = this.objBeKillMain.x - this.x;
            n2 = this.objBeKillMain.y - this.y;
        } else {
            n = this.toX - this.x;
            n2 = this.toY - this.y;
        }
        this.create_Speed(n, n2, null);
        if (this.Direction != 1) {
            Point point = new Point();
            point.x = this.x;
            point.y = this.y;
            this.VecEff.addElement(point);
        }
        if (this.objBeKillMain != null) {
            this.toX = this.objBeKillMain.x;
            this.toY = this.objBeKillMain.y - this.objBeKillMain.hOne / 4;
        }
    }

    private void create_PS_LV2_3_5() {
        this.y += 8;
        this.vMax = 12;
        int n = 0;
        int n2 = 0;
        if (this.objBeKillMain != null) {
            n = this.objBeKillMain.x - this.x;
            n2 = this.objBeKillMain.y - this.objBeKillMain.hOne / 2 - this.y;
        } else {
            n = this.toX - this.x;
            n2 = this.toY - this.y;
        }
        this.y += 6;
        this.vMax = 16;
        this.fraImgEff = new FrameImage(57);
        this.fraImgSubEff = new FrameImage(3);
        int n3 = CRes.angle(n, n2);
        this.frame = this.setFrameAngle(n3);
        this.create_Speed(n, n2, null);
        this.frameArrow = this.vx >= 0 ? 0 : 2;
    }

    private void createArrowProjectile(int n) {
        this.vMax = 12;
        int n2 = 0;
        int n3 = 0;
        if (this.objBeKillMain != null) {
            n2 = this.objBeKillMain.x - this.x;
            n3 = this.objBeKillMain.y - this.objBeKillMain.hOne / 2 - this.y;
        } else {
            n2 = this.toX - this.x;
            n3 = this.toY - this.y;
        }
        this.fraImgEff = new FrameImage(n);
        int n4 = CRes.angle(n2, n3);
        this.frame = this.setFrameAngle(n4);
        this.create_Speed(n2, n3, null);
        this.frameArrow = this.vx >= 0 ? 0 : 2;
    }

    private void create_Sung_LV2_LV4() {
        this.y += 5;
        if (this.typeEffect == 22) {
            this.indexSound = 17;
            this.fraImgEff = new FrameImage(74);
            this.vMax = 22;
        } else if (this.typeEffect == 31) {
            this.indexSound = 18;
            this.y += 3;
            this.fraImgEff = new FrameImage(97);
            this.fraImgSubEff = new FrameImage(31);
            this.vMax = 16;
        }
        int n = 0;
        int n2 = 0;
        if (this.objBeKillMain != null) {
            n = this.objBeKillMain.x - this.x;
            n2 = this.objBeKillMain.y - this.objBeKillMain.hOne / 2 - this.y;
        } else {
            n = this.toX - this.x;
            n2 = this.toY - this.y;
        }
        this.create_Speed(n, n2, null);
        int n3 = CRes.angle(n, n2);
        this.frame = this.setFrameAngle(n3);
    }

    private void create_Sung_Medusa() {
        this.y += 5;
        if (this.typeEffect == 99) {
            this.indexSound = 34;
            this.y += 3;
            this.fraImgEff = new FrameImage(135);
            this.vMax = 16;
        }
        int n = 0;
        int n2 = 0;
        if (this.objBeKillMain != null) {
            n = this.objBeKillMain.x - this.x;
            n2 = this.objBeKillMain.y - this.objBeKillMain.hOne / 2 - this.y;
        } else {
            n = this.toX - this.x;
            n2 = this.toY - this.y;
        }
        this.create_Speed(n, n2, null);
        int n3 = CRes.angle(n, n2);
        this.frame = this.setFrameAngle(n3);
    }

    private void create_FireBall_Tower() {
        this.dxTower = 20;
        this.dyTower = -50;
        this.y += this.dyTower;
        this.x += this.dxTower;
        this.indexSound = 32;
        this.fraImgEff = new FrameImage(133);
        this.fraImgSubEff = new FrameImage(31);
        this.vMax = 16;
        int n = 0;
        int n2 = 0;
        if (this.objBeKillMain != null) {
            n = this.objBeKillMain.x - this.x;
            n2 = this.objBeKillMain.y - this.y;
        } else {
            n = this.toX - this.x;
            n2 = this.toY - this.y;
        }
        this.create_Speed(n, n2, null);
        int n3 = CRes.angle(n, n2);
        this.frame = this.setFrameAngle(n3);
    }

    private void create_Sung_DAY_DAN(int n, int n2) {
        int n3 = n - this.x;
        int n4 = n2 - this.y;
        Point_Focus point_Focus = new Point_Focus();
        this.create_Speed_noToXY(n3, n4, point_Focus);
        Point point = new Point();
        point.x = this.x;
        point.y = this.y;
        point.vx = point_Focus.vx;
        point.vy = point_Focus.vy;
        point.fRe = point_Focus.fRe;
        int n5 = CRes.angle(n3, n4);
        point.frame = this.setFrameAngle(n5);
        this.VecEff.addElement(point);
    }

    private void create_Sung_Lv3() {
        int n;
        int n2;
        this.y += 5;
        this.fRemove = 5;
        this.fraImgEff = new FrameImage(47);
        this.vMax = 22;
        if (this.objBeKillMain != null) {
            n2 = this.objBeKillMain.x;
            n = this.objBeKillMain.y - this.objBeKillMain.hOne / 2;
        } else {
            n2 = this.toX;
            n = this.toY;
        }
        int n3 = CRes.angle(n2 - this.x, n - this.y);
        this.frame = this.setFrameAngle(n3);
        for (int i = 0; i < 4; ++i) {
            int n4 = n2 + CRes.random_Am(0, 13) - this.x;
            int n5 = n + CRes.random_Am(0, 13) - this.y;
            Point_Focus point_Focus = new Point_Focus();
            this.create_Speed(n4, n5, point_Focus);
            Point point = new Point();
            point.x = this.x;
            point.y = this.y;
            point.vx = point_Focus.vx;
            point.vy = point_Focus.vy;
            point.fRe = point_Focus.fRe;
            this.VecEff.addElement(point);
        }
    }

    private void create_Sung_BaoDan() {
        if (this.objBeKillMain == null) {
            if (this.f < this.fRemove) {
                this.f = this.fRemove;
            }
            return;
        }
        int n = CRes.angle(this.toX - this.x, this.toY - this.y);
        this.frame = this.setFrameAngle(n);
        for (int i = 0; i < 6; ++i) {
            int n2 = this.toX + CRes.random_Am(0, 35) - this.x;
            int n3 = this.toY + CRes.random_Am(0, 35) - this.y;
            Point_Focus point_Focus = new Point_Focus();
            this.create_Speed_noToXY(n2, n3, point_Focus);
            Point point = new Point();
            point.x = this.x;
            point.y = this.y;
            point.vx = point_Focus.vx;
            point.vy = point_Focus.vy;
            point.fRe = point_Focus.fRe + CRes.random(2);
            point.frame = this.frame;
            this.VecEff.addElement(point);
        }
    }

    private void create_Level_up() {
        this.levelPaint = -1;
        if (this.objKill != null) {
            this.y += this.objKill.hOne / 2;
        }
        this.fraImgEff = new FrameImage(65);
        this.fraImgSubEff = new FrameImage(67);
        this.fRemove = 23;
        for (int i = 0; i < 10; ++i) {
            Point point = new Point();
            int n = CRes.random(180, 360);
            point.x = 17 * CRes.cos(n) / 1000;
            point.y = 15 * CRes.sin(n) / 1000 - 5;
            point.fRe = CRes.random(2);
            point.frame = CRes.random(4);
            point.limitY = 50;
            this.VecEff.addElement(point);
        }
    }

    private void create_2Kiem_GaiDoc() {
        this.timeAddNum = (byte)4;
        this.fraImgEff = this.typeEffect == 47 ? new FrameImage(87) : new FrameImage(127);
        this.fraImgSubEff = new FrameImage(75);
        if (this.objKill != null) {
            this.y = this.objKill.y;
        }
        if (this.objBeKillMain != null) {
            this.toX = this.objBeKillMain.x;
            this.toY = this.objBeKillMain.y - this.objBeKillMain.hOne / 4;
        }
        this.fRemove = 20;
        int n = this.x * 1000;
        int n2 = this.y * 1000;
        int n3 = 18;
        int n4 = 0;
        int n5 = 0;
        int n6 = 0;
        int n7 = 0;
        int n8 = CRes.angle(this.toX - this.x, this.toY - this.y);
        int n9 = MainObject.getDistance(this.toX - this.x, this.toY - this.y);
        if (n9 > 60) {
            n = this.x * 1000 + (n9 - 50) * CRes.cos(n8);
            n2 = this.y * 1000 + (n9 - 50) * CRes.sin(n8);
        }
        n4 = n3 * CRes.cos(n8);
        n5 = n3 * CRes.sin(n8);
        Point point = new Point();
        point.fRe = 0;
        point.x = n + n4;
        point.y = n2 + n5;
        point.frame = CRes.random(5);
        if (this.Direction == 2) {
            point.dis = 2;
        }
        this.VecEff.addElement(point);
        for (int i = 1; i < 6; ++i) {
            point = new Point();
            point.fRe = i / 2;
            Point point2 = (Point)this.VecEff.elementAt(i - 1);
            point.x = point2.x + n4 + (i % 2 == 0 ? -n6 : n6);
            point.y = n8 > 1575 && n8 <= 3375 ? point2.y + n5 + (i % 2 == 0 ? -n7 : n7) : point2.y + n5 + (i % 2 == 0 ? n7 : -n7);
            point.dis = 0;
            point.frame = CRes.random(5);
            if (this.Direction == 2) {
                point.dis = 2;
            }
            this.VecEff.addElement(point);
        }
    }

    private void create_Monster() {
        this.vMax = 5;
        if (this.objKill != null && this.objBeKillMain != null && MainObject.getDistance(this.objKill.x, this.objBeKillMain.y, this.objKill.y, this.objBeKillMain.y) < 15) {
            this.vMax = 2;
        }
        switch (this.Direction) {
            case 2: {
                this.x -= this.vMax;
                this.x1000 = -this.vMax;
                this.frame = 10;
                this.frameArrow = 2;
                break;
            }
            case 3: {
                this.x += this.vMax;
                this.x1000 = this.vMax;
                this.frame = 6;
                this.frameArrow = 0;
                break;
            }
            case 1: {
                this.y -= this.vMax;
                this.y1000 = -this.vMax;
                this.frame = 33;
                this.frameArrow = 6;
                break;
            }
            case 0: {
                this.y += this.vMax;
                this.y1000 = this.vMax;
                this.frame = 17;
                this.frameArrow = 5;
            }
        }
        switch (this.subType) {
            case 0: {
                this.fraImgEff = new FrameImage(106);
                break;
            }
            case 1: {
                this.fraImgEff = new FrameImage(107);
                break;
            }
            case 2: {
                this.fraImgEff = new FrameImage(108);
                break;
            }
            case 3: {
                this.fraImgEff = new FrameImage(109);
                break;
            }
            case 4: {
                this.fraImgEff = new FrameImage(110);
                break;
            }
            default: {
                this.fraImgEff = new FrameImage(106);
            }
        }
        this.fRemove = 8;
    }

    private void create_PS_Xungkich() {
        this.vMax = 12;
        int n = 0;
        int n2 = 0;
        if (this.objBeKillMain != null) {
            n = this.objBeKillMain.x - this.x;
            n2 = this.objBeKillMain.y - this.y;
        } else {
            n = this.toX - this.x;
            n2 = this.toY - this.y;
        }
        this.create_Speed(n, n2, null);
        this.x1000 = this.x;
        this.y1000 = this.y;
        this.y1000 += 10;
        if (this.Direction == 1) {
            this.y1000 -= 5;
        }
        this.setBeginKillXY1000(10);
    }

    public void updateAngleNormal() {
        if (this.objBeKillMain == null) {
            this.removeEff();
            return;
        }
        int n = this.objBeKillMain.x - this.x;
        int n2 = this.objBeKillMain.y - (this.objBeKillMain.hOne >> 1) - this.y;
        ++this.life;
        if (CRes.abs(n) < 16 && CRes.abs(n2) < 16 || this.life > this.fRemove) {
            this.removeEff();
            return;
        }
        int n3 = CRes.angle(n, n2);
        if (Math.abs(n3 - this.gocT_Arc) < 90 || n * n + n2 * n2 > 4096) {
            this.gocT_Arc = Math.abs(n3 - this.gocT_Arc) < 15 ? n3 : (n3 - this.gocT_Arc >= 0 && n3 - this.gocT_Arc < 180 || n3 - this.gocT_Arc < -180 ? CRes.fixangle(this.gocT_Arc + 15) : CRes.fixangle(this.gocT_Arc - 15));
        }
        if (!this.isSpeedUp && this.va < 8192) {
            this.va += 2048;
        }
        this.vX1000 = this.va * CRes.cos(this.gocT_Arc) >> 10;
        this.vY1000 = this.va * CRes.sin(this.gocT_Arc) >> 10;
        int n4 = (n += this.vX1000) >> 10;
        this.x += n4;
        n &= 0x3FF;
        int n5 = (n2 += this.vY1000) >> 10;
        this.y += n5;
        n2 &= 0x3FF;
    }

    public void updateAngleXP() {
        int n;
        int n2;
        Point point;
        if (this.typeEffect == 96) {
            point = new Point();
            point.x = this.x;
            point.y = this.y;
            this.VecEff.addElement(point);
        }
        if (this.typeEffect == 88) {
            point = new Point();
            point.x = this.x;
            point.y = this.y;
            this.VecEff.addElement(point);
        }
        if (this.objBeKillMain == null || this.objBeKillMain.isRemove || this.f >= this.fRemove || this.objBeKillMain.isStop) {
            this.f = this.fRemove;
            return;
        }
        if (this.typeEffect == 92 || this.typeEffect == 89) {
            n2 = this.objKill.x - this.x;
            n = this.objKill.y - (this.objKill.hOne >> 1) - this.y;
        } else {
            n2 = this.objBeKillMain.x - this.x;
            n = this.objBeKillMain.y - (this.objBeKillMain.hOne >> 1) - this.y;
        }
        ++this.life;
        if (CRes.abs(n2) < 16 && CRes.abs(n) < 16 || this.life > this.fRemove) {
            this.f = this.fRemove;
            return;
        }
        int n3 = CRes.angle(n2, n);
        if (Math.abs(n3 - this.gocT_Arc) < 90 || n2 * n2 + n * n > 4096) {
            this.gocT_Arc = Math.abs(n3 - this.gocT_Arc) < 15 ? n3 : (n3 - this.gocT_Arc >= 0 && n3 - this.gocT_Arc < 180 || n3 - this.gocT_Arc < -180 ? CRes.fixangle(this.gocT_Arc + 15) : CRes.fixangle(this.gocT_Arc - 15));
        }
        if (!this.isSpeedUp && this.va < 8192) {
            this.va += 3096;
        }
        this.vX1000 = this.va * CRes.cos(this.gocT_Arc) >> 10;
        this.vY1000 = this.va * CRes.sin(this.gocT_Arc) >> 10;
        int n4 = (n2 += this.vX1000) >> 10;
        this.x += n4;
        n2 &= 0x3FF;
        int n5 = (n += this.vY1000) >> 10;
        this.y += n5;
        n &= 0x3FF;
        if (this.typeEffect != 88) {
            Point point2 = new Point();
            point2.x = this.x;
            point2.y = this.y;
            this.VecEff.addElement(point2);
        }
    }

    public void updateAngleHut_Mp_Hp() {
        if (this.f >= this.fRemove) {
            this.f = this.fRemove;
            return;
        }
        int n = this.toX - this.x;
        int n2 = this.toY - this.y;
        ++this.life;
        if (CRes.abs(n) < 16 && CRes.abs(n2) < 16 || this.life > this.fRemove) {
            this.f = this.fRemove;
            return;
        }
        int n3 = CRes.angle(n, n2);
        if (Math.abs(n3 - this.gocT_Arc) < 90 || n * n + n2 * n2 > 4096) {
            this.gocT_Arc = Math.abs(n3 - this.gocT_Arc) < 15 ? n3 : (n3 - this.gocT_Arc >= 0 && n3 - this.gocT_Arc < 180 || n3 - this.gocT_Arc < -180 ? CRes.fixangle(this.gocT_Arc + 15) : CRes.fixangle(this.gocT_Arc - 15));
        }
        if (!this.isSpeedUp && this.va < 8192) {
            this.va += 3096;
        }
        this.vX1000 = this.va * CRes.cos(this.gocT_Arc) >> 10;
        this.vY1000 = this.va * CRes.sin(this.gocT_Arc) >> 10;
        int n4 = (n += this.vX1000) >> 10;
        this.x += n4;
        n &= 0x3FF;
        int n5 = (n2 += this.vY1000) >> 10;
        this.y += n5;
        n2 &= 0x3FF;
        Point point = new Point();
        point.x = this.x;
        point.y = this.y;
        this.VecEff.addElement(point);
    }

    public void paint_phuong_hoang(mGraphics mGraphics2, FrameImage frameImage, int n, int n2, int n3) {
        int n4 = frameImage.nFrame / 3;
        switch (n) {
            case 0: {
                frameImage.drawFrame(this.f % n4, n2, n3, 2, 3, mGraphics2);
                break;
            }
            case 1: {
                frameImage.drawFrame(n4 * 2 + this.f % n4, n2, n3, 4, 3, mGraphics2);
                break;
            }
            case 2: {
                frameImage.drawFrame(n4 + this.f % n4, n2, n3, 0, 3, mGraphics2);
                break;
            }
            case 3: {
                frameImage.drawFrame(n4 * 2 + this.f % n4, n2, n3, 5, 3, mGraphics2);
                break;
            }
            case 4: {
                frameImage.drawFrame(this.f % n4, n2, n3, 0, 3, mGraphics2);
                break;
            }
            case 5: {
                frameImage.drawFrame(n4 * 2 + this.f % n4, n2, n3, 0, 3, mGraphics2);
                break;
            }
            case 6: {
                frameImage.drawFrame(n4 + this.f % n4, n2, n3, 1, 3, mGraphics2);
                break;
            }
            case 7: {
                frameImage.drawFrame(n4 * 2 + this.f % n4, n2, n3, 2, 3, mGraphics2);
                break;
            }
        }
    }

    private void paint_Ice_Nova_Effect(mGraphics mGraphics2) {
        if (this.fraImgEff == null) {
            return;
        }
        for (int i = 0; i < this.VecEff.size(); ++i) {
            Point point = (Point)this.VecEff.elementAt(i);
            if (point.f >= this.fRemove) continue;
            this.paint_Bullet(mGraphics2, this.fraImgEff, point.frame, point.x / 1000, point.y / 1000, false);
        }
    }

    private void paint_Poison_Nova_Effect(mGraphics mGraphics2) {
        if (this.fraImgEff == null) {
            return;
        }
        for (int i = 0; i < this.VecEff.size(); ++i) {
            Point point = (Point)this.VecEff.elementAt(i);
            if (point.f >= this.fRemove) continue;
            this.paint_Bullet(mGraphics2, this.fraImgEff, point.frame, point.x / 1000, point.y / 1000, false);
        }
    }

    private void update_Nova_Effect() {
        if (this.f >= this.fRemove) {
            for (int i = 0; i < this.VecEff.size(); ++i) {
                Point point = (Point)this.VecEff.elementAt(i);
                if (this.typeEffect == 90) {
                    GameScreen.addEffectEndKill(10, point.x / 1000, point.y / 1000);
                    GameScreen.addEffectEndKill(15, point.x / 1000, point.y / 1000 + this.objBeKillMain.hOne / 4);
                    continue;
                }
                if (this.typeEffect != 91) continue;
                GameScreen.addEffectEndKill(44, point.x / 1000, point.y / 1000);
            }
            this.removeEff();
            return;
        }
        for (int i = 0; i < this.VecEff.size(); ++i) {
            Point point = (Point)this.VecEff.elementAt(i);
            point.x += point.vx;
            point.y += point.vy;
            int n = GameCanvas.loadmap.getTile(point.x / 1000, point.y / 1000);
            if (n != -1 && n != 1 && MainEffect.isInScreen(point.x / 1000, point.y / 1000, this.fraImgEff.frameWidth, this.fraImgEff.frameHeight)) continue;
            if (this.typeEffect == 90) {
                GameScreen.addEffectEndKill(10, point.x / 1000, point.y / 1000);
                GameScreen.addEffectEndKill(15, point.x / 1000, point.y / 1000 + this.objBeKillMain.hOne / 4);
            } else if (this.typeEffect == 91) {
                GameScreen.addEffectEndKill(44, point.x / 1000, point.y / 1000);
            }
            this.VecEff.removeElement(point);
        }
    }

    public void paint_Bullet(mGraphics mGraphics2, FrameImage frameImage, int n, int n2, int n3, boolean bl) {
        int n4 = frameImage.nFrame / 3;
        int n5 = 3;
        frameImage.drawFrameEffectSkill(n4 * this.mImageBullet[n] + this.f % n4, n2, n3, this.mXoayBullet[n], 3, mGraphics2);
        if (bl) {
            switch (n) {
                case 0: 
                case 1: 
                case 2: 
                case 23: {
                    frameImage.drawFrameEffectSkill(n4 * this.mImageBullet[n] + this.f % n4, n2 + n5, n3 - n5, this.mXoayBullet[n], 3, mGraphics2);
                    frameImage.drawFrameEffectSkill(n4 * this.mImageBullet[n] + this.f % n4, n2 + n5, n3 + n5, this.mXoayBullet[n], 3, mGraphics2);
                    frameImage.drawFrameEffectSkill(n4 * this.mImageBullet[n] + this.f % n4, n2 + 2 * n5, n3, this.mXoayBullet[n], 3, mGraphics2);
                    break;
                }
                case 5: 
                case 6: 
                case 7: 
                case 8: {
                    frameImage.drawFrameEffectSkill(n4 * this.mImageBullet[n] + this.f % n4, n2 + n5, n3 - n5, this.mXoayBullet[n], 3, mGraphics2);
                    frameImage.drawFrameEffectSkill(n4 * this.mImageBullet[n] + this.f % n4, n2 - n5, n3 - n5, this.mXoayBullet[n], 3, mGraphics2);
                    frameImage.drawFrameEffectSkill(n4 * this.mImageBullet[n] + this.f % n4, n2, n3 - 2 * n5, this.mXoayBullet[n], 3, mGraphics2);
                    break;
                }
                case 11: 
                case 12: 
                case 13: 
                case 14: {
                    frameImage.drawFrameEffectSkill(n4 * this.mImageBullet[n] + this.f % n4, n2 - n5, n3 - n5, this.mXoayBullet[n], 3, mGraphics2);
                    frameImage.drawFrameEffectSkill(n4 * this.mImageBullet[n] + this.f % n4, n2 - n5, n3 + n5, this.mXoayBullet[n], 3, mGraphics2);
                    frameImage.drawFrameEffectSkill(n4 * this.mImageBullet[n] + this.f % n4, n2 - 2 * n5, n3, this.mXoayBullet[n], 3, mGraphics2);
                    break;
                }
                case 17: 
                case 18: 
                case 19: 
                case 20: {
                    frameImage.drawFrameEffectSkill(n4 * this.mImageBullet[n] + this.f % n4, n2 + n5, n3 + n5, this.mXoayBullet[n], 3, mGraphics2);
                    frameImage.drawFrameEffectSkill(n4 * this.mImageBullet[n] + this.f % n4, n2 - n5, n3 + n5, this.mXoayBullet[n], 3, mGraphics2);
                    frameImage.drawFrameEffectSkill(n4 * this.mImageBullet[n] + this.f % n4, n2, n3 + 2 * n5, this.mXoayBullet[n], 3, mGraphics2);
                    break;
                }
                case 21: 
                case 22: {
                    frameImage.drawFrameEffectSkill(n4 * this.mImageBullet[n] + this.f % n4, n2 + n5, n3, this.mXoayBullet[n], 3, mGraphics2);
                    frameImage.drawFrameEffectSkill(n4 * this.mImageBullet[n] + this.f % n4, n2, n3 + n5, this.mXoayBullet[n], 3, mGraphics2);
                    frameImage.drawFrameEffectSkill(n4 * this.mImageBullet[n] + this.f % n4, n2 + n5, n3 + n5, this.mXoayBullet[n], 3, mGraphics2);
                    break;
                }
                case 3: 
                case 4: {
                    frameImage.drawFrameEffectSkill(n4 * this.mImageBullet[n] + this.f % n4, n2 + n5, n3, this.mXoayBullet[n], 3, mGraphics2);
                    frameImage.drawFrameEffectSkill(n4 * this.mImageBullet[n] + this.f % n4, n2, n3 - n5, this.mXoayBullet[n], 3, mGraphics2);
                    frameImage.drawFrameEffectSkill(n4 * this.mImageBullet[n] + this.f % n4, n2 + n5, n3 - n5, this.mXoayBullet[n], 3, mGraphics2);
                    break;
                }
                case 9: 
                case 10: {
                    frameImage.drawFrameEffectSkill(n4 * this.mImageBullet[n] + this.f % n4, n2 - n5, n3, this.mXoayBullet[n], 3, mGraphics2);
                    frameImage.drawFrameEffectSkill(n4 * this.mImageBullet[n] + this.f % n4, n2, n3 - n5, this.mXoayBullet[n], 3, mGraphics2);
                    frameImage.drawFrameEffectSkill(n4 * this.mImageBullet[n] + this.f % n4, n2 - n5, n3 - n5, this.mXoayBullet[n], 3, mGraphics2);
                    break;
                }
                case 15: 
                case 16: {
                    frameImage.drawFrameEffectSkill(n4 * this.mImageBullet[n] + this.f % n4, n2 - n5, n3, this.mXoayBullet[n], 3, mGraphics2);
                    frameImage.drawFrameEffectSkill(n4 * this.mImageBullet[n] + this.f % n4, n2, n3 + n5, this.mXoayBullet[n], 3, mGraphics2);
                    frameImage.drawFrameEffectSkill(n4 * this.mImageBullet[n] + this.f % n4, n2 - n5, n3 + n5, this.mXoayBullet[n], 3, mGraphics2);
                    break;
                }
            }
        }
    }

    public void setSendMove(int n, int n2, int n3, int n4) {
        int n5 = GameCanvas.loadmap.getTile(n2, n4);
        if (n5 == 1 || n5 == -1) {
            this.objKill.toX = n2;
            this.objKill.toY = n4;
            this.objKill.x = n2;
            this.objKill.y = n4;
            this.removeEff();
            return;
        }
        if (this.objKill == null || this.objKill != GameScreen.player) {
            this.objKill.toX = n2;
            this.objKill.toY = n4;
            this.objKill.x = n2;
            this.objKill.y = n4;
            return;
        }
        int n6 = 0;
        int n7 = 0;
        int n8 = 0;
        int n9 = 0;
        int n10 = CRes.abs(n - n2);
        int n11 = CRes.abs(n3 - n4);
        if (n10 >= 4) {
            n6 = n > n2 ? -4 : 4;
        }
        if (n11 >= 4) {
            n7 = n3 > n4 ? -4 : 4;
        }
        n8 = n10 / 4;
        n9 = n11 / 4;
        int n12 = n10 > n11 ? n8 : n9;
        for (int i = 0; i < n12; ++i) {
            if (i < n8) {
                n += n6;
            }
            if (i >= n9) continue;
            n3 += n7;
        }
        this.objKill.toX = n2;
        this.objKill.toY = n4;
        this.objKill.x = this.savex;
        this.objKill.y = this.savey;
        this.objKill.xStand = n2;
        this.objKill.yStand = n4;
        this.objKill.xFire = n2;
        this.objKill.yFire = n4;
    }

    public static int setAddEffKillPlus(int[] nArray, MainObject mainObject, MainObject mainObject2, int[] nArray2) {
        int n = -1;
        block6: for (int i = 0; i < nArray.length; ++i) {
            switch (nArray[i]) {
                case 2: 
                case 3: {
                    GameScreen.addEffectKillSubTime(41, mainObject2.ID, mainObject2.typeObject, mainObject.ID, mainObject.typeObject, nArray2[i], mainObject.hp, (byte)(nArray[i] - 2), 3000);
                    GameScreen.addEffectNum("-" + nArray2[i], mainObject2.x, mainObject2.y - mainObject2.hOne / 2, nArray[i] == 2 ? 3 : 4);
                    continue block6;
                }
                case 4: {
                    GameScreen.addEffectKill(6, mainObject2.ID, mainObject2.typeObject, mainObject2.ID, mainObject2.typeObject, nArray2[i], mainObject.hp);
                    if (mainObject == GameScreen.player) {
                        GameScreen.addEffectNum(T.dcchimang, mainObject2.x, mainObject2.y - mainObject2.hOne / 2, 7);
                        n = 7;
                        continue block6;
                    }
                    GameScreen.addEffectNum(T.dcchimang, mainObject2.x, mainObject2.y - mainObject2.hOne / 2, 6);
                    continue block6;
                }
                case 1: {
                    GameScreen.addEffectEndKill(29, mainObject2.x, mainObject2.y - mainObject2.hOne / 2);
                    if (mainObject == GameScreen.player) {
                        GameScreen.addEffectNum(T.dcxuyengiap, mainObject2.x, mainObject2.y - mainObject2.hOne / 2, 7);
                        n = 7;
                        continue block6;
                    }
                    GameScreen.addEffectNum(T.dcxuyengiap, mainObject2.x, mainObject2.y - mainObject2.hOne / 2, 6);
                    continue block6;
                }
                case 5: {
                    GameScreen.addEffectKill(67, mainObject2.ID, mainObject2.typeObject, mainObject.ID, mainObject.typeObject, nArray2[i], mainObject.hp);
                    if (mainObject2 == GameScreen.player) {
                        GameScreen.addEffectNum(T.phandon, mainObject2.x, mainObject2.y - mainObject2.hOne / 2, 7);
                    } else {
                        GameScreen.addEffectNum(T.phandon, mainObject2.x, mainObject2.y - mainObject2.hOne / 2, 6);
                    }
                    if (mainObject.hp > 0 || mainObject.Action == 4) continue block6;
                    mainObject.resetAction();
                    mainObject.Action = 4;
                    mainObject.timedie = GameCanvas.timeNow;
                    GameScreen.addEffectEndKill(11, mainObject.x, mainObject.y);
                }
            }
        }
        return n;
    }

    public static boolean isMultiTarget(int n) {
        return n == 93 || n == 99 || n == 95 || n == 96 || n == 97 || n == 98 || n == 108 || n == 113 || n == 104 || n == 114 || n == 115;
    }

    private void update_Boss_De2() {
        if (this.objBeKillMain != null) {
            if (this.f == 1) {
                LoadMap.timeVibrateScreen = 103;
                GameScreen.addEffectEndKill(9, this.objBeKillMain.x, this.objBeKillMain.y - 2);
                GameScreen.addEffectEndKill_Time(47, this.objBeKillMain.x, this.objBeKillMain.y, this.objBeKillMain.timeBind);
            }
            if (!this.objBeKillMain.isBinded || this.objBeKillMain != null && this.objBeKillMain.isRemove) {
                this.isRemove = true;
            }
        }
    }

    private void update_Boss_Medusa2() {
        if (this.objBeKillMain != null && (this.objBeKillMain.Action == 4 || !this.objBeKillMain.isDongBang || this.objBeKillMain == null || this.objBeKillMain != null && this.objBeKillMain.isRemove)) {
            this.isRemove = true;
        }
    }

    private void update_Boss_De1() {
        for (int i = 0; i < this.VecEff.size(); ++i) {
            Point point = (Point)this.VecEff.elementAt(i);
            if (this.objBeKillMain == null) {
                this.removeEff();
                return;
            }
            int n = this.objBeKillMain.x - point.x;
            int n2 = this.objBeKillMain.y - (this.objBeKillMain.hOne >> 1) - point.y;
            ++this.life;
            if (CRes.abs(n) < 16 && CRes.abs(n2) < 16 || this.life > this.fRemove) {
                this.removeEff();
                GameScreen.addEffectEndKill(45, this.objBeKillMain.x, this.objBeKillMain.y - 20);
                return;
            }
            int n3 = CRes.angle(n, n2);
            if (Math.abs(n3 - this.gocT_Arc) < 90 || n * n + n2 * n2 > 4096) {
                this.gocT_Arc = Math.abs(n3 - this.gocT_Arc) < 15 ? n3 : (n3 - this.gocT_Arc >= 0 && n3 - this.gocT_Arc < 180 || n3 - this.gocT_Arc < -180 ? CRes.fixangle(this.gocT_Arc + 15) : CRes.fixangle(this.gocT_Arc - 15));
            }
            if (!this.isSpeedUp && this.va < 8192) {
                this.va += 2048;
            }
            this.vX1000 = this.va * CRes.cos(this.gocT_Arc) >> 10;
            this.vY1000 = this.va * CRes.sin(this.gocT_Arc) >> 10;
            int n4 = (n += this.vX1000) >> 10;
            point.x += n4;
            n &= 0x3FF;
            int n5 = (n2 += this.vY1000) >> 10;
            point.y += n5;
            n2 &= 0x3FF;
            if (this.f % 2 == 0) {
                GameScreen.addEffectEndKill(9, point.x, point.y);
                GameScreen.addEffectEndKill(46, point.x, point.y - 2);
            }
            ++point.f;
        }
        if (this.f >= this.fRemove) {
            this.removeEff();
        }
    }

    public void setTimeRemove(short s) {
        this.timeRemove = s;
        this.x = this.objBeKillMain.x;
        this.y = this.objBeKillMain.y;
        this.objBeKillMain.vx = 0;
        this.objBeKillMain.vy = 0;
        this.objBeKillMain.toX = this.x;
        this.objBeKillMain.toY = this.y;
        if (this.typeEffect == 101) {
            this.objBeKillMain.isSleep = true;
            this.objBeKillMain.timeSleep = mSystem.currentTimeMillis() + (long)(s * 1000);
        } else if (this.typeEffect == 102) {
            this.objBeKillMain.isStun = true;
            this.objBeKillMain.timeStun = mSystem.currentTimeMillis() + (long)(s * 1000);
        } else if (this.typeEffect == 107) {
            this.objBeKillMain.timeno = mSystem.currentTimeMillis() + (long)(s * 1000);
            this.objBeKillMain.isno = true;
        } else if (this.typeEffect == 103) {
            this.objBeKillMain.isnoBoss84 = true;
            this.objBeKillMain.timenoBoss84 = mSystem.currentTimeMillis() + (long)(s * 1000);
        } else if (this.typeEffect == 100) {
            this.objBeKillMain.isDongBang = true;
            this.objBeKillMain.timeDongBang = mSystem.currentTimeMillis() + (long)(s * 1000);
        } else {
            this.objBeKillMain.isBinded = true;
            this.objBeKillMain.timeBind = mSystem.currentTimeMillis() + (long)(s * 1000);
        }
    }

    public void setObjFrom(short s, byte by) {
        this.objKill = MainObject.get_Object(s, by);
    }

    static {
        colorStar = new int[][]{{-466912, -479160, -1}, {-9732096, -4133256, -1}};
        colorBuffMain = new int[][]{{-4898304, -479160, -1, -1}, {-532951, -479160, -1, -1}, {-7294728, -479160, -1, -1}, {-5207896, -479160, -1, -1}, {-131200, -3420104, -1, -1}};
        posx = new int[][]{{4, 11, 11, 15, 10, 7, 5, 6, 4, 3, 11, 13, 13, 13, 12, 15}, {14, 14, 16, 15, 18, 20, 22, 22, 23, 21, 9, 11, 9, 10, 9, 9}};
        posy = new int[][]{{11, 9, 11, 11, 13, 14, 13, 14, 11, 13, 15, 14, 13, 13, 12, 15}, {9, 12, 12, 14, 14, 15, 13, 11, 13, 14, 9, 11, 9, 10, 9, 9}};
    }
}

