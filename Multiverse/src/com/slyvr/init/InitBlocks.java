package com.slyvr.init;

import java.util.ArrayList;

import org.newdawn.slick.geom.Rectangle;

import com.slyvr.beans.*;

public class InitBlocks {
	
	public static void init(Global global)
    {
		Block highlight = new Block(0,1,new Rectangle(0,0,30,30),global.getImageByName("block_highlight"),false);
        Block tech1 = new Block(1, 1, new Rectangle(0, 0, 30, 30), global.getImageByName("block_tech1"), true);
        Block tech1_1 = new Block(2, 1, new Rectangle(0, 0, 30, 30), global.getImageByName("block_tech1-1"), true);
        Block tech2 = new Block(3, 1, new Rectangle(0, 0, 30, 30), global.getImageByName("block_tech2"), true);
        Block tech3 = new Block(4, 1, new Rectangle(0, 0, 30, 30), global.getImageByName("block_tech3"), true);
        Block grass = new Block(5, 1, new Rectangle(0, 0, 30, 30), global.getImageByName("block_grass"), true);
        Block wood = new Block(6, 1, new Rectangle(0,0,30,30), global.getImageByName("block_wood"),true);
        Block respawn = new Block(7, 1, new Rectangle(0, 0, 30, 30), global.getImageByName("block_respawn"), false);
        Block portal = new Block(8, 1, new Rectangle(0, 0, 30, 30), global.getImageByName("block_portal"), false);
        Block btn = new Block(9, 1, new Rectangle(0, 0, 30, 30), global.getImageByName("block_btnUp"), false);
        Block door = new Block(10, 1, new Rectangle(0, 0, 30, 30), global.getImageByName("block_door"), false);
        Block doorway = new Block(11, 1, new Rectangle(0, 0, 30, 30), global.getImageByName("block_doorway"), false);

        ArrayList<Block> blocks = new ArrayList<Block>();
        blocks.add(highlight);
        blocks.add(tech1);
        blocks.add(tech1_1);
        blocks.add(tech2);
        blocks.add(tech3);
        blocks.add(grass);
        blocks.add(wood);
        blocks.add(respawn);
        blocks.add(portal);
        blocks.add(btn);
        blocks.add(door);
        blocks.add(doorway);
        global.setBlocks(blocks);
    }
}
