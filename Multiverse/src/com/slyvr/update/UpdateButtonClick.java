package com.slyvr.update;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

import com.slyvr.beans.*;
import com.slyvr.tools.Tools;

public class UpdateButtonClick {

	static Input prevInput;
	static Menu prevMenu;
	
	public static void update(GameContainer container, Global global) throws SlickException{
		Input input = container.getInput();
		if (prevInput==null) prevInput = input;
		//Check current menu button clicks
		if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON) && !prevInput.isMousePressed(Input.MOUSE_LEFT_BUTTON)){
			Color[] mouseColor = new Color[1];
			mouseColor[0] = new Color(1,1,1);
			Rectangle mousePosition = new Rectangle(input.getMouseX(),input.getMouseY(),1,1);
			for(int i=0; i<global.getCurrent().getMenu().getMenuItems().size(); i++){
				MenuItem item = global.getCurrent().getMenu().getMenuItems().get(i);
				if (item.getImg()==null && item.getSheet()==null){
				}
				else{
					//Helps performance
					if (item.getPosition().intersects(mousePosition)){
						//Collision found
						if (item.getName().equals("btn_exit")){
							container.exit();
							break;
						}
						if (item.getName().equals("btn_options")){
							prevMenu = global.getCurrent().getMenu();
							global.getCurrent().setMenu(global.getMenuByName("options"));
							global.getSoundByName("cursor1").getSfx().play();
							break;
						}
						if (item.getName().equals("btn_changeVol")){
							float vol = global.getCurrent().getSong().getVolume();
							if (vol>=1) global.getCurrent().getSong().setVolume(0);
							else global.getCurrent().getSong().setVolume(vol+=0.2);
							global.getMenuByName("options").getMenuItemByName("txt_volume").setText("Volume: "+global.getCurrent().getSong().getVolume());
							global.getSoundByName("cursor1").getSfx().play();
							break;
						}
						if (item.getName().equals("btn_changeFullScreen")){
							if (container.isFullscreen()){
								container.setFullscreen(false);
								global.getMenuByName("options").getMenuItemByName("txt_fullscreen").setText("Windowed");
							}
							else {
								container.setFullscreen(true);
								global.getMenuByName("options").getMenuItemByName("txt_fullscreen").setText("Fullscreen");
							}
							global.getSoundByName("cursor1").getSfx().play();
							break;
						}
						if (item.getName().equals("btn_back")){
							global.getCurrent().setMenu(prevMenu);
							global.getSoundByName("cursor1").getSfx().play();
							break;
						}
						if (item.getName().equals("btn_play")){
							prevMenu = global.getCurrent().getMenu();
							global.getCurrent().setMenu(global.getMenuByName("play"));
							global.getSoundByName("cursor1").getSfx().play();
							break;
						}
						if (item.getName().equals("btn_multi")){
							prevMenu = global.getCurrent().getMenu();
							global.getCurrent().setMenu(global.getMenuByName("multi"));
							global.getSoundByName("cursor1").getSfx().play();
							break;
						}
						if (item.getName().equals("btn_newgame")){
							global.getCurrent().setMenu(global.getMenuByName("game"));
							global.getCurrent().setCurrentLevel(global.getLevels().get(1));
							global.getCurrent().setCurrentVerse(global.getLevels().get(1).getLevelVerses().get(0));
							UpdatePlayer.respawnPlayer(global);
							break;
						}
						if (item.getName().equals("btn_save")){
							global.getCurrent().setMenu(global.getMenuByName("save"));
							global.getSoundByName("cursor1").getSfx().play();
							break;
						}
						if (item.getName().equals("btn_load")){
							global.getCurrent().setMenu(global.getMenuByName("load"));
							global.getSoundByName("cursor1").getSfx().play();
							break;
						}
					}
				}
			}
		}
		prevInput = input;
	}
}
