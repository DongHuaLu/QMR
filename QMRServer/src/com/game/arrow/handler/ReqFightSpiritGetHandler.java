package com.game.arrow.handler;

import com.game.arrow.manager.ArrowManager;
import org.apache.log4j.Logger;

import com.game.arrow.message.ReqFightSpiritGetMessage;
import com.game.command.Handler;
import com.game.player.structs.Player;

public class ReqFightSpiritGetHandler extends Handler{

	Logger log = Logger.getLogger(ReqFightSpiritGetHandler.class);

	public void action(){
		try{
			ReqFightSpiritGetMessage msg = (ReqFightSpiritGetMessage)this.getMessage();
			ArrowManager.getInstance().reqFightSpiritGetToServer((Player) this.getParameter(), msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}