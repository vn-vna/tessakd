/*
 * Decompiled with CFR 0.152.
 */
package Skill;

import GameObjects.MainObject;

public class SplashSkill {
    public int typePlash = 0;
    public int min;
    boolean isSung = false;
    public static final byte PLASH_MIN_4 = 0;
    public static final byte PLASH_MIN_6 = 1;
    public static final byte PLASH_MIN_12 = 2;
    public static final byte PLASH_SUNG_0 = 3;
    public static final byte PLASH_2KIEM_LV2_LV4 = 4;
    public static final byte PLASH_MIN_8 = 5;
    public static final byte PLASH_SUNG_LASER = 6;
    public static final byte PLASH_MIN_16 = 7;
    public static final byte PLASH_MIN_10 = 8;
    public static final byte PLASH_BUFF = 9;
    public static final byte PLASH_SUNG_LV4 = 10;
    public static final byte PLASH_SUNG_MIN_8 = 11;
    public static final byte PLASH_KIEM_LV5 = 12;
    public static final byte PLASH_SUNG_BAODAN = 13;
    public static final byte PLASH_NULL = 14;
    public static final byte PLASH_SUNG_DAYDAN = 15;
    public static final byte PLASH_SUNG_MIN_6 = 16;

    public void setPlash(int n) {
        this.typePlash = n;
        this.isSung = false;
        switch (this.typePlash) {
            case 0: {
                this.min = 4;
                break;
            }
            case 1: {
                this.min = 6;
                break;
            }
            case 6: {
                this.min = 30;
                this.isSung = true;
                break;
            }
            case 2: {
                this.min = 12;
                break;
            }
            case 5: {
                this.min = 8;
                break;
            }
            case 10: {
                this.min = 22;
                this.isSung = true;
                break;
            }
            case 7: {
                this.min = 16;
                break;
            }
            case 8: {
                this.min = 10;
                break;
            }
            case 11: {
                this.min = 11;
                this.isSung = true;
                break;
            }
            case 16: {
                this.min = 6;
                this.isSung = true;
            }
        }
    }

    public void update(MainObject mainObject) {
        switch (this.typePlash) {
            case 3: {
                if (mainObject.fplash < 2) {
                    mainObject.frame = 5;
                    mainObject.weapon_frame = 4;
                    break;
                }
                mainObject.setEye(1);
                mainObject.weapon_frame = mainObject.fplash + 2;
                if (mainObject.fplash < 6) {
                    mainObject.frame = 4;
                    break;
                }
                mainObject.Action = 0;
                mainObject.frame = 0;
                mainObject.f = 0;
                mainObject.weapon_frame = 0;
                break;
            }
            case 13: {
                if (mainObject.fplash < 12) {
                    mainObject.frame = 5;
                    mainObject.weapon_frame = 4;
                    break;
                }
                if (mainObject.fplash < 30) {
                    if (mainObject.fplash < 14) {
                        mainObject.weapon_frame = mainObject.fplash - 8;
                        mainObject.frame = 4;
                        break;
                    }
                    if (mainObject.fplash % 2 == 0) {
                        mainObject.setEye(1);
                        mainObject.weapon_frame = 4;
                        mainObject.frame = 5;
                        break;
                    }
                    mainObject.weapon_frame = 7;
                    mainObject.frame = 4;
                    break;
                }
                mainObject.Action = 0;
                mainObject.frame = 0;
                mainObject.f = 0;
                mainObject.weapon_frame = 0;
                break;
            }
            case 15: {
                if (mainObject.fplash < 26) {
                    mainObject.frame = 5;
                    mainObject.weapon_frame = 4;
                    if (mainObject.fplash < 12) break;
                    mainObject.setEye(1);
                    break;
                }
                if (mainObject.fplash < 30) {
                    mainObject.weapon_frame = mainObject.fplash - 22;
                    mainObject.frame = 4;
                    if (mainObject.weapon_frame <= 7) break;
                    mainObject.weapon_frame = 7;
                    break;
                }
                mainObject.Action = 0;
                mainObject.frame = 0;
                mainObject.f = 0;
                mainObject.weapon_frame = 0;
                break;
            }
            case 4: {
                if (mainObject.fplash < 4) {
                    mainObject.frame = 4;
                    mainObject.weapon_frame = 4;
                    break;
                }
                mainObject.setEye(1);
                mainObject.weapon_frame = mainObject.fplash;
                if (mainObject.fplash < 10) {
                    mainObject.frame = 5;
                    break;
                }
                mainObject.Action = 0;
                mainObject.frame = 0;
                mainObject.f = 0;
                mainObject.weapon_frame = 0;
                break;
            }
            case 12: {
                if (mainObject.fplash < 30) break;
                mainObject.Action = 0;
                mainObject.frame = 0;
                mainObject.f = 0;
                mainObject.weapon_frame = 0;
                break;
            }
            case 14: {
                if (mainObject.clazz == 3) {
                    if (mainObject.fplash < 2) {
                        mainObject.frame = 5;
                        mainObject.weapon_frame = 4;
                        break;
                    }
                    mainObject.setEye(1);
                    mainObject.weapon_frame = mainObject.fplash + 2;
                    if (mainObject.fplash < 6) {
                        mainObject.frame = 4;
                        break;
                    }
                    mainObject.Action = 0;
                    mainObject.frame = 0;
                    mainObject.f = 0;
                    mainObject.weapon_frame = 0;
                    break;
                }
                if (mainObject.fplash < 4) {
                    mainObject.frame = 4;
                    mainObject.weapon_frame = 4;
                    break;
                }
                mainObject.setEye(1);
                mainObject.weapon_frame = mainObject.fplash;
                if (mainObject.fplash < 8) {
                    mainObject.frame = 5;
                    break;
                }
                mainObject.Action = 0;
                mainObject.frame = 0;
                mainObject.f = 0;
                mainObject.weapon_frame = 0;
                break;
            }
            case 9: {
                if (mainObject.fplash < 30) {
                    mainObject.frame = 4;
                    if (mainObject.clazz == 3) {
                        mainObject.frame = 5;
                    }
                    mainObject.weapon_frame = 4;
                    break;
                }
                mainObject.Action = 0;
                mainObject.frame = 0;
                mainObject.f = 0;
                mainObject.weapon_frame = 0;
                break;
            }
            default: {
                if (mainObject.fplash < this.min) {
                    mainObject.frame = 4;
                    if (this.isSung) {
                        mainObject.frame = 5;
                    }
                    mainObject.weapon_frame = 4;
                    if (mainObject.weapon_frame <= 4) break;
                    mainObject.weapon_frame = 4;
                    break;
                }
                mainObject.setEye(1);
                mainObject.weapon_frame = mainObject.fplash - (this.min - 4);
                if (mainObject.fplash < this.min + 4) {
                    mainObject.frame = 5;
                    if (!this.isSung) break;
                    mainObject.frame = 4;
                    break;
                }
                mainObject.Action = 0;
                mainObject.frame = 0;
                mainObject.f = 0;
                mainObject.weapon_frame = 0;
            }
        }
        ++mainObject.fplash;
    }
}

