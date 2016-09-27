package com.game.activities.handler;

import org.apache.log4j.Logger;

import com.game.activities.manager.ActivitiesManager;
import com.game.activities.message.ReqActivitiesInfoToWorldMessage;
import com.game.command.Handler;
import com.game.player.manager.PlayerManager;
import com.game.player.structs.Player;

public class ReqActivitiesInfoToWorldHandler extends Handler{

	Logger log = Logger.getLogger(ReqActivitiesInfoToWorldHandler.class);

	public void action(){
		try{
			ReqActivitiesInfoToWorldMessage msg = (ReqActivitiesInfoToWorldMessage)this.getMessage();
			Player player = PlayerManager.getInstance().getPlayer(Long.valueOf(msg.getPlayerid()));
			ActivitiesManager.getInstance().sendActivitiesInfo(player, msg.getActivities(), msg.getForce()==(byte)1? true:false);
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}