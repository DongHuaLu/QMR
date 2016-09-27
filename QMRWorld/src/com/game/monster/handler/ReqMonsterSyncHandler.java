package com.game.monster.handler;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.monster.message.ReqMonsterSyncMessage;
import com.game.command.Handler;

public class ReqMonsterSyncHandler extends Handler{

	Logger log = Logger.getLogger(ReqMonsterSyncHandler.class);

	public void action(){
		try{
			ReqMonsterSyncMessage msg = (ReqMonsterSyncMessage)this.getMessage();
			ManagerPool.monsterManager.syncMonster(msg.getMonsterId(), msg.getServerId(), msg.getLineId(), msg.getMapmodelid(),msg.getModelId(), msg.getCurrentHp(), msg.getMaxHp(), msg.getState(), msg.getKiller(), msg.getRevive(), msg.getBirthX(),msg.getBirthY());
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}