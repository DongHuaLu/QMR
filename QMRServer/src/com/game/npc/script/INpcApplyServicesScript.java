package com.game.npc.script;

import java.util.List;

import com.game.npc.bean.ServiceInfo;
import com.game.npc.struts.NPC;
import com.game.player.structs.Player;
import com.game.script.IScript;

public interface INpcApplyServicesScript extends IScript {

	public void applyServices(Player player, NPC npc, List<ServiceInfo> infos);
}
