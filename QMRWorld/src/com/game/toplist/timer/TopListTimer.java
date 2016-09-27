package com.game.toplist.timer;

import com.game.timer.TimerEvent;

/**
 * 排行榜崇拜次数更新Timer
 * @author 杨鸿岚
 */
public class TopListTimer extends TimerEvent{
	//private Logger log = Logger.getLogger(TopListTimer.class);
	public TopListTimer() {
		super(-1, 1000 * 30);
	}

	@Override
	public void action() {
		//PlayerManager.getInstance().clearPlayerWorldInfo();
	}
}
