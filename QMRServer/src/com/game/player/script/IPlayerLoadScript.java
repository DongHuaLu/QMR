package com.game.player.script;

import com.game.player.structs.Player;
import com.game.script.IScript;

public interface IPlayerLoadScript extends IScript {
	public Player onLoad(Player player, String data);
}
