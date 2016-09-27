package com.game.player.structs;

import com.alibaba.fastjson.JSON;
import java.util.HashMap;
import java.util.HashSet;

public class PlayerWorldInfo {

	private long id;
	private String name;
	//人气值
	private int popularity;
	//上次离线时间
	private long lastOnlineTime;
	//国家
	private int country;
	//等级
	private int level;
	//性别
	private int sex;
	//账号
	private String account;
	//是否允许改名
	private int changeName;
	//是否允许改账号
	private int changaccount;
	//系统设置
	private int menustatus;
	//今天膜拜别人次数
	private int worshipNum;
	//自己被膜拜总数
	private int allworshipNum;
	//今天膜拜了的人
	private String recordworshipiddata;
	private int lastworshipday;
	private HashSet<Long> recordworshipid = new HashSet<Long>();
	//VIP等级ID
	private int vip;
	//平台vip
	private int webvip;
	
	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPopularity() {
		return popularity;
	}

	public void setPopularity(int popularity) {
		this.popularity = popularity;
	}

	public long getLastOnlineTime() {
		return lastOnlineTime;
	}

	public void setLastOnlineTime(long lastOnlineTime) {
		this.lastOnlineTime = lastOnlineTime;
	}

	public int getCountry() {
		return country;
	}

	public void setCountry(int country) {
		this.country = country;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public int getChangeName() {
		return changeName;
	}

	public void setChangeName(int changeName) {
		this.changeName = changeName;
	}

	public int getChangaccount() {
		return changaccount;
	}

	public void setChangaccount(int changaccount) {
		this.changaccount = changaccount;
	}

	/**
	 * 是否临时玩家
	 *
	 * @return
	 */
	public boolean checkTempPlayer() {
		if (getChangeName() == 1 || getChangaccount() == 1) {
			return true;
		} else {
			return false;
		}
	}

	public int getMenustatus() {
		return menustatus;
	}

	public void setMenustatus(int menustatus) {
		this.menustatus = menustatus;
	}

	public int getAllworshipNum() {
		return allworshipNum;
	}

	public void setAllworshipNum(int allworshipNum) {
		this.allworshipNum = allworshipNum;
	}

	public HashSet<Long> getRecordworshipid() {
		return recordworshipid;
	}

	public void setRecordworshipid(HashSet<Long> recordworshipid) {
		this.recordworshipid = recordworshipid;
	}

	public int getLastworshipday() {
		return lastworshipday;
	}

	public void setLastworshipday(int lastworshipday) {
		this.lastworshipday = lastworshipday;
	}

	public int getWorshipNum() {
		return worshipNum;
	}

	public void setWorshipNum(int worshipNum) {
		this.worshipNum = worshipNum;
	}

	public String getRecordworshipiddata() {
		return recordworshipiddata;
	}

	public void setRecordworshipiddata(String recordworshipiddata) {
		this.recordworshipiddata = recordworshipiddata;
	}

	@SuppressWarnings("unchecked")
	public void getNewRecordworshipidSet() {
		if (getRecordworshipiddata() != null) {
			HashMap<String, Object> newHashMap = null;
			try {
				newHashMap = JSON.parseObject(getRecordworshipiddata(), HashMap.class);
				if (newHashMap != null && !newHashMap.isEmpty()) {
					if (newHashMap.containsKey("LASTWORSHIPDAY")) {
						int lastday = (Integer) newHashMap.get("LASTWORSHIPDAY");
						setLastworshipday(lastday);
					}
					if (newHashMap.containsKey("WORSHIPID")) {
						HashSet<Long> newHashSet = (HashSet<Long>) newHashMap.get("WORSHIPID");
						if (newHashSet != null && !newHashSet.isEmpty()) {
							getRecordworshipid().clear();
							getRecordworshipid().addAll(newHashSet);
						}
					}
				}
			} catch (Exception e) {
			}
		}
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public int getVip() {
		return vip;
	}

	public void setVip(int vip) {
		this.vip = vip;
	}

	public int getWebvip() {
		return webvip;
	}

	public void setWebvip(int webvip) {
		this.webvip = webvip;
	}
	
}
