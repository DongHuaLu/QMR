package com.game.fight.handler;

import org.apache.log4j.Logger;

import com.game.fight.message.ReqAttackMonsterMessage;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.command.Handler;

public class ReqAttackMonsterHandler extends Handler{

	Logger log = Logger.getLogger(ReqAttackMonsterHandler.class);

	public void action(){
		try{
			ReqAttackMonsterMessage msg = (ReqAttackMonsterMessage)this.getMessage();
			//攻击怪物
			ManagerPool.fightManager.playerAttackMonster((Player)this.getParameter(), msg.getFightTarget(), msg.getFightType(), msg.getFightDirection());
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}