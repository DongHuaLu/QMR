package com.game.backpack.log;

import org.apache.log4j.Logger;

import com.game.dblog.TableCheckStepEnum;
import com.game.dblog.base.Log;
import com.game.dblog.bean.BaseLogBean;
/**
 * 
 * @author 赵聪慧
 *
 */
public class AbleReceiveChangeLog extends BaseLogBean {
	private long roleId;
	//事件ID
	private long actionId;
	//事件
	private String action;
	//原因 
	private String reason;
	//变更的物品
	private String Item;
	
	//变更之前的可领取列表
	private String beforeList;
	//变更之后的可领取列表
	private String afterList;
	private int sid;
	@Log(logField="sid",fieldType="int")
	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}
	@Override
	public TableCheckStepEnum getRollingStep() {
		return TableCheckStepEnum.MONTH;
	}

	@Log(fieldType="bigint",logField="actionid")
	public long getActionId() {
		return actionId;
	}

	public void setActionId(long actionId) {
		this.actionId = actionId;
	}
	@Log(fieldType="varchar(20)",logField="action")
	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	@Log(fieldType="varchar(50)",logField="reason")
	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	@Log(fieldType="text",logField="changeitems")
	public String getItem() {
		return Item;
	}

	public void setItem(String item) {
		Item = item;
	}
	@Log(fieldType="text",logField="beforelist")
	public String getBeforeList() {
		return beforeList;
	}

	public void setBeforeList(String beforeList) {
		this.beforeList = beforeList;
	}
	@Log(fieldType="text",logField="afterlist")
	public String getAfterList() {
		return afterList;
	}

	public void setAfterList(String afterList) {
		this.afterList = afterList;
	}

	@Log(logField="roleId",fieldType="bigint")
	public long getRoleId() {
		return roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}
	@Override
	public void logToFile() {
		logger.error(buildSql());
	}
	private static final Logger logger=Logger.getLogger("AbleReceiveChangeLog");
}
