/*
 * Decompiled with CFR 0.152.
 */
package GameObjects;

import CLib.mGraphics;
import CLib.mImage;
import CLib.mSound;
import CLib.mVector;
import GameObjects.MainObject;
import GameObjects.MainQuest;
import GameObjects.ObjectData;
import GameScreen.GameScreen;
import GameScreen.PaintInfoGameScreen;
import InterfaceComponents.iCommand;
import Main.GameCanvas;
import Model.AvMain;
import Model.CRes;
import Model.MainImage;
import Model.T;
import Thread_More.LoadMap;
import netcommand.global.GlobalService;

public class NPC
extends MainObject {
    public short idImage;
    public short wBlock;
    public short hBlock;
    int nFrame;
    int wAvatar;
    int hAvatar;
    int indexSound = -1;
    int timePlaySound = -1;

    public void commandPointer(int n, int n2) {
        switch (n) {
            case 0: {
                if (n2 < 0 || n2 > MainQuest.vecQuestList.size()) break;
                MainQuest mainQuest = (MainQuest)MainQuest.vecQuestList.elementAt(n2);
                mainQuest.beginQuest();
                break;
            }
            case 1: {
                if (n2 < 0 || n2 > MainQuest.vecQuestFinish.size()) break;
                MainQuest mainQuest = (MainQuest)MainQuest.vecQuestFinish.elementAt(n2);
                mainQuest.beginQuest();
                break;
            }
            case 2: {
                if (n2 < 0 || n2 > MainQuest.vecQuestDoing_Main.size()) break;
                MainQuest mainQuest = (MainQuest)MainQuest.vecQuestDoing_Main.elementAt(n2);
                mainQuest.show_Info_Quest_Doing();
                break;
            }
            case 3: {
                if (n2 < 0 || n2 > MainQuest.vecQuestDoing_Sub.size()) break;
                MainQuest mainQuest = (MainQuest)MainQuest.vecQuestDoing_Sub.elementAt(n2);
                mainQuest.show_Info_Quest_Doing();
                break;
            }
            case 4: {
                GlobalService.gI().getlist_from_npc((byte)this.ID);
                break;
            }
            case 5: {
                this.NhiemVu();
            }
        }
        super.commandPointer(n, n2);
    }

    public NPC(String string, String string2, byte by, byte by2, short s, short s2, byte by3, byte by4, byte by5) {
        this.name = string;
        this.nameGiaotiep = string2;
        this.ID = by;
        this.idImage = by2;
        this.x = s + 12;
        this.y = s2;
        this.wBlock = by3;
        this.hBlock = by4;
        this.nFrame = by5;
        this.hp = 100;
        this.maxHp = 100;
        this.ysai = 0;
        GameCanvas.loadmap.setBlockNPC(s, s2 - 24, by3, by4);
        this.setPlaySound();
    }

    public void paint(mGraphics mGraphics2) {
        if (this.idImage != -1) {
            MainImage mainImage = ObjectData.getImagePartNPC(this.idImage);
            if (mainImage.img != null) {
                if (this.wOne == 0) {
                    this.hOne = mImage.getImageHeight(mainImage.img.image) / this.nFrame;
                    this.wOne = mImage.getImageWidth(mainImage.img.image);
                }
                mGraphics2.drawRegion(mainImage.img, 0, GameCanvas.gameTick / 7 % this.nFrame * this.hOne, this.wOne, this.hOne, 0, this.x, this.y, 33, false);
            }
        }
        if (GameScreen.ObjFocus == null || GameScreen.ObjFocus != null && this != GameScreen.ObjFocus || PaintInfoGameScreen.isLevelPoint) {
            this.paintName(mGraphics2, 0);
        }
    }

    public void update() {
        if (this.strChatPopup != null) {
            this.addChat(this.strChatPopup, true);
            this.strChatPopup = null;
        }
        if (this.chat != null) {
            this.chat.updatePos(this.x, this.y - this.hOne - 30);
            if (this.chat.setOff()) {
                this.chat = null;
            }
        }
        if (this.isMonPhoBangDie && this.timeFreeMove < 70) {
            int n;
            int n2;
            int n3;
            ++this.timeFreeMove;
            if (CRes.random(3) == 1) {
                if (CRes.random(2) == 1) {
                    LoadMap.timeVibrateScreen = 103;
                }
                n3 = CRes.random(1, 3);
                for (n2 = 0; n2 < n3; ++n2) {
                    n = CRes.random_Am_0(25);
                    int n4 = CRes.random_Am_0(30);
                    GameScreen.addEffectEndKill(36, this.x + n, this.y + n4 - this.hOne / 2);
                    if (CRes.random(3) != 1) continue;
                    GameScreen.addEffectEndKill(9, this.x + n, this.y + n4 - this.hOne / 2);
                }
            }
            if (this.timeFreeMove >= 70) {
                for (n3 = 0; n3 < 6; ++n3) {
                    n2 = CRes.random_Am_0(25);
                    n = CRes.random_Am_0(30);
                    GameScreen.addEffectEndKill(36, this.x + n2, this.y + n - this.hOne / 2);
                    if (CRes.random(3) != 1) continue;
                    GameScreen.addEffectEndKill(9, this.x + n2, this.y + n - this.hOne / 2);
                }
                GameScreen.addEffectKill(81, this.x, this.y - 20, 200, (short)0, (byte)0);
                this.isRemove = true;
                this.isMonPhoBangDie = false;
            }
        }
        if (this.indexSound >= 0 && GameCanvas.gameTick % this.timePlaySound == 0 && MainObject.isInScreen(this)) {
            if (this.indexSound == 44) {
                if (this.getCountPlayerINearMe() >= 5) {
                    mSound.playSound(this.indexSound, mSound.volumeSound);
                }
            } else {
                mSound.playSound(this.indexSound, mSound.volumeSound);
            }
        }
    }

    public void paintAvatarFocus(mGraphics mGraphics2, int n, int n2) {
        MainImage mainImage = ObjectData.getImagePartNPC(this.idImage);
        if (mainImage.img != null) {
            if (this.wAvatar <= 0) {
                if (this.wOne < 0) {
                    this.hOne = mImage.getImageHeight(mainImage.img.image) / this.nFrame;
                    this.wOne = mImage.getImageWidth(mainImage.img.image);
                }
                this.wAvatar = this.wOne;
                this.hAvatar = this.hOne;
                if (this.wAvatar > 26) {
                    this.wAvatar = 26;
                }
                if (this.hAvatar > 26) {
                    this.hAvatar = 26;
                }
            }
            mGraphics2.drawRegion(mainImage.img, this.wOne / 2 - this.wAvatar / 2, 0, this.wAvatar, this.hAvatar, 0, n, n2, 3, false);
        }
    }

    public void paintBigAvatar(mGraphics mGraphics2, int n, int n2) {
        MainImage mainImage = ObjectData.getImagePartNPC((short)this.IdBigAvatar);
        if (mainImage.img != null) {
            mGraphics2.drawImage(mainImage.img, n, n2, 40, false);
        }
    }

    public boolean isNPC() {
        return true;
    }

    public void GiaoTiep() {
        iCommand iCommand2;
        MainObject.resetDirection(GameScreen.player, this);
        if (this.isPerson == 0) {
            GlobalService.gI().getlist_from_npc((byte)this.ID);
            return;
        }
        mVector mVector2 = new mVector("NPC menu");
        if (this.nameGiaotiep.length() > 0) {
            iCommand2 = new iCommand(this.nameGiaotiep, 4, this);
            mVector2.addElement(iCommand2);
        }
        if (this.checkNV()) {
            iCommand2 = new iCommand(T.quest, 5, this);
            mVector2.addElement(iCommand2);
        }
        GameCanvas.menu2.startAt_NPC(mVector2, this.infoObject, this.ID, (byte)2, false, 0);
        mSound.playSound(39, mSound.volumeSound);
    }

    public boolean checkNV() {
        MainQuest mainQuest;
        int n;
        for (n = 0; n < MainQuest.vecQuestList.size(); ++n) {
            mainQuest = (MainQuest)MainQuest.vecQuestList.elementAt(n);
            if (mainQuest.idNPC_From != this.ID) continue;
            return true;
        }
        for (n = 0; n < MainQuest.vecQuestFinish.size(); ++n) {
            mainQuest = (MainQuest)MainQuest.vecQuestFinish.elementAt(n);
            if (mainQuest.idNPC_To != this.ID) continue;
            return true;
        }
        for (n = 0; n < MainQuest.vecQuestDoing_Main.size(); ++n) {
            mainQuest = (MainQuest)MainQuest.vecQuestDoing_Main.elementAt(n);
            if (mainQuest.idNPC_To != this.ID) continue;
            return true;
        }
        for (n = 0; n < MainQuest.vecQuestDoing_Sub.size(); ++n) {
            mainQuest = (MainQuest)MainQuest.vecQuestDoing_Sub.elementAt(n);
            if (mainQuest.idNPC_To != this.ID) continue;
            return true;
        }
        return false;
    }

    public void NhiemVu() {
        iCommand iCommand2;
        MainQuest mainQuest;
        int n;
        mVector mVector2 = new mVector("NPC menu2");
        for (n = 0; n < MainQuest.vecQuestList.size(); ++n) {
            mainQuest = (MainQuest)MainQuest.vecQuestList.elementAt(n);
            if (mainQuest.idNPC_From != this.ID) continue;
            iCommand2 = new iCommand(mainQuest.name, 0, n, (AvMain)this);
            iCommand2.setFraCaption(AvMain.fraQuest, 1, 1);
            mVector2.addElement(iCommand2);
        }
        for (n = 0; n < MainQuest.vecQuestFinish.size(); ++n) {
            mainQuest = (MainQuest)MainQuest.vecQuestFinish.elementAt(n);
            if (mainQuest.idNPC_To != this.ID) continue;
            iCommand2 = new iCommand(mainQuest.name, 1, n, (AvMain)this);
            iCommand2.setFraCaption(AvMain.fraQuest, 1, 3);
            mVector2.addElement(iCommand2);
        }
        for (n = 0; n < MainQuest.vecQuestDoing_Main.size(); ++n) {
            mainQuest = (MainQuest)MainQuest.vecQuestDoing_Main.elementAt(n);
            if (mainQuest.idNPC_To != this.ID) continue;
            iCommand2 = new iCommand(mainQuest.name, 2, n, (AvMain)this);
            iCommand2.setFraCaption(AvMain.fraQuest, 1, 2);
            mVector2.addElement(iCommand2);
        }
        for (n = 0; n < MainQuest.vecQuestDoing_Sub.size(); ++n) {
            mainQuest = (MainQuest)MainQuest.vecQuestDoing_Sub.elementAt(n);
            if (mainQuest.idNPC_To != this.ID) continue;
            iCommand2 = new iCommand(mainQuest.name, 3, n, (AvMain)this);
            iCommand2.setFraCaption(AvMain.fraQuest, 1, 2);
            mVector2.addElement(iCommand2);
        }
        if (mVector2.size() == 0) {
            GameCanvas.clearKeyHold();
            GameCanvas.clearKeyPressed();
            GameCanvas.menu2.doCloseMenu();
        } else {
            GameCanvas.menu2.doCloseMenu();
            GameCanvas.clearKeyHold();
            GameCanvas.clearKeyPressed();
            GameCanvas.menu2.startAt(mVector2, 2, T.quest, false, null);
        }
    }

    public void setPlaySound() {
        switch (this.ID) {
            case -21: 
            case -5: {
                this.indexSound = 43;
                this.timePlaySound = 150;
                break;
            }
            case -36: 
            case -20: 
            case -3: {
                this.indexSound = 44;
                this.timePlaySound = 150;
                break;
            }
        }
    }

    public int getCountPlayerINearMe() {
        int n = 0;
        for (int i = 0; i < GameScreen.Vecplayers.size(); ++i) {
            MainObject mainObject = (MainObject)GameScreen.Vecplayers.elementAt(i);
            if (mainObject.typeObject != 0 || MainObject.getDistance(this.x, this.y, mainObject.x, mainObject.y) > 120) continue;
            ++n;
        }
        return n;
    }
}

