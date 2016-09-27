package com.game.login.handler;

import org.apache.log4j.Logger;

import com.game.login.message.ReqLoginForPlatformMessage;
import com.game.manager.ManagerPool;
import com.game.command.Handler;

public class ReqLoginForPlatformHandler extends Handler{

	Logger log = Logger.getLogger(ReqLoginForPlatformHandler.class);

	public void action(){
		try{
			ReqLoginForPlatformMessage msg = (ReqLoginForPlatformMessage)this.getMessage();
			//平台登陆
			log.error("reqloginforplaform\t"+msg.getUsername()+"\tlogintype:"+msg.getLogintype());
			ManagerPool.playerManager.login(msg.getSession(), msg);
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}