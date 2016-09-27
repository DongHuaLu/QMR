package com.game.stalls.handler;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.stalls.message.ReqStallsOffShelfMessage;
import com.game.command.Handler;

public class ReqStallsOffShelfHandler extends Handler{

	Logger log = Logger.getLogger(ReqStallsOffShelfHandler.class);

	public void action(){
		try{
			ReqStallsOffShelfMessage msg = (ReqStallsOffShelfMessage)this.getMessage();
			ManagerPool.stallsManager.stReqStallsOffShelfMessage((Player)this.getParameter(),msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}