package com.slyvr.render;

import org.newdawn.slick.Graphics;

import com.slyvr.beans.*;

public class RenderParticles {

	public static void renderParticles(Graphics g, Global global){
		for (Particle particle : global.getParticleEngine().getParticles()){
			particle.img.getImage().draw(particle.position.getX(),particle.position.getY());
		}
	}
}
