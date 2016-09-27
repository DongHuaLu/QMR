package com.game.server.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;

public class ResRegisterGameForPublicHandler extends Handler{

	Logger log = Logger.getLogger(ResRegisterGameForPublicHandler.class);

	public void action(){
		try{
			//在游戏服务器处理
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}