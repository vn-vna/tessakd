/*
 * Decompiled with CFR 0.152.
 */
package GameObjects;

import CLib.mGraphics;
import CLib.mSystem;
import CLib.mVector;
import GameEffect.EffectCharWearing;
import GameObjects.AutoGetItem;
import GameObjects.DelaySkill;
import GameObjects.Item;
import GameObjects.MainInfoItem;
import GameObjects.MainObject;
import GameObjects.MainParty;
import GameObjects.MainQuest;
import GameObjects.Object_Effect_Skill;
import GameScreen.GameScreen;
import GameScreen.MainScreen;
import GameScreen.PaintInfoGameScreen;
import InterfaceComponents.ChatTextField;
import InterfaceComponents.MsgDialog;
import InterfaceComponents.PopupChat;
import InterfaceComponents.TabConfig;
import InterfaceComponents.TabInfoChar;
import InterfaceComponents.TabMySeftNew;
import InterfaceComponents.TabQuest;
import InterfaceComponents.TabShopNew;
import InterfaceComponents.TabSkillsNew;
import InterfaceComponents.iCommand;
import Main.GameCanvas;
import Model.CRes;
import Model.T;
import Skill.HotKey;
import Skill.ListSkill;
import Skill.LvSkill;
import Skill.MainListSkill;
import Skill.Skill;
import Skill.SplashSkill;
import Thread_More.LoadMap;
import netcommand.global.GlobalService;

