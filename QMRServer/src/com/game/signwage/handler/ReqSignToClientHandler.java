package com.game.signwage.handler;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.signwage.message.ReqSignToClientMessage;
import com.game.command.Handler;

public class ReqSignToClientHandler extends Handler{

	Logger log = Logger.getLogger(ReqSignToClientHandler.class);

	public void action(){
		try{
			ReqSignToClientMessage msg = (ReqSignToClientMessage)this.getMessage();
			ManagerPool.signWageManager.openSign((Player)this.getParameter());
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}