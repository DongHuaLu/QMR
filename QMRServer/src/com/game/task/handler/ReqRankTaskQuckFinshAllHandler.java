package com.game.task.handler;

import org.apache.log4j.Logger;

import com.game.task.message.ReqRankTaskQuckFinshAllMessage;
import com.game.command.Handler;
import com.game.player.structs.Player;
import com.game.task.manager.TaskManager;

public class ReqRankTaskQuckFinshAllHandler extends Handler{

	Logger log = Logger.getLogger(ReqRankTaskQuckFinshAllHandler.class);

	public void action(){
		try{
			ReqRankTaskQuckFinshAllMessage msg = (ReqRankTaskQuckFinshAllMessage)this.getMessage();
			TaskManager.getInstance().quickFinishAllRankTask((Player) this.getParameter());
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}