/*
 * Decompiled with CFR 0.152.
 */
package Model;

import CLib.mHashtable;
import CLib.mImage;
import CLib.mSystem;
import CLib.mVector;
import GameObjects.CharPartInfo;
import GameObjects.MainInfoItem;
import GameObjects.MainItem;
import GameObjects.MainObject;
import GameObjects.WPSplashInfo;
import GameObjects.WeaponInfo;
import GameScreen.PaintInfoGameScreen;
import Main.GameCanvas;
import Map.MainItemMap;
import Model.FilePack;
import Model.MainImageDataPartChar;
import Skill.Skill;
import Thread_More.Load_Data_And_Img;
import com.silverknight.TemMidlet;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;
import netcommand.global.GlobalService;

public class CRes {
    private static short[] sin;
    public static mHashtable hashWeapon;
    public static String RMS_REGION;
    private static short[] cos;
    private static int[] tan;
    static int value;
    public static Random r;
    public static final int LEG = 0;
    public static final int BODY = 1;
    public static final int HEAD = 2;
    public static final int HAT = 3;
    public static final int EYE = 4;
    public static final int HAIR = 5;
    public static final int WING = 6;
    public static CharPartInfo[][] charPartInfo;
    public static WPSplashInfo[][][] wpSplashInfos;

    public static final int sin(int n) {
        if (n >= 0 && n < 90) {
            return sin[n];
        }
        if (n >= 90 && n < 180) {
            return sin[180 - n];
        }
        if (n >= 180 && n < 270) {
            return -sin[n - 180];
        }
        return -sin[360 - n];
    }

    public static final int cos(int n) {
        if (n >= 0 && n < 90) {
            return cos[n];
        }
        if (n >= 90 && n < 180) {
            return -cos[180 - n];
        }
        if (n >= 180 && n < 270) {
            return -cos[n - 180];
        }
        return cos[360 - n];
    }

    public static final int tan(int n) {
        if (n >= 0 && n < 90) {
            return tan[n];
        }
        if (n >= 90 && n < 180) {
            return -tan[180 - n];
        }
        if (n >= 180 && n < 270) {
            return tan[n - 180];
        }
        return -tan[360 - n];
    }

    public static final int atan(int n) {
        for (int i = 0; i <= 90; ++i) {
            if (tan[i] < n) continue;
            return i;
        }
        return 0;
    }

    public static final int angle(int n, int n2) {
        int n3;
        if (n != 0) {
            int n4 = Math.abs((n2 << 10) / n);
            n3 = CRes.atan(n4);
            if (n2 >= 0 && n < 0) {
                n3 = 180 - n3;
            }
            if (n2 < 0 && n < 0) {
                n3 = 180 + n3;
            }
            if (n2 < 0 && n >= 0) {
                n3 = 360 - n3;
            }
        } else {
            n3 = n2 > 0 ? 90 : 270;
        }
        return n3;
    }

    public static final int fixangle(int n) {
        if (n >= 360) {
            n %= 360;
        }
        if (n < 0) {
            n = 360 + n % 360;
        }
        return n;
    }

    public static final int subangle(int n, int n2) {
        int n3 = n2 - n;
        if (n3 < -180) {
            return n3 + 360;
        }
        if (n3 > 180) {
            return n3 - 360;
        }
        return n3;
    }

    public static int abs(int n) {
        if (n < 0) {
            return -n;
        }
        return n;
    }

    public static int random(int n) {
        return r.nextInt(n);
    }

    public static int random_Am_0(int n) {
        int n2 = 0;
        while (n2 == 0) {
            n2 = r.nextInt() % n;
        }
        return n2;
    }

    public static int random_Am(int n, int n2) {
        int n3 = n + r.nextInt(n2 - n);
        if (CRes.random(2) == 0) {
            n3 = -n3;
        }
        return n3;
    }

    public static int random(int n, int n2) {
        return n + r.nextInt(n2 - n);
    }

    public static int sqrt(int n) {
        int n2;
        if (n <= 0) {
            return 0;
        }
        int n3 = (n + 1) / 2;
        while (Math.abs((n2 = n3) - (n3 = n3 / 2 + n / (2 * n3))) > 1) {
        }
        return n3;
    }

