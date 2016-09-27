package com.game.pet.log;

import org.apache.log4j.Logger;

import com.game.dblog.TableCheckStepEnum;
import com.game.dblog.base.Log;
import com.game.dblog.bean.BaseLogBean;

/**
 * 
 * @author 赵聪慧
 * @2012-9-27 下午3:31:55
 */
public class PetClearHeTiCDLog extends BaseLogBean {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(PetClearHeTiCDLog.class);

	private long petId;
	private long roleId;
	private String userid;
	private long actionid;
	private int cdtime;
	private int resumegold;
	private int sid;
	@Log(logField="sid",fieldType="int")
	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}
	@Log(fieldType="bigint",logField="petid")
	public long getPetId() {
		return petId;
	}
	@Log(fieldType="bigint",logField="roleid")
	public long getRoleId() {
		return roleId;
	}
	@Log(fieldType="varchar(30)",logField="userid")
	public String getUserid() {
		return userid;
	}
	@Log(fieldType="bigint",logField="actionid")
	public long getActionid() {
		return actionid;
	}
	@Log(fieldType="int",logField="cdtime")
	public int getCdtime() {
		return cdtime;
	}

	@Log(fieldType="int",logField="resumegold")
	public int getResumegold() {
		return resumegold;
	}

	public void setPetId(long petId) {
		this.petId = petId;
	}
	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public void setActionid(long actionid) {
		this.actionid = actionid;
	}
	public void setCdtime(int cdtime) {
		this.cdtime = cdtime;
	}
	public void setResumegold(int resumegold) {
		this.resumegold = resumegold;
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
