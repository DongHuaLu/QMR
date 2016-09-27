package com.game.player.log;

import org.apache.log4j.Logger;

import com.game.dblog.TableCheckStepEnum;
import com.game.dblog.base.Log;
import com.game.dblog.bean.BaseLogBean;

/**
 * 
 * @author 赵聪慧
 * @2012-8-25 下午4:51:31
 */
public class DelRoleLog extends BaseLogBean {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(DelRoleLog.class);

	private long roleId;	//角色ID
	private String rolename;	//角色名
	private long actionId;	//事件ID
	private String ip;		//执行操作的IP地址
	private String userId;	//账号ID
	private String yyuserid;//运营商ID
	private int sid;
	
	@Override
	public TableCheckStepEnum getRollingStep() {
		return TableCheckStepEnum.MONTH;
	}

	@Log(fieldType="bigint",logField="roleid")
	public long getRoleId() {
		return roleId;
	}

	@Log(fieldType="varchar(100)",logField="rolename")
	public String getRolename() {
		return rolename;
	}

	@Log(fieldType="bigint",logField="actionid")
	public long getActionId() {
		return actionId;
	}

	@Log(fieldType="varchar(100)",logField="optip")
	public String getIp() {
		return ip;
	}

	@Log(fieldType="varchar(100)",logField="userid")
	public String getUserId() {
		return userId;
	}

	@Log(fieldType="varchar(100)",logField="yyuserid")
	public String getYyuserid() {
		return yyuserid;
	}

	@Log(fieldType="int",logField="sid")
	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public void setActionId(long actionId) {
		this.actionId = actionId;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setYyuserid(String yyuserid) {
		this.yyuserid = yyuserid;
	}

	@Override
	public void logToFile() {
		logger.info(buildSql());
	}

}
