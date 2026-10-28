/*
 * Decompiled with CFR 0.152.
 */
package InterfaceComponents;

import CLib.mFont;
import CLib.mGraphics;
import CLib.mSound;
import GameScreen.PaintInfoGameScreen;
import InterfaceComponents.ChatTextField;
import InterfaceComponents.IAction;
import Main.GameCanvas;
import Model.AvMain;
import Model.FrameImage;

public class iCommand {
    public String caption;
    public String clickCaption = "";
    public String[] subCaption;
    public IAction action;
    public AvMain Pointer;
    public byte indexMenu;
    public byte subIndex = (byte)-1;
    public boolean isSelect = false;
    int nframe;
    int beginframe;
    FrameImage fraImgCaption;
    FrameImage fraImageCmd;
    int wimgCaption;
    int himgCaption;
    int wstr;
    int wimgCmd;
    int himgCmd;
    public int xCmd = -1;
    public int yCmd = -1;
    public int frameCmd;
    public int xdich;
    public int ydich;
    public static int wButtonCmd = 70;
    public static int hButtonCmd = 24;
    public int IdGiaotiep = 0;
    public Object obj;
    public boolean isButtonDai = false;

    public iCommand(String string, IAction iAction) {
        this.caption = string;
        this.action = iAction;
    }

    public iCommand(String string, int n) {
        this.caption = string;
        this.indexMenu = (byte)n;
    }

    public iCommand(String string, int n, AvMain avMain) {
        this.caption = string;
        this.indexMenu = (byte)n;
        this.Pointer = avMain;
    }

    public iCommand(String string, int n, int n2, AvMain avMain) {
        this.caption = string;
        this.indexMenu = (byte)n;
        this.subIndex = (byte)n2;
        this.Pointer = avMain;
    }

    public iCommand(String string, int n, int n2) {
        this.caption = string;
        this.indexMenu = (byte)n;
        this.subIndex = (byte)n2;
    }

    public iCommand(String string, int n, Object object, AvMain avMain) {
        this.caption = string;
        this.indexMenu = (byte)n;
        this.Pointer = avMain;
        this.obj = object;
    }

    public void setFraCaption(FrameImage frameImage) {
        this.fraImgCaption = frameImage;
        this.wimgCaption = this.fraImgCaption.frameWidth;
        this.himgCaption = this.fraImgCaption.frameHeight;
        this.nframe = this.fraImgCaption.nFrame;
        this.beginframe = 0;
        this.wstr = GameCanvas.isSmallScreen ? mFont.tahoma_7_white.getWidth(this.caption) : mFont.tahoma_7b_white.getWidth(this.caption);
    }

    public void setFraCaption(FrameImage frameImage, int n, int n2) {
        this.fraImgCaption = frameImage;
        this.wimgCaption = this.fraImgCaption.frameWidth;
        this.himgCaption = this.fraImgCaption.frameHeight;
        this.nframe = n;
        this.beginframe = n2;
        this.wstr = GameCanvas.isSmallScreen ? mFont.tahoma_7_white.getWidth(this.caption) : mFont.tahoma_7b_white.getWidth(this.caption);
    }

    public void setPosImg(int n, int n2, FrameImage frameImage, int n3, int n4) {
        this.xCmd = n;
        this.yCmd = n2;
        this.fraImgCaption = frameImage;
        this.wimgCaption = this.fraImgCaption.frameWidth;
        this.himgCaption = this.fraImgCaption.frameHeight;
        this.nframe = n3;
        this.beginframe = n4;
        if (this.fraImageCmd != null) {
            this.wimgCmd = this.fraImageCmd.frameWidth;
            this.himgCmd = this.fraImageCmd.frameHeight;
            if (this.wimgCmd < 28) {
                this.wimgCmd = 28;
            }
            if (this.himgCmd < 28) {
                this.himgCmd = 28;
            }
        } else {
            this.wimgCmd = 70;
            this.himgCmd = hButtonCmd;
        }
    }

