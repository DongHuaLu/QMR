package com.game.player.handler;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.player.message.ReqPlayerNonageRegisterMessage;
import com.game.player.structs.Player;
import com.game.command.Handler;

public class ReqPlayerNonageRegisterHandler extends Handler{

	Logger log = Logger.getLogger(ReqPlayerNonageRegisterHandler.class);

	public void action(){
		try{
			ReqPlayerNonageRegisterMessage msg = (ReqPlayerNonageRegisterMessage)this.getMessage();
			//防沉迷注册
			ManagerPool.playerManager.registerNonage((Player)getParameter(), msg.getName(), msg.getIdCard());
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}