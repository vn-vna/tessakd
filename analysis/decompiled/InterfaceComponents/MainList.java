/*
 * Decompiled with CFR 0.152.
 */
package InterfaceComponents;

import CLib.mSound;
import CLib.mVector;
import InterfaceComponents.iCommand;
import Main.GameCanvas;
import Model.CRes;

public class MainList {
    public int maxW;
    public int itemH;
    public int maxH;
    public int maxSize;
    public int x;
    public int y;
    public int value;
    public int cmtoX;
    public int cmx;
    public int cmdy;
    public int cmvy;
    public int cmxLim;
    public int xc;
    private int pointerDownTime;
    private int waitToPerform;
    private int pointerDownFirstX;
    private int[] pointerDownLastX = new int[3];
    private boolean pointerIsDowning;
    private boolean isDownWhenRunning;
    private int cmRun;
    mVector vecCmd;
    iCommand cmdCenter = null;
    public int w;
    int pa = 0;
    boolean trans = false;
    int cmvx;
    int cmdx;

    public MainList(int n, int n2, int n3, int n4, int n5, int n6, int n7, int n8, mVector mVector2) {
        this.x = n;
        this.y = n2;
        this.maxW = n3;
        this.maxH = n4;
        this.itemH = n5;
        this.maxSize = n6;
        this.value = n8;
        this.cmxLim = n7;
        this.vecCmd = mVector2;
    }

    public MainList(int n, int n2, int n3, int n4, int n5, int n6, int n7, int n8, iCommand iCommand2) {
        this.x = n;
        this.y = n2;
        this.maxW = n3;
        this.maxH = n4;
        this.itemH = n5;
        this.maxSize = n6;
        this.value = n8;
        this.cmxLim = n7;
        this.cmdCenter = iCommand2;
    }

    public void updateMenuKey() {
        boolean bl = false;
        if (GameCanvas.keyMyPressed[2] || GameCanvas.keyMyPressed[4]) {
            bl = true;
            --this.value;
            if (this.value < 0) {
                this.value = this.maxSize - 1;
            }
            GameCanvas.clearKeyPressed(2);
            GameCanvas.clearKeyPressed(4);
        } else if (GameCanvas.keyMyPressed[8] || GameCanvas.keyMyPressed[6]) {
            bl = true;
            ++this.value;
            if (this.value > this.maxSize - 1) {
                this.value = 0;
            }
            GameCanvas.clearKeyPressed(6);
            GameCanvas.clearKeyPressed(8);
        }
        if (bl) {
            this.cmtoX = (this.value + 1) * this.itemH - this.maxH / 2;
            if (this.cmtoX > this.cmxLim) {
                this.cmtoX = this.cmxLim;
            }
            if (this.cmtoX < 0) {
                this.cmtoX = 0;
            }
            if (this.value == this.maxSize - 1 || this.value == 0) {
                this.cmx = this.cmtoX;
            }
        }
        this.update_Pos_UP_DOWN();
    }

