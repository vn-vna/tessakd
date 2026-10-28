/*
 * Decompiled with CFR 0.152.
 */
package GameScreen;

import CLib.TField;
import CLib.mFont;
import CLib.mGraphics;
import CLib.mImage;
import CLib.mSound;
import CLib.mSystem;
import CLib.mVector;
import GameEffect.EffectAuto;
import GameObjects.DelaySkill;
import GameObjects.Item;
import GameObjects.MainBuff;
import GameObjects.MainEvent;
import GameObjects.MainItem;
import GameObjects.MainObject;
import GameObjects.ObjectData;
import GameObjects.ObjectParty;
import GameObjects.Player;
import GameScreen.EventScreen;
import GameScreen.GameScreen;
import GameScreen.MainScreen;
import GameScreen.ShipScr;
import GameScreen.WorldMapScreen;
import InterfaceComponents.ChatTextField;
import InterfaceComponents.MainTabNew;
import Main.GameCanvas;
import Model.AvMain;
import Model.CRes;
import Model.FrameImage;
import Model.MainImage;
import Model.MainTemplateItem;
import Model.Point;
import Model.T;
import Skill.HotKey;
import Skill.MainListSkill;
import Skill.Skill;
import Thread_More.LoadMap;
import Thread_More.MiniMap;
import Thread_More.MiniMapFull_Screen;

public class PaintInfoGameScreen {
    public short idCharLoiDai1 = (short)-1;
    public short idCharLoiDai2 = (short)-1;
    int xPaintInfo = 0;
    int xPaintInfoChar = 0;
    int xmaxInfo = 0;
    int xmaxInfoChar = 0;
    int speedInfo = 2;
    int speedInfoChar = 2;
    int timepaintServer;
    int timePaintInfoChar;
    public int timeInfoCharCline;
    public int maxtimeChar;
    public int ydInfoChar;
    public int hImgInfo = 22;
    boolean vipInfoChar;
    public static int xPointMove = 55;
    public static int yPointMove = GameCanvas.h - 55;
    public static int xMess;
    public static int yMess;
    public static int numMess;
    public static int yBeginInfo;
    public static int wInfoServer;
    public static int winfo18plus;
    public static int wArrowMove;
    public static int wPointArrow;
    public static mImage[] imgMove;
    public static mImage[] imgFire;
    public static mImage[] imgOther;
    public static mImage imgInfoFocus;
    public static mImage imgauto;
    public static mImage imgxp;
    public static mImage imgmove;
    public static mImage imgBackQuick;
    public static FrameImage fraClose;
    public static FrameImage fraFocusIngame;
    public static FrameImage fraBack;
    public static FrameImage fraMenu;
    public static FrameImage fraButton;
    public static FrameImage fraStatusArea;
    public static FrameImage fraCloseMenu;
    public static FrameImage fralevelup;
    public static FrameImage fraButton2;
    public static FrameImage fraContact;
    public static FrameImage fraEvent;
    public static FrameImage[] mfraIconQuick;
    int[] mRotateMove = new int[]{2, 0, 3, 1};
    int[] mKeyMove = new int[]{4, 6, 2, 8};
    public static int[] mKeySkill;
    public static int[] mValueHotKey;
    public static String[] mValueChar;
    int[] mKeyOther = new int[]{100, 101, 102, 103};
    public static int[][] mPosKill;
    public static int[][] mPosMove;
    public static int[][] mPosOther;
    public static int[][] mSizeImgOther;
    public static int timePointer;
    public static int keyPoint;
    public static int timeChange;
    public static int timeNameMap;
    public static int wNameMap;
    public static int vyNameMap;
    public static int yNameMap;
    public static String namemap;
    public static int xPointKill;
    public static int yPointKill;
    public static int wSkill;
    public static int wMainSkill;
    static int gocBegin;
    static int lSkill;
    static int xPaintSkill;
    static int yPaintSkill;
    public String strInfoServer = null;
    public String strInfoCharCline;
    public String strInfoCharServer;
    int indexTab = 0;
    public static boolean isLevelPoint;
    public static boolean isShowInfoAuto;
    public static boolean isPaintInfoFocus;
    public static byte taitho;
    public static int hShowInGame;
    public static boolean isShowInGame;
    public static long timeDoNotClick;
    public static int xFocus;
    static int yFocus;
    static int xParty;
    static int yParty;
    public static int delta;
    public static int[] imgHitWidth;
    public static int[] imgHitHeight;
    public static long timeThachdau;
    public static int WBlackclolor;
    public static int WRedclor;
    int timeEvent = 0;
    int indexEvent;
    int hShowEvent;
    public static int wShowEvent;
    MainEvent eventShow = null;
    private long timeDownChat = -1L;
    private int hClip = 0;
    public short[] posTam;
    int timeMove;
    int xlast;
    int ylast;
    boolean isTouchFocus = true;
    public static mVector vecfocus;
    public static int timeHS;
    public static int curTimeHS;
    public static boolean isCountTime;
    byte[] totalHouse = new byte[]{0, 0, 0, 0};
    short[] totalPlayer = new short[]{0, 0, 0, 0};
    short totalPoint = 0;
    byte[] typePK = new byte[]{1, 4, 5, 2, 0};
    public static FrameImage imgArenaIcon;
    byte[] typePaint = new byte[]{3, 2, 0, 1, 4};
    public boolean isShipMove = false;
    public EffectAuto eff;
    int xPos_minimap = 0;
    int yPos_minimap = 0;
    public static byte paint18plush;
    public static int idicon;
    public static String nameclan;

    public static void loadPaintInfo() {
        wInfoServer = GameCanvas.w * 2 / 3 - 63;
        winfo18plus = GameCanvas.hw - GameCanvas.hw / 3;
        yBeginInfo = GameCanvas.h / 4 - (GameCanvas.isTouch ? 75 : 30);
        if (yBeginInfo < 1) {
            yBeginInfo = 1;
        }
        xFocus = GameCanvas.w - 52;
        yFocus = 0;
        xParty = 2;
        yParty = 60;
        xMess = 70;
        yMess = 45;
        if (GameCanvas.isTouch) {
            PaintInfoGameScreen.loadImagePointer();
            xFocus = GameCanvas.hw;
        }
        wShowEvent = 130;
    }

    public static void reloadmove() {
        mPosMove = mSystem.new_M_Int(4, 2);
        for (int i = 0; i < mPosMove.length; ++i) {
            PaintInfoGameScreen.mPosMove[i][0] = xPointMove + (i < 2 ? -wArrowMove + wArrowMove * 2 * (i % 2) : 0);
            PaintInfoGameScreen.mPosMove[i][1] = yPointMove + (i > 1 ? -wArrowMove + wArrowMove * 2 * (i % 2) : 0);
        }
    }

    public static void loadImagePointer() {
        int n;
        wSkill = 32;
        int n2 = gocBegin;
        for (n = 0; n < mPosKill.length; ++n) {
            PaintInfoGameScreen.mPosKill[n][0] = xPointKill + CRes.cos(CRes.fixangle(n2)) * lSkill / 1000;
            PaintInfoGameScreen.mPosKill[n][1] = yPointKill + CRes.sin(CRes.fixangle(n2)) * lSkill / 1000;
            n2 -= 45;
        }
        xPaintSkill = GameCanvas.w - wSkill * 6;
        yPaintSkill = GameCanvas.h - 24;
        PaintInfoGameScreen.mPosOther[0][0] = 8;
        PaintInfoGameScreen.mPosOther[0][1] = 43;
        PaintInfoGameScreen.mPosOther[1][0] = taitho == 0 ? 8 : 38;
        PaintInfoGameScreen.mPosOther[1][1] = 73;
        PaintInfoGameScreen.mPosOther[2][0] = GameCanvas.w - 27;
        PaintInfoGameScreen.mPosOther[2][1] = GameCanvas.h - 145;
        PaintInfoGameScreen.mPosOther[3][0] = GameCanvas.w - 27;
        PaintInfoGameScreen.mPosOther[3][1] = GameCanvas.h - 175;
        if (mSystem.isIP_GDX) {
            PaintInfoGameScreen.mPosOther[3][0] = GameCanvas.w - 60;
            PaintInfoGameScreen.mPosOther[3][1] = GameCanvas.h - 146;
        }
        PaintInfoGameScreen.mPosOther[4][0] = GameCanvas.hw - 20;
        PaintInfoGameScreen.mPosOther[4][1] = GameCanvas.h - 16;
        PaintInfoGameScreen.setPosTouch();
        xMess = 45;
        yMess = 45;
        imgOther = new mImage[5];
        for (n = 0; n < imgOther.length; ++n) {
            PaintInfoGameScreen.imgOther[n] = mImage.createImage("/point/other_" + n + ".img");
            PaintInfoGameScreen.mSizeImgOther[n][0] = mImage.getImageWidth(PaintInfoGameScreen.imgOther[n].image);
            PaintInfoGameScreen.mSizeImgOther[n][1] = mImage.getImageHeight(PaintInfoGameScreen.imgOther[n].image) / 2;
        }
        imgMove = new mImage[3];
        for (n = 0; n < imgMove.length; ++n) {
            if (n == 1) continue;
            PaintInfoGameScreen.imgMove[n] = mImage.createImage("/point/move_" + n + ".img");
        }
        for (n = 0; n < mPosMove.length; ++n) {
            PaintInfoGameScreen.mPosMove[n][0] = xPointMove + (n < 2 ? -wArrowMove + wArrowMove * 2 * (n % 2) : 0);
            PaintInfoGameScreen.mPosMove[n][1] = yPointMove + (n > 1 ? -wArrowMove + wArrowMove * 2 * (n % 2) : 0);
        }
        imgFire = new mImage[2];
        for (n = 0; n < imgFire.length; ++n) {
            PaintInfoGameScreen.imgFire[n] = mImage.createImage("/point/fire_" + n + ".img");
        }
        fraClose = new FrameImage(mImage.createImage("/point/close.img"), 14, 14);
        fraCloseMenu = new FrameImage(mImage.createImage("/point/closemenu.img"), 21, 21);
        fraBack = new FrameImage(mImage.createImage("/point/buttonback.png"), 57, 30);
        fraMenu = new FrameImage(mImage.createImage("/point/buttonmenu.img"), 32, 32);
        fraButton = new FrameImage(mImage.createImage("/point/button.img"), 80, 30);
        fraButton2 = new FrameImage(mImage.createImage("/point/button2.img"), 60, 19);
        fraContact = new FrameImage(mImage.createImage("/point/contact.img"), 26, 26);
        mfraIconQuick = new FrameImage[11];
        for (n = 0; n < mfraIconQuick.length; ++n) {
            PaintInfoGameScreen.mfraIconQuick[n] = new FrameImage(mImage.createImage("/point/quick_" + n + ".png"), 30, 30);
        }
        imgBackQuick = mImage.createImage("/point/backquick.img");
        imgmove = mImage.createImage("/interface/move.img");
    }

    public static void paintHitscr(mGraphics mGraphics2, boolean bl) {
        int n;
        delta = bl ? 0 : 10;
        mGraphics2.drawRegion(AvMain.imghitScr[0], 0, 0, imgHitWidth[0], imgHitHeight[0], 0, GameCanvas.w - imgHitWidth[0] + delta, -delta, 0, false);
        mGraphics2.drawRegion(AvMain.imghitScr[0], 0, 0, imgHitWidth[0], imgHitHeight[0], 1, GameCanvas.w - imgHitWidth[0] + delta, GameCanvas.h - imgHitHeight[0] + delta, 0, false);
        mGraphics2.drawRegion(AvMain.imghitScr[0], 0, 0, imgHitWidth[0], imgHitHeight[0], 2, -delta, -delta, 0, false);
        mGraphics2.drawRegion(AvMain.imghitScr[0], 0, 0, imgHitWidth[0], imgHitHeight[0], 4, -delta, GameCanvas.h - imgHitHeight[0] + delta, 0, false);
        int n2 = (GameCanvas.w + delta - 2 * imgHitWidth[0]) / imgHitWidth[1] + 1;
        for (n = 0; n < n2; ++n) {
            mGraphics2.drawRegion(AvMain.imghitScr[1], 0, 0, imgHitWidth[1], imgHitHeight[1], 0, imgHitWidth[0] + imgHitWidth[1] * n - delta, -delta, 0, false);
            mGraphics2.drawRegion(AvMain.imghitScr[1], 0, 0, imgHitWidth[1], imgHitHeight[1], 1, imgHitWidth[0] + imgHitWidth[1] * n - delta, GameCanvas.h - imgHitHeight[1] + delta, 0, false);
        }
        n = (GameCanvas.w + delta - 2 * imgHitHeight[0]) / imgHitHeight[2] + 1;
        for (int i = 0; i < n; ++i) {
            mGraphics2.drawRegion(AvMain.imghitScr[2], 0, 0, imgHitWidth[2], imgHitHeight[2], 0, -delta, imgHitHeight[0] + imgHitHeight[2] * i - delta, 0, false);
            mGraphics2.drawRegion(AvMain.imghitScr[2], 0, 0, imgHitWidth[2], imgHitHeight[2], 2, GameCanvas.w - imgHitWidth[2] + delta, imgHitHeight[0] + imgHitHeight[2] * i - delta, 0, false);
        }
    }

