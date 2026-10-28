/*
 * Decompiled with CFR 0.152.
 */
package Main;

import CLib.mImage;
import CLib.mSystem;
import GameObjects.Item;
import GameObjects.MainObject;
import GameScreen.LoginScreen;
import GameScreen.PaintInfoGameScreen;
import GameScreen.WorldMapScreen;
import InterfaceComponents.MainTabNew;
import InterfaceComponents.MsgDialog;
import InterfaceComponents.PopupChat;
import Map.BackGround;
import Map.ItemMap;
import Model.AvMain;
import Model.FrameImage;
import Model.Point;

public class CreateImageStatic {
    public static void LoadImage() {
        int n;
        PaintInfoGameScreen.loadPaintInfo();
        AvMain.imgLock = mImage.createImage("/interface/lock.img");
        AvMain.imgGlass = mImage.createImage("/interface/glass.img");
        AvMain.imghpmp = mImage.createImage("/interface/hp_mp.img");
        AvMain.imgInfo = mImage.createImage("/interface/infochar.img");
        AvMain.imgBackInfo = mImage.createImage("/interface/backinfo.img");
        AvMain.fraQuest = new FrameImage(mImage.createImage("/interface/quest.img"), 12, 19);
        AvMain.imgFocusMap = mImage.createImage("/interface/focusmap.img");
        AvMain.imgcolorhpmp = mImage.createImage("/interface/color_hp_mp.png");
        AvMain.imgcolorhpmp_back = mImage.createImage("/interface/color_hp_mpback.png");
        AvMain.imgDelaySkill = mImage.createImage("/interface/delayskill.img");
        AvMain.imgLoadImg = mImage.createImage("/interface/loadimg.img");
        AvMain.imgEyeDie = mImage.createImage("/interface/eyedie.img");
        AvMain.imgHotKey = mImage.createImage("/interface/hotkey.img");
        AvMain.imgHotKey2 = mImage.createImage("/interface/hotkey2.img");
        AvMain.imgMess = mImage.createImage("/interface/mess.img");
        AvMain.imgicongt = mImage.createImage("/interface/gt.img");
        AvMain.imgColorItem = mImage.createImage("/interface/coloritem.img");
        AvMain.fraPk = new FrameImage(mImage.createImage("/interface/iconpk.png"), 12, 12);
        AvMain.fraStar = new FrameImage(mImage.createImage("/interface/ar.img"), 15, 15);
        AvMain.fraObjMiniMap = new FrameImage(mImage.createImage("/interface/objminimap.png"), 7, 7);
        AvMain.fraDiamond = new FrameImage(mImage.createImage("/interface/screentab4.img"), 14, 14);
        AvMain.fraPlayerDie = new FrameImage(mImage.createImage("/interface/die_char.img"), 28, 42);
        for (n = 0; n < AvMain.tab.length; ++n) {
            AvMain.tab[n] = mImage.createImage("/interface/tab" + n + ".img");
        }
        AvMain.wimg = mImage.getImageWidth(AvMain.tab[0].image);
        AvMain.imgtextfield = mImage.createImage("/interface/textf.img");
        MainObject.focus = mImage.createImage("/interface/focus.img");
        MainObject.newfocus = mImage.createImage("/interface/newfocus.png");
        MainObject.shadow = mImage.createImage("/shadow.png");
        MainObject.shadow1 = mImage.createImage("/shadow1.png");
        MainObject.water = mImage.createImage("/water.img");
        BackGround.LoadBackGround();
        AvMain.imgSelect = mImage.createImage("/interface/selected_hand.img");
        MsgDialog.fraWaiting = new FrameImage(mImage.createImage("/interface/waiting.img"), 18, 18);
        LoginScreen.logo = mSystem.isIP_TrucTiep ? mImage.createImage("/interface/logoip.png") : mImage.createImage("/interface/logo.png");
        for (n = 0; n < MainTabNew.imgTab.length; ++n) {
            MainTabNew.imgTab[n] = mImage.createImage("/interface/screentab" + n + ".img");
        }
        AvMain.imgPopup = mImage.createImage("/interface/popup.img");
        for (n = 0; n < PopupChat.mPopup.length; ++n) {
            PopupChat.mPopup[n] = mImage.createImage("/interface/chatpopup" + n + ".img");
        }
        PaintInfoGameScreen.imgInfoFocus = mImage.createImage("/interface/infofocus.img");
        PaintInfoGameScreen.imgauto = mImage.createImage("/interface/iconauto.img");
        PaintInfoGameScreen.imgxp = mImage.createImage("/interface/iconxp.img");
        PaintInfoGameScreen.fraStatusArea = new FrameImage(mImage.createImage("/interface/statusarea.png"), 11, 11);
        PaintInfoGameScreen.fralevelup = new FrameImage(mImage.createImage("/interface/levelup.img"), 11, 11);
        WorldMapScreen.fraMyPos = new FrameImage(AvMain.imgFocusMap, 10, 10);
        PaintInfoGameScreen.fraFocusIngame = new FrameImage(mImage.createImage("/interface/focusingame.img"), 32, 11);
        PaintInfoGameScreen.fraEvent = new FrameImage(mImage.createImage("/interface/event.img"), 22, 22);
        Item.fraeffitemdrop = new FrameImage(mImage.createImage("/interface/effitemdrop.img"), 15, 15);
        AvMain.fraMonSample = new FrameImage(mImage.createImage("/interface/monsample.img"), 36, 36);
        Point.FraEffInMap = new FrameImage[1];
        Point.FraEffInMap[0] = new FrameImage(mImage.createImage("/interface/eff_inmap0.png"), 16, 16);
        MainTabNew.img_skIcn = mImage.createImage("/interface/sk_icn.png");
        MainTabNew.img_pkIcn = mImage.createImage("/interface/pk_icn.png");
        MainTabNew.img_arenaIcn = mImage.createImage("/interface/arena_icn.png");
        PaintInfoGameScreen.imgArenaIcon = new FrameImage(mImage.createImage("/interface/iconarena.png"), 18, 18);
        if (!mSystem.isj2me) {
            for (n = 0; n < AvMain.imghitScr.length; ++n) {
                AvMain.imghitScr[n] = mImage.createImage("/interface/hitscr" + n + ".png");
            }
            for (n = 0; n < PaintInfoGameScreen.imgHitWidth.length; ++n) {
                PaintInfoGameScreen.imgHitWidth[n] = mImage.getImageWidth(AvMain.imghitScr[n].image);
                PaintInfoGameScreen.imgHitHeight[n] = mImage.getImageHeight(AvMain.imghitScr[n].image);
            }
        }
        AvMain.imgRect = mImage.createImage("/interface/rect.png");
        ItemMap.img_HouseArena_Die = new FrameImage[4];
        int[] nArray = new int[]{72, 112, 94, 105};
        int[] nArray2 = new int[]{74, 57, 54, 63};
        for (int i = 0; i < 4; ++i) {
            ItemMap.img_HouseArena_Die[i] = new FrameImage(mImage.createImage("/bg/nhabe" + (i + 1) + ".png"), nArray[i], nArray2[i]);
        }
        AvMain.imgStun = new FrameImage(143);
        AvMain.imgSleep = new FrameImage(142);
        AvMain.fraPlayerNo = new FrameImage(139);
        AvMain.imgSelect_1 = mImage.createImage("/interface/imgselect_1.png");
        AvMain.fraFogetPass = new FrameImage(mImage.createImage("/point/fgpass.png"), 14, 14);
        AvMain.imgcolorhpSmall_back = mImage.createImage("/interface/imghpsmall_back.png");
        AvMain.imgcolorhpSmall = mImage.createImage("/interface/imghpsmall.png");
        PaintInfoGameScreen.WBlackclolor = mImage.getImageWidth(AvMain.imgcolorhpmp_back.image);
        PaintInfoGameScreen.WRedclor = mImage.getImageWidth(AvMain.imgcolorhpmp.image);
        MainObject.weaponEff_Gem = new FrameImage[2];
        MainObject.weaponEff_Gem[0] = new FrameImage(150);
        MainObject.weaponEff_Gem[1] = new FrameImage(151);
        AvMain.img18Plus = mImage.createImage("/interface/18.png");
    }
}

