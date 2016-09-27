package com.game.map.script;

import com.game.map.structs.Map;
import com.game.player.structs.Player;
import com.game.script.IScript;

public interface IEnterMapScript extends IScript {

	public void onEnterMap(Player player, Map map);
}
