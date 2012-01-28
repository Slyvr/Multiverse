package com.slyvr.beans;

import org.newdawn.slick.Image;

public class Img {

	private Image image;
	private String name;
	
	public Img(Image image, String name){
		this.image=image;
		this.name=name;
	}
	
	//Image
	public Image getImage(){
		return image;
	}
	public void setImage(Image image){
		this.image=image;
	}
	//Name
	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name=name;
	}
}
