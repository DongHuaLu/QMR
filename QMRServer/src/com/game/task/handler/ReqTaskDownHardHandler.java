package com.game.task.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;
import com.game.player.structs.Player;
import com.game.task.manager.TaskManager;
import com.game.task.message.ReqTaskDownHardMessage;

public class ReqTaskDownHardHandler extends Handler{

	Logger log = Logger.getLogger(ReqTaskDownHardHandler.class);

	public void action(){
		try{
			ReqTaskDownHardMessage msg = (ReqTaskDownHardMessage)this.getMessage();
			TaskManager.getInstance().reducedDifficulty((Player) getParameter(), msg.getTaskId());
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}