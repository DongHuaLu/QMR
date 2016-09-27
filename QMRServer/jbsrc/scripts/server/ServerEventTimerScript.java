package scripts.server;

import com.game.manager.ManagerPool;
import com.game.realm.manager.RealmManager;
import com.game.script.structs.ScriptEnum;
import com.game.server.script.IServerEventTimerScript;
import com.game.utils.ScriptsUtils;
import org.apache.log4j.Logger;

public class ServerEventTimerScript implements IServerEventTimerScript {

	private Logger log = Logger.getLogger(ServerEventTimerScript.class);
	public static int maze_scriptId = 4007;		//迷宫副本scriptid
	public static int baguazhen_scriptId = 4008;		//八卦阵scriptid
	public static int shuiyandaliang_scriptId = 4012;		//水淹大梁scriptid
	
	
	public ServerEventTimerScript(){
		try {
			RealmManager.getInstance().setIsopen(true);	//临时添加台湾开放境界，
		} catch (Exception e) {
			log.error(e, e);
		}
	}
	
	
	@Override
	public int getId() {
		return ScriptEnum.SERVER_EVENT;
	}

	@Override
	public void action(int serverId, String serverWeb) {
		IServerEventTimerScript script = (IServerEventTimerScript) ManagerPool.scriptManager.getScript(maze_scriptId);
		if (script != null) {
			try {
				script.action(serverId, serverWeb);
			} catch (Exception e) {
				log.error(e, e);
			}
		}
		
		script = (IServerEventTimerScript) ManagerPool.scriptManager.getScript(baguazhen_scriptId);
		if (script != null) {
			try {
				script.action(serverId, serverWeb);
			} catch (Exception e) {
				log.error(e, e);
			}
		}
		
		script = (IServerEventTimerScript) ManagerPool.scriptManager.getScript(ScriptEnum.GUILDFLAG);
		if (script != null) {
			try {
				script.action(serverId, serverWeb);
			} catch (Exception e) {
				log.error(e, e);
			}
		}
		
		//四象剑阵定时开启公告消息
		script = (IServerEventTimerScript) ManagerPool.scriptManager.getScript(4010);
		if (script != null) {
			try {
				script.action(serverId, serverWeb);
			} catch (Exception e) {
				log.error(e, e);
			}
		}
		
		//比武岛调用
		script = (IServerEventTimerScript) ManagerPool.scriptManager.getScript(ScriptEnum.BIWUDAO);
		if (script != null) {
			try {
				script.action(serverId, serverWeb);
			} catch (Exception e) {
				log.error(e, e);
			}
		}
		
		//水淹大梁调用
		script = (IServerEventTimerScript) ManagerPool.scriptManager.getScript(shuiyandaliang_scriptId);
		if (script != null) {
			try {
				script.action(serverId, serverWeb);
			} catch (Exception e) {
				log.error(e, e);
			}
		}
		
		//基础活动脚本定时调用
		ScriptsUtils.call(ScriptEnum.BASEACTIVITIES, "delayCallEveryDay0Clock");
		
		ManagerPool.realmManager.setIsopen(true);//临时添加台湾开放境界，
		
		
		
//		
//		try{
//			long timems = System.currentTimeMillis();
//			int hour = TimeUtil.getDayOfHour(timems);
//			int min = TimeUtil.getDayOfMin(timems);
//
//			if (hour == 24 && min == 30 ) {			//发布副本开启国家公告(不去解析字符串了)
//				ResZoneTeamOpenBullToClientMessage cmsg = new ResZoneTeamOpenBullToClientMessage();
//				cmsg.setZonemodelid(ZONEID);
//				MessageUtil.tell_world_message(cmsg);
//			}
//		}catch (Exception e) {
//			log.error(e, e);
//		}
	}

}
