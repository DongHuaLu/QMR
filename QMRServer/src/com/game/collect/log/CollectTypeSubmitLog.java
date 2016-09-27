package com.game.collect.log;

import com.game.dblog.TableCheckStepEnum;
import com.game.dblog.base.Log;
import com.game.dblog.bean.BaseLogBean;

/**
 * 
 * @author 赵聪慧
 * @2012-11-27 下午9:12:16
 */
public class CollectTypeSubmitLog extends BaseLogBean{
	private long actionid;
	private long roleid;
	private int type;

	@Log(fieldType="bigint",logField="actionid")
	public long getActionid() {
		return actionid;
	}
	@Log(fieldType="bigint",logField="roleid")
	public long getRoleid() {
		return roleid;
	}
	@Log(fieldType="int",logField="collecttype")
	public int getType() {
		return type;
	}

	
	public void setActionid(long actionid) {
		this.actionid = actionid;
	}
	public void setRoleid(long roleid) {
		this.roleid = roleid;
	}
	public void setType(int type) {
		this.type = type;
	}
	@Override
	public TableCheckStepEnum getRollingStep() {
		return TableCheckStepEnum.MONTH;
	}

	@Override
	public void logToFile() {
		logger.info(buildSql());
	}
	
}