    public static float sqrt(float f) {
        float f2;
        if (f <= 0.0f) {
            return 0.0f;
        }
        float f3 = (f + 1.0f) / 2.0f;
        while (Math.abs((f2 = f3) - (f3 = f3 / 2.0f + f / (2.0f * f3))) > 1.0f) {
        }
        return f3;
    }

    public static int setDis(int n, int n2, int n3, int n4) {
        return CRes.abs(n - n3) + CRes.abs(n2 - n4);
    }

    public static void quickSort(mVector mVector2) {
        CRes.recQuickSort(mVector2, 0, mVector2.size() - 1);
    }

    private static void recQuickSort(mVector mVector2, int n, int n2) {
        try {
            if (n2 - n <= 0) {
                return;
            }
            MainObject mainObject = (MainObject)mVector2.elementAt(n2);
            int n3 = mainObject.ySort;
            int n4 = CRes.partitionIt(mVector2, n, n2, n3);
            CRes.recQuickSort(mVector2, n, n4 - 1);
            CRes.recQuickSort(mVector2, n4 + 1, n2);
        }
        catch (Exception exception) {
            mSystem.println("loi Cres 1");
            exception.printStackTrace();
        }
    }

    private static int partitionIt(mVector mVector2, int n, int n2, int n3) {
        int n4 = n - 1;
        int n5 = n2;
        try {
            while (true) {
                if (((MainObject)mVector2.elementAt((int)(++n4))).ySort < n3) {
                    continue;
                }
                while (n5 > 0 && ((MainObject)mVector2.elementAt((int)(--n5))).ySort > n3) {
                }
                if (n4 >= n5) break;
                CRes.swap(mVector2, n4, n5);
            }
            CRes.swap(mVector2, n4, n2);
        }
        catch (Exception exception) {
            mSystem.println("loi Cres 2");
            exception.printStackTrace();
        }
        return n4;
    }

    private static void swap(mVector mVector2, int n, int n2) {
        Object object = mVector2.elementAt(n2);
        if (((MainObject)mVector2.elementAt((int)n2)).ySort != ((MainObject)mVector2.elementAt((int)n)).ySort) {
            mVector2.setElementAt(mVector2.elementAt(n), n2);
            mVector2.setElementAt(object, n);
        }
    }

    public static void quickSort1(mVector mVector2) {
        CRes.recQuickSort1(mVector2, 0, mVector2.size() - 1);
    }

    private static void recQuickSort1(mVector mVector2, int n, int n2) {
        try {
            if (n2 - n <= 0) {
                return;
            }
            MainItemMap mainItemMap = (MainItemMap)mVector2.elementAt(n2);
            int n3 = mainItemMap.y;
            int n4 = CRes.partitionIt1(mVector2, n, n2, n3);
            CRes.recQuickSort1(mVector2, n, n4 - 1);
            CRes.recQuickSort1(mVector2, n4 + 1, n2);
        }
        catch (Exception exception) {
            mSystem.println("loi Cres 1");
            exception.printStackTrace();
        }
    }

    private static int partitionIt1(mVector mVector2, int n, int n2, int n3) {
        int n4 = n - 1;
        int n5 = n2;
        try {
            while (true) {
                if (((MainItemMap)mVector2.elementAt((int)(++n4))).y < n3) {
                    continue;
                }
                while (n5 > 0 && ((MainItemMap)mVector2.elementAt((int)(--n5))).y > n3) {
                }
                if (n4 >= n5) break;
                CRes.swap1(mVector2, n4, n5);
            }
            CRes.swap1(mVector2, n4, n2);
        }
        catch (Exception exception) {
            mSystem.println("loi Cres 2");
            exception.printStackTrace();
        }
        return n4;
    }

    private static void swap1(mVector mVector2, int n, int n2) {
        Object object = mVector2.elementAt(n2);
        if (((MainItemMap)mVector2.elementAt((int)n2)).y != ((MainItemMap)mVector2.elementAt((int)n)).y) {
            mVector2.setElementAt(mVector2.elementAt(n), n2);
            mVector2.setElementAt(object, n);
        }
    }

