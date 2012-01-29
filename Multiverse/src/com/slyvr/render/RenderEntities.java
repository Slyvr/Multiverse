package com.slyvr.render;

import org.newdawn.slick.Graphics;

import com.slyvr.beans.*;

public class RenderEntities {

	public static void renderEntities(Graphics g, Global global){
		Verse currentVerse = global.getCurrent().getCurrentVerse();
		for (int i=0; i<currentVerse.getVerseEntities().size(); i++){
			Entity ent = currentVerse.getVerseEntities().get(i);
			if (ent!=null){
				if (ent.getEntityImg()!=null){
					if (ent.getEntityImg().getImage()!=null){
						ent.getEntityImg().getImage().draw(ent.getEntityPos().getX(),ent.getEntityPos().getY());
					}
				}
			}
		}
		Level currentLevel = global.getCurrent().getCurrentLevel();
		for (int i=0; i<currentLevel.getLevelEntities().size(); i++){
			Entity ent = currentLevel.getLevelEntities().get(i);
			if (ent!=null){
				if (ent.getEntityImg()!=null){
					if (ent.getEntityImg().getImage()!=null){
						ent.getEntityImg().getImage().draw(ent.getEntityPos().getX(),ent.getEntityPos().getY());
					}
				}
			}
		}
	}
}
