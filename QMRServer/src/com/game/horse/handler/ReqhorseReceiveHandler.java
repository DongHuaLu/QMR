package com.game.horse.handler;

import org.apache.log4j.Logger;

import com.game.horse.message.ReqhorseReceiveMessage;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.command.Handler;

public class ReqhorseReceiveHandler extends Handler{

	Logger log = Logger.getLogger(ReqhorseReceiveHandler.class);

	public void action(){
		try{
			ReqhorseReceiveMessage msg = (ReqhorseReceiveMessage)this.getMessage();
			ManagerPool.horseManager.stReqhorseReceiveMessage((Player)this.getParameter(),msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}