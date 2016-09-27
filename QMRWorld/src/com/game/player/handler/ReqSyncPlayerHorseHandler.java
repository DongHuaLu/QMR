package com.game.player.handler;

import org.apache.log4j.Logger;

import com.game.player.message.ReqSyncPlayerHorseMessage;
import com.game.command.Handler;
import com.game.toplist.manager.TopListManager;
import com.game.toplist.structs.HorseTop;

public class ReqSyncPlayerHorseHandler extends Handler{

	Logger log = Logger.getLogger(ReqSyncPlayerHorseHandler.class);

	public void action(){
		try{
			ReqSyncPlayerHorseMessage msg = (ReqSyncPlayerHorseMessage)this.getMessage();
			HorseTop horseTop = new HorseTop(msg.getPlayerId(), msg.getHorseStage(), msg.getHorseLevel(), msg.getHorseSkillLevel(), msg.getHorseTime());
			TopListManager.getInstance().updateTopData(horseTop);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}