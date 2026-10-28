/*
 * Decompiled with CFR 0.152.
 */
package GameObjects;

import CLib.mGraphics;
import CLib.mVector;
import GameObjects.DataEffect;
import GameObjects.MainObject;
import GameObjects.ObjectData;
import GameObjects.Object_Effect_Skill;
import GameObjects.Pet;
import GameScreen.GameScreen;
import Main.GameCanvas;
import Model.CRes;
import Model.MainImage;
import Model.Point;

public class PetTool
extends Pet {
    public byte mAction;
    public int huongY;
    public int flip;
    public boolean isBeginAttack;
    public static final byte M_STAND = 0;
    public static final byte M_DEAD = 1;
    public static final byte M_WALK = 2;
    public static final byte M_ATTACK = 3;
    public int p1;
    public int maxAttack = 0;

    public PetTool(MainObject mainObject, short s, byte by, byte by2, byte by3) {
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
        this.isPetTool = true;
    }

    public PetTool(short s, int n, byte by, byte by2, byte by3) {
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
        this.mAction = 0;
        this.state = 0;
        this.timeAutoAction = CRes.random(200, 250);
        this.MonWater = 3;
        this.limitAttack = 10;
        this.timeMaxMoveAttack = 0;
        this.isPetTool = true;
    }

    public PetTool() {
    }

    public void paint(mGraphics mGraphics2) {
        mVector mVector2 = new mVector();
        mVector mVector3 = (mVector)Pet.PET_DATA.get("" + this.imageId);
        if (mVector3 != null) {
            mVector2 = mVector3;
        }
        if (mVector2.size() == 0) {
            return;
        }
        try {
            DataEffect dataEffect = (DataEffect)mVector2.elementAt(0);
            if (dataEffect != null) {
                this.paintDataEff_Top(mGraphics2, this.x, this.y);
                int n = this.mAction;
                if (this.state == 2 && this.isBeginAttack) {
                    n = 3;
                }
                int n2 = 0;
                int n3 = this.Action;
                if (this.isFly()) {
                    n2 = 35;
                    mGraphics2.drawImage(MainObject.shadow, this.x, this.y - 6, 3, false);
                } else {
                    if (!this.isWater) {
                        mGraphics2.drawImage(MainObject.shadow, this.x, this.y, 3, false);
                    }
                    if (this.isWater) {
                        mGraphics2.drawRegion(MainObject.water, 0, (n3 != 0 ? 2 : 0) * 17 + GameCanvas.gameTick / 2 % 2 * 17, 28, 17, 0, this.x + 1 + this.xtam, this.y + this.dyWater - 4, 3, false);
                    }
                }
                MainImage mainImage = ObjectData.getImagePet(this.imageId);
                mVector mVector4 = (mVector)Pet.PET_DATA.get("" + this.imageId);
                if (mVector4 != null && mainImage != null && mainImage.img != null) {
                    dataEffect.paintPet(mGraphics2, dataEffect.getFrame(this.frame, n, this.huongY), this.x, this.y - n2, this.flip, mainImage.img);
                }
                this.paintDataEff_Bot(mGraphics2, this.x, this.y);
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public void doChangeFrmaeBoss() {
        mVector mVector2 = new mVector();
        mVector mVector3 = (mVector)Pet.PET_DATA.get("" + this.imageId);
        if (mVector3 != null) {
            mVector2 = mVector3;
        }
        DataEffect dataEffect = null;
        if (mVector2.size() > 0) {
            dataEffect = (DataEffect)mVector2.elementAt(0);
            int n = this.mAction;
            if (this.state == 2 && this.isBeginAttack) {
                n = 3;
            }
            this.frame = (byte)((this.frame + 1) % dataEffect.getAnim((int)n, (int)this.huongY).frame.length);
            if (this.maxAttack == 0) {
                this.maxAttack = dataEffect.getAnim((int)3, (int)this.huongY).frame.length;
            }
        }
    }

    public void update() {
        this.doChangeFrmaeBoss();
        try {
            super.update();
            int n = GameCanvas.loadmap.getTile(this.x + this.vx, this.y + this.vy);
            this.setMove(this.MonWater, n);
            if (this.owner != null) {
                if (MainObject.getDistance(this.x, this.y, this.toX, this.toY) <= 10) {
                    this.wayPoint.removeElementAt(0);
                }
                this.xAnchor = this.owner.x;
                this.yAnchor = this.owner.y;
                this.ownerX = this.owner.x;
                this.ownerY = this.owner.y;
            }
            ++this.time;
            switch (this.state) {
                case 0: {
                    this.roam();
                    break;
                }
                case 1: {
                    this.mAction = (byte)2;
                    this.follow();
                    break;
                }
                case 2: {
                    this.attack();
                    break;
                }
                case 3: {
                    this.mAction = (byte)2;
                    this.returnToPlayer();
                    break;
                }
                case 4: {
                    this.mAction = 0;
                    this.waitForCommand();
                    break;
                }
            }
            if (this.state != this.preState) {
                this.preState = this.state;
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    protected void returnToPlayer() {
        this.vMax = this.owner.vMax;
        this.toX = this.owner.x;
        this.toY = this.owner.y;
        this.setHuong(this.toX, this.toY);
        this.move_to_XY();
        if (MainObject.getDistance(this.x, this.y, this.xAnchor, this.yAnchor) < 40 && this.owner.Action != 2) {
            this.setState((byte)0);
        }
    }

    protected void attack() {
        this.vMax = 12;
        if (this.vecObjskill != null && this.vecObjskill.size() > 0) {
            Object_Effect_Skill object_Effect_Skill = (Object_Effect_Skill)this.vecObjskill.elementAt(0);
            MainObject mainObject = MainObject.get_Object(object_Effect_Skill.ID, object_Effect_Skill.tem);
            if (mainObject == null) {
                this.isRunAttack = false;
                this.setState((byte)4);
                return;
            }
            if (MainObject.getDistance(this.x + this.vx, this.y + this.vy, mainObject.x, mainObject.y) > 30 && !this.isBeginAttack && mainObject.hp > 0) {
                this.toX = mainObject.x;
                this.toY = mainObject.y;
                this.setHuong(this.toX, this.toY);
                this.move_to_XY();
                this.mAction = (byte)2;
            } else {
                this.isBeginAttack = true;
            }
            ++this.p1;
            if (this.p1 > this.maxAttack && this.isBeginAttack) {
                try {
                    this.setHuong(mainObject.x, mainObject.y);
                    switch (this.attackType) {
                        case 11: {
                            for (int i = 0; i < this.vecObjskill.size(); ++i) {
                                MainObject mainObject2;
                                Object_Effect_Skill object_Effect_Skill2 = (Object_Effect_Skill)this.vecObjskill.elementAt(i);
                                if (object_Effect_Skill2 == null || (mainObject2 = MainObject.get_Object(object_Effect_Skill2.ID, object_Effect_Skill2.tem)) == null) continue;
                                GameScreen.startNewMagicBeam(15, this, mainObject2, this.x, this.y, -1 * object_Effect_Skill2.hpShow, (short)163, 163, 24);
                            }
                            break;
                        }
                        case 12: {
                            for (int i = 0; i < this.vecObjskill.size(); ++i) {
                                MainObject mainObject3;
                                Object_Effect_Skill object_Effect_Skill3 = (Object_Effect_Skill)this.vecObjskill.elementAt(i);
                                if (object_Effect_Skill3 == null || (mainObject3 = MainObject.get_Object(object_Effect_Skill3.ID, object_Effect_Skill3.tem)) == null) continue;
                                GameScreen.addEffectEndFromSv(57, 21, mainObject3.x, mainObject3.y);
                                GameScreen.addEffectNum("-" + object_Effect_Skill3.hpShow, mainObject3.x, mainObject3.y - mainObject3.hOne, 2, mainObject3.ID);
                            }
                            break;
                        }
                    }
                }
                catch (Exception exception) {
                    // empty catch block
                }
                this.p1 = 0;
                this.isBeginAttack = false;
                this.setState((byte)4);
            }
        }
    }

    protected void roam() {
        this.vMax = 1;
        if (this.Action == 1) {
            if (this.time > this.timeAutoAction || CRes.random(16) == 0 || MainObject.getDistance(this.x + this.vx, this.y + this.vy, this.xAnchor, this.yAnchor) >= this.limitMove) {
                this.time = 0;
                this.Action = 0;
                this.mAction = 0;
                this.vx = 0;
                this.vy = 0;
            }
        } else if (this.Action == 0) {
            this.vx = 0;
            this.vy = 0;
            if (this.time > this.timeAutoAction / 2 || CRes.random(12) == 0) {
                this.time = 0;
                this.Action = 1;
                this.mAction = (byte)2;
                this.Direction = CRes.random(4);
                this.setSpeedInDirection(this.vMax);
            }
        }
        if (this.owner != null) {
            if (this.owner.Action == 1 && MainObject.getDistance(this.x, this.y, this.ownerX, this.ownerY) > 40) {
                this.setState((byte)1);
            }
            if (this.owner.Action == 0 && MainObject.getDistance(this.x, this.y, this.ownerX, this.ownerY) > this.limitMove * 2) {
                this.setState((byte)3);
            }
        } else {
            int n = MainObject.getDistance(this.x, this.y, GameScreen.player.x, GameScreen.player.y);
            if (n < 80 && n > 40 && CRes.random(6) == 0) {
                this.setState((byte)6);
            }
        }
    }

    public void setSpeedInDirection(int n) {
        int n2 = CRes.random_Am_0(3);
        if (CRes.abs(n2) > 1) {
            --n;
        }
        switch (this.Direction) {
            case 1: {
                this.vy = -n;
                this.vx = n2;
                break;
            }
            case 0: {
                this.vy = n;
                this.vx = n2;
                break;
            }
            case 2: {
                this.vy = n2;
                this.vx = -n;
                break;
            }
            case 3: {
                this.vy = n2;
                this.vx = n;
            }
        }
        if (this.vx == 0 && CRes.random(3) == 0) {
            this.time = 0;
            this.Action = 0;
            this.vx = 0;
            this.vy = 0;
            this.mAction = 0;
        }
        this.Direction = this.vx > 0 ? 3 : 2;
        this.huongY = (byte)(this.y > this.toY ? 1 : 0);
        this.flip = (byte)(this.x - this.toX > 0 ? 0 : 2);
        this.flip = 0;
        if (this.Direction == 3) {
            this.flip = 2;
        } else if (this.Direction != 2) {
            if (this.Direction == 0) {
                this.huongY = 0;
            } else if (this.Direction == 1) {
                this.huongY = 1;
            }
        }
    }

    protected void follow() {
        this.vMax = this.owner.vMax;
        this.Action = 1;
        if (MainObject.getDistance(this.oldPosX, this.oldPosY, this.ownerX, this.ownerY) > 40) {
            Point point = new Point(this.ownerX, this.ownerY);
            this.oldPosX = this.ownerX;
            this.oldPosY = this.ownerY;
            this.wayPoint.addElement(point);
        } else if (MainObject.getDistance(this.x, this.y, this.xAnchor, this.yAnchor) < 40) {
            this.wayPoint.removeAllElements();
            this.setState((byte)4);
        }
        if (this.wayPoint.elementAt(0) != null) {
            this.toX = ((Point)this.wayPoint.elementAt((int)0)).x;
            this.toY = ((Point)this.wayPoint.elementAt((int)0)).y;
            this.setHuong(this.toX, this.toY);
            this.move_to_XY();
        }
    }

    public void setHuong(int n, int n2) {
        this.huongY = (byte)(this.y > n2 ? 1 : 0);
        this.flip = (byte)(this.x - n > 0 ? 0 : 2);
        this.flip = 0;
        if (this.Direction == 3) {
            this.flip = 2;
        } else if (this.Direction != 2) {
            if (this.Direction == 0) {
                this.huongY = 0;
            } else if (this.Direction == 1) {
                this.huongY = 1;
            }
        }
    }

    public boolean isFly() {
        DataEffect dataEffect;
        mVector mVector2 = new mVector();
        mVector mVector3 = (mVector)Pet.PET_DATA.get("" + this.imageId);
        if (mVector3 != null) {
            mVector2 = mVector3;
        }
        return (dataEffect = (DataEffect)mVector2.elementAt(0)) != null && dataEffect.isFly <= -5;
    }
}

