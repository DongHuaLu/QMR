package com.game.monster.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;
import com.game.monster.manager.MonsterManager;
import com.game.monster.message.ReqQueryBossStateListMessage;
import com.game.player.structs.Player;

public class ReqQueryBossStateListHandler extends Handler{

	Logger log = Logger.getLogger(ReqQueryBossStateListHandler.class);

	public void action(){
		try{
			ReqQueryBossStateListMessage msg = (ReqQueryBossStateListMessage)this.getMessage();
			MonsterManager.getInstance().queryBossStateList((Player)getParameter(),msg.getMonsterModelId());
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}