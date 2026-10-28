/*
 * Decompiled with CFR 0.152.
 */
package GameObjects;

import CLib.mGraphics;
import CLib.mHashtable;
import CLib.mImage;
import CLib.mSystem;
import Main.GameCanvas;
import Model.AvMain;
import Model.CRes;
import Model.FilePack;
import Model.MainImageDataPartChar;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.InputStream;
import netcommand.global.GlobalService;

public class CharPartInfo {
    public static mHashtable hashImagePartChar = new mHashtable();
    public byte type;
    public short id;
    public long timeRemove = 0L;
    public long timeRe = 0L;
    public int[][] x;
    public int[][] y;
    public int[][] w;
    public int[][] h;
    public int[][] dx;
    public int[][] dy;
    public byte[] arrData;
    public mImage image = null;
    int avxf;
    int avyf;
    int avx0;
    int avy0;
    int avw0;
    int avh0;
    public static final byte[][] PART_OF_FRAME = new byte[][]{{0, 0, 1, 2, 3, 4}, {0, 0, 1, 2, 3, 4}, {0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0}, {0, 1, 0, 1, 0, 1}};

    public void load(byte[] byArray, int n, int n2) {
        FilePack.initByArray(byArray);
        this.image = FilePack.getImg(n2 + "");
        InputStream inputStream = FilePack.instance.loadData(n2 + ".d");
        FilePack.reset();
        this.avx0 = CRes.readSignByte(inputStream);
        this.avy0 = CRes.readSignByte(inputStream);
        this.avw0 = CRes.readSignByte(inputStream);
        this.avh0 = CRes.readSignByte(inputStream);
        this.avxf = CRes.readSignByte(inputStream);
        this.avyf = CRes.readSignByte(inputStream);
        try {
            for (int i = 0; i < 4; ++i) {
                int n3;
                for (n3 = 0; n3 < this.x[i].length; ++n3) {
                    this.x[i][n3] = inputStream.read();
                    this.y[i][n3] = inputStream.read();
                    this.w[i][n3] = inputStream.read();
                    this.h[i][n3] = inputStream.read();
                }
                for (n3 = 0; n3 < 6; ++n3) {
                    this.dx[i][n3] = CRes.readSignByte(inputStream);
                    this.dy[i][n3] = CRes.readSignByte(inputStream);
                }
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
            mSystem.println("loi CharPart 1");
        }
    }

    public void loadNew(byte by, short s) {
        DataInputStream dataInputStream;
        Object object;
        if (mSystem.isImgLocal && this.timeRe == 0L) {
            this.timeRe = GameCanvas.timeNow;
            object = null;
            try {
                object = mImage.createImage("/c/" + FilePack.charAvatar[by] + "/b" + s + "_" + FilePack.charAvatar[by] + ".png");
            }
            catch (Exception exception) {
                object = null;
            }
            if (object != null && ((mImage)object).image != null) {
                dataInputStream = null;
                try {
                    dataInputStream = mImage.openFile("/c/" + FilePack.charAvatar[by] + "/b" + s + "_" + FilePack.charAvatar[by] + "_data");
                }
                catch (Exception exception) {
                    // empty catch block
                }
                if (dataInputStream != null) {
                    this.image = object;
                    FilePack.reset();
                    try {
                        int n;
                        int n2;
                        for (n2 = 0; n2 < 3; ++n2) {
                            for (n = 0; n < this.x[n2].length; ++n) {
                                this.x[n2][n] = ((InputStream)dataInputStream).read();
                                this.y[n2][n] = ((InputStream)dataInputStream).read();
                                this.w[n2][n] = ((InputStream)dataInputStream).read();
                                this.h[n2][n] = ((InputStream)dataInputStream).read();
                            }
                        }
                        for (n2 = 0; n2 < 4; ++n2) {
                            for (n = 0; n < 6; ++n) {
                                this.dx[n2][n] = CRes.readSignByte(dataInputStream);
                                this.dy[n2][n] = CRes.readSignByte(dataInputStream);
                            }
                        }
                    }
                    catch (Exception exception) {
                        mSystem.println("loi ham char par" + exception.toString());
                        exception.printStackTrace();
                    }
                    return;
                }
            }
        }
        if ((object = (MainImageDataPartChar)hashImagePartChar.get(by + "_" + s)) == null) {
            this.timeRemove = GameCanvas.timeNow;
            object = new MainImageDataPartChar();
            hashImagePartChar.put(by + "_" + s, object);
            this.getFromRms(by, s);
        } else if (((MainImageDataPartChar)object).img != null) {
            this.image = ((MainImageDataPartChar)object).img;
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(((MainImageDataPartChar)object).isData);
            dataInputStream = new DataInputStream(byteArrayInputStream);
            FilePack.reset();
            try {
                int n;
                int n3;
                for (n3 = 0; n3 < 3; ++n3) {
                    for (n = 0; n < this.x[n3].length; ++n) {
                        this.x[n3][n] = ((InputStream)dataInputStream).read();
                        this.y[n3][n] = ((InputStream)dataInputStream).read();
                        this.w[n3][n] = ((InputStream)dataInputStream).read();
                        this.h[n3][n] = ((InputStream)dataInputStream).read();
                    }
                }
                for (n3 = 0; n3 < 4; ++n3) {
                    for (n = 0; n < 6; ++n) {
                        this.dx[n3][n] = CRes.readSignByte(dataInputStream);
                        this.dy[n3][n] = CRes.readSignByte(dataInputStream);
                    }
                }
            }
            catch (Exception exception) {
                exception.printStackTrace();
                mSystem.outloi("loi CharPart 3");
            }
            hashImagePartChar.remove(object);
        } else if ((GameCanvas.timeNow - this.timeRemove) / 1000L >= 15L) {
            this.getFromRms(by, s);
        }
    }

    public CharPartInfo(byte by, short s) {
        this.type = by;
        this.id = s;
        this.dx = mSystem.new_M_Int(4, 6);
        this.dy = mSystem.new_M_Int(4, 6);
        int n = 0;
        switch (by) {
            case 0: 
            case 1: {
                n = 5;
                break;
            }
            case 2: 
            case 3: 
            case 4: 
            case 5: {
                n = 1;
                break;
            }
            case 6: {
                n = 2;
                break;
            }
        }
        this.x = mSystem.new_M_Int(4, n);
        this.y = mSystem.new_M_Int(4, n);
        this.w = mSystem.new_M_Int(4, n);
        this.h = mSystem.new_M_Int(4, n);
        this.loadNew(by, s);
    }

    public void paint(mGraphics mGraphics2, int n, int n2, int n3, int n4) {
        try {
            if (this.type < 0) {
                return;
            }
            int n5 = n3;
            if (n5 > 2) {
                n5 = 2;
            }
            if (this.image != null && this.image.image != null) {
                int n6 = this.dx[n3][n4];
                int n7 = this.dy[n3][n4];
                if (n3 > 2) {
                    n6 = -this.dx[n5][n4] - this.w[n5][PART_OF_FRAME[this.type][n4]];
                    n7 = this.dy[n5][n4];
                }
                mGraphics2.drawRegion(this.image, this.x[n5][PART_OF_FRAME[this.type][n4]], this.y[n5][PART_OF_FRAME[this.type][n4]], this.w[n5][PART_OF_FRAME[this.type][n4]], this.h[n5][PART_OF_FRAME[this.type][n4]], n3 > 2 ? 2 : 0, n + n6, n2 + n7, 0, true);
                this.timeRemove = GameCanvas.timeNow;
            } else {
                this.loadNew(this.type, this.id);
            }
        }
        catch (Exception exception) {
            mSystem.println("loi day ne " + this.id);
        }
    }

    public void paintShow(mGraphics mGraphics2, int n, int n2, int n3, int n4) {
        if (this.type < 0) {
            return;
        }
        int n5 = n3;
        if (n5 > 2) {
            n5 = 2;
        }
        if (this.image != null && this.image.image != null) {
            mGraphics2.drawRegion(this.image, this.x[n5][PART_OF_FRAME[this.type][n4]], this.y[n5][PART_OF_FRAME[this.type][n4]], this.w[n5][PART_OF_FRAME[this.type][n4]], this.h[n5][PART_OF_FRAME[this.type][n4]], n3 > 2 ? 2 : 0, n + this.dx[n3][n4], n2 - this.h[n5][PART_OF_FRAME[this.type][n4]] / 2, 0, true);
            this.timeRemove = GameCanvas.timeNow;
        } else {
            this.loadNew(this.type, this.id);
            mGraphics2.drawRegion(AvMain.imgLoadImg, 0, GameCanvas.gameTick % 12 * 16, 16, 16, 0, n, n2, 3, true);
        }
    }

    public void paintAvatar(mGraphics mGraphics2, short s, short s2, int n) {
        if (this.image != null && this.image.image != null) {
            mGraphics2.drawRegion(this.image, this.avx0, this.avy0, this.avw0, this.avh0, 0, s + this.avxf, s2 + this.avyf, 0, true);
        }
    }

    public void getFromRms(byte by, short s) {
        this.timeRemove = GameCanvas.timeNow;
        GlobalService.gI().load_image_data_part_char(by, s);
    }
}

