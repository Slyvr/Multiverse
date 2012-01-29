package com.slyvr.init;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.geom.Rectangle;

import com.slyvr.beans.*;
import com.slyvr.init.levels.*;

public class InitLevels {
	
	public static void init(Global global)
    {
        Level menuLevel = InitLevelMenu.init(global);
        Level level0 = InitLevel0.loadLevel0(global);

        ArrayList<Level> levels = new ArrayList<Level>();
        levels.add(menuLevel);
        levels.add(level0);
        global.setLevels(levels);
        global.getCurrent().setCurrentLevel(menuLevel);
        global.setMenuLevel(menuLevel);
    }
    public static Level loadLevelByIndex(Global global, Current current, int index)
    {
        Level level = null;
        if (index == 0) level = InitLevel0.loadLevel0(global);
        return level;
    }
}
