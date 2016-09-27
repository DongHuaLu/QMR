package com.game.horse.handler;

import org.apache.log4j.Logger;

import com.game.horse.message.ReqhorseLuckyRodMessage;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.command.Handler;

public class ReqhorseLuckyRodHandler extends Handler{

	Logger log = Logger.getLogger(ReqhorseLuckyRodHandler.class);

	public void action(){
		try{
			ReqhorseLuckyRodMessage msg = (ReqhorseLuckyRodMessage)this.getMessage();
			ManagerPool.horseManager.stReqhorseLuckyRodMessage((Player)this.getParameter(),msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}