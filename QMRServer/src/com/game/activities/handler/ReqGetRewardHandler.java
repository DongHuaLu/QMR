package com.game.activities.handler;

import org.apache.log4j.Logger;

import com.game.activities.message.ReqGetRewardMessage;
import com.game.command.Handler;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;

public class ReqGetRewardHandler extends Handler{

	Logger log = Logger.getLogger(ReqGetRewardHandler.class);

	public void action(){
		try{
			ReqGetRewardMessage msg = (ReqGetRewardMessage)this.getMessage();
			//领取奖励
			ManagerPool.activitiesManager.getReward((Player)this.getParameter(), msg.getActivityId(), msg.getActivityType(), msg.getSelected());
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}