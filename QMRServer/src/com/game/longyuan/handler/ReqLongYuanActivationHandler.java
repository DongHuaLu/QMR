package com.game.longyuan.handler;

import org.apache.log4j.Logger;

import com.game.longyuan.message.ReqLongYuanActivationMessage;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.command.Handler;

public class ReqLongYuanActivationHandler extends Handler{

	Logger log = Logger.getLogger(ReqLongYuanActivationHandler.class);

	public void action(){
		try{
			ReqLongYuanActivationMessage msg = (ReqLongYuanActivationMessage)this.getMessage();
			ManagerPool.longyuanManager.stReqLongYuanActivation((Player)getParameter(), msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}