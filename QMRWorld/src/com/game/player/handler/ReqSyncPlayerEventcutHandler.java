package com.game.player.handler;

import org.apache.log4j.Logger;

import com.game.player.message.ReqSyncPlayerEventcutMessage;
import com.game.command.Handler;
import com.game.toplist.manager.TopListManager;
import com.game.toplist.structs.EvenCutTop;

public class ReqSyncPlayerEventcutHandler extends Handler{

	Logger log = Logger.getLogger(ReqSyncPlayerEventcutHandler.class);

	public void action(){
		try{
			ReqSyncPlayerEventcutMessage msg = (ReqSyncPlayerEventcutMessage)this.getMessage();
			EvenCutTop evenCutTop = new EvenCutTop(msg.getPlayerId(), msg.getEventcut(), msg.getEventcutTime(), msg.getMapModelId(), msg.getMonsterModelId(), msg.getMapX(), msg.getMapY());
			TopListManager.getInstance().updateTopData(evenCutTop);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}