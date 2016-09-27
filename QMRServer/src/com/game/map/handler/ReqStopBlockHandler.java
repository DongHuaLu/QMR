package com.game.map.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;

public class ReqStopBlockHandler extends Handler{

	Logger log = Logger.getLogger(ReqStopBlockHandler.class);

	public void action(){
		try{
			//停止格挡处理
			ManagerPool.mapManager.playerStopBlock((Player)this.getParameter());
		}catch(ClassCastException e){
			log.error(e,e);
		}
	}
}