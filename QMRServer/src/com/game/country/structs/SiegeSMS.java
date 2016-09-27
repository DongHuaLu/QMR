package com.game.country.structs;

import com.game.country.bean.CountryTopInfo;
import com.game.guild.structs.GuildTmpInfo;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;


public class SiegeSMS {
	private long playerid;
	private int playerlevel;	
	private String playername;//玩家名字
	private String guildname;//帮会名
	private int kill;	//杀敌数量
	private int death;	//死亡次数
	private int hurt;	//总伤害输出
	private int beenhurt;	//被伤害总数
	
	public SiegeSMS(Player player){
		this.setPlayerid(player.getId());
		this.setPlayerlevel(player.getLevel());
		this.setPlayername(player.getName());
		GuildTmpInfo guildTmpInfo = ManagerPool.guildServerManager.getGuildTmpInfo(player.getGuildId());
		if (guildTmpInfo != null) {
			this.setGuildname(guildTmpInfo.getGuildname());
		}else {
			this.setGuildname("无");
		}
	}
	
	
	/**生成排行信息
	 * 
	 * @return
	 */
	public CountryTopInfo getinfo(){
		CountryTopInfo countryTopInfo = new CountryTopInfo();
		countryTopInfo.setBeenhurt(this.getBeenhurt());
		countryTopInfo.setDeath(this.getDeath());
		countryTopInfo.setHurt(this.getHurt());
		countryTopInfo.setKill(this.getKill());
		countryTopInfo.setPlayerid(this.getPlayerid());
		countryTopInfo.setGuildname(this.getGuildname());
		countryTopInfo.setPlayername(this.getPlayername());
		countryTopInfo.setPlayerlevel(this.getPlayerlevel());
		return countryTopInfo;
	}
	


	public long getPlayerid() {
		return playerid;
	}
	public void setPlayerid(long playerid) {
		this.playerid = playerid;
	}

	public String getGuildname() {
		return guildname;
	}
	public void setGuildname(String guildname) {
		this.guildname = guildname;
	}
	public int getKill() {
		return kill;
	}
	public void setKill(int kill) {
		this.kill = kill;
	}
	public int getDeath() {
		return death;
	}
	public void setDeath(int death) {
		this.death = death;
	}
	public int getHurt() {
		return hurt;
	}
	public void setHurt(int hurt) {
		this.hurt = hurt;
	}
	
	
	public int getBeenhurt() {
		return beenhurt;
	}
	public void setBeenhurt(int beenhurt) {
		this.beenhurt = beenhurt;
	}

	public String getPlayername() {
		return playername;
	}

	public void setPlayername(String playername) {
		this.playername = playername;
	}

	public int getPlayerlevel() {
		return playerlevel;
	}

	public void setPlayerlevel(int playerlevel) {
		this.playerlevel = playerlevel;
	}
	
	
	
	
	
	
	
}
