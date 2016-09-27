package com.game.signwage.handler;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.signwage.message.ReqReceiveSalaryToClientMessage;
import com.game.utils.MessageUtil;
import com.game.command.Handler;

public class ReqReceiveSalaryToClientHandler extends Handler{

	Logger log = Logger.getLogger(ReqReceiveSalaryToClientHandler.class);

	public void action(){
		try{
			ReqReceiveSalaryToClientMessage msg = (ReqReceiveSalaryToClientMessage)this.getMessage();
			ManagerPool.signWageManager.receiveWage((Player)this.getParameter());
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}