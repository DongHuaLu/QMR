package com.game.pet.log;

import org.apache.log4j.Logger;

import com.game.dblog.TableCheckStepEnum;
import com.game.dblog.base.Log;
import com.game.dblog.bean.BaseLogBean;

/**
 * 
 * @author 赵聪慧
 * @2012-9-27 下午2:42:08
 */
public class PetGotLog extends BaseLogBean{
	
	private long roleId;
	
	private long actionId;
	
	private long petId;
	
	private int modelId;
	
	private String userId;
	
	private int sid;
	@Log(logField="sid",fieldType="int")
	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}
	@Log(fieldType="bigint",logField="roleId")
	public long getRoleId() {
		return roleId;
	}
	@Log(fieldType="bigint",logField="actionId")
	public long getActionId() {
		return actionId;
	}
	@Log(fieldType="bigint",logField="petId")
	public long getPetId() {
		return petId;
	}
	@Log(fieldType="int",logField="petmodelId")
	public int getModelId() {
		return modelId;
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

	public void setActionId(long actionId) {
		this.actionId = actionId;
	}

	public void setPetId(long petId) {
		this.petId = petId;
	}

	public void setModelId(int modelId) {
		this.modelId = modelId;
	}



	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(PetGotLog.class);

	@Override
	public TableCheckStepEnum getRollingStep() {
		return TableCheckStepEnum.MONTH;
	}

	@Override
	public void logToFile() {
		logger.info(buildSql());
	}

}
