package com.slyvr.init;

import java.util.ArrayList;

import org.newdawn.slick.Animation;

import com.slyvr.beans.*;

public class InitAnimations {

	public static void init(Global global){
		ArrayList<Animate> animations = new ArrayList<Animate>();
		
		animations.add(new Animate(new Animation(global.getSheetByName("logoSheet").getSheet(),150),"logoAni"));
		
		global.setAnimations(animations);
	}
}
