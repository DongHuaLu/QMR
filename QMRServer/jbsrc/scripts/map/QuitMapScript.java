package scripts.map;

import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.game.data.bean.Q_clone_activityBean;
import com.game.data.bean.Q_task_mainBean;
import com.game.manager.ManagerPool;
import com.game.map.script.IQuitMapScript;
import com.game.map.structs.Map;
import com.game.monster.structs.Monster;
import com.game.player.structs.Player;
import com.game.script.structs.ScriptEnum;
import com.game.server.impl.MServer;
import com.game.zones.structs.ZoneContext;

public class QuitMapScript implements IQuitMapScript {

	private Logger log = Logger.getLogger(QuitMapScript.class);
	private static int jiaochang_scriptid = 4003;
	private static int shuiyandaliang = 4012;//水淹大梁副本
	@Override
	public int getId() {
		return ScriptEnum.QUIT_MAP;
	}

	@Override
	public void onQuitMap(Player player, Map map) {
		try{
			List<Q_task_mainBean> tasks = ManagerPool.dataManager.q_task_mainContainer.getList();
			Iterator<Q_task_mainBean> iter = tasks.iterator();
			while (iter.hasNext()) {
				Q_task_mainBean q_task_mainBean = (Q_task_mainBean) iter.next();
				if(q_task_mainBean.getQ_brush_mon_map()==map.getMapModelid()){
					if(ManagerPool.taskManager.getMainTaskState(player, q_task_mainBean.getQ_taskid())==2){
						List<Monster> monsters = ManagerPool.taskManager.getStoryMonster(player, q_task_mainBean.getQ_task_brush_monid());
						for (Monster monster : monsters) {
							ManagerPool.monsterManager.removeMonster(monster);
						}
					}
				}
			}
		} catch (Exception e) {
			log.error(e, e);
		}
		
		IQuitMapScript script = (IQuitMapScript) ManagerPool.scriptManager.getScript(jiaochang_scriptid);
		if (script != null) {
			try {
				script.onQuitMap(player, map);
			} catch (Exception e) {
				log.error(e, e);
			}
		}
		
		
		
		
		try{
			if(map.isCopy() == true){
				script = (IQuitMapScript) ManagerPool.scriptManager.getScript(shuiyandaliang);
				if (script != null) {
					try {
						script.onQuitMap(player, map);
					} catch (Exception e) {
						log.error(e, e);
					}
				}

			//-------------------------------退出副本基本处理------------------------
			Q_clone_activityBean bean = ManagerPool.dataManager.q_clone_activityContainer.getMap().get(map.getZoneModelId());
				if(bean!=null && (bean.getQ_zone_type()==1 || (bean.getQ_zone_type()==2 && bean.getQ_id()==3))){
					MServer server = ManagerPool.zonesManager.getmServers().get(map.getZoneId());
					server.setDelete(true);
				}
				else if(bean!=null &&  (bean.getQ_zone_type()==3 )){		//多人活动副本
					ZoneContext zcon = ManagerPool.zonesManager.getContexts().get(map.getZoneId());
					int num = ManagerPool.zonesManager.checkZonePlayerNum(zcon);
					if (num <= 1) {
						zcon.setCktime(System.currentTimeMillis());
					}
				}
			}
			
		} catch (Exception e) {
			log.error(e, e);
		}
		
//		try{
//			Exception exception = new Exception();
//			StackTraceElement[] stackTrace = exception.getStackTrace();
//			for (StackTraceElement stackTraceElement : stackTrace) {
//				if (stackTraceElement.getMethodName().equals("stop")) {
//					PlayerManager.getInstance().updatePlayerSync(player);
//				}
//			}
//		} catch (Exception e) {
//			log.error(e, e);
//		}
	}

}
