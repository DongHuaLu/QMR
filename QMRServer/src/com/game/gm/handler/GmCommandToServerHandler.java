package com.game.gm.handler;

import org.apache.log4j.Logger;

import com.game.gm.manager.BGGmcommandManager;
import com.game.gm.message.GmCommandToServerMessage;
import com.game.command.Handler;

public class GmCommandToServerHandler extends Handler{

	Logger log = Logger.getLogger(GmCommandToServerHandler.class);

	public void action(){
		try{
			GmCommandToServerMessage msg = (GmCommandToServerMessage)this.getMessage();
			BGGmcommandManager.getInstance().gmcommand(msg);
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}