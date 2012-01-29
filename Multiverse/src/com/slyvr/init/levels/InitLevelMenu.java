package com.slyvr.init.levels;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.geom.Rectangle;

import com.slyvr.beans.Block;
import com.slyvr.beans.Entity;
import com.slyvr.beans.Global;
import com.slyvr.beans.Level;
import com.slyvr.beans.Verse;

public class InitLevelMenu {

	public static Level init(Global global)
    {
        Verse verse = new Verse();
        verse.setVerseId(1);
        verse.setVerseBgColor(Color.gray);
        verse.setVerseBlockLimit(0);
        
        ArrayList<Block> background = new ArrayList<Block>();
        for(int x=0; x<990; x+=30){
        	for (int y=0; y<660; y+=30){
        		Block block = new Block(global.getBlockByTextureName("block_tech1-1"));
        		block.setBlockPos(new Rectangle(x,y,30,30));
        		background.add(block);
        	}
        }
        verse.setVerseBackground(background);

        ArrayList<Block> verseBlocks = LevelTools.generateLevelBlocks(global, global.getImageByName("grid_levelMain").getImage(), false);
        verse.setVerseBlocks(verseBlocks);

        ArrayList<Entity> verseEnts = LevelTools.generateLevelEntities(global, global.getImageByName("grid_levelMain").getImage());
        verse.setVerseEntities(verseEnts);

        Entity player = new Entity(global.getEntityByName("Player"));
        player.setEntityPos(new Rectangle(150,270,30,30));

        Level level = new Level();
        level.setLevelId(0);
        //Add verses
        level.addLevelVerse(verse);
        //Add entities
        level.addLevelEntity(player);

        global.getCurrent().setCurrentVerse(verse);
        return level;
    }
}
