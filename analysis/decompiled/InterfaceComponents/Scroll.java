/*
 * Decompiled with CFR 0.152.
 */
package InterfaceComponents;

import CLib.mGraphics;
import InterfaceComponents.MainTabNew;
import InterfaceComponents.ScrollResult;
import Main.GameCanvas;

public class Scroll {
    int x;
    int y;
    int h;
    int color;
    int yScroll;
    int hScroll;
    public int cmtoX;
    public int cmtoY;
    public int cmx;
    public int cmy;
    public int cmvx;
    public int cmvy;
    public int cmdx;
    public int cmdy;
    public int xPos;
    public int yPos;
    public int width;
    public int height;
    public int cmxLim;
    public int cmyLim;
    public static Scroll me;
    private int pointerDownTime;
    private int pointerDownFirstX;
    public int[] pointerDownLastX = new int[3];
    public boolean pointerIsDowning;
    public boolean isDownWhenRunning;
    private int cmRun;
    public int selectedItem;
    public int ITEM_SIZE;
    public int nITEM;
    public int ITEM_PER_LINE;
    public boolean styleUPDOWN = true;
    public boolean FocusNew;

    public void clear() {
        this.cmtoX = 0;
        this.cmtoY = 0;
        this.cmx = 0;
        this.cmy = 0;
        this.cmvx = 0;
        this.cmvy = 0;
        this.cmdx = 0;
        this.cmdy = 0;
        this.cmxLim = 0;
        this.cmyLim = 0;
        this.width = 0;
        this.height = 0;
    }

    public void setClip(mGraphics mGraphics2, int n, int n2, int n3, int n4) {
        mGraphics2.setClip(n, n2, n3, n4 - 1);
        mGraphics2.translate(-mGraphics2.getTranslateX(), -mGraphics2.getTranslateY());
        mGraphics2.translate(-this.cmx, -this.cmy);
    }

    public void setClip(mGraphics mGraphics2) {
        mGraphics2.setClip(this.xPos + 1, this.yPos + 1, this.width - 2, this.height - 2);
        mGraphics2.translate(-mGraphics2.getTranslateX(), -mGraphics2.getTranslateY());
        mGraphics2.translate(-this.cmx, -this.cmy);
    }

    public ScrollResult updateKey() {
        if (this.styleUPDOWN) {
            return this.updateKeyScrollUpDown();
        }
        return this.updateKeyScrollLeftRight();
    }

    private ScrollResult updateKeyScrollUpDown() {
        int n;
        int n2;
        int n3 = this.xPos;
        int n4 = this.yPos;
        int n5 = this.width;
        int n6 = this.height;
        if (GameCanvas.isPointerDown) {
            if (!this.pointerIsDowning && GameCanvas.isPointer(n3, n4, n5, n6)) {
                for (n2 = 0; n2 < this.pointerDownLastX.length; ++n2) {
                    this.pointerDownLastX[0] = GameCanvas.py;
                }
                this.pointerDownFirstX = GameCanvas.py;
                this.pointerIsDowning = true;
                this.selectedItem = -1;
                this.isDownWhenRunning = this.cmRun != 0;
                this.cmRun = 0;
            } else if (this.pointerIsDowning) {
                ++this.pointerDownTime;
                if (this.pointerDownTime > 5 && this.pointerDownFirstX == GameCanvas.py && !this.isDownWhenRunning) {
                    this.pointerDownFirstX = -1000;
                    if (this.ITEM_PER_LINE > 1) {
                        n2 = (this.cmtoY + GameCanvas.py - n4) / this.ITEM_SIZE;
                        n = (this.cmtoX + GameCanvas.px - n3) / this.ITEM_SIZE;
                        this.selectedItem = n2 * this.ITEM_PER_LINE + n;
                    } else {
                        this.selectedItem = (this.cmtoY + GameCanvas.py - n4) / this.ITEM_SIZE;
                    }
                }
                if ((n2 = GameCanvas.py - this.pointerDownLastX[0]) != 0 && this.selectedItem != -1) {
                    this.selectedItem = -1;
                }
                for (n = this.pointerDownLastX.length - 1; n > 0; --n) {
                    this.pointerDownLastX[n] = this.pointerDownLastX[n - 1];
                }
                this.pointerDownLastX[0] = GameCanvas.py;
                this.cmtoY -= n2;
                if (this.cmtoY < 0) {
                    this.cmtoY = 0;
                }
                if (this.cmtoY > this.cmyLim) {
                    this.cmtoY = this.cmyLim;
                }
                if (this.cmy < 0 || this.cmy > this.cmyLim) {
                    n2 /= 2;
                }
                this.cmy -= n2;
            }
        }
        n2 = 0;
        if (GameCanvas.isPointerRelease && this.pointerIsDowning) {
            n = GameCanvas.py - this.pointerDownLastX[0];
            GameCanvas.isPointerRelease = false;
            if (Math.abs(n) < 20 && Math.abs(GameCanvas.py - this.pointerDownFirstX) < 20 && !this.isDownWhenRunning) {
                this.cmRun = 0;
                this.cmtoY = this.cmy;
                this.pointerDownFirstX = -1000;
                if (this.ITEM_PER_LINE > 1) {
                    int n7 = (this.cmtoY + GameCanvas.py - n4) / this.ITEM_SIZE;
                    int n8 = (this.cmtoX + GameCanvas.px - n3) / this.ITEM_SIZE;
                    this.selectedItem = n7 * this.ITEM_PER_LINE + n8;
                } else {
                    this.selectedItem = (this.cmtoY + GameCanvas.py - n4) / this.ITEM_SIZE;
                }
                this.pointerDownTime = 0;
                n2 = 1;
            } else if (this.selectedItem != -1 && this.pointerDownTime > 5) {
                this.pointerDownTime = 0;
                n2 = 1;
            } else if (this.selectedItem == -1 && !this.isDownWhenRunning) {
                if (this.cmy < 0) {
                    this.cmtoY = 0;
                } else if (this.cmy > this.cmyLim) {
                    this.cmtoY = this.cmyLim;
                } else {
                    int n9 = GameCanvas.py - this.pointerDownLastX[0] + (this.pointerDownLastX[0] - this.pointerDownLastX[1]) + (this.pointerDownLastX[1] - this.pointerDownLastX[2]);
                    n9 = n9 > 10 ? 10 : (n9 < -10 ? -10 : 0);
                    this.cmRun = -n9 * 100;
                }
            }
            this.pointerIsDowning = false;
            this.pointerDownTime = 0;
            GameCanvas.isPointerRelease = false;
        }
        ScrollResult scrollResult = new ScrollResult();
        scrollResult.selected = this.selectedItem;
        scrollResult.isFinish = n2;
        scrollResult.isDowning = this.pointerIsDowning;
        return scrollResult;
    }

