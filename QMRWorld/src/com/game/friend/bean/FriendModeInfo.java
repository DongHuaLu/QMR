package com.game.friend.bean;


import com.game.message.Bean;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 好友角色造型信息
 */
public class FriendModeInfo extends Bean {

	//玩家ID
	private long playerid;
	
	//玩家名字
	private String playername;
	
	//玩家等级
	private int playerlv;
	
	//造型信息
	private com.game.player.bean.PlayerAppearanceInfo appearanceInfo;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//玩家ID
		writeLong(buf, this.playerid);
		//玩家名字
		writeString(buf, this.playername);
		//玩家等级
		writeInt(buf, this.playerlv);
		//造型信息
		writeBean(buf, this.appearanceInfo);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//玩家ID
		this.playerid = readLong(buf);
		//玩家名字
		this.playername = readString(buf);
		//玩家等级
		this.playerlv = readInt(buf);
		//造型信息
		this.appearanceInfo = (com.game.player.bean.PlayerAppearanceInfo)readBean(buf, com.game.player.bean.PlayerAppearanceInfo.class);
		return true;
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
	 * get 玩家等级
	 * @return 
	 */
	public int getPlayerlv(){
		return playerlv;
	}
	
	/**
	 * set 玩家等级
	 */
	public void setPlayerlv(int playerlv){
		this.playerlv = playerlv;
	}
	
	/**
	 * get 造型信息
	 * @return 
	 */
	public com.game.player.bean.PlayerAppearanceInfo getAppearanceInfo(){
		return appearanceInfo;
	}
	
	/**
	 * set 造型信息
	 */
	public void setAppearanceInfo(com.game.player.bean.PlayerAppearanceInfo appearanceInfo){
		this.appearanceInfo = appearanceInfo;
	}
	
	@Override
	public String toString(){
		StringBuffer buf = new StringBuffer("[");
		//玩家ID
		buf.append("playerid:" + playerid +",");
		//玩家名字
		if(this.playername!=null) buf.append("playername:" + playername.toString() +",");
		//玩家等级
		buf.append("playerlv:" + playerlv +",");
		//造型信息
		if(this.appearanceInfo!=null) buf.append("appearanceInfo:" + appearanceInfo.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}