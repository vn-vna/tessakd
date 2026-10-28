/*
 * Decompiled with CFR 0.152.
 */
package InterfaceComponents;

import CLib.mFont;
import CLib.mGraphics;
import Main.GameCanvas;

public class RunWord {
    public boolean showDlg = false;
    public int nStepToShow;
    public int currentDlgStep;
    public int dlgActionType;
    public String[] dlgChainInfo;
    public int dlgFocusX;
    public int dlgFocusY;
    public int dlgRunX;
    public int dlgRunY;
    public int dlgW;
    public int dlgX;
    mFont f;

    public void startDialogChain(String string, int n, int n2, int n3, int n4, mFont mFont2) {
        this.f = mFont2;
        this.dlgActionType = n;
        this.dlgFocusX = n2;
        this.dlgFocusY = n3;
        this.currentDlgStep = 0;
        this.dlgW = n4;
        if (this.dlgW > GameCanvas.w - 10) {
            this.dlgW = GameCanvas.w - 10;
        }
        this.dlgChainInfo = mFont2.splitFontArray(string, this.dlgW);
        this.dlgX = this.dlgFocusX;
        this.showDlg = true;
        this.dlgRunX = 0;
        this.dlgRunY = 0;
    }

    public boolean checkDlgStep() {
        return this.dlgRunY >= this.dlgChainInfo.length;
    }

    public boolean nextDlgStep() {
        if (this.dlgRunY < this.dlgChainInfo.length) {
            this.dlgRunY = this.dlgChainInfo.length;
            this.dlgRunX = 0;
            return false;
        }
        this.dlgRunY = 0;
        this.dlgRunX = 0;
        return true;
    }

    public void updateDlg() {
        if (this.showDlg && this.dlgRunY < this.dlgChainInfo.length) {
            this.dlgRunX += 2;
            if (this.dlgRunX >= this.dlgChainInfo[this.dlgRunY].length()) {
                this.dlgRunX = 0;
                ++this.dlgRunY;
            }
        }
    }

    public void paintText(mGraphics mGraphics2) {
        int n = -1;
        n = this.dlgFocusY;
        for (int i = 0; i < this.dlgRunY; ++i) {
            this.f.drawString(mGraphics2, this.dlgChainInfo[i], this.dlgX, n + i * GameCanvas.hText, 0, false);
        }
        if (this.dlgRunY < this.dlgChainInfo.length) {
            this.f.drawString(mGraphics2, this.dlgChainInfo[this.dlgRunY].substring(0, this.dlgRunX), this.dlgX, n + this.dlgRunY * GameCanvas.hText, 0, false);
        }
    }

    public void paintText(mGraphics mGraphics2, int n) {
        int n2 = -1;
        n2 = this.dlgFocusY;
        if (n == 2) {
            for (int i = 0; i < this.dlgRunY; ++i) {
                this.f.drawString(mGraphics2, this.dlgChainInfo[i], this.dlgX + this.dlgW / 2, n2 + i * GameCanvas.hText, 2, false);
            }
            if (this.dlgRunY < this.dlgChainInfo.length) {
                this.f.drawString(mGraphics2, this.dlgChainInfo[this.dlgRunY].substring(0, this.dlgRunX), this.dlgX + this.dlgW / 2, n2 + this.dlgRunY * GameCanvas.hText, 2, false);
            }
        } else {
            for (int i = 0; i < this.dlgRunY; ++i) {
                this.f.drawString(mGraphics2, this.dlgChainInfo[i], this.dlgX, n2 + i * GameCanvas.hText, 0, false);
            }
            if (this.dlgRunY < this.dlgChainInfo.length) {
                this.f.drawString(mGraphics2, this.dlgChainInfo[this.dlgRunY].substring(0, this.dlgRunX), this.dlgX, n2 + this.dlgRunY * GameCanvas.hText, 0, false);
            }
        }
    }
}

