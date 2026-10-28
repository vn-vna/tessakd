/*
 * Decompiled with CFR 0.152.
 */
package InterfaceComponents;

import CLib.mFont;
import CLib.mGraphics;
import GameObjects.Item;
import GameObjects.MainBuff;
import GameObjects.MainInfoItem;
import GameObjects.Player;
import GameScreen.GameScreen;
import GameScreen.MainScreen;
import InterfaceComponents.InputDialog;
import InterfaceComponents.ListNew;
import InterfaceComponents.MainTabNew;
import InterfaceComponents.iCommand;
import Main.GameCanvas;
import Model.T;
import netcommand.global.GlobalService;

public class TabInfoChar
extends MainTabNew {
    int w4;
    int idSelect;
    int hmax;
    int xCamInfo;
    int hitem = 20;
    int plusTouch = 0;
    int xplus = 30;
    iCommand cmdSetPoint;
    iCommand cmdSendSetPoint;
    iCommand cmdSendSetPointOne;
    iCommand cmdHoiSendSetPoint;
    int timePaintFocus = 0;
    InputDialog inputDialog;
    ListNew list;
    String strInfoSmall = "";

    public TabInfoChar(String string) {
        this.typeTab = (byte)2;
        this.nameTab = string;
        if (GameCanvas.isTouch) {
            this.hitem = 22;
        }
        if (GameCanvas.h <= 200) {
            this.hitem = 18;
        }
        this.plusTouch = this.hitem - 20;
        this.xBegin = this.xTab + MainTabNew.wOneItem + MainTabNew.wOne5 * 3;
        this.yBegin = this.yTab + GameCanvas.h / 5 + MainTabNew.wOneItem;
        this.w4 = MainTabNew.wblack / 4;
        this.init();
        this.cmdBack = new iCommand(T.back, -1, this);
        if (GameCanvas.isTouch) {
            this.cmdBack.caption = T.close;
        }
        this.cmdSetPoint = new iCommand(T.setPoint, 0, this);
        this.cmdHoiSendSetPoint = new iCommand(T.cong, 3, this);
        this.cmdSendSetPoint = new iCommand(T.cong, 1, this);
        this.cmdSendSetPointOne = new iCommand(T.cong, 2, this);
    }

    public void init() {
        int n = this.yBegin + 4 + GameCanvas.hText + this.hitem * T.mKyNang.length + 2;
        int n2 = MainTabNew.hblack - (GameCanvas.hText + 2 + this.hitem * T.mKyNang.length);
        this.hmax = GameScreen.player.mInfoChar.length * GameCanvas.hText - n2 + 5;
        this.list = new ListNew(this.xBegin, n, MainTabNew.wblack, n2, 0, 0, this.hmax);
        if (MainTabNew.longwidth > 0) {
            this.list.x = MainTabNew.xlongwidth;
            this.list.y = MainTabNew.ylongwidth + MainTabNew.wOneItem;
            this.list.maxW = MainTabNew.longwidth;
            this.list.maxH = MainTabNew.hblack - MainTabNew.wOneItem;
            this.list.cmxLim = GameScreen.player.mInfoChar.length * GameCanvas.hText - this.hSmall + MainTabNew.wOneItem + 10;
        }
        if (this.hmax < 0) {
            this.hmax = 0;
        }
        MainScreen.cameraSub.setAll(0, this.list.cmxLim, 0, 0);
        if (!GameCanvas.isTouch) {
            this.right = this.cmdBack;
            if (Player.diemTiemNang > 0) {
                this.center = this.cmdSetPoint;
            }
        }
        this.timePaintFocus = 0;
        super.init();
        if (GameCanvas.isSmallScreen) {
            for (int i = 0; i < GameScreen.player.mInfoChar.length; ++i) {
                MainInfoItem mainInfoItem = GameScreen.player.mInfoChar[i];
                if (mainInfoItem.id >= 23 && mainInfoItem.id <= 26) continue;
                this.strInfoSmall = this.strInfoSmall + Item.nameInfoItem[mainInfoItem.id] + ": " + Item.getPercent(Item.isPercentInfoItem[mainInfoItem.id], mainInfoItem.value) + "\n";
            }
        }
    }

    public void backTab() {
        MainTabNew.Focus = 0;
        this.idSelect = 0;
        super.backTab();
    }

    public void commandPointer(int n, int n2) {
        switch (n) {
            case -1: {
                this.backTab();
                break;
            }
            case 0: {
                if (Player.diemTiemNang == 1) {
                    GameCanvas.start_Left_Dialog(T.cong1diem + T.mKyNang[this.idSelect] + T.nhandc + this.infoShow(1), this.cmdSendSetPointOne);
                    break;
                }
                if (Player.diemTiemNang <= 0) break;
                this.inputDialog = new InputDialog();
                this.inputDialog.setinfo(T.nhapsodiem + T.mKyNang[this.idSelect] + " (" + T.nhohonhoacbang + Player.diemTiemNang + ") ", this.cmdHoiSendSetPoint, true, T.kynang);
                GameCanvas.currentDialog = this.inputDialog;
                break;
            }
            case 1: {
                short s = 0;
                try {
                    s = Short.parseShort(this.inputDialog.tfInput.getText());
                }
                catch (Exception exception) {
                    s = 0;
                }
                if (s < 1) {
                    return;
                }
                if (s > Player.diemTiemNang) {
                    GameCanvas.start_Ok_Dialog(T.khongthecong + Player.diemTiemNang);
                    break;
                }
                GlobalService.gI().Add_Base_Skill_Point((byte)0, (byte)this.idSelect, s);
                GameCanvas.end_Dialog();
                if (!GameScreen.help.setStep_Next(7, 6)) break;
                ++GameScreen.help.Next;
                GameScreen.help.setNext();
                break;
            }
            case 2: {
                GlobalService.gI().Add_Base_Skill_Point((byte)0, (byte)this.idSelect, (short)1);
                if (GameScreen.help.setStep_Next(7, 6)) {
                    ++GameScreen.help.Next;
                }
                GameCanvas.end_Dialog();
                break;
            }
            case 3: {
                short s = 0;
                try {
                    s = Short.parseShort(this.inputDialog.tfInput.getText());
                }
                catch (Exception exception) {
                    s = 0;
                }
                if (s < 1) {
                    return;
                }
                if (s > Player.diemTiemNang) {
                    GameCanvas.start_Ok_Dialog(T.khongthecong + Player.diemTiemNang);
                    break;
                }
                GameCanvas.start_Left_Dialog(s + T.diemcongvao + T.mKyNang[this.idSelect] + T.nhandc + this.infoShow(s), this.cmdSendSetPoint);
            }
        }
        super.commandPointer(n, n2);
    }

    public void paint(mGraphics mGraphics2) {
        int n;
        int n2;
        int n3;
        int n4 = this.yBegin + 4;
        int n5 = this.xBegin + 4;
        mFont.tahoma_7_white.drawString(mGraphics2, T.diemtiemnang + Player.diemTiemNang, n5, n4, 0, false);
        n4 += GameCanvas.hText + 2;
        for (n3 = 0; n3 < T.mKyNang.length; ++n3) {
            n2 = this.xBegin + this.w4 - this.w4 / 4 + this.xplus;
            n = n4 + this.hitem * n3 + this.plusTouch;
            if (GameCanvas.lowGraphic) {
                MainTabNew.paintRectLowGraphic(mGraphics2, n2 + 2 - this.plusTouch / 2, n - 1 - this.plusTouch / 2, 24 + this.plusTouch, 13 + this.plusTouch, 4);
            } else {
                mGraphics2.drawRegion(MainTabNew.imgTab[12], 0, 0, 24 + this.plusTouch, 13 + this.plusTouch, 0, n2 + 2 - this.plusTouch / 2, n - 1 - this.plusTouch / 2, 0, false);
            }
            mFont.tahoma_7b_white.drawString(mGraphics2, T.mKyNang[n3] + ":", n2 - this.plusTouch / 2 - this.xplus - this.w4 / 2 - (GameCanvas.isSmallScreen ? 4 : 0), n, 0, true);
            mFont.tahoma_7b_white.drawString(mGraphics2, Player.mTiemnang[0][n3] + "", n2 + 14, n, 2, true);
            if (Player.mTiemnang[1][n3] <= 0) continue;
            mFont.tahoma_7b_blue.drawString(mGraphics2, "+" + Player.mTiemnang[1][n3], n2 + 26 + this.plusTouch, n, 0, false);
        }
        mGraphics2.setColor(MainTabNew.color[3]);
        if (!(this.idSelect == 4 || this.timePaintFocus <= 0 && GameCanvas.isTouch || MainTabNew.Focus != 1)) {
            n3 = this.xBegin + this.w4 - this.w4 / 4 + this.xplus + 2 - this.plusTouch / 2;
            n2 = n4 + this.hitem * this.idSelect + this.plusTouch / 2 - 1;
            mGraphics2.drawRect(n3, n2, 24 + this.plusTouch, 13 + this.plusTouch, false);
        }
        n4 += this.hitem * T.mKyNang.length;
        n3 = MainTabNew.xlongwidth;
        n2 = MainTabNew.ylongwidth + MainTabNew.wOneItem + 4;
        n = MainTabNew.longwidth;
        int n6 = this.hSmall - MainTabNew.wOneItem - 6;
        if (MainTabNew.longwidth > 0) {
            mFont.tahoma_7b_white.drawString(mGraphics2, T.info, MainTabNew.xlongwidth + MainTabNew.longwidth / 2, MainTabNew.ylongwidth + MainTabNew.wOneItem / 4, 2, false);
        }
        mGraphics2.saveCanvas();
        mGraphics2.ClipRec(n3, n2, n, n6);
        if (MainTabNew.longwidth > 0) {
            GameCanvas.resetTrans(mGraphics2);
            mGraphics2.setClip(MainTabNew.xlongwidth, MainTabNew.ylongwidth + MainTabNew.wOneItem + 4, MainTabNew.longwidth, this.hSmall - MainTabNew.wOneItem - 6);
            mGraphics2.translate(MainTabNew.xlongwidth, MainTabNew.ylongwidth + MainTabNew.wOneItem);
            mGraphics2.translate(0, -MainScreen.cameraSub.yCam);
            n5 = 4;
            n4 = 4;
        } else {
            if (!GameCanvas.isSmallScreen && this.idSelect == 4) {
                if (MainScreen.cameraSub.yCam > 0) {
                    mGraphics2.drawRegion(MainTabNew.imgTab[7], 0, 0, 13, 8, 0, this.xBegin + MainTabNew.wblack - 16, n4 - 2 + GameCanvas.gameTick % 3, 0, false);
                }
                if (MainScreen.cameraSub.yCam < MainScreen.cameraSub.yLimit) {
                    mGraphics2.drawRegion(MainTabNew.imgTab[7], 0, 8, 13, 8, 0, this.xBegin + MainTabNew.wblack - 16, this.yBegin + MainTabNew.hblack - 10 - GameCanvas.gameTick % 3, 0, false);
                }
            }
            if (!GameCanvas.isSmallScreen) {
                mGraphics2.setClip(this.xBegin, n4 - 5, MainTabNew.wblack, this.yBegin + MainTabNew.hblack - n4 + 3);
                n3 = this.xBegin;
                n2 = n4 - 5;
                n = MainTabNew.wblack;
                n6 = this.yBegin + MainTabNew.hblack - n4 + 3;
                mGraphics2.translate(0, -MainScreen.cameraSub.yCam);
            }
        }
        if (!GameCanvas.isSmallScreen) {
            for (int i = 0; i < GameScreen.player.mInfoChar.length; ++i) {
                int n7;
                Object object;
                MainInfoItem mainInfoItem = GameScreen.player.mInfoChar[i];
                if (mainInfoItem.id >= 23 && mainInfoItem.id <= 26) continue;
                mFont mFont2 = mFont.tahoma_7_white;
                mFont2 = MainTabNew.setTextColor(Item.colorInfoItem[mainInfoItem.id]);
                String string = Item.nameInfoItem[mainInfoItem.id] + ": " + Item.getPercent(Item.isPercentInfoItem[mainInfoItem.id], mainInfoItem.value);
                mFont2.drawString(mGraphics2, string, n5, n4, 0, true);
                int n8 = 0;
                if (GameScreen.player.vecBuff != null) {
                    for (int j = 0; j < GameScreen.player.vecBuff.size(); ++j) {
                        object = (MainBuff)GameScreen.player.vecBuff.elementAt(j);
                        if (((MainBuff)object).minfo == null) continue;
                        for (n7 = 0; n7 < ((MainBuff)object).minfo.length; ++n7) {
                            if (mainInfoItem.id != ((MainBuff)object).minfo[n7].id) continue;
                            n8 += ((MainBuff)object).minfo[n7].value;
                        }
                    }
                }
                if (n8 != 0) {
                    String string2 = "";
                    object = mFont.tahoma_7_green;
                    if (n8 > 0) {
                        string2 = " +" + Item.getPercent(Item.isPercentInfoItem[mainInfoItem.id], n8);
                    } else {
                        string2 = " " + Item.getPercent(Item.isPercentInfoItem[mainInfoItem.id], n8);
                        object = mFont.tahoma_7_red;
                    }
                    n7 = mFont.tahoma_7_white.getWidth(string);
                    ((mFont)object).drawString(mGraphics2, " " + string2, n5 + n7, n4, 0, true);
                }
                n4 += GameCanvas.hText;
            }
        } else {
            mFont.tahoma_7b_white.drawString(mGraphics2, T.info, n5 + MainTabNew.wblack / 2, n4 - GameCanvas.hText / 2, 2, true);
        }
        mGraphics.resetTransAndroid(mGraphics2);
        mGraphics2.restoreCanvas();
        mGraphics2.endClip();
    }

    public void update() {
        if (GameCanvas.isTouch) {
            this.list.moveCamera();
            this.list.update_Pos_UP_DOWN();
            MainScreen.cameraSub.yCam = this.list.cmx;
        } else {
            MainScreen.cameraSub.UpdateCamera();
        }
        if (!GameCanvas.isTouch) {
            if (Player.diemTiemNang > 0 && this.idSelect != 4) {
                if (this.center == null) {
                    this.center = this.cmdSetPoint;
                }
            } else if (this.center != null) {
                this.center = null;
            }
        }
        if (this.timePaintFocus > 0 && GameCanvas.currentDialog == null) {
            --this.timePaintFocus;
        }
    }

    public void updatekey() {
        if (MainTabNew.Focus == 1) {
            if (this.idSelect == 4) {
                if (GameCanvas.isSmallScreen) {
                    this.idSelect = 3;
                    GameCanvas.start_Show_Dialog(this.strInfoSmall, T.info);
                } else if (GameCanvas.keyMyHold[2]) {
                    this.xCamInfo -= GameCanvas.hText;
                    if (this.xCamInfo < 0) {
                        this.idSelect = 3;
                    }
                    GameCanvas.clearKeyHold(2);
                    MainScreen.cameraSub.moveCamera(0, this.xCamInfo);
                } else if (GameCanvas.keyMyHold[8]) {
                    this.xCamInfo += GameCanvas.hText;
                    if (this.xCamInfo > this.hmax) {
                        this.xCamInfo = this.hmax;
                    }
                    GameCanvas.clearKeyHold(8);
                    MainScreen.cameraSub.moveCamera(0, this.xCamInfo);
                }
            } else {
                if (GameCanvas.keyMyHold[2]) {
                    --this.idSelect;
                    GameCanvas.clearKeyHold(2);
                } else if (GameCanvas.keyMyHold[8]) {
                    ++this.idSelect;
                    GameCanvas.clearKeyHold(8);
                } else if (GameCanvas.keyMyHold[4] || GameCanvas.keyMyHold[6]) {
                    MainTabNew.Focus = 0;
                    GameCanvas.clearKeyHold(4);
                    GameCanvas.clearKeyHold(6);
                }
                int n = T.mKyNang.length - 1;
                if (this.hmax > 0) {
                    ++n;
                }
                this.idSelect = this.resetSelect(this.idSelect, n, false);
                this.xCamInfo = 0;
            }
        }
        super.updatekey();
    }

    public void updatePointer() {
        if (GameCanvas.isPointerSelect) {
            for (int i = 0; i < 4; ++i) {
                int n = this.xBegin + this.w4 - this.w4 / 4 + this.xplus;
                int n2 = this.yBegin + GameCanvas.hText + 4 + this.hitem * i + this.plusTouch + 2;
                if (!GameCanvas.isPoint(n - 2 - this.plusTouch / 2, n2 - 5 - this.plusTouch / 2, 32 + this.plusTouch, 21 + this.plusTouch)) continue;
                this.idSelect = i;
                this.cmdSetPoint.perform();
                GameCanvas.isPointerSelect = false;
                this.timePaintFocus = 10;
                break;
            }
        }
        super.updatePointer();
    }

    public String infoShow(int n) {
        String string = "";
        switch (this.idSelect) {
            case 0: {
                string = string + "\n" + T.chimang + this.setpercent(2, n);
                string = string + "\n" + T.tatcasatthuong + this.setpercent(20, n);
                if (GameScreen.player.clazz == 0) {
                    string = string + "\n" + T.satthuongvatly + "+" + 4 * n;
                    string = string + "\n" + T.satthuonglua + "+" + 4 * n;
                    break;
                }
                if (GameScreen.player.clazz != 1) break;
                string = string + "\n" + T.satthuongvatly + "+" + 4 * n;
                string = string + "\n" + T.satthuongdoc + "+" + 4 * n;
                break;
            }
            case 1: {
                string = string + "\n" + T.nedon + this.setpercent(2, n);
                string = string + "\n" + T.phongthu + this.setpercent(10, n);
                if (GameScreen.player.clazz == 3) {
                    string = string + "\n" + T.phongthu + "+" + 22 * n;
                    break;
                }
                if (GameScreen.player.clazz == 1) {
                    string = string + "\n" + T.phongthu + "+" + 22 * n;
                    break;
                }
                string = string + "\n" + T.phongthu + "+" + 20 * n;
                break;
            }
            case 2: {
                string = string + "\n" + T.phansatthuong + this.setpercent(2, n);
                if (GameScreen.player.clazz == 0) {
                    string = string + "\n" + T.mau + "+" + 320 * n;
                    break;
                }
                if (GameScreen.player.clazz == 2) {
                    string = string + "\n" + T.mau + "+" + 310 * n;
                    string = string + "\n" + T.nangluong + "+" + 1 * n;
                    break;
                }
                string = string + "\n" + T.mau + "+" + 300 * n;
                if (GameScreen.player.clazz != 2) break;
                string = string + "\n" + T.khangtatcast + this.setpercent(5, n);
                break;
            }
            case 3: {
                string = string + "\n" + T.xuyengiap + this.setpercent(2, n);
                if (GameScreen.player.clazz == 2) {
                    string = string + "\n" + T.nangluong + "+" + 11 * n;
                    string = string + "\n" + T.satthuongvatly + "+" + 4 * n;
                    string = string + "\n" + T.satthuongbang + "+" + 4 * n;
                    string = string + "\n" + T.satthuongvatly + this.setpercent(18, n);
                    string = string + "\n" + T.satthuongbang + this.setpercent(18, n);
                    break;
                }
                if (GameScreen.player.clazz == 3) {
                    string = string + "\n" + T.nangluong + "+" + 11 * n;
                    string = string + "\n" + T.satthuongvatly + "+" + 4 * n;
                    string = string + "\n" + T.satthuongdien + "+" + 4 * n;
                    string = string + "\n" + T.satthuongvatly + this.setpercent(18, n);
                    string = string + "\n" + T.satthuongdien + this.setpercent(18, n);
                    break;
                }
                string = string + "\n" + T.nangluong + "+" + 10 * n;
            }
        }
        return string;
    }

    public String setpercent(int n, int n2) {
        String string = "";
        int n3 = n * n2;
        string = n3 % 100 == 0 ? "+" + n3 / 100 + "%" : (n3 % 10 == 0 ? "+" + n3 / 100 + "." + n3 % 100 / 10 + "%" : "+" + n3 / 100 + "." + n3 % 100 / 10 + "" + n3 % 10 + "%");
        return string;
    }
}

