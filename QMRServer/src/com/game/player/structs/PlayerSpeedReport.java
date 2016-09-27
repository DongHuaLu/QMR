package com.game.player.structs;

import java.util.concurrent.ConcurrentHashMap;

//玩家加速举报 做成对象方便拓展
public class PlayerSpeedReport {
	
	//被举报者ID
	private String roleid;
	
	//被举报次数
	private int reporttimes;  
	
	//最后被举报时间
	private long lastreporttime;
	
	//举报者列表 key=举报者id value=举报时间
	private ConcurrentHashMap<String, Long> reportmap;
	
	public long getLastreporttime() {
		return lastreporttime;
	}

	public void setLastreporttime(long lastreporttime) {
		this.lastreporttime = lastreporttime;
	}

	public String getRoleid() {
		return roleid;
	}

	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}

	public int getReporttimes() {
		return reporttimes;
	}

	public void setReporttimes(int reporttimes) {
		this.reporttimes = reporttimes;
	}

	public PlayerSpeedReport(){
		reportmap = new ConcurrentHashMap<String, Long>();
	}

	public ConcurrentHashMap<String, Long> getReportmap() {
		return reportmap;
	}

	public void setReportmap(ConcurrentHashMap<String, Long> reportmap) {
		this.reportmap = reportmap;
	}
	
}
