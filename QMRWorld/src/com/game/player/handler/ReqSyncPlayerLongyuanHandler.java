package com.game.player.handler;

import org.apache.log4j.Logger;

import com.game.player.message.ReqSyncPlayerLongyuanMessage;
import com.game.command.Handler;
import com.game.toplist.manager.TopListManager;
import com.game.toplist.structs.LongYuanTop;

public class ReqSyncPlayerLongyuanHandler extends Handler{

	Logger log = Logger.getLogger(ReqSyncPlayerLongyuanHandler.class);

	public void action(){
		try{
			ReqSyncPlayerLongyuanMessage msg = (ReqSyncPlayerLongyuanMessage)this.getMessage();
			LongYuanTop longYuanTop = new LongYuanTop(msg.getPlayerId(), msg.getLongyuanSection(), msg.getLongyuanLevel(), msg.getLongyuanTime());
			TopListManager.getInstance().updateTopData(longYuanTop);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}