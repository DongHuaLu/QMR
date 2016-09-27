package com.game.chestbox.handler;

import com.game.chestbox.manager.ChestBoxManager;
import org.apache.log4j.Logger;

import com.game.chestbox.message.ReqChestBoxGetGridItemToServerMessage;
import com.game.command.Handler;
import com.game.player.structs.Player;

public class ReqChestBoxGetGridItemToServerHandler extends Handler{

	Logger log = Logger.getLogger(ReqChestBoxGetGridItemToServerHandler.class);

	public void action(){
		try{
			ReqChestBoxGetGridItemToServerMessage msg = (ReqChestBoxGetGridItemToServerMessage)this.getMessage();
			ChestBoxManager.getInstance().reqChestBoxGetGridItemToServer((Player) this.getParameter(), msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}