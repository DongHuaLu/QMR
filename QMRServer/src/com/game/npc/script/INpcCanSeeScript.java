package com.game.npc.script;

import com.game.npc.struts.NPC;
import com.game.player.structs.Player;
import com.game.script.IScript;

public interface INpcCanSeeScript extends IScript {

	public boolean cansee(Player player, NPC npc);
}
