/*
 * Decompiled with CFR 0.152.
 */
package CLib;

import java.util.Vector;

public class mVector {
    private Vector a;
    private String name;

    public mVector() {
        this.a = new Vector();
        this.name = "EffectManager";
    }

    public mVector(String string) {
        this.a = new Vector();
        this.name = string;
    }

    public mVector(Vector vector) {
        this.a = vector;
        this.name = "No Name";
    }

    public void addElement(Object object) {
        this.a.addElement(object);
    }

    public boolean contains(Object object) {
        return this.a.contains(object);
    }

    public int size() {
        if (this.a == null) {
            return 0;
        }
        return this.a.size();
    }

    public Object elementAt(int n) {
        if (n > -1 && n < this.a.size()) {
            return this.a.elementAt(n);
        }
        return null;
    }

    public void setElementAt(Object object, int n) {
        if (n > -1 && n < this.a.size()) {
            this.a.setElementAt(object, n);
        }
    }

    public int indexOf(Object object) {
        return this.a.indexOf(object);
    }

    public void removeElementAt(int n) {
        if (n > -1 && n < this.a.size()) {
            this.a.removeElementAt(n);
        }
    }

    public void removeElement(Object object) {
        this.a.removeElement(object);
    }

    public void removeAllElements() {
        this.a.removeAllElements();
    }

    public void insertElementAt(Object object, int n) {
        this.a.insertElementAt(object, n);
    }

    public Object firstElement() {
        return this.a.firstElement();
    }

    public Object lastElement() {
        return this.a.lastElement();
    }
}

