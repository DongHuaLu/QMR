package com.game.player.handler;

import org.apache.log4j.Logger;

import com.game.player.message.ReqSyncPlayerHorseWeaponMessage;
import com.game.command.Handler;

public class ReqSyncPlayerHorseWeaponHandler extends Handler{

	Logger log = Logger.getLogger(ReqSyncPlayerHorseWeaponHandler.class);

	public void action(){
		try{
			ReqSyncPlayerHorseWeaponMessage msg = (ReqSyncPlayerHorseWeaponMessage)this.getMessage();
			//TODO 添加消息处理
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}