package com.game.server.timer;

import org.apache.log4j.Logger;

import com.game.server.WorldServer;
import com.game.server.message.ReqCloseForGameMessage;
import com.game.server.message.ReqCloseForGateMessage;
import com.game.server.message.ResCloseServerMessage;
import com.game.timer.TimerEvent;
import com.game.utils.MessageUtil;

public class CloseTimer extends TimerEvent {

	protected Logger log = Logger.getLogger(CloseTimer.class);
	
	private long cooldown;
	
	public CloseTimer() {
		super(-1, 1000);
	}
	
	@Override
	public void action() {
		if(WorldServer.getInstance().getCloseTime()==0) return;
		if(System.currentTimeMillis() < cooldown) return;
		long remain = WorldServer.getInstance().getCloseTime() - System.currentTimeMillis();
		
		log.error("关服剩余时间:" + remain);
		
		if(remain < -3 * 60 * 1000){
			System.exit(0);
			return;
		}else if(remain < -2 * 60 * 1000){
			ReqCloseForGameMessage msg= new ReqCloseForGameMessage();
			MessageUtil.send_to_game(msg);
			return;
		}else if(remain < 0){
			ReqCloseForGateMessage msg= new ReqCloseForGateMessage();
			MessageUtil.send_to_gate(msg);
			return;
		}
		
		if(remain > 30 * 60 * 1000){
			cooldown = System.currentTimeMillis() + 10 * 60 * 1000;
		}else if(remain > 10 * 60 * 1000){
			cooldown = System.currentTimeMillis() + 5 * 60 * 1000;
		}else if(remain > 1 * 60 * 1000){
			cooldown = System.currentTimeMillis() + 1 * 60 * 1000;
		}else if(remain > 30 * 1000){
			cooldown = System.currentTimeMillis() + 30 * 1000;
		}else if(remain > 10 * 1000){
			cooldown = System.currentTimeMillis() + 10 * 1000;
		}
		
		sendCloseMessage(remain);
	}

	private void sendCloseMessage(long remain){
		ResCloseServerMessage msg = new ResCloseServerMessage();
		msg.setTime((int)(remain / 1000));
		MessageUtil.tell_world_message(msg);
	}
}
