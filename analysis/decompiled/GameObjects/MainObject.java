/*
 * Decompiled with CFR 0.152.
 */
package GameObjects;

import CLib.mFont;
import CLib.mGraphics;
import CLib.mHashtable;
import CLib.mImage;
import CLib.mSystem;
import CLib.mVector;
import GameEffect.EffectAuto;
import GameEffect.EffectCharWearing;
import GameObjects.DataSkillEff;
import GameObjects.GameData;
import GameObjects.ImageIcon;
import GameObjects.Item;
import GameObjects.MainBuff;
import GameObjects.MainClan;
import GameObjects.MainMonster;
import GameObjects.MountTemplate;
import GameObjects.ObjectData;
import GameObjects.Player;
import GameObjects.WPSplashInfo;
import GameObjects.WeaponInfo;
import GameScreen.GameScreen;
import GameScreen.MainScreen;
import GameScreen.PaintInfoGameScreen;
import InterfaceComponents.MainTabNew;
import InterfaceComponents.PopupChat;
import Main.GameCanvas;
import Model.AvMain;
import Model.CRes;
import Model.FrameImage;
import Model.MainImage;
import Model.T;
import Skill.ListSkill;
import Skill.Monster_Skill;
import Skill.SplashSkill;
import Thread_More.LoadMap;
import java.util.Enumeration;
import netcommand.global.GlobalService;

