package com.game.bugtrace.log;

import org.apache.log4j.Logger;

import com.game.dblog.TableCheckStepEnum;
import com.game.dblog.base.Log;
import com.game.dblog.bean.BaseLogBean;

public class BugTraceLog extends BaseLogBean {
	private static final Logger logger=Logger.getLogger("BugTraceLog");
	
	private String agent;    // 平台
	private int    zone;     // 区
	private String userid;   // 用户id
	private String username; // 用户名
	private long role;   // 角色ID
	private String rolename; // 角色名
	private String problem;  // 追踪问题
	private String detail;   // 详细描述
	
	@Log(logField="userid",fieldType="varchar(100)")
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	@Log(logField="username",fieldType="varchar(100)")
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	@Log(logField="roleid",fieldType="bigint")
	public long getRole() {
		return role;
	}
	public void setRole(long role) {
		this.role = role;
	}
	
	@Log(logField="rolename",fieldType="varchar(100)")
	public String getRolename() {
		return rolename;
	}
	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
	
	@Log(logField="problem",fieldType="varchar(100)")
	public String getProblem() {
		return problem;
	}
	public void setProblem(String problem) {
		this.problem = problem;
	}
	
	@Log(logField="detail",fieldType="varchar(2000)")
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	

	@Log(logField="agent",fieldType="varchar(20)")
	public String getAgent() {
		return agent;
	}
	public void setAgent(String agent) {
		this.agent = agent;
	}
	
	@Log(logField="zone",fieldType="int")
	public int getZone() {
		return zone;
	}
	public void setZone(int zone) {
		this.zone = zone;
	}
	
	@Override
	public TableCheckStepEnum getRollingStep() {
		return TableCheckStepEnum.YEAR;
	}
	
	public static Logger getLogger() {
		return logger;
	}

	@Override
	public void logToFile() {
		logger.error(buildSql());
	}
}
