package com.game.equip.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;
import com.game.equip.message.WearEquipMessage;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;

public class WearEquipHandler extends Handler{

	Logger log = Logger.getLogger(WearEquipHandler.class);

	public void action(){
		try{
			WearEquipMessage msg = (WearEquipMessage)this.getMessage();
			ManagerPool.equipManager.wear((Player)this.getParameter(), msg.getItemId());
		}catch(ClassCastException e){
			log.error(e,e);
		}
	}
}