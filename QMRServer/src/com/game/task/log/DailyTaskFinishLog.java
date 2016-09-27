package com.game.task.log;

import org.apache.log4j.Logger;

import com.game.dblog.TableCheckStepEnum;
import com.game.dblog.base.Log;
import com.game.dblog.bean.BaseLogBean;
import com.game.server.log.ServerOnlineCountLog;

public class DailyTaskFinishLog extends BaseLogBean {
	private long roleId;
	private long taskId;
	private int condmodelId;//条件模型Id
	private int rewardsId;//奖励id
	private int extrarewardsId;//20环额外奖励
	private String taskInfo;//任务内容
	private int finshtype;//完成类型
	private int loopcount;//环数
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
	private static final Logger logger=Logger.getLogger("DailyTaskFinishLog");
	@Override
	public TableCheckStepEnum getRollingStep() {
		return TableCheckStepEnum.MONTH;
	}
	@Log(fieldType="bigint",logField="roleId")
	public long getRoleId() {
		return roleId;
	}
	
	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}
	@Log(fieldType="bigint",logField="taskId")
	public long getTaskId() {
		return taskId;
	}
	public void setTaskId(long taskId) {
		this.taskId = taskId;
	}
	
	@Log(fieldType="int",logField="condmodelid")
	public int getCondmodelId() {
		return condmodelId;
	}
	public void setCondmodelId(int condmodelId) {
		this.condmodelId = condmodelId;
	}
	@Log(fieldType="int",logField="rewardsmodelid")
	public int getRewardsId() {
		return rewardsId;
	}
	public void setRewardsId(int rewardsId) {
		this.rewardsId = rewardsId;
	}
	@Log(fieldType="longtext",logField="taskinfo")
	public String getTaskInfo() {
		return taskInfo;
	}
	public void setTaskInfo(String taskInfo) {
		this.taskInfo = taskInfo;
	}
	@Log(fieldType="int",logField="finshtype")
	public int getFinshtype() {
		return finshtype;
	}
	public void setFinshtype(int finshtype) {
		this.finshtype = finshtype;
	}
	@Log(fieldType="int",logField="extrarewardsid")
	public int getExtrarewardsId() {
		return extrarewardsId;
	}
	public void setExtrarewardsId(int extrarewardsId) {
		this.extrarewardsId = extrarewardsId;
	}
	@Log(fieldType="int",logField="loopcount")
	public int getLoopcount() {
		return loopcount;
	}
	public void setLoopcount(int loopcount) {
		this.loopcount = loopcount;
	}
}
