/*
 * Decompiled with CFR 0.152.
 */
package Model;

import Model.CRes;

public class Position {
    public int x;
    public int y;
    public int anchor;
    public byte depth = 0;
    public short index = (short)-1;

    public Position() {
        this.x = 0;
        this.y = 0;
    }

    public Position(int n, int n2) {
        this.x = n;
        this.y = n2;
    }

    public Position(int n, int n2, int n3) {
        this.x = n;
        this.y = n2;
        this.anchor = n3;
    }

    public boolean setDetectX(int n, int n2) {
        return CRes.abs(n - this.x) <= n2;
    }

    public boolean setDetectY(int n, int n2) {
        return CRes.abs(n - this.y) <= n2;
    }
}

