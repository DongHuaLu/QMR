package com.game.gm.script;

import com.game.player.structs.Player;
import com.game.script.IScript;

public interface IGmCommandScript extends IScript {

	public void doCommand(Player player, String command);
}