    private ScrollResult updateKeyScrollLeftRight() {
        int n;
        int n2;
        int n3 = this.xPos;
        int n4 = this.yPos;
        int n5 = this.width;
        int n6 = this.height;
        if (GameCanvas.isPointerDown) {
            if (!this.pointerIsDowning && GameCanvas.isPointer(n3, n4, n5, n6)) {
                for (n2 = 0; n2 < this.pointerDownLastX.length; ++n2) {
                    this.pointerDownLastX[0] = GameCanvas.px;
                }
                this.pointerDownFirstX = GameCanvas.px;
                this.pointerIsDowning = true;
                this.selectedItem = -1;
                this.isDownWhenRunning = this.cmRun != 0;
                this.cmRun = 0;
            } else if (this.pointerIsDowning) {
                ++this.pointerDownTime;
                if (this.pointerDownTime > 5 && this.pointerDownFirstX == GameCanvas.px && !this.isDownWhenRunning) {
                    this.pointerDownFirstX = -1000;
                    this.selectedItem = (this.cmtoX + GameCanvas.px - n3) / this.ITEM_SIZE;
                }
                if ((n2 = GameCanvas.px - this.pointerDownLastX[0]) != 0 && this.selectedItem != -1) {
                    this.selectedItem = -1;
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
        n2 = 0;
        if (GameCanvas.isPointerRelease && this.pointerIsDowning) {
            n = GameCanvas.px - this.pointerDownLastX[0];
            GameCanvas.isPointerRelease = false;
            if (Math.abs(n) < 20 && Math.abs(GameCanvas.px - this.pointerDownFirstX) < 20 && !this.isDownWhenRunning) {
                this.cmRun = 0;
                this.cmtoX = this.cmx;
                this.pointerDownFirstX = -1000;
                this.selectedItem = (this.cmtoX + GameCanvas.px - n3) / this.ITEM_SIZE;
                this.pointerDownTime = 0;
                n2 = 1;
            } else if (this.selectedItem != -1 && this.pointerDownTime > 5) {
                this.pointerDownTime = 0;
                n2 = 1;
            } else if (this.selectedItem == -1 && !this.isDownWhenRunning) {
                if (this.cmx < 0) {
                    this.cmtoX = 0;
                } else if (this.cmx > this.cmxLim) {
                    this.cmtoX = this.cmxLim;
                } else {
                    int n7 = GameCanvas.px - this.pointerDownLastX[0] + (this.pointerDownLastX[0] - this.pointerDownLastX[1]) + (this.pointerDownLastX[1] - this.pointerDownLastX[2]);
                    n7 = n7 > 10 ? 10 : (n7 < -10 ? -10 : 0);
                    this.cmRun = -n7 * 100;
                }
            }
            this.pointerIsDowning = false;
            this.pointerDownTime = 0;
            GameCanvas.isPointerRelease = false;
        }
        ScrollResult scrollResult = new ScrollResult();
        scrollResult.selected = this.selectedItem;
        scrollResult.isFinish = n2;
        scrollResult.isDowning = this.pointerIsDowning;
        return scrollResult;
    }

    public void updatecm() {
        int n = this.xPos;
        int n2 = this.yPos;
        int n3 = this.width;
        int n4 = this.height;
        if (GameCanvas.isPointer(n, n2, n3, n4) && GameCanvas.isTouch && !this.FocusNew) {
            this.FocusNew = true;
        }
        if (this.cmRun != 0 && !this.pointerIsDowning) {
            if (this.styleUPDOWN) {
                this.cmtoY += this.cmRun / 100;
                if (this.cmtoY < 0) {
                    this.cmtoY = 0;
                } else if (this.cmtoY > this.cmyLim) {
                    this.cmtoY = this.cmyLim;
                } else {
                    this.cmy = this.cmtoY;
                }
            } else {
                this.cmtoX += this.cmRun / 100;
                if (this.cmtoX < 0) {
                    this.cmtoX = 0;
                } else if (this.cmtoX > this.cmxLim) {
                    this.cmtoX = this.cmxLim;
                } else {
                    this.cmx = this.cmtoX;
                }
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
        if (this.cmy != this.cmtoY && !this.pointerIsDowning) {
            this.cmvy = this.cmtoY - this.cmy << 2;
            this.cmdy += this.cmvy;
            this.cmy += this.cmdy >> 4;
            this.cmdy &= 0xF;
        }
    }

    public void setStyle(int n, int n2, int n3, int n4, int n5, int n6, boolean bl, int n7) {
        this.xPos = n3;
        this.yPos = n4;
        this.ITEM_SIZE = n2;
        this.nITEM = n;
        this.width = n5;
        this.height = n6;
        this.styleUPDOWN = bl;
        this.ITEM_PER_LINE = n7;
        if (bl) {
            this.cmyLim = n * n2 - n6;
        } else {
            this.cmxLim = n * n2 - n5;
        }
        if (this.cmyLim < 0) {
            this.cmyLim = 0;
        }
        if (this.cmxLim < 0) {
            this.cmxLim = 0;
        }
    }

    public void moveTo(int n) {
        if (this.styleUPDOWN) {
            this.cmtoY = n -= (this.height - this.ITEM_SIZE * 2) / 2;
            if (this.cmtoY < 0) {
                this.cmtoY = 0;
            }
            if (this.cmtoY > this.cmyLim) {
                this.cmtoY = this.cmyLim;
            }
        } else {
            this.cmtoX = n -= (this.width - this.ITEM_SIZE) / 2;
            if (this.cmtoX < 0) {
                this.cmtoX = 0;
            }
            if (this.cmtoX > this.cmxLim) {
                this.cmtoX = this.cmxLim;
            }
        }
    }

    public void moveCmrTo(int n) {
        if (n > 0) {
            this.cmtoY += this.ITEM_SIZE;
        } else if (n < 0) {
            this.cmtoY -= 20;
        }
        if (this.cmtoY < 0) {
            this.cmtoY = 0;
        }
        if (this.cmtoY > this.cmyLim) {
            this.cmtoY = this.cmyLim;
        }
    }

    public static Scroll gI() {
        if (me == null) {
            me = new Scroll();
        }
        return me;
    }

    public void setInfo(int n, int n2, int n3, int n4) {
        this.x = n;
        this.y = n2;
        this.h = n3;
        this.color = n4;
        this.hScroll = n3 - MainTabNew.wOneItem;
    }

    public void paint(mGraphics mGraphics2) {
        mGraphics2.setColor(this.color);
        mGraphics2.fillRect(this.x - 2, this.y - 1, 3, 1, true);
        mGraphics2.fillRect(this.x - 3, this.y, 1, this.h - 1, true);
        mGraphics2.fillRect(this.x + 1, this.y, 1, this.h - 1, true);
        mGraphics2.fillRect(this.x - 2, this.y + this.h - 1, 3, 1, true);
        mGraphics2.fillRect(this.x - 2, this.y + this.yScroll, 3, MainTabNew.wOneItem, true);
    }

    public void setYScrool(int n, int n2) {
        this.yScroll = n * this.hScroll / n2;
    }
}

