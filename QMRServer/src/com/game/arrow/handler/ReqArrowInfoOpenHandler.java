package com.game.arrow.handler;

import com.game.arrow.manager.ArrowManager;
import org.apache.log4j.Logger;

import com.game.arrow.message.ReqArrowInfoOpenMessage;
import com.game.command.Handler;
import com.game.player.structs.Player;

public class ReqArrowInfoOpenHandler extends Handler{

	Logger log = Logger.getLogger(ReqArrowInfoOpenHandler.class);

	public void action(){
		try{
			ReqArrowInfoOpenMessage msg = (ReqArrowInfoOpenMessage)this.getMessage();
			ArrowManager.getInstance().reqArrowInfoOpenToServer((Player) this.getParameter(), msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}