/*
 * Decompiled with CFR 0.152.
 */
package GameEffect;

import CLib.mGraphics;
import GameEffect.MainEffect;
import GameObjects.DataSkillEff;
import GameObjects.MainObject;
import Model.CRes;

public class ArrowEff
extends MainEffect {
    public static final int[] TRANSFORM = new int[]{0, 0, 0, 7, 6, 6, 6, 2, 2, 3, 3, 4, 5, 5, 5, 1};
    public static final byte[] FRAME = new byte[]{0, 1, 2, 1, 0, 1, 2, 1, 0, 1, 2, 1, 0, 1, 2, 1, 0, 1, 2, 1, 0, 1, 2, 1, 0};
    public static final int[] ARROWINDEX = new int[]{0, 15, 37, 52, 75, 105, 127, 142, 165, 195, 217, 232, 255, 285, 307, 322, 345, 370};
    int dx;
    int dy;
    int angle;
    int xto;
    int yto;
    int d;
    MainObject target;
    DataSkillEff arrow;
    int transform;
    short timelive;

    ArrowEff(int n, int n2, MainObject mainObject, int n3, int n4, int n5) {
        this.x = n;
        this.y = n2;
        this.target = mainObject;
        this.xto = mainObject.x;
        this.yto = mainObject.y;
        this.setspeed();
        this.timelive = (short)500;
        if (FRAME[this.d] == 0) {
            this.arrow = new DataSkillEff(n3, this.transform);
        } else if (FRAME[this.d] == 1) {
            this.arrow = new DataSkillEff(n4, this.transform);
        } else if (FRAME[this.d] == 2) {
            this.arrow = new DataSkillEff(n5, this.transform);
        }
    }

    ArrowEff(int n, int n2, int n3, int n4, int n5, int n6, int n7) {
        this.x = n;
        this.y = n2;
        this.xto = n3;
        this.yto = n4;
        this.xto = this.target.x;
        this.yto = this.target.y;
        this.setspeed();
        this.timelive = (short)50;
        if (FRAME[this.d] == 0) {
            this.arrow = new DataSkillEff(n5, this.transform);
        } else if (FRAME[this.d] == 1) {
            this.arrow = new DataSkillEff(n6, this.transform);
        } else if (FRAME[this.d] == 2) {
            this.arrow = new DataSkillEff(n7, this.transform);
        }
    }

    public void setspeed() {
        this.dx = this.xto - this.x;
        this.dy = this.yto - 0 - this.y;
        this.angle = CRes.angle(this.dx, this.dy);
        this.vx = 1 * CRes.cos(this.angle) >> 10;
        this.vy = 1 * CRes.sin(this.angle) >> 10;
        this.d = ArrowEff.findDirIndexFromAngle(CRes.angle(this.dx, -this.dy));
        this.transform = TRANSFORM[this.d];
    }

    public static int findDirIndexFromAngle(int n) {
        for (int i = 0; i < ARROWINDEX.length - 1; ++i) {
            if (n < ARROWINDEX[i] || n > ARROWINDEX[i + 1]) continue;
            if (i >= 16) {
                return 0;
            }
            return i;
        }
        return 0;
    }

    public void paint(mGraphics mGraphics2) {
        this.arrow.paintTopArrow(mGraphics2, this.x, this.y);
        this.arrow.paintBottomArrow(mGraphics2, this.x, this.y);
    }

    public void update() {
        if (this.arrow != null) {
            this.arrow.updateArrow();
        }
        this.x += this.vx;
        this.y += this.vy;
        this.timelive = (short)(this.timelive - 1);
        if (this.timelive < 0) {
            this.isRemove = true;
        }
    }
}

