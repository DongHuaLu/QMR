package com.game.task.log;

import org.apache.log4j.Logger;

import com.game.dblog.TableCheckStepEnum;
import com.game.dblog.base.Log;
import com.game.dblog.bean.BaseLogBean;
import com.game.server.log.ServerOnlineCountLog;

public class DailyTaskUpAchieveLog extends BaseLogBean {
	private long roleid;
	private long taskid;
	private int beforeachieveid;
	private int afterachieveid;
	private String beforeTaskInfo;
	private String afterTaskInfo;
	private int sid;
	@Log(logField="sid",fieldType="int")
	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}
	@Override
	public void logToFile() {
		logger.error(buildSql());
	}
	private static final Logger logger=Logger.getLogger("DailyTaskUpAchieveLog");
	@Override
	public TableCheckStepEnum getRollingStep() {
		return TableCheckStepEnum.MONTH;
	}
	@Log(fieldType="bigint",logField="roleId")
	public long getRoleid() {
		return roleid;
	}

	public void setRoleid(long roleid) {
		this.roleid = roleid;
	}
	
	@Log(fieldType="bigint",logField="taskid")
	public long getTaskid() {
		return taskid;
	}
	
	public void setTaskid(long taskid) {
		this.taskid = taskid;
	}
	
	@Log(fieldType="int",logField="beforeachieveid")
	public int getBeforeachieveid() {
		return beforeachieveid;
	}

	public void setBeforeachieveid(int beforeachieveid) {
		this.beforeachieveid = beforeachieveid;
	}

	@Log(fieldType="int",logField="afterachieveid")
	public int getAfterachieveid() {
		return afterachieveid;
	}

	public void setAfterachieveid(int afterachieveid) {
		this.afterachieveid = afterachieveid;
	}
	@Log(fieldType="longtext",logField="beforeTaskInfo")
	public String getBeforeTaskInfo() {
		return beforeTaskInfo;
	}

	public void setBeforeTaskInfo(String beforeTaskInfo) {
		this.beforeTaskInfo = beforeTaskInfo;
	}
	@Log(fieldType="longtext",logField="afterTaskInfo")
	public String getAfterTaskInfo() {
		return afterTaskInfo;
	}

	public void setAfterTaskInfo(String afterTaskInfo) {
		this.afterTaskInfo = afterTaskInfo;
	}

}
