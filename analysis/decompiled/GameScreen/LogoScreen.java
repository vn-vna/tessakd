/*
 * Decompiled with CFR 0.152.
 */
package GameScreen;

import Build_Indo.IndoServer;
import Build_Usa.Usa_Server;
import CLib.mFont;
import CLib.mGraphics;
import CLib.mImage;
import CLib.mSystem;
import GameObjects.MainRMS;
import GameScreen.List_Server;
import GameScreen.LoginScreen;
import GameScreen.MainScreen;
import GameScreen.WorldMapScreen;
import Main.GameCanvas;
import Model.CRes;
import Model.T;
import Model.TE;
import com.silverknight.TemMidlet;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;

public class LogoScreen
extends MainScreen {
    int dem;
    public static String strListserver = "";
    public static boolean isLoadServer = false;
    public static String serverListSource = "DEFAULT";
    private static String savedServerHost;

    public LogoScreen() {
        try {
            DataInputStream dataInputStream;
            InputStream inputStream;
            boolean bl;
            byte[] byArray = CRes.loadRMS("listServer");
            byte[] byArray2 = CRes.loadRMS("isIndexServer");
            byte[] byArray3 = CRes.loadRMS("selectedServerHost");
            if (byArray3 != null) {
                savedServerHost = new String(byArray3, "UTF-8");
            }
            boolean bl2 = bl = byArray2 == null && savedServerHost == null;
            if (byArray != null) {
                inputStream = new DataInputStream(new ByteArrayInputStream(byArray));
                if (LogoScreen.applyServerList(inputStream.readUTF(), bl)) {
                    serverListSource = "RMS";
                }
                inputStream.close();
            }
            if (LogoScreen.applyServerList(strListserver = TemMidlet.connectHTTP(GameCanvas.linkIP), bl)) {
                serverListSource = "HTTP";
            } else if (byArray != null && LogoScreen.applyServerList(strListserver = (dataInputStream = new DataInputStream(inputStream = new ByteArrayInputStream(byArray))).readUTF(), bl)) {
                serverListSource = "RMS";
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void paint(mGraphics mGraphics2) {
        mGraphics2.setColor(0);
        mGraphics2.fillRect(0, 0, GameCanvas.w, GameCanvas.h, false);
    }

    public void checkIPSV() {
    }

    public void update() {
        ++this.dem;
        if (this.dem > 60) {
            this.dem = 0;
            GameCanvas.login.Show();
        }
    }

    public static void getServerList(String string, boolean bl) {
        LogoScreen.applyServerList(string, bl);
    }

    public static boolean refreshServerList() {
        strListserver = TemMidlet.connectHTTP(GameCanvas.linkIP);
        if (!LogoScreen.applyServerList(strListserver, false)) {
            return false;
        }
        serverListSource = "HTTP";
        return true;
    }

    public static boolean applyServerList(String string, boolean bl) {
        int n;
        Object[] objectArray;
        if (string == null || string.length() == 0) {
            return false;
        }
        String string2 = savedServerHost;
        if (!bl && GameCanvas.IndexServer >= 0 && GameCanvas.IndexServer < mSystem.listServer.length && string2 == null) {
            string2 = mSystem.listServer[GameCanvas.IndexServer][1];
        }
        String[] stringArray = mFont.split(string.trim(), ",");
        int n2 = 0;
        for (int i = 0; i < stringArray.length; ++i) {
            objectArray = mFont.split(stringArray[i].trim(), ":");
            if (objectArray.length < 4) continue;
            ++n2;
        }
        if (n2 == 0) {
            return false;
        }
        String[][] stringArray2 = new String[n2][2];
        objectArray = new int[n2];
        int[] nArray = new int[n2];
        int n3 = 0;
        try {
            for (n = 0; n < stringArray.length; ++n) {
                String[] stringArray3 = mFont.split(stringArray[n].trim(), ":");
                if (stringArray3.length < 4) continue;
                stringArray2[n3][0] = stringArray3[0].trim();
                stringArray2[n3][1] = stringArray3[1].trim();
                objectArray[n3] = (String)Integer.parseInt(stringArray3[2].trim());
                nArray[n3] = Byte.parseByte(stringArray3[3].trim());
                ++n3;
            }
        }
        catch (Exception exception) {
            return false;
        }
        mSystem.listServer = stringArray2;
        GameCanvas.portServer = (int[])objectArray;
        GameCanvas.langServer = nArray;
        n = -1;
        if (string2 != null) {
            for (int i = 0; i < stringArray2.length; ++i) {
                if (!string2.equals(stringArray2[i][1])) continue;
                n = i;
                break;
            }
        }
        if (n < 0) {
            n = 0;
        }
        GameCanvas.isVNLanguage = GameCanvas.langServer[GameCanvas.IndexServer = (byte)n] == 0;
        LogoScreen.saveListServer(string);
        LogoScreen.setChangeLang();
        return true;
    }

    public static void saveSelectedServer() {
        try {
            savedServerHost = mSystem.listServer[GameCanvas.IndexServer][1];
            CRes.saveRMS("selectedServerHost", savedServerHost.getBytes("UTF-8"));
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public static void setChangeLang() {
        MainRMS.showAuto = "";
        LoginScreen.indexInfoLogin = 1;
        if (GameCanvas.w < 200 || GameCanvas.h < 200) {
            LoginScreen.indexInfoLogin = (byte)2;
        } else if (GameCanvas.isTouch && GameCanvas.w < 380 && GameCanvas.w > 315 && GameCanvas.w < 380) {
            LoginScreen.indexInfoLogin = (byte)2;
        }
        GameCanvas.start_Wait_Dialog(T.pleaseWait, null);
        if (!GameCanvas.isVNLanguage) {
            GameCanvas.t = new TE();
            LoginScreen.logo = mSystem.isIP_TrucTiep ? mImage.createImage("/interface/logoeip.png") : mImage.createImage("/interface/logoe.png");
        } else {
            GameCanvas.t = new T();
            LoginScreen.logo = mSystem.isIP_TrucTiep ? mImage.createImage("/interface/logoip.png") : mImage.createImage("/interface/logo.png");
        }
        IndoServer.setLinkIp();
        Usa_Server.setLinkIp();
        List_Server.gI().doSetCaption();
        GameCanvas.loadCaptionCmd();
        WorldMapScreen.namePos = null;
        GameCanvas.end_Dialog();
    }

    public static void saveListServer(String string) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        try {
            dataOutputStream.writeUTF(string);
            CRes.saveRMS("listServer", byteArrayOutputStream.toByteArray());
            dataOutputStream.close();
        }
        catch (Exception exception) {
            // empty catch block
        }
    }
}

