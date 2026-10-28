/*
 * Decompiled with CFR 0.152.
 */
package GameObjects;

import Build_Indo.IndoServer;
import GameObjects.AutoGetItem;
import GameObjects.Player;
import GameScreen.GameScreen;
import GameScreen.PaintInfoGameScreen;
import InterfaceComponents.MsgDialog;
import InterfaceComponents.TabSkillsNew;
import Main.GameCanvas;
import Model.CRes;
import Model.T;
import Skill.Skill;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import netcommand.global.GlobalService;

public class MainRMS {
    public static final byte SKILL = 0;
    public static final byte HELP = 1;
    public static final byte BEGIN_GAME = 2;
    public static final byte AUTO = 3;
    public static final byte TOUCH = 4;
    public static boolean isLoadBegin = false;
    public static boolean isLoadShowAuto = true;
    public static String showAuto = "";

    public static void RequietRMS() {
        GlobalService.gI().Save_RMS_Server((byte)1, (byte)0, null);
        MainRMS.setLoadRMS((byte)0, null);
    }

    public static void setLoadRMS(byte by, byte[] byArray) {
        switch (by) {
            case 0: {
                if (GameScreen.player == null) break;
                TabSkillsNew.loadSkill(byArray);
                break;
            }
            case 1: {
                GameScreen.help.LoadStep(byArray);
                GameCanvas.load.isLoadHelp = true;
                if (GameCanvas.isVNLanguage || IndoServer.isIndoSv || GameScreen.help.Step <= 0 && GameScreen.help.Next <= -5) break;
                GameScreen.isFinishHelp = true;
                GameCanvas.end_Dialog();
                Player.isLockKey = false;
                break;
            }
            case 2: {
                GameScreen.help.loadBeginGame(byArray);
                isLoadBegin = true;
                break;
            }
            case 3: {
                if (byArray == null) break;
                MainRMS.setLoadAuto(byArray);
                break;
            }
            case 4: {
                if (byArray == null) break;
                if (GameCanvas.isTouch) {
                    PaintInfoGameScreen.isLevelPoint = byArray[0] == 1;
                    PaintInfoGameScreen.setPosTouch();
                }
                if (byArray.length <= 1) break;
                PaintInfoGameScreen.isShowInfoAuto = byArray[1] == 1;
            }
        }
    }

    public static void setLoadAuto(byte[] byArray) {
        int n;
        boolean bl;
        showAuto = "";
        boolean bl2 = false;
        Player.isAutoHPMP = byArray[0] == 1;
        MsgDialog.mHPMP[0] = byArray[1];
        MsgDialog.mHPMP[1] = byArray[2];
        if (Player.isAutoHPMP && PaintInfoGameScreen.isShowInfoAuto) {
            bl2 = true;
            showAuto = showAuto + T.autoHP + "\n  +" + T.mAuto[0] + MsgDialog.mHPMP[0] + "%\n  +" + T.mAuto[1] + MsgDialog.mHPMP[1] + "%";
        }
        boolean bl3 = bl = byArray[3] == 1;
        if (bl) {
            Player.autoItem = new AutoGetItem(byArray[4], byArray[5], byArray[6]);
        }
        if (bl && PaintInfoGameScreen.isShowInfoAuto) {
            if (bl2) {
                showAuto = showAuto + "\n";
            } else {
                bl2 = true;
            }
            showAuto = showAuto + T.autoItem + "\n  +" + T.mAutoItem[0] + ": " + T.mValueAutoItem[0][Player.autoItem.valueColorItem];
            showAuto = showAuto + "\n  +" + T.mAutoItem[1] + ": " + T.mValueAutoItem[1][Player.autoItem.isGetMoney];
            showAuto = showAuto + "\n  +" + T.mAutoItem[2] + ": " + T.mValueAutoItem[2][Player.autoItem.isGetPotion];
        }
        Player.isAutoBuff = byArray[7];
        int n2 = MsgDialog.MaxSkillBuff;
        if (byArray.length - 7 >= n2) {
            n2 -= CRes.abs(n2 - (byArray.length - 7)) + 1;
        }
        for (n = 0; n < n2; ++n) {
            MsgDialog.Autobuff[n][1] = byArray[8 + n];
        }
        if (Player.isAutoBuff == 1 && PaintInfoGameScreen.isShowInfoAuto) {
            if (bl2) {
                showAuto = showAuto + "\n";
            } else {
                bl2 = true;
            }
            showAuto = showAuto + T.autoBuff;
            for (n = 0; n < n2; ++n) {
                if (MsgDialog.Autobuff[n][1] != 1) continue;
                Skill skill = (Skill)TabSkillsNew.vecPaintSkill.elementAt(MsgDialog.Autobuff[n][2]);
                showAuto = showAuto + "\n  " + skill.name;
            }
        }
        if (showAuto.length() > 0 && isLoadShowAuto && GameCanvas.currentScreen == GameCanvas.game) {
            isLoadShowAuto = false;
            GameCanvas.start_Show_Dialog(T.autoFire + "\n  " + showAuto, T.auto);
        }
    }

    public static void setSaveAuto() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        try {
            dataOutputStream.writeByte(Player.isAutoHPMP ? 1 : 0);
            dataOutputStream.writeByte(MsgDialog.mHPMP[0]);
            dataOutputStream.writeByte(MsgDialog.mHPMP[1]);
            dataOutputStream.writeByte(Player.autoItem == null ? 0 : 1);
            if (Player.autoItem == null) {
                dataOutputStream.writeByte(0);
                dataOutputStream.writeByte(0);
                dataOutputStream.writeByte(0);
            } else {
                dataOutputStream.writeByte(Player.autoItem.valueColorItem);
                dataOutputStream.writeByte(Player.autoItem.isGetMoney);
                dataOutputStream.writeByte(Player.autoItem.isGetPotion);
            }
            dataOutputStream.writeByte(Player.isAutoBuff);
            for (int i = 0; i < MsgDialog.MaxSkillBuff; ++i) {
                dataOutputStream.writeByte(MsgDialog.Autobuff[i][1]);
            }
            CRes.saveRMSName((byte)3, byteArrayOutputStream.toByteArray());
            dataOutputStream.close();
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public static void setSaveTouch() {
        byte[] byArray = new byte[]{(byte)(PaintInfoGameScreen.isLevelPoint ? 1 : 0), (byte)(PaintInfoGameScreen.isShowInfoAuto ? 1 : 0)};
        try {
            CRes.saveRMSName((byte)4, byArray);
        }
        catch (Exception exception) {
            // empty catch block
        }
    }
}

