package com.game.guild.bean;


import com.game.message.Bean;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 帮旗信息
 */
public class BannerInfo extends Bean {

	//帮会id
	private long guildId;
	
	//帮会名
	private String guildName;
	
	//帮会旗帜
	private String guildBanner;
	
	//帮主名字
	private String bangZhuName;
	
	//旗帜造型
	private int bannerIcon;
	
	//旗帜等级
	private byte bannerLevel;
	
	//帮会创建时间
	private int createTime;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//帮会id
		writeLong(buf, this.guildId);
		//帮会名
		writeString(buf, this.guildName);
		//帮会旗帜
		writeString(buf, this.guildBanner);
		//帮主名字
		writeString(buf, this.bangZhuName);
		//旗帜造型
		writeInt(buf, this.bannerIcon);
		//旗帜等级
		writeByte(buf, this.bannerLevel);
		//帮会创建时间
		writeInt(buf, this.createTime);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//帮会id
		this.guildId = readLong(buf);
		//帮会名
		this.guildName = readString(buf);
		//帮会旗帜
		this.guildBanner = readString(buf);
		//帮主名字
		this.bangZhuName = readString(buf);
		//旗帜造型
		this.bannerIcon = readInt(buf);
		//旗帜等级
		this.bannerLevel = readByte(buf);
		//帮会创建时间
		this.createTime = readInt(buf);
		return true;
	}
	
	/**
	 * get 帮会id
	 * @return 
	 */
	public long getGuildId(){
		return guildId;
	}
	
	/**
	 * set 帮会id
	 */
	public void setGuildId(long guildId){
		this.guildId = guildId;
	}
	
	/**
	 * get 帮会名
	 * @return 
	 */
	public String getGuildName(){
		return guildName;
	}
	
	/**
	 * set 帮会名
	 */
	public void setGuildName(String guildName){
		this.guildName = guildName;
	}
	
	/**
	 * get 帮会旗帜
	 * @return 
	 */
	public String getGuildBanner(){
		return guildBanner;
	}
	
	/**
	 * set 帮会旗帜
	 */
	public void setGuildBanner(String guildBanner){
		this.guildBanner = guildBanner;
	}
	
	/**
	 * get 帮主名字
	 * @return 
	 */
	public String getBangZhuName(){
		return bangZhuName;
	}
	
	/**
	 * set 帮主名字
	 */
	public void setBangZhuName(String bangZhuName){
		this.bangZhuName = bangZhuName;
	}
	
	/**
	 * get 旗帜造型
	 * @return 
	 */
	public int getBannerIcon(){
		return bannerIcon;
	}
	
	/**
	 * set 旗帜造型
	 */
	public void setBannerIcon(int bannerIcon){
		this.bannerIcon = bannerIcon;
	}
	
	/**
	 * get 旗帜等级
	 * @return 
	 */
	public byte getBannerLevel(){
		return bannerLevel;
	}
	
	/**
	 * set 旗帜等级
	 */
	public void setBannerLevel(byte bannerLevel){
		this.bannerLevel = bannerLevel;
	}
	
	/**
	 * get 帮会创建时间
	 * @return 
	 */
	public int getCreateTime(){
		return createTime;
	}
	
	/**
	 * set 帮会创建时间
	 */
	public void setCreateTime(int createTime){
		this.createTime = createTime;
	}
	
	@Override
	public String toString(){
		StringBuffer buf = new StringBuffer("[");
		//帮会id
		buf.append("guildId:" + guildId +",");
		//帮会名
		if(this.guildName!=null) buf.append("guildName:" + guildName.toString() +",");
		//帮会旗帜
		if(this.guildBanner!=null) buf.append("guildBanner:" + guildBanner.toString() +",");
		//帮主名字
		if(this.bangZhuName!=null) buf.append("bangZhuName:" + bangZhuName.toString() +",");
		//旗帜造型
		buf.append("bannerIcon:" + bannerIcon +",");
		//旗帜等级
		buf.append("bannerLevel:" + bannerLevel +",");
		//帮会创建时间
		buf.append("createTime:" + createTime +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}