/*
 * Decompiled with CFR 0.152.
 */
package Model;

import CLib.mFont;
import CLib.mGraphics;
import CLib.mImage;
import InterfaceComponents.MainTabNew;
import InterfaceComponents.iCommand;
import Main.GameCanvas;
import Model.FrameImage;

public class AvMain {
    public iCommand left;
    public iCommand right;
    public iCommand center;
    public static mImage[] tab = new mImage[4];
    public static int wimg;
    public static mImage[] imghitScr;
    public static mImage imgtextfield;
    public static mImage imgInfo;
    public static mImage imghpmp;
    public static mImage imgcolorhpmp;
    public static mImage imgPopup;
    public static mImage imgBackInfo;
    public static mImage imgWorldMap;
    public static mImage imgSelect;
    public static mImage imgFocusMap;
    public static mImage imgDelaySkill;
    public static mImage imgLoadImg;
    public static mImage imgEyeDie;
    public static mImage imgHotKey;
    public static mImage imgHotKey2;
    public static mImage imgMess;
    public static mImage imgColorItem;
    public static mImage imgicongt;
    public static mImage imgGlass;
    public static mImage imgLock;
    public static mImage imgRect;
    public static mImage imgSelect_1;
    public static mImage imgcolorhpmp_back;
    public static mImage imgcolorhpSmall_back;
    public static mImage imgcolorhpSmall;
    public static mImage img18Plus;
    public static FrameImage fraPlayerDie;
    public static FrameImage fraObjMiniMap;
    public static FrameImage fraPk;
    public static FrameImage fraStar;
    public static FrameImage fraQuest;
    public static FrameImage fraMonSample;
    public static FrameImage fraDiamond;
    public static FrameImage fraPlayerNo;
    public static FrameImage imgStun;
    public static FrameImage imgSleep;
    public static FrameImage fraFogetPass;
    public static mImage[] textf;
    public static final int SELECTED_COLOR = -6517641;
    public static int[] color;
    static int[] colorDia;

    public void paint(mGraphics mGraphics2) {
        GameCanvas.resetTrans(mGraphics2);
        if (GameCanvas.currentDialog == null && !GameCanvas.menu2.isShowMenu) {
            this.paintCmd(mGraphics2);
        }
    }

    public void update() {
    }

    public void keypress(int n) {
    }

    public void paintCmd(mGraphics mGraphics2) {
        if (GameCanvas.isSmallScreen) {
            this.paintCmdSmall(mGraphics2);
        } else {
            if (this.left != null) {
                this.left.paint(mGraphics2, GameCanvas.wCommand, GameCanvas.h - iCommand.hButtonCmd / 2 - 1);
            }
            if (this.right != null) {
                this.right.paint(mGraphics2, GameCanvas.w - GameCanvas.wCommand, GameCanvas.h - iCommand.hButtonCmd / 2 - 1);
            }
            if (this.center != null) {
                this.center.paint(mGraphics2, GameCanvas.hw, GameCanvas.h - iCommand.hButtonCmd / 2 - 1);
            }
        }
    }

    public void paintCmd_OnlyText(mGraphics mGraphics2) {
        if (GameCanvas.menu2.isShowMenu || GameCanvas.currentDialog != null) {
            return;
        }
        GameCanvas.resetTrans(mGraphics2);
        if (GameCanvas.isSmallScreen) {
            this.paintCmd_OnlyText_Small(mGraphics2);
        } else {
            if (this.left != null) {
                AvMain.Font3dWhite(mGraphics2, this.left.caption, 30, GameCanvas.h - GameCanvas.hCommand / 2 - 4, 2);
            }
            if (this.right != null) {
                AvMain.Font3dWhite(mGraphics2, this.right.caption, GameCanvas.w - 30, GameCanvas.h - GameCanvas.hCommand / 2 - 4, 2);
            }
            if (this.center != null) {
                AvMain.Font3dWhite(mGraphics2, this.center.caption, GameCanvas.hw, GameCanvas.h - GameCanvas.hCommand / 2 - 4, 2);
            }
        }
    }

