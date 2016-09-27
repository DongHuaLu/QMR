package com.game.biwudao.handler;

import org.apache.log4j.Logger;

import com.game.biwudao.message.ReqBiWuDaoSelectToGameMessage;
import com.game.command.Handler;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;

public class ReqBiWuDaoSelectToGameHandler extends Handler{

	Logger log = Logger.getLogger(ReqBiWuDaoSelectToGameHandler.class);

	public void action(){
		try{
			ReqBiWuDaoSelectToGameMessage msg = (ReqBiWuDaoSelectToGameMessage)this.getMessage();
			ManagerPool.biWuDaoManager.stReqBiWuDaoSelectToGameMessage((Player)this.getParameter(),msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}