/*
 * Decompiled with CFR 0.152.
 */
package GameObjects;

import CLib.mGraphics;
import GameObjects.MainObject;
import Model.FrameImage;

public class Mount
extends MainObject {
    public static byte[][] Dir = new byte[][]{{5, 5, 5, 5, 5, 5, 6, 6, 6, 6, 6, 6}, {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1}, {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1}, {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1}};

    public Mount(byte by, byte by2, int n, int n2) {
        this.typeMount = by;
        this.x = n;
        this.y = n2;
        this.Direction = by2;
        this.Action = 0;
        this.typeObject = (byte)10;
    }

    public void updateMount() {
        this.frameMount = (byte)(this.frameMount + 1);
        if (this.frameMount > Dir[this.Direction].length - 1) {
            this.frameMount = 0;
        }
    }

    public void paint(mGraphics mGraphics2) {
        if (this.typeMount == -1) {
            return;
        }
        FrameImage frameImage = FrameImage.getFrameImageMount(this.typeMount, 3, 5, 0);
        if (frameImage != null) {
            mGraphics2.drawImage(MainObject.shadow, this.x, this.y - 8, 3, false);
            frameImage.drawFrameNew(Dir[this.Direction][this.frameMount], this.x + this.xMount, this.y - this.ysai - this.dy + this.yMount, this.Direction > 2 ? 2 : 0, 33, mGraphics2);
        }
    }
}

