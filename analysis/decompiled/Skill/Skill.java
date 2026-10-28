/*
 * Decompiled with CFR 0.152.
 */
package Skill;

import CLib.mFont;
import CLib.mGraphics;
import CLib.mHashtable;
import GameObjects.Item;
import GameObjects.ObjectData;
import GameObjects.Player;
import GameScreen.GameScreen;
import InterfaceComponents.MainTabNew;
import InterfaceComponents.TabSkillsNew;
import Main.GameCanvas;
import Model.AvMain;
import Model.MainImage;
import Model.T;
import Skill.LvSkill;

public class Skill {
    public static mHashtable hashImageSkill = new mHashtable();
    public int Id;
    public String name;
    public String detail;
    public byte typeSkill;
    public byte iconId;
    public byte typeBuff;
    public byte subEff;
    public short range;
    public short lvMin;
    public short performDur;
    public LvSkill[] mLvSkill;
    public static final byte ACTIVE = 0;
    public static final byte BUFF = 1;
    public static final byte PASSIVE = 2;
    public static final byte BUFF_ALL = 0;
    public static final byte BUFF_ME = 1;
    public static final byte BUFF_TEAM = 2;
    public static final byte BUFF_ENEMY = 3;
    public byte typePaint;
    public String[] mcontent;

    public void setContent() {
    }

    public void paint(mGraphics mGraphics2, int n, int n2, int n3) {
        MainImage mainImage = ObjectData.getImageSkill(this.iconId);
        if (mainImage.img != null) {
            mGraphics2.drawImage(mainImage.img, n, n2, n3, true);
        } else {
            mGraphics2.drawRegion(AvMain.imgLoadImg, 0, GameCanvas.gameTick % 12 * 16, 16, 16, 0, n, n2, n3, true);
        }
    }

    public void paintSmall(mGraphics mGraphics2, int n, int n2, int n3) {
        MainImage mainImage = ObjectData.getImageSkill(this.iconId);
        if (mainImage.img != null) {
            mGraphics2.drawRegion(mainImage.img, 1, 1, 18, 18, 0, n, n2, n3, false);
        } else {
            mGraphics2.drawRegion(AvMain.imgLoadImg, 0, GameCanvas.gameTick % 12 * 16, 16, 16, 0, n, n2, n3, false);
        }
    }

    public String[] getContent() {
        block10: {
            if (this.mcontent != null) break block10;
            if (GameScreen.player.Lv < this.lvMin) {
                String[] stringArray = mFont.tahoma_7_white.splitFontArray(this.detail, MainTabNew.longwidth > 0 ? MainTabNew.longwidth - 10 : 126);
                this.mcontent = new String[stringArray.length + 1];
                if (GameScreen.player.Lv < this.lvMin) {
                    this.mcontent[0] = T.yeucau + T.level + this.lvMin;
                }
                for (int i = 1; i < this.mcontent.length; ++i) {
                    this.mcontent[i] = stringArray[i - 1];
                }
            } else {
                int n;
                int n2;
                int n3 = Player.mCurentLvSkill[this.Id];
                if (n3 > 0) {
                    n3 += Player.mPlusLvSkill[this.Id];
                }
                if ((n2 = n3 - 1) < 0) {
                    n2 = 0;
                }
                if (this.Id == 0 || n2 >= 15) {
                    n2 = 0;
                }
                LvSkill lvSkill = this.mLvSkill[n2];
                String[] stringArray = lvSkill.getinfo();
                String[] stringArray2 = this.mInfoSkill(n2, Player.mCurentLvSkill[this.Id] == 0);
                int n4 = stringArray.length + stringArray2.length + lvSkill.minfo.length;
                this.mcontent = new String[n4];
                n4 = 0;
                for (n = 0; n < stringArray2.length; ++n) {
                    this.mcontent[n4] = stringArray2[n];
                    ++n4;
                }
                for (n = 0; n < stringArray.length; ++n) {
                    this.mcontent[n4] = stringArray[n];
                    ++n4;
                }
                for (n = 0; n < lvSkill.minfo.length; ++n) {
                    this.mcontent[n4] = Item.nameInfoItem[lvSkill.minfo[n].id] + ": " + Item.getPercent(Item.isPercentInfoItem[lvSkill.minfo[n].id], lvSkill.minfo[n].value);
                    ++n4;
                }
            }
        }
        return this.mcontent;
    }

    public String[] mInfoSkill(int n, boolean bl) {
        String[] stringArray = null;
        int n2 = 0;
        String[] stringArray2 = mFont.tahoma_7_white.splitFontArray(this.detail, MainTabNew.longwidth > 0 ? MainTabNew.longwidth - 10 : 126);
        n2 += stringArray2.length + 1;
        if (bl) {
            ++n2;
        } else if (Player.mCurentLvSkill[this.Id] < 10 && this.Id != 0) {
            ++n2;
        }
        if (this.typeSkill == 1) {
            ++n2;
        }
        if (this.mLvSkill[n].range_lan > 0) {
            ++n2;
        }
        stringArray = new String[n2];
        n2 = 0;
        if (bl) {
            stringArray[n2] = T.chuahoc;
            ++n2;
        } else if (Player.mCurentLvSkill[this.Id] < 10 && this.Id != 0) {
            stringArray[n2] = T.nangcapyeucau + this.mLvSkill[n].LvRe;
            ++n2;
        }
        String string = "[" + T.kynang + T.active + "]";
        if (this.typeSkill == 1) {
            string = "[" + T.kynang + T.buff + "]";
        } else if (this.typeSkill == 2) {
            string = "[" + T.kynang + T.passive + "]";
        }
        stringArray[n2] = string;
        ++n2;
        for (int i = 0; i < stringArray2.length; ++i) {
            stringArray[n2] = stringArray2[i];
            ++n2;
        }
        if (this.typeSkill == 1) {
            String string2 = T.tacdunglen + " : ";
            switch (this.typeBuff) {
                case 0: {
                    string2 = string2 + T.moinguoi;
                    break;
                }
                case 1: {
                    string2 = string2 + T.banthan;
                    break;
                }
                case 2: {
                    string2 = string2 + T.trongdoi;
                    break;
                }
                case 3: {
                    string2 = string2 + T.kethu;
                }
            }
            stringArray[n2++] = string2;
        }
        if (this.mLvSkill[n].range_lan > 0) {
            stringArray[n2++] = T.phamvilan + this.mLvSkill[n].range_lan;
        }
        return stringArray;
    }

    public static void resetContent() {
        for (int i = 0; i < TabSkillsNew.vecPaintSkill.size(); ++i) {
            Skill skill = (Skill)TabSkillsNew.vecPaintSkill.elementAt(i);
            skill.mcontent = null;
        }
    }
}