    public static void quickSort(MainItemMap[] mainItemMapArray) {
        CRes.recQuickSort(mainItemMapArray, 0, mainItemMapArray.length - 1);
    }

    private static void recQuickSort(MainItemMap[] mainItemMapArray, int n, int n2) {
        try {
            if (n2 - n <= 0) {
                return;
            }
            int n3 = mainItemMapArray[n2].y;
            int n4 = CRes.partitionIt(mainItemMapArray, n, n2, n3);
            CRes.recQuickSort(mainItemMapArray, n, n4 - 1);
            CRes.recQuickSort(mainItemMapArray, n4 + 1, n2);
        }
        catch (Exception exception) {
            mSystem.println("loi Cres 3");
            exception.printStackTrace();
        }
    }

    private static int partitionIt(MainItemMap[] mainItemMapArray, int n, int n2, int n3) {
        int n4 = n - 1;
        int n5 = n2;
        try {
            while (true) {
                if (mainItemMapArray[++n4].y < n3) {
                    continue;
                }
                while (n5 > 0 && mainItemMapArray[--n5].y > n3) {
                }
                if (n4 >= n5) break;
                CRes.swap(mainItemMapArray, n4, n5);
            }
            CRes.swap(mainItemMapArray, n4, n2);
        }
        catch (Exception exception) {
            mSystem.println("LOI PAINT partitionIt TRONG UTIL");
            exception.printStackTrace();
        }
        return n4;
    }

    private static void swap(MainItemMap[] mainItemMapArray, int n, int n2) {
        MainItemMap mainItemMap = mainItemMapArray[n2];
        if (mainItemMapArray[n2].y != mainItemMapArray[n].y) {
            mainItemMapArray[n2] = mainItemMapArray[n];
            mainItemMapArray[n] = mainItemMap;
        }
    }

    public static MainInfoItem[] selectionSort(MainInfoItem[] mainInfoItemArray) {
        int n = mainInfoItemArray.length;
        for (int i = 0; i < n - 1; ++i) {
            int n2 = i;
            for (int j = i + 1; j < n; ++j) {
                if (mainInfoItemArray[j].id >= mainInfoItemArray[n2].id) continue;
                n2 = j;
            }
            if (n2 == i) continue;
            MainInfoItem mainInfoItem = mainInfoItemArray[i];
            mainInfoItemArray[i] = mainInfoItemArray[n2];
            mainInfoItemArray[n2] = mainInfoItem;
        }
        return mainInfoItemArray;
    }

    public static mVector selectionSortIDSkill(mVector mVector2) {
        int n = mVector2.size();
        for (int i = 0; i < n - 1; ++i) {
            int n2 = i;
            for (int j = i + 1; j < n; ++j) {
                if (((Skill)mVector2.elementAt((int)j)).Id >= ((Skill)mVector2.elementAt((int)n2)).Id) continue;
                n2 = j;
            }
            if (n2 == i) continue;
            CRes.swapSkill(mVector2, i, n2);
        }
        return mVector2;
    }

    public static mVector selectionSortSkill(mVector mVector2) {
        int n = mVector2.size();
        for (int i = 0; i < n - 1; ++i) {
            int n2 = i;
            for (int j = i + 1; j < n; ++j) {
                if (((Skill)mVector2.elementAt((int)j)).lvMin >= ((Skill)mVector2.elementAt((int)n2)).lvMin) continue;
                n2 = j;
            }
            if (n2 == i) continue;
            CRes.swapSkill(mVector2, i, n2);
        }
        return mVector2;
    }

    private static void swapSkill(mVector mVector2, int n, int n2) {
        Object object = mVector2.elementAt(n2);
        if (((Skill)mVector2.elementAt((int)n2)).lvMin != ((Skill)mVector2.elementAt((int)n)).lvMin) {
            mVector2.setElementAt(mVector2.elementAt(n), n2);
            mVector2.setElementAt(object, n);
        }
    }

