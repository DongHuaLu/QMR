package com.game.activities.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;

public class ReqActivitiesInfoHandler extends Handler{

	Logger log = Logger.getLogger(ReqActivitiesInfoHandler.class);

	public void action(){
		try{
			//获取活动信息
			ManagerPool.activitiesManager.sendActivitiesInfo((Player)this.getParameter(), true);
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}