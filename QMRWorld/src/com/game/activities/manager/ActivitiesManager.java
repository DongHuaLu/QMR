package com.game.activities.manager;

import java.util.List;

import org.apache.log4j.Logger;

import com.game.activities.bean.ActivityInfo;
import com.game.activities.script.IWorldBaseActivityScript;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.script.structs.ScriptEnum;

public class ActivitiesManager {

	private Logger log = Logger.getLogger(ActivitiesManager.class);
	private static Object obj = new Object();
	//后台管理类实例
	private static ActivitiesManager manager;

	private ActivitiesManager() {
	}

	public static ActivitiesManager getInstance() {
		synchronized (obj) {
			if (manager == null) {
				manager = new ActivitiesManager();
			}
		}
		return manager;
	}

	//发送活动信息   
	public void sendActivitiesInfo(Player player, List<ActivityInfo> activities, boolean force) {  //TODO 每日0点的排行榜前3 put key toplevel value 2_daynum
		IWorldBaseActivityScript script = (IWorldBaseActivityScript) ManagerPool.scriptManager.getScript(ScriptEnum.BASEACTIVITIES);
		if (script != null) {
			try {
				script.sendActivitiesInfo(player, activities, force);
			} catch (Exception e) {
				log.error(e, e);
			}
		}else{
			log.error("没有基础活动脚本");
		}
	}

	//得到奖励
	public void getReward(Player player, ActivityInfo ac, int selected) {
		IWorldBaseActivityScript script = (IWorldBaseActivityScript) ManagerPool.scriptManager.getScript(ScriptEnum.BASEACTIVITIES);
		if (script != null) {
			try {
				script.getReward(player, ac, selected);
			} catch (Exception e) {
				log.error(e, e);
			}
		}else{
			log.error("没有基础活动脚本");
		}
	}
}