    public void setPos(int n, int n2, FrameImage frameImage, String string) {
        this.caption = string;
        this.xCmd = n;
        this.yCmd = n2;
        this.fraImageCmd = frameImage;
        if (this.fraImageCmd != null) {
            this.wimgCmd = this.fraImageCmd.frameWidth;
            this.himgCmd = this.fraImageCmd.frameHeight;
            if (this.wimgCmd < 28) {
                this.wimgCmd = 28;
            }
            if (this.himgCmd < 28) {
                this.himgCmd = 28;
            }
        } else {
            this.wimgCmd = 70;
            this.himgCmd = hButtonCmd;
        }
    }

    public void setPos_BoxText(int n, int n2, FrameImage frameImage, String string, int n3, int n4) {
        this.isButtonDai = true;
        this.caption = string;
        this.xCmd = n;
        this.yCmd = n2;
        this.fraImageCmd = frameImage;
        if (this.fraImageCmd != null) {
            this.wimgCmd = this.fraImageCmd.frameWidth;
            this.himgCmd = this.fraImageCmd.frameHeight;
            if (this.wimgCmd < 28) {
                this.wimgCmd = 28;
            }
            if (this.himgCmd < 28) {
                this.himgCmd = 28;
            }
        } else {
            this.wimgCmd = n3;
            this.himgCmd = n4;
        }
    }

    public void setPos_ShowName(int n, int n2, FrameImage frameImage, String string, int n3, int n4) {
        this.caption = "";
        this.clickCaption = string;
        this.xCmd = n;
        this.yCmd = n2;
        this.xdich = n3;
        this.ydich = n4;
        this.fraImageCmd = frameImage;
        if (this.fraImageCmd != null) {
            this.wimgCmd = this.fraImageCmd.frameWidth;
            this.himgCmd = this.fraImageCmd.frameHeight;
            if (this.wimgCmd < 28) {
                this.wimgCmd = 28;
            }
            if (this.himgCmd < 28) {
                this.himgCmd = 28;
            }
        } else {
            this.wimgCmd = 70;
            this.himgCmd = hButtonCmd;
        }
    }

    public void setPosXY(int n, int n2) {
        this.xCmd = n;
        this.yCmd = n2;
    }

    public void perform() {
        if (GameCanvas.menu2.isShowMenu && (GameCanvas.menu2.runText == null || GameCanvas.menu2.runText.checkDlgStep())) {
            GameCanvas.menu2.doCloseMenu();
        }
        if (this.action != null) {
            this.action.perform();
            GameCanvas.isPointerSelect = false;
            GameCanvas.clearKeyHold();
            GameCanvas.clearKeyPressed();
            mSound.playSound(41, mSound.volumeSound);
        } else {
            mSound.playSound(41, mSound.volumeSound);
            if (this.Pointer != null) {
                if (this.obj != null) {
                    this.Pointer.commandPointer((int)this.indexMenu, this.obj);
                } else {
                    this.Pointer.commandPointer((int)this.indexMenu, this.subIndex);
                }
            } else {
                if (GameCanvas.currentDialog != null) {
                    GameCanvas.currentDialog.commandTab(this.indexMenu, this.subIndex);
                    GameCanvas.isPointerSelect = false;
                    GameCanvas.clearKeyHold();
                    GameCanvas.clearKeyPressed();
                    return;
                }
                if (ChatTextField.isShow) {
                    ChatTextField.gI().commandTab(this.indexMenu, this.subIndex);
                    return;
                }
                if (GameCanvas.menu2.isShowMenu) {
                    GameCanvas.currentScreen.commandMenu(this.indexMenu, this.subIndex);
                    return;
                }
                if (GameCanvas.subDialog != null) {
                    GameCanvas.subDialog.commandTab(this.indexMenu, this.subIndex);
                    return;
                }
                GameCanvas.currentScreen.commandTab(this.indexMenu, this.subIndex);
                return;
            }
            GameCanvas.isPointerSelect = false;
            GameCanvas.clearKeyHold();
            GameCanvas.clearKeyPressed();
        }
    }

    public void update() {
    }

