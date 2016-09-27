package com.game.login.handler;

import org.apache.log4j.Logger;

import com.game.login.message.ReqLoginMessage;
import com.game.manager.ManagerPool;
import com.game.command.Handler;

public class ReqLoginHandler extends Handler{

	Logger log = Logger.getLogger(ReqLoginHandler.class);

	public void action(){
		try{
			ReqLoginMessage msg = (ReqLoginMessage)this.getMessage();
			//登录
			ManagerPool.playerManager.login(msg.getSession(), msg.getServerId(), msg.getName(), msg.getPassword());
		}catch(ClassCastException e){
			log.error(e, e);
		}
	}
}