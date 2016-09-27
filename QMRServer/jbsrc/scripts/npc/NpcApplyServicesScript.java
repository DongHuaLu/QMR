package scripts.npc;

import com.game.manager.ManagerPool;
import com.game.npc.bean.ServiceInfo;
import com.game.npc.script.INpcApplyServicesScript;
import com.game.npc.struts.NPC;
import com.game.player.structs.Player;
import com.game.script.structs.ScriptEnum;
import java.util.List;
import org.apache.log4j.Logger;

public class NpcApplyServicesScript implements INpcApplyServicesScript {
	
	private Logger log = Logger.getLogger(NpcApplyServicesScript.class);
	public static int mizong_scriptId = 4006;		//迷踪幻境副本scriptid
	public static int maze_scriptId = 4007;		//迷宫scriptid
	@Override
	public int getId() {
		return ScriptEnum.NPC_SERVICES;
	}

	@Override
	public void applyServices(Player player, NPC npc, List<ServiceInfo> infos) {
		INpcApplyServicesScript script = (INpcApplyServicesScript) ManagerPool.scriptManager.getScript(mizong_scriptId);
		if (script != null) {
			try {
				script.applyServices(player, npc, infos);
			} catch (Exception e) {
				log.error(e, e);
			}
		}
		
		script = (INpcApplyServicesScript) ManagerPool.scriptManager.getScript(maze_scriptId);
		if (script != null) {
			try {
				script.applyServices(player, npc, infos);
			} catch (Exception e) {
				log.error(e, e);
			}
		}
		

		
		
		if(npc.getModelId()==10230){
//			ServiceInfo info = new ServiceInfo();
//			info.setServiceId(20);
//			info.setServiceName("进入校场");
//			info.setServiceParameter("{\"type\": 1, \"zondId\": 3}");
//			infos.add(info);
			
//			info = new ServiceInfo();
//			info.setServiceId(20);
//			info.setServiceName("进入迷踪幻境");
//			info.setServiceParameter("{\"type\": 1, \"zondId\": 5}");
//			infos.add(info);
			
//			info = new ServiceInfo();
//			info.setServiceId(20);
//			info.setServiceName("进入迷宫");
//			info.setServiceParameter("{\"type\": 1, \"zondId\": 7}");
//			infos.add(info);
		}
	}

}
