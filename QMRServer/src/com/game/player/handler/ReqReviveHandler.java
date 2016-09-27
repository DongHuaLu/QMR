package com.game.player.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;

public class ReqReviveHandler extends Handler{

	Logger log = Logger.getLogger(ReqReviveHandler.class);
	
	public void action(){
		try{
			//回城复活
			ManagerPool.playerManager.revive((Player)getParameter(), 2);
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}