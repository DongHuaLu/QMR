package com.game.country.handler;

import org.apache.log4j.Logger;

import com.game.country.message.ReqCountrysalaryToGameMessage;
import com.game.command.Handler;
import com.game.country.manager.CountryAwardManager;
import com.game.player.structs.Player;

public class ReqCountrysalaryToGameHandler extends Handler{

	Logger log = Logger.getLogger(ReqCountrysalaryToGameHandler.class);

	public void action(){
		try{
			ReqCountrysalaryToGameMessage msg = (ReqCountrysalaryToGameMessage)this.getMessage();
			CountryAwardManager.getInstance().reqCountrysalaryToGame((Player)this.getParameter(), msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}