    private void update_Pos_UP_DOWN() {
        int n;
        iCommand iCommand2;
        if (GameCanvas.keyMyHold[5] && this.cmdCenter == null && this.vecCmd != null && (iCommand2 = (iCommand)this.vecCmd.elementAt(this.value)) != null) {
            if (iCommand2.action != null) {
                iCommand2.action.perform();
            } else if (iCommand2.Pointer != null) {
                iCommand2.Pointer.commandPointer((int)iCommand2.indexMenu, iCommand2.subIndex);
            } else {
                GameCanvas.currentScreen.commandMenu(iCommand2.indexMenu, iCommand2.subIndex);
            }
            GameCanvas.clearKeyHold();
            GameCanvas.clearKeyPressed();
        }
        if (GameCanvas.isPointerDown) {
            if (!this.pointerIsDowning && GameCanvas.isPointer(this.x, this.y, this.maxW, this.maxH)) {
                for (int i = 0; i < this.pointerDownLastX.length; ++i) {
                    this.pointerDownLastX[0] = GameCanvas.py;
                }
                this.pointerDownFirstX = GameCanvas.py;
                this.pointerIsDowning = true;
                this.isDownWhenRunning = this.cmRun != 0;
                this.cmRun = 0;
            } else if (this.pointerIsDowning) {
                int n2;
                ++this.pointerDownTime;
                if (this.pointerDownTime > 5 && this.pointerDownFirstX == GameCanvas.py && !this.isDownWhenRunning) {
                    this.pointerDownFirstX = -1000;
                    this.value = (this.cmtoX + GameCanvas.py - this.y) / this.itemH;
                }
                if ((n2 = GameCanvas.py - this.pointerDownLastX[0]) != 0 && this.value != -1) {
                    this.value = -1;
                }
                for (n = this.pointerDownLastX.length - 1; n > 0; --n) {
                    this.pointerDownLastX[n] = this.pointerDownLastX[n - 1];
                }
                this.pointerDownLastX[0] = GameCanvas.py;
                this.cmtoX -= n2;
                if (this.cmtoX < 0) {
                    this.cmtoX = 0;
                }
                if (this.cmtoX > this.cmxLim) {
                    this.cmtoX = this.cmxLim;
                }
                if (this.cmx < 0 || this.cmx > this.cmxLim) {
                    n2 /= 2;
                }
                this.cmx -= n2;
            }
        }
        if (GameCanvas.isPointerClick && this.pointerIsDowning) {
            int n3 = GameCanvas.py - this.pointerDownLastX[0];
            GameCanvas.isPointerClick = false;
            if (CRes.abs(n3) < 20 && CRes.abs(GameCanvas.py - this.pointerDownFirstX) < 20 && !this.isDownWhenRunning) {
                this.cmRun = 0;
                this.cmtoX = this.cmx;
                this.pointerDownFirstX = -1000;
                this.value = (this.cmtoX + GameCanvas.py - this.y) / this.itemH;
                this.pointerDownTime = 0;
                this.waitToPerform = 5;
            } else if (this.value != -1 && this.pointerDownTime > 5) {
                this.pointerDownTime = 0;
                this.waitToPerform = 1;
            } else if (this.value == -1 && !this.isDownWhenRunning) {
                if (this.cmx < 0) {
                    this.cmtoX = 0;
                } else if (this.cmx > this.cmxLim) {
                    this.cmtoX = this.cmxLim;
                } else {
                    n = GameCanvas.py - this.pointerDownLastX[0] + (this.pointerDownLastX[0] - this.pointerDownLastX[1]) + (this.pointerDownLastX[1] - this.pointerDownLastX[2]);
                    n = n > 10 ? 10 : (n < -10 ? -10 : 0);
                    this.cmRun = -n * 100;
                }
            }
            this.pointerIsDowning = false;
            this.pointerDownTime = 0;
            GameCanvas.isPointerClick = false;
        }
    }

    public void moveCamera() {
        if (this.cmRun != 0 && !this.pointerIsDowning) {
            this.cmtoX += this.cmRun / 100;
            if (this.cmtoX < 0) {
                this.cmtoX = 0;
            } else if (this.cmtoX > this.cmxLim) {
                this.cmtoX = this.cmxLim;
            } else {
                this.cmx = this.cmtoX;
            }
            this.cmRun = this.cmRun * 9 / 10;
            if (this.cmRun < 100 && this.cmRun > -100) {
                this.cmRun = 0;
            }
        }
        if (this.cmx != this.cmtoX && !this.pointerIsDowning) {
            this.cmvx = this.cmtoX - this.cmx << 2;
            this.cmdx += this.cmvx;
            this.cmx += this.cmdx >> 4;
            this.cmdx &= 0xF;
        }
    }

    public void updateMenu() {
        this.moveCamera();
        this.updateMenuKey();
        if (this.xc != 0) {
            this.xc >>= 1;
            if (this.xc < 0) {
                this.xc = 0;
            }
        }
        if (this.waitToPerform > 0) {
            --this.waitToPerform;
            if (this.waitToPerform == 0 && this.value >= 0) {
                iCommand iCommand2;
                if (this.cmdCenter != null) {
                    this.cmdCenter.perform();
                } else if (this.vecCmd != null && (iCommand2 = (iCommand)this.vecCmd.elementAt(this.value)) != null) {
                    if (iCommand2.action != null) {
                        iCommand2.action.perform();
                    } else if (iCommand2.Pointer != null) {
                        iCommand2.Pointer.commandPointer((int)iCommand2.indexMenu, iCommand2.subIndex);
                    } else {
                        GameCanvas.currentScreen.commandMenu(iCommand2.indexMenu, iCommand2.subIndex);
                    }
                    GameCanvas.clearKeyHold();
                    GameCanvas.clearKeyPressed();
                }
                GameCanvas.clearKeyHold();
                GameCanvas.clearKeyPressed();
                GameCanvas.isPointerEnd = true;
                GameCanvas.isPointerSelect = false;
                mSound.playSound(42, mSound.volumeSound);
            }
        }
    }
}

