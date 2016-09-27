package com.game.plugset.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;

public class ReqPlugBackToCityHandler extends Handler{

	Logger log = Logger.getLogger(ReqPlugBackToCityHandler.class);

	public void action(){
		try{
			//ReqPlugBackToCityMessage msg = (ReqPlugBackToCityMessage)this.getMessage();
			ManagerPool.plugSetManager.stPlugBackToCity((Player)this.getParameter());
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}