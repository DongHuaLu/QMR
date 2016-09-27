package com.game.task.log;

import com.game.dblog.TableCheckStepEnum;
import com.game.dblog.base.Log;
import com.game.dblog.bean.BaseLogBean;
import org.apache.log4j.Logger;

/**
 * 军衔任务日志
 *
 * @author 杨鸿岚
 */
public class RankTaskLog extends BaseLogBean{

	private static final Logger logger=Logger.getLogger("RankTaskLog");
	
	@Override
	public TableCheckStepEnum getRollingStep() {
		return TableCheckStepEnum.MONTH;
	}

	@Override
	public void logToFile() {
		logger.error(buildSql());
	}
	
	private long   roleId;
	private String userId;
	private int status;	//1 接受 2 完成
	private int   taskmodelId;
	private String taskInfo;
	private int rankpoint;
	private int sid;
	@Log(logField="sid",fieldType="int")
	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	@Log(logField="rankpoint",fieldType="int")
	public int getRankpoint() {
		return rankpoint;
	}

	public void setRankpoint(int rankpoint) {
		this.rankpoint = rankpoint;
	}

	@Log(logField="roleId",fieldType="bigint")
	public long getRoleId() {
		return roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	@Log(logField="status",fieldType="int")
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Log(logField="taskInfo",fieldType="longtext")
	public String getTaskInfo() {
		return taskInfo;
	}

	public void setTaskInfo(String taskInfo) {
		this.taskInfo = taskInfo;
	}

	@Log(logField="taskmodelId",fieldType="int")
	public int getTaskmodelId() {
		return taskmodelId;
	}

	public void setTaskmodelId(int taskmodelId) {
		this.taskmodelId = taskmodelId;
	}

	@Log(logField="userid",fieldType="varchar(512)")
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
