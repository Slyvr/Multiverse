package com.slyvr.update;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Rectangle;

import com.slyvr.beans.*;

public class UpdatePlayer {

	static Input prevInput;
	static Boolean jumping;
	static Boolean onGround;
	static int maxY;
	static Block returnBlock;
	static Entity returnEnt;
	static long prevMilli;
	
	public static void updateMovement(GameContainer container, Global global){
		Input input = container.getInput();
		Entity player = global.getCurrent().getCurrentPlayer(global.getCurrent());
		
		ArrayList<Block> blocks = global.getCurrent().getCurrentVerse().getVerseBlocks();
		ArrayList<Block> blockList = new ArrayList<Block>();
        blockList.addAll(blocks);
        blockList.addAll(global.getCurrent().getCurrentLevel().getLevelBlocks());
		
        if (prevMilli<=0) prevMilli = System.currentTimeMillis();
		int x = player.getEntityX();
        int y = player.getEntityY();
        if (onGround==null) onGround=true;
        if (jumping==null) jumping=false;
        if (prevInput == null) prevInput=container.getInput();
		
        //Player movement occurs every 5 milliseconds
        if ((System.currentTimeMillis()-prevMilli)>=player.getEntitySpeed()){
        	//Move Left
			if (input.isKeyDown(Keyboard.KEY_A)){
				player.setEntityX(x -= 1);
	            if (jumping) player.setEntityImg(global.getImageByName("player_jet_jump_left"));
	            else player.setEntityImg(global.getImageByName("player_jet_left"));
	            if (processBlockCollisions(player, blockList)||player.getEntityX()<0){
	                player.setEntityX(x += 1);
	            }
	            else if (processEntityCollisions(player, global.getCurrent().getCurrentVerse().getVerseEntities())){
	                player.setEntityX(x += 1);
	            }
			}
			//Move Right
			else if (input.isKeyDown(Keyboard.KEY_D)){
				player.setEntityX(x += 1);
	            if (jumping) player.setEntityImg(global.getImageByName("player_jet_jump_right"));
	            else player.setEntityImg(global.getImageByName("player_jet_right"));
	            if (processBlockCollisions(player, blockList)||player.getEntityX()>=960){
	                player.setEntityX(x -= 1);
	            }
	            else if (processEntityCollisions(player, global.getCurrent().getCurrentVerse().getVerseEntities())){
	                player.setEntityX(x -= 1);
	            }
			}
			//Jump
			if (input.isKeyDown(Keyboard.KEY_SPACE) && onGround){
                maxY = (int) (player.getEntityPos().getY() - player.getEntityJumpHeight());
                jumping = true;
            }
            if (input.isKeyDown(Keyboard.KEY_SPACE) && jumping){
                if (player.getEntityImg().equals(global.getImageByName("player_jet_right"))) player.setEntityImg(global.getImageByName("player_jet_jump_right"));
                else if (player.getEntityImg().equals(global.getImageByName("player_jet_left"))) player.setEntityImg(global.getImageByName("player_jet_jump_left"));
            }
            else{
                if (player.getEntityImg().equals(global.getImageByName("player_jet_jump_right"))) player.setEntityImg(global.getImageByName("player_jet_right"));
                else if (player.getEntityImg().equals(global.getImageByName("player_jet_jump_left"))) player.setEntityImg(global.getImageByName("player_jet_left"));
            }
            if (jumping){
                if (maxY < player.getEntityY() && prevInput.isKeyDown(Keyboard.KEY_SPACE)) jumping = true;
                else jumping = false;
                if (jumping){
                    player.setEntityY(y -= player.getEntityFallSpeed() * 2);
                    if (processBlockCollisions(player, blockList)||player.getEntityY()<30){
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
            player.setEntityY(y += player.getEntityFallSpeed());
            if (processBlockCollisions(player, blockList) && !returnBlock.getBlockImg().getName().equals("block_highlight")){
                player.setEntityY(y -= player.getEntityFallSpeed());
                onGround = true;
                jumping = false;
            }
            else if (processEntityCollisions(player, global.getCurrent().getCurrentVerse().getVerseEntities())){
                player.setEntityY(y -= player.getEntityFallSpeed());
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
	}
	
	public static void respawnPlayer(Global global){
		Entity player = global.getCurrent().getCurrentPlayer(global.getCurrent());
		if (global.getCurrent().getCurrentRespawn(global.getCurrent())!=null){
			Rectangle respawnPos = global.getCurrent().getCurrentRespawn(global.getCurrent()).getBlockPos();
			Rectangle pos = new Rectangle(respawnPos.getX(), respawnPos.getY()-10,respawnPos.getWidth(), respawnPos.getHeight());
			player.setEntityPos(pos);
		}
		else{
			player.setEntityPos(new Rectangle(0,0,30,30));
		}
	}
	
	public static Boolean processBlockCollisions(Entity player, ArrayList<Block> blocks)
    {
        for (int i=0; i<blocks.size(); i++){
        	Block block = blocks.get(i);
        	Rectangle pos = new Rectangle(player.getEntityPos().getX(),player.getEntityPos().getY(),player.getEntityPos().getWidth()-5,player.getEntityPos().getHeight());
            if (block.getBlockPos().intersects(pos)){
            	if (!block.getBlockImg().getName().contains("respawn") && !block.getBlockImg().getName().contains("portal")){
	            	returnBlock = block;
	            	return true;
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
