package com.game.chestbox.handler;

import com.game.chestbox.manager.ChestBoxManager;
import org.apache.log4j.Logger;

import com.game.chestbox.message.ReqChestBoxAutoSetToServerMessage;
import com.game.command.Handler;
import com.game.player.structs.Player;

public class ReqChestBoxAutoSetToServerHandler extends Handler{

	Logger log = Logger.getLogger(ReqChestBoxAutoSetToServerHandler.class);

	public void action(){
		try{
			ReqChestBoxAutoSetToServerMessage msg = (ReqChestBoxAutoSetToServerMessage)this.getMessage();
			ChestBoxManager.getInstance().reqChestBoxAutoSetToServer((Player) this.getParameter(), msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}