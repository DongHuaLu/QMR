package com.game.dazuo.handler;

import org.apache.log4j.Logger;

import com.game.dazuo.message.ReqCohesionZhenQiMessage;
import com.game.command.Handler;

public class ReqCohesionZhenQiHandler extends Handler{

	Logger log = Logger.getLogger(ReqCohesionZhenQiHandler.class);

	public void action(){
		try{
			ReqCohesionZhenQiMessage msg = (ReqCohesionZhenQiMessage)this.getMessage();
			//TODO 添加消息处理
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}