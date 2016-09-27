package com.game.player.script;

import com.game.fight.structs.Fighter;
import com.game.player.structs.Player;
import com.game.script.IScript;

public interface IPlayerDieScript extends IScript {

	public void onPlayerDie(Player player, Fighter killer);
}
