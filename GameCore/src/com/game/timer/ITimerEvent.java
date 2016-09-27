package com.game.timer;

import com.game.command.ICommand;

/** 
 * @author heyang E-mail: szy_heyang@163.com
 * 
 * @version 1.0.0
 * 
 * @since 2011-5-4
 * 
 * 计时事件接口
 */
public interface ITimerEvent extends ICommand {
	//获得剩余时间
	public long remain();
}
