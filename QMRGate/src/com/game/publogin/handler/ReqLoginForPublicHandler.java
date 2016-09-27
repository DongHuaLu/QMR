package com.game.publogin.handler;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.publogin.message.ReqLoginForPublicMessage;
import com.game.command.Handler;

public class ReqLoginForPublicHandler extends Handler{

	Logger log = Logger.getLogger(ReqLoginForPublicHandler.class);

	public void action(){
		try{
			ReqLoginForPublicMessage msg = (ReqLoginForPublicMessage)this.getMessage();
			//登录
			ManagerPool.publicPlayerManager.login(msg.getSession(), msg);
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}