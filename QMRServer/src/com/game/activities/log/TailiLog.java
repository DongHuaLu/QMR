package com.game.activities.log;

import org.apache.log4j.Logger;

import com.game.dblog.TableCheckStepEnum;
import com.game.dblog.base.Log;
import com.game.dblog.bean.BaseLogBean;

public class TailiLog extends BaseLogBean {
	private static final Logger logger=Logger.getLogger("TailiLog");
	
	private String agent;
	private int    zone;
	private String user;
	private String role;
	private String name;
	private String addr;
	private String phone;
	
	@Log(logField="name",fieldType="varchar(1000)")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Log(logField="addr",fieldType="varchar(1000)")
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}

	@Log(logField="phone",fieldType="varchar(20)")
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
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
	
	@Log(logField="user",fieldType="varchar(256)")
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	
	@Log(logField="role",fieldType="varchar(50)")
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public TableCheckStepEnum getRollingStep() {
		return TableCheckStepEnum.YEAR;
	}

	@Override
	public void logToFile() {
		logger.error(buildSql());
	}
}