    public void paintKillPlayer(mGraphics mGraphics2) {
        if (timeChange == 0) {
            int n = yPaintSkill + hShowInGame;
            if (GameScreen.ObjFocus != null && GameScreen.ObjFocus.typeBoss == 2) {
                for (int i = 0; i < 5; ++i) {
                    mGraphics2.drawImage(AvMain.imgHotKey, xPaintSkill + i * wSkill, n - 5, 0, false);
                    if (!GameCanvas.isTouch && TField.isQwerty) {
                        AvMain.Font3dWhite(mGraphics2, mValueChar[i] + "", xPaintSkill + i * wSkill + 11, n, 2);
                        continue;
                    }
                    AvMain.Font3dWhite(mGraphics2, mValueHotKey[i] + "", xPaintSkill + i * wSkill + 11, n, 2);
                }
                return;
            }
            n = 5 - hShowInGame;
            for (int i = 0; i < Player.mhotkey[0].length; ++i) {
                mGraphics2.drawImage(AvMain.imgHotKey, xPaintSkill + i * wSkill, yPaintSkill - n, 0, false);
                HotKey hotKey = Player.mhotkey[Player.levelTab][i];
                DelaySkill delaySkill = null;
                if (hotKey.type == HotKey.SKILL) {
                    Skill skill = MainListSkill.getSkillFormId(hotKey.id);
                    if (skill != null) {
                        skill.paint(mGraphics2, xPaintSkill + i * wSkill + 11, yPaintSkill - n + 11, 3);
                    }
                    delaySkill = Player.timeDelaySkill[hotKey.id];
                } else if (hotKey.type == HotKey.POTION && MainTemplateItem.isload) {
                    Item item = Item.getItemInventory(4, hotKey.id);
                    if (item != null && item.typePotion < 2) {
                        item.paintItem(mGraphics2, xPaintSkill + i * wSkill + 11, yPaintSkill + 11 - n, MainTabNew.wOneItem, 0, 3);
                        delaySkill = Player.timeDelayPotion[item.typePotion];
                    } else {
                        hotKey.setHotKey(0, HotKey.NULL, (byte)0);
                        MainItem.setAddHotKey((byte)1, false);
                        MainItem.setAddHotKey((byte)0, false);
                    }
                }
                if (hotKey.type != HotKey.NULL && delaySkill != null && delaySkill.limit > 0) {
                    if (delaySkill.value > 0) {
                        int n2 = delaySkill.value * 20 / delaySkill.limit;
                        if (n2 < 1) {
                            n2 = 1;
                        }
                        mGraphics2.drawRegion(AvMain.imgDelaySkill, 0, 0, 20, n2, 0, xPaintSkill + i * wSkill + 1, yPaintSkill + 1 - n, 0, false);
                        int n3 = delaySkill.value / 1000;
                        String string = "";
                        string = n3 == 0 ? "0." + delaySkill.value % 1000 / 100 : "" + n3;
                        mFont.tahoma_7b_white.drawString(mGraphics2, string, xPaintSkill + i * wSkill + 11, yPaintSkill + 5 - n, 2, false);
                    } else if (delaySkill.value > -150) {
                        mGraphics2.setColor(-1118516);
                        mGraphics2.fillRoundRect(xPaintSkill + i * wSkill + 1, yPaintSkill - n + 1, 20, 20, 4, 4, false);
                    }
                }
                if (GameCanvas.isTouch) continue;
                if (TField.isQwerty) {
                    mFont.tahoma_7b_white.drawString(mGraphics2, mValueChar[i] + "", xPaintSkill + i * wSkill + 12, yPaintSkill - n - 11, 2, false);
                    continue;
                }
                mFont.tahoma_7b_white.drawString(mGraphics2, mValueHotKey[i] + "", xPaintSkill + i * wSkill + 12, yPaintSkill - n - 11, 2, false);
            }
        } else {
            this.paintChangeSkill(mGraphics2);
        }
    }

    public void paintBuffPlayer(mGraphics mGraphics2) {
        for (int i = 0; i < GameScreen.player.vecBuff.size(); ++i) {
            MainBuff mainBuff = (MainBuff)GameScreen.player.vecBuff.elementAt(i);
        }
    }

    private void paintChangeSkill(mGraphics mGraphics2) {
        for (int i = 0; i < 10; ++i) {
            Object object;
            int n = -5;
            int n2 = Player.levelTab;
            if (i < 5) {
                n = timeChange * 8;
            } else {
                n = 64 - timeChange * 8;
                n2 = Player.levelTab == 0 ? 1 : 0;
            }
            mGraphics2.drawImage(AvMain.imgHotKey, xPaintSkill + i % 5 * wSkill - 1, n + yPaintSkill - 1, 0, false);
            HotKey hotKey = Player.mhotkey[n2][i % 5];
            if (hotKey.type == HotKey.SKILL) {
                object = MainListSkill.getSkillFormId(hotKey.id);
                ((Skill)object).paint(mGraphics2, xPaintSkill + i % 5 * wSkill + 11, n + yPaintSkill + 11, 3);
                continue;
            }
            if (hotKey.type != HotKey.POTION || (object = Item.getItemInventory(4, hotKey.id)) == null) continue;
            ((Item)object).paintItem(mGraphics2, xPaintSkill + i % 5 * wSkill + 11, n + yPaintSkill + 11, MainTabNew.wOneItem, 0, 3);
        }
    }

    public void paintInfoThachDau(mGraphics mGraphics2, int n, int n2) {
        if (!this.isMapThachdau()) {
            return;
        }
        GameCanvas.resetTrans(mGraphics2);
        if (this.idCharLoiDai1 != -1) {
            MainObject mainObject = GameScreen.findObjByteCat(this.idCharLoiDai1, (byte)0);
            if (mainObject != null) {
                int n3;
                int n4 = GameCanvas.w / 2 - 20;
                int n5 = n4 / (WBlackclolor / 2);
                if (n5 < 0) {
                    n5 = 1;
                }
                if (n5 == 1) {
                    mGraphics2.drawRegion(AvMain.imgcolorhpmp_back, 0, 0, WBlackclolor, 9, 0, n, n2 + 3, 0, false);
                } else {
                    mGraphics2.drawRegion(AvMain.imgcolorhpmp_back, 0, 0, WBlackclolor - 2, 9, 0, n, n2 + 3, 0, true);
                    mGraphics2.drawRegion(AvMain.imgcolorhpmp_back, 2, 0, WBlackclolor - 2, 9, 0, n + n4 - (WBlackclolor - 2) + 2, n2 + 3, 0, true);
                    n3 = (n4 - (WBlackclolor - 2) * 2) / 5;
                    if (n3 <= 0) {
                        n3 = 1;
                    }
                    for (int i = 0; i < n3 + 1; ++i) {
                        mGraphics2.drawRegion(AvMain.imgcolorhpmp_back, 10, 0, 15, 9, 0, n + WBlackclolor - 4 + i * 5, n2 + 3, 0, true);
                    }
                }
                if (mainObject.hp > 0) {
                    long l = mainObject.maxHp;
                    long l2 = mainObject.hp;
                    long l3 = n4;
                    long l4 = l2 * l3;
                    n3 = (int)(l4 / l);
                    int n6 = n4 + 1;
                    mGraphics2.setClip(n, n2 + 4, n6 - (n4 - n3), 7);
                    mGraphics2.saveCanvas();
                    mGraphics2.ClipRec(n, n2 + 4, n6 - (n4 - n3), 7);
                    int n7 = n4 / (WRedclor / 2);
                    if (n7 < 0) {
                        n7 = 1;
                    }
                    if (n7 == 1) {
                        mGraphics2.drawRegion(AvMain.imgcolorhpmp, 0, 0, WRedclor, 7, 0, n + 1, n2 + 4, 0, true);
                    } else {
                        mGraphics2.drawRegion(AvMain.imgcolorhpmp, 0, 0, WRedclor - 2, 7, 0, n + 1, n2 + 4, 0, true);
                        mGraphics2.drawRegion(AvMain.imgcolorhpmp, 2, 0, WRedclor - 2, 7, 0, n + 1 + n4 - (WRedclor - 2), n2 + 4, 0, true);
                        int n8 = (n4 - (WRedclor - 2) * 2) / 5;
                        if (n8 <= 0) {
                            n8 = 1;
                        }
                        for (int i = 0; i < n8 + 1; ++i) {
                            mGraphics2.drawRegion(AvMain.imgcolorhpmp, 10, 0, 15, 7, 0, n + WRedclor - 2 + i * 5, n2 + 4, 0, true);
                        }
                    }
                }
                if (mainObject.mp > 0) {
                    // empty if block
                }
                mGraphics2.restoreCanvas();
                mGraphics2.endClip();
                GameCanvas.resetTrans(mGraphics2);
                AvMain.Font3dColor(mGraphics2, mainObject.name.toUpperCase() + " Lv: " + mainObject.Lv, n + 2, n2 + 24 - 10, 0, (byte)0);
                if (!mainObject.overHP) {
                    mFont.tahoma_7_white.drawString(mGraphics2, mainObject.hp + "/" + mainObject.maxHp, n4 / 2, n2 + 2, 2, true);
                } else {
                    mFont mFont2 = mFont.tahoma_7_white;
                    if (MainObject.countmp > 5) {
                        mFont2 = mFont.tahoma_7_red;
                    }
                    mFont2.drawString(mGraphics2, mainObject.hp + "/" + mainObject.maxHp, n4 / 2, n2 + 2, 2, true);
                }
                if (timeThachdau - mSystem.currentTimeMillis() / 1000L > 0L) {
                    long l = timeThachdau - mSystem.currentTimeMillis() / 1000L;
                    mGraphics2.drawRegion(AvMain.imgBackInfo, 0, 0, 140, 20, 0, GameCanvas.w / 2, n2 + 35, 3, false);
                    mFont.tahoma_7_white.drawString(mGraphics2, T.TimeThachDau + l, GameCanvas.w / 2, n2 + 30, 2, false);
                }
            }
        } else {
            int n9;
            int n10;
            int n11;
            int n12;
            long l;
            long l5;
            long l6;
            int n13;
            int n14 = GameCanvas.w / 2 - 20;
            int n15 = n14 / (WBlackclolor / 2);
            if (n15 < 0) {
                n15 = 1;
            }
            if (n15 == 1) {
                mGraphics2.drawRegion(AvMain.imgcolorhpmp_back, 0, 0, WBlackclolor, 9, 0, n, n2 + 3, 0, false);
                mGraphics2.drawRegion(AvMain.imgcolorhpmp_back, 0, 9, WBlackclolor, 9, 0, n, n2 + 15, 0, false);
            } else {
                mGraphics2.drawRegion(AvMain.imgcolorhpmp_back, 0, 0, WBlackclolor - 2, 9, 0, n, n2 + 3, 0, true);
                mGraphics2.drawRegion(AvMain.imgcolorhpmp_back, 0, 9, WBlackclolor - 2, 9, 0, n, n2 + 15, 0, true);
                mGraphics2.drawRegion(AvMain.imgcolorhpmp_back, 2, 0, WBlackclolor - 2, 9, 0, n + n14 - (WBlackclolor - 2) + 2, n2 + 3, 0, true);
                mGraphics2.drawRegion(AvMain.imgcolorhpmp_back, 2, 9, WBlackclolor - 2, 9, 0, n + n14 - (WBlackclolor - 2) + 2, n2 + 15, 0, true);
                n13 = (n14 - (WBlackclolor - 2) * 2) / 5;
                if (n13 <= 0) {
                    n13 = 1;
                }
                for (int i = 0; i < n13 + 1; ++i) {
                    mGraphics2.drawRegion(AvMain.imgcolorhpmp_back, 10, 0, 15, 9, 0, n + WBlackclolor - 4 + i * 5, n2 + 3, 0, true);
                    mGraphics2.drawRegion(AvMain.imgcolorhpmp_back, 10, 9, 15, 9, 0, n + WBlackclolor - 4 + i * 5, n2 + 15, 0, true);
                }
            }
            if (GameScreen.player.hp > 0) {
                long l7 = GameScreen.player.maxHp;
                l6 = GameScreen.player.hp;
                l5 = n14;
                l = l6 * l5;
                n13 = (int)(l / l7);
                n12 = n14 + 1;
                mGraphics2.setClip(n, n2 + 4, n12 - (n14 - n13), 7);
                mGraphics2.saveCanvas();
                mGraphics2.ClipRec(n, n2 + 4, n12 - (n14 - n13), 7);
                n11 = n14 / (WRedclor / 2);
                if (n11 < 0) {
                    n11 = 1;
                }
                if (n11 == 1) {
                    mGraphics2.drawRegion(AvMain.imgcolorhpmp, 0, 0, WRedclor, 7, 0, n + 1, n2 + 4, 0, true);
                } else {
                    mGraphics2.drawRegion(AvMain.imgcolorhpmp, 0, 0, WRedclor - 2, 7, 0, n + 1, n2 + 4, 0, true);
                    mGraphics2.drawRegion(AvMain.imgcolorhpmp, 2, 0, WRedclor - 2, 7, 0, n + 1 + n14 - (WRedclor - 2), n2 + 4, 0, true);
                    n10 = (n14 - (WRedclor - 2) * 2) / 5;
                    if (n10 <= 0) {
                        n10 = 1;
                    }
                    for (n9 = 0; n9 < n10 + 1; ++n9) {
                        mGraphics2.drawRegion(AvMain.imgcolorhpmp, 10, 0, 15, 7, 0, n + WRedclor - 2 + n9 * 5, n2 + 4, 0, true);
                    }
                }
                mGraphics2.restoreCanvas();
            }
            if (GameScreen.player.mp > 0) {
                long l8 = GameScreen.player.maxMp;
                l6 = GameScreen.player.mp;
                l5 = n14;
                l = l6 * l5;
                n12 = n14 + 2;
                n13 = (int)(l / l8);
                mGraphics2.setClip(n, n2 + 16, n12 - (n14 - n13), 7);
                mGraphics2.saveCanvas();
                mGraphics2.ClipRec(n, n2 + 16, n12 - (n14 - n13), 7);
                n11 = n14 / (WRedclor / 2);
                if (n11 < 0) {
                    n11 = 1;
                }
                if (n11 == 1) {
                    mGraphics2.drawRegion(AvMain.imgcolorhpmp, 0, 7, WRedclor, 7, 0, n + 1, n2 + 16, 0, true);
                } else {
                    mGraphics2.drawRegion(AvMain.imgcolorhpmp, 0, 7, WRedclor - 2, 7, 0, n + 1, n2 + 16, 0, true);
                    mGraphics2.drawRegion(AvMain.imgcolorhpmp, 2, 7, WRedclor - 2, 7, 0, n + 1 + n14 - (WRedclor - 2), n2 + 16, 0, true);
                    n10 = (n14 - (WRedclor - 2) * 2) / 5;
                    if (n10 <= 0) {
                        n10 = 1;
                    }
                    for (n9 = 0; n9 < n10 + 1; ++n9) {
                        mGraphics2.drawRegion(AvMain.imgcolorhpmp, 10, 7, 15, 7, 0, n + WRedclor - 2 + n9 * 5, n2 + 16, 0, true);
                    }
                }
                mGraphics2.restoreCanvas();
            }
            mGraphics2.endClip();
            GameCanvas.resetTrans(mGraphics2);
            AvMain.Font3dColor(mGraphics2, GameScreen.player.name.toUpperCase() + " Lv: " + GameScreen.player.Lv, n + 2, n2 + 24, 0, (byte)0);
            if (!GameScreen.player.overHP) {
                mFont.tahoma_7_white.drawString(mGraphics2, GameScreen.player.hp + "/" + GameScreen.player.maxHp, n14 / 2, n2 + 2, 2, true);
            } else {
                mFont mFont3 = mFont.tahoma_7_white;
                if (MainObject.countmp > 5) {
                    mFont3 = mFont.tahoma_7_red;
                }
                mFont3.drawString(mGraphics2, GameScreen.player.hp + "/" + GameScreen.player.maxHp, n14 / 2, n2 + 2, 2, true);
            }
            if (!GameScreen.player.overMP) {
                mFont.tahoma_7_white.drawString(mGraphics2, GameScreen.player.mp + "/" + GameScreen.player.maxMp, n14 / 2, n2 + 14, 2, false);
            } else {
                mFont mFont4 = mFont.tahoma_7_white;
                if (MainObject.countmp > 5) {
                    mFont4 = mFont.tahoma_7_blue;
                }
                mFont4.drawString(mGraphics2, GameScreen.player.mp + "/" + GameScreen.player.maxMp, n14 / 2, n2 + 14, 2, true);
            }
            if (timeThachdau - mSystem.currentTimeMillis() / 1000L > 0L) {
                long l9 = timeThachdau - mSystem.currentTimeMillis() / 1000L;
                mGraphics2.drawRegion(AvMain.imgBackInfo, 0, 0, 140, 20, 0, GameCanvas.w / 2, n2 + 35, 3, false);
                mFont.tahoma_7_white.drawString(mGraphics2, T.TimeThachDau + l9, GameCanvas.w / 2, n2 + 30, 2, false);
            }
        }
    }

