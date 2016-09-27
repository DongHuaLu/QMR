package com.game.marriage.handler;

import org.apache.log4j.Logger;

import com.game.marriage.manager.MarriageManager;
import com.game.marriage.message.ReqDeleteMarriageToWorldMessage;
import com.game.command.Handler;

public class ReqDeleteMarriageToWorldHandler extends Handler{

	Logger log = Logger.getLogger(ReqDeleteMarriageToWorldHandler.class);

	public void action(){
		try{
			ReqDeleteMarriageToWorldMessage msg = (ReqDeleteMarriageToWorldMessage)this.getMessage();
			MarriageManager.getInstance().stReqDeleteMarriageToWorldMessage(msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}