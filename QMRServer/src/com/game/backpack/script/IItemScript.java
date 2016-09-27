package com.game.backpack.script;

import com.game.backpack.structs.Item;
import com.game.player.structs.Player;
import com.game.script.IScript;

public interface IItemScript extends IScript {

	public boolean use(Item item, Player player, String... parameters);
}
