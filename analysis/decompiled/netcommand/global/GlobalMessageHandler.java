/*
 * Decompiled with CFR 0.152.
 */
package netcommand.global;

import CLib.mSystem;
import Main.GameCanvas;
import com.silverknight.TemMidlet;
import net.IMessageHandler;
import net.Message;
import netcommand.Cmd_Message;
import netcommand.IMiniGameMsgHandler;
import netcommand.global.GlobalLogicHandler;

public class GlobalMessageHandler
extends Cmd_Message
implements IMessageHandler {
    public GlobalLogicHandler globalLogicHandler = new GlobalLogicHandler();
    public static GlobalMessageHandler me;
    public IMiniGameMsgHandler miniGameMessageHandler;

    public static GlobalMessageHandler gI() {
        if (me == null) {
            me = new GlobalMessageHandler();
        }
        return me;
    }

    public void setGameLogicHandler(GlobalLogicHandler globalLogicHandler) {
        this.globalLogicHandler = globalLogicHandler;
    }

    public void onConnectOK() {
        this.globalLogicHandler.onConnectOK();
    }

    public void onConnectionFail() {
        this.globalLogicHandler.onConnectFail();
    }

    public void onDisconnected() {
        GlobalLogicHandler.onDisconnect();
    }

    public void onMessage(Message message) {
        switch (message.command) {
            case -96: {
                GameCanvas.readMessenge.npcServer(message);
                break;
            }
            case -92: {
                GameCanvas.readMessenge.useItemArena(message);
                break;
            }
            case -95: {
                GameCanvas.readMessenge.updateMarkKiller(message);
                break;
            }
            case -94: {
                GameCanvas.readMessenge.UpdateInfoArena(message);
                break;
            }
            case -93: {
                mSystem.println("--Nap winphone ");
                break;
            }
            case -91: {
                GameCanvas.readMessenge.receiveLotteryReward(message);
                break;
            }
            case -76: {
                TemMidlet.handleMessage(message);
                break;
            }
            case -75: {
                TemMidlet.handleMessage(message);
                break;
            }
            case -57: {
                GameCanvas.readMessenge.UpdateDataAndroid(message);
                break;
            }
            case -54: {
                GameCanvas.readMessenge.SoSanhDataAndroid(message);
                break;
            }
            case -53: {
                GameCanvas.readMessenge.nap_tien(message);
                break;
            }
            case -52: {
                GameCanvas.readMessenge.loadImageDataCharPart(message);
                break;
            }
            case -51: {
                GameCanvas.readMessenge.loadImage(message);
                break;
            }
            case -49: {
                GameCanvas.readMessenge.loadImageDataAutoEff(message);
                break;
            }
            case -44: {
                GameCanvas.readMessenge.newNPCInfo(message);
                break;
            }
            case -100: {
                GameCanvas.readMessenge.khamNgoc(message);
                break;
            }
            case 1: {
                GameCanvas.readMessenge.Login_Ok(message);
                if (TemMidlet.currentIAPStore != 2 && !mSystem.isIpAppstore()) break;
                TemMidlet.handleAllMessage(message);
                break;
            }
            case -99: {
                GameCanvas.readMessenge.lastlogout(message);
                break;
            }
            case 2: {
                GameCanvas.readMessenge.Login_Fail(message);
                break;
            }
            case 3: {
                GameCanvas.readMessenge.mainCharInfo(message);
                break;
            }
            case 4: {
                GameCanvas.readMessenge.objectMove(message);
                break;
            }
            case 5: {
                GameCanvas.readMessenge.charInfo(message);
                break;
            }
            case 7: {
                GameCanvas.readMessenge.monsterInfo(message);
                break;
            }
            case 8: {
                GameCanvas.readMessenge.playerExit(message);
                break;
            }
            case 12: {
                GameCanvas.readMessenge.changeMap(message);
                break;
            }
            case 37: {
                GameCanvas.readMessenge.InfoServer_Download(message);
                break;
            }
            case 9: {
                GameCanvas.readMessenge.fireMonster(message);
                break;
            }
            case 15: {
                GameCanvas.readMessenge.charWearing(message);
                break;
            }
            case 17: {
                GameCanvas.readMessenge.dieMonster(message);
                break;
            }
            case 10: {
                GameCanvas.readMessenge.monsterFire(message);
                break;
            }
            case 16: {
                GameCanvas.readMessenge.charInventory(message);
                break;
            }
            case 19: {
                GameCanvas.readMessenge.ItemDrop(message);
                break;
            }
            case 20: {
                GameCanvas.readMessenge.GetItemMap(message);
                break;
            }
            case 23: {
                GameCanvas.readMessenge.npcInfo(message);
                break;
            }
            case 25: {
                GameCanvas.readMessenge.itemTemplate(message);
                break;
            }
            case 26: {
                GameCanvas.readMessenge.catalogyMonster(message);
                break;
            }
            case 13: {
                GameCanvas.readMessenge.listChar(message);
                break;
            }
            case -50: {
                GameCanvas.readMessenge.npcBig(message);
                break;
            }
            case 52: {
                GameCanvas.readMessenge.onReceiveInfoQuest(message);
                break;
            }
            case -31: {
                GameCanvas.readMessenge.Dialog_More_server(message);
                break;
            }
            case -32: {
                GameCanvas.readMessenge.Dialog_server(message);
                break;
            }
            case -30: {
                GameCanvas.readMessenge.Dynamic_Menu(message);
                break;
            }
            case 21: {
                GameCanvas.readMessenge.Item_More_Info(message);
                break;
            }
            case 28: {
                GameCanvas.readMessenge.get_Item_Tem(message);
                break;
            }
            case 29: {
                GameCanvas.readMessenge.Skill_List(message);
                break;
            }
            case 30: {
                GameCanvas.readMessenge.Set_XP(message);
                break;
            }
            case 31: {
                GameCanvas.readMessenge.writeUserAccountInfoToRMS(message);
                break;
            }
            case 33: {
                GameCanvas.readMessenge.Level_Up(message);
                break;
            }
            case 32: {
                GameCanvas.readMessenge.use_Potion(message);
                break;
            }
            case 27: {
                GameCanvas.readMessenge.chatPopup(message);
                break;
            }
            case 34: {
                GameCanvas.readMessenge.chatTab(message);
                break;
            }
            case 35: {
                GameCanvas.readMessenge.Friend(message);
                break;
            }
            case 39: {
                GameCanvas.readMessenge.Register(message);
                break;
            }
            case 48: {
                GameCanvas.readMessenge.Party(message);
                break;
            }
            case 36: {
                GameCanvas.readMessenge.Buy_Sell(message);
                break;
            }
            case 40: {
                GameCanvas.readMessenge.Buff(message);
                break;
            }
            case 6: {
                GameCanvas.readMessenge.firePK(message);
                break;
            }
            case 41: {
                GameCanvas.readMessenge.diePlayer(message);
                break;
            }
            case 42: {
                GameCanvas.readMessenge.pk(message);
                break;
            }
            case 49: {
                GameCanvas.readMessenge.other_player_info(message);
                break;
            }
            case 50: {
                GameCanvas.readMessenge.eff_plus_time(message);
                break;
            }
            case 51: {
                GameCanvas.readMessenge.changeArea(message);
                break;
            }
            case 53: {
                GameCanvas.readMessenge.InfoEasyFromServer(message);
                break;
            }
            case 54: {
                GameCanvas.readMessenge.update_Status_Area(message);
                break;
            }
            case 55: {
                GameCanvas.readMessenge.Save_RMS_Server(message);
                break;
            }
            case 56: {
                GameCanvas.readMessenge.List_Server(message);
                break;
            }
            case 57: {
                GameCanvas.readMessenge.List_Pk(message);
                break;
            }
            case 59: {
                GameCanvas.readMessenge.suckhoe(message);
                break;
            }
            case 60: {
                GameCanvas.readMessenge.chat_npc(message);
                break;
            }
            case 61: {
                GameCanvas.readMessenge.name_server(message);
                break;
            }
            case 62: {
                GameCanvas.readMessenge.x2_Xp(message);
                break;
            }
            case 63: {
                GameCanvas.readMessenge.delete_rms(message);
                break;
            }
            case 64: {
                GameCanvas.readMessenge.Help_From_Server(message);
                break;
            }
            case 65: {
                GameCanvas.readMessenge.CharChest(message);
                break;
            }
            case 67: {
                GameCanvas.readMessenge.Rebuild_Item(message);
                break;
            }
            case 73: {
                GameCanvas.readMessenge.ReplacePlusItem(message);
                break;
            }
            case 68: {
                GameCanvas.readMessenge.Thach_Dau(message);
                break;
            }
            case 69: {
                GameCanvas.readMessenge.Clan(message);
                break;
            }
            case 70: {
                GameCanvas.readMessenge.updateHpNPC(message);
                break;
            }
            case 74: {
                GameCanvas.readMessenge.Num_Eff(message);
                break;
            }
            case 75: {
                GameCanvas.readMessenge.EffFormServer(message);
                break;
            }
            case 76: {
                GameCanvas.readMessenge.EffWeather(message);
                break;
            }
            case 77: {
                GameCanvas.readMessenge.Rebuild_Wing(message);
                break;
            }
            case 78: {
                GameCanvas.readMessenge.Open_Box(message);
                break;
            }
            case -90: 
            case 90: {
                GameCanvas.readMessenge.remove_Actor(message);
                break;
            }
            case 44: {
                GameCanvas.readMessenge.UpdatePetContainer(message);
                break;
            }
            case 84: {
                GameCanvas.readMessenge.petAttack(message);
                break;
            }
            case 85: {
                GameCanvas.readMessenge.monsterDetonate(message);
                break;
            }
            case 86: {
                GameCanvas.readMessenge.monsterSkillInfo(message);
                break;
            }
            case -97: {
                GameCanvas.readMessenge.useMount(message);
                break;
            }
            case -98: {
                GameCanvas.readMessenge.useShip(message);
                break;
            }
            case -101: {
                GameCanvas.readMessenge.ThachDau(message);
                break;
            }
            case -102: {
                GameCanvas.readMessenge.StoreInfo(message);
                break;
            }
            case -103: {
                GameCanvas.readMessenge.MiNuongInfo(message);
                break;
            }
            case -104: {
                GameCanvas.readMessenge.infoclanChiemthanh(message);
                break;
            }
            case -105: {
                GameCanvas.readMessenge.onHopRac(message);
                break;
            }
            case -106: {
                GameCanvas.readMessenge.Material_Template(message);
                break;
            }
            case -108: {
                GameCanvas.readMessenge.onFillRec_Time(message);
                break;
            }
            case 22: {
                GameCanvas.readMessenge.onUpSkill(message);
                break;
            }
            case -110: {
                GameCanvas.readMessenge.onInfoMount(message);
            }
        }
    }
}

