package com.game.signwage.handler;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.signwage.message.ReqClickSignToClientMessage;
import com.game.command.Handler;

public class ReqClickSignToClientHandler extends Handler{

	Logger log = Logger.getLogger(ReqClickSignToClientHandler.class);

	public void action(){
		try{
			ReqClickSignToClientMessage msg = (ReqClickSignToClientMessage)this.getMessage();
			ManagerPool.signWageManager.setSign((Player)this.getParameter());
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}