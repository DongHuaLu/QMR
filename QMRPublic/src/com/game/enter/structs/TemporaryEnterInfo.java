package com.game.enter.structs;

/**
 * 临时比赛匹配信息（等待验证）
 * @author heyang
 *
 */
public class TemporaryEnterInfo {

	//分配Id
	private long enterId;
	
	//队伍1
	private Team team1;
	
	//队伍2
	private Team team2;
	
	//分配时间
	private long time;
	
	//服务器版本
	private int version;
	
	//比赛类型
	private int matchId;
	
	//一队准备情况
	private int[] prepare1 = new int[4];

	//二队准备情况
	private int[] prepare2 = new int[4];
	
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

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public int[] getPrepare1() {
		return prepare1;
	}

	public void setPrepare1(int[] prepare1) {
		this.prepare1 = prepare1;
	}

	public int[] getPrepare2() {
		return prepare2;
	}

	public void setPrepare2(int[] prepare2) {
		this.prepare2 = prepare2;
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
		return "enterId:" + enterId + ",team1:{" + team1.toString() + "},team2:{" + team2.toString() + "},time:" + time + ",prepare1:{" + prepare1[0] + "," + prepare1[1] + "," + prepare1[2] + "," + prepare1[3] + "},prepare2:{" + prepare2[0] + "," + prepare2[1] + "," + prepare2[2] + "," + prepare2[3] + "}" + ",version:" + version ;
	}
}
