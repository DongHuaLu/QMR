package com.game.setupmenu.handler;

import org.apache.log4j.Logger;

import com.game.setupmenu.manager.SetupMenuManager;
import com.game.setupmenu.message.ResSetMenuStatusToWorldMessage;
import com.game.command.Handler;

public class ResSetMenuStatusToWorldHandler extends Handler{

	Logger log = Logger.getLogger(ResSetMenuStatusToWorldHandler.class);

	public void action(){
		try{
			ResSetMenuStatusToWorldMessage msg = (ResSetMenuStatusToWorldMessage)this.getMessage();
			SetupMenuManager.getInstance().stResSetMenuStatusToWorldMessage(msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}