package com.slyvr.update;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Rectangle;

import com.slyvr.beans.*;
import com.slyvr.tools.Tools;

public class UpdateBlocks {

	static Input prevInput;
	static Boolean buttonPressed;
	static int blockPlaceDistance = 150;
	static Block returnBlock;
	static Entity returnEnt;
	static int blockFallSpeed=5;
	static long prevMilli;
	
	public static void updateBlockPlacement(Global global, GameContainer container){
		Input input = container.getInput();
		if (prevInput==null)prevInput=input;
		if (buttonPressed==null)buttonPressed=false;
		
		/*
		 * Add new Block by
		 * -creating red block_highlight on ButtonDown
		 * -if in valid location, remove block_highlight, create new Block in mousePosition
		 */
		if (input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) && !input.isMouseButtonDown(Input.MOUSE_RIGHT_BUTTON)){
			//Display red outline box
            if (global.getCurrent().getCurrentVerse().getVerseBlockById(0) == null)
            {
                //If no red outline exists yet, create it.
                Img blankblock = global.getImageByName("block_highlight");
                Rectangle pos = new Rectangle(input.getMouseX(), input.getMouseY(), blankblock.getImage().getWidth(), blankblock.getImage().getHeight());
                Block blank = new Block();
                blank.setBlockId(0);
                blank.setBlockType(1);
                blank.setBlockPos(pos);
                blank.setBlockImg(blankblock);
                global.getCurrent().getCurrentVerse().addVerseBlock(blank);
                buttonPressed = true;
            }
            else
            {
                //If red outline already exists, change its position
                Block blank = global.getCurrent().getCurrentVerse().getVerseBlockById(0);
                Rectangle pos = blank.getBlockPos();
                pos.setX(input.getMouseX());
                pos.setY(input.getMouseY());
                blank.setBlockPos(pos);
            }
		}
		//Remove and block_highlight, and place new block
		else if (!input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)){
            //remove red outline
            Block blank = global.getCurrent().getCurrentVerse().getVerseBlockById(0);
            global.getCurrent().getCurrentVerse().removeVerseBlock(blank);
            //place a new block
            if (buttonPressed)
            {
                Boolean returnval = processAllCollisions(input, global);
                if (!returnval)
                {
                	if (Tools.getDistance(new Rectangle(input.getMouseX(),input.getMouseY(),5,5), global.getCurrent().getCurrentPlayer(global.getCurrent()).getEntityPos())<blockPlaceDistance){
	                    if (global.getCurrent().getCurrentBlockType()!=null){
	                		Block block = new Block(global.getCurrent().getCurrentBlockType());
		                    Rectangle pos = new Rectangle(input.getMouseX(), input.getMouseY(), block.getBlockImg().getImage().getWidth(), block.getBlockImg().getImage().getHeight());
		                    block.setBlockPos(pos);
		                    block.setAffectBlockTotal(true);
		                    block.setPlayerPlaced(true);
		                    global.getCurrent().getCurrentVerse().addVerseBlock(block);
		                    global.getCurrent().setCurrentBlockType(null);
	                    }
                	}
                }
                buttonPressed = false;
            }
        }
		
		/*
		 * Remove a block by right clicking
		 */
		if (input.isMouseButtonDown(Input.MOUSE_RIGHT_BUTTON)){
			Rectangle mousePos = new Rectangle(input.getMouseX(), input.getMouseY(),5,5);
			for (int i=0; i<global.getCurrent().getCurrentVerse().getVerseBlocks().size(); i++){
				Block block = global.getCurrent().getCurrentVerse().getVerseBlocks().get(i);
				if (block.getBlockPos().intersects(mousePos)){
					if (Tools.getDistance(block.getBlockPos(), global.getCurrent().getCurrentPlayer(global.getCurrent()).getEntityPos())<blockPlaceDistance){
						if (block.getBlockImg().getName().contains("wood")){
							global.getCurrent().setCurrentBlockType(block);
							global.getCurrent().getCurrentVerse().getVerseBlocks().remove(block);
							break;
						}
					}
				}
			}
		}
		
		prevInput=input;
	}
	
	public static void updateWoodBlocks(Global global){
		
		if (prevMilli<=0) prevMilli = System.currentTimeMillis();
		
		if ((System.currentTimeMillis()-prevMilli)>=blockFallSpeed){
			ArrayList<Block> blocks = global.getCurrent().getCurrentVerse().getVerseBlocks();
			for (int i=0; i<blocks.size(); i++){
				Block block = blocks.get(i);
				if (block.getBlockImg().getName().contains("wood")){
					//Block Falling
					int x = (int) block.getBlockPos().getX();
					int y = (int) block.getBlockPos().getY();
					block.setBlockY(y += 1);
					
					//Check Block Collision
					if (processBlockCollisions(block, global)){
						//Halt falling if collision detected
						if (!returnBlock.getBlockImg().getName().contains("btn")) block.setBlockY(y -= 1);
						//Check if wood block landed on button
						else if (returnBlock.getBlockY()-10==block.getBlockY()){
							block.setBlockY(y -= 1);
						}
					}
				}
			}
			prevMilli = System.currentTimeMillis();
		}
	}
	
	public static Boolean processAllCollisions(Input input, Global global)
    {
		ArrayList<Block> blocks = global.getCurrent().getCurrentVerse().getVerseBlocks();
		ArrayList<Entity> ents = new ArrayList<Entity>();
		ents.addAll(global.getCurrent().getCurrentVerse().getVerseEntities());
		ents.add(global.getCurrent().getCurrentPlayer(global.getCurrent()));
		Rectangle mousePos = new Rectangle(input.getMouseX(),input.getMouseY(),30,30);
		
        for (int i=0; i<blocks.size(); i++){
        	Block block = blocks.get(i);
            if (block.getBlockPos().intersects(mousePos)){
            	if (!block.getBlockImg().getName().contains("respawn")){
	            	return true;
            	}
            }
        }
        for (int i=0; i<ents.size(); i++){
        	Entity ent = ents.get(i);
            if (ent.getEntityPos().intersects(mousePos)){
                return true;
            }
        }
        return false;
    }
	public static Boolean processBlockCollisions(Block block, Global global)
    {
		ArrayList<Block> blocks = global.getCurrent().getCurrentVerse().getVerseBlocks();

        for (int i=0; i<blocks.size(); i++){
        	Block block2 = blocks.get(i);
        	if (block2!=block){
	            if (block2.getBlockPos().intersects(block.getBlockPos())){
	            	returnBlock = block2;
		            return true;
	            }
        	}
        }
        return false;
    }
	public static Boolean processEntCollisions(Block block, Global global)
    {
		ArrayList<Entity> ents = new ArrayList<Entity>();
		ents.addAll(global.getCurrent().getCurrentVerse().getVerseEntities());
		ents.add(global.getCurrent().getCurrentPlayer(global.getCurrent()));

        for (int i=0; i<ents.size(); i++){
        	Entity ent = ents.get(i);
            if (ent.getEntityPos().intersects(block.getBlockPos())){
            	returnEnt = ent;
	            return true;
            }
        }
        return false;
    }
}
