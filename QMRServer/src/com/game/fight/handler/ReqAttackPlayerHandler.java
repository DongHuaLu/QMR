package com.game.fight.handler;

import org.apache.log4j.Logger;

import com.game.fight.message.ReqAttackPlayerMessage;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.command.Handler;

public class ReqAttackPlayerHandler extends Handler{

	Logger log = Logger.getLogger(ReqAttackPlayerHandler.class);

	public void action(){
		try{
			ReqAttackPlayerMessage msg = (ReqAttackPlayerMessage)this.getMessage();
			//攻击玩家
			ManagerPool.fightManager.playerAttackPlayer((Player)this.getParameter(), msg.getFightTarget(), msg.getFightType(), msg.getFightDirection());
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}