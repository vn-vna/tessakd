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
import GameScreen.GameScreen;
import Main.GameCanvas;
import Model.CRes;
import Model.MainImage;

public class PetFly
extends Pet {
    public byte[] dypet = new byte[]{0, 5, 5};
    public byte dyshadow = 0;
    int xs = 0;
    int ys = 0;
    int act;
    int fr;

    public PetFly(MainObject mainObject, short s, byte by, byte by2, byte by3) {
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
        this.limitMove = 48;
        this.Direction = 0;
        this.vMax = 4;
        this.Action = 0;
        this.state = 0;
        this.timeAutoAction = CRes.random(200, 250);
        this.MonWater = 0;
        this.limitAttack = 10;
        this.timeMaxMoveAttack = 0;
        this.yfly = (short)50;
        this.dyshadow = by2 == 3 || by2 == 9 ? (byte)0 : (byte)6;
    }

    public PetFly(short s, int n, byte by, byte by2, byte by3) {
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
        this.limitMove = 48;
        this.Direction = 0;
        this.vMax = 4;
        this.Action = 0;
        this.state = 0;
        this.timeAutoAction = CRes.random(200, 250);
        this.MonWater = 3;
        this.limitAttack = 10;
        this.timeMaxMoveAttack = 0;
        this.yfly = (short)50;
        this.dyshadow = by2 == 3 || by2 == 9 ? (byte)0 : (byte)6;
    }

    protected void roam() {
        switch (this.typemove) {
            case 2: {
                this.vMax = 1;
                if (this.Action == 1) {
                    if ((this.time > this.timeAutoAction || MainObject.getDistance(this.x + this.vx, this.y + this.vy, this.xAnchor, this.yAnchor) >= this.limitMove) && this.yfly >= 50) {
                        if (this.count > 20 && !this.isWater) {
                            this.time = 0;
                            this.vx = 0;
                            this.vy = 0;
                            this.Action = 0;
                            this.count = 0;
                        }
                        if (this.count <= 20) {
                            ++this.count;
                        }
                    }
                } else if (this.Action == 0) {
                    this.vx = 0;
                    this.vy = 0;
                    if (this.yfly >= 0) {
                        this.yfly = (short)(this.yfly - 5);
                    }
                    if (GameCanvas.gameTick % 30 == 0) {
                        this.Direction = CRes.random(4);
                    }
                    if (this.time > this.timeAutoAction && this.yfly <= 0) {
                        this.time = 0;
                        this.setSpeedInDirection(this.vMax);
                        this.Action = 1;
                    }
                }
                if (this.owner != null) {
                    if (this.owner.Action == 1) {
                        if (MainObject.getDistance(this.x, this.y, this.ownerX, this.ownerY) <= 40) break;
                        this.setState((byte)1);
                        break;
                    }
                    if (this.owner.Action != 0 || MainObject.getDistance(this.x, this.y, this.ownerX, this.ownerY) <= this.limitMove * 2) break;
                    this.setState((byte)3);
                    break;
                }
                int n = MainObject.getDistance(this.x, this.y, GameScreen.player.x, GameScreen.player.y);
                if (n >= 80 || n <= 40 || CRes.random(6) != 0) break;
                this.setState((byte)6);
                break;
            }
            case 1: {
                this.vMax = 1;
                if (this.Action == 1) {
                    if (this.time > this.timeAutoAction || CRes.random(16) == 0 || MainObject.getDistance(this.x + this.vx, this.y + this.vy, this.xAnchor, this.yAnchor) >= this.limitMove) {
                        this.time = 0;
                        this.Action = 0;
                        this.vx = 0;
                        this.vy = 0;
                    }
                } else if (this.Action == 0) {
                    this.vx = 0;
                    this.vy = 0;
                    if (this.time > this.timeAutoAction / 2 || CRes.random(12) == 0) {
                        this.time = 0;
                        this.Action = 1;
                        this.Direction = CRes.random(4);
                        this.setSpeedInDirection(this.vMax);
                    }
                }
                if (this.owner != null) {
                    if (this.owner.Action == 1 && MainObject.getDistance(this.x, this.y, this.ownerX, this.ownerY) > 40) {
                        this.setState((byte)1);
                    }
                    if (this.owner.Action != 0 || MainObject.getDistance(this.x, this.y, this.ownerX, this.ownerY) <= this.limitMove * 2) break;
                    this.setState((byte)3);
                    break;
                }
                int n = MainObject.getDistance(this.x, this.y, GameScreen.player.x, GameScreen.player.y);
                if (n >= 80 || n <= 40 || CRes.random(6) != 0) break;
                this.setState((byte)6);
                break;
            }
        }
    }

    public void paint(mGraphics mGraphics2) {
        try {
            this.paintDataEff_Top(mGraphics2, this.x, this.y);
            this.paintBuffFirst(mGraphics2);
            if (this.state == 1 && (this.Direction == 1 || this.Direction == 0)) {
                this.Direction = this.PrevDir;
            }
            MainImage mainImage = ObjectData.getImagePet(this.imageId);
            if (mainImage.img != null) {
                if (this.wOne < 0) {
                    this.hOne = mImage.getImageHeight(mainImage.img.image) / this.nFrame;
                    this.wOne = mImage.getImageWidth(mainImage.img.image) / 2;
                }
                mGraphics2.drawImage(MainObject.shadow, this.x + this.xtam, this.y - this.dyshadow, 3, false);
                if (this.isWater && this.yfly <= 0) {
                    mGraphics2.drawRegion(mainImage.img, this.xs, this.ys, this.wOne, this.hOne, this.Direction > 2 ? 2 : 0, this.x + this.xtam, this.y + this.dyWater + 8, 33, false);
                    mGraphics2.drawRegion(MainObject.water, 0, (this.Action != 0 ? 2 : 0) * 17 + GameCanvas.gameTick / 2 % 2 * 17, 28, 17, 0, this.x + this.xtam, this.y + this.dyWater - 8, 3, false);
                } else {
                    mGraphics2.drawRegion(mainImage.img, this.xs, this.ys, this.wOne, this.hOne, this.Direction > 2 ? 2 : 0, this.x + this.xtam, this.y - this.yfly, 33, false);
                }
            }
            this.paintBuffLast(mGraphics2);
            this.paintDataEff_Bot(mGraphics2, this.x, this.y);
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    protected void attack() {
        this.vMax = 12;
        if (this.vecObjskill != null && this.vecObjskill.size() > 0) {
            Object_Effect_Skill object_Effect_Skill = (Object_Effect_Skill)this.vecObjskill.elementAt(this.attackCount);
            MainObject mainObject = MainObject.get_Object(object_Effect_Skill.ID, object_Effect_Skill.tem);
            if (mainObject == null || mainObject.hp <= 0) {
                this.isRunAttack = false;
                this.setState((byte)4);
                return;
            }
            this.toX = mainObject.x;
            this.toY = mainObject.y;
            this.move_to_XY();
            if (MainObject.getDistance(this.x + this.vx, this.y + this.vy, mainObject.x, mainObject.y) < 30) {
                if (this.yfly >= 15) {
                    this.yfly = (short)(this.yfly - 15);
                }
                this.f = 4;
                if (this.yfly <= 15) {
                    this.addAttackEffect();
                    this.setState((byte)4);
                }
            } else if (this.yfly <= 50) {
                this.yfly = (short)(this.yfly + 5);
            }
        }
    }

    public void update() {
        this.act = this.Action;
        if (this.act > this.mAction.length - 1) {
            this.act = 0;
        }
        if (this.f > this.mAction[this.act][this.Direction > 2 ? 2 : this.Direction].length - 1) {
            this.f = 0;
        }
        this.fr = this.mAction[this.act][this.Direction > 2 ? 2 : this.Direction][this.f] / 3;
        this.xs = (this.fr > 1 ? 1 : this.fr) * this.wOne;
        this.fr = this.mAction[this.act][this.Direction > 2 ? 2 : this.Direction][this.f];
        this.ys = this.fr % 3 * this.hOne;
        if (this.xs == -1) {
            this.xs = 0;
        }
        if (this.ys == -1) {
            this.ys = 0;
        }
        if (this.yfly <= 0) {
            this.ys = 0;
            this.xs = 0;
        }
        if ((this.vx < 0 && this.vy > 0 || this.vx > 0 && this.vy > 0) && this.state == 0 && this.yfly >= 30) {
            this.f = 3;
            this.vx = this.vx < 0 ? -3 : 3;
            this.vy = this.vy < 0 ? -3 : 3;
        }
        super.update();
    }
}

