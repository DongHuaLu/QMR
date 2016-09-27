package com.game.dataserver.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;

public class ReqCheckTeamEnterToGameServerHandler extends Handler{

	Logger log = Logger.getLogger(ReqCheckTeamEnterToGameServerHandler.class);

	public void action(){
		try{
			//游戏服处理
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}