    public void paintCmdSmall(mGraphics mGraphics2) {
        GameCanvas.resetTrans(mGraphics2);
        if (this.left != null) {
            this.left.paint(mGraphics2, GameCanvas.wCommand, GameCanvas.h - iCommand.hButtonCmd / 2 - 1);
        }
        if (this.right != null) {
            this.right.paint(mGraphics2, GameCanvas.w - GameCanvas.wCommand, GameCanvas.h - iCommand.hButtonCmd / 2 - 1);
        }
        if (this.center != null) {
            this.center.paint(mGraphics2, GameCanvas.hw, GameCanvas.h - iCommand.hButtonCmd / 2 - 1);
        }
    }

    public void paintCmd_OnlyText_Small(mGraphics mGraphics2) {
        if (this.left != null) {
            mFont.tahoma_7b_white.drawString(mGraphics2, this.left.caption, 27, GameCanvas.h - GameCanvas.hCommand / 2 + 1, 2, false);
        }
        if (this.right != null) {
            mFont.tahoma_7b_white.drawString(mGraphics2, this.right.caption, GameCanvas.w - 27, GameCanvas.h - GameCanvas.hCommand / 2 + 1, 2, false);
        }
        if (this.center != null) {
            mFont.tahoma_7b_white.drawString(mGraphics2, this.center.caption, GameCanvas.hw, GameCanvas.h - GameCanvas.hCommand / 2 + 1, 2, false);
        }
    }

    public void commandTab(int n, int n2) {
    }

    public void commandMenu(int n, int n2) {
    }

    public void commandPointer(int n, int n2) {
    }

    public void commandPointer(int n, Object object) {
    }

    public void paintRect(mGraphics mGraphics2, int n, int n2, int n3, int n4, boolean bl) {
        mGraphics2.setColor(0);
        mGraphics2.fillRect(n, n2, n3, n4, false);
        mGraphics2.setColor(-15703219);
        if (bl) {
            mGraphics2.setColor(-1);
        }
        mGraphics2.fillRect(n + 2, n2 + 2, n3 - 4, n4 - 4, false);
    }

    public void paintRect(mGraphics mGraphics2, int n, int n2, int n3, int n4) {
        mGraphics2.setColor(0);
        mGraphics2.fillRect(n, n2, n3, n4, false);
        mGraphics2.setColor(-15703219);
        mGraphics2.fillRect(n + 2, n2 + 2, n3 - 4, n4 - 4, false);
    }

    public void paintRect(mGraphics mGraphics2, int n, int n2, int n3, int n4, int n5, int n6) {
        mGraphics2.setColor(n5);
        mGraphics2.fillRect(n, n2, n3, n4, false);
        mGraphics2.setColor(n6);
        mGraphics2.fillRect(n + 2, n2 + 2, n3 - 4, n4 - 4, false);
    }

