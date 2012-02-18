package com.slyvr.render;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.slyvr.beans.*;

public class Renderer {

	public static void render(GameContainer container, Graphics g, Global global){
		
		
		RenderBlocks.renderBlocks(g, global);
		RenderEntities.renderEntities(g, global);
		RenderParticles.renderParticles(g, global);
		
		//Always last on list, on top
		RenderMenu.renderMenu(g, global);
	}
	
}
