package com.game.player.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;
import com.game.player.manager.PlayerManager;
import com.game.player.message.ReqReportPlayerSpeedMessage;
import com.game.player.structs.Player;

public class ReqReportPlayerSpeedHandler extends Handler{

	Logger log = Logger.getLogger(ReqReportPlayerSpeedHandler.class);

	public void action(){
		try{
			ReqReportPlayerSpeedMessage msg = (ReqReportPlayerSpeedMessage)this.getMessage();
			Player player = (Player)this.getParameter();
			long targetid = msg.getTargetid();
			
			PlayerManager.getInstance().reportPlayerSpeed(player, targetid);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}