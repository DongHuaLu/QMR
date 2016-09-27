package com.game.map.script;

import com.game.player.structs.Player;
import com.game.script.IScript;

public interface IBeforeChangeMapScript extends IScript {

	public boolean beforeChangeMap(Player player);
	
}
