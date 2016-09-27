package com.game.scripts.handler;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.scripts.message.ReqScriptToGameMessage;
import com.game.command.Handler;

public class ReqScriptToGameHandler extends Handler{

	Logger log = Logger.getLogger(ReqScriptToGameHandler.class);

	public void action(){
		try{
			ReqScriptToGameMessage msg = (ReqScriptToGameMessage)this.getMessage();
			//脚本处理
			ManagerPool.scriptManager.excuteFromWorld(msg.getScriptId(), msg.getMethod(), msg.getParas());
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}