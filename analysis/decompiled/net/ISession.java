/*
 * Decompiled with CFR 0.152.
 */
package net;

import net.IMessageHandler;
import net.Message;

public interface ISession {
    public boolean isConnected();

    public void setHandler(IMessageHandler var1);

    public void connect(String var1, String var2);

    public void sendMessage(Message var1);

    public void close();
}

