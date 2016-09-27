package com.game.marriage.handler;

import org.apache.log4j.Logger;

import com.game.marriage.manager.MarriageManager;
import com.game.marriage.message.ReqUpdatedMarriageToWorldMessage;
import com.game.command.Handler;

public class ReqUpdatedMarriageToWorldHandler extends Handler{

	Logger log = Logger.getLogger(ReqUpdatedMarriageToWorldHandler.class);

	public void action(){
		try{
			ReqUpdatedMarriageToWorldMessage msg = (ReqUpdatedMarriageToWorldMessage)this.getMessage();
			MarriageManager.getInstance().stReqUpdatedMarriageToWorldMessage(msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}