package com.game.transactions.handler;

import org.apache.log4j.Logger;

import com.game.transactions.message.ReqCanreceiveYuanbaoMessage;
import com.game.command.Handler;

public class ReqCanreceiveYuanbaoHandler extends Handler{

	Logger log = Logger.getLogger(ReqCanreceiveYuanbaoHandler.class);

	public void action(){
		try{
			@SuppressWarnings("unused")
			ReqCanreceiveYuanbaoMessage msg = (ReqCanreceiveYuanbaoMessage)this.getMessage();
			//TODO 请求 可领取元宝消息
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}