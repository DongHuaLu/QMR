package com.game.marriage.handler;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.marriage.message.ReqRedEnvelopeToGameMessage;
import com.game.player.structs.Player;
import com.game.command.Handler;

public class ReqRedEnvelopeToGameHandler extends Handler{

	Logger log = Logger.getLogger(ReqRedEnvelopeToGameHandler.class);

	public void action(){
		try{
			ReqRedEnvelopeToGameMessage msg = (ReqRedEnvelopeToGameMessage)this.getMessage();
			ManagerPool.marriageManager.stReqRedEnvelopeToGameMessage((Player)this.getParameter());
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}