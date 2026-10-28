/*
 * Decompiled with CFR 0.152.
 */
package GameObjects;

import CLib.mFont;
import CLib.mGraphics;
import CLib.mSystem;
import CLib.mVector;
import GameEffect.EffectCharWearing;
import GameObjects.MainObject;
import GameObjects.MainQuest;
import GameObjects.Player;
import GameScreen.GameScreen;
import GameScreen.PaintInfoGameScreen;
import InterfaceComponents.MainTabNew;
import InterfaceComponents.PopupChat;
import InterfaceComponents.iCommand;
import Main.GameCanvas;
import Model.AvMain;
import Model.CRes;
import Model.T;
import Skill.ListSkill;
import Skill.SplashSkill;
import Thread_More.LoadMap;
import netcommand.global.GlobalService;

public class Other_Players
extends MainObject {
    private PopupChat nameStore;
    byte[] vmove = new byte[]{-1, 1};

    public Other_Players(int n, byte by, String string, int n2, int n3) {
        super(n, by, string, n2, n3);
        this.vMax = 6;
        this.hOne = 40;
        this.wOne = 30;
        this.hp = 0;
        this.maxHp = 0;
        this.mp = 0;
        this.maxMp = 0;
        this.xsai = 1;
        this.ysai = 2;
        this.PlashNow = new SplashSkill();
        this.ListKillNow = new ListSkill();
        this.countCharStand = 0;
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

    public void paintName(mGraphics mGraphics2, int n) {
        if (GameScreen.infoGame.isMapThachdau()) {
            return;
        }
        byte by = GameScreen.player.typePk;
        boolean bl = true;
        if (GameScreen.infoGame.isMapArena(GameCanvas.loadmap.idMap) && this.typePk == by) {
            bl = false;
        }
        String string = this.name;
        mFont mFont2 = mFont.tahoma_7_white;
        mFont2 = MainTabNew.setTextColor(n);
        boolean bl2 = true;
        int n2 = 0;
        if (this.typeSpec == 1) {
            if (this.typePk > 0) {
                bl2 = false;
            }
            n2 = 5;
        }
        int n3 = 18;
        if (PaintInfoGameScreen.isLevelPoint) {
            n3 = 12;
        }
        if (this.typeMonster == 7) {
            n3 += 8;
        }
        if (this.isObject && PaintInfoGameScreen.isLevelPoint) {
            n3 += 6;
        }
        if (this.idName != -1) {
            bl2 = false;
        }
        if (bl2 && !this.Namearena) {
            mFont2.drawString(mGraphics2, string, this.x, this.y - this.ysai - this.dy + this.dyWater - (this.isDongBang ? 5 : 0) - this.hOne - n3 - this.dyMount - this.yjum, 2, false);
        }
        if (this.typeObject == 0 && this.typeSpec == 1 && bl2 && !this.iscuop) {
            mFont2.drawString(mGraphics2, T.nhanban, this.x, this.y - this.ysai - this.dy + this.dyWater - (this.isDongBang ? 5 : 0) - this.hOne - (n3 += 10) - this.dyMount - this.yjum, 2, false);
        }
        if (this.typeObject == 2 && this.chat == null) {
            AvMain.fraQuest.drawFrame(this.typeNPC, this.x - 6, this.y - this.ysai - this.dy + this.dyWater - this.hOne - n3 - 18 - 4 + GameCanvas.gameTick / 2 % 4, 0, mGraphics2);
        }
        int n4 = 0;
        if (Player.party != null && this.isParty || this.isShowHP || this.typeMonster == 7) {
            int n5 = 44;
            if (this.typeObject == 2 || this.typeMonster == 7) {
                n5 = this.hOne + 5;
            }
            mGraphics2.setColor(-8714722);
            mGraphics2.fillRect(this.x - 20, this.y - this.ysai - this.dy + this.dyWater - n5 - n3 - this.dyMount - this.yjum, 40, 3, false);
            mGraphics2.setColor(-579511);
            mGraphics2.fillRect(this.x - 20, this.y - this.ysai - this.dy + this.dyWater - n5 - n3 - this.dyMount - this.yjum, 40 * this.hp / this.maxHp, 3, false);
            n4 += 5;
        }
        if (this.myClan != null && this.typeSpec != 1 && !this.Namearena) {
            this.paintIconClan(mGraphics2, this.x - 1, this.y - this.ysai - this.dy + this.dyWater - this.hOne - n3 - 8 - n4 - this.dyMount - this.yjum, 2);
            n4 += 16;
        }
        if (this.typePk >= 0 && this.typeObject == 0 && bl && !this.isPkVantieu()) {
            AvMain.fraPk.drawFrame(this.typePk * 3 + GameCanvas.gameTick / 3 % 3, this.x, this.y - this.dy + this.dyWater - this.ysai - (n4 += 59) + 18 - n3 + n2 - this.dyMount - this.yjum, 0, 33, mGraphics2);
        }
    }

    public void paint(mGraphics mGraphics2) {
        int n = 0;
        if (mSystem.isj2me) {
            if (this.typeObject == 0) {
                if (GameScreen.isHideOderPlayer) {
                    this.painthidePlayer(mGraphics2, n);
                    this.paintNameStore(mGraphics2, this.x, this.y);
                    return;
                }
                if (GameScreen.isHideFullOderPlayer) {
                    this.paintName(mGraphics2, n);
                    this.paintNameStore(mGraphics2, this.x, this.y);
                    return;
                }
            }
        } else if (this.typeObject == 0 && GameScreen.isHideOderPlayer) {
            this.painthidePlayer(mGraphics2, n);
            this.paintNameStore(mGraphics2, this.x, this.y);
            return;
        }
        if (this.typeObject == 2) {
            if (this.imageId == -1) {
                this.paintPlayer(mGraphics2, n);
            } else {
                this.paintObject(mGraphics2, n);
            }
        } else {
            this.paintPlayer(mGraphics2, n);
        }
        this.paintEffectCharWearing(mGraphics2);
        this.paintNameStore(mGraphics2, this.x, this.y);
    }

    public void paintNameStore(mGraphics mGraphics2, int n, int n2) {
        if (this.nameStore != null && !this.nameStore.name.equals("")) {
            this.nameStore.paint(mGraphics2);
        }
    }

    public void paintAvatarFocus(mGraphics mGraphics2, int n, int n2) {
        if (GameScreen.infoGame.isMapThachdau()) {
            return;
        }
        super.paintAvatarFocus(mGraphics2, n, n2);
    }

    public void update() {
        this.updateDataEffect();
        this.updateEffectCharWearing();
        this.updateActionPerson();
        if (this.nameStore != null) {
            this.nameStore.updatePos(this.x, this.y - this.hOne - 30);
        }
        if (this.KillFire != -1) {
            if (CRes.abs(this.x - this.xFire) <= this.vMax + this.getVmount() && CRes.abs(this.y - this.yFire) <= this.vMax + this.getVmount()) {
                ++this.timeHuyKill;
                this.posTransRoad = null;
                this.ListKillNow.setFireSkill(this, this.vecObjFocusSkill, this.KillFire, (byte)-1);
                if (this.timeHuyKill > 5) {
                    this.ListKillNow.fireSkillFree();
                    this.timeHuyKill = 0;
                    this.KillFire = -1;
                }
            }
        } else {
            this.timeHuyKill = 0;
        }
        if (!MainObject.isInScreen(this) && !this.setIsInScreen(this.toX, this.toY, this.wOne, this.hOne)) {
            this.x = this.toX;
            this.y = this.toY;
            this.vx = 0;
            this.vy = 0;
            if (this.Action != 4) {
                this.Action = 0;
            }
            return;
        }
        this.Move_to_Focus_Person();
        int n = GameCanvas.loadmap.getTile(this.x + this.vx, this.y + this.vy);
        this.setMove(1, n);
        this.updateEye();
        this.updateoverHP_MP();
        super.update();
        if (this.isMonPhoBangDie && this.timeFreeMove < 70) {
            int n2;
            int n3;
            ++this.timeFreeMove;
            if (CRes.random(3) == 1) {
                if (CRes.random(2) == 1) {
                    LoadMap.timeVibrateScreen = 103;
                }
                n = CRes.random(1, 3);
                for (n3 = 0; n3 < n; ++n3) {
                    n2 = CRes.random_Am_0(25);
                    int n4 = CRes.random_Am_0(30);
                    GameScreen.addEffectEndKill(36, this.x + n2, this.y + n4 - this.hOne / 2);
                    if (CRes.random(3) != 1) continue;
                    GameScreen.addEffectEndKill(9, this.x + n2, this.y + n4 - this.hOne / 2);
                }
            }
            if (this.timeFreeMove >= 70) {
                for (n = 0; n < 6; ++n) {
                    n3 = CRes.random_Am_0(25);
                    n2 = CRes.random_Am_0(30);
                    GameScreen.addEffectEndKill(36, this.x + n3, this.y + n2 - this.hOne / 2);
                    if (CRes.random(3) != 1) continue;
                    GameScreen.addEffectEndKill(9, this.x + n3, this.y + n2 - this.hOne / 2);
                }
                GameScreen.addEffectKill(81, this.x, this.y - 20, 200, (short)0, (byte)0);
                this.isRemove = true;
                this.isMonPhoBangDie = false;
            }
        }
    }

    public void move_to_XY(int n, int n2) {
        if (CRes.abs(this.x - n) > 2) {
            this.vy = 0;
            this.Action = 1;
            if (CRes.abs(this.x - n) > this.vMax + this.getVmount()) {
                if (this.x > n) {
                    this.vx = -(this.vMax + this.getVmount());
                    this.Direction = 2;
                } else {
                    this.vx = this.vMax + this.getVmount();
                    this.Direction = 3;
                }
            } else {
                this.vx = n - this.x;
            }
        } else if (CRes.abs(this.y - n2) > 2) {
            this.vx = 0;
            this.Action = 1;
            if (CRes.abs(this.y - n2) > this.vMax + this.getVmount()) {
                if (this.y > n2) {
                    this.vy = -(this.vMax + this.getVmount());
                    this.Direction = 1;
                } else {
                    this.vy = this.vMax + this.getVmount();
                    this.Direction = 0;
                }
            } else {
                this.vy = n2 - this.y;
            }
        } else {
            this.vx = 0;
            this.vy = 0;
        }
    }

    public int getIDnpc() {
        return this.isBot;
    }

    public void GiaoTiep() {
        if (this.isNPC()) {
            iCommand iCommand2;
            if (this.isObject) {
                return;
            }
            MainObject.resetDirection(GameScreen.player, this);
            if (this.isPerson == 0) {
                GlobalService.gI().getlist_from_npc((byte)this.getIDnpc());
                return;
            }
            mVector mVector2 = new mVector("Other_Player menu");
            if (this.nameGiaotiep.length() > 0) {
                iCommand2 = new iCommand(this.nameGiaotiep, 4, this);
                mVector2.addElement(iCommand2);
            }
            if (this.checkNV()) {
                iCommand2 = new iCommand(T.quest, 5, this);
                mVector2.addElement(iCommand2);
            }
            GameCanvas.menu2.startAt(mVector2, 2, T.giaotiep, false, null);
        } else if (this.typeObject == 0 && this.typeSpec == 0) {
            mVector mVector3 = new mVector("Other_Player menu2");
            mVector3.addElement(GameScreen.gI().cmdChat);
            mVector3.addElement(GameScreen.gI().cmdAddfriend);
            mVector3.addElement(GameScreen.gI().cmdInfoChar);
            if (Player.party == null || Player.party.vecPartys.size() < 5) {
                mVector3.addElement(GameScreen.gI().cmdMoiParty);
            }
            mVector3.addElement(GameScreen.gI().cmdBuy_Sell);
            if (LoadMap.typeMap != 1 && LoadMap.typeMap != 2) {
                mVector3.addElement(GameScreen.gI().cmdThachDau);
            }
            if (GameScreen.player.myClan != null && GameScreen.player.myClan.setAddMem()) {
                mVector3.addElement(GameScreen.gI().cmdAddMemClan);
            }
            mVector mVector4 = this.vectorObjectNear();
            mVector4.insertElementAt(this, 0);
            GameCanvas.menu2.startAt(mVector3, 2, T.giaotiep, true, mVector4);
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
        mVector mVector2 = new mVector("Other_Player menu3");
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

    public void addEffectCharWearing(int n, int n2) {
        EffectCharWearing effectCharWearing = new EffectCharWearing((byte)n, n2);
        this.vecEffectCharWearing.addElement(effectCharWearing);
    }

    public void removeNameStore() {
        this.nameStore = null;
    }

    public void setNameStore(String string) {
        if (this.nameStore == null) {
            this.nameStore = new PopupChat();
        }
        this.nameStore.setChat(string, this.isStop);
        this.nameStore.updatePos(this.x, this.y - this.hOne - 30);
    }

    public boolean isSelling() {
        return this.nameStore != null;
    }

    public boolean canFocus() {
        return this.typefocus == 1;
    }

    public boolean canfire() {
        return this.typefire == 1;
    }
}

