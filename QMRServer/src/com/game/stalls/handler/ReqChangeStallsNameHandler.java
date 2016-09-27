package com.game.stalls.handler;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.stalls.message.ReqChangeStallsNameMessage;
import com.game.command.Handler;

public class ReqChangeStallsNameHandler extends Handler{

	Logger log = Logger.getLogger(ReqChangeStallsNameHandler.class);

	public void action(){
		try{
			ReqChangeStallsNameMessage msg = (ReqChangeStallsNameMessage)this.getMessage();
			ManagerPool.stallsManager.stReqChangeStallsNameMessage((Player)this.getParameter(),msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}