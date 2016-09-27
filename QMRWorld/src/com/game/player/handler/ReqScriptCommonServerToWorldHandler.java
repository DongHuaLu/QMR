package com.game.player.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;
import com.game.player.manager.PlayerManager;
import com.game.player.message.ReqScriptCommonServerToWorldMessage;

public class ReqScriptCommonServerToWorldHandler extends Handler{

	Logger log = Logger.getLogger(ReqScriptCommonServerToWorldHandler.class);

	public void action(){
		try{
			ReqScriptCommonServerToWorldMessage msg = (ReqScriptCommonServerToWorldMessage)this.getMessage();
			PlayerManager.getInstance().reqScriptCommonServerToWorldMessage(msg);
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}