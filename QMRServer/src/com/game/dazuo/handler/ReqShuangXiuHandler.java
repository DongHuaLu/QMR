package com.game.dazuo.handler;

import org.apache.log4j.Logger;

import com.game.dazuo.manager.PlayerDaZuoManager;
import com.game.dazuo.message.ReqShuangXiuMessage;
import com.game.player.structs.Player;
import com.game.command.Handler;

public class ReqShuangXiuHandler extends Handler{

	Logger log = Logger.getLogger(ReqShuangXiuHandler.class);

	public void action(){
		try{
			ReqShuangXiuMessage msg = (ReqShuangXiuMessage)this.getMessage();
//			PlayerDaZuoManager.getInstacne().refuseShuangXiu((Player)getParameter(), msg.getRole());
			PlayerDaZuoManager.getInstacne().sendShuangXiuApply((Player) getParameter(), msg.getRole());
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}