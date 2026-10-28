/*
 * Decompiled with CFR 0.152.
 */
package InterfaceComponents;

import CLib.mFont;
import CLib.mGraphics;
import CLib.mImage;
import Main.GameCanvas;
import Model.AvMain;

public class PopupChat
extends AvMain {
    int x = GameCanvas.hw;
    int y = GameCanvas.hh;
    int dy;
    public int h;
    public int w;
    public int timeOff;
    public int indexpaint = 0;
    public String[] strChat;
    public String name;
    public static mImage[] mPopup = new mImage[2];
    boolean isStop = true;
    int[] color = new int[]{-13427660, -16788};

    public void setChat(String string, boolean bl) {
        this.isStop = bl;
        string = string.trim();
        this.w = mFont.tahoma_7_black.getWidth(string);
        if (this.w > 100) {
            this.w = 100;
        } else if (this.w < 20) {
            this.w = 20;
        }
        this.dy = 8;
        this.name = string;
        this.strChat = mFont.tahoma_7_black.splitFontArray(string, this.w);
        this.h = this.strChat.length * GameCanvas.hText;
        this.timeOff = this.strChat.length <= 2 ? 80 : 140;
    }

    public void paint(mGraphics mGraphics2) {
        if (this.dy > 0) {
            this.dy -= 2;
        }
        this.paintPopup(mGraphics2);
    }

    public void updatePos(int n, int n2) {
        this.x = n;
        this.y = n2;
    }

    public boolean setOff() {
        --this.timeOff;
        return this.timeOff <= 0 && this.isStop;
    }

    public void paintPopup(mGraphics mGraphics2) {
        try {
            int n = this.y - this.h + this.dy;
            int n2 = this.x - this.w / 2;
            mGraphics2.setColor(this.color[0]);
            mGraphics2.fillRect(n2 - 3, n, this.w + 6, this.h, false);
            mGraphics2.fillRect(n2, n - 3, this.w, this.h + 6, false);
            mGraphics2.setColor(this.color[1]);
            mGraphics2.fillRect(n2, n - 2, this.w, this.h + 4, false);
            mGraphics2.fillRect(n2 - 2, n, this.w + 4, this.h, false);
            mGraphics2.drawRegion(mPopup[0], 0, 0, 3, 3, 0, n2 - 3, n - 3, 0, false);
            mGraphics2.drawRegion(mPopup[0], 0, 3, 3, 3, 0, n2 + this.w, n - 3, 0, false);
            mGraphics2.drawRegion(mPopup[0], 0, 9, 3, 3, 0, n2 - 3, n + this.h, 0, false);
            mGraphics2.drawRegion(mPopup[0], 0, 6, 3, 3, 0, n2 + this.w, n + this.h, 0, false);
            if (this.indexpaint == 1) {
                mGraphics2.drawRegion(mPopup[1], 0, 0, 7, 7, 3, n2 + this.w / 2 - 3, n - 9, 0, false);
            } else {
                mGraphics2.drawImage(mPopup[1], n2 + this.w / 2 - 3, n + this.h + 2, 0, false);
            }
            if (this.strChat != null) {
                for (int i = 0; i < this.strChat.length; ++i) {
                    mFont.tahoma_7_black.drawString(mGraphics2, this.strChat[i], n2 + this.w / 2, n + 1 + i * GameCanvas.hText, 2, false);
                }
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}

