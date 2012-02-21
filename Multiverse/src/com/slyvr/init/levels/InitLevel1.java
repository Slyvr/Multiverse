package com.slyvr.init.levels;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;

import com.slyvr.beans.*;

public class InitLevel1 {
	
	public static Level loadLevel1(Global global)
    {     
        //Verse1
        Verse verse = new Verse();
        verse.setVerseId(1);
        verse.setVerseBgColor(Color.gray);
        verse.setVerseBlockLimit(0);
        verse.setVerseString("*Get to the Portal!*");
        
        Image bgTex = global.getImageByName("bg_level1").getImage();
        ArrayList<Block> bgBlocks = LevelTools.generateLevelBlocksBg(global, bgTex, false);
        verse.setVerseBackground(bgBlocks);

        Image gridTex = global.getImageByName("grid_level1").getImage();
        ArrayList<Block> verseBlocks = LevelTools.generateLevelBlocks(global, gridTex, false);
        verse.setVerseBlocks(verseBlocks);

        //Add Entities
        Entity player = new Entity(global.getEntityByName("ent_player"));
        player.setEntityPos(new Rectangle(60,60,30,30));

        //Level
        Level level = new Level();
        level.setLevelId(1);
        //Add verses
        level.addLevelVerse(verse);
        //Add entities
        level.addLevelEntity(player);

        return level;
    }
}
