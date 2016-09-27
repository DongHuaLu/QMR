package com.game.card.handler.special;

import com.game.card.manager.CardManager;
import org.apache.log4j.Logger;

import com.game.card.message.ReqInnerCardToWorldMessage;
import com.game.command.Handler;

public class ReqInnerCardToWorldSpecialHandler extends Handler{

	Logger log = Logger.getLogger(ReqInnerCardToWorldSpecialHandler.class);

	public void action(){
		try{
			ReqInnerCardToWorldMessage msg = (ReqInnerCardToWorldMessage)this.getMessage();
			CardManager.getInstance().reqInnerCardToWorld(msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}