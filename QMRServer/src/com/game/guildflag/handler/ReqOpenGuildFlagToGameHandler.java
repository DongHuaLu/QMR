package com.game.guildflag.handler;

import org.apache.log4j.Logger;

import com.game.guildflag.message.ReqOpenGuildFlagToGameMessage;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.command.Handler;

public class ReqOpenGuildFlagToGameHandler extends Handler{

	Logger log = Logger.getLogger(ReqOpenGuildFlagToGameHandler.class);

	public void action(){
		try{
			ReqOpenGuildFlagToGameMessage msg = (ReqOpenGuildFlagToGameMessage)this.getMessage();
			ManagerPool.guildFlagManager.stReqOpenGuildFlagToGameMessage((Player)this.getParameter());
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}