/*
 * Decompiled with CFR 0.152.
 */
package CLib;

import CLib.mGraphics;
import CLib.mImage;
import CLib.mSystem;
import CLib.mVector;
import java.io.DataInputStream;
import java.io.IOException;

public class mFont {
    public static final int LEFT = 0;
    public static final byte RIGHT = 1;
    public static final byte CENTER = 2;
    public static final byte RED = 0;
    public static final byte YELLOW = 1;
    public static final byte GREEN = 2;
    public static final byte FATAL = 3;
    public static final byte MISS = 4;
    public static final byte ORANGE = 5;
    public static final byte ADDMONEY = 6;
    public static final byte MISS_ME = 7;
    public static final byte FATAL_ME = 8;
    private int space;
    private int height;
    private mImage imgFont;
    private String strFont;
    private int[][] fImages;
    public static String str = " 0123456789+-*='_?.,<>/[]{}!@#$%^&*():a\u00e1\u00e0\u1ea3\u00e3\u1ea1\u00e2\u1ea5\u1ea7\u1ea9\u1eab\u1ead\u0103\u1eaf\u1eb1\u1eb3\u1eb5\u1eb7bcd\u0111e\u00e9\u00e8\u1ebb\u1ebd\u1eb9\u00ea\u1ebf\u1ec1\u1ec3\u1ec5\u1ec7fghi\u00ed\u00ec\u1ec9\u0129\u1ecbjklmno\u00f3\u00f2\u1ecf\u00f5\u1ecd\u00f4\u1ed1\u1ed3\u1ed5\u1ed7\u1ed9\u01a1\u1edb\u1edd\u1edf\u1ee1\u1ee3pqrstu\u00fa\u00f9\u1ee7\u0169\u1ee5\u01b0\u1ee9\u1eeb\u1eed\u1eef\u1ef1vxy\u00fd\u1ef3\u1ef7\u1ef9\u1ef5zwA\u00c1\u00c0\u1ea2\u00c3\u1ea0\u0102\u1eb0\u1eae\u1eb2\u1eb4\u1eb6\u00c2\u1ea4\u1ea6\u1ea8\u1eaa\u1eacBCD\u0110E\u00c9\u00c8\u1eba\u1ebc\u1eb8\u00ca\u1ebe\u1ec0\u1ec2\u1ec4\u1ec6FGHI\u00cd\u00cc\u1ec8\u0128\u1ecaJKLMNO\u00d3\u00d2\u1ece\u00d5\u1ecc\u00d4\u1ed0\u1ed2\u1ed4\u1ed6\u1ed8\u01a0\u1eda\u1edc\u1ede\u1ee0\u1ee2PQRSTU\u00da\u00d9\u1ee6\u0168\u1ee4\u01af\u1ee8\u1eea\u1eec\u1eee\u1ef0VXY\u00dd\u1ef2\u1ef6\u1ef8\u1ef4ZW";
    public static mFont tahoma_7b_orange;
    public static mFont tahoma_7b_blue;
    public static mFont tahoma_7b_black;
    public static mFont tahoma_7b_yellow;
    public static mFont tahoma_7b_violet;
    public static mFont tahoma_7b_white;
    public static mFont tahoma_7b_green;
    public static mFont tahoma_7b_red;
    public static mFont tahoma_7b_brown;
    public static mFont tahoma_7_black;
    public static mFont tahoma_7_white;
    public static mFont tahoma_7_yellow;
    public static mFont tahoma_7_orange;
    public static mFont tahoma_7_red;
    public static mFont tahoma_7_blue;
    public static mFont tahoma_7_green;
    public static mFont tahoma_7_violet;
    public static mFont number_yellow;
    public static mFont number_red;
    public static mFont number_green;
    public static mFont number_white;
    public static mFont number_orange;
    public static mFont number_Yellow_Small;
    public static mFont tahoma_7_gray;
    String pathImage;

