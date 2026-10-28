/*
 * Decompiled with CFR 0.152.
 */
package CLib;

import java.util.Enumeration;
import java.util.Hashtable;

public class mHashtable {
    public Hashtable htb = new Hashtable();

    public void clear() {
        this.htb.clear();
    }

    public boolean contains(Object object) {
        return this.htb.contains(object);
    }

    public boolean containsKey(Object object) {
        return this.htb.containsKey(object);
    }

    public Object get(String string) {
        return this.htb.get(string);
    }

    public int size() {
        return this.htb.size();
    }

    public void put(String string, Object object) {
        if (this.htb.containsKey(string)) {
            this.htb.remove(string);
        }
        this.htb.put(string, object);
    }

    public void remove(Object object) {
        this.htb.remove(object);
    }

    public Enumeration keys() {
        return this.htb.keys();
    }
}

