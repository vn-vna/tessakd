/*
 * Decompiled with CFR 0.152.
 */
package GameObjects;

import CLib.mFont;
import CLib.mGraphics;
import CLib.mImage;
import CLib.mSound;
import CLib.mVector;
import GameObjects.MainObject;
import GameObjects.MainQuest;
import GameObjects.NPCMini;
import GameObjects.ObjectData;
import GameObjects.Other_Players;
import GameScreen.GameScreen;
import InterfaceComponents.MainTabNew;
import InterfaceComponents.iCommand;
import Main.GameCanvas;
import Model.AvMain;
import Model.FrameImage;
import Model.MainImage;
import Model.T;
import Thread_More.MiniMap;
import netcommand.global.GlobalService;

public class NPCserver
extends MainObject {
    public short idImage;
    public short wBlock;
    public short hBlock;
    public short xBlock;
    public short yBlock;
    int nFrame;
    int wAvatar;
    int hAvatar;
    int indexSound = -1;
    int timePlaySound = -1;
    int dx;
    int dy;
    Other_Players p = null;
    mImage imgSub;
    FrameImage imgSubFrame;
    byte idnotFocus = (byte)127;
    byte[] frameArray;
    byte f;

    public NPCserver(byte by, short s, short s2, byte by2, byte by3, byte by4, String string, String string2, short s3, short s4, byte by5, byte by6, byte[] byArray, byte[] byArray2, byte[] byArray3) {
        this.idImage = by;
        this.x = s;
        this.y = s2;
        this.dx = by2;
        this.dy = by3;
        this.nFrame = by4;
        this.name = string2;
        this.nameGiaotiep = string;
        this.wBlock = by5;
        this.hBlock = by6;
        this.xBlock = s3;
        this.yBlock = s4;
        this.isStop = false;
        this.isRemove = false;
        String string3 = "npc_server" + this.idImage;
        this.imgSub = mImage.createImage(byArray2, 0, byArray2.length, string3);
        this.imgSubFrame = new FrameImage(this.imgSub, mImage.getImageWidth(this.imgSub.image), mImage.getImageHeight(this.imgSub.image) / this.nFrame);
        this.frameArray = byArray3;
        GameCanvas.loadmap.setBlockNPC_Server(this.xBlock - 12, this.yBlock, this.wBlock, this.hBlock);
        if (this.idImage != this.idnotFocus) {
            this.p = new Other_Players(this.idImage, 0, this.name, this.x, this.y);
            this.p.clazz = 1;
            this.p.Direction = 0;
            this.p.setInfo(byArray[12], byArray[13], byArray[14]);
            this.p.setWearingEquip(byArray);
            MiniMap.addNPCMini(new NPCMini(by, this.x, this.y));
            this.setPlaySound();
            this.p.x = this.x + this.dx;
            this.p.y = this.y + this.dy;
            this.p.toX = this.p.x;
            this.p.toY = this.p.y;
            this.hOne = mImage.getImageHeight(this.imgSub.image) + this.p.hOne - 10;
            this.hp = 100;
            this.maxHp = 100;
            this.Lv = 1;
        }
    }

    public void setInfo(byte by, short s, short s2, byte by2, byte by3, byte by4, String string, String string2, short s3, short s4, byte by5, byte by6, byte[] byArray, byte[] byArray2, byte[] byArray3) {
        this.p = null;
        this.imgSub = null;
        this.imgSubFrame = null;
        this.idImage = by;
        this.x = s;
        this.y = s2;
        this.dx = by2;
        this.dy = by3;
        this.nFrame = by4;
        this.name = string2;
        this.nameGiaotiep = string;
        this.wBlock = by5;
        this.hBlock = by6;
        this.xBlock = s3;
        this.yBlock = s4;
        this.isStop = false;
        this.isRemove = false;
        String string3 = "npc_server" + this.idImage;
        this.imgSub = mImage.createImage(byArray2, 0, byArray2.length, string3);
        this.imgSubFrame = new FrameImage(this.imgSub, mImage.getImageWidth(this.imgSub.image), mImage.getImageHeight(this.imgSub.image) / this.nFrame);
        this.frameArray = byArray3;
        GameCanvas.loadmap.setBlockNPC_Server(this.xBlock - 12, this.yBlock, this.wBlock, this.hBlock);
        if (this.idImage != this.idnotFocus) {
            this.p = new Other_Players(this.idImage, 0, this.name, this.x, this.y);
            this.p.clazz = 1;
            this.p.Direction = 0;
            this.p.setInfo(byArray[12], byArray[13], byArray[14]);
            this.p.setWearingEquip(byArray);
            MiniMap.addNPCMini(new NPCMini(by, this.x, this.y));
            this.setPlaySound();
            this.p.x = this.x + this.dx;
            this.p.y = this.y + this.dy;
            this.p.toX = this.p.x;
            this.p.toY = this.p.y;
            this.hOne = mImage.getImageHeight(this.imgSub.image) + this.p.hOne - 10;
        }
    }

    public void paint(mGraphics mGraphics2) {
        if (this.imgSubFrame != null) {
            this.imgSubFrame.drawFrame(this.frameArray[this.f], this.x, this.y, 0, 33, mGraphics2);
        }
        if (this.p != null) {
            this.p.paint(mGraphics2);
            this.paintName(mGraphics2, 2);
        }
    }

    public void paintName(mGraphics mGraphics2, int n) {
        mFont mFont2 = mFont.tahoma_7_white;
        mFont2 = MainTabNew.setTextColor(n);
        mFont2.drawString(mGraphics2, this.nameGiaotiep, this.p.x, this.p.y - 70, 2, false);
    }

    public void update() {
        this.f = (byte)(this.f + 1);
        if (this.f > this.frameArray.length - 1) {
            this.f = 0;
        }
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

    public boolean canFocus() {
        return this.idImage != this.idnotFocus;
    }

    public boolean isNPC() {
        return this.idImage != this.idnotFocus;
    }

    public void paintNameFocus(mGraphics mGraphics2) {
    }

    public boolean isNPC_server() {
        return true;
    }

    public int getIDnpc() {
        return this.idImage;
    }

    public void GiaoTiep() {
        if (this.isNPC()) {
            iCommand iCommand2;
            MainObject.resetDirection(GameScreen.player, this);
            if (this.isPerson == 0) {
                GlobalService.gI().getlist_from_npc((byte)this.getIDnpc());
                return;
            }
            mVector mVector2 = new mVector("NPCserver menu");
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
        mVector mVector2 = new mVector("NPCserver menu2");
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
}

