package com.game.country.handler;

import org.apache.log4j.Logger;

import com.game.country.message.ReqCountryWarCarToGameMessage;
import com.game.command.Handler;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;

public class ReqCountryWarCarToGameHandler extends Handler{

	Logger log = Logger.getLogger(ReqCountryWarCarToGameHandler.class);

	public void action(){
		try{
			ReqCountryWarCarToGameMessage msg = (ReqCountryWarCarToGameMessage)this.getMessage();
			ManagerPool.countryManager.stReqCountryWarCarToGameMessage((Player)this.getParameter(),msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}