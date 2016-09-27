package com.game.country.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;

public class ReqCountryOpenTopToGameHandler extends Handler{

	Logger log = Logger.getLogger(ReqCountryOpenTopToGameHandler.class);

	public void action(){
		try{
			//ReqCountryOpenTopToGameMessage msg = (ReqCountryOpenTopToGameMessage)this.getMessage();
			ManagerPool.countryManager.stReqCountryOpenTopToGameMessage((Player)this.getParameter());
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}