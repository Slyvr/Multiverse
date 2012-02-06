package com.slyvr.update;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import com.slyvr.beans.*;

public class Updater {

	static Input prevInput;
	
	public static void update(GameContainer container, int delta, Global global) throws SlickException{

		if (global.getCurrent().getMenu().getName().contains("game")){
			UpdatePlayer.updateMovement(container, global);
			UpdateBlocks.updateBlockPlacement(global, container);
			UpdateBlocks.updateWoodBlocks(global);
		}
		
		UpdateButtonClick.update(container, global);
		UpdateButtonClick.getControlSelect(global, container);
		UpdateMusic.update(container, global);
		
		Input input = container.getInput();
		if (prevInput==null) prevInput = input;
		
		MenuItem cursor = global.getMenuByName("constant").getMenuItemByName("btn_cursor");
		cursor.getPosition().setX(input.getMouseX());
		cursor.getPosition().setY(input.getMouseY());
		
		//Quit
		if (input.isKeyPressed(Input.KEY_ESCAPE) && !prevInput.isKeyPressed(Input.KEY_ESCAPE)){
			if (global.getCurrent().getMenu().getName().equals("main")){
				System.exit(0);
			}
			else{
				global.getCurrent().setMenu(global.getMenuByName("main"));
			}
		}
		//Pause
		if (input.isKeyDown(Input.KEY_P)){
			if(container.isPaused()) container.resume();
			else container.pause();
		}
		
		prevInput=input;
	}
}