    public static void loadmFont() {
        tahoma_7b_orange = new mFont(str, "/mfont/tahoma_7b_orange.png", "/mfont/tahoma_7b", 0);
        tahoma_7b_blue = new mFont(str, "/mfont/tahoma_7b_blue.png", "/mfont/tahoma_7b", 0);
        tahoma_7b_black = new mFont(str, "/mfont/tahoma_7b_black.png", "/mfont/tahoma_7b", 0);
        tahoma_7b_yellow = new mFont(str, "/mfont/tahoma_7b_yellow.png", "/mfont/tahoma_7b", 0);
        tahoma_7b_violet = new mFont(str, "/mfont/tahoma_7b_violet.png", "/mfont/tahoma_7b", 0);
        tahoma_7b_white = new mFont(str, "/mfont/tahoma_7b_white.png", "/mfont/tahoma_7b", 0);
        tahoma_7b_green = new mFont(str, "/mfont/tahoma_7b_green.png", "/mfont/tahoma_7b", 0);
        tahoma_7b_brown = new mFont(str, "/mfont/tahoma_7b_brown.png", "/mfont/tahoma_7b", 0);
        tahoma_7b_red = new mFont(str, "/mfont/tahoma_7b_red.png", "/mfont/tahoma_7b", 0);
        tahoma_7_black = new mFont(str, "/mfont/tahoma_7_black.png", "/mfont/tahoma_7", 0);
        tahoma_7_white = new mFont(str, "/mfont/tahoma_7_white.png", "/mfont/tahoma_7", 0);
        tahoma_7_yellow = new mFont(str, "/mfont/tahoma_7_yellow.png", "/mfont/tahoma_7", 0);
        tahoma_7_orange = new mFont(str, "/mfont/tahoma_7_orange.png", "/mfont/tahoma_7", 0);
        tahoma_7_red = new mFont(str, "/mfont/tahoma_7_red.png", "/mfont/tahoma_7", 0);
        tahoma_7_blue = new mFont(str, "/mfont/tahoma_7_blue.png", "/mfont/tahoma_7", 0);
        tahoma_7_green = new mFont(str, "/mfont/tahoma_7_green.png", "/mfont/tahoma_7", 0);
        tahoma_7_violet = new mFont(str, "/mfont/tahoma_7_violet.png", "/mfont/tahoma_7", 0);
        tahoma_7_gray = new mFont(str, "/mfont/tahoma_7_gray.png", "/mfont/tahoma_7", 0);
        number_yellow = new mFont(" 0123456789+-", "/mfont/number_yellow.png", "/mfont/number", 0);
        number_red = new mFont(" 0123456789+-", "/mfont/number_red.png", "/mfont/number", 0);
        number_green = new mFont(" 0123456789+-", "/mfont/number_green.png", "/mfont/number", 0);
        number_white = new mFont(" 0123456789+-", "/mfont/number_white.png", "/mfont/number", 0);
        number_orange = new mFont(" 0123456789+-", "/mfont/number_orange.png", "/mfont/number", 0);
        number_Yellow_Small = new mFont(" 0123456789+-", "/mfont/number_yellow.png", "/mfont/number", 0);
    }

