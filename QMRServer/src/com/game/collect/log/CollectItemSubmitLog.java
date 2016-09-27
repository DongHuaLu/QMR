package com.game.collect.log;

import com.game.dblog.TableCheckStepEnum;
import com.game.dblog.base.Log;
import com.game.dblog.bean.BaseLogBean;

/**
 * 
 * @author 赵聪慧
 * @2012-11-27 下午9:13:45
 */
public class CollectItemSubmitLog extends BaseLogBean {
	private long actionid;
	private long roleid;
	private int itemmodel;
	private int num;
	private String items;
	
	@Log(fieldType="bigint",logField="actionid")
	public long getActionid() {
		return actionid;
	}
	@Log(fieldType="bigint",logField="roleid")
	public long getRoleid() {
		return roleid;
	}
	@Log(fieldType="int",logField="itemmodel")
	public int getItemmodel() {
		return itemmodel;
	}
	@Log(fieldType="int",logField="num")
	public int getNum() {
		return num;
	}
	@Log(fieldType="varchar(1024)",logField="items")
	public String getItems() {
		return items;
	}
	public void setItems(String items) {
		this.items = items;
	}
	public void setActionid(long actionid) {
		this.actionid = actionid;
	}
	public void setRoleid(long roleid) {
		this.roleid = roleid;
	}
	public void setItemmodel(int itemmodel) {
		this.itemmodel = itemmodel;
	}
	public void setNum(int num) {
		this.num = num;
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
