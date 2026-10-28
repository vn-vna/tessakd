/*
 * Decompiled with CFR 0.152.
 */
package Thread_More;

import CLib.mVector;
import GameObjects.ObjectData;
import GameObjects.idSaveImage;
import Main.GameCanvas;
import Model.CRes;
import Model.MainImageDataPartChar;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.OutputStream;

public class SaveImageRMS
implements Runnable {
    public static mVector vecSaveImage = new mVector("SaveImageRMS vecSaveImage");
    public static mVector vecSaveCharPart = new mVector("SaveImageRMS vecSaveCharPart");

    public void run() {
        while (true) {
            Object object;
            boolean bl = false;
            while (vecSaveCharPart.size() > 0) {
                bl = true;
                object = (MainImageDataPartChar)vecSaveCharPart.elementAt(0);
                try {
                    CRes.saveRMS("img_data_char_" + ((MainImageDataPartChar)object).type + "_" + ((MainImageDataPartChar)object).id, ((MainImageDataPartChar)object).getSaveData().toByteArray());
                }
                catch (Exception exception) {
                    // empty catch block
                }
                vecSaveCharPart.removeElementAt(0);
            }
            if (bl) {
                object = new ByteArrayOutputStream();
                DataOutputStream dataOutputStream = new DataOutputStream((OutputStream)object);
                try {
                    dataOutputStream.writeShort(GameCanvas.IndexCharPar);
                    CRes.saveRMS("isIndexPart", ((ByteArrayOutputStream)object).toByteArray());
                    dataOutputStream.close();
                }
                catch (Exception exception) {
                    // empty catch block
                }
            }
            if (vecSaveImage.size() <= 0) break;
            try {
                object = (idSaveImage)vecSaveImage.elementAt(0);
                ObjectData.setToRms(((idSaveImage)object).mbytImage, ((idSaveImage)object).id);
                vecSaveImage.removeElementAt(0);
            }
            catch (Exception exception) {}
        }
    }

    public void start() {
        new Thread(this).start();
    }
}

