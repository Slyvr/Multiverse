package com.slyvr.beans;

import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Rectangle;

public class ParticleEngine {
	
	private Random random;
    public Rectangle EmitterLocation;
    private ArrayList<Particle> particles;
    private ArrayList<Img> imgs;

    public ParticleEngine(ArrayList<Img> imgs, Rectangle location)
    {
        EmitterLocation = location;
        this.imgs = imgs;
        this.particles = new ArrayList<Particle>();
        random = new Random();
    }

    public ArrayList<Particle> getParticles()
    {
        return particles;
    }

    public void Update(int lifetime)
    {
        int total = 10;

        for (int i = 0; i < total; i++)
        {
            particles.add(GenerateNewParticle(lifetime));
        }

        for (int particle = 0; particle < particles.size(); particle++)
        {
            particles.get(particle).Update();
            if (particles.get(particle).TTL <= 0)
            {
                particles.remove(particle);
                particle--;
            }
        }
    }

    private Particle GenerateNewParticle(int lifetime)
    {
        Img img = imgs.get(random.nextInt(imgs.size()));
        Rectangle position = EmitterLocation;
        Rectangle velocity = new Rectangle(
                                1f * (float)(random.nextDouble() * 2 - 1),
                                1f * (float)(random.nextDouble() * 2 - 1),0,0);
        float angle = 0;
        float angularVelocity = 0.1f * (float)(random.nextDouble() * 2 - 1);
        Color color = new Color(
                    (float)random.nextDouble(),
                    (float)random.nextDouble(),
                    (float)random.nextDouble());
        float size = (float)random.nextDouble();
        int ttl = random.nextInt(lifetime);

        return new Particle(img, position, velocity, angle, angularVelocity, color, size, ttl);
    }

//    public void Draw(SpriteBatch spriteBatch)
//    {
//        for (int index = 0; index < particles.Count; index++)
//        {
//            particles[index].Draw(spriteBatch);
//        }
//    }
}
