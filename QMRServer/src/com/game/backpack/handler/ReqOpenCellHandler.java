package com.game.backpack.handler;

import org.apache.log4j.Logger;

import com.game.backpack.manager.BackpackManager;
import com.game.backpack.message.ReqOpenCellMessage;
import com.game.command.Handler;
import com.game.player.structs.Player;

public class ReqOpenCellHandler extends Handler{

	Logger log = Logger.getLogger(ReqOpenCellHandler.class);

	public void action(){
		try{
			ReqOpenCellMessage msg = (ReqOpenCellMessage)this.getMessage();
			BackpackManager.getInstance().openCellMsg((Player)this.getParameter(), msg.getCellId());
		}catch(ClassCastException e){
			log.error(e,e);
		}
	}
}