public class Player
extends MainObject {
    public static iCommand cmdNextFocus;
    public static iCommand cmdRevice;
    public static iCommand cmdVeLang;
    public static int levelTab;
    public static int PointSucKhoe;
    public static int MaxSkill;
    public static int timeX2;
    public static int timeResetAuto;
    public static int PointArena;
    public static long timeSetX2;
    public static boolean isNewPlayer;
    public static boolean isAutoHPMP;
    public static boolean isLockKey;
    public static boolean isSendMove;
    public static boolean isPhong;
    public static byte isAutoFire;
    public static byte isAutoBuff;
    public static AutoGetItem autoItem;
    public static boolean isFullInven;
    public static long timeFristSkill;
    public static DelaySkill[] timeDelaySkill;
    public static DelaySkill[] timeDelayPotion;
    public static int diemTiemNang;
    public static int diemKyNang;
    public static int[][] mTiemnang;
    public static HotKey[][] mhotkey;
    public static short maxInven;
    public static short maxChest;
    public static short maxPet;
    public static byte typeX2;
    public byte timeHit;
    public long timeSendmove;
    public boolean ispaintHit;
    public boolean isMaxdame;
    public int timeCombo;
    public static byte[] ID_HAIR_NO_HAT;
    private PopupChat nameStore;
    public int lastX = 1000000;
    public int lastY = 1000000;
    public static int[] mKillPlayer;
    public static int[] mCurentLvSkill;
    public static int[] mPlusLvSkill;
    public String[] mKhangChar = new String[5];
    public MainInfoItem[] mInfoChar;
    public MainQuest currentQuest;
    public static MainParty party;
    public static int xFocus;
    public static int yFocus;
    public static int timeFocus;
    public static int skillDefault;
    public static int xBeginAutoFire;
    public static int yBeginAutofire;
    public static short[] vecPlayerPk;
    public TabSkillsNew tabskills;
    int coutauto;
    public static byte IndexFire;
    long timeAutoBuff;
    public static short demUnFire;
    int xtam;
    int ytam;
    int counttam;
    int skilltam = 0;
    public static int testloadmap;
    public static int timeStand;
    public static int timeQuest;
    public static byte typeFocusLast;
    public static byte timeFocusLast;
    int indexFocus = 0;
    public static boolean isFocusNPC;
    public static int timeFocusNPC;
    public static boolean isCurAutoFire;

    public boolean isMainChar() {
        return true;
    }

    public void doResetLastXY() {
        this.lastX = 1000000;
        this.lastY = 1000000;
    }

    public Player(int n, byte by, String string, int n2, int n3) {
        super(n, by, string, n2, n3);
        int n4;
        party = null;
        this.typeObject = 0;
        this.hOne = 40;
        this.wOne = 30;
        this.vMax = 6;
        this.name = string;
        this.wFocus = 140;
        cmdNextFocus = new iCommand(T.next, 0, this);
        cmdRevice = new iCommand(T.hoisinh, 1, this);
        cmdRevice.setPos(iCommand.wButtonCmd / 2, GameCanvas.h - iCommand.hButtonCmd / 2 - 2, null, Player.cmdRevice.caption);
        cmdVeLang = new iCommand(T.velang, 2, this);
        cmdVeLang.setPos(GameCanvas.w - iCommand.wButtonCmd / 2, GameCanvas.h - iCommand.hButtonCmd / 2 - 2, null, Player.cmdVeLang.caption);
        this.PlashNow = new SplashSkill();
        this.ListKillNow = new ListSkill();
        isNewPlayer = false;
        this.xsai = 1;
        this.ysai = 2;
        for (n4 = 0; n4 < timeDelaySkill.length; ++n4) {
            Player.timeDelaySkill[n4] = new DelaySkill();
            Player.timeDelaySkill[n4].value = 0;
            Player.timeDelaySkill[n4].timebegin = mSystem.currentTimeMillis();
        }
        for (n4 = 0; n4 < timeDelayPotion.length; ++n4) {
            Player.timeDelayPotion[n4] = new DelaySkill();
            Player.timeDelayPotion[n4].value = 0;
            Player.timeDelayPotion[n4].timebegin = mSystem.currentTimeMillis();
        }
        this.timeSendmove = mSystem.currentTimeMillis();
        this.timeCombo = -1;
    }

    public void init() {
        TabShopNew tabShopNew = new TabShopNew(Item.VecInvetoryPlayer, 0, T.tabhanhtrang, -1, TabShopNew.NORMAL);
        mVector mVector2 = new mVector("Player (init) v");
        mVector2.addElement(tabShopNew);
        TabMySeftNew tabMySeftNew = new TabMySeftNew(T.tabtrangbi);
        mVector2.addElement(tabMySeftNew);
        TabInfoChar tabInfoChar = new TabInfoChar(T.tabthongtin);
        mVector2.addElement(tabInfoChar);
        this.tabskills = new TabSkillsNew(T.tabkynang);
        mVector2.addElement(this.tabskills);
        TabQuest tabQuest = new TabQuest(T.tabnhiemvu);
        mVector2.addElement(tabQuest);
        mVector mVector3 = new mVector("Player mcmdTest");
        if (GameCanvas.isTouch) {
            TabConfig tabConfig = new TabConfig(T.tabchucnang, mVector3, 11);
            mVector2.addElement(tabConfig);
        } else {
            TabConfig tabConfig = new TabConfig(T.tabhethong, mVector3, 7);
            mVector2.addElement(tabConfig);
            TabConfig tabConfig2 = new TabConfig(T.tabchucnang, mVector3, 11);
            mVector2.addElement(tabConfig2);
        }
        GameCanvas.AllInfo.addMoreTab(mVector2);
    }

    public void commandPointer(int n, int n2) {
        switch (n) {
            case 0: {
                this.nextFocus();
                break;
            }
            case 1: {
                GlobalService.gI().gohome((byte)1);
                GameCanvas.start_Ok_Dialog(T.pleaseWait);
                break;
            }
            case 2: {
                GameCanvas.start_Left_Dialog(T.muonvelang, new iCommand(Player.cmdVeLang.caption, 3, this));
                break;
            }
            case 3: {
                GlobalService.gI().gohome((byte)0);
                GameCanvas.end_Dialog();
                break;
            }
            case 4: {
                GlobalService.gI().do_Buy_Sell_Item(4, null, "", (short)0, 0, (byte)0);
                break;
            }
            case 5: {
                GameCanvas.end_Dialog();
            }
        }
    }

    public void setPainthit(byte by, boolean bl) {
        this.timeHit = by;
        this.isMaxdame = bl;
    }

    public void paint(mGraphics mGraphics2) {
        this.paintPlayer(mGraphics2, 100);
        this.paintEffectCharWearing(mGraphics2);
        this.paintNameStore(mGraphics2, this.x, this.y);
    }

    public void paintNameStore(mGraphics mGraphics2, int n, int n2) {
        if (this.nameStore != null && !this.nameStore.name.equals("")) {
            this.nameStore.paint(mGraphics2);
        }
    }

    public void paintBigAvatar(mGraphics mGraphics2, int n, int n2) {
    }

    public void update() {
        try {
            if (this.moveToBoss && this.vx == 0 && this.vy == 0) {
                this.moveToBoss = false;
            }
            if (this.moveToBoss) {
                if (this.vx != 0) {
                    this.vx = 0;
                }
                if (this.vy != 0) {
                    this.vy = 0;
                }
                this.frameLeg = CRes.random(0, 4);
            }
            this.updateActionPerson();
            if (this.currentQuest == null) {
                this.setFocus();
            }
            this.setMove(true);
            this.updatePlayer();
            this.updateEye();
            if (party != null && party.update()) {
                party = null;
            }
            this.updateDelaySkill();
            this.updateDataEffect();
            if (timeResetAuto > 0) {
                --timeResetAuto;
            }
            if (!(this.vx == 0 && this.vy == 0 || this.isTanHinh || GameCanvas.gameTick % 3 != 0)) {
                if (!this.isWater && !this.isFootSnow) {
                    if (this.Direction == 2) {
                        GameScreen.addEffInMap(this.x + CRes.random_Am(0, 3), this.y + CRes.random_Am(0, 3) + mSystem.dyCharStep, 0, this.Direction);
                    } else if (this.Direction == 3) {
                        GameScreen.addEffInMap(this.x + CRes.random_Am(0, 3), this.y + CRes.random_Am(0, 3) - mSystem.dyCharStep, 0, this.Direction);
                    } else {
                        GameScreen.addEffInMap(this.x + CRes.random_Am(0, 3), this.y + CRes.random_Am(0, 3), 0, this.Direction);
                    }
                } else {
                    GameScreen.addEffInMap(this.x, this.y, 1, this.Direction);
                }
            }
            this.updateoverHP_MP();
            this.updateEffectCharWearing();
            if (this.nameStore != null) {
                this.nameStore.updatePos(this.x, this.y - this.hOne - 30);
            }
            super.update();
            if (this.timeHit > 0) {
                this.ispaintHit = true;
                this.timeHit = (byte)(this.timeHit - 1);
            } else {
                this.ispaintHit = false;
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private void updatePlayer() {
        if (isAutoHPMP && this.Action != 4 && GameCanvas.gameTick % 5 == 0) {
            Item item;
            HotKey hotKey;
            if (this.hp * 100 / this.maxHp < MsgDialog.mHPMP[0]) {
                hotKey = mhotkey[levelTab][4];
                if (hotKey.type == HotKey.POTION && hotKey.typePotion == 0 && (item = Item.getItemInventory(4, hotKey.id)) != null && item.typePotion < 2 && Player.timeDelayPotion[item.typePotion].value <= 0) {
                    GlobalService.gI().Use_Potion((short)item.Id);
                    Player.timeDelayPotion[item.typePotion].value = 2000;
                    Player.timeDelayPotion[item.typePotion].limit = 2000;
                    Player.timeDelayPotion[item.typePotion].timebegin = mSystem.currentTimeMillis();
                }
            }
            if (this.mp * 100 / this.maxMp < MsgDialog.mHPMP[1] && (hotKey = mhotkey[levelTab][3]) != null && hotKey.type == HotKey.POTION && hotKey.typePotion == 1 && (item = Item.getItemInventory(4, hotKey.id)) != null && Player.timeDelayPotion[item.typePotion].value <= 0) {
                GlobalService.gI().Use_Potion((short)item.Id);
                Player.timeDelayPotion[item.typePotion].value = 2000;
                Player.timeDelayPotion[item.typePotion].limit = 2000;
                Player.timeDelayPotion[item.typePotion].timebegin = mSystem.currentTimeMillis();
            }
        }
        if (this.KillFire != -1) {
            if (CRes.abs(this.x - this.xFire) <= 4 && CRes.abs(this.y - this.yFire) <= 4) {
                ++this.timeHuyKill;
                this.posTransRoad = null;
                this.ListKillNow.setFireSkill(this, this.vecObjFocusSkill, this.KillFire, (byte)-1);
                if (this.timeHuyKill > 5) {
                    this.ListKillNow.fireSkillFree();
                    this.timeHuyKill = 0;
                    this.KillFire = -1;
                }
            }
            if (this.posTransRoad == null && (CRes.abs(this.x - this.xFire) > 4 || CRes.abs(this.y - this.yFire) > 4)) {
                this.Move_to_Focus_Person();
            }
        } else {
            this.timeHuyKill = 0;
        }
        if (this.hp <= 0 && this.Action != 4) {
            this.resetAction();
            this.Action = 4;
            if (LoadMap.typeMap == 1) {
                GameScreen.gI().center = cmdVeLang;
                cmdVeLang.setPos(GameCanvas.hw, GameCanvas.h - iCommand.hButtonCmd / 2 - 2, null, Player.cmdVeLang.caption);
            } else if (LoadMap.typeMap == 2) {
                GameScreen.gI().center = cmdRevice;
                cmdRevice.setPos(GameCanvas.hw, GameCanvas.h - iCommand.hButtonCmd / 2 - 2, null, Player.cmdRevice.caption);
            } else if (!GameScreen.infoGame.isMapThachdau()) {
                GameScreen.gI().left = cmdRevice;
                GameScreen.gI().right = cmdVeLang;
                cmdRevice.setPos(iCommand.wButtonCmd / 2, GameCanvas.h - iCommand.hButtonCmd / 2 - 2, null, Player.cmdRevice.caption);
                cmdVeLang.setPos(GameCanvas.w - iCommand.wButtonCmd / 2, GameCanvas.h - iCommand.hButtonCmd / 2 - 2, null, Player.cmdVeLang.caption);
            }
            GameScreen.addEffectEndKill(11, this.x, this.y);
        }
        if (this.currentQuest == null) {
            if (this.Action == 4 && !GameScreen.infoGame.isMapThachdau()) {
                if (LoadMap.typeMap == 1) {
                    if (GameScreen.gI().center != cmdVeLang) {
                        GameScreen.gI().center = cmdVeLang;
                        cmdVeLang.setPos(GameCanvas.hw, GameCanvas.h - iCommand.hButtonCmd / 2 - 2, null, Player.cmdVeLang.caption);
                    }
                } else if (LoadMap.typeMap == 2) {
                    if (GameScreen.gI().center != cmdRevice) {
                        GameScreen.gI().center = cmdRevice;
                        cmdRevice.setPos(GameCanvas.hw, GameCanvas.h - iCommand.hButtonCmd / 2 - 2, null, Player.cmdRevice.caption);
                    }
                } else {
                    if (GameScreen.gI().left != cmdRevice) {
                        GameScreen.gI().left = cmdRevice;
                        cmdRevice.setPos(iCommand.wButtonCmd / 2, GameCanvas.h - iCommand.hButtonCmd / 2 - 2, null, Player.cmdRevice.caption);
                    }
                    if (GameScreen.gI().right != cmdVeLang) {
                        GameScreen.gI().right = cmdVeLang;
                        cmdVeLang.setPos(GameCanvas.w - iCommand.wButtonCmd / 2, GameCanvas.h - iCommand.hButtonCmd / 2 - 2, null, Player.cmdVeLang.caption);
                    }
                }
            } else if (GameCanvas.isTouch) {
                if (LoadMap.typeMap == 1) {
                    if (GameScreen.gI().center == cmdVeLang) {
                        GameScreen.gI().center = null;
                    }
                } else if (LoadMap.typeMap == 2) {
                    if (GameScreen.gI().center == cmdRevice) {
                        GameScreen.gI().center = null;
                    }
                } else {
                    if (GameScreen.gI().left == cmdRevice) {
                        GameScreen.gI().left = null;
                    }
                    if (GameScreen.gI().right == cmdVeLang) {
                        GameScreen.gI().right = null;
                    }
                }
            } else {
                if (this.center == cmdRevice) {
                    this.center = null;
                }
                if (GameScreen.gI().right == null || GameScreen.gI().right != cmdNextFocus) {
                    GameScreen.gI().right = cmdNextFocus;
                }
                if (GameScreen.gI().left != GameScreen.gI().cmdMenu) {
                    GameScreen.gI().left = GameScreen.gI().cmdMenu;
                }
            }
        }
        if (this.posTransRoad != null && !this.moveToBoss) {
            if (this.isBinded) {
                return;
            }
            if (CRes.abs(this.x - this.toX) <= 6 && CRes.abs(this.y - this.toY) <= 6) {
                if (this.countAutoMove > this.posTransRoad.length - 1) {
                    this.countAutoMove = 0;
                    this.posTransRoad = null;
                    timeStand = 0;
                    this.xStopMove = 0;
                    this.yStopMove = 0;
                } else {
                    if (this.countAutoMove == this.posTransRoad.length - 1 && this.xStopMove > 0 && this.yStopMove > 0) {
                        this.toX = this.xStopMove;
                        this.toY = this.yStopMove;
                    } else {
                        byte by = (byte)(this.posTransRoad[this.countAutoMove] >> 8);
                        byte by2 = (byte)(this.posTransRoad[this.countAutoMove] & 0xFF);
                        this.toX = by * LoadMap.wTile + LoadMap.wTile / 2;
                        this.toY = by2 * LoadMap.wTile + LoadMap.wTile / 2;
                    }
                    ++this.countAutoMove;
                }
            }
            this.move_to_XY();
        } else {
            if (!isSendMove) {
                isSendMove = true;
                this.xStand = this.x;
                this.yStand = this.y;
            }
            if (isLockKey && !GameScreen.help.setStep_Next(0, -4) && !GameScreen.help.setStep_Next(0, -2)) {
                isLockKey = false;
            }
        }
        int n = MainObject.getDistance(this.xStand, this.yStand, this.x, this.y);
        boolean bl = false;
        if (mSystem.currentTimeMillis() - this.timeSendmove > 250L && timeStand <= 0) {
            bl = true;
        }
        if (this.posTransRoad != null && mSystem.currentTimeMillis() - this.timeSendmove > 250L) {
            bl = true;
        }
        if (bl || timeStand > 20 && n >= 5 && this.posTransRoad == null) {
            if (isSendMove && !this.isMoveOut) {
                GlobalService.gI().player_move((short)this.x, (short)this.y);
                this.timeSendmove = mSystem.currentTimeMillis();
            }
            this.xStand = this.x;
            this.yStand = this.y;
            this.countSendMove = 0;
        }
        if (autoItem != null && GameCanvas.gameTick % 5 == 0) {
            this.autoGetItem();
        }
        if (isAutoFire == 1 && this.posTransRoad == null) {
            if (PointSucKhoe <= 0) {
                isAutoFire = 0;
            }
            if (isAutoFire == 1) {
                this.coutauto = this.Action == 0 ? ++this.coutauto : 0;
                if (this.coutauto > 500) {
                    GameCanvas.addInfoChar(T.farfocus);
                    this.KillFire = -1;
                    this.IDAttack = -1;
                    this.vecObjFocusSkill = null;
                    this.coutauto = 0;
                    this.dofire();
                }
            }
            if (this.Action != 2 && this.Action != 4 && (MainObject.getDistance(this.x, this.y, xBeginAutoFire, yBeginAutofire) > this.wFocus * 3 / 2 || demUnFire > 100)) {
                this.toX = this.x;
                this.toY = this.y;
                this.xStopMove = xBeginAutoFire;
                this.yStopMove = yBeginAutofire;
                this.posTransRoad = GameCanvas.game.updateFindRoad(this.xStopMove / LoadMap.wTile, this.yStopMove / LoadMap.wTile, this.x / LoadMap.wTile, this.y / LoadMap.wTile, 20);
                xFocus = -1;
                yFocus = -1;
                if (this.posTransRoad != null && this.posTransRoad.length > 20) {
                    this.posTransRoad = null;
                }
            }
            if (this.posTransRoad == null && this.Action != 2 && this.Action != 4) {
                this.setAutoFire();
            }
            if (this.Action == 4) {
                Player.setCurAutoFire();
            }
        }
        if (GameScreen.ObjFocus != null) {
            typeFocusLast = GameScreen.ObjFocus.typeObject;
            timeFocusLast = 0;
        } else if (timeFocusLast < 20) {
            timeFocusLast = (byte)(timeFocusLast + 1);
        }
        if ((this.vx != 0 || this.vy != 0) && timeFocusNPC < 5) {
            timeFocusNPC = 10;
        }
        if (!isFocusNPC && timeFocusNPC > 5 && ++timeFocusNPC > 60) {
            isFocusNPC = true;
            timeFocusNPC = 0;
        }
        if (isAutoFire == 1 && GameScreen.ObjFocus != null && MainObject.getDistance(xBeginAutoFire, yBeginAutofire, GameScreen.ObjFocus.x, GameScreen.ObjFocus.y) > 240) {
            this.nextMonster();
        }
    }

    private void autoGetItem() {
        int n;
        if (GameCanvas.gameTick % 200 == 0) {
            n = Item.VecInvetoryPlayer.size();
            if (isFullInven) {
                if (n < maxInven) {
                    isFullInven = false;
                } else {
                    GameCanvas.addInfoChar(T.fullInven);
                    if (GameScreen.ObjFocus != null && (GameScreen.ObjFocus.typeObject == 4 || GameScreen.ObjFocus.typeObject == 3 || GameScreen.ObjFocus.typeObject == 5 || GameScreen.ObjFocus.typeObject == 7 || GameScreen.ObjFocus.typeObject == 3)) {
                        this.nextFocus();
                    }
                }
            } else if (n >= maxInven) {
                isFullInven = true;
            }
        }
        for (n = 0; n < GameScreen.Vecplayers.size(); ++n) {
            MainObject mainObject = (MainObject)GameScreen.Vecplayers.elementAt(n);
            if (mainObject == null || mainObject.isSend || mainObject.typeObject != 4 && mainObject.typeObject != 3 && mainObject.typeObject != 5 && mainObject.typeObject != 7) continue;
            if (this.setGetItem(mainObject) && MainObject.getDistance(this.x, this.y, mainObject.x, mainObject.y) < this.wFocus) {
                GlobalService.gI().Get_Item_Map((short)mainObject.ID, mainObject.typeObject);
            }
            mainObject.isSend = true;
        }
    }

    public boolean isItem(MainObject mainObject) {
        return mainObject.typeObject == 4 || mainObject.typeObject == 3 || mainObject.typeObject == 5 || mainObject.typeObject == 7;
    }

    private boolean setGetItem(MainObject mainObject) {
        switch (mainObject.typeObject) {
            case 3: {
                if (isFullInven) {
                    return false;
                }
                if (Player.autoItem.valueColorItem == -1) {
                    return false;
                }
                if (mainObject.colorName < Player.autoItem.valueColorItem) break;
                return true;
            }
            case 4: {
                if (mainObject.colorName == 0) {
                    if (Player.autoItem.isGetPotion != AutoGetItem.POI_NHAT_MAU && Player.autoItem.isGetPotion != AutoGetItem.POI_NHAT_HET) break;
                    return true;
                }
                if (mainObject.colorName == 1) {
                    if (Player.autoItem.isGetPotion != AutoGetItem.POI_NHAT_NANG_LUONG && Player.autoItem.isGetPotion != AutoGetItem.POI_NHAT_HET) break;
                    return true;
                }
                if (mainObject.colorName == 2) {
                    return true;
                }
                if (mainObject.colorName == 6) {
                    return true;
                }
                return true;
            }
            case 5: 
            case 7: {
                return true;
            }
        }
        return false;
    }

    public void setAutoFire() {
        Object object;
        int n;
        if (!ListSkill.canAttack()) {
            return;
        }
        long l = System.currentTimeMillis();
        MainObject mainObject = GameScreen.ObjFocus;
        if (mainObject == null || mainObject.Action == 4 || mainObject.typeObject != 1 || mainObject.isSend || mainObject.isDie) {
            if (mainObject != null && mainObject.isDie) {
                GameScreen.ObjFocus = null;
            }
            this.nextMonster();
        }
        if (GameScreen.ObjFocus == null || GameScreen.ObjFocus.typeObject != 1 || GameScreen.ObjFocus.typeBoss == 2) {
            return;
        }
        if (isAutoBuff == 1 && (l - this.timeAutoBuff) / (long)ListSkill.limitTimeAtt > 2L) {
            this.timeAutoBuff = l;
            for (n = 0; n < MsgDialog.MaxSkillBuff; ++n) {
                if (MsgDialog.Autobuff[n][1] == 0 || !this.setDelaySkill(MsgDialog.Autobuff[n][0], -1)) continue;
                object = MainListSkill.getSkillFormId(MsgDialog.Autobuff[n][0]);
                if (((Skill)object).typeSkill != 1) continue;
                this.setAutoBuff(((Skill)object).typeBuff, MsgDialog.Autobuff[n][0], GameScreen.ObjFocus);
            }
        }
        if (IndexFire < mhotkey[0].length - 1) {
            for (n = IndexFire + 1; n < mhotkey[0].length; ++n) {
                object = mhotkey[levelTab][n];
                if (((HotKey)object).type != HotKey.SKILL || !this.setDelaySkill(((HotKey)object).id, -1)) continue;
                this.setActionHotKey(n, false);
                timeFristSkill = l;
                IndexFire = (byte)n;
                return;
            }
        }
        if (IndexFire > 0) {
            for (n = 0; n <= IndexFire; ++n) {
                object = mhotkey[levelTab][n];
                if (((HotKey)object).type != HotKey.SKILL || !this.setDelaySkill(((HotKey)object).id, -1)) continue;
                this.setActionHotKey(n, false);
                timeFristSkill = l;
                IndexFire = (byte)n;
                return;
            }
        }
        if (this.setDelaySkill(0, -1)) {
            this.setActionHotKey(-1, false);
            timeFristSkill = l;
        }
    }

    public void nextMonster() {
        if (!isCurAutoFire) {
            isAutoFire = (byte)-1;
            return;
        }
        int n = this.wFocus * 3 / 2;
        MainObject mainObject = null;
        for (int i = 0; i < GameScreen.Vecplayers.size(); ++i) {
            int n2;
            MainObject mainObject2 = (MainObject)GameScreen.Vecplayers.elementAt(i);
            if (mainObject2 == null || mainObject2.Action == 4 || mainObject2.isSend || mainObject2.typeObject != 1 || mainObject2.isLuaThieng() || (n2 = MainObject.getDistance(this.x, this.y, mainObject2.x, mainObject2.y)) >= n && mainObject2.typeBoss != 2) continue;
            n = n2;
            mainObject = mainObject2;
            if (mainObject2.typeBoss == 2) break;
        }
        if (mainObject != null) {
            PaintInfoGameScreen.isPaintInfoFocus = true;
            GameScreen.ObjFocus = mainObject;
            demUnFire = 0;
        } else {
            PaintInfoGameScreen.isPaintInfoFocus = false;
            demUnFire = (short)(demUnFire + 1);
        }
    }

    public void updateKey() {
        if (!this.Canmove()) {
            return;
        }
        if (LoadMap.isShowEffAuto == LoadMap.EFF_PHOBANG_END) {
            return;
        }
        if (isLockKey) {
            return;
        }
        if (this.currentQuest != null) {
            if (!GameCanvas.menu2.isShowMenu) {
                if (++timeQuest > 100) {
                    this.currentQuest = null;
                    GameScreen.gI().center = null;
                    GameCanvas.clearKeyHold();
                    GameCanvas.isPointerSelect = false;
                }
            } else {
                timeQuest = 0;
            }
        } else {
            if (this.Action != 4 && !this.isTanHinh) {
                if (!(this.isBinded || this.isSleep || this.isStun || this.isMoveOut || this.isDongBang || this.Action == 2 || this.Action == 3)) {
                    this.vx = 0;
                    this.vy = 0;
                    if (GameCanvas.keyMove(2)) {
                        if (!this.isSelling()) {
                            this.Action = 1;
                            this.Direction = 2;
                            this.vx = -(this.vMax + this.getVmount());
                            this.vy = 0;
                            this.resetMove();
                            this.setValueAuto(0);
                        } else {
                            mVector mVector2 = new mVector();
                            mVector2.addElement(new iCommand(T.yes, 4, this));
                            mVector2.addElement(new iCommand(T.cancel, 5, this));
                            GameCanvas.start_Select_Dialog(T.can_not_move, mVector2);
                        }
                    } else if (GameCanvas.keyMove(3)) {
                        if (!this.isSelling()) {
                            this.Action = 1;
                            this.Direction = 3;
                            this.vx = this.vMax + this.getVmount();
                            this.vy = 0;
                            this.resetMove();
                            this.setValueAuto(0);
                        } else {
                            mVector mVector3 = new mVector();
                            mVector3.addElement(new iCommand(T.yes, 4, this));
                            mVector3.addElement(new iCommand(T.cancel, 5, this));
                            GameCanvas.start_Select_Dialog(T.can_not_move, mVector3);
                        }
                    } else if (GameCanvas.keyMove(1)) {
                        if (!this.isSelling()) {
                            this.Action = 1;
                            this.Direction = 1;
                            this.vy = -(this.vMax + this.getVmount());
                            this.vx = 0;
                            this.resetMove();
                            this.setValueAuto(0);
                        } else {
                            mVector mVector4 = new mVector();
                            mVector4.addElement(new iCommand(T.yes, 4, this));
                            mVector4.addElement(new iCommand(T.cancel, 5, this));
                            GameCanvas.start_Select_Dialog(T.can_not_move, mVector4);
                        }
                    } else if (GameCanvas.keyMove(0)) {
                        if (!this.isSelling()) {
                            this.Action = 1;
                            this.Direction = 0;
                            this.vy = this.vMax + this.getVmount();
                            this.vx = 0;
                            this.resetMove();
                            this.setValueAuto(0);
                        } else {
                            mVector mVector5 = new mVector();
                            mVector5.addElement(new iCommand(T.yes, 4, this));
                            mVector5.addElement(new iCommand(T.cancel, 5, this));
                            GameCanvas.start_Select_Dialog(T.can_not_move, mVector5);
                        }
                    }
                    timeStand = this.vx == 0 && this.vy == 0 ? ++timeStand : 0;
                }
                if (GameCanvas.keyMyHold[10] && !GameCanvas.isTouch) {
                    GameCanvas.clearKeyHold(10);
                    boolean bl = ChatTextField.isShow = !ChatTextField.isShow;
                }
                if (GameCanvas.keyMyHold[11] && !GameCanvas.isTouch) {
                    GameCanvas.clearKeyHold(11);
                    GameCanvas.mevent.init();
                    GameCanvas.mevent.Show(GameCanvas.currentScreen);
                }
            }
            if (GameCanvas.keyMyPressed[21]) {
                this.setActionHotKey(0, true);
            } else if (GameCanvas.keyMyPressed[23]) {
                this.setActionHotKey(1, true);
            } else if (GameCanvas.keyMyHold[5] || GameCanvas.keyMyPressed[25]) {
                this.setActionHotKey(2, true);
            } else if (GameCanvas.keyMyPressed[27]) {
                this.setActionHotKey(3, true);
            } else if (GameCanvas.keyMyPressed[29]) {
                this.setActionHotKey(4, true);
            }
        }
        if (GameCanvas.keyMyHold[20]) {
            if ((GameScreen.ObjFocus == null || GameScreen.ObjFocus.typeBoss != 2) && PaintInfoGameScreen.timeChange == 0) {
                PaintInfoGameScreen.timeChange = 1;
            }
            GameCanvas.clearKeyHold(20);
        }
    }

    public void setFocus() {
        if (isAutoFire == 1) {
            return;
        }
        if (GameScreen.ObjFocus != null && (GameScreen.ObjFocus.typeBoss == 2 || GameScreen.ObjFocus.isLuaThieng())) {
            return;
        }
        if (GameScreen.ObjFocus != null) {
            if (GameScreen.ObjFocus.Action == 4 && GameScreen.ObjFocus.typeObject != 0 || MainObject.getDistance(GameScreen.ObjFocus.x, GameScreen.ObjFocus.y, this.x, this.y) > this.wFocus + 20 || GameScreen.ObjFocus.isDie && GameScreen.ObjFocus.typeObject == 1) {
                if (!GameScreen.infoGame.isMapThachdau()) {
                    GameScreen.ObjFocus = null;
                    PaintInfoGameScreen.isPaintInfoFocus = false;
                }
            } else if (!(GameCanvas.isTouch || GameScreen.ObjFocus.typeObject != 2 && (GameScreen.ObjFocus.typeObject != 0 || this.setFirePlayer(GameScreen.ObjFocus) || GameScreen.gI().center == GameScreen.gI().cmdGiaotiep || this.typePk != -1 && this.typePk != 10 && !GameCanvas.loadmap.mapLang()))) {
                GameScreen.gI().center = GameScreen.gI().cmdGiaotiep;
            }
        }
        if (GameScreen.ObjFocus == null) {
            int n;
            if (GameScreen.gI().center != null) {
                GameScreen.gI().center = null;
            }
            if (this.indexFocus > (n = GameScreen.Vecplayers.size()) - 1) {
                this.indexFocus = n - 1;
            }
            int n2 = 1000;
            int n3 = -1;
            MainObject mainObject = null;
            for (int i = 0; i < n; ++i) {
                int n4 = 0;
                MainObject mainObject2 = (MainObject)GameScreen.Vecplayers.elementAt((i + this.indexFocus) % n);
                if (mainObject2 != null) {
                    n4 = MainObject.getDistance(mainObject2.x, mainObject2.y, this.x, this.y);
                }
                if (n4 > this.wFocus) continue;
                int n5 = this.setTypeFocus(mainObject2.typeObject);
                if (mainObject2.ID == GameScreen.player.ID || mainObject2.Action == 4 && mainObject2.typeObject != 0 || mainObject2.isRemove || n5 < n3 || mainObject2.isDie && mainObject2.typeObject == 1 || mainObject2.typeObject == 9 || mainObject2.isLuaThieng() || mainObject2.isDongBang || n4 >= this.wFocus && (mainObject == null || mainObject.typeObject != 0 && mainObject.typeObject != 2 || mainObject2.typeObject != 3 && mainObject2.typeObject != 1 && mainObject2.typeObject != 4 && mainObject2.typeObject != 5 && mainObject2.typeObject != 7) || n4 >= n2 && n5 <= n3 || (mainObject2.typeObject == 0 || mainObject2.typeObject == 2) && timeFocusLast < 20 && typeFocusLast != 0 && typeFocusLast != 2) continue;
                mainObject = mainObject2;
                n2 = n4;
                this.indexFocus = i;
                n3 = n5;
            }
            if (mainObject != null) {
                if (!mainObject.canFocus()) {
                    return;
                }
                GameScreen.ObjFocus = mainObject;
                if (!GameCanvas.isTouch) {
                    if (GameScreen.ObjFocus != null && GameScreen.ObjFocus.typeObject == 2 || GameScreen.ObjFocus.typeObject == 0 && !this.setFirePlayer(GameScreen.ObjFocus) && (this.typePk == -1 || this.typePk == 10 || GameCanvas.loadmap.mapLang())) {
                        GameScreen.gI().center = GameScreen.gI().cmdGiaotiep;
                    } else if (GameScreen.gI().center == GameScreen.gI().cmdGiaotiep) {
                        GameScreen.gI().center = null;
                    }
                    GameScreen.gI().right = cmdNextFocus;
                }
            } else if (GameScreen.gI().right == cmdNextFocus) {
                GameScreen.gI().right = null;
            }
        }
    }

    public boolean setStatusPk(MainObject mainObject) {
        if (GameCanvas.loadmap.mapLang()) {
            return true;
        }
        if (mainObject.typeObject == 2) {
            return true;
        }
        if (this.typePk == -1 || this.typePk == 10) {
            return true;
        }
        if (mainObject.typeObject == 1 && mainObject.typeMonster == 7) {
            return true;
        }
        if (mainObject.isMonstervantieu()) {
            return true;
        }
        if (mainObject.typeObject != 0) {
            return false;
        }
        if (mainObject.typePk == 0 || mainObject.typePk == 10) {
            return true;
        }
        if (mainObject.typePk != this.typePk && mainObject.typePk != -1) {
            return true;
        }
        return this.typePk == 0;
    }

    private int setTypeFocus(int n) {
        int n2 = -1;
        switch (n) {
            case 0: 
            case 2: {
                n2 = 0;
                break;
            }
            case 1: {
                n2 = 1;
                break;
            }
            case 3: 
            case 4: 
            case 5: 
            case 7: {
                n2 = 2;
            }
        }
        return n2;
    }

    public void nextFocus() {
        MainObject mainObject;
        int n;
        if (GameScreen.ObjFocus != null && GameScreen.ObjFocus.typeBoss == 2) {
            return;
        }
        this.setValueAuto(0);
        MainObject mainObject2 = null;
        int n2 = -1;
        int n3 = -1;
        if (isFocusNPC) {
            for (n = 0; n < GameScreen.Vecplayers.size(); ++n) {
                mainObject = (MainObject)GameScreen.Vecplayers.elementAt(n);
                if (GameScreen.infoGame.isMapArena(GameCanvas.loadmap.idMap) && mainObject.typePk == this.typePk && mainObject.typeObject == 0 || !mainObject.isNPC()) continue;
                if (GameScreen.ObjFocus != null && mainObject == GameScreen.ObjFocus) {
                    n3 = n;
                    continue;
                }
                if (mainObject2 != null && n3 < 0 || MainObject.getDistance(mainObject.x, mainObject.y, this.x, this.y) >= this.wFocus) continue;
                mainObject2 = mainObject;
                n2 = n;
                this.indexFocus = n;
                GameScreen.ObjFocus = mainObject2;
                if (!GameCanvas.isTouch) {
                    if (mainObject2.typeObject == 2 || GameScreen.ObjFocus != null && GameScreen.ObjFocus.typeObject == 0 && !this.setFirePlayer(GameScreen.ObjFocus) && (this.typePk == -1 || this.typePk == 10 || GameCanvas.loadmap.mapLang())) {
                        GameScreen.gI().center = GameScreen.gI().cmdGiaotiep;
                    } else if (GameScreen.gI().center == GameScreen.gI().cmdGiaotiep) {
                        GameScreen.gI().center = null;
                    }
                }
                isFocusNPC = false;
                return;
            }
        }
        mainObject2 = null;
        n2 = -1;
        n3 = -1;
        for (n = 0; n < GameScreen.Vecplayers.size(); ++n) {
            mainObject = (MainObject)GameScreen.Vecplayers.elementAt(n);
            if (mainObject == GameScreen.player || mainObject.Action == 4 && mainObject.typeObject != 0 || !this.setStatusPk(mainObject) || mainObject.typeObject == 9 || mainObject.typeObject == 10 || mainObject.isLuaThieng() || mainObject.isDongBang || !mainObject.canFocus() || PaintInfoGameScreen.isMarket(GameCanvas.loadmap.idMap) && mainObject.typeObject == 0 && !mainObject.isSelling() || this.isItem(mainObject) && autoItem != null) continue;
            if (GameScreen.ObjFocus != null && mainObject == GameScreen.ObjFocus) {
                n3 = n;
            } else if ((mainObject2 == null || n3 >= 0) && MainObject.getDistance(mainObject.x, mainObject.y, this.x, this.y) < this.wFocus) {
                mainObject2 = mainObject;
                n2 = n;
            }
            if (n3 < 0 || n2 <= n3) continue;
            this.indexFocus = n;
            GameScreen.ObjFocus = mainObject2;
            if (!GameCanvas.isTouch) {
                if (mainObject2.typeObject == 2 || GameScreen.ObjFocus.typeObject == 0 && !this.setFirePlayer(GameScreen.ObjFocus) && (this.typePk == -1 || this.typePk == 10 || GameCanvas.loadmap.mapLang())) {
                    GameScreen.gI().center = GameScreen.gI().cmdGiaotiep;
                } else if (GameScreen.gI().center == GameScreen.gI().cmdGiaotiep) {
                    GameScreen.gI().center = null;
                }
            }
            return;
        }
        if (mainObject2 != null) {
            GameScreen.ObjFocus = mainObject2;
            if (!GameCanvas.isTouch) {
                if (mainObject2.typeObject == 2 || GameScreen.ObjFocus.typeObject == 0 && !this.setFirePlayer(GameScreen.ObjFocus) && (this.typePk == -1 || this.typePk == 10 || GameCanvas.loadmap.mapLang())) {
                    GameScreen.gI().center = GameScreen.gI().cmdGiaotiep;
                } else if (GameScreen.gI().center == GameScreen.gI().cmdGiaotiep) {
                    GameScreen.gI().center = null;
                }
            }
        }
    }

    public void setActionHotKey(int n, boolean bl) {
        HotKey hotKey;
        GameCanvas.clearAll();
        if (this.Action != 4 && GameScreen.ObjFocus != null && GameScreen.ObjFocus.typeObject == 1 && GameScreen.ObjFocus.typeBoss == 2) {
            if (n >= 0 && n <= 5) {
                GlobalService.gI().Mon_Capchar((byte)n);
            }
            return;
        }
        if (GameScreen.ObjFocus != null && GameScreen.ObjFocus.typeObject == 1) {
            if (GameScreen.ObjFocus.isMonstervantieu()) {
                if (GameScreen.ObjFocus.getnameOwner().equals(this.name)) {
                    GameScreen.gI().cmdInfoVantieu.perform();
                    return;
                }
                if (!GameScreen.ObjFocus.getnameOwner().equals(this.name)) {
                    if (this.typePk != 0 && !this.isPkVantieu()) {
                        return;
                    }
                    if (this.typePk == 11 ? !GameScreen.ObjFocus.isMonsterVantieuDen() : (this.typePk == 12 ? !GameScreen.ObjFocus.isMonsterVantieuTrang() : this.typePk == 13 && !GameScreen.ObjFocus.isMonsterVantieuDen())) {
                        return;
                    }
                }
            }
            if (GameScreen.ObjFocus.isMiNuong()) {
                GameScreen.gI().cmdinfoMiNuong.perform();
                return;
            }
        }
        if (n == -1) {
            hotKey = new HotKey();
            hotKey.setHotKey(0, HotKey.SKILL, (byte)0);
        } else {
            hotKey = mhotkey[levelTab][n];
        }
        if (hotKey.type == HotKey.SKILL) {
            skillDefault = hotKey.id;
        }
        if (hotKey.type == HotKey.POTION) {
            Item item = Item.getItemInventory(4, hotKey.id);
            if (item != null && item.typePotion < 2) {
                if (!(Player.timeDelayPotion[item.typePotion].value > 0 || item.typePotion == 0 && this.hp == this.maxHp || item.typePotion == 1 && this.mp == this.maxMp)) {
                    GlobalService.gI().Use_Potion((short)item.Id);
                    Player.timeDelayPotion[item.typePotion].value = 2000;
                    Player.timeDelayPotion[item.typePotion].limit = 2000;
                    Player.timeDelayPotion[item.typePotion].timebegin = mSystem.currentTimeMillis();
                }
            } else if (GameScreen.ObjFocus != null && GameScreen.ObjFocus.typeObject == 2 && n == 2) {
                GameScreen.gI().cmdGiaotiep.perform();
                return;
            }
            return;
        }
        if (GameScreen.ObjFocus == null) {
            if (hotKey.type == HotKey.SKILL && this.setDelaySkill(hotKey.id, n)) {
                Skill skill = MainListSkill.getSkillFormId(hotKey.id);
                if (skill.typeSkill == 1 && (skill.typeBuff == 1 || skill.typeBuff == 2)) {
                    this.posTransRoad = null;
                    this.IDAttack = this.ID;
                    mVector mVector2 = new mVector("Player vec");
                    Object_Effect_Skill object_Effect_Skill = new Object_Effect_Skill((short)this.ID, this.typeObject);
                    mVector2.addElement(object_Effect_Skill);
                    this.ListKillNow.setFireSkill(this, mVector2, hotKey.id, (byte)-1);
                }
            }
        } else {
            if (GameScreen.ObjFocus.typeObject == 3 || GameScreen.ObjFocus.typeObject == 4 || GameScreen.ObjFocus.typeObject == 5 || GameScreen.ObjFocus.typeObject == 7) {
                if (!GameScreen.ObjFocus.isSend) {
                    GlobalService.gI().Get_Item_Map((short)GameScreen.ObjFocus.ID, GameScreen.ObjFocus.typeObject);
                    GameScreen.ObjFocus.isSend = true;
                }
                return;
            }
            if (GameScreen.ObjFocus.typeObject == 2 && n == 2) {
                GameScreen.gI().cmdGiaotiep.perform();
                return;
            }
            if (this.Action == 2 || this.Action == 4 || this.currentQuest != null || this.isTanHinh) {
                if (this.Action == 4 && GameScreen.ObjFocus.typeObject == 0 && n == 2 && (this.typePk == -1 || this.typePk == 10 || GameCanvas.loadmap.mapLang())) {
                    GameScreen.gI().cmdGiaotiep.perform();
                }
                return;
            }
            if (hotKey.type == HotKey.SKILL && this.setDelaySkill(hotKey.id, n)) {
                Skill skill = MainListSkill.getSkillFormId(hotKey.id);
                if (skill.typeSkill == 1) {
                    if (this.setAutoBuff(skill.typeBuff, hotKey.id, GameScreen.ObjFocus)) {
                        return;
                    }
                } else if (skill.typeSkill == 0 && GameScreen.ObjFocus.Action != 4 && GameScreen.ObjFocus.typeObject != 2) {
                    if (this.setFirePlayer(GameScreen.ObjFocus)) {
                        this.setStartSkill(hotKey.id, bl);
                        return;
                    }
                    if (GameScreen.ObjFocus.typeObject == 1 && GameScreen.ObjFocus.typeMonster == 7 && this.myClan != null && GameScreen.ObjFocus.myClan != null && this.myClan.IdClan == GameScreen.ObjFocus.myClan.IdClan) {
                        mVector mVector3 = new mVector("Player vec2");
                        Object_Effect_Skill object_Effect_Skill = new Object_Effect_Skill((short)GameScreen.ObjFocus.ID, GameScreen.ObjFocus.typeObject);
                        mVector3.addElement(object_Effect_Skill);
                        mSystem.println("Att 1");
                        GlobalService.gI().fire_monster(mVector3, (byte)0);
                        if (isAutoFire == 1) {
                            Player.setCurAutoFire();
                        }
                        return;
                    }
                }
            }
            if (GameScreen.ObjFocus != null && GameScreen.ObjFocus.typeObject == 0 && n == 2 && !this.setFirePlayer(GameScreen.ObjFocus)) {
                if (GameScreen.ObjFocus.isSelling()) {
                    GameScreen.gI().cmdplayerStore.perform();
                } else if (this.typePk == -1 || this.typePk == 10 || GameCanvas.loadmap.mapLang()) {
                    GameScreen.gI().cmdGiaotiep.perform();
                    return;
                }
            }
        }
    }

    public void setStartSkill(int n, boolean bl) {
        this.posTransRoad = null;
        if (GameScreen.ObjFocus == null) {
            return;
        }
        this.IDAttack = GameScreen.ObjFocus.ID;
        mVector mVector2 = new mVector("Player vec8");
        Object_Effect_Skill object_Effect_Skill = new Object_Effect_Skill((short)GameScreen.ObjFocus.ID, GameScreen.ObjFocus.typeObject);
        mVector2.addElement(object_Effect_Skill);
        this.ListKillNow.setFireSkill(this, mVector2, n, (byte)-1);
        timeFristSkill = System.currentTimeMillis();
        if (GameScreen.ObjFocus.typeObject == 1 && isAutoFire == 0) {
            isAutoFire = 1;
            isCurAutoFire = true;
            xBeginAutoFire = this.x;
            yBeginAutofire = this.y;
        }
    }

    public boolean setDelaySkill(int n, int n2) {
        if (mCurentLvSkill[n] - 1 < 0) {
            if (n2 >= 0) {
                if (n2 == 2) {
                    mhotkey[levelTab][n2].setHotKey(0, HotKey.SKILL, (byte)0);
                } else {
                    Player.mhotkey[Player.levelTab][n2].type = HotKey.NULL;
                }
            }
            return false;
        }
        if (Player.timeDelaySkill[n].value > 0) {
            return false;
        }
        short s = MainListSkill.getSkillFormId((int)n).mLvSkill[Player.mCurentLvSkill[n] + Player.mPlusLvSkill[n] - 1].mpLost;
        if (this.mp < s) {
            return false;
        }
        if (this.typeMount == 0) {
            GameCanvas.addInfoChar(T.TisNguaNau);
            return false;
        }
        return this.KillFire != n;
    }

    public mVector setSkillLan(int n, MainObject mainObject) {
        mVector mVector2;
        block7: {
            Skill skill;
            byte by;
            Object_Effect_Skill object_Effect_Skill;
            block6: {
                mVector2 = new mVector("Player vec7");
                object_Effect_Skill = new Object_Effect_Skill((short)mainObject.ID, mainObject.typeObject);
                mVector2.addElement(object_Effect_Skill);
                by = mainObject.typeObject;
                skill = MainListSkill.getSkillFormId(n);
                if (mCurentLvSkill[skill.Id] == 0) {
                    return mVector2;
                }
                if (skill.typeSkill != 1) break block6;
                this.ListKillNow.classbuff = this.clazz;
                this.ListKillNow.typebuff = skill.typeBuff;
                if (skill.mLvSkill[Player.mCurentLvSkill[skill.Id] + Player.mPlusLvSkill[skill.Id] - 1].nTarget <= 0 || by != 0 && skill.typeBuff != 3) break block7;
                byte by2 = 1;
                for (int i = 0; i < GameScreen.Vecplayers.size(); ++i) {
                    MainObject mainObject2 = (MainObject)GameScreen.Vecplayers.elementAt(i);
                    if (mainObject2.ID == mainObject.ID || mainObject2 == GameScreen.player || by != mainObject2.typeObject || mainObject2.Action == 4 || by == 0 && (skill.typeBuff == 3 && !this.setFirePlayer(mainObject2) || skill.typeBuff == 2 && this.setFirePlayer(mainObject2))) continue;
                    if (MainObject.getDistance(mainObject.x, mainObject.y, mainObject2.x, mainObject2.y) <= skill.mLvSkill[Player.mCurentLvSkill[skill.Id] + Player.mPlusLvSkill[skill.Id] - 1].range_lan) {
                        object_Effect_Skill = new Object_Effect_Skill((short)mainObject2.ID, mainObject2.typeObject);
                        mVector2.addElement(object_Effect_Skill);
                        ++by2;
                    }
                    if (by2 < skill.mLvSkill[Player.mCurentLvSkill[skill.Id] + Player.mPlusLvSkill[skill.Id] - 1].nTarget) continue;
                    return mVector2;
                }
                break block7;
            }
            if (skill.mLvSkill[Player.mCurentLvSkill[skill.Id] + Player.mPlusLvSkill[skill.Id] - 1].nTarget > 1) {
                byte by3 = 1;
                for (int i = 0; i < GameScreen.Vecplayers.size(); ++i) {
                    MainObject mainObject3 = (MainObject)GameScreen.Vecplayers.elementAt(i);
                    if (mainObject3.ID == mainObject.ID || mainObject3 == GameScreen.player || by != mainObject3.typeObject || mainObject3.Action == 4 || by == 0 && !this.setFirePlayer(mainObject3)) continue;
                    if (MainObject.getDistance(mainObject.x, mainObject.y, mainObject3.x, mainObject3.y) <= skill.mLvSkill[Player.mCurentLvSkill[skill.Id] + Player.mPlusLvSkill[skill.Id] - 1].range_lan) {
                        object_Effect_Skill = new Object_Effect_Skill((short)mainObject3.ID, mainObject3.typeObject);
                        mVector2.addElement(object_Effect_Skill);
                        ++by3;
                    }
                    if (by3 < skill.mLvSkill[Player.mCurentLvSkill[skill.Id] + Player.mPlusLvSkill[skill.Id] - 1].nTarget) continue;
                    return mVector2;
                }
            }
        }
        return mVector2;
    }

    public void setAddDelaySkill(int n, int n2) {
        if (n == 35) {
            for (int i = 0; i < mKillPlayer.length; ++i) {
                Player.timeDelaySkill[i].value = 2000;
                Player.timeDelaySkill[i].limit = 2000;
                Player.timeDelaySkill[i].timebegin = mSystem.currentTimeMillis();
            }
        } else if (mKillPlayer[n2] == n) {
            Skill skill;
            LvSkill lvSkill = MainListSkill.getSkillFormId((int)n2).mLvSkill[mCurentLvSkill[n2] + mPlusLvSkill[n2] - 1];
            Player.timeDelaySkill[n2].value = lvSkill.getdelay();
            Player.timeDelaySkill[n2].limit = lvSkill.getdelay();
            Player.timeDelaySkill[n2].typeSkill = HotKey.SKILL;
            Player.timeDelaySkill[n2].timebegin = mSystem.currentTimeMillis();
            if (lvSkill.mpLost > 0) {
                this.mp -= lvSkill.mpLost;
            }
            if ((skill = MainListSkill.getSkillFormId(n2)) != null && skill.typeSkill == 0) {
                Player.timeDelaySkill[n2].isSkillAttack = true;
            }
        }
    }

    public void resetCoolDown() {
        for (int i = 0; i < timeDelaySkill.length; ++i) {
            if (!Player.timeDelaySkill[i].isSkillAttack) continue;
            Player.timeDelaySkill[i].value = 0;
        }
    }

    public void updateDelaySkill() {
        int n;
        for (n = 0; n < timeDelaySkill.length; ++n) {
            if (timeDelaySkill[n] == null || Player.timeDelaySkill[n].value <= -150) continue;
            Player.timeDelaySkill[n].value -= (int)(mSystem.currentTimeMillis() - Player.timeDelaySkill[n].timebegin);
            Player.timeDelaySkill[n].timebegin = mSystem.currentTimeMillis();
        }
        for (n = 0; n < timeDelayPotion.length; ++n) {
            if (Player.timeDelayPotion[n].value <= -150) continue;
            Player.timeDelayPotion[n].value -= (int)(mSystem.currentTimeMillis() - Player.timeDelayPotion[n].timebegin);
            Player.timeDelayPotion[n].timebegin = mSystem.currentTimeMillis();
        }
    }

    public void resetMove() {
        if (this.KillFire != -1) {
            this.KillFire = -1;
            this.posTransRoad = null;
            this.toX = this.x;
            this.toY = this.y;
            this.xStopMove = 0;
            this.yStopMove = 0;
        }
    }

    public boolean setFirePlayer(MainObject mainObject) {
        if (mainObject.canfire()) {
            return true;
        }
        if (mainObject.isLuaThieng()) {
            return false;
        }
        if (GameCanvas.loadmap.mapLang()) {
            return false;
        }
        if (mainObject == null) {
            return false;
        }
        if (this.isStun || this.isDongBang) {
            return false;
        }
        if (mainObject.isSleep) {
            return true;
        }
        if (mainObject.isMoveOut) {
            return true;
        }
        if (mainObject.typeObject == 0 && mainObject.typeSpec == 1) {
            if (mainObject.typePk >= 1 && mainObject.typePk <= 9 && this.typePk >= 1 && this.typePk <= 9 && mainObject.typePk == this.typePk) {
                return false;
            }
            return mainObject.myClan == null || GameScreen.player.myClan == null || mainObject.myClan.IdClan != GameScreen.player.myClan.IdClan;
        }
        if (this.Action == 4 || mainObject.Action == 4) {
            return false;
        }
        if (mainObject.typeObject == 0) {
            if (mainObject.typePk >= 1 && mainObject.typePk <= 9 && this.typePk >= 1 && this.typePk <= 9 && mainObject.typePk == this.typePk) {
                return false;
            }
            if (this.pointPk >= 2000) {
                return false;
            }
            if (this.typePk == 12 && (mainObject.typePk == 13 || mainObject.typePk == 11)) {
                return true;
            }
            if (this.typePk == 12 && mainObject.typePk == 12) {
                return false;
            }
            if (this.typePk == 13 && mainObject.typePk == 12) {
                return true;
            }
            if (this.typePk == 13 && (mainObject.typePk == 13 || mainObject.typePk == 11)) {
                return false;
            }
            if (this.typePk == 11 && mainObject.typePk == 12) {
                return true;
            }
            if (this.typePk == 11 && (mainObject.typePk == 11 || mainObject.typePk == 13)) {
                return false;
            }
            if (this.typePk == 10 && this.setPlayerPk((short)mainObject.ID)) {
                return true;
            }
            if (mainObject.typePk == 0 || mainObject.typePk == 10) {
                return true;
            }
            if (mainObject.typePk != -1 && this.typePk != -1 && this.typePk != mainObject.typePk && this.typePk != 10) {
                return true;
            }
            return mainObject.Lv > 10 && this.typePk == 0;
        }
        if (PointSucKhoe <= 0) {
            GameCanvas.addInfoChar(T.yeusuckhoe);
            return false;
        }
        return mainObject.typeObject != 1 || mainObject.typeMonster != 7 || this.myClan == null || mainObject.myClan == null || this.myClan.IdClan != mainObject.myClan.IdClan;
    }

    public boolean setPlayerPk(short s) {
        for (int i = 0; i < vecPlayerPk.length; ++i) {
            if (s != vecPlayerPk[i]) continue;
            return true;
        }
        return false;
    }

    public void dofire() {
        if (isAutoFire == 0) {
            isAutoFire = 1;
            xBeginAutoFire = this.x;
            yBeginAutofire = this.y;
            timeResetAuto = 60;
            isCurAutoFire = true;
        } else if (isAutoFire == -1) {
            GameCanvas.keyMyPressed[25] = true;
            GameCanvas.keyMyPressed[5] = true;
            isAutoFire = 1;
            xBeginAutoFire = this.x;
            yBeginAutofire = this.y;
            timeResetAuto = 60;
            isCurAutoFire = false;
        }
    }

    public void dofire_() {
        isAutoFire = 1;
        xBeginAutoFire = this.x;
        yBeginAutofire = this.y;
        timeResetAuto = 60;
        isCurAutoFire = true;
    }

    public void setPointFocus() {
        if (GameScreen.ObjFocus == null) {
            return;
        }
        if (GameScreen.ObjFocus.typeBoss == 2) {
            return;
        }
        if (GameScreen.ObjFocus.isNPC()) {
            GameScreen.gI().cmdGiaotiep.perform();
            if (isAutoFire == 1) {
                Player.setCurAutoFire();
            }
            GameCanvas.isPointerSelect = false;
            return;
        }
        if (GameScreen.ObjFocus.typeObject == 0) {
            if (this.setFirePlayer(GameScreen.ObjFocus)) {
                HotKey hotKey = mhotkey[levelTab][IndexFire];
                if (hotKey.type != HotKey.SKILL) {
                    for (int i = 0; i < mhotkey[levelTab].length; ++i) {
                        hotKey = mhotkey[levelTab][i];
                        if (hotKey.type != HotKey.SKILL) continue;
                        IndexFire = (byte)i;
                        if (isAutoFire != 1) break;
                        Player.setCurAutoFire();
                        break;
                    }
                }
                this.setActionHotKey(IndexFire, false);
            } else if (GameScreen.ObjFocus.isSelling()) {
                GameScreen.gI().cmdplayerStore.perform();
            } else if (this.typePk == -1 || this.typePk == 10 || GameCanvas.loadmap.mapLang()) {
                GameScreen.gI().cmdGiaotiep.setPos(GameScreen.ObjFocus.x - MainScreen.cameraMain.xCam, GameScreen.ObjFocus.y - MainScreen.cameraMain.yCam - GameScreen.ObjFocus.hOne - 30, PaintInfoGameScreen.fraContact, "");
                GameScreen.gI().cmdGiaotiep.IdGiaotiep = GameScreen.ObjFocus.ID;
                GameScreen.timePaintCmdGiaotiep = (byte)60;
            }
            return;
        }
        if (GameScreen.ObjFocus.typeObject == 4 || GameScreen.ObjFocus.typeObject == 3 || GameScreen.ObjFocus.typeObject == 5 || GameScreen.ObjFocus.typeObject == 7) {
            if (!GameScreen.ObjFocus.isSend) {
                GlobalService.gI().Get_Item_Map((short)GameScreen.ObjFocus.ID, GameScreen.ObjFocus.typeObject);
                GameScreen.ObjFocus.isSend = true;
            }
            return;
        }
        if (GameScreen.ObjFocus.typeObject == 1) {
            if (GameScreen.ObjFocus.Action == 4) {
                return;
            }
            if (GameScreen.ObjFocus.isMiNuong()) {
                GameScreen.gI().cmdinfoMiNuong.perform();
                return;
            }
            if (!GameScreen.ObjFocus.isMonstervantieu()) {
                this.dofire();
            } else if (GameScreen.ObjFocus.isMonstervantieu()) {
                if (GameScreen.ObjFocus.getnameOwner().equals(this.name)) {
                    GameScreen.gI().cmdInfoVantieu.perform();
                } else if (!GameScreen.ObjFocus.getnameOwner().equals(this.name)) {
                    if (this.typePk == 0) {
                        this.dofire();
                    } else if (this.typePk == 11 || this.typePk == 13) {
                        if (GameScreen.ObjFocus.isMonsterVantieuDen()) {
                            this.dofire();
                        }
                    } else if (this.typePk == 12 && GameScreen.ObjFocus.isMonsterVantieuTrang()) {
                        this.dofire();
                    }
                }
            }
        }
    }

    public void resetPlayer() {
        party = null;
        this.currentQuest = null;
        this.chat = null;
    }

    public void setValueAuto(int n) {
        if (isAutoFire != -1 && (isAutoFire = isCurAutoFire ? (byte)n : (byte)-1) == 0) {
            xBeginAutoFire = -1;
            yBeginAutofire = -1;
        }
    }

    public boolean setAutoBuff(int n, int n2, MainObject mainObject) {
        if (mainObject == null) {
            this.posTransRoad = null;
            this.IDAttack = this.ID;
            mVector mVector2 = new mVector("Player vec3");
            Object_Effect_Skill object_Effect_Skill = new Object_Effect_Skill((short)this.ID, this.typeObject);
            mVector2.addElement(object_Effect_Skill);
            this.ListKillNow.setFireSkill(this, mVector2, n2, (byte)-1);
            return true;
        }
        if (n == 2) {
            this.posTransRoad = null;
            this.IDAttack = this.ID;
            mVector mVector3 = new mVector("Player vec4");
            Object_Effect_Skill object_Effect_Skill = null;
            object_Effect_Skill = mainObject.Action == 4 || mainObject.typeObject == 2 || this.typePk == 0 || mainObject.typePk == 0 || mainObject.typePk == 10 || mainObject.typePk != -1 && this.typePk != -1 && this.typePk != mainObject.typePk ? new Object_Effect_Skill((short)this.ID, this.typeObject) : new Object_Effect_Skill((short)mainObject.ID, mainObject.typeObject);
            mVector3.addElement(object_Effect_Skill);
            this.ListKillNow.setFireSkill(this, mVector3, n2, (byte)-1);
            return true;
        }
        if (n == 1) {
            this.posTransRoad = null;
            this.IDAttack = this.ID;
            mVector mVector4 = new mVector("Player vec5");
            Object_Effect_Skill object_Effect_Skill = new Object_Effect_Skill((short)this.ID, this.typeObject);
            mVector4.addElement(object_Effect_Skill);
            this.ListKillNow.setFireSkill(this, mVector4, n2, (byte)-1);
            return true;
        }
        if (n == 3 && mainObject.Action != 4 && mainObject.typeObject != 2 && this.setFirePlayer(mainObject)) {
            this.posTransRoad = null;
            this.IDAttack = mainObject.ID;
            mVector mVector5 = new mVector("Player vec6");
            Object_Effect_Skill object_Effect_Skill = new Object_Effect_Skill((short)mainObject.ID, mainObject.typeObject);
            mVector5.addElement(object_Effect_Skill);
            this.ListKillNow.setFireSkill(this, mVector5, n2, (byte)-1);
            return true;
        }
        return false;
    }

    public static boolean setFirePlayerSpec(MainObject mainObject) {
        if (mainObject == null) {
            return false;
        }
        return mainObject.typeObject == 0 && mainObject.typeSpec == 1 && (mainObject.myClan == null || GameScreen.player.myClan == null || mainObject.myClan.IdClan != GameScreen.player.myClan.IdClan);
    }

    public boolean checkGiaoTiep() {
        if (GameScreen.ObjFocus == null) {
            return false;
        }
        if (GameScreen.ObjFocus.typeObject == 2) {
            return true;
        }
        if (GameScreen.ObjFocus.typeObject == 1 && (GameScreen.ObjFocus.myClan == null || GameScreen.player.myClan == null || GameScreen.ObjFocus.myClan.IdClan != GameScreen.player.myClan.IdClan)) {
            return false;
        }
        return !this.setFirePlayer(GameScreen.ObjFocus);
    }

    public void move_to_XY() {
        if (this.isSelling()) {
            this.toX = this.x;
            this.toY = this.y;
            this.posTransRoad = null;
            mVector mVector2 = new mVector();
            mVector2.addElement(new iCommand(T.yes, 4, this));
            mVector2.addElement(new iCommand(T.cancel, 5, this));
            GameCanvas.start_Select_Dialog(T.can_not_move, mVector2);
            return;
        }
        if (!this.Canmove() || this.isBinded) {
            this.toX = this.x;
            this.toY = this.y;
            return;
        }
        if (this.isMoveOut) {
            return;
        }
        if (this.x != this.toX) {
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
            } else if (CRes.abs(this.x - this.toX) < this.vMax + this.getVmount()) {
                if (this.x > this.toX) {
                    this.vx = -CRes.abs(this.x - this.toX);
                    this.PrevDir = this.Direction;
                    this.Direction = 2;
                } else {
                    this.vx = CRes.abs(this.x - this.toX);
                    this.PrevDir = this.Direction;
                    this.Direction = 3;
                }
            } else if (this.x > this.toX) {
                this.vx = -(this.vMax + this.getVmount());
                this.PrevDir = this.Direction;
                this.Direction = 2;
            } else {
                this.vx = this.vMax + this.getVmount();
                this.PrevDir = this.Direction;
                this.Direction = 3;
            }
        } else if (this.y != this.toY) {
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
            } else if (CRes.abs(this.y - this.toY) < this.vMax + this.getVmount()) {
                if (this.y > this.toY) {
                    this.vy = -CRes.abs(this.y - this.toY);
                    this.PrevDir = this.Direction;
                    this.Direction = 1;
                } else {
                    this.vy = CRes.abs(this.y - this.toY);
                    this.PrevDir = this.Direction;
                    this.Direction = 0;
                }
            } else if (this.y > this.toY) {
                this.vy = -(this.vMax + this.getVmount());
                this.PrevDir = this.Direction;
                this.Direction = 1;
            } else {
                this.vy = this.vMax + this.getVmount();
                this.PrevDir = this.Direction;
                this.Direction = 0;
            }
        } else {
            this.vx = 0;
            this.vy = 0;
            this.Action = 0;
        }
    }

    public void addEffectCharWearing(int n, int n2) {
        EffectCharWearing effectCharWearing = new EffectCharWearing((byte)n, n2);
        this.vecEffectCharWearing.addElement(effectCharWearing);
    }

    public static void setCurAutoFire() {
        isAutoFire = isCurAutoFire ? (byte)0 : (byte)-1;
    }

    public void removeNameStore() {
        this.nameStore = null;
    }

    public void setNameStore(String string) {
        if (this.nameStore == null) {
            this.nameStore = new PopupChat();
        }
        this.nameStore.setChat(string, this.isStop);
        this.nameStore.updatePos(this.x, this.y - this.hOne - 30);
    }

    public boolean isSelling() {
        return this.nameStore != null;
    }

    static {
        levelTab = 0;
        PointSucKhoe = 0;
        MaxSkill = 21;
        timeX2 = 0;
        timeResetAuto = 0;
        PointArena = 0;
        isNewPlayer = false;
        isAutoHPMP = false;
        isLockKey = false;
        isSendMove = true;
        isPhong = false;
        isAutoFire = 1;
        isAutoBuff = 0;
        isFullInven = false;
        timeDelaySkill = new DelaySkill[MaxSkill];
        timeDelayPotion = new DelaySkill[3];
        diemTiemNang = 0;
        diemKyNang = 0;
        mTiemnang = mSystem.new_M_Int(2, 4);
        mhotkey = new HotKey[3][];
        maxInven = (short)42;
        maxChest = (short)20;
        maxPet = (short)24;
        typeX2 = 0;
        for (int i = 0; i < mhotkey.length; ++i) {
            Player.mhotkey[i] = new HotKey[5];
        }
        mKillPlayer = new int[MaxSkill];
        mCurentLvSkill = new int[MaxSkill];
        mPlusLvSkill = new int[MaxSkill];
        xFocus = -1;
        yFocus = -1;
        timeFocus = 0;
        skillDefault = 0;
        xBeginAutoFire = -1;
        yBeginAutofire = -1;
        vecPlayerPk = new short[0];
        IndexFire = 0;
        demUnFire = 0;
        testloadmap = 0;
        timeStand = 0;
        typeFocusLast = (byte)-1;
        timeFocusLast = 0;
        isFocusNPC = false;
        timeFocusNPC = 0;
        isCurAutoFire = false;
    }
}