public class MainObject
extends AvMain {
    public static final byte DIR_UP = 1;
    public static final byte DIR_DOWN = 0;
    public static final byte DIR_LEFT = 2;
    public static final byte DIR_RIGHT = 3;
    public static final byte AC_STAND = 0;
    public static final byte AC_MOVE = 1;
    public static final byte AC_FIRE = 2;
    public static final byte AC_HIT = 3;
    public static final byte AC_DIE = 4;
    public static final byte CAT_PLAYER = 0;
    public static final byte CAT_MONSTER = 1;
    public static final byte CAT_NPC = 2;
    public static final byte CAT_ITEM = 3;
    public static final byte CAT_POTION = 4;
    public static final byte CAT_QUEST_ITEM = 5;
    public static final byte CAT_ITEM_TOC = 6;
    public static final byte CAT_MATERIAL = 7;
    public static final byte CAT_ICON_CLAN = 8;
    public static final byte CAT_PET = 9;
    public static final byte CAT_MOUNT = 10;
    public static final byte SPEC_NORMAL = 0;
    public static final byte SPEC_MONSTER = 1;
    public static final byte KIEMSI = 0;
    public static final byte SONGKIEM = 1;
    public static final byte PHAPSU = 2;
    public static final byte XATHU = 3;
    public static final byte TEM_ANIMAL = 0;
    public static final byte TEM_ITEM = 1;
    public static final byte TEM_ALL = -1;
    public static final byte TEM_NPC = 2;
    public static final byte TEM_PLAYER = 3;
    public static final byte MON_NOR = 0;
    public static final byte MON_BOSS = 1;
    public static final byte MON_CAPCHAR = 2;
    public static final byte MON_PHOBANG = 3;
    public static final byte BOSS_PHOBANG = 4;
    public static final byte CUCDA_BANGHOI = 5;
    public static final byte MON_NHANBANG = 6;
    public static final byte CHARMONSTER = -126;
    public static final byte MONSTERARENA = -125;
    public static final byte newMONSTER = 92;
    public short idMatna = (short)-1;
    public short idPhiPhong = (short)-1;
    public short idWeaPon = (short)-1;
    public short idHorse = (short)-1;
    public short idHair = (short)-1;
    public short idWing = (short)-1;
    public short idName = (short)-1;
    public short idBody = (short)-1;
    public short idLeg = (short)-1;
    public short idBienhinh = (short)-1;
    public static final byte isThucLui = 127;
    public long timedie;
    public EffectAuto effAuto = null;
    public int x;
    public int y;
    public int f;
    public int vx;
    public int vy;
    public int wOne;
    public int hOne;
    public int toX;
    public int toY;
    public int wFocus;
    public int vMax;
    public int dy;
    public int dx;
    public int dyWater;
    public int dyKill;
    public int xFire;
    public int yFire;
    public int xsai;
    public int ysai;
    public int ySort;
    public int yEffAuto;
    int timeDyKill;
    int vDy = 0;
    public int hp;
    public int maxHp;
    public int mp;
    public int maxMp;
    public int hpEffect;
    public int yjum;
    public int vjum;
    public byte clazz;
    public byte index;
    public short Lv;
    public short phantramLv;
    public short countCharStand = 0;
    public short idImageHenshin = (short)-1;
    public long gold;
    public long coin;
    public int hang = 0;
    public int idClanMoDa;
    int timeHuyKill = 0;
    public boolean isTanHinh = false;
    public boolean isShowHP;
    public boolean isBinded = false;
    public boolean isDongBang = false;
    public boolean moveToBoss = false;
    public boolean isFootSnow = false;
    public long timeBind = 0L;
    public long timeSleep = 0L;
    public long timeStun = 0L;
    public long timeno = 0L;
    public long timenoBoss84 = 0L;
    public long timeDongBang = 0L;
    public int timeTanHinh;
    public int timeStop;
    public int timeEye;
    public int EyeMain;
    public int endEye;
    public long timeRePlayerInfo;
    public int hat = 2;
    public int head = 1;
    public int body = 2;
    public int leg = 2;
    public int eye = 2;
    public int hair = 2;
    public int wing = -1;
    public int pet = -1;
    public int frame;
    public int frameLeg;
    byte[] A_Stand = new byte[]{0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1};
    public byte[] A_Move = new byte[]{2, 2, 2, 0, 0, 3, 3, 3, 0, 0};
    public static byte framefocus;
    public static byte hideEff;
    public static int Wfc;
    public static int Hfc;
    public int xStand;
    public int yStand;
    public int countSendMove;
    public int xStopMove;
    public int yStopMove;
    int frameDie = 0;
    public int typeNPC = 0;
    public byte isPerson = 0;
    public boolean isObject = false;
    public static String[] strHelpNPC;
    public static byte StepHelpServer;
    public byte colorName;
    public int ID;
    public byte typeObject;
    public byte typePk = (byte)-1;
    public byte typeOnline;
    public byte typeBoss = 0;
    public byte typeSpec;
    public int KillFire = -1;
    public short pointPk = 0;
    public String name = "";
    public String nameGiaotiep = T.giaotiep;
    public String infoObject = "";
    public String strChatPopup = null;
    public int Action;
    public int Direction;
    public int PrevDir;
    public int countAutoMove = 0;
    public int timeFreeMove = 0;
    public boolean isRemove;
    public boolean isRemoveObjFocus;
    boolean isDirMove = false;
    public boolean isStop;
    public boolean iscuop;
    public short[] posTransRoad;
    public MainClan myClan = null;
    public int fplash;
    public int IDAttack;
    public mVector vecObjFocusSkill;
    public int imageId;
    public int IdBigAvatar;
    public boolean isRunAttack = false;
    public boolean isWater = false;
    public boolean isInfo = false;
    public boolean isParty = false;
    public long timeLoadInfo;
    public static mImage focus;
    public static mImage newfocus;
    public static mImage shadow;
    public static mImage shadow1;
    public static mImage water;
    public static mImage imgCapchar;
    public mVector vecBuff = new mVector("MainObject vecBuff");
    public SplashSkill PlashNow;
    public ListSkill ListKillNow;
    public int typeMonster;
    public boolean isDie = false;
    public PopupChat chat;
    public boolean ispaintArena;
    public boolean isjum;
    public int timeStand = 0;
    public int timeCapchar = -1;
    public Monster_Skill skillDefault;
    public static mVector vecCapchar;
    public static String strCapchar;
    public boolean isMonPhoBangDie = false;
    public boolean overMP;
    public boolean overHP;
    public boolean isPhiHanh;
    public static byte countmp;
    public mVector vecEffauto = new mVector("MainObject VecEffauto");
    public mVector veclowEffauto = new mVector("Low VecEffauto");
    public mVector vecEffectCharWearing = new mVector("Effect CharWearing");
    public mVector vecDataSkillEff = new mVector();
    public int coutEff;
    public boolean isSend;
    public int timeGet = 0;
    public int timeReveice = 1500;
    public byte numFrame;
    public boolean isDetonateInDest;
    public boolean isMove;
    public boolean isServerControl = false;
    public short[] idPartFashion = new short[]{-1, -1, -1, -1, -1, -1, -1};
    public static short[] idMaterialHopNguyenLieu;
    public short markKiller;
    public boolean isPaint_No = false;
    public boolean isStun = false;
    public boolean isSleep = false;
    public boolean isno = false;
    public boolean isnoBoss84 = false;
    public static final byte TYPE_PK_THUONG_GIA = 11;
    public static final byte TYPE_PK_CUOP = 12;
    public static final byte TYPE_PK_HIEP_SY = 13;
    public byte isBot = (byte)-1;
    public byte StepMovebocap = (byte)-1;
    public boolean Namearena;
    public boolean useShip = false;
    public boolean canFocusMon;
    public byte typefocus = 1;
    public byte typefire = 0;
    public static final byte CAN_FOCUS = 1;
    public static final byte CAN_NOT_FOCUS = 0;
    public static final byte CAN_FIRE = 1;
    public static final byte CAN_NOT_FIRE = 0;
    public static byte[] frameicon;
    public byte frameClan = 0;
    public static byte[][] mTypePartPaintPlayer;
    int[][][] mAction = new int[][][]{new int[][]{{0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0}}, new int[][]{{0, 0, 0, 1, 1, 1, 2, 2, 2}, {1, 1, 1, 0, 0, 0, 2, 2, 2}, {2, 2, 2, 0, 0, 0, 1, 1, 1}}, new int[][]{{2, 2, 2, 2, 2, 3, 3, 3, 3}, {2, 2, 2, 2, 2, 3, 3, 3, 3}, {2, 2, 2, 2, 2, 3, 3, 3, 3}}, new int[][]{{4, 4, 4, 4, 4, 4, 4, 4, 4}, {4, 4, 4, 4, 4, 4, 4, 4, 4}, {4, 4, 4, 4, 4, 4, 4, 4, 4}}, new int[][]{{4, 4, 4, 4, 4, 4, 4, 4, 4}, {4, 4, 4, 4, 4, 4, 4, 4, 4}, {4, 4, 4, 4, 4, 4, 4, 4, 4}}};
    int mjum;
    int range;
    public byte FrameBody;
    public byte FrameLeg;
    public byte FrameBienhinh;
    public static mHashtable ALL_EFF_MAT_NA;
    public int FramePP;
    public byte FrameHair;
    public byte FrameWing;
    public byte FrameName;
    public int frameThuCuoi;
    public int Fhorse = 0;
    public static byte[] horseMove;
    public byte FrameMatNa = 0;
    public byte FrameWP = 0;
    public boolean paintMatnaTruocNon = true;
    byte ffire;
    public int time = 0;
    public int idLight;
    public int weapon_frame = 0;
    public int weaponType = -1;
    public static WeaponInfo[][][] imgWeapone;
    static final byte[] hwp;
    static final byte[] fixY;
    public int frNo = 0;
    public int[] frameNo = new int[]{0, 0, 0, 1, 1, 1, 2, 2, 2};
    public boolean isMoveOut = false;
    public int xMoveOut = 0;
    public int yMoveOut = 0;
    public int dyMount = 0;
    public int xMount = 0;
    public int yMount = 0;
    public byte typeMount = (byte)-1;
    public byte frameMount;
    public byte fMount;
    public static final byte NGUA_NAU = 0;
    public static final byte NGUA_TRANG = 1;
    public static final byte NGUA_CHIENGIAP = 2;
    public static final byte NGUA_DO = 3;
    public static final byte NGUA_DEN = 4;
    public static byte[] ArrMount;
    public static byte[] ArrMount1;
    public short[][] arrayGemKham = new short[12][];
    public byte[] slotGem = new byte[]{-1, -1, -1};
    public FrameImage weaponEff;
    public static FrameImage[] weaponEff_Gem;
    int timeEff = 0;
    int dxEff = 0;
    int dyEff = 0;
    byte fweapon = 0;
    byte timeShow = (byte)8;
    byte[] transEff = new byte[]{2, 0, 2, 2};
    byte[][] eff_dx = new byte[][]{{-2, 4, 2, -10}, {-2, 4, 2, -10}};
    byte[][] eff_dy = new byte[][]{{6, 5, 5, 2}, {6, 5, 5, 2}};

    public static boolean isMaHopNguyenLieu(int n) {
        try {
            for (int i = 0; i < idMaterialHopNguyenLieu.length; ++i) {
                if (idMaterialHopNguyenLieu[i] != n) continue;
                return true;
            }
            return false;
        }
        catch (Exception exception) {
            mSystem.println("----Err mainobj:-- isMaHopNguyenLieu");
            return false;
        }
    }

    public String getnameOwner() {
        return "";
    }

    public MainObject() {
    }

    public boolean isMainChar() {
        return false;
    }

    public boolean isMonstervantieu() {
        return false;
    }

    public void setspeedVantieu(int n) {
    }

    public boolean isMonsterVantieuTrang() {
        return false;
    }

    public boolean isMonsterVantieuDen() {
        return false;
    }

    public MainObject(int n, byte by, String string, int n2, int n3) {
        this.ID = n;
        this.name = string;
        this.typeObject = by;
        this.x = n2;
        this.y = n3;
        this.toX = n2;
        this.toY = n3;
        this.Direction = 0;
    }

    public void setInfo(byte by, short s, short s2, byte by2, byte by3, byte by4, String string, String string2, short s3, short s4, byte by5, byte by6, byte[] byArray, byte[] byArray2, byte[] byArray3) {
    }

    public void setInfo(int n, int n2, int n3) {
        if (n2 < 8) {
            n2 += 8;
        }
        this.head = n;
        this.eye = n2;
        this.hair = n3;
        this.EyeMain = n2;
    }

    public void setEffectauto(int n, int n2, short s) {
    }

    public void addEffAutoFromsv(int n, int n2, int n3, int n4, int n5, int n6, int n7, byte[] byArray, byte by, long l, byte by2, int n8, int n9) {
        EffectAuto effectAuto = new EffectAuto(n, n2, n3, n4, n5, n6, n7, byArray, l, by2, n8, n9);
        if (by == 0) {
            for (int i = 0; i < this.vecEffauto.size(); ++i) {
                EffectAuto effectAuto2 = (EffectAuto)this.vecEffauto.elementAt(i);
                if (effectAuto2 == null || effectAuto2.IDItem != n) continue;
                this.vecEffauto.removeElement(effectAuto2);
            }
            this.vecEffauto.addElement(effectAuto);
        } else {
            for (int i = 0; i < this.veclowEffauto.size(); ++i) {
                EffectAuto effectAuto3 = (EffectAuto)this.veclowEffauto.elementAt(i);
                if (effectAuto3 == null || effectAuto3.IDItem != n) continue;
                this.veclowEffauto.removeElement(effectAuto3);
            }
            this.veclowEffauto.addElement(effectAuto);
        }
    }

    public void addeffAuto(int n, int n2, int n3, int n4, int n5, int n6, int n7) {
        if (this.isMonstervantieu()) {
            this.vecEffauto.removeAllElements();
        }
        for (int i = 0; i < this.vecEffauto.size(); ++i) {
            EffectAuto effectAuto = (EffectAuto)this.vecEffauto.elementAt(i);
            if (effectAuto == null || effectAuto.IDItem != n) continue;
            this.vecEffauto.removeElement(effectAuto);
        }
        EffectAuto effectAuto = new EffectAuto(n, n2, n3, n4, n5, n6, n7);
        this.vecEffauto.addElement(effectAuto);
    }

    public boolean isPkVantieu() {
        return this.typePk == 12 || this.typePk == 13 || this.typePk == 11;
    }

    public static void paintShadow(mGraphics mGraphics2, int n, int n2, int n3) {
        mGraphics2.drawRegion(shadow1, 0, 0, mImage.getImageWidth(MainObject.shadow1.image), mImage.getImageHeight(MainObject.shadow1.image), 0, n2, n3, 3, false);
    }

    public boolean isLuaThieng() {
        return false;
    }

    public void setWearingListChar(byte[] byArray) {
        try {
            if (byArray == null) {
                this.body = -1;
                this.leg = -1;
                this.hat = -1;
                this.wing = -1;
                this.hair = -1;
                this.eye = -1;
                this.head = -1;
                this.weaponType = -1;
                this.pet = -1;
            } else {
                this.body = byArray[0];
                this.leg = byArray[1];
                this.hat = byArray[2];
                this.wing = byArray[7];
                int n = this.clazz;
                if (n == 3) {
                    n = 2;
                } else if (n == 2) {
                    n = 3;
                }
                this.weaponType = byArray[8 + n];
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void setPainthit(byte by, boolean bl) {
    }

    public void setWearingEquip(byte[] byArray) {
        if (byArray == null) {
            this.body = -1;
            this.leg = -1;
            this.hat = -1;
            this.wing = -1;
            this.weaponType = -1;
            this.pet = -1;
        } else {
            this.body = byArray[1];
            this.leg = byArray[7];
            this.hat = byArray[6];
            this.wing = byArray[10];
            this.weaponType = byArray[0];
            this.pet = byArray[5];
        }
    }

    public void paint(mGraphics mGraphics2) {
    }

    public void paintBuffLast(mGraphics mGraphics2) {
        if (this.vecBuff == null) {
            return;
        }
        for (int i = this.vecBuff.size() - 1; i >= 0; --i) {
            MainBuff mainBuff = (MainBuff)this.vecBuff.elementAt(i);
            if (mainBuff == null || !mainBuff.isPaintLast) continue;
            if (mainBuff.typeBuff == 4) {
                mainBuff.paint(mGraphics2, this.x + this.xsai, this.y - this.hOne + 5 + this.ysai);
                continue;
            }
            mainBuff.paint(mGraphics2, this.x + this.xsai, this.y + this.ysai);
        }
    }

    public void paintBuffFirst(mGraphics mGraphics2) {
        if (this.vecBuff == null) {
            return;
        }
        for (int i = this.vecBuff.size() - 1; i >= 0; --i) {
            MainBuff mainBuff = (MainBuff)this.vecBuff.elementAt(i);
            if (mainBuff == null || mainBuff.isPaintLast) continue;
            if (mainBuff.typeBuff == 4) {
                mainBuff.paint(mGraphics2, this.x + this.xsai, this.y - this.hOne + 5 + this.ysai);
                continue;
            }
            mainBuff.paint(mGraphics2, this.x + this.xsai, this.y + this.ysai);
        }
    }

    public void paintAvatarFocus(mGraphics mGraphics2, int n, int n2) {
        if (this.head != -1) {
            CRes.getCharPartInfo(2, this.head).paint(mGraphics2, n - 1, n2 + 23, 0, 0);
        }
        if (this.eye != -1) {
            CRes.getCharPartInfo(4, this.eye).paint(mGraphics2, n - 1, n2 + 23, 0, 0);
        }
        if (this.hair != -1) {
            CRes.getCharPartInfo(5, this.hair).paint(mGraphics2, n - 1, n2 + 23, 0, 0);
        }
        if (this.hat != -1) {
            CRes.getCharPartInfo(3, this.hat).paint(mGraphics2, n - 1, n2 + 23, 0, 0);
        }
    }

    public String getNameAndClan(String string) {
        String string2 = this.name;
        if (this.myClan != null) {
            string2 = this.myClan.shortName + string + string2;
        }
        return string2;
    }

    public void paintIconClan(mGraphics mGraphics2, int n, int n2, int n3) {
        if (this.myClan != null) {
            MainImage mainImage = ObjectData.getImageIconClan(this.myClan.IdIcon);
            if (n3 == 2) {
                int n4 = (mFont.tahoma_7_white.getWidth(this.myClan.shortName) + 12) / 2;
                if (mainImage.img != null) {
                    if (mImage.getImageHeight(mainImage.img.image) / 18 == 3) {
                        if (GameCanvas.gameTick % 6 == 0) {
                            int n5 = frameicon.length;
                            if (n5 == 0) {
                                n5 = 1;
                            }
                            this.frameClan = (byte)((this.frameClan + 1) % n5);
                        }
                        mGraphics2.drawRegion(mainImage.img, 0, frameicon[this.frameClan] * 18, 18, 18, 0, n - n4 + 6, n2, 3, true);
                    } else {
                        mGraphics2.drawImage(mainImage.img, n - n4 + 6, n2, 3, true);
                    }
                    Item.eff_UpLv.paintUpgradeEffect(n - n4 + 6, n2 - 1, this.myClan.getEffChucVu(), 14, mGraphics2, 0);
                }
                mFont.tahoma_7_white.drawString(mGraphics2, this.myClan.shortName, n - n4 + 15, n2 - 6, 0, true);
            } else if (n3 == 0) {
                if (mainImage.img != null) {
                    if (mImage.getImageHeight(mainImage.img.image) / 18 == 3) {
                        if (GameCanvas.gameTick % 6 == 0) {
                            int n6 = frameicon.length;
                            if (n6 == 0) {
                                n6 = 1;
                            }
                            this.frameClan = (byte)((this.frameClan + 1) % n6);
                        }
                        mGraphics2.drawRegion(mainImage.img, 0, frameicon[this.frameClan] * 18, 18, 18, 0, n, n2, 3, true);
                    } else {
                        mGraphics2.drawImage(mainImage.img, n, n2, 3, true);
                    }
                    Item.eff_UpLv.paintUpgradeEffect(n - 1, n2 - 1, this.myClan.getEffChucVu(), 14, mGraphics2, 0);
                }
                mFont.tahoma_7b_white.drawString(mGraphics2, this.myClan.shortName, n + 13, n2, 0, true);
            } else if (n3 == -1) {
                if (mainImage.img != null) {
                    if (mImage.getImageHeight(mainImage.img.image) / 18 == 3) {
                        if (GameCanvas.gameTick % 6 == 0) {
                            int n7 = frameicon.length;
                            if (n7 == 0) {
                                n7 = 1;
                            }
                            this.frameClan = (byte)((this.frameClan + 1) % n7);
                        }
                        mGraphics2.drawRegion(mainImage.img, 0, frameicon[this.frameClan] * 18, 18, 18, 0, n - 1, n2, 3, true);
                    } else {
                        mGraphics2.drawImage(mainImage.img, n - 1, n2, 3, true);
                    }
                    Item.eff_UpLv.paintUpgradeEffect(n - 1, n2 - 1, this.myClan.getEffChucVu(), 14, mGraphics2, 0);
                }
            } else if (n3 == -2) {
                int n8 = (mFont.tahoma_7_white.getWidth(this.myClan.shortName + " - " + this.myClan.name) + 15) / 2;
                if (mainImage.img != null) {
                    if (mImage.getImageHeight(mainImage.img.image) / 18 == 3) {
                        if (GameCanvas.gameTick % 6 == 0) {
                            int n9 = frameicon.length;
                            if (n9 == 0) {
                                n9 = 1;
                            }
                            this.frameClan = (byte)((this.frameClan + 1) % n9);
                        }
                        mGraphics2.drawRegion(mainImage.img, 0, frameicon[this.frameClan] * 18, 18, 18, 0, n - n8 + 7, n2, 3, true);
                    } else {
                        mGraphics2.drawImage(mainImage.img, n - n8 + 7, n2, 3, true);
                    }
                    Item.eff_UpLv.paintUpgradeEffect(n - n8 + 6, n2 - 1, this.myClan.getEffChucVu(), 14, mGraphics2, 0);
                }
                mFont.tahoma_7_white.drawString(mGraphics2, this.myClan.shortName + " - " + this.myClan.name, n - n8 + 15, n2 - 6, 0, true);
            }
        }
    }

    public void paintObject(mGraphics mGraphics2, int n) {
        MainImage mainImage = ObjectData.getImagePartNPC((short)this.imageId);
        if (mainImage.img != null) {
            this.hOne = mImage.getImageHeight(mainImage.img.image) / this.numFrame;
            this.wOne = mImage.getImageWidth(mainImage.img.image);
            mGraphics2.drawRegion(mainImage.img, 0, GameCanvas.gameTick / 7 % this.numFrame * this.hOne, this.wOne, this.hOne, 0, this.x, this.y, 33, false);
        }
        if (GameScreen.ObjFocus == null || GameScreen.ObjFocus != null && this != GameScreen.ObjFocus || PaintInfoGameScreen.isLevelPoint) {
            this.paintName(mGraphics2, 0);
        }
        int n2 = this.x;
        int n3 = this.y - this.hOne - 8;
        int n4 = 0;
        if (this.maxHp > 0) {
            if (this.hp > 0) {
                n4 = this.hp * 50 / this.maxHp;
                if (n4 <= 0) {
                    n4 = 1;
                } else if (n4 > 50) {
                    n4 = 50;
                }
            }
            mGraphics2.setColor(-8714722);
            mGraphics2.fillRect(n2 - this.wOne / 2 + 10, n3, 50, 5, true);
            mGraphics2.setColor(-579511);
            mGraphics2.fillRect(n2 - this.wOne / 2 + 10, n3, n4, 5, true);
        }
    }

    public void jum() {
        this.isjum = true;
        this.yjum = 0;
        this.vjum = 10;
        this.mjum = 5;
        this.range = 72;
    }

    public void paintNameStore(mGraphics mGraphics2, int n, int n2) {
    }

    public void painthidePlayer(mGraphics mGraphics2, int n) {
        block27: {
            block28: {
                int n2;
                block30: {
                    block31: {
                        int n3;
                        block32: {
                            block29: {
                                if (this.isTanHinh) break block27;
                                if (!this.Canpaint()) break block28;
                                if (this.Action != 4 || this.isDongBang) break block29;
                                mGraphics2.drawImage(shadow, this.x + 1, this.y + 1, 3, true);
                                this.paintBuffFirst(mGraphics2);
                                AvMain.fraPlayerDie.drawFrame(0, this.x + 1, this.y - this.ysai + 5, 0, 33, mGraphics2);
                                if (this.effAuto != null) {
                                    this.isDongBang = false;
                                    this.timeDongBang = mSystem.currentTimeMillis();
                                    GameScreen.addEffectEndKill(15, this.x, this.y);
                                    this.effAuto = null;
                                }
                                if (this.frameDie == 0) {
                                    mGraphics2.drawImage(AvMain.imgEyeDie, this.x + 1, this.y - this.ysai + 5 - 24, 17, true);
                                }
                                break block30;
                            }
                            if (this.checktanghinh()) break block31;
                            if (this.idImageHenshin == -1 && this.idHorse == -1) {
                                mGraphics2.drawImage(shadow, this.x + 1, this.y - this.ysai + 2, 3, true);
                            }
                            this.paintBuffFirst(mGraphics2);
                            this.paintEffauto_Low(mGraphics2, this.x, this.y);
                            this.paintDataEff_Top(mGraphics2, this.x, this.y);
                            n2 = (this.Direction == 0 || this.Direction == 2 || this.Direction == 3) && (this.clazz == 3 && this.frame != 4 || this.clazz != 3 && this.frame != 5) ? 1 : 0;
                            n3 = 0;
                            if (this.idImageHenshin == -1) break block32;
                            MainImage mainImage = ObjectData.getImagePartMonster(this.idImageHenshin);
                            if (mainImage.img != null) {
                                int n4 = this.Action;
                                if (n4 > this.mAction.length - 1) {
                                    n4 = 0;
                                }
                                this.hOne = mImage.getImageHeight(mainImage.img.image) / 3;
                                this.wOne = mImage.getImageWidth(mainImage.img.image) / 2;
                                if (this.eye == 4 || this.eye == 2) {
                                    n4 = 3;
                                }
                                int n5 = 0;
                                int n6 = this.mAction[n4][this.Direction > 2 ? 2 : this.Direction][this.frame] * this.hOne;
                                n5 = this.mAction[n4][this.Direction > 2 ? 2 : this.Direction][this.frame] / 3 * this.wOne;
                                n6 = this.mAction[n4][this.Direction > 2 ? 2 : this.Direction][this.frame] % 3 * this.hOne;
                                mGraphics2.drawRegion(mainImage.img, n5, n6, this.wOne, this.hOne, this.Direction > 2 ? 2 : 0, this.x, this.y - this.dy + this.dyWater, 33, false);
                            }
                            break block30;
                        }
                        if (n2 != 0) {
                            try {
                                this.paintWeaponhideplayer(mGraphics2, n3);
                            }
                            catch (Exception exception) {
                                // empty catch block
                            }
                        }
                        boolean bl = false;
                        for (int i = 0; i < mTypePartPaintPlayer[this.Direction].length; ++i) {
                            byte by = mTypePartPaintPlayer[this.Direction][i];
                            if (!(by != 6 && by != 4 || by == 6 && this.Direction == 1 && i == 7 || by == 6 && this.Direction != 1 && i == 0) && (by != 4 || this.Direction == 1)) continue;
                            if (this.getTypeParthide(i) >= 0) {
                                if (this.typeMount == -1) {
                                    CRes.getCharPartInfo(by, this.getTypeParthide(i)).paint(mGraphics2, this.x, this.y - this.ysai - this.dy + this.dyWater - this.yjum, this.Direction, this.frame);
                                    continue;
                                }
                                if (i != 1) {
                                    CRes.getCharPartInfo(by, this.getTypeParthide(i)).paint(mGraphics2, this.x, this.y - this.ysai - this.dy + this.dyWater - this.dyMount - this.yjum, this.Direction, this.frame);
                                    continue;
                                }
                                this.paintMount_Sau(mGraphics2);
                                continue;
                            }
                            if (by != 0 && by != 1 && by != 2 && by != 4 && by != 5 || bl) continue;
                            bl = true;
                            this.setReInfo();
                        }
                        if (this.typeMount != -1) {
                            this.paintMount_Truoc(mGraphics2);
                        }
                        if (n2 != 0) break block30;
                        try {
                            this.paintWeaponhideplayer(mGraphics2, n3);
                        }
                        catch (Exception exception) {}
                        break block30;
                    }
                    for (n2 = 0; n2 < mTypePartPaintPlayer[this.Direction].length; ++n2) {
                        byte by = mTypePartPaintPlayer[this.Direction][n2];
                        if (by != 4 || this.getTypeParthide(n2) < 0) continue;
                        if (this.typeMount == -1) {
                            CRes.getCharPartInfo(by, this.getTypeParthide(n2)).paint(mGraphics2, this.x, this.y - this.ysai - this.dy + this.dyWater - this.yjum, this.Direction, this.frame);
                            continue;
                        }
                        if (n2 != 1) {
                            CRes.getCharPartInfo(by, this.getTypeParthide(n2)).paint(mGraphics2, this.x, this.y - this.ysai - this.dy + this.dyWater - this.dyMount - this.yjum, this.Direction, this.frame);
                            continue;
                        }
                        this.paintMount_Sau(mGraphics2);
                    }
                }
                if (!this.checktanghinh()) {
                    this.paintDataEff_Bot(mGraphics2, this.x, this.y);
                    if (!this.useShip && this.isWater && this.dy == 0) {
                        n2 = 1;
                        if (this.Direction == 2) {
                            n2 += 2;
                        } else if (this.Direction == 3) {
                            n2 -= 2;
                        }
                        mGraphics2.drawRegion(water, 0, (this.Action != 0 ? 2 : 0) * 17 + GameCanvas.gameTick / 2 % 2 * 17, 28, 17, 0, this.x + n2, this.y - this.ysai - 2 + this.dyWater, 3, true);
                    }
                }
            }
            if (!this.checktanghinh()) {
                if ((PaintInfoGameScreen.isLevelPoint || this != GameScreen.ObjFocus) && n != -1) {
                    this.paintName(mGraphics2, n);
                }
                this.paintBuffLast(mGraphics2);
                this.paint_no(mGraphics2);
                this.paintEffauto(mGraphics2, this.x, this.y);
                if (this.hp > 0) {
                    this.paintDongBang(mGraphics2);
                }
            }
        }
    }

    public int getFrameLeg() {
        if (this.Direction == 0) {
            return this.frame + this.FrameLeg * 18;
        }
        if (this.Direction == 1) {
            return this.Direction * 6 + this.frame + this.FrameLeg * 18;
        }
        return this.frame + 12 + this.FrameLeg * 18;
    }

    public int getFrameBien() {
        if (this.Direction == 0) {
            return this.frame + this.FrameBienhinh * 18;
        }
        if (this.Direction == 1) {
            return this.Direction * 6 + this.frame + this.FrameBienhinh * 18;
        }
        return this.frame + 12 + this.FrameBienhinh * 18;
    }

    public int getFrameBody() {
        if (this.Direction == 0) {
            return this.frame + this.FrameBody * 18;
        }
        if (this.Direction == 1) {
            return this.Direction * 6 + this.frame + this.FrameBody * 18;
        }
        return this.frame + 12 + this.FrameBody * 18;
    }

    public int getFramePP() {
        if (this.Direction == 0) {
            return this.frame + this.FramePP * 18;
        }
        if (this.Direction == 1) {
            return this.Direction * 6 + this.frame + this.FramePP * 18;
        }
        return this.frame + 12 + this.FramePP * 18;
    }

    public int getFrameWing() {
        if (this.Direction == 0) {
            return this.frame + this.FrameWing * 18;
        }
        if (this.Direction == 1) {
            return this.Direction * 6 + this.frame + this.FrameWing * 18;
        }
        return this.frame + 12 + this.FrameWing * 18;
    }

    public int getFrameHair() {
        if (this.Direction == 0) {
            return this.frame + this.FrameHair * 18;
        }
        if (this.Direction == 1) {
            return this.Direction * 6 + this.frame + this.FrameHair * 18;
        }
        return this.frame + 12 + this.FrameHair * 18;
    }

    public int getFrameWing_Wearing(int n) {
        return n + this.FrameWing * 18;
    }

    public int getFrameHair_Wearing(int n) {
        return n + this.FrameHair * 18;
    }

    public int getFrameBody_Wearing(int n) {
        return n + this.FrameBody * 18;
    }

    public int getFrameLeg_Wearing(int n) {
        return n + this.FrameLeg * 18;
    }

    public int getFrameHorse() {
        if (this.Direction == 0) {
            return this.Fhorse + this.frameThuCuoi * 21;
        }
        if (this.Direction == 1) {
            return this.Fhorse + 7 + this.frameThuCuoi * 21;
        }
        return this.Fhorse + 14 + this.frameThuCuoi * 21;
    }

    public int getFramePP_Wearing(int n) {
        return n + this.FramePP * 18;
    }

    public int getFrameMatNa_Wearing(int n) {
        return n + this.FrameMatNa * 18;
    }

    public int getFrameWP_Wearing(int n) {
        return n + this.FrameWP * 18;
    }

    public int getFrameMatNa() {
        if (this.Direction == 0) {
            return this.frame + this.FrameMatNa * 18;
        }
        if (this.Direction == 1) {
            return this.Direction * 6 + this.frame + this.FrameMatNa * 18;
        }
        return this.frame + 12 + this.FrameMatNa * 18;
    }

    public int getFrameWP() {
        if (this.Direction == 0) {
            return this.frame + this.FrameWP * 18;
        }
        if (this.Direction == 1) {
            return this.Direction * 6 + this.frame + this.FrameWP * 18;
        }
        return this.frame + 12 + this.FrameWP * 18;
    }

    public static DataSkillEff getEffMatNa(int n) {
        if (n == -1) {
            return null;
        }
        DataSkillEff dataSkillEff = (DataSkillEff)ALL_EFF_MAT_NA.get(n + "");
        if (dataSkillEff == null) {
            dataSkillEff = new DataSkillEff(n);
            dataSkillEff.typRequestImg = 113;
            ALL_EFF_MAT_NA.put(n + "", dataSkillEff);
            ImageIcon imageIcon = GameData.getImgIcon((short)(n + GameData.ID_START_SKILL), dataSkillEff.typRequestImg);
            dataSkillEff.timeRemove = (int)(mSystem.currentTimeMillis() / 1000L);
        } else {
            dataSkillEff.timeRemove = GameCanvas.timeNow;
        }
        return dataSkillEff;
    }

    public static void SetRemove() {
        Enumeration enumeration = ALL_EFF_MAT_NA.keys();
        while (enumeration.hasMoreElements()) {
            String string = (String)enumeration.nextElement();
            DataSkillEff dataSkillEff = (DataSkillEff)ALL_EFF_MAT_NA.get(string);
            if ((GameCanvas.timeNow - dataSkillEff.timeRemove) / 1000L <= 60L) continue;
            if (dataSkillEff.img_pp != null) {
                dataSkillEff.img_pp.clearData();
            }
            ALL_EFF_MAT_NA.remove(string);
        }
    }

    void testQuayphim() {
        this.name = "";
        if (this.clazz == 2) {
            this.idPhiPhong = (short)38;
            this.idWeaPon = (short)53;
            this.hat = 92;
            this.leg = 91;
            this.body = 91;
        } else if (this.clazz == 3) {
            this.idPhiPhong = (short)39;
            this.idWeaPon = (short)56;
            this.hat = 93;
            this.leg = 92;
            this.body = 92;
        } else if (this.clazz == 1) {
            this.idPhiPhong = (short)40;
            this.idWeaPon = (short)55;
            this.hat = 91;
            this.leg = 90;
            this.body = 90;
        } else {
            this.idPhiPhong = (short)41;
            this.idWeaPon = (short)54;
            this.hat = 90;
            this.leg = 89;
            this.body = 89;
        }
    }

    public void paintHorseUI(mGraphics mGraphics2, int n, int n2) {
        DataSkillEff dataSkillEff = MainObject.getEffMatNa(this.idHorse);
        if (dataSkillEff != null) {
            dataSkillEff.paintBottomHorse(mGraphics2, n, n2, this.getFrameHorse(), 0);
            dataSkillEff.paintTopHorse(mGraphics2, n, n2, this.getFrameHorse(), 0);
        }
    }

    public void paintPlayer(mGraphics mGraphics2, int n) {
        block77: {
            DataSkillEff dataSkillEff;
            block78: {
                block80: {
                    DataSkillEff dataSkillEff2;
                    block76: {
                        int n2;
                        block81: {
                            int n3;
                            int n4;
                            int n5;
                            DataSkillEff dataSkillEff3;
                            block82: {
                                DataSkillEff dataSkillEff4;
                                block79: {
                                    if (this.Action == 2 && this.idHorse != -1) {
                                        this.Fhorse = this.frame + 1;
                                    }
                                    if (this.isTanHinh) break block77;
                                    if (!this.Canpaint()) break block78;
                                    if (this.Action != 4 || this.isDongBang) break block79;
                                    mGraphics2.drawImage(shadow, this.x + 1, this.y + 1, 3, true);
                                    this.paintBuffFirst(mGraphics2);
                                    AvMain.fraPlayerDie.drawFrame(0, this.x + 1, this.y - this.ysai + 5, 0, 33, mGraphics2);
                                    if (this.effAuto != null) {
                                        this.isDongBang = false;
                                        this.timeDongBang = mSystem.currentTimeMillis();
                                        GameScreen.addEffectEndKill(15, this.x, this.y);
                                        this.effAuto = null;
                                    }
                                    if (this.frameDie == 0) {
                                        mGraphics2.drawImage(AvMain.imgEyeDie, this.x + 1, this.y - this.ysai + 5 - 24, 17, true);
                                    }
                                    break block80;
                                }
                                if (this.checktanghinh()) break block81;
                                if (this.idImageHenshin == -1 && this.idHorse == -1) {
                                    mGraphics2.drawImage(shadow, this.x + 1, this.y - this.ysai + 2, 3, true);
                                }
                                this.paintBuffFirst(mGraphics2);
                                this.paintEffauto_Low(mGraphics2, this.x, this.y);
                                this.paintDataEff_Top(mGraphics2, this.x, this.y);
                                n2 = (this.Direction == 0 || this.Direction == 2 || this.Direction == 3) && (this.clazz == 3 && this.frame != 4 || this.clazz != 3 && this.frame != 5) ? 1 : 0;
                                dataSkillEff3 = MainObject.getEffMatNa(this.idWeaPon);
                                if (GameCanvas.gameTick % 5 == 0 && dataSkillEff3 != null) {
                                    int n6 = dataSkillEff3.listFrame.size() / 18;
                                    if (n6 == 0) {
                                        n6 = 1;
                                    }
                                    this.FrameWP = (byte)((this.FrameWP + 1) % n6);
                                }
                                if ((dataSkillEff4 = MainObject.getEffMatNa(this.idHorse)) != null) {
                                    dataSkillEff4.paintBottomHorse(mGraphics2, this.x + this.xMount, this.y - this.ysai - this.dy + this.dyWater - this.yjum + this.yMount, this.getFrameHorse(), this.Direction == 3 ? 2 : 0);
                                }
                                n5 = 0;
                                if (this.idImageHenshin == -1) break block82;
                                MainImage mainImage = ObjectData.getImagePartMonster(this.idImageHenshin);
                                if (mainImage.img != null) {
                                    int n7 = this.Action;
                                    if (n7 > this.mAction.length - 1) {
                                        n7 = 0;
                                    }
                                    this.hOne = mImage.getImageHeight(mainImage.img.image) / 3;
                                    this.wOne = mImage.getImageWidth(mainImage.img.image) / 2;
                                    if (this.eye == 4 || this.eye == 2) {
                                        n7 = 3;
                                    }
                                    int n8 = 0;
                                    int n9 = this.mAction[n7][this.Direction > 2 ? 2 : this.Direction][this.frame] * this.hOne;
                                    n8 = this.mAction[n7][this.Direction > 2 ? 2 : this.Direction][this.frame] / 3 * this.wOne;
                                    n9 = this.mAction[n7][this.Direction > 2 ? 2 : this.Direction][this.frame] % 3 * this.hOne;
                                    mGraphics2.drawRegion(mainImage.img, n8, n9, this.wOne, this.hOne, this.Direction > 2 ? 2 : 0, this.x, this.y - this.dy + this.dyWater, 33, false);
                                }
                                break block76;
                            }
                            if (n2 != 0) {
                                try {
                                    this.paintWeapon(mGraphics2, n5);
                                    if (dataSkillEff3 != null && this.Action != 2) {
                                        dataSkillEff3.paintBottomWeaPon(mGraphics2, this.x, this.y + (this.typeMount != -1 ? -this.dyMount : 0) + this.dyWater - this.yjum, this.getFrameWP(), this.Direction == 3 ? 2 : 0);
                                        dataSkillEff3.paintTopWeaPon(mGraphics2, this.x, this.y + (this.typeMount != -1 ? -this.dyMount : 0) + this.dyWater - this.yjum, this.getFrameWP(), this.Direction == 3 ? 2 : 0);
                                    }
                                }
                                catch (Exception exception) {
                                    // empty catch block
                                }
                            }
                            boolean bl = false;
                            DataSkillEff dataSkillEff5 = MainObject.getEffMatNa(this.idPhiPhong);
                            DataSkillEff dataSkillEff6 = MainObject.getEffMatNa(this.idHair);
                            DataSkillEff dataSkillEff7 = MainObject.getEffMatNa(this.idBody);
                            DataSkillEff dataSkillEff8 = MainObject.getEffMatNa(this.idLeg);
                            DataSkillEff dataSkillEff9 = MainObject.getEffMatNa(this.idBienhinh);
                            if (GameCanvas.gameTick % 6 == 0 && dataSkillEff9 != null) {
                                n4 = dataSkillEff9.listFrame.size() / 18;
                                if (n4 == 0) {
                                    n4 = 1;
                                }
                                this.FrameBienhinh = (byte)((this.FrameBienhinh + 1) % n4);
                            }
                            if (GameCanvas.gameTick % 6 == 0 && dataSkillEff8 != null) {
                                n4 = dataSkillEff8.listFrame.size() / 18;
                                if (n4 == 0) {
                                    n4 = 1;
                                }
                                this.FrameLeg = (byte)((this.FrameLeg + 1) % n4);
                            }
                            if (GameCanvas.gameTick % 10 == 0 && dataSkillEff5 != null) {
                                n4 = dataSkillEff5.listFrame.size() / 18;
                                if (n4 == 0) {
                                    n4 = 1;
                                }
                                this.FramePP = (byte)((this.FramePP + 1) % n4);
                            }
                            if (GameCanvas.gameTick % 6 == 0 && dataSkillEff7 != null) {
                                n4 = dataSkillEff7.listFrame.size() / 18;
                                if (n4 == 0) {
                                    n4 = 1;
                                }
                                this.FrameBody = (byte)((this.FrameBody + 1) % n4);
                            }
                            if (GameCanvas.gameTick % 6 == 0 && dataSkillEff6 != null) {
                                n4 = dataSkillEff6.listFrame.size() / 18;
                                if (n4 == 0) {
                                    n4 = 1;
                                }
                                this.FrameHair = (byte)((this.FrameHair + 1) % n4);
                            }
                            DataSkillEff dataSkillEff10 = MainObject.getEffMatNa(this.idWing);
                            if (GameCanvas.gameTick % 6 == 0 && dataSkillEff10 != null) {
                                int n10 = dataSkillEff10.listFrame.size() / 18;
                                if (n10 == 0) {
                                    n10 = 1;
                                }
                                this.FrameWing = (byte)((this.FrameWing + 1) % n10);
                            }
                            if (this.Direction != 1 && dataSkillEff5 != null) {
                                dataSkillEff5.paintBottomPP(mGraphics2, this.x, this.y + (this.typeMount != -1 ? -this.dyMount : 0) + this.dyWater - this.yjum, this.getFramePP(), this.Direction == 3 ? 2 : 0);
                            }
                            DataSkillEff dataSkillEff11 = MainObject.getEffMatNa(this.idMatna);
                            if (GameCanvas.gameTick % 5 == 0 && dataSkillEff11 != null) {
                                n3 = dataSkillEff11.listFrame.size() / 18;
                                if (n3 == 0) {
                                    n3 = 1;
                                }
                                this.FrameMatNa = (byte)((this.FrameMatNa + 1) % n3);
                            }
                            if (dataSkillEff9 != null && this.idBienhinh != -1) {
                                dataSkillEff9.paintBottomAll(mGraphics2, this.x, this.y + (this.typeMount != -1 ? -this.dyMount : 0) + this.dyWater - this.yjum, this.getFrameBody(), this.Direction == 3 ? 2 : 0);
                                dataSkillEff9.paintTopAll(mGraphics2, this.x, this.y + (this.typeMount != -1 ? -this.dyMount : 0) + this.dyWater - this.yjum, this.getFrameBody(), this.Direction == 3 ? 2 : 0);
                            }
                            for (n3 = 0; n3 < mTypePartPaintPlayer[this.Direction].length; ++n3) {
                                byte by = mTypePartPaintPlayer[this.Direction][n3];
                                if (by == -1) {
                                    if (dataSkillEff11 == null) continue;
                                    dataSkillEff11.paintBottom(mGraphics2, this.x, this.y + (this.typeMount != -1 ? -this.dyMount : 0) + this.dyWater - this.yjum, this.getFrameMatNa(), this.Direction == 3 ? 2 : 0);
                                    continue;
                                }
                                if (this.Direction == 1) {
                                    if (dataSkillEff5 != null) {
                                        dataSkillEff5.paintBottomPP(mGraphics2, this.x, this.y + (this.typeMount != -1 ? -this.dyMount : 0) + this.dyWater - this.yjum, this.getFramePP(), this.Direction == 3 ? 2 : 0);
                                        dataSkillEff5.paintTopPP(mGraphics2, this.x, this.y + (this.typeMount != -1 ? -this.dyMount : 0) + this.dyWater - this.yjum, this.getFramePP(), this.Direction == 3 ? 2 : 0);
                                    }
                                    if (by == 2 && dataSkillEff11 != null) {
                                        dataSkillEff11.paintBottom(mGraphics2, this.x, this.y + (this.typeMount != -1 ? -this.dyMount : 0) + this.dyWater - this.yjum, this.getFrameMatNa(), this.Direction == 3 ? 2 : 0);
                                        dataSkillEff11.paintTop(mGraphics2, this.x, this.y + (this.typeMount != -1 ? -this.dyMount : 0) + this.dyWater - this.yjum, this.getFrameMatNa(), this.Direction == 3 ? 2 : 0);
                                    }
                                } else if (by == 4 && dataSkillEff11 != null && this.paintMatnaTruocNon) {
                                    dataSkillEff11.paintBottom(mGraphics2, this.x, this.y + (this.typeMount != -1 ? -this.dyMount : 0) + this.dyWater - this.yjum, this.getFrameMatNa(), this.Direction == 3 ? 2 : 0);
                                    dataSkillEff11.paintTop(mGraphics2, this.x, this.y + (this.typeMount != -1 ? -this.dyMount : 0) + this.dyWater - this.yjum, this.getFrameMatNa(), this.Direction == 3 ? 2 : 0);
                                }
                                if (by != 6 && by != 4 || by == 6 && this.Direction == 1 && n3 == 7 || by == 6 && this.Direction != 1 && n3 == 0 || by == 4 && this.Direction != 1) {
                                    if (by == 6 && dataSkillEff10 != null && this.idWing != -1) {
                                        dataSkillEff10.paintBottomWing(mGraphics2, this.x, this.y + (this.typeMount != -1 ? -this.dyMount : 0) + this.dyWater - this.yjum, this.getFrameWing(), this.Direction == 3 ? 2 : 0);
                                        dataSkillEff10.paintTopWing(mGraphics2, this.x, this.y + (this.typeMount != -1 ? -this.dyMount : 0) + this.dyWater - this.yjum, this.getFrameWing(), this.Direction == 3 ? 2 : 0);
                                        continue;
                                    }
                                    if (this.idBienhinh != -1 && dataSkillEff9 != null && (by == 1 || by == 0 || by == 3 || by == 5 || by == 2)) continue;
                                    if (by == 1 && this.idBody != -1 && dataSkillEff7 != null) {
                                        dataSkillEff7.paintBottomAll(mGraphics2, this.x, this.y + (this.typeMount != -1 ? -this.dyMount : 0) + this.dyWater - this.yjum, this.getFrameBody(), this.Direction == 3 ? 2 : 0);
                                        dataSkillEff7.paintTopAll(mGraphics2, this.x, this.y + (this.typeMount != -1 ? -this.dyMount : 0) + this.dyWater - this.yjum, this.getFrameBody(), this.Direction == 3 ? 2 : 0);
                                        continue;
                                    }
                                    if (by == 0 && this.idLeg != -1 && dataSkillEff8 != null) {
                                        dataSkillEff8.paintBottomAll(mGraphics2, this.x, this.y + (this.typeMount != -1 ? -this.dyMount : 0) + this.dyWater - this.yjum, this.getFrameLeg(), this.Direction == 3 ? 2 : 0);
                                        dataSkillEff8.paintTopAll(mGraphics2, this.x, this.y + (this.typeMount != -1 ? -this.dyMount : 0) + this.dyWater - this.yjum, this.getFrameLeg(), this.Direction == 3 ? 2 : 0);
                                        continue;
                                    }
                                    if (by == 5 && this.idHair != -1 && dataSkillEff6 != null) {
                                        dataSkillEff6.paintBottomHair(mGraphics2, this.x, this.y + (this.typeMount != -1 ? -this.dyMount : 0) + this.dyWater - this.yjum, this.getFrameHair(), this.Direction == 3 ? 2 : 0);
                                        dataSkillEff6.paintTopHair(mGraphics2, this.x, this.y + (this.typeMount != -1 ? -this.dyMount : 0) + this.dyWater - this.yjum, this.getFrameHair(), this.Direction == 3 ? 2 : 0);
                                        continue;
                                    }
                                    if (this.getTypePart(n3) >= 0) {
                                        if (this.typeMount == -1) {
                                            if (by == 6 && (this.Direction == 0 || this.Direction == 2 || this.Direction == 3)) {
                                                CRes.getCharPartInfo(by, this.getTypePart(n3)).paint(mGraphics2, this.x, this.y - this.ysai - this.dy + this.dyWater - this.yjum, this.Direction, this.frame);
                                                if (dataSkillEff5 != null) {
                                                    dataSkillEff5.paintBottomPP(mGraphics2, this.x, this.y + (this.typeMount != -1 ? -this.dyMount : 0) + this.dyWater - this.yjum, this.getFramePP(), this.Direction == 3 ? 2 : 0);
                                                    dataSkillEff5.paintTopPP(mGraphics2, this.x, this.y + (this.typeMount != -1 ? -this.dyMount : 0) + this.dyWater - this.yjum, this.getFramePP(), this.Direction == 3 ? 2 : 0);
                                                }
                                            } else {
                                                CRes.getCharPartInfo(by, this.getTypePart(n3)).paint(mGraphics2, this.x, this.y - this.ysai - this.dy + this.dyWater - this.yjum, this.Direction, this.frame);
                                            }
                                        } else if (n3 != 1) {
                                            if (by == 6 && (this.Direction == 0 || this.Direction == 2 || this.Direction == 3)) {
                                                CRes.getCharPartInfo(by, this.getTypePart(n3)).paint(mGraphics2, this.x, this.y - this.ysai - this.dy + this.dyWater - this.dyMount - this.yjum, this.Direction, this.frame);
                                                if (dataSkillEff5 != null) {
                                                    dataSkillEff5.paintBottomPP(mGraphics2, this.x, this.y + (this.typeMount != -1 ? -this.dyMount : 0) + this.dyWater - this.yjum, this.getFramePP(), this.Direction == 3 ? 2 : 0);
                                                    dataSkillEff5.paintTopPP(mGraphics2, this.x, this.y + (this.typeMount != -1 ? -this.dyMount : 0) + this.dyWater - this.yjum, this.getFramePP(), this.Direction == 3 ? 2 : 0);
                                                }
                                            } else {
                                                CRes.getCharPartInfo(by, this.getTypePart(n3)).paint(mGraphics2, this.x, this.y - this.ysai - this.dy + this.dyWater - this.dyMount - this.yjum, this.Direction, this.frame);
                                            }
                                        } else {
                                            this.paintMount_Sau(mGraphics2);
                                        }
                                    } else if (!(by != 0 && by != 1 && by != 2 && by != 4 && by != 5 || bl)) {
                                        bl = true;
                                        this.setReInfo();
                                    }
                                }
                                if (by != 3 || dataSkillEff11 == null || this.paintMatnaTruocNon || this.Direction == 1) continue;
                                dataSkillEff11.paintBottom(mGraphics2, this.x, this.y + (this.typeMount != -1 ? -this.dyMount : 0) + this.dyWater - this.yjum, this.getFrameMatNa(), this.Direction == 3 ? 2 : 0);
                                dataSkillEff11.paintTop(mGraphics2, this.x, this.y + (this.typeMount != -1 ? -this.dyMount : 0) + this.dyWater - this.yjum, this.getFrameMatNa(), this.Direction == 3 ? 2 : 0);
                            }
                            if (this.Direction != 1 && dataSkillEff5 != null) {
                                dataSkillEff5.paintTopPP(mGraphics2, this.x, this.y + (this.typeMount != -1 ? -this.dyMount : 0) + this.dyWater, this.getFramePP(), this.Direction == 3 ? 2 : 0);
                            }
                            if (this.typeMount != -1) {
                                this.paintMount_Truoc(mGraphics2);
                            }
                            if (n2 != 0) break block76;
                            try {
                                this.paintWeapon(mGraphics2, n5);
                                if (dataSkillEff3 != null && this.Action != 2) {
                                    dataSkillEff3.paintBottomWeaPon(mGraphics2, this.x, this.y + (this.typeMount != -1 ? -this.dyMount : 0) + this.dyWater - this.yjum, this.getFrameWP(), this.Direction == 3 ? 2 : 0);
                                    dataSkillEff3.paintTopWeaPon(mGraphics2, this.x, this.y + (this.typeMount != -1 ? -this.dyMount : 0) + this.dyWater - this.yjum, this.getFrameWP(), this.Direction == 3 ? 2 : 0);
                                }
                                break block76;
                            }
                            catch (Exception exception) {}
                            break block76;
                        }
                        for (n2 = 0; n2 < mTypePartPaintPlayer[this.Direction].length; ++n2) {
                            byte by = mTypePartPaintPlayer[this.Direction][n2];
                            if (by != 4 || this.getTypePart(n2) < 0) continue;
                            if (this.typeMount == -1) {
                                CRes.getCharPartInfo(by, this.getTypePart(n2)).paint(mGraphics2, this.x, this.y - this.ysai - this.dy + this.dyWater - this.yjum, this.Direction, this.frame);
                                continue;
                            }
                            if (n2 != 1) {
                                CRes.getCharPartInfo(by, this.getTypePart(n2)).paint(mGraphics2, this.x, this.y - this.ysai - this.dy + this.dyWater - this.dyMount - this.yjum, this.Direction, this.frame);
                                continue;
                            }
                            this.paintMount_Sau(mGraphics2);
                        }
                    }
                    if ((dataSkillEff2 = MainObject.getEffMatNa(this.idHorse)) != null) {
                        dataSkillEff2.paintTopHorse(mGraphics2, this.x + this.xMount, this.y - this.ysai - this.dy + this.dyWater - this.yjum + this.yMount, this.getFrameHorse(), this.Direction == 3 ? 2 : 0);
                    }
                }
                if (!this.checktanghinh()) {
                    this.paintDataEff_Bot(mGraphics2, this.x, this.y);
                    if (!this.useShip && this.isWater && this.dy == 0) {
                        int n11 = 1;
                        if (this.Direction == 2) {
                            n11 += 2;
                        } else if (this.Direction == 3) {
                            n11 -= 2;
                        }
                        mGraphics2.drawRegion(water, 0, (this.Action != 0 ? 2 : 0) * 17 + GameCanvas.gameTick / 2 % 2 * 17, 28, 17, 0, this.x + n11, this.y - this.ysai - 2 + this.dyWater, 3, true);
                    }
                }
            }
            if (!this.canpaintDataSkillEff()) {
                this.paintDataEff_Top(mGraphics2, this.x, this.y);
                this.paintDataEff_Bot(mGraphics2, this.x, this.y);
            }
            if (!this.checktanghinh() && (dataSkillEff = MainObject.getEffMatNa(this.idName)) != null) {
                if (GameCanvas.gameTick % 5 == 0 && dataSkillEff != null) {
                    int n12 = dataSkillEff.listFrame.size();
                    if (n12 == 0) {
                        n12 = 1;
                    }
                    this.FrameName = (byte)((this.FrameName + 1) % n12);
                }
                dataSkillEff.paintBottomName(mGraphics2, this.x, this.y + (this.typeMount != -1 ? -this.dyMount : 0) + this.dyWater - this.yjum, this.FrameName, 0);
                dataSkillEff.paintTopName(mGraphics2, this.x, this.y + (this.typeMount != -1 ? -this.dyMount : 0) + this.dyWater - this.yjum, this.FrameName, 0);
            }
            if (!this.checktanghinh()) {
                if ((PaintInfoGameScreen.isLevelPoint || this != GameScreen.ObjFocus) && n != -1) {
                    this.paintName(mGraphics2, n);
                }
                this.paintBuffLast(mGraphics2);
                this.paint_no(mGraphics2);
                this.paintEffauto(mGraphics2, this.x, this.y);
                if (this.hp > 0) {
                    this.paintDongBang(mGraphics2);
                }
            }
        }
    }

    public void paintEffauto(mGraphics mGraphics2, int n, int n2) {
        if (!this.isMainChar() && hideEff == 1) {
            return;
        }
        for (int i = 0; i < this.vecEffauto.size(); ++i) {
            EffectAuto effectAuto = (EffectAuto)this.vecEffauto.elementAt(i);
            if (effectAuto == null) continue;
            effectAuto.paint(mGraphics2, n, n2 - this.yEffAuto);
        }
    }

    public void paintDataEff_Top(mGraphics mGraphics2, int n, int n2) {
        if (!this.isMainChar() && hideEff == 1) {
            return;
        }
        int n3 = this.vecDataSkillEff.size();
        if (n3 > 0) {
            for (int i = 0; i < n3; ++i) {
                DataSkillEff dataSkillEff = (DataSkillEff)this.vecDataSkillEff.elementAt(i);
                if (dataSkillEff == null) continue;
                dataSkillEff.paintTop(mGraphics2, n, n2);
            }
        }
    }

    public void paintDataEff_Bot(mGraphics mGraphics2, int n, int n2) {
        if (!this.isMainChar() && hideEff == 1) {
            return;
        }
        int n3 = this.vecDataSkillEff.size();
        if (n3 > 0) {
            for (int i = 0; i < n3; ++i) {
                DataSkillEff dataSkillEff = (DataSkillEff)this.vecDataSkillEff.elementAt(i);
                if (dataSkillEff == null) continue;
                dataSkillEff.paintBottom(mGraphics2, n, n2);
            }
        }
    }

    public void paintEffauto_Low(mGraphics mGraphics2, int n, int n2) {
        if (!this.isMainChar() && hideEff == 1) {
            return;
        }
        for (int i = 0; i < this.veclowEffauto.size(); ++i) {
            EffectAuto effectAuto = (EffectAuto)this.veclowEffauto.elementAt(i);
            if (effectAuto == null) continue;
            effectAuto.paint(mGraphics2, n, n2 - this.yEffAuto);
        }
    }

    public void updateEffauto() {
        EffectAuto effectAuto;
        int n;
        for (n = 0; n < this.vecEffauto.size(); ++n) {
            effectAuto = (EffectAuto)this.vecEffauto.elementAt(n);
            if (effectAuto == null) continue;
            effectAuto.update();
            if (!effectAuto.wantdestroy) continue;
            this.vecEffauto.removeElement(effectAuto);
        }
        for (n = 0; n < this.veclowEffauto.size(); ++n) {
            effectAuto = (EffectAuto)this.veclowEffauto.elementAt(n);
            if (effectAuto == null) continue;
            effectAuto.update();
            if (!effectAuto.wantdestroy) continue;
            this.veclowEffauto.removeElement(effectAuto);
        }
    }

    public boolean checktanghinh() {
        for (int i = 0; i < this.vecDataSkillEff.size(); ++i) {
            DataSkillEff dataSkillEff = (DataSkillEff)this.vecDataSkillEff.elementAt(i);
            if (dataSkillEff == null || !dataSkillEff.isTanghinhbyEffauto()) continue;
            return true;
        }
        return false;
    }

    public boolean Canpaint() {
        Object object;
        int n;
        int n2 = this.vecEffauto.size();
        if (n2 > 0) {
            for (n = 0; n < n2; ++n) {
                object = (EffectAuto)this.vecEffauto.elementAt(n);
                if (object == null || !((EffectAuto)object).CanpaintByeffauto()) continue;
                return false;
            }
        }
        for (n = 0; n < this.vecDataSkillEff.size(); ++n) {
            object = (DataSkillEff)this.vecDataSkillEff.elementAt(n);
            if (object == null || !((DataSkillEff)object).CanpaintByeffauto()) continue;
            return false;
        }
        return true;
    }

    public boolean canpaintDataSkillEff() {
        for (int i = 0; i < this.vecDataSkillEff.size(); ++i) {
            DataSkillEff dataSkillEff = (DataSkillEff)this.vecDataSkillEff.elementAt(i);
            if (dataSkillEff == null || !dataSkillEff.CanpaintByeffauto()) continue;
            return false;
        }
        return true;
    }

    public boolean Canmove() {
        Object object;
        int n;
        if (this.isSleep || this.isStun || this.isno || this.isDongBang) {
            return false;
        }
        int n2 = this.vecEffauto.size();
        if (n2 > 0) {
            for (n = 0; n < n2; ++n) {
                object = (EffectAuto)this.vecEffauto.elementAt(n);
                if (object == null || !((EffectAuto)object).lockmoveByeffAuto()) continue;
                return false;
            }
        }
        if (this.veclowEffauto.size() > 0) {
            for (n = 0; n < this.veclowEffauto.size(); ++n) {
                object = (EffectAuto)this.veclowEffauto.elementAt(n);
                if (object == null || !((EffectAuto)object).lockmoveByeffAuto()) continue;
                return false;
            }
        }
        for (n = 0; n < this.vecDataSkillEff.size(); ++n) {
            object = (DataSkillEff)this.vecDataSkillEff.elementAt(n);
            if (object == null || !((DataSkillEff)object).lockmoveByeffAuto()) continue;
            return false;
        }
        return true;
    }

    public void paintDongBang(mGraphics mGraphics2) {
        if (!this.isDongBang || this.Action == 4) {
            return;
        }
        if (this.isDongBang && this.effAuto != null) {
            this.effAuto.paint(mGraphics2);
        }
    }

    public int getTypePart(int n) {
        switch (mTypePartPaintPlayer[this.Direction][n]) {
            case 0: {
                return this.idPartFashion[1] != -1 ? this.idPartFashion[1] : this.leg;
            }
            case 1: {
                return this.idPartFashion[0] != -1 ? this.idPartFashion[0] : this.body;
            }
            case 2: {
                if (this.idPartFashion.length > 6) {
                    return this.idPartFashion[6] != -1 ? this.idPartFashion[6] : this.head;
                }
                return this.head;
            }
            case 3: {
                if (this.isHairNotHat()) {
                    return -1;
                }
                return this.idPartFashion[2] != -1 ? this.idPartFashion[2] : this.hat;
            }
            case 4: {
                return this.eye;
            }
            case 5: {
                return this.idPartFashion[5] != -1 ? this.idPartFashion[5] : this.hair;
            }
            case 6: {
                return this.idPartFashion[4] != -1 ? this.idPartFashion[4] : this.wing;
            }
        }
        return -1;
    }

    public int getTypeParthide(int n) {
        switch (mTypePartPaintPlayer[this.Direction][n]) {
            case 0: {
                return this.clazz;
            }
            case 1: {
                return this.clazz;
            }
            case 2: {
                return 0;
            }
            case 3: {
                return -1;
            }
            case 4: {
                return this.clazz + 8;
            }
            case 5: {
                return this.clazz;
            }
            case 6: {
                return -1;
            }
        }
        return -1;
    }

    public void setReInfo() {
        if ((GameCanvas.timeNow - this.timeRePlayerInfo) / 1000L > 60L) {
            GlobalService.gI().char_info((short)this.ID);
            this.timeRePlayerInfo = GameCanvas.timeNow;
        }
    }

    public void paintShowPlayer(mGraphics mGraphics2, int n, int n2, int n3) {
        int n4;
        int n5;
        int n6 = GameCanvas.gameTick / 6 % 2;
        DataSkillEff dataSkillEff = MainObject.getEffMatNa(this.idPhiPhong);
        DataSkillEff dataSkillEff2 = MainObject.getEffMatNa(this.idHair);
        DataSkillEff dataSkillEff3 = MainObject.getEffMatNa(this.idBody);
        DataSkillEff dataSkillEff4 = MainObject.getEffMatNa(this.idWing);
        DataSkillEff dataSkillEff5 = MainObject.getEffMatNa(this.idLeg);
        if (GameCanvas.gameTick % 6 == 0 && dataSkillEff4 != null) {
            n5 = dataSkillEff4.listFrame.size() / 18;
            if (n5 == 0) {
                n5 = 1;
            }
            this.FrameWing = (byte)((this.FrameWing + 1) % n5);
        }
        if (GameCanvas.gameTick % 10 == 0 && dataSkillEff != null) {
            n5 = dataSkillEff.listFrame.size() / 18;
            if (n5 == 0) {
                n5 = 1;
            }
            this.FramePP = (byte)((this.FramePP + 1) % n5);
        }
        if (dataSkillEff != null && this.wing == -1 && this.idPartFashion[4] == -1) {
            dataSkillEff.paintBottomPP(mGraphics2, n, n2, this.getFramePP_Wearing(n6), 0);
            dataSkillEff.paintTopPP(mGraphics2, n, n2, this.getFramePP_Wearing(n6), 0);
        }
        DataSkillEff dataSkillEff6 = MainObject.getEffMatNa(this.idMatna);
        DataSkillEff dataSkillEff7 = MainObject.getEffMatNa(this.idWeaPon);
        if (GameCanvas.gameTick % 5 == 0 && dataSkillEff6 != null) {
            n4 = dataSkillEff6.listFrame.size() / 18;
            if (n4 == 0) {
                n4 = 1;
            }
            this.FrameMatNa = (byte)((this.FrameMatNa + 1) % n4);
        }
        if (GameCanvas.gameTick % 6 == 0 && dataSkillEff2 != null) {
            n4 = dataSkillEff2.listFrame.size() / 18;
            if (n4 == 0) {
                n4 = 1;
            }
            this.FrameHair = (byte)((this.FrameHair + 1) % n4);
        }
        if (GameCanvas.gameTick % 6 == 0 && dataSkillEff3 != null) {
            n4 = dataSkillEff3.listFrame.size() / 18;
            if (n4 == 0) {
                n4 = 1;
            }
            this.FrameBody = (byte)((this.FrameBody + 1) % n4);
        }
        if (GameCanvas.gameTick % 6 == 0 && dataSkillEff5 != null) {
            n4 = dataSkillEff5.listFrame.size() / 18;
            if (n4 == 0) {
                n4 = 1;
            }
            this.FrameLeg = (byte)((this.FrameLeg + 1) % n4);
        }
        if (dataSkillEff7 != null) {
            dataSkillEff7.paintBottomWeaPon(mGraphics2, n, n2, this.getFrameWP_Wearing(n6), 0);
            dataSkillEff7.paintTopWeaPon(mGraphics2, n, n2, this.getFrameWP_Wearing(n6), 0);
        }
        mGraphics2.drawImage(shadow, n + 1, n2 + 2, 3, true);
        n4 = this.weaponType;
        if (n4 != -1 && CRes.loadImgWeaPone(this.clazz, n4, 0) != null) {
            WeaponInfo weaponInfo = CRes.loadImgWeaPone(this.clazz, n4, 0);
            if (weaponInfo.img != null) {
                mGraphics2.drawRegion(weaponInfo.img, weaponInfo.mRegion[n3][0], 0, weaponInfo.mRegion[n3][1], weaponInfo.himg, 0, n + weaponInfo.mPos[n3][n6][0], n2 + weaponInfo.mPos[n3][n6][1], 0, true);
            }
        }
        this.paintDataEff_Top(mGraphics2, n, n2);
        for (int i = 0; i < mTypePartPaintPlayer[this.Direction].length - 1; ++i) {
            byte by = mTypePartPaintPlayer[0][i];
            if (by == -1 && dataSkillEff6 != null) {
                dataSkillEff6.paintBottom(mGraphics2, n, n2 + 2, this.getFrameMatNa_Wearing(n6), 0);
            }
            if (by == 5 && this.idHair != -1 && dataSkillEff2 != null) {
                dataSkillEff2.paintBottomHair(mGraphics2, n, n2 + 2, this.getFrameHair_Wearing(n6), 0);
                dataSkillEff2.paintTopHair(mGraphics2, n, n2 + 2, this.getFrameHair_Wearing(n6), 0);
                continue;
            }
            if (by == 1 && this.idBody != -1 && dataSkillEff3 != null) {
                dataSkillEff3.paintBottomAll(mGraphics2, n, n2 + 2, this.getFrameBody_Wearing(n6), 0);
                dataSkillEff3.paintTopAll(mGraphics2, n, n2 + 2, this.getFrameBody_Wearing(n6), 0);
                continue;
            }
            if (by == 0 && this.idLeg != -1 && dataSkillEff5 != null) {
                dataSkillEff5.paintBottomAll(mGraphics2, n, n2 + 2, this.getFrameLeg_Wearing(n6), 0);
                dataSkillEff5.paintTopAll(mGraphics2, n, n2 + 2, this.getFrameLeg_Wearing(n6), 0);
                continue;
            }
            if (by == 6 && this.idWing != -1 && dataSkillEff4 != null) {
                dataSkillEff4.paintBottomWing(mGraphics2, n, n2 + 2, this.getFrameWing_Wearing(n6), 0);
                dataSkillEff4.paintTopWing(mGraphics2, n, n2 + 2, this.getFrameWing_Wearing(n6), 0);
                continue;
            }
            if (this.getTypePart(i) <= -1) continue;
            if (by == 6 && (this.wing != -1 || this.idPartFashion[4] != -1)) {
                CRes.getCharPartInfo(by, this.getTypePart(i)).paint(mGraphics2, n, n2, n3, n6);
                if (dataSkillEff == null) continue;
                dataSkillEff.paintBottomPP(mGraphics2, n, n2, this.getFramePP_Wearing(n6), 0);
                dataSkillEff.paintTopPP(mGraphics2, n, n2, this.getFramePP_Wearing(n6), 0);
                continue;
            }
            CRes.getCharPartInfo(by, this.getTypePart(i)).paint(mGraphics2, n, n2, n3, n6);
        }
        if (dataSkillEff6 != null) {
            dataSkillEff6.paintBottom(mGraphics2, n, n2 + 2, this.getFrameMatNa_Wearing(n6), 0);
            dataSkillEff6.paintTop(mGraphics2, n, n2 + 2, this.getFrameMatNa_Wearing(n6), 0);
        }
        this.paintDataEff_Bot(mGraphics2, n, n2);
    }

    public void paintShowHairPlayer(mGraphics mGraphics2, int n, int n2, int n3) {
        int n4 = 0;
        int n5 = GameCanvas.gameTick / 6 % 2;
        mGraphics2.drawImage(shadow, n + 1, n2 + 2, 3, false);
        int n6 = this.weaponType;
        if (n6 != -1 && CRes.loadImgWeaPone(this.clazz, n6, 0) != null) {
            WeaponInfo weaponInfo = CRes.loadImgWeaPone(this.clazz, n6, 0);
            if (weaponInfo.img != null) {
                mGraphics2.drawRegion(weaponInfo.img, weaponInfo.mRegion[this.Direction][0], 0, weaponInfo.mRegion[this.Direction][1], weaponInfo.himg, 0, n + weaponInfo.mPos[this.Direction][n5][0], n2 + weaponInfo.mPos[this.Direction][n5][1], 0, false);
            }
        }
        for (int i = 0; i < mTypePartPaintPlayer[this.Direction].length - 1; ++i) {
            byte by = mTypePartPaintPlayer[this.Direction][i];
            if (by == 5) {
                if (n3 == -1) continue;
                CRes.getCharPartInfo(5, n3).paint(mGraphics2, n, n2, n4, n5);
                continue;
            }
            if (this.getTypePart(i) <= -1) continue;
            CRes.getCharPartInfo(by, this.getTypePart(i)).paint(mGraphics2, n, n2, n4, n5);
        }
    }

    public void paintBigAvatar(mGraphics mGraphics2, int n, int n2) {
    }

    public void paintName(mGraphics mGraphics2, int n) {
        if (GameScreen.infoGame.isMapThachdau()) {
            return;
        }
        String string = this.name;
        mFont mFont2 = mFont.tahoma_7_white;
        mFont2 = MainTabNew.setTextColor(n);
        boolean bl = true;
        int n2 = 0;
        if (this.typeSpec == 1) {
            if (this.typePk > 0) {
                bl = false;
            }
            n2 = 5;
        }
        if (this.idName != -1) {
            bl = false;
        }
        int n3 = 18;
        if (PaintInfoGameScreen.isLevelPoint) {
            n3 = 12;
        }
        if (this.typeMonster == 7) {
            n3 += 8;
        }
        if (this.isObject && PaintInfoGameScreen.isLevelPoint) {
            n3 += 6;
        }
        if (bl) {
            mFont2.drawString(mGraphics2, string, this.x, this.y - this.ysai - this.dy + this.dyWater - (this.isDongBang ? 5 : 0) - this.hOne - n3 - this.dyMount - this.yjum, 2, false);
        }
        if (this.typeObject == 0 && this.typeSpec == 1 && bl && !this.iscuop) {
            mFont2.drawString(mGraphics2, T.nhanban, this.x, this.y - this.ysai - this.dy + this.dyWater - (this.isDongBang ? 5 : 0) - this.hOne - (n3 += 10) - this.dyMount - this.yjum, 2, false);
        }
        if (this.typeObject == 2 && this.chat == null) {
            AvMain.fraQuest.drawFrame(this.typeNPC, this.x - 6, this.y - this.ysai - this.dy + this.dyWater - this.hOne - n3 - 18 - 4 + GameCanvas.gameTick / 2 % 4, 0, mGraphics2);
        }
        int n4 = 0;
        if (Player.party != null && this.isParty || this.isShowHP || this.typeMonster == 7) {
            int n5 = 44;
            if (this.typeObject == 2 || this.typeMonster == 7) {
                n5 = this.hOne + 5;
            }
            mGraphics2.setColor(-8714722);
            mGraphics2.fillRect(this.x - 20, this.y - this.ysai - this.dy + this.dyWater - n5 - n3, 40, 3, false);
            mGraphics2.setColor(-579511);
            mGraphics2.fillRect(this.x - 20, this.y - this.ysai - this.dy + this.dyWater - n5 - n3, 40 * this.hp / this.maxHp, 3, false);
            n4 += 5;
        }
        if (this.myClan != null && this.typeSpec != 1) {
            this.paintIconClan(mGraphics2, this.x - 1, this.y - this.ysai - this.dy + this.dyWater - this.hOne - n3 - 8 - n4 - this.dyMount - this.yjum, 2);
            n4 += 16;
        }
        if (this.typePk >= 0 && this.typeObject == 0 && !this.isPkVantieu()) {
            AvMain.fraPk.drawFrame(this.typePk * 3 + GameCanvas.gameTick / 3 % 3, this.x, this.y - this.dy + this.dyWater - this.ysai - (n4 += 59) + 18 - n3 + n2 - this.dyMount - this.yjum, 0, 33, mGraphics2);
        }
    }

    public void paintNameShow(mGraphics mGraphics2, int n, int n2, boolean bl) {
        String string = this.getNameAndClan(" - ");
        if (this.myClan != null) {
            this.paintIconClan(mGraphics2, (n += 16) - 8, n2 + 6, -1);
        }
        mFont.tahoma_7b_white.drawString(mGraphics2, string, n, n2 - this.dyMount - this.yjum, 0, true);
    }

    public void paintNameFocus(mGraphics mGraphics2) {
        if (GameScreen.infoGame.isMapThachdau()) {
            return;
        }
        if (GameScreen.ObjFocus != null && !PaintInfoGameScreen.isLevelPoint) {
            MainObject mainObject = GameScreen.ObjFocus;
            int n = mainObject.x;
            int n2 = mainObject.y - mainObject.ysai - mainObject.dy + mainObject.dyWater - mainObject.hOne;
            int n3 = 18;
            boolean bl = true;
            int n4 = 0;
            if (this.typeSpec == 1) {
                if (this.typePk > 0) {
                    bl = false;
                }
                n4 = 5;
            }
            mFont mFont2 = mFont.tahoma_7b_white;
            if (this.typeObject == 3) {
                mFont2 = MainTabNew.setTextColorName(this.colorName);
            }
            if (this.typeObject == 2 && this.chat == null) {
                AvMain.fraQuest.drawFrame(this.typeNPC, n - 6, n2 - n3 - 18 - 4 + GameCanvas.gameTick / 2 % 4, 0, mGraphics2);
            }
            if (this.idName == -1) {
                mFont2.drawString(mGraphics2, this.name + "", n, n2 - n3 - this.dyMount - (this.isDongBang ? 5 : 0) - this.yjum, 2, false);
            }
            int n5 = 0;
            if (this.typeObject == 0 && this.typeSpec == 1 && bl && !this.iscuop) {
                n5 += 10;
                mFont2.drawString(mGraphics2, T.nhanban, n, n2 - (n3 += 10) - this.dyMount - (this.isDongBang ? 5 : 0) - this.yjum, 2, false);
            }
            if (Player.party != null && mainObject.isParty) {
                n5 = 5;
            }
            if (this.myClan != null && mainObject.typeSpec != 1) {
                this.paintIconClan(mGraphics2, n, n2 - n3 - 8 - n5 - this.dyMount - this.yjum, 2);
                n5 += 16;
            }
            if (mainObject.typePk >= 0 && mainObject.typeObject == 0 && !this.isPkVantieu()) {
                AvMain.fraPk.drawFrame(this.typePk * 3 + GameCanvas.gameTick / 3 % 3, n, n2 - (n5 += 20) + n4 - this.dyMount - this.yjum, 0, 33, mGraphics2);
            }
        }
    }

    public static void updatefocus() {
        if (GameScreen.ObjFocus != null) {
            long l;
            long l2;
            long l3;
            if (GameScreen.ObjFocus.maxHp > 0 && !PaintInfoGameScreen.isLevelPoint && GameScreen.ObjFocus.typeObject != 10 && (framefocus = (byte)((l3 = (l2 = (long)GameScreen.ObjFocus.hp) * (l = 10L)) / (long)GameScreen.ObjFocus.maxHp)) > 9) {
                framefocus = (byte)9;
            }
            framefocus = (byte)CRes.abs(framefocus - 9);
            if (GameScreen.ObjFocus.typeObject == 3 || GameScreen.ObjFocus.typeObject == 4) {
                framefocus = 0;
            }
        }
    }

    public static void paintFocus(mGraphics mGraphics2) {
        if (GameScreen.ObjFocus != null && !PaintInfoGameScreen.isLevelPoint && GameScreen.ObjFocus.typeObject != 10) {
            MainObject mainObject = GameScreen.ObjFocus;
            int n = 1;
            if (mainObject.typeObject == 1) {
                if (!mainObject.canFocusMon) {
                    return;
                }
                n = 4;
                if (mainObject.typeMonster == 3 || mainObject.typeMonster == 5 || mainObject.typeMonster == 10 || mainObject.typeMonster == 8) {
                    n = 12;
                }
            }
            if (Wfc == 0) {
                Wfc = mImage.getImageWidth(MainObject.newfocus.image);
                Hfc = mImage.getImageHeight(MainObject.newfocus.image) / 10;
            }
            mGraphics2.drawRegion(newfocus, 0, framefocus * Hfc, Wfc, Hfc, 0, mainObject.x, mainObject.y - mainObject.hOne - mainObject.dy + mainObject.dyWater - n - GameCanvas.gameTick % 7 - mainObject.dyMount - mainObject.yjum, 3, false);
            if (Player.party != null && mainObject.isParty || mainObject.isShowHP) {
                int n2 = 64;
                if (mainObject.typeObject == 2) {
                    n2 = mainObject.hOne + 23;
                }
                mGraphics2.setColor(-8714722);
                mGraphics2.fillRect(mainObject.x - 20, mainObject.y - n2 - mainObject.dyMount - mainObject.yjum, 40, 3, false);
                mGraphics2.setColor(-579511);
                mGraphics2.fillRect(mainObject.x - 20, mainObject.y - n2 - mainObject.dyMount - mainObject.yjum, 40 * mainObject.hp / mainObject.maxHp, 3, false);
            }
        }
    }

    public static void paintHPQuai(mGraphics mGraphics2, int n, int n2, int n3) {
        mGraphics2.setColor(0);
        mGraphics2.fillRect(n - 13, n2 - 3, 26, 5, true);
        mGraphics2.setColor(-1);
        mGraphics2.fillRect(n - 12, n2 - 2, 24, 3, true);
        mGraphics2.setColor(-4052663);
        mGraphics2.fillRect(n - 12, n2 - 2, n3, 3, true);
    }

    public void updateDataEffect() {
        int n = this.vecDataSkillEff.size();
        if (n > 0) {
            for (int i = 0; i < n; ++i) {
                DataSkillEff dataSkillEff = (DataSkillEff)this.vecDataSkillEff.elementAt(i);
                if (dataSkillEff == null) continue;
                dataSkillEff.update();
                if (!dataSkillEff.wantDetroy) continue;
                this.vecDataSkillEff.removeElement(dataSkillEff);
            }
        }
    }

    public void update() {
        this.x += this.vx;
        this.y += this.vy;
        if (this.isjum) {
            this.yjum += this.vjum;
            this.vjum += this.mjum;
            if (this.yjum > this.range) {
                this.yjum = this.range;
                this.vjum = -5;
                this.mjum = -2;
            }
            if (this.yjum <= 0) {
                this.yjum = 0;
                this.isjum = false;
            }
        }
        if (this.isMoveOut) {
            this.move_to_point();
        }
        this.updateDy();
        if (this.chat != null) {
            this.chat.updatePos(this.x, this.y - this.hOne - 30);
            if ((this != GameScreen.player || GameScreen.player.currentQuest == null) && this.chat.setOff()) {
                this.chat = null;
            }
        }
        if (this.isTanHinh) {
            ++this.timeTanHinh;
            if (this.timeTanHinh > 40) {
                this.timeTanHinh = 0;
                this.isTanHinh = false;
            }
        }
        for (int i = 0; i < this.vecBuff.size(); ++i) {
            MainBuff mainBuff = (MainBuff)this.vecBuff.elementAt(i);
            mainBuff.update();
            if (!mainBuff.isRemove) continue;
            mainBuff.clearData();
            this.vecBuff.removeElement(mainBuff);
            --i;
        }
        if (this.isSend) {
            ++this.timeGet;
            if (this.timeGet > 40) {
                this.isSend = false;
                this.timeGet = 0;
            }
        }
        if (this.strChatPopup != null) {
            this.addChat(this.strChatPopup, true);
            this.strChatPopup = null;
        }
        if (this.myClan != null && this.myClan.isRemove) {
            this.myClan = null;
        }
        if (this.isSleep) {
            this.vx = 0;
            this.vy = 0;
            this.toX = this.x;
            this.toY = this.y;
            this.eye = 0;
            if (this.isDie || this.timeSleep - mSystem.currentTimeMillis() < 0L) {
                this.isSleep = false;
                this.timeSleep = mSystem.currentTimeMillis();
            }
        }
        if (this.isStun) {
            this.vx = 0;
            this.vy = 0;
            this.toX = this.x;
            this.toY = this.y;
            if (this.isDie || this.timeStun - mSystem.currentTimeMillis() < 0L) {
                this.isStun = false;
                this.timeStun = mSystem.currentTimeMillis();
            }
        }
        if (this.isDongBang) {
            this.vx = 0;
            this.vy = 0;
            this.toX = this.x;
            this.toY = this.y;
            if (this.effAuto == null) {
                this.effAuto = new EffectAuto(51, this.x, this.y, 0, 0, 0, 0);
            }
            if (this.effAuto != null) {
                this.effAuto.update();
            }
            if (this.isDie || this.timeDongBang - mSystem.currentTimeMillis() < 0L) {
                this.isDongBang = false;
                this.timeDongBang = mSystem.currentTimeMillis();
                GameScreen.addEffectEndKill(15, this.x, this.y);
                this.effAuto = null;
            }
        }
        if (this.isBinded) {
            this.vx = 0;
            this.vy = 0;
            this.toX = this.x;
            this.toY = this.y;
            if (this.isDie || this.timeBind - mSystem.currentTimeMillis() < 0L) {
                this.isBinded = false;
                this.timeBind = mSystem.currentTimeMillis();
            }
        }
        if (this.isno) {
            this.vx = 0;
            this.vy = 0;
            this.toX = this.x;
            this.toY = this.y;
            if (this.isDie || this.timeno - mSystem.currentTimeMillis() < 0L) {
                this.isno = false;
                this.timeno = mSystem.currentTimeMillis();
            }
        }
        if (this.isnoBoss84 && (this.isDie || (this.timenoBoss84 - mSystem.currentTimeMillis()) / 1000L <= 0L)) {
            this.isnoBoss84 = false;
            this.timenoBoss84 = mSystem.currentTimeMillis();
        }
        if (this.isNPC() && this.Direction != 0) {
            this.Direction = 0;
        }
        if (this.typeMount == -1 && this.dyMount != 0) {
            this.resetMount();
        }
        this.updateMount();
        this.updateEffauto();
        MainObject.updatefocus();
        this.updateEffectWeapon();
    }

    public void updateActionPerson() {
        if (this.Action == 4) {
            if (this.typeMount != -1) {
                this.typeMount = (byte)-1;
            }
            if (this.frameDie == 0) {
                if (CRes.random(20) == 0) {
                    this.frameDie = 1;
                }
            } else if (CRes.random(3) == 0) {
                this.frameDie = 0;
            }
            this.isDongBang = false;
            this.effAuto = null;
            if (!GameCanvas.lowGraphic && CRes.random(50) == 0) {
                GameScreen.addEffectKill(69, this.ID, this.typeObject, this.ID, this.typeObject, 0, 0);
                if (CRes.random(10) == 0) {
                    GameScreen.addEffectKill(69, this.ID, this.typeObject, this.ID, this.typeObject, 0, 0);
                }
            }
            return;
        }
        switch (this.Action) {
            case 0: {
                ++this.f;
                if (this.f > this.A_Stand.length - 1) {
                    this.f = 0;
                }
                this.Fhorse = this.frame = this.A_Stand[this.f];
                break;
            }
            case 1: {
                ++this.f;
                if (this.idHorse != -1) {
                    if (this.f > horseMove.length - 1) {
                        this.f = 0;
                    }
                } else if (this.isPhiHanh) {
                    if (this.f > this.A_Stand.length - 1) {
                        this.f = 0;
                    }
                } else if (this.f > this.A_Move.length - 1) {
                    this.f = 0;
                }
                if (this.vx == 0 && this.vy == 0 && this.posTransRoad == null) {
                    this.Action = 0;
                    this.frameLeg = CRes.random(4);
                    this.f = 0;
                }
                if (this.idHorse != -1) {
                    this.Fhorse = horseMove[this.f];
                    this.frame = 3;
                } else {
                    this.frame = this.isPhiHanh ? this.A_Stand[this.f] : this.A_Move[this.f];
                }
                if (this.isMainChar() && this.idHorse == 114 && !this.isWater && GameCanvas.gameTick % 5 == 0) {
                    GameScreen.addEffectEndKill_Direction(58, this.x, this.y + (this.Direction == 2 || this.Direction == 3 ? 10 : 0), this.Direction, (byte)-1);
                }
                if (!this.isFootSnow || this.isWater || GameCanvas.gameTick % 5 != 0) break;
                GameScreen.addEffectEndKill_Direction(56, this.x, this.y, this.Direction, (byte)-1);
                break;
            }
            case 2: {
                if (this.PlashNow != null) {
                    this.ListKillNow.updateEffSkill();
                    this.PlashNow.update(this);
                }
                this.ffire = (byte)(this.ffire + 1);
                if (this.ffire > this.A_Stand.length - 1) {
                    this.ffire = 0;
                }
                this.Fhorse = this.A_Stand[this.ffire];
            }
        }
    }

    public void updateDy() {
        if (this.dyKill > 0) {
            this.dy = this.dyKill;
            ++this.timeDyKill;
            if (this.timeDyKill > 30) {
                this.dyKill = 30;
            }
        } else {
            if (this.dy > 0) {
                this.vDy -= 2;
                this.dy += this.vDy;
            }
            if (this.dy < 0) {
                this.dy = -this.dy;
                this.vDy = 0;
            }
            if (this.dy < 3) {
                this.dy = 0;
            }
            this.timeDyKill = 0;
        }
    }

    public void updateEye() {
        if (this.hp < this.hpEffect) {
            this.eye = 3;
        } else {
            if (this.eye == 3) {
                this.eye = this.EyeMain;
            }
            if (this.eye != -1) {
                ++this.timeEye;
                if (this.eye < 3 || this.eye == 4 || this.eye == 5) {
                    if (this.eye == 4 && this.timeEye > 2) {
                        this.eye = 5;
                    }
                    if (this.eye == 2) {
                        if (this.timeEye >= 8) {
                            this.timeEye = 0;
                            this.eye = this.EyeMain;
                            this.endEye = this.clazz < 2 ? CRes.random(30, 80) : CRes.random(10, 60);
                        }
                    } else if (this.timeEye >= 3) {
                        this.timeEye = 0;
                        this.eye = this.EyeMain;
                        this.endEye = this.clazz < 2 ? CRes.random(30, 80) : CRes.random(10, 60);
                    }
                } else if (this.timeEye >= this.endEye) {
                    this.timeEye = 0;
                    this.eye = 0;
                }
            }
        }
    }

    public void move_to_XY() {
        if (!this.Canmove() || this.isBinded) {
            this.toX = this.x;
            this.toY = this.y;
            return;
        }
        if (this.isMoveOut) {
            return;
        }
        if (CRes.abs(this.x - this.toX) > this.vMax + this.getVmount()) {
            this.vy = 0;
            this.Action = 1;
            if (CRes.abs(this.x - this.toX) > this.vMax + this.getVmount()) {
                if (this.x > this.toX) {
                    this.vx = -(this.vMax + this.getVmount());
                    this.PrevDir = this.Direction;
                    this.Direction = 2;
                } else {
                    this.vx = this.vMax + this.getVmount();
                    this.PrevDir = this.Direction;
                    this.Direction = 3;
                }
            } else {
                this.vx = this.toX - this.x;
            }
        } else if (CRes.abs(this.y - this.toY) > this.vMax + this.getVmount()) {
            this.vx = 0;
            this.Action = 1;
            if (CRes.abs(this.y - this.toY) > this.vMax + this.getVmount()) {
                if (this.y > this.toY) {
                    this.vy = -(this.vMax + this.getVmount());
                    this.PrevDir = this.Direction;
                    this.Direction = 1;
                } else {
                    this.vy = this.vMax + this.getVmount();
                    this.PrevDir = this.Direction;
                    this.Direction = 0;
                }
            } else {
                this.vy = this.toY - this.y;
            }
        } else {
            if (this.isDetonateInDest) {
                GameScreen.addEffectEndKill(43, this.x, this.y);
                LoadMap.timeVibrateScreen = 10;
                this.isStop = true;
            }
            this.vx = 0;
            this.vy = 0;
        }
    }

    public static MainObject get_Object(int n, byte by) {
        for (int i = GameScreen.Vecplayers.size() - 1; i >= 0; --i) {
            MainObject mainObject = (MainObject)GameScreen.Vecplayers.elementAt(i);
            if (mainObject == null) continue;
            if (by == 2 && mainObject.getIDnpc() == n) {
                return mainObject;
            }
            if (mainObject.typeObject != by || mainObject.ID != n) continue;
            return mainObject;
        }
        return null;
    }

    public static MainObject get_Item_Object(int n, int n2) {
        for (int i = 0; i < GameScreen.Vecplayers.size(); ++i) {
            MainObject mainObject = (MainObject)GameScreen.Vecplayers.elementAt(i);
            if (mainObject.typeObject != n2 || mainObject.ID != n) continue;
            return mainObject;
        }
        return null;
    }

    public static int getDistance(int n, int n2, int n3, int n4) {
        return MainObject.getDistance(n - n3, n2 - n4);
    }

    public static int getDistance(int n, int n2) {
        return CRes.sqrt(n * n + n2 * n2);
    }

    public boolean isMountFly() {
        if (this.typeMount == -1 || MountTemplate.Arr_Fly == null) {
            return false;
        }
        return MountTemplate.Arr_Fly[this.typeMount] == 1;
    }

    public void setMove(boolean bl) {
        int n;
        if (this.isMoveOut) {
            return;
        }
        int n2 = GameCanvas.loadmap.getTile(this.x + this.vx, this.y + this.vy);
        if (n2 == 2 && !this.isMountFly()) {
            if (this.vy != 0) {
                this.vy = this.vy > 0 ? this.vMax + this.getVmount() - 1 : -(this.vMax + this.getVmount()) + 1;
            }
            if (this.vx != 0) {
                this.vx = this.vx > 0 ? this.vMax + this.getVmount() - 1 : -(this.vMax + this.getVmount()) + 1;
            }
            this.isWater = true;
            this.dyWater = 3;
        } else {
            this.isWater = false;
            this.dyWater = 0;
            if (n2 == 1) {
                if (bl) {
                    this.setAutoMoveNear();
                } else {
                    this.Action = 0;
                    this.frameLeg = CRes.random(4);
                    this.vx = 0;
                    this.vy = 0;
                }
            }
            if (GameCanvas.loadmap.getTile(this.x, this.y) == 2 && !this.isMountFly()) {
                this.isWater = true;
                this.dyWater = 3;
            }
        }
        if (this.vx == 0 && this.vy == 0 && ((n = GameCanvas.loadmap.getTile(this.x, this.y)) == 1 || n == -1)) {
            int n3 = 24;
            int n4 = this.x * 1000;
            int n5 = this.y * 1000;
            int n6 = 0;
            boolean bl2 = false;
            do {
                int n7;
                bl2 = false;
                int n8 = n4 + CRes.cos(n6) * n3;
                int n9 = n5 + CRes.sin(n6) * n3;
                if (n8 >= 0 && n9 >= 0 && ((n7 = GameCanvas.loadmap.getTile(n8 / 1000, n9 / 1000)) == 0 || n7 == 2)) {
                    this.x = n8 / 1000;
                    this.y = n9 / 1000;
                    this.resetAction();
                    bl2 = true;
                }
                if ((n6 += 44) < 360) continue;
                n6 = 0;
                n3 += 24;
            } while (!bl2);
        }
    }

    public void setAutoMoveNear() {
        int n = GameCanvas.loadmap.getIndex(this.x + this.vx, this.y + this.vy);
        if (this.vy != 0) {
            if (!(n % GameCanvas.loadmap.mapW <= 0 || GameCanvas.loadmap.getTile(this.x + this.vx - LoadMap.wTile, this.y + this.vy) != 0 && GameCanvas.loadmap.getTile(this.x + this.vx - LoadMap.wTile, this.y + this.vy) != 2 || GameCanvas.loadmap.getTile(this.x + this.vx - LoadMap.wTile, this.y) != 0 && GameCanvas.loadmap.getTile(this.x + this.vx - LoadMap.wTile, this.y) != 2)) {
                this.vx = -(this.vMax + this.getVmount());
                this.Direction = 2;
                this.vy = 0;
            } else if (!(n % GameCanvas.loadmap.mapW >= GameCanvas.loadmap.mapW - 1 || GameCanvas.loadmap.getTile(this.x + this.vx + LoadMap.wTile, this.y + this.vy) != 0 && GameCanvas.loadmap.getTile(this.x + this.vx + LoadMap.wTile, this.y + this.vy) != 2 || GameCanvas.loadmap.getTile(this.x + this.vx + LoadMap.wTile, this.y) != 0 && GameCanvas.loadmap.getTile(this.x + this.vx + LoadMap.wTile, this.y) != 2)) {
                this.vx = this.vMax + this.getVmount();
                this.Direction = 3;
                this.vy = 0;
            } else {
                this.vy = 0;
            }
        } else if (this.vx != 0) {
            if (!(GameCanvas.loadmap.getTile(this.x + this.vx, this.y + this.vy - LoadMap.wTile) != 0 && GameCanvas.loadmap.getTile(this.x + this.vx, this.y + this.vy - LoadMap.wTile) != 2 || GameCanvas.loadmap.getTile(this.x, this.y + this.vy - LoadMap.wTile) != 0 && GameCanvas.loadmap.getTile(this.x, this.y + this.vy - LoadMap.wTile) != 2)) {
                this.vy = -(this.vMax + this.getVmount());
                this.Direction = 1;
                this.vx = 0;
            } else if (!(GameCanvas.loadmap.getTile(this.x + this.vx, this.y + this.vy + LoadMap.wTile) != 0 && GameCanvas.loadmap.getTile(this.x + this.vx, this.y + this.vy + LoadMap.wTile) != 2 || GameCanvas.loadmap.getTile(this.x, this.y + this.vy + LoadMap.wTile) != 0 && GameCanvas.loadmap.getTile(this.x, this.y + this.vy + LoadMap.wTile) != 2)) {
                this.vy = this.vMax + this.getVmount();
                this.Direction = 0;
                this.vx = 0;
            } else {
                this.vx = 0;
            }
        }
        if (this.vx == 0 && this.vy == 0) {
            this.Action = 0;
            this.frameLeg = CRes.random(4);
        }
    }

    public void setSpeed(int n) {
        switch (this.Direction) {
            case 1: {
                this.vy = -n;
                this.vx = 0;
                break;
            }
            case 0: {
                this.vy = n;
                this.vx = 0;
                break;
            }
            case 2: {
                this.vy = 0;
                this.vx = -n;
                break;
            }
            case 3: {
                this.vy = 0;
                this.vx = n;
            }
        }
    }

    public void setSpeedInDirection(int n) {
        int n2 = CRes.random_Am_0(3);
        if (CRes.abs(n2) > 1) {
            --n;
        }
        switch (this.Direction) {
            case 1: {
                this.vy = -n;
                this.vx = n2;
                break;
            }
            case 0: {
                this.vy = n;
                this.vx = n2;
                break;
            }
            case 2: {
                this.vy = n2;
                this.vx = -n;
                break;
            }
            case 3: {
                this.vy = n2;
                this.vx = n;
            }
        }
        if (this.vx == 0 && CRes.random(3) == 0) {
            this.time = 0;
            this.Action = 0;
            this.vx = 0;
            this.vy = 0;
        }
        this.Direction = this.vx > 0 ? 3 : 2;
    }

    public boolean setIsInScreen(int n, int n2, int n3, int n4) {
        return n >= MainScreen.cameraMain.xCam - n3 && n <= MainScreen.cameraMain.xCam + GameCanvas.w + n3 && n2 >= MainScreen.cameraMain.yCam - n4 / 2 && n2 <= MainScreen.cameraMain.yCam + GameCanvas.h + n4 * 3 / 2;
    }

    public static boolean isInScreen(MainObject mainObject) {
        return mainObject.x >= MainScreen.cameraMain.xCam - mainObject.wOne && mainObject.x <= MainScreen.cameraMain.xCam + GameCanvas.w + mainObject.wOne && mainObject.y >= MainScreen.cameraMain.yCam - mainObject.hOne && mainObject.y <= MainScreen.cameraMain.yCam + GameCanvas.h + mainObject.hOne * 3 / 2;
    }

    public void Move_to_Focus_Person() {
        int n;
        if (this.x == this.toX && this.y == this.toY) {
            return;
        }
        if (this.isMoveOut) {
            return;
        }
        if (this.iscuop && ((n = GameCanvas.loadmap.getTile(this.x + this.vx, this.y + this.vy)) == 1 || n == -1)) {
            this.isWater = false;
            this.dyWater = 0;
            this.vx = 0;
            this.vy = 0;
            this.x = this.toX;
            this.y = this.toY;
            ++this.timeFreeMove;
            if (this.timeFreeMove >= 10) {
                this.timeFreeMove = 25;
            }
            GameScreen.addEffectEndKill(35, this.x, this.y - 20);
        }
        if (CRes.abs(this.x - this.toX) > this.vMax + this.getVmount() || CRes.abs(this.y - this.toY) > this.vMax + this.getVmount()) {
            this.move_to_XY_Normal();
        } else if (this.Action != 2 && this.Action != 3 && this.Action != 4) {
            this.xFire = this.x;
            this.yFire = this.y;
            this.toX = this.x;
            this.toY = this.y;
            this.vx = 0;
            this.vy = 0;
            this.Action = 0;
            this.frameLeg = CRes.random(4);
        }
    }

    public void moveX() {
        if (this.isMoveOut) {
            return;
        }
        this.vy = 0;
        this.Action = 1;
        if (CRes.abs(this.x - this.toX) >= this.vMax + this.getVmount()) {
            if (this.x >= this.toX) {
                this.vx = -(this.vMax + this.getVmount());
                this.Direction = 2;
            } else {
                this.vx = this.vMax + this.getVmount();
                this.Direction = 3;
            }
        } else {
            this.vx = this.toX - this.x;
        }
    }

    public void moveY() {
        if (this.isMoveOut) {
            return;
        }
        this.vx = 0;
        this.Action = 1;
        if (CRes.abs(this.y - this.toY) >= this.vMax + this.getVmount()) {
            if (this.y > this.toY) {
                this.vy = -(this.vMax + this.getVmount());
                this.Direction = 1;
            } else {
                this.vy = this.vMax + this.getVmount();
                this.Direction = 0;
            }
        } else {
            this.vy = this.toY - this.y;
        }
    }

    public void move_to_XY_Normal() {
        if (this.isMoveOut) {
            return;
        }
        if (this.isDirMove) {
            if (CRes.abs(this.x - this.toX) >= this.vMax + this.getVmount()) {
                this.moveX();
            } else if (CRes.abs(this.y - this.toY) >= this.vMax + this.getVmount()) {
                this.moveY();
            } else {
                this.vx = 0;
                this.vy = 0;
            }
        } else if (CRes.abs(this.y - this.toY) >= this.vMax + this.getVmount()) {
            this.moveY();
        } else if (CRes.abs(this.x - this.toX) >= this.vMax + this.getVmount()) {
            this.moveX();
        } else {
            this.vx = 0;
            this.vy = 0;
        }
    }

    public void setMove(int n, int n2) {
        if (this.isMoveOut || this.isBinded || this.isDongBang) {
            return;
        }
        if ((n2 == 1 || n2 == -1) && this.timeFreeMove < 12) {
            this.isWater = false;
            this.dyWater = 0;
            this.vx = 0;
            this.vy = 0;
            this.isDirMove = !this.isDirMove;
            ++this.timeFreeMove;
            if (this.timeFreeMove >= 10) {
                this.timeFreeMove = 25;
            }
        } else {
            this.isWater = false;
            this.dyWater = 0;
            if (this.timeFreeMove > 0) {
                --this.timeFreeMove;
            }
            if (n2 == 2 && !this.isMountFly()) {
                this.isWater = true;
                this.dyWater = 3;
                if (this.vy != 0) {
                    this.vy = this.vy > 0 ? this.vMax + this.getVmount() - n : -(this.vMax + this.getVmount()) + n;
                }
                if (this.vx != 0) {
                    this.vx = this.vx > 0 ? this.vMax + this.getVmount() - n : -(this.vMax + this.getVmount()) + n;
                }
            }
        }
    }

    public void paintWeaponhideplayer(mGraphics mGraphics2, int n) {
        int n2 = 0;
        if (n2 < 0 || n2 >= 5) {
            return;
        }
        byte by = this.clazz;
        int n3 = 0;
        int n4 = 0;
        if (this.Action != 2) {
            n4 = 0;
        }
        if (this.weapon_frame >= 0 && n2 >= 0) {
            int n5;
            int n6;
            WPSplashInfo wPSplashInfo = CRes.wpSplashInfos[by][n2][n4];
            if (wPSplashInfo == null) {
                wPSplashInfo = CRes.GetWPSplashInfo(by, n2, n4);
                return;
            }
            int n7 = 0;
            if (this.Action != 2 || this.weapon_frame < 4) {
                if (this.weapon_frame > 1) {
                    this.weapon_frame = 0;
                }
                if (this.weapon_frame < 0) {
                    this.weapon_frame = 0;
                }
                if (n7 != -1 && CRes.loadImgWeaPone(by, n7, n3) != null) {
                    WeaponInfo weaponInfo = CRes.loadImgWeaPone(by, n7, n3);
                    n6 = this.frame;
                    if (n6 > 2) {
                        n6 = 2;
                    }
                    if (weaponInfo.img != null) {
                        mGraphics2.drawRegion(weaponInfo.img, weaponInfo.mRegion[this.Direction][0], 0, weaponInfo.mRegion[this.Direction][1], weaponInfo.himg, 0, this.x + weaponInfo.mPos[this.Direction][n6][0], this.y - this.ysai + weaponInfo.mPos[this.Direction][n6][1] - this.dy + this.dyWater - this.dyMount - this.yjum, 0, true);
                        this.paintEffectWeapon(mGraphics2);
                    }
                }
            } else if (this.weapon_frame < wPSplashInfo.P0_X[this.Direction].length && wPSplashInfo.image != null) {
                mGraphics2.drawRegion(wPSplashInfo.image, wPSplashInfo.P0_X[this.Direction][this.weapon_frame], wPSplashInfo.P0_Y[this.Direction][this.weapon_frame], wPSplashInfo.PF_W[this.Direction][this.weapon_frame], wPSplashInfo.PF_H[this.Direction][this.weapon_frame], 0, this.x + wPSplashInfo.PF_X[this.Direction][this.weapon_frame], n + this.y - this.ysai + wPSplashInfo.PF_Y[this.Direction][this.weapon_frame] - this.dy + this.dyWater - this.dyMount - this.yjum, 0, true);
            }
            if ((this.Action == 0 || this.Action == 1) && (n5 = 0) > 1) {
                n6 = n5 - 1;
                if (n6 > 3) {
                    n6 = 3;
                }
                this.paintEffectVukhi(mGraphics2, n6, wPSplashInfo, by);
            }
        }
    }

    public void paintWeapon(mGraphics mGraphics2, int n) {
        int n2 = 0;
        if (n2 < 0 || n2 >= 5) {
            return;
        }
        byte by = this.clazz;
        int n3 = 0;
        int n4 = 0;
        if (this.Action != 2) {
            n4 = 0;
        }
        if (this.weapon_frame >= 0 && n2 >= 0) {
            int n5;
            int n6;
            WPSplashInfo wPSplashInfo = CRes.wpSplashInfos[by][n2][n4];
            if (wPSplashInfo == null) {
                wPSplashInfo = CRes.GetWPSplashInfo(by, n2, n4);
                return;
            }
            int n7 = this.weaponType;
            if (this.Action != 2 || this.weapon_frame < 4) {
                if (this.weapon_frame > 1) {
                    this.weapon_frame = 0;
                }
                if (this.weapon_frame < 0) {
                    this.weapon_frame = 0;
                }
                if (n7 != -1 && CRes.loadImgWeaPone(by, n7, n3) != null) {
                    WeaponInfo weaponInfo = CRes.loadImgWeaPone(by, n7, n3);
                    n6 = this.frame;
                    if (n6 > 2) {
                        n6 = 2;
                    }
                    if (weaponInfo.img != null) {
                        mGraphics2.drawRegion(weaponInfo.img, weaponInfo.mRegion[this.Direction][0], 0, weaponInfo.mRegion[this.Direction][1], weaponInfo.himg, 0, this.x + weaponInfo.mPos[this.Direction][n6][0], this.y - this.ysai + weaponInfo.mPos[this.Direction][n6][1] - this.dy + this.dyWater - this.dyMount - this.yjum, 0, true);
                        this.paintEffectWeapon(mGraphics2);
                    }
                }
            } else if (this.weapon_frame < wPSplashInfo.P0_X[this.Direction].length && wPSplashInfo.image != null) {
                mGraphics2.drawRegion(wPSplashInfo.image, wPSplashInfo.P0_X[this.Direction][this.weapon_frame], wPSplashInfo.P0_Y[this.Direction][this.weapon_frame], wPSplashInfo.PF_W[this.Direction][this.weapon_frame], wPSplashInfo.PF_H[this.Direction][this.weapon_frame], 0, this.x + wPSplashInfo.PF_X[this.Direction][this.weapon_frame], n + this.y - this.ysai + wPSplashInfo.PF_Y[this.Direction][this.weapon_frame] - this.dy + this.dyWater - this.dyMount - this.yjum, 0, true);
            }
            if ((this.Action == 0 || this.Action == 1) && (n5 = 0) > 1) {
                n6 = n5 - 1;
                if (n6 > 3) {
                    n6 = 3;
                }
                this.paintEffectVukhi(mGraphics2, n6, wPSplashInfo, by);
            }
        }
    }

    private void paintEffectVukhi(mGraphics mGraphics2, int n, WPSplashInfo wPSplashInfo, int n2) {
    }

    public void resetXY() {
        this.toX = this.x;
        this.toY = this.y;
        this.vx = 0;
        this.vy = 0;
    }

    public void beginFire() {
        this.f = 0;
        this.Action = 2;
        this.fplash = 0;
        this.vx = 0;
        this.vy = 0;
    }

    public static void startDeadFly(MainMonster mainMonster, int n, int n2) {
        MainObject mainObject = MainObject.get_Object(n, (byte)0);
        if (mainObject == null) {
            return;
        }
        int n3 = 5;
        int n4 = 0;
        if (mainMonster != null) {
            int n5 = 0;
            int n6 = 0;
            if (mainMonster.typeMonster != 7 && mainObject != null) {
                n5 = (mainMonster.x - mainObject.x) * 2;
                n6 = (mainMonster.y - mainObject.y) * 2;
                while (MainObject.getDistance(n5, n6) > 20) {
                    n5 = n5 * 2 / 3;
                    n6 = n6 * 2 / 3;
                }
                if (n2 == 1) {
                    n5 *= 2;
                    n6 *= 2;
                } else if (n2 == 2) {
                    while (MainObject.getDistance(n5, n6) > 16) {
                        n5 = n5 * 2 / 3;
                        n6 = n6 * 2 / 3;
                    }
                    n3 = 20;
                    n4 = 18;
                }
            }
            mainMonster.startDeadFly(n5, n6, n3, n4);
        }
    }

    public void GiaoTiep() {
    }

    public void addChat(String string, boolean bl) {
        if (this.chat == null) {
            this.chat = new PopupChat();
        }
        this.chat.setChat(string, bl);
        this.chat.updatePos(this.x, this.y - this.hOne - 30);
    }

    public void resetAction() {
        this.vy = 0;
        this.vx = 0;
        this.toX = this.x;
        this.toY = this.y;
        if (this.Action != 2 && this.Action != 4) {
            this.Action = 0;
            this.weapon_frame = 0;
        }
    }

    public void resetVx_vy() {
        this.vy = 0;
        this.vx = 0;
        if (this.Action == 1) {
            this.Action = 0;
        }
    }

    public static void resetDirection(MainObject mainObject, MainObject mainObject2) {
        int n = mainObject.x - mainObject2.x;
        int n2 = mainObject.y - mainObject2.y;
        int n3 = CRes.abs(n) > CRes.abs(n2) ? (n > 0 ? 2 : 3) : (n2 > 0 ? 1 : 0);
        mainObject.Direction = n3;
    }

    public void addnewBuff(int n, int n2) {
        for (int i = 0; i < this.vecBuff.size(); ++i) {
            MainBuff mainBuff = (MainBuff)this.vecBuff.elementAt(i);
            if (mainBuff == null || mainBuff.typeBuff != n) continue;
            mainBuff.settimebuff(n2);
            return;
        }
        MainBuff mainBuff = new MainBuff(n, n2);
        this.vecBuff.addElement(mainBuff);
    }

    public void addBuff(int n, int n2, int n3) {
        MainBuff mainBuff = MainBuff.getBuff(n, n3);
        if (mainBuff != null) {
            mainBuff.timeBegin = GameCanvas.timeNow;
            mainBuff.timeOff = n2;
        } else {
            mainBuff = new MainBuff(n, n2, n3);
            this.vecBuff.addElement(mainBuff);
        }
    }

    public void setEye(int n) {
        this.eye = n;
        this.timeEye = 0;
    }

    public int skillMonster() {
        return 0;
    }

    public void startDie() {
    }

    public mVector vectorObjectNear() {
        mVector mVector2 = new mVector("MainObject vec");
        int n = 30;
        for (int i = 0; i < GameScreen.Vecplayers.size(); ++i) {
            int n2;
            MainObject mainObject = (MainObject)GameScreen.Vecplayers.elementAt(i);
            if (mainObject == null || mainObject == GameScreen.player || mainObject == this || mainObject.typeObject != 0 || (n2 = MainObject.getDistance(this.x, this.y, mainObject.x, mainObject.y - mainObject.hOne / 2)) > n) continue;
            mVector2.addElement(mainObject);
        }
        return mVector2;
    }

    public void setVx_Vy_Item(int n, int n2) {
        int n3;
        this.toX = n;
        this.toY = n2;
        int n4 = this.toX - this.x;
        int n5 = this.toY - this.y;
        if (n5 == 0) {
            n5 = 1;
        }
        if (n4 == 0) {
            n4 = 1;
        }
        if ((n3 = MainObject.getDistance(n4, n5) / (this.vMax + this.getVmount())) == 0) {
            n3 = 1;
        }
        this.vx = n4 / n3;
        this.vy = n5 / n3;
        if (CRes.abs(this.vx) > CRes.abs(n4)) {
            this.vx = n4;
        }
        if (CRes.abs(this.vy) > CRes.abs(n5)) {
            this.vy = n5;
        }
        this.timeHuyKill = n3;
    }

    public boolean isThacNuoc() {
        return false;
    }

    public boolean findOwner(MainObject mainObject) {
        return false;
    }

    public boolean isMonsterHouse() {
        return false;
    }

    public boolean isItemBox() {
        return false;
    }

    public boolean isNPC() {
        return this.isBot >= -100 && this.isBot < -2;
    }

    public int getIDnpc() {
        return this.ID;
    }

    public boolean canFocus() {
        return true;
    }

    public boolean isNPC_server() {
        return false;
    }

    public void paint_no(mGraphics mGraphics2) {
        if (this.isPaint_No) {
            ++this.frNo;
            if (this.frNo > this.frameNo.length - 1) {
                this.frNo = 0;
            }
            AvMain.fraPlayerNo.drawFrame(this.frameNo[this.frNo], this.x, this.y + 5, 0, 33, mGraphics2);
        }
    }

    public void move_to_point() {
        if (this.isMoveOut) {
            if (this.ID == GameScreen.player.ID) {
                GameCanvas.clearAll();
                this.posTransRoad = null;
            }
            this.vx = 0;
            this.vy = 0;
            boolean bl = false;
            int n = this.yMoveOut - this.y >> 1;
            this.y += n;
            if (n <= 0) {
                this.y = this.yMoveOut;
                bl = true;
            }
            if (bl) {
                this.vx = 0;
                this.vy = 0;
                this.Action = 0;
                this.toX = this.x;
                this.toY = this.y;
                n = 0;
                this.yMoveOut = this.y;
                this.yMoveOut = this.y;
                this.xStand = this.x;
                this.yStand = this.y;
                this.isMoveOut = false;
                return;
            }
            if (GameCanvas.loadmap.getTile(this.x, this.y) == 2 && !this.isMountFly()) {
                this.isWater = true;
                this.dyWater = 3;
            }
            if (GameCanvas.gameTick % 2 == 0) {
                GameScreen.addEffectEndKill(9, this.x, this.y);
                GameScreen.addEffectEndKill(46, this.x, this.y - 2);
            }
        }
    }

    public static void createImageMount() {
    }

    public int getVmount() {
        if (this.typeMount == -1) {
            return 0;
        }
        return MountTemplate.speed[this.typeMount];
    }

    public boolean isFramePaintMount_Truoc() {
        if (this.typeMount == -1) {
            return false;
        }
        byte[] byArray = MountTemplate.FRAME_VE_TRUOC[this.typeMount];
        for (int i = 0; i < byArray.length; ++i) {
            if (this.frameMount != byArray[i] || this.frameMount == -1) continue;
            return true;
        }
        return false;
    }

    public void updateMount() {
        if (this.typeMount == -1) {
            return;
        }
        DataSkillEff dataSkillEff = MainObject.getEffMatNa(this.idHorse);
        if (GameCanvas.gameTick % 5 == 0 && dataSkillEff != null) {
            int n = dataSkillEff.listFrame.size() / 21;
            if (n == 0) {
                n = 1;
            }
            this.frameThuCuoi = (byte)((this.frameThuCuoi + 1) % n);
        }
        if (this.typeMount != -1) {
            if (dataSkillEff == null) {
                this.A_Move = ArrMount;
            }
            if (this.Action == 0) {
                if (this.Direction == 2) {
                    this.frameMount = this.frame == 1 ? (byte)1 : 0;
                    this.xMount = MountTemplate.dx[this.typeMount][0];
                    this.yMount = MountTemplate.dy[this.typeMount][0];
                    this.dyMount = MountTemplate.DY_CHAR_STAND[this.typeMount][0];
                } else if (this.Direction == 3) {
                    this.frameMount = this.frame == 1 ? (byte)1 : 0;
                    this.xMount = MountTemplate.dx[this.typeMount][2];
                    this.yMount = MountTemplate.dy[this.typeMount][2];
                    this.dyMount = MountTemplate.DY_CHAR_STAND[this.typeMount][1];
                } else if (this.Direction == 1) {
                    this.frameMount = this.frame == 1 ? (byte)11 : (byte)10;
                    this.xMount = MountTemplate.dx[this.typeMount][4];
                    this.yMount = MountTemplate.dy[this.typeMount][4];
                    this.dyMount = MountTemplate.DY_CHAR_STAND[this.typeMount][2];
                } else {
                    this.frameMount = this.frame == 1 ? (byte)6 : (byte)5;
                    this.xMount = MountTemplate.dx[this.typeMount][6];
                    this.yMount = MountTemplate.dy[this.typeMount][6];
                    this.dyMount = MountTemplate.DY_CHAR_STAND[this.typeMount][3];
                }
            } else if (this.Action == 1) {
                this.fMount = (byte)(this.fMount + 1);
                if (this.Direction == 2) {
                    if (this.fMount > MountTemplate.FRAME_MOVE_LR[this.typeMount].length - 1) {
                        this.fMount = 0;
                    }
                    this.frameMount = MountTemplate.FRAME_MOVE_LR[this.typeMount][this.fMount];
                    this.xMount = MountTemplate.dx[this.typeMount][1];
                    this.yMount = MountTemplate.dy[this.typeMount][1];
                    this.dyMount = this.frameMount == 3 ? MountTemplate.DY_CHAR_MOVE[this.typeMount][0] : (this.frameMount == 2 ? MountTemplate.DY_CHAR_MOVE[this.typeMount][1] : MountTemplate.DY_CHAR_MOVE[this.typeMount][2]);
                    if (this.fMount == 4 && !this.isMountFly()) {
                        GameScreen.addEffectEndKill_Direction(55, this.x + 19, this.y - 5, this.Direction, (byte)-1);
                    }
                } else if (this.Direction == 3) {
                    if (this.fMount > MountTemplate.FRAME_MOVE_LR[this.typeMount].length - 1) {
                        this.fMount = 0;
                    }
                    this.frameMount = MountTemplate.FRAME_MOVE_LR[this.typeMount][this.fMount];
                    this.xMount = MountTemplate.dx[this.typeMount][3];
                    this.yMount = MountTemplate.dy[this.typeMount][3];
                    this.dyMount = this.frameMount == 3 ? MountTemplate.DY_CHAR_MOVE[this.typeMount][3] : (this.frameMount == 2 ? MountTemplate.DY_CHAR_MOVE[this.typeMount][4] : MountTemplate.DY_CHAR_MOVE[this.typeMount][5]);
                    if (this.fMount == 4 && !this.isMountFly()) {
                        GameScreen.addEffectEndKill_Direction(55, this.x - 11, this.y - 5, this.Direction, (byte)-1);
                    }
                } else if (this.Direction == 1) {
                    if (this.fMount > MountTemplate.FRAME_MOVE_UP[this.typeMount].length - 1) {
                        this.fMount = 0;
                    }
                    this.frameMount = MountTemplate.FRAME_MOVE_UP[this.typeMount][this.fMount];
                    this.xMount = MountTemplate.dx[this.typeMount][5];
                    this.yMount = MountTemplate.dy[this.typeMount][5];
                    this.dyMount = this.frameMount == 13 ? MountTemplate.DY_CHAR_MOVE[this.typeMount][6] : (this.frameMount == 12 ? MountTemplate.DY_CHAR_MOVE[this.typeMount][7] : MountTemplate.DY_CHAR_MOVE[this.typeMount][8]);
                    if (this.fMount == 4 && !this.isMountFly()) {
                        GameScreen.addEffectEndKill_Direction(55, this.x - 14, this.y - 7, 3, (byte)-1);
                        GameScreen.addEffectEndKill_Direction(55, this.x + 16, this.y - 7, 2, (byte)-1);
                    }
                } else {
                    if (this.fMount > MountTemplate.FRAME_MOVE_DOWN[this.typeMount].length - 1) {
                        this.fMount = 0;
                    }
                    this.frameMount = MountTemplate.FRAME_MOVE_DOWN[this.typeMount][this.fMount];
                    this.xMount = MountTemplate.dx[this.typeMount][7];
                    this.yMount = MountTemplate.dy[this.typeMount][7];
                    this.dyMount = this.frameMount == 8 ? MountTemplate.DY_CHAR_MOVE[this.typeMount][9] : (this.frameMount == 7 ? MountTemplate.DY_CHAR_MOVE[this.typeMount][10] : MountTemplate.DY_CHAR_MOVE[this.typeMount][11]);
                    if (this.fMount == 4 && !this.isMountFly()) {
                        GameScreen.addEffectEndKill_Direction(55, this.x - 15, this.y - 10, 3, (byte)-1);
                        GameScreen.addEffectEndKill_Direction(55, this.x + 17, this.y - 10, 2, (byte)-1);
                    }
                }
            }
        } else {
            this.resetMount();
        }
    }

    public void resetMount() {
        this.A_Move = ArrMount1;
        this.dyMount = 0;
    }

    public void paintMount_Truoc(mGraphics mGraphics2) {
        if (this.idHorse != -1) {
            return;
        }
        if (this.typeMount == -1) {
            return;
        }
        if (this.isDongBang) {
            return;
        }
        FrameImage frameImage = FrameImage.getFrameImageMount(this.typeMount, 3, 5, 0);
        if (frameImage != null && this.isFramePaintMount_Truoc()) {
            frameImage.drawFrameNew(this.frameMount, this.x + this.xMount, this.y - this.ysai - this.dy + this.dyWater - this.yjum + this.yMount, this.Direction > 2 ? 2 : 0, 33, mGraphics2);
        }
    }

    public void paintMount_Sau(mGraphics mGraphics2) {
        if (this.idHorse != -1) {
            return;
        }
        if (this.typeMount == -1) {
            return;
        }
        if (this.isDongBang) {
            return;
        }
        FrameImage frameImage = FrameImage.getFrameImageMount(this.typeMount, 3, 5, 0);
        if (frameImage != null && !this.isFramePaintMount_Truoc()) {
            frameImage.drawFrameNew(this.frameMount, this.x + this.xMount, this.y - this.ysai - this.dy + this.dyWater - this.yjum + this.yMount, this.Direction > 2 ? 2 : 0, 33, mGraphics2);
        }
    }

    public void setArrayGemKham(short[][] sArray) {
        this.arrayGemKham = sArray;
        int n = this.clazz;
        if (n == 3) {
            n = 2;
        } else if (n == 2) {
            n = 3;
        }
        this.slotGem = new byte[this.arrayGemKham[8 + n].length];
        for (int i = 0; i < this.arrayGemKham.length; ++i) {
            for (int j = 0; j < this.arrayGemKham[i].length; ++j) {
                this.slotGem[j] = this.arrayGemKham[8 + n][j] >= 23 && this.arrayGemKham[8 + n][j] <= 27 ? 0 : (this.arrayGemKham[8 + n][j] >= 28 && this.arrayGemKham[8 + n][j] <= 32 ? 1 : -1);
            }
        }
    }

    private void paintEffectWeapon(mGraphics mGraphics2) {
        if (this.weaponEff != null) {
            this.weaponEff.drawFrameEffectSkill(this.fweapon, this.x + this.dxEff, this.y - this.ysai + this.dyEff - this.dy + this.dyWater - this.dyMount - this.yjum, this.transEff[this.Direction], 33, mGraphics2);
        }
    }

    private void updateEffectWeapon() {
        if (GameCanvas.gameTick % 2 == 0) {
            this.fweapon = (byte)(this.fweapon + 1);
        }
        if (this.fweapon > 4) {
            this.fweapon = 0;
        }
        ++this.timeEff;
        if (this.timeEff < this.timeShow) {
            if (this.slotGem[0] == -1) {
                if (this.weaponEff != null) {
                    this.weaponEff = null;
                }
                if (this.fweapon != 0) {
                    this.fweapon = 0;
                }
            } else {
                this.weaponEff = weaponEff_Gem[this.slotGem[0]];
            }
        } else if (this.timeEff >= this.timeShow * 2 && this.timeEff < this.timeShow * 3) {
            if (this.slotGem[1] == -1) {
                if (this.weaponEff != null) {
                    this.weaponEff = null;
                }
                if (this.fweapon != 0) {
                    this.fweapon = 0;
                }
            } else {
                this.weaponEff = weaponEff_Gem[this.slotGem[1]];
            }
        } else if (this.timeEff >= this.timeShow * 4 && this.timeEff < this.timeShow * 5) {
            if (this.slotGem[2] == -1) {
                if (this.weaponEff != null) {
                    this.weaponEff = null;
                }
                if (this.fweapon != 0) {
                    this.fweapon = 0;
                }
            } else {
                this.weaponEff = weaponEff_Gem[this.slotGem[2]];
            }
        } else if (this.timeEff >= this.timeShow * 6) {
            this.timeEff = 0;
            if (this.weaponEff != null) {
                this.weaponEff = null;
            }
            if (this.fweapon != 0) {
                this.fweapon = 0;
            }
        } else {
            if (this.weaponEff != null) {
                this.weaponEff = null;
            }
            if (this.fweapon != 0) {
                this.fweapon = 0;
            }
        }
        if (this.slotGem[0] != -1 && this.weaponEff == weaponEff_Gem[this.slotGem[0]]) {
            this.dxEff = this.eff_dx[this.slotGem[0]][this.Direction];
            this.dyEff = this.frame == 1 ? this.eff_dy[this.slotGem[0]][this.Direction] + 1 : this.eff_dy[this.slotGem[0]][this.Direction];
        }
        if (this.slotGem[1] != -1 && this.weaponEff == weaponEff_Gem[this.slotGem[1]]) {
            this.dxEff = this.eff_dx[this.slotGem[1]][this.Direction];
            this.dyEff = this.frame == 1 ? this.eff_dy[this.slotGem[1]][this.Direction] + 1 : this.eff_dy[this.slotGem[1]][this.Direction];
        }
        if (this.slotGem[2] != -1 && this.weaponEff == weaponEff_Gem[this.slotGem[2]]) {
            this.dxEff = this.eff_dx[this.slotGem[2]][this.Direction];
            this.dyEff = this.frame == 1 ? this.eff_dy[this.slotGem[2]][this.Direction] + 1 : this.eff_dy[this.slotGem[2]][this.Direction];
        }
    }

    public void addEffectCharWearing(int n, int n2) {
    }

    public void setNameStore(String string) {
    }

    public void removeNameStore() {
    }

    public void updateoverHP_MP() {
        if ((countmp = (byte)(countmp + 1)) > 10) {
            countmp = 0;
        }
        this.overMP = this.mp < this.maxMp / 10;
        this.overHP = this.hp < this.maxHp / 10 && this.hp > 0;
    }

    public boolean isSelling() {
        return false;
    }

    public void paintEffectCharWearing(mGraphics mGraphics2) {
        if (this.vecEffectCharWearing.size() > 0) {
            for (int i = 0; i < this.vecEffectCharWearing.size(); ++i) {
                EffectCharWearing effectCharWearing = (EffectCharWearing)this.vecEffectCharWearing.elementAt(i);
                if (effectCharWearing == null) continue;
                effectCharWearing.paint(mGraphics2, this.x, this.y);
            }
        }
    }

    public void updateEffectCharWearing() {
        if (this.vecEffectCharWearing.size() > 0) {
            for (int i = 0; i < this.vecEffectCharWearing.size(); ++i) {
                EffectCharWearing effectCharWearing = (EffectCharWearing)this.vecEffectCharWearing.elementAt(i);
                if (effectCharWearing == null) continue;
                effectCharWearing.update();
            }
        }
    }

    public void removeEffectCharWearing(int n) {
        int n2 = this.vecEffectCharWearing.size();
        if (n2 > 0) {
            for (int i = 0; i < n2; ++i) {
                EffectCharWearing effectCharWearing = (EffectCharWearing)this.vecEffectCharWearing.elementAt(i);
                if (effectCharWearing == null || effectCharWearing.type != n) continue;
                this.vecEffectCharWearing.removeElement(effectCharWearing);
            }
        }
    }

    public void removeAllEffCharWearing() {
        this.vecEffectCharWearing.removeAllElements();
    }

    public boolean isMiNuong() {
        return false;
    }

    public void SetnameOwner(String string) {
    }

    public void addDataEff(int n, byte[] byArray, int n2, int n3) {
        for (int i = 0; i < this.vecDataSkillEff.size(); ++i) {
            DataSkillEff dataSkillEff = (DataSkillEff)this.vecDataSkillEff.elementAt(i);
            if (dataSkillEff == null || dataSkillEff.idEff != n) continue;
            this.vecDataSkillEff.removeElement(dataSkillEff);
        }
        DataSkillEff dataSkillEff = new DataSkillEff(byArray, (short)n, n2, n3);
        this.vecDataSkillEff.addElement(dataSkillEff);
    }

    public void addDataEffMatna(int n, byte[] byArray) {
        for (int i = 0; i < this.vecDataSkillEff.size(); ++i) {
            DataSkillEff dataSkillEff = (DataSkillEff)this.vecDataSkillEff.elementAt(i);
            if (dataSkillEff == null || dataSkillEff.idEff != n) continue;
            this.vecDataSkillEff.removeElement(dataSkillEff);
        }
        DataSkillEff dataSkillEff = new DataSkillEff(byArray, (short)n, 0, 0);
        dataSkillEff.isMatna = true;
        dataSkillEff.typRequestImg = 113;
        this.vecDataSkillEff.addElement(dataSkillEff);
    }

    public void addDataEff(int n, byte[] byArray, int n2, int n3, long l, byte by) {
        for (int i = 0; i < this.vecDataSkillEff.size(); ++i) {
            DataSkillEff dataSkillEff = (DataSkillEff)this.vecDataSkillEff.elementAt(i);
            if (dataSkillEff == null || dataSkillEff.idEff != n) continue;
            this.vecDataSkillEff.removeElement(dataSkillEff);
        }
        DataSkillEff dataSkillEff = new DataSkillEff(byArray, (short)n, n2, n3, l, by);
        this.vecDataSkillEff.addElement(dataSkillEff);
    }

    public void addDataEff2(int n, byte[] byArray, int n2, int n3, long l, byte by) {
        for (int i = 0; i < this.vecDataSkillEff.size(); ++i) {
            DataSkillEff dataSkillEff = (DataSkillEff)this.vecDataSkillEff.elementAt(i);
            if (dataSkillEff == null || dataSkillEff.idEff != n) continue;
            this.vecDataSkillEff.removeElement(dataSkillEff);
        }
        DataSkillEff dataSkillEff = new DataSkillEff(byArray, (short)n, n2, n3, l, by);
        dataSkillEff.isremovebyTime = false;
        dataSkillEff.isremovebyFrame = true;
        this.vecDataSkillEff.addElement(dataSkillEff);
    }

    public void removeDataSkillEff(int n) {
        for (int i = 0; i < this.vecDataSkillEff.size(); ++i) {
            DataSkillEff dataSkillEff = (DataSkillEff)this.vecDataSkillEff.elementAt(i);
            if (dataSkillEff == null || dataSkillEff.idEff != n) continue;
            this.vecDataSkillEff.removeElement(dataSkillEff);
        }
    }

    public boolean isHairNotHat() {
        if (Player.ID_HAIR_NO_HAT == null) {
            return false;
        }
        if (this.idPartFashion[2] != -1) {
            return false;
        }
        int n = Player.ID_HAIR_NO_HAT.length;
        for (int i = 0; i < n; ++i) {
            byte by = Player.ID_HAIR_NO_HAT[i];
            if (by != this.hair) continue;
            return true;
        }
        return false;
    }

    public int getDy() {
        return 0;
    }

    public int getHeight() {
        return this.hOne;
    }

    public int getDir() {
        return this.Direction;
    }

    public void addiconClan() {
    }

    public boolean canNotMove() {
        return false;
    }

    public boolean canfire() {
        return false;
    }

    public boolean isBossNew() {
        return false;
    }

    static {
        hideEff = 0;
        Wfc = 0;
        Hfc = 0;
        strHelpNPC = null;
        StepHelpServer = 0;
        imgCapchar = null;
        vecCapchar = new mVector("MainObject vecCapchar");
        strCapchar = "";
        frameicon = new byte[]{0, 1, 2, 1};
        mTypePartPaintPlayer = new byte[][]{{6, 0, 1, 2, 5, 4, 3, 6}, {6, 0, 1, 2, 5, 4, 3, 6}, {6, 0, 1, 2, 5, 4, 3, 6}, {6, 0, 1, 2, 5, 4, 3, 6}};
        ALL_EFF_MAT_NA = new mHashtable();
        horseMove = new byte[]{2, 2, 2, 3, 3, 4, 4, 4};
        imgWeapone = new WeaponInfo[4][][];
        for (int i = 0; i < imgWeapone.length; ++i) {
            MainObject.imgWeapone[i] = new WeaponInfo[40][];
            for (int j = 0; j < imgWeapone[i].length; ++j) {
                MainObject.imgWeapone[i][j] = new WeaponInfo[8];
            }
        }
        hwp = new byte[]{24, 21, 19, 13};
        fixY = new byte[]{0, -1, 0, 0, 0};
        ArrMount = new byte[]{3};
        ArrMount1 = new byte[]{2, 2, 2, 0, 0, 3, 3, 3, 0, 0};
    }
}

