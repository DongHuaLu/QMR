package com.game.enter.structs;

import java.util.ArrayList;
import java.util.List;

/**
 * 团队报名信息
 * @author heyang
 *
 */
public class Team {

	//队伍Id(原服务器)
	private long teamId;
	
	//队伍Id(跨服服务器)
	private long dataServerTeamId;
	
	//队伍成员信息
	private List<TeamMember> players = new ArrayList<TeamMember>();
	
	//队伍战斗力
	private int power;
	
	//队伍成员连胜之和
	private int victory;
	
	//玩家所在平台
	private String web;
	
	//玩家所在服务器
	private int serverId;
	
	//匹配比赛时间
	private long matchTime;
	
	//报名时间
	private long enterTime;
	
	//服务器版本
	private int version;
		
	//比赛类型
	private int matchId;

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

	public List<TeamMember> getPlayers() {
		return players;
	}

	public void setPlayers(List<TeamMember> players) {
		this.players = players;
	}

	public int getPower() {
		return power;
	}

	public void setPower(int power) {
		this.power = power;
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

	public int getVictory() {
		return victory;
	}

	public void setVictory(int victory) {
		this.victory = victory;
	}

	@Override
	public String toString(){
		String s = "teamId:" + teamId + ",dataServerTeamId:" + dataServerTeamId + ",players:{";
		for (int i = 0; i < players.size(); i++) {
			s += players.get(i).toString() + ",";
		}
		s += "}, power:" + power + ",web:" + web + ",serverId:" + serverId + ",version:" + version;
		return s;
	}
}
