/*
 * Decompiled with CFR 0.152.
 */
package net;

import net.Message;

public interface IMessageHandler {
    public void onMessage(Message var1);

    public void onConnectionFail();

    public void onDisconnected();

    public void onConnectOK();
}

