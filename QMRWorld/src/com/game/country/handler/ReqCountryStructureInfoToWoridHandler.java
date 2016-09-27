package com.game.country.handler;

import org.apache.log4j.Logger;

import com.game.country.message.ReqCountryStructureInfoToWoridMessage;
import com.game.command.Handler;
import com.game.manager.ManagerPool;

public class ReqCountryStructureInfoToWoridHandler extends Handler{

	Logger log = Logger.getLogger(ReqCountryStructureInfoToWoridHandler.class);

	public void action(){
		try{
			ReqCountryStructureInfoToWoridMessage msg = (ReqCountryStructureInfoToWoridMessage)this.getMessage();
			ManagerPool.countryManager.stReqCountryStructureInfoToWoridMessage(msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}