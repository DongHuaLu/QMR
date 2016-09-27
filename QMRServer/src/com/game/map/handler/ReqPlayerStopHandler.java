package com.game.map.handler;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.map.message.ReqPlayerStopMessage;
import com.game.player.structs.Player;
import com.game.command.Handler;

public class ReqPlayerStopHandler extends Handler{

	Logger log = Logger.getLogger(ReqPlayerStopHandler.class);

	public void action(){
		try{
			ReqPlayerStopMessage msg = (ReqPlayerStopMessage)this.getMessage();

			ManagerPool.mapManager.playerStopRun((Player)this.getParameter(), msg.getPosition());
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}