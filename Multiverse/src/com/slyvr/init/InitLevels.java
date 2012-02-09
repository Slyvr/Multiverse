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
}
