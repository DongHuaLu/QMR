package com.game.longyuan.handler;

import org.apache.log4j.Logger;

import com.game.longyuan.message.ReqLongYuanTipsMessage;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.command.Handler;

public class ReqLongYuanTipsHandler extends Handler{

	Logger log = Logger.getLogger(ReqLongYuanTipsHandler.class);

	public void action(){
		try{
			ReqLongYuanTipsMessage msg = (ReqLongYuanTipsMessage)this.getMessage();
			ManagerPool.longyuanManager.stReqLongYuanTipsMessage((Player)getParameter(), msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}