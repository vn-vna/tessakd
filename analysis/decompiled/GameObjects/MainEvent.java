/*
 * Decompiled with CFR 0.152.
 */
package GameObjects;

import Main.GameCanvas;
import Model.AvMain;
import netcommand.global.GlobalService;

public class MainEvent
extends AvMain {
    public int IDObj;
    public int IDCmd;
    public int isNew;
    public int numThachDau;
    public String nameEvent;
    public String contentEvent;
    public static final byte CHAT = 0;
    public static final byte KET_BAN = 1;
    public static final byte PARTY = 2;
    public static final byte BUY_SELL = 3;
    public static final byte THACH_DAU = 4;
    public static final byte MOI_VAO_CLAN = 5;
    public boolean isRemove = false;

    public MainEvent(int n, int n2, String string, String string2) {
        this.IDObj = n;
        this.IDCmd = n2;
        this.nameEvent = string;
        this.contentEvent = string2;
    }

    public void commandPointer(int n, int n2) {
        switch (n) {
            case 1: {
                GlobalService.gI().Friend((byte)n2, this.nameEvent);
                this.isRemove = true;
                GameCanvas.end_Dialog();
                break;
            }
            case 2: {
                if (n2 == 1) {
                    GlobalService.gI().Party((byte)2, this.nameEvent);
                }
                this.isRemove = true;
                GameCanvas.end_Dialog();
                break;
            }
            case 3: {
                if (n2 == 1) {
                    GlobalService.gI().Buy_Sell((byte)1, this.nameEvent, (byte)0, (short)0, 0);
                }
                this.isRemove = true;
                GameCanvas.end_Dialog();
                break;
            }
            case 4: {
                if (n2 == 1) {
                    GlobalService.gI().Thach_Dau((byte)1, this.nameEvent);
                } else if (n2 == 2) {
                    GlobalService.gI().Re_Info_Other_Object(this.nameEvent, (byte)1);
                }
                this.isRemove = true;
                GameCanvas.end_Dialog();
                break;
            }
            case 5: {
                if (n2 == 1) {
                    GlobalService.gI().Add_And_AnS_MemClan((byte)11, this.nameEvent);
                }
                this.isRemove = true;
                GameCanvas.end_Dialog();
                break;
            }
        }
    }
}

