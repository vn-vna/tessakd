/*
 * Decompiled with CFR 0.152.
 */
package Model;

import CLib.mImage;
import CLib.mSystem;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

public class FilePack {
    public static FilePack instance;
    public String[] fname;
    private int[] fpos;
    private int[] flen;
    private byte[] fullData;
    private int nFile;
    private String name;
    private byte[] code = new byte[]{78, 103, 117, 121, 101, 110, 86, 97, 110, 77, 105, 110, 104};
    private int codeLen = this.code.length;
    public static final String[] charAvatar;
    public static final String wps = "/wps/";
    public static final String weapon = "/weapon/";
    private DataInputStream file;

    public FilePack() {
    }

    public static void reset() {
        if (instance != null) {
            instance.close();
        }
        instance = null;
        System.gc();
    }

    public FilePack(String string, byte[] byArray) {
        int n = 0;
        int n2 = 0;
        this.name = string;
        if (byArray == null) {
            this.open();
        } else {
            this.openbyArray(byArray);
        }
        if (this.file == null) {
            instance = null;
            return;
        }
        try {
            this.nFile = this.encode(this.file.readUnsignedByte());
            this.fname = new String[this.nFile];
            this.fpos = new int[this.nFile];
            this.flen = new int[this.nFile];
            for (int i = 0; i < this.nFile; ++i) {
                int n3 = this.encode(this.file.readByte());
                byte[] byArray2 = new byte[n3];
                this.file.read(byArray2);
                this.encode(byArray2);
                this.fname[i] = new String(byArray2);
                this.fpos[i] = n;
                this.flen[i] = this.encode(this.file.readUnsignedShort());
                n += this.flen[i];
                n2 += this.flen[i];
            }
            this.fullData = new byte[n2];
            this.file.readFully(this.fullData);
            this.encode(this.fullData);
        }
        catch (Exception exception) {
            mSystem.outloi("loi FilePack 1");
            exception.printStackTrace();
        }
        this.close();
    }

    public static mImage getImg(String string) {
        return instance.loadImage(string + ".png");
    }

    public static void init(String string) {
        instance = new FilePack(string, null);
    }

    public static void initByArray(byte[] byArray) {
        instance = new FilePack("", byArray);
    }

    private int encode(int n) {
        return n;
    }

    private void encode(byte[] byArray) {
        int n = byArray.length;
        for (int i = 0; i < n; ++i) {
            byArray[i] = (byte)(byArray[i] ^ this.code[i % this.codeLen]);
        }
    }

    private void open() {
        this.file = new DataInputStream(this.getClass().getResourceAsStream(this.name));
    }

    private void openbyArray(byte[] byArray) {
        this.file = new DataInputStream(new ByteArrayInputStream(byArray));
    }

    public void close() {
        try {
            if (this.file != null) {
                this.file.close();
            }
        }
        catch (IOException iOException) {
            mSystem.outloi("loi FilePack 1");
            iOException.printStackTrace();
        }
    }

    public byte[] loadFile(String string) throws Exception {
        for (int i = 0; i < this.nFile; ++i) {
            if (this.fname[i].compareTo(string) != 0) continue;
            byte[] byArray = new byte[this.flen[i]];
            System.arraycopy(this.fullData, this.fpos[i], byArray, 0, this.flen[i]);
            return byArray;
        }
        throw new Exception("File '" + string + "' not found!");
    }

    public mImage loadImage(String string) {
        for (int i = 0; i < this.nFile; ++i) {
            if (this.fname[i].compareTo(string) != 0) continue;
            return mImage.createImage(this.fullData, this.fpos[i], this.flen[i], string);
        }
        return null;
    }

    public InputStream loadData(String string) {
        for (int i = 0; i < this.nFile; ++i) {
            if (this.fname[i].compareTo(string) != 0) continue;
            byte[] byArray = new byte[this.flen[i]];
            System.arraycopy(this.fullData, this.fpos[i], byArray, 0, this.flen[i]);
            return new ByteArrayInputStream(byArray);
        }
        return null;
    }

    public byte[] getBinaryFile(String string) {
        for (int i = 0; i < this.nFile; ++i) {
            if (this.fname[i].compareTo(string) != 0) continue;
            byte[] byArray = new byte[this.flen[i]];
            System.arraycopy(this.fullData, this.fpos[i], byArray, 0, this.flen[i]);
            return byArray;
        }
        mSystem.outloi("File '" + string + "' not found!");
        return null;
    }

    static {
        charAvatar = new String[]{"leg", "body", "head", "hat", "eye", "hair", "wing"};
    }
}

