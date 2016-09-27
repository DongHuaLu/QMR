package com.game.task.handler;

import org.apache.log4j.Logger;

import com.game.task.message.ReqRankTaskQuckFinshMessage;
import com.game.command.Handler;
import com.game.player.structs.Player;
import com.game.task.manager.TaskManager;

public class ReqRankTaskQuckFinshHandler extends Handler{

	Logger log = Logger.getLogger(ReqRankTaskQuckFinshHandler.class);

	public void action(){
		try{
			ReqRankTaskQuckFinshMessage msg = (ReqRankTaskQuckFinshMessage)this.getMessage();
			TaskManager.getInstance().quickFinishRankTask((Player)this.getParameter(), msg.getModelId());
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}