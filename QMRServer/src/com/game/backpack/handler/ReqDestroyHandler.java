package com.game.backpack.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;
/**
 * 物品销毁  弃用 
 * @author 赵聪慧
 *
 */
public class ReqDestroyHandler extends Handler{

	Logger log = Logger.getLogger(ReqDestroyHandler.class);

	public void action(){
		try{
//			DestroyMessage msg = (DestroyMessage)this.getMessage();
//			DropManager.playerDiscard(msg.getRoleId(),msg.getCellId());			
		}catch(ClassCastException e){
			log.error(e,e);
		}
	}
}