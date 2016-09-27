package com.game.player.script;

import com.game.player.structs.Player;
import com.game.script.IScript;

public interface IPlayerLoginScript extends IScript {

	public void onLogin(Player player, int type);
}
