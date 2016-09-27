package com.game.chat.timer;

import com.game.timer.TimerEvent;
import com.game.utils.TimeUtil;

public class BulletinTimer extends TimerEvent {
	int serverid;
	int lineid;
	int mapid;
	long lastSend;
	public BulletinTimer(int serverid,int lineid,int mapid) {
		super(-1,200);
		this.serverid=serverid;
		this.lineid=lineid;
		this.mapid=mapid;
		lastSend=0;
	}

	@Override
	public void action() {
		
//		 循环定时公告 
//		if(TimeUtil.isNowSatisfiedBy("")){
//			WorldNoticeMessage msg = new WorldNoticeMessage();
//			msg.setContent("这是定时公告");
//			MessageUtil.tell_map_message(serverid, lineid, mapid, msg);;
//		}
	}

}
