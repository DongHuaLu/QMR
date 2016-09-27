package com.game.dblog.task;

import com.game.dblog.bean.BaseLogBean;

/**
 * 
 * @author 赵聪慧
 * @2012-8-20 下午10:25:11
 */
public class FileLogTask implements Runnable {
	BaseLogBean log;
	public FileLogTask(BaseLogBean log) {
		this.log=log;
	}

	@Override
	public void run() {
		log.logToFile();

	}
}
