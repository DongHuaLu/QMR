package com.game.task.handler;

import org.apache.log4j.Logger;

import com.game.player.structs.Player;
import com.game.task.manager.TaskManager;
import com.game.task.message.ReqTaskUpAchieveMessage;
import com.game.command.Handler;

public class ReqTaskUpAchieveHandler extends Handler{

	Logger log = Logger.getLogger(ReqTaskUpAchieveHandler.class);

	public void action(){
		try{
			ReqTaskUpAchieveMessage msg = (ReqTaskUpAchieveMessage)this.getMessage();
			TaskManager.getInstance().upAchrive((Player)getParameter(), msg.getTaskId());
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}