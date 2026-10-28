/*
 * Decompiled with CFR 0.152.
 */
package InterfaceComponents;

import CLib.mVector;
import InterfaceComponents.iCommand;
import Main.GameCanvas;
import Model.CRes;

public class ListNew {
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
    private int pointerDownTime;
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

    public ListNew(int n, int n2, int n3, int n4, int n5, int n6, int n7) {
        this.x = n;
        this.y = n2;
        this.maxW = n3;
        this.maxH = n4;
        this.itemH = n5;
        this.maxSize = n6;
        this.cmxLim = n7;
    }

    public void updateMenuKey() {
        boolean bl = false;
        if (GameCanvas.keyMyPressed[2]) {
            bl = true;
            --this.value;
            if (this.value < 0) {
                this.value = this.maxSize - 1;
            }
            GameCanvas.clearKeyPressed(2);
            GameCanvas.clearKeyPressed(4);
        } else if (GameCanvas.keyMyPressed[8]) {
            bl = true;
            ++this.value;
            if (this.value > this.maxSize - 1) {
                this.value = this.maxSize - 1;
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

    public void update_Pos_UP_DOWN() {
        int n;
        int n2;
        if (GameCanvas.isPointerDown) {
            if (!this.pointerIsDowning && GameCanvas.isPointer(this.x, this.y, this.maxW, this.maxH)) {
                for (n2 = 0; n2 < this.pointerDownLastX.length; ++n2) {
                    this.pointerDownLastX[0] = GameCanvas.py;
                }
                this.pointerDownFirstX = GameCanvas.py;
                this.pointerIsDowning = true;
                this.isDownWhenRunning = this.cmRun != 0;
                this.cmRun = 0;
            } else if (this.pointerIsDowning) {
                ++this.pointerDownTime;
                if (this.pointerDownTime > 5 && this.pointerDownFirstX == GameCanvas.py && !this.isDownWhenRunning) {
                    this.pointerDownFirstX = -1000;
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
            n2 = GameCanvas.py - this.pointerDownLastX[0];
            GameCanvas.isPointerClick = false;
            if (CRes.abs(n2) < 20 && CRes.abs(GameCanvas.py - this.pointerDownFirstX) < 20 && !this.isDownWhenRunning && GameCanvas.isPointerSelect) {
                this.cmRun = 0;
                this.cmtoX = this.cmx;
                this.pointerDownFirstX = -1000;
                this.pointerDownTime = 0;
            } else if (this.value != -1 && this.pointerDownTime > 5) {
                this.pointerDownTime = 0;
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

    public void updatePos_LEFT_RIGHT() {
        int n;
        int n2;
        if (GameCanvas.isPointerDown) {
            if (!this.pointerIsDowning && GameCanvas.isPointer(this.x, this.y, this.maxW, this.maxH)) {
                for (n2 = 0; n2 < this.pointerDownLastX.length; ++n2) {
                    this.pointerDownLastX[0] = GameCanvas.px;
                }
                this.pointerDownFirstX = GameCanvas.px;
                this.pointerIsDowning = true;
                this.isDownWhenRunning = this.cmRun != 0;
                this.cmRun = 0;
            } else if (this.pointerIsDowning) {
                ++this.pointerDownTime;
                if (this.pointerDownTime > 5 && this.pointerDownFirstX == GameCanvas.px && !this.isDownWhenRunning) {
                    this.pointerDownFirstX = -1000;
                }
                if ((n2 = GameCanvas.px - this.pointerDownLastX[0]) != 0 && this.value != -1) {
                    this.value = -1;
                }
                for (n = this.pointerDownLastX.length - 1; n > 0; --n) {
                    this.pointerDownLastX[n] = this.pointerDownLastX[n - 1];
                }
                this.pointerDownLastX[0] = GameCanvas.px;
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
            n2 = GameCanvas.px - this.pointerDownLastX[0];
            GameCanvas.isPointerClick = false;
            if (CRes.abs(n2) < 20 && CRes.abs(GameCanvas.px - this.pointerDownFirstX) < 20 && !this.isDownWhenRunning && GameCanvas.isPointerSelect) {
                this.cmRun = 0;
                this.cmtoX = this.cmx;
                this.pointerDownFirstX = -1000;
                this.pointerDownTime = 0;
            } else if (this.value != -1 && this.pointerDownTime > 5) {
                this.pointerDownTime = 0;
            } else if (this.value == -1 && !this.isDownWhenRunning) {
                if (this.cmx < 0) {
                    this.cmtoX = 0;
                } else if (this.cmx > this.cmxLim) {
                    this.cmtoX = this.cmxLim;
                } else {
                    n = GameCanvas.px - this.pointerDownLastX[0] + (this.pointerDownLastX[0] - this.pointerDownLastX[1]) + (this.pointerDownLastX[1] - this.pointerDownLastX[2]);
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
    }
}

