package scripts.npc;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.npc.script.INpcDefaultActionScript;
import com.game.npc.struts.NPC;
import com.game.player.structs.Player;

/**
 * npc采集行为
 * @author heyang
 *
 */
public class NpcGatherActionScript implements INpcDefaultActionScript {
	
	protected Logger log = Logger.getLogger(NpcGatherActionScript.class);
	
	public static int scriptId = 5003;		//scriptid
	
	@Override
	public int getId() {
		return scriptId;
	}

	@Override
	public void defaultAction(Player player, NPC npc){
		ManagerPool.npcManager.playerGather(player, npc);
	}

}
