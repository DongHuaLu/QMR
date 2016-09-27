package com.game.player.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;

public class ReqNonageTimeHandler extends Handler{

	Logger log = Logger.getLogger(ReqNonageTimeHandler.class);

	public void action(){
		try{
			//请求防沉迷时间
			ManagerPool.playerManager.getNonageTime((Player)this.getParameter());
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}