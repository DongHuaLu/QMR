package com.game.equipstreng.handler;

import org.apache.log4j.Logger;

import com.game.equipstreng.message.ReqStrengItemToServerMessage;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.command.Handler;

public class ReqStrengItemToServerHandler extends Handler{

	Logger log = Logger.getLogger(ReqStrengItemToServerHandler.class);

	public void action(){
		try{
			ReqStrengItemToServerMessage msg = (ReqStrengItemToServerMessage)this.getMessage();
			ManagerPool.equipstrengManager.stReqStrengItemToServerMessage((Player)this.getParameter(),msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}