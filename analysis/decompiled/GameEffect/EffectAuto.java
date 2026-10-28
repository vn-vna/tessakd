/*
 * Decompiled with CFR 0.152.
 */
package GameEffect;

import CLib.mFont;
import CLib.mGraphics;
import CLib.mHashtable;
import CLib.mImage;
import CLib.mSound;
import CLib.mSystem;
import GameEffect.DataEffAuto;
import GameEffect.MainEffectAuto;
import GameScreen.GameScreen;
import Main.GameCanvas;
import Main.ImageEffectAuto;
import Map.MainItemMap;
import Model.CRes;
import Model.MainPartImage;
import Thread_More.LoadMap;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.util.Enumeration;
import netcommand.global.GlobalService;

public class EffectAuto
extends MainItemMap {
    MainEffectAuto eff;
    int nCountReplay = 0;
    boolean isPaint = true;
    boolean isUpdate = false;
    int typeEffect;
    int valueEffect;
    int f;
    long timeBegin;
    public boolean wantdestroy;
    int indexSound = -1;
    int timePlaySound = -1;
    public static long timeSoundFIRE = 0L;
    public static long timeSoundWATER = 0L;
    public static long timeSoundPHUNNUOC = 0L;
    public static long timeSoundGIOTNUOC = 0L;
    public static long lastTime;
    public long timelive;
    public short Loop;
    byte typedata = 0;
    public static mHashtable ALL_DATA_EFF_AUTO;
    public int dxx;
    public int dyy;
    public byte Typemove;

    public EffectAuto(String string, String string2) {
        this.TypeItem = 1;
        String[] stringArray = mFont.split(string2, ";");
        this.IDItem = Short.parseShort(stringArray[0]);
        this.IDImage = Short.parseShort(stringArray[1]);
        this.x = Integer.parseInt(stringArray[2]) * LoadMap.wTile;
        this.y = Integer.parseInt(stringArray[3]) * LoadMap.wTile;
        this.dx = Integer.parseInt(stringArray[4]);
        this.dy = Integer.parseInt(stringArray[5]);
        this.typeEffect = Integer.parseInt(stringArray[6]);
        this.valueEffect = Integer.parseInt(stringArray[7]);
        this.wOne = 70;
        this.hOne = 70;
        this.setDataEff(null);
    }

    public EffectAuto(int n, int n2, int n3, int n4, int n5, int n6, int n7) {
        this.TypeItem = 1;
        this.IDItem = (short)n;
        this.IDImage = (short)n;
        this.x = n2;
        this.y = n3;
        this.dx = n4;
        this.dy = n5;
        this.typeEffect = n6;
        this.valueEffect = n7;
        this.wOne = 70;
        this.hOne = 70;
        this.dxx = 0;
        this.dyy = 0;
        this.setDataEff(null);
    }

    public EffectAuto(int n, int n2, int n3, int n4, int n5, int n6, int n7, byte[] byArray, long l, byte by, int n8, int n9) {
        this.typedata = 1;
        this.TypeItem = (byte)2;
        this.IDItem = (short)n;
        this.IDImage = (short)n;
        this.x = n2;
        this.y = n3;
        this.dx = n4;
        this.dy = n5;
        this.typeEffect = n6;
        this.valueEffect = n7;
        this.wOne = 70;
        this.hOne = 70;
        this.timelive = l;
        this.Typemove = by;
        this.dxx = n8;
        this.dyy = n9;
        this.setdataFromSV(byArray);
    }

    public EffectAuto(int n, int n2, int n3, int n4, int n5, int n6, int n7, byte[] byArray) {
        this.typedata = 1;
        this.TypeItem = 1;
        this.IDItem = (short)n;
        this.IDImage = (short)n;
        this.x = n2;
        this.y = n3;
        this.dx = n4;
        this.dy = n5;
        this.typeEffect = n6;
        this.valueEffect = n7;
        this.wOne = 70;
        this.hOne = 70;
        this.dxx = 0;
        this.dyy = 0;
        this.setdataFromSV(byArray);
    }

    public EffectAuto(int n, int n2, int n3, byte[] byArray) {
        this.typedata = 1;
        this.TypeItem = (byte)3;
        this.IDItem = (short)n;
        this.IDImage = (short)n;
        this.x = n2;
        this.y = n3;
        this.wOne = 70;
        this.hOne = 70;
        this.dxx = 0;
        this.dyy = 0;
        this.setdataFromSV(byArray);
    }

    public byte[] getdataFromRMS() {
        byte[] byArray = null;
        try {
            byte[] byArray2 = CRes.loadRMS("data_eff" + this.IDImage);
            if (byArray2 != null) {
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byArray2);
                DataInputStream dataInputStream = new DataInputStream(byteArrayInputStream);
                byArray = new byte[dataInputStream.readShort()];
                dataInputStream.read(byArray);
                dataInputStream.close();
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
        return byArray;
    }

    public EffectAuto(int n, int n2, int n3, int n4, int n5, int n6, int n7, byte[] byArray, short s) {
        this.typedata = 1;
        this.TypeItem = 1;
        this.IDItem = (short)n;
        this.IDImage = (short)n;
        this.x = n2;
        this.y = n3;
        this.dx = n4;
        this.dy = n5;
        this.typeEffect = n6;
        this.valueEffect = n7;
        this.wOne = 70;
        this.hOne = 70;
        this.dxx = 0;
        this.dyy = 0;
        this.Loop = s;
        this.setdataFromSV(byArray);
    }

    public void setdataFromSV(byte[] byArray) {
        if (byArray != null && byArray.length > 0) {
            this.eff = this.loadTemEff(this.IDImage, byArray);
            this.timeBegin = GameCanvas.timeNow;
            this.isPaint = true;
            this.isUpdate = true;
            this.setPlaySound();
        } else {
            DataEffAuto dataEffAuto = (DataEffAuto)ALL_DATA_EFF_AUTO.get("" + this.IDImage);
            if (dataEffAuto == null) {
                byte[] byArray2 = this.getdataFromRMS();
                if (byArray2 != null && byArray2.length > 0) {
                    dataEffAuto = new DataEffAuto(this.IDImage);
                    dataEffAuto.setdata(byArray2);
                    ALL_DATA_EFF_AUTO.put(this.IDImage + "", dataEffAuto);
                    this.eff = this.loadTemEff(this.IDImage, dataEffAuto.data);
                    this.timeBegin = GameCanvas.timeNow;
                    this.isPaint = true;
                    this.isUpdate = true;
                    dataEffAuto.timeremove = (int)(mSystem.currentTimeMillis() / 1000L);
                    this.setPlaySound();
                } else {
                    dataEffAuto = new DataEffAuto(this.IDImage);
                    ALL_DATA_EFF_AUTO.put(this.IDImage + "", dataEffAuto);
                    ImageEffectAuto.setImage(this.IDImage);
                    dataEffAuto.timeremove = (int)(mSystem.currentTimeMillis() / 1000L);
                }
            }
            if (dataEffAuto != null && dataEffAuto.data != null && dataEffAuto.data.length > 0) {
                this.eff = this.loadTemEff(this.IDImage, dataEffAuto.data);
                this.timeBegin = GameCanvas.timeNow;
                this.isPaint = true;
                this.isUpdate = true;
                this.setPlaySound();
            }
        }
    }

    public void setDataEff(byte[] byArray) {
        this.eff = this.loadTemEff(this.IDImage, byArray);
        this.timeBegin = GameCanvas.timeNow;
        this.isPaint = true;
        this.isUpdate = true;
        this.setPlaySound();
    }

    public void paintMatna(mGraphics mGraphics2) {
        if (this.typedata == 1 && !this.isUpdate) {
            this.setdataFromSV(null);
            return;
        }
        try {
            if (this.eff != null && this.isPaint) {
                short s = this.eff.mRunFrame[this.f];
                int n = this.eff.mFrame[s].mpart.length;
                for (int i = 0; i < n; ++i) {
                    MainPartImage mainPartImage = (MainPartImage)this.eff.hashImage.get("" + this.eff.mFrame[s].mpart[i].idPartImage);
                    mImage mImage2 = ImageEffectAuto.setImage(this.IDImage);
                    if (mainPartImage == null || mImage2 == null || mImage2.image == null) continue;
                    mGraphics2.drawRegion(mImage2, mainPartImage.x, mainPartImage.y, mainPartImage.w, mainPartImage.h, 0, this.x + this.dx + this.eff.mFrame[s].mpart[i].x, this.y + this.dy + this.eff.mFrame[s].mpart[i].y, 0, false);
                }
            }
        }
        catch (Exception exception) {
            mSystem.println("paint effauto null img1: " + exception.toString());
            this.isPaint = false;
            this.isUpdate = false;
        }
    }

    public void paint(mGraphics mGraphics2) {
        if (this.typedata == 1 && !this.isUpdate) {
            this.setdataFromSV(null);
            return;
        }
        if (this.TypeItem == 3) {
            try {
                if (this.eff != null && this.isPaint) {
                    short s = this.eff.mRunFrame[this.f];
                    int n = this.eff.mFrame[s].mpart.length;
                    for (int i = 0; i < n; ++i) {
                        MainPartImage mainPartImage = (MainPartImage)this.eff.hashImage.get("" + this.eff.mFrame[s].mpart[i].idPartImage);
                        mImage mImage2 = ImageEffectAuto.setImage(this.IDImage);
                        if (mainPartImage == null || mImage2 == null || mImage2.image == null) continue;
                        mGraphics2.drawRegion(mImage2, mainPartImage.x, mainPartImage.y, mainPartImage.w, mainPartImage.h, 0, this.x + this.dx + this.eff.mFrame[s].mpart[i].x, this.y + this.dy + this.eff.mFrame[s].mpart[i].y, 0, false);
                    }
                }
            }
            catch (Exception exception) {
                mSystem.println("paint effauto null img1: " + exception.toString());
                this.isPaint = false;
                this.isUpdate = false;
            }
            return;
        }
        if (this.TypeItem == 4) {
            this.paintMatna(mGraphics2);
            return;
        }
        if (!(!GameCanvas.lowGraphic && this.isUpdate && LoadMap.isShowEffAuto != LoadMap.EFF_PHOBANG_END || GameScreen.infoGame.isMapchienthanh())) {
            return;
        }
        try {
            if (this.eff != null && this.isPaint) {
                short s = this.eff.mRunFrame[this.f];
                int n = this.eff.mFrame[s].mpart.length;
                for (int i = 0; i < n; ++i) {
                    MainPartImage mainPartImage = (MainPartImage)this.eff.hashImage.get("" + this.eff.mFrame[s].mpart[i].idPartImage);
                    mImage mImage3 = ImageEffectAuto.setImage(this.IDImage);
                    if (mainPartImage == null || mImage3 == null || mImage3.image == null) continue;
                    mGraphics2.drawRegion(mImage3, mainPartImage.x, mainPartImage.y, mainPartImage.w, mainPartImage.h, 0, this.x + this.dx + this.eff.mFrame[s].mpart[i].x, this.y + this.dy + this.eff.mFrame[s].mpart[i].y, 0, false);
                }
            }
        }
        catch (Exception exception) {
            mSystem.println("paint effauto null img1: " + this.IDImage + " " + exception.toString() + " " + this.eff.mRunFrame.length);
            this.isPaint = false;
            this.isUpdate = false;
        }
    }

    public void paint(mGraphics mGraphics2, int n, int n2) {
        if (this.typedata == 1 && !this.isUpdate) {
            this.setdataFromSV(null);
            return;
        }
        if (GameCanvas.lowGraphic || !this.isUpdate || LoadMap.isShowEffAuto == LoadMap.EFF_PHOBANG_END) {
            return;
        }
        try {
            if (this.eff != null && this.isPaint) {
                short s = this.eff.mRunFrame[this.f];
                int n3 = this.eff.mFrame[s].mpart.length;
                for (int i = 0; i < n3; ++i) {
                    MainPartImage mainPartImage = (MainPartImage)this.eff.hashImage.get("" + this.eff.mFrame[s].mpart[i].idPartImage);
                    mImage mImage2 = ImageEffectAuto.setImage(this.IDImage);
                    if (mainPartImage == null || mImage2 == null || mImage2.image == null) continue;
                    mGraphics2.drawRegion(mImage2, mainPartImage.x, mainPartImage.y, mainPartImage.w, mainPartImage.h, 0, n + this.dx + this.eff.mFrame[s].mpart[i].x + this.dxx, n2 + this.dy + this.eff.mFrame[s].mpart[i].y + this.dyy, 0, false);
                }
            }
        }
        catch (Exception exception) {
            mSystem.println("paint effauto null img " + this.IDImage + " " + exception.toString());
            this.isPaint = false;
            this.isUpdate = false;
        }
    }

    public boolean lockmoveByeffAuto() {
        return this.timelive - mSystem.currentTimeMillis() > 0L && this.Typemove == 1;
    }

    public boolean CanpaintByeffauto() {
        return this.timelive - mSystem.currentTimeMillis() > 0L && this.Typemove == 2;
    }

    public void update() {
        if (this.typedata == 1 && !this.isUpdate) {
            return;
        }
        try {
            if (this.TypeItem == 3) {
                ++this.f;
                if (this.f >= this.eff.mRunFrame.length - 1) {
                    this.f = 0;
                    this.wantdestroy = true;
                }
                return;
            }
            if (!(!GameCanvas.lowGraphic && this.isUpdate && LoadMap.isShowEffAuto != LoadMap.EFF_PHOBANG_END || GameScreen.infoGame.isMapchienthanh())) {
                return;
            }
            if (this.TypeItem == 2 && this.timelive - mSystem.currentTimeMillis() < 0L) {
                this.wantdestroy = true;
            }
            if (this.IDImage == 51) {
                ++this.f;
                if (this.f >= this.eff.mRunFrame.length - 1) {
                    this.f = this.eff.mRunFrame.length - 1;
                }
            } else if (this.f >= this.eff.mRunFrame.length - 1) {
                switch (this.typeEffect) {
                    case 0: {
                        ++this.nCountReplay;
                        this.isPaint = false;
                        if (this.nCountReplay < this.valueEffect) break;
                        this.nCountReplay = 0;
                        this.isPaint = true;
                        this.f = 0;
                        break;
                    }
                    case 1: {
                        this.f = 0;
                        break;
                    }
                    case 2: {
                        this.isPaint = false;
                        if (GameCanvas.gameTick % 5 != 0 || (GameCanvas.timeNow - this.timeBegin) / 1000L <= (long)this.valueEffect) break;
                        this.timeBegin = GameCanvas.timeNow;
                        this.f = 0;
                        this.isPaint = true;
                        break;
                    }
                    case 3: {
                        if (this.Loop > 0 && CRes.random(this.valueEffect) == 0) {
                            this.Loop = (short)(this.Loop - 1);
                            this.f = 0;
                            break;
                        }
                        if (this.Loop > 0) break;
                        this.wantdestroy = true;
                        this.isPaint = false;
                        this.isUpdate = false;
                        break;
                    }
                    case 4: {
                        if (CRes.random(this.valueEffect) != 0) break;
                        this.f = 0;
                        if (this.indexSound < 0 || CRes.random(this.timePlaySound) != 0 || !this.isInScreen()) break;
                        mSound.playSound(this.indexSound, mSound.volumeSound);
                    }
                }
            } else {
                ++this.f;
            }
        }
        catch (Exception exception) {
            mSystem.println("eff=" + this.IDImage + exception.toString());
            this.isPaint = false;
            this.isUpdate = false;
        }
        this.setBeginSound();
    }

    private void setBeginSound() {
        if (this.indexSound == 48) {
            if (CRes.random(this.timePlaySound) == 0 && this.isInScreen()) {
                mSound.playSound(this.indexSound, mSound.volumeSound);
            }
        } else if (this.indexSound == 49) {
            if (this.f == 35 || this.f == 43) {
                if (this.isInScreen()) {
                    mSound.playSound(49, mSound.volumeSound);
                }
            } else if (this.f == 125 && this.isInScreen()) {
                mSound.playSound(50, mSound.volumeSound);
            }
        } else if (this.indexSound == 52) {
            if (this.f == 50 && this.isInScreen()) {
                mSound.playSound(52, mSound.volumeSound);
            }
        } else if (this.indexSound == 54) {
            if (this.f % 5 == 0 && this.isInScreen() && (GameCanvas.timeNow - timeSoundPHUNNUOC) / 100L > 48L) {
                mSound.playSound(this.indexSound, mSound.volumeSound);
                timeSoundPHUNNUOC = GameCanvas.timeNow;
            }
        } else if (this.indexSound == 53) {
            if (this.f % 5 == 0 && this.isInScreen() && (GameCanvas.timeNow - timeSoundFIRE) / 100L > 68L && GameCanvas.currentScreen == GameCanvas.game) {
                mSound.playSound(this.indexSound, mSound.volumeSound);
                timeSoundFIRE = GameCanvas.timeNow;
            }
        } else if (this.indexSound == 55) {
            if (this.f % 5 == 0 && this.isInScreen() && (GameCanvas.timeNow - timeSoundWATER) / 100L > 28L) {
                mSound.playSound(this.indexSound, mSound.volumeSound);
                timeSoundWATER = GameCanvas.timeNow;
            }
        } else if (this.indexSound == 50) {
            if (this.f == 30 && this.isInScreen()) {
                mSound.playSound(50, mSound.volumeSound);
            }
        } else if (this.indexSound == 56 && this.f % 5 == 0 && this.isInScreen() && (GameCanvas.timeNow - timeSoundGIOTNUOC) / 100L > 48L) {
            mSound.playSound(this.indexSound, mSound.volumeSound);
            timeSoundGIOTNUOC = GameCanvas.timeNow;
        }
    }

    public MainEffectAuto loadTemEff(int n, byte[] byArray) {
        MainEffectAuto mainEffectAuto = (MainEffectAuto)MainEffectAuto.hashTemEffAuto.get("" + n);
        if (mainEffectAuto == null) {
            mainEffectAuto = new MainEffectAuto(n, byArray);
            MainEffectAuto.hashTemEffAuto.put(n + "", mainEffectAuto);
        }
        return mainEffectAuto;
    }

    public static mImage getEffectAutoImgFromRMS(int n) {
        mImage mImage2 = new mImage();
        lastTime = GameCanvas.timeNow;
        GlobalService.gI().load_image_data_part_char((byte)111, (short)n);
        return mImage2;
    }

    public void setPlaySound() {
        switch (this.IDImage) {
            case 7: {
                this.indexSound = 48;
                this.timePlaySound = 200;
                break;
            }
            case 29: {
                this.indexSound = 47;
                this.timePlaySound = 3;
                break;
            }
            case 11: {
                this.indexSound = 49;
                this.timePlaySound = 2;
                break;
            }
            case 9: 
            case 12: {
                this.indexSound = 52;
                this.timePlaySound = 2;
                break;
            }
            case 28: {
                this.indexSound = 54;
                this.timePlaySound = 2;
                break;
            }
            case 0: 
            case 15: 
            case 17: 
            case 18: 
            case 30: 
            case 32: {
                this.indexSound = 53;
                this.timePlaySound = 2;
                break;
            }
            case 19: 
            case 20: 
            case 31: {
                this.indexSound = 55;
                this.timePlaySound = 2;
                break;
            }
            case 6: {
                this.indexSound = 50;
                this.timePlaySound = 2;
                break;
            }
            case 22: {
                this.indexSound = 56;
                this.timePlaySound = 2;
                break;
            }
        }
    }

    public void setidActor(int n) {
        this.idActor = (short)n;
    }

    public static void SetRemove() {
        Enumeration enumeration = ALL_DATA_EFF_AUTO.keys();
        while (enumeration.hasMoreElements()) {
            String string = (String)enumeration.nextElement();
            DataEffAuto dataEffAuto = (DataEffAuto)ALL_DATA_EFF_AUTO.get(string);
            if ((GameCanvas.timeNow - dataEffAuto.timeremove) / 1000L <= 60L) continue;
            ALL_DATA_EFF_AUTO.remove(string);
        }
    }

    static {
        ALL_DATA_EFF_AUTO = new mHashtable();
    }
}

