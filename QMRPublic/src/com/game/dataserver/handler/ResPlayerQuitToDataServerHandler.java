package com.game.dataserver.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;

public class ResPlayerQuitToDataServerHandler extends Handler{

	Logger log = Logger.getLogger(ResPlayerQuitToDataServerHandler.class);

	public void action(){
		try{
			//游戏服务器处理
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}