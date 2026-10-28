/*
 * Decompiled with CFR 0.152.
 */
package GameObjects;

import CLib.mFont;
import CLib.mGraphics;
import CLib.mSystem;
import GameEffect.EffectAuto;
import GameObjects.MainMonster;
import GameScreen.GameScreen;
import Model.CRes;
import Model.FrameImage;
import Model.T;
import Thread_More.LoadMap;
import netcommand.global.GlobalService;

public class Monsterplus
extends MainMonster {
    public byte ideffect = (byte)-1;
    public byte fireFrame = 0;
    public EffectAuto eff;
    public int r;
    public FrameImage fraImgEff;
    public int[] arr_radian = new int[]{0, 30, 60, 90, 120, 150, 180, 210, 240, 270, 300, 330};
    public long timeLive = 0L;

    public Monsterplus(int n, int n2, int n3, String string, int n4, int n5, int n6, int n7) {
        this.typeObject = 1;
        this.typeMonster = n3;
        this.ID = n;
        this.catalogyMonster = n2;
        this.xAnchor = n4;
        this.yAnchor = n5;
        this.x = n4;
        this.y = n5;
        this.name = string;
        this.maxHp = n6;
        this.hp = n6;
        this.Lv = (short)n7;
        this.MonWater = 1;
        this.Direction = 0;
        this.nFrame = 5;
        this.hOne = -1;
        this.wOne = -1;
        this.vMax = 3;
        this.fraImgEff = new FrameImage(1);
        this.r = 120;
        this.limitMove = 60;
        this.timeAutoAction = CRes.random(50, 70);
        this.limitAttack = 50;
        this.xsai = 0;
        this.ysai = -2;
        this.timeLoadInfo = mSystem.currentTimeMillis();
        if (this.catalogyMonster == 110) {
            this.ideffect = (byte)52;
            this.r = 120;
            this.eff = new EffectAuto(this.ideffect, n4, n5, 0, 0, 1, 0);
        }
    }

    public void setEffectauto(int n, int n2, short s) {
        this.ideffect = (byte)n;
        this.eff = new EffectAuto(this.ideffect, this.x, this.y, 0, 0, 1, 0);
        this.r = n2;
        this.Lv = s;
    }

    public void setTimelive(long l) {
        this.timeLive = l;
    }

    public boolean isLuaThieng() {
        return true;
    }

    public void setLvmonster(int n) {
        this.Lv = (byte)n;
        if (this.catalogyMonster == 110) {
            if (this.Lv == 1) {
                this.ideffect = (byte)52;
                this.r = 120;
            }
            if (this.Lv == 2) {
                this.ideffect = (byte)53;
                this.r = 130;
            } else if (this.Lv == 3) {
                this.ideffect = (byte)54;
                this.r = 140;
            } else if (this.Lv == 4) {
                this.ideffect = (byte)55;
                this.r = 150;
            }
            this.eff = new EffectAuto(this.ideffect, this.x, this.y, 0, 0, 1, 0);
        }
    }

    public void setIdeffect(byte by) {
        this.ideffect = by;
    }

    public void updateAction() {
    }

    public void update() {
        long l;
        long l2;
        super.update();
        if (!this.isInfo && (l2 = (l = mSystem.currentTimeMillis()) - this.timeLoadInfo) >= 5000L) {
            this.timeLoadInfo = l;
            GlobalService.gI().monster_info((short)this.ID);
        }
        this.setDie();
        this.updateAction();
        this.fireFrame = (byte)(this.fireFrame + 1);
        if (this.fireFrame > 10) {
            this.fireFrame = 0;
        }
        if (this.eff != null) {
            this.eff.update();
        }
        boolean bl = false;
        if (this.myClan != null) {
            if (GameScreen.player.myClan != null && this.myClan.IdClan == GameScreen.player.myClan.IdClan) {
                bl = true;
            }
        } else if (this.nameowner.equals(GameScreen.player.name)) {
            bl = true;
        }
        if (bl) {
            this.ysai = 10;
            for (int i = 0; i < this.arr_radian.length; ++i) {
                int n = i;
                this.arr_radian[n] = this.arr_radian[n] + 1;
                if (this.arr_radian[i] < 360) continue;
                this.arr_radian[i] = 0;
            }
        }
    }

    public void paint(mGraphics mGraphics2) {
        if (this.eff != null) {
            this.eff.paint(mGraphics2);
        }
        boolean bl = false;
        if (this.myClan != null) {
            if (GameScreen.player.myClan != null && this.myClan.IdClan == GameScreen.player.myClan.IdClan) {
                bl = true;
            }
        } else if (this.nameowner.equals(GameScreen.player.name)) {
            bl = true;
        }
        if (bl) {
            this.paintIconClan(mGraphics2, this.x - 1, this.y - this.ysai - this.dy + this.dyWater - this.hOne - 20, 2);
            String string = "";
            long l = this.timeLive - mSystem.currentTimeMillis();
            string = LoadMap.convertSecondsToHMmSs(l);
            if (!string.equals("")) {
                mFont.tahoma_7_yellow.drawString(mGraphics2, string, this.x, this.y - this.ysai - this.dy + this.dyWater - this.hOne - 40, 3, false);
            }
            mFont.tahoma_7_yellow.drawString(mGraphics2, T.level + this.Lv, this.x, this.y - this.ysai - this.dy + this.dyWater - this.hOne - 55, 3, false);
            for (int i = 0; i < this.arr_radian.length; ++i) {
                if (this.fraImgEff == null) continue;
                this.fraImgEff.drawFrameEffectSkill(this.fireFrame / 2 % this.fraImgEff.nFrame, CRes.cos(this.arr_radian[i]) * this.r / 1024 + this.x + 2, CRes.sin(this.arr_radian[i]) * this.r / 1024 + this.y, 0, 3, mGraphics2);
            }
        }
    }

    public void move_to_XY() {
    }

    public void move_to_XY_Normal() {
    }

    public void moveX() {
    }

    public void moveY() {
    }
}

