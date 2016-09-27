package com.game.player.log;

import org.apache.log4j.Logger;

import com.game.dblog.TableCheckStepEnum;
import com.game.dblog.base.Log;
import com.game.dblog.bean.BaseLogBean;
import com.game.player.structs.Player;

public class RoleHeartCheatLog extends BaseLogBean {

	private static Logger log = Logger.getLogger(RoleHeartCheatLog.class);
	
	@Override
	public TableCheckStepEnum getRollingStep() {
		return TableCheckStepEnum.DAY;
	}

	@Override
	public void logToFile() {
		log.error(buildSql());
	}
	
	private String username;
	private String userid;
	private String rolename;
	private String roleid;
	private int sid;
	
	private int checktimes;
	private long checkparam;

	public RoleHeartCheatLog() {
		super();
	}

	public RoleHeartCheatLog(Player player) {
		super();
		this.username = player.getUserName();
		this.userid = player.getUserId();
		this.rolename = player.getName();
		this.roleid = String.valueOf(player.getId());
		this.sid = player.getCreateServerId();
	}
	
	@Log(logField="username",fieldType="varchar(1024)")
	public String getUsername() {
		return username;
	}

	@Log(logField="userid",fieldType="varchar(1024)")
	public String getUserid() {
		return userid;
	}

	@Log(logField="rolename",fieldType="varchar(256)")
	public String getRolename() {
		return rolename;
	}

	@Log(logField="roleid",fieldType="varchar(1024)")
	public String getRoleid() {
		return roleid;
	}

	@Log(logField="sid",fieldType="int")
	public int getSid() {
		return sid;
	}

	@Log(logField="checktimes",fieldType="int")
	public int getChecktimes() {
		return checktimes;
	}

	@Log(logField="checkparam",fieldType="bigint")
	public long getCheckparam() {
		return checkparam;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public void setChecktimes(int checktimes) {
		this.checktimes = checktimes;
	}

	public void setCheckparam(long checkparam) {
		this.checkparam = checkparam;
	}
	
}
