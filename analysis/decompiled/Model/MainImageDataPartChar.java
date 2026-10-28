/*
 * Decompiled with CFR 0.152.
 */
package Model;

import CLib.mImage;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.OutputStream;

public class MainImageDataPartChar {
    public mImage img;
    public int count = -1;
    public byte type;
    public short id;
    public short version;
    public long timeImageNull = 0L;
    public byte[] isData = null;
    public byte[] isDataImg = null;

    public MainImageDataPartChar() {
    }

    public MainImageDataPartChar(byte[] byArray, byte[] byArray2, byte by, short s, short s2) {
        this.isDataImg = byArray;
        this.count = 0;
        this.isData = byArray2;
        this.type = by;
        this.id = s;
        this.version = s2;
    }

    public MainImageDataPartChar(mImage mImage2, byte[] byArray) {
        this.img = mImage2;
        this.count = 0;
        this.isData = byArray;
    }

    public ByteArrayOutputStream getSaveData() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        try {
            dataOutputStream.writeShort(this.version);
            dataOutputStream.writeInt(this.isDataImg.length);
            ((OutputStream)dataOutputStream).write(this.isDataImg);
            dataOutputStream.writeShort(this.isData.length);
            ((OutputStream)dataOutputStream).write(this.isData);
        }
        catch (Exception exception) {
            // empty catch block
        }
        return byteArrayOutputStream;
    }
}

