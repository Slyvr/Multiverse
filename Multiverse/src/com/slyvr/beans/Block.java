package com.slyvr.beans;

import org.newdawn.slick.Color;
import org.newdawn.slick.geom.Rectangle;

public class Block {
    private Rectangle blockPos;
    private Img blockImg;
    private Boolean removable;
    private Boolean affectBlockTotal;
    private Boolean playerPlaced;
    private Color color;

    //Constructor
    public Block()
    {
        blockPos = new Rectangle(0, 0, 0, 0);
        blockImg = null;
        removable = true;
        affectBlockTotal = true;
        playerPlaced = false;
        color = Color.white;
    }
    public Block(Block block)
    {
        this.blockPos = block.getBlockPos();
        this.blockImg = block.getBlockImg();
        this.removable = block.getRemovable();
        this.affectBlockTotal = block.getAffectBlockTotal();
        this.playerPlaced = block.getPlayerPlaced();
        this.color = block.getColor();
    }
    public Block(Rectangle pos, Img img, Boolean rem)
    {
        blockPos = pos;
        blockImg = img;
        removable = rem;
        affectBlockTotal = false;
        color = Color.white;
    }
    //Pos
    public Rectangle getBlockPos()
    {
        return blockPos;
    }
    public void setBlockPos(Rectangle pos)
    {
        blockPos = pos;
    }
  //X
    public int getBlockX()
    {
        return (int)blockPos.getX();
    }
    public void setBlockX(int x)
    {
        blockPos.setX(x);
    }
    //Y
    public int getBlockY()
    {
        return (int)blockPos.getY();
    }
    public void setBlockY(int y)
    {
        blockPos.setY(y);
    }
    //Texture
    public Img getBlockImg()
    {
        return blockImg;
    }
    public void setBlockImg(Img img)
    {
        blockImg = img;
    }
    //Removable
    public Boolean getRemovable()
    {
        return removable;
    }
    public void setRemovable(Boolean b)
    {
        removable = b;
    }
    //Affects Block Total
    public Boolean getAffectBlockTotal()
    {
        return affectBlockTotal;
    }
    public void setAffectBlockTotal(Boolean b)
    {
        affectBlockTotal = b;
    }
    //Player Placed
    public Boolean getPlayerPlaced()
    {
        return playerPlaced;
    }
    public void setPlayerPlaced(Boolean b)
    {
        playerPlaced = b;
    }
    //Color
    public Color getColor()
    {
        return color;
    }
    public void setColor(Color color)
    {
        this.color = color;
    }
}
