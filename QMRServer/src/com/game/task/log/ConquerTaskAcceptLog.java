package com.game.task.log;

import org.apache.log4j.Logger;

import com.game.dblog.TableCheckStepEnum;
import com.game.dblog.base.Log;
import com.game.dblog.bean.BaseLogBean;
/**
 * 讨伐任务接受日志
 * @author 赵聪慧
 *
 */
public class ConquerTaskAcceptLog extends BaseLogBean {
	private long roleId;	//角色ID
	private long taskId;	//任务唯一ID
	private String taskInfo;	//任务内容
	private int taskcount;	//当日己接次数
	private int taskmodelId;	//讨伐任务模型
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
	private static final Logger logger=Logger.getLogger("ConquerTaskAcceptLog");
	
	
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

	@Log(fieldType="bigint",logField="taskid")
	public long getTaskId() {
		return taskId;
	}

	public void setTaskId(long taskId) {
		this.taskId = taskId;
	}
	
	@Log(fieldType="longtext",logField="taskinfo")
	public String getTaskInfo() {
		return taskInfo;
	}

	public void setTaskInfo(String taskInfo) {
		this.taskInfo = taskInfo;
	}

	@Log(fieldType="int",logField="taskcount")
	public int getTaskcount() {
		return taskcount;
	}

	public void setTaskcount(int taskcount) {
		this.taskcount = taskcount;
	}
	
	@Log(fieldType="int",logField="taskModelId")
	public int getTaskmodelId() {
		return taskmodelId;
	}

	public void setTaskmodelId(int taskmodelId) {
		this.taskmodelId = taskmodelId;
	}
	
}
