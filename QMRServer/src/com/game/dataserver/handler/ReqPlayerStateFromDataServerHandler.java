package com.game.dataserver.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;

public class ReqPlayerStateFromDataServerHandler extends Handler{

	Logger log = Logger.getLogger(ReqPlayerStateFromDataServerHandler.class);

	public void action(){
		try{
			//公共数据服务器处理
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}