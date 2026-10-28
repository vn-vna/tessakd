/*
 * Decompiled with CFR 0.152.
 */
package InterfaceComponents;

import CLib.TField;
import CLib.mGraphics;
import CLib.mSystem;
import GameScreen.GameScreen;
import InterfaceComponents.iCommand;
import Main.GameCanvas;
import Model.AvMain;
import Model.T;
import netcommand.global.GlobalService;

public class ChatTextField
extends AvMain {
    public TField tfChat = new TField();
    public static ChatTextField instance;
    public static boolean isShow;

    public static ChatTextField gI() {
        return instance == null ? (instance = new ChatTextField()) : instance;
    }

    public void setChat() {
        boolean bl = isShow = !isShow;
        if (isShow) {
            this.tfChat.setPoiter();
        }
    }

    public void commandTab(int n, int n2) {
        switch (n) {
            case 0: {
                this.tfChat.setText("");
                isShow = false;
                if (GameCanvas.isTouch) break;
                this.tfChat.setFocus(true);
                break;
            }
            case 1: {
                this.sendChat();
            }
        }
    }

    protected ChatTextField() {
        this.tfChat.isChangeFocus = false;
        this.tfChat.setFocus(true);
        this.init();
        this.tfChat.x = (GameCanvas.w - this.tfChat.width) / 2;
        this.tfChat.setMaxTextLenght(70);
        this.tfChat.setStringNull(T.chat);
        if (!GameCanvas.isTouch) {
            this.left = new iCommand(T.close, 0);
            this.center = new iCommand(T.chat, 1);
            this.right = this.tfChat.setCmdClear();
        }
    }

    public void init() {
        this.tfChat.y = GameCanvas.h - iCommand.hButtonCmd - this.tfChat.height - 5;
        this.tfChat.width = GameCanvas.w - TField.xDu * 2 - 10;
    }

    public void keyPressed(int n) {
        this.tfChat.keyPressed(n);
    }

    public void updatekey() {
        this.tfChat.update();
        super.updatekey();
    }

    public void paint(mGraphics mGraphics2) {
        super.paint(mGraphics2);
        if (!mSystem.isIP_GDX) {
            this.tfChat.paint(mGraphics2);
        }
    }

    public void updatePointer() {
        this.tfChat.updatePoiter();
        super.updatePointer();
    }

    public void sendChat() {
        if (this.tfChat.getText().length() > 0) {
            GameScreen.player.strChatPopup = this.tfChat.getText();
            GlobalService.gI().chatPopup(this.tfChat.getText());
            this.tfChat.setText("");
        }
        if (GameCanvas.isTouch) {
            isShow = false;
        }
    }

    static {
        isShow = false;
    }
}

