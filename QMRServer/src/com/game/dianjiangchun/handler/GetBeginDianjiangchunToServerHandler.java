package com.game.dianjiangchun.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;
import com.game.dianjiangchun.manager.DianjiangchunManager;
import com.game.player.structs.Player;

public class GetBeginDianjiangchunToServerHandler extends Handler{

	Logger log = Logger.getLogger(GetBeginDianjiangchunToServerHandler.class);

	public void action(){
		try{
//			GetBeginDianjiangchunToServerMessage msg = (GetBeginDianjiangchunToServerMessage)this.getMessage();
			DianjiangchunManager.getInstance().GetBeginDianjiangchun((Player)this.getParameter());
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}