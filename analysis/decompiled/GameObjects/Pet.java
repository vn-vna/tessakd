/*
 * Decompiled with CFR 0.152.
 */
package GameObjects;

import CLib.mHashtable;
import CLib.mVector;
import GameObjects.MainMonster;
import GameObjects.MainObject;
import GameObjects.Object_Effect_Skill;
import GameObjects.PetFly;
import GameObjects.PetTool;
import GameObjects.PetWalk;
import GameScreen.GameScreen;
import Main.GameCanvas;
import Model.CRes;
import Model.Point;
import Skill.ListSkill;
import Thread_More.LoadMap;

public class Pet
extends MainMonster {
    public static final byte ROAM = 0;
    public static final byte FOLLOW = 1;
    public static final byte ATTACK = 2;
    public static final byte RETURN = 3;
    public static final byte WAIT = 4;
    public static final byte DEATH = 5;
    public static final byte ATTRACTION = 6;
    public static final byte ATTACK_OWL = 0;
    public static final byte ATTACK_BAT = 1;
    public static final byte ATTACK_MELEE = 2;
    public static final byte ATTACK_POISON_NOVA = 3;
    public static final byte ATTACK_ICE_NOVA = 4;
    public static final byte ATTACK_FIRE_BLAST = 5;
    public static final byte ATTACK_PET_EAGLE = 10;
    public static final byte ATTACK_TOOL_1 = 11;
    public static final byte ATTACK_TOOL_2 = 12;
    public boolean isPetTool;
    public static mHashtable PET_DATA = new mHashtable();
    public static final byte TYPE_MOVE_WALK = 0;
    public static final byte TYPE_MOVE_FLY = 1;
    public static final byte TYPE_MOVE_FLY_AND_MOVE = 2;
    public static final byte TYPE_MOVE_PET_TOOL = 3;
    public static byte[][][] mElfAnimFrame = new byte[][][]{new byte[][]{{0, 0, 0, 0, 1, 1, 1, 1}, {0, 0, 0, 0, 1, 1, 1, 1}, {0, 0, 0, 0, 1, 1, 1, 1}}, new byte[][]{{0, 0, 0, 0, 1, 1, 1, 1}, {0, 0, 0, 0, 1, 1, 1, 1}, {0, 0, 0, 0, 1, 1, 1, 1}}, new byte[][]{{2, 2, 2, 2, 2, 3, 3, 3}, {2, 2, 2, 2, 2, 3, 3, 3}, {2, 2, 2, 2, 2, 3, 3, 3}}, new byte[][]{{2, 2, 2, 2, 2, 3, 3, 3}, {2, 2, 2, 2, 2, 3, 3, 3}, {2, 2, 2, 2, 2, 3, 3, 3}}, new byte[][]{{0, 0, 0, 0, 1, 1, 1, 1}, {0, 0, 0, 0, 1, 1, 1, 1}, {0, 0, 0, 0, 1, 1, 1, 1}}};
    public static byte[][][] mWoftAnimFrame = new byte[][][]{new byte[][]{{0, 0, 0, 0, 1, 1, 1, 1}, {0, 0, 0, 0, 1, 1, 1, 1}, {0, 0, 0, 0, 1, 1, 1, 1}}, new byte[][]{{2, 2, 2, 3, 3, 3, 4, 4}, {2, 2, 2, 3, 3, 3, 4, 4}, {2, 2, 2, 3, 3, 3, 4, 4}}, new byte[][]{{5, 5, 5, 5, 5, 5, 1, 1}, {5, 5, 5, 5, 5, 5, 1, 1}, {5, 5, 5, 5, 5, 5, 1, 1}}, new byte[][]{{5, 5, 5, 5, 5, 5, 1, 1}, {5, 5, 5, 5, 5, 5, 1, 1}, {5, 5, 5, 5, 5, 5, 1, 1}}, new byte[][]{{6, 6, 6, 6, 6, 6, 6, 6}, {6, 6, 6, 6, 6, 6, 6, 6}, {6, 6, 6, 6, 6, 6, 6, 6}}};
    public static byte[][][] mBat = new byte[][][]{new byte[][]{{2, 2, 2, 3, 3, 3, 4, 4, 3}, {2, 2, 2, 3, 3, 3, 4, 4, 3}, {2, 2, 2, 3, 3, 3, 4, 4, 3, 3}}, new byte[][]{{2, 2, 2, 3, 3, 3, 4, 4, 3, 3}, {2, 2, 2, 3, 3, 3, 4, 4, 3, 3}, {2, 2, 2, 3, 3, 3, 4, 4, 3, 3}}, new byte[][]{{5, 5, 5, 5, 5, 5, 1, 1}, {5, 5, 5, 5, 5, 5}, {5, 5, 5, 5, 5, 5}}, new byte[][]{{5, 5, 5, 5, 5, 5}, {5, 5, 5, 5, 5, 5, 1, 1}, {5, 5, 5, 5, 5, 5, 1, 1}}, new byte[][]{{6, 6, 6, 6, 6, 6, 6, 6}, {6, 6, 6, 6, 6, 6, 6, 6}, {6, 6, 6, 6, 6, 6, 6, 6}}};
    public static byte[][][] mDragon = new byte[][][]{new byte[][]{{2, 2, 2, 3, 3, 3, 4, 4, 3}, {2, 2, 2, 3, 3, 3, 4, 4, 3}, {2, 2, 2, 3, 3, 3, 4, 4, 3, 3}}, new byte[][]{{2, 2, 2, 3, 3, 3, 4, 4, 3, 3}, {2, 2, 2, 3, 3, 3, 4, 4, 3, 3}, {2, 2, 2, 3, 3, 3, 4, 4, 3, 3}}, new byte[][]{{5, 5, 5, 5, 5, 5, 1, 1}, {5, 5, 5, 5, 5, 5}, {5, 5, 5, 5, 5, 5}}, new byte[][]{{5, 5, 5, 5, 5, 5}, {5, 5, 5, 5, 5, 5, 1, 1}, {5, 5, 5, 5, 5, 5, 1, 1}}, new byte[][]{{6, 6, 6, 6, 6, 6, 6, 6}, {6, 6, 6, 6, 6, 6, 6, 6}, {6, 6, 6, 6, 6, 6, 6, 6}}};
    public static byte[][][] mOwl = new byte[][][]{new byte[][]{{1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}}, new byte[][]{{2, 2, 2, 3, 3, 3, 4, 4, 3, 3}, {2, 2, 2, 3, 3, 3, 4, 4, 3, 3}, {2, 2, 2, 3, 3, 3, 4, 4, 3, 3}}, new byte[][]{{2, 2, 2, 3, 3, 3, 4, 4, 3, 3}, {2, 2, 2, 3, 3, 3, 4, 4, 3, 3}, {2, 2, 2, 3, 3, 3, 4, 4, 3, 3}}, new byte[][]{{5, 2, 2, 2, 3, 3, 3, 4, 4, 3, 3}, {5, 5, 5, 5, 5, 5, 5}, {5, 5, 5, 5, 5, 5, 5}}, new byte[][]{{6, 6, 6, 6, 6, 6, 6, 6}, {6, 6, 6, 6, 6, 6, 6, 6}, {6, 6, 6, 6, 6, 6, 6, 6}}};
    public static byte[][][] mEagle = new byte[][][]{new byte[][]{{1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}}, new byte[][]{{2, 2, 2, 3, 3, 3, 4, 4, 3, 3}, {2, 2, 2, 3, 3, 3, 4, 4, 3, 3}, {2, 2, 2, 3, 3, 3, 4, 4, 3, 3}}, new byte[][]{{2, 2, 2, 3, 3, 3, 4, 4, 3, 3}, {2, 2, 2, 3, 3, 3, 4, 4, 3, 3}, {2, 2, 2, 3, 3, 3, 4, 4, 3, 3}}, new byte[][]{{5, 2, 2, 2, 3, 3, 3, 4, 4, 3, 3}, {5, 5, 5, 5, 5, 5, 5}, {5, 5, 5, 5, 5, 5, 5}}, new byte[][]{{6, 6, 6, 6, 6, 6, 6, 6}, {6, 6, 6, 6, 6, 6, 6, 6}, {6, 6, 6, 6, 6, 6, 6, 6}}};
    protected Point[] posArray = new Point[]{new Point(13 * LoadMap.wTile, 12 * LoadMap.wTile), new Point(11 * LoadMap.wTile, 21 * LoadMap.wTile), new Point(22 * LoadMap.wTile, 20 * LoadMap.wTile), new Point(23 * LoadMap.wTile, 16 * LoadMap.wTile), new Point(16 * LoadMap.wTile, 14 * LoadMap.wTile), new Point(13 * LoadMap.wTile, 22 * LoadMap.wTile)};
    protected final byte DIS_TO_ATTRACT = (byte)80;
    protected final byte DIS_TO_FOLLOW = (byte)40;
    protected boolean isSequenceAttack;
    protected boolean isDoneAttack;
    protected short type;
    protected byte imageId;
    protected byte state;
    protected byte preState;
    protected byte attackType;
    protected int attackCount;
    protected int oldPosX;
    protected int oldPosY;
    protected int ownerX;
    protected int ownerY;
    public int xtam;
    public int vatam = 6;
    public short yfly;
    public byte typemove;
    protected mVector wayPoint = new mVector("Pet wayPoint");
    protected MainObject owner;
    public static mHashtable HashImagePet = new mHashtable();
    public static byte[] mTypemove = new byte[]{2, 1, 0, 2, 0, 1};

    public static Pet createPet(short s, int n, byte by, byte by2) {
        Pet pet = null;
        byte by3 = 0;
        by3 = mTypemove[s];
        switch (by3) {
            case 0: {
                pet = new PetWalk(s, n, by, by2, by3);
                break;
            }
            case 1: 
            case 2: {
                pet = new PetFly(s, n, by, by2, by3);
                break;
            }
            case 3: {
                pet = new PetTool(s, n, by, by2, by3);
                break;
            }
        }
        return pet;
    }

    public static Pet createPet(MainObject mainObject, short s, byte by, byte by2) {
        Pet pet = null;
        byte by3 = 0;
        by3 = mTypemove[s];
        switch (by3) {
            case 0: {
                pet = new PetWalk(mainObject, s, by, by2, by3);
                break;
            }
            case 1: 
            case 2: {
                pet = new PetFly(mainObject, s, by, by2, by3);
                break;
            }
            case 3: {
                pet = new PetTool(mainObject, s, by, by2, by3);
                break;
            }
        }
        return pet;
    }

    public Pet() {
    }

    public Pet(short s, byte by, byte by2, byte by3) {
        this.nFrame = by;
        this.type = s;
        this.imageId = by2;
        this.isDoneAttack = false;
        this.ListKillNow = new ListSkill();
        this.typemove = by3;
        this.setAnim();
    }

    public void setAnim() {
        switch (this.typemove) {
            case 0: {
                this.mAction = mWoftAnimFrame;
                break;
            }
            case 1: {
                this.mAction = mBat;
                break;
            }
            case 2: {
                this.mAction = mOwl;
                break;
            }
        }
    }

    public void clearWayPoints() {
        this.wayPoint.removeAllElements();
    }

    public void setState(byte by) {
        this.state = by;
    }

    protected void roam() {
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

    protected void standStill() {
        this.vx = 0;
        this.vy = 0;
        this.Action = 4;
        if (this.owner != null && this.owner.Action == 0) {
            this.setState((byte)0);
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
            this.move_to_XY();
        }
    }

    public void move_to_XY() {
        super.move_to_XY();
        if (!(this.state == 3 || this.owner.Direction != 1 && this.owner.Direction != 0 || this.isPetTool)) {
            this.xtam += this.vatam;
            if (this.vatam > 0) {
                this.Direction = 3;
            }
            if (this.vatam < 0) {
                this.Direction = 2;
            }
            if (this.xtam + this.vatam >= 48 && this.vatam > 0 || this.xtam + this.vatam < -48 && this.vatam < 0) {
                this.vatam *= -1;
            }
        }
    }

    public void initAttackState(byte by) {
        try {
            this.attackType = by;
            this.isRunAttack = true;
            this.timeBeginMoveAttack = GameCanvas.timeNow;
            this.attackCount = 0;
            this.isSequenceAttack = false;
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    protected void attack() {
        if (this.isSequenceAttack) {
            if (this.attackCount == 3) {
                this.isDoneAttack = false;
                this.setState((byte)4);
                this.attackCount = 0;
            }
        } else if (this.isDoneAttack) {
            this.isDoneAttack = false;
            this.setState((byte)4);
        }
    }

    protected void returnToPlayer() {
        this.vMax = this.owner.vMax;
        this.toX = this.owner.x;
        this.toY = this.owner.y;
        this.move_to_XY();
        if (MainObject.getDistance(this.x, this.y, this.xAnchor, this.yAnchor) < 40 && this.owner.Action != 2) {
            this.setState((byte)0);
        }
    }

    protected void waitForCommand() {
        this.vx = 0;
        this.vy = 0;
        this.Action = this.typemove == 1 || this.typemove == 2 ? 1 : 0;
        if (this.owner != null) {
            if (this.owner.Action == 0) {
                this.wayPoint.removeAllElements();
                this.setState((byte)0);
            } else if (this.owner.Action == 1 && MainObject.getDistance(this.x, this.y, this.xAnchor, this.yAnchor) > 40) {
                this.wayPoint.removeAllElements();
                this.setState((byte)1);
            }
        }
    }

    public void addAttackEffect() {
        if (this.vecObjskill != null && this.vecObjskill.size() > 0) {
            switch (this.attackType) {
                case 0: {
                    GameScreen.addEffectEndKill(10, this.x, this.y);
                    for (int i = 0; i < this.vecObjskill.size(); ++i) {
                        Object_Effect_Skill object_Effect_Skill = (Object_Effect_Skill)this.vecObjskill.elementAt(i);
                        MainObject mainObject = MainObject.get_Object(object_Effect_Skill.ID, object_Effect_Skill.tem);
                        if (mainObject != null && object_Effect_Skill.hpShow > 0) {
                            GameScreen.addEffectNum("-" + object_Effect_Skill.hpShow, mainObject.x, mainObject.y - mainObject.hOne, 0, this.owner.ID);
                        }
                        mVector mVector2 = new mVector("Pet target");
                        mVector2.addElement(object_Effect_Skill);
                        GameScreen.addEffectKill(89, this.owner.ID, this.owner.typeObject, mVector2);
                    }
                    ++this.attackCount;
                    break;
                }
                case 1: {
                    GameScreen.addEffectEndKill(51, this.x, this.y - 8);
                    for (int i = 0; i < this.vecObjskill.size(); ++i) {
                        Object_Effect_Skill object_Effect_Skill = (Object_Effect_Skill)this.vecObjskill.elementAt(i);
                        MainObject mainObject = MainObject.get_Object(object_Effect_Skill.ID, object_Effect_Skill.tem);
                        if (mainObject != null && object_Effect_Skill.hpShow > 0) {
                            GameScreen.addEffectNum("-" + object_Effect_Skill.hpShow, mainObject.x, mainObject.y - mainObject.hOne, 0, this.owner.ID);
                        }
                        mVector mVector3 = new mVector("Pet target2");
                        mVector3.addElement(object_Effect_Skill);
                        GameScreen.addEffectKill(92, this.owner.ID, this.owner.typeObject, mVector3);
                    }
                    ++this.attackCount;
                    break;
                }
                case 2: {
                    GameScreen.addEffectKill(50, this.ID, (byte)9, this.vecObjskill);
                    ++this.attackCount;
                    break;
                }
                case 3: {
                    GameScreen.addEffectKill(91, this.ID, this.typeObject, this.vecObjskill);
                    ++this.attackCount;
                    break;
                }
                case 4: {
                    GameScreen.addEffectKill(90, this.ID, this.typeObject, this.vecObjskill);
                    ++this.attackCount;
                    break;
                }
                case 5: {
                    GameScreen.addEffectKill(81, this.x, this.y - 20, 200, (short)0, (byte)0);
                    ++this.attackCount;
                    break;
                }
                case 10: {
                    for (int i = 0; i < this.vecObjskill.size(); ++i) {
                        Object_Effect_Skill object_Effect_Skill = (Object_Effect_Skill)this.vecObjskill.elementAt(i);
                        MainObject mainObject = MainObject.get_Object(object_Effect_Skill.ID, object_Effect_Skill.tem);
                        if (mainObject == null || object_Effect_Skill.hpShow <= 0) continue;
                        GameScreen.addEffectNum("-" + object_Effect_Skill.hpShow, mainObject.x, mainObject.y - mainObject.hOne, 0, this.owner.ID);
                    }
                    break;
                }
            }
        }
    }

    private void attract() {
        this.vMax = 3;
        this.Action = 1;
        this.toX = GameScreen.player.x;
        this.toY = GameScreen.player.y;
        this.move_to_XY();
        if (MainObject.getDistance(this.x, this.y, this.toX, this.toY) < 40) {
            this.setState((byte)0);
        }
    }

    private void updatePetAction() {
        ++this.f;
        if (this.f > this.mAction[this.Action][this.Direction > 2 ? 2 : this.Direction].length - 1) {
            this.f = 0;
            if (this.Action == 3 || this.Action == 2) {
                this.Action = 0;
                this.vx = 0;
                this.vy = 0;
                this.isDoneAttack = true;
            }
        }
    }

    public void update() {
        try {
            super.update();
            this.updatePetAction();
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
            if ((this.typemove == 1 || this.typemove == 2) && this.Action != 0 && this.yfly < 50) {
                this.yfly = (short)(this.yfly + 3);
            }
            switch (this.state) {
                case 0: {
                    this.roam();
                    break;
                }
                case 1: {
                    this.follow();
                    break;
                }
                case 2: {
                    this.attack();
                    break;
                }
                case 3: {
                    this.returnToPlayer();
                    break;
                }
                case 4: {
                    this.waitForCommand();
                    break;
                }
                case 6: {
                    this.attract();
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

    public boolean findOwner(MainObject mainObject) {
        return this.owner.equals(mainObject);
    }
}

