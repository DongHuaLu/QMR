package com.game.npc.script;

import com.game.npc.struts.NPC;
import com.game.player.structs.Player;
import com.game.script.IScript;

public interface INpcDefaultActionScript extends IScript {

	public void defaultAction(Player player, NPC npc);
}
