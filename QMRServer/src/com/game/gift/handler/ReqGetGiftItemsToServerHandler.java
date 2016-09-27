package com.game.gift.handler;

import org.apache.log4j.Logger;

import com.game.gift.message.ReqGetGiftItemsToServerMessage;
import com.game.command.Handler;
import com.game.gift.manager.GiftManager;
import com.game.player.structs.Player;

public class ReqGetGiftItemsToServerHandler extends Handler{

	Logger log = Logger.getLogger(ReqGetGiftItemsToServerHandler.class);

	public void action(){
		try{
			ReqGetGiftItemsToServerMessage msg = (ReqGetGiftItemsToServerMessage)this.getMessage();
			//GiftManager.getInstance().reqGetGiftItemsToServer((Player)this.getParameter(), msg, 1);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}