/*
 * Decompiled with CFR 0.152.
 */
package Model;

import CLib.mGraphics;
import CLib.mVector;
import GameEffect.MainEffect;

public class EffectManager
extends mVector {
    public static EffectManager lowEffects = new EffectManager();
    public static EffectManager hiEffects = new EffectManager();

    public void updateAll() {
        for (int i = this.size() - 1; i >= 0; --i) {
            MainEffect mainEffect = (MainEffect)this.elementAt(i);
            if (mainEffect == null) continue;
            mainEffect.update();
            if (!mainEffect.isRemove) continue;
            mainEffect.clearData();
            this.removeElementAt(i);
        }
    }

    public void paintAll(mGraphics mGraphics2) {
        for (int i = 0; i < this.size(); ++i) {
            MainEffect mainEffect = (MainEffect)this.elementAt(i);
            if (mainEffect == null || mainEffect.isRemove) continue;
            mainEffect.paint(mGraphics2);
        }
    }

    public void reMoveAll() {
        for (int i = this.size() - 1; i >= 0; --i) {
            MainEffect mainEffect = (MainEffect)this.elementAt(i);
            if (mainEffect == null) continue;
            mainEffect.isRemove = true;
            mainEffect.clearData();
            this.removeElementAt(i);
        }
    }

    public static void addHiEffect(MainEffect mainEffect) {
        hiEffects.addElement(mainEffect);
    }

    public static void addLowEffect(MainEffect mainEffect) {
        lowEffects.addElement(mainEffect);
    }
}

