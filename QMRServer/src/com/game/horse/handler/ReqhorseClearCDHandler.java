package com.game.horse.handler;

import org.apache.log4j.Logger;

import com.game.horse.message.ReqhorseClearCDMessage;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.command.Handler;

public class ReqhorseClearCDHandler extends Handler{

	Logger log = Logger.getLogger(ReqhorseClearCDHandler.class);

	public void action(){
		try{
			ReqhorseClearCDMessage msg = (ReqhorseClearCDMessage)this.getMessage();
			ManagerPool.horseManager.stReqhorseClearCDMessage((Player)this.getParameter(),msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}