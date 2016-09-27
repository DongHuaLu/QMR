package com.game.recharge;

import java.util.Date;

import org.apache.log4j.Logger;

import com.game.db.bean.GoldRechargeLog;
import com.game.dblog.TableCheckStepEnum;
import com.game.dblog.base.Log;
import com.game.dblog.bean.BaseLogBean;

public class RechargelogLog extends BaseLogBean{
	private static final Logger logger=Logger.getLogger("RechargelogLog");
	
	private String username; //账号
	private String oid; //订单号
	private String serverid; //服务器id
	private int gold;   //充值数
	private int type;   //类型 1-普通  9后台
	private String date; //日期
	private long userid; //userid

	public RechargelogLog() {
	}
	
	public RechargelogLog(GoldRechargeLog rlog){
		this.username = rlog.getUid();
		this.oid = rlog.getOid();
		this.serverid = rlog.getServerid();
		this.gold = rlog.getGold();
		this.type = rlog.getType();
		this.date = sdf.format(new Date());
		this.userid = rlog.getUserid();
	}
	
	
	

	public void setUserid(long userid) {
		this.userid = userid;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public void setServerid(String serverid) {
		this.serverid = serverid;
	}

	public void setGold(int gold) {
		this.gold = gold;
	}

	public void setType(int type) {
		this.type = type;
	}

	public void setDate(String date) {
		this.date = date;
	}


	@Log(logField="username",fieldType="varchar(255)")
	public String getUsername() {
		return username;
	}
	
	@Log(logField="orderid",fieldType="varchar(255)")
	public String getOid() {
		return oid;
	}
	
	@Log(logField="serverid",fieldType="varchar(255)")
	public String getServerid() {
		return serverid;
	}
	
	@Log(logField="userid", fieldType="bigint")
	public long getUserid() {
		return userid;
	}
	
	@Log(logField="gold",fieldType="varchar(255)")
	public int getGold() {
		return gold;
	}

	@Log(logField="type",fieldType="varchar(255)")
	public int getType() {
		return type;
	}

	@Log(logField="rechargedate",fieldType="varchar(255)")
	public String getDate() {
		return date;
	}
	

	@Override
	public TableCheckStepEnum getRollingStep() {
		return TableCheckStepEnum.MONTH;
	}

	@Override
	public void logToFile() {
		logger.error(buildSql());
	}

	
	
}
