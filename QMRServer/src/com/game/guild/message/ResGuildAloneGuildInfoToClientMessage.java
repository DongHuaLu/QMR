package com.game.guild.message;

import com.game.guild.bean.GuildInfo;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 发送单独帮会信息给客户端消息
 */
public class ResGuildAloneGuildInfoToClientMessage extends Message{

	//通知类型 0 创建 1 添加或更新 2 删除 等
	private byte notifyType;
	
	//单独帮会信息
	private GuildInfo guildInfo;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//通知类型 0 创建 1 添加或更新 2 删除 等
		writeByte(buf, this.notifyType);
		//单独帮会信息
		writeBean(buf, this.guildInfo);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//通知类型 0 创建 1 添加或更新 2 删除 等
		this.notifyType = readByte(buf);
		//单独帮会信息
		this.guildInfo = (GuildInfo)readBean(buf, GuildInfo.class);
		return true;
	}
	
	/**
	 * get 通知类型 0 创建 1 添加或更新 2 删除 等
	 * @return 
	 */
	public byte getNotifyType(){
		return notifyType;
	}
	
	/**
	 * set 通知类型 0 创建 1 添加或更新 2 删除 等
	 */
	public void setNotifyType(byte notifyType){
		this.notifyType = notifyType;
	}
	
	/**
	 * get 单独帮会信息
	 * @return 
	 */
	public GuildInfo getGuildInfo(){
		return guildInfo;
	}
	
	/**
	 * set 单独帮会信息
	 */
	public void setGuildInfo(GuildInfo guildInfo){
		this.guildInfo = guildInfo;
	}
	
	
	@Override
	public int getId() {
		return 121390;
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
		//通知类型 0 创建 1 添加或更新 2 删除 等
		buf.append("notifyType:" + notifyType +",");
		//单独帮会信息
		if(this.guildInfo!=null) buf.append("guildInfo:" + guildInfo.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}