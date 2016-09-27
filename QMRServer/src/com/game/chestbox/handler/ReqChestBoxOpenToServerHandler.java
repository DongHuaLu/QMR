package com.game.chestbox.handler;

import com.game.chestbox.manager.ChestBoxManager;
import org.apache.log4j.Logger;

import com.game.chestbox.message.ReqChestBoxOpenToServerMessage;
import com.game.command.Handler;
import com.game.player.structs.Player;

public class ReqChestBoxOpenToServerHandler extends Handler{

	Logger log = Logger.getLogger(ReqChestBoxOpenToServerHandler.class);

	public void action(){
		try{
			ReqChestBoxOpenToServerMessage msg = (ReqChestBoxOpenToServerMessage)this.getMessage();
			ChestBoxManager.getInstance().reqChestBoxOpenToServer((Player) this.getParameter(), msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}