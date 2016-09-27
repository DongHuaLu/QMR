package com.game.backpack.handler;

import org.apache.log4j.Logger;

import com.game.backpack.manager.BackpackManager;
import com.game.backpack.message.ReqOpenTimeCellMessage;
import com.game.command.Handler;
import com.game.player.structs.Player;
import com.game.store.manager.StoreManager;

public class ReqOpenTimeCellHandler extends Handler{

	Logger log = Logger.getLogger(ReqOpenTimeCellHandler.class);

	public void action(){
		try{
			ReqOpenTimeCellMessage msg = (ReqOpenTimeCellMessage)this.getMessage();
			if(msg.getType()==0){
				BackpackManager.getInstance().openCellByTime((Player) getParameter());
			}
			if(msg.getType()==1){
				StoreManager.getInstance().openCellByTime((Player) getParameter());	
			}
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}