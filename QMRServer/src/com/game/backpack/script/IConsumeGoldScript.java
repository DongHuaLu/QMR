package com.game.backpack.script;

import com.game.player.structs.Player;
import com.game.script.IScript;
import com.game.structs.Reasons;

public interface IConsumeGoldScript extends IScript {

	public boolean consumegold(Player player, int num , Reasons reason, long action);
}
