package com.game.player.script;

import com.game.fight.structs.Fighter;
import com.game.player.structs.Player;
import com.game.script.IScript;

public interface IPlayerWasHitScript extends IScript {

	public void wasHit(Fighter fighter, Player player);
}
