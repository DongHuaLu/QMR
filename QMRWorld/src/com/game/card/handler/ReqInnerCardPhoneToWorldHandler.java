package com.game.card.handler;

import com.game.card.manager.CardManager;
import org.apache.log4j.Logger;

import com.game.card.message.ReqInnerCardPhoneToWorldMessage;
import com.game.command.Handler;

public class ReqInnerCardPhoneToWorldHandler extends Handler{

	Logger log = Logger.getLogger(ReqInnerCardPhoneToWorldHandler.class);

	public void action(){
		try{
			ReqInnerCardPhoneToWorldMessage msg = (ReqInnerCardPhoneToWorldMessage)this.getMessage();
			CardManager.getInstance().reqInnerCardPhoneToWorld(msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}