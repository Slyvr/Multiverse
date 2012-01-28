package com.slyvr.update;

import org.newdawn.slick.GameContainer;

import com.slyvr.beans.*;

public class UpdateMusic {

	public static void update(GameContainer container, Global global){
		if(global.getCurrent().getSong()==null){
			global.getCurrent().setSong(global.getSongByName("Epoq-Lepidoptera").getSong());
		}
		else{
			if (global.getCurrent().getSong().getPosition()==0){
				//Find next song
				
			}
		}
		//global.getCurrent().getSong().play();
	}
}
