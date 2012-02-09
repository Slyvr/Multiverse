package com.slyvr.beans;

import java.util.ArrayList;

public class Global {

	private Current current;
	private Options options;
	private ArrayList<Img> images;
	private ArrayList<ImgSheet> sheets;
	private ArrayList<Animate> animations;
	private ArrayList<Menu> menus;
	private ArrayList<Song> songs;
	private ArrayList<Sfx> sounds;
	private ArrayList<Block> blocks;
    private ArrayList<Entity> entities;
    private ArrayList<Level> levels;
    private Boolean paused;
    private ParticleEngine particleEngine;
    private Level menuLevel;
    private long millisec;
	
	public Global(){
		paused=true;
		current=new Current();
		millisec = System.currentTimeMillis();
		options = new Options();
	}
	
	//Current
	public Current getCurrent(){
		return current;
	}
	public void setCurrent(Current current){
		this.current=current;
	}
	//Options
    public Options getOptions()
    {
        return options;
    }
    public void setOptions(Options options)
    {
        this.options = options;
    }
	//Images
	public ArrayList<Img> getImages(){
		return images;
	}
	public void setImages(ArrayList<Img> images){
		this.images=images;
	}
	public Img getImageByName(String name){
		for (int i=0; i<images.size(); i++){
			if (images.get(i).getName().equals(name)){
				return images.get(i);
			}
		}
		return null;
	}
	//Sheets
	public ArrayList<ImgSheet> getSheets(){
		return sheets;
	}
	public void setSheets(ArrayList<ImgSheet> sheets){
		this.sheets=sheets;
	}
	public ImgSheet getSheetByName(String name){
		for(int i=0; i<sheets.size(); i++){
			if (sheets.get(i).getName().equals(name)) return sheets.get(i);
		}
		return null;
	}
	//Animations
	public ArrayList<Animate> getAnimations(){
		return animations;
	}
	public void setAnimations(ArrayList<Animate> animations){
		this.animations=animations;
	}
	public Animate getAnimationByName(String name){
		for(int i=0; i<animations.size(); i++){
			if (animations.get(i).getName().equals(name)) return animations.get(i);
		}
		return null;
	}
	//Menus
	public ArrayList<Menu> getMenus(){
		return menus;
	}
	public void setMenus(ArrayList<Menu> menus){
		this.menus=menus;
	}
	public Menu getMenuByName(String name){
		for (int i=0; i<menus.size(); i++){
			if (menus.get(i).getName().equals(name)){
				return menus.get(i);
			}
		}
		return null;
	}
	//Songs
	public ArrayList<Song> getSongs(){
		return songs;
	}
	public void setSongs(ArrayList<Song> songs){
		this.songs=songs;
	}
	public Song getSongByName(String name){
		for (int i=0; i<songs.size(); i++){
			if (songs.get(i).getName().equals(name)){
				return songs.get(i);
			}
		}
		return null;
	}
	//Sounds
	public ArrayList<Sfx> getSounds(){
		return sounds;
	}
	public void setSounds(ArrayList<Sfx> sounds){
		this.sounds=sounds;
	}
	public Sfx getSoundByName(String name){
		for (int i=0; i<sounds.size(); i++){
			if (sounds.get(i).getName().equals(name)){
				return sounds.get(i);
			}
		}
		return null;
	}
	//Paused
    public Boolean getPaused()
    {
        return paused;
    }
    public void setPaused(Boolean pause)
    {
        paused = pause;
    }
    //Blocks
    public ArrayList<Block> getBlocks()
    {
        return blocks;
    }
    public void setBlocks(ArrayList<Block> blocks)
    {
        this.blocks = blocks;
    }
    public Block getBlockByTextureName(String name)
    {
    	for (int i=0; i<blocks.size(); i++){
    		if (blocks.get(i).getBlockImg().getName().equals(name)) return blocks.get(i);
    	}
        return null;
    }
    //Entities
    public ArrayList<Entity> getEntities()
    {
        return entities;
    }
    public void setEntities(ArrayList<Entity> entities)
    {
        this.entities = entities;
    }
    public Entity getEntityByName(String name)
    {
    	for (int i=0; i<entities.size(); i++){
    		if (entities.get(i).getEntityName().equals(name)) return entities.get(i);
    	}
        return null;
    }
    //Levels
    public ArrayList<Level> getLevels()
    {
        return levels;
    }
    public void setLevels(ArrayList<Level> levels)
    {
        this.levels = levels;
    }
    //Particle Engine
    public ParticleEngine getParticleEngine()
    {
        return particleEngine;
    }
    public void setParticleEngine(ParticleEngine particleEngine)
    {
        this.particleEngine = particleEngine;
    }
    //Menu Level
    public Level getMenuLevel()
    {
        return menuLevel;
    }
    public void setMenuLevel(Level level)
    {
        menuLevel = level;
    }
    //Milliseconds
    public long getMilliseconds()
    {
        return millisec;
    }
    public void setMilliseconds(long millisec)
    {
        this.millisec = millisec;
    }
}
