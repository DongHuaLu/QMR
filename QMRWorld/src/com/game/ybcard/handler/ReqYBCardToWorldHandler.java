package com.game.ybcard.handler;

import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.game.ybcard.message.ReqYBCardToWorldMessage;
import com.game.command.Handler;
import com.game.manager.ManagerPool;

public class ReqYBCardToWorldHandler extends Handler{

	Logger log = Logger.getLogger(ReqYBCardToWorldHandler.class);

	public void action(){
		try{
			ReqYBCardToWorldMessage msg = (ReqYBCardToWorldMessage)this.getMessage();
			try {
				ManagerPool.ybcardManager.stReqYBCardToWorldMessage(msg);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}