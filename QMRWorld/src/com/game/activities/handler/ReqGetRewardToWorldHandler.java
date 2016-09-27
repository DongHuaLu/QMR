package com.game.activities.handler;

import org.apache.log4j.Logger;

import com.game.activities.manager.ActivitiesManager;
import com.game.activities.message.ReqGetRewardToWorldMessage;
import com.game.command.Handler;
import com.game.player.manager.PlayerManager;
import com.game.player.structs.Player;

public class ReqGetRewardToWorldHandler extends Handler{

	Logger log = Logger.getLogger(ReqGetRewardToWorldHandler.class);

	public void action(){
		try{
			ReqGetRewardToWorldMessage msg = (ReqGetRewardToWorldMessage)this.getMessage();
			Player player = PlayerManager.getInstance().getPlayer(Long.valueOf(msg.getPlayerid()));
			ActivitiesManager.getInstance().getReward(player, msg.getActivityinfo(), msg.getSelected());
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}