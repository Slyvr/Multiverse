package com.slyvr.update;

import java.util.ArrayList;
import java.util.Random;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Rectangle;

import com.slyvr.beans.*;
import com.slyvr.init.levels.LevelTools;
import com.slyvr.tools.Tools;

public class UpdatePlayer {

	static Input prevInput;
	static Boolean jumping;
	static Boolean onGround;
	static Boolean portaling;
	static int maxY;
	static Block returnBlock;
	static Entity returnEnt;
	static long prevMilli;
	
	public static void updateMovement(GameContainer container, Global global){
		Input input = container.getInput();
		Entity player = global.getCurrent().getCurrentPlayer(global.getCurrent());
		
		ArrayList<Block> blockList = new ArrayList<Block>();
		if (global.getCurrent().getCurrentVerse().getVerseEffectBlocks()!=null)
			blockList.addAll(global.getCurrent().getCurrentVerse().getVerseEffectBlocks());
        blockList.addAll(global.getCurrent().getCurrentVerse().getVerseBlocks());
        blockList.addAll(global.getCurrent().getCurrentLevel().getLevelBlocks());
		
        if (prevMilli<=0) prevMilli = System.currentTimeMillis();
		int x = player.getEntityX();
        int y = player.getEntityY();
        if (onGround==null) onGround=true;
        if (jumping==null) jumping=false;
        if (prevInput == null) prevInput=container.getInput();
        if (portaling == null) portaling=false;
		
        //Player movement occurs every 5 milliseconds
        if ((System.currentTimeMillis()-prevMilli)>=player.getEntitySpeed() && !portaling){
        	//Move Left
			if (input.isKeyDown(global.getOptions().getLeft())){
				player.setEntityX(x -= 1);
	            if (jumping) player.setEntityImg(global.getImageByName("ent_player2-1"));
	            else player.setEntityImg(global.getImageByName("ent_player1-1"));
	            if (processBlockCollisions(global, player, blockList)||player.getEntityX()<0){
	            	player.setEntityX(x += 1);
	            	if (returnBlock.getBlockImg().getName().contains("wood")){
	            		UpdateBlocks.moveWoodBlock(global, returnBlock, player.getEntityPos());
	            	}
	            }
	            else if (processEntityCollisions(player, global.getCurrent().getCurrentVerse().getVerseEntities())){
	                player.setEntityX(x += 1);
	            }
			}
			//Move Right
			else if (input.isKeyDown(global.getOptions().getRight())){
				player.setEntityX(x += 1);
	            if (jumping) player.setEntityImg(global.getImageByName("ent_player2"));
	            else player.setEntityImg(global.getImageByName("ent_player1"));
	            if (processBlockCollisions(global, player, blockList)||player.getEntityX()>=960){
	            	player.setEntityX(x -= 1);
	            	if (returnBlock.getBlockImg().getName().contains("wood")){
	            		UpdateBlocks.moveWoodBlock(global, returnBlock, player.getEntityPos());
	            	}
	            }
	            else if (processEntityCollisions(player, global.getCurrent().getCurrentVerse().getVerseEntities())){
	                player.setEntityX(x -= 1);
	            }
			}
			//Jump
			if (input.isKeyDown(global.getOptions().getJump()) && onGround){
                maxY = (int) (player.getEntityPos().getY() - player.getEntityJumpHeight());
                jumping = true;
            }
            if (input.isKeyDown(global.getOptions().getJump()) && jumping){
                if (player.getEntityImg().equals(global.getImageByName("ent_player1"))) player.setEntityImg(global.getImageByName("ent_player2"));
                else if (player.getEntityImg().equals(global.getImageByName("ent_player1-1"))) player.setEntityImg(global.getImageByName("ent_player2-1"));
            }
            else{
                if (player.getEntityImg().equals(global.getImageByName("ent_player2"))) player.setEntityImg(global.getImageByName("ent_player1"));
                else if (player.getEntityImg().equals(global.getImageByName("ent_player2-1"))) player.setEntityImg(global.getImageByName("ent_player1-1"));
            }
            if (jumping){
                if (maxY < player.getEntityY() && prevInput.isKeyDown(global.getOptions().getJump())) jumping = true;
                else jumping = false;
                if (jumping){
                    player.setEntityY(y -= player.getEntityFallSpeed() * 2);
                    if (processBlockCollisions(global, player, blockList)||player.getEntityY()<30){
                        player.setEntityY(y += player.getEntityFallSpeed() * 2);
                        jumping = false;
                        onGround = false;
                    }
                    else if (processEntityCollisions(player, global.getCurrent().getCurrentVerse().getVerseEntities())){
                        player.setEntityY(y += player.getEntityFallSpeed() * 2);
                        jumping = false;
                        onGround = false;
                    }
                }
            }
			
            //process falling
            int fallSpeed = 1;
            player.setEntityY(y += fallSpeed);
            if (processBlockCollisions(global, player, blockList)){
            	if (!returnBlock.getBlockImg().getName().equals("block_highlight") && !returnBlock.getBlockImg().getName().equals("block_respawn")){
	                player.setEntityY(y -= fallSpeed);
	                onGround = true;
	                jumping = false;
            	}
            }
            else if (processEntityCollisions(player, global.getCurrent().getCurrentVerse().getVerseEntities())){
                player.setEntityY(y -= fallSpeed);
                onGround = true;
                jumping = false;
            }
            else onGround = false;
            //fall to death
            if (player.getEntityPos().getY() > 680){
                respawnPlayer(global);
            }
			
			prevMilli = System.currentTimeMillis();
        }
        else if (portaling){
        	processPortalCollision(global);
        }
	}
	public static void processPortalCollision(Global global){
		//Block black hole animation
		ArrayList<Block> blocks = new ArrayList<Block>();
		if (global.getCurrent().getCurrentVerse().getVerseEffectBlocks()!=null)
			blocks.addAll(global.getCurrent().getCurrentVerse().getVerseEffectBlocks());
		blocks.addAll(global.getCurrent().getCurrentVerse().getVerseBlocks());
		blocks.addAll(global.getCurrent().getCurrentVerse().getVerseBackground());
		for (Block block : blocks){
			//If it's not the portal
			if (!block.getBlockImg().getName().contains("portal")){
				Block portal = global.getCurrent().getCurrentPortal(global.getCurrent());
				Random rand = new Random();
				//Determine the X and Y direction to move in and move about randomly
				int blockSpeed = 5;
				if (rand.nextInt(10)<blockSpeed){
					if (block.getBlockX() - portal.getBlockX() < 0){
						block.setBlockX(block.getBlockX() + rand.nextInt(blockSpeed));
					}
					else if (block.getBlockX() - portal.getBlockX() > 0){
						block.setBlockX(block.getBlockX() - rand.nextInt(blockSpeed));
					}
					else {
						block.setBlockPos(portal.getBlockPos());
					}
				}
				if (rand.nextInt(10)<blockSpeed){
					if (block.getBlockY() - portal.getBlockY() < 0){
						block.setBlockY(block.getBlockY() + rand.nextInt(blockSpeed));
					}
					else if (block.getBlockY() - portal.getBlockY() > 0){
						block.setBlockY(block.getBlockY() - rand.nextInt(blockSpeed));
					}
					else {
						block.setBlockPos(portal.getBlockPos());
					}
				}
				
				//if collision with portal occurs, delete block
				if (block.getBlockPos().intersects(portal.getBlockPos()) | block.getBlockPos().equals(portal.getBlockPos())){
					global.getCurrent().getCurrentVerse().getVerseBlocks().remove(block);
				}
				//if no more blocks exist, end this animation and continue to next level
				if (global.getCurrent().getCurrentVerse().getVerseBlocks().size() <= 3){
					portaling=false;
					//tele to next level
					Level level= LevelTools.getNextLevel(global);
					global.getCurrent().setCurrentLevel(level);
					global.getCurrent().setCurrentVerse(level.getLevelVerses().get(0));
					global.getMenuByName("game").getMenuItemByName("leveltext").setText("Level "+level.getLevelId());
					respawnPlayer(global);
				}
			}
		}
	}
	
