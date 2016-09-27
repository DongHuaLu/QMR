package com.game.store.log;

import org.apache.log4j.Logger;

import com.game.dblog.TableCheckStepEnum;
import com.game.dblog.base.Log;
import com.game.dblog.bean.BaseLogBean;
import com.game.server.log.ServerOnlineCountLog;

/**
 * 物品变更日志
 * @author 赵聪慧
 *
 */
public class StoreItemChangeLog extends BaseLogBean {
	/**
	 * 
	 * @param roleid	角色ID
	 * @param itemid	物品ID
	 * @param modelid	模型 ID
	 * @param num		变更数量
	 * @param itembeforeInfo	变更之前
	 * @param itemafterInfo		变更之后
	 * @param reason	变更原因
	 * @param action	变更事件
	 * @param actionId	事件ID
	 * @param changeAction	变更类型
	 * @return
	 */
	public static StoreItemChangeLog createLog(long roleid, long itemid, int modelid, int num, String itembeforeInfo, String itemafterInfo, int reason,String action, long actionId, String changeAction,int sid) {
		StoreItemChangeLog log=new StoreItemChangeLog();
		log.setAction(action);
		log.setActionId(actionId);
		log.setChangeAction(changeAction);
		log.setItemafterInfo(itemafterInfo);
		log.setItembeforeInfo(itembeforeInfo);
		log.setItemid(itemid);
		log.setModelid(modelid);
		log.setNum(num);
		log.setReason(reason);
		log.setRoleid(roleid);
		log.setSid(sid);
		return log;
	}
	
	private long roleid;//角色ID
	private long itemid;//物品ID
	private int modelid;//物品模型ID
	private int num;//变更的数量
	private String itembeforeInfo;//变更之前
	private String itemafterInfo;//变更之后
	private int reason;//原因
	private String action;//事件
	private long actionId;//关联ID
	private String changeAction;//变更类型
	private int sid;
	@Log(logField="sid",fieldType="int")
	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}
	@Override
	public void logToFile() {
		logger.error(buildSql());
	}
	private static final Logger logger=Logger.getLogger("StoreItemChangeLog");
	@Override
	public TableCheckStepEnum getRollingStep() {
		
		return TableCheckStepEnum.DAY;
	}

	@Log(logField="roleid",fieldType="bigint")
	public long getRoleid() {
		return roleid;
	}

	public void setRoleid(long roleid) {
		this.roleid = roleid;
	}
	
	@Log(logField="itemid",fieldType="bigint")
	public long getItemid() {
		return itemid;
	}

	public void setItemid(long itemid) {
		this.itemid = itemid;
	}
	
	@Log(logField="modelid",fieldType="int")
	public int getModelid() {
		return modelid;
	}

	public void setModelid(int modelid) {
		this.modelid = modelid;
	}
	
	@Log(logField="num",fieldType="int")
	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}
	
	@Log(logField="reason",fieldType="int")
	public int getReason() {
		return reason;
	}

	public void setReason(int reason) {
		this.reason = reason;
	}
	@Log(logField="itembeforeInfo",fieldType="text")
	public String getItembeforeInfo() {
		return itembeforeInfo;
	}

	public void setItembeforeInfo(String itembeforeInfo) {
		this.itembeforeInfo = itembeforeInfo;
	}

	@Log(logField="itemafterInfo",fieldType="text")
	public String getItemafterInfo() {
		return itemafterInfo;
	}
	public void setItemafterInfo(String itemafterInfo) {
		this.itemafterInfo = itemafterInfo;
	}
	
	@Log(logField="action",fieldType="varchar(20)")
	public String getAction() {
		return action;
	}
	
	public void setAction(String action) {
		this.action = action;
	}

	@Log(logField="actionid",fieldType="bigint")
	public long getActionId() {
		return actionId;
	}

	public void setActionId(long actionId) {
		this.actionId = actionId;
	}

	@Log(logField="changeaction",fieldType="varchar(20)")
	public String getChangeAction() {
		return changeAction;
	}
	public void setChangeAction(String changeAction) {
		this.changeAction=changeAction;
		
	}
	
}
