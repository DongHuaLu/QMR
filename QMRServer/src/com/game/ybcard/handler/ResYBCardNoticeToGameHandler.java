package com.game.ybcard.handler;

import org.apache.log4j.Logger;

import com.game.ybcard.message.ResYBCardNoticeToGameMessage;
import com.game.command.Handler;
import com.game.manager.ManagerPool;

public class ResYBCardNoticeToGameHandler extends Handler{

	Logger log = Logger.getLogger(ResYBCardNoticeToGameHandler.class);

	public void action(){
		try{
			ResYBCardNoticeToGameMessage msg = (ResYBCardNoticeToGameMessage)this.getMessage();
			ManagerPool.ybcardManager.stResYBCardNoticeToGameMessage(msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}