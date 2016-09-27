package com.game.task.handler;

import org.apache.log4j.Logger;

import com.game.player.structs.Player;
import com.game.task.manager.TaskManager;
import com.game.task.message.ReqDailyTaskTransMessage;
import com.game.command.Handler;

public class ReqDailyTaskTransHandler extends Handler{

	Logger log = Logger.getLogger(ReqDailyTaskTransHandler.class);

	public void action(){
		try{
			ReqDailyTaskTransMessage msg = (ReqDailyTaskTransMessage)this.getMessage();
			TaskManager.getInstance().transByDailyTask((Player) getParameter(), msg.getMapid(),msg.getX(),msg.getY(),msg.getLine());
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}