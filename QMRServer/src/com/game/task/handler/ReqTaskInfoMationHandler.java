package com.game.task.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;

public class ReqTaskInfoMationHandler extends Handler{

	Logger log = Logger.getLogger(ReqTaskInfoMationHandler.class);

	public void action(){
		try{
//			TaskManager.getInstance().;
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}