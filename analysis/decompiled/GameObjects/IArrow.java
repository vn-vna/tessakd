/*
 * Decompiled with CFR 0.152.
 */
package GameObjects;

import CLib.mGraphics;
import GameObjects.MainObject;

public abstract class IArrow {
    public boolean wantDestroy;

    public abstract void set(int var1, int var2, int var3, int var4, short var5, MainObject var6, MainObject var7);

    public abstract void setAngle(int var1);

    public abstract void update();

    public abstract void paint(mGraphics var1);

    public abstract void SetEffFollow(int var1);

    public abstract void onArrowTouchTarget();
}

