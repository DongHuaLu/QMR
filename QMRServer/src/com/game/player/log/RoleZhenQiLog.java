package com.game.player.log;

import com.game.dblog.TableCheckStepEnum;
import com.game.dblog.base.Log;
import com.game.dblog.bean.BaseLogBean;

/**
 * 
 * @author 赵聪慧
 * @2012-11-14 上午11:16:43
 */
public class RoleZhenQiLog extends BaseLogBean{
	
	private long beforenum;	
	private long changenum;
	private long afternum;
	private long roleid;
	private String userid;
	private String username;
	private int sid;
	private String reason;

	@Override
	public void logToFile() {
		logger.error(buildSql());
	}
	@Log(fieldType="bigint",logField="beforenum")
	public long getBeforenum() {
		return beforenum;
	}

	@Log(fieldType="bigint",logField="changenum")
	public long getChangenum() {
		return changenum;
	}
	@Log(fieldType="bigint",logField="afternum")
	public long getAfternum() {
		return afternum;
	}
	@Log(fieldType="bigint",logField="roleid")
	public long getRoleid() {
		return roleid;
	}
	@Log(fieldType="varchar(255)",logField="userid")
	public String getUserid() {
		return userid;
	}
	@Log(fieldType="varchar(255)",logField="username")
	public String getUsername() {
		return username;
	}

	@Log(fieldType="int",logField="sid")
	public int getSid() {
		return sid;
	}

	@Log(fieldType="varchar(255)",logField="reason")
	public String getReason() {
		return reason;
	}

	public void setBeforenum(long beforenum) {
		this.beforenum = beforenum;
	}

	public void setChangenum(long changenum) {
		this.changenum = changenum;
	}

	public void setAfternum(long afternum) {
		this.afternum = afternum;
	}

	public void setRoleid(long roleid) {
		this.roleid = roleid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	@Override
	public TableCheckStepEnum getRollingStep() {
		return TableCheckStepEnum.DAY;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
}
