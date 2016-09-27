package com.game.backpack.handler;

import org.apache.log4j.Logger;

import com.game.backpack.message.ReqDiscardMessage;
import com.game.command.Handler;
import com.game.drop.manager.DropManager;
import com.game.player.structs.Player;

public class ReqDiscardHandler extends Handler{

	Logger log = Logger.getLogger(ReqDiscardHandler.class);

	public void action(){
		try{
			ReqDiscardMessage msg = (ReqDiscardMessage)this.getMessage();
			DropManager.playerDiscard((Player)this.getParameter(), msg.getCellId());
		}catch(ClassCastException e){
			log.error(e,e);
		}
	}
}