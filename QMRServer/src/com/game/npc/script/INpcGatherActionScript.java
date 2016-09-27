package com.game.npc.script;

import com.game.npc.struts.NPC;
import com.game.player.structs.Player;
import com.game.script.IScript;

public interface INpcGatherActionScript extends IScript {

	public void gather(Player player, NPC npc);
}
