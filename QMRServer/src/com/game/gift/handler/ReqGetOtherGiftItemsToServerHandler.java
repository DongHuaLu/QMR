package com.game.gift.handler;

import org.apache.log4j.Logger;

import com.game.gift.message.ReqGetOtherGiftItemsToServerMessage;
import com.game.command.Handler;
import com.game.gift.manager.GiftManager;
import com.game.player.structs.Player;

public class ReqGetOtherGiftItemsToServerHandler extends Handler{

	Logger log = Logger.getLogger(ReqGetOtherGiftItemsToServerHandler.class);

	public void action(){
		try{
			ReqGetOtherGiftItemsToServerMessage msg = (ReqGetOtherGiftItemsToServerMessage)this.getMessage();
			GiftManager.getInstance().reqGetOtherGiftItemsToServer((Player) this.getParameter(), msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}