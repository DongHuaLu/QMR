package com.game.map.script;

import com.game.player.structs.Player;
import com.game.script.IScript;

public interface IChangeMapScript extends IScript {

	public void onChangeMap(Player player);
}
