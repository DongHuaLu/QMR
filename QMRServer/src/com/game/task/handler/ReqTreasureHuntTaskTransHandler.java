package com.game.task.handler;

import org.apache.log4j.Logger;

import com.game.player.structs.Player;
import com.game.task.manager.TaskManager;
import com.game.task.message.ReqTreasureHuntTaskTransMessage;
import com.game.command.Handler;

public class ReqTreasureHuntTaskTransHandler extends Handler{

	Logger log = Logger.getLogger(ReqTreasureHuntTaskTransHandler.class);

	public void action(){
		try{
			ReqTreasureHuntTaskTransMessage msg = (ReqTreasureHuntTaskTransMessage)this.getMessage();
			TaskManager.getInstance().transByTreasureHuntTask((Player) getParameter(), msg.getTaskId(), msg.getMapid(), msg.getX(), msg.getY(), msg.getLine());
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}