package com.game.chestbox.handler;

import com.game.chestbox.manager.ChestBoxManager;
import org.apache.log4j.Logger;

import com.game.chestbox.message.ReqChestBoxShowPanelToServerMessage;
import com.game.command.Handler;
import com.game.player.structs.Player;

public class ReqChestBoxShowPanelToServerHandler extends Handler{

	Logger log = Logger.getLogger(ReqChestBoxShowPanelToServerHandler.class);

	public void action(){
		try{
			ReqChestBoxShowPanelToServerMessage msg = (ReqChestBoxShowPanelToServerMessage)this.getMessage();
			ChestBoxManager.getInstance().reqChestBoxShowPanelToServer((Player) this.getParameter(), msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}