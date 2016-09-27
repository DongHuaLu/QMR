package com.game.team.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;
import com.game.manager.ManagerPool;
import com.game.team.message.ReqCreateateamWorldMessage;

public class ReqCreateateamWorldHandler extends Handler{

	Logger log = Logger.getLogger(ReqCreateateamWorldHandler.class);

	public void action(){
		try{
			ReqCreateateamWorldMessage msg = (ReqCreateateamWorldMessage)this.getMessage();
			ManagerPool.teamManager.stCreateateamWorld(msg.getPlayerid());
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}