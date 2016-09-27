package com.game.player.handler;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.player.message.ReqNonageRegisterToWorldMessage;
import com.game.command.Handler;

public class ReqNonageRegisterToWorldHandler extends Handler{

	Logger log = Logger.getLogger(ReqNonageRegisterToWorldHandler.class);

	public void action(){
		try{
			ReqNonageRegisterToWorldMessage msg = (ReqNonageRegisterToWorldMessage)this.getMessage();
			//防沉迷注册
			ManagerPool.playerManager.registerUser(msg.getUserId(), msg.getPlayerId(), msg.getName(), msg.getIdCard());
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}