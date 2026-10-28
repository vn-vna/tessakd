/*
 * Decompiled with CFR 0.152.
 */
package Thread_More;

import CLib.mGraphics;
import CLib.mImage;
import GameObjects.MainObject;
import GameObjects.NPCMini;
import GameObjects.ObjectParty;
import GameObjects.Player;
import GameScreen.GameScreen;
import GameScreen.MainScreen;
import GameScreen.WorldMapScreen;
import InterfaceComponents.iCommand;
import Main.GameCanvas;
import Model.AvMain;
import Model.T;
import Model.mCamera;
import Thread_More.LoadMap;
import Thread_More.MiniMap;

public class MiniMapFull_Screen
extends MainScreen {
    int maxX;
    int maxY;
    int LimitX;
    int LimitY;
    int speedShowX;
    int speedShowY;
    int wMini;
    mCamera miniCamera = new mCamera();
    iCommand cmdBack;
    public mImage imgtest;
    byte typeEnd = (byte)-1;
    boolean canShowMiniMap = true;

    public static MiniMapFull_Screen gI() {
        if (GameCanvas.fullMiniMap == null) {
            GameCanvas.fullMiniMap = new MiniMapFull_Screen();
        }
        return GameCanvas.fullMiniMap;
    }

    public void Show() {
        this.canShowMiniMap = true;
        this.lastScreen = GameCanvas.game;
        this.init();
        super.Show();
        if (this.maxX == this.LimitX && this.maxY == this.LimitY) {
            this.canShowMiniMap = false;
        }
    }

    public void init() {
        this.cmdBack = new iCommand(T.back, -1, this);
        this.maxX = GameCanvas.minimap.maxX;
        this.maxY = GameCanvas.minimap.maxY;
        this.LimitX = GameCanvas.loadmap.mapW;
        this.LimitY = GameCanvas.loadmap.mapH;
        if (this.LimitX > GameCanvas.w - GameCanvas.hCommand) {
            this.LimitX = GameCanvas.w - GameCanvas.hCommand;
        }
        if (this.LimitY > GameCanvas.h - GameCanvas.hCommand) {
            this.LimitY = GameCanvas.h - GameCanvas.hCommand;
        }
        this.speedShowX = (this.LimitX - this.maxX) / 5;
        this.speedShowY = (this.LimitY - this.maxY) / 5;
        if (GameCanvas.isTouch) {
            this.wMini = 3;
            this.imgtest = MiniMap.CreateMiniMap(this.wMini);
        } else {
            this.wMini = MiniMap.wMini;
            this.imgtest = MiniMap.imgMiniMap;
        }
        this.typeEnd = (byte)-1;
    }

    public void commandPointer(int n, int n2) {
        switch (n) {
            case -1: {
                if (!this.canShowMiniMap) {
                    this.typeEnd = (byte)2;
                    return;
                }
                if (this.typeEnd != 0) break;
                this.typeEnd = 1;
            }
        }
    }

    public void paint(mGraphics mGraphics2) {
        Object object;
        int n;
        this.lastScreen.paint(mGraphics2);
        if (GameScreen.infoGame.isMapArena(GameCanvas.loadmap.idMap)) {
            GameCanvas.resetTrans(mGraphics2);
            mGraphics2.fillRect(0, 0, GameCanvas.w, GameCanvas.h, 0, 60, false);
        }
        if (GameCanvas.isTouch) {
            mGraphics2.translate(GameCanvas.w - this.maxX * this.wMini, 0);
        } else {
            mGraphics2.translate(GameCanvas.w - this.maxX * this.wMini - 3, GameCanvas.h - 23 - this.maxY * this.wMini);
        }
        mGraphics2.setColor(-9164782);
        mGraphics2.fillRect(-3, -3, this.maxX * this.wMini + 6, this.maxY * this.wMini + 6, false);
        mGraphics2.setColor(-470164);
        mGraphics2.fillRect(-2, -2, this.maxX * this.wMini + 4, this.maxY * this.wMini + 4, false);
        mGraphics2.setColor(-12052464);
        mGraphics2.fillRect(-1, -1, this.maxX * this.wMini + 2, this.maxY * this.wMini + 2, false);
        mGraphics2.setClip(0, 0, this.maxX * this.wMini, this.maxY * this.wMini);
        if (this.imgtest != null) {
            mGraphics2.drawImage(this.imgtest, 0, 0, 0, false);
        }
        if (LoadMap.typeMap == 2) {
            for (n = 0; n < GameScreen.Vecplayers.size(); ++n) {
                object = (MainObject)GameScreen.Vecplayers.elementAt(n);
                if (((MainObject)object).typeObject != 1) continue;
                AvMain.fraObjMiniMap.drawFrame(11, ((MainObject)object).x / LoadMap.wTile * this.wMini, ((MainObject)object).y / LoadMap.wTile * this.wMini, 0, 3, mGraphics2);
            }
        }
        for (n = 0; n < MiniMap.vecNPC_Map.size(); ++n) {
            object = (NPCMini)MiniMap.vecNPC_Map.elementAt(n);
            AvMain.fraObjMiniMap.drawFrame(((NPCMini)object).type + 4, ((NPCMini)object).x / LoadMap.wTile * this.wMini, ((NPCMini)object).y / LoadMap.wTile * this.wMini, 0, 3, mGraphics2);
        }
        AvMain.fraObjMiniMap.drawFrame(GameScreen.player.Action == 4 ? 9 : GameScreen.player.Direction, GameScreen.player.x / LoadMap.wTile * this.wMini, GameScreen.player.y / LoadMap.wTile * this.wMini, 0, 3, mGraphics2);
        mGraphics2.setColor(-16776961);
        if (Player.party != null) {
            for (n = 0; n < Player.party.vecPartys.size(); ++n) {
                object = (ObjectParty)Player.party.vecPartys.elementAt(n);
                if (((ObjectParty)object).name.compareTo(GameScreen.player.name) == 0 || ((ObjectParty)object).idMap != GameCanvas.loadmap.idMap) continue;
                AvMain.fraObjMiniMap.drawFrame(10, ((ObjectParty)object).x / LoadMap.wTile * this.wMini, ((ObjectParty)object).y / LoadMap.wTile * this.wMini, 0, 3, mGraphics2);
            }
        }
        if (MiniMap.pHelp != null && MiniMap.pHelp.frame == GameCanvas.loadmap.idMap) {
            n = MiniMap.pHelp.x;
            int n2 = MiniMap.pHelp.y;
            if (n < this.miniCamera.xCam + 3) {
                n = this.miniCamera.xCam + 3;
            }
            if (n > this.miniCamera.xCam + this.maxX * this.wMini - 3) {
                n = this.miniCamera.xCam + this.maxX * this.wMini - 3;
            }
            if (n2 < this.miniCamera.yCam + 3) {
                n2 = this.miniCamera.yCam + 3;
            }
            if (n2 > this.miniCamera.yCam + this.maxY * this.wMini - 3) {
                n2 = this.miniCamera.yCam + this.maxY * this.wMini - 3;
            }
            WorldMapScreen.fraMyPos.drawFrame(GameCanvas.gameTick / 2 % WorldMapScreen.fraMyPos.nFrame, n, n2, 0, 3, mGraphics2);
        }
        mGraphics2.endClip();
        GameCanvas.resetTrans(mGraphics2);
        if (GameScreen.infoGame.isMapArena(GameCanvas.loadmap.idMap)) {
            GameScreen.infoGame.paintSttArena(mGraphics2, 10, 40, 20, 20);
        }
        GameScreen.infoGame.paintPos_minimap(mGraphics2, GameCanvas.w, 0);
    }

    public void update() {
        if (this.typeEnd == 2) {
            this.imgtest = null;
            this.lastScreen.Show();
        } else if (this.typeEnd == 1) {
            if (this.maxX > GameCanvas.minimap.maxX) {
                this.maxX -= this.speedShowX * 2;
                if (this.maxX <= GameCanvas.minimap.maxX) {
                    this.maxX = GameCanvas.minimap.maxX;
                    this.typeEnd = (byte)2;
                }
            }
            if (this.maxY > GameCanvas.minimap.maxY) {
                this.maxY -= this.speedShowY * 2;
                if (this.maxY <= GameCanvas.minimap.maxY) {
                    this.maxY = GameCanvas.minimap.maxY;
                    this.typeEnd = (byte)2;
                }
            }
        } else {
            if (this.maxX < this.LimitX) {
                this.maxX += this.speedShowX;
                if (this.maxX >= this.LimitX) {
                    this.maxX = this.LimitX;
                    this.typeEnd = 0;
                }
            }
            if (this.maxY < this.LimitY) {
                this.maxY += this.speedShowY;
                if (this.maxY >= this.LimitY) {
                    this.maxY = this.LimitY;
                    this.typeEnd = 0;
                }
            }
        }
        this.lastScreen.update();
        for (int i = 0; i < GameCanvas.keyMyHold.length; ++i) {
            if (!GameCanvas.keyMyHold[i]) continue;
            this.cmdBack.perform();
            GameCanvas.clearKeyHold(i);
            break;
        }
    }

    public void updatePointer() {
        if (GameCanvas.isPointerDown) {
            this.cmdBack.perform();
        }
    }
}

