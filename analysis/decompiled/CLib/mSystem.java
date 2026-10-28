/*
 * Decompiled with CFR 0.152.
 */
package CLib;

import CLib.mVector;

public class mSystem {
    public static boolean Android = false;
    public static String link_region = "http://dl.teamobi.com/geo/kn.php?ip=";
    public static String[][] listServer = new String[][]{{"Chi\u1ebfn Th\u1ea7n", "hs1.teamobi.com"}, {"R\u1ed3ng L\u1eeda", "hs2.teamobi.com"}, {"Global Server", "hsglobal.teamobi.com"}, {"Ph\u01b0\u1ee3ng Ho\u00e0ng", "hs3.teamobi.com"}, {"Nh\u00e2n M\u00e3", "hs5.teamobi.com"}, {"K\u00ec L\u00e2n", "hs6.teamobi.com"}, {"Thi\u00ean H\u00e0 (New)", "hs7.teamobi.com"}, {"Th\u00e1ch \u0110\u1ea5u", "hs4.teamobi.com"}};
    public static String[][] listServer_VN = new String[][]{{"Chi\u1ebfn Th\u1ea7n", "hs1.teamobi.com"}, {"R\u1ed3ng L\u1eeda", "hs2.teamobi.com"}, {"Global Server", "hsglobal.teamobi.com"}, {"Ph\u01b0\u1ee3ng Ho\u00e0ng", "hs3.teamobi.com"}, {"Nh\u00e2n M\u00e3", "hs5.teamobi.com"}, {"K\u00ec L\u00e2n (New)", "hs6.teamobi.com"}, {"Th\u00e1ch \u0110\u1ea5u", "hs4.teamobi.com"}};
    public static String[][] listServer_In_Do = new String[][]{{"Indo Naga", "hsindo.teamobi.com"}, {"Indo Garuda(new)", "hsindo.teamobi.com"}, {"Knight Age (ENG)", "hsglobal.teamobi.com"}};
    public static String[][] listServer_Usa = new String[][]{{"Fire Dragon (ENG)", "46.137.254.172"}, {"Sky Dragon (SPN)", "54.254.156.202"}, {"Knight Age (ENG)", "hsglobal.teamobi.com"}};
    public static int ID_REGION;
    public static boolean isLoad_REGION;
    public static boolean isMaHoa;
    public static boolean isIP_GDX;
    public static boolean isIP_TrucTiep;
    public static boolean isj2me;
    public static int dyCharStep;
    public static boolean isImgLocal;
    public static final byte INDEX_SV_GLOBAL = 2;
    public static boolean isWinphone;
    public static boolean isIphone;

    public static long currentTimeMillis() {
        return System.currentTimeMillis();
    }

    public static void outz(String string) {
    }

    public static void println(String string) {
        System.out.println(string);
    }

    public static void freeDataChangeMap() {
    }

    public static void outloi(String string) {
    }

    public static void my_Gc() {
        System.gc();
    }

    public static int[][] new_M_Int(int n, int n2) {
        int[][] nArray = new int[n][n2];
        return nArray;
    }

    public static String[][] new_M_String(int n, int n2) {
        String[][] stringArray = new String[n][n2];
        return stringArray;
    }

    public static byte[][] new_M_Byte(int n, int n2) {
        byte[][] byArray = new byte[n][n2];
        return byArray;
    }

    public static byte[][][] new_M_Byte(int n, int n2, int n3) {
        byte[][][] byArray = new byte[n][n2][n3];
        return byArray;
    }

    public static String[] split(String string, String string2) {
        mVector mVector2 = new mVector("mSys nodes");
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

    public static String getPackageName() {
        return "";
    }

    public static void doChangeMenuNapapple() {
    }

    public static String getLat() {
        return "";
    }

    public static String getLong() {
        return "";
    }

    public static boolean isIpAppstore() {
        return false;
    }

    public static boolean isHideNaptien() {
        return false;
    }

    static {
        isMaHoa = false;
        isIP_GDX = false;
        isIP_TrucTiep = false;
        isj2me = true;
        dyCharStep = 0;
        isImgLocal = true;
        isWinphone = false;
        isIphone = false;
    }
}

