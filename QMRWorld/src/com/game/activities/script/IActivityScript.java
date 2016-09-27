package com.game.activities.script;

import com.game.player.structs.Player;
import com.game.script.IScript;

public interface IActivityScript extends IScript {
	/**
	 * 领取奖励
	 */
	public void getReward(Player player);
	
	/**
	 * 获得领取描述
	 * @return
	 */
	public String getDescribe(Player player);
	
	/**
	 * 获得奖励描述
	 * @return
	 */
	public String getRewardInfo(Player player);
	
	/**
	 * 获取领取状态
	 * @return 0-已领取 1-可领取 2-不可领取 3-到世界服判断   4-6公测王帮活动专用 勿占
	 */
	public int getState(Player player);
	
	
	/**
	 * 活动是否开启
	 * @return
	 */
	public boolean isOpen(Player player);
	
	/**
	 * 活动剩余时间
	 * @param player
	 * @return
	 */
	public int getDuration(Player player);
		
	
}
