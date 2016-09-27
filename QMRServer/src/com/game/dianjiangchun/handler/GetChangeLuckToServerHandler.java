package com.game.dianjiangchun.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;
import com.game.dianjiangchun.manager.DianjiangchunManager;
import com.game.player.structs.Player;

public class GetChangeLuckToServerHandler extends Handler{

	Logger log = Logger.getLogger(GetChangeLuckToServerHandler.class);

	public void action(){
		try{
//			GetChangeLuckToServerMessage msg = (GetChangeLuckToServerMessage)this.getMessage();
			DianjiangchunManager.getInstance().GetChangeLuck((Player)this.getParameter());
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}