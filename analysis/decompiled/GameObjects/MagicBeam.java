/*
 * Decompiled with CFR 0.152.
 */
package GameObjects;

import CLib.mGraphics;
import GameEffect.EffectEnd;
import GameObjects.IArrow;
import GameObjects.MagicLogic;
import GameObjects.MainObject;
import GameScreen.GameScreen;
import Model.EffectManager;
import Model.FrameImage;

public class MagicBeam
extends IArrow {
    public int power;
    public int type;
    public short effect;
    public int hpLost;
    public int frame;
    public int idfollow;
    public int idend;
    public int w;
    public int h;
    public int x;
    public int y;
    public int Efftail;
    public int Effend;
    MainObject owner;
    MainObject target;
    public MagicLogic arrow = new MagicLogic();
    byte fra;
    private FrameImage img;

    public void setAngle(int n) {
        this.arrow.setAngle(n);
    }

    public void setEff(int n, int n2) {
        this.Efftail = n;
        this.Effend = n2;
    }

    public void set(int n, int n2, int n3, int n4, short s, MainObject mainObject, MainObject mainObject2) {
        this.arrow.set(n, n2, n3, mainObject.getDir(), mainObject2);
        this.type = n;
        this.owner = mainObject;
        this.effect = s;
        if (n == 20) {
            n = 2;
        }
        this.hpLost = n4;
        this.Efftail = -1;
        this.Effend = -1;
        this.power = n4;
        this.wantDestroy = false;
        this.img = new FrameImage(s);
        this.target = mainObject2;
    }

    public void update() {
        this.arrow.updateAngle();
        this.x = this.arrow.x;
        this.y = this.arrow.y;
        if (this.Efftail != -1) {
            EffectEnd effectEnd = new EffectEnd(57, this.Efftail, this.x, this.y);
            EffectManager.addHiEffect(effectEnd);
        }
        if (this.arrow.isEnd) {
            this.onArrowTouchTarget();
        }
    }

    public void onArrowTouchTarget() {
        if (this.Effend != -1) {
            if (this.power < 0) {
                GameScreen.addEffectNum("" + this.power, this.target.x, this.target.y - this.target.hOne, 2, this.target.ID);
            }
            if (this.power > 0) {
                GameScreen.addEffectNum("+" + this.power, this.target.x, this.target.y - this.target.hOne, 0, this.target.ID);
            }
            GameScreen.addEffectEndFromSv(57, this.Effend, this.x, this.y);
        }
        this.wantDestroy = true;
    }

    public void paint(mGraphics mGraphics2) {
        if (this.img != null) {
            this.img.drawFrameEffectSkill(this.fra, this.x, this.y, this.arrow.headTransform, 3, mGraphics2);
        }
    }

    public void SetEffFollow(int n) {
        this.idfollow = n;
    }
}

