/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.io.Connector
 *  javax.microedition.io.SocketConnection
 */
package CLib;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import javax.microedition.io.Connector;
import javax.microedition.io.SocketConnection;

public class mSocket {
    SocketConnection s;

    public mSocket(String string, int n) {
        try {
            this.s = (SocketConnection)Connector.open((String)("socket://" + string + ":" + n));
        }
        catch (IOException iOException) {
            iOException.printStackTrace();
        }
    }

    public void close() {
        try {
            if (this.s != null) {
                this.s.close();
            }
        }
        catch (IOException iOException) {
            iOException.printStackTrace();
        }
    }

    public void setKeepAlive(boolean bl) {
    }

    public DataOutputStream getOutputStream() {
        try {
            return this.s.openDataOutputStream();
        }
        catch (IOException iOException) {
            iOException.printStackTrace();
            return null;
        }
    }

    public DataInputStream getInputStream() {
        try {
            return this.s.openDataInputStream();
        }
        catch (IOException iOException) {
            iOException.printStackTrace();
            return null;
        }
    }
}

