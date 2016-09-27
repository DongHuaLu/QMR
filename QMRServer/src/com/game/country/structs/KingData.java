package com.game.country.structs;

import java.util.Date;

/**
 * 秦王数据
 *
 * @author 杨鸿岚
 */
public class KingData {

	private int term;		//任期
	private long playerid;		//玩家id
	private String playername;	//玩家名字
	private long reigntime;		//上位时间  秒
	private long abdicatetime;		//退位结束  秒
	
	
	public static void main(String[] args) {
		System.out.println(new Date(1348923935000L));
	}
	
	public long getPlayerid() {
		return playerid;
	}

	public void setPlayerid(long playerid) {
		this.playerid = playerid;
	}

	public String getPlayername() {
		return playername;
	}

	public void setPlayername(String playername) {
		this.playername = playername;
	}

	public int getTerm() {
		return term;
	}

	public void setTerm(int term) {
		this.term = term;
	}

	public long getReigntime() {
		return reigntime;
	}

	public void setReigntime(long reigntime) {
		this.reigntime = reigntime;
	}

	/**退位时间
	 * 
	 * @return
	 */
	public long getAbdicatetime() {
		return abdicatetime;
	}
	/**退位时间
	 * 
	 * @return
	 */
	public void setAbdicatetime(long abdicatetime) {
		this.abdicatetime = abdicatetime;
	}
}
