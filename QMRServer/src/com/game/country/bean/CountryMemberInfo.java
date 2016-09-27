package com.game.country.bean;


import com.game.message.Bean;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 王城成员信息
 */
public class CountryMemberInfo extends Bean {

	//职位：1城主，2列侯，3丞相，4太尉，5御史大夫，6夫人
	private byte post;
	
	//玩家ID
	private long playerid;
	
	//玩家名字
	private String playername;
	
	//玩家性别
	private int sex;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//职位：1城主，2列侯，3丞相，4太尉，5御史大夫，6夫人
		writeByte(buf, this.post);
		//玩家ID
		writeLong(buf, this.playerid);
		//玩家名字
		writeString(buf, this.playername);
		//玩家性别
		writeInt(buf, this.sex);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//职位：1城主，2列侯，3丞相，4太尉，5御史大夫，6夫人
		this.post = readByte(buf);
		//玩家ID
		this.playerid = readLong(buf);
		//玩家名字
		this.playername = readString(buf);
		//玩家性别
		this.sex = readInt(buf);
		return true;
	}
	
	/**
	 * get 职位：1城主，2列侯，3丞相，4太尉，5御史大夫，6夫人
	 * @return 
	 */
	public byte getPost(){
		return post;
	}
	
	/**
	 * set 职位：1城主，2列侯，3丞相，4太尉，5御史大夫，6夫人
	 */
	public void setPost(byte post){
		this.post = post;
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
	 * get 玩家性别
	 * @return 
	 */
	public int getSex(){
		return sex;
	}
	
	/**
	 * set 玩家性别
	 */
	public void setSex(int sex){
		this.sex = sex;
	}
	
	@Override
	public String toString(){
		StringBuffer buf = new StringBuffer("[");
		//职位：1城主，2列侯，3丞相，4太尉，5御史大夫，6夫人
		buf.append("post:" + post +",");
		//玩家ID
		buf.append("playerid:" + playerid +",");
		//玩家名字
		if(this.playername!=null) buf.append("playername:" + playername.toString() +",");
		//玩家性别
		buf.append("sex:" + sex +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}