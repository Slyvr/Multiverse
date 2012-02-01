package com.slyvr.init;

import java.util.ArrayList;

import org.newdawn.slick.geom.Rectangle;

import com.slyvr.beans.*;

public class InitEntities {

	public static void init(Global global)
    {
        Entity player = new Entity(0, "ent_player", new Rectangle(0, 0, global.getImageByName("ent_player1").getImage().getWidth(), global.getImageByName("ent_player1").getImage().getHeight()), 100, 5, 45, 2, 0, global.getImageByName("ent_player1"));
        Entity ent_block_rock = new Entity(1, "ent_block_rock", new Rectangle(0, 0, global.getImageByName("ent_block_rock").getImage().getWidth(), global.getImageByName("ent_block_rock").getImage().getHeight()), 500, 0, 0, 0, 0, global.getImageByName("ent_block_rock"));
        Entity ent_floaty = new Entity(1, "ent_floaty", new Rectangle(0, 0, global.getImageByName("ent_floaty").getImage().getWidth(), global.getImageByName("ent_floaty").getImage().getHeight()), 500, 0, 0, 0, 0, global.getImageByName("ent_floaty"));

        ArrayList<Entity> entities = new ArrayList<Entity>();
        entities.add(player);
        entities.add(ent_block_rock);
        entities.add(ent_floaty);
        global.setEntities(entities);
    }
}
