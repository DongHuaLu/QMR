package scripts.npc;

import java.util.List;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.npc.script.INpcEventTimerScript;
import com.game.npc.struts.NPC;

/**
 * npc出现
 * @author heyang
 *
 */
public class NpcAppearEventScript implements INpcEventTimerScript {
	
	protected Logger log = Logger.getLogger(NpcAppearEventScript.class);
	
	public static int scriptId = 5001;		//scriptid

	@Override
	public int getId() {
		return scriptId;
	}

	@Override
	public void action(NPC npc, List<Object> parameters){
		ManagerPool.npcManager.showNpc(npc);
	}

}
