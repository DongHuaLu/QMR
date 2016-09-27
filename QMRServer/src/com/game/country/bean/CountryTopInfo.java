package com.game.country.bean;


import com.game.message.Bean;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 王城战排行榜信息
 */
public class CountryTopInfo extends Bean {

	//玩家名字
	private String playername;
	
	//帮会名字
	private String guildname;
	
	//玩家ID
	private long playerid;
	
	//玩家等级
	private int playerlevel;
	
	//杀敌数量
	private int kill;
	
	//死亡次数
	private int death;
	
	//总伤害输出
	private int hurt;
	
	//被伤害总数
	private int beenhurt;
	
	//排名
	private int ranking;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//玩家名字
		writeString(buf, this.playername);
		//帮会名字
		writeString(buf, this.guildname);
		//玩家ID
		writeLong(buf, this.playerid);
		//玩家等级
		writeInt(buf, this.playerlevel);
		//杀敌数量
		writeInt(buf, this.kill);
		//死亡次数
		writeInt(buf, this.death);
		//总伤害输出
		writeInt(buf, this.hurt);
		//被伤害总数
		writeInt(buf, this.beenhurt);
		//排名
		writeInt(buf, this.ranking);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//玩家名字
		this.playername = readString(buf);
		//帮会名字
		this.guildname = readString(buf);
		//玩家ID
		this.playerid = readLong(buf);
		//玩家等级
		this.playerlevel = readInt(buf);
		//杀敌数量
		this.kill = readInt(buf);
		//死亡次数
		this.death = readInt(buf);
		//总伤害输出
		this.hurt = readInt(buf);
		//被伤害总数
		this.beenhurt = readInt(buf);
		//排名
		this.ranking = readInt(buf);
		return true;
	}
	
	/**
	 * get 玩家名字
	 * @return 
	 */
	public String getPlayername(){
		return playername;
	}
	
	/**
	 * set 玩家名字
	 */
	public void setPlayername(String playername){
		this.playername = playername;
	}
	
	/**
	 * get 帮会名字
	 * @return 
	 */
	public String getGuildname(){
		return guildname;
	}
	
	/**
	 * set 帮会名字
	 */
	public void setGuildname(String guildname){
		this.guildname = guildname;
	}
	
	/**
	 * get 玩家ID
	 * @return 
	 */
	public long getPlayerid(){
		return playerid;
	}
	
	/**
	 * set 玩家ID
	 */
	public void setPlayerid(long playerid){
		this.playerid = playerid;
	}
	
	/**
	 * get 玩家等级
	 * @return 
	 */
	public int getPlayerlevel(){
		return playerlevel;
	}
	
	/**
	 * set 玩家等级
	 */
	public void setPlayerlevel(int playerlevel){
		this.playerlevel = playerlevel;
	}
	
	/**
	 * get 杀敌数量
	 * @return 
	 */
	public int getKill(){
		return kill;
	}
	
	/**
	 * set 杀敌数量
	 */
	public void setKill(int kill){
		this.kill = kill;
	}
	
	/**
	 * get 死亡次数
	 * @return 
	 */
	public int getDeath(){
		return death;
	}
	
	/**
	 * set 死亡次数
	 */
	public void setDeath(int death){
		this.death = death;
	}
	
	/**
	 * get 总伤害输出
	 * @return 
	 */
	public int getHurt(){
		return hurt;
	}
	
	/**
	 * set 总伤害输出
	 */
	public void setHurt(int hurt){
		this.hurt = hurt;
	}
	
	/**
	 * get 被伤害总数
	 * @return 
	 */
	public int getBeenhurt(){
		return beenhurt;
	}
	
	/**
	 * set 被伤害总数
	 */
	public void setBeenhurt(int beenhurt){
		this.beenhurt = beenhurt;
	}
	
	/**
	 * get 排名
	 * @return 
	 */
	public int getRanking(){
		return ranking;
	}
	
	/**
	 * set 排名
	 */
	public void setRanking(int ranking){
		this.ranking = ranking;
	}
	
	@Override
	public String toString(){
		StringBuffer buf = new StringBuffer("[");
		//玩家名字
		if(this.playername!=null) buf.append("playername:" + playername.toString() +",");
		//帮会名字
		if(this.guildname!=null) buf.append("guildname:" + guildname.toString() +",");
		//玩家ID
		buf.append("playerid:" + playerid +",");
		//玩家等级
		buf.append("playerlevel:" + playerlevel +",");
		//杀敌数量
		buf.append("kill:" + kill +",");
		//死亡次数
		buf.append("death:" + death +",");
		//总伤害输出
		buf.append("hurt:" + hurt +",");
		//被伤害总数
		buf.append("beenhurt:" + beenhurt +",");
		//排名
		buf.append("ranking:" + ranking +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}