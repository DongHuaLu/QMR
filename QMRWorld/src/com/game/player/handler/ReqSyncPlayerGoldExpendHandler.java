package com.game.player.handler;

import org.apache.log4j.Logger;

import com.game.player.message.ReqSyncPlayerGoldExpendMessage;
import com.game.command.Handler;
import com.game.goldexpend.manager.GoldExpendManager;

public class ReqSyncPlayerGoldExpendHandler extends Handler{

	Logger log = Logger.getLogger(ReqSyncPlayerGoldExpendHandler.class);

	public void action(){
		try{
			ReqSyncPlayerGoldExpendMessage msg = (ReqSyncPlayerGoldExpendMessage)this.getMessage();
			GoldExpendManager.getInstance().add(msg.getPlayerId(), msg.getGold(), msg.getReason());
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}