package com.game.country.handler;

import org.apache.log4j.Logger;

import com.game.country.message.ResCountrySyncKingCityToGameMessage;
import com.game.command.Handler;
import com.game.manager.ManagerPool;

public class ResCountrySyncKingCityToGameHandler extends Handler{

	Logger log = Logger.getLogger(ResCountrySyncKingCityToGameHandler.class);

	public void action(){
		try{
			ResCountrySyncKingCityToGameMessage msg = (ResCountrySyncKingCityToGameMessage)this.getMessage();
			ManagerPool.countryManager.stResCountrySyncKingCityToGameMessage(msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}