    public static mVector selectionSortInven(mVector mVector2) {
        int n = mVector2.size();
        for (int i = 0; i < n - 1; ++i) {
            int n2 = i;
            for (int j = i + 1; j < n; ++j) {
                if (((MainItem)mVector2.elementAt((int)j)).IndexSort >= ((MainItem)mVector2.elementAt((int)n2)).IndexSort) continue;
                n2 = j;
            }
            if (n2 == i) continue;
            CRes.swapItem(mVector2, i, n2);
        }
        return mVector2;
    }

    private static void swapItem(mVector mVector2, int n, int n2) {
        Object object = mVector2.elementAt(n2);
        mVector2.setElementAt(mVector2.elementAt(n), n2);
        mVector2.setElementAt(object, n);
    }

    public static int readSignByte(InputStream inputStream) {
        byte[] byArray = new byte[1];
        try {
            inputStream.read(byArray, 0, 1);
        }
        catch (IOException iOException) {
            iOException.printStackTrace();
            mSystem.outloi("loi Cres 5");
        }
        return byArray[0];
    }

    public static void setRemoveCharPartInfo() {
        if (charPartInfo == null) {
            return;
        }
        for (int i = 0; i < charPartInfo.length; ++i) {
            for (int j = 0; j < charPartInfo[i].length; ++j) {
                if (charPartInfo[i][j] == null || (GameCanvas.timeNow - CRes.charPartInfo[i][j].timeRemove) / 1000L <= 60L) continue;
                CRes.charPartInfo[i][j] = null;
            }
        }
    }

    public static synchronized CharPartInfo getCharPartInfo(int n, int n2) {
        if (charPartInfo[n][n2] == null) {
            CRes.charPartInfo[n][n2] = new CharPartInfo((byte)n, (short)n2);
        }
        return charPartInfo[n][n2];
    }

    public static void getImgWeaPone(int n, int n2, int n3) {
        block18: {
            try {
                InputStream inputStream;
                Object object;
                Object object2;
                MainObject.imgWeapone[n][n2][n3] = new WeaponInfo();
                if (mSystem.isImgLocal) {
                    object2 = new String[]{"kiem/", "songkiem/", "phapsu/", "sung/"};
                    object = null;
                    try {
                        object = mImage.createImage("/weapon/" + object2[n] + n2 + ".img");
                    }
                    catch (Exception exception) {
                        // empty catch block
                    }
                    if (object != null && ((mImage)object).image != null) {
                        inputStream = null;
                        try {
                            inputStream = mImage.openFile("/weapon/" + object2[n] + n2 + "_data");
                        }
                        catch (Exception exception) {
                            // empty catch block
                        }
                        if (inputStream != null) {
                            MainObject.imgWeapone[n][n2][n3].img = object;
                            for (int i = 0; i < 4; ++i) {
                                for (int j = 0; j < 3; ++j) {
                                    MainObject.imgWeapone[n][n2][n3].mPos[i][j][0] = (byte)inputStream.read();
                                    MainObject.imgWeapone[n][n2][n3].mPos[i][j][1] = (byte)inputStream.read();
                                }
                                MainObject.imgWeapone[n][n2][n3].mRegion[i][0] = (byte)inputStream.read();
                                MainObject.imgWeapone[n][n2][n3].mRegion[i][1] = (byte)inputStream.read();
                            }
                            MainObject.imgWeapone[n][n2][n3].himg = mImage.getImageHeight(MainObject.imgWeapone[n][n2][n3].img.image);
                            return;
                        }
                    }
                }
                if ((object2 = (String[])hashWeapon.get(n + "_" + n2)) == null) {
                    object2 = new MainImageDataPartChar();
                    hashWeapon.put(n + "_" + n2, object2);
                    object2.timeImageNull = GameCanvas.timeNow;
                    CRes.getFromRms((byte)n, (short)n2);
                    MainObject.imgWeapone[n][n2][n3] = null;
                    break block18;
                }
                if (object2.img != null) {
                    MainObject.imgWeapone[n][n2][n3].img = object2.img;
                    inputStream = new ByteArrayInputStream(object2.isData);
                    object = new DataInputStream(inputStream);
                    FilePack.reset();
                    try {
                        for (int i = 0; i < 4; ++i) {
                            for (int j = 0; j < 3; ++j) {
                                MainObject.imgWeapone[n][n2][n3].mPos[i][j][0] = (byte)((InputStream)object).read();
                                MainObject.imgWeapone[n][n2][n3].mPos[i][j][1] = (byte)((InputStream)object).read();
                            }
                            MainObject.imgWeapone[n][n2][n3].mRegion[i][0] = (byte)((InputStream)object).read();
                            MainObject.imgWeapone[n][n2][n3].mRegion[i][1] = (byte)((InputStream)object).read();
                        }
                        MainObject.imgWeapone[n][n2][n3].himg = mImage.getImageHeight(MainObject.imgWeapone[n][n2][n3].img.image);
                    }
                    catch (Exception exception) {
                        // empty catch block
                    }
                    hashWeapon.remove(object2);
                    break block18;
                }
                if ((GameCanvas.timeNow - object2.timeImageNull) / 1000L >= 15L) {
                    CRes.getFromRms((byte)n, (short)n2);
                }
            }
            catch (Exception exception) {
                exception.printStackTrace();
                mSystem.println("loi Cres 7");
            }
        }
        FilePack.reset();
    }