    public void paintInfoThachDauOtherPlayer(mGraphics mGraphics2, int n, int n2) {
        if (!this.isMapThachdau()) {
            return;
        }
        if (this.idCharLoiDai2 != -1) {
            MainObject mainObject = GameScreen.findObjByteCat(this.idCharLoiDai2, (byte)0);
            if (mainObject != null) {
                int n3;
                GameCanvas.resetTrans(mGraphics2);
                int n4 = GameCanvas.w / 2 - 20;
                int n5 = n4 / (WBlackclolor / 2);
                if (n5 < 0) {
                    n5 = 1;
                }
                if (n5 == 1) {
                    mGraphics2.drawRegion(AvMain.imgcolorhpmp_back, 0, 0, WBlackclolor, 9, 0, n, n2 + 3, 0, true);
                } else {
                    mGraphics2.drawRegion(AvMain.imgcolorhpmp_back, 0, 0, WBlackclolor - 2, 9, 0, n, n2 + 3, 0, true);
                    mGraphics2.drawRegion(AvMain.imgcolorhpmp_back, 2, 0, WBlackclolor - 2, 9, 0, GameCanvas.w - WBlackclolor, n2 + 3, 0, true);
                    n3 = (n4 - (WBlackclolor - 2) * 2) / 5;
                    if (n3 <= 0) {
                        n3 = 1;
                    }
                    for (int i = 0; i < n3 + 1; ++i) {
                        mGraphics2.drawRegion(AvMain.imgcolorhpmp_back, 10, 0, 15, 9, 0, n + WBlackclolor - 4 + i * 5, n2 + 3, 0, true);
                    }
                }
                if (mainObject.hp > 0) {
                    long l = mainObject.maxHp;
                    long l2 = mainObject.hp;
                    long l3 = n4;
                    long l4 = l2 * l3;
                    n3 = (int)(l4 / l);
                    mGraphics2.setClip(n + (n4 - n3), n2 + 4, GameCanvas.w / 2 - (n4 - n3), 7);
                    mGraphics2.saveCanvas();
                    mGraphics2.ClipRec(n + (n4 - n3), n2 + 4, GameCanvas.w / 2 - (n4 - n3), 7);
                    int n6 = n4 / (WRedclor / 2);
                    if (n6 < 0) {
                        n6 = 1;
                    }
                    if (n6 == 1) {
                        mGraphics2.drawRegion(AvMain.imgcolorhpmp, 0, 0, WRedclor, 7, 0, n + 1, n2 + 4, 0, true);
                    } else {
                        mGraphics2.drawRegion(AvMain.imgcolorhpmp, 0, 0, WRedclor - 2, 7, 0, n + 1, n2 + 4, 0, true);
                        mGraphics2.drawRegion(AvMain.imgcolorhpmp, 2, 0, WRedclor - 2, 7, 0, GameCanvas.w - WBlackclolor, n2 + 4, 0, true);
                        int n7 = (n4 - (WRedclor - 2) * 2) / 5;
                        if (n7 <= 0) {
                            n7 = 1;
                        }
                        for (int i = 0; i < n7 + 1; ++i) {
                            mGraphics2.drawRegion(AvMain.imgcolorhpmp, 10, 0, 15, 7, 0, n + WRedclor - 2 + i * 5, n2 + 4, 0, true);
                        }
                    }
                }
                if (mainObject.mp <= 0 || mainObject.maxMp > 0) {
                    // empty if block
                }
                mGraphics2.restoreCanvas();
                mGraphics2.endClip();
                GameCanvas.resetTrans(mGraphics2);
                AvMain.Font3dColor(mGraphics2, mainObject.name.toUpperCase() + " Lv: " + mainObject.Lv, n + n4 - 2, n2 + 24 - 10, 1, (byte)0);
                if (!mainObject.overHP) {
                    mFont.tahoma_7_white.drawString(mGraphics2, mainObject.hp + "/" + mainObject.maxHp, n + n4 / 2, n2 + 2, 2, true);
                } else {
                    mFont mFont2 = mFont.tahoma_7_white;
                    if (MainObject.countmp > 5) {
                        mFont2 = mFont.tahoma_7_red;
                    }
                    mFont2.drawString(mGraphics2, mainObject.hp + "/" + mainObject.maxHp, n + n4 / 2, n2 + 2, 2, true);
                }
            }
        } else {
            int n8;
            int n9;
            int n10;
            long l;
            long l5;
            long l6;
            int n11;
            if (GameScreen.ObjFocus == null || GameScreen.ObjFocus != null && GameScreen.ObjFocus.typeObject != 0) {
                return;
            }
            GameCanvas.resetTrans(mGraphics2);
            int n12 = GameCanvas.w / 2 - 20;
            int n13 = n12 / (WBlackclolor / 2);
            if (n13 < 0) {
                n13 = 1;
            }
            if (n13 == 1) {
                mGraphics2.drawRegion(AvMain.imgcolorhpmp_back, 0, 0, WBlackclolor, 9, 0, n, n2 + 3, 0, true);
                mGraphics2.drawRegion(AvMain.imgcolorhpmp_back, 0, 9, WBlackclolor, 9, 0, n, n2 + 15, 0, false);
            } else {
                mGraphics2.drawRegion(AvMain.imgcolorhpmp_back, 0, 0, WBlackclolor - 2, 9, 0, n, n2 + 3, 0, true);
                mGraphics2.drawRegion(AvMain.imgcolorhpmp_back, 0, 9, WBlackclolor - 2, 9, 0, n, n2 + 15, 0, true);
                mGraphics2.drawRegion(AvMain.imgcolorhpmp_back, 2, 0, WBlackclolor - 2, 9, 0, GameCanvas.w - WBlackclolor, n2 + 3, 0, true);
                mGraphics2.drawRegion(AvMain.imgcolorhpmp_back, 2, 9, WBlackclolor - 2, 9, 0, GameCanvas.w - WBlackclolor, n2 + 15, 0, true);
                n11 = (n12 - (WBlackclolor - 2) * 2) / 5;
                if (n11 <= 0) {
                    n11 = 1;
                }
                for (int i = 0; i < n11 + 1; ++i) {
                    mGraphics2.drawRegion(AvMain.imgcolorhpmp_back, 10, 0, 15, 9, 0, n + WBlackclolor - 4 + i * 5, n2 + 3, 0, true);
                    mGraphics2.drawRegion(AvMain.imgcolorhpmp_back, 10, 9, 15, 9, 0, n + WBlackclolor - 4 + i * 5, n2 + 15, 0, true);
                }
            }
            if (GameScreen.ObjFocus.hp > 0) {
                long l7 = GameScreen.ObjFocus.maxHp;
                l6 = GameScreen.ObjFocus.hp;
                l5 = n12;
                l = l6 * l5;
                n11 = (int)(l / l7);
                mGraphics2.setClip(n + (n12 - n11), n2 + 4, GameCanvas.w / 2 - (n12 - n11), 7);
                mGraphics2.saveCanvas();
                mGraphics2.ClipRec(n + (n12 - n11), n2 + 4, GameCanvas.w / 2 - (n12 - n11), 7);
                n10 = n12 / (WRedclor / 2);
                if (n10 < 0) {
                    n10 = 1;
                }
                if (n10 == 1) {
                    mGraphics2.drawRegion(AvMain.imgcolorhpmp, 0, 0, WRedclor, 7, 0, n + 1, n2 + 4, 0, true);
                } else {
                    mGraphics2.drawRegion(AvMain.imgcolorhpmp, 0, 0, WRedclor - 2, 7, 0, n + 1, n2 + 4, 0, true);
                    mGraphics2.drawRegion(AvMain.imgcolorhpmp, 2, 0, WRedclor - 2, 7, 0, GameCanvas.w - WBlackclolor, n2 + 4, 0, true);
                    n9 = (n12 - (WRedclor - 2) * 2) / 5;
                    if (n9 <= 0) {
                        n9 = 1;
                    }
                    for (n8 = 0; n8 < n9 + 1; ++n8) {
                        mGraphics2.drawRegion(AvMain.imgcolorhpmp, 10, 0, 15, 7, 0, n + WRedclor - 2 + n8 * 5, n2 + 4, 0, true);
                    }
                }
                mGraphics2.restoreCanvas();
            }
            if (GameScreen.ObjFocus.mp > 0 && GameScreen.ObjFocus.maxMp > 0) {
                long l8 = GameScreen.ObjFocus.maxMp;
                l6 = GameScreen.ObjFocus.mp;
                l5 = n12;
                l = l6 * l5;
                n11 = (int)(l / l8);
                mGraphics2.setClip(n + (n12 - n11), n2 + 16, GameCanvas.w / 2 - (n12 - n11), 7);
                mGraphics2.saveCanvas();
                mGraphics2.ClipRec(n + (n12 - n11), n2 + 16, GameCanvas.w / 2 - (n12 - n11), 7);
                n10 = n12 / (WRedclor / 2);
                if (n10 < 0) {
                    n10 = 1;
                }
                if (n10 == 1) {
                    mGraphics2.drawRegion(AvMain.imgcolorhpmp, 0, 7, WRedclor, 7, 0, n + 1, n2 + 16, 0, true);
                } else {
                    mGraphics2.drawRegion(AvMain.imgcolorhpmp, 0, 7, WRedclor - 2, 7, 0, n + 1, n2 + 16, 0, true);
                    mGraphics2.drawRegion(AvMain.imgcolorhpmp, 2, 7, WRedclor - 2, 7, 0, GameCanvas.w - WBlackclolor, n2 + 16, 0, true);
                    n9 = (n12 - (WRedclor - 2) * 2) / 5;
                    if (n9 <= 0) {
                        n9 = 1;
                    }
                    for (n8 = 0; n8 < n9 + 1; ++n8) {
                        mGraphics2.drawRegion(AvMain.imgcolorhpmp, 10, 7, 15, 7, 0, n + WRedclor - 2 + n8 * 5, n2 + 16, 0, true);
                    }
                }
                mGraphics2.restoreCanvas();
            }
            mGraphics2.endClip();
            GameCanvas.resetTrans(mGraphics2);
            AvMain.Font3dColor(mGraphics2, GameScreen.ObjFocus.name.toUpperCase() + " Lv: " + GameScreen.ObjFocus.Lv, n + n12 - 2, n2 + 24, 1, (byte)0);
            if (!GameScreen.ObjFocus.overHP) {
                mFont.tahoma_7_white.drawString(mGraphics2, GameScreen.ObjFocus.hp + "/" + GameScreen.ObjFocus.maxHp, n + n12 / 2, n2 + 2, 2, true);
            } else {
                mFont mFont3 = mFont.tahoma_7_white;
                if (MainObject.countmp > 5) {
                    mFont3 = mFont.tahoma_7_red;
                }
                mFont3.drawString(mGraphics2, GameScreen.ObjFocus.hp + "/" + GameScreen.ObjFocus.maxHp, n + n12 / 2, n2 + 2, 2, true);
            }
            if (!GameScreen.ObjFocus.overMP) {
                mFont.tahoma_7_white.drawString(mGraphics2, GameScreen.ObjFocus.mp + "/" + GameScreen.ObjFocus.maxMp, n + n12 / 2, n2 + 14, 2, false);
            } else {
                mFont mFont4 = mFont.tahoma_7_white;
                if (MainObject.countmp > 5) {
                    mFont4 = mFont.tahoma_7_blue;
                }
                mFont4.drawString(mGraphics2, GameScreen.ObjFocus.mp + "/" + GameScreen.ObjFocus.maxMp, n + n12 / 2, n2 + 14, 2, true);
            }
        }
    }

