package com.game.task.log;

import org.apache.log4j.Logger;

import com.game.dblog.TableCheckStepEnum;
import com.game.dblog.bean.BaseLogBean;
import com.game.server.log.ServerOnlineCountLog;

/**
 * 
 * @author 赵聪慧
 * @2012-8-18 上午12:06:24
 */
public class TaskCountZeroClockClearLog extends BaseLogBean {
	private long roleid;
	private int beforeDailyCount;
	private int beforeConquerCount;
	private int beforeDaydevourcount;
	private int afterDailyCount;
	private int afterConquerCount;
	private int afterDaydevourcount;
	@Override
	public void logToFile() {
		logger.error(buildSql());
	}
	private static final Logger logger=Logger.getLogger("ServerOnlineCountLog");
	

	@Override
	public TableCheckStepEnum getRollingStep() {
		// TODO Auto-generated method stub
		return TableCheckStepEnum.YEAR;
	}

}
