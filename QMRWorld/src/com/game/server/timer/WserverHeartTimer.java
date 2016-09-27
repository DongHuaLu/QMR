package com.game.server.timer;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.script.structs.ScriptEnum;
import com.game.server.script.IServerEventTimerScript;
import com.game.timer.TimerEvent;

public class WserverHeartTimer extends TimerEvent {

	protected Logger log = Logger.getLogger(WserverHeartTimer.class);
	


	public WserverHeartTimer() {
		super(-1, 60*1000);
	}

	@Override
	public void action() {
		IServerEventTimerScript script = (IServerEventTimerScript) ManagerPool.scriptManager.getScript(ScriptEnum.SERVER_EVENT);
		if (script != null) {
			try {
				script.action() ;
			} catch (Exception e) {
				log.error(e, e);
			}
		} else {
//			log.error("服务器定时事件脚本不存在！");
		}
		
		
		
		
		
		
	}
}
