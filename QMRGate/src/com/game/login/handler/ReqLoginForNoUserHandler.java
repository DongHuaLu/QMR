package com.game.login.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;

public class ReqLoginForNoUserHandler extends Handler{

	Logger log = Logger.getLogger(ReqLoginForNoUserHandler.class);

	public void action(){
		try{
//			ReqLoginForNoUserMessage msg = (ReqLoginForNoUserMessage)this.getMessage();
			//无账号登陆
//			ManagerPool.playerManager.login(msg.getSession(), msg);
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}