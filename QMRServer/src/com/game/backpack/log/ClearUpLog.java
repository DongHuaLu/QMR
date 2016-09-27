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
public class ClearUpLog extends BaseLogBean {
	private long roleId;
	private String before;
	private String after;
	private int type;
	private int sid;
	@Log(logField="sid",fieldType="int")
	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}
	@Override
	public TableCheckStepEnum getRollingStep() {
		return TableCheckStepEnum.DAY;
	}
	@Log(logField="roleId",fieldType="bigint")
	public long getRoleId() {
		return roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	@Log(logField="befores",fieldType="longtext")
	public String getBefore() {
		return before;
	}

	public void setBefore(String before) {
		this.before = before;
	}
	@Log(logField="afters",fieldType="longtext")
	public String getAfter() {
		return after;
	}

	public void setAfter(String after) {
		this.after = after;
	}
	@Log(logField="cleartype",fieldType="int")
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	@Override
	public void logToFile() {
		logger.error(buildSql());
	}
	private static final Logger logger=Logger.getLogger("ClearUpLog");
	
}
