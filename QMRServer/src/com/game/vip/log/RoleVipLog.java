package com.game.vip.log;

import org.apache.log4j.Logger;

import com.game.dblog.TableCheckStepEnum;
import com.game.dblog.base.Log;
import com.game.dblog.bean.BaseLogBean;
import com.game.player.structs.Player;

//vip功能日志
public class RoleVipLog extends BaseLogBean {

	private static final Logger logger=Logger.getLogger("RoleVipLog");

	private long userid;   //用户id
	private long roleid;   //角色id
	private int type;      // 1-成为vip 2-vip续费 3-升级 4-vip消失  5-领取礼包 6领取至尊奖励
	private int befvipid;  // 操作前vipid
	private int aftvipid;  // 操作后vipid
	private long actionid; // actionid
	private int sid;
	@Log(logField="sid",fieldType="int")
	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}
	public RoleVipLog() {
		super();
	}

	public RoleVipLog(Player player, int type, int befvipid, int aftvipid, long actionid){
		super();
		this.userid = Long.valueOf(player.getUserId());
		this.roleid = player.getId();
		this.type = type;
		this.befvipid = befvipid;
		this.aftvipid = aftvipid;
		this.actionid = actionid;
		setSid(player.getCreateServerId());
	}
	
	public RoleVipLog(long userid, long roleid, int type, int befvipid,
			int aftvipid, long actionid,int sid) {
		super();
		this.userid = userid;
		this.roleid = roleid;
		this.type = type;
		this.befvipid = befvipid;
		this.aftvipid = aftvipid;
		this.actionid = actionid;
		setSid(sid);
	}

	@Override
	public TableCheckStepEnum getRollingStep() {
		return TableCheckStepEnum.MONTH;
	}

	@Override
	public void logToFile() {
		logger.error(buildSql());
	}
	
	@Log(logField="userid",fieldType="bigint")
	public long getUserid() {
		return userid;
	}
	
	@Log(logField="roleid",fieldType="bigint")
	public long getRoleid() {
		return roleid;
	}
	@Log(logField="type",fieldType="int")
	public int getType() {
		return type;
	}
	
	@Log(logField="befvipid",fieldType="int")
	public int getBefvipid() {
		return befvipid;
	}
	
	@Log(logField="aftvipid",fieldType="int")
	public int getAftvipid() {
		return aftvipid;
	}
	
	@Log(logField="actionid",fieldType="bigint")
	public long getActionid() {
		return actionid;
	}

	public void setUserid(long userid) {
		this.userid = userid;
	}

	public void setRoleid(long roleid) {
		this.roleid = roleid;
	}

	public void setType(int type) {
		this.type = type;
	}

	public void setBefvipid(int befvipid) {
		this.befvipid = befvipid;
	}

	public void setAftvipid(int aftvipid) {
		this.aftvipid = aftvipid;
	}

	public void setActionid(long actionid) {
		this.actionid = actionid;
	}
	
	
}
