/*
 * Decompiled with CFR 0.152.
 */
package Thread_More;

import CLib.mGraphics;
import CLib.mImage;
import CLib.mSystem;
import CLib.mVector;
import GameObjects.ThacNuoc;
import GameScreen.GameScreen;
import GameScreen.LoadMapScreen;
import GameScreen.MainScreen;
import Main.GameCanvas;
import Map.ItemMap;
import Map.MainItemMap;
import Model.CRes;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import netcommand.global.GlobalService;

public class LoadMap {
    public static final int T_MAP_NULL = -1;
    public static final int T_MAP_NORMAL = 0;
    public static final int T_MAP_STAND = 1;
    public static final int T_MAP_SLOW = 2;
    public static final int MAP_NORMAL = 0;
    public static final int MAP_THACH_DAU = 1;
    public static final int MAP_PHO_BANG = 2;
    public static final int MAP_PET_CONTAINER = 3;
    public static mVector vecMapItem = new mVector("LoadMap vecMapItem");
    public static mVector vecPointChange = new mVector("LoadMap vecPointChange");
    public static mVector mItemMap = new mVector("LoadMap mItemMap");
    public static int[] mTranPointChangeMap = new int[]{5, 4, 1, 0};
    public int idMap;
    public int idMapMini;
    public int mapW;
    public int mapH;
    public int limitW;
    public int limitH;
    public int limitMap;
    public int maxX = GameCanvas.w / wTile + 1;
    public int maxY = GameCanvas.h / wTile + 1;
    public int maxWMap;
    public int maxHMap;
    public static byte isShowEffAuto = 0;
    public static byte EFF_NORMAL = 0;
    public static byte EFF_PHOBANG_END = 1;
    public static int idTile = -1;
    public String nameMap = "";
    public static int wTile = 24;
    public int[] mapPaint;
    public int[] mapType;
    public static int[] mStatusArea;
    public byte[][] mapFind;
    public static mImage imgTile;
    public static mImage imgTileWater;
    public static mImage imgTileSmall;
    public static int timeVibrateScreen;
    public static byte Area;
    public static byte MaxArea;
    public static byte typeMap;
    public mImage itemLogin0;
    public mImage itemLogin1;
    public mImage itemLogin2;
    public static long lastTime;
    int fStand;
    int fWater;
    int fStartWater;
    int fEndWater;
    public static mVector Thacnuoc;
    public static MainItemMap[] mItemMapLogin;
    public int mapWLogin;
    public int mapHLogin;
    public int[] mapPaintLogin;
    public mImage imgTileLogin;

    public LoadMap() {
        this.load_Table_Map();
    }

    public boolean mapLang() {
        return GameScreen.isMapLang;
    }

    public void load_Table_Map() {
        try {
            DataInputStream dataInputStream = mImage.openFile("/table_item");
            short s = dataInputStream.readShort();
            for (short s2 = 0; s2 < s; s2 = (short)(s2 + 1)) {
                short s3 = dataInputStream.readShort();
                dataInputStream.readByte();
                short s4 = dataInputStream.readShort();
                short s5 = dataInputStream.readShort();
                int n = dataInputStream.readByte();
                int[][] nArray = mSystem.new_M_Int(n, 2);
                for (int i = 0; i < n; ++i) {
                    nArray[i][0] = dataInputStream.readByte();
                    nArray[i][1] = dataInputStream.readByte();
                }
                vecMapItem.addElement(new MainItemMap(s2, s3, s4, s5, nArray));
            }
        }
        catch (Exception exception) {
            mSystem.println("loi load map 1");
            exception.printStackTrace();
        }
    }

