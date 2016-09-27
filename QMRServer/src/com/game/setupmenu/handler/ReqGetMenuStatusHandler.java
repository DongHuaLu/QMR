package com.game.setupmenu.handler;

import org.apache.log4j.Logger;

import com.game.player.structs.Player;
import com.game.setupmenu.manager.SetupMenuManager;
import com.game.setupmenu.message.ReqGetMenuStatusMessage;
import com.game.command.Handler;

public class ReqGetMenuStatusHandler extends Handler{

	Logger log = Logger.getLogger(ReqGetMenuStatusHandler.class);

	public void action(){
		try{
			ReqGetMenuStatusMessage msg = (ReqGetMenuStatusMessage)this.getMessage();
			SetupMenuManager.getInstance().stReqGetMenuStatusMessage((Player)this.getParameter(),msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}