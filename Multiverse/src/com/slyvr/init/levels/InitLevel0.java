package com.slyvr.init.levels;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;

import com.slyvr.beans.*;

public class InitLevel0 {
	
	public static Level loadLevel0(Global global)
    {     
        //Verse1
        Verse verse = new Verse();
        verse.setVerseId(1);
        verse.setVerseBgColor(Color.gray);
        verse.setVerseBackground(global.getImageByName("bg1"));
        verse.setVerseBlockLimit(0);
        verse.setVerseString("*Get to the Portal!*");

        Image gridTex = global.getImageByName("grid_level0").getImage();
        ArrayList<Block> verseBlocks = LevelTools.generateLevelBlocks(global, gridTex, false);
        verse.setVerseBlocks(verseBlocks);

        //Add Entities
        Entity player = new Entity(global.getEntityByName("Player"));

        //Level
        Level level = new Level();
        level.setLevelId(0);
        //Add verses
        level.addLevelVerse(verse);
        //Add entities
        level.addLevelEntity(player);

        return level;
    }
}
