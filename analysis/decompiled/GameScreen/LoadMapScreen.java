/*
 * Decompiled with CFR 0.152.
 */
package GameScreen;

import Build_Indo.IndoServer;
import CLib.mFont;
import CLib.mGraphics;
import CLib.mImage;
import CLib.mSound;
import CLib.mSystem;
import GameEffect.EffectMonster;
import GameObjects.MainRMS;
import GameObjects.NPC;
import GameObjects.NPCMini;
import GameScreen.GameScreen;
import GameScreen.LoginScreen;
import GameScreen.MainScreen;
import GameScreen.PaintInfoGameScreen;
import GameScreen.SelectCharScreen;
import GameScreen.StoryScreen;
import InterfaceComponents.iCommand;
import Main.GameCanvas;
import Model.AvMain;
import Model.T;
import Thread_More.MiniMap;
import Thread_More.SaveImageRMS;
import net.Message;
import netcommand.global.GlobalService;

public class LoadMapScreen
extends MainScreen {
    public boolean isLoadSkill = false;
    public boolean isTele = false;
    public boolean isLoadHelp = false;
    public static boolean isNextMap = false;
    long time;
    long timeWait;
    public byte[] mItemMap;
    public byte[] mEffMap;
    public byte[] mNPCMap;
    public static byte[] mMusic = new byte[]{2, 0, 3, 2, 3, 2, 6, 2, 2, 2, 6, 5, 5, 5, 6, 4, 4, 3, 6, 8, 8, 8, 8, 8, 8, 4, 4, 4, 6, 3, 3, 3, 6, 1, 1, 1, 5, 5, 5, 5, 5, 5, 8, 8, 8, 8, 5, 6, 5, 5, 5, 2, 5, 0};

    public void Show() {
        mSystem.my_Gc();
        super.Show();
        this.time = GameCanvas.timeNow;
        GameCanvas.saveImage.start();
        PaintInfoGameScreen.timeChange = 0;
    }

    public void paint(mGraphics mGraphics2) {
        mGraphics2.setColor(0);
        mGraphics2.fillRect(0, 0, GameCanvas.w, GameCanvas.h, false);
        if (LoginScreen.logo != null) {
            mGraphics2.drawImage(LoginScreen.logo, GameCanvas.hw, GameCanvas.hh - 16, 3, false);
            mGraphics2.drawRegion(AvMain.imgLoadImg, 0, GameCanvas.gameTick % 12 * 16, 16, 16, 0, GameCanvas.hw, GameCanvas.hh + mImage.getImageHeight(LoginScreen.logo.image) / 2 - 9, 3, false);
            if (this.timeWait > 0L) {
                mFont.tahoma_7_white.drawString(mGraphics2, this.timeWait + "", GameCanvas.hw, GameCanvas.hh + mImage.getImageHeight(LoginScreen.logo.image) / 2 - 15, 2, false);
            }
        }
    }

    public void update() {
        if ((GameCanvas.timeNow - this.time) / 1000L > 180L && GameCanvas.currentDialog != null) {
            GameCanvas.start_Center_Dialog_Only(T.disconnect, new iCommand(T.exit, 0));
            if (GameScreen.player != null) {
                GameScreen.player.resetAction();
            }
        } else if (GameCanvas.currentDialog == null) {
            // empty if block
        }
        if (isNextMap && this.isLoadSkill && MainRMS.isLoadBegin && (SaveImageRMS.vecSaveImage.size() <= 5 || (GameCanvas.timeNow - this.time) / 1000L > 45L)) {
            if (GameScreen.help.setStep_Next(0, -5)) {
                GameCanvas.story = new StoryScreen();
                GameCanvas.story.setContent();
                GameCanvas.story.Show();
            } else {
                GameScreen.player.resetMove();
                GameScreen.player.posTransRoad = null;
                GameScreen.player.resetAction();
                GameCanvas.game.Show();
                GameCanvas.game.checkRemoveImage();
                if (MainRMS.showAuto.length() > 0 && MainRMS.isLoadShowAuto) {
                    MainRMS.isLoadShowAuto = false;
                    GameCanvas.start_Show_Dialog(T.autoFire + "\n" + MainRMS.showAuto, T.auto);
                }
                GameCanvas.hLoad = GameCanvas.h / 4 * 3;
                PaintInfoGameScreen.setNameMap();
                if (this.isTele) {
                    GameScreen.addEffectKill(58, GameScreen.player.ID, GameScreen.player.typeObject, GameScreen.player.ID, GameScreen.player.typeObject, 0, GameScreen.player.hp);
                    GameScreen.player.isTanHinh = true;
                }
                if (GameCanvas.loadmap.idMap == 0 && GameScreen.help.setStep_Next(5, 8) && (GameCanvas.isVNLanguage || IndoServer.isIndoSv)) {
                    GameScreen.help.NextStep();
                    GameScreen.help.setNext();
                }
            }
            if (this.isLoadHelp) {
                GameScreen.help.setNext();
                this.isLoadHelp = false;
            }
            SelectCharScreen.isSelectOk = true;
            GlobalService.gI().Ok_Change_Map();
            GlobalService.gI().send_cmd_server((byte)59);
            EffectMonster.listEffectMonster.removeAllElements();
            this.PlayMusicLang();
        }
        boolean bl = false;
        if (GameCanvas.timenextLogin - mSystem.currentTimeMillis() > 0L) {
            this.timeWait = (GameCanvas.timenextLogin - mSystem.currentTimeMillis()) / 1000L;
        }
        if (GameCanvas.timenextLogin - mSystem.currentTimeMillis() <= 0L && GameCanvas.timenextLogin > 0L) {
            bl = true;
        }
        if (bl) {
            this.timeWait = 0L;
            GameCanvas.timenextLogin = 0L;
            GameCanvas.login.checkLoginAgain(GameCanvas.IndexServer);
            SelectCharScreen.reSelect = true;
            SelectCharScreen.Canselect = true;
        }
        if (GameCanvas.countLogin > 0L && (mSystem.currentTimeMillis() - GameCanvas.countLogin) / 1000L > 15L) {
            GameCanvas.countLogin = 0L;
            GameCanvas.start_Wait_Dialog(T.Logifail, new iCommand(T.close, 16));
        }
    }

    private void PlayMusicLang() {
        mSound.stopSoundAll();
        if (GameCanvas.loadmap.idMap >= mMusic.length - 1 || mMusic[GameCanvas.loadmap.idMap] < 0) {
            mSound.playMus(0, mSound.volumeMusic, true);
        } else {
            mSound.playMus(mMusic[GameCanvas.loadmap.idMap], mSound.volumeMusic, true);
        }
    }

    public static void NPCBig(Message message) {
        try {
            int n = message.reader().readByte();
            for (int i = 0; i < n; ++i) {
                String string = message.reader().readUTF();
                String string2 = message.reader().readUTF();
                byte by = message.reader().readByte();
                byte by2 = message.reader().readByte();
                short s = message.reader().readShort();
                short s2 = message.reader().readShort();
                byte by3 = message.reader().readByte();
                byte by4 = message.reader().readByte();
                byte by5 = message.reader().readByte();
                NPC nPC = new NPC(string, string2, by, by2, s, s2, by3, by4, by5);
                nPC.IdBigAvatar = message.reader().readByte() + 500;
                nPC.infoObject = message.reader().readUTF();
                nPC.isPerson = message.reader().readByte();
                nPC.typeObject = (byte)2;
                GameScreen.addPlayer(nPC);
                MiniMap.addNPCMini(new NPCMini(by, s, s2));
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }
}

