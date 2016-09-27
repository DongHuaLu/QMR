package com.game.player.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;
import com.game.manager.ManagerPool;
import com.game.player.message.ReqLocalReviveMessage;
import com.game.player.structs.Player;

public class ReqLocalReviveHandler extends Handler{

	Logger log = Logger.getLogger(ReqLocalReviveHandler.class);

	public void action(){
		try{
			ReqLocalReviveMessage msg = (ReqLocalReviveMessage)this.getMessage();
			//原地复活 1 
			ManagerPool.playerManager.reviveReady((Player)this.getParameter(), 1  ,msg.getItemmodelid(),msg.getType());
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}