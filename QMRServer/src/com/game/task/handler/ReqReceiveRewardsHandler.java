package com.game.task.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;
import com.game.player.structs.Player;
import com.game.task.manager.TaskManager;

public class ReqReceiveRewardsHandler extends Handler{

	Logger log = Logger.getLogger(ReqReceiveRewardsHandler.class);

	public void action(){
		try{
			TaskManager.getInstance().receiveRewardsAbleArea((Player)getParameter());
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}