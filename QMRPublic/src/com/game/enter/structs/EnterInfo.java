package com.game.enter.structs;

/**
 * 比赛信息
 * @author Administrator
 *
 */
public class EnterInfo {

	//分配Id
	private long enterId;
	
	//队伍1
	private Team team1;
	
	//队伍2
	private Team team2;
		
	//跨服服务器id
	private int crossServer;
	
	//服务器版本
	private int version;
	
	//比赛类型
	private int matchId;

	public long getEnterId() {
		return enterId;
	}

	public void setEnterId(long enterId) {
		this.enterId = enterId;
	}

	public Team getTeam1() {
		return team1;
	}

	public void setTeam1(Team team1) {
		this.team1 = team1;
	}

	public Team getTeam2() {
		return team2;
	}

	public void setTeam2(Team team2) {
		this.team2 = team2;
	}

	public int getCrossServer() {
		return crossServer;
	}

	public void setCrossServer(int crossServer) {
		this.crossServer = crossServer;
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
		return "enterId:" + enterId + ",team1:{" + team1.toString() + "},team2:{" + team2.toString() + "},crossServer:" + crossServer + ",version:" + version;
	}
}
