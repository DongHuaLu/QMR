package com.game.dazuo.handler;

import org.apache.log4j.Logger;

import com.game.dazuo.manager.PlayerDaZuoManager;
import com.game.dazuo.message.ReqRefuseShuangXiuMessage;
import com.game.player.manager.PlayerManager;
import com.game.player.structs.Player;
import com.game.command.Handler;

public class ReqRefuseShuangXiuHandler extends Handler{

	Logger log = Logger.getLogger(ReqRefuseShuangXiuHandler.class);

	public void action(){
		try{
			ReqRefuseShuangXiuMessage msg = (ReqRefuseShuangXiuMessage)this.getMessage();
//			PlayerDaZuoManager.getInstacne().startShuangXiu((Player) getParameter(), msg.getOtherRoleId());
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}