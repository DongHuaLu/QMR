package com.game.wine.handler;

import org.apache.log4j.Logger;

import com.game.wine.message.ReqWineShowPanelMessage;
import com.game.command.Handler;

public class ReqWineShowPanelHandler extends Handler{

	Logger log = Logger.getLogger(ReqWineShowPanelHandler.class);

	public void action(){
		try{
			ReqWineShowPanelMessage msg = (ReqWineShowPanelMessage)this.getMessage();
			//TODO 添加消息处理
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}