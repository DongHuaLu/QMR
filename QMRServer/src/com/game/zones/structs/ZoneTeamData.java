package com.game.zones.structs;

import java.util.HashMap;
/**组队进入副本信息
 * 
 * @author zhangrong
 *
 */
public class ZoneTeamData {
	//发起者
	private long initiateplayerid ;
	//队伍成员< ID,选择>
	private HashMap<Long, Integer> memberidmap = new HashMap<Long, Integer>();

	//目标副本
	private int zonemodelid ;
	
	//发起时间（秒）
	private int initiatetime ;
	
	//等待时间+30
	private int waittime ;
	
	//进入时间+5
	private int entertime ;
	
	//选择数量
	private int selectnum ;
	
	//队伍ID
	private long teamid;
	
	//队伍总人数验证用
	private int teamsum;
	
	/**发起者
	 * 
	 * @return
	 */
	public long getInitiateplayerid() {
		return initiateplayerid;
	}
	/**发起者
	 * 
	 * @return
	 */
	public void setInitiateplayerid(long initiateplayerid) {
		this.initiateplayerid = initiateplayerid;
	}
	
	/**发起时间（秒）
	 * 
	 * @return
	 */
	public int getInitiatetime() {
		return initiatetime;
	}
	/**发起时间（秒）
	 * 
	 * @param initiatetime
	 */
	public void setInitiatetime(int initiatetime) {
		this.initiatetime = initiatetime;
	}
	
	/**
	 * 队伍成员< ID,选择>
	 * @return
	 */

	public HashMap<Long, Integer> getMemberidmap() {
		return memberidmap;
	}
	/**
	 * 队伍成员< ID,选择>
	 * @return
	 */
	public void setMemberidmap(HashMap<Long, Integer> memberidmap) {
		this.memberidmap = memberidmap;
	}
	public int getZonemodelid() {
		return zonemodelid;
	}
	public void setZonemodelid(int zonemodelid) {
		this.zonemodelid = zonemodelid;
	}
	public int getWaittime() {
		return waittime;
	}
	public void setWaittime(int waittime) {
		this.waittime = waittime;
	}
	public int getEntertime() {
		return entertime;
	}
	public void setEntertime(int entertime) {
		this.entertime = entertime;
	}
	public int getSelectnum() {
		return selectnum;
	}
	public void setSelectnum(int selectnum) {
		this.selectnum = selectnum;
	}
	public long getTeamid() {
		return teamid;
	}
	public void setTeamid(long teamid) {
		this.teamid = teamid;
	}
	public int getTeamsum() {
		return teamsum;
	}
	public void setTeamsum(int teamsum) {
		this.teamsum = teamsum;
	}
	
	
	
	
	
}
