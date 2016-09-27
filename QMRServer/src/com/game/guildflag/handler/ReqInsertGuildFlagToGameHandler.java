package com.game.guildflag.handler;

import org.apache.log4j.Logger;

import com.game.guildflag.message.ReqInsertGuildFlagToGameMessage;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.command.Handler;

public class ReqInsertGuildFlagToGameHandler extends Handler{

	Logger log = Logger.getLogger(ReqInsertGuildFlagToGameHandler.class);

	public void action(){
		try{
			ReqInsertGuildFlagToGameMessage msg = (ReqInsertGuildFlagToGameMessage)this.getMessage();
			ManagerPool.guildFlagManager.stReqInsertGuildFlagToGameMessage((Player)this.getParameter());
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}