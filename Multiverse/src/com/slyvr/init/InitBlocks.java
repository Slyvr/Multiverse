package com.slyvr.init;

import java.util.ArrayList;

import org.newdawn.slick.geom.Rectangle;

import com.slyvr.beans.*;

public class InitBlocks {
	
	public static void init(Global global)
    {
		Block highlight = new Block(new Rectangle(0,0,30,30),global.getImageByName("block_highlight"),false);
        Block tech1 = new Block(new Rectangle(0, 0, 30, 30), global.getImageByName("block_tech1"), true);
        Block tech1_dark = new Block(new Rectangle(0, 0, 30, 30), global.getImageByName("block_tech1_dark"), true);
        Block tech2_1 = new Block(new Rectangle(0, 0, 30, 30), global.getImageByName("block_tech2-1"), true);
        Block tech2_1_dark = new Block(new Rectangle(0, 0, 30, 30), global.getImageByName("block_tech2-1_dark"), true);
        Block tech2_2 = new Block(new Rectangle(0, 0, 30, 30), global.getImageByName("block_tech2-2"), true);
        Block tech2_2_dark = new Block(new Rectangle(0, 0, 30, 30), global.getImageByName("block_tech2-2_dark"), true);
        Block tech2_3 = new Block(new Rectangle(0, 0, 30, 30), global.getImageByName("block_tech2-3"), true);
        Block tech2_3_dark = new Block(new Rectangle(0, 0, 30, 30), global.getImageByName("block_tech2-3_dark"), true);
        Block tech2_4 = new Block(new Rectangle(0, 0, 30, 30), global.getImageByName("block_tech2-4"), true);
        Block tech2_4_dark = new Block(new Rectangle(0, 0, 30, 30), global.getImageByName("block_tech2-4_dark"), true);
        Block tech3 = new Block(new Rectangle(0, 0, 30, 30), global.getImageByName("block_tech3"), true);
        Block tech3_dark = new Block(new Rectangle(0, 0, 30, 30), global.getImageByName("block_tech3_dark"), true);
        Block grass = new Block(new Rectangle(0, 0, 30, 30), global.getImageByName("block_grass"), true);
        Block grass_dark = new Block(new Rectangle(0, 0, 30, 30), global.getImageByName("block_grass_dark"), true);
        Block wood = new Block(new Rectangle(0,0,30,30), global.getImageByName("block_wood"),true);
        Block wood_dark = new Block(new Rectangle(0,0,30,30), global.getImageByName("block_wood_dark"),true);
        Block respawn = new Block(new Rectangle(0, 0, 30, 30), global.getImageByName("block_respawn"), false);
        Block portal = new Block(new Rectangle(0, 0, 30, 30), global.getImageByName("block_portal"), false);
        Block btn = new Block(new Rectangle(0, 0, 30, 30), global.getImageByName("block_btnUp"), false);
        Block door = new Block(new Rectangle(0, 0, 30, 30), global.getImageByName("block_door"), false);
        Block doorway = new Block(new Rectangle(0, 0, 30, 30), global.getImageByName("block_doorway"), false);
        Block lever1 = new Block(new Rectangle(0, 0, 30, 30), global.getImageByName("block_lever1"), false);
        Block lever2 = new Block(new Rectangle(0, 0, 30, 30), global.getImageByName("block_lever2"), false);

        ArrayList<Block> blocks = new ArrayList<Block>();
        blocks.add(highlight);
        blocks.add(tech1);
        blocks.add(tech1_dark);
        blocks.add(tech2_1);
        blocks.add(tech2_1_dark);
        blocks.add(tech2_2);
        blocks.add(tech2_2_dark);
        blocks.add(tech2_3);
        blocks.add(tech2_3_dark);
        blocks.add(tech2_4);
        blocks.add(tech2_4_dark);
        blocks.add(tech3);
        blocks.add(tech3_dark);
        blocks.add(grass);
        blocks.add(grass_dark);
        blocks.add(wood);
        blocks.add(wood_dark);
        blocks.add(respawn);
        blocks.add(portal);
        blocks.add(btn);
        blocks.add(door);
        blocks.add(doorway);
        blocks.add(lever1);
        blocks.add(lever2);
        global.setBlocks(blocks);
    }
}
