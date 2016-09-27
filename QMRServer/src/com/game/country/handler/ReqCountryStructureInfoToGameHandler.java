package com.game.country.handler;

import org.apache.log4j.Logger;

import com.game.country.message.ReqCountryStructureInfoToGameMessage;
import com.game.command.Handler;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;

public class ReqCountryStructureInfoToGameHandler extends Handler{

	Logger log = Logger.getLogger(ReqCountryStructureInfoToGameHandler.class);

	public void action(){
		try{
			ReqCountryStructureInfoToGameMessage msg = (ReqCountryStructureInfoToGameMessage)this.getMessage();
			ManagerPool.countryManager.stReqCountryStructureInfoToGameMessage((Player)this.getParameter());
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}