    public static void getFromRms(byte by, short s) {
        MainImageDataPartChar mainImageDataPartChar = (MainImageDataPartChar)hashWeapon.get(by + "_" + s);
        if (mainImageDataPartChar != null) {
            mainImageDataPartChar.timeImageNull = GameCanvas.timeNow;
        }
        GlobalService.gI().load_image_data_part_char((byte)(by + 50), s);
    }

    public static WPSplashInfo GetWPSplashInfo(int n, int n2, int n3) {
        try {
            String[] stringArray = new String[]{"kiem/", "songkiem/", "phapsu/", "sung/"};
            CRes.wpSplashInfos[n][n2][n3] = new WPSplashInfo();
            CRes.wpSplashInfos[n][n2][n3].image = mImage.createImage("/wps/" + stringArray[n] + n2 + ".img");
            DataInputStream dataInputStream = mImage.openFile("/wps/" + stringArray[n] + n2 + "_data");
            if (dataInputStream != null) {
                for (int i = 0; i < 4; ++i) {
                    for (int j = 0; j < 8; ++j) {
                        CRes.wpSplashInfos[n][n2][n3].P0_X[i][j] = ((InputStream)dataInputStream).read();
                        CRes.wpSplashInfos[n][n2][n3].P0_Y[i][j] = ((InputStream)dataInputStream).read();
                        CRes.wpSplashInfos[n][n2][n3].PF_X[i][j] = CRes.readSignByte(dataInputStream);
                        CRes.wpSplashInfos[n][n2][n3].PF_Y[i][j] = CRes.readSignByte(dataInputStream);
                        CRes.wpSplashInfos[n][n2][n3].PF_W[i][j] = ((InputStream)dataInputStream).read();
                        CRes.wpSplashInfos[n][n2][n3].PF_H[i][j] = ((InputStream)dataInputStream).read();
                    }
                }
            }
            FilePack.reset();
        }
        catch (IOException iOException) {
            CRes.wpSplashInfos[n][n2][n3] = null;
            mSystem.println("loi Cres 8");
            iOException.printStackTrace();
        }
        return wpSplashInfos[n][n2][n3];
    }

    public static WeaponInfo loadImgWeaPone(int n, int n2, int n3) {
        if (MainObject.imgWeapone[n][n2][n3] == null) {
            Load_Data_And_Img.load.loadImgWeaPone(n, n2, n3);
        }
        return MainObject.imgWeapone[n][n2][n3];
    }

    public static WPSplashInfo loadWpsPlash(int n, int n2, int n3) {
        if (wpSplashInfos[n2][n3] == null) {
            Load_Data_And_Img.load.loadWpsPlash(n2, n3);
        }
        return wpSplashInfos[n][n2][n3];
    }

    public static byte[] encoding(byte[] byArray) {
        if (byArray != null) {
            for (int i = 0; i < byArray.length; ++i) {
                byArray[i] = ~byArray[i];
            }
        }
        return byArray;
    }

