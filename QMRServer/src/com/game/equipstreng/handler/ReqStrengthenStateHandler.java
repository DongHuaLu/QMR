package com.game.equipstreng.handler;

import org.apache.log4j.Logger;

import com.game.equipstreng.message.ReqStrengthenStateMessage;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.command.Handler;

public class ReqStrengthenStateHandler extends Handler{

	Logger log = Logger.getLogger(ReqStrengthenStateHandler.class);

	public void action(){
		try{
			ReqStrengthenStateMessage msg = (ReqStrengthenStateMessage)this.getMessage();
			ManagerPool.equipstrengManager.stReqStrengthenStateMessage((Player)this.getParameter(),msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}