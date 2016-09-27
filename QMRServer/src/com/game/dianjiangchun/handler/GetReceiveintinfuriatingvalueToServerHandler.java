package com.game.dianjiangchun.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;
import com.game.dianjiangchun.manager.DianjiangchunManager;
import com.game.player.structs.Player;

public class GetReceiveintinfuriatingvalueToServerHandler extends Handler{

	Logger log = Logger.getLogger(GetReceiveintinfuriatingvalueToServerHandler.class);

	public void action(){
		try{
//			GetReceiveintinfuriatingvalueToServerMessage msg = (GetReceiveintinfuriatingvalueToServerMessage)this.getMessage();
			DianjiangchunManager.getInstance().GetReceiveintinfuriatingvalue((Player)this.getParameter());
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}