package com.game.task.log;

import org.apache.log4j.Logger;

import com.game.dblog.TableCheckStepEnum;
import com.game.dblog.base.Log;
import com.game.dblog.bean.BaseLogBean;

public class TreasureHuntTaskFinshLog extends BaseLogBean {
	private long roleId;
	private long taskId;
	private long actionId;
	private String taskInfo;
	private String rewardsReceiveAble;
	private String beforeRewardsReceiveAble;
	private int sid;
	@Log(logField="sid",fieldType="int")
	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}
	
	@Log(fieldType="bigint",logField="roleid")
	public long getRoleId() {
		return roleId;
	}
	@Log(fieldType="bigint",logField="taskid")
	public long getTaskId() {
		return taskId;
	}
	@Log(fieldType="bigint",logField="actionid")
	public long getActionId() {
		return actionId;
	}
	@Log(fieldType="longtext",logField="taskinfo")
	public String getTaskInfo() {
		return taskInfo;
	}
	@Log(fieldType="longtext",logField="receiveable")
	public String getRewardsReceiveAble() {
		return rewardsReceiveAble;
	}
	@Log(fieldType="longtext",logField="beforereceiveable")
	public String getBeforeRewardsReceiveAble() {
		return beforeRewardsReceiveAble;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}
	public void setTaskId(long taskId) {
		this.taskId = taskId;
	}
	public void setActionId(long actionId) {
		this.actionId = actionId;
	}
	public void setTaskInfo(String taskInfo) {
		this.taskInfo = taskInfo;
	}
	public void setRewardsReceiveAble(String rewardsReceiveAble) {
		this.rewardsReceiveAble = rewardsReceiveAble;
	}
	public void setBeforeRewardsReceiveAble(String beforeRewardsReceiveAble) {
		this.beforeRewardsReceiveAble = beforeRewardsReceiveAble;
	}

	@Override
	public TableCheckStepEnum getRollingStep() {
		return TableCheckStepEnum.MONTH;
	}

	public void logToFile() {
		logger.error(buildSql());
	}
	private static final Logger logger=Logger.getLogger("TreasureHuntTaskFinshLog");
}
