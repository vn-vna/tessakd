/*
 * Decompiled with CFR 0.152.
 */
package InterfaceComponents;

import CLib.TField;
import CLib.mFont;
import CLib.mVector;
import GameObjects.MainTextChat;
import GameScreen.GameScreen;
import InterfaceComponents.MainTabNew;
import InterfaceComponents.MsgChat;
import Main.GameCanvas;
import Model.CRes;
import Model.T;
import netcommand.global.GlobalService;

public class ChatDetail {
    public mVector vecDetail = new mVector("ChatDetail vecDetail");
    String name;
    String friend;
    byte timeNew = (byte)-1;
    boolean isNew = false;
    public TField tfchat;
    public byte typeChat = 0;
    public static final byte TYPE_CHAT = 0;
    public static final byte TYPE_SERVER = 1;
    public static final byte TYPE_ADDFRIEND = 2;
    int limY = 0;
    int indexColor = 0;
    byte typeColorChat = 0;
    public static final byte TYPE_TROCHUYEN = 0;
    public static final byte TYPE_BANGHOI_NHOM = 1;

    public ChatDetail(String string, byte by) {
        this.name = string;
        this.typeChat = by;
        if (this.typeChat == 0) {
            this.tfchat = new TField(GameCanvas.msgchat.xDia + 5, GameCanvas.msgchat.yDia + GameCanvas.msgchat.hDia - (TField.getHeight() + 5), GameCanvas.msgchat.wDia - 10);
            this.tfchat.isCloseKey = false;
        } else if (this.typeChat == 2) {
            this.friend = string;
            this.name = T.addFriend;
        }
        this.typeColorChat = string.compareTo(T.tabBangHoi) == 0 || string.compareTo(T.tinden) == 0 || string.compareTo(T.tabThuLinh) == 0 ? (byte)1 : 0;
    }

    public void addString(String string, String string2) {
        if (string.length() > 0) {
            String[] stringArray = mFont.tahoma_7_white.splitFontArray(string, GameCanvas.msgchat.wDia - GameCanvas.msgchat.wOne5 * 2 - 8);
            MainTextChat[] mainTextChatArray = this.addChatNew(stringArray, this.setColorText(string2));
            if (mainTextChatArray != null) {
                for (int i = 0; i < mainTextChatArray.length; ++i) {
                    this.vecDetail.addElement(mainTextChatArray[i]);
                }
            }
            this.setLim();
            if (this.limY > 0 && GameCanvas.subDialog != null && GameCanvas.subDialog == GameCanvas.msgchat && MsgChat.curentfocus != null && MsgChat.curentfocus == this) {
                GameCanvas.msgchat.updateCameraNew(stringArray.length);
            }
            if (MsgChat.curentfocus != null && MsgChat.curentfocus != this && this.name.compareTo(T.tinden) != 0) {
                this.isNew = true;
                this.timeNew = (byte)CRes.random(1, 11);
            }
        }
    }

    public void addStartChat(String string) {
        String string2 = "";
        if (this.tfchat != null) {
            string2 = this.tfchat.getText();
        }
        if (string2.length() > 0) {
            String[] stringArray = mFont.tahoma_7_white.splitFontArray(GameScreen.player.name + ": " + string2, GameCanvas.msgchat.wDia - GameCanvas.msgchat.wOne5 * 2 - 8);
            MainTextChat[] mainTextChatArray = this.addChatNew(stringArray, this.setColorText(string));
            if (mainTextChatArray != null) {
                for (int i = 0; i < mainTextChatArray.length; ++i) {
                    this.vecDetail.addElement(mainTextChatArray[i]);
                }
            }
            this.setLim();
            if (MsgChat.curentfocus != null && MsgChat.curentfocus == this) {
                GameCanvas.msgchat.updateCameraNew(stringArray.length);
            }
            GlobalService.gI().chatTab(this.name, string2);
        }
        if (this.tfchat != null) {
            this.tfchat.setText("");
        }
    }

    public void setLim() {
        this.limY = this.vecDetail.size() * GameCanvas.hText - (GameCanvas.msgchat.hDia - MainTabNew.wOneItem - 10 - (this.typeChat == 0 ? TField.getHeight() + 2 : 0));
        if (this.limY < 0) {
            this.limY = 0;
        }
    }

    public MainTextChat[] addChatNew(String[] stringArray, byte by) {
        if (stringArray == null || stringArray.length == 0) {
            return null;
        }
        MainTextChat[] mainTextChatArray = new MainTextChat[stringArray.length];
        for (int i = 0; i < stringArray.length; ++i) {
            mainTextChatArray[i] = new MainTextChat(stringArray[i], by);
        }
        return mainTextChatArray;
    }

    private byte setColorText(String string) {
        byte by = 0;
        if (this.typeColorChat == 1) {
            by = this.indexColor % 2 == 0 ? (byte)0 : 5;
            ++this.indexColor;
        } else {
            by = string.compareTo(GameScreen.player.name) == 0 ? (byte)5 : 0;
        }
        return by;
    }
}

