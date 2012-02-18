package com.slyvr.beans;

import java.util.List;

import org.newdawn.slick.geom.Rectangle;

public class EffectBlock extends Block{

	private Block affectedBlock;
	private Rectangle affectedOrigPos;
	private int effectId;
	
	public EffectBlock(Block affectedBlock, Rectangle affectedOrigPos, int effectId){
		this.affectedBlock=affectedBlock;
		this.affectedOrigPos=affectedOrigPos;
		this.effectId=effectId;
	}
	public EffectBlock(Block block)
    {
        this.blockPos = block.getBlockPos();
        this.blockImg = block.getBlockImg();
        this.removable = block.getRemovable();
        this.affectBlockTotal = block.getAffectBlockTotal();
        this.playerPlaced = block.getPlayerPlaced();
        this.color = block.getColor();
    }
	
	//Blocks
	public Block getAffectedBlock(){
		return affectedBlock;
	}
	public void setAffectedBlock(Block affectedBlock){
		this.affectedBlock=affectedBlock;
	}
	//OrigPos's
	public Rectangle getAffectedOrigPos(){
		return affectedOrigPos;
	}
	public void setAffectedOrigPos(Rectangle affectedOrigPos){
		this.affectedOrigPos=affectedOrigPos;
	}
	//Id
	public int getId(){
		return effectId;
	}
	public void setId(int effectId){
		this.effectId=effectId;
	}
	
}
