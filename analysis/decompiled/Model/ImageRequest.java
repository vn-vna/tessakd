/*
 * Decompiled with CFR 0.152.
 */
package Model;

import CLib.mVector;
import Main.GameCanvas;

public class ImageRequest {
    public static mVector vecImageRequest = new mVector("ImageRequest vecImageRequest");
    public long timeRequest;
    public int idRequest;

    public ImageRequest(int n) {
        this.idRequest = n;
        this.timeRequest = GameCanvas.timeNow;
    }
}

