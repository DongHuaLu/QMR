package com.game.task.handler;

import org.apache.log4j.Logger;

import com.game.player.structs.Player;
import com.game.task.manager.TaskManager;
import com.game.task.message.ReqConqueryTaskTransMessage;
import com.game.command.Handler;

public class ReqConqueryTaskTransHandler extends Handler{

	Logger log = Logger.getLogger(ReqConqueryTaskTransHandler.class);

	public void action(){
		try{
			ReqConqueryTaskTransMessage msg = (ReqConqueryTaskTransMessage)this.getMessage();
			TaskManager.getInstance().transByConquerTask((Player) getParameter(), msg.getTaskId(), msg.getMapid(),msg.getX(),msg.getY(),msg.getLine());
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}