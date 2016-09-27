package com.game.enter.structs;

/**
 * 队伍成员信息
 * @author heyang
 *
 */
public class TeamMember {

	//玩家Id(原服务器)
	private long playerId;
	
	//玩家Id(跨服服务器)
	private long dataServerPlayerId;
		
	//队伍Id(原服务器)
	private long teamId;
	
	//队伍Id(跨服服务器)
	private long dataServerTeamId;
	
	//比赛Id(跨服服务器)
	private long enterId;
	
	//玩家所在平台
	private String web;
	
	//玩家所在服务器
	private int serverId;
	
	//玩家战斗力
	private int power;
	
	//队伍成员连胜之和
	private int victory;
	
	//匹配比赛时间
	private long matchTime;
	
	//报名时间
	private long enterTime;
	
	//服务器版本
	private int version;
		
	//比赛类型
	private int matchId;

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

	public long getTeamId() {
		return teamId;
	}

	public void setTeamId(long teamId) {
		this.teamId = teamId;
	}

	public long getDataServerTeamId() {
		return dataServerTeamId;
	}

	public void setDataServerTeamId(long dataServerTeamId) {
		this.dataServerTeamId = dataServerTeamId;
	}

	public long getEnterId() {
		return enterId;
	}

	public void setEnterId(long enterId) {
		this.enterId = enterId;
	}

	public String getWeb() {
		return web;
	}

	public void setWeb(String web) {
		this.web = web;
	}

	public int getServerId() {
		return serverId;
	}

	public void setServerId(int serverId) {
		this.serverId = serverId;
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

	public long getMatchTime() {
		return matchTime;
	}

	public void setMatchTime(long matchTime) {
		this.matchTime = matchTime;
	}

	public long getEnterTime() {
		return enterTime;
	}

	public void setEnterTime(long enterTime) {
		this.enterTime = enterTime;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public int getMatchId() {
		return matchId;
	}

	public void setMatchId(int matchId) {
		this.matchId = matchId;
	}

	@Override
	public String toString(){
		String s = "playerId:" + playerId + ",dataServerPlayerId:" + dataServerPlayerId + ",teamId:" + teamId + ",dataServerTeamId:" + dataServerTeamId + ",enterId:"+ enterId +", power:" + power + ",web:" + web + ",serverId:" + serverId + ",version:" + version;
		return s;
	}
}
