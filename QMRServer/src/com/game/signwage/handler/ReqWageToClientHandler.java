package com.game.signwage.handler;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.signwage.message.ReqWageToClientMessage;
import com.game.command.Handler;

public class ReqWageToClientHandler extends Handler{

	Logger log = Logger.getLogger(ReqWageToClientHandler.class);

	public void action(){
		try{
			ReqWageToClientMessage msg = (ReqWageToClientMessage)this.getMessage();
			ManagerPool.signWageManager.openWage((Player)this.getParameter());
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}