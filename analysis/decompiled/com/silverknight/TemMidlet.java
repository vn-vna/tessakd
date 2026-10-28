/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.io.ConnectionNotFoundException
 *  javax.microedition.io.Connector
 *  javax.microedition.io.HttpConnection
 *  javax.microedition.lcdui.Display
 *  javax.microedition.lcdui.Displayable
 *  javax.microedition.midlet.MIDlet
 *  javax.microedition.midlet.MIDletStateChangeException
 *  javax.microedition.rms.RecordStore
 */
package com.silverknight;

import CLib.Session_ME;
import CLib.mSystem;
import Model.CRes;
import Thread_More.Load_Data_And_Img;
import com.silverknight.TemCanvas;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.microedition.io.ConnectionNotFoundException;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;
import javax.microedition.rms.RecordStore;
import net.Message;
import netcommand.global.GlobalMessageHandler;

public class TemMidlet
extends MIDlet {
    public static TemCanvas temCanvas;
    public static TemMidlet instance;
    public static final byte DIVICE = 0;
    public static final byte NONE = 0;
    public static final byte NOKIA_STORE = 1;
    public static final byte GOOGLE_STORE = 2;
    public static byte currentIAPStore;
    public static boolean isBlockNOKIAStore;
    public static byte langServer;
    public static String[] google_productIds;
    public static String[] google_listGems;
    public static String[] google_productIds_US;
    public static String[] google_listGems_US;
    public static String[] google_productIds_GB;
    public static String[] google_listGems_GB;
    public static final String[] productIds;
    public static String[] listGems;

    public TemMidlet() {
        temCanvas = new TemCanvas();
        instance = this;
        Session_ME.gI().setHandler(GlobalMessageHandler.gI());
        Display.getDisplay((MIDlet)this).setCurrent((Displayable)temCanvas);
        temCanvas.start();
        Load_Data_And_Img.load.isInitGame = true;
    }

    protected void destroyApp(boolean bl) throws MIDletStateChangeException {
        instance.notifyDestroyed();
    }

    protected void pauseApp() {
    }

    protected void startApp() throws MIDletStateChangeException {
    }

    public void destroy() {
        try {
            instance.destroyApp(true);
        }
        catch (MIDletStateChangeException mIDletStateChangeException) {
            mIDletStateChangeException.printStackTrace();
        }
    }

    public static byte[] encoding(byte[] byArray) {
        if (byArray != null) {
            for (int i = 0; i < byArray.length; ++i) {
                byArray[i] = ~byArray[i];
            }
        }
        return byArray;
    }

    public static void saveRMS(String string, byte[] byArray) throws Exception {
        byArray = TemMidlet.encoding(byArray);
        RecordStore recordStore = RecordStore.openRecordStore((String)string, (boolean)true);
        if (recordStore.getNumRecords() > 0) {
            recordStore.setRecord(1, byArray, 0, byArray.length);
        } else {
            recordStore.addRecord(byArray, 0, byArray.length);
        }
        recordStore.closeRecordStore();
    }

    public static byte[] loadRMS(String string) {
        byte[] byArray;
        try {
            RecordStore recordStore = RecordStore.openRecordStore((String)string, (boolean)false);
            byArray = recordStore.getRecord(1);
            recordStore.closeRecordStore();
        }
        catch (Exception exception) {
            return null;
        }
        return TemMidlet.encoding(byArray);
    }

    public static void openUrl(String string) {
        try {
            instance.platformRequest(string);
            instance.notifyDestroyed();
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public static void delRMS() {
        try {
            String[] stringArray = RecordStore.listRecordStores();
            if (stringArray != null) {
                for (int i = 0; i < stringArray.length; ++i) {
                    if (!CRes.CheckDelRMS(stringArray[i])) continue;
                    RecordStore.deleteRecordStore((String)stringArray[i]);
                }
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static String connectHTTP(String string) {
        HttpConnection httpConnection = null;
        InputStream inputStream = null;
        try {
            httpConnection = (HttpConnection)Connector.open((String)string);
            httpConnection.setRequestMethod("GET");
            httpConnection.setRequestProperty("Content-Type", "//text plain");
            httpConnection.setRequestProperty("Connection", "close");
            if (httpConnection.getResponseCode() == 200) {
                int n;
                inputStream = httpConnection.openInputStream();
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                byte[] byArray = new byte[256];
                while ((n = inputStream.read(byArray)) != -1) {
                    byteArrayOutputStream.write(byArray, 0, n);
                }
                String string2 = new String(byteArrayOutputStream.toByteArray(), "UTF-8");
                if (string2.length() > 0 && string2.charAt(0) == '\ufeff') {
                    string2 = string2.substring(1);
                }
                String string3 = string2;
                return string3;
            }
        }
        catch (IOException iOException) {
            String string4 = null;
            return string4;
        }
        finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (httpConnection != null) {
                    httpConnection.close();
                }
            }
            catch (IOException iOException) {}
        }
        return null;
    }

    public static void setProductIds() {
        TemMidlet.productIds[0] = "1270462";
    }

    public static void makePurchase(String string) {
    }

    public static void submitPurchase() {
    }

    public static void handleMessage(Message message) {
    }

    public void call(String string) {
        try {
            this.platformRequest("tel:" + string);
        }
        catch (ConnectionNotFoundException connectionNotFoundException) {
            mSystem.println("loi roi " + connectionNotFoundException.toString());
        }
    }

    public static void handleAllMessage(Message message) {
    }

    static {
        currentIAPStore = 0;
        isBlockNOKIAStore = true;
        langServer = 0;
        google_productIds = new String[]{"hs_gold_10", "hs_gold_30", "hs_gold_70", "hs_gold_180", "hs_gold_380", "hs_gold_800"};
        google_listGems = new String[]{"24 Gems ($0.99)", "84 Gems ($2.99)", "150 Gems ($4.99)", "350 Gems ($9.99)", "1.000 Gems ($24.99)", "2.500 Gems ($49.99)"};
        google_productIds_US = new String[]{"hs_gold_10", "hs_gold_30", "hs_gold_70", "hs_gold_180", "hs_gold_380", "hs_gold_800"};
        google_listGems_US = new String[]{"24 Gems ($0.99)", "84 Gems ($2.99)", "150 Gems ($4.99)", "350 Gems ($9.99)", "1.000 Gems ($24.99)", "2.500 Gems ($49.99)"};
        google_productIds_GB = new String[]{"hs_gold_10", "hs_gold_30", "hs_gold_70", "hs_gold_180", "hs_gold_380", "hs_gold_800"};
        google_listGems_GB = new String[]{"24 Gems ($0.99)", "84 Gems ($2.99)", "150 Gems ($4.99)", "350 Gems ($9.99)", "1.000 Gems ($24.99)", "2.500 Gems ($49.99)"};
        productIds = new String[1];
        listGems = new String[]{"24 Gems"};
    }
}

