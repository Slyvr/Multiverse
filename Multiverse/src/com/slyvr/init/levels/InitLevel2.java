package com.slyvr.init.levels;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;

import com.slyvr.beans.*;

public class InitLevel2 {
	
	public static Level loadLevel2(Global global)
    {     
        //Verse1
        Verse verse = new Verse();
        verse.setVerseId(1);
        verse.setVerseBgColor(Color.gray);
        verse.setVerseBlockLimit(0);
        verse.setVerseString("*Get to the Portal!*");
        
        Image bgTex = global.getImageByName("bg_level2").getImage();
        ArrayList<Block> bgBlocks = LevelTools.generateLevelBlocksBg(global, bgTex, false);
        verse.setVerseBackground(bgBlocks);

        Image gridTex = global.getImageByName("grid_level2").getImage();
        ArrayList<Block> verseBlocks = LevelTools.generateLevelBlocks(global, gridTex, false);
        verse.setVerseBlocks(verseBlocks);
        
        
        //Effect Blocks
        ArrayList<EffectBlock> verseEffectBlocks = LevelTools.generateLevelEffectBlocks(global, gridTex, false);
        verse.setVerseEffectBlocks(verseEffectBlocks);
        
        //Get Btns
        ArrayList<EffectBlock> btns = new ArrayList<EffectBlock>();
        for (EffectBlock block : verse.getVerseEffectBlocks()){
        	if (block.getBlockImg().getName().contains("btn")){
        		btns.add(block);
        	}
        }
        //Set Btns affected Door Blocks
        for (EffectBlock block : verse.getVerseEffectBlocks()){
        	if (block.getBlockImg().getName().equals("block_door")){
        		for (EffectBlock btn : btns){
        			if (btn.getAffectedBlock()==null) {
        				btn.setAffectedBlock(block);
        				btn.setAffectedOrigPos(block.getBlockPos());
        			}
        			break;
        		}
        	}
        }
        

        //Add Entities
        Entity player = new Entity(global.getEntityByName("ent_player"));
        player.setEntityPos(new Rectangle(60,60,30,30));

        //Level
        Level level = new Level();
        level.setLevelId(2);
        //Add verses
        level.addLevelVerse(verse);
        //Add entities
        level.addLevelEntity(player);

        return level;
    }
}