    public static void load_Table_Map(byte[] byArray) {
        try {
            vecMapItem.removeAllElements();
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byArray);
            DataInputStream dataInputStream = new DataInputStream(byteArrayInputStream);
            short s = dataInputStream.readShort();
            for (short s2 = 0; s2 < s; s2 = (short)(s2 + 1)) {
                short s3 = dataInputStream.readShort();
                dataInputStream.readByte();
                short s4 = dataInputStream.readShort();
                short s5 = dataInputStream.readShort();
                int n = dataInputStream.readByte();
                int[][] nArray = mSystem.new_M_Int(n, 2);
                for (int i = 0; i < n; ++i) {
                    nArray[i][0] = dataInputStream.readByte();
                    nArray[i][1] = dataInputStream.readByte();
                }
                vecMapItem.addElement(new MainItemMap(s2, s3, s4, s5, nArray));
            }
        }
        catch (Exception exception) {
            mSystem.outloi("loi load map 1");
        }
    }

    public void loadmap(byte[] byArray) {
        try {
            Object object;
            Thacnuoc.removeAllElements();
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byArray);
            DataInputStream dataInputStream = new DataInputStream(byteArrayInputStream);
            this.mapW = dataInputStream.readByte();
            this.mapH = dataInputStream.readByte();
            byte by = dataInputStream.readByte();
            if (idTile != by) {
                idTile = by;
                imgTile = null;
                imgTileWater = null;
                imgTileSmall = null;
                imgTile = mImage.createImage("/tile" + idTile + ".png");
                if (imgTile == null || LoadMap.imgTile.image == null) {
                    imgTile = LoadMap.getTileMapDataFromRMS((byte)100, (short)idTile);
                }
                if (imgTile == null || LoadMap.imgTile.image == null) {
                    GlobalService.gI().load_image((short)(idTile + 10400));
                }
                if ((imgTileWater = mImage.createImage("/tilewater" + idTile + ".png")) == null || LoadMap.imgTileWater.image == null) {
                    imgTileWater = LoadMap.getTileMapDataFromRMS((byte)102, (short)idTile);
                }
                if (imgTileWater == null || LoadMap.imgTileWater.image == null) {
                    GlobalService.gI().load_image((short)(idTile + 10500));
                }
                if ((imgTileSmall = mImage.createImage("/tile_small" + idTile + ".png")) == null || LoadMap.imgTileSmall.image == null) {
                    imgTileSmall = LoadMap.getTileMapDataFromRMS((byte)101, (short)idTile);
                }
                if (imgTileSmall == null || LoadMap.imgTileSmall.image == null) {
                    GlobalService.gI().load_image((short)(idTile + 10600));
                }
                DataInputStream dataInputStream2 = mImage.openFile("/tile_map_info_" + idTile);
                if (!mSystem.isMaHoa) {
                    this.fWater = ((InputStream)dataInputStream2).read();
                    this.fStand = ((InputStream)dataInputStream2).read();
                    if (LoadMap.isTileMoi()) {
                        if (idTile == 9) {
                            this.fWater = 127;
                            this.fStand = 19;
                            this.fStartWater = 19;
                            this.fEndWater = 26;
                        } else if (idTile == 10) {
                            this.fWater = 127;
                            this.fStand = 5;
                            this.fStartWater = 4;
                            this.fEndWater = 25;
                        } else if (idTile == 11) {
                            this.fWater = 127;
                            this.fStand = 21;
                            this.fStartWater = 20;
                            this.fEndWater = 37;
                        } else if (idTile == 12) {
                            this.fWater = 127;
                            this.fStand = 34;
                            this.fStartWater = 35;
                            this.fEndWater = 44;
                        } else if (idTile == 13) {
                            this.fWater = 0;
                            this.fStand = 47;
                            this.fStartWater = 0;
                            this.fEndWater = 0;
                        } else if (idTile == 14) {
                            this.fWater = 0;
                            this.fStand = 26;
                            this.fStartWater = 0;
                            this.fEndWater = 0;
                        } else if (idTile == 15) {
                            this.fWater = 0;
                            this.fStand = 9;
                            this.fStartWater = 0;
                            this.fEndWater = 0;
                        } else if (idTile == 16) {
                            this.fWater = 0;
                            this.fStand = 42;
                            this.fStartWater = 0;
                            this.fEndWater = 0;
                        }
                    }
                } else {
                    DataInputStream dataInputStream3 = new DataInputStream(dataInputStream2);
                    byte[] byArray2 = new byte[dataInputStream3.available()];
                    dataInputStream3.read(byArray2);
                    for (int i = 0; i < byArray2.length; ++i) {
                        byArray2[i] = (byte)(byArray2[i] ^ 3);
                    }
                    object = new DataInputStream(new ByteArrayInputStream(byArray2));
                    this.fWater = ((FilterInputStream)object).read();
                    this.fStand = ((FilterInputStream)object).read();
                    if (LoadMap.isTileMoi()) {
                        if (idTile == 9) {
                            this.fWater = 127;
                            this.fStand = 19;
                            this.fStartWater = 19;
                            this.fEndWater = 26;
                        } else if (idTile == 10) {
                            this.fWater = 127;
                            this.fStand = 5;
                            this.fStartWater = 4;
                            this.fEndWater = 25;
                        } else if (idTile == 11) {
                            this.fWater = 127;
                            this.fStand = 21;
                            this.fStartWater = 20;
                            this.fEndWater = 37;
                        } else if (idTile == 12) {
                            this.fWater = 127;
                            this.fStand = 34;
                            this.fStartWater = 35;
                            this.fEndWater = 44;
                        } else if (idTile == 13) {
                            this.fWater = 0;
                            this.fStand = 47;
                            this.fStartWater = 0;
                            this.fEndWater = 0;
                        } else if (idTile == 14) {
                            this.fWater = 0;
                            this.fStand = 26;
                            this.fStartWater = 0;
                            this.fEndWater = 0;
                        } else if (idTile == 15) {
                            this.fWater = 0;
                            this.fStand = 9;
                            this.fStartWater = 0;
                            this.fEndWater = 0;
                        } else if (idTile == 16) {
                            this.fWater = 0;
                            this.fStand = 42;
                            this.fStartWater = 0;
                            this.fEndWater = 0;
                        }
                    }
                }
            }
            this.maxWMap = this.mapW * wTile;
            this.maxHMap = this.mapH * wTile;
            this.limitW = this.maxWMap - GameCanvas.w;
            this.limitH = this.maxHMap - GameCanvas.h;
            MainScreen.cameraMain.setAll(this.limitW, this.limitH, GameScreen.player.x - GameCanvas.hw, GameScreen.player.y - GameCanvas.hh);
            this.mapPaint = new int[this.mapW * this.mapH];
            this.mapType = new int[this.mapW * this.mapH];
            this.mapFind = new byte[this.mapW][this.mapH];
            this.limitMap = this.mapW * this.mapH;
            for (int i = 0; i < this.mapW * this.mapH; ++i) {
                byte by2 = dataInputStream.readByte();
                this.mapPaint[i] = by2;
                if (!LoadMap.isTileMoi()) {
                    if (by2 >= this.fStand || by2 == 0) {
                        this.mapType[i] = 1;
                        continue;
                    }
                    if (by2 >= this.fWater) {
                        this.mapType[i] = 2;
                        continue;
                    }
                    this.mapType[i] = 0;
                    continue;
                }
                if (idTile == 9) {
                    if (by2 == 27 || by2 == 28 || by2 == 29 || by2 == 30 || by2 == 31) {
                        object = new ThacNuoc(by2 - 27, i % this.mapW * 24, i / this.mapW * 24);
                        Thacnuoc.addElement(object);
                    }
                } else if (idTile == 10) {
                    if (by2 == 18 || by2 == 19) {
                        object = new ThacNuoc(by2 - 18 + 5, i % this.mapW * 24, i / this.mapW * 24);
                        Thacnuoc.addElement(object);
                    }
                } else if (idTile == 11) {
                    if (by2 == 38 || by2 == 39) {
                        object = new ThacNuoc(by2 - 38 + 7, i % this.mapW * 24, i / this.mapW * 24);
                        Thacnuoc.addElement(object);
                    }
                } else if (idTile == 12 || idTile == 13 || idTile == 14) {
                    // empty if block
                }
                this.mapType[i] = by2 >= this.fStand || by2 == 0 ? 1 : 0;
            }
            isShowEffAuto = EFF_NORMAL;
        }
        catch (IOException iOException) {
            iOException.printStackTrace();
            mSystem.outloi("loi load map 2");
        }
    }

    public void load_ItemMap(byte[] byArray) {
        try {
            int n;
            Object object;
            int n2;
            int n3;
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byArray);
            DataInputStream dataInputStream = new DataInputStream(byteArrayInputStream);
            mItemMap.removeAllElements();
            int n4 = dataInputStream.readShort();
            int n5 = 0;
            for (n3 = 0; n3 < n4; ++n3) {
                n2 = dataInputStream.readShort();
                MainItemMap mainItemMap = this.get_Item(n2);
                if (mainItemMap == null) {
                    n2 = 85;
                    mainItemMap = this.get_Item(n2);
                }
                object = new ItemMap(mainItemMap.IDItem, mainItemMap.IDImage, mainItemMap.dx, mainItemMap.dy, mainItemMap.Block);
                short s = dataInputStream.readShort();
                n = dataInputStream.readShort();
                if (GameCanvas.lowGraphic && mainItemMap.Block.length <= 0) continue;
                this.Block_TileMap_Item(s, n, mainItemMap.Block);
                ((ItemMap)object).setInfoItem(s * wTile, n * wTile);
                mItemMap.addElement(object);
                ++n5;
            }
            if (dataInputStream.available() > 0 && !GameCanvas.lowGraphic) {
                n3 = dataInputStream.readShort();
                for (n2 = 0; n2 < n3; ++n2) {
                    int n6 = dataInputStream.readByte();
                    object = "";
                    for (int i = 0; i < n6; ++i) {
                        object = (String)object + (char)dataInputStream.readByte();
                    }
                    n6 = dataInputStream.readByte();
                    String string = "";
                    for (n = 0; n < n6; ++n) {
                        string = string + (char)dataInputStream.readByte();
                    }
                    if (mSystem.isj2me) continue;
                    mItemMap.addElement(GameScreen.addEffectAuto((String)object, string));
                }
            }
            CRes.quickSort1(mItemMap);
        }
        catch (Exception exception) {
            mSystem.println("loi load map 3");
            exception.printStackTrace();
        }
    }

    public void paint(mGraphics mGraphics2) {
        try {
            int n = MainScreen.cameraMain.xCam / wTile - 1;
            int n2 = MainScreen.cameraMain.yCam / wTile - 1;
            if (n < 0) {
                n = 0;
            }
            if (n2 < 0) {
                n2 = 0;
            }
            int n3 = n + this.maxX + 2;
            int n4 = n2 + this.maxY + 2;
            if (n3 > this.mapW) {
                n3 = this.mapW;
            }
            if (n4 > this.mapH) {
                n4 = this.mapH;
            }
            for (int i = n; i < n3; ++i) {
                for (int j = n2; j < n4 && LoadMapScreen.isNextMap; ++j) {
                    int n5 = this.mapPaint[j * this.mapW + i] - 1;
                    if (!LoadMap.isTileMoi()) {
                        if (n5 >= this.fWater - 1 && n5 < this.fStand - 1 && (GameCanvas.gameTick % 14 < 7 && i % 2 == 0 || GameCanvas.gameTick % 14 > 7 && i % 2 != 0)) {
                            if (imgTileWater == null || LoadMap.imgTileWater.image == null) continue;
                            mGraphics2.drawRegion(imgTileWater, (n5 - (this.fWater - 1)) / 10 * wTile, (n5 - (this.fWater - 1)) % 10 * wTile, wTile, wTile, 0, i * wTile, j * wTile, 0, false);
                            continue;
                        }
                        if (n5 <= -1 || imgTile == null || LoadMap.imgTile.image == null) continue;
                        mGraphics2.drawRegion(imgTile, 0, n5 * wTile, wTile, wTile, 0, i * wTile, j * wTile, 0, false);
                        continue;
                    }
                    if (n5 >= this.fStartWater - 1 && n5 < this.fEndWater && (GameCanvas.gameTick % 14 < 7 && i % 2 == 0 || GameCanvas.gameTick % 14 > 7 && i % 2 != 0)) {
                        if (imgTileWater == null || LoadMap.imgTileWater.image == null) continue;
                        mGraphics2.drawRegion(imgTileWater, (n5 - (this.fStartWater - 1)) / 10 * wTile, (n5 - (this.fStartWater - 1)) % 10 * wTile, wTile, wTile, 0, i * wTile, j * wTile, 0, false);
                        continue;
                    }
                    if (n5 <= -1 || imgTile == null || LoadMap.imgTile.image == null) continue;
                    mGraphics2.drawRegion(imgTile, 0, n5 * wTile, wTile, wTile, 0, i * wTile, j * wTile, 0, false);
                }
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void update() {
    }

    public int getTile(int n, int n2) {
        int n3 = n2 / wTile * this.mapW + n / wTile;
        if (n3 > this.limitMap || n < 0 || n >= this.limitW + GameCanvas.w || n2 < 0 || n2 >= this.limitH + GameCanvas.h) {
            return 1;
        }
        return this.mapType[n3];
    }

    public int getIndex(int n, int n2) {
        return n2 / wTile * this.mapW + n / wTile;
    }

    public MainItemMap get_Item(int n) {
        for (int i = 0; i < vecMapItem.size(); ++i) {
            MainItemMap mainItemMap = (MainItemMap)vecMapItem.elementAt(i);
            if (mainItemMap.IDItem != n) continue;
            return mainItemMap;
        }
        return null;
    }

    public void Block_TileMap_Item(int n, int n2, int[][] nArray) {
        try {
            for (int i = 0; i < nArray.length; ++i) {
                if (n + nArray[i][0] < 0 || n + nArray[i][0] >= this.mapW || n2 + nArray[i][1] < 0 || n2 + nArray[i][1] >= this.mapH) continue;
                this.mapType[(n2 + nArray[i][1]) * this.mapW + (n + nArray[i][0])] = 1;
            }
        }
        catch (Exception exception) {
            mSystem.println("loi load map 4");
            exception.printStackTrace();
        }
    }

    public void setBlockNPC(int n, int n2, int n3, int n4) {
        if (this.mapW == 0) {
            this.mapW = 1;
        }
        int n5 = this.getIndex(n, n2);
        for (int i = 0; i < n3; ++i) {
            for (int j = 0; j < n4; ++j) {
                if (n5 % this.mapW - n3 / 2 + i < 0 || n5 % this.mapW - n3 / 2 + i >= this.mapW || n5 / this.mapW - n4 / 2 + j < 0 || n5 / this.mapW - n4 / 2 >= this.mapH) continue;
                this.mapType[(n5 / this.mapW - n4 / 2 + j) * this.mapW + (n5 % this.mapW - n3 / 2 + i)] = 1;
            }
        }
    }

    public void setBlockNPC_Server(int n, int n2, int n3, int n4) {
        if (this.mapW == 0) {
            this.mapW = 1;
        }
        int n5 = this.getIndex(n, n2);
        for (int i = 0; i < n3; ++i) {
            for (int j = 0; j < n4; ++j) {
                if (n5 % this.mapW - n3 / 2 + i < 0 || n5 % this.mapW - n3 / 2 + i >= this.mapW || n5 / this.mapW - n4 / 2 + j < 0 || n5 / this.mapW - n4 / 2 >= this.mapH) continue;
                this.mapType[(n5 / this.mapW - n4 / 2 + j) * this.mapW + (n5 % this.mapW + i)] = 1;
            }
        }
    }

    public static byte getAreaPaint() {
        return (byte)(Area + 1);
    }

    public static String getTimeSpecialRegion() {
        String string = "";
        long l = (GameScreen.timeSpRegion - mSystem.currentTimeMillis()) / 1000L;
        if (l <= 0L) {
            return "";
        }
        long l2 = l / 3600L;
        long l3 = l / 60L;
        long l4 = l;
        string = l2 > 0L ? l2 + "h" + l % 3600L / 60L + "'" : (l3 > 0L ? l3 + "p" + l % 60L + "s" : l4 + "s");
        return GameScreen.nameSpecialRegion + "" + string;
    }

    public static mImage getTileMapDataFromRMS(byte by, short s) {
        mImage mImage2 = new mImage();
        lastTime = GameCanvas.timeNow;
        GlobalService.gI().load_image_data_part_char(by, s);
        return mImage2;
    }

    public static boolean isTileMoi() {
        return idTile >= 9;
    }

    public static String getTimeArena(long l) {
        String string = "";
        long l2 = (l + 3600000L - mSystem.currentTimeMillis()) / 1000L;
        if (l2 <= 0L) {
            return "";
        }
        long l3 = l2 / 60L;
        long l4 = l2;
        string = l3 > 0L ? (l3 < 10L ? (l2 % 60L >= 0L && l2 % 60L < 10L ? "0" + l3 + ":" + "0" + l2 % 60L : "0" + l3 + ":" + l2 % 60L) : (l2 % 60L >= 0L && l2 % 60L < 10L ? l3 + ":" + "0" + l2 % 60L : l3 + ":" + l2 % 60L)) : (l4 < 10L ? "0" + l4 + "s" : l4 + "s");
        return string;
    }

    public static String convertSecondsToHMmSs(long l) {
        int n = (int)(l / 1000L) % 60;
        int n2 = (int)(l / 60000L % 60L);
        int n3 = (int)(l / 3600000L % 24L);
        if (n <= 0 && n3 <= 0 && n2 <= 0) {
            return "0h: 0': 0";
        }
        return n3 + "h: " + n2 + "': " + n;
    }

    public static String getTimeCountDown(long l, int n) {
        String string = "";
        long l2 = (l + (long)(n * 1000) - mSystem.currentTimeMillis()) / 1000L;
        if (l2 <= 0L) {
            return "";
        }
        long l3 = l2 / 60L;
        long l4 = l2;
        string = l3 > 0L ? (l3 < 10L ? (l2 % 60L >= 0L && l2 % 60L < 10L ? "0" + l3 + ":" + "0" + l2 % 60L : "0" + l3 + ":" + l2 % 60L) : (l2 % 60L >= 0L && l2 % 60L < 10L ? l3 + ":" + "0" + l2 % 60L : l3 + ":" + l2 % 60L)) : (l4 < 10L ? "0" + l4 + "s" : l4 + "s");
        return string;
    }

    public static String converSecon2hours(int n) {
        int n2 = n % 60;
        int n3 = n / 60;
        int n4 = n3 % 60;
        int n5 = n3 / 60;
        if (n5 > 0) {
            return n5 + ":" + n4;
        }
        if (n4 > 0) {
            return n4 + ":" + n2;
        }
        if (n2 < 10) {
            return "0:" + n2;
        }
        return n2 + "";
    }

    static {
        timeVibrateScreen = 0;
        Area = 0;
        MaxArea = (byte)10;
        typeMap = 0;
        lastTime = 0L;
        Thacnuoc = new mVector("LoadMap Thacnuoc");
        mItemMapLogin = new MainItemMap[0];
    }
}

