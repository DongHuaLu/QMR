package com.game.guild.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 通知世界服务器创建公会消息
 */
public class ReqInnerGuildCreateToWorldMessage extends Message{

	//角色Id
	private long playerId;
	
	//公会名字
	private String guildName;
	
	//公会旗帜
	private String guildBanner;
	
	//公会旗帜造型
	private int guildBannerIcon;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//角色Id
		writeLong(buf, this.playerId);
		//公会名字
		writeString(buf, this.guildName);
		//公会旗帜
		writeString(buf, this.guildBanner);
		//公会旗帜造型
		writeInt(buf, this.guildBannerIcon);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//角色Id
		this.playerId = readLong(buf);
		//公会名字
		this.guildName = readString(buf);
		//公会旗帜
		this.guildBanner = readString(buf);
		//公会旗帜造型
		this.guildBannerIcon = readInt(buf);
		return true;
	}
	
	/**
	 * get 角色Id
	 * @return 
	 */
	public long getPlayerId(){
		return playerId;
	}
	
	/**
	 * set 角色Id
	 */
	public void setPlayerId(long playerId){
		this.playerId = playerId;
	}
	
	/**
	 * get 公会名字
	 * @return 
	 */
	public String getGuildName(){
		return guildName;
	}
	
	/**
	 * set 公会名字
	 */
	public void setGuildName(String guildName){
		this.guildName = guildName;
	}
	
	/**
	 * get 公会旗帜
	 * @return 
	 */
	public String getGuildBanner(){
		return guildBanner;
	}
	
	/**
	 * set 公会旗帜
	 */
	public void setGuildBanner(String guildBanner){
		this.guildBanner = guildBanner;
	}
	
	/**
	 * get 公会旗帜造型
	 * @return 
	 */
	public int getGuildBannerIcon(){
		return guildBannerIcon;
	}
	
	/**
	 * set 公会旗帜造型
	 */
	public void setGuildBannerIcon(int guildBannerIcon){
		this.guildBannerIcon = guildBannerIcon;
	}
	
	
	@Override
	public int getId() {
		return 121301;
	}

	@Override
	public String getQueue() {
		return null;
	}
	
	@Override
	public String getServer() {
		return null;
	}
	
	@Override
	public String toString(){
		StringBuffer buf = new StringBuffer("[");
		//角色Id
		buf.append("playerId:" + playerId +",");
		//公会名字
		if(this.guildName!=null) buf.append("guildName:" + guildName.toString() +",");
		//公会旗帜
		if(this.guildBanner!=null) buf.append("guildBanner:" + guildBanner.toString() +",");
		//公会旗帜造型
		buf.append("guildBannerIcon:" + guildBannerIcon +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}