    public void paintInfoPlayer(mGraphics mGraphics2, int n, int n2, boolean bl, mFont mFont2) {
        mFont mFont3;
        int n3;
        if (this.isMapThachdau()) {
            return;
        }
        if (bl) {
            mGraphics2.drawRegion(AvMain.imgInfo, 0, 0, 16, 42, 0, n + 1, n2 + 2, 0, false);
            mGraphics2.drawRegion(AvMain.imgInfo, 0, 84, 16, 42, 0, n + 96, n2 + 2, 24, false);
            for (n3 = 0; n3 < 4; ++n3) {
                mGraphics2.drawRegion(AvMain.imgInfo, 0, 42, 16, 42, 0, n + 17 + 16 * n3, n2 + 2, 0, false);
            }
            n += 8;
            n2 += 4;
        }
        mGraphics2.drawImage(AvMain.imghpmp, n + 2, n2 + 3, 0, false);
        mGraphics2.drawRegion(AvMain.imgcolorhpmp_back, 0, 0, 62, 9, 0, n + 19, n2 + 3, 0, false);
        mGraphics2.drawRegion(AvMain.imgcolorhpmp_back, 0, 9, 62, 9, 0, n + 19, n2 + 15, 0, false);
        n3 = 0;
        int n4 = 0;
        if (GameScreen.player.hp > 0) {
            n3 = GameScreen.player.hp * 60 / GameScreen.player.maxHp;
            if (n3 <= 0) {
                n3 = 1;
            } else if (n3 > 60) {
                n3 = 60;
            }
            mGraphics2.drawRegion(AvMain.imgcolorhpmp, 0, 0, n3, 7, 0, n + 20, n2 + 4, 0, false);
        }
        if (GameScreen.player.mp > 0) {
            n4 = GameScreen.player.mp * 60 / GameScreen.player.maxMp;
            if (n4 <= 0) {
                n4 = 1;
            } else if (n4 > 60) {
                n4 = 60;
            }
            mGraphics2.drawRegion(AvMain.imgcolorhpmp, 0, 7, n4, 7, 0, n + 20, n2 + 16, 0, false);
        }
        mFont2.drawString(mGraphics2, "Lv." + GameScreen.player.Lv + " + " + GameScreen.player.phantramLv / 10 + "," + GameScreen.player.phantramLv % 10 + "%", n + 3, n2 + 24, 0, false);
        int n5 = 0;
        if (GameScreen.player.phantramLv > 0) {
            n5 = GameScreen.player.phantramLv / 10 * 77 / 100;
            mGraphics2.setColor(-13263058);
            mGraphics2.fillRect(n + 3, n2 + 35, n5, 2, false);
        }
        if (!GameScreen.player.overHP) {
            mFont.tahoma_7_white.drawString(mGraphics2, GameScreen.player.hp + "/" + GameScreen.player.maxHp, n + 50, n2 + 2, 2, false);
        } else {
            mFont3 = mFont.tahoma_7_white;
            if (MainObject.countmp > 5) {
                mFont3 = mFont.tahoma_7_red;
            }
            mFont3.drawString(mGraphics2, GameScreen.player.hp + "/" + GameScreen.player.maxHp, n + 50, n2 + 2, 2, false);
        }
        if (!GameScreen.player.overMP) {
            mFont.tahoma_7_white.drawString(mGraphics2, GameScreen.player.mp + "/" + GameScreen.player.maxMp, n + 50, n2 + 14, 2, false);
        } else {
            mFont3 = mFont.tahoma_7_white;
            if (MainObject.countmp > 5) {
                mFont3 = mFont.tahoma_7_blue;
            }
            mFont3.drawString(mGraphics2, GameScreen.player.mp + "/" + GameScreen.player.maxMp, n + 50, n2 + 14, 2, false);
        }
    }

    public void paintInfoChar(mGraphics mGraphics2) {
        int n;
        GameCanvas.resetTrans(mGraphics2);
        int n2 = yBeginInfo;
        int n3 = 0;
        if (GameCanvas.isTouch) {
            n3 = 25;
        }
        if (this.strInfoServer != null) {
            mGraphics2.setClip(GameCanvas.hw - wInfoServer / 2, n2, wInfoServer - n3, 20);
            mGraphics2.saveCanvas();
            mGraphics2.ClipRec(GameCanvas.hw - wInfoServer / 2, n2, wInfoServer - n3, 20);
            for (n = 0; n < wInfoServer / 140 + 1; ++n) {
                if (n == wInfoServer / 140) {
                    mGraphics2.drawRegion(AvMain.imgBackInfo, 0, 0, wInfoServer % 140, 20, 0, GameCanvas.hw - wInfoServer / 2 + n * 140, n2, 0, false);
                    continue;
                }
                mGraphics2.drawImage(AvMain.imgBackInfo, GameCanvas.hw - wInfoServer / 2 + n * 140, n2, 0, false);
            }
            mFont.tahoma_7_yellow.drawString(mGraphics2, this.strInfoServer, GameCanvas.hw + wInfoServer / 2 - this.xPaintInfo, n2 + 4, 0, true);
            n2 += this.hImgInfo;
            mGraphics2.restoreCanvas();
            mGraphics2.endClip();
        }
        if (this.strInfoCharServer != null) {
            mGraphics2.setClip(GameCanvas.hw - wInfoServer / 2, n2, wInfoServer - n3, 20);
            mGraphics2.saveCanvas();
            mGraphics2.ClipRec(GameCanvas.hw - wInfoServer / 2, n2, wInfoServer - n3, 20);
            for (n = 0; n < wInfoServer / 140 + 1; ++n) {
                if (n == wInfoServer / 140) {
                    mGraphics2.drawRegion(AvMain.imgBackInfo, 0, 0, wInfoServer % 140, 20, 0, GameCanvas.hw - wInfoServer / 2 + n * 140, n2, 0, false);
                    continue;
                }
                mGraphics2.drawImage(AvMain.imgBackInfo, GameCanvas.hw - wInfoServer / 2 + n * 140, n2, 0, false);
            }
            mFont.tahoma_7b_white.drawString(mGraphics2, this.strInfoCharServer, GameCanvas.hw + wInfoServer / 2 - this.xPaintInfoChar, n2 + 4, 0, true);
            n2 += this.hImgInfo;
            mGraphics2.restoreCanvas();
            mGraphics2.endClip();
        }
        if (this.strInfoCharCline != null) {
            mGraphics2.setClip(GameCanvas.hw - 70, n2, 140, 20);
            mGraphics2.saveCanvas();
            mGraphics2.ClipRec(GameCanvas.hw - 70, n2, 140, 20);
            mGraphics2.drawImage(AvMain.imgBackInfo, GameCanvas.hw - 70, n2 + this.ydInfoChar, 0, false);
            mFont.tahoma_7_white.drawString(mGraphics2, this.strInfoCharCline, GameCanvas.hw, n2 + 4 + this.ydInfoChar, 2, true);
            mGraphics2.restoreCanvas();
            mGraphics2.endClip();
        }
        GameCanvas.resetTrans(mGraphics2);
    }

    public void PaintIconPlayer(mGraphics mGraphics2) {
        int n = 102;
        int n2 = 8 - hShowInGame;
        if (GameCanvas.isSmallScreen) {
            n = 90;
            n2 = 7 - hShowInGame;
        }
        if (Player.diemTiemNang > 0 && !this.isMapThachdau()) {
            fralevelup.drawFrame(GameCanvas.gameTick / 4 % 2, n, n2, 0, 3, mGraphics2);
        }
        if (Player.diemKyNang > 0 && !this.isMapThachdau()) {
            fralevelup.drawFrame(2 + GameCanvas.gameTick / 4 % 2, n, n2 + 14, 0, 3, mGraphics2);
        }
        if (Player.isAutoFire > -1) {
            mGraphics2.drawImage(imgauto, n + 1, n2 + 28, 3, false);
        }
        if (Player.typeX2 == 1) {
            if (GameCanvas.gameTick % 200 < 100) {
                mGraphics2.drawImage(imgxp, n + 1, n2 + 42, 3, false);
            } else {
                mFont.tahoma_7_green.drawString(mGraphics2, this.getTimex2(), n - 7, n2 + 36, 0, false);
            }
        }
    }

    public String getTimex2() {
        if (Player.timeX2 > 0 && (GameCanvas.timeNow - Player.timeSetX2) / 1000L > 60L) {
            Player.timeSetX2 += 60000L;
            --Player.timeX2;
        }
        return PaintInfoGameScreen.getStringTime(Player.timeX2);
    }

    public static String getStringTime(int n) {
        if (n >= 60) {
            return n / 60 + "h" + n % 60 + "'";
        }
        return n + "'";
    }

