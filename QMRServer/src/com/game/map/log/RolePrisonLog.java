package com.game.map.log;

import org.apache.log4j.Logger;

import com.game.dblog.TableCheckStepEnum;
import com.game.dblog.base.Log;
import com.game.dblog.bean.BaseLogBean;

public class RolePrisonLog extends BaseLogBean {

	private static Logger log = Logger.getLogger(RolePrisonLog.class);
	
	@Override
	public TableCheckStepEnum getRollingStep() {
		return TableCheckStepEnum.MONTH;
	}

	@Override
	public void logToFile() {
		log.error(buildSql());
	}
	
	//
	private int sid;
	private String userid;
	private String roleid;
	private String rolename;
	private int type; //0-进监狱 1-出监狱
	private int prisontime; //进监狱次数
	private int prisonremaintime; //监狱剩余时间
	private int handletype; //1-游戏自动自动 2-后台操作
	//
	@Log(logField="userid",fieldType="varchar(1024)")
	public String getUserid() {
		return userid;
	}
	@Log(logField="roleid",fieldType="varchar(255)")
	public String getRoleid() {
		return roleid;
	}
	@Log(logField="rolename",fieldType="varchar(255)")
	public String getRolename() {
		return rolename;
	}
	@Log(logField="type",fieldType="int")
	public int getType() {
		return type;
	}
	@Log(logField="prisontime",fieldType="int")
	public int getPrisontime() {
		return prisontime;
	}
	@Log(logField="prisonremaintime",fieldType="int")
	public int getPrisonremaintime() {
		return prisonremaintime;
	}
	@Log(logField="handletype",fieldType="int")
	public int getHandletype() {
		return handletype;
	}
	
	@Log(logField="sid",fieldType="int")
	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public void setHandletype(int handletype) {
		this.handletype = handletype;
	}

	//
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public void setType(int type) {
		this.type = type;
	}

	public void setPrisontime(int prisontime) {
		this.prisontime = prisontime;
	}

	public void setPrisonremaintime(int prisonremaintime) {
		this.prisonremaintime = prisonremaintime;
	}
	//
	
	
}
