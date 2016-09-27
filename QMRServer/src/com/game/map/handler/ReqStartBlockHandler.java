package com.game.map.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;

public class ReqStartBlockHandler extends Handler{

	Logger log = Logger.getLogger(ReqStartBlockHandler.class);

	public void action(){
		try{
			//格挡处理
			ManagerPool.mapManager.playerBlock((Player)this.getParameter());
		}catch(ClassCastException e){
			log.error(e,e);
		}
	}
}