    public static void saveRMS(String string, byte[] byArray) throws Exception {
        TemMidlet.saveRMS(string, byArray);
    }

    public static byte[] loadRMS(String string) {
        return TemMidlet.loadRMS(string);
    }

    public static String loadRMSString(String string) {
        byte[] byArray = CRes.loadRMS(string);
        if (byArray == null) {
            return null;
        }
        try {
            String string2 = new String(byArray, "UTF-8");
            return string2;
        }
        catch (Exception exception) {
            return new String(byArray);
        }
    }

    public static void saveRMSString_(String string, String string2) {
        try {
            CRes.saveRMS(string, string2.getBytes("UTF-8"));
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public static void saveRMSName(byte by, byte[] byArray) throws Exception {
        GlobalService.gI().Save_RMS_Server((byte)0, by, byArray);
    }

    public static void saveRMSString(String string, String string2) throws Exception {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            dataOutputStream.writeUTF(string2);
            CRes.saveRMS(string, byteArrayOutputStream.toByteArray());
            dataOutputStream.close();
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public static boolean ktvc(int n, int n2, int n3, int n4, int n5, int n6, int n7, int n8) {
        return n <= n4 && n2 >= n3 && n5 <= n8 && n6 >= n7;
    }

    public static boolean CheckDelRMS(String string) {
        return string.compareTo("isLowDevice") != 0 && string.compareTo("isQty") != 0 && string.compareTo("user_pass") != 0 && string.compareTo("isIndexPart") != 0 && string.compareTo("isIndexServer") != 0;
    }

    public static int rnd(int n, int n2) {
        int n3 = r.nextInt(2);
        if (n3 == 0) {
            return n;
        }
        return n2;
    }

    public static int rnd(int n) {
        return r.nextInt(n);
    }

    public static void load_TaiTho() {
        try {
            byte[] byArray = CRes.loadRMS("taitho");
            if (byArray != null) {
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byArray);
                DataInputStream dataInputStream = new DataInputStream(byteArrayInputStream);
                PaintInfoGameScreen.taitho = dataInputStream.readByte();
                PaintInfoGameScreen.xPointMove = PaintInfoGameScreen.taitho == 0 ? 55 : 85;
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public static void save_TaiTho() {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            dataOutputStream.writeByte(PaintInfoGameScreen.taitho);
            CRes.saveRMS("taitho", byteArrayOutputStream.toByteArray());
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public static void saveID_REGION() {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            dataOutputStream.writeByte(mSystem.ID_REGION);
            CRes.saveRMS(RMS_REGION, byteArrayOutputStream.toByteArray());
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    static {
        int n;
        sin = new short[]{0, 18, 36, 54, 71, 89, 107, 125, 143, 160, 178, 195, 213, 230, 248, 265, 282, 299, 316, 333, 350, 367, 384, 400, 416, 433, 449, 465, 481, 496, 512, 527, 543, 558, 573, 587, 602, 616, 630, 644, 658, 672, 685, 698, 711, 724, 737, 749, 761, 773, 784, 796, 807, 818, 828, 839, 849, 859, 868, 878, 887, 896, 904, 912, 920, 928, 935, 943, 949, 956, 962, 968, 974, 979, 984, 989, 994, 998, 1002, 1005, 1008, 1011, 1014, 1016, 1018, 1020, 1022, 1023, 1023, 1024, 1024};
        hashWeapon = new mHashtable();
        RMS_REGION = "region_Rms";
        cos = new short[91];
        tan = new int[91];
        for (n = 0; n <= 90; ++n) {
            CRes.cos[n] = sin[90 - n];
            CRes.tan[n] = cos[n] == 0 ? Integer.MAX_VALUE : (sin[n] << 10) / cos[n];
        }
        value = 1;
        r = new Random();
        charPartInfo = new CharPartInfo[7][];
        for (n = 0; n < charPartInfo.length; ++n) {
            CRes.charPartInfo[n] = new CharPartInfo[256];
        }
        wpSplashInfos = new WPSplashInfo[4][5][3];
    }
}

