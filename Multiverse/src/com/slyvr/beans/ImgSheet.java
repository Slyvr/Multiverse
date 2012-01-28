package com.slyvr.beans;

import org.newdawn.slick.SpriteSheet;

public class ImgSheet {

	private SpriteSheet sheet;
	private String name;
	
	public ImgSheet(SpriteSheet sheet, String name){
		this.sheet=sheet;
		this.name=name;
	}
	
	//Name
	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name=name;
	}
	//Sheet
	public SpriteSheet getSheet(){
		return sheet;
	}
	public void setSheet(SpriteSheet sheet){
		this.sheet=sheet;
	}
}
