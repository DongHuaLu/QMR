package com.game.monster.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;
import com.game.monster.manager.MonsterManager;
import com.game.player.structs.Player;

public class ReqQueryBossListHandler extends Handler{

	Logger log = Logger.getLogger(ReqQueryBossListHandler.class);

	public void action(){
		try{
//			ReqQueryBossListMessage msg = (ReqQueryBossListMessage)this.getMessage();
			MonsterManager.getInstance().queryBossList((Player) getParameter());
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}