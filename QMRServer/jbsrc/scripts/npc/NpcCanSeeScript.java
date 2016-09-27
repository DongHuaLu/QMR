package scripts.npc;

import org.apache.log4j.Logger;

import com.game.npc.script.INpcCanSeeScript;
import com.game.npc.struts.NPC;
import com.game.player.structs.Player;
import com.game.script.structs.ScriptEnum;

public class NpcCanSeeScript implements INpcCanSeeScript {
	
	protected Logger log = Logger.getLogger(NpcCanSeeScript.class);

	@Override
	public int getId() {
		return ScriptEnum.NPC_SEE;
	}

	@Override
	public boolean cansee(Player player, NPC npc){
		if(player.getShowSet().contains(String.valueOf(npc.getModelId()))){
			return true;
		}else if(player.getHideSet().contains(String.valueOf(npc.getModelId()))){
			return false;
		}
		return npc.isShow();
	}

}
