package com.game.store.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.store.message.ReqStoreOpenCellMessage;

public class ReqStoreOpenCellHandler extends Handler{

	Logger log = Logger.getLogger(ReqStoreOpenCellHandler.class);

	public void action(){
		try{
			ReqStoreOpenCellMessage msg = (ReqStoreOpenCellMessage)this.getMessage();
			ManagerPool.storeManager.openCellByCheck((Player)this.getParameter(), msg.getCellId(),msg.getNpcid());
//			ManagerPool.storeManager.openCell((Player)this.getParameter(), msg.getCellId());
		}catch(ClassCastException e){
			log.error(e,e);
		}
	}
}