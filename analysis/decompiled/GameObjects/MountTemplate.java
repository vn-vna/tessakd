/*
 * Decompiled with CFR 0.152.
 */
package GameObjects;

public class MountTemplate {
    public short idTemplate;
    public short idImage;
    public short lv;
    public byte typemove;
    public String name = "";
    public static final byte NGUA_NAU = 0;
    public static final byte NGUA_TRANG = 1;
    public static final byte NGUA_CHIENGIAP = 2;
    public static final byte NGUA_DO = 3;
    public static final byte NGUA_DEN = 4;
    public static MountTemplate me;
    public static byte[] speed;
    public static byte[][] FRAME_VE_TRUOC;
    public static byte[][] DY_CHAR_STAND;
    public static byte[][] DY_CHAR_MOVE;
    public static byte[][] dx;
    public static byte[] Arr_Fly;
    public static byte[][] dy;
    public static byte[][] FRAME_MOVE_LR;
    public static byte[][] FRAME_MOVE_DOWN;
    public static byte[][] FRAME_MOVE_UP;

    public static MountTemplate gI() {
        return me == null ? (me = new MountTemplate()) : me;
    }
}

