/*
 * Decompiled with CFR 0.152.
 */
package GameObjects;

import CLib.mGraphics;
import GameObjects.IArrow;
import GameObjects.MainObject;
import Model.CRes;
import Model.FrameImage;

public class Arrow
extends IArrow {
    public static final int[] ARROWINDEX = new int[]{0, 15, 37, 52, 75, 105, 127, 142, 165, 195, 217, 232, 255, 285, 307, 322, 345, 370};
    public int power;
    public int effect;
    public int typeEffEnd = -1;
    private MainObject target;
    private int[] xw;
    private int[] yw;
    int frame;
    int transform;
    int pos;
    private FrameImage img;
    public static final byte[][] ARROWSIZE = new byte[][]{new byte[0], new byte[0]};
    public static final int[] TRANSFORM = new int[]{0, 0, 0, 7, 6, 6, 6, 2, 2, 3, 3, 4, 5, 5, 5, 1};
    public static final byte[] FRAME = new byte[]{0, 1, 2, 1, 0, 1, 2, 1, 0, 1, 2, 1, 0, 1, 2, 1, 0, 1, 2, 1, 0, 1, 2, 1, 0};
    private short endeff;

    public Arrow(int n) {
    }

    public Arrow() {
    }

    public void setAngle(int n) {
    }

    public void onArrowTouchTarget() {
        this.wantDestroy = true;
    }

    public void paint(mGraphics mGraphics2) {
        if (this.img != null) {
            this.img.drawFrameEffectSkill(this.frame, this.xw[this.pos], this.yw[this.pos], this.transform, 3, mGraphics2);
        }
    }

    public void set(int n, int n2, int n3, int n4, short s, MainObject mainObject, MainObject mainObject2) {
    }

    public void set(int n, int n2, int n3, int n4, short s, MainObject mainObject, MainObject mainObject2, int n5) {
        int n6;
        this.effect = n5;
        this.effect = s;
        this.target = mainObject2;
        this.power = n4;
        int n7 = 0;
        int n8 = 0;
        n7 = mainObject2.x - n2;
        n8 = mainObject2.y + mainObject2.getDy() - n3;
        if (n == 7) {
            n7 = mainObject2.x + 10 - n2;
            n8 = mainObject2.y + mainObject2.getDy() - n3;
        } else if (n == 8) {
            n7 = mainObject2.x - 10 - n2;
            n8 = mainObject2.y - 10 + mainObject2.getDy() - n3;
        }
        int n9 = (Math.abs(n7) + Math.abs(n8)) / 20;
        if (n9 < 2) {
            n9 = 2;
        }
        this.xw = new int[n9];
        this.yw = new int[n9];
        for (n6 = 1; n6 < n9; ++n6) {
            this.xw[n6] = n2 + n6 * n7 / n9;
            this.yw[n6] = n3 + n6 * n8 / n9;
        }
        n6 = Arrow.findDirIndexFromAngle(CRes.angle(n7, -n8));
        this.frame = FRAME[n6];
        this.transform = TRANSFORM[n6];
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

    public void update() {
        ++this.pos;
        if (this.pos >= this.xw.length) {
            this.pos = this.xw.length;
        }
        if (this.pos == this.xw.length) {
            this.onArrowTouchTarget();
            return;
        }
    }

    public void SetEffFollow(int n) {
    }
}

