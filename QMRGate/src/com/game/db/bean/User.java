package com.game.db.bean;

public class User {

	private String username;
	private Long userid;
	private Long createtime;
	private Long lastlogintime;
	private Integer server;
	private Integer isForbid;
	private String agentPlusdata;
	private String agentColdatas;
	private String agent;
	private String ad;
	private String adregtime;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public Long getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Long createtime) {
		this.createtime = createtime;
	}

	public Long getLastlogintime() {
		return lastlogintime;
	}

	public void setLastlogintime(Long lastlogintime) {
		this.lastlogintime = lastlogintime;
	}

	public Integer getServer() {
		return server;
	}

	public void setServer(Integer server) {
		this.server = server;
	}

	public Integer getIsForbid() {
		return isForbid;
	}

	public void setIsForbid(Integer isForbid) {
		this.isForbid = isForbid;
	}

	public String getAgentPlusdata() {
		return agentPlusdata;
	}

	public void setAgentPlusdata(String agentPlusdata) {
		this.agentPlusdata = agentPlusdata;
	}

	public String getAgentColdatas() {
		return agentColdatas;
	}

	public void setAgentColdatas(String agentColdatas) {
		this.agentColdatas = agentColdatas;
	}

	public String getAgent() {
		return agent;
	}

	public void setAgent(String agent) {
		this.agent = agent;
	}

	public String getAd() {
		return ad;
	}

	public void setAd(String ad) {
		this.ad = ad;
	}

	public String getAdregtime() {
		return adregtime;
	}

	public void setAdregtime(String adregtime) {
		this.adregtime = adregtime;
	}
	
}