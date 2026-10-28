/*
 * Decompiled with CFR 0.152.
 */
package InterfaceComponents;

import CLib.TField;
import CLib.mFont;
import CLib.mGraphics;
import CLib.mVector;
import GameObjects.MainEvent;
import GameObjects.MainTextChat;
import GameScreen.EventScreen;
import GameScreen.GameScreen;
import GameScreen.PaintInfoGameScreen;
import GameScreen.TabScreenNew;
import InterfaceComponents.ChatDetail;
import InterfaceComponents.ListNew;
import InterfaceComponents.MainDialog;
import InterfaceComponents.MainTabNew;
import InterfaceComponents.Scroll;
import InterfaceComponents.iCommand;
import Main.GameCanvas;
import Model.AvMain;
import Model.T;
import Model.mCamera;
import netcommand.global.GlobalService;

public class MsgChat
extends MainDialog {
    int wMainTab;
    int maxTab;
    int min;
    int max;
    int xdich;
    byte maxText = (byte)5;
    static int idSelect = 0;
    int xselect;
    public static mVector vecChatTab = new mVector("MsgChat vecChatTab");
    static String[] strName;
    public static ChatDetail curentfocus;
    public static iCommand cmdChat;
    public static iCommand cmdDelete;
    public static iCommand cmdClose;
    public static iCommand cmdMenu;
    public static iCommand cmdOkAdd;
    public static iCommand cmdCancelAdd;
    boolean isLeft;
    boolean isRight;
    Scroll scroll = new Scroll();
    ListNew list;
    mCamera cam = new mCamera();
    int wOneItem = MainTabNew.wOneItem;
    int wOne5 = MainTabNew.wOne5;
    int minchat;
    int maxchat;
    int hchat;

    public MsgChat() {
        this.wDia = GameCanvas.w / 4 * 3;
        if (this.wDia < 160) {
            this.wDia = 160;
        } else if (this.wDia > 240) {
            this.wDia = 240;
        }
        this.hDia = GameCanvas.h - iCommand.hButtonCmd * 2 - 16;
        if (this.hDia > 240) {
            this.hDia = 240;
        }
        this.hchat = (this.hDia - 3 * this.wOneItem) / GameCanvas.hText + 5;
        this.xDia = GameCanvas.hw - this.wDia / 2;
        this.yDia = GameCanvas.hh - this.hDia / 2;
        this.cam.setAll(0, 0, 0, 0);
        this.wMainTab = this.wOneItem * 3;
        if (this.wMainTab > 70) {
            this.wMainTab = 70;
        }
        if (this.wOneItem < 20) {
            this.maxText = (byte)4;
        }
        this.maxTab = (this.wDia - this.wMainTab - 20) / (this.wOneItem - 1) + 1;
        this.min = idSelect - this.maxTab / 2;
        if (this.min < 0) {
            this.min = 0;
        }
        this.max = this.min + this.maxTab;
        if (this.max > vecChatTab.size()) {
            this.max = vecChatTab.size();
        }
        this.xdich = (this.wDia - (this.maxTab - 1) * (this.wOneItem - 1) - this.wMainTab) / 2;
        cmdChat = new iCommand(T.chat, 0, this);
        cmdDelete = new iCommand(T.delTabChat, 1, this);
        cmdClose = new iCommand(T.trora, -1, this);
        cmdMenu = new iCommand(T.menu, 2, this);
        cmdOkAdd = new iCommand(T.chapnhan, 3, this);
        cmdCancelAdd = new iCommand(T.tuchoi, 4, this);
        this.list = new ListNew(this.xDia, this.yDia + this.wOneItem, this.wDia, this.hDia - 3 * this.wOneItem, 0, 0, 0);
        this.scroll.setInfo(this.xDia + this.wDia - 6, this.yDia + this.wOneItem + 10, this.hDia - this.wOneItem - TField.getHeight() - 25, MainTabNew.color[9]);
        this.init();
    }

    public void setCaptionCmd() {
        MsgChat.cmdChat.caption = T.chat;
        MsgChat.cmdDelete.caption = T.delTabChat;
        MsgChat.cmdClose.caption = T.trora;
        MsgChat.cmdMenu.caption = T.menu;
        MsgChat.cmdOkAdd.caption = T.chapnhan;
        MsgChat.cmdCancelAdd.caption = T.tuchoi;
    }

    public void init() {
        if (vecChatTab.size() != 0) {
            if (curentfocus == null && vecChatTab.size() > 0) {
                if (idSelect < 0 || idSelect >= vecChatTab.size()) {
                    idSelect = 0;
                }
                curentfocus = (ChatDetail)vecChatTab.elementAt(idSelect);
                this.updateSelect();
            }
            if (curentfocus != null) {
                this.updateSelect();
            }
            if (GameCanvas.isTouch) {
                cmdMenu.setPos(this.xDia + this.wDia - 12, this.yDia - 14, PaintInfoGameScreen.fraCloseMenu, "");
            }
            this.left = cmdMenu;
        }
    }

    public void backTab() {
        idSelect = 0;
        this.cam.setAll(0, 0, 0, 0);
        this.list = new ListNew(this.xDia, this.yDia + this.wOneItem, this.wMainTab, this.hDia - 3 * this.wOneItem, 0, 0, 0);
    }

    public void commandPointer(int n, int n2) {
        switch (n) {
            case -1: {
                GameCanvas.end_Dialog();
                break;
            }
            case 0: {
                curentfocus.addStartChat(GameScreen.player.name);
                break;
            }
            case 1: {
                if (idSelect < 0 || idSelect >= vecChatTab.size()) {
                    return;
                }
                ChatDetail chatDetail = (ChatDetail)vecChatTab.elementAt(idSelect);
                if (chatDetail == null) break;
                MainEvent mainEvent = EventScreen.setEvent(chatDetail.name, (byte)0);
                if (mainEvent != null) {
                    mainEvent.isRemove = true;
                }
                vecChatTab.removeElementAt(idSelect);
                if (idSelect > 0) {
                    --idSelect;
                }
                if (vecChatTab.size() == 0) {
                    curentfocus = null;
                    this.left = null;
                    this.center = null;
                    this.right = cmdClose;
                    break;
                }
                this.updateSelect();
                break;
            }
            case 2: {
                if (GameCanvas.isTouch && vecChatTab.size() == 1) {
                    GameCanvas.end_Dialog();
                    return;
                }
                mVector mVector2 = new mVector("MsgChat menu");
                if (idSelect > 0) {
                    mVector2.addElement(cmdDelete);
                }
                mVector2.addElement(cmdClose);
                GameCanvas.menu2.startAt(mVector2, 2, T.trochuyen, false, null);
                break;
            }
            case 3: {
                GlobalService.gI().Friend((byte)1, MsgChat.curentfocus.friend);
                cmdDelete.perform();
                break;
            }
            case 4: {
                GlobalService.gI().Friend((byte)2, MsgChat.curentfocus.friend);
                cmdDelete.perform();
                break;
            }
        }
        super.commandPointer(n, n2);
    }

    public void paint(mGraphics mGraphics2) {
        GameCanvas.resetTrans(mGraphics2);
        AvMain.paintDialogNew(mGraphics2, this.xDia - 10, this.yDia - GameCanvas.hCommand - 6, this.wDia + 20, this.hDia + GameCanvas.hCommand + 12, 0);
        this.paintBack(mGraphics2);
        AvMain.FontBorderColor(mGraphics2, T.trochuyen, this.xDia + this.wDia / 2, this.yDia - GameCanvas.hCommand + GameCanvas.hCommand / 4, 2, 0);
        int n = this.xDia + this.xdich;
        int n2 = this.yDia + this.wOne5;
        if (vecChatTab.size() > 0 && curentfocus != null) {
            Object object;
            int n3;
            if (this.min > 0) {
                mGraphics2.drawRegion(MainTabNew.imgTab[13], 0, this.isLeft && GameCanvas.gameTick % 6 < 3 ? 16 : 0, 13, 8, 6, n - 6, n2 + this.wOne5 * 2, 3, true);
            }
            mGraphics2.setColor(MainTabNew.color[7]);
            for (n3 = this.min; n3 < idSelect; ++n3) {
                object = (ChatDetail)vecChatTab.elementAt(n3);
                this.paintRectNew(mGraphics2, n, n2, this.wOneItem, this.wOneItem, ((ChatDetail)object).isNew, ((ChatDetail)object).timeNew);
                mFont.tahoma_7_black.drawString(mGraphics2, strName[n3], n + this.wOneItem / 2, n2 + this.wOneItem / 2 - 7, 2, true);
                n += this.wOneItem - 1;
            }
            this.xselect = n;
            n += this.wMainTab - 1;
            for (n3 = idSelect + 1; n3 < this.max; ++n3) {
                object = (ChatDetail)vecChatTab.elementAt(n3);
                this.paintRectNew(mGraphics2, n, n2, this.wOneItem, this.wOneItem, ((ChatDetail)object).isNew, ((ChatDetail)object).timeNew);
                mFont.tahoma_7_black.drawString(mGraphics2, strName[n3], n + this.wOneItem / 2, n2 + this.wOneItem / 2 - 7, 2, true);
                n += this.wOneItem - 1;
            }
            if (this.max < vecChatTab.size()) {
                mGraphics2.drawRegion(MainTabNew.imgTab[13], 0, this.isRight && GameCanvas.gameTick % 6 < 3 ? 24 : 8, 13, 8, 6, n + 7, n2 + this.wOne5 * 2, 3, true);
            }
            this.paintTabFocus(mGraphics2, this.xselect, n2 - 1, this.wMainTab);
            String string = "";
            string = mFont.tahoma_7b_white.getWidth(MsgChat.curentfocus.name) < this.wMainTab - 4 ? MsgChat.curentfocus.name : MsgChat.curentfocus.name.substring(0, (this.maxText - 1) * 2) + "..";
            object = mFont.tahoma_7b_white;
            ((mFont)object).drawString(mGraphics2, string, this.xselect + this.wMainTab / 2, n2 + this.wOneItem / 2 - 7, 2, true);
            mGraphics2.setClip(this.xDia, n2 += this.wOneItem, this.wDia, this.hDia - this.wOneItem - 7 - (MsgChat.curentfocus.typeChat == 0 ? 17 : 0));
            mGraphics2.saveCanvas();
            mGraphics2.ClipRec(this.xDia, n2, this.wDia, this.hDia - this.wOneItem - 7 - (MsgChat.curentfocus.typeChat == 0 ? 17 : 0));
            mGraphics2.translate(0, -this.cam.yCam);
            this.minchat = this.cam.yCam / GameCanvas.hText - 2;
            if (this.minchat < 0) {
                this.minchat = 0;
            }
            this.maxchat = this.minchat + this.hchat;
            for (int i = this.minchat; i <= this.maxchat; ++i) {
                if (i >= MsgChat.curentfocus.vecDetail.size() || i < 0) continue;
                MainTextChat mainTextChat = (MainTextChat)MsgChat.curentfocus.vecDetail.elementAt(i);
                MainTabNew.setTextColor(mainTextChat.color).drawString(mGraphics2, mainTextChat.text, this.xDia + this.wOne5, n2 + 2 + i * GameCanvas.hText, 0, true);
            }
            mGraphics.resetTransAndroid(mGraphics2);
            mGraphics2.restoreCanvas();
            mGraphics2.endClip();
            GameCanvas.resetTrans(mGraphics2);
            if (this.cam.yLimit > 0) {
                this.scroll.paint(mGraphics2);
            }
            if (MsgChat.curentfocus.typeChat == 0) {
                MsgChat.curentfocus.tfchat.paint(mGraphics2);
            }
        } else {
            mFont.tahoma_7_white.drawString(mGraphics2, T.noMessage, this.xDia + this.wDia / 2, (n2 += this.wOneItem) + 2, 2, true);
        }
        super.paint(mGraphics2);
    }

    public void updatekey() {
        if (vecChatTab.size() > 0 && curentfocus != null) {
            int n = idSelect++;
            if (GameCanvas.keyMyHold[4]) {
                if (idSelect != 0) {
                    --idSelect;
                }
                GameCanvas.clearKeyHold(4);
            } else if (GameCanvas.keyMyHold[6]) {
                GameCanvas.clearKeyHold(6);
            }
            idSelect = this.resetSelect(idSelect, vecChatTab.size() - 1, true);
            if (GameCanvas.keyMyHold[2]) {
                this.cam.yTo -= GameCanvas.hText;
                if (this.cam.yTo < 0) {
                    this.cam.yTo = 0;
                }
                GameCanvas.clearKeyHold(2);
            } else if (GameCanvas.keyMyHold[8]) {
                this.cam.yTo += GameCanvas.hText;
                if (this.cam.yTo > this.cam.yLimit) {
                    this.cam.yTo = this.cam.yLimit;
                }
                GameCanvas.clearKeyHold(8);
            }
            if (n != idSelect) {
                this.updateSelect();
            }
        }
        super.updatekey();
    }

    public void update() {
        if (GameCanvas.isTouch) {
            this.list.update_Pos_UP_DOWN();
            this.cam.yCam = this.list.cmx;
        }
        this.isLeft = false;
        this.isRight = false;
        if (vecChatTab.size() > 0 && curentfocus != null) {
            for (int i = 0; i < vecChatTab.size(); ++i) {
                if (i >= this.min && i < this.max) continue;
                ChatDetail chatDetail = (ChatDetail)vecChatTab.elementAt(i);
                if (!chatDetail.isNew) continue;
                if (i < this.min) {
                    this.isLeft = true;
                    continue;
                }
                this.isRight = true;
            }
            if (vecChatTab.size() == 0) {
                idSelect = 0;
            }
            if (this.cam.yLimit > 0) {
                this.scroll.setYScrool(this.cam.yCam, this.cam.yLimit);
            }
            if (GameCanvas.isTouch) {
                this.list.moveCamera();
            } else {
                this.cam.UpdateCamera();
                this.cam.UpdateCamera();
            }
            if (MsgChat.curentfocus.typeChat == 0) {
                MsgChat.curentfocus.tfchat.update();
            }
        } else if (curentfocus == null && vecChatTab.size() > 0) {
            idSelect = 0;
            curentfocus = (ChatDetail)vecChatTab.elementAt(idSelect);
            this.updateSelect();
        }
        this.updatekey();
        this.updatePointer();
        super.update();
    }

    public void keypress(int n) {
        if (vecChatTab.size() > 0 && curentfocus != null && MsgChat.curentfocus.typeChat == 0) {
            MsgChat.curentfocus.tfchat.keyPressed(n);
        }
        super.keypress(n);
    }

    public static void getStrName() {
        strName = new String[vecChatTab.size()];
        for (int i = 0; i < strName.length; ++i) {
            ChatDetail chatDetail = (ChatDetail)vecChatTab.elementAt(i);
            MsgChat.strName[i] = chatDetail.name.length() <= 4 ? chatDetail.name : chatDetail.name.substring(0, 4);
        }
    }

    public void updateCameraNew(int n) {
        if (GameCanvas.isTouch) {
            this.list.cmxLim = MsgChat.curentfocus.limY;
            if (this.list.cmx + this.hDia / 4 > this.list.cmxLim && this.list.cmxLim > 0) {
                this.list.cmtoX += GameCanvas.hText * n;
            }
        } else {
            this.cam.yLimit = MsgChat.curentfocus.limY;
            if (this.cam.yCam + this.hDia / 4 > this.cam.yLimit) {
                this.cam.yTo += GameCanvas.hText * n;
            }
        }
    }

    public void updateSelect() {
        curentfocus = (ChatDetail)vecChatTab.elementAt(idSelect);
        if (this.left == null) {
            this.left = cmdMenu;
        }
        if (MsgChat.curentfocus.typeChat == 0) {
            MsgChat.curentfocus.tfchat.setText("");
            if (!GameCanvas.isTouch) {
                MsgChat.curentfocus.tfchat.setFocus(true);
                this.center = cmdChat;
                this.right = MsgChat.curentfocus.tfchat.setCmdClear();
            }
        } else if (MsgChat.curentfocus.typeChat == 2) {
            this.center = cmdOkAdd;
            this.right = cmdCancelAdd;
        } else {
            this.center = null;
            this.right = null;
        }
        if (MsgChat.curentfocus.timeNew > 0) {
            MsgChat.curentfocus.timeNew = (byte)-1;
            MsgChat.curentfocus.isNew = false;
        }
        if (MsgChat.curentfocus.limY > 0) {
            int n = MsgChat.curentfocus.limY - this.hDia / 4;
            if (n < 0) {
                n = 0;
            }
            if (GameCanvas.isTouch) {
                this.list.cmxLim = MsgChat.curentfocus.limY;
                this.list.cmtoX = MsgChat.curentfocus.limY;
            } else {
                this.cam.setAll(0, MsgChat.curentfocus.limY, 0, n);
                this.cam.yTo = MsgChat.curentfocus.limY;
            }
        } else if (!GameCanvas.isTouch) {
            this.cam.setAll(0, 0, 0, 0);
        } else {
            this.list.cmxLim = 0;
            this.list.cmtoX = 0;
        }
        this.min = idSelect - this.maxTab / 2;
        if (this.min < 0) {
            this.min = 0;
        }
        this.max = this.min + this.maxTab;
        if (this.max > vecChatTab.size()) {
            this.max = vecChatTab.size();
            this.min = this.max - this.maxTab;
            if (this.min < 0) {
                this.min = 0;
            }
        }
        MsgChat.getStrName();
        TabScreenNew.timeRepaint = 10;
    }

    public void updatePointer() {
        if (vecChatTab.size() > 0 && curentfocus != null) {
            if (MsgChat.curentfocus.typeChat == 0) {
                MsgChat.curentfocus.tfchat.updatePoiter();
            }
            if (GameCanvas.isPointerSelect) {
                int n = this.xDia;
                int n2 = this.yDia;
                int n3 = n + this.xdich;
                int n4 = n3 + (this.maxTab - 1) * (this.wOneItem - 1) + this.wMainTab;
                int n5 = idSelect;
                if (GameCanvas.isPoint(this.xDia, n2, this.wDia, this.wOneItem)) {
                    if (GameCanvas.px < this.xselect && GameCanvas.px > n3) {
                        n5 = idSelect + (GameCanvas.px - this.xselect) / (this.wOneItem - 1) - 1;
                        TabScreenNew.timeRepaint = 10;
                    } else if (GameCanvas.px > this.xselect + this.wMainTab && GameCanvas.px < n4) {
                        n5 = idSelect + (GameCanvas.px - this.xselect - this.wMainTab) / (this.wOneItem - 1) + 1;
                    } else if (GameCanvas.px < n3) {
                        n5 = idSelect - this.maxTab;
                    } else if (GameCanvas.px > n4) {
                        n5 = idSelect + this.maxTab;
                    }
                }
                if ((n5 = this.resetSelect(n5, vecChatTab.size() - 1, false)) != idSelect) {
                    idSelect = n5;
                    this.updateSelect();
                    GameCanvas.isPointerSelect = false;
                    TabScreenNew.timeRepaint = 10;
                }
            }
        }
        super.updatePointer();
    }

    public void addNewChat(String string, String string2, String string3, byte by, boolean bl) {
        String[] stringArray;
        for (int i = 0; i < vecChatTab.size(); ++i) {
            stringArray = (String[])vecChatTab.elementAt(i);
            if (stringArray.name.compareTo(string) != 0) continue;
            if (bl) {
                idSelect = i;
            }
            if (string3.length() > 0) {
                stringArray.addString(string2 + string3, string);
                String[] stringArray2 = mFont.tahoma_7_white.splitFontArray(string3, GameCanvas.mevent.wDia / 5 * 2);
                GameCanvas.mevent.addEvent(string, (byte)0, stringArray2[0], 0);
            }
            return;
        }
        ChatDetail chatDetail = new ChatDetail(string, by);
        if (by == 0) {
            chatDetail.addString(T.beginChat + string, string);
        }
        if (string3.length() > 0) {
            chatDetail.addString(string2 + string3, string);
            stringArray = mFont.tahoma_7_white.splitFontArray(string3, GameCanvas.mevent.wDia / 2);
            GameCanvas.mevent.addEvent(string, (byte)0, stringArray[0], 0);
        }
        vecChatTab.addElement(chatDetail);
        this.set_text_min_max();
        if (bl) {
            idSelect = vecChatTab.size() - 1;
        }
    }

    public void set_text_min_max() {
        MsgChat.getStrName();
        this.min = idSelect - this.maxTab / 2;
        if (this.min < 0) {
            this.min = 0;
        }
        this.max = this.min + this.maxTab;
        if (this.max > vecChatTab.size()) {
            this.max = vecChatTab.size();
            this.min = this.max - this.maxTab;
            if (this.min < 0) {
                this.min = 0;
            }
        }
    }

    public void paintRectNew(mGraphics mGraphics2, int n, int n2, int n3, int n4, boolean bl, int n5) {
        if (bl && (n5 + GameCanvas.gameTick) % 8 < 4) {
            TabScreenNew.timeRepaint = 10;
            if (GameCanvas.lowGraphic) {
                MainTabNew.paintRectLowGraphic(mGraphics2, n + 1, n2 + 1, n3 - 2, n4 - this.wOne5 - 1, 3);
            } else {
                mGraphics2.drawRegion(MainTabNew.imgTab[10], 0, 0, n3 - 2, n4 - this.wOne5 - 1, 0, n + 1, n2 + 1, 0, true);
            }
        }
        mGraphics2.fillRect(n, n2 + 1, 1, n4 - 1, true);
        mGraphics2.fillRect(n + 1, n2, n3 - 2, 1, true);
        mGraphics2.fillRect(n + n3 - 1, n2 + 1, 1, n4 - 1, true);
    }

    public void paintTabFocus(mGraphics mGraphics2, int n, int n2, int n3) {
        if (GameCanvas.lowGraphic) {
            MainTabNew.paintRectLowGraphic(mGraphics2, n, n2, n3, 32, 2);
        } else {
            for (int i = 0; i <= n3 / 32; ++i) {
                if (i == 0) {
                    mGraphics2.drawImage(MainTabNew.imgTab[9], n, n2, 0, true);
                    continue;
                }
                if (i == n3 / 32) {
                    mGraphics2.drawRegion(MainTabNew.imgTab[9], 0, 0, 32, 32, 2, n + n3 - 32, n2, 0, true);
                    continue;
                }
                mGraphics2.drawImage(MainTabNew.imgTab[2], n + i * 32, n2, 0, true);
            }
        }
    }

    public void paintBack(mGraphics mGraphics2) {
        if (GameCanvas.lowGraphic) {
            MainTabNew.paintRectLowGraphic(mGraphics2, this.xDia, this.yDia + this.wOneItem, this.wDia, this.hDia - this.wOneItem, 2);
        } else {
            int n = this.hDia - this.wOneItem;
            int n2 = this.wDia / 32;
            int n3 = n / 32;
            for (int i = 0; i <= n2; ++i) {
                for (int j = 0; j <= n3; ++j) {
                    if (i == n2) {
                        if (j == n3) {
                            mGraphics2.drawImage(MainTabNew.imgTab[2], this.xDia + this.wDia - 32, this.yDia + this.hDia - 32, 0, true);
                            continue;
                        }
                        mGraphics2.drawImage(MainTabNew.imgTab[2], this.xDia + this.wDia - 32, this.yDia + this.wOneItem + j * 32, 0, true);
                        continue;
                    }
                    if (j == n3) {
                        mGraphics2.drawImage(MainTabNew.imgTab[2], this.xDia + i * 32, this.yDia + this.hDia - 32, 0, true);
                        continue;
                    }
                    mGraphics2.drawImage(MainTabNew.imgTab[2], this.xDia + i * 32, this.yDia + this.wOneItem + j * 32, 0, true);
                }
            }
        }
    }

    public boolean setAddFriend(String string) {
        for (int i = 0; i < vecChatTab.size(); ++i) {
            ChatDetail chatDetail = (ChatDetail)vecChatTab.elementAt(i);
            if (chatDetail.typeChat != 2 || chatDetail.friend.compareTo(string) != 0) continue;
            return false;
        }
        return true;
    }

    public static void setFocus(int n) {
        if (GameCanvas.subDialog != GameCanvas.msgchat && vecChatTab != null && n >= 0 && n < vecChatTab.size()) {
            idSelect = n;
        }
    }

    public static void setIndexFocus(String string) {
        for (int i = 0; i < vecChatTab.size(); ++i) {
            ChatDetail chatDetail = (ChatDetail)vecChatTab.elementAt(i);
            if (chatDetail.name.compareTo(string) != 0) continue;
            idSelect = i;
            break;
        }
    }

    public void checkRemoveText() {
        for (int i = 0; i < vecChatTab.size(); ++i) {
            ChatDetail chatDetail = (ChatDetail)vecChatTab.elementAt(i);
            int n = chatDetail.vecDetail.size();
            if (n <= 80) continue;
            for (int j = 0; j < n - 80; ++j) {
                chatDetail.vecDetail.removeElementAt(i);
            }
            chatDetail.setLim();
        }
    }
}

