package com.game.task.handler;

import org.apache.log4j.Logger;

import com.game.player.structs.Player;
import com.game.task.manager.TaskManager;
import com.game.task.message.ReqConquerTaskDevourMessage;
import com.game.command.Handler;

public class ReqConquerTaskDevourHandler extends Handler{

	Logger log = Logger.getLogger(ReqConquerTaskDevourHandler.class);

	public void action(){
		try{
			ReqConquerTaskDevourMessage msg = (ReqConquerTaskDevourMessage)this.getMessage();
			TaskManager.getInstance().devourTask((Player) getParameter(), msg.getDevourId(),msg.getIsfull()==1);
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}