package com.game.store.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;
import com.game.manager.ManagerPool;
import com.game.message.Message;
import com.game.player.structs.Player;
import com.game.store.message.ReqStoreClearUpMessage;

public class ReqStoreClearUpHandler extends Handler{
	Logger log = Logger.getLogger(ReqStoreClearUpHandler.class);
	public void action(){
		try{
			ReqStoreClearUpMessage msg = (ReqStoreClearUpMessage) getMessage();
			ManagerPool.storeManager.storeClearUp((Player)this.getParameter(),false,msg.getNpcid());
		}catch(ClassCastException e){
			log.error(e,e);
		}
	}
}