    public static void paintDialog(mGraphics mGraphics2, int n, int n2, int n3, int n4, int n5) {
        if (n3 < 35) {
            n3 = 35;
        }
        int n6 = (n3 - 6) / 32;
        int n7 = (n4 - 6) / 32;
        if (n4 % 2 != 0) {
            ++n4;
        }
        if (GameCanvas.lowGraphic) {
            if (n5 > 2) {
                n5 = n5 == 8 || n5 == 12 ? 4 : 3;
            }
            mGraphics2.setColor(colorDia[0]);
            mGraphics2.fillRect(n, n2, n3, n4, false);
            mGraphics2.setColor(colorDia[4]);
            mGraphics2.drawRect(n + 1, n2 + 1, n3 - 3, n4 - 3, false);
            MainTabNew.paintRectLowGraphic(mGraphics2, n + 3, n2 + 3, n3 - 6, n4 - 6, n5);
        } else {
            if (n4 < 32) {
                for (int i = 0; i <= n6; ++i) {
                    for (int j = 0; j <= n7; ++j) {
                        if (i == n6) {
                            if (j == n7) {
                                mGraphics2.drawRegion(MainTabNew.imgTab[n5], 0, 0, 32, n4, 0, n - 3 + n3 - 32, n2, 0, false);
                                continue;
                            }
                            mGraphics2.drawRegion(MainTabNew.imgTab[n5], 0, 0, 32, n4, 0, n - 3 + n3 - 32, n2 + 3 + 32 * j, 0, false);
                            continue;
                        }
                        if (j == n7) {
                            mGraphics2.drawRegion(MainTabNew.imgTab[n5], 0, 0, 32, n4, 0, n + 3 + i * 32, n2, 0, false);
                            continue;
                        }
                        mGraphics2.drawRegion(MainTabNew.imgTab[n5], 0, 0, 32, n4, 0, n + 3 + i * 32, n2 + 3 + 32 * j, 0, false);
                    }
                }
            } else {
                for (int i = 0; i <= n6; ++i) {
                    for (int j = 0; j <= n7; ++j) {
                        if (i == n6) {
                            if (j == n7) {
                                mGraphics2.drawImage(MainTabNew.imgTab[n5], n - 3 + n3 - 32, n2 - 3 + n4 - 32, 0, false);
                                continue;
                            }
                            mGraphics2.drawImage(MainTabNew.imgTab[n5], n - 3 + n3 - 32, n2 + 3 + 32 * j, 0, false);
                            continue;
                        }
                        if (j == n7) {
                            mGraphics2.drawImage(MainTabNew.imgTab[n5], n + 3 + i * 32, n2 - 3 + n4 - 32, 0, false);
                            continue;
                        }
                        mGraphics2.drawImage(MainTabNew.imgTab[n5], n + 3 + i * 32, n2 + 3 + 32 * j, 0, false);
                    }
                }
            }
            mGraphics2.drawRegion(imgPopup, 0, 0, 5, 5, 0, n, n2, 0, false);
            mGraphics2.drawRegion(imgPopup, 0, 5, 5, 5, 0, n + n3 - 5, n2, 0, false);
            mGraphics2.drawRegion(imgPopup, 0, 15, 5, 5, 0, n, n2 + n4 - 5, 0, false);
            mGraphics2.drawRegion(imgPopup, 0, 10, 5, 5, 0, n + n3 - 5, n2 + n4 - 5, 0, false);
            mGraphics2.setColor(colorDia[0]);
            mGraphics2.fillRect(n + 3, n2, n3 - 6, 1, false);
            mGraphics2.fillRect(n, n2 + 3, 1, n4 - 6, false);
            mGraphics2.setColor(colorDia[1]);
            mGraphics2.fillRect(n + 3, n2 + 1, n3 - 6, 1, false);
            mGraphics2.fillRect(n + 1, n2 + 3, 1, n4 - 6, false);
            mGraphics2.setColor(colorDia[2]);
            mGraphics2.fillRect(n + 3, n2 + 2, n3 - 6, 1, false);
            mGraphics2.fillRect(n + 2, n2 + 3, 1, n4 - 6, false);
            mGraphics2.setColor(colorDia[2]);
            mGraphics2.fillRect(n + 3, n2 + n4 - 1, n3 - 6, 1, false);
            mGraphics2.fillRect(n + n3 - 1, n2 + 3, 1, n4 - 6, false);
            mGraphics2.setColor(colorDia[4]);
            mGraphics2.fillRect(n + 3, n2 + n4 - 2, n3 - 6, 1, false);
            mGraphics2.fillRect(n + n3 - 2, n2 + 3, 1, n4 - 6, false);
            mGraphics2.setColor(colorDia[0]);
            mGraphics2.fillRect(n + 3, n2 + n4 - 3, n3 - 6, 1, false);
            mGraphics2.fillRect(n + n3 - 3, n2 + 3, 1, n4 - 6, false);
        }
    }

