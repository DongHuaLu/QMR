package com.game.realm.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.realm.message.ReqBreakthroughToGameMessage;

public class ReqBreakthroughToGameHandler extends Handler{

	Logger log = Logger.getLogger(ReqBreakthroughToGameHandler.class);

	public void action(){
		try{
			ReqBreakthroughToGameMessage msg = (ReqBreakthroughToGameMessage)this.getMessage();
			ManagerPool.realmManager.stReqBreakthroughToGameMessage((Player)this.getParameter(),msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}