package com.game.offline.handler;

import org.apache.log4j.Logger;

import com.game.offline.message.ReqRetreatInfoMessage;
import com.game.command.Handler;
import com.game.offline.manager.OffLineManager;
import com.game.player.structs.Player;

public class ReqRetreatInfoHandler extends Handler{

	Logger log = Logger.getLogger(ReqRetreatInfoHandler.class);

	public void action(){
		try{
			ReqRetreatInfoMessage msg = (ReqRetreatInfoMessage)this.getMessage();
			OffLineManager.getInstance().reqRetreatInfoMessageToServer((Player)this.getParameter(), msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}