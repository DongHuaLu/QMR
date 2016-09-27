package com.game.task.log;

import org.apache.log4j.Logger;

import com.game.dblog.TableCheckStepEnum;
import com.game.dblog.base.Log;
import com.game.dblog.bean.BaseLogBean;
/**
 * 讨伐任务完成日志
 * @author 赵聪慧
 *
 */
public class ConquerTaskFinishLog extends BaseLogBean {
	private long taskid;	//任务 唯序号
	private long roleid;	//角色ID
	private String taskInfo;	//任务信息
	private String taskRewardsReceiveAble;//可领取区域物品
	private String beforeTaskRewardsReceiveable;//完成之前的可领取区域物品
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
	private static final Logger logger=Logger.getLogger("ConquerTaskFinishLog");
	@Override
	public TableCheckStepEnum getRollingStep() {
		return TableCheckStepEnum.MONTH;
	}
	@Log(fieldType="bigint",logField="taskid")
	public long getTaskid() {
		return taskid;
	}

	public void setTaskid(long taskid) {
		this.taskid = taskid;
	}
	@Log(fieldType="bigint",logField="roleId")
	public long getRoleid() {
		return roleid;
	}

	public void setRoleid(long roleid) {
		this.roleid = roleid;
	}
	@Log(fieldType="longtext",logField="taskinfo")
	public String getTaskInfo() {
		return taskInfo;
	}

	public void setTaskInfo(String taskInfo) {
		this.taskInfo = taskInfo;
	}
	@Log(fieldType="longtext",logField="receiveAbleInfo")
	public String getTaskRewardsReceiveAble() {
		return taskRewardsReceiveAble;
	}

	public void setTaskRewardsReceiveAble(String taskRewardsReceiveAble) {
		this.taskRewardsReceiveAble = taskRewardsReceiveAble;
	}
	@Log(fieldType="longtext",logField="beforereceiveAbleInfo")
	public String getBeforeTaskRewardsReceiveable() {
		return beforeTaskRewardsReceiveable;
	}
	public void setBeforeTaskRewardsReceiveable(String beforeTaskRewardsReceiveable) {
		this.beforeTaskRewardsReceiveable = beforeTaskRewardsReceiveable;
	}
	
}
