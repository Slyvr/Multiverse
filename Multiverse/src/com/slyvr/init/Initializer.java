package com.slyvr.init;

import org.newdawn.slick.GameContainer;

import org.newdawn.slick.SlickException;

import com.slyvr.beans.*;

public class Initializer {

	public static void init(GameContainer container, Global global) throws SlickException{
		
		//container.setMouseGrabbed(true);
		System.out.println("Loading images...");
		InitImages.init(global);
		System.out.println("Loading particles...");
		InitParticles.init(global);
		System.out.println("Loading menus...");
		InitMenus.init(global);
		System.out.println("Loading music...");
		InitMusic.init(global);
		System.out.println("Loading sounds...");
		InitSounds.init(global);
		System.out.println("Loading blocks...");
		InitBlocks.init(global);
		System.out.println("Loading entities...");
		InitEntities.init(global);
		System.out.println("Loading levels...");
		//TODO - causes lag in the initial loading
		InitLevels.init(global);
		System.out.println("Done Loading!");
	}
}