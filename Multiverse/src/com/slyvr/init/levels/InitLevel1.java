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
        
        ArrayList<Block> background = new ArrayList<Block>();
        for(int x=0; x<990; x+=30){
        	for (int y=30; y<660; y+=30){
        		Block block = new Block(global.getBlockByTextureName("block_tech1-1"));
        		block.setBlockPos(new Rectangle(x,y,30,30));
        		background.add(block);
        	}
        }
        verse.setVerseBackground(background);

        Image gridTex = global.getImageByName("grid_level2").getImage();
        ArrayList<Block> verseBlocks = LevelTools.generateLevelBlocks(global, gridTex, false);
        verse.setVerseBlocks(verseBlocks);

        //Add Entities
        Entity player = new Entity(global.getEntityByName("Player"));
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
