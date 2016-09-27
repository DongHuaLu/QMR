package com.game.map.script;

import com.game.map.structs.Map;
import com.game.player.structs.Player;
import com.game.script.IScript;

public interface IQuitMapScript extends IScript {

	public void onQuitMap(Player player, Map map);
}
