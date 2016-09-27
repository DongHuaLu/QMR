package com.game.monster.handler;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.monster.message.ReqMonsterDoubleNoticeMessage;
import com.game.command.Handler;

public class ReqMonsterDoubleNoticeHandler extends Handler{

	Logger log = Logger.getLogger(ReqMonsterDoubleNoticeHandler.class);

	public void action(){
		try{
			ReqMonsterDoubleNoticeMessage msg = (ReqMonsterDoubleNoticeMessage)this.getMessage();
			ManagerPool.monsterManager.stReqMonsterDoubleNoticeMessage(msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}