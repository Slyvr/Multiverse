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
	static EffectBlock returnEffectBlock;
	static Entity returnEnt;
	static int blockFallSpeed=5;
	static long prevMilli;
	static long prevMilli2;
	static long counter;
	
	public static void updateBlockPlacement(Global global, GameContainer container){
		Input input = container.getInput();
		if (prevInput==null)prevInput=input;
		if (buttonPressed==null)buttonPressed=false;
		
		
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
                Rectangle pos = new Rectangle(input.getMouseX()-15, input.getMouseY()-15, blankblock.getImage().getWidth(), blankblock.getImage().getHeight());
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
                pos.setX(input.getMouseX()-15);
                pos.setY(input.getMouseY()-15);
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
                	if (Tools.getDistance(new Rectangle(input.getMouseX()-15,input.getMouseY()-15,5,5), global.getCurrent().getCurrentPlayer(global.getCurrent()).getEntityPos())<blockPlaceDistance){
	                    if (global.getCurrent().getCurrentBlockType()!=null){
	                		Block block = new Block(global.getCurrent().getCurrentBlockType());
		                    Rectangle pos = new Rectangle(input.getMouseX()-15, input.getMouseY()-15, block.getBlockImg().getImage().getWidth(), block.getBlockImg().getImage().getHeight());
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
							//check for downed btns
							for (Block btnBlock : global.getCurrent().getCurrentVerse().getVerseBlocks()){
								if (btnBlock.getBlockImg().getName().contains("block_btnDown")) 
									btnBlock.setBlockImg(global.getImageByName("block_btnUp"));
							}
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
					if (processBlockCollisions(block, global)){
						//Halt falling if collision detected
						if (!returnBlock.getBlockImg().getName().contains("btn") && !returnBlock.getBlockImg().getName().contains("respawn")){
							block.setBlockY(y -= 1);
						}
						//Check if wood block landed on button or respawn
						else if (returnBlock.getBlockImg().getName().contains("btn") && returnBlock.getBlockY()-8<block.getBlockY()){
							block.setBlockY(y -= 1);
							UpdateButtonBlock.updateButtonBlock(global, returnEffectBlock);
						}
						else if (returnBlock.getBlockImg().getName().contains("respawn") && returnBlock.getBlockY()-1<block.getBlockY()){
							block.setBlockY(y -= 1);
						}
						else if (returnBlock.getBlockImg().getName().contains("block_btnDown")){
							returnBlock.setBlockImg(global.getImageByName("block_btnUp"));
						}
					}
					else{
						for (Block btnBlock : blocks){
							if (btnBlock.getBlockImg().getName().contains("block_btnDown")) 
								btnBlock.setBlockImg(global.getImageByName("block_btnUp"));
						}
					}
				}
			}
			prevMilli = System.currentTimeMillis();
		}
	}
	public static void updateDoorBlocks(Global global){
		
		if (prevMilli2<=0) prevMilli2 = System.currentTimeMillis();
		
		if ((System.currentTimeMillis()-prevMilli2)>=blockFallSpeed){
			ArrayList<Block> blocks = global.getCurrent().getCurrentVerse().getVerseBlocks();
			for (int i=0; i<blocks.size(); i++){
				Block block = blocks.get(i);
				if (block.getBlockImg().getName().equals("block_door")){
					//Block Falling
					int y = (int) block.getBlockPos().getY();
					block.setBlockY(y += 1);
					
					//Check Block Collision
					if (processBlockCollisions(block, global)){
						//Halt falling if collision detected
						if (!returnBlock.getBlockImg().getName().equals("block_doorway")){
							block.setBlockY(y -= 1);
						}
						
						//Add this if door needs to go upward out of a doorway...still bugged
//						if (returnBlock.getBlockImg().getName().equals("block_doorway")){
//							Rectangle up = new Rectangle(returnBlock.getBlockX(),returnBlock.getBlockY()-30,30,30);
//							Rectangle down = new Rectangle(returnBlock.getBlockX(),returnBlock.getBlockY()+30,30,30);
//							if (processBlockPosCollision(up,global)){
//								block.setBlockY(y -= 1);
//							}
//							else if (processBlockPosCollision(down,global)){
//								block.setBlockY(y += 1);
//							}
//						}
					}
				}
			}
			prevMilli2 = System.currentTimeMillis();
		}
	}
	public static void moveWoodBlock(Global global, Block block, Rectangle position){
		//Determine if standing on top of block
		if (global.getCurrent().getCurrentPlayer(global.getCurrent()).getEntityY()-3>=block.getBlockY()){
			//Determine if moving left or right
			if (position.getX() - block.getBlockX() > 0){
				block.setBlockX(block.getBlockX() - 1);
				//Check block collisions left
				Rectangle pos = new Rectangle(block.getBlockX(),block.getBlockY()-15,0,0);
				if (processBlockPosCollision(pos, global)){
					block.setBlockX(block.getBlockX() + 1);
				}
			}
			else if (position.getX() - block.getBlockX() < 0){
				block.setBlockX(block.getBlockX() + 1);
				//Check block collisions right
				Rectangle pos = new Rectangle(block.getBlockX()+30,block.getBlockY()-15,0,0);
				if (processBlockPosCollision(pos, global)){
					block.setBlockX(block.getBlockX() - 1);
				}
			}
		}
	}
	
	public static Boolean processAllCollisions(Input input, Global global)
    {
		ArrayList<Block> blocks = new ArrayList<Block>();
		if (global.getCurrent().getCurrentVerse().getVerseEffectBlocks()!=null)
			blocks.addAll(global.getCurrent().getCurrentVerse().getVerseEffectBlocks());
		blocks.addAll(global.getCurrent().getCurrentVerse().getVerseBlocks());
		
		ArrayList<Entity> ents = new ArrayList<Entity>();
		ents.addAll(global.getCurrent().getCurrentVerse().getVerseEntities());
		ents.add(global.getCurrent().getCurrentPlayer(global.getCurrent()));
		Rectangle mousePos = new Rectangle(input.getMouseX()-15,input.getMouseY()-15,30,30);
		
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
	public static Boolean processBlockPosCollision(Rectangle rect, Global global){
		ArrayList<Block> blocks = new ArrayList<Block>();
		if (global.getCurrent().getCurrentVerse().getVerseEffectBlocks()!=null)
			blocks.addAll(global.getCurrent().getCurrentVerse().getVerseEffectBlocks());
		blocks.addAll(global.getCurrent().getCurrentVerse().getVerseBlocks());
		
        for (int i=0; i<blocks.size(); i++){
        	Block block = blocks.get(i);
        	if (block.getBlockPos().intersects(rect)) {
        		returnBlock = block;
        		return true;
        	}
        }
        return false;
	}
	public static Boolean processBlockCollisions(Block block, Global global)
    {
		ArrayList<Block> blocks = new ArrayList<Block>();
		if (global.getCurrent().getCurrentVerse().getVerseEffectBlocks()!=null)
			blocks.addAll(global.getCurrent().getCurrentVerse().getVerseEffectBlocks());
		blocks.addAll(global.getCurrent().getCurrentVerse().getVerseBlocks());
		
        for (int i=0; i<blocks.size(); i++){
        	Block block2 = blocks.get(i);
        	if (block2!=block){
	            if (block2.getBlockPos().intersects(block.getBlockPos())){
	            	if (block2.getBlockImg().getName().contains("block_btn")){
	            		returnEffectBlock = (EffectBlock) block2;
	            	}
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
