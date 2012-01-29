package com.slyvr.render;

import org.newdawn.slick.Graphics;

import com.slyvr.beans.*;

public class RenderBlocks {

	public static void renderBlocks(Graphics g, Global global){
		Verse currentVerse = global.getCurrent().getCurrentVerse();
		//render background
		for (int i=0; i<currentVerse.getVerseBackground().size(); i++){
			Block block = currentVerse.getVerseBackground().get(i);
			if (block!=null){
				if (block.getBlockImg()!=null){
					if (block.getBlockImg().getImage()!=null){
						block.getBlockImg().getImage().draw(block.getBlockPos().getX(),block.getBlockPos().getY());
					}
				}
			}
		}
		//render foreground
		for (int i=0; i<currentVerse.getVerseBlocks().size(); i++){
			Block block = currentVerse.getVerseBlocks().get(i);
			if (block!=null){
				if (block.getBlockImg()!=null){
					if (block.getBlockImg().getImage()!=null){
						block.getBlockImg().getImage().draw(block.getBlockPos().getX(),block.getBlockPos().getY());
					}
				}
			}
		}
	}
}