    public static String getStringTime2(int n) {
        String string = n / 60 + "'";
        return string;
    }

    public void updateInfoServer() {
        if (GameScreen.VecInfoServer.size() > 0) {
            if (this.strInfoServer == null) {
                int n;
                this.strInfoServer = (String)GameScreen.VecInfoServer.elementAt(0);
                if (this.strInfoServer != null && this.strInfoServer.trim().length() > 0) {
                    GameCanvas.msgchat.addNewChat(T.tinden, T.text2kenhthegioi, this.strInfoServer, (byte)1, false);
                }
                this.speedInfo = (n = GameScreen.VecInfoServer.size()) < 2 ? 2 : (n < 5 ? 3 : 4);
                this.xPaintInfo = 0;
                this.xmaxInfo = mFont.tahoma_7_white.getWidth(this.strInfoServer) + wInfoServer;
                if (this.xmaxInfo < wInfoServer) {
                    this.xmaxInfo = wInfoServer;
                }
            } else {
                if (this.xPaintInfo >= this.xmaxInfo) {
                    ++this.timepaintServer;
                    this.timepaintServer = 0;
                    this.strInfoServer = null;
                    GameScreen.VecInfoServer.removeElementAt(0);
                }
                this.xPaintInfo += this.speedInfo;
            }
        }
    }

    public void updateInfoCharServer() {
        if (GameScreen.VecInfoChar.size() > 0) {
            if (this.strInfoCharServer == null) {
                this.strInfoCharServer = (String)GameScreen.VecInfoChar.elementAt(0);
                int n = GameScreen.VecInfoChar.size();
                this.speedInfoChar = n < 2 ? 2 : (n < 5 ? 3 : 4);
                this.xPaintInfoChar = 0;
                this.xmaxInfoChar = mFont.tahoma_7b_white.getWidth(this.strInfoCharServer) + wInfoServer;
                if (this.xmaxInfoChar < wInfoServer) {
                    this.xmaxInfoChar = wInfoServer;
                }
            } else {
                if (this.xPaintInfoChar >= this.xmaxInfoChar) {
                    this.timePaintInfoChar = 0;
                    this.strInfoCharServer = null;
                    GameScreen.VecInfoChar.removeElementAt(0);
                }
                this.xPaintInfoChar += this.speedInfoChar;
            }
        }
    }

    public void paintInfoFocus(mGraphics mGraphics2) {
        if (this.isMapThachdau()) {
            return;
        }
        if (!isPaintInfoFocus && isLevelPoint) {
            return;
        }
        if (GameScreen.ObjFocus != null) {
            int n;
            int n2 = 0;
            int n3 = yFocus - hShowInGame + 2;
            n2 = xFocus;
            if (this.isMapchienthanh()) {
                n2 = GameCanvas.w - 62;
                n3 = yFocus - hShowInGame + 2 + GameCanvas.hText;
            }
            MainObject mainObject = GameScreen.ObjFocus;
            if (mainObject.typeObject == 3) {
                AvMain.Font3dColor(mGraphics2, mainObject.name, n2 + 48, n3 + 2, 1, mainObject.colorName);
            } else if (mainObject.typeObject == 0 || mainObject.typeObject == 1) {
                if (mainObject.myClan != null && mainObject.typeSpec == 0) {
                    n = mFont.tahoma_7b_white.getWidth(mainObject.name) + 1;
                    mainObject.paintIconClan(mGraphics2, n2 + 48 - n / 2, n3 + 7, 2);
                    n3 += 12;
                }
                if (mainObject.typeObject == 1) {
                    n = mainObject.colorName;
                    AvMain.Font3dColorAndColor(mGraphics2, mainObject.name, n2 + 48, n3 + 2, 1, (byte)7, (byte)n);
                } else {
                    AvMain.Font3dWhite(mGraphics2, mainObject.name, n2 + 48, n3 + 2, 1);
                }
                AvMain.Font3dColorAndColor(mGraphics2, T.Lv + mainObject.Lv, n2 + 48, (n3 += 10) + 2, 1, (byte)7, mainObject.colorName);
            } else {
                AvMain.Font3dWhite(mGraphics2, mainObject.name, n2 + 48, n3 + 2, 1);
            }
            if (mainObject.typeObject == 0 || mainObject.typeObject == 1 || mainObject.typeObject == 2) {
                mGraphics2.drawImage(AvMain.imgcolorhpSmall_back, n2 - 4, n3 + 14, 0, false);
                n = 0;
                if (mainObject.maxHp > 0 && mainObject.hp > 0) {
                    long l = 50L;
                    long l2 = mainObject.hp;
                    long l3 = l * l2;
                    n = (int)(l3 / (long)mainObject.maxHp);
                    if (n <= 0) {
                        n = 1;
                    } else if (n > 50) {
                        n = 50;
                    }
                    mGraphics2.drawRegion(AvMain.imgcolorhpSmall, 0, 0, n + 1, 7, 0, n2 - 4, n3 + 14, 0, false);
                }
                mFont.tahoma_7_white.drawString(mGraphics2, mainObject.hp + "/" + mainObject.maxHp, n2 + 48, n3 + 20, 1, false);
            }
        }
    }

    public void updateInfoChar() {
        if (this.strInfoCharCline != null) {
            ++this.timeInfoCharCline;
            if (this.timeInfoCharCline >= 120) {
                this.timeInfoCharCline = 0;
                this.strInfoCharCline = null;
            }
            if (this.ydInfoChar > 0) {
                this.ydInfoChar -= 2;
            }
        }
        if (!GameCanvas.isTouch && timeChange > 0 && ++timeChange > 8) {
            timeChange = 0;
            if (++Player.levelTab > 2) {
                Player.levelTab = 0;
            }
        }
        if (timeNameMap > 0) {
            if (timeNameMap == 20) {
                vyNameMap = 10;
            } else if (timeNameMap < 20 && vyNameMap > -20) {
                vyNameMap -= 4;
            }
            if (yNameMap > -30) {
                yNameMap += vyNameMap;
            } else {
                timeNameMap = 0;
            }
        }
    }

    public void updateEvent() {
        if (EventScreen.vecEventShow.size() > 0) {
            if (this.eventShow == null) {
                this.eventShow = (MainEvent)EventScreen.vecEventShow.elementAt(0);
                this.timeEvent = 100;
                this.hShowEvent = 0;
            } else {
                --this.timeEvent;
                if (this.timeEvent <= 0) {
                    this.eventShow = null;
                    EventScreen.vecEventShow.removeElementAt(0);
                }
                if (this.hShowEvent < 35) {
                    this.hShowEvent += 10;
                }
                if (this.hShowEvent > 35) {
                    this.hShowEvent = 35;
                }
            }
            if (GameCanvas.isPointSelect(GameCanvas.hw - wShowEvent / 2, 0, wShowEvent, 35)) {
                MainEvent mainEvent = EventScreen.setEvent(this.eventShow.nameEvent, (byte)this.eventShow.IDCmd);
                if (mainEvent != null) {
                    GameCanvas.mevent.doEvent(false, mainEvent);
                }
                if (this.timeEvent > 40) {
                    this.timeEvent = 40;
                }
                GameCanvas.isPointerSelect = false;
            }
            if (GameCanvas.keyMyHold[11]) {
                GameCanvas.clearKeyHold(11);
                int n = EventScreen.setIndexEvent(this.eventShow.nameEvent, (byte)this.eventShow.IDCmd);
                if (n >= 0) {
                    GameCanvas.mevent.idSelect = n;
                }
                GameCanvas.mevent.init();
                GameCanvas.mevent.Show(GameCanvas.currentScreen);
            }
        } else if (this.hShowEvent > 0) {
            this.hShowEvent -= 20;
        }
    }

    public void paintShowEvent(mGraphics mGraphics2) {
        if (this.eventShow != null || this.hShowEvent > 0) {
            GameCanvas.resetTrans(mGraphics2);
            int n = GameCanvas.hw - wShowEvent / 2;
            int n2 = 2;
            if (GameCanvas.gameTick % 16 > 7) {
                n2 = 12;
            }
            AvMain.paintDialogNew(mGraphics2, n, -5, wShowEvent, this.hShowEvent + 5, n2);
            if (this.eventShow != null) {
                fraEvent.drawFrame(this.eventShow.IDCmd * 2, n + 20, -35 + this.hShowEvent + 17 + 3, 0, 3, mGraphics2);
                mFont.tahoma_7b_white.drawString(mGraphics2, this.eventShow.nameEvent, n + 35, -35 + this.hShowEvent + 5, 0, false);
                mFont.tahoma_7_white.drawString(mGraphics2, this.eventShow.contentEvent, n + 42, -35 + this.hShowEvent + 18, 0, false);
            }
        }
    }

