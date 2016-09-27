package com.game.task.handler;

import org.apache.log4j.Logger;

import com.game.player.structs.Player;
import com.game.task.manager.TaskManager;
import com.game.task.message.ReqGiveUpTaskMessage;
import com.game.command.Handler;
/**
 * 放弃任务 
 * @author 赵聪慧
 *
 */
public class ReqGiveUpTaskHandler extends Handler{

	Logger log = Logger.getLogger(ReqGiveUpTaskHandler.class);

	public void action(){
		try{
			ReqGiveUpTaskMessage msg = (ReqGiveUpTaskMessage)this.getMessage();
			TaskManager.getInstance().giveUpTask((Player)getParameter(), msg.getType(),msg.getTaskid());
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}