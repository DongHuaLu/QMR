package com.game.backend.handler;

import org.apache.log4j.Logger;

import com.game.backend.manager.BackendManager;
import com.game.backend.message.ReqChangePlayerCurrencyMessage;
import com.game.command.Handler;
import com.game.player.manager.PlayerManager;
import com.game.player.structs.Player;

public class ReqChangePlayerCurrencyHandler extends Handler{

	Logger log = Logger.getLogger(ReqChangePlayerCurrencyHandler.class);

	public void action(){
		try{
			ReqChangePlayerCurrencyMessage msg = (ReqChangePlayerCurrencyMessage)this.getMessage();
			Player player = PlayerManager.getInstance().getPlayer(msg.getPersonId());
			int num = msg.getNum();
			if(msg.getType()==(byte)1){ //铜币
				BackendManager.getInstance().changemoney(player, num);
			}else if(msg.getType()==(byte)2){ //绑定元宝
				BackendManager.getInstance().changebindgold(player, num);
			}
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}