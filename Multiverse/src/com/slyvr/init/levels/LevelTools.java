package com.slyvr.init.levels;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;

import com.slyvr.beans.*;

public class LevelTools {
	
	public static ArrayList<Block> generateLevelBlocks(Global global, Image gridTex, Boolean removable){
		System.out.println("Generating Level Blocks...");

        ArrayList<Block> blocks = new ArrayList<Block>();

        //Each Block
        for (int y = 30; y < 660; y+=30){
            for (int x = 0; x < 990; x+=30){
                Color colorA = gridTex.getColor(x+5, y+5);
            	
                if (colorA.getRed() == 255 && colorA.getGreen() == 201 && colorA.getBlue() == 14){
                    Block block = new Block(global.getBlockByTextureName("block_portal"));
                    block.setBlockPos(new Rectangle(x,y,30,30));
                    block.setRemovable(false);
                    blocks.add(block);
                }
                else if (colorA.getRed()== 112 && colorA.getGreen() == 146 && colorA.getBlue() == 190){
                    Block block = new Block(global.getBlockByTextureName("block_respawn"));
                    block.setBlockPos(new Rectangle(x, y, 30, 30));
                    block.setRemovable(false);
                    blocks.add(block);
                }
                else if (colorA.getRed() == 185 && colorA.getGreen() == 122 && colorA.getBlue() == 87){
                    Block block = new Block(global.getBlockByTextureName("block_wood"));
                    block.setBlockPos(new Rectangle(x, y, 30, 30));
                    block.setRemovable(removable);
                    blocks.add(block);
                }
                else if (colorA.getRed() == 255 && colorA.getGreen() == 174 && colorA.getBlue() == 201){
                    Block block = new Block(global.getBlockByTextureName("block_btnUp"));
                    block.setBlockPos(new Rectangle(x, y, 30, 30));
                    block.setRemovable(removable);
                    blocks.add(block);
                }
                else if (colorA.getRed() == 153 && colorA.getGreen() == 217 && colorA.getBlue() == 234){
                    Block block = new Block(global.getBlockByTextureName("block_door"));
                    block.setBlockPos(new Rectangle(x, y, 30, 30));
                    block.setRemovable(removable);
                    blocks.add(block);
                }
                else if (colorA.getRed() == 195 && colorA.getGreen() == 195 && colorA.getBlue() == 195){
                    Block block = new Block(global.getBlockByTextureName("block_tech1"));
                    block.setBlockPos(new Rectangle(x, y, 30, 30));
                    block.setRemovable(removable);
                    blocks.add(block);
                }
                else if (colorA.getRed() == 127 && colorA.getGreen() == 127 && colorA.getBlue() == 127){
                    Block block = new Block(global.getBlockByTextureName("block_tech2"));
                    block.setBlockPos(new Rectangle(x, y, 30, 30));
                    block.setRemovable(removable);
                    blocks.add(block);
                }
                else if (colorA.getRed() == 54 && colorA.getGreen() == 54 && colorA.getBlue() == 54){
                    Block block = new Block(global.getBlockByTextureName("block_tech3"));
                    block.setBlockPos(new Rectangle(x, y, 30, 30));
                    block.setRemovable(removable);
                    blocks.add(block);
                }
                else if (colorA.getRed() == 216 && colorA.getGreen() == 216 && colorA.getBlue() == 216){
                    Block block = new Block(global.getBlockByTextureName("block_doorway"));
                    block.setBlockPos(new Rectangle(x, y, 30, 30));
                    block.setRemovable(removable);
                    blocks.add(block);
                }
            }
        }
        System.out.println("Level Blocks Generated!");

        return blocks;
    }
    public static ArrayList<Entity> generateLevelEntities(Global global, Image gridTex){
    	System.out.println("Generating Level Entities");

        ArrayList<Entity> ents = new ArrayList<Entity>();

        //Each Block
        for (int y = 0; y < 660; y += 30){
            for (int x = 0; x < 990; x += 30){
                //Each Block's Pixels
                Color colorA = gridTex.getColor(x+5, y+5);
                if (colorA.getRed() == 136 && colorA.getGreen() == 0 && colorA.getBlue() == 21){
                    Entity ent = new Entity(global.getEntityByName("ent_block_rock"));
                    ent.setEntityPos(new Rectangle(x,y,30,30));
                    ents.add(ent);
                }
                else if (colorA.getRed() == 237 && colorA.getGreen() == 28 && colorA.getBlue() == 36){
                    Entity ent = new Entity(global.getEntityByName("ent_floaty"));
                    ent.setEntityPos(new Rectangle(x, y, 30, 30));
                    ents.add(ent);
                }
            }
        }
        System.out.println("Level Entities Generated!");

        return ents;
    }
    
    public static Level getNextLevel(Global global){
    	int index=0;
		for (index=0; index<global.getLevels().size(); index++){
			Level level = global.getLevels().get(index);
			if (level.equals(global.getCurrent().getCurrentLevel())){
				break;
			}
		}
		Level level = null;
		if (index==0) level = InitLevel1.loadLevel1(global);
		else if (index==1) level = InitLevel2.loadLevel2(global);
		else if (index==2) level = InitLevel1.loadLevel1(global);
		else if (index==3) level = InitLevel1.loadLevel1(global);
		
		global.getLevels().add(level);
		return level;
    }
}
