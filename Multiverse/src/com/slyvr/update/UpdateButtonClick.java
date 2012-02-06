package com.slyvr.update;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

import com.slyvr.beans.*;
import com.slyvr.init.InitLevels;
import com.slyvr.init.levels.*;
import com.slyvr.tools.Tools;

public class UpdateButtonClick {

	static Input prevInput;
	static Menu prevMenu;
	
	public static void update(GameContainer container, Global global) throws SlickException{
		Input input = container.getInput();
		if (prevInput==null) prevInput = input;
		//Check current menu button clicks
		if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON) && !prevInput.isMousePressed(Input.MOUSE_LEFT_BUTTON)){
			Color[][] mouseColor = new Color[5][5];
			for (int x=0; x<5; x++)
				for (int y=0; y<5; y++)
					mouseColor[x][y] = new Color(1,1,1);
			Rectangle mousePosition = new Rectangle(input.getMouseX(),input.getMouseY(),5,5);
			for(int i=0; i<global.getCurrent().getMenu().getMenuItems().size(); i++){
				MenuItem item = global.getCurrent().getMenu().getMenuItems().get(i);
				//Helps performance
				if (item.getPosition().intersects(mousePosition)){
					//Collision found, do per pixel collision detect
					
					Color[][] dataB = null;
					if (item.getImg()!=null) dataB = Tools.getColorData(item.getImg().getImage());
					else if (item.getSheet()!=null) dataB = Tools.getColorData(item.getSheet().getSheet().getSubImage(item.getSubImgX(), item.getSubImgY()));
					
					if (Tools.intersectPixels(mousePosition, mouseColor, item.getPosition(), dataB)){
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
						if (item.getName().equals("btn_changeControls")){
							prevMenu = global.getCurrent().getMenu();
							global.getCurrent().setMenu(global.getMenuByName("controls"));
							global.getSoundByName("cursor1").getSfx().play();
							break;
						}
						if (item.getName().equals("btn_changeLeft")){
							prevMenu = global.getCurrent().getMenu();
							global.getMenuByName("controlselect").getMenuItemByName("txt_key").setText("Left");
							global.getCurrent().setMenu(global.getMenuByName("controlselect"));
							global.getSoundByName("cursor1").getSfx().play();
							break;
						}
						if (item.getName().equals("btn_changeRight")){
							prevMenu = global.getCurrent().getMenu();
							global.getMenuByName("controlselect").getMenuItemByName("txt_key").setText("Right");
							global.getCurrent().setMenu(global.getMenuByName("controlselect"));
							global.getSoundByName("cursor1").getSfx().play();
							break;
						}
						if (item.getName().equals("btn_changeJump")){
							prevMenu = global.getCurrent().getMenu();
							global.getMenuByName("controlselect").getMenuItemByName("txt_key").setText("Jump");
							global.getCurrent().setMenu(global.getMenuByName("controlselect"));
							global.getSoundByName("cursor1").getSfx().play();
							break;
						}
						if (item.getName().equals("btn_back")){
							getBackMenu(global);
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
						if (item.getName().equals("btn_resume")){
							if (global.getCurrent().getCurrentLevel().getLevelId()!=0)
							global.getCurrent().setMenu(global.getMenuByName("game"));
							break;
						}
						if (item.getName().equals("btn_newgame")){
							global.getCurrent().setMenu(global.getMenuByName("game"));
							if (global.getLevels().size()>1){
								InitLevels.init(global);
							}
							global.getLevels().add(InitLevel1.loadLevel1(global));
							global.getCurrent().setCurrentLevel(global.getLevels().get(1));
							global.getCurrent().setCurrentVerse(global.getCurrent().getCurrentLevel().getLevelVerses().get(0));
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
						if (item.getName().equals("btn_credits")){
							global.getCurrent().setMenu(global.getMenuByName("credits"));
							global.getSoundByName("cursor1").getSfx().play();
							break;
						}
					}
				}
			}
		}
		prevInput = input;
	}
	
	public static void getBackMenu(Global global){
		Menu menu = global.getCurrent().getMenu();
		if (menu.getName().equals("controls")){
			global.getCurrent().setMenu(global.getMenuByName("options"));
		}
		else if (menu.getName().equals("options")){
			global.getCurrent().setMenu(global.getMenuByName("main"));
		}
		else if (menu.getName().equals("controlselect")){
			global.getCurrent().setMenu(global.getMenuByName("controls"));
		}
		else if (menu.getName().equals("play")){
			global.getCurrent().setMenu(global.getMenuByName("main"));
		}
		else if (menu.getName().equals("save")){
			global.getCurrent().setMenu(global.getMenuByName("play"));
		}
		else if (menu.getName().equals("load")){
			global.getCurrent().setMenu(global.getMenuByName("play"));
		}
		else if (menu.getName().equals("multi")){
			global.getCurrent().setMenu(global.getMenuByName("main"));
		}
		else if (menu.getName().equals("credits")){
			global.getCurrent().setMenu(global.getMenuByName("main"));
		}
	}
	
	public static void getControlSelect(Global global, GameContainer container){
		
		if (global.getCurrent().getMenu().getName().equals("controlselect")){
			Input input = container.getInput();
			
			for (int i=0; i<Keyboard.KEYBOARD_SIZE; i++){
				if (input.isKeyDown(i) && !input.isKeyDown(Input.KEY_LMENU)){
					if (global.getCurrent().getMenu().getMenuItemByName("txt_key").getText().equals("Left")){
						global.getOptions().setLeft(i);
						global.getMenuByName("controls").getMenuItemByName("txt_left").setText("Left: "+input.getKeyName(i));
					}
					else if (global.getCurrent().getMenu().getMenuItemByName("txt_key").getText().equals("Right")){
						global.getOptions().setRight(i);
						global.getMenuByName("controls").getMenuItemByName("txt_right").setText("Right: "+input.getKeyName(i));
					}
					else if (global.getCurrent().getMenu().getMenuItemByName("txt_key").getText().equals("Jump")){
						global.getOptions().setJump(i);
						global.getMenuByName("controls").getMenuItemByName("txt_jump").setText("Jump: "+input.getKeyName(i));
					}
					global.getCurrent().setMenu(global.getMenuByName("controls"));
					break;
				}
			}
		}
	}
}
