package com.game.shop.log;

import org.apache.log4j.Logger;

import com.game.dblog.LogService;
import com.game.dblog.TableCheckStepEnum;
import com.game.dblog.base.Log;
import com.game.dblog.bean.BaseLogBean;

public class ShopBuyLog extends BaseLogBean {
	private static final Logger logger = Logger.getLogger("ShopBuyLog");
	private long actionId;
	private long roleid;
	private int npcid;
	private int itemmodel;
	private int sellid;
	private int num;
	private int costtype;
	private int resume;
	private String items;
	private int rolelevel;
	private long userid;
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
	
	@Log(logField="actionid",fieldType="bigint")
	public long getActionId() {
		return actionId;
	}

	
	public void setActionId(long actionId) {
		this.actionId = actionId;
	}


	@Log(logField="roleid",fieldType="bigint")
	public long getRoleid() {
		return roleid;
	}
	
	@Log(logField="userid",fieldType="bigint")
	public long getUserid() {
		return userid;
	}

	public void setUserid(long userid) {
		this.userid = userid;
	}

	public void setRoleid(long roleid) {
		this.roleid = roleid;
	}
	@Log(logField="npcid",fieldType="int")
	public int getNpcid() {
		return npcid;
	}

	public void setNpcid(int npcid) {
		this.npcid = npcid;
	}
	@Log(logField="sellid",fieldType="int")
	public int getSellid() {
		return sellid;
	}

	public void setSellid(int sellid) {
		this.sellid = sellid;
	}
	@Log(logField="num",fieldType="int")
	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}
	@Log(logField="costtype",fieldType="int")
	public int getCosttype() {
		return costtype;
	}

	public void setCosttype(int costtype) {
		this.costtype = costtype;
	}
	@Log(logField="resume",fieldType="int")
	public int getResume() {
		return resume;
	}
	
	public void setResume(int resume) {
		this.resume = resume;
	}
	
	@Log(logField="items",fieldType="longtext")
	public String getItems() {
		return items;
	}
	
	public void setItems(String items) {
		this.items = items;
	}
	@Log(logField="roleLevel",fieldType="int")
	public int getRolelevel() {
		return rolelevel;
	}
	
	@Log(logField="itemmodel",fieldType="int")
	public int getItemmodel() {
		return itemmodel;
	}


	public void setItemmodel(int itemmodel) {
		this.itemmodel = itemmodel;
	}


	public void setRolelevel(int rolelevel) {
		this.rolelevel = rolelevel;
	}
	@Override
	public void logToFile() {
		logger.error(buildSql());
	}
	
}
