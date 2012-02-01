package com.slyvr.beans;

import java.util.ArrayList;

import org.newdawn.slick.Music;

public class Current {

	private Menu menu;
	private Verse currentVerse;
    private Level currentLevel;
    private Block currentBlockType;
	private Music song;
	
	public Current(){
	
	}
	
	//Current Menu
	public Menu getMenu(){
		return menu;
	}
	public void setMenu(Menu menu){
		this.menu=menu;
	}
	//Current verse
    public Verse getCurrentVerse()
    {
        return currentVerse;
    }
    public void setCurrentVerse(Verse verse)
    {
        currentVerse = verse;
    }
    //Current level
    public Level getCurrentLevel()
    {
        return currentLevel;
    }
    public void setCurrentLevel(Level level)
    {
        currentLevel = level;
    }
    //Current Block Type
    public Block getCurrentBlockType()
    {
        return currentBlockType;
    }
    public void setCurrentBlockType(Block block)
    {
        currentBlockType = block;
    }
	//Current Song
	public Music getSong(){
		return song;
	}
	public void setSong(Music song){
		this.song=song;
	}
	//Player
    public Entity getCurrentPlayer(Current current)
    {
        ArrayList<Entity> entities = current.getCurrentLevel().getLevelEntities();
        for (int i=0; i<entities.size(); i++){
        	if (entities.get(i).getEntityName().equals("ent_player")) return entities.get(i);
        }
        return null;
    }
    //Portal Door
    public Block getCurrentPortal(Current current)
    {
    	ArrayList<Block> blocks = current.getCurrentVerse().getVerseBlocks();
    	for (int i=0; i<blocks.size(); i++){
        	if (blocks.get(i).getBlockImg().getName().equals("block_portal")) return blocks.get(i);
        }
        return null;
    }
    //Respawn
    public Block getCurrentRespawn(Current current)
    {
    	ArrayList<Block> blocks = current.getCurrentVerse().getVerseBlocks();
    	for (int i=0; i<blocks.size(); i++){
        	if (blocks.get(i).getBlockImg().getName().equals("block_respawn")) return blocks.get(i);
        }
        return null;
    }
}
