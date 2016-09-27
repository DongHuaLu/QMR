package com.game.task.handler;

import org.apache.log4j.Logger;

import com.game.task.message.ReqTaskGoldAddNumMessage;
import com.game.command.Handler;
import com.game.player.structs.Player;
import com.game.task.manager.TaskManager;

public class ReqTaskGoldAddNumHandler extends Handler{

	Logger log = Logger.getLogger(ReqTaskGoldAddNumHandler.class);

	public void action(){
		try{
			ReqTaskGoldAddNumMessage msg = (ReqTaskGoldAddNumMessage)this.getMessage();
			TaskManager.getInstance().reqTaskGoldAddNumToServer((Player) this.getParameter(), msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}