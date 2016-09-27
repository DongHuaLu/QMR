package com.game.horse.handler;

import org.apache.log4j.Logger;

import com.game.horse.message.ReqChangeHorseMessage;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.command.Handler;

public class ReqChangeHorseHandler extends Handler{

	Logger log = Logger.getLogger(ReqChangeHorseHandler.class);

	public void action(){
		try{
			ReqChangeHorseMessage msg = (ReqChangeHorseMessage)this.getMessage();
			ManagerPool.horseManager.stReqChangeHorseMessage((Player)this.getParameter(),msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}