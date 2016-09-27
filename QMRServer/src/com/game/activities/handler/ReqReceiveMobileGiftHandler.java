package com.game.activities.handler;

import org.apache.log4j.Logger;

import com.game.activities.manager.ActivitiesMobileManager;
import com.game.activities.message.ReqReceiveMobileGiftMessage;
import com.game.command.Handler;
import com.game.player.structs.Player;

public class ReqReceiveMobileGiftHandler extends Handler{

	Logger log = Logger.getLogger(ReqReceiveMobileGiftHandler.class);

	public void action(){
		try{
			ReqReceiveMobileGiftMessage msg = (ReqReceiveMobileGiftMessage)this.getMessage();
			ActivitiesMobileManager.getInstance().getVersionGift((Player)this.getParameter(), msg.getIndex());
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}