package com.game.arrow.handler;

import com.game.arrow.manager.ArrowManager;
import org.apache.log4j.Logger;

import com.game.arrow.message.ReqFightSpiritOpenMessage;
import com.game.command.Handler;
import com.game.player.structs.Player;

public class ReqFightSpiritOpenHandler extends Handler{

	Logger log = Logger.getLogger(ReqFightSpiritOpenHandler.class);

	public void action(){
		try{
			ReqFightSpiritOpenMessage msg = (ReqFightSpiritOpenMessage)this.getMessage();
			ArrowManager.getInstance().reqFightSpiritOpenToServer((Player) this.getParameter(), msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}