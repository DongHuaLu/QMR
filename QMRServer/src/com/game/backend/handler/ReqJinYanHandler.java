package com.game.backend.handler;

import org.apache.log4j.Logger;

import com.game.backend.message.ReqJinYanMessage;
import com.game.command.Handler;
import com.game.player.manager.PlayerManager;
import com.game.player.structs.Player;

public class ReqJinYanHandler extends Handler{

	Logger log = Logger.getLogger(ReqJinYanHandler.class);

	public void action(){
		try{
			ReqJinYanMessage msg = (ReqJinYanMessage)this.getMessage();
			Player p = PlayerManager.getInstance().getPlayer(msg.getPlayerId());
			if(p!=null){
				p.setAutojinyancount(p.getAutojinyancount()+1);
				p.setProhibitChatTime(msg.getTimes());
				p.setStartProhibitChatTime(msg.getStartTime());
				if(msg.getTimes()==0){
					p.getChatCount().clear();
				}
			}
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}