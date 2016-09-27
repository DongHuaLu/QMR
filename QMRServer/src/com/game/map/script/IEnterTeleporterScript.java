package com.game.map.script;

import com.game.player.structs.Player;
import com.game.script.IScript;

public interface IEnterTeleporterScript extends IScript {

	public void onTeleporter(Player player, int line, int tranId,int scriptid);
}
