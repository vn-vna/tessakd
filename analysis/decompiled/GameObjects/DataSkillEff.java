/*
 * Decompiled with CFR 0.152.
 */
package GameObjects;

import CLib.mGraphics;
import CLib.mHashtable;
import CLib.mImage;
import CLib.mSystem;
import CLib.mVector;
import GameObjects.EffectData;
import GameObjects.FrameEff;
import GameObjects.GameData;
import GameObjects.ImageIcon;
import GameObjects.PartFrame;
import GameObjects.SmallImage;
import Main.GameCanvas;
import Model.CRes;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.util.Enumeration;

public class DataSkillEff {
    public boolean isMatna;
    public mVector listFrame = new mVector();
    public mVector listAnima = new mVector();
    public SmallImage[] smallImage;
    public byte[][] frameChar = new byte[][]{{0}, {0}, {0}, {0}};
    public byte[] sequence;
    public byte Frame;
    public byte f;
    public static byte TYPE_EFFECT_END = 0;
    public static byte TYPE_EFFECT_STARTSKILL = 1;
    public static byte TYPE_EFFECT_BUFF = (byte)2;
    public boolean wantDetroy;
    public short x;
    public short y;
    public long timeRemove;
    public byte indexStartSkill;
    int idEff = 0;
    int dxx;
    int dyy;
    public int typRequestImg = 112;
    public byte Typemove;
    public boolean isremovebyTime;
    public boolean isremovebyFrame;
    private boolean isLoadData;
    int transform;
    mImage img_pp;
    public static mHashtable ALL_DATA_EFFECT = new mHashtable();
    public static final int TRANS_MIRROR = 2;
    public static final int TRANS_MIRROR_ROT180 = 1;
    public static final int TRANS_MIRROR_ROT270 = 4;
    public static final int TRANS_MIRROR_ROT90 = 7;
    public static final int TRANS_NONE = 0;
    public static final int TRANS_ROT180 = 3;
    public static final int TRANS_ROT270 = 6;
    public static final int TRANS_ROT90 = 5;
    private byte loop;
    private long lasttime;
    public long timelive;

    public DataSkillEff(byte[] byArray, short s, int n, int n2) {
        this.idEff = s;
        this.dxx = n;
        this.dyy = n2;
        this.loadData(byArray);
    }

    public DataSkillEff(byte[] byArray, short s, int n, int n2, long l, byte by) {
        this.idEff = s;
        this.dxx = n;
        this.dyy = n2;
        this.Typemove = by;
        this.timelive = l;
        this.isremovebyTime = true;
        this.loadData(byArray);
    }

    public DataSkillEff(short s, int n, int n2, byte[] byArray) {
        this.idEff = s;
        this.x = (short)n;
        this.y = (short)n2;
        this.loadData(byArray);
        this.isremovebyFrame = true;
    }

    public DataSkillEff(int n) {
        this.idEff = n;
    }

    public DataSkillEff(int n, int n2) {
        this.idEff = n;
        try {
            DataInputStream dataInputStream = mImage.openFile("/x1/wp/e" + n);
            DataInputStream dataInputStream2 = new DataInputStream(dataInputStream);
            byte[] byArray = new byte[dataInputStream2.available()];
            dataInputStream2.read(byArray);
            this.loadData(byArray);
            this.img_pp = mImage.createImage("/wp/e" + n + ".png");
        }
        catch (Exception exception) {
            mSystem.println("loi load pp " + exception.toString());
        }
        this.transform = n2;
    }

    public boolean isHavedata() {
        if (this.isLoadData) {
            return true;
        }
        this.loadData(null);
        return false;
    }

