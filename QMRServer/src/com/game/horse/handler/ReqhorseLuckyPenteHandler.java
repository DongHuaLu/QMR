package com.game.horse.handler;

import org.apache.log4j.Logger;

import com.game.horse.message.ReqhorseLuckyPenteMessage;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.command.Handler;

public class ReqhorseLuckyPenteHandler extends Handler{

	Logger log = Logger.getLogger(ReqhorseLuckyPenteHandler.class);

	public void action(){
		try{
			ReqhorseLuckyPenteMessage msg = (ReqhorseLuckyPenteMessage)this.getMessage();
			ManagerPool.horseManager.stReqhorseLuckyPenteMessage((Player)this.getParameter(),msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}