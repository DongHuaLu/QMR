package com.game.signwage.log;

import org.apache.log4j.Logger;

import com.game.dblog.TableCheckStepEnum;
import com.game.dblog.base.Log;
import com.game.dblog.bean.BaseLogBean;
import com.game.player.structs.Player;

public class SignWageLog extends BaseLogBean {

	private static Logger log = Logger.getLogger(SignWageLog.class);
	
	@Override
	public TableCheckStepEnum getRollingStep() {
		return TableCheckStepEnum.MONTH;
	}

	@Override
	public void logToFile() {
		log.error(buildSql());
	}
	//
	public SignWageLog() {
		super();
	}
	
	public SignWageLog(Player player) {
		super();
		this.userid = player.getUserId();
		this.roleid = String.valueOf(player.getId());
		this.username = player.getUserName();
	}
	//
	private String userid;
	private String roleid;
	private String username;
	private int sid;
	private int type; //1-领取签到奖励  2-领取工资 3-领取美人  
	private String content; //领取的具体内容

	public static Logger getLog() {
		return log;
	}

	@Log(logField="userid",fieldType="bigint")
	public String getUserid() {
		return userid;
	}

	@Log(logField="roleid",fieldType="bigint")
	public String getRoleid() {
		return roleid;
	}

	@Log(logField="username",fieldType="varchar(1024)")
	public String getUsername() {
		return username;
	}

	@Log(logField="sid",fieldType="int")
	public int getSid() {
		return sid;
	}

	@Log(logField="type",fieldType="int")
	public int getType() {
		return type;
	}

	@Log(logField="content",fieldType="varchar(1024)")
	public String getContent() {
		return content;
	}

	public static void setLog(Logger log) {
		SignWageLog.log = log;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public void setType(int type) {
		this.type = type;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
