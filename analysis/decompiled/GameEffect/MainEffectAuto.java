/*
 * Decompiled with CFR 0.152.
 */
package GameEffect;

import CLib.mHashtable;
import CLib.mImage;
import CLib.mSystem;
import GameEffect.MainFrameEff;
import GameObjects.Part;
import Model.MainPartImage;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.InputStream;

public class MainEffectAuto {
    public static mHashtable hashTemEffAuto = new mHashtable();
    public mHashtable hashImage = new mHashtable();
    public MainFrameEff[] mFrame;
    short[] mRunFrame;

    public MainEffectAuto(int n, byte[] byArray) {
        this.setEffAuto(n, byArray);
    }

    public void setEffAuto(int n, byte[] byArray) {
        try {
            InputStream inputStream = null;
            inputStream = byArray != null ? new ByteArrayInputStream(byArray) : mImage.openFile("/eff_" + n);
            if (inputStream != null) {
                int n2;
                int n3;
                int n4;
                DataInputStream dataInputStream;
                if (n < 34) {
                    if (!mSystem.isMaHoa) {
                        dataInputStream = new DataInputStream(inputStream);
                    } else {
                        DataInputStream dataInputStream2 = new DataInputStream(inputStream);
                        byte[] byArray2 = new byte[dataInputStream2.available()];
                        dataInputStream2.read(byArray2);
                        for (n4 = 0; n4 < byArray2.length; ++n4) {
                            byArray2[n4] = (byte)(byArray2[n4] ^ 3);
                        }
                        dataInputStream = new DataInputStream(new ByteArrayInputStream(byArray2));
                    }
                } else {
                    dataInputStream = new DataInputStream(inputStream);
                }
                int n5 = dataInputStream.readUnsignedByte();
                for (n3 = 0; n3 < n5; ++n3) {
                    MainPartImage mainPartImage = new MainPartImage(dataInputStream.readUnsignedByte(), dataInputStream.readUnsignedByte(), dataInputStream.readUnsignedByte(), dataInputStream.readUnsignedByte(), dataInputStream.readUnsignedByte());
                    this.hashImage.put("" + mainPartImage.ID, mainPartImage);
                }
                n3 = dataInputStream.readShort();
                this.mFrame = new MainFrameEff[n3];
                for (n4 = 0; n4 < n3; ++n4) {
                    n2 = dataInputStream.readByte();
                    this.mFrame[n4] = new MainFrameEff();
                    this.mFrame[n4].mpart = new Part[n2];
                    for (int i = 0; i < n2; ++i) {
                        this.mFrame[n4].mpart[i] = new Part();
                        this.mFrame[n4].mpart[i].x = dataInputStream.readShort();
                        this.mFrame[n4].mpart[i].y = dataInputStream.readShort();
                        this.mFrame[n4].mpart[i].idPartImage = dataInputStream.readByte();
                    }
                }
                n4 = dataInputStream.readShort();
                this.mRunFrame = new short[n4];
                for (n2 = 0; n2 < n4; ++n2) {
                    this.mRunFrame[n2] = dataInputStream.readShort();
                }
                dataInputStream.readByte();
                dataInputStream.readByte();
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
            mSystem.println("err effauto 2 id=" + n);
        }
    }
}