    public void loadData(byte[] byArray) {
        try {
            Object object;
            if (byArray == null || byArray.length == 0) {
                object = (EffectData)ALL_DATA_EFFECT.get("" + (this.idEff + GameData.ID_START_SKILL));
                if (object != null && ((EffectData)object).data != null) {
                    byArray = ((EffectData)object).data;
                    ((EffectData)object).timeRemove = GameCanvas.timeNow;
                }
                if (object == null) {
                    object = new EffectData((short)(this.idEff + GameData.ID_START_SKILL));
                    ALL_DATA_EFFECT.put("" + (this.idEff + GameData.ID_START_SKILL), object);
                    GameData.RequestImgandData((short)(this.idEff + GameData.ID_START_SKILL), this.typRequestImg);
                    ((EffectData)object).timeRemove = (int)(mSystem.currentTimeMillis() / 1000L);
                }
            }
            if (byArray != null && byArray.length > 0) {
                int n;
                int n2;
                int n3;
                object = new DataInputStream(new ByteArrayInputStream(byArray));
                int n4 = ((DataInputStream)object).readByte();
                this.smallImage = new SmallImage[n4];
                for (n3 = 0; n3 < n4; ++n3) {
                    this.smallImage[n3] = new SmallImage(((DataInputStream)object).readUnsignedByte(), ((DataInputStream)object).readUnsignedByte(), ((DataInputStream)object).readUnsignedByte(), ((DataInputStream)object).readUnsignedByte(), ((DataInputStream)object).readUnsignedByte());
                }
                n3 = ((DataInputStream)object).readShort();
                for (n2 = 0; n2 < n3; ++n2) {
                    n = ((DataInputStream)object).readByte();
                    mVector mVector2 = new mVector();
                    mVector mVector3 = new mVector();
                    for (int i = 0; i < n; ++i) {
                        PartFrame partFrame = new PartFrame(((DataInputStream)object).readShort(), ((DataInputStream)object).readShort(), ((DataInputStream)object).readByte());
                        partFrame.flip = ((DataInputStream)object).readByte();
                        partFrame.onTop = ((DataInputStream)object).readByte();
                        if (partFrame.onTop == 0) {
                            mVector2.addElement(partFrame);
                            continue;
                        }
                        mVector3.addElement(partFrame);
                    }
                    this.listFrame.addElement(new FrameEff(mVector2, mVector3));
                }
                n2 = (short)((DataInputStream)object).readUnsignedByte();
                this.sequence = new byte[n2];
                for (n = 0; n < n2; ++n) {
                    this.sequence[n] = (byte)((DataInputStream)object).readShort();
                }
                this.indexStartSkill = ((DataInputStream)object).readByte();
                n2 = ((DataInputStream)object).readByte();
                this.frameChar[0] = new byte[n2];
                for (n = 0; n < n2; ++n) {
                    this.frameChar[0][n] = ((DataInputStream)object).readByte();
                }
                n2 = ((DataInputStream)object).readByte();
                this.frameChar[1] = new byte[n2];
                for (n = 0; n < n2; ++n) {
                    this.frameChar[1][n] = ((DataInputStream)object).readByte();
                }
                n2 = ((DataInputStream)object).readByte();
                this.frameChar[3] = new byte[n2];
                for (n = 0; n < n2; ++n) {
                    this.frameChar[3][n] = ((DataInputStream)object).readByte();
                }
                this.isLoadData = true;
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public static int[] getTransform(int n, int n2, int n3, int n4) {
        int n5 = n;
        int n6 = n3;
        int n7 = n4;
        if (n5 == 0) {
            return new int[]{n2, n3, n4};
        }
        if (n2 == 0) {
            n5 = 2;
            n6 = n3;
            n7 = n4;
        } else if (n2 == 2) {
            n5 = 0;
            n6 = -n3;
        } else if (n2 == 1) {
            n5 = 3;
            n7 = -n4;
        } else if (n2 == 4) {
            n5 = 5;
            n6 = -n3;
            n7 = -n4;
        } else if (n2 == 7) {
            n5 = 2;
            n6 = n3;
            n7 = n4;
        } else if (n2 == 3) {
            n5 = 5;
            n7 = -n4;
            n6 = -n3;
        } else if (n2 == 6) {
            n5 = 0;
            n6 = -n3;
        } else if (n2 == 5) {
            n5 = 3;
            n7 = -n4;
        }
        return new int[]{n5, n6, n7};
    }

    public static int getWidthRotate(int n, int n2, int n3) {
        switch (n) {
            case 0: {
                return n2;
            }
            case 1: {
                return n2;
            }
            case 4: {
                return n3;
            }
            case 7: {
                return n3;
            }
            case 3: {
                return n2;
            }
            case 5: {
                return n3;
            }
            case 6: {
                return n3;
            }
            case 2: {
                return n2;
            }
        }
        return n2;
    }

    public static int getHeightRotate(int n, int n2, int n3) {
        switch (n) {
            case 0: {
                return n3;
            }
            case 1: {
                return n3;
            }
            case 4: {
                return n2;
            }
            case 7: {
                return n2;
            }
            case 3: {
                return n3;
            }
            case 5: {
                return n2;
            }
            case 6: {
                return n2;
            }
            case 2: {
                return n3;
            }
        }
        return n3;
    }

    public void paintTopArrow(mGraphics mGraphics2, int n, int n2) {
        if (!this.isHavedata()) {
            return;
        }
        if (this.Frame < this.listFrame.size()) {
            FrameEff frameEff = (FrameEff)this.listFrame.elementAt(this.Frame);
            try {
                mVector mVector2 = frameEff.listPartTop;
                mVector2 = frameEff.listPartBottom;
                for (int i = 0; i < mVector2.size(); ++i) {
                    PartFrame partFrame = (PartFrame)mVector2.elementAt(i);
                    SmallImage smallImage = this.smallImage[partFrame.idSmallImg];
                    if (this.img_pp == null) continue;
                    int n3 = partFrame.dx;
                    int n4 = smallImage.w;
                    int n5 = smallImage.h;
                    short s = smallImage.x;
                    short s2 = smallImage.y;
                    int[] nArray = DataSkillEff.getTransform(partFrame.flip, this.transform, partFrame.dx, partFrame.dy);
                    n4 = DataSkillEff.getWidthRotate(nArray[0], smallImage.w, smallImage.h);
                    n5 = DataSkillEff.getHeightRotate(nArray[0], smallImage.w, smallImage.h);
                    int n6 = mImage.getImageWidth(this.img_pp.image);
                    int n7 = mImage.getImageHeight(this.img_pp.image);
                    if (s > n6) {
                        s = 0;
                    }
                    if (s2 > n7) {
                        s2 = 0;
                    }
                    if (s + n4 > n6) {
                        n4 = n6 - s;
                    }
                    if (s2 + n5 > n7) {
                        n5 = n7 - s2;
                    }
                    n3 = nArray[1];
                    mGraphics2.drawRegion(this.img_pp, s, s2, n4, n5, nArray[0], n + n3 + this.dxx, n2 + nArray[2] + this.dyy, 0, false);
                }
            }
            catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    public void paintTop(mGraphics mGraphics2, int n, int n2) {
        if (!this.isHavedata()) {
            return;
        }
        if (this.Frame < this.listFrame.size()) {
            FrameEff frameEff = (FrameEff)this.listFrame.elementAt(this.Frame);
            try {
                mVector mVector2 = frameEff.listPartTop;
                mVector2 = frameEff.listPartBottom;
                for (int i = 0; i < mVector2.size(); ++i) {
                    PartFrame partFrame = (PartFrame)mVector2.elementAt(i);
                    SmallImage smallImage = this.smallImage[partFrame.idSmallImg];
                    ImageIcon imageIcon = GameData.getImgIcon((short)(this.idEff + GameData.ID_START_SKILL), this.typRequestImg);
                    if (imageIcon == null || imageIcon.img == null) continue;
                    short s = partFrame.dx;
                    int n3 = smallImage.w;
                    int n4 = smallImage.h;
                    short s2 = smallImage.x;
                    short s3 = smallImage.y;
                    int n5 = mImage.getImageWidth(imageIcon.img.image);
                    int n6 = mImage.getImageHeight(imageIcon.img.image);
                    if (s2 > n5) {
                        s2 = 0;
                    }
                    if (s3 > n6) {
                        s3 = 0;
                    }
                    if (s2 + n3 > n5) {
                        n3 = n5 - s2;
                    }
                    if (s3 + n4 > n6) {
                        n4 = n6 - s3;
                    }
                    mGraphics2.drawRegion(imageIcon.img, s2, s3, n3, n4, partFrame.flip == 1 ? 2 : 0, n + s + this.dxx, n2 + partFrame.dy + this.dyy, 0, false);
                }
            }
            catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    public void paintBottomArrow(mGraphics mGraphics2, int n, int n2) {
        if (!this.isHavedata()) {
            return;
        }
        if (this.Frame < this.listFrame.size()) {
            FrameEff frameEff = (FrameEff)this.listFrame.elementAt(this.Frame);
            try {
                mVector mVector2 = frameEff.listPartTop;
                for (int i = 0; i < mVector2.size(); ++i) {
                    PartFrame partFrame = (PartFrame)mVector2.elementAt(i);
                    SmallImage smallImage = this.smallImage[partFrame.idSmallImg];
                    ImageIcon imageIcon = GameData.getImgIcon((short)(this.idEff + GameData.ID_START_SKILL), this.typRequestImg);
                    if (this.img_pp == null) continue;
                    int n3 = partFrame.dx;
                    int n4 = smallImage.w;
                    int n5 = smallImage.h;
                    short s = smallImage.x;
                    short s2 = smallImage.y;
                    int[] nArray = DataSkillEff.getTransform(partFrame.flip, this.transform, partFrame.dx, partFrame.dy);
                    n4 = DataSkillEff.getWidthRotate(nArray[0], smallImage.w, smallImage.h);
                    n5 = DataSkillEff.getHeightRotate(nArray[0], smallImage.w, smallImage.h);
                    int n6 = mImage.getImageWidth(this.img_pp.image);
                    int n7 = mImage.getImageHeight(this.img_pp.image);
                    if (s > n6) {
                        s = 0;
                    }
                    if (s2 > n7) {
                        s2 = 0;
                    }
                    if (s + n4 > n6) {
                        n4 = n6 - s;
                    }
                    if (s2 + n5 > n7) {
                        n5 = n7 - s2;
                    }
                    n3 = nArray[1];
                    mGraphics2.drawRegion(this.img_pp, s, s2, n4, n5, nArray[0], n + n3 + this.dxx, n2 + nArray[2] + this.dyy, 0, false);
                }
            }
            catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    public void paintBottom(mGraphics mGraphics2, int n, int n2) {
        if (!this.isHavedata()) {
            return;
        }
        if (this.Frame < this.listFrame.size()) {
            FrameEff frameEff = (FrameEff)this.listFrame.elementAt(this.Frame);
            try {
                mVector mVector2 = frameEff.listPartTop;
                for (int i = 0; i < mVector2.size(); ++i) {
                    PartFrame partFrame = (PartFrame)mVector2.elementAt(i);
                    SmallImage smallImage = this.smallImage[partFrame.idSmallImg];
                    ImageIcon imageIcon = GameData.getImgIcon((short)(this.idEff + GameData.ID_START_SKILL), this.typRequestImg);
                    if (imageIcon == null || imageIcon.img == null) continue;
                    short s = partFrame.dx;
                    int n3 = smallImage.w;
                    int n4 = smallImage.h;
                    short s2 = smallImage.x;
                    short s3 = smallImage.y;
                    int n5 = mImage.getImageWidth(imageIcon.img.image);
                    int n6 = mImage.getImageHeight(imageIcon.img.image);
                    if (s2 > n5) {
                        s2 = 0;
                    }
                    if (s3 > n6) {
                        s3 = 0;
                    }
                    if (s2 + n3 > n5) {
                        n3 = n5 - s2;
                    }
                    if (s3 + n4 > n6) {
                        n4 = n6 - s3;
                    }
                    mGraphics2.drawRegion(imageIcon.img, s2, s3, n3, n4, partFrame.flip == 1 ? 2 : 0, n + s + this.dxx, n2 + partFrame.dy + this.dyy, 0, false);
                }
            }
            catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    public void paintTopWeaPon(mGraphics mGraphics2, int n, int n2, int n3, int n4) {
        if (!this.isHavedata()) {
            return;
        }
        if (n3 < this.listFrame.size()) {
            FrameEff frameEff = (FrameEff)this.listFrame.elementAt(n3);
            try {
                mVector mVector2 = frameEff.listPartTop;
                for (int i = 0; i < mVector2.size(); ++i) {
                    int n5;
                    PartFrame partFrame = (PartFrame)mVector2.elementAt(i);
                    SmallImage smallImage = this.smallImage[partFrame.idSmallImg];
                    ImageIcon imageIcon = GameData.getImgIcon((short)(this.idEff + GameData.ID_START_SKILL), this.typRequestImg);
                    if (imageIcon == null || imageIcon.img == null) continue;
                    int n6 = partFrame.dx;
                    int n7 = smallImage.w;
                    int n8 = smallImage.h;
                    short s = smallImage.x;
                    short s2 = smallImage.y;
                    int n9 = mImage.getImageWidth(imageIcon.img.image);
                    int n10 = mImage.getImageHeight(imageIcon.img.image);
                    if (s > n9) {
                        s = 0;
                    }
                    if (s2 > n10) {
                        s2 = 0;
                    }
                    if (s + n7 > n9) {
                        n7 = n9 - s;
                    }
                    if (s2 + n8 > n10) {
                        n8 = n10 - s2;
                    }
                    int n11 = n5 = partFrame.flip == 1 ? 2 : 0;
                    if (n4 == 2 || n4 == 6) {
                        n5 = n5 == 2 ? 0 : 2;
                        n6 = -(n6 + n7);
                    }
                    mGraphics2.drawRegion(imageIcon.img, s, s2, n7, n8, n5, n + n6 + this.dxx, n2 + partFrame.dy + this.dyy, 0, false);
                }
            }
            catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    public void paintTopHorse(mGraphics mGraphics2, int n, int n2, int n3, int n4) {
        if (!this.isHavedata()) {
            return;
        }
        if (n3 < this.listFrame.size()) {
            FrameEff frameEff = (FrameEff)this.listFrame.elementAt(n3);
            try {
                mVector mVector2 = frameEff.listPartTop;
                for (int i = 0; i < mVector2.size(); ++i) {
                    int n5;
                    PartFrame partFrame = (PartFrame)mVector2.elementAt(i);
                    SmallImage smallImage = this.smallImage[partFrame.idSmallImg];
                    ImageIcon imageIcon = GameData.getImgIcon((short)(this.idEff + GameData.ID_START_SKILL), this.typRequestImg);
                    if (imageIcon == null || imageIcon.img == null) continue;
                    int n6 = partFrame.dx;
                    int n7 = smallImage.w;
                    int n8 = smallImage.h;
                    short s = smallImage.x;
                    short s2 = smallImage.y;
                    int n9 = mImage.getImageWidth(imageIcon.img.image);
                    int n10 = mImage.getImageHeight(imageIcon.img.image);
                    if (s > n9) {
                        s = 0;
                    }
                    if (s2 > n10) {
                        s2 = 0;
                    }
                    if (s + n7 > n9) {
                        n7 = n9 - s;
                    }
                    if (s2 + n8 > n10) {
                        n8 = n10 - s2;
                    }
                    int n11 = n5 = partFrame.flip == 1 ? 2 : 0;
                    if (n4 == 2 || n4 == 6) {
                        n5 = n5 == 2 ? 0 : 2;
                        n6 = -(n6 + n7);
                    }
                    mGraphics2.drawRegion(imageIcon.img, s, s2, n7, n8, n5, n + n6 + this.dxx, n2 + partFrame.dy + this.dyy, 0, false);
                }
            }
            catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    public void paintTopPP(mGraphics mGraphics2, int n, int n2, int n3, int n4) {
        if (!this.isHavedata()) {
            return;
        }
        if (n3 < this.listFrame.size()) {
            FrameEff frameEff = (FrameEff)this.listFrame.elementAt(n3);
            try {
                mVector mVector2 = frameEff.listPartTop;
                for (int i = 0; i < mVector2.size(); ++i) {
                    int n5;
                    PartFrame partFrame = (PartFrame)mVector2.elementAt(i);
                    SmallImage smallImage = this.smallImage[partFrame.idSmallImg];
                    ImageIcon imageIcon = GameData.getImgIcon((short)(this.idEff + GameData.ID_START_SKILL), this.typRequestImg);
                    if (imageIcon == null || imageIcon.img == null) continue;
                    int n6 = partFrame.dx;
                    int n7 = smallImage.w;
                    int n8 = smallImage.h;
                    short s = smallImage.x;
                    short s2 = smallImage.y;
                    int n9 = mImage.getImageWidth(imageIcon.img.image);
                    int n10 = mImage.getImageHeight(imageIcon.img.image);
                    if (s > n9) {
                        s = 0;
                    }
                    if (s2 > n10) {
                        s2 = 0;
                    }
                    if (s + n7 > n9) {
                        n7 = n9 - s;
                    }
                    if (s2 + n8 > n10) {
                        n8 = n10 - s2;
                    }
                    int n11 = n5 = partFrame.flip == 1 ? 2 : 0;
                    if (n4 == 2 || n4 == 6) {
                        n5 = n5 == 2 ? 0 : 2;
                        n6 = -(n6 + n7);
                    }
                    mGraphics2.drawRegion(imageIcon.img, s, s2, n7, n8, n5, n + n6 + this.dxx, n2 + partFrame.dy + this.dyy, 0, false);
                }
            }
            catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    public void paintTop(mGraphics mGraphics2, int n, int n2, int n3, int n4) {
        if (!this.isHavedata()) {
            return;
        }
        if (n3 < this.listFrame.size()) {
            FrameEff frameEff = (FrameEff)this.listFrame.elementAt(n3);
            try {
                mVector mVector2 = frameEff.listPartTop;
                for (int i = 0; i < mVector2.size(); ++i) {
                    int n5;
                    PartFrame partFrame = (PartFrame)mVector2.elementAt(i);
                    SmallImage smallImage = this.smallImage[partFrame.idSmallImg];
                    ImageIcon imageIcon = GameData.getImgIcon((short)(this.idEff + GameData.ID_START_SKILL), this.typRequestImg);
                    if (imageIcon == null || imageIcon.img == null) continue;
                    int n6 = partFrame.dx;
                    int n7 = smallImage.w;
                    int n8 = smallImage.h;
                    short s = smallImage.x;
                    short s2 = smallImage.y;
                    int n9 = mImage.getImageWidth(imageIcon.img.image);
                    int n10 = mImage.getImageHeight(imageIcon.img.image);
                    if (s > n9) {
                        s = 0;
                    }
                    if (s2 > n10) {
                        s2 = 0;
                    }
                    if (s + n7 > n9) {
                        n7 = n9 - s;
                    }
                    if (s2 + n8 > n10) {
                        n8 = n10 - s2;
                    }
                    int n11 = n5 = partFrame.flip == 1 ? 2 : 0;
                    if (n4 == 2 || n4 == 6) {
                        n5 = n5 == 2 ? 0 : 2;
                        n6 = -(n6 + n7);
                    }
                    mGraphics2.drawRegion(imageIcon.img, s, s2, n7, n8, n5, n + n6 + this.dxx, n2 + partFrame.dy + this.dyy, 0, false);
                }
            }
            catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    public void paintTopAll(mGraphics mGraphics2, int n, int n2, int n3, int n4) {
        if (!this.isHavedata()) {
            return;
        }
        if (n3 < this.listFrame.size()) {
            FrameEff frameEff = (FrameEff)this.listFrame.elementAt(n3);
            try {
                mVector mVector2 = frameEff.listPartTop;
                for (int i = 0; i < mVector2.size(); ++i) {
                    int n5;
                    PartFrame partFrame = (PartFrame)mVector2.elementAt(i);
                    SmallImage smallImage = this.smallImage[partFrame.idSmallImg];
                    ImageIcon imageIcon = GameData.getImgIcon((short)(this.idEff + GameData.ID_START_SKILL), this.typRequestImg);
                    if (imageIcon == null || imageIcon.img == null) continue;
                    int n6 = partFrame.dx;
                    int n7 = smallImage.w;
                    int n8 = smallImage.h;
                    short s = smallImage.x;
                    short s2 = smallImage.y;
                    int n9 = mImage.getImageWidth(imageIcon.img.image);
                    int n10 = mImage.getImageHeight(imageIcon.img.image);
                    if (s > n9) {
                        s = 0;
                    }
                    if (s2 > n10) {
                        s2 = 0;
                    }
                    if (s + n7 > n9) {
                        n7 = n9 - s;
                    }
                    if (s2 + n8 > n10) {
                        n8 = n10 - s2;
                    }
                    int n11 = n5 = partFrame.flip == 1 ? 2 : 0;
                    if (n4 == 2 || n4 == 6) {
                        n5 = n5 == 2 ? 0 : 2;
                        n6 = -(n6 + n7);
                    }
                    mGraphics2.drawRegion(imageIcon.img, s, s2, n7, n8, n5, n + n6 + this.dxx, n2 + partFrame.dy + this.dyy, 0, false);
                }
            }
            catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    public void paintTopHair(mGraphics mGraphics2, int n, int n2, int n3, int n4) {
        if (!this.isHavedata()) {
            return;
        }
        if (n3 < this.listFrame.size()) {
            FrameEff frameEff = (FrameEff)this.listFrame.elementAt(n3);
            try {
                mVector mVector2 = frameEff.listPartTop;
                for (int i = 0; i < mVector2.size(); ++i) {
                    int n5;
                    PartFrame partFrame = (PartFrame)mVector2.elementAt(i);
                    SmallImage smallImage = this.smallImage[partFrame.idSmallImg];
                    ImageIcon imageIcon = GameData.getImgIcon((short)(this.idEff + GameData.ID_START_SKILL), this.typRequestImg);
                    if (imageIcon == null || imageIcon.img == null) continue;
                    int n6 = partFrame.dx;
                    int n7 = smallImage.w;
                    int n8 = smallImage.h;
                    short s = smallImage.x;
                    short s2 = smallImage.y;
                    int n9 = mImage.getImageWidth(imageIcon.img.image);
                    int n10 = mImage.getImageHeight(imageIcon.img.image);
                    if (s > n9) {
                        s = 0;
                    }
                    if (s2 > n10) {
                        s2 = 0;
                    }
                    if (s + n7 > n9) {
                        n7 = n9 - s;
                    }
                    if (s2 + n8 > n10) {
                        n8 = n10 - s2;
                    }
                    int n11 = n5 = partFrame.flip == 1 ? 2 : 0;
                    if (n4 == 2 || n4 == 6) {
                        n5 = n5 == 2 ? 0 : 2;
                        n6 = -(n6 + n7);
                    }
                    mGraphics2.drawRegion(imageIcon.img, s, s2, n7, n8, n5, n + n6 + this.dxx, n2 + partFrame.dy + this.dyy, 0, false);
                }
            }
            catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    public void paintTopName(mGraphics mGraphics2, int n, int n2, int n3, int n4) {
        if (!this.isHavedata()) {
            return;
        }
        if (n3 < this.listFrame.size()) {
            FrameEff frameEff = (FrameEff)this.listFrame.elementAt(n3);
            try {
                mVector mVector2 = frameEff.listPartTop;
                for (int i = 0; i < mVector2.size(); ++i) {
                    int n5;
                    PartFrame partFrame = (PartFrame)mVector2.elementAt(i);
                    SmallImage smallImage = this.smallImage[partFrame.idSmallImg];
                    ImageIcon imageIcon = GameData.getImgIcon((short)(this.idEff + GameData.ID_START_SKILL), this.typRequestImg);
                    if (imageIcon == null || imageIcon.img == null) continue;
                    int n6 = partFrame.dx;
                    int n7 = smallImage.w;
                    int n8 = smallImage.h;
                    short s = smallImage.x;
                    short s2 = smallImage.y;
                    int n9 = mImage.getImageWidth(imageIcon.img.image);
                    int n10 = mImage.getImageHeight(imageIcon.img.image);
                    if (s > n9) {
                        s = 0;
                    }
                    if (s2 > n10) {
                        s2 = 0;
                    }
                    if (s + n7 > n9) {
                        n7 = n9 - s;
                    }
                    if (s2 + n8 > n10) {
                        n8 = n10 - s2;
                    }
                    int n11 = n5 = partFrame.flip == 1 ? 2 : 0;
                    if (n4 == 2 || n4 == 6) {
                        n5 = n5 == 2 ? 0 : 2;
                        n6 = -(n6 + n7);
                    }
                    mGraphics2.drawRegion(imageIcon.img, s, s2, n7, n8, n5, n + n6 + this.dxx, n2 + partFrame.dy + this.dyy, 0, false);
                }
            }
            catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    public void paintTopWing(mGraphics mGraphics2, int n, int n2, int n3, int n4) {
        if (!this.isHavedata()) {
            return;
        }
        if (n3 < this.listFrame.size()) {
            FrameEff frameEff = (FrameEff)this.listFrame.elementAt(n3);
            try {
                mVector mVector2 = frameEff.listPartTop;
                for (int i = 0; i < mVector2.size(); ++i) {
                    int n5;
                    PartFrame partFrame = (PartFrame)mVector2.elementAt(i);
                    SmallImage smallImage = this.smallImage[partFrame.idSmallImg];
                    ImageIcon imageIcon = GameData.getImgIcon((short)(this.idEff + GameData.ID_START_SKILL), this.typRequestImg);
                    if (imageIcon == null || imageIcon.img == null) continue;
                    int n6 = partFrame.dx;
                    int n7 = smallImage.w;
                    int n8 = smallImage.h;
                    short s = smallImage.x;
                    short s2 = smallImage.y;
                    int n9 = mImage.getImageWidth(imageIcon.img.image);
                    int n10 = mImage.getImageHeight(imageIcon.img.image);
                    if (s > n9) {
                        s = 0;
                    }
                    if (s2 > n10) {
                        s2 = 0;
                    }
                    if (s + n7 > n9) {
                        n7 = n9 - s;
                    }
                    if (s2 + n8 > n10) {
                        n8 = n10 - s2;
                    }
                    int n11 = n5 = partFrame.flip == 1 ? 2 : 0;
                    if (n4 == 2 || n4 == 6) {
                        n5 = n5 == 2 ? 0 : 2;
                        n6 = -(n6 + n7);
                    }
                    mGraphics2.drawRegion(imageIcon.img, s, s2, n7, n8, n5, n + n6 + this.dxx, n2 + partFrame.dy + this.dyy, 0, false);
                }
            }
            catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    public void paintBottomWeaPon(mGraphics mGraphics2, int n, int n2, int n3, int n4) {
        if (!this.isHavedata()) {
            return;
        }
        if (n3 < this.listFrame.size()) {
            FrameEff frameEff = (FrameEff)this.listFrame.elementAt(n3);
            try {
                mVector mVector2 = frameEff.listPartBottom;
                for (int i = 0; i < mVector2.size(); ++i) {
                    int n5;
                    PartFrame partFrame = (PartFrame)mVector2.elementAt(i);
                    SmallImage smallImage = this.smallImage[partFrame.idSmallImg];
                    ImageIcon imageIcon = GameData.getImgIcon((short)(this.idEff + GameData.ID_START_SKILL), this.typRequestImg);
                    if (imageIcon == null || imageIcon.img == null) continue;
                    int n6 = partFrame.dx;
                    int n7 = smallImage.w;
                    int n8 = smallImage.h;
                    short s = smallImage.x;
                    short s2 = smallImage.y;
                    int n9 = mImage.getImageWidth(imageIcon.img.image);
                    int n10 = mImage.getImageHeight(imageIcon.img.image);
                    if (s > n9) {
                        s = 0;
                    }
                    if (s2 > n10) {
                        s2 = 0;
                    }
                    if (s + n7 > n9) {
                        n7 = n9 - s;
                    }
                    if (s2 + n8 > n10) {
                        n8 = n10 - s2;
                    }
                    int n11 = n5 = partFrame.flip == 1 ? 2 : 0;
                    if (n4 == 2 || n4 == 6) {
                        n5 = n5 == 2 ? 0 : 2;
                        n6 = -(n6 + n7);
                    }
                    mGraphics2.drawRegion(imageIcon.img, s, s2, n7, n8, n5, n + n6 + this.dxx, n2 + partFrame.dy + this.dyy, 0, false);
                }
            }
            catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    public void paintBottomHorse(mGraphics mGraphics2, int n, int n2, int n3, int n4) {
        if (!this.isHavedata()) {
            return;
        }
        if (n3 < this.listFrame.size()) {
            FrameEff frameEff = (FrameEff)this.listFrame.elementAt(n3);
            try {
                mVector mVector2 = frameEff.listPartBottom;
                for (int i = 0; i < mVector2.size(); ++i) {
                    int n5;
                    PartFrame partFrame = (PartFrame)mVector2.elementAt(i);
                    SmallImage smallImage = this.smallImage[partFrame.idSmallImg];
                    ImageIcon imageIcon = GameData.getImgIcon((short)(this.idEff + GameData.ID_START_SKILL), this.typRequestImg);
                    if (imageIcon == null || imageIcon.img == null) continue;
                    int n6 = partFrame.dx;
                    int n7 = smallImage.w;
                    int n8 = smallImage.h;
                    short s = smallImage.x;
                    short s2 = smallImage.y;
                    int n9 = mImage.getImageWidth(imageIcon.img.image);
                    int n10 = mImage.getImageHeight(imageIcon.img.image);
                    if (s > n9) {
                        s = 0;
                    }
                    if (s2 > n10) {
                        s2 = 0;
                    }
                    if (s + n7 > n9) {
                        n7 = n9 - s;
                    }
                    if (s2 + n8 > n10) {
                        n8 = n10 - s2;
                    }
                    int n11 = n5 = partFrame.flip == 1 ? 2 : 0;
                    if (n4 == 2 || n4 == 6) {
                        n5 = n5 == 2 ? 0 : 2;
                        n6 = -(n6 + n7);
                    }
                    mGraphics2.drawRegion(imageIcon.img, s, s2, n7, n8, n5, n + n6 + this.dxx, n2 + partFrame.dy + this.dyy, 0, false);
                }
            }
            catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    public void paintBottomPP(mGraphics mGraphics2, int n, int n2, int n3, int n4) {
        if (!this.isHavedata()) {
            return;
        }
        if (n3 < this.listFrame.size()) {
            FrameEff frameEff = (FrameEff)this.listFrame.elementAt(n3);
            try {
                mVector mVector2 = frameEff.listPartBottom;
                for (int i = 0; i < mVector2.size(); ++i) {
                    int n5;
                    PartFrame partFrame = (PartFrame)mVector2.elementAt(i);
                    SmallImage smallImage = this.smallImage[partFrame.idSmallImg];
                    ImageIcon imageIcon = GameData.getImgIcon((short)(this.idEff + GameData.ID_START_SKILL), this.typRequestImg);
                    if (imageIcon == null || imageIcon.img == null) continue;
                    int n6 = partFrame.dx;
                    int n7 = smallImage.w;
                    int n8 = smallImage.h;
                    short s = smallImage.x;
                    short s2 = smallImage.y;
                    int n9 = mImage.getImageWidth(imageIcon.img.image);
                    int n10 = mImage.getImageHeight(imageIcon.img.image);
                    if (s > n9) {
                        s = 0;
                    }
                    if (s2 > n10) {
                        s2 = 0;
                    }
                    if (s + n7 > n9) {
                        n7 = n9 - s;
                    }
                    if (s2 + n8 > n10) {
                        n8 = n10 - s2;
                    }
                    int n11 = n5 = partFrame.flip == 1 ? 2 : 0;
                    if (n4 == 2 || n4 == 6) {
                        n5 = n5 == 2 ? 0 : 2;
                        n6 = -(n6 + n7);
                    }
                    mGraphics2.drawRegion(imageIcon.img, s, s2, n7, n8, n5, n + n6 + this.dxx, n2 + partFrame.dy + this.dyy, 0, false);
                }
            }
            catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    public void paintBottom(mGraphics mGraphics2, int n, int n2, int n3, int n4) {
        if (!this.isHavedata()) {
            return;
        }
        if (n3 < this.listFrame.size()) {
            FrameEff frameEff = (FrameEff)this.listFrame.elementAt(n3);
            try {
                mVector mVector2 = frameEff.listPartBottom;
                for (int i = 0; i < mVector2.size(); ++i) {
                    int n5;
                    PartFrame partFrame = (PartFrame)mVector2.elementAt(i);
                    SmallImage smallImage = this.smallImage[partFrame.idSmallImg];
                    ImageIcon imageIcon = GameData.getImgIcon((short)(this.idEff + GameData.ID_START_SKILL), this.typRequestImg);
                    if (imageIcon == null || imageIcon.img == null) continue;
                    int n6 = partFrame.dx;
                    int n7 = smallImage.w;
                    int n8 = smallImage.h;
                    short s = smallImage.x;
                    short s2 = smallImage.y;
                    int n9 = mImage.getImageWidth(imageIcon.img.image);
                    int n10 = mImage.getImageHeight(imageIcon.img.image);
                    if (s > n9) {
                        s = 0;
                    }
                    if (s2 > n10) {
                        s2 = 0;
                    }
                    if (s + n7 > n9) {
                        n7 = n9 - s;
                    }
                    if (s2 + n8 > n10) {
                        n8 = n10 - s2;
                    }
                    int n11 = n5 = partFrame.flip == 1 ? 2 : 0;
                    if (n4 == 2 || n4 == 6) {
                        n5 = n5 == 2 ? 0 : 2;
                        n6 = -(n6 + n7);
                    }
                    mGraphics2.drawRegion(imageIcon.img, s, s2, n7, n8, n5, n + n6 + this.dxx, n2 + partFrame.dy + this.dyy, 0, false);
                }
            }
            catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    public void paintBottomAll(mGraphics mGraphics2, int n, int n2, int n3, int n4) {
        if (!this.isHavedata()) {
            return;
        }
        if (n3 < this.listFrame.size()) {
            FrameEff frameEff = (FrameEff)this.listFrame.elementAt(n3);
            try {
                mVector mVector2 = frameEff.listPartBottom;
                for (int i = 0; i < mVector2.size(); ++i) {
                    int n5;
                    PartFrame partFrame = (PartFrame)mVector2.elementAt(i);
                    SmallImage smallImage = this.smallImage[partFrame.idSmallImg];
                    ImageIcon imageIcon = GameData.getImgIcon((short)(this.idEff + GameData.ID_START_SKILL), this.typRequestImg);
                    if (imageIcon == null || imageIcon.img == null) continue;
                    int n6 = partFrame.dx;
                    int n7 = smallImage.w;
                    int n8 = smallImage.h;
                    short s = smallImage.x;
                    short s2 = smallImage.y;
                    int n9 = mImage.getImageWidth(imageIcon.img.image);
                    int n10 = mImage.getImageHeight(imageIcon.img.image);
                    if (s > n9) {
                        s = 0;
                    }
                    if (s2 > n10) {
                        s2 = 0;
                    }
                    if (s + n7 > n9) {
                        n7 = n9 - s;
                    }
                    if (s2 + n8 > n10) {
                        n8 = n10 - s2;
                    }
                    int n11 = n5 = partFrame.flip == 1 ? 2 : 0;
                    if (n4 == 2 || n4 == 6) {
                        n5 = n5 == 2 ? 0 : 2;
                        n6 = -(n6 + n7);
                    }
                    mGraphics2.drawRegion(imageIcon.img, s, s2, n7, n8, n5, n + n6 + this.dxx, n2 + partFrame.dy + this.dyy, 0, false);
                }
            }
            catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    public void paintBottomHair(mGraphics mGraphics2, int n, int n2, int n3, int n4) {
        if (!this.isHavedata()) {
            return;
        }
        if (n3 < this.listFrame.size()) {
            FrameEff frameEff = (FrameEff)this.listFrame.elementAt(n3);
            try {
                mVector mVector2 = frameEff.listPartBottom;
                for (int i = 0; i < mVector2.size(); ++i) {
                    int n5;
                    PartFrame partFrame = (PartFrame)mVector2.elementAt(i);
                    SmallImage smallImage = this.smallImage[partFrame.idSmallImg];
                    ImageIcon imageIcon = GameData.getImgIcon((short)(this.idEff + GameData.ID_START_SKILL), this.typRequestImg);
                    if (imageIcon == null || imageIcon.img == null) continue;
                    int n6 = partFrame.dx;
                    int n7 = smallImage.w;
                    int n8 = smallImage.h;
                    short s = smallImage.x;
                    short s2 = smallImage.y;
                    int n9 = mImage.getImageWidth(imageIcon.img.image);
                    int n10 = mImage.getImageHeight(imageIcon.img.image);
                    if (s > n9) {
                        s = 0;
                    }
                    if (s2 > n10) {
                        s2 = 0;
                    }
                    if (s + n7 > n9) {
                        n7 = n9 - s;
                    }
                    if (s2 + n8 > n10) {
                        n8 = n10 - s2;
                    }
                    int n11 = n5 = partFrame.flip == 1 ? 2 : 0;
                    if (n4 == 2 || n4 == 6) {
                        n5 = n5 == 2 ? 0 : 2;
                        n6 = -(n6 + n7);
                    }
                    mGraphics2.drawRegion(imageIcon.img, s, s2, n7, n8, n5, n + n6 + this.dxx, n2 + partFrame.dy + this.dyy, 0, false);
                }
            }
            catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    public void paintBottomName(mGraphics mGraphics2, int n, int n2, int n3, int n4) {
        if (!this.isHavedata()) {
            return;
        }
        if (n3 < this.listFrame.size()) {
            FrameEff frameEff = (FrameEff)this.listFrame.elementAt(n3);
            try {
                mVector mVector2 = frameEff.listPartBottom;
                for (int i = 0; i < mVector2.size(); ++i) {
                    int n5;
                    PartFrame partFrame = (PartFrame)mVector2.elementAt(i);
                    SmallImage smallImage = this.smallImage[partFrame.idSmallImg];
                    ImageIcon imageIcon = GameData.getImgIcon((short)(this.idEff + GameData.ID_START_SKILL), this.typRequestImg);
                    if (imageIcon == null || imageIcon.img == null) continue;
                    int n6 = partFrame.dx;
                    int n7 = smallImage.w;
                    int n8 = smallImage.h;
                    short s = smallImage.x;
                    short s2 = smallImage.y;
                    int n9 = mImage.getImageWidth(imageIcon.img.image);
                    int n10 = mImage.getImageHeight(imageIcon.img.image);
                    if (s > n9) {
                        s = 0;
                    }
                    if (s2 > n10) {
                        s2 = 0;
                    }
                    if (s + n7 > n9) {
                        n7 = n9 - s;
                    }
                    if (s2 + n8 > n10) {
                        n8 = n10 - s2;
                    }
                    int n11 = n5 = partFrame.flip == 1 ? 2 : 0;
                    if (n4 == 2 || n4 == 6) {
                        n5 = n5 == 2 ? 0 : 2;
                        n6 = -(n6 + n7);
                    }
                    mGraphics2.drawRegion(imageIcon.img, s, s2, n7, n8, n5, n + n6 + this.dxx, n2 + partFrame.dy + this.dyy, 0, false);
                }
            }
            catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    public void paintBottomWing(mGraphics mGraphics2, int n, int n2, int n3, int n4) {
        if (!this.isHavedata()) {
            return;
        }
        if (n3 < this.listFrame.size()) {
            FrameEff frameEff = (FrameEff)this.listFrame.elementAt(n3);
            try {
                mVector mVector2 = frameEff.listPartBottom;
                for (int i = 0; i < mVector2.size(); ++i) {
                    int n5;
                    PartFrame partFrame = (PartFrame)mVector2.elementAt(i);
                    SmallImage smallImage = this.smallImage[partFrame.idSmallImg];
                    ImageIcon imageIcon = GameData.getImgIcon((short)(this.idEff + GameData.ID_START_SKILL), this.typRequestImg);
                    if (imageIcon == null || imageIcon.img == null) continue;
                    int n6 = partFrame.dx;
                    int n7 = smallImage.w;
                    int n8 = smallImage.h;
                    short s = smallImage.x;
                    short s2 = smallImage.y;
                    int n9 = mImage.getImageWidth(imageIcon.img.image);
                    int n10 = mImage.getImageHeight(imageIcon.img.image);
                    if (s > n9) {
                        s = 0;
                    }
                    if (s2 > n10) {
                        s2 = 0;
                    }
                    if (s + n7 > n9) {
                        n7 = n9 - s;
                    }
                    if (s2 + n8 > n10) {
                        n8 = n10 - s2;
                    }
                    int n11 = n5 = partFrame.flip == 1 ? 2 : 0;
                    if (n4 == 2 || n4 == 6) {
                        n5 = n5 == 2 ? 0 : 2;
                        n6 = -(n6 + n7);
                    }
                    mGraphics2.drawRegion(imageIcon.img, s, s2, n7, n8, n5, n + n6 + this.dxx, n2 + partFrame.dy + this.dyy, 0, false);
                }
            }
            catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    public boolean lockmoveByeffAuto() {
        return this.Typemove == 1;
    }

    public boolean CanpaintByeffauto() {
        return this.Typemove == 2;
    }

    public boolean isTanghinhbyEffauto() {
        return this.Typemove == 17;
    }

    public void updateArrow() {
        if (!this.isHavedata()) {
            return;
        }
        this.f = (byte)((this.f + 1) % this.sequence.length);
        this.Frame = this.sequence[this.f];
    }

    public void update() {
        try {
            if (!this.isHavedata()) {
                return;
            }
            if (this.isremovebyTime && this.timelive - mSystem.currentTimeMillis() < 0L) {
                this.wantDetroy = true;
            }
            this.f = (byte)(this.f + 1);
            if (this.f >= this.sequence.length) {
                if (this.isremovebyFrame) {
                    this.wantDetroy = true;
                }
                if (this.isremovebyTime) {
                    this.f = 0;
                }
                if (!this.isremovebyTime && mSystem.currentTimeMillis() - this.lasttime > (long)(this.loop * 1000)) {
                    this.f = 0;
                    this.lasttime = mSystem.currentTimeMillis();
                    this.loop = (byte)CRes.random(1, 8);
                }
            }
            if (this.f > 0 && this.f < this.sequence.length) {
                this.Frame = this.sequence[this.f];
            }
        }
        catch (Exception exception) {
            mSystem.println("loi update me day");
            exception.printStackTrace();
        }
    }

    public static void SetRemove() {
        Enumeration enumeration = ALL_DATA_EFFECT.keys();
        while (enumeration.hasMoreElements()) {
            String string = (String)enumeration.nextElement();
            EffectData effectData = (EffectData)ALL_DATA_EFFECT.get(string);
            if ((GameCanvas.timeNow - effectData.timeRemove) / 1000L <= 60L) continue;
            ALL_DATA_EFFECT.remove(string);
        }
    }
}

