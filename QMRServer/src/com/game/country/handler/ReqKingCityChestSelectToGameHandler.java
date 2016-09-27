package com.game.country.handler;

import org.apache.log4j.Logger;

import com.game.country.message.ReqKingCityChestSelectToGameMessage;
import com.game.command.Handler;
import com.game.country.manager.CountryAwardManager;
import com.game.player.structs.Player;

public class ReqKingCityChestSelectToGameHandler extends Handler{

	Logger log = Logger.getLogger(ReqKingCityChestSelectToGameHandler.class);

	public void action(){
		try{
			ReqKingCityChestSelectToGameMessage msg = (ReqKingCityChestSelectToGameMessage)this.getMessage();
			CountryAwardManager.getInstance().reqKingCityChestSelectToGame((Player)this.getParameter(), msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}