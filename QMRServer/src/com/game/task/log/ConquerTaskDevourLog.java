package com.game.task.log;

import org.apache.log4j.Logger;

import com.game.dblog.TableCheckStepEnum;
import com.game.dblog.base.Log;
import com.game.dblog.bean.BaseLogBean;

/**
 * 讨伐任务吞噬日志
 * @author 赵聪慧
 *
 */
public class ConquerTaskDevourLog extends BaseLogBean {
	private long roleId;	//角色ID	
	private long resumeTaskId;//　被吞噬的任务ID
	private int devourCount;	//当日吞噬次数
	private long taskId;		//任务ID
	private int taskmodelId;	//任务模型ID
	private String resumeTaskInfo;	//被吞噬的任务信息
	private String beforeTaskInfo;	//吞噬前任务信息
	private String afterTaskInfo;	//吞噬后任务信息
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
	private static final Logger logger=Logger.getLogger("ConquerTaskDevourLog");
	@Override
	public TableCheckStepEnum getRollingStep() {
		return TableCheckStepEnum.MONTH;
	}

	@Log(fieldType="bigint",logField="roleid")
	public long getRoleId() {
		return roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}
	
	@Log(fieldType="bigint",logField="resumeid")
	public long getResumeTaskId() {
		return resumeTaskId;
	}

	public void setResumeTaskId(long resumeTaskId) {
		this.resumeTaskId = resumeTaskId;
	}
	
	@Log(fieldType="int",logField="devourcount")
	public int getDevourCount() {
		return devourCount;
	}

	public void setDevourCount(int devourCount) {
		this.devourCount = devourCount;
	}

	@Log(fieldType="longtext",logField="resumeTaskInfo")
	public String getResumeTaskInfo() {
		return resumeTaskInfo;
	}

	public void setResumeTaskInfo(String resumeTaskInfo) {
		this.resumeTaskInfo = resumeTaskInfo;
	}
	
	@Log(fieldType="bigint",logField="taskid")
	public long getTaskId() {
		return taskId;
	}

	public void setTaskId(long taskId) {
		this.taskId = taskId;
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
	@Log(fieldType="int",logField="taskmodelId")
	public int getTaskmodelId() {
		return taskmodelId;
	}

	public void setTaskmodelId(int taskmodelId) {
		this.taskmodelId = taskmodelId;
	}
	
}
