package com.game.dataserver.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;

public class ResCancelTeamEnterToGameServerHandler extends Handler{

	Logger log = Logger.getLogger(ResCancelTeamEnterToGameServerHandler.class);

	public void action(){
		try{
			//游戏服处理
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}