    public void paintPoiterAll(mGraphics mGraphics2) {
        int n;
        int n2;
        int n3;
        int n4;
        int n5;
        if (!GameCanvas.isTouch) {
            return;
        }
        for (n5 = 0; n5 < mPosOther.length; ++n5) {
            if (n5 == 3 && isLevelPoint) continue;
            n4 = 0;
            if (timePointer > 0 && keyPoint == 100 + n5) {
                n4 = 1;
            }
            if (GameScreen.player.Action == 4) continue;
            n3 = mPosOther[n5][0];
            n2 = mPosOther[n5][1];
            if (n5 == 0) {
                n2 -= hShowInGame;
            } else if (n5 == 1) {
                n3 -= hShowInGame;
            } else if (n5 == 4) {
                n2 += hShowInGame;
            } else {
                n3 += hShowInGame;
            }
            n = 1;
            if (n5 == 0 && GameScreen.player.Lv > 10) {
                n = 0;
            }
            if (n == 0) continue;
            if (n5 != 1) {
                mGraphics2.drawRegion(imgOther[n5], 0, n4 * mSizeImgOther[n5][1], mSizeImgOther[n5][0], mSizeImgOther[n5][1], 0, n3, n2, 0, false);
                continue;
            }
            mGraphics2.drawRegion(imgOther[n5], 0, 0, mSizeImgOther[n5][0], mSizeImgOther[n5][1], 0, n3, n2, 0, true);
            if (this.timeDownChat != -1L && mSystem.currentTimeMillis() - this.timeDownChat >= 100L) {
                if (GameCanvas.gameTick % 2 == 0) {
                    ++this.hClip;
                }
                if (this.hClip >= mSizeImgOther[n5][1]) {
                    this.hClip = mSizeImgOther[n5][1];
                }
            }
            mGraphics2.setClip(n3, n2, mSizeImgOther[n5][0], this.hClip);
            mGraphics2.saveCanvas();
            mGraphics2.ClipRec(n3, n2, mSizeImgOther[n5][0], this.hClip);
            mGraphics2.drawRegion(imgOther[n5], 0, mSizeImgOther[n5][1], mSizeImgOther[n5][0], mSizeImgOther[n5][1], 0, n3, n2, 0, true);
            mGraphics2.restoreCanvas();
            GameCanvas.resetTrans(mGraphics2);
        }
        if (GameScreen.player.Action == 4) {
            return;
        }
        if (isLevelPoint) {
            this.paintKillPlayer(mGraphics2);
        } else {
            mGraphics2.drawImage(imgMove[0], xPointMove - hShowInGame, yPointMove, 3, false);
            for (n5 = 0; n5 < 4; ++n5) {
                if (timePointer <= 0 || this.mKeyMove[n5] != keyPoint) continue;
                mGraphics2.drawRegion(imgMove[2], 0, 0, 32, 56, this.mRotateMove[n5] + (n5 > 1 ? 4 : 0), mPosMove[n5][0] - hShowInGame, mPosMove[n5][1], 3, false);
            }
            n5 = 0;
            if (timePointer > 0 && keyPoint == 5) {
                n5 = 1;
            }
            mGraphics2.drawImage(AvMain.imgHotKey, xPointKill + hShowInGame, yPointKill, 3, false);
            if (timeChange == 0) {
                int n6;
                Item item;
                if (GameScreen.ObjFocus != null && GameScreen.ObjFocus.typeBoss == 2) {
                    for (n4 = 0; n4 < 5; ++n4) {
                        n3 = 0;
                        if (timePointer > 0 && keyPoint == mValueHotKey[n4]) {
                            n3 = 1;
                        }
                        n2 = 0;
                        n = 0;
                        if (n4 == 2) {
                            n2 = xPointKill + hShowInGame;
                            n = yPointKill;
                        } else {
                            n2 = mPosKill[n4 - (n4 > 2 ? 1 : 0)][0] + hShowInGame;
                            n = mPosKill[n4 - (n4 > 2 ? 1 : 0)][1];
                        }
                        mGraphics2.drawRegion(imgFire[1], 0, n3, 50, 50, 0, n2, n, 3, false);
                        mGraphics2.drawImage(AvMain.imgHotKey, n2, n, 3, false);
                        AvMain.Font3dWhite(mGraphics2, mValueHotKey[n4] + "", n2, n - 5, 2);
                    }
                    return;
                }
                HotKey hotKey = Player.mhotkey[Player.levelTab][2];
                if (GameScreen.player.checkGiaoTiep()) {
                    mGraphics2.drawImage(AvMain.imgicongt, xPointKill + hShowInGame, yPointKill, 3, false);
                } else if (hotKey != null && hotKey.type == HotKey.SKILL) {
                    Skill skill = MainListSkill.getSkillFormId(hotKey.id);
                    if (skill != null) {
                        skill.paint(mGraphics2, xPointKill + hShowInGame, yPointKill, 3);
                    }
                } else if (hotKey != null && hotKey.type == HotKey.POTION && (item = Item.getItemInventory(4, hotKey.id)) != null) {
                    item.paintItem(mGraphics2, xPointKill + hShowInGame, yPointKill, MainTabNew.wOneItem, 0, 3);
                }
                for (n6 = 0; n6 < mPosKill.length; ++n6) {
                    Object object;
                    n2 = 0;
                    if (timePointer > 0 && keyPoint == mKeySkill[n6]) {
                        n2 = 1;
                    }
                    mGraphics2.drawRegion(imgFire[1], 0, n2 * 50, 50, 50, 0, mPosKill[n6][0] + hShowInGame, mPosKill[n6][1], 3, false);
                    mGraphics2.drawImage(AvMain.imgHotKey, mPosKill[n6][0] + hShowInGame, mPosKill[n6][1], 3, false);
                    hotKey = Player.mhotkey[Player.levelTab][n6 + (n6 > 1 ? 1 : 0)];
                    if (hotKey != null && hotKey.type == HotKey.SKILL) {
                        object = MainListSkill.getSkillFormId(hotKey.id);
                        if (object == null) continue;
                        ((Skill)object).paint(mGraphics2, mPosKill[n6][0] + hShowInGame, mPosKill[n6][1], 3);
                        continue;
                    }
                    if (hotKey == null || hotKey.type != HotKey.POTION || !MainTemplateItem.isload) continue;
                    object = Item.getItemInventory(4, hotKey.id);
                    if (object != null) {
                        if (!MainTemplateItem.isload) continue;
                        ((Item)object).paintItem(mGraphics2, mPosKill[n6][0] + hShowInGame, mPosKill[n6][1], MainTabNew.wOneItem, 0, 3);
                        continue;
                    }
                    hotKey.setHotKey(0, HotKey.NULL, (byte)0);
                    MainItem.setAddHotKey((byte)1, false);
                    MainItem.setAddHotKey((byte)0, false);
                }
                for (n6 = 0; n6 < 5; ++n6) {
                    Item item2;
                    HotKey hotKey2 = Player.mhotkey[Player.levelTab][n6];
                    if (hotKey2 == null || hotKey2.type == HotKey.NULL) continue;
                    int n7 = 0;
                    int n8 = 0;
                    if (n6 == 2) {
                        n7 = xPointKill + hShowInGame;
                        n8 = yPointKill;
                    } else {
                        n7 = mPosKill[n6 - (n6 > 2 ? 1 : 0)][0] + hShowInGame;
                        n8 = mPosKill[n6 - (n6 > 2 ? 1 : 0)][1];
                    }
                    DelaySkill delaySkill = null;
                    if (hotKey2 != null && hotKey2.type == HotKey.SKILL) {
                        delaySkill = Player.timeDelaySkill[hotKey2.id];
                    } else if (hotKey2 != null && hotKey2.type == HotKey.POTION && MainTemplateItem.isload && (item2 = Item.getItemInventory(4, hotKey2.id)) != null && item2.typePotion < 2) {
                        delaySkill = Player.timeDelayPotion[item2.typePotion];
                    }
                    if (delaySkill == null || delaySkill.limit <= 0) continue;
                    if (delaySkill.value > 0) {
                        int n9 = delaySkill.value * 20 / delaySkill.limit;
                        if (n9 < 1) {
                            n9 = 1;
                        }
                        mGraphics2.drawRegion(AvMain.imgDelaySkill, 0, 0, 20, n9, 0, n7 - 10, n8 - 10, 0, false);
                        int n10 = delaySkill.value / 1000;
                        String string = "";
                        string = n10 == 0 ? "0." + delaySkill.value % 1000 / 100 : "" + n10;
                        mFont.tahoma_7b_white.drawString(mGraphics2, string, n7, n8 - 5, 2, false);
                        continue;
                    }
                    if (delaySkill.value <= -150) continue;
                    mGraphics2.setColor(-1118516);
                    mGraphics2.fillRoundRect(n7 - 10, n8 - 10, 20, 20, 4, 4, false);
                }
            } else {
                this.paintChangeTab(mGraphics2);
            }
            mGraphics2.drawRegion(imgFire[0], 0, n5 * 50, 50, 50, 0, xPointKill + hShowInGame, yPointKill, 3, false);
        }
    }

    public void updatePoiterAll() {
        boolean bl;
        block39: {
            block40: {
                Object object;
                int n;
                block42: {
                    block41: {
                        if (timePointer > 0) {
                            --timePointer;
                        }
                        if (timeChange > 0 && ++timeChange > 6) {
                            timeChange = 0;
                            if (++Player.levelTab > 2) {
                                Player.levelTab = 0;
                            }
                        }
                        if (LoadMap.isShowEffAuto == LoadMap.EFF_PHOBANG_END) {
                            return;
                        }
                        bl = true;
                        if (!GameCanvas.isPointerSelect) break block40;
                        if (!isLevelPoint) break block41;
                        if (!GameCanvas.isPoint(xPaintSkill + 11 - wSkill / 2, yPaintSkill + 11 - wSkill / 2, 5 * wSkill, wSkill)) break block42;
                        n = (GameCanvas.px - xPaintSkill + 11) / wSkill;
                        GameCanvas.isPointerSelect = false;
                        if (n >= 0 && n < Player.mhotkey[Player.levelTab].length) {
                            object = Player.mhotkey[Player.levelTab][n];
                            if (isLevelPoint) {
                                if (((HotKey)object).type == HotKey.SKILL) {
                                    if (GameScreen.ObjFocus != null) {
                                        GameScreen.player.setActionHotKey(n, false);
                                    }
                                } else {
                                    keyPoint = n == 2 ? 5 : mKeySkill[n > 1 ? n - 1 : n];
                                    GameCanvas.keyMyPressed[20 + PaintInfoGameScreen.keyPoint] = true;
                                }
                            }
                            timePointer = 3;
                            bl = false;
                        }
                        break block42;
                    }
                    if (GameCanvas.isPoint(xPointKill - wMainSkill / 2, yPointKill - wMainSkill / 2, wMainSkill, wMainSkill)) {
                        GameCanvas.isPointerSelect = false;
                        keyPoint = 5;
                        timePointer = 3;
                        GameCanvas.keyMyPressed[25] = true;
                        GameCanvas.keyMyPressed[5] = true;
                        bl = false;
                    } else {
                        for (n = 0; n < mPosKill.length; ++n) {
                            if (!GameCanvas.isPoint(mPosKill[n][0] - wSkill / 2, mPosKill[n][1] - wSkill / 2, wSkill, wSkill)) continue;
                            GameCanvas.isPointerSelect = false;
                            keyPoint = mKeySkill[n];
                            GameCanvas.keyMyPressed[20 + PaintInfoGameScreen.keyPoint] = true;
                            timePointer = 3;
                            bl = false;
                            break;
                        }
                    }
                }
                if (GameCanvas.isPointerSelect) {
                    for (n = 0; n < mPosOther.length; ++n) {
                        if (n == 3 && isLevelPoint || !GameCanvas.isPoint(mPosOther[n][0] - 2, mPosOther[n][1] - 2, mSizeImgOther[n][0] + 4, mSizeImgOther[n][1] + 4)) continue;
                        GameCanvas.isPointerSelect = false;
                        if (n == 1 && this.timeDownChat == -1L) {
                            this.timeDownChat = mSystem.currentTimeMillis();
                        }
                        this.selectPointer(n);
                        bl = false;
                        break;
                    }
                    if (GameCanvas.isPoint(MainTabNew.gI().xChar, MainTabNew.gI().yChar, 90, 35)) {
                        GameCanvas.isPointerSelect = false;
                        this.selectPointer(0);
                        bl = false;
                        return;
                    }
                    if (GameCanvas.isPoint(xMess - 4, yMess - 4, 24, 20)) {
                        GameCanvas.isPointerSelect = false;
                        this.selectPointer(-1);
                    }
                    if (GameCanvas.isPoint(GameCanvas.w - 50, GameCanvas.minimap.maxY * MiniMap.wMini - 8, 50, 30)) {
                        GameCanvas.isPointerSelect = false;
                        this.selectPointer(-2);
                    } else if (GameCanvas.isPoint(GameCanvas.w - GameCanvas.minimap.maxX * MiniMap.wMini, 0, GameCanvas.minimap.maxX * MiniMap.wMini, GameCanvas.minimap.maxY * MiniMap.wMini) && !this.isMapThachdau()) {
                        GameCanvas.isPointerSelect = false;
                        this.selectPointer(-4);
                    }
                    if (GameCanvas.isPoint(95, 0, 24, 40)) {
                        GameCanvas.isPointerSelect = false;
                        this.selectPointer(-3);
                    }
                    for (n = 0; n < LoadMap.vecPointChange.size(); ++n) {
                        object = (Point)LoadMap.vecPointChange.elementAt(n);
                        int n2 = ((Point)object).x - MainScreen.cameraMain.xCam;
                        int n3 = ((Point)object).y - MainScreen.cameraMain.yCam;
                        if (!GameCanvas.isPoint(n2 - 12, n3 - 12, 24, 24)) continue;
                        GameScreen.player.toX = GameScreen.player.x;
                        GameScreen.player.toY = GameScreen.player.y;
                        this.posTam = GameCanvas.game.updateFindRoad(((Point)object).x / LoadMap.wTile, ((Point)object).y / LoadMap.wTile, GameScreen.player.x / LoadMap.wTile, GameScreen.player.y / LoadMap.wTile, 16);
                        if (this.posTam != null && this.posTam.length > 16) {
                            this.posTam = null;
                        }
                        GameScreen.player.posTransRoad = this.posTam;
                        Player.xFocus = ((Point)object).x;
                        Player.yFocus = ((Point)object).y;
                        Player.timeFocus = 9;
                        GameCanvas.isPointerSelect = false;
                        if (Player.isAutoFire != 1) break block39;
                        Player.setCurAutoFire();
                        break block39;
                    }
                }
                break block39;
            }
            if (!isLevelPoint) {
                if (GameCanvas.isPointerDown || GameCanvas.isPointerMove) {
                    int n;
                    for (n = 0; n < mPosOther.length; ++n) {
                        if (!GameCanvas.isPoint(mPosOther[n][0] - 4, mPosOther[n][1] - 4, mSizeImgOther[n][0] + 8, mSizeImgOther[n][1] + 8)) continue;
                        if (n == 1 && this.timeDownChat == -1L) {
                            this.timeDownChat = mSystem.currentTimeMillis();
                        }
                        keyPoint = 100 + n;
                        timePointer = 3;
                        break;
                    }
                    if (GameCanvas.isPointLast(xPointMove - 2 * wArrowMove, yPointMove - 2 * wArrowMove, wArrowMove * 4, wArrowMove * 4)) {
                        n = CRes.angle(GameCanvas.px - xPointMove, GameCanvas.py - yPointMove);
                        int n4 = 0;
                        n4 = n > 45 && n <= 135 ? 3 : (n > 135 && n <= 225 ? 0 : (n > 225 && n <= 315 ? 2 : 1));
                        GameCanvas.clearKeyHold();
                        GameCanvas.isPointerDown = true;
                        GameCanvas.isPointerSelect = false;
                        keyPoint = this.mKeyMove[n4];
                        GameCanvas.keyMyHold[PaintInfoGameScreen.keyPoint] = true;
                        timePointer = 3;
                        bl = false;
                        if (Player.isAutoFire == 1) {
                            Player.setCurAutoFire();
                        }
                    }
                }
            } else if (isLevelPoint && (GameCanvas.isPointerDown || GameCanvas.isPointerMove)) {
                for (int i = 0; i < mPosOther.length; ++i) {
                    if (!GameCanvas.isPoint(mPosOther[i][0] - 4, mPosOther[i][1] - 4, mSizeImgOther[i][0] + 8, mSizeImgOther[i][1] + 8)) continue;
                    if (i == 1 && this.timeDownChat == -1L) {
                        this.timeDownChat = mSystem.currentTimeMillis();
                    }
                    keyPoint = 100 + i;
                    timePointer = 3;
                    break;
                }
            }
        }
        if (bl) {
            this.updatePointMoveIngame();
        }
        if (isLevelPoint && GameCanvas.currentScreen == GameCanvas.game) {
            if (GameCanvas.isPointerMove) {
                if (!(GameScreen.isMoveCamera || CRes.abs(GameCanvas.px - GameCanvas.pxLast) <= 36 && CRes.abs(GameCanvas.py - GameCanvas.pyLast) <= 36)) {
                    GameScreen.isMoveCamera = true;
                }
                GameScreen.xMoveCam = GameCanvas.px - GameCanvas.pxLast;
                GameScreen.yMoveCam = GameCanvas.py - GameCanvas.pyLast;
                GameScreen.timeResetCam = 40;
            } else if (GameCanvas.isPointerDown) {
                GameScreen.xCur = MainScreen.cameraMain.xCam;
                GameScreen.yCur = MainScreen.cameraMain.yCam;
                GameScreen.xMoveCam = 0;
                GameScreen.yMoveCam = 0;
            }
        }
    }

