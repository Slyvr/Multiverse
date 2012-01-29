package com.slyvr.update;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import com.slyvr.beans.*;

public class Updater {

	static Input prevInput;
	
	public static void update(GameContainer container, int delta, Global global) throws SlickException{
		
		UpdatePlayer.updateMovement(container, global);
		UpdateButtonClick.update(container, global);
		UpdateMusic.update(container, global);
		
		Input input = container.getInput();

		MenuItem cursor = global.getMenuByName("constant").getMenuItemByName("btn_cursor");
		cursor.getPosition().setX(input.getMouseX());
		cursor.getPosition().setY(input.getMouseY());
		
		//Quit
		if (input.isKeyDown(Input.KEY_ESCAPE)){
			global.getCurrent().setMenu(global.getMenuByName("main"));
		}
		//Pause
		if (input.isKeyDown(Input.KEY_P)){
			if(container.isPaused()) container.resume();
			else container.pause();
		}
		
		prevInput=input;
	}
}
