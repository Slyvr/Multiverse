package com.slyvr.init;

import java.util.ArrayList;

import com.slyvr.beans.*;
import com.slyvr.init.levels.*;

public class InitLevels {
	
	public static void init(Global global)
    {
        Level menuLevel = InitLevelMenu.init(global);

        ArrayList<Level> levels = new ArrayList<Level>();
        levels.add(menuLevel);
        global.setLevels(levels);
        global.getCurrent().setCurrentLevel(menuLevel);
        global.setMenuLevel(menuLevel);
    }
    public static Level loadLevelByIndex(Global global, Current current, int index)
    {
        Level level = null;
        if (index == 0) level = InitLevel1.loadLevel1(global);
        return level;
    }
}
