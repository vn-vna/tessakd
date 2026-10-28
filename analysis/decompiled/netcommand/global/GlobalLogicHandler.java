/*
 * Decompiled with CFR 0.152.
 */
package netcommand.global;

import CLib.Session_ME;
import CLib.mSystem;
import CLib.mVector;
import GameObjects.Player;
import GameScreen.GameScreen;
import GameScreen.SelectCharScreen;
import InterfaceComponents.iCommand;
import Main.GameCanvas;
import Model.T;

public class GlobalLogicHandler {
    public static GlobalLogicHandler instance;
    public static boolean isDisConect;
    public static long timeReconnect;
    public static boolean isMelogin;

    public static GlobalLogicHandler gI() {
        if (instance == null) {
            instance = new GlobalLogicHandler();
        }
        return instance;
    }

    public void onConnectFail() {
    }

    public void onConnectOK() {
    }

    public static void onDisconnect() {
        mSystem.outloi("mat ket noi");
        mVector mVector2 = new mVector("GlobalLogicHandler vec");
        if (SelectCharScreen.isSelectOk && GameCanvas.currentScreen != GameCanvas.login && GameCanvas.currentScreen != GameCanvas.load) {
            mVector2.addElement(new iCommand(T.again, 0));
        }
        mVector2.addElement(new iCommand(T.exit, 6));
        GameCanvas.start_Select_Dialog(T.disconnect, mVector2);
        Session_ME.gI().close();
        if (!isMelogin) {
            isDisConect = true;
            timeReconnect = mSystem.currentTimeMillis() + 30000L;
        } else {
            isDisConect = false;
            timeReconnect = 0L;
        }
        if (GameScreen.isMapLang) {
            isDisConect = false;
            timeReconnect = 0L;
        }
        if (GameScreen.player != null) {
            GameScreen.player.resetAction();
            if (Player.isAutoFire == 1) {
                Player.setCurAutoFire();
            }
        }
    }

    static {
        isDisConect = false;
        timeReconnect = mSystem.currentTimeMillis();
        isMelogin = false;
    }
}

