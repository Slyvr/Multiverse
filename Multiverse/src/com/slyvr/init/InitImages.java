package com.slyvr.init;

import java.util.ArrayList;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import com.slyvr.beans.*;

public class InitImages {

	public static void init(Global global){
		ArrayList<Img> images = new ArrayList<Img>();
		ArrayList<ImgSheet> sheets = new ArrayList<ImgSheet>();
		
		try{
			//Menu Stuff
			images.add(new Img(new Image("resources/imgs/btn_cursor.png"),"btn_cursor"));
			images.add(new Img(new Image("resources/imgs/logo.png"),"logo"));
			images.add(new Img(new Image("resources/imgs/inventory.png"),"inventory"));
			sheets.add(new ImgSheet(new SpriteSheet(new Image("resources/imgs/btns.png"),193,58), "btns"));
			sheets.add(new ImgSheet(new SpriteSheet(new Image("resources/imgs/btns2.png"),193,58), "btns2"));
			sheets.add(new ImgSheet(new SpriteSheet(new Image("resources/imgs/logoSheet.png"),434,125), "logoSheet"));
			
			//Blocks
			images.add(new Img(new Image("resources/imgs/blocks/block_grass.png"),"block_grass"));
			images.add(new Img(new Image("resources/imgs/blocks/block_wood.png"),"block_wood"));
			images.add(new Img(new Image("resources/imgs/blocks/block_tech1.png"),"block_tech1"));
			images.add(new Img(new Image("resources/imgs/blocks/block_tech1-1.png"),"block_tech1-1"));
			images.add(new Img(new Image("resources/imgs/blocks/block_tech2-1.png"),"block_tech2-1"));
			images.add(new Img(new Image("resources/imgs/blocks/block_tech2-2.png"),"block_tech2-2"));
			images.add(new Img(new Image("resources/imgs/blocks/block_tech2-3.png"),"block_tech2-3"));
			images.add(new Img(new Image("resources/imgs/blocks/block_tech2-4.png"),"block_tech2-4"));
			images.add(new Img(new Image("resources/imgs/blocks/block_tech3.png"),"block_tech3"));
			images.add(new Img(new Image("resources/imgs/blocks/block_portal.png"),"block_portal"));
			images.add(new Img(new Image("resources/imgs/blocks/block_respawn.png"),"block_respawn"));
			images.add(new Img(new Image("resources/imgs/blocks/block_highlight.png"),"block_highlight"));
			images.add(new Img(new Image("resources/imgs/blocks/block_btnDown.png"),"block_btnDown"));
			images.add(new Img(new Image("resources/imgs/blocks/block_btnUp.png"),"block_btnUp"));
			images.add(new Img(new Image("resources/imgs/blocks/block_door.png"),"block_door"));
			images.add(new Img(new Image("resources/imgs/blocks/block_doorway.png"),"block_doorway"));
			images.add(new Img(new Image("resources/imgs/blocks/block_lever1.png"),"block_lever1"));
			images.add(new Img(new Image("resources/imgs/blocks/block_lever2.png"),"block_lever2"));
			
			//Entities
			images.add(new Img(new Image("resources/imgs/entities/player/player_jet_jump_left.png"),"player_jet_jump_left"));
			images.add(new Img(new Image("resources/imgs/entities/player/player_jet_jump_right.png"),"player_jet_jump_right"));
			images.add(new Img(new Image("resources/imgs/entities/player/player_jet_left.png"),"player_jet_left"));
			images.add(new Img(new Image("resources/imgs/entities/player/player_jet_right.png"),"player_jet_right"));
			//right
			images.add(new Img(new Image("resources/imgs/entities/ent_player1.png"),"ent_player1"));
			images.add(new Img(new Image("resources/imgs/entities/ent_player2.png"),"ent_player2"));
			//left
			images.add(new Img(new Image("resources/imgs/entities/ent_player1-1.png"),"ent_player1-1"));
			images.add(new Img(new Image("resources/imgs/entities/ent_player2-1.png"),"ent_player2-1"));
			//enemies
			images.add(new Img(new Image("resources/imgs/entities/ent_block_rock.png"),"ent_block_rock"));
			images.add(new Img(new Image("resources/imgs/entities/ent_floaty.png"),"ent_floaty"));
			
			//Grids
			images.add(new Img(new Image("resources/imgs/grids/grid_levelMain.png"),"grid_levelMain"));
			images.add(new Img(new Image("resources/imgs/grids/grid_level1.png"),"grid_level1"));
			images.add(new Img(new Image("resources/imgs/grids/grid_level2.png"),"grid_level2"));
			
		}catch(SlickException e){
			e.printStackTrace();
		}
		
		global.setSheets(sheets);
		global.setImages(images);
	}
}
