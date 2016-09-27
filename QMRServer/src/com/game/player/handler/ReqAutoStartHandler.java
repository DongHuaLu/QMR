package com.game.player.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;

public class ReqAutoStartHandler extends Handler{

	Logger log = Logger.getLogger(ReqAutoStartHandler.class);

	public void action(){
		try{
			//开始挂机
			ManagerPool.playerManager.startAuto((Player)getParameter());
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}