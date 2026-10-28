/*
 * Decompiled with CFR 0.152.
 */
package GameObjects;

import CLib.mGraphics;
import CLib.mImage;
import CLib.mVector;
import GameObjects.Animation;
import GameObjects.FrameEff;
import GameObjects.PartFrame;
import GameObjects.Pet;
import GameObjects.SmallImage;
import Model.CRes;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.FilterInputStream;

public class DataEffect {
    public mVector listFrame = new mVector();
    public mVector listAnima = new mVector();
    public SmallImage[] smallImage;
    public byte[] sequence;
    public short idimg;
    private short FrameWith;
    private short FrameHeight;
    public String name = "";
    public byte isFly = 0;
    public byte idShadow = 0;
    public static byte[][] indexAction2 = new byte[][]{{0, 0, 1, 2, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1}, {4, 4, 5, 6, 7, 5, 5, 5, 5, 5, 5, 5, 5, 5}};
    public static byte[] indexAction = new byte[]{0, 0, 1, 2, 3, 1, 1, 1, 1};

    public DataEffect(byte[] byArray, int n, boolean bl) {
        this.idimg = (short)n;
        this.setDataType1(byArray);
    }

    public DataEffect(byte[] byArray) {
        try {
            Object object;
            int n;
            int n2;
            int n3;
            DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(byArray));
            int n4 = dataInputStream.readByte();
            this.smallImage = new SmallImage[n4];
            for (n3 = 0; n3 < n4; ++n3) {
                this.smallImage[n3] = new SmallImage(dataInputStream.readUnsignedByte(), dataInputStream.readUnsignedByte(), dataInputStream.readUnsignedByte(), dataInputStream.readUnsignedByte(), dataInputStream.readUnsignedByte());
            }
            n3 = dataInputStream.readShort();
            for (n2 = 0; n2 < n3; ++n2) {
                n = dataInputStream.readByte();
                mVector mVector2 = new mVector();
                for (int i = 0; i < n; ++i) {
                    object = new PartFrame(dataInputStream.readShort(), dataInputStream.readShort(), dataInputStream.readByte());
                    mVector2.addElement(object);
                }
                this.listFrame.addElement(new FrameEff(mVector2));
            }
            n2 = dataInputStream.readShort();
            this.sequence = new byte[n2];
            for (n = 0; n < n2; ++n) {
                this.sequence[n] = (byte)dataInputStream.readShort();
            }
            n = dataInputStream.readByte();
            byte by = dataInputStream.readByte();
            n2 = dataInputStream.readByte();
            byte[] byArray2 = new byte[n2];
            dataInputStream.read(byArray2);
            object = new Animation(byArray2);
            this.listAnima.addElement(object);
            n2 = dataInputStream.readByte();
            byArray2 = new byte[n2];
            dataInputStream.read(byArray2);
            object = new Animation(byArray2);
            this.listAnima.addElement(object);
            n2 = dataInputStream.readByte();
            byArray2 = new byte[n2];
            dataInputStream.read(byArray2);
            object = new Animation(byArray2);
            this.listAnima.addElement(object);
            n2 = dataInputStream.readByte();
            byArray2 = new byte[n2];
            dataInputStream.read(byArray2);
            object = new Animation(byArray2);
            this.listAnima.addElement(object);
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void setDataType1(byte[] byArray) {
        short s = 0;
        FilterInputStream filterInputStream = null;
        ByteArrayInputStream byteArrayInputStream = null;
        this.listFrame.removeAllElements();
        this.listAnima.removeAllElements();
        try {
            int n;
            int n2;
            byteArrayInputStream = new ByteArrayInputStream(byArray);
            filterInputStream = new DataInputStream(byteArrayInputStream);
            int n3 = ((DataInputStream)filterInputStream).readByte();
            this.smallImage = new SmallImage[n3];
            for (n2 = 0; n2 < n3; ++n2) {
                this.smallImage[n2] = new SmallImage(((DataInputStream)filterInputStream).readUnsignedByte(), ((DataInputStream)filterInputStream).readUnsignedByte(), ((DataInputStream)filterInputStream).readUnsignedByte(), ((DataInputStream)filterInputStream).readUnsignedByte(), ((DataInputStream)filterInputStream).readUnsignedByte());
            }
            n2 = 0;
            boolean bl = false;
            int n4 = -1000000;
            int n5 = ((DataInputStream)filterInputStream).readShort();
            for (n = 0; n < n5; ++n) {
                int n6 = ((DataInputStream)filterInputStream).readByte();
                mVector mVector2 = new mVector();
                for (int i = 0; i < n6; ++i) {
                    PartFrame partFrame = new PartFrame(((DataInputStream)filterInputStream).readShort(), ((DataInputStream)filterInputStream).readShort(), ((DataInputStream)filterInputStream).readByte());
                    partFrame.flip = ((DataInputStream)filterInputStream).readByte();
                    partFrame.onTop = ((DataInputStream)filterInputStream).readByte();
                    mVector2.addElement(partFrame);
                    if (n != 0) continue;
                    if (n4 < partFrame.dy + this.smallImage[partFrame.idSmallImg].h) {
                        n4 = partFrame.dy + this.smallImage[partFrame.idSmallImg].h;
                    }
                    if (n2 >= CRes.abs(partFrame.dy)) continue;
                    n2 = CRes.abs(partFrame.dy);
                }
                if (n == 0 && n4 <= -5) {
                    this.isFly = (byte)n4;
                }
                this.listFrame.addElement(new FrameEff(mVector2, null));
            }
            this.FrameWith = this.smallImage[0].w;
            this.FrameHeight = (short)n2;
            s = ((DataInputStream)filterInputStream).readShort();
            this.sequence = new byte[s];
            for (n = 0; n < s; ++n) {
                this.sequence[n] = (byte)((DataInputStream)filterInputStream).readShort();
            }
            s = ((DataInputStream)filterInputStream).readByte();
            byte[] byArray2 = new byte[s];
            ((DataInputStream)filterInputStream).read(byArray2);
            Animation animation = new Animation(byArray2);
            this.listAnima.addElement(animation);
            s = ((DataInputStream)filterInputStream).readByte();
            byArray2 = new byte[s];
            ((DataInputStream)filterInputStream).read(byArray2);
            animation = new Animation(byArray2);
            this.listAnima.addElement(animation);
            s = ((DataInputStream)filterInputStream).readByte();
            byArray2 = new byte[s];
            ((DataInputStream)filterInputStream).read(byArray2);
            animation = new Animation(byArray2);
            this.listAnima.addElement(animation);
            s = ((DataInputStream)filterInputStream).readByte();
            byArray2 = new byte[s];
            ((DataInputStream)filterInputStream).read(byArray2);
            animation = new Animation(byArray2);
            this.listAnima.addElement(animation);
            s = ((DataInputStream)filterInputStream).readByte();
            byArray2 = new byte[s];
            ((DataInputStream)filterInputStream).read(byArray2);
            animation = new Animation(byArray2);
            this.listAnima.addElement(animation);
            s = ((DataInputStream)filterInputStream).readByte();
            byArray2 = new byte[s];
            ((DataInputStream)filterInputStream).read(byArray2);
            animation = new Animation(byArray2);
            this.listAnima.addElement(animation);
            s = ((DataInputStream)filterInputStream).readByte();
            byArray2 = new byte[s];
            ((DataInputStream)filterInputStream).read(byArray2);
            animation = new Animation(byArray2);
            this.listAnima.addElement(animation);
            s = ((DataInputStream)filterInputStream).readByte();
            byArray2 = new byte[s];
            ((DataInputStream)filterInputStream).read(byArray2);
            animation = new Animation(byArray2);
            this.listAnima.addElement(animation);
            if (filterInputStream.available() > 0) {
                this.idShadow = ((DataInputStream)filterInputStream).readByte();
                for (int i = 0; i < n5; ++i) {
                    FrameEff frameEff = (FrameEff)this.listFrame.elementAt(i);
                    frameEff.xShadow = ((DataInputStream)filterInputStream).readByte();
                    frameEff.yShadow = ((DataInputStream)filterInputStream).readByte();
                }
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        finally {
            try {
                filterInputStream.close();
            }
            catch (Exception exception) {}
            try {
                byteArrayInputStream.close();
            }
            catch (Exception exception) {}
        }
    }

    public Animation getAnim(int n) {
        return (Animation)this.listAnima.elementAt(indexAction[n]);
    }

    public int getFrame(int n, int n2, int n3) {
        Animation animation = (Animation)this.listAnima.elementAt(indexAction2[n3][n2]);
        if (n < animation.frame.length) {
            return animation.frame[n];
        }
        return 0;
    }

    public int getFrame(int n, int n2) {
        Animation animation = (Animation)this.listAnima.elementAt(indexAction[n2]);
        if (n < animation.frame.length) {
            return animation.frame[n];
        }
        return 0;
    }

    public void paint(mGraphics mGraphics2, int n, int n2, int n3, int n4, mImage mImage2) {
        if (mImage2 == null) {
            return;
        }
        n = 0;
        FrameEff frameEff = (FrameEff)this.listFrame.elementAt(n);
        try {
            for (int i = 0; i < frameEff.listPart.size(); ++i) {
                PartFrame partFrame = (PartFrame)frameEff.listPart.elementAt(i);
                SmallImage smallImage = this.smallImage[partFrame.idSmallImg];
                int n5 = partFrame.dx;
                if (n4 == 2) {
                    n5 = -n5 - smallImage.w;
                }
                mGraphics2.drawRegion(mImage2, smallImage.x, smallImage.y, smallImage.w, smallImage.h, n4, n2 + n5, n3 + partFrame.dy, 0, false);
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void paintPet(mGraphics mGraphics2, int n, int n2, int n3, int n4, mImage mImage2) {
        if (mImage2 == null) {
            return;
        }
        FrameEff frameEff = (FrameEff)this.listFrame.elementAt(n);
        if (frameEff == null || frameEff.listPartTop == null) {
            // empty if block
        }
        try {
            if (frameEff != null) {
                for (int i = 0; i < frameEff.listPartTop.size(); ++i) {
                    PartFrame partFrame = (PartFrame)frameEff.listPartTop.elementAt(i);
                    SmallImage smallImage = this.smallImage[partFrame.idSmallImg];
                    int n5 = partFrame.dx;
                    int n6 = smallImage.w;
                    int n7 = smallImage.h;
                    short s = smallImage.x;
                    short s2 = smallImage.y;
                    if (s > mImage.getImageWidth(mImage2.image)) {
                        s = 0;
                    }
                    if (s2 > mImage.getImageHeight(mImage2.image)) {
                        s2 = 0;
                    }
                    if (s + n6 > mImage.getImageWidth(mImage2.image)) {
                        n6 = mImage.getImageWidth(mImage2.image) - s;
                    }
                    if (s2 + n7 > mImage.getImageHeight(mImage2.image)) {
                        n7 = mImage.getImageHeight(mImage2.image) - s2;
                    }
                    if (n4 == 2) {
                        n5 = -n5 - n6;
                    }
                    if (partFrame.flip != 1) {
                        mGraphics2.drawRegion(mImage2, s, s2, n6, n7, n4, n2 + n5, n3 + partFrame.dy, 0, false);
                        continue;
                    }
                    mGraphics2.drawRegion(mImage2, s, s2, n6, n7, n4 == 2 ? 0 : 2, n2 + n5, n3 + partFrame.dy, 0, false);
                }
            }
        }
        catch (Exception exception) {
            System.out.println("loi dataeff: " + this.name);
            exception.printStackTrace();
        }
    }

    public mVector getDataPet() {
        mVector mVector2 = new mVector();
        mVector mVector3 = (mVector)Pet.PET_DATA.get("" + this.idimg);
        if (mVector3 != null) {
            mVector2 = mVector3;
        }
        return mVector2;
    }

    public Animation getAnim(int n, int n2) {
        return (Animation)this.listAnima.elementAt(indexAction2[n2][n]);
    }
}

