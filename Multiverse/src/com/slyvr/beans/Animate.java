package com.slyvr.beans;

import org.newdawn.slick.Animation;

public class Animate {

	private String name;
	private Animation animation;
	
	public Animate(Animation animation, String name){
		this.animation=animation;
		this.name=name;
	}
	
	//Name
	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name=name;
	}
	//Animation
	public Animation getAnimation(){
		return animation;
	}
	public void setAnimation(Animation animation){
		this.animation=animation;
	}
}
