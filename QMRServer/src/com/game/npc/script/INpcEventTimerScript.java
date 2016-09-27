package com.game.npc.script;

import java.util.List;

import com.game.npc.struts.NPC;
import com.game.script.IScript;

public interface INpcEventTimerScript extends IScript {
	
	public void action(NPC npc, List<Object> parameters);

}
