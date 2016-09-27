package com.game.task.handler;

import org.apache.log4j.Logger;

import com.game.player.structs.Player;
import com.game.task.manager.TaskManager;
import com.game.task.message.ReqConquerTaskQuckFinshMessage;
import com.game.command.Handler;

public class ReqConquerTaskQuckFinshHandler extends Handler{

	Logger log = Logger.getLogger(ReqConquerTaskQuckFinshHandler.class);

	public void action(){
		try{
			ReqConquerTaskQuckFinshMessage msg = (ReqConquerTaskQuckFinshMessage)this.getMessage();
			TaskManager.getInstance().supperCurrentTaskFinsh((Player) getParameter(), msg.getTaskId());
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}