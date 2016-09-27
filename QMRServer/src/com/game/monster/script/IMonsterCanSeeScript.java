package com.game.monster.script;

import com.game.monster.structs.Monster;
import com.game.player.structs.Player;
import com.game.script.IScript;

public interface IMonsterCanSeeScript extends IScript {

	public boolean cansee(Player player, Monster monster);
}
