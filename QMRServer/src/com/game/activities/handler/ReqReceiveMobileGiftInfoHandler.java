package com.game.activities.handler;

import org.apache.log4j.Logger;

import com.game.activities.manager.ActivitiesMobileManager;
import com.game.command.Handler;
import com.game.player.structs.Player;

public class ReqReceiveMobileGiftInfoHandler extends Handler{

	Logger log = Logger.getLogger(ReqReceiveMobileGiftInfoHandler.class);

	public void action(){
		try{
			ActivitiesMobileManager.getInstance().sendVersionGift((Player)this.getParameter());
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}