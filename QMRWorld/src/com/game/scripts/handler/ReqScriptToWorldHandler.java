package com.game.scripts.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;
import com.game.manager.ManagerPool;
import com.game.scripts.message.ReqScriptToWorldMessage;

public class ReqScriptToWorldHandler extends Handler{

	Logger log = Logger.getLogger(ReqScriptToWorldHandler.class);

	public void action(){
		try{
			ReqScriptToWorldMessage msg = (ReqScriptToWorldMessage)this.getMessage();
			//脚本处理
			ManagerPool.scriptManager.excuteFromGame(msg.getScriptId(), msg.getMethod(), msg.getParas());
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}