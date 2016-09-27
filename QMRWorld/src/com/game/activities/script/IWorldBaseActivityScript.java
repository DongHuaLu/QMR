package com.game.activities.script;

import com.game.activities.bean.ActivityInfo;
import com.game.player.structs.Player;
import com.game.script.IScript;
import java.util.List;

public interface IWorldBaseActivityScript extends IScript {

	/**
	 * 发送活动信息
	 *
	 * @param player
	 */
	public void sendActivitiesInfo(Player player, List<ActivityInfo> activities, boolean force);

	/**
	 * 领取奖励
	 *
	 * @param ac the value of ac
	 */
	public void getReward(Player player, ActivityInfo ac, int selected);
}
