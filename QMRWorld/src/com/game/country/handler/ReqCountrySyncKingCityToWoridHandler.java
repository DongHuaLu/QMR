package com.game.country.handler;

import org.apache.log4j.Logger;

import com.game.country.message.ReqCountrySyncKingCityToWoridMessage;
import com.game.command.Handler;
import com.game.manager.ManagerPool;

public class ReqCountrySyncKingCityToWoridHandler extends Handler{

	Logger log = Logger.getLogger(ReqCountrySyncKingCityToWoridHandler.class);

	public void action(){
		try{
			ReqCountrySyncKingCityToWoridMessage msg = (ReqCountrySyncKingCityToWoridMessage)this.getMessage();
			ManagerPool.countryManager.stReqCountrySyncKingCityToWoridMessage(msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}