/*
 * Decompiled with CFR 0.152.
 */
package Build_Usa;

import CLib.mSystem;
import Main.GameCanvas;
import Model.TE;

public class Usa_Server {
    public static boolean isUsa_server = false;

    public static void setLinkIp() {
        if (isUsa_server) {
            GameCanvas.t = new TE();
            GameCanvas.linkIP = "http://knightageonline.com/srvip/us.php";
            mSystem.listServer = new String[][]{{"Fire Dragon", "46.137.254.172"}, {"Sky Dragon", "54.254.156.202"}};
        }
    }
}

