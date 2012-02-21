package com.slyvr.init.levels;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;

import com.slyvr.beans.*;

public class InitLevel3 {
	
	public static Level loadLevel3(Global global)
    {     
        //Verse1
        Verse verse = new Verse();
        verse.setVerseId(1);
        verse.setVerseBgColor(Color.gray);
        verse.setVerseBlockLimit(0);
        verse.setVerseString("*Get to the Portal!*");
        
        Image bgTex = global.getImageByName("bg_level3-1").getImage();
        ArrayList<Block> bgBlocks = LevelTools.generateLevelBlocksBg(global, bgTex, false);
        verse.setVerseBackground(bgBlocks);

        Image gridTex = global.getImageByName("grid_level3-1").getImage();
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
        
        //Verse2
        Verse verse2 = new Verse();
        verse2.setVerseId(2);
        verse2.setVerseBgColor(Color.gray);
        verse2.setVerseBlockLimit(0);
        verse2.setVerseString("*Get to the Portal!*");
        
        Image bgTex2 = global.getImageByName("bg_level3-2").getImage();
        ArrayList<Block> bgBlocks2 = LevelTools.generateLevelBlocksBg(global, bgTex2, false);
        verse2.setVerseBackground(bgBlocks2);

        Image gridTex2 = global.getImageByName("grid_level3-2").getImage();
        ArrayList<Block> verseBlocks2 = LevelTools.generateLevelBlocks(global, gridTex2, false);
        verse2.setVerseBlocks(verseBlocks2);


        //Add Entities
        Entity player = new Entity(global.getEntityByName("ent_player"));
        player.setEntityPos(new Rectangle(60,60,30,30));

        //Level
        Level level = new Level();
        level.setLevelId(3);
        //Add verses
        level.addLevelVerse(verse);
        level.addLevelVerse(verse2);
        //Add entities
        level.addLevelEntity(player);

        return level;
    }
}
