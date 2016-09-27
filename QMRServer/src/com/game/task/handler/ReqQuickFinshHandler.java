package com.game.task.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;
import com.game.player.structs.Player;
import com.game.task.manager.TaskManager;
import com.game.task.message.ReqQuickFinshMessage;

public class ReqQuickFinshHandler extends Handler{

	Logger log = Logger.getLogger(ReqQuickFinshHandler.class);

	public void action(){
		try{
			ReqQuickFinshMessage msg = (ReqQuickFinshMessage)this.getMessage();
			TaskManager.getInstance().supperFinsh((Player)getParameter(), msg.getTaskId(),msg.getType());
		}catch(Exception e){
			log.error(e,e);
		}
	}
}