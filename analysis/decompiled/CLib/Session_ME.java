/*
 * Decompiled with CFR 0.152.
 */
package CLib;

import CLib.mSocket;
import CLib.mSystem;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Vector;
import net.IMessageHandler;
import net.ISession;
import net.Message;

public class Session_ME
implements ISession {
    protected static Session_ME instance = new Session_ME();
    private DataOutputStream dos;
    public DataInputStream dis;
    public IMessageHandler messageHandler;
    private mSocket sc;
    public boolean connected;
    public boolean connecting;
    private final Sender sender = new Sender();
    public Thread initThread;
    public Thread collectorThread;
    public Thread sendThread;
    public int sendByteCount;
    public int recvByteCount;
    boolean getKeyComplete;
    public byte[] key = null;
    private byte curR;
    private byte curW;
    long timeConnected;
    public String strRecvByteCount = "";
    public static boolean isCancel;
    int countMsg = 0;
    public static int countRead;

    public static Session_ME gI() {
        return instance;
    }

    public boolean isConnected() {
        return this.connected;
    }

    public void setHandler(IMessageHandler iMessageHandler) {
        this.messageHandler = iMessageHandler;
    }

    public void connect(String string, int n) {
        System.out.println("------------IP host:" + string + "--port:" + n);
        if (this.connected || this.connecting) {
            return;
        }
        this.sender.removeAllMessage();
        this.getKeyComplete = false;
        this.sc = null;
        this.initThread = new Thread(new NetworkInit(string, n));
        this.initThread.start();
    }

    public void sendMessage(Message message) {
        this.sender.AddMessage(message);
    }

    private synchronized void doSendMessage(Message message) throws IOException {
        byte[] byArray = message.getData();
        try {
            int n;
            if (this.getKeyComplete) {
                n = this.writeKey(message.command);
                this.dos.writeByte(n);
            } else {
                this.dos.writeByte(message.command);
            }
            if (byArray != null) {
                int n2;
                n = byArray.length;
                if (this.getKeyComplete) {
                    n2 = this.writeKey((byte)(n >> 8));
                    this.dos.writeByte(n2);
                    byte by = this.writeKey((byte)(n & 0xFF));
                    this.dos.writeByte(by);
                } else {
                    this.dos.writeShort(n);
                }
                if (this.getKeyComplete) {
                    for (n2 = 0; n2 < byArray.length; ++n2) {
                        byArray[n2] = this.writeKey(byArray[n2]);
                    }
                }
                ((OutputStream)this.dos).write(byArray);
                this.sendByteCount += 5 + byArray.length;
            } else {
                this.dos.writeShort(0);
                this.sendByteCount += 5;
            }
            this.dos.flush();
        }
        catch (IOException iOException) {
            iOException.printStackTrace();
        }
    }

    private byte readKey(byte by) {
        byte by2 = this.curR;
        this.curR = (byte)(by2 + 1);
        byte by3 = (byte)(this.key[by2] & 0xFF ^ by & 0xFF);
        if (this.curR >= this.key.length) {
            this.curR = (byte)(this.curR % this.key.length);
        }
        return by3;
    }

    private byte writeKey(byte by) {
        byte by2 = this.curW;
        this.curW = (byte)(by2 + 1);
        byte by3 = (byte)(this.key[by2] & 0xFF ^ by & 0xFF);
        if (this.curW >= this.key.length) {
            this.curW = (byte)(this.curW % this.key.length);
        }
        return by3;
    }

    public void close() {
        this.cleanNetwork();
    }

    private void cleanNetwork() {
        this.key = null;
        this.curR = 0;
        this.curW = 0;
        try {
            this.connected = false;
            this.connecting = false;
            if (this.sc != null) {
                this.sc.close();
                this.sc = null;
            }
            if (this.dos != null) {
                this.dos.close();
                this.dos = null;
            }
            if (this.dis != null) {
                this.dis.close();
                this.dis = null;
            }
            this.sendThread = null;
            this.collectorThread = null;
            System.gc();
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void connect(String string, String string2) {
    }

    static {
        countRead = 0;
    }

    class MessageCollector
    implements Runnable {
        MessageCollector() {
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        public void run() {
            try {
                Message message;
                while (Session_ME.this.isConnected() && (message = this.readMessage()) != null) {
                    try {
                        if (message.command == -40) {
                            this.getKey(message);
                            continue;
                        }
                        Session_ME.this.messageHandler.onMessage(message);
                    }
                    catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }
            }
            catch (Exception exception) {
                // empty catch block
            }
            if (Session_ME.this.connected) {
                if (Session_ME.this.messageHandler != null) {
                    if (mSystem.currentTimeMillis() - Session_ME.this.timeConnected > 500L) {
                        Session_ME.this.messageHandler.onDisconnected();
                    } else {
                        Session_ME.this.messageHandler.onConnectionFail();
                    }
                }
                if (Session_ME.this.sc != null) {
                    Session_ME.this.cleanNetwork();
                }
            }
        }

        private void getKey(Message message) throws IOException {
            int n;
            int n2 = message.reader().readByte();
            Session_ME.this.key = new byte[n2];
            for (n = 0; n < n2; ++n) {
                Session_ME.this.key[n] = message.reader().readByte();
            }
            for (n = 0; n < Session_ME.this.key.length - 1; ++n) {
                int n3 = n + 1;
                Session_ME.this.key[n3] = (byte)(Session_ME.this.key[n3] ^ Session_ME.this.key[n]);
            }
            Session_ME.this.getKeyComplete = true;
        }

        private Message readMessage() throws Exception {
            byte by;
            int n;
            byte[] byArray;
            ++countRead;
            byte by2 = Session_ME.this.dis.readByte();
            if (Session_ME.this.getKeyComplete) {
                by2 = Session_ME.this.readKey(by2);
            }
            if (Session_ME.this.getKeyComplete) {
                if (by2 == -51 || by2 == -52 || by2 == -54 || by2 == 126) {
                    if (by2 == 126) {
                        by2 = Session_ME.this.dis.readByte();
                        by2 = Session_ME.this.readKey(by2);
                    }
                    byArray = new byte[]{Session_ME.this.dis.readByte(), Session_ME.this.dis.readByte(), Session_ME.this.dis.readByte(), Session_ME.this.dis.readByte()};
                    n = Session_ME.this.readKey(byArray[3]) & 0xFF | (Session_ME.this.readKey(byArray[2]) & 0xFF) << 8 | (Session_ME.this.readKey(byArray[1]) & 0xFF) << 16 | (Session_ME.this.readKey(byArray[0]) & 0xFF) << 24;
                } else {
                    byte by3 = Session_ME.this.dis.readByte();
                    by = Session_ME.this.dis.readByte();
                    n = (Session_ME.this.readKey(by3) & 0xFF) << 8 | Session_ME.this.readKey(by) & 0xFF;
                }
            } else {
                n = Session_ME.this.dis.readUnsignedShort();
            }
            byArray = new byte[n];
            by = 0;
            int n2 = 0;
            while (by != -1 && n2 < n) {
                by = Session_ME.this.dis.read(byArray, n2, n - n2);
                if (by <= 0) continue;
                Session_ME.this.recvByteCount += 5 + (n2 += by);
            }
            if (Session_ME.this.getKeyComplete) {
                for (int i = 0; i < byArray.length; ++i) {
                    byArray[i] = Session_ME.this.readKey(byArray[i]);
                }
            }
            Message message = new Message(by2, byArray);
            return message;
        }
    }

    private class Sender
    implements Runnable {
        public final Vector sendingMessage = new Vector();

        public void AddMessage(Message message) {
            this.sendingMessage.addElement(message);
        }

        public void removeAllMessage() {
            if (this.sendingMessage != null) {
                this.sendingMessage.removeAllElements();
            }
        }

        public void run() {
            try {
                while (Session_ME.this.connected) {
                    if (Session_ME.this.getKeyComplete) {
                        while (this.sendingMessage.size() > 0) {
                            Message message = (Message)this.sendingMessage.elementAt(0);
                            this.sendingMessage.removeElementAt(0);
                            Session_ME.this.doSendMessage(message);
                        }
                    }
                    try {
                        Thread.sleep(10L);
                    }
                    catch (InterruptedException interruptedException) {}
                }
            }
            catch (IOException iOException) {
                iOException.printStackTrace();
            }
        }
    }

    class NetworkInit
    implements Runnable {
        private final String host;
        private final int port;

        NetworkInit(String string, int n) {
            this.host = string;
            this.port = n;
        }

        public void run() {
            block5: {
                isCancel = false;
                new Thread(new Runnable(){

                    public void run() {
                        try {
                            Thread.sleep(20000L);
                        }
                        catch (InterruptedException interruptedException) {
                            // empty catch block
                        }
                        if (((NetworkInit)NetworkInit.this).Session_ME.this.connecting) {
                            try {
                                Session_ME.this.sc.close();
                            }
                            catch (Exception exception) {
                                // empty catch block
                            }
                            isCancel = true;
                            ((NetworkInit)NetworkInit.this).Session_ME.this.connecting = false;
                            ((NetworkInit)NetworkInit.this).Session_ME.this.connected = false;
                            ((NetworkInit)NetworkInit.this).Session_ME.this.messageHandler.onConnectionFail();
                        }
                    }
                }).start();
                Session_ME.this.connecting = true;
                Thread.currentThread().setPriority(1);
                Session_ME.this.connected = true;
                try {
                    this.doConnect(this.host, this.port);
                    Session_ME.this.messageHandler.onConnectOK();
                }
                catch (Exception exception) {
                    try {
                        Thread.sleep(500L);
                    }
                    catch (InterruptedException interruptedException) {
                        // empty catch block
                    }
                    if (isCancel) {
                        return;
                    }
                    if (Session_ME.this.messageHandler == null) break block5;
                    Session_ME.this.close();
                    Session_ME.this.messageHandler.onConnectionFail();
                }
            }
        }

        public void doConnect(String string, int n) throws Exception {
            Session_ME.this.sc = new mSocket(string, n);
            Session_ME.this.sc.setKeepAlive(true);
            Session_ME.this.dos = Session_ME.this.sc.getOutputStream();
            Session_ME.this.dis = Session_ME.this.sc.getInputStream();
            new Thread(Session_ME.this.sender).start();
            Session_ME.this.collectorThread = new Thread(new MessageCollector());
            Session_ME.this.collectorThread.start();
            Session_ME.this.timeConnected = mSystem.currentTimeMillis();
            Session_ME.this.doSendMessage(new Message(-40));
            Session_ME.this.connecting = false;
        }
    }
}

