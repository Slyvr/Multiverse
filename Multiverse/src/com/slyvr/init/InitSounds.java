package com.slyvr.init;

import java.util.ArrayList;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

import com.slyvr.beans.*;

public class InitSounds {

	public static void init(Global global){
		ArrayList<Sfx> sounds = new ArrayList<Sfx>();
		
		try{
			sounds.add(new Sfx("cursor1",new Sound("resources/sfx/cursor1.wav")));
		
		}catch(SlickException e){
			e.printStackTrace();
		}
		
		global.setSounds(sounds);
	}
}
