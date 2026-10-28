/*
 * Decompiled with CFR 0.152.
 */
package GameObjects;

import CLib.mGraphics;
import CLib.mImage;
import GameObjects.MainObject;
import GameObjects.ObjectData;
import GameObjects.Object_Effect_Skill;
import GameObjects.Pet;
import Main.GameCanvas;
import Model.CRes;
import Model.MainImage;
import Skill.Monster_Skill;

public class PetWalk
extends Pet {
    public PetWalk(MainObject mainObject, short s, byte by, byte by2, byte by3) {
        super(s, by, by2, by3);
        this.owner = mainObject;
        this.typeObject = (byte)9;
        this.ID = mainObject.ID;
        this.xAnchor = mainObject.x;
        this.yAnchor = mainObject.y;
        this.x = mainObject.x;
        this.y = mainObject.y;
        this.oldPosX = mainObject.x;
        this.oldPosY = mainObject.y;
        this.wOne = -1;
        this.hOne = -1;
        this.limitMove = 60;
        this.Direction = 0;
        this.vMax = 4;
        this.Action = 0;
        this.state = 0;
        this.timeAutoAction = CRes.random(50, 70);
        this.MonWater = 0;
        this.limitAttack = 30;
        this.timeMaxMoveAttack = 0;
    }

    public PetWalk(short s, int n, byte by, byte by2, byte by3) {
        super(s, by, by2, by3);
        this.owner = null;
        this.typeObject = (byte)9;
        this.ID = n;
        int n2 = this.posArray[CRes.random((int)this.posArray.length)].x;
        int n3 = this.posArray[CRes.random((int)this.posArray.length)].y;
        this.xAnchor = n2;
        this.yAnchor = n3;
        this.x = n2;
        this.y = n3;
        this.oldPosX = n2;
        this.oldPosY = n3;
        this.wOne = -1;
        this.hOne = -1;
        this.limitMove = 120;
        this.Direction = 0;
        this.vMax = 4;
        this.Action = 0;
        this.state = 0;
        this.timeAutoAction = CRes.random(50, 70);
        this.MonWater = 3;
        this.limitAttack = 200;
        this.timeMaxMoveAttack = 0;
    }

    public void paint(mGraphics mGraphics2) {
        try {
            int n;
            this.paintDataEff_Top(mGraphics2, this.x, this.y);
            this.paintBuffFirst(mGraphics2);
            MainImage mainImage = ObjectData.getImagePet(this.imageId);
            if (this.state == 1 && (this.Direction == 1 || this.Direction == 0)) {
                this.Direction = this.PrevDir;
            }
            if ((n = this.Action) > this.mAction.length - 1) {
                n = 0;
            }
            if (this.f > this.mAction[n][this.Direction > 2 ? 2 : this.Direction].length - 1) {
                this.f = 0;
            }
            if (mainImage.img != null) {
                if (this.wOne < 0) {
                    this.hOne = mImage.getImageHeight(mainImage.img.image) / this.nFrame;
                    this.wOne = mImage.getImageWidth(mainImage.img.image) / 2;
                }
                int n2 = 0;
                int n3 = 0;
                n2 = this.mAction[n][this.Direction > 2 ? 2 : this.Direction][this.f] / 3 * this.wOne;
                n3 = this.mAction[n][this.Direction > 2 ? 2 : this.Direction][this.f] % 3 * this.hOne;
                if (this.isWater) {
                    mGraphics2.drawRegion(mainImage.img, n2, n3, this.wOne, this.hOne, this.Direction > 2 ? 2 : 0, this.x + this.xtam, this.y + this.dyWater + 4, 33, false);
                    int n4 = 1;
                    mGraphics2.drawRegion(MainObject.water, 0, (n != 0 ? 2 : 0) * 17 + GameCanvas.gameTick / 2 % 2 * 17, 28, 17, 0, this.x + n4 + this.xtam, this.y + this.dyWater - 4, 3, false);
                } else {
                    mGraphics2.drawRegion(mainImage.img, n2, n3, this.wOne, this.hOne, this.Direction > 2 ? 2 : 0, this.x + this.xtam, this.y, 33, false);
                }
            }
            this.paintBuffLast(mGraphics2);
            this.paintDataEff_Bot(mGraphics2, this.x, this.y);
        }
        catch (Exception exception) {
            exception.printStackTrace();
            exception.toString();
        }
    }

    protected void attack() {
        if (this.skillDefault == null) {
            this.skillDefault = new Monster_Skill(2);
        }
        this.vMax = 9;
        if (this.vecObjskill != null && this.vecObjskill.size() > 0) {
            Object_Effect_Skill object_Effect_Skill = (Object_Effect_Skill)this.vecObjskill.elementAt(this.attackCount);
            MainObject mainObject = MainObject.get_Object(object_Effect_Skill.ID, object_Effect_Skill.tem);
            if (mainObject == null) {
                this.isRunAttack = false;
                return;
            }
            if (MainObject.getDistance(this.x + this.vx, this.y + this.vy, mainObject.x, mainObject.y) > this.limitAttack && mainObject.hp > 0) {
                this.toX = mainObject.x;
                this.toY = mainObject.y;
                this.move_to_XY();
            } else if (GameCanvas.timeNow - this.timeBeginMoveAttack > (long)this.timeMaxMoveAttack) {
                this.IDAttack = object_Effect_Skill.ID;
                object_Effect_Skill.skillMonster = this.skillDefault;
                this.beginFire();
                this.addAttackEffect();
                this.timeMaxMoveAttack = 200;
                this.timeBeginMoveAttack = GameCanvas.timeNow;
                if (this.attackType == 2) {
                    if (this.attackCount >= this.vecObjskill.size() - 1) {
                        this.isDoneAttack = false;
                        this.setState((byte)4);
                        this.attackCount = 0;
                    }
                } else if (this.attackType == 5 && this.attackCount == 3) {
                    this.isDoneAttack = false;
                    this.setState((byte)4);
                    this.attackCount = 0;
                }
            }
        }
    }
}

