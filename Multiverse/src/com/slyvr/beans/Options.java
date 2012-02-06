package com.slyvr.beans;

import org.lwjgl.input.Keyboard;

public class Options {
	private Boolean fullscreen;
    private int left;
    private int right;
    private int jump;
    private float volume;

    public Options()
    {
        fullscreen = false;
        left = Keyboard.KEY_A;
        right = Keyboard.KEY_D;
        jump = Keyboard.KEY_SPACE;
        volume = 1f;
    }

    //Fullscreen
    public Boolean getFullscreen()
    {
        return fullscreen;
    }
    public void setFullscreen(Boolean fullscreen)
    {
        this.fullscreen = fullscreen;
    }
    //Left
    public int getLeft()
    {
        return left;
    }
    public void setLeft(int left)
    {
        this.left = left;
    }
    //Right
    public int getRight()
    {
        return right;
    }
    public void setRight(int right)
    {
        this.right = right;
    }
    //Jump
    public int getJump()
    {
        return jump;
    }
    public void setJump(int jump)
    {
        this.jump = jump;
    }
    //Volume
    public float getVolume()
    {
        return volume;
    }
    public void setVolume(float volume)
    {
        this.volume = volume;
    }
}