    public void paint(mGraphics mGraphics2, int n, int n2) {
        if (this.isPosCmd()) {
            if (this.fraImageCmd != null) {
                this.fraImageCmd.drawFrame(this.frameCmd, this.xCmd, this.yCmd, 0, 3, mGraphics2);
            } else {
                this.paintbutton(mGraphics2, this.xCmd, this.yCmd);
            }
            this.paintCaptionImage(mGraphics2, this.xCmd, this.yCmd - 6, 2);
        } else {
            if (this.fraImageCmd != null) {
                this.fraImageCmd.drawFrame(this.frameCmd, n, n2, 0, 3, mGraphics2);
            } else {
                this.paintbutton(mGraphics2, n, n2);
            }
            this.paintCaptionImage(mGraphics2, n, n2 - 6, 2);
        }
    }

    public void paintCaptionImage(mGraphics mGraphics2, int n, int n2, int n3) {
        if (this.caption == null) {
            return;
        }
        int n4 = 0;
        if (this.fraImgCaption != null) {
            if (n3 == 2) {
                this.fraImgCaption.drawFrame(this.beginframe + GameCanvas.gameTick / 2 % this.nframe, n - this.wimgCaption / 2 - this.wstr / 2, n2 + this.himgCaption / 2 - 3, 0, 3, mGraphics2);
                n4 = this.wimgCaption / 2;
            } else if (n3 == 0) {
                this.fraImgCaption.drawFrame(this.beginframe + GameCanvas.gameTick / 2 % this.nframe, n + this.wimgCaption / 2, n2 + this.himgCaption / 2 - 4, 0, 3, mGraphics2);
                n4 = this.wimgCaption + 6;
            }
        }
        if (!this.isButtonDai) {
            if (GameCanvas.isSmallScreen) {
                mFont.tahoma_7_white.drawString(mGraphics2, this.caption, n + n4, n2, n3, false);
            } else {
                AvMain.Font3dColor(mGraphics2, this.caption, n + n4, n2, n3, (byte)0);
            }
        } else if (GameCanvas.isSmallScreen) {
            mFont.tahoma_7_white.drawString(mGraphics2, this.caption, n + n4 + this.wimgCmd / 2, n2 + this.himgCmd / 2 + 1, n3, false);
        } else {
            AvMain.Font3dColor(mGraphics2, this.caption, n + n4 + this.wimgCmd / 2, n2 + this.himgCmd / 2 + 1, n3, (byte)0);
        }
    }

    public void paintClickCaption(mGraphics mGraphics2, int n, int n2, int n3) {
        if (this.frameCmd == 1 && this.clickCaption.length() > 0) {
            AvMain.Font3dColor(mGraphics2, this.clickCaption, n + this.xdich, n2 + this.ydich, n3, (byte)0);
        }
    }

    public void paintCaptionImageMenu(mGraphics mGraphics2, int n, int n2, int n3) {
        int n4 = 0;
        if (this.fraImgCaption != null) {
            if (n3 == 2) {
                this.fraImgCaption.drawFrame(this.beginframe + GameCanvas.gameTick / 2 % this.nframe, n - this.wimgCaption / 2 - this.wstr / 2, n2 + this.himgCaption / 2, 0, 3, mGraphics2);
                n4 = this.wimgCaption / 2;
            } else if (n3 == 0) {
                this.fraImgCaption.drawFrame(this.beginframe + GameCanvas.gameTick / 2 % this.nframe, n + this.wimgCaption / 2, n2 + this.himgCaption / 2, 0, 3, mGraphics2);
                n4 = this.wimgCaption + 6;
            }
        }
        if (GameCanvas.isSmallScreen) {
            mFont.tahoma_7_white.drawString(mGraphics2, this.caption, n + n4, n2, n3, false);
        } else {
            AvMain.Font3dColorAndColor(mGraphics2, this.caption, n + n4, n2, n3, (byte)7, (byte)0);
        }
    }

    public void paintImage(mGraphics mGraphics2, int n, int n2, int n3, int n4) {
        if (this.fraImgCaption != null) {
            if (n4 == 0) {
                this.fraImgCaption.drawFrame(this.beginframe + GameCanvas.gameTick / 2 % this.nframe, n, n2 + this.himgCaption / 2, 0, n3, mGraphics2);
            } else if (n4 == 1) {
                int n5;
                int n6 = n5 = this.beginframe + this.frameCmd == 1 ? 1 : 0;
                if (n5 > this.nframe) {
                    n5 = this.beginframe - 1;
                }
                this.fraImgCaption.drawFrame(n5, n, n2 + this.himgCaption / 2, 0, n3, mGraphics2);
            } else {
                this.fraImgCaption.drawFrame(this.beginframe, n, n2 + this.himgCaption / 2, 0, n3, mGraphics2);
            }
        }
    }

