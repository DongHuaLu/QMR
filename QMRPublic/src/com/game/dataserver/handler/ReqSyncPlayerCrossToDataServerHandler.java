package com.game.dataserver.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;

public class ReqSyncPlayerCrossToDataServerHandler extends Handler{

	Logger log = Logger.getLogger(ReqSyncPlayerCrossToDataServerHandler.class);

	public void action(){
		try{
			//公共数据服务器处理
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}