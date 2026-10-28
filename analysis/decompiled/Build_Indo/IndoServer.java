/*
 * Decompiled with CFR 0.152.
 */
package Build_Indo;

import CLib.mSystem;
import Main.GameCanvas;
import Model.TIndo;

public class IndoServer {
    public static boolean isIndoSv = false;

    public static void setLinkIp() {
        if (isIndoSv) {
            GameCanvas.t = new TIndo();
            GameCanvas.linkIP = "http://knightageonline.com/srvip/indo.php";
            mSystem.listServer = new String[][]{{"Indo Naga", "54.151.177.35"}};
        }
    }
}

