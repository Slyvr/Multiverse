package com.slyvr.init;

import java.util.ArrayList;

import org.newdawn.slick.geom.Rectangle;

import com.slyvr.beans.*;

public class InitBlocks {
	
	public static void init(Global global)
    {
        Block tech1 = new Block(1, 1, new Rectangle(0, 0, 30, 30), global.getImageByName("block_tech1"), true);
        Block tech2 = new Block(2, 1, new Rectangle(0, 0, 30, 30), global.getImageByName("block_tech2"), true);
        Block tech3 = new Block(3, 1, new Rectangle(0, 0, 30, 30), global.getImageByName("block_tech3"), true);
        Block grass = new Block(4, 1, new Rectangle(0, 0, 30, 30), global.getImageByName("block_grass"), true);
        Block wood = new Block(5, 1, new Rectangle(0,0,30,30), global.getImageByName("block_wood"),true);
        Block respawn = new Block(6, 1, new Rectangle(0, 0, 30, 30), global.getImageByName("block_respawn"), false);
        Block portal = new Block(7, 1, new Rectangle(0, 0, 30, 30), global.getImageByName("block_portal"), false);

        ArrayList<Block> blocks = new ArrayList<Block>();
        blocks.add(tech1);
        blocks.add(tech2);
        blocks.add(tech3);
        blocks.add(grass);
        blocks.add(wood);
        blocks.add(respawn);
        blocks.add(portal);
        global.setBlocks(blocks);
    }
}
