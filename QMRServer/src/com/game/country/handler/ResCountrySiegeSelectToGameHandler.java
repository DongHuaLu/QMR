package com.game.country.handler;

import org.apache.log4j.Logger;

import com.game.country.message.ResCountrySiegeSelectToGameMessage;
import com.game.command.Handler;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;

public class ResCountrySiegeSelectToGameHandler extends Handler{

	Logger log = Logger.getLogger(ResCountrySiegeSelectToGameHandler.class);

	public void action(){
		try{
			ResCountrySiegeSelectToGameMessage msg = (ResCountrySiegeSelectToGameMessage)this.getMessage();
			ManagerPool.countryManager.stResCountrySiegeSelectToGameMessage((Player)this.getParameter(),msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}