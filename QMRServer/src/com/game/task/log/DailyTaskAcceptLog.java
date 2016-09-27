package com.game.task.log;

import org.apache.log4j.Logger;

import com.game.dblog.TableCheckStepEnum;
import com.game.dblog.base.Log;
import com.game.dblog.bean.BaseLogBean;

public class DailyTaskAcceptLog extends BaseLogBean {

	private long roleId;	//角色ID
	private long taskId;	//任务唯一ID
	private String taskInfo;	//任务内容
	private int taskcount;	//当日己接次数
	private int condmodelId;	//任务条件模型
	private int rewardsmodelId;	//任务奖励模型
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
	private static final Logger logger=Logger.getLogger("DailyTaskAcceptLog");
	@Override
	public TableCheckStepEnum getRollingStep() {
		return TableCheckStepEnum.MONTH;
	}
	@Log(logField="roleId",fieldType="bigint")
	public long getRoleId() {
		return roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}
	@Log(logField="taskId",fieldType="bigint")
	public long getTaskId() {
		return taskId;
	}

	public void setTaskId(long taskId) {
		this.taskId = taskId;
	}
	@Log(logField="taskinfo",fieldType="longtext")
	public String getTaskInfo() {
		return taskInfo;
	}

	public void setTaskInfo(String taskInfo) {
		this.taskInfo = taskInfo;
	}
	@Log(logField="taskCount",fieldType="int")
	public int getTaskcount() {
		return taskcount;
	}

	public void setTaskcount(int taskcount) {
		this.taskcount = taskcount;
	}
	@Log(logField="condmodelid",fieldType="int")
	public int getCondmodelId() {
		return condmodelId;
	}

	public void setCondmodelId(int condmodelId) {
		this.condmodelId = condmodelId;
	}
	@Log(logField="rewardsmodelid",fieldType="int")
	public int getRewardsmodelId() {
		return rewardsmodelId;
	}

	public void setRewardsmodelId(int rewardsmodelId) {
		this.rewardsmodelId = rewardsmodelId;
	}
}
