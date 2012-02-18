package com.slyvr.init;

import java.util.ArrayList;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

import com.slyvr.beans.*;

public class InitParticles {

	public static void init(Global global) throws SlickException
    {
        ParticleEngine particleEngine;
        ArrayList<Img> particleTexes = new ArrayList<Img>();
        particleTexes.add(new Img(new Image("resources/imgs/particle1.png"),"particle1"));
        particleEngine = new ParticleEngine(particleTexes, new Rectangle(400, 240,0,0));
        global.setParticleEngine(particleEngine);
    }
}
