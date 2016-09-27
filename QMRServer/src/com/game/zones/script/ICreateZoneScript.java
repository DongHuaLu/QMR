package com.game.zones.script;

import com.game.player.structs.Player;
import com.game.script.IScript;
import com.game.zones.structs.ZoneContext;

public interface ICreateZoneScript extends IScript {

	public ZoneContext onCreate(Player player, int zoneId);
}
