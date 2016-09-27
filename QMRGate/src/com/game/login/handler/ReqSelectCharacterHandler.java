package com.game.login.handler;

import org.apache.log4j.Logger;

import com.game.login.message.ReqSelectCharacterMessage;
import com.game.manager.ManagerPool;
import com.game.command.Handler;

public class ReqSelectCharacterHandler extends Handler{

	Logger log = Logger.getLogger(ReqSelectCharacterHandler.class);

	public void action(){
		try{
			ReqSelectCharacterMessage msg = (ReqSelectCharacterMessage)this.getMessage();
			//选择角色
			ManagerPool.playerManager.selectCharacter(msg.getSession(), msg.getPlayerId());
		}catch(ClassCastException e){
			log.error(e, e);
		}
	}
}