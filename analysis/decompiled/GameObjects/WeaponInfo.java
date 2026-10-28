/*
 * Decompiled with CFR 0.152.
 */
package GameObjects;

import CLib.mImage;
import CLib.mSystem;

public class WeaponInfo {
    public mImage img;
    public byte[][][] mPos = mSystem.new_M_Byte(4, 3, 2);
    public byte[][] mRegion = mSystem.new_M_Byte(4, 2);
    public int himg;
}

