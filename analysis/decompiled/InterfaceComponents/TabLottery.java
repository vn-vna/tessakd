/*
 * Decompiled with CFR 0.152.
 */
package InterfaceComponents;

import CLib.mFont;
import CLib.mGraphics;
import CLib.mImage;
import CLib.mVector;
import GameEffect.EffectEnd;
import GameEffect.MainEffect;
import GameObjects.Item;
import GameObjects.MainItem;
import GameObjects.MainObject;
import GameObjects.PetItem;
import GameScreen.MainScreen;
import GameScreen.PaintInfoGameScreen;
import GameScreen.TabScreenNew;
import InterfaceComponents.ListNew;
import InterfaceComponents.MainTabNew;
import InterfaceComponents.MsgDialog;
import InterfaceComponents.iCommand;
import Main.GameCanvas;
import Model.AvMain;
import Model.CRes;
import Model.T;
import Model.Vector2;
import netcommand.global.GlobalService;

public class TabLottery
extends MainTabNew {
    public static final byte SECTION_CHOOSE_REWARD = 0;
    public static final byte SECTION_LOTTERY_DRAWING = 1;
    private final int AT_DESTINATION_LIMIT;
    private final int MAX_MOVE_COUNT;
    private final int MAX_VELO;
    private int maxw;
    private int maxh;
    private int indexPaint = 12;
    private int winfo = 140;
    private int numW = 6;
    private int numH = 6;
    private int numHPaint;
    private int maxSize = 60;
    private int hcmd = 0;
    private int moveCount;
    private int selectIdx;
    private int curSelectPointerIdx;
    private int itemIdSelect;
    private static boolean waitForNewPlay;
    private static boolean beginDraw;
    private boolean endDraw;
    private boolean moveDone;
    public static boolean isReady;
    private Vector2[] posArray;
    private Vector2[] objPosArray;
    private Vector2[] fixPosArray;
    private Vector2 rewardPos;
    private static iCommand cmdStartDraw;
    private static iCommand cmdGetItem;
    private static iCommand cmdRepick;
    private static iCommand cmdContinue;
    private static iCommand cmdSelectGlass;
    private static mVector vecListCmd;
    private mVector vecEffEnd = new mVector("TabLottery vecEffEnd");
    private mVector vecRewardList = new mVector("TabLottery vecRewardList");
    private int[] ranIdx = new int[]{-1, -1, -1, -1};
    private Vector2 moveVec1;
    private Vector2 moveVec2;
    private Vector2 moveVec3;
    private Vector2 moveVec4;
    private float velocity;
    private float acceleration;
    private float itemVel;
    private int test_posX;
    private int test_posY;
    private static byte sectionType;
    private static byte luckyNumber;
    private int selectNumber;
    private ListNew list;
    public static boolean isWin;

    public static void changeSection(byte by) {
        sectionType = by;
        TabLottery.setCommand();
    }

    public static void setLuckyNumber(byte by) {
        luckyNumber = by;
    }

    private static void setCommand() {
        if (GameCanvas.isTouch) {
            vecListCmd.removeAllElements();
            if (sectionType == 0) {
                vecListCmd.addElement(cmdGetItem);
            } else if (waitForNewPlay) {
                vecListCmd.addElement(cmdRepick);
                vecListCmd.addElement(cmdContinue);
            } else if (!beginDraw) {
                vecListCmd.addElement(cmdStartDraw);
            }
        }
    }

    public TabLottery(String string, mVector mVector2, byte by) {
        this.AT_DESTINATION_LIMIT = 20;
        this.MAX_MOVE_COUNT = 30;
        this.MAX_VELO = 16;
        this.nameTab = string;
        waitForNewPlay = false;
        beginDraw = false;
        this.endDraw = false;
        isReady = false;
        this.moveDone = true;
        isWin = false;
        this.moveCount = 0;
        this.velocity = 0.0f;
        this.acceleration = 1.0f;
        this.itemVel = 1.0f;
        this.selectIdx = -1;
        this.curSelectPointerIdx = -1;
        this.selectNumber = -1;
        luckyNumber = (byte)-1;
        this.vecRewardList = mVector2;
        sectionType = by;
        this.typeTab = 0;
        this.xBegin = this.xTab + MainTabNew.wOneItem + MainTabNew.wOne5 * 3;
        this.yBegin = this.yTab + GameCanvas.h / 5 + MainTabNew.wOneItem;
        this.maxw = (MainTabNew.wblack - 8) / 32;
        this.maxh = (MainTabNew.hblack - 8) / 32;
        this.fixPosArray = new Vector2[5];
        this.fixPosArray[0] = new Vector2(this.xBegin + MainTabNew.wblack / 2 - 52, this.yBegin + MainTabNew.hblack / 2 - 16);
        this.fixPosArray[1] = new Vector2(this.xBegin + MainTabNew.wblack / 2 - 32, this.yBegin + MainTabNew.hblack / 2 + 45);
        this.fixPosArray[2] = new Vector2(this.xBegin + MainTabNew.wblack / 2 + 32, this.yBegin + MainTabNew.hblack / 2 + 45);
        this.fixPosArray[3] = new Vector2(this.xBegin + MainTabNew.wblack / 2 + 52, this.yBegin + MainTabNew.hblack / 2 - 16);
        this.fixPosArray[4] = new Vector2(this.xBegin + MainTabNew.wblack / 2, this.yBegin + MainTabNew.hblack / 2 - 52);
        this.posArray = new Vector2[5];
        this.posArray[0] = new Vector2(this.xBegin + MainTabNew.wblack / 2 - 52, this.yBegin + MainTabNew.hblack / 2 - 16);
        this.posArray[1] = new Vector2(this.xBegin + MainTabNew.wblack / 2 - 32, this.yBegin + MainTabNew.hblack / 2 + 45);
        this.posArray[2] = new Vector2(this.xBegin + MainTabNew.wblack / 2 + 32, this.yBegin + MainTabNew.hblack / 2 + 45);
        this.posArray[3] = new Vector2(this.xBegin + MainTabNew.wblack / 2 + 52, this.yBegin + MainTabNew.hblack / 2 - 16);
        this.posArray[4] = new Vector2(this.xBegin + MainTabNew.wblack / 2, this.yBegin + MainTabNew.hblack / 2 - 52);
        this.objPosArray = new Vector2[5];
        this.objPosArray[0] = new Vector2(this.xBegin + MainTabNew.wblack / 2 - 52, this.yBegin + MainTabNew.hblack / 2 - 16);
        this.objPosArray[1] = new Vector2(this.xBegin + MainTabNew.wblack / 2 - 32, this.yBegin + MainTabNew.hblack / 2 + 45);
        this.objPosArray[2] = new Vector2(this.xBegin + MainTabNew.wblack / 2 + 32, this.yBegin + MainTabNew.hblack / 2 + 45);
        this.objPosArray[3] = new Vector2(this.xBegin + MainTabNew.wblack / 2 + 52, this.yBegin + MainTabNew.hblack / 2 - 16);
        this.objPosArray[4] = new Vector2(this.xBegin + MainTabNew.wblack / 2, this.yBegin + MainTabNew.hblack / 2 - 52);
        this.rewardPos = new Vector2(this.xBegin + MainTabNew.wblack / 2, this.yBegin + MainTabNew.hblack / 2);
        cmdStartDraw = new iCommand(T.startdraw, 0, this);
        cmdGetItem = new iCommand(T.select, 1, this);
        cmdRepick = new iCommand(T.chonlai, 2, this);
        cmdContinue = new iCommand(T.choitiep, 4, this);
        cmdSelectGlass = new iCommand(T.select, 3, this);
        this.cmdBack = new iCommand(T.back, -1, this);
        if (GameCanvas.isTouch) {
            this.cmdBack.caption = T.close;
        }
        if (MainTabNew.longwidth > 0) {
            int n = MainTabNew.ylongwidth + this.hSmall;
            int n2 = MainTabNew.xlongwidth;
            if (MainTabNew.is320) {
                cmdStartDraw.setPos(n2 + MainTabNew.longwidth / 2, n - 10, PaintInfoGameScreen.fraButton2, TabLottery.cmdStartDraw.caption);
                cmdGetItem.setPos(n2 + MainTabNew.longwidth / 2, n - 10, PaintInfoGameScreen.fraButton2, TabLottery.cmdGetItem.caption);
                cmdRepick.setPos(n2 + MainTabNew.longwidth / 2 - 30, n - 10, PaintInfoGameScreen.fraButton2, TabLottery.cmdRepick.caption);
                cmdContinue.setPos(n2 + MainTabNew.longwidth / 2 + 30, n - 10, PaintInfoGameScreen.fraButton2, TabLottery.cmdContinue.caption);
            } else {
                cmdStartDraw.setPos(n2 + MainTabNew.longwidth / 2, n - 15, null, TabLottery.cmdStartDraw.caption);
                cmdGetItem.setPos(n2 + MainTabNew.longwidth / 2, n - 15, null, TabLottery.cmdGetItem.caption);
                cmdRepick.setPos(n2 + MainTabNew.longwidth / 2 - 42, n - 15, null, TabLottery.cmdRepick.caption);
                cmdContinue.setPos(n2 + MainTabNew.longwidth / 2 + 42, n - 15, null, TabLottery.cmdContinue.caption);
            }
        } else if (GameCanvas.isTouch) {
            cmdStartDraw.setPos(iCommand.wButtonCmd / 2 + 2, GameCanvas.h - iCommand.hButtonCmd / 2 - 2, null, TabLottery.cmdStartDraw.caption);
            cmdGetItem.setPos(iCommand.wButtonCmd / 2 + 2, GameCanvas.h - iCommand.hButtonCmd / 2 - 2, null, TabLottery.cmdGetItem.caption);
            cmdRepick.setPos(iCommand.wButtonCmd / 2 + 2 - 42, GameCanvas.h - iCommand.hButtonCmd / 2 - 2, null, TabLottery.cmdRepick.caption);
            cmdContinue.setPos(iCommand.wButtonCmd / 2 + 2 + 42, GameCanvas.h - iCommand.hButtonCmd / 2 - 2, null, TabLottery.cmdContinue.caption);
        }
        this.init();
    }

    public void init() {
        this.list = new ListNew(this.xBegin, this.yBegin, MainTabNew.wblack, MainTabNew.hblack, 0, 0, MainScreen.cameraSub.yLimit);
        TabLottery.setCommand();
        if (!GameCanvas.isTouch) {
            this.center = cmdGetItem;
            this.right = this.cmdBack;
        }
        this.vecEffEnd.removeAllElements();
        this.imgStarRebuild = mImage.createImage("/interface/rebuild.img");
    }

    public void paint(mGraphics mGraphics2) {
        if (sectionType == 0) {
            this.paintChooseRewardSection(mGraphics2);
        } else if (sectionType == 1) {
            this.paintDrawingSection(mGraphics2);
            mFont.tahoma_7b_white.drawString(mGraphics2, T.chondemo, this.xBegin + 5, this.yBegin + 5, 0, false);
        }
    }

    private void paintChooseRewardSection(mGraphics mGraphics2) {
        try {
            Object object;
            int n;
            GameCanvas.resetTrans(mGraphics2);
            mGraphics2.setClip(this.xBegin - MainTabNew.wOne5, this.yBegin, MainTabNew.wblack + MainTabNew.wOne5 * 2, MainTabNew.hblack - MainTabNew.wOne5 / 2 + 1);
            mGraphics2.saveCanvas();
            mGraphics2.ClipRec(this.xBegin - MainTabNew.wOne5, this.yBegin, MainTabNew.wblack + MainTabNew.wOne5 * 2, MainTabNew.hblack - MainTabNew.wOne5 / 2 + 1);
            mGraphics2.translate(-MainScreen.cameraSub.xCam, -MainScreen.cameraSub.yCam);
            for (n = 0; n < this.vecRewardList.size(); ++n) {
                object = (Item)this.vecRewardList.elementAt(n);
                if (object == null) continue;
                if (((Item)object).ItemCatagory == 7) {
                    MainItem mainItem = Item.getMaterial(((Item)object).Id);
                    if (mainItem != null) {
                        ((Item)object).setinfo(mainItem.itemName, mainItem.imageId, 7, mainItem.priceItem, mainItem.priceType, mainItem.content, (short)mainItem.value, mainItem.typeMaterial, (short)1, mainItem.canSell, mainItem.canTrade);
                        ((Item)object).paintItem(mGraphics2, this.xBegin + MainTabNew.wOneItem / 2 + n % this.numW * MainTabNew.wOneItem, this.yBegin + MainTabNew.wOneItem / 2 + n / this.numW * MainTabNew.wOneItem, MainTabNew.wOneItem, 0, 0);
                        if (((Item)object).timeUse <= 0) continue;
                        mGraphics2.drawRegion(AvMain.imgDelaySkill, 0, 0, MainTabNew.wOneItem - 1, MainTabNew.wOneItem - 1, 0, this.xBegin + MainTabNew.wOneItem / 2 + n % this.numW * MainTabNew.wOneItem, this.yBegin + MainTabNew.wOneItem / 2 + n / this.numW * MainTabNew.wOneItem, 3, true);
                        continue;
                    }
                    Item.put_Material(((Item)object).Id);
                    continue;
                }
                ((Item)object).paintItem(mGraphics2, this.xBegin + MainTabNew.wOneItem / 2 + n % this.numW * MainTabNew.wOneItem, this.yBegin + MainTabNew.wOneItem / 2 + n / this.numW * MainTabNew.wOneItem, MainTabNew.wOneItem, 0, 0);
                if (((Item)object).timeUse <= 0) continue;
                mGraphics2.drawRegion(AvMain.imgDelaySkill, 0, 0, MainTabNew.wOneItem - 1, MainTabNew.wOneItem - 1, 0, this.xBegin + MainTabNew.wOneItem / 2 + n % this.numW * MainTabNew.wOneItem, this.yBegin + MainTabNew.wOneItem / 2 + n / this.numW * MainTabNew.wOneItem, 3, true);
            }
            mGraphics2.setColor(MainTabNew.color[1]);
            mGraphics2.drawRect(this.xBegin, this.yBegin, MainTabNew.wblack, MainTabNew.wOneItem * this.numH, true);
            for (n = 0; n < this.numW / 2; ++n) {
                mGraphics2.drawRect(this.xBegin + MainTabNew.wOneItem + n * MainTabNew.wOneItem * 2, this.yBegin, MainTabNew.wOneItem, MainTabNew.wOneItem * this.numH, true);
            }
            for (n = 0; n < this.numH / 2; ++n) {
                mGraphics2.drawRect(this.xBegin, this.yBegin + MainTabNew.wOneItem + n * MainTabNew.wOneItem * 2, MainTabNew.wblack, MainTabNew.wOneItem, true);
            }
            if (this.itemIdSelect > -1 && MainTabNew.Focus == 1) {
                mGraphics2.setColor(MainTabNew.color[2]);
                mGraphics2.drawRect(this.xBegin + this.itemIdSelect % this.numW * MainTabNew.wOneItem + 1, this.yBegin + this.itemIdSelect / this.numW * MainTabNew.wOneItem + 1, MainTabNew.wOneItem - 2, MainTabNew.wOneItem - 2, true);
                mGraphics2.setColor(MainTabNew.color[3]);
                mGraphics2.drawRect(this.xBegin + this.itemIdSelect % this.numW * MainTabNew.wOneItem, this.yBegin + this.itemIdSelect / this.numW * MainTabNew.wOneItem, MainTabNew.wOneItem, MainTabNew.wOneItem, true);
            }
            mGraphics.resetTransAndroid(mGraphics2);
            mGraphics2.restoreCanvas();
            mGraphics2.endClip();
            if (!GameCanvas.menu2.isShowMenu && GameCanvas.currentDialog == null && GameCanvas.subDialog == null && MainTabNew.Focus == 1 && MainTabNew.timePaintInfo > MainTabNew.timeRequest) {
                this.paintPopupContent(mGraphics2, false);
                if (vecListCmd != null) {
                    for (n = 0; n < vecListCmd.size(); ++n) {
                        object = (iCommand)vecListCmd.elementAt(n);
                        ((iCommand)object).paint(mGraphics2, ((iCommand)object).xCmd, ((iCommand)object).yCmd);
                    }
                }
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    private void paintDrawingSection(mGraphics mGraphics2) {
        try {
            Object object;
            int n;
            mGraphics2.fillRect(this.xBegin + 2, this.yBegin + 2, MainTabNew.wblack - 4, MainTabNew.hblack - 4, true);
            if (GameCanvas.lowGraphic) {
                MainTabNew.paintRectLowGraphic(mGraphics2, this.xBegin + 4, this.yBegin + 4, MainTabNew.wblack - 8, MainTabNew.hblack - 8, 4);
            } else {
                this.paintRect(mGraphics2);
            }
            mGraphics2.drawImage(this.imgStarRebuild, this.xBegin + MainTabNew.wblack / 2 - 54, this.yBegin + MainTabNew.hblack / 2 - 52, 0, true);
            mGraphics2.drawRegion(this.imgStarRebuild, 0, 0, 54, 105, 2, this.xBegin + MainTabNew.wblack / 2, this.yBegin + MainTabNew.hblack / 2 - 52, 0, true);
            Item item = (Item)this.vecRewardList.elementAt(this.itemIdSelect);
            if (isReady || item != null) {
                // empty if block
            }
            for (n = 0; n < this.objPosArray.length; ++n) {
                if (this.selectNumber == -1) {
                    mGraphics2.drawImage(AvMain.imgGlass, (int)this.objPosArray[n].x, (int)this.objPosArray[n].y, 3, true);
                } else if (isWin) {
                    if (luckyNumber == n) {
                        mGraphics2.drawImage(AvMain.imgHotKey, (int)this.fixPosArray[n].x, (int)this.fixPosArray[n].y, 3, true);
                        item.paintItem(mGraphics2, (int)this.fixPosArray[n].x, (int)this.fixPosArray[n].y, 20, 0, 0);
                    } else {
                        mGraphics2.drawImage(AvMain.imgGlass, (int)this.fixPosArray[n].x, (int)this.fixPosArray[n].y, 3, true);
                    }
                } else if (luckyNumber == n) {
                    mGraphics2.drawImage(AvMain.imgHotKey, (int)this.fixPosArray[n].x, (int)this.fixPosArray[n].y, 3, true);
                    item.paintItem(mGraphics2, (int)this.fixPosArray[n].x, (int)this.fixPosArray[n].y, 20, 0, 0);
                } else {
                    if (this.selectNumber == n) continue;
                    mGraphics2.drawImage(AvMain.imgGlass, (int)this.fixPosArray[n].x, (int)this.fixPosArray[n].y, 3, true);
                }
                if (GameCanvas.isTouch || !this.endDraw || this.curSelectPointerIdx <= -1) continue;
                if (MainObject.Wfc == 0) {
                    MainObject.Wfc = mImage.getImageWidth(MainObject.newfocus.image);
                    MainObject.Hfc = mImage.getImageHeight(MainObject.newfocus.image) / 10;
                }
                mGraphics2.drawRegion(MainObject.newfocus, 0, 0, MainObject.Wfc, MainObject.Hfc, 0, (int)this.fixPosArray[this.curSelectPointerIdx].x, (int)this.fixPosArray[this.curSelectPointerIdx].y - GameCanvas.gameTick % 5 - 10, 3, true);
            }
            for (n = 0; n < this.vecEffEnd.size(); ++n) {
                object = (MainEffect)this.vecEffEnd.elementAt(n);
                ((MainEffect)object).paint(mGraphics2);
            }
            if (!(GameCanvas.menu2.isShowMenu || GameCanvas.currentDialog != null || GameCanvas.subDialog != null || MainTabNew.Focus != 1 && MainTabNew.longwidth <= 0 || vecListCmd == null)) {
                for (n = 0; n < vecListCmd.size(); ++n) {
                    object = (iCommand)vecListCmd.elementAt(n);
                    ((iCommand)object).paint(mGraphics2, ((iCommand)object).xCmd, ((iCommand)object).yCmd);
                }
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private void paintRect(mGraphics mGraphics2) {
        for (int i = 0; i <= this.maxw; ++i) {
            for (int j = 0; j <= this.maxh; ++j) {
                this.indexPaint = 12;
                if (j == 0) {
                    this.indexPaint = 12;
                }
                if (i == this.maxw) {
                    if (j == this.maxh) {
                        mGraphics2.drawImage(MainTabNew.imgTab[this.indexPaint], this.xBegin + 4 + (MainTabNew.wblack - 8) - 32, this.yBegin + 4 + MainTabNew.hblack - 8 - 32, 0, true);
                        continue;
                    }
                    mGraphics2.drawImage(MainTabNew.imgTab[this.indexPaint], this.xBegin + 4 + (MainTabNew.wblack - 8) - 32, this.yBegin + 4 + j * 32, 0, true);
                    continue;
                }
                if (j == this.maxh) {
                    mGraphics2.drawImage(MainTabNew.imgTab[this.indexPaint], this.xBegin + 4 + i * 32, this.yBegin + 4 + MainTabNew.hblack - 8 - 32, 0, true);
                    continue;
                }
                mGraphics2.drawImage(MainTabNew.imgTab[this.indexPaint], this.xBegin + 4 + i * 32, this.yBegin + 4 + j * 32, 0, true);
            }
        }
    }

    public void update() {
        try {
            if (sectionType == 0) {
                if (MainTabNew.Focus == 1) {
                    if (this.listContent != null) {
                        this.listContent.moveCamera();
                    }
                    if (GameCanvas.isTouch) {
                        this.list.moveCamera();
                    } else {
                        MainScreen.cameraSub.UpdateCamera();
                    }
                    mVector mVector2 = this.vecRewardList;
                    if (mVector2.size() == 0) {
                        MainTabNew.Focus = 0;
                        return;
                    }
                    this.updateContent(mVector2, this.itemIdSelect);
                } else {
                    MainTabNew.timePaintInfo = 0;
                }
            } else if (sectionType == 1) {
                for (int i = 0; i < this.vecEffEnd.size(); ++i) {
                    MainEffect mainEffect = (MainEffect)this.vecEffEnd.elementAt(i);
                    mainEffect.update();
                    if (!mainEffect.isStop) continue;
                    this.vecEffEnd.removeElement(mainEffect);
                }
                if (this.selectIdx > -1) {
                    EffectEnd effectEnd = new EffectEnd(34, (int)this.fixPosArray[this.selectIdx].x, (int)this.fixPosArray[this.selectIdx].y);
                    this.vecEffEnd.addElement(effectEnd);
                    GlobalService.gI().request_LotteryItems((byte)2, (byte)this.selectIdx);
                    this.selectNumber = this.selectIdx;
                    this.selectIdx = -1;
                    this.endDraw = false;
                    waitForNewPlay = true;
                    TabLottery.setCommand();
                }
                if (!isReady) {
                    Vector2 vector2 = Vector2.substract(this.rewardPos, this.posArray[luckyNumber]);
                    vector2.normalize();
                    this.itemVel += 0.5f;
                    this.rewardPos.add(vector2.x * this.itemVel, vector2.y * this.itemVel);
                    if (this.atDestination(this.rewardPos, this.posArray[luckyNumber])) {
                        luckyNumber = (byte)-1;
                        isReady = true;
                    }
                }
                if (this.moveCount >= 30) {
                    beginDraw = false;
                }
                if (beginDraw) {
                    GameCanvas.isPointerSelect = false;
                    this.moveDone = true;
                    this.endDraw = true;
                    if (this.moveDone) {
                        int n = 0;
                        int n2 = 2;
                        if (this.moveCount > 16) {
                            n = CRes.random(0, 2);
                        }
                        n2 = n == 0 ? 2 : 4;
                        int n3 = 0;
                        while (n3 < n2) {
                            boolean bl = false;
                            int n4 = CRes.random(0, 5);
                            for (int i = 0; i < this.ranIdx.length; ++i) {
                                if (n4 != this.ranIdx[i]) continue;
                                bl = true;
                            }
                            if (bl) continue;
                            this.ranIdx[n3] = n4;
                            ++n3;
                        }
                        this.moveVec1 = Vector2.substract(this.objPosArray[this.ranIdx[0]], this.objPosArray[this.ranIdx[1]]);
                        this.moveVec2 = Vector2.substract(this.objPosArray[this.ranIdx[1]], this.objPosArray[this.ranIdx[0]]);
                        this.moveVec1.normalize();
                        this.moveVec2.normalize();
                        if (this.ranIdx[2] > -1 && this.ranIdx[3] > -1) {
                            this.moveVec3 = Vector2.substract(this.objPosArray[this.ranIdx[2]], this.objPosArray[this.ranIdx[3]]);
                            this.moveVec4 = Vector2.substract(this.objPosArray[this.ranIdx[3]], this.objPosArray[this.ranIdx[2]]);
                            this.moveVec3.normalize();
                            this.moveVec4.normalize();
                        }
                        this.moveDone = false;
                        this.velocity = this.moveCount < 16 ? (float)this.moveCount : 16.0f;
                    } else {
                        if (this.velocity < 16.0f) {
                            this.velocity += this.acceleration;
                        }
                        this.objPosArray[this.ranIdx[0]].add(this.moveVec1.x * this.velocity, this.moveVec1.y * this.velocity);
                        this.objPosArray[this.ranIdx[1]].add(this.moveVec2.x * this.velocity, this.moveVec2.y * this.velocity);
                        if (this.ranIdx[2] > -1 && this.ranIdx[3] > -1) {
                            this.objPosArray[this.ranIdx[2]].add(this.moveVec3.x * this.velocity, this.moveVec3.y * this.velocity);
                            this.objPosArray[this.ranIdx[3]].add(this.moveVec4.x * this.velocity, this.moveVec4.y * this.velocity);
                        }
                        if (this.atDestination(this.objPosArray[this.ranIdx[0]], this.posArray[this.ranIdx[1]])) {
                            this.objPosArray[this.ranIdx[0]].set(this.posArray[this.ranIdx[1]]);
                            this.objPosArray[this.ranIdx[1]].set(this.posArray[this.ranIdx[0]]);
                            this.posArray[this.ranIdx[0]].set(this.objPosArray[this.ranIdx[0]]);
                            this.posArray[this.ranIdx[1]].set(this.objPosArray[this.ranIdx[1]]);
                            if (this.ranIdx[2] > -1 && this.ranIdx[3] > -1) {
                                this.objPosArray[this.ranIdx[2]].set(this.posArray[this.ranIdx[3]]);
                                this.objPosArray[this.ranIdx[3]].set(this.posArray[this.ranIdx[2]]);
                                this.posArray[this.ranIdx[2]].set(this.objPosArray[this.ranIdx[2]]);
                                this.posArray[this.ranIdx[3]].set(this.objPosArray[this.ranIdx[3]]);
                            }
                            if (this.moveCount == 29) {
                                this.endDraw = true;
                                this.curSelectPointerIdx = 0;
                                if (!GameCanvas.isTouch) {
                                    this.center = cmdSelectGlass;
                                }
                            }
                            this.moveDone = true;
                            ++this.moveCount;
                            for (int i = 0; i < this.ranIdx.length; ++i) {
                                this.ranIdx[i] = -1;
                            }
                        }
                    }
                }
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private void updateContent(mVector mVector2, int n) {
        if (MainTabNew.timePaintInfo < MainTabNew.timeRequest + 2 && ++MainTabNew.timePaintInfo == MainTabNew.timeRequest) {
            this.setPaintInfo();
        }
        if (this.mContent == null && n >= 0 && n < mVector2.size()) {
            Item item = (Item)mVector2.elementAt(n);
            if (item.ItemCatagory != 5) {
                if (item.mcontent == null) {
                    if (item.timeupdateMore % 100 == 3) {
                        if (this.typeTab == 0) {
                            GlobalService.gI().Item_More_Info((byte)0, (byte)item.Id);
                        }
                        ++item.timeupdateMore;
                    } else {
                        ++item.timeupdateMore;
                    }
                } else {
                    this.mContent = item.mcontent;
                    this.mcolor = item.mColor;
                    this.setYCon(item);
                }
            }
        }
    }

    public void setYCon(Item item) {
        int n = 0;
        if (MainScreen.cameraSub.yCam > 0) {
            n = MainScreen.cameraSub.yCam / MainTabNew.wOneItem;
        }
        int n2 = 1;
        this.mContent = item.mcontent;
        this.mcolor = item.mColor;
        if (item.mcontent != null) {
            n2 += this.mContent.length;
        }
        if (item.mPlusContent != null) {
            n2 += item.mPlusContent.length;
        }
        if (this.itemIdSelect / this.numW < this.numHPaint / 2 + n) {
            this.yCon = this.yBegin + (this.itemIdSelect / this.numW + 1) * MainTabNew.wOneItem + 2;
            if (this.yCon - MainScreen.cameraSub.yCam + (n2 + 1) * GameCanvas.hText > GameCanvas.h - (GameCanvas.hCommand - 5)) {
                this.yCon = GameCanvas.h - (GameCanvas.hCommand - 5) - ((n2 + 1) * GameCanvas.hText - MainScreen.cameraSub.yCam);
            }
        } else {
            this.yCon = this.yBegin + this.itemIdSelect / this.numW * MainTabNew.wOneItem - 7 - n2 * GameCanvas.hText - MainScreen.cameraSub.yCam;
            if (this.yCon - MainScreen.cameraSub.yCam < 6) {
                this.yCon = 6 + MainScreen.cameraSub.yCam;
            }
        }
        if ((n2 + 1) * GameCanvas.hText > MainTabNew.hMaxContent - this.hcmd) {
            this.listContent = new ListNew(this.xCon, this.yCon, this.wContent, MainTabNew.hMaxContent, 0, 0, (n2 + 1) * GameCanvas.hText - (MainTabNew.hMaxContent - this.hcmd));
        }
    }

    public void setPaintInfo() {
        mVector mVector2 = this.vecRewardList;
        this.mContent = null;
        this.mSubContent = null;
        this.mPlusContent = null;
        if (this.itemIdSelect >= mVector2.size() || this.itemIdSelect < 0) {
            if (this.itemIdSelect > mVector2.size() - 1) {
                this.itemIdSelect = mVector2.size() - 1;
            }
            MainTabNew.timePaintInfo = 0;
            return;
        }
        Item item = (Item)mVector2.elementAt(this.itemIdSelect);
        if (item.setNameNull()) {
            MainTabNew.timePaintInfo = 0;
            return;
        }
        if (item.ItemCatagory == 9) {
            MsgDialog.pet = (PetItem)item;
            this.isPet = true;
        } else {
            this.isPet = false;
        }
        this.name = item.itemName;
        this.colorName = item.colorNameItem;
        if (item.ItemCatagory == 3 || this.typeTab == 8) {
            this.mPlusContent = item.mPlusContent;
            this.mPlusColor = item.mPlusColor;
        }
        this.listContent = null;
        if (MainTabNew.longwidth > 0) {
            int n = 1;
            this.mContent = item.mcontent;
            this.mcolor = item.mColor;
            if (item.mcontent != null) {
                n += this.mContent.length;
            }
            if (item.mPlusContent != null) {
                n += item.mPlusContent.length;
            }
            if (n * GameCanvas.hText > MainTabNew.hMaxContent) {
                this.listContent = new ListNew(MainTabNew.xlongwidth, MainTabNew.ylongwidth, MainTabNew.longwidth, MainTabNew.hMaxContent, 0, 0, n * GameCanvas.hText - MainTabNew.hMaxContent);
            } else if (GameCanvas.isTouch) {
                this.listContent = new ListNew(MainTabNew.xlongwidth, MainTabNew.ylongwidth, MainTabNew.longwidth, MainTabNew.hMaxContent, 0, 0, 0);
            }
            return;
        }
        this.wContent = item.sizeW;
        this.setYCon(item);
        this.xCon = this.itemIdSelect % this.numW < 2 ? this.xBegin + MainTabNew.wOneItem / 2 + this.itemIdSelect % this.numW * MainTabNew.wOneItem : (this.itemIdSelect % this.numW < 5 ? this.xBegin + MainTabNew.wOneItem / 2 + this.itemIdSelect % this.numW * MainTabNew.wOneItem - this.wContent / 2 : this.xBegin + MainTabNew.wOneItem / 2 + this.itemIdSelect % this.numW * MainTabNew.wOneItem - this.wContent);
    }

    public void backTab() {
        MainTabNew.Focus = 0;
        super.backTab();
    }

    private boolean atDestination(Vector2 vector2, Vector2 vector22) {
        float f = Vector2.distance(vector2, vector22);
        return f < 20.0f;
    }

    public void updatekey() {
        mVector mVector2 = this.vecRewardList;
        if (MainTabNew.Focus == 1) {
            int n = this.itemIdSelect;
            boolean bl = false;
            if (sectionType == 1) {
                if (this.endDraw) {
                    if (GameCanvas.keyMyHold[4]) {
                        --this.curSelectPointerIdx;
                        if (this.curSelectPointerIdx < 0) {
                            this.curSelectPointerIdx = 4;
                        }
                        GameCanvas.clearKeyHold(4);
                    } else if (GameCanvas.keyMyHold[6]) {
                        ++this.curSelectPointerIdx;
                        if (this.curSelectPointerIdx > 4) {
                            this.curSelectPointerIdx = 0;
                        }
                        GameCanvas.clearKeyHold(6);
                    }
                }
            } else {
                if (this.listContent != null) {
                    if (GameCanvas.keyMyHold[2]) {
                        this.listContent.cmtoX = this.listContent.cmtoX > 0 ? (this.listContent.cmtoX -= GameCanvas.hText) : 0;
                        GameCanvas.clearKeyHold(2);
                    } else if (GameCanvas.keyMyHold[8]) {
                        this.listContent.cmtoX = this.listContent.cmtoX < this.listContent.cmxLim ? (this.listContent.cmtoX += GameCanvas.hText) : this.listContent.cmxLim;
                        GameCanvas.clearKeyHold(8);
                    }
                } else if (GameCanvas.keyMyHold[2]) {
                    this.itemIdSelect -= this.numW;
                    GameCanvas.clearKeyHold(2);
                    bl = true;
                } else if (GameCanvas.keyMyHold[8]) {
                    this.itemIdSelect += this.numW;
                    GameCanvas.clearKeyHold(8);
                    bl = true;
                }
                if (GameCanvas.keyMyHold[4]) {
                    if (this.itemIdSelect % this.numW == 0) {
                        MainTabNew.Focus = 0;
                    } else {
                        --this.itemIdSelect;
                    }
                    GameCanvas.clearKeyHold(4);
                    bl = true;
                } else if (GameCanvas.keyMyHold[6]) {
                    ++this.itemIdSelect;
                    GameCanvas.clearKeyHold(6);
                    bl = true;
                }
                if (bl) {
                    this.listContent = null;
                    this.itemIdSelect = this.resetSelect(this.itemIdSelect, mVector2.size() - 1, false);
                    if (GameCanvas.isTouch || this.typeTab == 0 || this.typeTab == 9) {
                        // empty if block
                    }
                    TabScreenNew.timeRepaint = 10;
                }
                if (n != this.itemIdSelect) {
                    MainScreen.cameraSub.moveCamera(0, (this.itemIdSelect / this.numW - 1) * MainTabNew.wOneItem);
                    MainTabNew.timePaintInfo = 0;
                }
            }
        }
        super.updatekey();
    }

    public void updatePointer() {
        int n;
        int n2;
        if (sectionType == 1) {
            n2 = mImage.getImageWidth(AvMain.imgGlass.image);
            n = mImage.getImageHeight(AvMain.imgGlass.image);
            if (this.endDraw) {
                for (int i = 0; i < this.posArray.length; ++i) {
                    if (!GameCanvas.isPointSelect((int)this.fixPosArray[i].x - n2 / 2, (int)this.fixPosArray[i].y - n / 2, n2, n)) continue;
                    this.selectIdx = i;
                    GameCanvas.isPointerSelect = false;
                    break;
                }
            }
        } else if (sectionType == 0) {
            n2 = 0;
            if (this.listContent != null && GameCanvas.isPoint(this.listContent.x, this.listContent.y, this.listContent.maxW, this.listContent.maxH)) {
                this.listContent.update_Pos_UP_DOWN();
                n2 = 1;
            }
            if (GameCanvas.isTouch && n2 == 0) {
                this.list.update_Pos_UP_DOWN();
                MainScreen.cameraSub.yCam = this.list.cmx;
            }
            if (GameCanvas.isPointSelect(this.xBegin, this.yBegin, this.numW * MainTabNew.wOneItem, MainTabNew.hblack - MainTabNew.wOne5 / 2) && n2 == 0) {
                n = (GameCanvas.px - this.xBegin) / MainTabNew.wOneItem + (GameCanvas.py - this.yBegin + MainScreen.cameraSub.yCam) / MainTabNew.wOneItem * this.numW;
                if (n >= 0 && n < this.vecRewardList.size()) {
                    GameCanvas.isPointerSelect = false;
                    MainTabNew.timePaintInfo = 0;
                    this.itemIdSelect = n;
                    if (MainTabNew.Focus != 1) {
                        MainTabNew.Focus = 1;
                    }
                } else {
                    MainTabNew.timePaintInfo = 0;
                    this.itemIdSelect = -1;
                }
            }
        }
        if (!(vecListCmd == null || GameCanvas.menu2.isShowMenu || GameCanvas.currentDialog != null || GameCanvas.subDialog != null || MainTabNew.Focus != 1 && MainTabNew.longwidth <= 0)) {
            for (n2 = 0; n2 < vecListCmd.size(); ++n2) {
                iCommand iCommand2 = (iCommand)vecListCmd.elementAt(n2);
                iCommand2.updatePointer();
            }
        }
        super.updatePointer();
    }

    public void commandTab(int n, int n2) {
        super.commandTab(n, n2);
    }

    public void commandPointer(int n, int n2) {
        switch (n) {
            case -1: {
                if (waitForNewPlay) {
                    GameCanvas.game.Show();
                    break;
                }
                this.backTab();
                break;
            }
            case 0: {
                if (beginDraw || this.endDraw) {
                    return;
                }
                beginDraw = true;
                this.moveCount = 0;
                this.velocity = 0.0f;
                if (!GameCanvas.isTouch) {
                    this.center = null;
                    this.right = null;
                    this.left = null;
                }
                TabLottery.setCommand();
                break;
            }
            case 1: {
                if (waitForNewPlay) break;
                GlobalService.gI().request_LotteryItems((byte)1, (byte)this.itemIdSelect);
                if (GameCanvas.isTouch) break;
                this.center = cmdStartDraw;
                this.right = null;
                this.left = null;
                break;
            }
            case 2: {
                GlobalService.gI().request_LotteryItems((byte)0, (byte)0);
                break;
            }
            case 3: {
                this.selectIdx = this.curSelectPointerIdx;
                if (GameCanvas.isTouch) break;
                this.center = cmdContinue;
                this.left = cmdRepick;
                this.cmdBack.caption = T.close;
                this.right = this.cmdBack;
                break;
            }
            case 4: {
                GlobalService.gI().request_LotteryItems((byte)1, (byte)this.itemIdSelect);
                if (!GameCanvas.isTouch) {
                    this.center = cmdStartDraw;
                    this.right = null;
                    this.left = null;
                }
                waitForNewPlay = false;
                beginDraw = false;
                this.endDraw = false;
                this.moveDone = true;
                isWin = false;
                this.moveCount = 0;
                this.selectIdx = -1;
                this.curSelectPointerIdx = -1;
                this.selectNumber = -1;
                luckyNumber = (byte)-1;
                this.itemVel = 1.0f;
                this.rewardPos = new Vector2(this.xBegin + MainTabNew.wblack / 2, this.yBegin + MainTabNew.hblack / 2);
            }
        }
        super.commandPointer(n, n2);
    }

    static {
        vecListCmd = new mVector("TabLottery vecListCmd");
    }
}

