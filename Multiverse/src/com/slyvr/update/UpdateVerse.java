package com.slyvr.update;

import org.newdawn.slick.Input;

import com.slyvr.beans.*;

public class UpdateVerse {

	private static Input prevInput;
	
	public static void updateMiddleClick(Global global){
		Input input = global.getContainer().getInput();
		if (input.isMousePressed(Input.MOUSE_MIDDLE_BUTTON) && !prevInput.isMousePressed(Input.MOUSE_MIDDLE_BUTTON)){
			//Middle button pressed, find and set next verse, if exists
			Level currentLvl = global.getCurrent().getCurrentLevel();
			if (currentLvl.getLevelVerses().size()>1)
			for (int i=0; i<currentLvl.getLevelVerses().size(); i++){
				Verse verse = currentLvl.getLevelVerses().get(i);
				if (verse.equals(global.getCurrent().getCurrentVerse())){
					if (currentLvl.getLevelVerses().size() != i+1)
						//Change to next verse
						global.getCurrent().setCurrentVerse(currentLvl.getLevelVerses().get(i+1));
					else{
						//Change back to first verse
						global.getCurrent().setCurrentVerse(currentLvl.getLevelVerses().get(0));
					}
					break;
				}
			}
		}
		prevInput=input;
	}
}
