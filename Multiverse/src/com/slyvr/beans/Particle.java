package com.slyvr.beans;

import org.newdawn.slick.Color;
import org.newdawn.slick.geom.Rectangle;

public class Particle {
	public Img img;
    public Rectangle position;
    public Rectangle velocity;
    public float angle;
    public float angularVelocity;
    public Color color;
    public float size;
    public int TTL;

    public Particle(Img img, Rectangle position, Rectangle velocity,
        float angle, float angularVelocity, Color color, float size, int ttl)
    {
        this.img = img;
        this.position = position;
        this.velocity = velocity;
        this.angle = angle;
        this.angularVelocity = angularVelocity;
        this.color = color;
        this.size = size;
        this.TTL = ttl;
    }

    public void Update()
    {
        TTL--;
        //just add x+x and y+y
        //position += velocity;
        angle += angularVelocity;
    }
    public void UpdateGun(float x, float y)
    {
        TTL--;
        //just add x+x, y+y
        //position += new Rectangle(x,y,0,0);
        angle += angularVelocity;
    }

//    public void Draw(SpriteBatch spriteBatch)
//    {
//        Rectangle sourceRectangle = new Rectangle(0, 0, Texture.Width, Texture.Height);
//        Vector2 origin = new Vector2(Texture.Width / 2, Texture.Height / 2);
//
//        spriteBatch.Draw(Texture, Position, sourceRectangle, Color,
//            Angle, origin, Size, SpriteEffects.None, 0f);
//    }
}