    public mFont(String string, String string2, String string3, int n) {
        try {
            this.strFont = string;
            this.space = n;
            this.pathImage = string2;
            DataInputStream dataInputStream = null;
            this.reloadImage();
            try {
                dataInputStream = mImage.openFile(string3);
                this.fImages = new int[dataInputStream.readShort()][];
                for (int i = 0; i < this.fImages.length; ++i) {
                    this.fImages[i] = new int[4];
                    this.fImages[i][0] = dataInputStream.readShort();
                    this.fImages[i][1] = dataInputStream.readShort();
                    this.fImages[i][2] = dataInputStream.readShort();
                    this.fImages[i][3] = dataInputStream.readShort();
                }
                this.setHeight(this.fImages[this.fImages.length - 1][3]);
            }
            catch (Exception exception) {
                try {
                    dataInputStream.close();
                }
                catch (IOException iOException) {
                    iOException.printStackTrace();
                }
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
            mSystem.println("paht data:" + string2);
        }
    }

    public void reloadImage() {
        this.imgFont = mImage.createImage(this.pathImage);
    }

    public void freeImage() {
        this.imgFont = null;
    }

    public int getHeight() {
        return this.height;
    }

    public void setHeight(int n) {
        this.height = n;
    }

    public int getWidth(String string) {
        int n = 0;
        for (int i = 0; i < string.length(); ++i) {
            int n2 = this.strFont.indexOf(string.charAt(i));
            if (n2 == -1) {
                n2 = 0;
            }
            n += this.fImages[n2][2] + this.space;
        }
        return n;
    }

    public void drawString(mGraphics mGraphics2, String string, int n, int n2, int n3, boolean bl) {
        int n4 = string.length();
        int n5 = n3 == 0 ? n : (n3 == 1 ? n - this.getWidth(string) : n - (this.getWidth(string) >> 1));
        for (int i = 0; i < n4; ++i) {
            int n6 = this.strFont.indexOf(string.charAt(i));
            if (n6 == -1) {
                n6 = 0;
            }
            if (n6 > -1) {
                mGraphics2.drawRegion(this.imgFont, this.fImages[n6][0], this.fImages[n6][1], this.fImages[n6][2], this.fImages[n6][3], 0, n5, n2, 20, bl);
            }
            n5 += this.fImages[n6][2] + this.space;
        }
    }

    public void drawString(mGraphics mGraphics2, String string, int n, int n2, int n3, mFont mFont2, boolean bl) {
        int n4 = string.length();
        int n5 = n3 == 0 ? n : (n3 == 1 ? n - this.getWidth(string) : n - (this.getWidth(string) >> 1));
        for (int i = 0; i < n4; ++i) {
            int n6 = this.strFont.indexOf(string.charAt(i));
            if (n6 == -1) {
                n6 = 0;
            }
            if (n6 > -1) {
                mGraphics2.drawRegion(mFont2.imgFont, this.fImages[n6][0], this.fImages[n6][1], this.fImages[n6][2], this.fImages[n6][3], 0, n5 + 1, n2, 20, bl);
                mGraphics2.drawRegion(mFont2.imgFont, this.fImages[n6][0], this.fImages[n6][1], this.fImages[n6][2], this.fImages[n6][3], 0, n5, n2 + 1, 20, bl);
                mGraphics2.drawRegion(this.imgFont, this.fImages[n6][0], this.fImages[n6][1], this.fImages[n6][2], this.fImages[n6][3], 0, n5, n2, 20, bl);
            }
            n5 += this.fImages[n6][2] + this.space;
        }
    }

    public void drawString(mGraphics mGraphics2, String string, int n, int n2, int n3, mFont mFont2, mFont mFont3, boolean bl) {
        int n4 = string.length();
        int n5 = n3 == 0 ? n : (n3 == 1 ? n - this.getWidth(string) : n - (this.getWidth(string) >> 1));
        for (int i = 0; i < n4; ++i) {
            int n6 = this.strFont.indexOf(string.charAt(i));
            if (n6 == -1) {
                n6 = 0;
            }
            if (n6 > -1) {
                mGraphics2.drawRegion(mFont2.imgFont, this.fImages[n6][0], this.fImages[n6][1], this.fImages[n6][2], this.fImages[n6][3], 0, n5 + 1, n2, 20, bl);
                mGraphics2.drawRegion(mFont3.imgFont, this.fImages[n6][0], this.fImages[n6][1], this.fImages[n6][2], this.fImages[n6][3], 0, n5, n2 + 1, 20, bl);
                mGraphics2.drawRegion(this.imgFont, this.fImages[n6][0], this.fImages[n6][1], this.fImages[n6][2], this.fImages[n6][3], 0, n5, n2, 20, bl);
            }
            n5 += this.fImages[n6][2] + this.space;
        }
    }

    public mVector splitFontVector(String string, int n) {
        mVector mVector2 = new mVector("mFont lines");
        String string2 = "";
        for (int i = 0; i < string.length(); ++i) {
            if (string.charAt(i) == '\n') {
                mVector2.addElement(string2);
                string2 = "";
                continue;
            }
            if (this.getWidth(string2 = string2 + string.charAt(i)) > n) {
                int n2 = 0;
                for (n2 = string2.length() - 1; n2 >= 0 && string2.charAt(n2) != ' '; --n2) {
                }
                if (n2 < 0) {
                    n2 = string2.length() - 1;
                }
                mVector2.addElement(string2.substring(0, n2));
                i = i - (string2.length() - n2) + 1;
                string2 = "";
            }
            if (i != string.length() - 1 || string2.trim().equals("")) continue;
            mVector2.addElement(string2);
        }
        return mVector2;
    }

    public static String[] split(String string, String string2) {
        mVector mVector2 = new mVector("mFont nodes");
        int n = string.indexOf(string2);
        while (n >= 0) {
            mVector2.addElement(string.substring(0, n));
            string = string.substring(n + string2.length());
            n = string.indexOf(string2);
        }
        mVector2.addElement(string);
        String[] stringArray = new String[mVector2.size()];
        if (mVector2.size() > 0) {
            for (int i = 0; i < mVector2.size(); ++i) {
                stringArray[i] = (String)mVector2.elementAt(i);
            }
        }
        return stringArray;
    }

    public String splitFirst(String string) {
        String string2 = "";
        boolean bl = false;
        for (int i = 0; i < string.length(); ++i) {
            if (!bl) {
                String string3 = string.substring(i, string.length());
                string2 = this.compare(string3, " ") ? string2 + string.charAt(i) + "-" : string2 + string3;
                bl = true;
                continue;
            }
            if (string.charAt(i) != ' ') continue;
            bl = false;
        }
        return string2;
    }

    public String[] splitFontArray(String string, int n) {
        mVector mVector2 = this.splitFontVector(string, n);
        String[] stringArray = new String[mVector2.size()];
        for (int i = 0; i < mVector2.size(); ++i) {
            stringArray[i] = mVector2.elementAt(i).toString();
        }
        return stringArray;
    }

    public boolean compare(String string, String string2) {
        for (int i = 0; i < string.length(); ++i) {
            if (!String.valueOf(string.charAt(i)).equals(string2)) continue;
            return true;
        }
        return false;
    }
}

