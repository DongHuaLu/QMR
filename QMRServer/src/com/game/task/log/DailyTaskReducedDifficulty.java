package com.game.task.log;

import org.apache.log4j.Logger;

import com.game.dblog.TableCheckStepEnum;
import com.game.dblog.base.Log;
import com.game.dblog.bean.BaseLogBean;
import com.game.server.log.ServerOnlineCountLog;

public class DailyTaskReducedDifficulty extends BaseLogBean {
	private long roleid;
	private long taskid;
	private int beforeCondModelId;
	private int afterCondModelId;
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
	private static final Logger logger=Logger.getLogger("DailyTaskReducedDifficulty");
	@Override
	public TableCheckStepEnum getRollingStep() {
		return TableCheckStepEnum.MONTH;
	}
	@Log(fieldType="bigint",logField="roleid")
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
	@Log(fieldType="int",logField="beforecondid")
	public int getBeforeCondModelId() {
		return beforeCondModelId;
	}
	
	public void setBeforeCondModelId(int beforeCondModelId) {
		this.beforeCondModelId = beforeCondModelId;
	}
	@Log(fieldType="int",logField="aftercondid")
	public int getAfterCondModelId() {
		return afterCondModelId;
	}
	public void setAfterCondModelId(int afterCondModelId) {
		this.afterCondModelId = afterCondModelId;
	}
	@Log(fieldType="longtext",logField="beforetaskinfo")
	public String getBeforeTaskInfo() {
		return beforeTaskInfo;
	}
	public void setBeforeTaskInfo(String beforeTaskInfo) {
		this.beforeTaskInfo = beforeTaskInfo;
	}
	@Log(fieldType="longtext",logField="aftertaskinfo")
	public String getAfterTaskInfo() {
		return afterTaskInfo;
	}
	public void setAfterTaskInfo(String afterTaskInfo) {
		this.afterTaskInfo = afterTaskInfo;
	}
}
