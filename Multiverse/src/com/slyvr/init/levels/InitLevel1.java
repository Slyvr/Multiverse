package com.slyvr.init.levels;

import java.util.ArrayList;

import org.newdawn.slick.Color;

import com.slyvr.beans.*;

public class InitLevel1 {
	
	public static Level loadLevel1(Global global)
    {
        //Verse1
        Verse verse = new Verse();
        verse.setVerseId(1);
        verse.setVerseBgColor(Color.gray);
        verse.setVerseBackground(global.getImageByName("bg2"));
        verse.setVerseBlockLimit(0);
        verse.setVerseString("*Try middle clicking*");

        ArrayList<Block> verseBlocks = LevelTools.generateLevelBlocks(global, global.getImageByName("grid_level1-1").getImage(), false);
        verse.setVerseBlocks(verseBlocks);

        //Verse2
        Verse verse2 = new Verse();
        verse2.setVerseId(2);
        verse2.setVerseBgColor(Color.gray);
        verse2.setVerseBackground(global.getImageByName("bg2"));
        verse2.setVerseBlockLimit(8);
        verse2.setVerseString("*Try left clicking - R to Reload*");

        ArrayList<Block> verse2Blocks = LevelTools.generateLevelBlocks(global, global.getImageByName("grid_level1-2").getImage(), false);
        verse2.setVerseBlocks(verse2Blocks);

        ArrayList<Entity> verse2Ents = LevelTools.generateLevelEntities(global, global.getImageByName("grid_level1-2").getImage());
        verse2.setVerseEntities(verse2Ents);

        //Add Entities
        Entity player = new Entity(global.getEntityByName("Player"));

        //Level
        Level level = new Level();
        level.setLevelId(1);
        //Add verses
        level.addLevelVerse(verse);
        level.addLevelVerse(verse2);
        //Add entities
        level.addLevelEntity(player);

        return level;
    }
}
