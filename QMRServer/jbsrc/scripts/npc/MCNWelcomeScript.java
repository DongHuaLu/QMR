package scripts.npc;

import org.apache.log4j.Logger;

import com.game.npc.message.ResNpcActionMessage;
import com.game.npc.script.INpcWelcomeActionScript;
import com.game.npc.struts.NPC;
import com.game.player.structs.Player;
import com.game.utils.MessageUtil;

/**
 * npc采集行为
 * @author heyang
 *
 */
public class MCNWelcomeScript implements INpcWelcomeActionScript {
	
	protected Logger log = Logger.getLogger(NpcGatherActionScript.class);
	
	public static int scriptId = 5004;		//scriptid
	
	@Override
	public int getId() {
		return scriptId;
	}

	@Override
	public void welcome(Player player, NPC npc){
		ResNpcActionMessage msg = new ResNpcActionMessage();
		msg.setNpcId(npc.getId());
		msg.setTatget(player.getId());
		msg.setActionType(1);
		MessageUtil.tell_player_message(player, msg);
	}

}