    public void updatePointer() {
        if (!this.isButtonDai) {
            if (this.isPosCmd()) {
                this.frameCmd = GameCanvas.isPointerDown || GameCanvas.isPointerMove ? (GameCanvas.isPoint(this.xCmd - this.wimgCmd / 2 - 5, this.yCmd - this.himgCmd / 2 - 5, this.wimgCmd + 10, this.himgCmd + 10) ? 1 : 0) : 0;
                if (GameCanvas.isPointSelect(this.xCmd - this.wimgCmd / 2 - 5, this.yCmd - this.himgCmd / 2 - 5, this.wimgCmd + 10, this.himgCmd + 10)) {
                    this.perform();
                    GameCanvas.isPointerSelect = false;
                    this.frameCmd = 0;
                }
            }
        } else if (this.isPosCmd()) {
            if (GameCanvas.isPointerDown || GameCanvas.isPointerMove) {
                if (GameCanvas.isPoint(this.xCmd - 5, this.yCmd - 5, this.wimgCmd + 10, this.himgCmd + 10)) {
                    this.isSelect = true;
                    this.frameCmd = 1;
                } else {
                    this.frameCmd = 0;
                    this.isSelect = false;
                }
            } else {
                this.frameCmd = 0;
                this.isSelect = false;
            }
            if (GameCanvas.isPointSelect(this.xCmd - 5, this.yCmd - 5, this.wimgCmd + 10, this.himgCmd + 10)) {
                this.perform();
                GameCanvas.isPointerSelect = false;
                this.frameCmd = 0;
                this.isSelect = false;
            }
        }
    }

    public void updatePointer(int n, int n2) {
        if (this.isPosCmd()) {
            this.frameCmd = GameCanvas.isPointerDown || GameCanvas.isPointerMove ? (GameCanvas.isPoint(this.xCmd - this.wimgCmd / 2 - 3 - n, this.yCmd - this.himgCmd / 2 - 3 - n2, this.wimgCmd + 6, this.himgCmd + 6) ? 1 : 0) : 0;
            if (GameCanvas.isPointSelect(this.xCmd - this.wimgCmd / 2 - 3 - n, this.yCmd - this.himgCmd / 2 - 3 - n2, this.wimgCmd + 6, this.himgCmd + 6)) {
                this.perform();
                GameCanvas.isPointerSelect = false;
                this.frameCmd = 0;
            }
        }
    }

    public void updatePointerShow(int n, int n2) {
        if (this.isPosCmd()) {
            this.frameCmd = GameCanvas.isPointerDown || GameCanvas.isPointerMove ? (GameCanvas.isPoint(this.xCmd - this.wimgCmd / 2 - 3 - n, this.yCmd - this.himgCmd / 2 - 3 - n2, this.wimgCmd + 6, this.himgCmd + 6) ? 1 : 0) : 0;
        }
    }

    public void paintbutton(mGraphics mGraphics2, int n, int n2) {
        if (GameCanvas.isTouch) {
            if (!this.isButtonDai) {
                PaintInfoGameScreen.fraButton.drawFrame(this.frameCmd, n, n2, 0, 3, mGraphics2);
            } else {
                AvMain.paintDialog(mGraphics2, n, n2, this.wimgCmd, this.himgCmd, this.isSelect ? 1 : 2);
            }
        } else if (!this.isButtonDai) {
            AvMain.paintDialog(mGraphics2, n - wButtonCmd / 2, n2 - hButtonCmd / 2, wButtonCmd, hButtonCmd, this.isSelect ? 1 : 2);
        } else {
            AvMain.paintDialog(mGraphics2, n, n2, this.wimgCmd, this.himgCmd, this.isSelect ? 1 : 2);
        }
    }

    public boolean isPosCmd() {
        return this.xCmd > 0 && this.yCmd > 0;
    }
}

