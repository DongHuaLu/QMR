package com.game.task.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;
import com.game.dblog.LogService;
import com.game.json.JSONserializable;
import com.game.player.structs.Player;
import com.game.task.log.MainTaskLog;
import com.game.task.manager.TaskManager;
import com.game.task.message.ReqTaskReceiveMessage;
import com.game.task.struts.Task;

public class ReqTaskReceiveHandler extends Handler{

	Logger logger = Logger.getLogger(ReqTaskReceiveHandler.class);

	public void action(){
		try{
			ReqTaskReceiveMessage msg=new ReqTaskReceiveMessage();
			Player player= (Player) getParameter();
			String beforeReceiveAble=JSONserializable.toString(player.getTaskRewardsReceiveAble());
			TaskManager.getInstance().acceptMainTask((Player) getParameter(), msg.getModelId());
			Task task = TaskManager.getInstance().getTaskByModelId(player, msg.getModelId());
			try {
				if(task!=null){
					MainTaskLog log=new MainTaskLog();
					log.setRoleId(player.getId());
					log.setAcceptafterReceiveAble(JSONserializable.toString(player.getTaskRewardsReceiveAble()));
					log.setAcceptbeforeReceiveAble(beforeReceiveAble);
					log.setAcceptmodelId(msg.getModelId());
					log.setAccepttaskInfo(JSONserializable.toString(task));
					log.setUserId(player.getUserId());
					log.setAcceptonlinetime(player.getAccunonlinetime());
					log.setAcceptlevel(player.getLevel());
					log.setSid(player.getCreateServerId());
					LogService.getInstance().execute(log);	
				}
			} catch (Exception e) {
				logger.error(e,e);
			}
			
		}catch(ClassCastException e){
			logger.error(e);
		}
	}
}