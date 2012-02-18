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
    
    //Img
    public Img getImg() {
		return img;
	}
	public void setImg(Img img) {
		this.img = img;
	}
	//Position
	public Rectangle getPosition() {
		return position;
	}
	public void setPosition(Rectangle position) {
		this.position = position;
	}
	//Velocity
	public Rectangle getVelocity() {
		return velocity;
	}
	public void setVelocity(Rectangle velocity) {
		this.velocity = velocity;
	}
	//Angle
	public float getAngle() {
		return angle;
	}
	public void setAngle(float angle) {
		this.angle = angle;
	}
	//AngularVelocity
	public float getAngularVelocity() {
		return angularVelocity;
	}
	public void setAngularVelocity(float angularVelocity) {
		this.angularVelocity = angularVelocity;
	}
	//Color
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	//Size
	public float getSize() {
		return size;
	}
	public void setSize(float size) {
		this.size = size;
	}
	//Time To Live
	public int getTTL() {
		return TTL;
	}
	public void setTTL(int tTL) {
		TTL = tTL;
	}

	public void Update()
    {
        TTL--;
        //just add x+x and y+y
        position.setX(position.getX()+velocity.getX());
        position.setY(position.getY()+velocity.getY());
        angle += angularVelocity;
    }
}
