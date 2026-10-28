/*
 * Decompiled with CFR 0.152.
 */
package Thread_More;

import CLib.TemGraphics;
import CLib.mGraphics;
import CLib.mImage;
import CLib.mSystem;
import CLib.mVector;
import GameObjects.MainObject;
import GameObjects.MainQuest;
import GameObjects.NPCMini;
import GameObjects.ObjectParty;
import GameObjects.Player;
import GameScreen.GameScreen;
import GameScreen.PaintInfoGameScreen;
import GameScreen.WorldMapScreen;
import Main.GameCanvas;
import Model.AvMain;
import Model.Point;
import Model.mCamera;
import Thread_More.LoadMap;

public class MiniMap {
    public int maxX;
    public int maxY;
    public static int wMini = 1;
    public mCamera miniCamera = new mCamera();
    public static mImage imgMiniMap;
    public static mVector vecNPC_Map;
    public static Point pHelp;
    int[] color = new int[]{-15731219, -661753, -4539997, -1173963};

    public void setSize() {
        if (GameCanvas.w > 300 && GameCanvas.h > 300) {
            wMini = 3;
        } else if (GameCanvas.w > 200 && GameCanvas.h > 200) {
            wMini = 2;
        }
        this.maxX = 25;
        this.maxY = 20;
        if (this.maxX > GameCanvas.loadmap.mapW) {
            this.maxX = GameCanvas.loadmap.mapW;
        }
        if (this.maxY > GameCanvas.loadmap.mapH) {
            this.maxY = GameCanvas.loadmap.mapH;
        }
        if (GameCanvas.isTouch) {
            PaintInfoGameScreen.xFocus = GameCanvas.w - GameCanvas.minimap.maxX * wMini - 55;
        }
        this.miniCamera.setAll((GameCanvas.loadmap.mapW - this.maxX) * wMini, (GameCanvas.loadmap.mapH - this.maxY) * wMini, (GameScreen.player.x / LoadMap.wTile - this.maxX / 2) * wMini, (GameScreen.player.y / LoadMap.wTile - this.maxY / 2) * wMini);
        imgMiniMap = MiniMap.CreateMiniMap(wMini);
    }

