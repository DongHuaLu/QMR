package com.game.backpack.structs;

import com.game.player.structs.Player;

public interface IAutoUseItem {

	public boolean autoTakeUpUse(Player player, String... parameters);
}
