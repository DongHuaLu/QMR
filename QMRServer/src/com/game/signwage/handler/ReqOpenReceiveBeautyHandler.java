package com.game.signwage.handler;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.signwage.message.ReqOpenReceiveBeautyMessage;
import com.game.command.Handler;

public class ReqOpenReceiveBeautyHandler extends Handler{

	Logger log = Logger.getLogger(ReqOpenReceiveBeautyHandler.class);

	public void action(){
		try{
			ReqOpenReceiveBeautyMessage msg = (ReqOpenReceiveBeautyMessage)this.getMessage();
			
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}