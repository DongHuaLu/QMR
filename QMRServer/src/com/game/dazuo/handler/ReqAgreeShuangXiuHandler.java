package com.game.dazuo.handler;

import org.apache.log4j.Logger;

import com.game.dazuo.manager.PlayerDaZuoManager;
import com.game.dazuo.message.ReqAgreeShuangXiuMessage;
import com.game.player.structs.Player;
import com.game.command.Handler;

public class ReqAgreeShuangXiuHandler extends Handler{

	Logger log = Logger.getLogger(ReqAgreeShuangXiuHandler.class);

	public void action(){
		try{
			ReqAgreeShuangXiuMessage msg = (ReqAgreeShuangXiuMessage)this.getMessage();
			PlayerDaZuoManager.getInstacne().startShuangXiu((Player) getParameter(), msg.getOtherRoleId());
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}