package com.game.longyuan.handler;

import org.apache.log4j.Logger;

import com.game.longyuan.message.ReqLongYuanStarMapTipsMessage;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.command.Handler;

public class ReqLongYuanStarMapTipsHandler extends Handler{

	Logger log = Logger.getLogger(ReqLongYuanStarMapTipsHandler.class);

	public void action(){
		try{
			ReqLongYuanStarMapTipsMessage msg = (ReqLongYuanStarMapTipsMessage)this.getMessage();
			ManagerPool.longyuanManager.stReqLongYuanStarMapTipsMessage((Player)getParameter(), msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}