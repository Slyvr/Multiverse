package com.slyvr.beans;

import org.newdawn.slick.Sound;

public class Sfx {

	private Sound sfx;
	private String name;
	
	public Sfx(String name, Sound sfx){
		this.name=name;
		this.sfx=sfx;
	}
	
	//SFX
	public Sound getSfx(){
		return sfx;
	}
	public void setSfx(Sound sfx){
		this.sfx=sfx;
	}
	//Name
	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name=name;
	}
}
