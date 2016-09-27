package com.game.map.script;

import com.game.map.structs.Map;
import com.game.player.structs.Player;
import com.game.script.IScript;
import com.game.structs.Grid;

public interface IEnterGridScript extends IScript {

	public void onEnterGrid(Player player, Map map, Grid grid);
}
