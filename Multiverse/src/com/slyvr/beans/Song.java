package com.slyvr.beans;

import org.newdawn.slick.Music;

public class Song {

	private Music song;
	private String name;
	
	public Song(String name, Music song){
		this.name=name;
		this.song=song;
	}
	
	//Song
	public Music getSong(){
		return song;
	}
	public void setSong(Music song){
		this.song=song;
	}
	//Name
	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name=name;
	}
}
