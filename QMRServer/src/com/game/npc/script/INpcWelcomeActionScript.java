package com.game.npc.script;

import com.game.npc.struts.NPC;
import com.game.player.structs.Player;
import com.game.script.IScript;

public interface INpcWelcomeActionScript extends IScript {

	public void welcome(Player player, NPC npc);
}
