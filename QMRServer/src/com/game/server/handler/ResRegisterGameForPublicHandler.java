package com.game.server.handler;

import org.apache.log4j.Logger;

import com.game.server.message.ResRegisterGameForPublicMessage;
import com.game.command.Handler;

public class ResRegisterGameForPublicHandler extends Handler{

	Logger log = Logger.getLogger(ResRegisterGameForPublicHandler.class);

	public void action(){
		try{
			ResRegisterGameForPublicMessage msg = (ResRegisterGameForPublicMessage)this.getMessage();
			log.info("平台" + msg.getWebName() + "游戏服务器" + msg.getServerId() + "(" + msg.getServerName() + ")" + "注册公共服务器成功");
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}