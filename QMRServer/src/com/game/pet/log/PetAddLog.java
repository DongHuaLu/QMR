package com.game.pet.log;

import org.apache.log4j.Logger;

import com.game.dblog.TableCheckStepEnum;
import com.game.dblog.base.Log;
import com.game.dblog.bean.BaseLogBean;

/**
 * 
 * @author 赵聪慧
 * @2012-9-27 下午2:42:25
 */
public class PetAddLog extends BaseLogBean {
	private long roleId;
	private long petid;
	private String reason;
	private int petmodelid;
	private long actionId;
	private String userId;
	private int sid;
	@Log(logField="sid",fieldType="int")
	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}
	
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(PetAddLog.class);
	
	@Log(fieldType="bigint",logField="roleId")
	public long getRoleId() {
		return roleId;
	}
	@Log(fieldType="bigint",logField="petId")
	public long getPetid() {
		return petid;
	}
	@Log(fieldType="varchar(256)",logField="reason")
	public String getReason() {
		return reason;
	}
	@Log(fieldType="int",logField="petmodelid")
	public int getPetmodelid() {
		return petmodelid;
	}
	@Log(fieldType="bigint",logField="actionId")
	public long getActionId() {
		return actionId;
	}
	@Log(fieldType="varchar(30)",logField="userId")
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}
	public void setPetid(long petid) {
		this.petid = petid;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public void setPetmodelid(int petmodelid) {
		this.petmodelid = petmodelid;
	}
	
	public static Logger getLogger() {
		return logger;
	}

	public void setActionId(long actionId) {
		this.actionId = actionId;
	}
	@Override
	public TableCheckStepEnum getRollingStep() {
		// TODO Auto-generated method stub
		return TableCheckStepEnum.MONTH;
	}

	@Override
	public void logToFile() {
		logger.error(buildSql());
	}

}
