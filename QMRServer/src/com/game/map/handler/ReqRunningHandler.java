package com.game.map.handler;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.map.message.ReqRunningMessage;
import com.game.player.structs.Player;
import com.game.command.Handler;

public class ReqRunningHandler extends Handler{

	Logger log = Logger.getLogger(ReqRunningHandler.class);

	public void action(){
		try{
			ReqRunningMessage msg = (ReqRunningMessage)this.getMessage();
			//移动处理
			ManagerPool.mapManager.playerRunning((Player)this.getParameter(), msg.getPosition(), msg.getPositions(), this.getCreateTime());
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}