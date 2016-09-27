package com.game.plugset.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;
import com.game.player.structs.Player;
import com.game.plugset.manager.PlugSetManager;
import com.game.plugset.message.ReqPlugSetInfoMessage;

public class ReqPlugSetInfoHandler extends Handler{

	Logger log = Logger.getLogger(ReqPlugSetInfoHandler.class);

	public void action(){
		try{
			ReqPlugSetInfoMessage msg = (ReqPlugSetInfoMessage)this.getMessage();
			PlugSetManager.getInstance().stReqPlugSetInfoMessage((Player)this.getParameter(),msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}