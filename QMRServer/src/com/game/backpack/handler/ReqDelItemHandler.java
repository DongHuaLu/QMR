package com.game.backpack.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;
/**
 * @author 赵聪慧
 *
 */
public class ReqDelItemHandler extends Handler{

	Logger log = Logger.getLogger(ReqDelItemHandler.class);

	public void action(){
		try{
//			ReqDelItemMessage msg = (ReqDelItemMessage)this.getMessage();
			
//			ManagerPool.backpackManager.removeItem((Player)this.getParameter(), msg.getItemId());
		}catch(ClassCastException e){
			log.error(e,e);
		}
	}
}