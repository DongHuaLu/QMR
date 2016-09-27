package com.game.npc.script;

import com.game.npc.struts.NPC;
import com.game.player.structs.Player;
import com.game.script.IScript;

public interface INpcServiceScript extends IScript {

	public void reqService(Player player, NPC npc, String parameters);
}
