package com.game.txconsume.log;

import org.apache.log4j.Logger;

import com.game.dblog.TableCheckStepEnum;
import com.game.dblog.base.Log;
import com.game.dblog.bean.BaseLogBean;

public class TxConsumesLog extends BaseLogBean {

	private static Logger log = Logger.getLogger(TxConsumesLog.class);
	
	@Override
	public TableCheckStepEnum getRollingStep() {
		return TableCheckStepEnum.MONTH;
	}

	@Override
	public void logToFile() {
		log.error(buildSql());
	}

	private String oid;
	private String username;
	private String userid;
	private String roleid;
	private int state; // 1-即时返回  2-2次确认
	private String ret;
	private String msg;
	private String desc;
	private int totalconsume;
	
	private int isconfirm;
	private String confirmmsg;
	
	@Log(logField="isconfirm",fieldType="int")
	public int getIsconfirm() {
		return isconfirm;
	}

	public void setIsconfirm(int isconfirm) {
		this.isconfirm = isconfirm;
	}

	@Log(logField="confirmmsg",fieldType="varchar(4096)")
	public String getConfirmmsg() {
		return confirmmsg;
	}

	public void setConfirmmsg(String confirmmsg) {
		this.confirmmsg = confirmmsg;
	}

	@Log(logField="`desc`",fieldType="varchar(4096)")
	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	@Log(logField="totalconsume",fieldType="int")
	public int getTotalconsume() {
		return totalconsume;
	}

	public void setTotalconsume(int totalconsume) {
		this.totalconsume = totalconsume;
	}

	@Log(logField="ret",fieldType="varchar(100)")
	public String getRet() {
		return ret;
	}

	public void setRet(String ret) {
		this.ret = ret;
	}

	@Log(logField="msg",fieldType="varchar(100)")
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Log(logField="oid",fieldType="varchar(255)")
	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	@Log(logField="username",fieldType="varchar(1024)")
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Log(logField="userid",fieldType="varchar(1024)")
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	@Log(logField="roleid",fieldType="varchar(1024)")
	public String getRoleid() {
		return roleid;
	}

	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}

	@Log(logField="state",fieldType="int")
	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
}