    public void paint(mGraphics mGraphics2) {
        Object object;
        int n;
        if (GameScreen.infoGame.isMapThachdau() || GameScreen.infoGame.isMapchienthanh()) {
            return;
        }
        mGraphics2.setColor(-9164782);
        mGraphics2.fillRect(-3, -3, this.maxX * wMini + 6, this.maxY * wMini + 6, false);
        mGraphics2.setColor(-470164);
        mGraphics2.fillRect(-2, -2, this.maxX * wMini + 4, this.maxY * wMini + 4, false);
        mGraphics2.setColor(-12052464);
        mGraphics2.fillRect(-1, -1, this.maxX * wMini + 2, this.maxY * wMini + 2, false);
        mGraphics2.setClip(0, 0, this.maxX * wMini, this.maxY * wMini);
        mGraphics2.translate(-this.miniCamera.xCam, -this.miniCamera.yCam);
        if (imgMiniMap != null && MiniMap.imgMiniMap.image != null) {
            mGraphics2.drawImage(imgMiniMap, 0, 0, 0, true);
        }
        if (LoadMap.typeMap == 2) {
            for (n = 0; n < GameScreen.Vecplayers.size(); ++n) {
                object = (MainObject)GameScreen.Vecplayers.elementAt(n);
                if (((MainObject)object).typeObject != 1) continue;
                AvMain.fraObjMiniMap.drawFrame(11, ((MainObject)object).x / LoadMap.wTile * wMini, ((MainObject)object).y / LoadMap.wTile * wMini, 0, 3, mGraphics2);
            }
        }
        for (n = 0; n < vecNPC_Map.size(); ++n) {
            object = (NPCMini)vecNPC_Map.elementAt(n);
            AvMain.fraObjMiniMap.drawFrame(((NPCMini)object).type + 4, ((NPCMini)object).x / LoadMap.wTile * wMini, ((NPCMini)object).y / LoadMap.wTile * wMini, 0, 3, mGraphics2);
        }
        AvMain.fraObjMiniMap.drawFrame(GameScreen.player.Action == 4 ? 9 : GameScreen.player.Direction, GameScreen.player.x / LoadMap.wTile * wMini, GameScreen.player.y / LoadMap.wTile * wMini, 0, 3, mGraphics2);
        if (LoadMap.idTile == 9) {
            mGraphics2.setColor(367554);
        } else {
            mGraphics2.setColor(-16776961);
        }
        if (Player.party != null) {
            for (n = 0; n < Player.party.vecPartys.size(); ++n) {
                object = (ObjectParty)Player.party.vecPartys.elementAt(n);
                if (((ObjectParty)object).name.compareTo(GameScreen.player.name) == 0 || ((ObjectParty)object).idMap != GameCanvas.loadmap.idMap) continue;
                AvMain.fraObjMiniMap.drawFrame(10, ((ObjectParty)object).x / LoadMap.wTile * wMini, ((ObjectParty)object).y / LoadMap.wTile * wMini, 0, 3, mGraphics2);
            }
        }
        if (pHelp != null && MiniMap.pHelp.frame == GameCanvas.loadmap.idMap) {
            n = MiniMap.pHelp.x;
            int n2 = MiniMap.pHelp.y;
            if (n < this.miniCamera.xCam + 3) {
                n = this.miniCamera.xCam + 3;
            }
            if (n > this.miniCamera.xCam + this.maxX * wMini - 3) {
                n = this.miniCamera.xCam + this.maxX * wMini - 3;
            }
            if (n2 < this.miniCamera.yCam + 3) {
                n2 = this.miniCamera.yCam + 3;
            }
            if (n2 > this.miniCamera.yCam + this.maxY * wMini - 3) {
                n2 = this.miniCamera.yCam + this.maxY * wMini - 3;
            }
            WorldMapScreen.fraMyPos.drawFrame(GameCanvas.gameTick / 2 % WorldMapScreen.fraMyPos.nFrame, n, n2, 0, 3, mGraphics2);
        }
        mGraphics2.endClip();
    }

    public static void addNPCMini(NPCMini nPCMini) {
        MainObject mainObject = MainObject.get_Object(nPCMini.ID, (byte)2);
        if (mainObject != null) {
            MiniMap.SetTypeNPC(mainObject);
            nPCMini.type = mainObject.typeNPC;
            vecNPC_Map.addElement(nPCMini);
        }
    }

    public static void addMonMini(int n, byte by) {
        MainObject mainObject = MainObject.get_Object(n, by);
        if (mainObject != null) {
            NPCMini nPCMini = new NPCMini(n, mainObject.x, mainObject.y);
            nPCMini.type = 8;
            for (int i = 0; i < vecNPC_Map.size(); ++i) {
                NPCMini nPCMini2 = (NPCMini)vecNPC_Map.elementAt(i);
                if (nPCMini2.ID != n || nPCMini2.type != nPCMini.type) continue;
                nPCMini2.x = nPCMini.x;
                nPCMini2.y = nPCMini.y;
                return;
            }
            vecNPC_Map.addElement(nPCMini);
        }
    }

    public static void SetTypeNPC(MainObject mainObject) {
        MainQuest mainQuest;
        int n;
        for (n = 0; n < MainQuest.vecQuestList.size(); ++n) {
            mainQuest = (MainQuest)MainQuest.vecQuestList.elementAt(n);
            if (mainQuest.idNPC_From != mainObject.ID || mainObject.typeNPC != 0 && mainObject.typeNPC != 2) continue;
            mainObject.typeNPC = 1;
        }
        for (n = 0; n < MainQuest.vecQuestFinish.size(); ++n) {
            mainQuest = (MainQuest)MainQuest.vecQuestFinish.elementAt(n);
            if (mainQuest.idNPC_To != mainObject.ID) continue;
            mainObject.typeNPC = 3;
        }
        for (n = 0; n < MainQuest.vecQuestDoing_Main.size(); ++n) {
            mainQuest = (MainQuest)MainQuest.vecQuestDoing_Main.elementAt(n);
            if (mainQuest.idNPC_To != mainObject.ID || mainObject.typeNPC != 0) continue;
            mainObject.typeNPC = 2;
        }
        for (n = 0; n < MainQuest.vecQuestDoing_Sub.size(); ++n) {
            mainQuest = (MainQuest)MainQuest.vecQuestDoing_Sub.elementAt(n);
            if (mainQuest.idNPC_To != mainObject.ID || mainObject.typeNPC != 0) continue;
            mainObject.typeNPC = 2;
        }
    }

