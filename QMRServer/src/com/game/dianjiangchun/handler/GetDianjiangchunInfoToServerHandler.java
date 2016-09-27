package com.game.dianjiangchun.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;
import com.game.dianjiangchun.manager.DianjiangchunManager;
import com.game.player.structs.Player;

public class GetDianjiangchunInfoToServerHandler extends Handler{

	Logger log = Logger.getLogger(GetDianjiangchunInfoToServerHandler.class);

	public void action(){
		try{
//			GetDianjiangchunInfoToServerMessage msg = (GetDianjiangchunInfoToServerMessage)this.getMessage();
			DianjiangchunManager.getInstance().GetDianjiangchunInfo((Player)this.getParameter());
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}