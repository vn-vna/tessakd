/*
 * Decompiled with CFR 0.152.
 */
package Model;

import GameObjects.MainObject;
import Model.CRes;

public class MagicLogic {
    public int angle;
    public int vx;
    public int vy;
    public short va;
    public int x;
    public int y;
    public int z;
    public int index = 0;
    public MainObject target;
    int life;
    public boolean isSpeedUp = false;
    public int headArrowFrame;
    public int headTransform;
    public static final short[] SPEED = new short[]{1, 60, 8, 1, 1, 16, 60, 18};
    public boolean isEnd = false;

    public void setAngle(int n) {
        this.angle = n;
        this.vx = this.va * CRes.cos(n) >> 10;
        this.vy = this.va * CRes.sin(n) >> 10;
    }

    public void set(int n, int n2, int n3, int n4, MainObject mainObject) {
        this.x = n2;
        this.y = n3;
        this.target = mainObject;
        switch (n4) {
            case 0: {
                this.angle = 90;
                break;
            }
            case 1: {
                this.angle = 270;
                break;
            }
            case 2: {
                this.angle = 180;
                break;
            }
            case 3: {
                this.angle = 0;
            }
        }
        if (n == 20) {
            n = 2;
        }
        this.va = (short)(256 * SPEED[n]);
        this.z = 0;
        this.life = 0;
        this.vx = this.va * CRes.cos(this.angle) >> 10;
        this.vy = this.va * CRes.sin(this.angle) >> 10;
    }

    public void updateAngle() {
        if (this.target == null) {
            this.isEnd = true;
            return;
        }
        int n = this.target.x - this.x;
        int n2 = this.target.y - (this.target.hOne >> 1) - this.y;
        ++this.life;
        if (CRes.abs(n) < 16 && CRes.abs(n2) < 16 || this.life > 60) {
            this.isEnd = true;
            return;
        }
        int n3 = CRes.angle(n, n2);
        if (Math.abs(n3 - this.angle) < 90 || n * n + n2 * n2 > 4096) {
            this.angle = Math.abs(n3 - this.angle) < 15 ? n3 : (n3 - this.angle >= 0 && n3 - this.angle < 180 || n3 - this.angle < -180 ? CRes.fixangle(this.angle + 15) : CRes.fixangle(this.angle - 15));
        }
        if (!this.isSpeedUp && this.va < 8192) {
            this.va = (short)(this.va + 1024);
        }
        this.vx = this.va * CRes.cos(this.angle) >> 10;
        this.vy = this.va * CRes.sin(this.angle) >> 10;
        int n4 = (n += this.vx) >> 10;
        this.x += n4;
        n &= 0x3FF;
        int n5 = (n2 += this.vy) >> 10;
        this.y += n5;
        n2 &= 0x3FF;
    }
}

