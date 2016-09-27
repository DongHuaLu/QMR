package com.game.horse.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;
import com.game.horse.message.ReqGethorseInfoMessage;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;

public class ReqGethorseInfoHandler extends Handler{

	Logger log = Logger.getLogger(ReqGethorseInfoHandler.class);

	public void action(){
		try{
			ReqGethorseInfoMessage msg = (ReqGethorseInfoMessage)this.getMessage();
			ManagerPool.horseManager.stReqGethorseInfoMessage((Player)this.getParameter(),msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}