package com.slyvr.beans;

import org.newdawn.slick.Color;
import org.newdawn.slick.geom.Rectangle;

public class Entity {
	private int entId;
    private String entName;
    private Rectangle entPos;
    private int entHp;
    private int entSpeed;
    private int entJumpHeight;
    private int entFallSpeed;
    private int entType;
    private Img entImg;
    private int entAttackRange;
    private Color color;
    //Constructor
    public Entity()
    {
        color = Color.white;
    }
    public Entity(Img img)
    {
        entId++;
        entName = "blank";
        entPos = new Rectangle(0, 0, 0, 0);
        entHp = 10;
        entSpeed = 10;
        entJumpHeight = 10;
        entFallSpeed = 10;
        entType = 0;
        entImg = img;
        entAttackRange = 200;
        color = Color.white;
    }
    public Entity(int id, String name, Rectangle pos, int hp, int speed, int jump, int fall, int type, Img img)
    {
        entId=id;
        entName=name;
        entPos = pos;
        entHp = hp;
        entSpeed = speed;
        entJumpHeight = jump;
        entFallSpeed = fall;
        entType = type;
        entImg = img;
        entAttackRange = 200;
        color = Color.white;
    }
    public Entity(Entity ent)
    {
        entId = ent.getEntityId();
        entName = ent.getEntityName();
        entPos = ent.getEntityPos();
        entHp = ent.getEntityHp();
        entSpeed = ent.getEntitySpeed();
        entJumpHeight = ent.getEntityJumpHeight();
        entFallSpeed = ent.getEntityFallSpeed();
        entType = ent.getEntityType();
        entImg = ent.getEntityImg();
        entAttackRange = ent.getEntityAttackRange();
        color = Color.white;
    }
    //Id
    public int getEntityId()
    {
        return entId;
    }
    public void setEntityId(int id)
    {
        entId = id;
    }
    //Name
    public String getEntityName()
    {
        return entName;
    }
    public void setEntityName(String name)
    {
        entName = name;
    }
    //Pos
    public Rectangle getEntityPos()
    {
        return entPos;
    }
    public void setEntityPos(Rectangle pos)
    {
        entPos = pos;
    }
    //Hp
    public int getEntityHp()
    {
        return entHp;
    }
    public void setEntityHp(int hp)
    {
        entHp = hp;
    }
    //Speed
    public int getEntitySpeed()
    {
        return entSpeed;
    }
    public void setEntitySpeed(int speed)
    {
        entSpeed = speed;
    }
    //Jump Height
    public int getEntityJumpHeight()
    {
        return entJumpHeight;
    }
    public void setEntityJumpHeight(int jump)
    {
        entJumpHeight = jump;
    }
    //Fall Speed
    public int getEntityFallSpeed()
    {
        return entFallSpeed;
    }
    public void setEntityFallSpeed(int fall)
    {
        entFallSpeed = fall;
    }
    //Type
    public int getEntityType()
    {
        return entType;
    }
    public void setEntityType(int type)
    {
        entType = type;
    }
    //Texture
    public Img getEntityImg()
    {
        return entImg;
    }
    public void setEntityImg(Img img)
    {
    	entImg = img;
    }
    //Entity X
    public int getEntityX()
    {
        return (int)entPos.getX();
    }
    public void setEntityX(int x)
    {
        entPos.setX(x);
    }
    //Entity Y
    public int getEntityY()
    {
        return (int)entPos.getY();
    }
    public void setEntityY(int y)
    {
        entPos.setY(y);
    }
    //Entity Attack Range
    public int getEntityAttackRange()
    {
        return entAttackRange;
    }
    public void setEntityAttackRange(int range)
    {
        entAttackRange = range;
    }
    //Color
    public Color getEntityColor()
    {
        return color;
    }
    public void setEntityColor(Color color)
    {
        this.color = color;
    }
}
