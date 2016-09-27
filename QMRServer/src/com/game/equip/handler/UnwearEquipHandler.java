package com.game.equip.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;
import com.game.equip.message.UnwearEquipMessage;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;

public class UnwearEquipHandler extends Handler{

	Logger log = Logger.getLogger(UnwearEquipHandler.class);

	public void action(){
		try{
			UnwearEquipMessage msg = (UnwearEquipMessage)this.getMessage();
			ManagerPool.equipManager.unwear((Player)this.getParameter(), msg.getItemId());
		}catch(ClassCastException e){
			log.error(e,e);
		}
	}
}