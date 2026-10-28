/*
 * Decompiled with CFR 0.152.
 */
package GameScreen;

import CLib.mGraphics;
import CLib.mSound;
import CLib.mSystem;
import CLib.mVector;
import GameScreen.GameScreen;
import GameScreen.MainScreen;
import GameScreen.PaintInfoGameScreen;
import InterfaceComponents.MainTabNew;
import InterfaceComponents.TabRebuildItem;
import InterfaceComponents.iCommand;
import Main.GameCanvas;
import Model.T;

public class TabScreenNew
extends MainScreen {
    public int selectTab = 0;
    public mVector VecTabScreen = new mVector("TabScreenNew VecTabScreen");
    public static iCommand cmdTab;
    public static iCommand cmdBack;
    public static final int CMD_TAB_CLOSE = 0;
    public static final int CMD_BACK_TAB = 1;
    public static int timeRepaint;
    public static int xback;
    public static int yback;

    public TabScreenNew() {
        cmdBack = new iCommand(T.close, 0, this);
        xback = GameCanvas.w - 20;
        yback = 17;
        if (GameCanvas.isTouch) {
            cmdBack.setPos(xback, yback, PaintInfoGameScreen.fraCloseMenu, "");
        }
    }

    public void setCaptionCmd() {
        if (!GameCanvas.isTouch) {
            TabScreenNew.cmdBack.caption = T.close;
        }
    }

    public void Show() {
        mSystem.outloi("goi ham co truyen lastScreen");
    }

    public void Show(MainScreen mainScreen) {
        timeRepaint = 10;
        if (GameScreen.help.setStep_Next(6, 2)) {
            this.selectTab = 2;
        }
        if (GameCanvas.isTouch) {
            MainTabNew.Focus = 1;
            MainTabNew mainTabNew = this.getCurrentTab();
            mainTabNew.init();
            MainTabNew.timePaintInfo = 0;
            this.right = cmdBack;
        } else {
            MainTabNew.Focus = 0;
        }
        super.Show();
        GameCanvas.currentScreen.lastScreen = mainScreen;
    }

    public void commandPointer(int n, int n2) {
        switch (n) {
            case 0: {
                if (this.lastScreen == null || this.lastScreen == GameCanvas.currentScreen || GameCanvas.currentScreen == GameCanvas.AllInfo) {
                    GameCanvas.game.Show();
                } else {
                    this.lastScreen.Show(this.lastScreen.lastScreen);
                }
                if (!GameScreen.help.setStep_Next(4, 9)) break;
                GameScreen.help.NextStep();
                GameScreen.help.setNext();
            }
        }
    }

    public void addMoreTab(mVector mVector2) {
        this.VecTabScreen.removeAllElements();
        this.VecTabScreen = mVector2;
    }

    public void paint(mGraphics mGraphics2) {
        MainTabNew mainTabNew = this.getCurrentTab();
        GameCanvas.resetTrans(mGraphics2);
        MainTabNew.gI().paintTab(mGraphics2, mainTabNew.nameTab, this.selectTab, this.VecTabScreen, mainTabNew.isClan);
        mainTabNew.paint(mGraphics2);
        GameCanvas.resetTrans(mGraphics2);
        if (!GameCanvas.menu2.isShowMenu && GameCanvas.currentDialog == null && GameCanvas.subDialog == null && GameCanvas.currentScreen == this) {
            if (GameCanvas.isTouch) {
                super.paintCmd(mGraphics2);
            } else if (MainTabNew.Focus == 0) {
                this.paintCmd_OnlyText(mGraphics2);
            } else {
                mainTabNew.paintCmd_OnlyText(mGraphics2);
            }
        } else {
            timeRepaint = 10;
        }
        if (GameScreen.help.Step >= 0 && !GameCanvas.menu2.isShowMenu && GameCanvas.currentDialog == null && GameCanvas.subDialog == null && mainTabNew.typeTab != 0) {
            GameScreen.help.itemTabHelp(mGraphics2, null, mainTabNew.typeTab);
        }
    }

    public void update() {
        if (this.lastScreen == GameCanvas.game) {
            this.lastScreen.update();
        }
        MainTabNew mainTabNew = this.getCurrentTab();
        if (!GameCanvas.menu2.isShowMenu && GameCanvas.currentDialog == null && GameCanvas.currentScreen == this) {
            mainTabNew.update();
        }
        if (GameCanvas.menu2.isShowMenu || GameCanvas.currentDialog != null) {
            timeRepaint = 10;
        } else if (timeRepaint > 0) {
            --timeRepaint;
        }
    }

    private MainTabNew getCurrentTab() {
        return (MainTabNew)this.VecTabScreen.elementAt(this.selectTab);
    }

    public void updatekey() {
        if (GameCanvas.menu2.isShowMenu || GameCanvas.currentDialog != null || GameCanvas.subDialog != null) {
            return;
        }
        if (MainTabNew.Focus == 0) {
            timeRepaint = 10;
            this.left = null;
            this.center = null;
            this.right = cmdBack;
            int n = this.selectTab--;
            if (GameCanvas.keyMyHold[2]) {
                GameCanvas.clearKeyHold();
                if (TabRebuildItem.resetItemReplace) {
                    TabRebuildItem.itemFree = null;
                    TabRebuildItem.itemPlus = null;
                    TabRebuildItem.itemWing = null;
                    TabRebuildItem.resetItemReplace = false;
                }
            } else if (GameCanvas.keyMyHold[8]) {
                ++this.selectTab;
                GameCanvas.clearKeyHold();
                if (TabRebuildItem.resetItemReplace) {
                    TabRebuildItem.itemFree = null;
                    TabRebuildItem.itemPlus = null;
                    TabRebuildItem.itemWing = null;
                    TabRebuildItem.resetItemReplace = false;
                }
            } else if (GameCanvas.keyMyHold[4] || GameCanvas.keyMyHold[6]) {
                GameCanvas.clearKeyHold();
                GameCanvas.clearKeyPressed();
                this.SetInit();
            }
            this.selectTab = this.resetSelect(this.selectTab, this.VecTabScreen.size() - 1, true);
            if (n != this.selectTab) {
                MainScreen.cameraSub.yCam = 0;
            }
            MainTabNew mainTabNew = this.getCurrentTab();
            if (mainTabNew.typeTab == 7 || mainTabNew.typeTab == 11) {
                mainTabNew.init();
            }
            if (GameScreen.help.setStep_Next(3, 8)) {
                if (mainTabNew.typeTab == 1) {
                    mainTabNew.init();
                    GameScreen.help.NextStep();
                    GameScreen.help.setNext();
                }
            } else if (GameScreen.help.setStep_Next(7, 9)) {
                if (mainTabNew.typeTab == 3) {
                    mainTabNew.init();
                    GameScreen.help.NextStep();
                    GameScreen.help.setNext();
                }
            } else if (GameScreen.help.setStep_Next(9, 1) && mainTabNew.typeTab == 4) {
                mainTabNew.init();
                ++GameScreen.help.Next;
                GameScreen.help.setNext();
            }
            super.updatekey();
        } else {
            MainTabNew mainTabNew = this.getCurrentTab();
            if (!GameCanvas.menu2.isShowMenu && GameCanvas.currentDialog == null) {
                mainTabNew.updatekey();
            }
        }
    }

    public void updatePointer() {
        if (GameCanvas.menu2.isShowMenu || GameCanvas.currentDialog != null || GameCanvas.subDialog != null) {
            return;
        }
        MainTabNew mainTabNew = this.getCurrentTab();
        if (mainTabNew.typeTab == 10 && TabRebuildItem.isBeginEff != 0) {
            return;
        }
        if (GameCanvas.isPointSelect(MainTabNew.gI().xTab, MainTabNew.gI().yTab + GameCanvas.h / 5, MainTabNew.wOneItem + MainTabNew.wOne5 * 2, MainTabNew.wOneItem * this.VecTabScreen.size())) {
            int n = (GameCanvas.py - (MainTabNew.gI().yTab + GameCanvas.h / 5)) / MainTabNew.wOneItem;
            if ((n = this.resetSelect(n, this.VecTabScreen.size() - 1, false)) != this.selectTab) {
                if (TabRebuildItem.resetItemReplace) {
                    TabRebuildItem.itemFree = null;
                    TabRebuildItem.itemPlus = null;
                    TabRebuildItem.itemWing = null;
                    TabRebuildItem.resetItemReplace = false;
                }
                timeRepaint = 10;
                this.selectTab = n;
                this.SetInit();
                mainTabNew = this.getCurrentTab();
                if (GameScreen.help.setStep_Next(3, 8)) {
                    if (mainTabNew.typeTab == 1) {
                        GameScreen.help.NextStep();
                        GameScreen.help.setNext();
                    }
                } else if (GameScreen.help.setStep_Next(7, 9)) {
                    if (mainTabNew.typeTab == 3) {
                        GameScreen.help.NextStep();
                        GameScreen.help.setNext();
                    }
                } else if (GameScreen.help.setStep_Next(9, 1) && mainTabNew.typeTab == 4) {
                    ++GameScreen.help.Next;
                    GameScreen.help.setNext();
                }
                mSound.playSound(41, mSound.volumeSound);
            }
            GameCanvas.isPointerSelect = false;
        }
        mainTabNew.updatePointer();
        super.updatePointer();
    }

    public void SetInit() {
        MainTabNew.Focus = 1;
        MainTabNew mainTabNew = this.getCurrentTab();
        mainTabNew.init();
    }

    public void keyPress(int n) {
        MainTabNew mainTabNew = this.getCurrentTab();
        mainTabNew.keypress(n);
        super.keyPress(n);
    }

    public void setvecShop(mVector mVector2) {
    }

    static {
        timeRepaint = 0;
    }
}

