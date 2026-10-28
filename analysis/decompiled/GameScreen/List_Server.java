/*
 * Decompiled with CFR 0.152.
 */
package GameScreen;

import CLib.mFont;
import CLib.mGraphics;
import CLib.mImage;
import CLib.mSystem;
import CLib.mVector;
import GameObjects.MainClan;
import GameObjects.MainObject;
import GameObjects.ObjectData;
import GameScreen.GameScreen;
import GameScreen.Info_Other_Player;
import GameScreen.MainScreen;
import GameScreen.PaintInfoGameScreen;
import InterfaceComponents.ChatTextField;
import InterfaceComponents.ListNew;
import InterfaceComponents.iCommand;
import Main.GameCanvas;
import Model.AvMain;
import Model.CRes;
import Model.MainImage;
import Model.T;
import netcommand.global.GlobalService;

public class List_Server
extends MainScreen {
    public static List_Server me;
    public mVector vecListServer = new mVector("List_server vecListServer");
    public static mVector vecMyFriend;
    public static mVector vecArche;
    public static boolean isLoadFriend;
    public static final byte FRIEND = 0;
    public static final byte LIST_TOP = 1;
    public static final byte LIST_ARCHE = 2;
    public static final byte LIST_CLAN = 3;
    public static final byte LIST_MEM_CLAN = 4;
    public static final byte LIST_MEM_OTHER_CLAN = 5;
    public static final byte LIST_THI_DAU = 6;
    int idSelect;
    int xMove;
    int limitMove;
    int hItem = 50;
    int maxpaint = 0;
    int min;
    int max;
    int x = 0;
    int y = 0;
    int w;
    int h;
    iCommand cmdchat;
    iCommand cmdMenuPoiter;
    iCommand cmdInfo;
    iCommand cmdClose;
    iCommand nextPage;
    iCommand prePage;
    iCommand cmddelete;
    iCommand cmdhoidelete;
    iCommand cmdPhongchuc;
    iCommand cmdDuoiMem;
    iCommand cmdInfoCLan;
    iCommand cmdListMemClan;
    iCommand cmdDongGopClan;
    iCommand cmdUpdateFriendList;
    iCommand cmdThidau;
    ListNew list;
    public String nameList;
    public byte page;
    public byte typeList;
    int[][] mPosStar = new int[][]{{6, 20}, {22, 18}, {38, 20}, {14, 8}, {30, 8}};
    public byte[] frameicon = new byte[]{0, 1, 2, 1};
    public byte frameClan = 0;
    boolean isTran = false;
    int yCamBegin = 0;

    public static List_Server gI() {
        if (me == null) {
            me = new List_Server();
            return me;
        }
        return me;
    }

    public List_Server() {
        this.cmdClose = new iCommand(T.close, -1);
        this.cmdchat = new iCommand(T.trochuyen, 1, this);
        this.cmdMenuPoiter = new iCommand(T.menu, 3, this);
        this.cmdInfo = new iCommand(T.info, 4, this);
        this.prePage = new iCommand(T.vetruoc, 5, this);
        this.nextPage = new iCommand(T.toitruoc, 6, this);
        this.cmddelete = new iCommand(T.del, 0, this);
        this.cmdhoidelete = new iCommand(T.del, 2, this);
        this.cmdPhongchuc = new iCommand(T.phonghacap, 7, this);
        this.cmdDuoiMem = new iCommand(T.moiroiclan, 8, this);
        this.cmdInfoCLan = new iCommand(T.info, 10, this);
        this.cmdListMemClan = new iCommand(T.xemdanhsach, 11, this);
        this.cmdDongGopClan = new iCommand(T.donggopclan, 12, this);
        this.cmdUpdateFriendList = new iCommand(T.update, 13, this);
        this.cmdThidau = new iCommand(T.thachdau, 14, this);
    }

    public void doSetCaption() {
        this.cmdClose.caption = T.close;
        this.cmdchat.caption = T.trochuyen;
        this.cmdMenuPoiter.caption = T.menu;
        this.cmdInfo.caption = T.info;
        this.prePage.caption = T.vetruoc;
        this.nextPage.caption = T.toitruoc;
        this.cmddelete.caption = T.del;
        this.cmdhoidelete.caption = T.del;
        this.cmdPhongchuc.caption = T.phonghacap;
        this.cmdDuoiMem.caption = T.moiroiclan;
        this.cmdInfoCLan.caption = T.info;
        this.cmdListMemClan.caption = T.xemdanhsach;
        this.cmdDongGopClan.caption = T.donggopclan;
        this.cmdUpdateFriendList.caption = T.update;
        this.cmdThidau.caption = T.thachdau;
    }

    public void Show() {
        mSystem.outloi("goi ham show kia");
        this.Show(GameCanvas.currentScreen);
    }

    public void setSize(byte by) {
        this.typeList = by;
        this.hItem = 50;
        if (this.typeList == 2) {
            this.hItem = 70;
        }
        this.w = GameCanvas.w / 4 * 3;
        this.h = GameCanvas.h / 5 * 4;
        if (this.w < 160) {
            this.w = 160;
        } else if (this.w > 280) {
            this.w = 280;
        }
        if (this.h < 210) {
            this.h = 210;
        } else if (this.h > 280) {
            this.h = 280;
        }
        this.x = GameCanvas.hw - this.w / 2;
        this.y = GameCanvas.hh - this.h / 2;
        this.idSelect = 0;
        this.maxpaint = (this.h - GameCanvas.hCommand) / this.hItem + 3;
        if (this.vecListServer.size() > 0) {
            this.setXmove();
            MainScreen.cameraSub.setAll(0, this.vecListServer.size() * this.hItem - this.h + GameCanvas.hCommand + 10, 0, 0);
            this.list = new ListNew(this.x, this.y, this.w, this.h, this.hItem, 0, MainScreen.cameraSub.yLimit);
            this.min = 0;
            this.max = this.maxpaint;
            if (this.max > this.vecListServer.size()) {
                this.max = this.vecListServer.size();
            }
            this.setCmd();
        }
        if (GameCanvas.isTouch) {
            this.cmdClose.setPos(this.x + this.w - 12, this.y + 10, PaintInfoGameScreen.fraCloseMenu, "");
        }
        this.right = this.cmdClose;
    }

    private void doMenuPhongChuc() {
        if (this.idSelect >= 0 && this.idSelect < this.vecListServer.size()) {
            MainObject mainObject = (MainObject)this.vecListServer.elementAt(this.idSelect);
            if (mainObject == null) {
                return;
            }
            mVector mVector2 = new mVector("List_server menu4");
            if (GameScreen.player.myClan.chucvu == 127) {
                mVector2.addElement(new iCommand(T.mChucVuClan[1], 9, 126, (AvMain)this));
            }
            if (GameScreen.player.myClan.chucvu >= 126) {
                mVector2.addElement(new iCommand(T.mChucVuClan[2], 9, 125, (AvMain)this));
            }
            if (GameScreen.player.myClan.chucvu >= 125) {
                mVector2.addElement(new iCommand(T.mChucVuClan[3], 9, 124, (AvMain)this));
                mVector2.addElement(new iCommand(T.mChucVuClan[4], 9, 123, (AvMain)this));
                mVector2.addElement(new iCommand(T.mChucVuClan[5], 9, 122, (AvMain)this));
            }
            GameCanvas.menu2.startAt(mVector2, 2, mainObject.name, false, null);
        }
    }

    private void doMenuListClan() {
        if (this.idSelect >= 0 && this.idSelect < this.vecListServer.size()) {
            MainClan mainClan = (MainClan)this.vecListServer.elementAt(this.idSelect);
            if (mainClan.hang == -1) {
                return;
            }
            mVector mVector2 = new mVector("List_server menu2");
            mVector2.addElement(this.cmdInfoCLan);
            mVector2.addElement(this.cmdListMemClan);
            GameCanvas.menu2.startAt(mVector2, 2, T.clan, false, null);
        }
    }

    private void doMenuListTop() {
        if (this.idSelect >= 0 && this.idSelect < this.vecListServer.size()) {
            MainObject mainObject = (MainObject)this.vecListServer.elementAt(this.idSelect);
            if (mainObject.name.compareTo(GameScreen.player.name) == 0) {
                return;
            }
            mVector mVector2 = new mVector("List_server menu3");
            if (this.typeList == 4) {
                if (GameScreen.player.myClan.chucvu >= 125) {
                    mVector2.addElement(this.cmdPhongchuc);
                    if (GameScreen.player.myClan.chucvu >= 126) {
                        mVector2.addElement(this.cmdDuoiMem);
                    }
                }
                mVector2.addElement(this.cmdDongGopClan);
            }
            if (mainObject != null && mainObject.typeOnline == 1 && mainObject.hang != -1 && mainObject.name.compareTo(GameScreen.player.name) != 0) {
                mVector2.addElement(this.cmdInfo);
                mVector2.addElement(this.cmdchat);
            }
            GameCanvas.menu2.startAt(mVector2, 2, T.chucnang, false, null);
        }
    }

    public void doMenu() {
        MainObject mainObject;
        mVector mVector2 = new mVector("List_server menu");
        if (this.typeList == 0) {
            mVector2.addElement(this.cmdchat);
            mVector2.addElement(this.cmdhoidelete);
            mVector2.addElement(this.cmdUpdateFriendList);
        }
        if (this.typeList != 6 && this.idSelect >= 0 && this.idSelect < this.vecListServer.size() && (mainObject = (MainObject)this.vecListServer.elementAt(this.idSelect)) != null && mainObject.typeOnline == 1 && mainObject.hang != -1 && mainObject.name.compareTo(GameScreen.player.name) != 0) {
            mVector2.addElement(this.cmdInfo);
        }
        if (this.typeList == 6 && (mainObject = (MainObject)this.vecListServer.elementAt(this.idSelect)) != null && mainObject.hang != -1 && mainObject.name.compareTo(GameScreen.player.name) != 0) {
            mVector2.addElement(this.cmdInfo);
            mVector2.addElement(this.cmdThidau);
        }
        if (this.page != 99) {
            if (this.page != 0) {
                this.prePage.caption = T.vetruoc + CRes.abs(this.page);
                mVector2.addElement(this.prePage);
            }
            if (this.page >= 0) {
                this.nextPage.caption = T.toitruoc + (this.page + 2);
                mVector2.addElement(this.nextPage);
            }
        }
        GameCanvas.menu2.startAt(mVector2, 2, T.friend, false, null);
    }

    public void commandTab(int n, int n2) {
        switch (n) {
            case -1: {
                if (this.lastScreen == GameCanvas.AllInfo) {
                    this.lastScreen.Show(this.lastScreen.lastScreen);
                    break;
                }
                this.lastScreen.Show();
            }
        }
        super.commandTab(n, n2);
    }

    public void commandPointer(int n, int n2) {
        switch (n) {
            case 0: {
                MainObject mainObject = (MainObject)vecMyFriend.elementAt(this.idSelect);
                GlobalService.gI().Friend((byte)3, mainObject.name);
                vecMyFriend.removeElementAt(this.idSelect);
                if (this.idSelect > 0) {
                    --this.idSelect;
                }
                if (vecMyFriend.size() == 0) {
                    this.left = null;
                    this.center = null;
                }
                GameCanvas.end_Dialog();
                break;
            }
            case 2: {
                GameCanvas.start_Left_Dialog(T.deleteFriend, this.cmddelete);
                break;
            }
            case 1: {
                MainObject mainObject = (MainObject)this.vecListServer.elementAt(this.idSelect);
                if (mainObject == null || mainObject.hang == -1) break;
                GameCanvas.msgchat.addNewChat(mainObject.name, "", "", (byte)0, true);
                GameCanvas.start_Chat_Dialog();
                break;
            }
            case 3: {
                if (this.typeList == 1 || this.typeList == 0 || this.typeList == 6) {
                    this.doMenu();
                    break;
                }
                if (this.typeList == 5 || this.typeList == 4) {
                    this.doMenuListTop();
                    break;
                }
                if (this.typeList != 3) break;
                this.doMenuListClan();
                break;
            }
            case 4: {
                if (this.idSelect < 0 || this.idSelect >= this.vecListServer.size()) {
                    return;
                }
                MainObject mainObject = (MainObject)this.vecListServer.elementAt(this.idSelect);
                if (mainObject == null || mainObject.hang == -1) break;
                if (this.typeList == 6) {
                    GlobalService.gI().Re_Info_Other_Object(mainObject.name, Info_Other_Player.THACH_DAU_INFO);
                    break;
                }
                GlobalService.gI().Re_Info_Other_Object(mainObject.name, Info_Other_Player.VIEW);
                break;
            }
            case 5: {
                GlobalService.gI().set_Page((byte)(CRes.abs(this.page) - 1));
                GameCanvas.start_Wait_Dialog(T.pleaseWait, new iCommand(T.cancel, -1));
                break;
            }
            case 6: {
                GlobalService.gI().set_Page((byte)CRes.abs(this.page + 1));
                GameCanvas.start_Wait_Dialog(T.pleaseWait, new iCommand(T.cancel, -1));
                break;
            }
            case 7: {
                this.doMenuPhongChuc();
                break;
            }
            case 8: {
                MainObject mainObject = (MainObject)this.vecListServer.elementAt(this.idSelect);
                if (mainObject == null) break;
                GlobalService.gI().Delete_Mem_Clan((byte)18, mainObject.name);
                break;
            }
            case 9: {
                MainObject mainObject = (MainObject)this.vecListServer.elementAt(this.idSelect);
                if (mainObject != null) {
                    GlobalService.gI().PhongCap_Clan((byte)4, (byte)n2, mainObject.name);
                }
                GameCanvas.start_Ok_Dialog(T.pleaseWait);
                break;
            }
            case 10: {
                MainClan mainClan = (MainClan)this.vecListServer.elementAt(this.idSelect);
                if (mainClan == null || mainClan.hang == -1) break;
                GlobalService.gI().ChucNang_Clan((byte)15, mainClan.IdClan);
                GameCanvas.start_Wait_Dialog(T.danglaydulieu, new iCommand(T.close, -1));
                break;
            }
            case 11: {
                MainClan mainClan = (MainClan)this.vecListServer.elementAt(this.idSelect);
                if (mainClan == null || mainClan.hang == -1) break;
                GlobalService.gI().ChucNang_Clan((byte)13, mainClan.IdClan);
                GameCanvas.start_Wait_Dialog(T.danglaydulieu, new iCommand(T.close, -1));
                break;
            }
            case 12: {
                MainObject mainObject = (MainObject)this.vecListServer.elementAt(this.idSelect);
                if (mainObject == null) break;
                GlobalService.gI().info_Mem_Clan((byte)14, mainObject.name);
                GameCanvas.start_Wait_Dialog(T.danglaydulieu, new iCommand(T.close, -1));
                break;
            }
            case 13: {
                GlobalService.gI().Friend((byte)4, "");
                GameCanvas.start_Wait_Dialog(T.pleaseWait, new iCommand(T.cancel, -1));
                break;
            }
            case 14: {
                if (this.idSelect < 0 || this.idSelect >= this.vecListServer.size()) {
                    return;
                }
                MainObject mainObject = (MainObject)this.vecListServer.elementAt(this.idSelect);
                if (mainObject == null || mainObject.hang == -1) break;
                GlobalService.gI().doSendThachDau((byte)0, mainObject.name);
            }
        }
    }

    public void paint(mGraphics mGraphics2) {
        if (this.lastScreen != null) {
            this.lastScreen.paint(mGraphics2);
        }
        if (GameCanvas.currentScreen != this) {
            return;
        }
        GameCanvas.resetTrans(mGraphics2);
        this.paintFormList(mGraphics2, this.x, this.y, this.w, this.h, this.nameList);
        mGraphics2.translate(this.x, this.y + GameCanvas.hCommand);
        mGraphics2.setClip(3, 0, this.w, this.h - GameCanvas.hCommand);
        mGraphics.resetTransAndroid(mGraphics2);
        mGraphics2.saveCanvas();
        mGraphics2.translateAndroid(this.x, this.y + GameCanvas.hCommand);
        mGraphics2.ClipRec(3, 0, this.w, this.h - GameCanvas.hCommand);
        int n = 5;
        if (this.vecListServer == null) {
            return;
        }
        if (this.vecListServer.size() > 0) {
            if (this.typeList == 3) {
                this.paintTopClan(mGraphics2, n);
            } else {
                this.paintTopNormal(mGraphics2, n);
            }
        } else if (this.typeList == 0) {
            mFont.tahoma_7_white.drawString(mGraphics2, T.nullFriend, this.w / 2, n, 2, true);
        } else {
            mFont.tahoma_7_white.drawString(mGraphics2, T.listnull, this.w / 2, n, 2, true);
        }
        mGraphics.resetTransAndroid(mGraphics2);
        mGraphics2.restoreCanvas();
        mGraphics2.endClip();
        if (!GameCanvas.menu2.isShowMenu && GameCanvas.currentDialog == null && GameCanvas.currentScreen == this && !ChatTextField.isShow && GameCanvas.subDialog == null) {
            super.paint(mGraphics2);
        }
    }

    private void paintTopClan(mGraphics mGraphics2, int n) {
        mGraphics2.setColor(-6517641);
        mGraphics2.fillRect(3, -MainScreen.cameraSub.yCam + n + this.idSelect * this.hItem + 1, this.w - 6, this.hItem - 1, true);
        mGraphics2.translate(0, -MainScreen.cameraSub.yCam);
        n += this.min * this.hItem;
        for (int i = this.min; i < this.max; ++i) {
            int n2;
            MainClan mainClan = (MainClan)this.vecListServer.elementAt(i);
            if (mainClan.hang == -1) {
                mFont.tahoma_7b_white.drawString(mGraphics2, mainClan.shortName, 20, n + 5, 0, true);
                n += this.hItem;
                continue;
            }
            MainImage mainImage = ObjectData.getImageIconClan(mainClan.IdIcon);
            if (mainImage.img != null) {
                if (mImage.getImageHeight(mainImage.img.image) / 18 == 3) {
                    if (GameCanvas.gameTick % 6 == 0) {
                        n2 = this.frameicon.length;
                        if (n2 == 0) {
                            n2 = 1;
                        }
                        this.frameClan = (byte)((this.frameClan + 1) % n2);
                    }
                    mGraphics2.drawRegion(mainImage.img, 0, this.frameicon[this.frameClan] * 18, 18, 18, 0, 9, n + 11, 3, true);
                } else {
                    mGraphics2.drawImage(mainImage.img, 9, n + 11, 3, true);
                }
            }
            if (GameScreen.player.myClan != null && GameScreen.player.myClan.shortName.compareTo(mainClan.shortName) == 0) {
                AvMain.Font3dWhite(mGraphics2, mainClan.shortName + " - " + mainClan.name, 20, n + 5, 0);
            } else {
                mFont.tahoma_7b_white.drawString(mGraphics2, mainClan.shortName + " - " + mainClan.name, 20, n + 5, 0, true);
            }
            n2 = 10;
            mFont.tahoma_7_white.drawString(mGraphics2, mainClan.slogan, n2, 20 + n, 0, true);
            if (mainClan.hang < 4) {
                mFont.tahoma_7b_white.drawString(mGraphics2, T.hang + " " + T.mhang[mainClan.hang], n2, 35 + n, 0, true);
            } else {
                mFont.tahoma_7_white.drawString(mGraphics2, T.hang + " " + (mainClan.hang + 1), n2, 35 + n, 0, true);
            }
            n += this.hItem;
            if (i >= this.vecListServer.size() - 1) continue;
            mGraphics2.setColor(AvMain.color[4]);
            mGraphics2.fillRect(4, n, this.w - 8, 1, true);
        }
    }

    public void paintTopNormal(mGraphics mGraphics2, int n) {
        mGraphics2.setColor(-6517641);
        mGraphics2.fillRect(3, -MainScreen.cameraSub.yCam + n + this.idSelect * this.hItem + 1, this.w - 6, this.hItem - 1, true);
        mGraphics2.translate(0, -MainScreen.cameraSub.yCam);
        n += this.min * this.hItem;
        for (int i = this.min; i < this.max; ++i) {
            if (i < 0 || i >= this.vecListServer.size()) continue;
            MainObject mainObject = (MainObject)this.vecListServer.elementAt(i);
            if (mainObject.hang == -1) {
                mainObject.paintNameShow(mGraphics2, 50, 5 + n, true);
                n += this.hItem;
                if (i >= this.vecListServer.size() - 1) continue;
                mGraphics2.setColor(AvMain.color[4]);
                mGraphics2.fillRect(4, n, this.w - 8, 1, true);
                continue;
            }
            mainObject.paintShowPlayer(mGraphics2, 20, 40 + n, 0);
            this.paintIconOnline(mGraphics2, mainObject.typeOnline, 40, 10 + n);
            if (mainObject.name.compareTo(GameScreen.player.name) == 0) {
                String string = mainObject.getNameAndClan(" - ");
                int n2 = 0;
                if (mainObject.myClan != null) {
                    n2 = 16;
                    mainObject.paintIconClan(mGraphics2, 50 + n2 - 7, 5 + n + 6, -1);
                }
                AvMain.FontBorderColor(mGraphics2, string, 50 + n2, 5 + n, 0, 0);
            } else {
                mainObject.paintNameShow(mGraphics2, 50, 5 + n, true);
            }
            int n3 = 40;
            if (i == this.idSelect) {
                mGraphics2.setClip(35, MainScreen.cameraSub.yCam, this.w - 40, this.h - GameCanvas.hCommand);
                n3 -= this.xMove;
            }
            if (this.typeList == 6) {
                if (mainObject.hang < 4) {
                    mFont.tahoma_7b_white.drawString(mGraphics2, T.hang + " " + T.mhang[mainObject.hang], n3, 35 + n, 0, true);
                } else {
                    mFont.tahoma_7_white.drawString(mGraphics2, T.hang + " " + (mainObject.hang + 1), n3, 35 + n, 0, true);
                }
                mFont.tahoma_7_white.drawString(mGraphics2, mainObject.infoObject, n3, 20 + n, 0, true);
            } else {
                if (this.typeList == 4 || this.typeList == 5) {
                    mFont.tahoma_7_white.drawString(mGraphics2, T.level + mainObject.Lv, n3, 20 + n, 0, true);
                    mFont.tahoma_7_white.drawString(mGraphics2, MainClan.getNameChucVu(mainObject.myClan.chucvu), n3, 35 + n, 0, true);
                } else {
                    mFont.tahoma_7_white.drawString(mGraphics2, mainObject.infoObject, n3, 20 + n, 0, true);
                }
                if (this.typeList == 1) {
                    if (mainObject.hang < 4) {
                        mFont.tahoma_7b_white.drawString(mGraphics2, T.hang + " " + T.mhang[mainObject.hang], n3, 35 + n, 0, true);
                    } else {
                        mFont.tahoma_7_white.drawString(mGraphics2, T.hang + " " + (mainObject.hang + 1), n3, 35 + n, 0, true);
                    }
                }
            }
            mGraphics2.endClip();
            if (i == this.idSelect) {
                mGraphics2.setClip(5, MainScreen.cameraSub.yCam, this.w - 10, this.h - GameCanvas.hCommand);
            }
            n += this.hItem;
            if (i < this.vecListServer.size() - 1) {
                mGraphics2.setColor(AvMain.color[4]);
                mGraphics2.fillRect(4, n, this.w - 8, 1, true);
            }
            mGraphics2.endClip();
        }
    }

    public void paintAr(mGraphics mGraphics2, int n, int n2, int n3, int n4) {
        mGraphics2.setColor(0);
        mGraphics2.fillRect(n3, n4 + 1, 62, 7, true);
        mGraphics2.fillRect(n3 + 1, n4, 60, 1, true);
        mGraphics2.fillRect(n3 + 1, n4 + 8, 60, 1, true);
        if (n2 > 0) {
            int n5 = n2 * 60 / n;
            if (n5 <= 0) {
                n5 = 1;
            } else if (n5 > 60) {
                n5 = 60;
            }
            mGraphics2.setColor(-14436849);
            mGraphics2.fillRect(n3 + 1, n4, n5, 1, true);
        }
        mFont.tahoma_7_white.drawString(mGraphics2, n2 + "/" + n, n3 + 31, n4 + 4, 2, true);
    }

    private void paintIconOnline(mGraphics mGraphics2, byte by, int n, int n2) {
        int n3 = 0;
        if (by == 0) {
            n3 = 2;
        }
        PaintInfoGameScreen.fraStatusArea.drawFrame(n3, n, n2, 0, 3, mGraphics2);
    }

    public void update() {
        this.lastScreen.update();
        if (this.limitMove > 0) {
            this.xMove += 2;
            if (this.xMove > this.limitMove) {
                this.xMove = 0;
            }
        }
        if (this.vecListServer.size() > 0) {
            if (GameCanvas.isTouch && this.list != null) {
                this.list.moveCamera();
            } else {
                MainScreen.cameraSub.UpdateCamera();
            }
            if (MainScreen.cameraSub.yCam != MainScreen.cameraSub.yTo) {
                this.setMinMax();
            }
        } else if (this.center != null || this.left != null) {
            this.center = null;
            this.left = null;
        }
    }

    public void updatekey() {
        if (this.vecListServer.size() > 0) {
            int n = this.idSelect--;
            if (GameCanvas.keyMyHold[2]) {
                GameCanvas.clearKeyHold(2);
            } else if (GameCanvas.keyMyHold[8]) {
                ++this.idSelect;
                GameCanvas.clearKeyHold(8);
            }
            this.idSelect = this.resetSelect(this.idSelect, this.vecListServer.size() - 1, false);
            if (n != this.idSelect) {
                this.setXmove();
                MainScreen.cameraSub.moveCamera(0, this.idSelect * this.hItem - this.h / 2 + 40 + GameCanvas.hCommand);
            }
        }
        super.updatekey();
    }

    public void updatePointer() {
        if (this.vecListServer.size() > 0) {
            if (GameCanvas.isPointSelect(this.x, this.y + GameCanvas.hCommand, this.w, this.h - GameCanvas.hCommand)) {
                int n = (MainScreen.cameraSub.yCam + GameCanvas.py - this.y - GameCanvas.hCommand) / this.hItem;
                if (n >= 0 && n < this.vecListServer.size()) {
                    GameCanvas.isPointerSelect = false;
                    if (n == this.idSelect) {
                        this.cmdMenuPoiter.perform();
                    } else {
                        this.idSelect = n;
                        this.setXmove();
                    }
                } else {
                    this.idSelect = 0;
                }
                GameCanvas.isPointerSelect = false;
            }
            if (GameCanvas.isTouch && this.list != null) {
                this.list.update_Pos_UP_DOWN();
                MainScreen.cameraSub.yCam = this.list.cmx;
            }
        }
        super.updatePointer();
    }

    public void setMinMax() {
        this.min = MainScreen.cameraSub.yCam / this.hItem - 1;
        if (this.min < 0) {
            this.min = 0;
        }
        this.max = this.min + this.maxpaint;
        if (this.max > this.vecListServer.size()) {
            this.max = this.vecListServer.size();
            this.min = this.max - this.maxpaint;
            if (this.min < 0) {
                this.min = 0;
            }
        }
    }

    public void setXmove() {
        if (this.vecListServer == null || this.idSelect == -1 || this.idSelect > this.vecListServer.size() - 1) {
            return;
        }
        this.xMove = 0;
        String string = "";
        int n = 40;
        if (this.typeList == 3) {
            MainClan mainClan = (MainClan)this.vecListServer.elementAt(this.idSelect);
            if (mainClan.hang != -1) {
                string = mainClan.slogan;
            }
            n = 20;
        } else {
            MainObject mainObject = (MainObject)this.vecListServer.elementAt(this.idSelect);
            string = mainObject.infoObject;
        }
        if (this.typeList == 2) {
            n = 50;
        }
        this.limitMove = mFont.tahoma_7_black.getWidth(string) - (this.w - n) + 5;
        if (this.limitMove > 0) {
            this.limitMove += 25;
        }
    }

    public void setCmd() {
        if (this.vecListServer.size() > 0 && !GameCanvas.isTouch) {
            this.left = this.cmdMenuPoiter;
            if (this.typeList == 0) {
                this.center = this.cmdchat;
            }
        }
    }

    public void updateList() {
        MainScreen.cameraSub.setAll(0, this.vecListServer.size() * this.hItem - this.h + GameCanvas.hCommand + 10, 0, 0);
        this.list = new ListNew(this.x, this.y, this.w, this.h, this.hItem, 0, MainScreen.cameraSub.yLimit);
    }

    static {
        vecMyFriend = new mVector("List_server vecMyFriend");
        vecArche = new mVector("List_server vecMyFriend");
        isLoadFriend = false;
    }
}

