/*
 * Decompiled with CFR 0.152.
 */
package GameEffect;

import CLib.mGraphics;
import Main.GameCanvas;

public class Effect_UpLv_Item {
    int wnew = 1;
    int[][] colorBorder = new int[][]{{-1, -1, -1052689, -1052689, -4079167, -4079167, -6250336}, {-14097665, -14097665, -14104585, -14104585, -14114080, -14114080, -14249063}, {-1280, -1280, -1251840, -1251840, -3949821, -3949821, -5594621}, {-423937, -423937, -1473281, -1473281, -3768833, -3768833, -5934642}, {-20158, -20158, -26313, -26313, -34787, -34787, -3179218}};
    int[] size = new int[]{1, 1, 1, 1, 1};

    public void paintUpgradeEffect(int n, int n2, int n3, int n4, mGraphics mGraphics2, int n5) {
        int n6;
        int n7;
        int n8;
        if (n3 <= 0) {
            return;
        }
        int n9 = n4;
        int n10 = 0;
        int n11 = (n3 - 1) % 5;
        mGraphics2.setColor(this.colorBorder[n11][6]);
        mGraphics2.drawRect(n - n4 / 2 - n5, n2 - n4 / 2 - n5, n4, n4, true);
        for (n8 = n10; n8 < this.size.length; ++n8) {
            n7 = n - n9 / 2 + this.upgradeEffectX(GameCanvas.gameTick * 1 - n8 * this.wnew, n4);
            n6 = n2 - n9 / 2 + this.upgradeEffectY(GameCanvas.gameTick * 1 - n8 * this.wnew, n4);
            mGraphics2.setColor(this.colorBorder[n11][n8]);
            mGraphics2.fillRect(n7 - this.size[n8] / 2 - n5, n6 - this.size[n8] / 2 - n5, this.size[n8], this.size[n8], true);
        }
        if (n3 >= 6 && n3 < 11) {
            for (n8 = n10; n8 < this.size.length; ++n8) {
                n7 = n - n9 / 2 + this.upgradeEffectX((GameCanvas.gameTick - n9 * 2) * 1 - n8 * this.wnew, n4);
                n6 = n2 - n9 / 2 + this.upgradeEffectY((GameCanvas.gameTick - n9 * 2) * 1 - n8 * this.wnew, n4);
                mGraphics2.setColor(this.colorBorder[n11][n8]);
                mGraphics2.fillRect(n7 - this.size[n8] / 2 - n5, n6 - this.size[n8] / 2 - n5, this.size[n8], this.size[n8], true);
            }
        }
        if (n3 >= 11) {
            for (n8 = n10; n8 < this.size.length; ++n8) {
                n7 = n - n9 / 2 + this.upgradeEffectX((GameCanvas.gameTick - n9 * 13 / 10) * 1 - n8 * this.wnew, n4);
                n6 = n2 - n9 / 2 + this.upgradeEffectY((GameCanvas.gameTick - n9 * 13 / 10) * 1 - n8 * this.wnew, n4);
                mGraphics2.setColor(this.colorBorder[n11][n8]);
                mGraphics2.fillRect(n7 - this.size[n8] / 2 - n5, n6 - this.size[n8] / 2 - n5, this.size[n8], this.size[n8], true);
            }
            for (n8 = n10; n8 < this.size.length; ++n8) {
                n7 = n - n9 / 2 + this.upgradeEffectX((GameCanvas.gameTick - n9 * 13 / 5) * 1 - n8 * this.wnew, n4);
                n6 = n2 - n9 / 2 + this.upgradeEffectY((GameCanvas.gameTick - n9 * 13 / 5) * 1 - n8 * this.wnew, n4);
                mGraphics2.setColor(this.colorBorder[n11][n8]);
                mGraphics2.fillRect(n7 - this.size[n8] / 2 - n5, n6 - this.size[n8] / 2 - n5, this.size[n8], this.size[n8], true);
            }
        }
    }

    public int upgradeEffectY(int n, int n2) {
        int n3 = n2;
        int n4 = n % (4 * n3);
        if (0 <= n4 && n4 < n3) {
            return 0;
        }
        if (n3 <= n4 && n4 < n3 * 2) {
            return n4 % n3;
        }
        if (n3 * 2 <= n4 && n4 < n3 * 3) {
            return n3;
        }
        return n3 - n4 % n3;
    }

    public int upgradeEffectX(int n, int n2) {
        int n3 = n2;
        int n4 = n % (4 * n3);
        if (0 <= n4 && n4 < n3) {
            return n4 % n3;
        }
        if (n3 <= n4 && n4 < n3 * 2) {
            return n3;
        }
        if (n3 * 2 <= n4 && n4 < n3 * 3) {
            return n3 - n4 % n3;
        }
        return 0;
    }
}

