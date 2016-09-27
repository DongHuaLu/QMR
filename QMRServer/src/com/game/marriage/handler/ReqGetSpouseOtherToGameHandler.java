package com.game.marriage.handler;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.marriage.message.ReqGetSpouseOtherToGameMessage;
import com.game.player.structs.Player;
import com.game.command.Handler;

public class ReqGetSpouseOtherToGameHandler extends Handler{

	Logger log = Logger.getLogger(ReqGetSpouseOtherToGameHandler.class);

	public void action(){
		try{
			ReqGetSpouseOtherToGameMessage msg = (ReqGetSpouseOtherToGameMessage)this.getMessage();
			ManagerPool.marriageManager.stReqGetSpouseOtherToGameMessage((Player)this.getParameter(), msg);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}