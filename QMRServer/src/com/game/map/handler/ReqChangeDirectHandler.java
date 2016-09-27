package com.game.map.handler;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.map.message.ReqChangeDirectMessage;
import com.game.player.structs.Player;
import com.game.command.Handler;

public class ReqChangeDirectHandler extends Handler{

	Logger log = Logger.getLogger(ReqChangeDirectHandler.class);

	public void action(){
		try{
			ReqChangeDirectMessage msg = (ReqChangeDirectMessage)this.getMessage();
			//变化方向
			ManagerPool.mapManager.playerChangeDirection((Player)this.getParameter(), msg.getDir());
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}