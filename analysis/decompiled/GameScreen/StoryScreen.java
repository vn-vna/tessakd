/*
 * Decompiled with CFR 0.152.
 */
package GameScreen;

import Build_Indo.IndoServer;
import CLib.mFont;
import CLib.mGraphics;
import GameObjects.MainQuest;
import GameScreen.GameScreen;
import GameScreen.MainScreen;
import InterfaceComponents.RunWord;
import InterfaceComponents.iCommand;
import Main.GameCanvas;
import Model.AvMain;
import Model.T;

public class StoryScreen
extends MainScreen {
    String[] strcontent;
    int maxH;
    int ypaint;
    int indexType = 0;
    int speed;
    RunWord run = new RunWord();
    public static int[] mTypeStory = null;
    private iCommand cmdNext;

    public static void setTypeStory() {
        mTypeStory = !GameCanvas.isVNLanguage && !IndoServer.isIndoSv ? new int[]{0, 0, 0} : new int[]{0, 0};
    }

    public void setCmdnext() {
        if (this.cmdNext == null) {
            this.cmdNext = new iCommand(T.next, -1, this);
            this.cmdNext.caption = T.next;
            this.cmdNext.setPos(GameCanvas.hw, GameCanvas.h - iCommand.hButtonCmd, null, this.cmdNext.caption);
            this.center = this.cmdNext;
        }
    }

    public void setContent() {
        this.setCmdnext();
        if (this.indexType >= mTypeStory.length) {
            GameCanvas.game.Show();
            GameCanvas.hLoad = GameCanvas.hh;
            this.indexType = 0;
            if (MainQuest.vecQuestList != null) {
                for (int i = 0; i < MainQuest.vecQuestList.size(); ++i) {
                    MainQuest mainQuest = (MainQuest)MainQuest.vecQuestList.elementAt(i);
                    mainQuest.beginQuest();
                    GameScreen.player.Direction = 1;
                }
            }
            return;
        }
        int n = 220;
        if (n > GameCanvas.w) {
            n = GameCanvas.w - 10;
        }
        if (mTypeStory[this.indexType] == 0) {
            this.strcontent = mFont.tahoma_7b_white.splitFontArray(T.story[this.indexType], n);
            this.maxH = 180 + this.strcontent.length * GameCanvas.hText;
            this.speed = 1;
            if (this.strcontent.length < 4) {
                this.speed = 2;
            }
        } else {
            this.run.startDialogChain(T.story[this.indexType], 0, GameCanvas.hw - n / 2, GameCanvas.hh, 220, mFont.tahoma_7b_white);
        }
        ++this.indexType;
    }

    public void paint(mGraphics mGraphics2) {
        mGraphics2.setColor(0);
        mGraphics2.fillRect(0, 0, GameCanvas.w, GameCanvas.h, false);
        mGraphics2.setClip(0, GameCanvas.hh - 90, GameCanvas.w, 180);
        if (this.strcontent != null) {
            for (int i = 0; i < this.strcontent.length; ++i) {
                mFont.tahoma_7b_white.drawString(mGraphics2, this.strcontent[i], GameCanvas.hw, GameCanvas.hh + 80 + i * GameCanvas.hText - this.ypaint, 2, true);
            }
        } else if (this.run.dlgChainInfo != null) {
            this.run.paintText(mGraphics2);
            GameCanvas.resetTrans(mGraphics2);
            AvMain.Font3dWhite(mGraphics2, T.next, GameCanvas.hw, GameCanvas.h - GameCanvas.hCommand / 2 - 4, 2);
        }
        mGraphics2.endClip();
        super.paint(mGraphics2);
    }

    public void update() {
        if (this.strcontent != null) {
            this.ypaint += this.speed;
            if (this.ypaint > this.maxH) {
                this.strcontent = null;
                this.setContent();
                this.ypaint = 0;
            }
        } else if (this.run.dlgChainInfo != null) {
            this.run.updateDlg();
        }
    }

    public void commandPointer(int n, int n2) {
        switch (n) {
            case -1: {
                this.indexType = mTypeStory.length;
                this.setContent();
            }
        }
    }

    public void updatekey() {
        if (GameCanvas.keyMyPressed[5]) {
            this.indexType = mTypeStory.length;
            this.setContent();
            GameCanvas.clearKeyPressed(5);
        }
        if (this.run.dlgChainInfo != null) {
            if (GameCanvas.keyMyHold[5] && this.run.nextDlgStep()) {
                this.run.dlgChainInfo = null;
                this.setContent();
                GameCanvas.clearKeyHold(5);
            }
            super.updatekey();
        }
    }
}

