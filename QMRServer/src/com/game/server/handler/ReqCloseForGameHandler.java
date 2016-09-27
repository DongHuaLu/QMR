package com.game.server.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;

public class ReqCloseForGameHandler extends Handler{

	Logger log = Logger.getLogger(ReqCloseForGameHandler.class);

	public void action(){
		try{
			//关闭系统
			System.exit(0);
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}