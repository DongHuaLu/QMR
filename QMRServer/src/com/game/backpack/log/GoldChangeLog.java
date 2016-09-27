package com.game.backpack.log;

import org.apache.log4j.Logger;

import com.game.dblog.TableCheckStepEnum;
import com.game.dblog.base.Log;
import com.game.dblog.bean.BaseLogBean;
/**
 * 
 * @author 赵聪慧
 *
 */
public class GoldChangeLog extends BaseLogBean {
	private long roleId;
	private int num;
	private int reason;
	private int beforenum;
	private int afternum;
	private long actionId;
	private String userId;
	private int sid;
	@Log(logField="sid",fieldType="int")
	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}
	@Log(logField="userid",fieldType="varchar(512)")
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	@Override
	public TableCheckStepEnum getRollingStep() {
		return TableCheckStepEnum.MONTH;
	}
	@Log(logField = "num", fieldType = "int")
	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	@Log(logField = "reason", fieldType = "int")
	public int getReason() {
		return reason;
	}

	public void setReason(int reason) {
		this.reason = reason;
	}

	@Log(logField = "beforenum", fieldType = "int")
	public int getBeforenum() {
		return beforenum;
	}

	public void setBeforenum(int beforenum) {
		this.beforenum = beforenum;
	}

	@Log(logField = "afternum", fieldType = "int")
	public int getAfternum() {
		return afternum;
	}

	public void setAfternum(int afternum) {
		this.afternum = afternum;
	}
	@Log(logField="roleId",fieldType="bigint")
	public long getRoleId() {
		return roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}
	
	public long setActionId(long action) {
		return actionId=action;
	}
	@Log(logField="actionId",fieldType="bigint")
	public long getActionId() {
		return actionId;
	}
	@Override
	public void logToFile() {
		logger.error(buildSql());
	}
	private static final Logger logger=Logger.getLogger("GoldChangeLog");
	
}
