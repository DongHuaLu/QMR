package com.game.hiddenweapon.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;

public class ReqWearHiddenWeaponStateHandler extends Handler{

	Logger log = Logger.getLogger(ReqWearHiddenWeaponStateHandler.class);

	public void action(){
		try{
//			ReqWearHiddenWeaponStateMessage msg = (ReqWearHiddenWeaponStateMessage)this.getMessage();
//			if(msg.getStatus()==0){
//				ManagerPool.hiddenWeaponManager.unwearHiddenWeapon((Player) this.getParameter());
//			}else{
//				ManagerPool.hiddenWeaponManager.wearHiddenWeapon((Player) this.getParameter(), msg.getCurlayer());
//			}
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}