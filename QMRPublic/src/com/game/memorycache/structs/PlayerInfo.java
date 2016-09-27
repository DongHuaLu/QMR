package com.game.memorycache.structs;

public class PlayerInfo {

	//玩家角色id
	private long playerId;
	//数据服务器player id
	private long dataServerPlayerId;
	//玩家账号id
	private String userId;
	//玩家账号名字
	private String name;
	//玩家账号所在服务器
	private int serverId;
	//玩家账号平台
	private String web;
	//玩家战力
	private int power;
	//玩家总胜利场数
	private int victory;
	//玩家连胜场数
	private int series;
	//玩家上一场是否胜利
	private int previctory;
	//玩家战斗总场数
	private int matchtimes;
	//玩家最大连胜场数
	private int maxseries;
	//玩家当天最大连胜场数
	private int todayMaxseries;
	//玩家当天当前连胜场数
	private int todaySeries;
	//玩家连胜时间
	private long victoryTime;
	//胜负记录
	private int matchinfo;
	//玩家主数据
	private String data;
	//最后同步时间
	private long time;
	
	public long getPlayerId() {
		return playerId;
	}
	public void setPlayerId(long playerId) {
		this.playerId = playerId;
	}
	public long getDataServerPlayerId() {
		return dataServerPlayerId;
	}
	public void setDataServerPlayerId(long dataServerPlayerId) {
		this.dataServerPlayerId = dataServerPlayerId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getServerId() {
		return serverId;
	}
	public void setServerId(int serverId) {
		this.serverId = serverId;
	}
	public String getWeb() {
		return web;
	}
	public void setWeb(String web) {
		this.web = web;
	}
	public int getPower() {
		return power;
	}
	public void setPower(int power) {
		this.power = power;
	}
	public int getVictory() {
		return victory;
	}
	public void setVictory(int victory) {
		this.victory = victory;
	}
	public int getSeries() {
		return series;
	}
	public void setSeries(int series) {
		this.series = series;
	}
	public int getPrevictory() {
		return previctory;
	}
	public void setPrevictory(int previctory) {
		this.previctory = previctory;
	}
	public int getMatchtimes() {
		return matchtimes;
	}
	public void setMatchtimes(int matchtimes) {
		this.matchtimes = matchtimes;
	}
	public int getMaxseries() {
		return maxseries;
	}
	public void setMaxseries(int maxseries) {
		this.maxseries = maxseries;
	}
	public int getMatchinfo() {
		return matchinfo;
	}
	public void setMatchinfo(int matchinfo) {
		this.matchinfo = matchinfo;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
	public int getTodayMaxseries() {
		return todayMaxseries;
	}
	public void setTodayMaxseries(int todayMaxseries) {
		this.todayMaxseries = todayMaxseries;
	}
	public int getTodaySeries() {
		return todaySeries;
	}
	public void setTodaySeries(int todaySeries) {
		this.todaySeries = todaySeries;
	}
	public long getVictoryTime() {
		return victoryTime;
	}
	public void setVictoryTime(long victoryTime) {
		this.victoryTime = victoryTime;
	}
	
	@Override
	public String toString(){
		return "playerId:" + playerId + ",dataServerPlayerId:" + dataServerPlayerId + ",userId:" + userId + ",name:" + name + ",serverId:" + serverId + ",web:" + web + ",todayMaxseries:" + todayMaxseries + ",todaySeries:" + todaySeries + ",victoryTime:" + victoryTime;
	}
}