    public static void paintDialogNew(mGraphics mGraphics2, int n, int n2, int n3, int n4, int n5) {
        if (GameCanvas.lowGraphic) {
            AvMain.paintDialog(mGraphics2, n, n2, n3, n4, n5);
        } else {
            int n6 = (n3 - 6) / 32;
            int n7 = (n4 - 6) / 32;
            if (n4 % 2 != 0) {
                ++n4;
            }
            for (int i = 0; i <= n6; ++i) {
                for (int j = 0; j <= n7; ++j) {
                    if (j > 1 && j < n7 - 1 && i != 0 && i != n6) continue;
                    if (i == n6) {
                        if (j == n7) {
                            mGraphics2.drawImage(MainTabNew.imgTab[n5], n - 3 + n3 - 32, n2 - 3 + n4 - 32, 0, false);
                            continue;
                        }
                        mGraphics2.drawImage(MainTabNew.imgTab[n5], n - 3 + n3 - 32, n2 + 3 + 32 * j, 0, false);
                        continue;
                    }
                    if (j == n7) {
                        mGraphics2.drawImage(MainTabNew.imgTab[n5], n + 3 + i * 32, n2 - 3 + n4 - 32, 0, false);
                        continue;
                    }
                    mGraphics2.drawImage(MainTabNew.imgTab[n5], n + 3 + i * 32, n2 + 3 + 32 * j, 0, false);
                }
            }
            mGraphics2.drawRegion(imgPopup, 0, 0, 5, 5, 0, n, n2, 0, false);
            mGraphics2.drawRegion(imgPopup, 0, 5, 5, 5, 0, n + n3 - 5, n2, 0, false);
            mGraphics2.drawRegion(imgPopup, 0, 15, 5, 5, 0, n, n2 + n4 - 5, 0, false);
            mGraphics2.drawRegion(imgPopup, 0, 10, 5, 5, 0, n + n3 - 5, n2 + n4 - 5, 0, false);
            mGraphics2.setColor(colorDia[0]);
            mGraphics2.fillRect(n + 3, n2, n3 - 6, 1, false);
            mGraphics2.fillRect(n, n2 + 3, 1, n4 - 6, false);
            mGraphics2.setColor(colorDia[1]);
            mGraphics2.fillRect(n + 3, n2 + 1, n3 - 6, 1, false);
            mGraphics2.fillRect(n + 1, n2 + 3, 1, n4 - 6, false);
            mGraphics2.setColor(colorDia[2]);
            mGraphics2.fillRect(n + 3, n2 + 2, n3 - 6, 1, false);
            mGraphics2.fillRect(n + 2, n2 + 3, 1, n4 - 6, false);
            mGraphics2.setColor(colorDia[2]);
            mGraphics2.fillRect(n + 3, n2 + n4 - 1, n3 - 6, 1, false);
            mGraphics2.fillRect(n + n3 - 1, n2 + 3, 1, n4 - 6, false);
            mGraphics2.setColor(colorDia[4]);
            mGraphics2.fillRect(n + 3, n2 + n4 - 2, n3 - 6, 1, false);
            mGraphics2.fillRect(n + n3 - 2, n2 + 3, 1, n4 - 6, false);
            mGraphics2.setColor(colorDia[0]);
            mGraphics2.fillRect(n + 3, n2 + n4 - 3, n3 - 6, 1, false);
            mGraphics2.fillRect(n + n3 - 3, n2 + 3, 1, n4 - 6, false);
        }
    }

    public static void paintTabNew(mGraphics mGraphics2, int n, int n2, int n3, int n4, boolean bl, byte by) {
        if (GameCanvas.lowGraphic) {
            AvMain.paintDialog(mGraphics2, n, n2, n3, n4, by);
        } else {
            if (n4 < 32) {
                n4 = 32;
            }
            mGraphics2.setColor(color[0]);
            mGraphics2.fillRect(n + wimg - 2, n2 + 3, n3 - 2 * wimg + 4, n4 - 5, false);
            mGraphics2.fillRect(n + 4, n2 + wimg - 2, n3 - 8, n4 - 2 * wimg + 4, false);
            mGraphics2.setColor(color[1]);
            mGraphics2.fillRect(n + wimg - 2, n2 + 4, n3 - 2 * wimg + 4, n4 - 7, false);
            mGraphics2.fillRect(n + 5, n2 + wimg - 2, n3 - 10, n4 - 2 * wimg + 4, false);
            mGraphics2.setColor(color[0]);
            mGraphics2.fillRect(n + wimg - 2, n2 + 5, n3 - 2 * wimg + 4, n4 - 9, false);
            mGraphics2.fillRect(n + 6, n2 + wimg - 2, n3 - 12, n4 - 2 * wimg + 4, false);
            mGraphics2.setColor(color[2]);
            mGraphics2.fillRect(n + 7, n2 + 6, n3 - 14, n4 - 12, false);
            for (int i = 0; i <= (n3 - 15) / 32; ++i) {
                for (int j = 0; j <= (n4 - 11) / 32; ++j) {
                    if (i == (n3 - 15) / 32) {
                        if (j == (n4 - 11) / 32) {
                            mGraphics2.drawImage(MainTabNew.imgTab[by], n + n3 - 39, n2 + n4 - 37, 0, false);
                            continue;
                        }
                        mGraphics2.drawImage(MainTabNew.imgTab[by], n + n3 - 39, n2 + 7 + j * 32, 0, false);
                        continue;
                    }
                    if (j == (n4 - 11) / 32) {
                        mGraphics2.drawImage(MainTabNew.imgTab[by], n + 8 + i * 32, n2 + n4 - 37, 0, false);
                        continue;
                    }
                    mGraphics2.drawImage(MainTabNew.imgTab[by], n + 8 + i * 32, n2 + 7 + j * 32, 0, false);
                }
            }
            mGraphics2.drawImage(tab[0], n, n2, 0, false);
            mGraphics2.drawRegion(tab[0], 0, 0, wimg, wimg, 2, n + n3 - wimg, n2, 0, false);
            mGraphics2.drawImage(tab[1], n + 2, n2 + n4 - wimg, 0, false);
            mGraphics2.drawRegion(tab[1], 0, 0, 30, 30, 2, n + n3 - 32, n2 + n4 - wimg, 0, false);
            if (bl) {
                mGraphics2.drawImage(tab[2], n + n3 / 2, n2 + 2, 3, false);
            }
        }
    }

