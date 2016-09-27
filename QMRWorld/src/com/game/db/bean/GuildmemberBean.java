package com.game.db.bean;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Guildmember Bean
 */
public class GuildmemberBean {

	//玩家id
	private long userid;
	
	//公会id
	private long guildid;
	
	//玩家名字
	private String username;
	
	//公会名字
	private String guildname;
	
	//玩家帮会数据
	private String guildmemberdata;
	
	
	/**
	 * get 玩家id
	 * @return 
	 */
	public long getUserid(){
		return userid;
	}
	
	/**
	 * set 玩家id
	 */
	public void setUserid(long userid){
		this.userid = userid;
	}
	
	/**
	 * get 公会id
	 * @return 
	 */
	public long getGuildid(){
		return guildid;
	}
	
	/**
	 * set 公会id
	 */
	public void setGuildid(long guildid){
		this.guildid = guildid;
	}
	
	/**
	 * get 玩家名字
	 * @return 
	 */
	public String getUsername(){
		return username;
	}
	
	/**
	 * set 玩家名字
	 */
	public void setUsername(String username){
		this.username = username;
	}
	
	/**
	 * get 公会名字
	 * @return 
	 */
	public String getGuildname(){
		return guildname;
	}
	
	/**
	 * set 公会名字
	 */
	public void setGuildname(String guildname){
		this.guildname = guildname;
	}
	
	/**
	 * get 玩家帮会数据
	 * @return 
	 */
	public String getGuildmemberdata(){
		return guildmemberdata;
	}
	
	/**
	 * set 玩家帮会数据
	 */
	public void setGuildmemberdata(String guildmemberdata){
		this.guildmemberdata = guildmemberdata;
	}
	
}