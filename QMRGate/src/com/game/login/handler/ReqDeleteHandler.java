package com.game.login.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;
import com.game.login.message.ReqDeleteMessage;
import com.game.manager.ManagerPool;

public class ReqDeleteHandler extends Handler{

	Logger log = Logger.getLogger(ReqDeleteHandler.class);

	public void action(){
		try{
			ReqDeleteMessage msg = (ReqDeleteMessage)this.getMessage();
			//删除角色
			ManagerPool.playerManager.deleteCharacter(msg.getSession(), msg.getPlayerId());
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}