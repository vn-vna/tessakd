/*
 * Decompiled with CFR 0.152.
 */
package GameScreen;

import CLib.mFont;
import CLib.mGraphics;
import CLib.mImage;
import CLib.mVector;
import GameObjects.MainClan;
import GameObjects.MainItem;
import GameObjects.ObjectData;
import GameScreen.GameScreen;
import GameScreen.MainScreen;
import GameScreen.PaintInfoGameScreen;
import InterfaceComponents.InputDialog;
import InterfaceComponents.ListNew;
import InterfaceComponents.iCommand;
import Main.GameCanvas;
import Model.AvMain;
import Model.MainImage;
import Model.T;
import Model.mCamera;
import netcommand.global.GlobalService;

public class Clan_Screen
extends MainScreen {
    public static boolean isUpdateThongTin = true;
    int xbegin;
    int ybegin;
    int wScreen;
    int hScreen;
    int hTouch = 0;
    MainClan clanShow;
    String[] mslogan;
    String[] mnoiquy;
    byte type;
    public static final byte MY_CLAN = 0;
    public static final byte VIEW_CLAN = 1;
    iCommand cmdMenu;
    iCommand cmdXinvao;
    iCommand cmdXemList;
    iCommand cmdGopXu;
    iCommand cmdGopLuong;
    iCommand cmdInfoMySeft;
    iCommand cmdChucNang;
    iCommand cmdChangeSlogan;
    iCommand cmdChangeNoiQuy;
    iCommand cmdPhongcap;
    iCommand cmdClose;
    iCommand cmdRoiClan;
    iCommand cmdChatBang;
    iCommand cmdThongBao;
    iCommand cmdInvenClan;
    InputDialog inputDialog;
    ListNew list;
    mCamera cam = new mCamera();
    public byte[] frameicon = new byte[]{0, 1, 2, 1};
    public byte frameClan = 0;

    public Clan_Screen() {
        if (GameCanvas.isTouch) {
            this.hTouch = iCommand.hButtonCmd;
        }
        this.init(180);
    }

    public void init(int n) {
        this.wScreen = n;
        this.hScreen = n;
        if (this.hScreen > GameCanvas.h - 20) {
            this.hScreen = GameCanvas.h - 20;
        }
        if (this.wScreen > GameCanvas.w - 20) {
            this.wScreen = GameCanvas.w - 20;
        }
        this.xbegin = GameCanvas.hw - this.wScreen / 2;
        this.ybegin = GameCanvas.hh - this.hScreen / 2;
    }

    public void Show(MainScreen mainScreen) {
        super.Show(mainScreen);
        GameCanvas.end_Dialog();
    }

    public void setInfoClan(MainClan mainClan, byte by) {
        this.type = by;
        this.clanShow = mainClan;
        if (this.clanShow == null) {
            this.lastScreen.Show(this.lastScreen.lastScreen);
            return;
        }
        this.updateShow();
        this.cmdClose = new iCommand(T.close, -1, this);
        this.cmdXemList = new iCommand(T.xemdanhsach, 2, this);
        if (by == 1) {
            this.cmdXemList.caption = T.thanhvien;
            this.left = this.cmdXemList;
        } else if (by == 0) {
            this.cmdMenu = new iCommand(T.menu, 0, this);
            this.cmdGopXu = new iCommand(T.gop + T.coin, 3, this);
            this.cmdGopLuong = new iCommand(T.gop + T.gem, 4, this);
            this.cmdInfoMySeft = new iCommand(T.info + " " + T.banthan, 6, this);
            this.cmdChucNang = new iCommand(T.chucnang, 7, this);
            this.cmdChangeSlogan = new iCommand(T.doiSlogan, 8, this);
            this.cmdChangeNoiQuy = new iCommand(T.doiNoiquy, 9, this);
            this.cmdPhongcap = new iCommand(T.phonghacap, 2, this);
            this.cmdRoiClan = new iCommand(T.roiclan, 11, this);
            this.cmdChatBang = new iCommand(T.chattoanbang, 12, this);
            this.cmdThongBao = new iCommand(T.doithongbao, 13, this);
            this.cmdInvenClan = new iCommand(T.tabhanhtrang + " " + T.clan, 14, this);
            this.left = this.cmdMenu;
        }
        this.right = this.cmdClose;
        if (GameCanvas.isTouch) {
            this.left.setPos(this.xbegin + this.wScreen / 2, this.ybegin + this.wScreen - iCommand.hButtonCmd / 2 - 4, PaintInfoGameScreen.fraButton, this.left.caption);
            this.right.setPos(this.xbegin + this.wScreen - 12, this.ybegin + 10, PaintInfoGameScreen.fraCloseMenu, "");
        }
    }

    public void commandPointer(int n, int n2) {
        switch (n) {
            case -1: {
                this.lastScreen.Show(this.lastScreen.lastScreen);
                break;
            }
            case 0: {
                mVector mVector2 = new mVector("Clan_Screen menu");
                if (this.type == 1) {
                    mVector2.addElement(this.cmdXemList);
                    mVector2.addElement(this.cmdXinvao);
                } else if (this.type == 0) {
                    if (GameScreen.player.myClan == null || GameScreen.player.myClan.isRemove) {
                        this.lastScreen.Show(this.lastScreen.lastScreen);
                        GameCanvas.start_Ok_Dialog(T.bankhongconclan);
                        return;
                    }
                    mVector2.addElement(this.cmdXemList);
                    mVector2.addElement(this.cmdInfoMySeft);
                    mVector2.addElement(this.cmdChatBang);
                    if (GameScreen.player.myClan.getChucNang()) {
                        mVector2.addElement(this.cmdChucNang);
                        mVector2.addElement(this.cmdInvenClan);
                    }
                    mVector2.addElement(this.cmdGopXu);
                    mVector2.addElement(this.cmdGopLuong);
                    if (GameScreen.player.myClan.chucvu != 127) {
                        mVector2.addElement(this.cmdRoiClan);
                    }
                }
                GameCanvas.menu2.startAt(mVector2, 2, T.chucnang, false, null);
                break;
            }
            case 1: {
                GlobalService.gI().ChucNang_Clan((byte)0, this.clanShow.IdClan);
                GameCanvas.start_Ok_Dialog(T.dagoidangky);
                break;
            }
            case 2: {
                GlobalService.gI().ChucNang_Clan((byte)13, this.clanShow.IdClan);
                GameCanvas.start_Wait_Dialog(T.danglaydulieu, new iCommand(T.close, -1));
                break;
            }
            case 3: {
                this.inputDialog = new InputDialog();
                this.inputDialog.setinfo(T.nhapsoxumuongop, new iCommand(T.gop, 5, 6, (AvMain)this), true, T.gop + T.coin);
                GameCanvas.currentDialog = this.inputDialog;
                break;
            }
            case 4: {
                this.inputDialog = new InputDialog();
                this.inputDialog.setinfo(T.nhapsoluongmuongop, new iCommand(T.gop, 5, 7, (AvMain)this), true, T.gop + T.gem);
                GameCanvas.currentDialog = this.inputDialog;
                break;
            }
            case 5: {
                int n3 = 0;
                try {
                    n3 = Integer.parseInt(this.inputDialog.tfInput.getText());
                }
                catch (Exception exception) {
                    return;
                }
                if (n3 <= 0) break;
                GlobalService.gI().gop_Xu_Luong_Clan((byte)n2, n3);
                GameCanvas.start_Ok_Dialog(T.pleaseWait);
                break;
            }
            case 6: {
                GlobalService.gI().info_Mem_Clan((byte)14, GameScreen.player.name);
                GameCanvas.start_Wait_Dialog(T.danglaydulieu, new iCommand(T.close, -1));
                break;
            }
            case 7: {
                if (GameScreen.player.myClan == null || GameScreen.player.myClan.isRemove) {
                    this.lastScreen.Show(this.lastScreen.lastScreen);
                    GameCanvas.start_Ok_Dialog(T.bankhongconclan);
                    return;
                }
                if (GameScreen.player.myClan.chucvu == 127) {
                    mVector mVector3 = new mVector("Clan_Screen menu2");
                    mVector3.addElement(this.cmdPhongcap);
                    mVector3.addElement(this.cmdChangeSlogan);
                    mVector3.addElement(this.cmdChangeNoiQuy);
                    mVector3.addElement(this.cmdThongBao);
                    GameCanvas.menu2.startAt(mVector3, 2, T.chucnang, false, null);
                    break;
                }
                this.cmdPhongcap.perform();
                break;
            }
            case 8: {
                this.inputDialog = new InputDialog();
                this.inputDialog.setinfo(T.nhapthongtindoi, new iCommand(T.change, 10, 16, (AvMain)this), false, T.change + " " + T.slogan);
                GameCanvas.currentDialog = this.inputDialog;
                break;
            }
            case 9: {
                this.inputDialog = new InputDialog();
                this.inputDialog.setinfo(T.nhapthongtindoi, new iCommand(T.change, 10, 17, (AvMain)this), false, T.change + " " + T.noiquy);
                GameCanvas.currentDialog = this.inputDialog;
                break;
            }
            case 10: {
                String string = this.inputDialog.tfInput.getText();
                if (string == null) break;
                GlobalService.gI().Change_Slo_NoiQuy_Clan((byte)n2, string);
                GameCanvas.start_Ok_Dialog(T.pleaseWait);
                break;
            }
            case 11: {
                GameCanvas.start_Left_Dialog(T.hoiroiClan, new iCommand(T.roiclan, 15, this));
                break;
            }
            case 12: {
                GameCanvas.msgchat.addNewChat(T.tabBangHoi, "", "", (byte)0, true);
                GameCanvas.start_Chat_Dialog();
                break;
            }
            case 13: {
                this.inputDialog = new InputDialog();
                this.inputDialog.setinfo(T.nhapthongtindoi, new iCommand(T.change, 10, 2, (AvMain)this), false, T.change + " " + T.thongbao);
                GameCanvas.currentDialog = this.inputDialog;
                break;
            }
            case 14: {
                GlobalService.gI().InvenClan((byte)21);
                GameCanvas.start_Ok_Dialog(T.pleaseWait);
                break;
            }
            case 15: {
                GlobalService.gI().Delete_Mem_Clan((byte)18, GameScreen.player.name);
                this.lastScreen.Show(this.lastScreen.lastScreen);
            }
        }
    }

    public void paint(mGraphics mGraphics2) {
        int n;
        if (this.lastScreen != null) {
            this.lastScreen.paint(mGraphics2);
        }
        if (GameCanvas.currentScreen != this) {
            return;
        }
        GameCanvas.resetTrans(mGraphics2);
        int n2 = this.ybegin;
        int n3 = this.xbegin;
        this.paintFormList(mGraphics2, n3, n2, this.wScreen, this.hScreen, T.clan);
        n3 += 5;
        mGraphics2.setClip(this.xbegin, n2 += GameCanvas.hCommand + 2, this.wScreen, this.hScreen - GameCanvas.hCommand - this.hTouch - 5);
        mGraphics2.saveCanvas();
        mGraphics2.ClipRec(this.xbegin, n2, this.wScreen, this.hScreen - GameCanvas.hCommand - this.hTouch - 5);
        mGraphics2.translate(0, -this.cam.yCam);
        AvMain.Font3dWhite(mGraphics2, this.clanShow.name, this.xbegin + this.wScreen / 2, n2 + 4, 2);
        mFont.tahoma_7b_white.drawString(mGraphics2, T.bieutuong, n3, n2 += GameCanvas.hText + GameCanvas.hText / 2, 0, true);
        MainImage mainImage = ObjectData.getImageIconClan(this.clanShow.IdIcon);
        if (mainImage.img != null) {
            if (mImage.getImageHeight(mainImage.img.image) / 18 == 3) {
                if (GameCanvas.gameTick % 6 == 0) {
                    n = this.frameicon.length;
                    if (n == 0) {
                        n = 1;
                    }
                    this.frameClan = (byte)((this.frameClan + 1) % n);
                }
                mGraphics2.drawRegion(mainImage.img, 0, this.frameicon[this.frameClan] * 18, 18, 18, 0, n3 + 70, n2 + 6, 3, true);
            } else {
                mGraphics2.drawImage(mainImage.img, n3 + 70, n2 + 6, 3, true);
            }
        }
        mFont.tahoma_7b_white.drawString(mGraphics2, this.clanShow.shortName, n3 + 78, n2, 0, true);
        mFont.tahoma_7b_white.drawString(mGraphics2, T.level + this.clanShow.Lv + " +" + this.clanShow.ptLv / 10 + "," + this.clanShow.ptLv % 10 + "%" + "    " + T.hang + ": " + this.clanShow.hang, n3, n2 += GameCanvas.hText, 0, true);
        mFont.tahoma_7b_white.drawString(mGraphics2, T.members + this.clanShow.numMem + "/" + this.clanShow.maxMem, n3, n2 += GameCanvas.hText, 0, true);
        mFont.tahoma_7b_white.drawString(mGraphics2, T.mChucVuClan[0] + ": " + this.clanShow.nameThuLinh, n3, n2 += GameCanvas.hText, 0, true);
        n2 += GameCanvas.hText;
        for (n = 0; n < this.mslogan.length; ++n) {
            mFont.tahoma_7_white.drawString(mGraphics2, this.mslogan[n], n3, n2, 0, true);
            n2 += GameCanvas.hText;
        }
        mFont.tahoma_7b_white.drawString(mGraphics2, T.quyxu + MainItem.getDotNumber(this.clanShow.coin), n3, n2, 0, true);
        mFont.tahoma_7b_white.drawString(mGraphics2, T.quyngoc + MainItem.getDotNumber(this.clanShow.gold), n3, n2 += GameCanvas.hText, 0, true);
        if (this.clanShow.mthanhtich != null) {
            for (n = 0; n < this.clanShow.mthanhtich.length; ++n) {
                n2 += 20;
                mainImage = ObjectData.getImageIconArCheClan(this.clanShow.mthanhtich[n].id);
                if (mainImage.img != null) {
                    mGraphics2.drawImage(mainImage.img, n3 + 9, n2 + 5, 3, true);
                }
                mFont.tahoma_7_yellow.drawString(mGraphics2, this.clanShow.mthanhtich[n].num + "", n3 + 16, n2 + 4, 2, true);
                mFont.tahoma_7b_white.drawString(mGraphics2, ": " + this.clanShow.mthanhtich[n].nameThanhTich, n3 + 22, n2, 0, true);
            }
            n2 += 5;
        }
        n2 += GameCanvas.hText;
        for (n = 0; n < this.mnoiquy.length; ++n) {
            mFont.tahoma_7_white.drawString(mGraphics2, this.mnoiquy[n], n3, n2, 0, true);
            n2 += GameCanvas.hText;
        }
        mGraphics.resetTransAndroid(mGraphics2);
        mGraphics2.restoreCanvas();
        mGraphics2.endClip();
        if (GameCanvas.currentScreen == this && !GameCanvas.menu2.isShowMenu && GameCanvas.currentDialog == null && GameCanvas.subDialog == null) {
            super.paint(mGraphics2);
        }
    }

    public void updateShow() {
        if (this.clanShow.slogan.length() > 100) {
            this.init(220);
        } else if (this.clanShow.noiquy.length() > 100) {
            this.init(220);
        }
        this.mslogan = mFont.tahoma_7_white.splitFontArray(this.clanShow.slogan, this.wScreen - 10);
        this.mnoiquy = mFont.tahoma_7_white.splitFontArray(this.clanShow.noiquy, this.wScreen - 10);
        int n = GameCanvas.hCommand + 2 + (6 + this.mnoiquy.length + this.mslogan.length) * GameCanvas.hText;
        if (this.clanShow.mthanhtich != null) {
            n += 20 * this.clanShow.mthanhtich.length + 5;
        }
        this.list = n > this.hScreen - GameCanvas.hCommand - this.hTouch - 5 ? new ListNew(this.xbegin, this.ybegin, this.wScreen, this.hScreen, 0, 0, n - (this.hScreen - GameCanvas.hCommand - this.hTouch - 5)) : new ListNew(this.xbegin, this.ybegin, this.wScreen, this.hScreen, 0, 0, 0);
        this.cam.setAll(0, this.list.cmxLim, 0, 0);
        isUpdateThongTin = false;
    }

    public void update() {
        if (this.lastScreen != null) {
            this.lastScreen.update();
        }
        if (isUpdateThongTin) {
            this.updateShow();
            isUpdateThongTin = false;
        }
        if (GameCanvas.isTouch) {
            this.list.moveCamera();
            this.cam.yCam = this.list.cmx;
        } else {
            this.cam.UpdateCamera();
        }
        super.update();
    }

    public void updatekey() {
        if (this.cam.yLimit > 0) {
            if (GameCanvas.keyMyHold[2]) {
                if (this.cam.yTo > 0) {
                    this.cam.yTo -= GameCanvas.hText;
                }
                if (this.cam.yTo < 0) {
                    this.cam.yTo = 0;
                }
                GameCanvas.clearKeyHold(2);
            } else if (GameCanvas.keyMyHold[8]) {
                if (this.cam.yTo < this.cam.yLimit) {
                    this.cam.yTo += GameCanvas.hText;
                }
                if (this.cam.yTo > this.cam.yLimit) {
                    this.cam.yTo = this.cam.yLimit;
                }
                GameCanvas.clearKeyHold(2);
            }
        }
        super.updatekey();
    }

    public void updatePointer() {
        if (GameCanvas.isTouch) {
            this.list.update_Pos_UP_DOWN();
        }
        super.updatePointer();
    }
}