    public void setPoint(int n, int n2, int n3) {
        if (pHelp == null) {
            pHelp = new Point();
        }
        MiniMap.pHelp.x = n * wMini - wMini / 2;
        MiniMap.pHelp.y = n2 * wMini - wMini / 2;
        MiniMap.pHelp.frame = n3;
    }

    public static mImage CreateMiniMap(int n) {
        try {
            int n2;
            mImage mImage2 = mImage.createImage(GameCanvas.loadmap.mapW * n, GameCanvas.loadmap.mapH * n);
            TemGraphics temGraphics = mImage2.getGraphics();
            if (GameCanvas.mapBack != null) {
                temGraphics.g.setColor(GameCanvas.mapBack.colorMini);
                temGraphics.g.fillRect(0, 0, mImage2.image.getWidth(), mImage2.image.getHeight());
            }
            for (n2 = 0; n2 < GameCanvas.loadmap.mapW; ++n2) {
                for (int i = 0; i < GameCanvas.loadmap.mapH; ++i) {
                    int n3 = GameCanvas.loadmap.mapPaint[i * GameCanvas.loadmap.mapW + n2] - 1;
                    if (n3 <= -1 || LoadMap.imgTileSmall.image == null) continue;
                    temGraphics.g.drawRegion(LoadMap.imgTileSmall.image, 0 * mGraphics.zoomLevel, n3 * 3 * mGraphics.zoomLevel, n * mGraphics.zoomLevel, n * mGraphics.zoomLevel, 0, n2 * n * mGraphics.zoomLevel, i * n * mGraphics.zoomLevel, 0);
                }
            }
            block10: for (n2 = 0; n2 < LoadMap.vecPointChange.size(); ++n2) {
                Point point = (Point)LoadMap.vecPointChange.elementAt(n2);
                temGraphics.g.setColor(-10621185);
                switch (point.f) {
                    case 0: {
                        temGraphics.g.fillRect((point.x * n / LoadMap.wTile - n) * mGraphics.zoomLevel, (point.y * n / LoadMap.wTile - 2 * n) * mGraphics.zoomLevel, n * mGraphics.zoomLevel, n * 4 * mGraphics.zoomLevel);
                        continue block10;
                    }
                    case 1: {
                        temGraphics.g.fillRect(point.x * n / LoadMap.wTile * mGraphics.zoomLevel, (point.y * n / LoadMap.wTile - 2 * n) * mGraphics.zoomLevel, n * mGraphics.zoomLevel, n * 4 * mGraphics.zoomLevel);
                        continue block10;
                    }
                    case 2: {
                        temGraphics.g.fillRect((point.x * n / LoadMap.wTile - 2 * n) * mGraphics.zoomLevel, point.y * n / LoadMap.wTile * mGraphics.zoomLevel, 4 * n * mGraphics.zoomLevel, n * mGraphics.zoomLevel);
                        continue block10;
                    }
                    case 3: {
                        temGraphics.g.fillRect((point.x * n / LoadMap.wTile - 2 * n) * mGraphics.zoomLevel, point.y * n / LoadMap.wTile * mGraphics.zoomLevel, 4 * n * mGraphics.zoomLevel, n * mGraphics.zoomLevel);
                    }
                }
            }
            return mImage2;
        }
        catch (Exception exception) {
            mSystem.println("--CreateMiniMap: " + exception.toString());
            return null;
        }
    }

    static {
        vecNPC_Map = new mVector("MiniMap vecNPC_Map");
    }
}

