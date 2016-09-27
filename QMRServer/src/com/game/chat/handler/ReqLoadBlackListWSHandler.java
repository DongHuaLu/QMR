package com.game.chat.handler;

import org.apache.log4j.Logger;

import com.game.chat.manager.ChatManager;
import com.game.chat.message.ReqLoadBlackListWSMessage;
import com.game.command.Handler;

public class ReqLoadBlackListWSHandler extends Handler{

	Logger log = Logger.getLogger(ReqLoadBlackListWSHandler.class);

	public void action(){
		try{
			ReqLoadBlackListWSMessage msg = (ReqLoadBlackListWSMessage)this.getMessage();
			int type = msg.getBlacktype();
			if(type==1 || type==2){
				ChatManager.getInstance().reloadChatBlackList(type); //重加载聊天黑名单
			}else if(type == 3){ //重加载测试GM IP
				
			}
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}