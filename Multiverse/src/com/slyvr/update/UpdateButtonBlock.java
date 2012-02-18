package com.slyvr.update;

import java.util.List;

import org.newdawn.slick.geom.Rectangle;

import com.slyvr.beans.*;

public class UpdateButtonBlock {

	public static void updateButtonBlock(Global global, EffectBlock block){
		block.setBlockImg(global.getImageByName("block_btnDown"));
		
		Block door = block.getAffectedBlock();
		Rectangle origPos = block.getAffectedOrigPos();
		//find doorway block
		for (Block b : global.getCurrent().getCurrentVerse().getVerseBlocks()){
			if (b.getBlockImg().getName().contains("doorway")){
				
				//find up or down from door
				Rectangle up = new Rectangle(origPos.getX(),origPos.getY()-30,30,30);
				Rectangle down = new Rectangle(origPos.getX(),origPos.getY()+30,30,30);
				if (up.intersects(b.getBlockPos())){
					if (!door.getBlockPos().equals(b.getBlockPos())){
						door.setBlockY(door.getBlockY() - 2);
					}
				}
				else if (down.intersects(b.getBlockPos())){
					if (!door.getBlockPos().equals(b.getBlockPos())){
						door.setBlockY(door.getBlockY() + 2);
					}
				}
			}
		}
	}
}
