package com.game.setupmenu.handler;

import org.apache.log4j.Logger;

import com.game.player.structs.Player;
import com.game.setupmenu.manager.SetupMenuManager;
import com.game.setupmenu.message.ReqSetMenuStatusMessage;
import com.game.command.Handler;

public class ReqSetMenuStatusHandler extends Handler{

	Logger log = Logger.getLogger(ReqSetMenuStatusHandler.class);

	public void action(){
		try{
			ReqSetMenuStatusMessage msg = (ReqSetMenuStatusMessage)this.getMessage();
			SetupMenuManager.getInstance().stReqSetMenuStatusMessage((Player)this.getParameter(),msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}