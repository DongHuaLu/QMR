package com.game.server.timer;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.monster.message.ReqMonsterDoubleNoticeMessage;
import com.game.sceneobj.manager.SceneobjManager;
import com.game.script.structs.ScriptEnum;
import com.game.server.script.IServerEventTimerScript;
import com.game.timer.TimerEvent;
import com.game.utils.MessageUtil;
import com.game.utils.TimeUtil;

public class ServerHeartTimer extends TimerEvent {

	protected Logger log = Logger.getLogger(ServerHeartTimer.class);
	
	protected int serverId;
	
	protected String serverWeb;
	
	protected int minute = 0;

	public ServerHeartTimer(int serverId, String serverWeb) {
		super(-1, 30*1000);
		this.serverId = serverId;
		this.serverWeb = serverWeb;
	}

	@Override
	public void action() {
		//30秒调用一次，确保每分钟不跳过
		int min = TimeUtil.getDayOfMin(System.currentTimeMillis());
		if (minute != min ) {
			minute = min;
		}else {
			return;
		}
		
		IServerEventTimerScript script = (IServerEventTimerScript) ManagerPool.scriptManager.getScript(ScriptEnum.SERVER_EVENT);
		if (script != null) {
			try {
				script.action(serverId, serverWeb);
			} catch (Exception e) {
				log.error(e, e);
			}
		} else {
//			log.error("服务器定时事件脚本不存在！");
		}
		
		
		
		//打坐双倍
		if(ManagerPool.dazuoManager.isDaZuoDouble() != null){
			ManagerPool.dazuoManager.setDaZuoDoubleStatus((byte) 1);
		}else {
			ManagerPool.dazuoManager.setDaZuoDoubleStatus((byte) 0);
		}
		
		//打普通怪双倍
		String douString = ManagerPool.monsterManager.isDaguaiDouble();
		if(douString != null){
			//if (ManagerPool.monsterManager.getDaguaiDoubleStatus() == 0) {
				ReqMonsterDoubleNoticeMessage wmsg = new ReqMonsterDoubleNoticeMessage();
				wmsg.setContent(douString);
				wmsg.setStatus((byte) 1);
				wmsg.setType((byte) 1);
				MessageUtil.send_to_world(wmsg);//发送到世界服务器，再进行广播，前端展示用
			//}
			ManagerPool.monsterManager.setDaguaiDoubleStatus((byte) 1);
		}else {
			if (ManagerPool.monsterManager.getDaguaiDoubleStatus() == 1) {
				ReqMonsterDoubleNoticeMessage wmsg = new ReqMonsterDoubleNoticeMessage();
				wmsg.setStatus((byte) 0);
				wmsg.setType((byte) 1);
				MessageUtil.send_to_world(wmsg);//发送到世界服务器，再进行广播，前端展示用
			}
			ManagerPool.monsterManager.setDaguaiDoubleStatus((byte) 0);
		}
		
		//攻城战循环调用
		ManagerPool.countryManager.loopcall();
		//金钱盗贼刷新调用
		SceneobjManager.getInstance().loopRefreshSceneObjSpecial(this.getClass().toString());
		
		//广播BOSS刷新时间倒计时（）
		//int min = TimeUtil.getDayOfMin(System.currentTimeMillis());
		if (min%5 == 0) {
			ManagerPool.monsterManager.loginTrackShow(null);
		}
	}
}
