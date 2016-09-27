package com.game.player.log;

import org.apache.log4j.Logger;

import com.game.dblog.TableCheckStepEnum;
import com.game.dblog.base.Log;
import com.game.dblog.bean.BaseLogBean;
import com.game.player.structs.Player;

public class RoleLevelUpLog extends BaseLogBean {

	@Override
	public TableCheckStepEnum getRollingStep() {
		return TableCheckStepEnum.DAY;
	}

	@Override
	public void logToFile() {
		logger.error(buildSql());
	}
	private static final Logger logger=Logger.getLogger("PlayerLevelUpLog");

	private long userid;		//用户ID
	private long roleid;		//角色ID
	private int  beforelevel;	//升级前等级
	private int  afterlevel;	//升级后等级
	private long curexp;		//当前经验
	private long acconlinetime; //当前累积在线时间
	private int  reason;		//原因
	private int sid;
	@Log(logField="sid",fieldType="int")
	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}
	public RoleLevelUpLog() {
		super();
	}
	
	public RoleLevelUpLog(Player player) {
		super();
		this.userid = Long.valueOf(player.getUserId());
		this.roleid = player.getId();
		this.curexp = player.getExp();
		this.acconlinetime = player.getAccunonlinetime();
	}

	@Log(logField="userid",fieldType="bigint")
	public long getUserid() {
		return userid;
	}
	@Log(logField="roleid",fieldType="bigint")
	public long getRoleid() {
		return roleid;
	}
	@Log(logField="beflevel",fieldType="int")
	public int getBeforelevel() {
		return beforelevel;
	}
	@Log(logField="aftlevel",fieldType="int")
	public int getAfterlevel() {
		return afterlevel;
	}
	@Log(logField="curexp",fieldType="bigint")
	public long getCurexp() {
		return curexp;
	}
	@Log(logField="acconlinetime",fieldType="bigint")
	public long getAcconlinetime() {
		return acconlinetime;
	}
	@Log(logField="reason",fieldType="int")
	public int getReason() {
		return reason;
	}

	public void setUserid(long userid) {
		this.userid = userid;
	}

	public void setRoleid(long roleid) {
		this.roleid = roleid;
	}

	public void setBeforelevel(int beforelevel) {
		this.beforelevel = beforelevel;
	}

	public void setAfterlevel(int afterlevel) {
		this.afterlevel = afterlevel;
	}

	public void setCurexp(long curexp) {
		this.curexp = curexp;
	}

	public void setAcconlinetime(long acconlinetime) {
		this.acconlinetime = acconlinetime;
	}

	public void setReason(int reason) {
		this.reason = reason;
	}
}
