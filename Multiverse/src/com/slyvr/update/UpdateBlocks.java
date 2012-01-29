package com.slyvr.update;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Rectangle;

import com.slyvr.beans.*;

public class UpdateBlocks {

	static Input prevInput;
	static Boolean buttonPressed;
	
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
                int blockcount = 0;
                for (int i=0; i<global.getCurrent().getCurrentVerse().getVerseBlocks().size(); i++){
                	Block block = global.getCurrent().getCurrentVerse().getVerseBlocks().get(i);
                    if (block.getAffectBlockTotal())
                    {
                        blockcount++;
                    }
                }
                //if (blockcount < global.getCurrent().getCurrentVerse().getVerseBlockLimit()){
                    Boolean returnval = processAllCollisions(input, global);
                    if (!returnval)
                    {
                        Block block = new Block(global.getBlockByTextureName("block_tech3"));
                        Rectangle pos = new Rectangle(input.getMouseX(), input.getMouseY(), block.getBlockImg().getImage().getWidth(), block.getBlockImg().getImage().getHeight());
                        block.setBlockPos(pos);
                        block.setAffectBlockTotal(true);
                        block.setPlayerPlaced(true);
                        global.getCurrent().getCurrentVerse().addVerseBlock(block);
                    }
                //}
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
					global.getCurrent().getCurrentVerse().getVerseBlocks().remove(block);
					break;
				}
			}
		}
		
		prevInput=input;
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
}
