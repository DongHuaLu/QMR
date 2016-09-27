package com.game.player.script;

import com.game.player.structs.Player;
import com.game.script.IScript;

public interface IPlayerCheckScript extends IScript {
	
	public void onCheck(Player player, int type, Object... obj);
	
}
