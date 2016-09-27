package com.game.server.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;

public class ReqCloseForGateHandler extends Handler{

	Logger log = Logger.getLogger(ReqCloseForGateHandler.class);

	public void action(){
		try{
			//系统关闭
			System.exit(0);
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}