package com.slyvr.beans;

import org.newdawn.slick.geom.Rectangle;

public class MenuItem {

	private String name;
	private String text;
	private Img img;
	private ImgSheet sheet;
	private int subImgX;
	private int subImgY;
	private Animate animation;
	private Rectangle position;
	
	public MenuItem(String name, Img img, Rectangle position){
		this.name=name;
		this.img=img;
		this.position=position;
	}
	public MenuItem(String name, String text, Rectangle position){
		this.name=name;
		this.text=text;
		this.position=position;
	}
	public MenuItem(String name, ImgSheet sheet, int subImgX, int subImgY, Rectangle position){
		this.name=name;
		this.sheet=sheet;
		this.subImgX=subImgX;
		this.subImgY=subImgY;
		this.position=position;
	}
	public MenuItem(String name, Animate animation, Rectangle position){
		this.name=name;
		this.animation=animation;
		this.position=position;
	}
	
	//Name
	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name=name;
	}
	//Text
	public String getText(){
		return text;
	}
	public void setText(String text){
		this.text=text;
	}
	//Image
	public Img getImg(){
		return img;
	}
	public void setImg(Img img){
		this.img=img;
	}
	//ImgSheet
	public ImgSheet getSheet(){
		return sheet;
	}
	public void setSheet(ImgSheet sheet){
		this.sheet=sheet;
	}
	//SubImgX
	public int getSubImgX(){
		return subImgX;
	}
	public void setSubImgX(int subImgX){
		this.subImgX=subImgX;
	}
	//SubImgY
	public int getSubImgY(){
		return subImgY;
	}
	public void setSubImgY(int subImgY){
		this.subImgY=subImgY;
	}
	//Animation
	public Animate getAnimation(){
		return animation;
	}
	public void setAnimation(Animate animation){
		this.animation=animation;
	}
	//Position
	public Rectangle getPosition(){
		return position;
	}
	public void setPosition(Rectangle position){
		this.position=position;
	}
}
