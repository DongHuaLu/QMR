package com.game.player.handler;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.player.message.ReqOtherPlayerInfoToWorldMessage;
import com.game.command.Handler;

public class ReqOtherPlayerInfoToWorldHandler extends Handler{

	Logger log = Logger.getLogger(ReqOtherPlayerInfoToWorldHandler.class);

	public void action(){
		try{
			ReqOtherPlayerInfoToWorldMessage msg = (ReqOtherPlayerInfoToWorldMessage)this.getMessage();
			//获取其他玩家信息
			ManagerPool.playerManager.getOtherPlayerInfo(msg.getPlayerId(), msg.getOtherPlayerId(),msg.getType());
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}