    public static void paintRectNice(mGraphics mGraphics2, int n, int n2, int n3, int n4, byte by) {
        if (n4 % 2 == 1) {
            ++n4;
        }
        if (GameCanvas.lowGraphic) {
            if (by > 2) {
                by = by == 8 || by == 12 ? (byte)4 : (byte)3;
            }
            MainTabNew.paintRectLowGraphic(mGraphics2, n, n2, n3, n4, by);
        } else {
            for (int i = 0; i <= n3 / 32; ++i) {
                for (int j = 0; j <= n4 / 32; ++j) {
                    if (i == n3 / 32) {
                        if (j == n4 / 32) {
                            mGraphics2.drawImage(MainTabNew.imgTab[by], n + n3 - 32, n2 + n4 - 32, 0, false);
                            continue;
                        }
                        mGraphics2.drawImage(MainTabNew.imgTab[by], n + n3 - 32, n2 + j * 32, 0, false);
                        continue;
                    }
                    if (j == n4 / 32) {
                        mGraphics2.drawImage(MainTabNew.imgTab[by], n + i * 32, n2 + n4 - 32, 0, false);
                        continue;
                    }
                    mGraphics2.drawImage(MainTabNew.imgTab[by], n + i * 32, n2 + j * 32, 0, false);
                }
            }
        }
    }

    public static void fill(int n, int n2, int n3, int n4, int n5, mGraphics mGraphics2) {
        mGraphics2.setColor(n5);
        mGraphics2.fillRect(n, n2, n3, n4, false);
    }

    public static void paintRectText(mGraphics mGraphics2, int n, int n2, int n3, int n4, boolean bl) {
        mGraphics2.setColor(-4155296);
        if (bl) {
            mGraphics2.setColor(-132648);
        }
        mGraphics2.drawRegion(imgtextfield, 0, bl ? 30 : 0, 4, 15, 0, n, n2, 0, true);
        mGraphics2.drawRegion(imgtextfield, 0, (bl ? 30 : 0) + 15, 4, 15, 0, n + n3 - 4, n2 + n4 - 15, 0, true);
        mGraphics2.drawRegion(imgtextfield, 0, (bl ? 30 : 0) + 11, 4, 4, 0, n, n2 + n4 - 4, 0, true);
        mGraphics2.drawRegion(imgtextfield, 0, (bl ? 30 : 0) + 15, 4, 4, 0, n + n3 - 4, n2, 0, true);
        mGraphics2.fillRect(n + 4, n2, n3 - 8, n4, true);
        mGraphics2.fillRect(n, n2 + 4, n3, n4 - 8, true);
    }

    public void paintSelect(mGraphics mGraphics2, int n, int n2, int n3, int n4) {
        mGraphics2.setColor(-6517641);
        mGraphics2.fillRect(n, n2, n3, n4, false);
    }

    public void paintFormList(mGraphics mGraphics2, int n, int n2, int n3, int n4, String string) {
        AvMain.paintDialogNew(mGraphics2, n - 6, n2 - 6, n3 + 12, n4 + 12, 0);
        AvMain.paintRectNice(mGraphics2, n, n2 + GameCanvas.hCommand, n3, n4 - GameCanvas.hCommand, (byte)2);
        MainTabNew.paintNameItem(mGraphics2, n + n3 / 2, n2 + GameCanvas.hCommand / 4, n3, string, 7);
    }

    public int resetSelect(int n, int n2, boolean bl) {
        if (n < 0) {
            n = bl ? n2 : 0;
        } else if (n > n2) {
            n = bl ? 0 : n2;
        }
        return n;
    }

