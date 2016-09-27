package com.game.pet.log;

import org.apache.log4j.Logger;

import com.game.dblog.TableCheckStepEnum;
import com.game.dblog.base.Log;
import com.game.dblog.bean.BaseLogBean;

/**
 * 
 * @author 赵聪慧
 * @2012-9-27 下午3:21:58
 */
public class PetHeTiLog extends BaseLogBean {
	private long petid;
	private long roleid;
	private int modelid;
	private int count;
	private long actionid;
	private String userId;
	
	@Log(fieldType="bigint",logField="petId")
	public long getPetid() {
		return petid;
	}
	@Log(fieldType="bigint",logField="roleId")
	public long getRoleid() {
		return roleid;
	}

	@Log(fieldType="bigint",logField="modelId")
	public int getModelid() {
		return modelid;
	}

	@Log(fieldType="int",logField="count")
	public int getCount() {
		return count;
	}
	
	@Log(fieldType="bigint",logField="actionId")
	public long getActionid() {
		return actionid;
	}

	@Log(fieldType="varchar(30)",logField="userId")
	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public void setPetid(long petid) {
		this.petid = petid;
	}

	public void setRoleid(long roleid) {
		this.roleid = roleid;
	}

	public void setModelid(int modelid) {
		this.modelid = modelid;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public void setActionid(long actionid) {
		this.actionid = actionid;
	}


	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(PetHeTiLog.class);

	@Override
	public TableCheckStepEnum getRollingStep() {
		return TableCheckStepEnum.MONTH;
	}

	@Override
	public void logToFile() {
		logger.error("");

	}

}
