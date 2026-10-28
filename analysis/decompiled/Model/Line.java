/*
 * Decompiled with CFR 0.152.
 */
package Model;

public class Line {
    public int x0;
    public int y0;
    public int x1;
    public int y1;
    public int vx;
    public int vy;
    public int f;
    public int fRe;
    public int idColor;
    public boolean is2Line = false;

    public void setLine(int n, int n2, int n3, int n4, int n5, int n6, boolean bl) {
        this.x0 = n;
        this.y0 = n2;
        this.x1 = n3;
        this.y1 = n4;
        this.vx = n5;
        this.vy = n6;
        this.is2Line = bl;
    }

    public void update() {
        this.x0 += this.vx;
        this.x1 += this.vx;
        this.y0 += this.vy;
        this.y1 += this.vy;
        ++this.f;
    }
}

