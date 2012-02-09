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
			Boolean highlightOn=false;
			for(int i=0; i<global.getCurrent().getCurrentVerse().getVerseBlocks().size(); i++){
				Block block = global.getCurrent().getCurrentVerse().getVerseBlocks().get(i);
				if (block.getBlockImg().getName().equals("block_highlight")){
					highlightOn = true;
					break;
				}
			}
            if (!highlightOn)
            {
                //If no red outline exists yet, create it.
                Img blankblock = global.getImageByName("block_highlight");
                Rectangle pos = new Rectangle(input.getMouseX(), input.getMouseY(), blankblock.getImage().getWidth(), blankblock.getImage().getHeight());
                Block blank = new Block();
                blank.setBlockPos(pos);
                blank.setBlockImg(blankblock);
                global.getCurrent().getCurrentVerse().addVerseBlock(blank);
                buttonPressed = true;
            }
            else
            {
                //If red outline already exists, change its position
            	Block blank = null;
            	for(int i=0; i<global.getCurrent().getCurrentVerse().getVerseBlocks().size(); i++){
    				Block block = global.getCurrent().getCurrentVerse().getVerseBlocks().get(i);
    				if (block.getBlockImg().getName().equals("block_highlight")){
    					blank = block;
    					break;
    				}
    			}
                Rectangle pos = blank.getBlockPos();
                pos.setX(input.getMouseX());
                pos.setY(input.getMouseY());
                blank.setBlockPos(pos);
            }
		}
		//Remove and block_highlight, and place new block
		else if (!input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)){
            //remove red outline
            Block blank = null;
        	for(int i=0; i<global.getCurrent().getCurrentVerse().getVerseBlocks().size(); i++){
				Block block = global.getCurrent().getCurrentVerse().getVerseBlocks().get(i);
				if (block.getBlockImg().getName().equals("block_highlight")){
					blank = block;
					break;
				}
			}
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
		                    global.getMenuByName("game").getMenuItemByName("invblock").setImg(null);
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
							global.getMenuByName("game").getMenuItemByName("invblock").setImg(block.getBlockImg());
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
					int y = (int) block.getBlockPos().getY();
					block.setBlockY(y += 1);
					
					//Check Block Collision
					Boolean blockCollision=false;
					if (processBlockCollisions(block, global)){
						blockCollision=true;
						//Halt falling if collision detected
						if (!returnBlock.getBlockImg().getName().contains("btn") && !returnBlock.getBlockImg().getName().contains("respawn")){
							block.setBlockY(y -= 1);
						}
						//Check if wood block landed on button or respawn
						else if (returnBlock.getBlockImg().getName().contains("btn") && returnBlock.getBlockY()-12<block.getBlockY()){
							block.setBlockY(y -= 1);
						}
						else if (returnBlock.getBlockImg().getName().contains("respawn") && returnBlock.getBlockY()-8<block.getBlockY()){
							block.setBlockY(y -= 1);
						}
					}
					//Check Entity Collision
					if (processEntCollisions(block, global)){
						if (blockCollision){
							//Check for wall collision
							Rectangle pos = new Rectangle(block.getBlockX(),block.getBlockY()-5,30,30);
							if (!returnBlock.getBlockImg().getName().contains("respawn")){
								if (!pos.intersects(returnBlock.getBlockPos())){
									moveWoodBlock(global, block);
								}
							}
							else{
								Rectangle pos1 = pos;
								pos1.setX(pos.getX()+30);
								Rectangle pos2 = pos;
								pos2.setX(pos.getX()-30);
								if (pos1.intersects(returnBlock.getBlockPos()) && returnBlock.getBlockPos().intersects(returnEnt.getEntityPos())){
									moveWoodBlock(global, block);
								}
								else if (pos2.intersects(returnBlock.getBlockPos()) && returnBlock.getBlockPos().intersects(returnEnt.getEntityPos())){
									moveWoodBlock(global, block);
								}
							}
						}
					}
				}
			}
			prevMilli = System.currentTimeMillis();
		}
	}
	public static void moveWoodBlock(Global global, Block block){
		if (returnEnt.getEntityImg().getName().contains("player")){
			//Determine if player is not standing on it
			if (returnEnt.getEntityY()>block.getBlockY()){
				//Determine if moving left or right
				if (returnEnt.getEntityX() - block.getBlockX() > 0){
					block.setBlockX(block.getBlockX() - 1);
				}
				else if (returnEnt.getEntityX() - block.getBlockX() < 0){
					block.setBlockX(block.getBlockX() + 1);
				}
			}
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
        		//Allow placement just above btn block
        		if (block.getBlockImg().getName().contains("btn")){
        			if (mousePos.getY() >= block.getBlockY()-10){
        				return true;
        			}
        		}
        		else if (block.getBlockImg().getName().contains("respawn")){
        			if (mousePos.getY() >= block.getBlockY()-10){
        				return true;
        			}
        		}
        		else{
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
