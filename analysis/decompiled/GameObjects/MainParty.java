/*
 * Decompiled with CFR 0.152.
 */
package GameObjects;

import CLib.mVector;
import GameObjects.MainObject;
import GameObjects.ObjectParty;
import GameScreen.GameScreen;

public class MainParty {
    public String nameMain;
    public mVector vecPartys = new mVector("MainParty vecPartys");
    public boolean isClear = false;

    public MainParty(String string, short s, int n, byte by) {
        this.nameMain = string;
        this.vecPartys.removeAllElements();
        this.isClear = false;
        this.addObjParty(string, s, n, by);
    }

    public void addObjParty(String string, short s, int n, byte by) {
        ObjectParty objectParty = new ObjectParty(string, s, n, by);
        objectParty.maxhp = 10;
        objectParty.hp = 10;
        this.vecPartys.addElement(objectParty);
    }

    public void removeObj(String string) {
        for (int i = 0; i < this.vecPartys.size(); ++i) {
            ObjectParty objectParty = (ObjectParty)this.vecPartys.elementAt(i);
            if (objectParty.name.compareTo(string) != 0) continue;
            objectParty.isRemove = true;
            break;
        }
    }

    public void setPos(String string, int n, int n2, int n3, int n4) {
        for (int i = 0; i < this.vecPartys.size(); ++i) {
            ObjectParty objectParty = (ObjectParty)this.vecPartys.elementAt(i);
            if (objectParty.name.compareTo(string) != 0) continue;
            objectParty.x = n;
            objectParty.y = n2;
            if (n3 > n4) {
                n3 = n4;
            }
            objectParty.hp = n3;
            objectParty.maxhp = n4;
            break;
        }
    }

    public boolean update() {
        if (this.isClear) {
            this.vecPartys.removeAllElements();
            return true;
        }
        for (int i = 0; i < this.vecPartys.size(); ++i) {
            ObjectParty objectParty = (ObjectParty)this.vecPartys.elementAt(i);
            if (!objectParty.isRemove) continue;
            this.vecPartys.removeElement(objectParty);
            --i;
        }
        return false;
    }

    public void setIsParty() {
        Object object;
        int n;
        for (n = 0; n < this.vecPartys.size(); ++n) {
            object = (ObjectParty)this.vecPartys.elementAt(n);
            ((ObjectParty)object).ischeck = false;
        }
        block1: for (n = 0; n < GameScreen.Vecplayers.size(); ++n) {
            object = (MainObject)GameScreen.Vecplayers.elementAt(n);
            if (((MainObject)object).typeObject != 0 || object == GameScreen.player) continue;
            ((MainObject)object).isParty = false;
            for (int i = 0; i < this.vecPartys.size(); ++i) {
                ObjectParty objectParty = (ObjectParty)this.vecPartys.elementAt(i);
                if (objectParty.ischeck || objectParty.name.compareTo(((MainObject)object).name) != 0) continue;
                ((MainObject)object).isParty = true;
                objectParty.ischeck = true;
                continue block1;
            }
        }
    }

    public void setObjParty(MainObject mainObject) {
        for (int i = 0; i < this.vecPartys.size(); ++i) {
            ObjectParty objectParty = (ObjectParty)this.vecPartys.elementAt(i);
            if (objectParty.name.compareTo(mainObject.name) != 0) continue;
            mainObject.isParty = true;
            break;
        }
    }
}

