package com.slyvr.init.levels;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;

import com.slyvr.beans.*;
import com.slyvr.tools.Tools;

public class LevelTools {
	
	public static ArrayList<Block> generateLevelBlocks(Global global, Image gridTex, Boolean removable)
    {
		System.out.println("Generating Level Blocks...");

        ArrayList<Block> blocks = new ArrayList<Block>();

        //Each Block
        for (int y = 0; y < 660; y+=30)
        {
            for (int x = 0; x < 990; x+=30)
            {
                Color colorA = gridTex.getColor(x+5, y+5);
            	
                if (colorA.getRed() == 255 && colorA.getGreen() == 201 && colorA.getBlue() == 14)
                {
                    //Portal Block
                    Block block = new Block(global.getBlockByTextureName("block_portal"));
                    block.setBlockPos(new Rectangle(x,y,30,30));
                    block.setRemovable(false);
                    blocks.add(block);
                }
                else if (colorA.getRed()== 112 && colorA.getGreen() == 146 && colorA.getBlue() == 190)
                {
                    //Respawn Block
                    Block block = new Block(global.getBlockByTextureName("block_respawn"));
                    block.setBlockPos(new Rectangle(x, y, 30, 30));
                    block.setRemovable(false);
                    blocks.add(block);
                }
                else if (colorA.getRed() == 185 && colorA.getGreen() == 122 && colorA.getBlue() == 87)
                {
                    //Dirt Block
                    Block block = new Block(global.getBlockByTextureName("block_grass"));
                    block.setBlockPos(new Rectangle(x, y, 30, 30));
                    block.setRemovable(removable);
                    blocks.add(block);
                }
                else if (colorA.getRed() == 195 && colorA.getGreen() == 195 && colorA.getBlue() == 195)
                {
                    //Stone Block
                    Block block = new Block(global.getBlockByTextureName("block_tech1"));
                    block.setBlockPos(new Rectangle(x, y, 30, 30));
                    block.setRemovable(removable);
                    blocks.add(block);
                }
                else if (colorA.getRed() == 136 && colorA.getGreen() == 0 && colorA.getBlue() == 21)
                {
                    //Tech Block
                    Block block = new Block(global.getBlockByTextureName("block_tech2"));
                    block.setBlockPos(new Rectangle(x, y, 30, 30));
                    block.setRemovable(removable);
                    blocks.add(block);
                }
                else if (colorA.getRed() == 34 && colorA.getGreen() == 177 && colorA.getBlue() == 76)
                {
                    //Grass Block
                    Block block = new Block(global.getBlockByTextureName("block_tech3"));
                    block.setBlockPos(new Rectangle(x, y, 30, 30));
                    block.setRemovable(removable);
                    blocks.add(block);
                }
            }
        }
        System.out.println("Level Blocks Generated!");

        return blocks;
    }
    public static ArrayList<Entity> generateLevelEntities(Global global, Image gridTex)
    {
    	System.out.println("Generating Level Entities");

        ArrayList<Entity> ents = new ArrayList<Entity>();

        //Each Block
        for (int y = 0; y < 660; y += 30)
        {
            for (int x = 0; x < 990; x += 30)
            {
                //Each Block's Pixels
                Color colorA = gridTex.getColor(x, y);

                if (colorA.getRed() == 127 && colorA.getGreen() == 127 && colorA.getBlue() == 127)
                {
                    Entity ent = new Entity(global.getEntityByName("ent_block_rock"));
                    ent.setEntityPos(new Rectangle(x,y,30,30));
                    ents.add(ent);
                }
                else if (colorA.getRed() == 237 && colorA.getGreen() == 28 && colorA.getBlue() == 36)
                {
                    Entity ent = new Entity(global.getEntityByName("ent_floaty"));
                    ent.setEntityPos(new Rectangle(x, y, 30, 30));
                    ents.add(ent);
                }
            }
        }
        System.out.println("Level Entities Generated!");

        return ents;
    }
}
