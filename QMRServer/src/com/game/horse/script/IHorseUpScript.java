package com.game.horse.script;

import com.game.player.structs.Player;
import com.game.script.IScript;

public interface IHorseUpScript extends IScript {

	public void onHorseUp(Player player , byte status , int nextLayer);
}
