package com.game.map.handler;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.map.message.ReqChangeMapByMoveMessage;
import com.game.player.structs.Player;
import com.game.command.Handler;

public class ReqChangeMapByMoveHandler extends Handler{

	Logger log = Logger.getLogger(ReqChangeMapByMoveHandler.class);

	public void action(){
		try{
			ReqChangeMapByMoveMessage msg = (ReqChangeMapByMoveMessage)this.getMessage();

			ManagerPool.mapManager.changeMapByMove((Player)this.getParameter(), msg.getLine(), msg.getTranId());
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}