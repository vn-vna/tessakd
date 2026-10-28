/*
 * Decompiled with CFR 0.152.
 */
package Model;

import CLib.mGraphics;
import CLib.mVector;
import GameScreen.GameScreen;
import Model.FrameImage;

public class Point {
    public static final byte DAU_CHAN = 0;
    public static final byte DONG_NUOC = 1;
    public int x;
    public int y;
    public int g;
    public int v;
    public int w;
    public int h;
    public int color = 0;
    public int limitY;
    public int vx;
    public int vy;
    public int x2;
    public int y2;
    public int dis;
    public int f;
    public int fRe;
    public int frame;
    public int maxframe;
    public int fSmall;
    public mVector vecEffPoint;
    public String name;
    public boolean isRemove = false;
    public boolean isSmall = false;
    public static FrameImage[] FraEffInMap;

    public Point() {
    }

    public Point(int n, int n2) {
        this.x = n;
        this.y = n2;
    }

    public void update() {
        ++this.f;
        this.x += this.vx;
        this.y += this.vy;
    }

    public void paint(mGraphics mGraphics2) {
        if (!this.isRemove) {
            int n = 0;
            if (this.isSmall && this.f >= this.fSmall) {
                n = 1;
            }
            FraEffInMap[this.color].drawFrame(this.frame / 2 + n, this.x, this.y, this.dis, 3, mGraphics2);
        }
    }

    public void updateInMap() {
        boolean bl = true;
        if (this.color == 0 && GameScreen.vecStep.size() < 10) {
            bl = false;
        }
        if (!bl) {
            return;
        }
        ++this.f;
        if (this.maxframe > 1) {
            ++this.frame;
            if (this.frame / 2 >= this.maxframe) {
                this.frame = 0;
            }
        }
        if (this.f >= this.fRe) {
            this.isRemove = true;
        }
    }
}

