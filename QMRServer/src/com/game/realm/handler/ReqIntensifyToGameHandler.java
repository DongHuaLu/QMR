package com.game.realm.handler;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.realm.message.ReqIntensifyToGameMessage;
import com.game.command.Handler;

public class ReqIntensifyToGameHandler extends Handler{

	Logger log = Logger.getLogger(ReqIntensifyToGameHandler.class);

	public void action(){
		try{
			ReqIntensifyToGameMessage msg = (ReqIntensifyToGameMessage)this.getMessage();
			ManagerPool.realmManager.stReqIntensifyToGameMessage((Player)this.getParameter(),msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}