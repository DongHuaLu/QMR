package com.game.utils;

import com.game.timer.TimerEvent;
import com.game.util.TimerUtil;

/**
 * 常用延时
 * @author 赵聪慧
 *
 */
public class CommonDelayTimerUtil extends TimerEvent {
	public static void addDelayTimer(int sec,Runnable able){
		TimerUtil.addTimerEvent(new CommonDelayTimerUtil(sec, able));
	}
	private Runnable action=null;
	private CommonDelayTimerUtil(int sec,Runnable runable) {
		super(1,sec*1000);
		action=runable;
	}
	@Override
	public void action() {
		action.run();
	}

}
