package com.slyvr.beans;

import java.util.ArrayList;

public class Level {
	private int levelId;
    private ArrayList<Verse> levelVerses;
    private ArrayList<Entity> globalEntities;
    private ArrayList<Block> globalBlocks;
    //Constructor
    public Level()
    {
        levelId = 0;
        levelVerses = new ArrayList<Verse>();
        globalBlocks = new ArrayList<Block>();
        globalEntities = new ArrayList<Entity>();
    }
    public Level(int id)
    {
        levelId=id;
        levelVerses=new ArrayList<Verse>();
    }
    //Id
    public int getLevelId()
    {
        return levelId;
    }
    public void setLevelId(int id)
    {
        levelId = id;
    }
    //Verse
    public ArrayList<Verse> getLevelVerses()
    {
        return levelVerses;
    }
    public void setLevelVerses(ArrayList<Verse> verse)
    {
        levelVerses = verse;
    }
    //Individual verse
    public void addLevelVerse(Verse verse)
    {
        levelVerses.add(verse);
    }
    public void removeLevelVerse(Verse verse)
    {
        levelVerses.remove(verse);
    }
    //Global Blocks
    public ArrayList<Entity> getLevelEntities()
    {
        return globalEntities;
    }
    public void setLevelEntities(ArrayList<Entity> ents)
    {
        globalEntities = ents;
    }
    //Individual global blocks
    public void addLevelEntity(Entity ent)
    {
        globalEntities.add(ent);
    }
    public void removeLevelEntity(Entity ent)
    {
        globalEntities.remove(ent);
    }
    //Global Blocks
    public ArrayList<Block> getLevelBlocks()
    {
        return globalBlocks;
    }
    public void setLevelBlocks(ArrayList<Block> blocks)
    {
        globalBlocks = blocks;
    }
    //Individual global blocks
    public void addLevelBlock(Block block)
    {
        globalBlocks.add(block);
    }
    public void removeLevelBlock(Block block)
    {
        globalBlocks.remove(block);
    }
}
