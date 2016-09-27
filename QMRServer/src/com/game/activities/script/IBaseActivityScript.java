package com.game.activities.script;

import com.game.player.structs.Player;
import com.game.script.IScript;

public interface IBaseActivityScript extends IScript {

	/**
	 * 发送活动信息
	 *
	 * @param player
	 */
	public void sendActivitiesInfo(Player player, boolean force);

	/**
	 * 领取奖励
	 */
	public void getReward(Player player, int id, int type, int selected);
	
	/**
	 * 检查活动是否存在
	 *
	 * @param player 玩家
	 * @param activitiesid 活动id
	 * @return boolean
	 */
	public boolean checkActivities(Player player, int activitiesid);
}
