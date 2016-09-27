package com.game.collect.handler;

import org.apache.log4j.Logger;

import com.game.collect.message.ReqSubmitFragByTypeMessage;
import com.game.command.Handler;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;

public class ReqSubmitFragByTypeHandler extends Handler{

	Logger log = Logger.getLogger(ReqSubmitFragByTypeHandler.class);

	public void action(){
		try{
			ReqSubmitFragByTypeMessage msg = (ReqSubmitFragByTypeMessage)this.getMessage();
			ManagerPool.collectManager.submitFrag(msg.getCollecttype(), (Player)getParameter());
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}