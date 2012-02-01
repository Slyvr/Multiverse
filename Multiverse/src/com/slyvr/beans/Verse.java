package com.slyvr.beans;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.geom.Rectangle;

import com.slyvr.tools.Tools;

public class Verse {
	private int verseId;
    private int verseType;
    private ArrayList<Block> blocks;
    private ArrayList<Entity> entities;
    private int entlimit;
    private int blocklimit;
    private Color bgColor;
    private ArrayList<Block> background;
    private String verseString;

    //Constructor
    public Verse()
    {
        verseId = 0;
        verseType = 0;
        blocks = new ArrayList<Block>();
        entities = new ArrayList<Entity>();
        blocklimit = 30;
        entlimit = 30;
        bgColor = Color.darkGray;
    }
    //Id
    public int getVerseId()
    {
        return verseId;
    }
    public void setVerseId(int id)
    {
        verseId = id;
    }
    //Type
    public int getVerseType()
    {
        return verseType;
    }
    public void setVerseType(int type)
    {
        verseType = type;
    }
    //Blocks
    public ArrayList<Block> getVerseBlocks()
    {
        return blocks;
    }
    public void setVerseBlocks(ArrayList<Block> blockList)
    {
        blocks = blockList;
    }
    //Blocks
    public Block getVerseBlockRecentlyPlaced()
    {
        return blocks.get(blocks.size());
    }
    //Entities
    public ArrayList<Entity> getVerseEntities()
    {
        return entities;
    }
    public void setVerseEntities(ArrayList<Entity> entityList)
    {
        entities = entityList;
    }
    //Add Single Entity
    public void addVerseEntity(Entity entity)
    {
        entities.add(entity);
    }
    //Remove Single Entity
    public void removeVerseEntity(Entity entity)
    {
        if (entities.size() > 1)
        {
            entities.remove(entity);
        }
    }
    //Add Single Block
    public void addVerseBlock(Block block)
    {
        blocks.add(block);
    }
    //Remove Single Block
    public void removeVerseBlock(Block block)
    {
        blocks.remove(block);
    }
    //Remove Single Block By Position
    public void removeVerseBlockByPosition(Rectangle pos)
    {
    	
    	for (int i=0; i<blocks.size(); i++)
        {
    		if (blocks.get(i).getBlockPos().intersects(pos)){
	            Color[] blockTextureData = new Color[blocks.get(i).getBlockImg().getImage().getWidth() * blocks.get(i).getBlockImg().getImage().getHeight()];
	            Color[] positionTextureData = new Color[blockTextureData.length];
	            positionTextureData[0] = new Color(1, 1, 1);
	            Color[] imgColor = Tools.getColorData(blocks.get(i).getBlockImg().getImage());
				Boolean collision = Tools.IntersectPixels(pos, positionTextureData, blocks.get(i).getBlockPos(), imgColor);
	
	            if (collision)
	            {
	                removeVerseBlock(blocks.get(i));
	                break;
	            }
    		}
        }
    }
    //Entity Limit
    public int getVerseEntityLimit()
    {
        return entlimit;
    }
    public void setVerseEntityLimit(int limit)
    {
        entlimit = limit;
    }
    //Block Limit
    public int getVerseBlockLimit()
    {
        return blocklimit;
    }
    public void setVerseBlockLimit(int limit)
    {
        blocklimit = limit;
    }
    //Bg Color
    public Color getVerseBgColor()
    {
        return bgColor;
    }
    public void setVerseBgColor(Color color)
    {
        bgColor = color;
    }
    //Bg Color
    public ArrayList<Block> getVerseBackground()
    {
        return background;
    }
    public void setVerseBackground(ArrayList<Block> blocks)
    {
        background = blocks;
    }
    //Verse String
    public String getVerseString()
    {
        return verseString;
    }
    public void setVerseString(String str)
    {
        verseString = str;
    }
}
