package com.game.zones.structs;

import java.util.HashMap;

import com.game.object.GameObject;
/**副本扫荡记录
 * 
 * @author zhangrong
 *
 */
public class Raid extends GameObject{

	private static final long serialVersionUID = 7433373957114653824L;
	

	//对应副本扫荡最快时间记录（序列化保存HashMap，只能用String做KEY）
	private HashMap<String, Integer> zoneraidtimes = new HashMap<String, Integer>();
	
	//对应副本的最高星级（序列化保存HashMap，只能用String做KEY）
	private HashMap<String, Integer> zonestar = new HashMap<String, Integer>();
	
	//自动扫荡结束时间(秒)
	private int raidautoendtime;
	//当前自动扫荡的副本id
	private int raidzonemodelid;
	
	
	//当前手动扫荡的副本id
	private int raidmanualzonemodelid;
	//当前副本本次通关花费时间(秒)
	private int manualendtime;
	//当前副本怪物击杀数量
	private int killmonsternum;
	//当前副本玩家死亡次数
	private int deathnum;
	
	
	//七曜-自动扫荡结束时间(秒)
	private int qiyaoraidautoendtime;
	//七曜-当前自动扫荡的副本id
	private int qiyaoraidzonemodelid;
	
	
	
	
/**当前自动扫荡的副本id
 * 
 * @return
 */
	public int getRaidzonemodelid() {
		return raidzonemodelid;
	}
	/**当前自动扫荡的副本id
	 * 
	 * @return
	 */
	public void setRaidzonemodelid(int raidzonemodelid) {
		this.raidzonemodelid = raidzonemodelid;
	}
	/**
	 * 对应副本扫荡最快时间记录（序列化保存HashMap，只能用String做KEY）
	 * @return
	 */
	public HashMap<String, Integer> getZoneraidtimes() {
		return zoneraidtimes;
	}
	/**
	 * 对应副本扫荡最快时间记录（序列化保存HashMap，只能用String做KEY）
	 * @return
	 */
	public void setZoneraidtimes(HashMap<String, Integer> zoneraidtimes) {
		this.zoneraidtimes = zoneraidtimes;
	}

	/**自动扫荡结束时间(秒)
	 * 
	 * @return
	 */
	public int getRaidautoendtime() {
		return raidautoendtime;
	}
	/**自动扫荡结束时间
	 * 
	 * @return
	 */
	public void setRaidautoendtime(int raidautoendtime) {
		this.raidautoendtime = raidautoendtime;
	}
	
	
	/**当前副本本次通关花费时间
	 * 
	 * @return
	 */
	public int getManualendtime() {
		return manualendtime;
	}
	/**当前副本本次通关花费时间
	 * 
	 * @return
	 */
	public void setManualendtime(int manualendtime) {
		this.manualendtime = manualendtime;
	}

	
	/**当前副本怪物击杀数量
	 * 
	 * @return
	 */
	public int getKillmonsternum() {
		return killmonsternum;
	}
	
	/**当前副本怪物击杀数量
	 * 
	 * @param killmonsternum
	 */
	public void setKillmonsternum(int killmonsternum) {
		this.killmonsternum = killmonsternum;
	}

	public int getDeathnum() {
		return deathnum;
	}

	public void setDeathnum(int deathnum) {
		this.deathnum = deathnum;
	}

	/**对应副本的最高星级
	 * 
	 * @return
	 */
	public HashMap<String, Integer> getZonestar() {
		return zonestar;
	}
	
	/**对应副本的最高星级
	 * 
	 * @param zonestar
	 */
	public void setZonestar(HashMap<String, Integer> zonestar) {
		this.zonestar = zonestar;
	}
/**当前手动扫荡的副本id
 * 
 * @return
 */
	public int getRaidmanualzonemodelid() {
		return raidmanualzonemodelid;
	}
/**当前手动扫荡的副本id
 * 
 * @param raidmanualzonemodelid
 */
	public void setRaidmanualzonemodelid(int raidmanualzonemodelid) {
		this.raidmanualzonemodelid = raidmanualzonemodelid;
	}
	
	/**七曜-自动扫荡结束时间(秒)
	 * 
	 * @return
	 */
	public int getQiyaoraidautoendtime() {
		return qiyaoraidautoendtime;
	}
	/**七曜-自动扫荡结束时间(秒)
	 * 
	 * @return
	 */
	public void setQiyaoraidautoendtime(int qiyaoraidautoendtime) {
		this.qiyaoraidautoendtime = qiyaoraidautoendtime;
	}
	/**七曜-当前自动扫荡的副本id
	 * 
	 * @return
	 */
	public int getQiyaoraidzonemodelid() {
		return qiyaoraidzonemodelid;
	}
	/**七曜-当前自动扫荡的副本id
	 * 
	 * @param qiyaoraidzonemodelid
	 */
	public void setQiyaoraidzonemodelid(int qiyaoraidzonemodelid) {
		this.qiyaoraidzonemodelid = qiyaoraidzonemodelid;
	}
		
		
		
	
}
