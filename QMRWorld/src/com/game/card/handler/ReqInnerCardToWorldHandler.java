package com.game.card.handler;

import org.apache.log4j.Logger;

import com.game.card.handler.special.ReqInnerCardToWorldSpecialHandler;
import com.game.card.message.ReqInnerCardToWorldMessage;
import com.game.command.Handler;
import com.game.server.WorldServer;

public class ReqInnerCardToWorldHandler extends Handler{

	Logger log = Logger.getLogger(ReqInnerCardToWorldHandler.class);

	public void action(){
		try{
			ReqInnerCardToWorldMessage msg = (ReqInnerCardToWorldMessage)this.getMessage();
//			CardManager.getInstance().reqInnerCardToWorld(msg);
			ReqInnerCardToWorldSpecialHandler handler = new ReqInnerCardToWorldSpecialHandler();
			handler.setMessage(msg);
			WorldServer.getInstance().getCardThread().addCommand(handler);
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}