	public static void respawnPlayer(Global global){
		//Get position of respawn block...if none exists spawn at 0,0.
		//Create respawn animation?
		Entity player = global.getCurrent().getCurrentPlayer(global.getCurrent());
		if (global.getCurrent().getCurrentRespawn(global.getCurrent())!=null){
			Rectangle respawnPos = global.getCurrent().getCurrentRespawn(global.getCurrent()).getBlockPos();
			Rectangle pos = new Rectangle(respawnPos.getX(), respawnPos.getY()-10,player.getEntityImg().getImage().getWidth(),player.getEntityImg().getImage().getHeight());
			player.setEntityPos(pos);
		}
		else{
			player.setEntityPos(new Rectangle(0,0,player.getEntityImg().getImage().getWidth(),player.getEntityImg().getImage().getHeight()));
		}
	}
	
	public static Boolean processBlockCollisions(Global global, Entity player, ArrayList<Block> blocks)
    {
        for (int i=0; i<blocks.size(); i++){
        	Block block = blocks.get(i);
        	Rectangle pos = new Rectangle(player.getEntityPos().getX(),player.getEntityPos().getY(),player.getEntityPos().getWidth(),player.getEntityPos().getHeight());
            if (block.getBlockPos().intersects(player.getEntityPos())){
            	if (block.getBlockImg().getName().contains("portal")){
            		portaling=true;
            		processPortalCollision(global);
            	}
            	if (!block.getBlockImg().getName().contains("respawn") && !block.getBlockImg().getName().contains("portal")){
            		
        			returnBlock = block;
	            	return true;
            		
            		//process per pixel collision
//            		Rectangle rectangleA = player.getEntityPos();
//            		Rectangle rectangleB = block.getBlockPos();
//            		Color[][] dataA = Tools.getColorData(player.getEntityImg().getImage());
//            		Color[][] dataB = Tools.getColorData(block.getBlockImg().getImage());
//            		if(Tools.intersectPixels(rectangleA, dataA, rectangleB, dataB)){
//            			returnBlock = block;
//    	            	return true;
//            		}
            	}
            }
        }
        return false;
    }
	public static Boolean processEntityCollisions(Entity player, ArrayList<Entity> ents){
        for (int i=0; i<ents.size(); i++){
        	Entity ent = ents.get(i);
        	Rectangle pos = new Rectangle(player.getEntityPos().getX(),player.getEntityPos().getY(),player.getEntityPos().getWidth()-5,player.getEntityPos().getHeight());
            if (ent.getEntityPos().intersects(pos)){
                returnEnt = ent;
                return true;
            }
        }
        return false;
    }
}
