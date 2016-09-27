package com.game.signwage.handler;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.signwage.message.ReqReceiveBeautyMessage;
import com.game.command.Handler;

public class ReqReceiveBeautyHandler extends Handler{

	Logger log = Logger.getLogger(ReqReceiveBeautyHandler.class);

	public void action(){
		try{
			ReqReceiveBeautyMessage msg = (ReqReceiveBeautyMessage)this.getMessage();
			ManagerPool.signWageManager.stReceiveBeauty((Player)this.getParameter(),msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}