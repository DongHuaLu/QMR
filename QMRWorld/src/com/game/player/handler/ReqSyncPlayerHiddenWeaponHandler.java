package com.game.player.handler;

import org.apache.log4j.Logger;

import com.game.player.message.ReqSyncPlayerHiddenWeaponMessage;
import com.game.command.Handler;

public class ReqSyncPlayerHiddenWeaponHandler extends Handler{

	Logger log = Logger.getLogger(ReqSyncPlayerHiddenWeaponHandler.class);

	public void action(){
		try{
			ReqSyncPlayerHiddenWeaponMessage msg = (ReqSyncPlayerHiddenWeaponMessage)this.getMessage();
			//TODO 添加消息处理
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}