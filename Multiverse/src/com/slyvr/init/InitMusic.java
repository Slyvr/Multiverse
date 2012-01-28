package com.slyvr.init;

import java.util.ArrayList;

import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;

import com.slyvr.beans.*;

public class InitMusic {

	public static void init(Global global) throws SlickException{
		ArrayList<Song> songs = new ArrayList<Song>();
		
		songs.add(new Song("Epoq-Lepidoptera", new Music("resources/music/Epoq-Lepidoptera.ogg")));
		
		//songs.get(0).getSong().loop();
		
		global.setSongs(songs);
	}
}
