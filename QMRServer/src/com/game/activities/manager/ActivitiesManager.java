package com.game.activities.manager;

import com.game.activities.script.IBaseActivityScript;
import com.game.activities.structs.ScriptInfo;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.script.structs.ScriptEnum;
import com.game.utils.TimeUtil;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

public class ActivitiesManager {

//	public static HashMap<String, Integer> activities = new HashMap<String, Integer>();
	public static List<ScriptInfo> activities = new ArrayList<ScriptInfo>();
	protected Logger log = Logger.getLogger(ActivitiesManager.class);
	private static Object obj = new Object();
	//玩家管理类实例
	private static ActivitiesManager manager;

	private ActivitiesManager() {

		//新手激活码
		addActivity(20, 20, 2080);
		//累积充值 每X领取
		addActivity(29, 21, 2081);  //500
		addActivity(30, 23, 2083);  //2000
		//达到领取 
		addActivity(21, 22, 2082);  //1000
		addActivity(21, 24, 2084);  //5000
		addActivity(21, 25, 2085);  //10000
		//等级 坐骑 武功排名
		addActivity(22, 26, 2086);
		addActivity(23, 27, 2087);
		addActivity(24, 28, 2088);
		//公测王帮
		addActivity(25, 29, 2089);
		//全民练级
//		addActivity(26, 30, 2090);
		//商城打折
		addActivity(27, 31, 2093);
		//VIP奖励
//		addActivity(28, 32, 2094);
//		//悬赏等级 坐骑 武功 前三
//		addActivity(29, 33, 2093);
//		addActivity(30, 34, 2094);
//		addActivity(31, 35, 2095);
		//封测礼金
		addActivity(32, 36, 2095);

//国庆活动
		//连续登陆
		addActivity(3, 3, 2041);  //1
		addActivity(3, 4, 2042);  //2
		addActivity(3, 5, 2043);  //3
		addActivity(3, 6, 2044);  //4
		addActivity(3, 7, 2045);  //5
		addActivity(3, 8, 2046);  //6
		addActivity(3, 9, 2047);  //7
		addActivity(3, 10, 2048); //8
		addActivity(3, 11, 2049); //9
		addActivity(3, 12, 2050); //10
		addActivity(3, 13, 2040); //10+
		//坐骑升阶触手可及
//		addActivity(4, 40, 2100);  //5-6
//		addActivity(4, 41, 2101);  //6-7
//		addActivity(4, 42, 2102);  //7-8
//		addActivity(4, 43, 2103);  //8-9
//		addActivity(4, 44, 2104);  //9-10
		//单日累计充值送
//		addActivity(5, 45, 2105);  //500
//		addActivity(6, 46, 2106);  //2000
//		addActivity(7, 47, 2107);  //5000
//		addActivity(8, 48, 2108);  //10000
//		addActivity(9, 49, 2109);  //100000
	}

	public static ActivitiesManager getInstance() {
		synchronized (obj) {
			if (manager == null) {
				manager = new ActivitiesManager();
			}
		}
		return manager;
	}

	private void addActivity(int type, int id, int script) {
		ScriptInfo info = new ScriptInfo();
		info.setType(type);
		info.setId(id);
		info.setScript(script);
		activities.add(info);
	}

	/**
	 * 连续登陆
	 *
	 * @param player
	 */
	public void nextLogin(Player player) {
		long now = System.currentTimeMillis();
		int days = TimeUtil.getBetweenDays(now, player.getLastAddLoginTime());
		if (days > 0) {
			player.setLastAddLoginTime(now);
			if (days == 1) {
				player.setLoginTimes(player.getLoginTimes() + 1);
				if (player.getLoginTimes() > player.getMaxLoginTimes()) {
					player.setMaxLoginTimes(player.getLoginTimes());
				}
			} else if (days > 1) {
				player.setLoginTimes(1);
				if (player.getLoginTimes() > player.getMaxLoginTimes()) {
					player.setMaxLoginTimes(player.getLoginTimes());
				}
			}
		}
	}

	/**
	 * 发送活动信息
	 *
	 * @param player
	 */
	public void sendActivitiesInfo(Player player, boolean force) {
		IBaseActivityScript script = (IBaseActivityScript) ManagerPool.scriptManager.getScript(ScriptEnum.BASEACTIVITIES);
		if (script != null) {
			script.sendActivitiesInfo(player, force);
		}
	}

	/**
	 * 领取活动奖励
	 *
	 * @param player
	 * @param id
	 * @param type
	 */
	public void getReward(Player player, int id, int type, int selected) {
		IBaseActivityScript script = (IBaseActivityScript) ManagerPool.scriptManager.getScript(ScriptEnum.BASEACTIVITIES);
		if (script != null) {
			script.getReward(player, id, type, selected);
		}
	}
}
