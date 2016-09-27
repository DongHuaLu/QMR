package com.game.equipstreng.handler;

import org.apache.log4j.Logger;

import com.game.equipstreng.message.ReqStageUpItemToServerMessage;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.command.Handler;

public class ReqStageUpItemToServerHandler extends Handler{

	Logger log = Logger.getLogger(ReqStageUpItemToServerHandler.class);

	public void action(){
		try{
			ReqStageUpItemToServerMessage msg = (ReqStageUpItemToServerMessage)this.getMessage();
			ManagerPool.equipstrengManager.stReqStageUpItemToServerMessage((Player)this.getParameter(),msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}