    public void updatekey() {
        if (GameCanvas.keyMyHold[5]) {
            if (this.center != null) {
                GameCanvas.clearKeyPressed(5);
                GameCanvas.clearKeyHold(5);
                this.center.perform();
            }
        } else if (GameCanvas.keyMyHold[12]) {
            if (this.left != null) {
                GameCanvas.clearKeyPressed(12);
                GameCanvas.clearKeyHold(12);
                this.left.perform();
            }
        } else if (GameCanvas.keyMyHold[13] && this.right != null) {
            GameCanvas.clearKeyPressed(13);
            GameCanvas.clearKeyHold(13);
            this.right.perform();
        }
    }

    public void updatePointer() {
        if (GameCanvas.isTouch) {
            if (this.left != null) {
                if (this.left.isPosCmd()) {
                    this.left.updatePointer();
                } else if (GameCanvas.isPointSelect(0, GameCanvas.h - GameCanvas.hCommand - 5, GameCanvas.wCommand * 2, GameCanvas.hCommand + 10)) {
                    this.left.perform();
                }
            }
            if (this.right != null) {
                if (this.right.isPosCmd()) {
                    this.right.updatePointer();
                } else if (GameCanvas.isPointSelect(GameCanvas.w - GameCanvas.wCommand * 2, GameCanvas.h - GameCanvas.hCommand - 5, GameCanvas.wCommand * 2, GameCanvas.hCommand + 10)) {
                    this.right.perform();
                }
            }
            if (this.center != null) {
                if (this.center.isPosCmd()) {
                    this.center.updatePointer();
                } else if (GameCanvas.isPointSelect(GameCanvas.hw - GameCanvas.wCommand, GameCanvas.h - GameCanvas.hCommand - 5, GameCanvas.wCommand * 2, GameCanvas.hCommand + 10)) {
                    this.center.perform();
                }
            }
        }
    }

    public static void Font3dWhite(mGraphics mGraphics2, String string, int n, int n2, int n3) {
        mFont.tahoma_7b_black.drawString(mGraphics2, string, n + 1, n2 + 1, n3, true);
        mFont.tahoma_7b_white.drawString(mGraphics2, string, n, n2, n3, true);
    }

    public static void Font3dColor(mGraphics mGraphics2, String string, int n, int n2, int n3, byte by) {
        mFont.tahoma_7b_black.drawString(mGraphics2, string, n + 1, n2 + 1, n3, true);
        MainTabNew.setTextColorName(by).drawString(mGraphics2, string, n, n2, n3, true);
    }

    public static void Font3dColorAndColor(mGraphics mGraphics2, String string, int n, int n2, int n3, byte by, byte by2) {
        MainTabNew.setTextColorName(by).drawString(mGraphics2, string, n + 1, n2 + 1, n3, true);
        MainTabNew.setTextColorName(by2).drawString(mGraphics2, string, n, n2, n3, true);
    }

    public static void FontBorderColor(mGraphics mGraphics2, String string, int n, int n2, int n3, int n4) {
        if (n4 == 2) {
            mFont.tahoma_7b_black.drawString(mGraphics2, string, n + 1, n2 + 1, n3, true);
        } else {
            mFont.tahoma_7b_black.drawString(mGraphics2, string, n - 1, n2 - 1, n3, true);
            mFont.tahoma_7b_black.drawString(mGraphics2, string, n - 1, n2 + 1, n3, true);
            mFont.tahoma_7b_black.drawString(mGraphics2, string, n + 1, n2 - 1, n3, true);
            mFont.tahoma_7b_black.drawString(mGraphics2, string, n + 1, n2 + 1, n3, true);
            mFont.tahoma_7b_black.drawString(mGraphics2, string, n - 1, n2, n3, true);
            mFont.tahoma_7b_black.drawString(mGraphics2, string, n + 1, n2, n3, true);
            mFont.tahoma_7b_black.drawString(mGraphics2, string, n, n2 - 1, n3, true);
            mFont.tahoma_7b_black.drawString(mGraphics2, string, n, n2 + 1, n3, true);
        }
        MainTabNew.setTextColorName(n4).drawString(mGraphics2, string, n, n2, n3, false);
    }

    static {
        imghitScr = new mImage[3];
        textf = new mImage[2];
        color = new int[]{-12052464, -477112, -6778760, -2043720, -6649744};
        colorDia = new int[]{-8902126, -460632, -11467264, -477112, -477112};
    }
}

