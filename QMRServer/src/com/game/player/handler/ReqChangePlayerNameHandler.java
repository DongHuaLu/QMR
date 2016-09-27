package com.game.player.handler;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.player.message.ReqChangePlayerNameMessage;
import com.game.player.structs.Player;
import com.game.command.Handler;

public class ReqChangePlayerNameHandler extends Handler{

	Logger log = Logger.getLogger(ReqChangePlayerNameHandler.class);

	public void action(){
		try{
			ReqChangePlayerNameMessage msg = (ReqChangePlayerNameMessage)this.getMessage();
			ManagerPool.playerManager.stReqChangePlayerNameMessage((Player)this.getParameter(),msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}