package com.game.longyuan.handler;

import org.apache.log4j.Logger;

import com.game.longyuan.message.ReqLongYuanOpenMessage;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.command.Handler;

public class ReqLongYuanOpenHandler extends Handler{

	Logger log = Logger.getLogger(ReqLongYuanOpenHandler.class);

	public void action(){
		try{
			ReqLongYuanOpenMessage msg = (ReqLongYuanOpenMessage)this.getMessage();
			ManagerPool.longyuanManager.stReqLongYuanOpenMessage((Player)getParameter(), msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}