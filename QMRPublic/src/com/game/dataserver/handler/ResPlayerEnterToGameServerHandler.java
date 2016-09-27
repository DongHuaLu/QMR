package com.game.dataserver.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;

public class ResPlayerEnterToGameServerHandler extends Handler{

	Logger log = Logger.getLogger(ResPlayerEnterToGameServerHandler.class);

	public void action(){
		try{
			//游戏服处理
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}