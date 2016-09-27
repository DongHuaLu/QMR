package com.game.map.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;
import com.game.manager.ManagerPool;
import com.game.map.message.ReqEnterMapMessage;
import com.game.player.structs.Player;

public class ReqEnterMapHandler extends Handler{

	Logger log = Logger.getLogger(ReqEnterMapHandler.class);

	public void action(){
		try{
			ReqEnterMapMessage msg = (ReqEnterMapMessage)this.getMessage();
			
			ManagerPool.mapManager.enterMap((Player)this.getParameter(), msg.getWidth(), msg.getHeight());
		}catch(Exception e){
			log.error(e, e);
		}
	}
}