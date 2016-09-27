package com.game.marriage.handler;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.marriage.message.ReqReceiveredenvelopeToGameMessage;
import com.game.player.structs.Player;
import com.game.command.Handler;

public class ReqReceiveredenvelopeToGameHandler extends Handler{

	Logger log = Logger.getLogger(ReqReceiveredenvelopeToGameHandler.class);

	public void action(){
		try{
			ReqReceiveredenvelopeToGameMessage msg = (ReqReceiveredenvelopeToGameMessage)this.getMessage();
			ManagerPool.marriageManager.stReqReceiveredenvelopeToGameMessage((Player)this.getParameter());
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}