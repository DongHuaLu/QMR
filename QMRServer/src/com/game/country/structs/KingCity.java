package com.game.country.structs;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import com.game.player.structs.Player;
import com.game.utils.TimeUtil;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 王城信息
 *
 */
public class KingCity {

	//王城帮会ID
	private long guildid;
	//王城帮会名字
	private String guildname;
	
	//领取俸禄记录标记为天数 今日天数=领取时间，表示已经领取
	private HashMap<String, Integer> salarymap = new HashMap<String, Integer>();
	//秦王数据
	private HashMap<String, KingData> kingdataMap = new HashMap<String, KingData>();

	//攻城战期间，玩家持有玉玺时间 (秒)（开始时间）
	private transient int  holdtime;
	
	//玉玺持有者ID
	private  long  holdplayerid;
	//玉玺持有者名字 (包括雕像显示)
	private  String  holdplayername;
	//玉玺持有者性别 (雕像显示)
	private  int  holdplayersex;
	
	//-----------------------成员函数-----------------------//
	/**
	 * 判断是否王城帮派
	 *
	 * @return true 是 false 不是
	 */
	public boolean checkKingCity(Player player) {
		return (player.getGuildId() == getGuildid() && getGuildid() != 0 )? true : false;
	}

	/**
	 * 判断是否王城帮派
	 *
	 * @return true 是 false 不是
	 */
	public boolean checkKingCity(long guildid) {
		return (guildid == getGuildid() && getGuildid() != 0)  ? true : false;
	}
	/////////////////////////////////////////////////////////

	public int gKingDataKey() {
		return gKingTerm() + 1;
	}

	public int gKingTerm() {
		return getKingdataMap().size();
	}

	public KingData gCurKingData() {
		return getKingdataMap().get(""+gKingTerm());
	}

	public KingData gKingData(int kingterm) {
		return getKingdataMap().get(""+kingterm);
	}

	public void putKingData(KingData kingData) {
		getKingdataMap().put(""+kingData.getTerm(), kingData);
	}

	public Collection<KingData> gKingDataValue() {
		return getKingdataMap().values();
	}

	public Iterator<KingData> gKingDataIterator() {
		return gKingDataValue().iterator();
	}
	//////////////////////////////////////////////////////////

	private int gSalary(int powerLv) {
		if (getSalarymap().containsKey(""+powerLv)) {
			return getSalarymap().get(""+powerLv);
		}
		return 0;
	}

	private void sSalary(int powerLv, int curDay) {
		getSalarymap().put(""+powerLv, curDay);
	}

	public void sCurdayToSalary(int powerLv) {
		long curday = TimeUtil.GetCurTimeInMin(4);
		sSalary(powerLv, (int) curday);
	}

	/**
	 * 判断是否可以领取薪水
	 *
	 * @return true 可以 false 不可以
	 */
	public boolean checkSalary(int powerLv) {
		long curday = TimeUtil.GetCurTimeInMin(4);
		if (gSalary(powerLv) < curday) {
			return true;
		}
		return false;
	}

	/**
	 * 判断是否可以领取薪水
	 *
	 * @return true 可以 false 不可以
	 */
	public boolean checkSalary(Player player) {
		if (player.getMemberInfo().getGuildPowerLevel() > 0 && player.getMemberInfo().getGuildPowerLevel() < 6) {
			return checkSalary(player.getMemberInfo().getGuildPowerLevel());
		}
		return false;
	}
	//-----------------------成员函数-----------------------//

	public long getGuildid() {
		return guildid;
	}

	public void setGuildid(long guildid) {
		this.guildid = guildid;
	}

	public HashMap<String, KingData> getKingdataMap() {
		return kingdataMap;
	}

	public void setKingdataMap(HashMap<String, KingData> kingdataMap) {
		this.kingdataMap = kingdataMap;
	}

	public HashMap<String, Integer> getSalarymap() {
		return salarymap;
	}

	public void setSalarymap(HashMap<String, Integer> salarymap) {
		this.salarymap = salarymap;
	}

	public int getHoldtime() {
		return holdtime;
	}

	public void setHoldtime(int holdtime) {
		this.holdtime = holdtime;
	}

	public long getHoldplayerid() {
		return holdplayerid;
	}

	public void setHoldplayerid(long holdplayerid) {
		this.holdplayerid = holdplayerid;
	}

	public String getGuildname() {
		return guildname;
	}

	public void setGuildname(String guildname) {
		this.guildname = guildname;
	}

	public String getHoldplayername() {
		return holdplayername;
	}

	public void setHoldplayername(String holdplayername) {
		this.holdplayername = holdplayername;
	}

	public int getHoldplayersex() {
		return holdplayersex;
	}

	public void setHoldplayersex(int holdplayersex) {
		this.holdplayersex = holdplayersex;
	}

}