    public void updatePointMoveIngame() {
        if (GameCanvas.isPointerSelect) {
            if (!isLevelPoint && (GameCanvas.isPoint(xPointKill - lSkill - 25, yPointKill - lSkill - 25, lSkill * 2 + 50, lSkill * 2 + 50) || GameCanvas.isPoint(xPointMove - wArrowMove - 30, yPointMove - wArrowMove - 30, wArrowMove * 2 + 60, wArrowMove * 2 + 60)) && GameScreen.player.Action != 4) {
                GameCanvas.isPointerSelect = false;
                GameCanvas.isPointerDown = false;
                return;
            }
            int n = GameCanvas.px + MainScreen.cameraMain.xCam;
            int n2 = GameCanvas.py + MainScreen.cameraMain.yCam;
            MainObject mainObject = null;
            if (!(GameScreen.ObjFocus != null && GameScreen.ObjFocus.typeBoss == 2 || MainObject.getDistance(n, n2, GameScreen.player.x, GameScreen.player.y) > GameScreen.player.wFocus - 15 && GameScreen.player.Action != 4 || (mainObject = this.setObjectNear(n, n2)) == null || mainObject.typeObject == 1 || Player.isAutoFire != 1)) {
                Player.setCurAutoFire();
            }
            if (this.isTouchFocus && GameCanvas.isTouch) {
                if (mainObject != null) {
                    if (!mainObject.canFocus()) {
                        return;
                    }
                    if (PaintInfoGameScreen.isMarket(GameCanvas.loadmap.idMap) && !mainObject.isSelling() && mainObject.typeObject == 0) {
                        return;
                    }
                    mainObject.timeStand = 5;
                    GameScreen.ObjFocus = mainObject;
                    GameCanvas.isPointerSelect = false;
                    if (MainObject.getDistance(mainObject.x, mainObject.y, GameScreen.player.x, GameScreen.player.y) <= GameScreen.player.wFocus) {
                        GameScreen.player.setPointFocus();
                        isPaintInfoFocus = true;
                        GameScreen.addEffectKill(68, GameScreen.player.ID, (byte)0, GameScreen.ObjFocus.ID, GameScreen.ObjFocus.typeObject, 0, GameScreen.ObjFocus.hp, (byte)(GameScreen.ObjFocus.typeObject != 1 ? 1 : 0));
                        this.posTam = null;
                    }
                    if (GameCanvas.isPointerSelect) {
                        int n3 = GameCanvas.loadmap.getTile(n, n2);
                        if (n3 != -1 && n3 != 1) {
                            GameScreen.player.toX = GameScreen.player.x;
                            GameScreen.player.toY = GameScreen.player.y;
                            this.posTam = GameCanvas.game.updateFindRoad(n / LoadMap.wTile, n2 / LoadMap.wTile, GameScreen.player.x / LoadMap.wTile, GameScreen.player.y / LoadMap.wTile, 100);
                            if (this.posTam != null && this.posTam.length > 100) {
                                this.posTam = null;
                            }
                            this.timeMove = 3;
                            GameCanvas.isPointerSelect = false;
                            Player.xFocus = n;
                            Player.yFocus = n2;
                        } else {
                            this.posTam = null;
                        }
                    }
                } else {
                    if (GameScreen.player.Action == 4) {
                        return;
                    }
                    int n4 = GameCanvas.loadmap.getTile(n, n2);
                    if (n4 != -1 && n4 != 1) {
                        GameScreen.player.toX = GameScreen.player.x;
                        GameScreen.player.toY = GameScreen.player.y;
                        this.posTam = GameCanvas.game.updateFindRoad(n / LoadMap.wTile, n2 / LoadMap.wTile, GameScreen.player.x / LoadMap.wTile, GameScreen.player.y / LoadMap.wTile, 100);
                        if (this.posTam != null && this.posTam.length > 100) {
                            this.posTam = null;
                        }
                        this.timeMove = GameScreen.player.posTransRoad != null ? 1 : 3;
                        GameCanvas.isPointerSelect = false;
                        Player.xFocus = n;
                        Player.yFocus = n2;
                    } else {
                        this.posTam = null;
                        GameCanvas.isPointerSelect = false;
                    }
                }
            } else if (!(isLevelPoint || mainObject == null || mainObject.typeObject == 9 || mainObject.typeObject == 10 || mainObject.isLuaThieng() || mainObject.isDongBang)) {
                vecfocus.removeAllElements();
                if (GameScreen.ObjFocus == mainObject) {
                    if (Player.mhotkey[Player.levelTab][2].type == HotKey.SKILL) {
                        GameCanvas.keyMyPressed[25] = true;
                        GameCanvas.keyMyPressed[5] = true;
                    }
                    this.timeMove = 0;
                    this.posTam = null;
                } else {
                    if (!mainObject.canFocus()) {
                        return;
                    }
                    mainObject.timeStand = 5;
                    GameScreen.ObjFocus = mainObject;
                }
                GameCanvas.isPointerSelect = false;
            }
        }
        if (isLevelPoint && this.timeMove > 0) {
            if (this.timeMove == 1 && this.posTam != null && GameScreen.player.Action != 4 && GameScreen.player.Action != 2 && GameScreen.player.currentQuest == null) {
                GameScreen.player.xStopMove = 0;
                GameScreen.player.yStopMove = 0;
                if (GameScreen.player.posTransRoad != null) {
                    GameScreen.player.countAutoMove = 1;
                }
                GameScreen.player.resetMove();
                GameScreen.player.posTransRoad = this.posTam;
                this.posTam = null;
                Player.timeFocus = 9;
                if (Player.timeResetAuto <= 0 && Player.isAutoFire == 1) {
                    Player.setCurAutoFire();
                }
            }
            --this.timeMove;
        }
    }

    public MainObject setObjectNear(int n, int n2) {
        MainObject mainObject = null;
        MainObject mainObject2 = null;
        int n3 = 40;
        boolean bl = false;
        for (int i = 0; i < GameScreen.Vecplayers.size(); ++i) {
            int n4;
            MainObject mainObject3 = (MainObject)GameScreen.Vecplayers.elementAt(i);
            if (!GameScreen.infoGame.isMapArena(GameCanvas.loadmap.idMap) ? mainObject3 == null || mainObject3 == GameScreen.player || mainObject3.typeObject == 9 || mainObject3.isDongBang || mainObject3.typeObject == 10 || mainObject3.isThacNuoc() || mainObject3.isLuaThieng() : (mainObject3.typePk == GameScreen.player.typePk || mainObject3 == null || mainObject3 == GameScreen.player || mainObject3.typeObject == 9 || mainObject3.isLuaThieng() || mainObject3.typeObject == 10 || mainObject3.isDongBang) && mainObject3.typeObject != 2) continue;
            if (mainObject3.Action == 4 && mainObject3.typeObject == 1 || (n4 = MainObject.getDistance(n, n2, mainObject3.x, mainObject3.y - mainObject3.hOne / 4)) >= n3 && (!GameCanvas.loadmap.mapLang() || mainObject3.typeObject != 2 || n4 >= 40 || !Player.isFocusNPC)) continue;
            if (!bl) {
                bl = true;
                mainObject2 = mainObject3;
            }
            boolean bl2 = true;
            for (int j = 0; j < vecfocus.size(); ++j) {
                MainObject mainObject4 = (MainObject)vecfocus.elementAt(j);
                if (mainObject4 == null || mainObject3.ID != mainObject4.ID) continue;
                bl2 = false;
                break;
            }
            if (bl2) {
                mainObject = mainObject3;
                n3 = n4;
            }
            if (mainObject3.typeObject != 2 || !Player.isFocusNPC) continue;
            vecfocus.addElement(mainObject);
            Player.isFocusNPC = false;
            Player.timeFocusNPC = 0;
            return mainObject;
        }
        if (bl && mainObject == null) {
            vecfocus.removeAllElements();
            mainObject = mainObject2;
        }
        if (mainObject != null) {
            vecfocus.addElement(mainObject);
        } else {
            vecfocus.removeAllElements();
        }
        return mainObject;
    }

    public void paintChangeTab(mGraphics mGraphics2) {
        int n = gocBegin;
        if (timeChange > 0) {
            n -= timeChange * 30;
        }
        for (int i = 0; i < 8; ++i) {
            Object object;
            HotKey hotKey;
            int n2 = xPointKill + CRes.cos(CRes.fixangle(n)) * lSkill / 1000 + hShowInGame;
            int n3 = yPointKill + CRes.sin(CRes.fixangle(n)) * lSkill / 1000;
            mGraphics2.drawImage(AvMain.imgHotKey, n2, n3, 3, false);
            int n4 = Player.levelTab;
            if (i > 3) {
                n4 = Player.levelTab == 0 ? 1 : 0;
            }
            if ((hotKey = Player.mhotkey[n4][i % 4 + (i % 4 > 1 ? 1 : 0)]) != null && hotKey.type == HotKey.SKILL) {
                object = MainListSkill.getSkillFormId(hotKey.id);
                ((Skill)object).paint(mGraphics2, n2, n3, 3);
            } else if (hotKey != null && hotKey.type == HotKey.POTION && (object = Item.getItemInventory(4, hotKey.id)) != null) {
                ((Item)object).paintItem(mGraphics2, n2, n3, MainTabNew.wOneItem, 0, 3);
            }
            n -= 45;
        }
    }

    public void selectPointer(int n) {
        switch (n) {
            case -4: {
                if (this.isMapchienthanh()) break;
                MiniMapFull_Screen.gI().Show();
                mSound.playSound(41, mSound.volumeSound);
                break;
            }
            case -3: {
                if (Player.diemTiemNang > 0) {
                    mSound.playSound(41, mSound.volumeSound);
                    GameCanvas.AllInfo.Show(GameCanvas.currentScreen);
                    GameCanvas.AllInfo.selectTab = 2;
                    return;
                }
                if (Player.diemKyNang <= 0) break;
                mSound.playSound(41, mSound.volumeSound);
                GameCanvas.AllInfo.Show(GameCanvas.currentScreen);
                GameCanvas.AllInfo.selectTab = 3;
                return;
            }
            case -2: {
                break;
            }
            case -1: {
                if (numMess <= 0) break;
                mSound.playSound(41, mSound.volumeSound);
                GameCanvas.mevent.init();
                GameCanvas.mevent.Show(GameCanvas.currentScreen);
                break;
            }
            case 0: {
                if (GameScreen.isMoveCamera) {
                    GameScreen.isMoveCamera = false;
                    break;
                }
                GameScreen.gI().cmdMenu.perform();
                break;
            }
            case 1: {
                mSound.playSound(41, mSound.volumeSound);
                if (mSystem.currentTimeMillis() - this.timeDownChat <= 500L) {
                    ChatTextField.gI().setChat();
                    this.timeDownChat = -1L;
                    this.hClip = 0;
                    break;
                }
                GameScreen.gI().cmdQuickChat.perform();
                this.timeDownChat = -1L;
                this.hClip = 0;
                break;
            }
            case 2: {
                if (GameScreen.ObjFocus != null && GameScreen.ObjFocus.typeBoss == 2 || timeChange != 0) break;
                mSound.playSound(41, mSound.volumeSound);
                timeChange = 1;
                break;
            }
            case 3: {
                if (GameScreen.ObjFocus == null) break;
                Player.cmdNextFocus.perform();
                break;
            }
            case 4: {
                if (ChatTextField.isShow) break;
                GameCanvas.menu2.setAt_Quick();
                break;
            }
            case 5: {
                mSound.playSound(41, mSound.volumeSound);
                GameScreen.gI().cmdQuickChat.perform();
            }
        }
    }

