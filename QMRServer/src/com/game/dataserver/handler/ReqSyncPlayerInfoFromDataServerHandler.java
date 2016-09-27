package com.game.dataserver.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;

public class ReqSyncPlayerInfoFromDataServerHandler extends Handler{

	Logger log = Logger.getLogger(ReqSyncPlayerInfoFromDataServerHandler.class);

	public void action(){
		try{
			//公共数据服务器处理
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}