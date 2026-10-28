/*
 * Decompiled with CFR 0.152.
 */
package InterfaceComponents;

import CLib.mFont;
import CLib.mGraphics;
import CLib.mImage;
import CLib.mSound;
import CLib.mSystem;
import CLib.mVector;
import GameEffect.EffectEnd;
import GameEffect.EffectSkill;
import GameEffect.MainEffect;
import GameObjects.Item;
import GameObjects.MainItem;
import GameScreen.PaintInfoGameScreen;
import InterfaceComponents.DataRebuildItem;
import InterfaceComponents.ListNew;
import InterfaceComponents.MainTabNew;
import InterfaceComponents.Scroll;
import InterfaceComponents.ScrollResult;
import InterfaceComponents.iCommand;
import Main.GameCanvas;
import Model.AvMain;
import Model.Menu2;
import Model.T;
import netcommand.global.GlobalService;

public class TabRebuildItem
extends MainTabNew {
    byte typeRebuild = 0;
    public static byte TYPE_REBUILD = 0;
    public static byte TYPE_REPLACE_PLUS = 1;
    public static byte TYPE_REBUILD_WING = (byte)2;
    public static byte TYPE_KHAM_NGOC = (byte)3;
    public static byte TYPE_GHEP_NGOC = (byte)4;
    public static byte TYPE_DUC_LO = (byte)5;
    public static byte TYPE_ANY = (byte)6;
    public static byte KHAM_NGOC = 0;
    public static byte GHEP_NGOC = 1;
    public static byte DUC_LO = (byte)2;
    public static String[] numofGem = new String[]{"", "", "", "", ""};
    public static byte[] numColor = new byte[]{0, 0, 0, 0, 0};
    int maxw;
    int maxh;
    int indexPaint = 12;
    int winfo = 140;
    int[][] posMaterial;
    public static String[] mNameMaterial;
    int xinfo;
    int yinfo;
    public static DataRebuildItem[] dataRebuild;
    public static MainItem itemRe;
    public static MainItem itemPlus;
    public static MainItem itemFree;
    public static MainItem itemWing;
    public static int[] numMaterialInven;
    public static short[] idMaterial;
    public static int[] numWingMaterialInven;
    public static DataRebuildItem[] dataWing;
    public static int priceWing;
    public static int timeUseWing;
    public static String nameWing;
    public static int isChetao;
    public static short lvReWing;
    public static short idWingOk;
    public static String tilemayman;
    public static String tilemaymanafter;
    public static byte isBeginEff;
    public static byte isNextRebuild;
    public static boolean isLucky;
    public static boolean isShow;
    iCommand cmdHoiDap;
    iCommand cmdView;
    iCommand cmcloseInfo;
    iCommand cmdHoiReplace;
    iCommand cmdHoiReWing;
    iCommand cmdHopThanh;
    iCommand cmdDapnhanh;
    public static mVector vecEffRe;
    public static String contentShow;
    mVector vecListCmd = new mVector("TabRebuildItem vecListCmd");
    public static mVector vecGem;
    public static boolean resetItemReplace;
    public byte typekham;
    public static boolean isDapnhanh;
    Scroll scr = new Scroll();
    public boolean ispaintitemRe = false;
    public static Item itemDaMayMan;
    int timeShowReplace;
    long time;
    public static byte countSetpaintinfo;

    public TabRebuildItem(String string, byte by) {
        isDapnhanh = false;
        this.isTabHopNguyenLieu = false;
        this.imgStarRebuild = null;
        this.typeRebuild = by;
        this.typeTab = (byte)10;
        this.nameTab = string;
        this.xBegin = this.xTab + MainTabNew.wOneItem + MainTabNew.wOne5 * 3;
        this.yBegin = this.yTab + GameCanvas.h / 5 + MainTabNew.wOneItem;
        this.maxw = (MainTabNew.wblack - 8) / 32;
        this.maxh = (MainTabNew.hblack - 8) / 32;
        if (MainTabNew.longwidth > 0) {
            this.winfo = MainTabNew.longwidth;
            this.xinfo = MainTabNew.xlongwidth;
            this.yinfo = MainTabNew.ylongwidth + MainTabNew.wOneItem / 2;
        } else {
            this.xinfo = this.xBegin + MainTabNew.wblack / 2 - this.winfo / 2;
            this.yinfo = this.yBegin + 4;
            if (this.yinfo > GameCanvas.h - GameCanvas.hCommand - 150 - GameCanvas.hCommand) {
                this.yinfo = GameCanvas.h - GameCanvas.hCommand - 150 - GameCanvas.hCommand;
            }
        }
        if (GameCanvas.isSmallScreen) {
            this.yinfo = GameCanvas.h - GameCanvas.hCommand - 150 - GameCanvas.hCommand;
        }
        this.cmdBack = new iCommand(T.back, -1, this);
        this.cmdView = new iCommand(T.info, 1, this);
        if (GameCanvas.isTouch) {
            this.cmdBack.caption = T.close;
        }
        if (this.typeRebuild == TYPE_REBUILD || this.typeRebuild == TYPE_KHAM_NGOC || this.typeRebuild == TYPE_DUC_LO || this.typeRebuild == TYPE_GHEP_NGOC || this.typeRebuild == TYPE_ANY) {
            this.posMaterial = mSystem.new_M_Int(6, 2);
            this.posMaterial[4][0] = this.xBegin + MainTabNew.wblack / 2;
            this.posMaterial[4][1] = this.yBegin + MainTabNew.hblack / 2 - 52;
            this.posMaterial[2][0] = this.xBegin + MainTabNew.wblack / 2 + 52;
            this.posMaterial[2][1] = this.yBegin + MainTabNew.hblack / 2 - 16;
            this.posMaterial[1][0] = this.xBegin + MainTabNew.wblack / 2 + 32;
            this.posMaterial[1][1] = this.yBegin + MainTabNew.hblack / 2 + 45;
            this.posMaterial[3][0] = this.xBegin + MainTabNew.wblack / 2 - 32;
            this.posMaterial[3][1] = this.yBegin + MainTabNew.hblack / 2 + 45;
            this.posMaterial[0][0] = this.xBegin + MainTabNew.wblack / 2 - 52;
            this.posMaterial[0][1] = this.yBegin + MainTabNew.hblack / 2 - 16;
            this.posMaterial[5][0] = this.xBegin + MainTabNew.wblack / 2;
            this.posMaterial[5][1] = this.yBegin + MainTabNew.hblack / 2;
            isLucky = false;
            this.right = null;
            isShow = false;
            this.cmdHoiDap = new iCommand(T.dapdo, 0, this);
            this.cmdDapnhanh = new iCommand(T.dapnhanh, 9, this);
            if (MainTabNew.longwidth > 0) {
                int n = MainTabNew.ylongwidth + this.hSmall;
                int n2 = MainTabNew.xlongwidth;
                if (MainTabNew.is320) {
                    this.cmdHoiDap.setPos(n2 + MainTabNew.longwidth / 2, n - 10, PaintInfoGameScreen.fraButton2, this.cmdHoiDap.caption);
                    this.cmdDapnhanh.setPos(n2 + MainTabNew.longwidth / 2, n - 10, PaintInfoGameScreen.fraButton2, this.cmdDapnhanh.caption);
                } else {
                    this.cmdHoiDap.setPos(n2 + MainTabNew.longwidth / 2, n - 15, null, this.cmdHoiDap.caption);
                    this.cmdDapnhanh.setPos(n2 + MainTabNew.longwidth / 2, n - 15, null, this.cmdDapnhanh.caption);
                }
            } else if (GameCanvas.isTouch) {
                this.cmdHoiDap.setPos(iCommand.wButtonCmd / 2 + 2, GameCanvas.h - iCommand.hButtonCmd / 2 - 2, null, this.cmdHoiDap.caption);
                this.cmdDapnhanh.setPos(iCommand.wButtonCmd / 2 + 2, GameCanvas.h - iCommand.hButtonCmd / 2 - 2, null, this.cmdDapnhanh.caption);
                this.cmdView.setPos(GameCanvas.w - iCommand.wButtonCmd / 2 - 2, GameCanvas.h - iCommand.hButtonCmd / 2 - 2, null, this.cmdView.caption);
            }
        } else if (this.typeRebuild == TYPE_REPLACE_PLUS) {
            this.posMaterial = mSystem.new_M_Int(2, 2);
            this.posMaterial[1][0] = this.xBegin + MainTabNew.wblack / 2 + 52;
            this.posMaterial[1][1] = this.yBegin + MainTabNew.hblack / 2 - 16;
            this.posMaterial[0][0] = this.xBegin + MainTabNew.wblack / 2 - 52;
            this.posMaterial[0][1] = this.yBegin + MainTabNew.hblack / 2 - 16;
            this.cmdHoiReplace = new iCommand(T.replace, 3, this);
            if (MainTabNew.longwidth > 0) {
                int n = MainTabNew.ylongwidth + this.hSmall;
                int n3 = MainTabNew.xlongwidth;
                if (MainTabNew.is320) {
                    this.cmdHoiReplace.setPos(n3 + MainTabNew.longwidth / 2, n - 10, PaintInfoGameScreen.fraButton2, this.cmdHoiReplace.caption);
                } else {
                    this.cmdHoiReplace.setPos(n3 + MainTabNew.longwidth / 2, n - 15, null, this.cmdHoiReplace.caption);
                }
            } else if (GameCanvas.isTouch) {
                this.cmdHoiReplace.setPos(iCommand.wButtonCmd / 2 + 2, GameCanvas.h - iCommand.hButtonCmd / 2 - 2, null, this.cmdHoiReplace.caption);
                this.cmdView.setPos(GameCanvas.w - iCommand.wButtonCmd / 2 - 2, GameCanvas.h - iCommand.hButtonCmd / 2 - 2, null, this.cmdView.caption);
            }
        } else if (this.typeRebuild == TYPE_REBUILD_WING) {
            this.posMaterial = mSystem.new_M_Int(7, 2);
            this.posMaterial[1][0] = this.xBegin + MainTabNew.wblack / 2;
            this.posMaterial[1][1] = this.yBegin + MainTabNew.hblack / 2 - 52;
            this.posMaterial[5][0] = this.xBegin + MainTabNew.wblack / 2 + 46;
            this.posMaterial[5][1] = this.yBegin + MainTabNew.hblack / 2 - 26;
            this.posMaterial[2][0] = this.xBegin + MainTabNew.wblack / 2 + 46;
            this.posMaterial[2][1] = this.yBegin + MainTabNew.hblack / 2 + 26;
            this.posMaterial[4][0] = this.xBegin + MainTabNew.wblack / 2;
            this.posMaterial[4][1] = this.yBegin + MainTabNew.hblack / 2 + 52;
            this.posMaterial[0][0] = this.xBegin + MainTabNew.wblack / 2 - 46;
            this.posMaterial[0][1] = this.yBegin + MainTabNew.hblack / 2 + 26;
            this.posMaterial[3][0] = this.xBegin + MainTabNew.wblack / 2 - 46;
            this.posMaterial[3][1] = this.yBegin + MainTabNew.hblack / 2 - 26;
            this.posMaterial[6][0] = this.xBegin + MainTabNew.wblack / 2;
            this.posMaterial[6][1] = this.yBegin + MainTabNew.hblack / 2;
            this.cmdHoiReWing = new iCommand(T.nangcap, 4, this);
            if (MainTabNew.longwidth > 0) {
                int n = MainTabNew.ylongwidth + this.hSmall;
                int n4 = MainTabNew.xlongwidth;
                if (MainTabNew.is320) {
                    this.cmdHoiReWing.setPos(n4 + MainTabNew.longwidth / 2, n - 10, PaintInfoGameScreen.fraButton2, this.cmdHoiReWing.caption);
                } else {
                    this.cmdHoiReWing.setPos(n4 + MainTabNew.longwidth / 2, n - 15, null, this.cmdHoiReWing.caption);
                }
            } else if (GameCanvas.isTouch) {
                this.cmdHoiReWing.setPos(iCommand.wButtonCmd / 2 + 2, GameCanvas.h - iCommand.hButtonCmd / 2 - 2, null, this.cmdHoiReWing.caption);
                this.cmdView.setPos(GameCanvas.w - iCommand.wButtonCmd / 2 - 2, GameCanvas.h - iCommand.hButtonCmd / 2 - 2, null, this.cmdView.caption);
            }
        }
        this.init();
        if (this.typeRebuild == TYPE_DUC_LO || this.typeRebuild == TYPE_GHEP_NGOC || this.typeRebuild == TYPE_KHAM_NGOC || this.typeRebuild == TYPE_REBUILD || this.typeRebuild == TYPE_REPLACE_PLUS || this.typeRebuild == TYPE_ANY) {
            this.imgStarRebuild = mImage.createImage("/interface/rebuild.img");
        } else if (this.typeRebuild == TYPE_REBUILD_WING) {
            this.imgStarRebuild = mImage.createImage("/interface/rebuild2.img");
        }
    }

    public void setPaintInfo() {
        this.mContent = null;
        this.mSubContent = null;
        this.mPlusContent = null;
        if (itemRe != null && TabRebuildItem.itemRe.itemName != null) {
            this.name = TabRebuildItem.itemRe.itemName;
        }
        this.listContent = null;
        if (itemRe != null) {
            int n = 1;
            this.mContent = TabRebuildItem.itemRe.mcontent;
            this.moreInfoconten = TabRebuildItem.itemRe.moreContenGem;
            this.mPlusContent = TabRebuildItem.itemRe.mPlusContent;
            this.mPlusColor = TabRebuildItem.itemRe.mPlusColor;
            this.mcolor = TabRebuildItem.itemRe.mColor;
            this.colorName = TabRebuildItem.itemRe.colorNameItem;
            if (TabRebuildItem.itemRe.mcontent != null) {
                n += this.mContent.length;
            }
            if (TabRebuildItem.itemRe.mPlusContent != null) {
                n += TabRebuildItem.itemRe.mPlusContent.length;
            }
            if (n * GameCanvas.hText > MainTabNew.hMaxContent - 30) {
                this.listContent = new ListNew(MainTabNew.xlongwidth, MainTabNew.ylongwidth, MainTabNew.longwidth, MainTabNew.hMaxContent, 0, 0, n * GameCanvas.hText - (MainTabNew.hMaxContent - 30));
            } else if (GameCanvas.isTouch) {
                this.listContent = new ListNew(MainTabNew.xlongwidth, MainTabNew.ylongwidth, MainTabNew.longwidth, MainTabNew.hMaxContent, 0, 0, 0);
            }
            return;
        }
    }

    public void commandPointer(int n, int n2) {
        switch (n) {
            case -1: {
                this.backTab();
                break;
            }
            case 0: 
            case 9: {
                if (n == 9) {
                    isDapnhanh = true;
                }
                if (this.typeRebuild == TYPE_ANY) {
                    if (this.isUPgradeMedal && itemRe != null) {
                        GlobalService.gI().Hop_rac((byte)4, (short)TabRebuildItem.itemRe.Id, (byte)TabRebuildItem.itemRe.ItemCatagory);
                        vecGem.removeAllElements();
                        return;
                    }
                    if (this.isCreate_medal) {
                        GlobalService.gI().Hop_rac((byte)3);
                        vecGem.removeAllElements();
                        break;
                    }
                    if (vecGem.size() > 0) {
                        MainItem mainItem = (MainItem)vecGem.elementAt(0);
                        if (mainItem == null) break;
                        GlobalService.gI().Hop_rac((byte)2, (short)mainItem.Id, (byte)mainItem.ItemCatagory);
                        vecGem.removeAllElements();
                        break;
                    }
                    GameCanvas.start_Ok_Dialog(T.bonguyenlieu);
                    break;
                }
                if (this.typeRebuild == TYPE_DUC_LO) {
                    int n3 = -1;
                    if (itemRe != null && vecGem.size() > 0) {
                        MainItem mainItem = (MainItem)vecGem.elementAt(0);
                        if (mainItem != null) {
                            n3 = mainItem.Id;
                        }
                        GlobalService.gI().KhamNgoc(DUC_LO, TabRebuildItem.itemRe.Id, n3, -1, -1);
                        break;
                    }
                    GameCanvas.start_Ok_Dialog(T.chuaboduclo);
                    break;
                }
                if (this.typeRebuild == TYPE_GHEP_NGOC) {
                    if (vecGem.size() > 0) {
                        MainItem mainItem = (MainItem)vecGem.elementAt(0);
                        if (mainItem == null) break;
                        GlobalService.gI().KhamNgoc(GHEP_NGOC, mainItem.Id, -1, -1, -1);
                        break;
                    }
                    GameCanvas.start_Ok_Dialog(T.bongoc);
                    break;
                }
                if (this.typeRebuild == TYPE_KHAM_NGOC) {
                    if (itemRe != null && vecGem.size() > 0) {
                        int[] nArray = new int[]{-1, -1, -1};
                        for (int i = 0; i < vecGem.size(); ++i) {
                            MainItem mainItem = (MainItem)vecGem.elementAt(i);
                            if (mainItem == null) continue;
                            nArray[i] = mainItem.Id;
                        }
                        GlobalService.gI().KhamNgoc(KHAM_NGOC, TabRebuildItem.itemRe.Id, nArray[0], nArray[1], nArray[2]);
                        break;
                    }
                    GameCanvas.start_Ok_Dialog(T.chuaboitem);
                    break;
                }
                if (itemRe == null) break;
                String string = "";
                int n4 = 0;
                mVector mVector2 = new mVector("TabRebuildItem menu");
                if (TabRebuildItem.dataRebuild[TabRebuildItem.itemRe.tier].priceCoin != 0) {
                    mVector2.addElement(new iCommand(T.coin, 2, 0, (AvMain)this));
                    string = T.dapbangxu + MainItem.getDotNumber(TabRebuildItem.dataRebuild[TabRebuildItem.itemRe.tier].priceCoin) + " " + T.coin + "?";
                    ++n4;
                }
                if (TabRebuildItem.dataRebuild[TabRebuildItem.itemRe.tier].priceGold != 0) {
                    mVector2.addElement(new iCommand(T.gem, 2, 1, (AvMain)this));
                    string = T.dapbangxu + TabRebuildItem.dataRebuild[TabRebuildItem.itemRe.tier].priceGold + " " + T.gem + "?";
                    ++n4;
                }
                if (n4 == 2) {
                    string = T.hoidapxuluong + MainItem.getDotNumber(TabRebuildItem.dataRebuild[TabRebuildItem.itemRe.tier].priceCoin) + " " + T.coin + T.hay + TabRebuildItem.dataRebuild[TabRebuildItem.itemRe.tier].priceGold + " " + T.gem + "?";
                }
                GameCanvas.menu2.startAt_NPC(mVector2, string, Menu2.IdNPCLast, (byte)2, false, 0);
                break;
            }
            case 1: {
                if (MainTabNew.longwidth != 0) break;
                isShow = itemRe != null && (this.typeRebuild == TYPE_REBUILD || this.typeRebuild == TYPE_KHAM_NGOC) || itemPlus != null && itemFree != null && this.typeRebuild == TYPE_REPLACE_PLUS || this.typeRebuild == TYPE_REBUILD_WING && dataWing != null || this.typeRebuild == TYPE_ANY ? !isShow : false;
                if (isShow) {
                    if (!GameCanvas.isTouch) {
                        this.cmdView.caption = T.close;
                        break;
                    }
                    this.cmdView.setPos(this.xinfo + this.winfo - 12, this.yinfo + 10, PaintInfoGameScreen.fraCloseMenu, "");
                    break;
                }
                if (!GameCanvas.isTouch) {
                    this.cmdView.caption = T.info;
                    break;
                }
                this.cmdView.caption = T.info;
                this.cmdView.setPos(GameCanvas.w - iCommand.wButtonCmd / 2, GameCanvas.h - iCommand.hButtonCmd / 2, null, this.cmdView.caption);
                break;
            }
            case 2: {
                if (itemRe == null) break;
                GameCanvas.menu2.doCloseMenu();
                GlobalService.gI().Rebuild_Item((byte)2, (short)0, (byte)n2);
                if (MainTabNew.longwidth == 0) {
                    isShow = false;
                    if (!GameCanvas.isTouch) {
                        this.cmdView.caption = T.info;
                    } else {
                        this.cmdView.caption = T.info;
                        this.cmdView.setPos(GameCanvas.w - iCommand.wButtonCmd / 2, GameCanvas.h - iCommand.hButtonCmd / 2, null, this.cmdView.caption);
                    }
                }
                GameCanvas.start_Wait_Dialog(T.pleaseWait, new iCommand(T.close, -1));
                break;
            }
            case 3: {
                if (itemFree == null || itemPlus == null) break;
                GlobalService.gI().Replace_Item((byte)1, (short)0);
                GameCanvas.start_Wait_Dialog(T.pleaseWait, new iCommand(T.close, -1));
                break;
            }
            case 4: {
                mVector mVector3 = new mVector("TabRebuildItem menu2");
                String string = "";
                if (itemWing == null) {
                    string = T.hoiTaoCanh + this.nameTab + "? " + T.phi + ": " + MainItem.getDotNumber(priceWing) + " " + T.coin + ", " + T.LVyeucau + lvReWing + ", " + T.timeyeucau + PaintInfoGameScreen.getStringTime(timeUseWing) + ".";
                    mVector3.addElement(new iCommand(T.taoCanh, 5, 0, (AvMain)this));
                } else {
                    string = T.hoinangcapcanh + nameWing + "? " + T.phi + ": " + MainItem.getDotNumber(priceWing) + " " + T.coin + ", " + T.LVyeucau + lvReWing + ", " + T.timeyeucau + PaintInfoGameScreen.getStringTime(timeUseWing) + ".";
                    mVector3.addElement(new iCommand(T.nangcap, 6, 0, (AvMain)this));
                }
                GameCanvas.menu2.startAt_NPC(mVector3, string, Menu2.IdNPCLast, (byte)2, false, 0);
                break;
            }
            case 5: {
                GameCanvas.menu2.doCloseMenu();
                GlobalService.gI().Rebuild_Wing((byte)1, isChetao, (short)0);
                break;
            }
            case 6: {
                GameCanvas.menu2.doCloseMenu();
                if (itemWing == null) break;
                GlobalService.gI().Rebuild_Wing((byte)3, isChetao, (short)TabRebuildItem.itemWing.Id);
                break;
            }
            case 7: {
                GameCanvas.menu2.doCloseMenu();
                GlobalService.gI().Rebuild_Item((byte)5, (short)TabRebuildItem.itemRe.Id, (byte)TabRebuildItem.itemRe.ItemCatagory);
                if (MainTabNew.longwidth == 0) {
                    isShow = false;
                    if (!GameCanvas.isTouch) {
                        this.cmdView.caption = T.info;
                    } else {
                        this.cmdView.caption = T.info;
                        this.cmdView.setPos(GameCanvas.w - iCommand.wButtonCmd / 2, GameCanvas.h - iCommand.hButtonCmd / 2, null, this.cmdView.caption);
                    }
                }
                GameCanvas.start_Wait_Dialog(T.pleaseWait, new iCommand(T.close, -1));
            }
        }
        super.commandPointer(n, n2);
    }

    public void init() {
        this.cmdView.caption = T.info;
        if (this.typeRebuild == TYPE_REBUILD || this.typeRebuild == TYPE_KHAM_NGOC || this.typeRebuild == TYPE_GHEP_NGOC || this.typeRebuild == TYPE_DUC_LO || this.typeRebuild == TYPE_ANY) {
            this.initRebuild();
        } else if (this.typeRebuild == TYPE_REPLACE_PLUS) {
            this.initReplace();
        } else if (this.typeRebuild == TYPE_REBUILD_WING) {
            this.initReWing();
        }
    }

    private void initReplace() {
        MainTabNew.timePaintInfo = 0;
        if (MainTabNew.longwidth > 0) {
            isShow = true;
        }
        if (!GameCanvas.isTouch) {
            if (itemPlus != null && itemFree != null && !resetItemReplace) {
                this.left = this.cmdView;
                this.center = this.cmdHoiReplace;
            }
            this.right = this.cmdBack;
        } else {
            this.vecListCmd.removeAllElements();
            if (!resetItemReplace && itemPlus != null && itemFree != null) {
                if (MainTabNew.longwidth > 0) {
                    this.vecListCmd.addElement(this.cmdHoiReplace);
                } else {
                    this.vecListCmd.addElement(this.cmdView);
                    this.vecListCmd.addElement(this.cmdHoiReplace);
                }
            }
        }
        this.listContent = null;
        vecEffRe.removeAllElements();
        isBeginEff = 0;
        isNextRebuild = (byte)-1;
        if (resetItemReplace) {
            itemFree = null;
            itemPlus = null;
            resetItemReplace = false;
        }
        if (itemPlus == null && itemFree == null) {
            GameCanvas.menu2.startAt_NPC(null, T.boitemreplace, Menu2.IdNPCLast, (byte)2, false, 0);
        }
    }

    private void initRebuild() {
        isDapnhanh = false;
        MainTabNew.timePaintInfo = 0;
        if (MainTabNew.longwidth > 0) {
            isShow = true;
        }
        if (!GameCanvas.isTouch) {
            if (itemRe != null || this.typeRebuild == TYPE_GHEP_NGOC || this.typeRebuild == TYPE_ANY) {
                this.left = this.cmdView;
                this.center = this.cmdHoiDap;
            }
            this.right = this.cmdBack;
        } else {
            this.vecListCmd.removeAllElements();
            if (itemRe != null) {
                if (MainTabNew.longwidth > 0) {
                    this.vecListCmd.addElement(this.cmdHoiDap);
                } else {
                    this.vecListCmd.addElement(this.cmdView);
                    this.vecListCmd.addElement(this.cmdHoiDap);
                }
            }
            if (this.typeRebuild == TYPE_GHEP_NGOC) {
                this.vecListCmd.addElement(this.cmdHoiDap);
                this.cmdHoiDap.caption = T.hopngoc;
            }
            if (this.typeRebuild == TYPE_ANY) {
                this.vecListCmd.addElement(this.cmdHoiDap);
                this.cmdHoiDap.caption = T.hoprac;
            }
        }
        this.listContent = null;
        vecEffRe.removeAllElements();
        isBeginEff = 0;
        isNextRebuild = 0;
        if (itemRe == null && this.typeRebuild != TYPE_GHEP_NGOC && this.typeRebuild != TYPE_ANY) {
            String string = this.isTabHopNguyenLieu ? T.hopNguyenLieu : T.bovatphamvao;
            GameCanvas.menu2.startAt_NPC(null, string, Menu2.IdNPCLast, (byte)2, false, 0);
        }
    }

    private void initReWing() {
        MainTabNew.timePaintInfo = 0;
        if (MainTabNew.longwidth > 0) {
            isShow = true;
        }
        if (!GameCanvas.isTouch) {
            if (!resetItemReplace && dataWing != null) {
                this.left = this.cmdView;
                this.cmdHoiReWing.caption = T.nangcap;
                if (itemWing == null) {
                    this.cmdHoiReWing.caption = T.taoCanh;
                }
                this.center = this.cmdHoiReWing;
            }
            this.right = this.cmdBack;
        } else {
            this.vecListCmd.removeAllElements();
            if (!resetItemReplace && dataWing != null) {
                this.cmdHoiReWing.caption = T.nangcap;
                if (itemWing == null) {
                    this.cmdHoiReWing.caption = T.taoCanh;
                }
                if (MainTabNew.longwidth > 0) {
                    this.vecListCmd.addElement(this.cmdHoiReWing);
                } else {
                    this.vecListCmd.addElement(this.cmdView);
                    this.vecListCmd.addElement(this.cmdHoiReWing);
                }
            }
        }
        this.listContent = null;
        vecEffRe.removeAllElements();
        isBeginEff = 0;
        isNextRebuild = 0;
        if (resetItemReplace) {
            itemWing = null;
            resetItemReplace = false;
        }
    }

    public void backTab() {
        if (MainTabNew.longwidth == 0) {
            isShow = false;
        }
        MainTabNew.timePaintInfo = 0;
        MainTabNew.Focus = 0;
        vecEffRe.removeAllElements();
        super.backTab();
    }

    public void paint(mGraphics mGraphics2) {
        Object object;
        int n;
        if (isBeginEff == 0 && !GameCanvas.isTouch && MainTabNew.Focus == 1) {
            mGraphics2.fillRect(this.xBegin + 2, this.yBegin + 2, MainTabNew.wblack - 4, MainTabNew.hblack - 4, false);
        }
        if (GameCanvas.lowGraphic) {
            MainTabNew.paintRectLowGraphic(mGraphics2, this.xBegin + 4, this.yBegin + 4, MainTabNew.wblack - 8, MainTabNew.hblack - 8, 4);
        } else {
            this.paintRect(mGraphics2);
        }
        mGraphics2.drawImage(this.imgStarRebuild, this.xBegin + MainTabNew.wblack / 2 - 54, this.yBegin + MainTabNew.hblack / 2 - 52, 0, false);
        mGraphics2.drawRegion(this.imgStarRebuild, 0, 0, 54, 105, 2, this.xBegin + MainTabNew.wblack / 2, this.yBegin + MainTabNew.hblack / 2 - 52, 0, false);
        if (this.typeRebuild == TYPE_ANY) {
            this.paintAny(mGraphics2);
        } else if (this.typeRebuild == TYPE_DUC_LO) {
            this.paintDucLo(mGraphics2);
        } else if (this.typeRebuild == TYPE_GHEP_NGOC) {
            this.paintGhepNgoc(mGraphics2);
        } else if (this.typeRebuild == TYPE_KHAM_NGOC) {
            this.paintKhamNgoc(mGraphics2);
        } else if (this.typeRebuild == TYPE_REBUILD) {
            this.paintRebuild(mGraphics2);
        } else if (this.typeRebuild == TYPE_REPLACE_PLUS) {
            this.paintReplace(mGraphics2);
        } else if (this.typeRebuild == TYPE_REBUILD_WING) {
            this.paintReWing(mGraphics2);
        }
        for (n = 0; n < vecEffRe.size(); ++n) {
            object = (MainEffect)vecEffRe.elementAt(n);
            ((MainEffect)object).paint(mGraphics2);
        }
        if (isShow || MainTabNew.longwidth > 0) {
            this.paintInfo(mGraphics2, this.xinfo, this.yinfo);
        }
        if (!(GameCanvas.menu2.isShowMenu || GameCanvas.currentDialog != null || GameCanvas.subDialog != null || MainTabNew.Focus != 1 && MainTabNew.longwidth <= 0 || this.vecListCmd == null)) {
            for (n = 0; n < this.vecListCmd.size(); ++n) {
                object = (iCommand)this.vecListCmd.elementAt(n);
                ((iCommand)object).paint(mGraphics2, ((iCommand)object).xCmd, ((iCommand)object).yCmd);
            }
        }
    }

    private void paintReWing(mGraphics mGraphics2) {
        for (int i = 0; i < this.posMaterial.length; ++i) {
            if (GameCanvas.lowGraphic) {
                MainTabNew.paintRectLowGraphic(mGraphics2, this.posMaterial[i][0] - 10, this.posMaterial[i][1] - 10, 20, 20, 2);
            } else {
                mGraphics2.drawRegion(MainTabNew.imgTab[2], 0, 0, 20, 20, 0, this.posMaterial[i][0], this.posMaterial[i][1], 3, false);
            }
            if (i == 6) {
                if (itemWing != null) {
                    itemWing.paintItem(mGraphics2, this.posMaterial[6][0], this.posMaterial[6][1], 21, 1, 0);
                    if (isBeginEff == 0) {
                        mFont.tahoma_7_white.drawString(mGraphics2, "Lv " + TabRebuildItem.itemWing.tier, this.posMaterial[6][0], this.posMaterial[6][1] - 22, 2, false);
                    }
                }
            } else if (dataWing != null && i < dataWing.length && isBeginEff == 0) {
                MainItem mainItem = Item.getMaterial(TabRebuildItem.dataWing[i].id);
                if (mainItem != null) {
                    mainItem.paintItem(mGraphics2, this.posMaterial[i][0], this.posMaterial[i][1], 21, 0, 0);
                    if (numWingMaterialInven[i] >= TabRebuildItem.dataWing[i].valueWing) {
                        mFont.tahoma_7_white.drawString(mGraphics2, "" + TabRebuildItem.dataWing[i].valueWing, this.posMaterial[i][0], this.posMaterial[i][1] + 11, 2, false);
                    } else {
                        mFont.tahoma_7_red.drawString(mGraphics2, "" + TabRebuildItem.dataWing[i].valueWing, this.posMaterial[i][0], this.posMaterial[i][1] + 11, 2, false);
                    }
                } else {
                    Item.put_Material(TabRebuildItem.dataWing[i].id);
                }
            }
            mGraphics2.drawImage(AvMain.imgHotKey, this.posMaterial[i][0], this.posMaterial[i][1], 3, false);
        }
    }

    public static void addGem(MainItem mainItem) {
        vecGem.addElement(mainItem);
    }

    public static void removeKhamNgoc(Item item) {
        for (int i = 0; i < vecGem.size(); ++i) {
            MainItem mainItem = (MainItem)vecGem.elementAt(i);
            if (mainItem == null || mainItem.Id != item.Id) continue;
            vecGem.removeElement(mainItem);
        }
    }

    public static boolean isKham(Item item) {
        for (int i = 0; i < vecGem.size(); ++i) {
            MainItem mainItem = (MainItem)vecGem.elementAt(i);
            if (mainItem == null || mainItem.Id != item.Id) continue;
            return true;
        }
        return false;
    }

    public void paintAny(mGraphics mGraphics2) {
        MainItem mainItem;
        int n;
        for (n = 0; n < this.posMaterial.length; ++n) {
            if (isBeginEff != 0 && n != 5) continue;
            if (GameCanvas.lowGraphic) {
                MainTabNew.paintRectLowGraphic(mGraphics2, this.posMaterial[n][0] - 10, this.posMaterial[n][1] - 10, 20, 20, 2);
            } else {
                mGraphics2.drawRegion(MainTabNew.imgTab[2], 0, 0, 20, 20, 0, this.posMaterial[n][0], this.posMaterial[n][1], 3, false);
            }
            if (itemRe != null && n == 5 && (isBeginEff != 5 || this.time > 15L)) {
                if (TabRebuildItem.itemRe.ItemCatagory == 7) {
                    mainItem = Item.getMaterial(TabRebuildItem.itemRe.Id);
                    if (mainItem != null) {
                        mainItem.paintItem(mGraphics2, this.posMaterial[n][0], this.posMaterial[n][1], 21, 1, 0);
                    } else {
                        Item.put_Material(TabRebuildItem.itemRe.Id);
                    }
                } else {
                    itemRe.paintItem(mGraphics2, this.posMaterial[n][0], this.posMaterial[n][1], 21, 1, 0);
                }
            }
            mGraphics2.drawImage(AvMain.imgHotKey, this.posMaterial[n][0], this.posMaterial[n][1], 3, false);
        }
        for (n = 0; n < vecGem.size(); ++n) {
            mainItem = (MainItem)vecGem.elementAt(n);
            if (mainItem == null) continue;
            MainItem mainItem2 = Item.getMaterial(mainItem.Id);
            if (mainItem2 != null) {
                mainItem2.paintItemNew(mGraphics2, this.posMaterial[n][0], this.posMaterial[n][1], 21, 0, 0);
            } else {
                Item.put_Material(mainItem.Id);
            }
            if (numofGem[n].equals("")) continue;
        }
    }

    public void paintGhepNgoc(mGraphics mGraphics2) {
        MainItem mainItem;
        int n;
        for (n = 0; n < this.posMaterial.length; ++n) {
            if (isBeginEff != 0 && n != 5) continue;
            if (GameCanvas.lowGraphic) {
                MainTabNew.paintRectLowGraphic(mGraphics2, this.posMaterial[n][0] - 10, this.posMaterial[n][1] - 10, 20, 20, 2);
            } else {
                mGraphics2.drawRegion(MainTabNew.imgTab[2], 0, 0, 20, 20, 0, this.posMaterial[n][0], this.posMaterial[n][1], 3, false);
            }
            if (itemRe != null && n == 5 && (isBeginEff != 5 || this.time > 15L)) {
                mainItem = Item.getMaterial(TabRebuildItem.itemRe.Id);
                if (mainItem != null) {
                    mainItem.paintItem(mGraphics2, this.posMaterial[n][0], this.posMaterial[n][1], 21, 1, 0);
                } else {
                    Item.put_Material(TabRebuildItem.itemRe.Id);
                }
            }
            mGraphics2.drawImage(AvMain.imgHotKey, this.posMaterial[n][0], this.posMaterial[n][1], 3, false);
        }
        for (n = 0; n < vecGem.size(); ++n) {
            mainItem = (MainItem)vecGem.elementAt(n);
            if (mainItem == null) continue;
            mainItem.paintItemNew(mGraphics2, this.posMaterial[n][0], this.posMaterial[n][1], 21, 0, 0);
        }
    }

    public void updateAny() {
        if (itemRe != null && countSetpaintinfo > 0) {
            this.setPaintInfo();
        }
        if (countSetpaintinfo > 0) {
            countSetpaintinfo = (byte)(countSetpaintinfo - 1);
        }
        if (isBeginEff == 1) {
            if (MainTabNew.longwidth == 0) {
                isShow = false;
            }
            GameCanvas.end_Dialog();
            if (!GameCanvas.isTouch) {
                this.left = null;
                this.center = null;
            } else {
                this.vecListCmd.removeAllElements();
            }
            if (itemRe != null) {
                vecEffRe.removeAllElements();
                int n = TabRebuildItem.itemRe.tier;
                if (n > 14) {
                    n = 14;
                }
                for (int i = 0; i < TabRebuildItem.dataRebuild[n].mValue.length; ++i) {
                    if (TabRebuildItem.dataRebuild[n].mValue[i] <= 0) continue;
                    TabRebuildItem.addEffectEndRebuild(31, this.posMaterial[i][0], this.posMaterial[i][1], this.posMaterial[5][0], this.posMaterial[5][1], 1);
                }
                if (isLucky) {
                    TabRebuildItem.addEffectEndRebuild(31, this.posMaterial[4][0], this.posMaterial[4][1], this.posMaterial[5][0], this.posMaterial[5][1], 1);
                }
            }
            isBeginEff = (byte)2;
            this.time = 0L;
        } else if (isBeginEff == 2) {
            if (this.time < 10L) {
                ++this.time;
                if (this.time == 10L) {
                    mSound.playSound(24, mSound.volumeSound);
                }
            } else {
                if (GameCanvas.timeNow - this.time > 12000L) {
                    this.time = GameCanvas.timeNow;
                    this.addEffectRebuild(29, this.posMaterial[5][0], this.posMaterial[5][1], 12100);
                }
                if (GameCanvas.timeNow - this.time > 3700L && (isNextRebuild == 3 || isNextRebuild == 4)) {
                    isBeginEff = isNextRebuild;
                    this.time = 0L;
                }
            }
        } else if (isBeginEff == 3) {
            mSound.playSound(26, mSound.volumeSound);
            vecEffRe.removeAllElements();
            TabRebuildItem.addEffectEnd_ReBuild_ss(32, this.posMaterial[5][0], this.posMaterial[5][1] - 11);
            TabRebuildItem.addEffectEnd_ReBuild_ss(33, this.posMaterial[5][0], this.posMaterial[5][1]);
            TabRebuildItem.addEffectEnd_ReBuild_ss(34, this.posMaterial[5][0], this.posMaterial[5][1]);
            isBeginEff = (byte)5;
            this.time = 0L;
        } else if (isBeginEff == 4) {
            mSound.playSound(27, mSound.volumeSound);
            vecEffRe.removeAllElements();
            TabRebuildItem.addEffectEnd_ReBuild_ss(11, this.posMaterial[5][0], this.posMaterial[5][1]);
            this.addEffectRebuild(69, this.posMaterial[5][0], this.posMaterial[5][1] - 11, 300);
            this.addEffectRebuild(69, this.posMaterial[5][0], this.posMaterial[5][1] - 11, 300);
            isBeginEff = (byte)5;
            this.time = 0L;
        } else if (isBeginEff == 5) {
            ++this.time;
            if (vecEffRe.size() == 0 || this.time >= 100L) {
                GameCanvas.menu2.startAt_NPC(null, contentShow, Menu2.IdNPCLast, (byte)2, false, 0);
                this.time = 0L;
                isBeginEff = (byte)6;
                this.ispaintitemRe = true;
            }
        } else if (isBeginEff == 6) {
            if (TabRebuildItem.itemRe.tier == 15) {
                itemRe = null;
            }
            if (this.isTabHopNguyenLieu && itemRe != null) {
                itemRe = null;
            }
            isBeginEff = 0;
            if (!GameCanvas.isTouch) {
                this.left = this.cmdView;
                this.center = this.cmdHoiDap;
                MainTabNew.Focus = 0;
            } else {
                this.vecListCmd.removeAllElements();
                if (itemRe != null) {
                    if (MainTabNew.longwidth > 0) {
                        this.vecListCmd.addElement(this.cmdHoiDap);
                    } else {
                        this.vecListCmd.addElement(this.cmdView);
                        this.vecListCmd.addElement(this.cmdHoiDap);
                    }
                }
            }
        }
    }

    public void updateGhepngoc() {
        if (isBeginEff == 1) {
            if (MainTabNew.longwidth == 0) {
                isShow = false;
            }
            GameCanvas.end_Dialog();
            if (!GameCanvas.isTouch) {
                this.left = null;
                this.center = null;
            } else {
                this.vecListCmd.removeAllElements();
            }
            if (itemRe != null) {
                vecEffRe.removeAllElements();
                for (int i = 0; i < TabRebuildItem.dataRebuild[TabRebuildItem.itemRe.tier].mValue.length; ++i) {
                    if (TabRebuildItem.dataRebuild[TabRebuildItem.itemRe.tier].mValue[i] <= 0) continue;
                    TabRebuildItem.addEffectEndRebuild(31, this.posMaterial[i][0], this.posMaterial[i][1], this.posMaterial[5][0], this.posMaterial[5][1], 1);
                }
                if (isLucky) {
                    TabRebuildItem.addEffectEndRebuild(31, this.posMaterial[4][0], this.posMaterial[4][1], this.posMaterial[5][0], this.posMaterial[5][1], 1);
                }
            }
            isBeginEff = (byte)2;
            this.time = 0L;
        } else if (isBeginEff == 2) {
            if (this.time < 10L) {
                ++this.time;
                if (this.time == 10L) {
                    mSound.playSound(24, mSound.volumeSound);
                }
            } else {
                if (GameCanvas.timeNow - this.time > 12000L) {
                    this.time = GameCanvas.timeNow;
                    this.addEffectRebuild(29, this.posMaterial[5][0], this.posMaterial[5][1], 12100);
                }
                if (GameCanvas.timeNow - this.time > 3700L && (isNextRebuild == 3 || isNextRebuild == 4)) {
                    isBeginEff = isNextRebuild;
                    this.time = 0L;
                }
            }
        } else if (isBeginEff == 3) {
            mSound.playSound(26, mSound.volumeSound);
            vecEffRe.removeAllElements();
            TabRebuildItem.addEffectEnd_ReBuild_ss(32, this.posMaterial[5][0], this.posMaterial[5][1] - 11);
            TabRebuildItem.addEffectEnd_ReBuild_ss(33, this.posMaterial[5][0], this.posMaterial[5][1]);
            TabRebuildItem.addEffectEnd_ReBuild_ss(34, this.posMaterial[5][0], this.posMaterial[5][1]);
            isBeginEff = (byte)5;
            this.time = 0L;
        } else if (isBeginEff == 5) {
            ++this.time;
            if (vecEffRe.size() == 0 || this.time >= 100L) {
                GameCanvas.menu2.startAt_NPC(null, contentShow, Menu2.IdNPCLast, (byte)2, false, 0);
                this.time = 0L;
                isBeginEff = (byte)6;
                this.ispaintitemRe = true;
            }
        } else if (isBeginEff == 6) {
            if (TabRebuildItem.itemRe.tier == 15) {
                itemRe = null;
            }
            if (this.isTabHopNguyenLieu && itemRe != null) {
                itemRe = null;
            }
            isBeginEff = 0;
            if (!GameCanvas.isTouch) {
                this.left = this.cmdView;
                this.center = this.cmdHoiDap;
                MainTabNew.Focus = 0;
            } else {
                this.vecListCmd.removeAllElements();
                if (itemRe != null) {
                    if (MainTabNew.longwidth > 0) {
                        this.vecListCmd.addElement(this.cmdHoiDap);
                    } else {
                        this.vecListCmd.addElement(this.cmdView);
                        this.vecListCmd.addElement(this.cmdHoiDap);
                    }
                }
            }
        }
    }

    public void paintKhamNgoc(mGraphics mGraphics2) {
        int n;
        for (n = 0; n < this.posMaterial.length; ++n) {
            if (isBeginEff != 0 && n != 5) continue;
            if (GameCanvas.lowGraphic) {
                MainTabNew.paintRectLowGraphic(mGraphics2, this.posMaterial[n][0] - 10, this.posMaterial[n][1] - 10, 20, 20, 2);
            } else {
                mGraphics2.drawRegion(MainTabNew.imgTab[2], 0, 0, 20, 20, 0, this.posMaterial[n][0], this.posMaterial[n][1], 3, false);
            }
            if (itemRe != null && n == 5 && (isBeginEff != 5 || this.time > 15L)) {
                itemRe.paintItem(mGraphics2, this.posMaterial[n][0], this.posMaterial[n][1], 21, 1, 0);
            }
            mGraphics2.drawImage(AvMain.imgHotKey, this.posMaterial[n][0], this.posMaterial[n][1], 3, false);
        }
        for (n = 0; n < vecGem.size(); ++n) {
            MainItem mainItem = (MainItem)vecGem.elementAt(n);
            if (mainItem == null) continue;
            mainItem.paintItemNew(mGraphics2, this.posMaterial[n][0], this.posMaterial[n][1], 21, 0, 0);
        }
    }

    public void paintDucLo(mGraphics mGraphics2) {
        int n;
        for (n = 0; n < this.posMaterial.length; ++n) {
            if (isBeginEff != 0 && n != 5) continue;
            if (GameCanvas.lowGraphic) {
                MainTabNew.paintRectLowGraphic(mGraphics2, this.posMaterial[n][0] - 10, this.posMaterial[n][1] - 10, 20, 20, 2);
            } else {
                mGraphics2.drawRegion(MainTabNew.imgTab[2], 0, 0, 20, 20, 0, this.posMaterial[n][0], this.posMaterial[n][1], 3, false);
            }
            if (itemRe != null && n == 5 && (isBeginEff != 5 || this.time > 15L)) {
                itemRe.paintItem(mGraphics2, this.posMaterial[n][0], this.posMaterial[n][1], 21, 1, 0);
            }
            mGraphics2.drawImage(AvMain.imgHotKey, this.posMaterial[n][0], this.posMaterial[n][1], 3, false);
        }
        for (n = 0; n < vecGem.size(); ++n) {
            MainItem mainItem = (MainItem)vecGem.elementAt(n);
            if (mainItem == null) continue;
            mainItem.paintItemNew(mGraphics2, this.posMaterial[n][0], this.posMaterial[n][1], 21, 0, 0);
        }
    }

    public void paintRebuild(mGraphics mGraphics2) {
        for (int i = 0; i < this.posMaterial.length; ++i) {
            if (isBeginEff != 0 && i != 5) continue;
            if (GameCanvas.lowGraphic) {
                MainTabNew.paintRectLowGraphic(mGraphics2, this.posMaterial[i][0] - 10, this.posMaterial[i][1] - 10, 20, 20, 2);
            } else {
                mGraphics2.drawRegion(MainTabNew.imgTab[2], 0, 0, 20, 20, 0, this.posMaterial[i][0], this.posMaterial[i][1], 3, false);
            }
            if (itemRe != null) {
                MainItem mainItem;
                if (this.isTabHopNguyenLieu) {
                    if (i < 5) {
                        itemRe.paintItem(mGraphics2, this.posMaterial[i][0], this.posMaterial[i][1], 21, 1, 0);
                    } else if (TabRebuildItem.itemRe.itemClone != null) {
                        mainItem = Item.getMaterial(TabRebuildItem.itemRe.itemClone.Id);
                        if (mainItem != null) {
                            mainItem.paintItem(mGraphics2, this.posMaterial[i][0], this.posMaterial[i][1], 21, 1, 0);
                        } else {
                            Item.put_Material(TabRebuildItem.itemRe.itemClone.Id);
                        }
                    }
                } else if (i < 5) {
                    mainItem = Item.getMaterial(idMaterial[i]);
                    if (mainItem != null) {
                        if (mainItem.typeMaterial == 11 && isLucky || i < 4 && TabRebuildItem.dataRebuild[TabRebuildItem.itemRe.tier].mValue[i] > 0) {
                            if (mainItem.typeMaterial == 11 && isLucky && itemDaMayMan != null) {
                                itemDaMayMan.paintItem_notnum(mGraphics2, this.posMaterial[i][0], this.posMaterial[i][1], 21, 0, 0);
                            } else {
                                mainItem.paintItem_notnum(mGraphics2, this.posMaterial[i][0], this.posMaterial[i][1], 21, 0, 0);
                            }
                        }
                    } else {
                        Item.put_Material(idMaterial[i]);
                    }
                } else if (i == 5) {
                    if (isBeginEff != 5 || this.time > 15L) {
                        itemRe.paintItem(mGraphics2, this.posMaterial[i][0], this.posMaterial[i][1], 21, 1, 0);
                    }
                    if (isBeginEff == 0) {
                        mFont.tahoma_7_white.drawString(mGraphics2, "Lv " + TabRebuildItem.itemRe.tier, this.posMaterial[i][0], this.posMaterial[i][1] - 22, 2, false);
                    }
                }
            }
            mGraphics2.drawImage(AvMain.imgHotKey, this.posMaterial[i][0], this.posMaterial[i][1], 3, false);
            if (itemRe == null) continue;
            if (i < 4 && TabRebuildItem.dataRebuild[TabRebuildItem.itemRe.tier].mValue[i] > numMaterialInven[i]) {
                mGraphics2.drawImage(AvMain.imgHotKey2, this.posMaterial[i][0], this.posMaterial[i][1], 3, false);
                continue;
            }
            if (i != 5 || isBeginEff != 5 || isNextRebuild != 4) continue;
            mGraphics2.drawImage(AvMain.imgHotKey2, this.posMaterial[i][0], this.posMaterial[i][1], 3, false);
        }
    }

    public void paintRect(mGraphics mGraphics2) {
        for (int i = 0; i <= this.maxw; ++i) {
            for (int j = 0; j <= this.maxh; ++j) {
                this.indexPaint = 12;
                if (j == 0) {
                    this.indexPaint = 12;
                }
                if (i == this.maxw) {
                    if (j == this.maxh) {
                        mGraphics2.drawImage(MainTabNew.imgTab[this.indexPaint], this.xBegin + 4 + (MainTabNew.wblack - 8) - 32, this.yBegin + 4 + MainTabNew.hblack - 8 - 32, 0, false);
                        continue;
                    }
                    mGraphics2.drawImage(MainTabNew.imgTab[this.indexPaint], this.xBegin + 4 + (MainTabNew.wblack - 8) - 32, this.yBegin + 4 + j * 32, 0, false);
                    continue;
                }
                if (j == this.maxh) {
                    mGraphics2.drawImage(MainTabNew.imgTab[this.indexPaint], this.xBegin + 4 + i * 32, this.yBegin + 4 + MainTabNew.hblack - 8 - 32, 0, false);
                    continue;
                }
                mGraphics2.drawImage(MainTabNew.imgTab[this.indexPaint], this.xBegin + 4 + i * 32, this.yBegin + 4 + j * 32, 0, false);
            }
        }
    }

    public void paintInfo(mGraphics mGraphics2, int n, int n2) {
        if (this.typeRebuild == TYPE_ANY) {
            this.paintinfoAny(mGraphics2, n, n2);
            if (itemRe == null && this.isCreate_medal || itemRe != null && this.isUPgradeMedal) {
                this.paintconTenAny(mGraphics2, n, n2 - 10);
            }
        } else if (this.typeRebuild == TYPE_GHEP_NGOC) {
            this.paintinfoGhepNgoc(mGraphics2, n, n2);
        } else if (this.typeRebuild == TYPE_KHAM_NGOC || this.typeRebuild == TYPE_DUC_LO) {
            this.paintinfoKhamNgoc(mGraphics2, n, n2);
        } else if (this.typeRebuild == TYPE_REBUILD) {
            this.paintInfoRebuild(mGraphics2, n, n2);
        } else if (this.typeRebuild == TYPE_REPLACE_PLUS) {
            if (!resetItemReplace) {
                this.paintInfoReplace(mGraphics2, n, n2);
            }
        } else if (this.typeRebuild == TYPE_REBUILD_WING) {
            this.paintInfoReWing(mGraphics2, n, n2);
        }
    }

    private void paintInfoReWing(mGraphics mGraphics2, int n, int n2) {
        if (dataWing == null) {
            return;
        }
        int n3 = GameCanvas.hText;
        if (MainTabNew.longwidth > 0) {
            MainTabNew.paintNameItem(mGraphics2, n + MainTabNew.longwidth / 2, n2 - MainTabNew.wOneItem / 4, MainTabNew.longwidth, nameWing, 5);
            n2 += MainTabNew.wOneItem - GameCanvas.hText + GameCanvas.hText / 4;
            n += 4;
        } else {
            this.paintFormList(mGraphics2, n, n2, this.winfo, 150, nameWing);
            n2 += GameCanvas.hCommand + 2;
            n += 4;
        }
        mFont.tahoma_7_white.drawString(mGraphics2, T.LVyeucau + lvReWing, n, n2, 0, false);
        mFont.tahoma_7_white.drawString(mGraphics2, T.timeyeucau + PaintInfoGameScreen.getStringTime(timeUseWing), n, n2 += n3, 0, false);
        n2 += n3;
        for (int i = 0; i < dataWing.length; ++i) {
            MainItem mainItem = Item.getMaterial(TabRebuildItem.dataWing[i].id);
            if (mainItem != null) {
                mFont mFont2 = mFont.tahoma_7_white;
                if (TabRebuildItem.dataWing[i].valueWing > numWingMaterialInven[i]) {
                    mFont2 = mFont.tahoma_7_red;
                }
                if (mainItem.itemName != null) {
                    mFont2.drawString(mGraphics2, mainItem.itemName, n + 4, n2, 0, false);
                }
                mFont2.drawString(mGraphics2, TabRebuildItem.dataWing[i].valueWing + "/" + numWingMaterialInven[i], n + this.winfo / 2 + 10, n2, 0, false);
                n2 += n3;
                continue;
            }
            Item.put_Material(TabRebuildItem.dataWing[i].id);
        }
    }

    private void paintInfoReplace(mGraphics mGraphics2, int n, int n2) {
        if (itemPlus == null || itemFree == null) {
            return;
        }
        int n3 = GameCanvas.hText;
        if (MainTabNew.longwidth > 0) {
            mFont.tahoma_7b_white.drawString(mGraphics2, T.replace, n + MainTabNew.longwidth / 2, n2 - MainTabNew.wOneItem / 4, 2, false);
            n2 += MainTabNew.wOneItem - GameCanvas.hText + GameCanvas.hText / 4;
            n += 4;
        } else {
            this.paintFormList(mGraphics2, n, n2, this.winfo, 100, T.replace);
            n2 += GameCanvas.hCommand + 2;
            n += 4;
        }
        MainTabNew.setTextColor(TabRebuildItem.itemPlus.colorNameItem).drawString(mGraphics2, TabRebuildItem.itemPlus.itemName, n, n2, 0, false);
        MainTabNew.setTextColor(TabRebuildItem.itemFree.colorNameItem).drawString(mGraphics2, TabRebuildItem.itemFree.itemName, n, n2 += n3, 0, false);
        mFont.tahoma_7_white.drawString(mGraphics2, T.plusnhanduoc, n, n2 += n3, 0, false);
        mFont.tahoma_7b_white.drawString(mGraphics2, TabRebuildItem.itemPlus.tier - 2 + " > " + TabRebuildItem.itemPlus.tier, n, n2 += n3, 0, false);
    }

    public void paintinfoKhamNgoc(mGraphics mGraphics2, int n, int n2) {
        if (itemRe == null) {
            return;
        }
        this.paintContentNew(mGraphics2, false);
    }

    public void paintinfoAny(mGraphics mGraphics2, int n, int n2) {
        if (itemRe == null) {
            return;
        }
        if (MainTabNew.longwidth > 0 && TabRebuildItem.itemRe.itemName != null) {
            MainTabNew.paintNameItem(mGraphics2, n + MainTabNew.longwidth / 2, n2 - MainTabNew.wOneItem / 4, MainTabNew.longwidth, TabRebuildItem.itemRe.itemName, TabRebuildItem.itemRe.colorNameItem);
            n2 += MainTabNew.wOneItem - GameCanvas.hText + GameCanvas.hText / 4;
            n += 4;
        } else {
            if (TabRebuildItem.itemRe.itemName != null) {
                this.paintFormList(mGraphics2, n, n2, this.winfo, 150, TabRebuildItem.itemRe.itemName);
            }
            n2 += GameCanvas.hCommand + 2;
            n += 4;
        }
    }

    public void paintconTenAny(mGraphics mGraphics2, int n, int n2) {
        int n3 = GameCanvas.hText - 2;
        if (MainTabNew.longwidth > 0) {
            n2 += MainTabNew.wOneItem - GameCanvas.hText + GameCanvas.hText / 4;
            n += 4;
        } else {
            String string = T.info;
            if (itemRe != null && TabRebuildItem.itemRe.itemName != null) {
                string = TabRebuildItem.itemRe.itemName;
            }
            this.paintFormList(mGraphics2, n, n2, this.winfo, 150, string);
            n2 += GameCanvas.hCommand + 2;
            n += 4;
        }
        n2 += n3;
        int n4 = 0;
        if (!GameCanvas.isTouch) {
            n4 = 20;
        }
        GameCanvas.resetTrans(mGraphics2);
        if (GameCanvas.isTouch) {
            this.scr.setStyle(vecGem.size() + 2, GameCanvas.hText + 2, n, n2 - GameCanvas.hText * 2, this.winfo, n3 * (vecGem.size() + 2) * 2, true, 1);
            this.scr.setClip(mGraphics2, n, n2, this.winfo, n3 * (vecGem.size() + 2) * 2);
        }
        mFont.tahoma_7b_white.drawString(mGraphics2, T.nguyenlieu, n, n2 - n4, 0, false);
        n2 += n3;
        for (int i = 0; i < vecGem.size(); ++i) {
            MainItem mainItem = (MainItem)vecGem.elementAt(i);
            if (mainItem == null) continue;
            MainItem mainItem2 = Item.getMaterial(mainItem.Id);
            if (mainItem2 != null) {
                mFont mFont2 = mFont.tahoma_7_green;
                if (mainItem2.itemName != null && !mainItem2.itemName.equals("")) {
                    MainTabNew.setTextColorName(mainItem2.colorNameItem).drawString(mGraphics2, mainItem2.itemName, n + 4, n2 - n4, 0, true);
                }
                if (numColor[i] == 0) {
                    mFont2 = mFont.tahoma_7_red;
                }
                mFont.tahoma_7_white.drawString(mGraphics2, T.soluong + " ", n + 4, (n2 += n3) - n4, 0, true);
                mFont2.drawString(mGraphics2, numofGem[i], n + 50, n2 - n4, 0, true);
                n2 += n3;
                continue;
            }
            Item.put_Material(mainItem.Id);
        }
        GameCanvas.resetTrans(mGraphics2);
    }

    public void paintinfoGhepNgoc(mGraphics mGraphics2, int n, int n2) {
        if (itemRe == null) {
            return;
        }
        if (MainTabNew.longwidth > 0 && TabRebuildItem.itemRe.itemName != null) {
            MainTabNew.paintNameItem(mGraphics2, n + MainTabNew.longwidth / 2, n2 - MainTabNew.wOneItem / 4, MainTabNew.longwidth, TabRebuildItem.itemRe.itemName, TabRebuildItem.itemRe.colorNameItem);
            n2 += MainTabNew.wOneItem - GameCanvas.hText + GameCanvas.hText / 4;
            n += 4;
        } else {
            if (TabRebuildItem.itemRe.itemName != null) {
                this.paintFormList(mGraphics2, n, n2, this.winfo, 150, TabRebuildItem.itemRe.itemName);
            }
            n2 += GameCanvas.hCommand + 2;
            n += 4;
        }
    }

    public void paintInfoRebuild(mGraphics mGraphics2, int n, int n2) {
        if (itemRe == null) {
            return;
        }
        int n3 = GameCanvas.hText - 2;
        if (MainTabNew.longwidth > 0) {
            if (TabRebuildItem.itemRe.itemName != null) {
                MainTabNew.paintNameItem(mGraphics2, n + MainTabNew.longwidth / 2, n2 - MainTabNew.wOneItem / 4, MainTabNew.longwidth, TabRebuildItem.itemRe.itemName, TabRebuildItem.itemRe.colorNameItem);
            }
            if (TabRebuildItem.itemRe.tier >= 15) {
                return;
            }
            n2 += MainTabNew.wOneItem - GameCanvas.hText + GameCanvas.hText / 4;
            n += 4;
        } else {
            this.paintFormList(mGraphics2, n, n2, this.winfo, 150, TabRebuildItem.itemRe.itemName);
            n2 += GameCanvas.hCommand + 2;
            n += 4;
        }
        if (this.isTabHopNguyenLieu) {
            if (TabRebuildItem.itemRe.itemClone == null) {
                return;
            }
            String[] stringArray = null;
            int n4 = 0;
            int n5 = mFont.tahoma_7b_blue.getWidth(TabRebuildItem.itemRe.infoHop[0]);
            stringArray = mFont.tahoma_7b_blue.splitFontArray(TabRebuildItem.itemRe.infoHop[0], MainTabNew.longwidth == 0 ? (this.maxw - 1) * 32 : MainTabNew.longwidth);
            n4 = stringArray.length;
            for (int i = 0; i < n4; ++i) {
                mFont.tahoma_7b_blue.drawString(mGraphics2, stringArray[i], n, n2, 0, false);
                n2 += n3;
            }
            mFont.tahoma_7b_white.drawString(mGraphics2, T.chiphi, n, n2, 0, false);
            mFont.tahoma_7_white.drawString(mGraphics2, TabRebuildItem.itemRe.infoHop[1], n + 4, n2 += n3 - 2, 0, false);
            return;
        }
        if (TabRebuildItem.itemRe.tier < 15) {
            mFont.tahoma_7b_blue.drawString(mGraphics2, "+" + TabRebuildItem.itemRe.tier + " > +" + (TabRebuildItem.itemRe.tier + 1), n, n2, 0, false);
        }
        mFont.tahoma_7b_white.drawString(mGraphics2, T.nguyenlieu, n, n2 += n3, 0, false);
        n2 += n3;
        for (int i = 0; i < TabRebuildItem.dataRebuild[TabRebuildItem.itemRe.tier].mValue.length; ++i) {
            MainItem mainItem;
            mFont mFont2 = mFont.tahoma_7_white;
            if (TabRebuildItem.dataRebuild[TabRebuildItem.itemRe.tier].mValue[i] > numMaterialInven[i]) {
                mFont2 = mFont.tahoma_7_red;
            }
            if ((mainItem = Item.getMaterial(idMaterial[i])) != null) {
                TabRebuildItem.mNameMaterial[i] = mainItem.itemName;
            } else {
                Item.put_Material(idMaterial[i]);
            }
            if (mNameMaterial[i] != null) {
                mFont2.drawString(mGraphics2, mNameMaterial[i], n + 4, n2, 0, false);
            }
            mFont2.drawString(mGraphics2, TabRebuildItem.dataRebuild[TabRebuildItem.itemRe.tier].mValue[i] + "/" + numMaterialInven[i], n + this.winfo / 2 + 10, n2, 0, false);
            n2 += n3;
        }
        mFont.tahoma_7b_white.drawString(mGraphics2, T.tilemayman, n, n2, 0, false);
        mFont.tahoma_7_white.drawString(mGraphics2, tilemayman, n + 4, n2 += n3, 0, false);
        mFont.tahoma_7b_white.drawString(mGraphics2, T.chiphi, n, n2 += n3, 0, false);
        this.paintPrice(mGraphics2, n, n2 += n3 - 2);
    }

    public void paintPrice(mGraphics mGraphics2, int n, int n2) {
        if (TabRebuildItem.dataRebuild[TabRebuildItem.itemRe.tier].priceCoin == 0) {
            mFont.tahoma_7_white.drawString(mGraphics2, TabRebuildItem.dataRebuild[TabRebuildItem.itemRe.tier].priceGold + " " + T.gem, n + 4, n2, 0, false);
            return;
        }
        if (TabRebuildItem.dataRebuild[TabRebuildItem.itemRe.tier].priceGold == 0) {
            mFont.tahoma_7_white.drawString(mGraphics2, TabRebuildItem.dataRebuild[TabRebuildItem.itemRe.tier].priceCoin + " " + T.coin, n + 4, n2, 0, false);
            return;
        }
        mFont.tahoma_7_white.drawString(mGraphics2, TabRebuildItem.dataRebuild[TabRebuildItem.itemRe.tier].priceCoin + " " + T.coin + T.hoac + TabRebuildItem.dataRebuild[TabRebuildItem.itemRe.tier].priceGold + " " + T.gem, n + 4, n2, 0, false);
    }

    public void paintReplace(mGraphics mGraphics2) {
        for (int i = 0; i < this.posMaterial.length; ++i) {
            if (GameCanvas.lowGraphic) {
                MainTabNew.paintRectLowGraphic(mGraphics2, this.posMaterial[i][0] - 10, this.posMaterial[i][1] - 10, 20, 20, 2);
            } else {
                mGraphics2.drawRegion(MainTabNew.imgTab[2], 0, 0, 20, 20, 0, this.posMaterial[i][0], this.posMaterial[i][1], 3, false);
            }
            if (itemPlus != null && i == 0) {
                itemPlus.paintItem(mGraphics2, this.posMaterial[0][0], this.posMaterial[0][1], 21, 1, 0);
                if (isBeginEff == 0) {
                    mFont.tahoma_7_white.drawString(mGraphics2, "Lv " + TabRebuildItem.itemPlus.tier, this.posMaterial[0][0], this.posMaterial[0][1] - 22, 2, false);
                }
            }
            if (itemFree != null && i == 1) {
                itemFree.paintItem(mGraphics2, this.posMaterial[1][0], this.posMaterial[1][1], 21, 1, 0);
                if (isBeginEff == 0) {
                    mFont.tahoma_7_white.drawString(mGraphics2, "Lv " + TabRebuildItem.itemFree.tier, this.posMaterial[1][0], this.posMaterial[1][1] - 22, 2, false);
                }
            }
            mGraphics2.drawImage(AvMain.imgHotKey, this.posMaterial[i][0], this.posMaterial[i][1], 3, false);
        }
    }

    public void updatekey() {
        if (isBeginEff != 0) {
            return;
        }
        if (GameCanvas.keyMyHold[4] || GameCanvas.keyMyHold[6]) {
            MainTabNew.Focus = 0;
            GameCanvas.clearKeyHold();
        }
        super.updatekey();
    }

    public void update() {
        if (this.typeRebuild == TYPE_REBUILD) {
            this.updateRebuild();
            if (this.isTabHopNguyenLieu) {
                this.cmdHoiDap.caption = T.hopThanh;
                this.cmdHoiDap.indexMenu = (byte)7;
            } else {
                this.cmdHoiDap.caption = T.dapdo;
                this.cmdHoiDap.indexMenu = 0;
            }
        } else if (this.typeRebuild == TYPE_REPLACE_PLUS) {
            this.updateReplace();
        } else if (this.typeRebuild == TYPE_REBUILD_WING) {
            this.updateReWing();
        } else if (this.typeRebuild == TYPE_KHAM_NGOC) {
            this.updateKhamNgoc();
            this.cmdHoiDap.caption = T.khamNgoc;
            this.cmdHoiDap.indexMenu = 0;
        } else if (this.typeRebuild == TYPE_GHEP_NGOC) {
            this.updateGhepngoc();
            this.cmdHoiDap.caption = T.hopngoc;
            this.cmdHoiDap.indexMenu = 0;
        } else if (this.typeRebuild == TYPE_DUC_LO) {
            this.updateDucLo();
            this.cmdHoiDap.caption = T.DucLo;
            this.cmdHoiDap.indexMenu = 0;
        } else if (this.typeRebuild == TYPE_ANY) {
            this.updateAny();
            ScrollResult scrollResult = this.scr.updateKey();
            if (scrollResult.isDowning || scrollResult.isFinish) {
                // empty if block
            }
            if (scrollResult.isFinish || GameCanvas.isKeyPressed(5)) {
                // empty if block
            }
            this.scr.updatecm();
            this.cmdHoiDap.caption = T.hoprac;
            this.cmdHoiDap.indexMenu = 0;
        }
        for (int i = 0; i < vecEffRe.size(); ++i) {
            MainEffect mainEffect = (MainEffect)vecEffRe.elementAt(i);
            mainEffect.update();
            if (!mainEffect.isStop) continue;
            vecEffRe.removeElement(mainEffect);
        }
    }

    private void updateReWing() {
        if (isBeginEff == 1) {
            if (MainTabNew.longwidth == 0) {
                isShow = false;
            }
            GameCanvas.end_Dialog();
            if (!GameCanvas.isTouch) {
                this.left = null;
                this.center = null;
            } else {
                this.vecListCmd.removeAllElements();
            }
            if (dataWing != null) {
                vecEffRe.removeAllElements();
                for (int i = 0; i < dataWing.length; ++i) {
                    TabRebuildItem.addEffectEndRebuild(31, this.posMaterial[i][0], this.posMaterial[i][1], this.posMaterial[6][0], this.posMaterial[6][1], 1);
                }
            }
            isBeginEff = (byte)2;
            this.time = 0L;
        } else if (isBeginEff == 2) {
            if (this.time < 10L) {
                ++this.time;
                if (this.time == 10L) {
                    mSound.playSound(24, mSound.volumeSound);
                }
            } else {
                if (GameCanvas.timeNow - this.time > 12000L) {
                    this.time = GameCanvas.timeNow;
                    this.addEffectRebuild(29, this.posMaterial[6][0], this.posMaterial[6][1], 12100);
                }
                if (GameCanvas.timeNow - this.time > 3700L) {
                    isBeginEff = (byte)3;
                    this.time = 0L;
                }
            }
        } else if (isBeginEff == 3) {
            mSound.playSound(26, mSound.volumeSound);
            vecEffRe.removeAllElements();
            if (itemWing != null) {
                TabRebuildItem.addEffectEnd_ReBuild_ss(32, this.posMaterial[6][0], this.posMaterial[6][1] - 11);
            }
            TabRebuildItem.addEffectEnd_ReBuild_ss(33, this.posMaterial[6][0], this.posMaterial[6][1]);
            TabRebuildItem.addEffectEnd_ReBuild_ss(34, this.posMaterial[6][0], this.posMaterial[6][1]);
            isBeginEff = (byte)5;
            this.time = 0L;
        } else if (isBeginEff == 5) {
            ++this.time;
            MainItem mainItem = (MainItem)Item.getItemInventory(3, idWingOk);
            itemWing = mainItem != null ? MainItem.MainItem_Item(mainItem.Id, mainItem.itemNameExcludeLv, mainItem.imageId, mainItem.tier, mainItem.colorNameItem, mainItem.classcharItem, mainItem.ItemCatagory, mainItem.mInfo, mainItem.type_Only_Item, false, mainItem.IdTem, mainItem.priceItem, (short)mainItem.LvItem, mainItem.canSell, mainItem.canTrade, mainItem.timeUse, (byte)0, (byte)0) : null;
            if (vecEffRe.size() == 0 || this.time >= 100L) {
                GameCanvas.menu2.startAt_NPC(null, contentShow, Menu2.IdNPCLast, (byte)2, false, 0);
                this.time = 0L;
                isBeginEff = (byte)6;
            }
        } else if (isBeginEff == 6) {
            dataWing = null;
            resetItemReplace = true;
            isBeginEff = 0;
            if (!GameCanvas.isTouch) {
                this.left = null;
                this.center = null;
                MainTabNew.Focus = 0;
            } else {
                this.vecListCmd.removeAllElements();
            }
        }
    }

    private void updateReplace() {
        if (isBeginEff == 1) {
            if (itemPlus == null || itemFree == null) {
                isBeginEff = 0;
                return;
            }
            if (MainTabNew.longwidth == 0) {
                isShow = false;
            }
            GameCanvas.end_Dialog();
            if (!GameCanvas.isTouch) {
                this.left = null;
                this.center = null;
            } else {
                this.vecListCmd.removeAllElements();
            }
            vecEffRe.removeAllElements();
            TabRebuildItem.addEffectEndRebuild(37, this.posMaterial[0][0], this.posMaterial[0][1], this.posMaterial[1][0], this.posMaterial[1][1], TabRebuildItem.itemPlus.tier);
            this.timeShowReplace = TabRebuildItem.itemPlus.tier * 120;
            isBeginEff = (byte)2;
            this.time = 0L;
        } else if (isBeginEff == 2) {
            if (this.time < 10L) {
                ++this.time;
                if (this.time == 10L) {
                    mSound.playSound(24, mSound.volumeSound);
                }
            } else {
                if (this.time == 10L) {
                    this.time = GameCanvas.timeNow;
                    this.addEffectRebuild(29, this.posMaterial[1][0], this.posMaterial[1][1], 1200 + this.timeShowReplace);
                }
                if (GameCanvas.timeNow - this.time > (long)(2000 + this.timeShowReplace)) {
                    vecEffRe.removeAllElements();
                    isBeginEff = (byte)3;
                    this.time = 0L;
                }
            }
        } else if (isBeginEff == 3) {
            MainItem mainItem;
            mSound.playSound(26, mSound.volumeSound);
            GameCanvas.menu2.startAt_NPC(null, contentShow, Menu2.IdNPCLast, (byte)2, false, 0);
            if (itemPlus != null) {
                mainItem = (MainItem)Item.getItemInventory(TabRebuildItem.itemPlus.ItemCatagory, (short)TabRebuildItem.itemPlus.Id);
                itemPlus = mainItem != null ? MainItem.MainItem_Item(mainItem.Id, mainItem.itemNameExcludeLv, mainItem.imageId, mainItem.tier, mainItem.colorNameItem, mainItem.classcharItem, mainItem.ItemCatagory, mainItem.mInfo, mainItem.type_Only_Item, false, mainItem.IdTem, mainItem.priceItem, (short)mainItem.LvItem, mainItem.canSell, mainItem.canTrade, mainItem.timeUse, (byte)0, (byte)0) : null;
            }
            if (itemFree != null) {
                mainItem = (MainItem)Item.getItemInventory(TabRebuildItem.itemFree.ItemCatagory, (short)TabRebuildItem.itemFree.Id);
                itemFree = mainItem != null ? MainItem.MainItem_Item(mainItem.Id, mainItem.itemNameExcludeLv, mainItem.imageId, mainItem.tier, mainItem.colorNameItem, mainItem.classcharItem, mainItem.ItemCatagory, mainItem.mInfo, mainItem.type_Only_Item, false, mainItem.IdTem, mainItem.priceItem, (short)mainItem.LvItem, mainItem.canSell, mainItem.canTrade, mainItem.timeUse, (byte)0, (byte)0) : null;
            }
            isBeginEff = 0;
            resetItemReplace = true;
            this.vecListCmd.removeAllElements();
            isShow = false;
            this.center = null;
            this.left = null;
        }
    }

    public void updateKhamNgoc() {
        if (itemRe != null && countSetpaintinfo > 0) {
            this.setPaintInfo();
        }
        if (countSetpaintinfo > 0) {
            countSetpaintinfo = (byte)(countSetpaintinfo - 1);
        }
        if (isBeginEff == 1) {
            if (MainTabNew.longwidth == 0) {
                isShow = false;
            }
            GameCanvas.end_Dialog();
            if (!GameCanvas.isTouch) {
                this.left = null;
                this.center = null;
            } else {
                this.vecListCmd.removeAllElements();
            }
            if (itemRe != null) {
                vecEffRe.removeAllElements();
                int n = TabRebuildItem.itemRe.tier;
                if (n > 14) {
                    n = 14;
                }
                for (int i = 0; i < TabRebuildItem.dataRebuild[n].mValue.length; ++i) {
                    if (TabRebuildItem.dataRebuild[n].mValue[i] <= 0) continue;
                    TabRebuildItem.addEffectEndRebuild(31, this.posMaterial[i][0], this.posMaterial[i][1], this.posMaterial[5][0], this.posMaterial[5][1], 1);
                }
                if (isLucky) {
                    TabRebuildItem.addEffectEndRebuild(31, this.posMaterial[4][0], this.posMaterial[4][1], this.posMaterial[5][0], this.posMaterial[5][1], 1);
                }
            }
            isBeginEff = (byte)2;
            this.time = 0L;
        } else if (isBeginEff == 2) {
            if (this.time < 10L) {
                ++this.time;
                if (this.time == 10L) {
                    mSound.playSound(24, mSound.volumeSound);
                }
            } else {
                if (GameCanvas.timeNow - this.time > 12000L) {
                    this.time = GameCanvas.timeNow;
                    this.addEffectRebuild(29, this.posMaterial[5][0], this.posMaterial[5][1], 12100);
                }
                if (GameCanvas.timeNow - this.time > 3700L && (isNextRebuild == 3 || isNextRebuild == 4)) {
                    isBeginEff = isNextRebuild;
                    this.time = 0L;
                }
            }
        } else if (isBeginEff == 3) {
            mSound.playSound(26, mSound.volumeSound);
            vecEffRe.removeAllElements();
            TabRebuildItem.addEffectEnd_ReBuild_ss(32, this.posMaterial[5][0], this.posMaterial[5][1] - 11);
            TabRebuildItem.addEffectEnd_ReBuild_ss(33, this.posMaterial[5][0], this.posMaterial[5][1]);
            TabRebuildItem.addEffectEnd_ReBuild_ss(34, this.posMaterial[5][0], this.posMaterial[5][1]);
            isBeginEff = (byte)5;
            this.time = 0L;
        } else if (isBeginEff == 4) {
            mSound.playSound(27, mSound.volumeSound);
            vecEffRe.removeAllElements();
            TabRebuildItem.addEffectEnd_ReBuild_ss(11, this.posMaterial[5][0], this.posMaterial[5][1]);
            this.addEffectRebuild(69, this.posMaterial[5][0], this.posMaterial[5][1] - 11, 300);
            this.addEffectRebuild(69, this.posMaterial[5][0], this.posMaterial[5][1] - 11, 300);
            isBeginEff = (byte)5;
            this.time = 0L;
        } else if (isBeginEff == 5) {
            ++this.time;
            MainItem mainItem = (MainItem)Item.getItemInventory(TabRebuildItem.itemRe.ItemCatagory, (short)TabRebuildItem.itemRe.Id);
            if (!this.isTabHopNguyenLieu) {
                itemRe = mainItem != null ? MainItem.MainItem_Item(mainItem.Id, mainItem.itemNameExcludeLv, mainItem.imageId, mainItem.tier, mainItem.colorNameItem, mainItem.classcharItem, mainItem.ItemCatagory, mainItem.mInfo, mainItem.type_Only_Item, false, mainItem.IdTem, mainItem.priceItem, (short)mainItem.LvItem, mainItem.canSell, mainItem.canTrade, mainItem.timeUse, (byte)0, (byte)0) : null;
            }
            tilemayman = tilemaymanafter;
            if (vecEffRe.size() == 0 || this.time >= 100L) {
                GameCanvas.menu2.startAt_NPC(null, contentShow, Menu2.IdNPCLast, (byte)2, false, 0);
                this.time = 0L;
                isBeginEff = (byte)6;
            }
            countSetpaintinfo = 1;
        } else if (isBeginEff == 6) {
            if (TabRebuildItem.itemRe.tier == 15) {
                itemRe = null;
            }
            if (this.isTabHopNguyenLieu && itemRe != null) {
                itemRe = null;
            }
            isBeginEff = 0;
            if (!GameCanvas.isTouch) {
                this.left = this.cmdView;
                this.center = this.cmdHoiDap;
                MainTabNew.Focus = 0;
            } else {
                this.vecListCmd.removeAllElements();
                if (itemRe != null) {
                    if (MainTabNew.longwidth > 0) {
                        this.vecListCmd.addElement(this.cmdHoiDap);
                    } else {
                        this.vecListCmd.addElement(this.cmdView);
                        this.vecListCmd.addElement(this.cmdHoiDap);
                    }
                }
            }
        }
    }

    public void updateDucLo() {
        if (itemRe != null && countSetpaintinfo > 0) {
            this.setPaintInfo();
        }
        if (countSetpaintinfo > 0) {
            countSetpaintinfo = (byte)(countSetpaintinfo - 1);
        }
        if (isBeginEff == 1) {
            if (MainTabNew.longwidth == 0) {
                isShow = false;
            }
            GameCanvas.end_Dialog();
            if (!GameCanvas.isTouch) {
                this.left = null;
                this.center = null;
            } else {
                this.vecListCmd.removeAllElements();
            }
            if (itemRe != null) {
                vecEffRe.removeAllElements();
                int n = TabRebuildItem.itemRe.tier;
                if (n > 14) {
                    n = 14;
                }
                for (int i = 0; i < TabRebuildItem.dataRebuild[n].mValue.length; ++i) {
                    if (TabRebuildItem.dataRebuild[n].mValue[i] <= 0) continue;
                    TabRebuildItem.addEffectEndRebuild(31, this.posMaterial[i][0], this.posMaterial[i][1], this.posMaterial[5][0], this.posMaterial[5][1], 1);
                }
                if (isLucky) {
                    TabRebuildItem.addEffectEndRebuild(31, this.posMaterial[4][0], this.posMaterial[4][1], this.posMaterial[5][0], this.posMaterial[5][1], 1);
                }
            }
            isBeginEff = (byte)2;
            this.time = 0L;
        } else if (isBeginEff == 2) {
            if (this.time < 10L) {
                ++this.time;
                if (this.time == 10L) {
                    mSound.playSound(24, mSound.volumeSound);
                }
            } else {
                if (GameCanvas.timeNow - this.time > 12000L) {
                    this.time = GameCanvas.timeNow;
                    this.addEffectRebuild(29, this.posMaterial[5][0], this.posMaterial[5][1], 12100);
                }
                if (GameCanvas.timeNow - this.time > 3700L && (isNextRebuild == 3 || isNextRebuild == 4)) {
                    isBeginEff = isNextRebuild;
                    this.time = 0L;
                }
            }
        } else if (isBeginEff == 3) {
            mSound.playSound(26, mSound.volumeSound);
            vecEffRe.removeAllElements();
            TabRebuildItem.addEffectEnd_ReBuild_ss(32, this.posMaterial[5][0], this.posMaterial[5][1] - 11);
            TabRebuildItem.addEffectEnd_ReBuild_ss(33, this.posMaterial[5][0], this.posMaterial[5][1]);
            TabRebuildItem.addEffectEnd_ReBuild_ss(34, this.posMaterial[5][0], this.posMaterial[5][1]);
            isBeginEff = (byte)5;
            this.time = 0L;
        } else if (isBeginEff == 4) {
            mSound.playSound(27, mSound.volumeSound);
            vecEffRe.removeAllElements();
            TabRebuildItem.addEffectEnd_ReBuild_ss(11, this.posMaterial[5][0], this.posMaterial[5][1]);
            this.addEffectRebuild(69, this.posMaterial[5][0], this.posMaterial[5][1] - 11, 300);
            this.addEffectRebuild(69, this.posMaterial[5][0], this.posMaterial[5][1] - 11, 300);
            isBeginEff = (byte)5;
            this.time = 0L;
        } else if (isBeginEff == 5) {
            ++this.time;
            MainItem mainItem = (MainItem)Item.getItemInventory(TabRebuildItem.itemRe.ItemCatagory, (short)TabRebuildItem.itemRe.Id);
            if (!this.isTabHopNguyenLieu) {
                itemRe = mainItem != null ? MainItem.MainItem_Item(mainItem.Id, mainItem.itemNameExcludeLv, mainItem.imageId, mainItem.tier, mainItem.colorNameItem, mainItem.classcharItem, mainItem.ItemCatagory, mainItem.mInfo, mainItem.type_Only_Item, false, mainItem.IdTem, mainItem.priceItem, (short)mainItem.LvItem, mainItem.canSell, mainItem.canTrade, mainItem.timeUse, (byte)0, (byte)0) : null;
            }
            tilemayman = tilemaymanafter;
            if (vecEffRe.size() == 0 || this.time >= 100L) {
                GameCanvas.menu2.startAt_NPC(null, contentShow, Menu2.IdNPCLast, (byte)2, false, 0);
                this.time = 0L;
                isBeginEff = (byte)6;
            }
            countSetpaintinfo = 1;
        } else if (isBeginEff == 6) {
            if (TabRebuildItem.itemRe.tier == 15) {
                itemRe = null;
            }
            if (this.isTabHopNguyenLieu && itemRe != null) {
                itemRe = null;
            }
            isBeginEff = 0;
            if (!GameCanvas.isTouch) {
                this.left = this.cmdView;
                this.center = this.cmdHoiDap;
                MainTabNew.Focus = 0;
            } else {
                this.vecListCmd.removeAllElements();
                if (itemRe != null) {
                    if (MainTabNew.longwidth > 0) {
                        this.vecListCmd.addElement(this.cmdHoiDap);
                    } else {
                        this.vecListCmd.addElement(this.cmdView);
                        this.vecListCmd.addElement(this.cmdHoiDap);
                    }
                }
            }
        }
    }

    public void updateRebuild() {
        if (isDapnhanh && isBeginEff == 2) {
            isBeginEff = isNextRebuild;
            this.time = 0L;
        }
        if (isBeginEff == 1) {
            if (MainTabNew.longwidth == 0) {
                isShow = false;
            }
            GameCanvas.end_Dialog();
            if (!GameCanvas.isTouch) {
                this.left = null;
                this.center = null;
            } else {
                this.vecListCmd.removeAllElements();
            }
            if (itemRe != null) {
                vecEffRe.removeAllElements();
                for (int i = 0; i < TabRebuildItem.dataRebuild[TabRebuildItem.itemRe.tier].mValue.length; ++i) {
                    if (TabRebuildItem.dataRebuild[TabRebuildItem.itemRe.tier].mValue[i] <= 0) continue;
                    TabRebuildItem.addEffectEndRebuild(31, this.posMaterial[i][0], this.posMaterial[i][1], this.posMaterial[5][0], this.posMaterial[5][1], 1);
                }
                if (isLucky) {
                    TabRebuildItem.addEffectEndRebuild(31, this.posMaterial[4][0], this.posMaterial[4][1], this.posMaterial[5][0], this.posMaterial[5][1], 1);
                }
            }
            isBeginEff = (byte)2;
            this.time = 0L;
        } else if (isBeginEff == 2) {
            if (this.time < 10L) {
                ++this.time;
                if (this.time == 10L) {
                    mSound.playSound(24, mSound.volumeSound);
                }
            } else {
                if (GameCanvas.timeNow - this.time > 12000L) {
                    this.time = GameCanvas.timeNow;
                    this.addEffectRebuild(29, this.posMaterial[5][0], this.posMaterial[5][1], 12100);
                }
                if (GameCanvas.timeNow - this.time > 3700L && (isNextRebuild == 3 || isNextRebuild == 4)) {
                    isBeginEff = isNextRebuild;
                    this.time = 0L;
                }
            }
        } else if (isBeginEff == 3) {
            mSound.playSound(26, mSound.volumeSound);
            vecEffRe.removeAllElements();
            TabRebuildItem.addEffectEnd_ReBuild_ss(32, this.posMaterial[5][0], this.posMaterial[5][1] - 11);
            TabRebuildItem.addEffectEnd_ReBuild_ss(33, this.posMaterial[5][0], this.posMaterial[5][1]);
            TabRebuildItem.addEffectEnd_ReBuild_ss(34, this.posMaterial[5][0], this.posMaterial[5][1]);
            isBeginEff = (byte)5;
            this.time = 0L;
        } else if (isBeginEff == 4) {
            mSound.playSound(27, mSound.volumeSound);
            vecEffRe.removeAllElements();
            TabRebuildItem.addEffectEnd_ReBuild_ss(11, this.posMaterial[5][0], this.posMaterial[5][1]);
            this.addEffectRebuild(69, this.posMaterial[5][0], this.posMaterial[5][1] - 11, 300);
            this.addEffectRebuild(69, this.posMaterial[5][0], this.posMaterial[5][1] - 11, 300);
            isBeginEff = (byte)5;
            this.time = 0L;
        } else if (isBeginEff == 5) {
            ++this.time;
            MainItem mainItem = (MainItem)Item.getItemInventory(TabRebuildItem.itemRe.ItemCatagory, (short)TabRebuildItem.itemRe.Id);
            if (!this.isTabHopNguyenLieu) {
                itemRe = mainItem != null ? MainItem.MainItem_Item(mainItem.Id, mainItem.itemNameExcludeLv, mainItem.imageId, mainItem.tier, mainItem.colorNameItem, mainItem.classcharItem, mainItem.ItemCatagory, mainItem.mInfo, mainItem.type_Only_Item, false, mainItem.IdTem, mainItem.priceItem, (short)mainItem.LvItem, mainItem.canSell, mainItem.canTrade, mainItem.timeUse, (byte)0, (byte)0) : null;
            }
            tilemayman = tilemaymanafter;
            if (vecEffRe.size() == 0 || this.time >= 100L) {
                if (!isDapnhanh) {
                    GameCanvas.menu2.startAt_NPC(null, contentShow, Menu2.IdNPCLast, (byte)2, false, 0);
                } else if (isNextRebuild == 3) {
                    GameCanvas.addInfoChar(T.thanhcong);
                } else if (isNextRebuild == 4) {
                    GameCanvas.addInfoChar(T.thatbai);
                }
                this.time = 0L;
                isBeginEff = (byte)6;
            }
        } else if (isBeginEff == 6) {
            isDapnhanh = false;
            if (TabRebuildItem.itemRe.tier == 15) {
                itemRe = null;
            }
            if (this.isTabHopNguyenLieu && itemRe != null) {
                itemRe = null;
            }
            isBeginEff = 0;
            if (isLucky) {
                this.checkAddIsLucky();
            }
            if (!GameCanvas.isTouch) {
                this.left = this.cmdView;
                this.center = this.cmdHoiDap;
                MainTabNew.Focus = 0;
            } else {
                this.vecListCmd.removeAllElements();
                if (itemRe != null) {
                    if (MainTabNew.longwidth > 0) {
                        this.vecListCmd.addElement(this.cmdHoiDap);
                    } else {
                        this.vecListCmd.addElement(this.cmdView);
                        this.vecListCmd.addElement(this.cmdHoiDap);
                    }
                }
            }
        }
    }

    public void updatePointer() {
        if (isBeginEff != 0) {
            return;
        }
        if (!(this.vecListCmd == null || GameCanvas.menu2.isShowMenu || GameCanvas.currentDialog != null || GameCanvas.subDialog != null || MainTabNew.Focus != 1 && MainTabNew.longwidth <= 0)) {
            for (int i = 0; i < this.vecListCmd.size(); ++i) {
                iCommand iCommand2 = (iCommand)this.vecListCmd.elementAt(i);
                iCommand2.updatePointer();
            }
        }
        super.updatePointer();
    }

    public static void addEffectEndRebuild(int n, int n2, int n3, int n4, int n5, int n6) {
        EffectEnd effectEnd = new EffectEnd(n, n2, n3, n4, n5, n6);
        vecEffRe.addElement(effectEnd);
    }

    public static void addEffectEnd_ReBuild_ss(int n, int n2, int n3) {
        EffectEnd effectEnd = new EffectEnd(n, n2, n3);
        vecEffRe.addElement(effectEnd);
    }

    public void addEffectRebuild(int n, int n2, int n3, int n4) {
        EffectSkill effectSkill = new EffectSkill(n, n2, n3, n4, 0, 0);
        vecEffRe.addElement(effectSkill);
    }

    public void checkAddIsLucky() {
        for (int i = 0; i < Item.VecInvetoryPlayer.size(); ++i) {
            MainItem mainItem = (MainItem)Item.VecInvetoryPlayer.elementAt(i);
            if (mainItem.ItemCatagory != 7 || mainItem.typeMaterial != 11 || itemDaMayMan == null || TabRebuildItem.itemDaMayMan.Id != mainItem.Id) continue;
            GlobalService.gI().Rebuild_Item((byte)0, (short)mainItem.Id, (byte)mainItem.ItemCatagory);
            return;
        }
        isLucky = false;
    }

    static {
        priceWing = 0;
        nameWing = "";
        isChetao = 0;
        lvReWing = 0;
        idWingOk = 0;
        tilemayman = "test nao";
        isBeginEff = 0;
        isNextRebuild = 0;
        isLucky = false;
        vecEffRe = new mVector("TabRebuildItem vecEffRe");
        contentShow = "";
        vecGem = new mVector("TabRebuildItem vecGem");
        resetItemReplace = false;
        isDapnhanh = false;
        itemDaMayMan = null;
        countSetpaintinfo = 0;
    }
}

