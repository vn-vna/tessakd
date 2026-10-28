/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Image
 */
package CLib;

import CLib.TemGraphics;
import CLib.mGraphics;
import CLib.mSystem;
import java.io.DataInputStream;
import java.io.IOException;
import javax.microedition.lcdui.Image;

public class mImage {
    public Image image;

    public static String getLink(String string) {
        return string;
    }

    public void clearData() {
    }

    public static mImage createEncodedImage(String string) {
        mImage mImage2 = new mImage();
        String string2 = "/x" + mGraphics.zoomLevel + string;
        try {
            DataInputStream dataInputStream = null;
            dataInputStream = new DataInputStream("".getClass().getResourceAsStream(string2));
            int n = dataInputStream.available();
            byte[] byArray = new byte[n];
            dataInputStream.read(byArray, 0, n);
            for (int i = 0; i < byArray.length; ++i) {
                byArray[i] = (byte)(byArray[i] ^ 3);
            }
            mImage2.image = Image.createImage((byte[])byArray, (int)0, (int)n);
        }
        catch (IOException iOException) {
            mSystem.outz("IOException :" + iOException.getMessage());
        }
        catch (IllegalArgumentException illegalArgumentException) {
            mSystem.outz(string2 + " IllegalArgumentException :" + illegalArgumentException.getMessage());
        }
        catch (NullPointerException nullPointerException) {
            mSystem.outz(string2 + " NullPointerException :" + ((Throwable)nullPointerException).getMessage());
        }
        if (mImage2.image == null) {
            return null;
        }
        return mImage2;
    }

    public static String replaceIMGtoPNG(String string, String string2, String string3) {
        String string4 = "";
        String string5 = "";
        int n = string.lastIndexOf(46);
        if (n > 0) {
            string4 = string.substring(n + 1, string.length());
            string5 = string.substring(0, n + 1);
        }
        if (string4.compareTo(string2) == 0) {
            StringBuffer stringBuffer = new StringBuffer(string5);
            stringBuffer.append(string3);
            return stringBuffer.toString();
        }
        return string;
    }

    public static mImage createImage(String string) {
        string = mImage.replaceIMGtoPNG(string, "img", "png");
        String string2 = "";
        int n = string.lastIndexOf(46);
        if (n > 0) {
            string2 = string.substring(n + 1, string.length());
        }
        if (string2.compareTo("img") == 0) {
            return mImage.createEncodedImage(string);
        }
        mImage mImage2 = new mImage();
        try {
            mImage2.image = Image.createImage((String)("/x" + mGraphics.zoomLevel + string));
        }
        catch (Exception exception) {
            // empty catch block
        }
        if (mImage2.image == null) {
            return null;
        }
        return mImage2;
    }

    public static mImage createImageAll(String string) {
        mImage mImage2 = new mImage();
        try {
            mImage2.image = Image.createImage((String)string);
        }
        catch (Exception exception) {
            // empty catch block
        }
        if (mImage2.image == null) {
            return null;
        }
        return mImage2;
    }

    public static DataInputStream openFile(String string) {
        DataInputStream dataInputStream = null;
        dataInputStream = new DataInputStream("".getClass().getResourceAsStream(mImage.getLink(string)));
        return dataInputStream;
    }

    public static mImage createImage(int n, int n2) {
        mImage mImage2 = new mImage();
        mImage2.image = Image.createImage((int)(n * mGraphics.zoomLevel), (int)(n2 * mGraphics.zoomLevel));
        return mImage2;
    }

    public static mImage createImage(byte[] byArray, int n, int n2, String string) {
        mImage mImage2 = new mImage();
        mImage2.image = Image.createImage((byte[])byArray, (int)0, (int)byArray.length);
        return mImage2;
    }

    public TemGraphics getGraphics() {
        TemGraphics temGraphics = new TemGraphics();
        temGraphics.g = this.image.getGraphics();
        return temGraphics;
    }

    public static int getImageWidth(Image image) {
        return image.getWidth() / mGraphics.zoomLevel;
    }

    public int getWith() {
        return this.image.getWidth();
    }

    public int getHeight() {
        return this.image.getHeight();
    }

    public static int getImageHeight(Image image) {
        return image.getHeight() / mGraphics.zoomLevel;
    }

    public void getRGB(int[] nArray, int n, int n2, int n3, int n4, int n5, int n6) {
        this.image.getRGB(nArray, n, n2, n3, n4, n5, n6);
    }

    public static mImage createRGBImage(int[] nArray, int n, int n2, boolean bl) {
        mImage mImage2 = new mImage();
        mImage2.image = Image.createRGBImage((int[])nArray, (int)n, (int)n2, (boolean)bl);
        return mImage2;
    }
}

