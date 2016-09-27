package com.game.util;

import org.apache.log4j.Logger;

public class TimeUtil {

	protected static Logger log = Logger.getLogger(TimeUtil.class);

	/**
	 * 获取指定时间到现在的时间数（毫秒）
	 *
	 * @param time
	 * @return
	 */
	public static long getDurationToNow(long time) {
		return System.currentTimeMillis() - time;
	}
}
