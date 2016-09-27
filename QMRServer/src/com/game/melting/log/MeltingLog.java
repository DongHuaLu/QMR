package com.game.melting.log;

import com.game.dblog.TableCheckStepEnum;
import com.game.dblog.base.Log;
import com.game.dblog.bean.BaseLogBean;

/**
 * 熔炼日志
 *
 * @author 杨鸿岚
 */
public class MeltingLog extends BaseLogBean {

	@Override
	public TableCheckStepEnum getRollingStep() {
		return TableCheckStepEnum.MONTH;
	}

	@Override
	public void logToFile() {
		logger.error(buildSql());
	}
	
	private long userid;    //用户id
	private long roleid;    //角色id
	private int meltingid;	//熔炼石id
	private String iteminfo;//物品信息
	private int idx;	//替换的位置
	private String oldattrinfo;//被替换的属性
	private String newattrinfo;//替换的属性
	private int gold;	//花费元宝
	private int money;	//花费铜币

	@Log(logField="gold",fieldType="int")
	public int getGold() {
		return gold;
	}

	public void setGold(int gold) {
		this.gold = gold;
	}

	@Log(logField="idx",fieldType="int")
	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	@Log(logField="iteminfo",fieldType="longtext")
	public String getIteminfo() {
		return iteminfo;
	}

	public void setIteminfo(String iteminfo) {
		this.iteminfo = iteminfo;
	}

	@Log(logField="meltingid",fieldType="int")
	public int getMeltingid() {
		return meltingid;
	}

	public void setMeltingid(int meltingid) {
		this.meltingid = meltingid;
	}

	@Log(logField="money",fieldType="int")
	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	@Log(logField="newattrinfo",fieldType="varchar(256)")
	public String getNewattrinfo() {
		return newattrinfo;
	}

	public void setNewattrinfo(String newattrinfo) {
		this.newattrinfo = newattrinfo;
	}

	@Log(logField="oldattrinfo",fieldType="varchar(256)")
	public String getOldattrinfo() {
		return oldattrinfo;
	}

	public void setOldattrinfo(String oldattrinfo) {
		this.oldattrinfo = oldattrinfo;
	}

	@Log(logField="roleid",fieldType="bigint")
	public long getRoleid() {
		return roleid;
	}

	public void setRoleid(long roleid) {
		this.roleid = roleid;
	}

	@Log(logField="userid",fieldType="bigint")
	public long getUserid() {
		return userid;
	}

	public void setUserid(long userid) {
		this.userid = userid;
	}
}
