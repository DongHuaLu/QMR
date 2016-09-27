package com.game.login.handler;

import org.apache.log4j.Logger;

import com.game.login.message.ReqCreateCharacterMessage;
import com.game.manager.ManagerPool;
import com.game.command.Handler;

public class ReqCreateCharacterHandler extends Handler{

	Logger log = Logger.getLogger(ReqCreateCharacterHandler.class);

	public void action(){
		try{
			ReqCreateCharacterMessage msg = (ReqCreateCharacterMessage)this.getMessage();
			//创建角色
			ManagerPool.playerManager.createCharacter(msg.getSession(), msg.getName(), msg.getIcon(), msg.getSex(), (byte)0, msg.getAuto());
		}catch(ClassCastException e){
			log.error(e, e);
		}
	}
}