    public void paintParty(mGraphics mGraphics2) {
        if (Player.party != null) {
            for (int i = 0; i < Player.party.vecPartys.size(); ++i) {
                ObjectParty objectParty = (ObjectParty)Player.party.vecPartys.elementAt(i);
                if (objectParty.name.compareTo(GameScreen.player.name) != 0 && !objectParty.isRemove) continue;
            }
        }
    }

    public void paintNameMap(mGraphics mGraphics2) {
        if (timeNameMap > 0) {
            --timeNameMap;
            AvMain.paintDialog(mGraphics2, GameCanvas.hw - wNameMap / 2 - 10, yNameMap, wNameMap + 20, 35, 12);
            mFont.tahoma_7b_white.drawString(mGraphics2, namemap, GameCanvas.hw, yNameMap + 7, 2, false);
            mFont.tahoma_7_white.drawString(mGraphics2, "- " + T.Area + LoadMap.getAreaPaint() + " -", GameCanvas.hw, yNameMap + 20, 2, false);
        }
    }

    public void paintNameServer(mGraphics mGraphics2, String string) {
        if (timeNameMap > 0) {
            --timeNameMap;
            AvMain.paintDialog(mGraphics2, GameCanvas.hw - wNameMap / 2 - 10, yNameMap, wNameMap + 20, 20, 12);
            mFont.tahoma_7b_white.drawString(mGraphics2, string, GameCanvas.hw, yNameMap + 4, 2, false);
        }
    }

    public void setNameServer(String string) {
        timeNameMap = 80;
        wNameMap = mFont.tahoma_7b_white.getWidth(string);
        yNameMap = GameCanvas.h / 8;
        vyNameMap = 0;
        if (wNameMap < 80) {
            wNameMap = 80;
        }
    }

    public static void setNameMap() {
        timeNameMap = 80;
        namemap = "map";
        if (WorldMapScreen.namePos != null && GameCanvas.loadmap.idMap < WorldMapScreen.namePos.length) {
            namemap = WorldMapScreen.namePos[GameCanvas.loadmap.idMap];
        }
        wNameMap = mFont.tahoma_7b_white.getWidth(namemap);
        yNameMap = GameCanvas.h / 8;
        vyNameMap = 0;
        if (wNameMap < 80) {
            wNameMap = 80;
        }
    }

    public void updateShowIngame() {
        if (isShowInGame) {
            if (GameCanvas.isSmallScreen) {
                if ((GameCanvas.timeNow - timeDoNotClick) / 1000L > 15L) {
                    isShowInGame = false;
                }
            } else if ((GameCanvas.timeNow - timeDoNotClick) / 1000L > 2L) {
                isShowInGame = false;
            }
            if (hShowInGame > 0 && (hShowInGame -= 20) < 0) {
                hShowInGame = 0;
            }
        } else if (hShowInGame < 100) {
            hShowInGame += 10;
        }
    }

    public static void setPosTouch() {
        if (isLevelPoint) {
            PaintInfoGameScreen.mPosOther[2][1] = GameCanvas.h - 31;
            PaintInfoGameScreen.mPosOther[4][0] = 0;
        } else {
            PaintInfoGameScreen.mPosOther[2][1] = GameCanvas.h - 145;
            PaintInfoGameScreen.mPosOther[4][0] = GameCanvas.hw - 20;
        }
    }

    public boolean isMapCountTime(int n) {
        return n == 59 || n == 57 || n == 55 || n == 53;
    }

    public boolean isMapChienTruong(int n) {
        return n == 61 || n == 60 || n == 58 || n == 56 || n == 54;
    }

    public boolean isMapPetcage(int n) {
        return n == 50;
    }

    public boolean isMapLight(int n) {
        return n == 51 || n >= 37 && n <= 41;
    }

    public boolean isMapDark(int n) {
        return n == 52 || n == 62 || n >= 42 && n <= 45;
    }

    public boolean isMapArena(int n) {
        return this.isMapChienTruong(n) || this.isMapCountTime(n);
    }

    public boolean isMapThachdau() {
        return GameScreen.isShowHoiSinh;
    }

    public boolean isMapchienthanh() {
        return GameCanvas.loadmap.idMap == 83 || GameCanvas.loadmap.idMap == 84 || GameCanvas.loadmap.idMap == 85 || GameCanvas.loadmap.idMap == 86 || GameCanvas.loadmap.idMap == 87;
    }

    public boolean ismapHouse(int n) {
        return n == 60 || n == 58 || n == 56 || n == 54;
    }

    public void setCountTimeHS(int n, long l, int n2) {
        if (this.isMapCountTime(n)) {
            isCountTime = true;
            curTimeHS = (int)(l / 1000L + (long)n2);
        } else {
            isCountTime = false;
            timeHS = 0;
        }
    }

    public void countTimeHS() {
        if (isCountTime) {
            if (timeHS >= 0) {
                timeHS = (int)((long)curTimeHS - mSystem.currentTimeMillis() / 1000L);
            } else {
                isCountTime = false;
                timeHS = 0;
            }
        }
    }

    public void paintTimeHS(mGraphics mGraphics2) {
        if (isCountTime && timeHS >= 0) {
            AvMain.Font3dWhite(mGraphics2, T.backBattlefield + timeHS + "s", 70 - GameCanvas.w, mPosOther[0][1] + 15, 0);
        }
    }

    public void setSttArena(byte by, byte by2, short s) {
        if (by == -1) {
            return;
        }
        int n = 0;
        n = by == 4 ? 1 : (by == 5 ? 2 : (by == 2 ? 3 : 0));
        if (by2 != -1) {
            this.totalHouse[n] = by2;
        }
        if (s != -1) {
            this.totalPlayer[n] = s;
        }
    }

    public void paintSttArena(mGraphics mGraphics2, int n, int n2, int n3, int n4) {
        AvMain.Font3dWhite(mGraphics2, T.infoArena, n - 5, n2 - 2 * n4, 0);
        imgArenaIcon.drawFrame(this.typePaint[4], n, n2 - n4, 0, 3, mGraphics2);
        AvMain.Font3dWhite(mGraphics2, "" + GameScreen.player.markKiller, n + n3, n2 - n4 - 3, 0);
        for (int i = 0; i < this.totalHouse.length; ++i) {
            if (this.totalHouse[i] > 0) {
                AvMain.fraPk.drawFrame(this.typePK[i] * 3 + GameCanvas.gameTick / 3 % 3, n, n2 + i * n4 + 2, 0, 3, mGraphics2);
            } else {
                AvMain.fraPk.drawFrame(this.typePK[4] * 3 + GameCanvas.gameTick / 3 % 3, n, n2 + i * n4, 0, 3, mGraphics2);
            }
            imgArenaIcon.drawFrame(this.typePaint[i], n + n3, n2 + i * n4 - 2, 0, 3, mGraphics2);
            AvMain.Font3dWhite(mGraphics2, "" + this.totalHouse[i], n + n3 + 10, n2 + i * n4 - 3, 0);
            mGraphics2.drawRegion(MainTabNew.imgTab[3], 0, 32, 16, 16, 0, n + 3 * n3, n2 + i * n4, 3, false);
            AvMain.Font3dWhite(mGraphics2, "" + this.totalPlayer[i], n + 3 * n3 + 10, n2 + i * n4 - 3, 0);
        }
    }

    public void resetShip() {
        this.eff = null;
        this.isShipMove = false;
    }

    public void paintShip(mGraphics mGraphics2) {
        if (GameCanvas.loadmap.idMap == 19) {
            if (this.eff == null) {
                this.eff = new EffectAuto(50, 1080, 96, 0, 0, 1, 0);
            } else {
                this.eff.update();
                if (!this.isShipMove) {
                    this.eff.paint(mGraphics2);
                } else {
                    this.eff.x += 2;
                    this.eff.paint(mGraphics2);
                    GameScreen.player.dyWater = 0;
                    GameScreen.player.Action = 0;
                    GameScreen.player.Direction = 3;
                    GameScreen.player.x = this.eff.x + 30;
                    GameScreen.player.y = this.eff.y - 40;
                    GameScreen.player.updateActionPerson();
                    GameScreen.player.paintPlayer(mGraphics2, -1);
                    if (this.eff.x > 1200) {
                        ShipScr.gI().typeMap = 1;
                        ShipScr.gI().Show();
                    }
                }
            }
        } else if (GameCanvas.loadmap.idMap == 67) {
            if (this.eff == null) {
                this.eff = new EffectAuto(50, 360, 672, 0, 0, 1, 0);
            } else {
                this.eff.update();
                if (!this.isShipMove) {
                    this.eff.paint(mGraphics2);
                } else {
                    this.eff.x += 2;
                    this.eff.paint(mGraphics2);
                    GameScreen.player.dyWater = 0;
                    GameScreen.player.Action = 0;
                    GameScreen.player.Direction = 3;
                    GameScreen.player.x = this.eff.x + 30;
                    GameScreen.player.y = this.eff.y - 40;
                    GameScreen.player.updateActionPerson();
                    GameScreen.player.paintPlayer(mGraphics2, -1);
                    if (this.eff.x > 480) {
                        ShipScr.gI().typeMap = (byte)3;
                        ShipScr.gI().Show();
                    }
                }
            }
        }
    }

    public static boolean isMarket(int n) {
        return n == 82;
    }

    public void paintPos_minimap(mGraphics mGraphics2, int n, int n2) {
        if (this.isMapThachdau()) {
            return;
        }
        this.xPos_minimap = GameScreen.player.x / LoadMap.wTile;
        this.yPos_minimap = GameScreen.player.y / LoadMap.wTile;
        mFont.tahoma_7_yellow.drawString(mGraphics2, this.xPos_minimap + ":" + this.yPos_minimap, n, n2, 1, false);
    }

    public static void paintinfo18plush(mGraphics mGraphics2) {
        if (paint18plush == 0) {
            return;
        }
        GameCanvas.resetTrans(mGraphics2);
        int n = 110;
        if (GameCanvas.currentScreen != GameCanvas.game) {
            n = 0;
        }
        int n2 = mFont.tahoma_7_white.getWidth("18+ Ch\u01a1i qu\u00e1 180 ph\u00fat m\u1ed7i ng\u00e0y s\u1ebd h\u1ea1i s\u1ee9c kh\u1ecfe.");
        mGraphics2.setColor(0, 0.6f);
        mGraphics2.fillRect(n, 0, n2, 12, false);
        mFont.tahoma_7_white.drawString(mGraphics2, "18+ Ch\u01a1i qu\u00e1 180 ph\u00fat m\u1ed7i ng\u00e0y s\u1ebd h\u1ea1i s\u1ee9c kh\u1ecfe.", n, 0, 0, false);
    }

    public void paintIconClan(mGraphics mGraphics2) {
        if (this.isMapchienthanh() && idicon != -1) {
            MainImage mainImage = ObjectData.getImageIconClan((short)idicon);
            if (mainImage.img != null) {
                AvMain.Font3dColor(mGraphics2, nameclan.toUpperCase(), GameCanvas.w / 2, 5, 2, (byte)0);
                mGraphics2.drawImage(mainImage.img, GameCanvas.w / 2 + mFont.tahoma_7_black.getWidth(nameclan), 10, 3, false);
            }
        }
    }

    static {
        wArrowMove = 30;
        wPointArrow = 38;
        mKeySkill = new int[]{1, 3, 7, 9};
        mValueHotKey = new int[]{1, 3, 5, 7, 9};
        mValueChar = new String[]{"R", "T", "Y", "U", "I"};
        mPosKill = mSystem.new_M_Int(4, 2);
        mPosMove = mSystem.new_M_Int(4, 2);
        mPosOther = mSystem.new_M_Int(5, 2);
        mSizeImgOther = mSystem.new_M_Int(5, 2);
        timePointer = 0;
        namemap = "";
        xPointKill = GameCanvas.w - 35;
        yPointKill = GameCanvas.h - 50;
        wSkill = 24;
        wMainSkill = 50;
        gocBegin = 285;
        lSkill = 50;
        xPaintSkill = GameCanvas.hw - 60;
        yPaintSkill = GameCanvas.h - GameCanvas.hCommand - 14;
        isLevelPoint = false;
        isShowInfoAuto = true;
        isPaintInfoFocus = false;
        hShowInGame = 0;
        isShowInGame = true;
        timeDoNotClick = 0L;
        imgHitWidth = new int[3];
        imgHitHeight = new int[3];
        vecfocus = new mVector("PaintInfoGameScr vecfocus");
        paint18plush = 0;
        idicon = -1;
    }
}

