/*
 * Decompiled with CFR 0.152.
 */
package InterfaceComponents;

import CLib.mFont;
import CLib.mGraphics;
import CLib.mImage;
import CLib.mSystem;
import CLib.mVector;
import GameObjects.Item;
import GameObjects.MainItem;
import GameObjects.PetItem;
import GameObjects.Player;
import GameScreen.GameScreen;
import GameScreen.LoginScreen;
import GameScreen.MainScreen;
import GameScreen.PaintInfoGameScreen;
import GameScreen.TabScreenNew;
import InterfaceComponents.ListNew;
import InterfaceComponents.MsgDialog;
import InterfaceComponents.iCommand;
import Main.GameCanvas;
import Model.AvMain;
import Model.InfocontenNew;
import Model.T;

public class MainTabNew
extends AvMain {
    public byte typeTab;
    public static byte maxTypeTab = (byte)13;
    public static final byte INVENTORY = 0;
    public static final byte EQUIP = 1;
    public static final byte MY_INFO = 2;
    public static final byte SKILLS = 3;
    public static final byte QUEST = 4;
    public static final byte CHAT = 5;
    public static final byte GOLD = 6;
    public static final byte CONFIG = 7;
    public static final byte SHOP = 8;
    public static final byte CHEST = 9;
    public static final byte REBUILD = 10;
    public static final byte FUNCTION = 11;
    public static final byte CLAN_INVENTORY = 12;
    public static final byte PET_KEEPER = 13;
    public static final byte IMFO_VANTIEU = 14;
    public static final byte SELLITEM = 15;
    public static final byte INVEN_AND_STORE = 16;
    public static final byte OTHER_PLAYER_STORE = 17;
    public static final byte COLOR_WHITE = 0;
    public static final byte COLOR_BLUE = 1;
    public static final byte COLOR_YELLOW = 2;
    public static final byte COLOR_VIOLET = 3;
    public static final byte COLOR_ORANGE = 4;
    public static final byte COLOR_GREEN = 5;
    public static final byte COLOR_RED = 6;
    public static final byte COLOR_BLACK = 7;
    public static final byte COLOR_GREY = 8;
    public int xTab;
    public int yTab;
    public int wSmall;
    public int hSmall;
    public int numWSmall;
    public int numHSmall;
    public int xMoney;
    public int yMoney;
    public int xChar;
    public int yChar;
    public int sizeFocus;
    public int numWBlack;
    public int numHBlack;
    public static int wbackground;
    public static int hbackground;
    public boolean isClan = false;
    public boolean isPet = false;
    public static int wblack;
    public static int hblack;
    public static int hMaxContent;
    int xBegin;
    int yBegin;
    public String nameTab = "";
    public static byte wOneItem;
    public static byte wOne5;
    public static byte Focus;
    public static final byte TAB = 0;
    public static final byte INFO = 1;
    public static mImage[] imgTab;
    public static mImage img_skIcn;
    public static mImage img_pkIcn;
    public static mImage img_arenaIcn;
    public static MainTabNew instance;
    iCommand cmdBack;
    public static int longwidth;
    public static int xlongwidth;
    public static int ylongwidth;
    public static int timeRequest;
    public ListNew listContent = null;
    public static boolean is320;
    mImage imgStarRebuild;
    public boolean isTabHopNguyenLieu;
    public boolean isSetpostCMD;
    public iCommand cmd;
    public boolean isCreate_medal = false;
    public boolean isUPgradeMedal;
    public static int[] color;
    public int xpos_cmd = 0;
    public int ypos_cmd = 0;
    static String nameCur;
    static String[] namePaint;
    public static int timePaintInfo;
    public String[] mContent;
    public String[] mSubContent;
    public String[] mPlusContent = null;
    public int[] mcolor;
    public int[] mSubColor;
    public int[] mPlusColor;
    public String name;
    public int wContent;
    public int colorName;
    public int xCon;
    public int yCon;
    public mVector moreInfoconten = new mVector("MainTabNew moreInfoconten");
    static int[] colorLow;

    public static MainTabNew gI() {
        if (instance == null) {
            instance = new MainTabNew();
        }
        return instance;
    }

    public MainTabNew() {
        int n;
        if (GameCanvas.isTouch) {
            wOneItem = (byte)26;
        } else if (GameCanvas.w >= 240) {
            wOneItem = (byte)24;
        }
        if (GameCanvas.h < 240 && wOneItem > 24) {
            wOneItem = (byte)24;
        }
        hMaxContent = GameCanvas.h - GameCanvas.hCommand * 2;
        wOne5 = (byte)(wOneItem / 5);
        wbackground = GameCanvas.w / 32 + 1;
        hbackground = GameCanvas.h / 32 + 1;
        int n2 = GameCanvas.w / wOneItem;
        if (n2 > 9) {
            n2 = 9;
        }
        int n3 = GameCanvas.h / 5 * 4 - GameCanvas.hCommand / 2;
        if (GameCanvas.isTouch) {
            n3 += GameCanvas.hCommand / 2;
        }
        if ((n = n3 / wOneItem) > 8) {
            n = 8;
        }
        this.wSmall = (n2 - 1) * wOneItem - wOne5 * 3 + (GameCanvas.isSmallScreen ? wOne5 : (byte)0);
        this.hSmall = n * wOneItem + wOne5;
        if (this.hSmall % 2 != 0) {
            --this.hSmall;
        }
        if (GameCanvas.isTouch) {
            if (GameCanvas.w >= 380) {
                longwidth = 170;
                timeRequest = 5;
                hMaxContent = this.hSmall - wOneItem - wOne5;
                this.xTab = (GameCanvas.w - n2 * wOneItem - longwidth) / 2;
                xlongwidth = GameCanvas.w - this.xTab - longwidth;
                ylongwidth = this.yTab + GameCanvas.h / 5;
            } else if (GameCanvas.w > 315) {
                is320 = true;
                if (LoginScreen.indexInfoLogin == 1) {
                    LoginScreen.indexInfoLogin = (byte)2;
                }
                n2 = 8;
                this.wSmall = (n2 - 1) * wOneItem - wOne5 * 3;
                longwidth = 130;
                timeRequest = 5;
                hMaxContent = this.hSmall - wOneItem - wOne5;
                xlongwidth = GameCanvas.w - this.xTab - longwidth + 5;
                ylongwidth = this.yTab + GameCanvas.h / 5;
            }
        }
        if (is320) {
            this.xTab = -5;
            xlongwidth = this.xTab + this.wSmall + wOneItem + wOne5 * 2;
            longwidth = GameCanvas.w - xlongwidth;
        } else {
            this.xTab = (GameCanvas.w - n2 * wOneItem - longwidth) / 2;
        }
        this.yTab = 0;
        this.numWSmall = this.wSmall / 32;
        this.numHSmall = this.hSmall / 32;
        wblack = this.wSmall / wOneItem * wOneItem;
        hblack = (this.hSmall / wOneItem - 1) * wOneItem;
        this.numWBlack = wblack / 32;
        this.numHBlack = hblack / 32;
        this.xMoney = GameCanvas.w - (this.xTab - 9) - 72;
        if (GameCanvas.isTouch && this.xMoney > GameCanvas.w - 112) {
            this.xMoney = GameCanvas.w - 112;
        }
        this.yMoney = 5;
        if (GameCanvas.isSmallScreen) {
            this.yMoney = 2;
        }
        this.xChar = 0;
        this.yChar = GameCanvas.h / 10 - 21;
        if (GameCanvas.isSmallScreen) {
            this.yChar += 4;
        }
        this.sizeFocus = wOne5 + wOneItem;
        if (this.sizeFocus > 32) {
            this.sizeFocus = 32;
        }
    }

    public void init() {
    }

    public void setNameCmd(String string) {
    }

    public void backTab() {
        MainScreen.cameraSub.setAll(0, 0, 0, 0);
        if (GameCanvas.isTouch) {
            if (GameCanvas.currentScreen == GameCanvas.AllInfo) {
                if (GameCanvas.AllInfo.lastScreen != null && GameCanvas.AllInfo.lastScreen != GameCanvas.AllInfo && GameCanvas.AllInfo.lastScreen != GameCanvas.shopNpc) {
                    GameCanvas.AllInfo.lastScreen.Show();
                } else {
                    GameCanvas.game.Show();
                }
            } else if (GameCanvas.currentScreen == GameCanvas.shopNpc) {
                if (GameCanvas.AllInfo.lastScreen != null && GameCanvas.AllInfo.lastScreen != GameCanvas.AllInfo && GameCanvas.AllInfo.lastScreen != GameCanvas.shopNpc) {
                    GameCanvas.shopNpc.lastScreen.Show();
                } else {
                    GameCanvas.game.Show();
                }
            } else if (GameCanvas.currentScreen == GameCanvas.foodPet) {
                if (GameCanvas.foodPet.lastScreen != null) {
                    GameCanvas.foodPet.lastScreen.Show();
                } else {
                    GameCanvas.game.Show();
                }
            }
        }
    }

    public void keypress(int n) {
    }

    public void paintHairShop(mGraphics mGraphics2, int n) {
        GameCanvas.resetTrans(mGraphics2);
        GameScreen.player.paintShowHairPlayer(mGraphics2, GameCanvas.hw, GameCanvas.h / 10 + 15, n);
    }

    public void paintTab(mGraphics mGraphics2, String string, int n, mVector mVector2, boolean bl) {
        int n2;
        int n3;
        int n4;
        int n5 = mVector2.size();
        if (GameCanvas.lowGraphic) {
            MainTabNew.paintRectLowGraphic(mGraphics2, 0, 0, GameCanvas.w, GameCanvas.h, 0);
            MainTabNew.paintRectLowGraphic(mGraphics2, this.xMoney, this.yMoney, 66, 32, 1);
            MainTabNew.paintRectLowGraphic(mGraphics2, this.xTab + wOneItem + wOne5 * 2, this.yTab + GameCanvas.h / 5, this.wSmall, this.hSmall, 1);
        } else {
            int n6;
            for (n4 = 0; n4 < wbackground; ++n4) {
                for (n6 = 0; n6 < hbackground; ++n6) {
                    mGraphics2.drawImage(imgTab[0], n4 * 32, n6 * 32, 0, false);
                }
            }
            for (n4 = 0; n4 < 3; ++n4) {
                mGraphics2.drawImage(imgTab[1], this.xMoney + 22 * n4, this.yMoney, 0, false);
            }
            for (n4 = 0; n4 <= this.numWSmall; ++n4) {
                for (n6 = 0; n6 <= this.numHSmall; ++n6) {
                    if (n4 != 0 && n4 != this.numWSmall && n6 != this.numHSmall && n6 != 0) continue;
                    if (n4 == this.numWSmall) {
                        if (n6 == this.numHSmall) {
                            mGraphics2.drawImage(imgTab[1], this.xTab + wOneItem + wOne5 * 2 + this.wSmall - 32, this.yTab + GameCanvas.h / 5 + this.hSmall - 32, 0, false);
                            continue;
                        }
                        mGraphics2.drawImage(imgTab[1], this.xTab + wOneItem + wOne5 * 2 + this.wSmall - 32, this.yTab + n6 * 32 + GameCanvas.h / 5, 0, false);
                        continue;
                    }
                    if (n6 == this.numHSmall) {
                        mGraphics2.drawImage(imgTab[1], this.xTab + wOneItem + wOne5 * 2 + n4 * 32, this.yTab + GameCanvas.h / 5 + this.hSmall - 32, 0, false);
                        continue;
                    }
                    mGraphics2.drawImage(imgTab[1], this.xTab + wOneItem + wOne5 * 2 + n4 * 32, this.yTab + n6 * 32 + GameCanvas.h / 5, 0, false);
                }
            }
        }
        AvMain.Font3dWhite(mGraphics2, string, this.xTab + wOneItem + wOne5 * 2 + this.wSmall / 2, this.yTab + GameCanvas.h / 5 + wOneItem / 2 - 6, 2);
        GameScreen.infoGame.paintInfoPlayer(mGraphics2, 0, 0, !GameCanvas.isSmallScreen, GameCanvas.isSmallScreen ? mFont.tahoma_7_black : mFont.tahoma_7_white);
        mGraphics2.drawRegion(imgTab[4], 0, 0, 14, 14, 0, this.xMoney + 4, this.yMoney + 2, 0, false);
        mGraphics2.drawRegion(imgTab[4], 0, 14, 14, 14, 0, this.xMoney + 4, this.yMoney + 17, 0, false);
        if (bl) {
            PaintInfoGameScreen.fraEvent.drawFrame(10, this.xMoney - 12, this.yMoney + 10, 0, 3, mGraphics2);
            if (GameScreen.player.myClan != null) {
                mFont.tahoma_7_white.drawString(mGraphics2, MainItem.getDotNumber(GameScreen.player.myClan.coin), this.xMoney + 19, this.yMoney + 3, 0, false);
                mFont.tahoma_7_white.drawString(mGraphics2, MainItem.getDotNumber(GameScreen.player.myClan.gold), this.xMoney + 19, this.yMoney + 18, 0, false);
            }
        } else {
            mFont.tahoma_7_white.drawString(mGraphics2, MainItem.getDotNumber(GameScreen.player.coin), this.xMoney + 19, this.yMoney + 3, 0, false);
            mFont.tahoma_7_white.drawString(mGraphics2, MainItem.getDotNumber(GameScreen.player.gold), this.xMoney + 19, this.yMoney + 18, 0, false);
        }
        if (GameCanvas.lowGraphic) {
            MainTabNew.paintRectLowGraphic(mGraphics2, this.xTab + wOne5, this.yTab + GameCanvas.h / 5 + wOneItem * n, wOne5 + wOneItem, wOneItem, 1);
        } else if (wOne5 + wOneItem > 32) {
            mGraphics2.drawRegion(imgTab[1], 0, 0, wOneItem, wOneItem, 0, this.xTab + wOne5, this.yTab + GameCanvas.h / 5 + wOneItem * n, 0, false);
            mGraphics2.drawRegion(imgTab[1], 0, 0, wOne5, wOneItem, 0, this.xTab + wOne5 + wOneItem, this.yTab + GameCanvas.h / 5 + wOneItem * n, 0, false);
        } else {
            mGraphics2.drawRegion(imgTab[1], 0, 0, wOne5 + wOneItem, wOneItem, 0, this.xTab + wOne5, this.yTab + GameCanvas.h / 5 + wOneItem * n, 0, false);
        }
        if (!GameCanvas.isTouch && Focus == 0) {
            mGraphics2.setColor(color[3]);
            mGraphics2.drawRect(this.xTab + wOne5 + 1, this.yTab + GameCanvas.h / 5 + wOneItem * n + 1, wOne5 + wOneItem - 3, wOneItem - 5, false);
        }
        mGraphics2.setColor(color[0]);
        for (n4 = 0; n4 < n5; ++n4) {
            MainTabNew mainTabNew = (MainTabNew)mVector2.elementAt(n4);
            n3 = 0;
            if (n4 != n) {
                mGraphics2.drawRect(this.xTab + wOne5, this.yTab + GameCanvas.h / 5 + wOneItem * n4, wOne5 + wOneItem, wOneItem, false);
            } else if (Focus == 0 || GameCanvas.isTouch) {
                n3 = -1 + GameCanvas.gameTick / 2 % 3;
            }
            n2 = mainTabNew.typeTab;
            if (n2 > maxTypeTab) {
                n2 = this.typeTab;
            }
            mGraphics2.drawRegion(imgTab[3], 0, n2 * 16, 16, 16, 0, this.xTab + wOne5 + wOne5 / 2 + wOneItem / 2 + n3, this.yTab + GameCanvas.h / 5 + wOneItem / 2 + wOneItem * n4, 3, false);
            if (mainTabNew.typeTab == 2 && Player.diemTiemNang > 0) {
                PaintInfoGameScreen.fralevelup.drawFrame(GameCanvas.gameTick / 4 % 2, this.xTab + wOne5 + wOne5 / 2 + wOneItem + n3 - 4, this.yTab + GameCanvas.h / 5 + wOneItem + wOneItem * n4 - 6, 0, 3, mGraphics2);
                continue;
            }
            if (mainTabNew.typeTab != 3 || Player.diemKyNang <= 0) continue;
            PaintInfoGameScreen.fralevelup.drawFrame(2 + GameCanvas.gameTick / 4 % 2, this.xTab + wOne5 + wOne5 / 2 + wOneItem + n3 - 4, this.yTab + GameCanvas.h / 5 + wOneItem + wOneItem * n4 - 6, 0, 3, mGraphics2);
        }
        if (GameCanvas.lowGraphic) {
            MainTabNew.paintRectLowGraphic(mGraphics2, this.xTab + wOneItem + wOne5 * 3, this.yTab + GameCanvas.h / 5 + wOneItem, wblack, hblack, 2);
        } else {
            for (n4 = 0; n4 <= this.numWBlack; ++n4) {
                for (int i = 0; i <= this.numHBlack; ++i) {
                    if (n4 == this.numWBlack) {
                        if (i == this.numHBlack) {
                            mGraphics2.drawImage(imgTab[2], this.xTab + wOneItem + wOne5 * 3 + wblack - 32, this.yTab + GameCanvas.h / 5 + wOneItem + hblack - 32, 0, false);
                            continue;
                        }
                        mGraphics2.drawImage(imgTab[2], this.xTab + wOneItem + wOne5 * 3 + wblack - 32, this.yTab + GameCanvas.h / 5 + wOneItem + i * 32, 0, false);
                        continue;
                    }
                    if (i == this.numHBlack) {
                        mGraphics2.drawImage(imgTab[2], this.xTab + wOneItem + wOne5 * 3 + n4 * 32, this.yTab + GameCanvas.h / 5 + wOneItem + hblack - 32, 0, false);
                        continue;
                    }
                    mGraphics2.drawImage(imgTab[2], this.xTab + wOneItem + wOne5 * 3 + n4 * 32, this.yTab + GameCanvas.h / 5 + wOneItem + i * 32, 0, false);
                }
            }
        }
        if (longwidth > 0) {
            GameCanvas.resetTrans(mGraphics2);
            n4 = 12;
            if (GameCanvas.lowGraphic) {
                MainTabNew.paintRectLowGraphic(mGraphics2, xlongwidth, ylongwidth, longwidth, this.hSmall, n4);
            } else {
                int n7 = longwidth / 32;
                n3 = this.hSmall / 32;
                for (n2 = 0; n2 <= n7; ++n2) {
                    for (int i = 0; i <= n3; ++i) {
                        n4 = 12;
                        if (i == 0) {
                            n4 = 12;
                        }
                        if (n2 == n7) {
                            if (i == n3) {
                                mGraphics2.drawImage(imgTab[n4], xlongwidth + longwidth - 32, ylongwidth + this.hSmall - 32, 0, false);
                                continue;
                            }
                            mGraphics2.drawImage(imgTab[n4], xlongwidth + longwidth - 32, ylongwidth + i * 32, 0, false);
                            continue;
                        }
                        if (i == n3) {
                            mGraphics2.drawImage(imgTab[n4], xlongwidth + n2 * 32, ylongwidth + this.hSmall - 32, 0, false);
                            continue;
                        }
                        mGraphics2.drawImage(imgTab[n4], xlongwidth + n2 * 32, ylongwidth + i * 32, 0, false);
                    }
                }
            }
            for (int i = xlongwidth; i < xlongwidth + longwidth; i += 6) {
                mGraphics2.fillRect(i, ylongwidth + wOneItem, 4, 1, false);
            }
        }
    }

    public void paintPopupContent(mGraphics mGraphics2, boolean bl) {
        if (longwidth > 0) {
            this.paintContentNew(mGraphics2, bl);
        } else {
            this.paintContent(mGraphics2, bl);
        }
    }

    public void paintContent(mGraphics mGraphics2, boolean bl) {
        int n;
        int n2;
        int n3;
        int n4 = 4;
        TabScreenNew.timeRepaint = 10;
        mGraphics2.setClip(-mGraphics2.getTranslateX(), -mGraphics2.getTranslateY(), GameCanvas.w, GameCanvas.h);
        mGraphics.resetTransAndroid(mGraphics2);
        mGraphics2.saveCanvas();
        mGraphics2.ClipRec(-mGraphics2.getTranslateX(), -mGraphics2.getTranslateY(), GameCanvas.w, GameCanvas.h);
        int n5 = 1;
        if (this.mContent != null) {
            n5 = this.mContent.length;
        }
        if (this.mPlusContent != null) {
            n5 += this.mPlusContent.length;
        }
        if (this.moreInfoconten != null && this.moreInfoconten.size() > 0 && this.mPlusContent == null) {
            ++n5;
        }
        if ((n3 = (n5 + 1) * GameCanvas.hText + 8) > hMaxContent) {
            n3 = hMaxContent;
        }
        if (this.xCon + this.wContent > GameCanvas.w) {
            this.xCon = GameCanvas.w / 2 - this.wContent / 2;
        }
        int n6 = this.yCon;
        mGraphics2.setColor(color[10]);
        mGraphics2.fillRect(this.xCon - 1, n6 - 1, this.wContent + 2, n3 + 2, true);
        mGraphics2.setColor(color[2]);
        mGraphics2.fillRect(this.xCon, n6, this.wContent + 1, n3 + 1, true);
        if (GameCanvas.lowGraphic) {
            MainTabNew.paintRectLowGraphic(mGraphics2, this.xCon, n6, this.wContent, n3, 4);
        } else {
            n2 = this.wContent / 32;
            int n7 = n3 / 32;
            for (n = 0; n <= n2; ++n) {
                for (int i = 0; i <= n7; ++i) {
                    if (n == n2) {
                        if (i == n7) {
                            mGraphics2.drawImage(imgTab[12], this.xCon + this.wContent - 32, n6 + n3 - 32, 0, true);
                            continue;
                        }
                        mGraphics2.drawImage(imgTab[12], this.xCon + this.wContent - 32, n6 + i * 32, 0, true);
                        continue;
                    }
                    if (i == n7) {
                        mGraphics2.drawImage(imgTab[12], this.xCon + n * 32, n6 + n3 - 32, 0, true);
                        continue;
                    }
                    mGraphics2.drawImage(imgTab[12], this.xCon + n * 32, n6 + i * 32, 0, true);
                }
            }
        }
        mGraphics2.restoreCanvas();
        mGraphics2.setClip(this.xCon + 1, n6 + 1, this.wContent - 2, n3 - 2);
        if (!bl && this.name != null) {
            n2 = n6 + 2;
            if (this.mContent == null && mSystem.isj2me) {
                n2 = n6 + 6;
            }
            MainTabNew.paintNameItem(mGraphics2, this.xCon + this.wContent / 2, n2, this.wContent, this.name, this.colorName);
        }
        mGraphics2.saveCanvas();
        mGraphics2.ClipRec(this.xCon + 1, n6 + 1 + 13, this.wContent - 2, n3 - 2 - 13);
        if (!bl && this.listContent != null) {
            mGraphics2.setClip(this.xCon, n6 + GameCanvas.hText, this.wContent, hMaxContent - GameCanvas.hText - 20);
            mGraphics2.translate(0, -this.listContent.cmx);
        }
        if (this.mPlusContent != null) {
            for (n2 = 0; n2 < this.mPlusContent.length; ++n2) {
                MainTabNew.setTextColor(this.mPlusColor[n2]).drawString(mGraphics2, this.mPlusContent[n2], this.xCon + n4, (n6 += GameCanvas.hText) + 2, 0, true);
            }
        }
        if (this.mPlusContent == null && this.moreInfoconten.size() > 0) {
            n6 += GameCanvas.hText;
        }
        for (n2 = 0; n2 < this.moreInfoconten.size(); ++n2) {
            InfocontenNew infocontenNew = (InfocontenNew)this.moreInfoconten.elementAt(n2);
            if (infocontenNew == null) continue;
            if (this.mPlusContent != null) {
                Item.eff_UpLv.paintUpgradeEffect(this.xCon + n4 + this.mPlusContent[0].length() * 5 + 3 + 15 * n2, n6 - GameCanvas.hText / 2 + (this.mPlusContent.length == 1 ? GameCanvas.hText : 0), 13, 13, mGraphics2, 0);
            } else {
                Item.eff_UpLv.paintUpgradeEffect(this.xCon + n4 + 16 + 15 * n2, n6 + GameCanvas.hText - 8, 13, 13, mGraphics2, 0);
            }
            if (infocontenNew.idimage == -1) continue;
            MainItem mainItem = Item.getMaterial(infocontenNew.idimage);
            if (mainItem != null) {
                if (this.mPlusContent != null && this.mPlusContent[0] != null) {
                    mainItem.paintItem_not_num(mGraphics2, this.xCon + n4 + this.mPlusContent[0].length() * 5 + 3 + 15 * n2 + 1, n6 - GameCanvas.hText / 2 + 1 + (this.mPlusContent.length == 1 ? GameCanvas.hText : 0), 21, 1, 0);
                    continue;
                }
                mainItem.paintItem_not_num(mGraphics2, this.xCon + n4 + 16 + 15 * n2, n6 + GameCanvas.hText + 1 - 8, 21, 1, 0);
                continue;
            }
            Item.put_Material(infocontenNew.idimage);
        }
        if (this.mContent != null) {
            for (n2 = 0; n2 < this.mContent.length; ++n2) {
                if (this.mContent[n2] == null) continue;
                mFont mFont2 = null;
                mFont2 = this.mcolor != null ? MainTabNew.setTextColor(this.mcolor[n2]) : mFont.tahoma_7_white;
                mFont2.drawString(mGraphics2, this.mContent[n2], this.xCon + n4, n6 + 2 + (n2 + 1) * GameCanvas.hText, 0, true);
                if (this.mSubContent == null) continue;
                n = mFont2.getWidth(this.mContent[n2]) + 5;
                mFont2 = MainTabNew.setTextColor(this.mSubColor[n2]);
                mFont2.drawString(mGraphics2, this.mSubContent[n2], this.xCon + n + n4, n6 + 2 + (n2 + 1) * GameCanvas.hText, 0, true);
            }
        } else if (!bl) {
            if (this.name != null) {
                n2 = n6 + 2;
                if (this.mContent == null && mSystem.isj2me) {
                    n2 = n6 + 6;
                }
                MainTabNew.paintNameItem(mGraphics2, this.xCon + this.wContent / 2, n2, this.wContent, this.name, this.colorName);
            }
        } else if (this.name != null) {
            MainTabNew.paintNameItem(mGraphics2, this.xCon + this.wContent / 2, n6 + GameCanvas.hText / 4, this.wContent, this.name, this.colorName);
        }
        if (this.cmd != null) {
            this.cmd.paint(mGraphics2, this.xCon + this.wContent - iCommand.wButtonCmd / 2, n6 + n3 - iCommand.hButtonCmd);
            if (this.xpos_cmd == 0 || this.ypos_cmd == 0) {
                this.xpos_cmd = this.xCon + this.wContent - iCommand.wButtonCmd / 2;
                this.ypos_cmd = n6 + n3 - iCommand.hButtonCmd;
            }
        }
        mGraphics.resetTransAndroid(mGraphics2);
        mGraphics2.restoreCanvas();
        mGraphics2.endClip();
        GameCanvas.resetTrans(mGraphics2);
    }

    public void updateCMD() {
        if (this.cmd != null) {
            if (this.xpos_cmd != 0 && this.ypos_cmd != 0) {
                this.cmd.setPos(this.xpos_cmd, this.ypos_cmd, PaintInfoGameScreen.fraButton, this.cmd.caption);
            }
            if (GameCanvas.isTouch) {
                this.cmd.updatePointer();
            } else if (GameCanvas.keyMyPressed[5]) {
                GameCanvas.keyMyPressed[5] = false;
                this.cmd.perform();
            }
        }
    }

    public void update() {
        this.updateCMD();
    }

    public void paintContentNew(mGraphics mGraphics2, boolean bl) {
        Object object;
        int n;
        int n2;
        int n3 = 4;
        TabScreenNew.timeRepaint = 10;
        GameCanvas.resetTrans(mGraphics2);
        int n4 = xlongwidth;
        int n5 = n2 = ylongwidth;
        int n6 = n4 + 1;
        int n7 = n5 + 1;
        int n8 = longwidth - 2;
        int n9 = this.hSmall - 2;
        boolean bl2 = false;
        if (!bl) {
            MainTabNew.paintNameItem(mGraphics2, n4 + longwidth / 2, n5 + wOneItem / 2 - 5, longwidth, this.name, this.colorName);
            if (this.listContent != null) {
                mGraphics2.setClip(n4, n5 + wOneItem + 2, longwidth, hMaxContent - wOneItem - 2);
                n6 = n4;
                n7 = n5 + wOneItem + 2;
                n8 = longwidth;
                n9 = hMaxContent - wOneItem - 2;
                bl2 = true;
            }
            n5 += wOneItem - GameCanvas.hText + GameCanvas.hText / 4;
        }
        mGraphics2.setClip(n6, n7, n8, n9);
        mGraphics2.saveCanvas();
        mGraphics2.ClipRec(n6, n7, n8, n9);
        if (bl2) {
            mGraphics2.translate(0, -this.listContent.cmx);
        }
        if (this.mPlusContent != null) {
            for (n = 0; n < this.mPlusContent.length; ++n) {
                MainTabNew.setTextColor(this.mPlusColor[n]).drawString(mGraphics2, this.mPlusContent[n], n4 + n3, (n5 += GameCanvas.hText) + 2, 0, true);
            }
        }
        if (this.mPlusContent == null && this.moreInfoconten.size() > 0) {
            n5 += GameCanvas.hText;
        }
        for (n = 0; n < this.moreInfoconten.size(); ++n) {
            InfocontenNew infocontenNew = (InfocontenNew)this.moreInfoconten.elementAt(n);
            if (infocontenNew == null) continue;
            if (this.mPlusColor != null) {
                Item.eff_UpLv.paintUpgradeEffect(n4 + n3 + this.mPlusContent[0].length() * 5 + 3 + 15 * n, n5 - GameCanvas.hText / 2 + (this.mPlusContent.length == 1 ? GameCanvas.hText : 0), 13, 13, mGraphics2, 0);
            } else {
                Item.eff_UpLv.paintUpgradeEffect(n4 + n3 + 16 + 15 * n, n5 - GameCanvas.hText / 2 + GameCanvas.hText, 13, 13, mGraphics2, 0);
            }
            if (infocontenNew.idimage == -1) continue;
            object = Item.getMaterial(infocontenNew.idimage);
            if (object != null) {
                if (this.mPlusContent != null && this.mPlusContent[0] != null) {
                    ((MainItem)object).paintItem_not_num(mGraphics2, n4 + n3 + this.mPlusContent[0].length() * 5 + 3 + 15 * n + 1, n5 - GameCanvas.hText / 2 + 1 + (this.mPlusContent.length == 1 ? GameCanvas.hText : 0), 21, 1, 0);
                    continue;
                }
                ((MainItem)object).paintItem_not_num(mGraphics2, n4 + n3 + 16 + 15 * n, n5 - GameCanvas.hText / 2 + GameCanvas.hText + 1, 21, 1, 0);
                continue;
            }
            Item.put_Material(infocontenNew.idimage);
        }
        if (this.mContent != null) {
            PetItem petItem = MsgDialog.pet;
            if (this.isPet && petItem != null) {
                int n10;
                mFont.tahoma_7_white.drawString(mGraphics2, T.level + petItem.LvItem + " + " + petItem.experience / 10 + "," + petItem.experience % 10 + "%", n4 + n3, (n5 += GameCanvas.hText) + 2, 0, true);
                n5 += GameCanvas.hText;
                int n11 = petItem.age / 24;
                int n12 = petItem.age % 24;
                int n13 = (int)((long)petItem.timeDefaultItemFashion - petItem.getTimeItemFashion() / 60000L);
                if (n13 > 0) {
                    mFont.tahoma_7_red.drawString(mGraphics2, T.sudungsau + " " + PaintInfoGameScreen.getStringTime(n13), n4 + n3, n5 + 2, 0, true);
                    n5 += GameCanvas.hText;
                }
                mFont.tahoma_7_white.drawString(mGraphics2, T.tuoi + n11 + "d " + n12 + "h", n4 + n3, n5 + 2, 0, true);
                n5 += GameCanvas.hText;
                if (petItem.petAttack != null) {
                    MainTabNew.setTextColor(Item.colorInfoItem[petItem.petAttack.id]).drawString(mGraphics2, Item.nameInfoItem[petItem.petAttack.id] + ": " + petItem.petAttack.value + "-" + petItem.petAttack.maxDam, n4 + n3, n5 + 2, 0, true);
                }
                mFont.tahoma_7_white.drawString(mGraphics2, T.choan + ": " + petItem.growpoint + "/" + petItem.maxgrow, n4 + n3, (n5 += GameCanvas.hText) + 2, 0, true);
                n5 += GameCanvas.hText;
                for (n10 = 0; n10 < T.mKynangPet.length; ++n10) {
                    mFont.tahoma_7_white.drawString(mGraphics2, T.mKynangPet[n10] + ": " + petItem.mvaluetiemnang[n10] + "/" + petItem.maxtiemnang, n4 + n3, n5 + 2, 0, true);
                    n5 += GameCanvas.hText;
                }
                for (n10 = 0; n10 < petItem.mInfo.length; ++n10) {
                    if (petItem.mInfo[n10].id <= 6) continue;
                    String string = Item.nameInfoItem[petItem.mInfo[n10].id] + ": " + Item.getPercent(Item.isPercentInfoItem[petItem.mInfo[n10].id], petItem.mInfo[n10].value);
                    MainTabNew.setTextColor(Item.colorInfoItem[petItem.mInfo[n10].id]).drawString(mGraphics2, string, n4 + n3, n5 + 2, 0, true);
                    n5 += GameCanvas.hText;
                }
            } else {
                for (int i = 0; i < this.mContent.length; ++i) {
                    if (this.mContent[i] == null) continue;
                    object = null;
                    object = this.mcolor != null ? MainTabNew.setTextColor(this.mcolor[i]) : mFont.tahoma_7_white;
                    ((mFont)object).drawString(mGraphics2, this.mContent[i], n4 + n3, n5 + 2 + (i + 1) * GameCanvas.hText, 0, true);
                    if (this.mSubContent == null) continue;
                    int n14 = ((mFont)object).getWidth(this.mContent[i]) + 5;
                    object = MainTabNew.setTextColor(this.mSubColor[i]);
                    ((mFont)object).drawString(mGraphics2, this.mSubContent[i], n4 + n14 + n3, n5 + 2 + (i + 1) * GameCanvas.hText, 0, true);
                }
            }
        } else if (bl) {
            mFont.tahoma_7b_white.drawString(mGraphics2, this.name, n4 + longwidth / 2, n5 + wOneItem / 2 - 5, 2, true);
        }
        mGraphics.resetTransAndroid(mGraphics2);
        mGraphics2.restoreCanvas();
        mGraphics2.endClip();
        GameCanvas.resetTrans(mGraphics2);
    }

    public static void paintNameItem(mGraphics mGraphics2, int n, int n2, int n3, String string, int n4) {
        if (mFont.tahoma_7b_black.getWidth(string) <= n3) {
            mFont mFont2 = MainTabNew.setTextColorName(n4);
            mFont2.drawString(mGraphics2, string, n, n2, 2, true);
        } else {
            if (nameCur.compareTo(string.trim()) != 0) {
                MainTabNew.getTextName(string);
            }
            mFont mFont3 = MainTabNew.setTextColor(n4);
            mFont3.drawString(mGraphics2, namePaint[0], n, n2 - 6, 2, true);
            mFont3.drawString(mGraphics2, namePaint[1], n, n2 + 6, 2, true);
        }
    }

    public static void getTextName(String string) {
        nameCur = string.trim();
        namePaint = new String[2];
        for (int i = 0; i < namePaint.length; ++i) {
            MainTabNew.namePaint[i] = "";
        }
        String[] stringArray = mFont.split(nameCur, " ");
        for (int i = 0; i < stringArray.length; ++i) {
            if (i <= stringArray.length / 2) {
                namePaint[0] = namePaint[0] + stringArray[i];
                if (i >= stringArray.length / 2) continue;
                namePaint[0] = namePaint[0] + " ";
                continue;
            }
            namePaint[1] = namePaint[1] + stringArray[i];
            if (i >= stringArray.length - 1) continue;
            namePaint[1] = namePaint[1] + " ";
        }
    }

    public static mFont setTextColor(int n) {
        int n2 = n;
        if (n >= 20 && n < 30) {
            n2 = n - 20;
        } else if (n >= 30 && n < 40) {
            n2 = n - 30;
        } else if (n >= 40 && n < 50) {
            n2 = n - 40;
        }
        switch (n2) {
            case 0: {
                return mFont.tahoma_7_white;
            }
            case 1: {
                return mFont.tahoma_7_blue;
            }
            case 2: {
                return mFont.tahoma_7_yellow;
            }
            case 3: {
                return mFont.tahoma_7_violet;
            }
            case 4: {
                return mFont.tahoma_7_orange;
            }
            case 5: {
                return mFont.tahoma_7_green;
            }
            case 6: {
                return mFont.tahoma_7_red;
            }
            case 7: {
                return mFont.tahoma_7_black;
            }
            case 8: {
                return mFont.tahoma_7_gray;
            }
        }
        return mFont.tahoma_7_white;
    }

    public static mFont setTextColorName(int n) {
        int n2 = n;
        if (n >= 20 && n < 30) {
            n2 = n - 20;
        } else if (n >= 30 && n < 40) {
            n2 = n - 30;
        } else if (n >= 40 && n < 50) {
            n2 = n - 40;
        }
        switch (n2) {
            case 0: {
                return mFont.tahoma_7b_white;
            }
            case 1: {
                return mFont.tahoma_7b_blue;
            }
            case 2: {
                return mFont.tahoma_7b_yellow;
            }
            case 3: {
                return mFont.tahoma_7b_violet;
            }
            case 4: {
                return mFont.tahoma_7b_orange;
            }
            case 5: {
                return mFont.tahoma_7b_green;
            }
            case 7: {
                return mFont.tahoma_7b_black;
            }
            case 8: {
                return mFont.tahoma_7_gray;
            }
        }
        return mFont.tahoma_7b_white;
    }

    public void setPaintInfo() {
    }

    public void setYCon(MainItem mainItem) {
    }

    public void setinfoContennew(int n, int n2) {
        InfocontenNew infocontenNew = new InfocontenNew(n, n2);
        this.moreInfoconten.addElement(infocontenNew);
    }

    public void setPosCmd(mVector mVector2) {
        if (mVector2 == null) {
            return;
        }
        int n = mVector2.size();
        if (n == 0) {
            return;
        }
        int n2 = ylongwidth + this.hSmall;
        int n3 = xlongwidth;
        if (n == 1) {
            iCommand iCommand2 = (iCommand)mVector2.elementAt(0);
            if (is320) {
                iCommand2.setPos(n3 + longwidth / 2, n2 - 10, PaintInfoGameScreen.fraButton2, iCommand2.caption);
            } else {
                iCommand2.setPos(n3 + longwidth / 2, n2 - 15, null, iCommand2.caption);
            }
        } else if (n == 2) {
            iCommand iCommand3 = (iCommand)mVector2.elementAt(0);
            if (is320) {
                iCommand3.setPos(n3 + longwidth / 4, n2 - 10, PaintInfoGameScreen.fraButton2, iCommand3.caption);
            } else {
                iCommand3.setPos(n3 + longwidth / 4, n2 - 15, null, iCommand3.caption);
            }
            iCommand iCommand4 = (iCommand)mVector2.elementAt(1);
            if (is320) {
                iCommand4.setPos(n3 + longwidth / 4 * 3 + 2, n2 - 10, PaintInfoGameScreen.fraButton2, iCommand4.caption);
            } else {
                iCommand4.setPos(n3 + longwidth / 4 * 3 + 2, n2 - 15, null, iCommand4.caption);
            }
        } else {
            for (int i = 0; i < n; ++i) {
                iCommand iCommand5 = (iCommand)mVector2.elementAt(i);
                if (i == n - 1 && n % 2 == 1) {
                    if (is320) {
                        iCommand5.setPos(n3 + longwidth / 2, n2 - 10, PaintInfoGameScreen.fraButton2, iCommand5.caption);
                        continue;
                    }
                    iCommand5.setPos(n3 + longwidth / 2, n2 - 15 - (n - 1) / 2 * 30 + i / 2 * 30, null, iCommand5.caption);
                    continue;
                }
                if (is320) {
                    iCommand5.setPos(n3 + longwidth / 4 + i % 2 * (longwidth / 2 + 2), n2 - 10 - (n - 1) / 2 * 22 + i / 2 * 22, PaintInfoGameScreen.fraButton2, iCommand5.caption);
                    continue;
                }
                iCommand5.setPos(n3 + longwidth / 4 + i % 2 * (longwidth / 2 + 2), n2 - 15 - (n - 1) / 2 * 30 + i / 2 * 30, null, iCommand5.caption);
            }
        }
    }

    public static void paintRectLowGraphic(mGraphics mGraphics2, int n, int n2, int n3, int n4, int n5) {
        mGraphics2.setColor(colorLow[n5]);
        mGraphics2.fillRect(n, n2, n3, n4, false);
    }

    static {
        wOneItem = (byte)20;
        Focus = 0;
        imgTab = new mImage[15];
        longwidth = 0;
        timeRequest = 15;
        is320 = false;
        color = new int[]{-5727870, -4543854, -477112, -1315861, -5859456, -3689060, -4807282, -9411756, -1845826, -5267575, -460632};
        nameCur = "";
        namePaint = new String[2];
        colorLow = new int[]{-2701394, -6517641, -9411756, -7832985, -11844807};
    }
}

