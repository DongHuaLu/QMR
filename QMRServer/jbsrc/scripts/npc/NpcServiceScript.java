package scripts.npc;

import java.util.HashMap;

import org.apache.log4j.Logger;

import com.game.json.JSONserializable;
import com.game.manager.ManagerPool;
import com.game.npc.script.INpcServiceScript;
import com.game.npc.struts.NPC;
import com.game.player.structs.Player;
import com.game.script.structs.ScriptEnum;
import com.game.zones.message.ReqZoneIntoMessage;

public class NpcServiceScript implements INpcServiceScript {
	
	protected Logger log = Logger.getLogger(NpcServiceScript.class);
	
	public static int mizong_scriptId = 4006;		//迷踪幻境副本scriptid
	public static int maze_scriptId = 4007;		//迷宫scriptid
	@Override
	public int getId() {
		return ScriptEnum.NPC_ACTION;
	}

	@Override
	public void reqService(Player player, NPC npc, String parameters){
		INpcServiceScript script = (INpcServiceScript) ManagerPool.scriptManager.getScript(mizong_scriptId);
		if (script != null) {
			try {
				script.reqService(player, npc, parameters);
			} catch (Exception e) {
				log.error(e, e);
			}
		}
		
		script = (INpcServiceScript) ManagerPool.scriptManager.getScript(maze_scriptId);
		if (script != null) {
			try {
				script.reqService(player, npc, parameters);
			} catch (Exception e) {
				log.error(e, e);
			}
		}
		
		if(npc.getModelId()==10230){
			@SuppressWarnings("rawtypes")
			HashMap map = (HashMap)JSONserializable.toObject(parameters, HashMap.class);
			Object obj = map.get("type");
			if(obj!=null && (Integer)obj == 1){
				Integer zoneid = (Integer)map.get("zondId");
				if(zoneid!=null){
					ReqZoneIntoMessage smsg = new ReqZoneIntoMessage();
					smsg.setIsauto((byte) 0);
					smsg.setZoneid(zoneid);
					ManagerPool.zonesManager.stResReqZoneIntoMessage(player, smsg);
				}
			}
		}
	}
	

}
