package com.game.monster.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;
import com.game.monster.manager.MonsterManager;
import com.game.monster.message.ReqMonsterDoubleTimeToGameMessage;
import com.game.server.impl.WServer;

public class ReqMonsterDoubleTimeToGameHandler extends Handler{

	Logger log = Logger.getLogger(ReqMonsterDoubleTimeToGameHandler.class);

	public void action(){
		try{
			ReqMonsterDoubleTimeToGameMessage msg = (ReqMonsterDoubleTimeToGameMessage)this.getMessage();
			if (msg.getSendId() == 0 || msg.getSendId() == WServer.getInstance().getServerId()) {
				MonsterManager.DaguaiDoubleTime =msg.getContent();		//自定义服务器双倍时间
			}
			
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}