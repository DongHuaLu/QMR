package com.game.player.log;

import org.apache.log4j.Logger;

import com.game.dblog.TableCheckStepEnum;
import com.game.dblog.base.Log;
import com.game.dblog.bean.BaseLogBean;
import com.game.player.structs.Player;

//举报日志
public class RoleReportLog extends BaseLogBean {

	private static Logger log = Logger.getLogger(RoleReportLog.class);
	
	@Override
	public TableCheckStepEnum getRollingStep() {
		return TableCheckStepEnum.MONTH;
	}

	@Override
	public void logToFile() {
		log.error(buildSql());
	}
	
	//被举报者信息
	private String username;
	private String userid;
	private String rolename;
	private String roleid;
	private int sid;
	
	//举报者ID
	private long reporterid;
	//举报者角色名
	private String reportername;
	//被举报次数
	private int reporttimes;

	public RoleReportLog() {
		super();
	}

	public RoleReportLog(Player player) {
		super();
		this.username = player.getUserName();
		this.userid = player.getUserId();
		this.rolename = player.getName();
		this.roleid = String.valueOf(player.getId());
		this.sid = player.getCreateServerId();
	}

	public RoleReportLog(String username, String userid, String rolename,
			String roleid, int sid) {
		super();
		this.username = username;
		this.userid = userid;
		this.rolename = rolename;
		this.roleid = roleid;
		this.sid = sid;
	}

	@Log(logField="username",fieldType="varchar(512)")
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
	
	@Log(logField="reporterid",fieldType="bigint")
	public long getReporterid() {
		return reporterid;
	}

	@Log(logField="reportname",fieldType="varchar(512)")
	public String getReportername() {
		return reportername;
	}

	@Log(logField="reporttimes",fieldType="int")
	public int getReporttimes() {
		return reporttimes;
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

	public void setReporterid(long reporterid) {
		this.reporterid = reporterid;
	}

	public void setReportername(String reportername) {
		this.reportername = reportername;
	}

	public void setReporttimes(int reporttimes) {
		this.reporttimes = reporttimes;
	}
}
