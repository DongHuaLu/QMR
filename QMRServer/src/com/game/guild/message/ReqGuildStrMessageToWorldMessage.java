package com.game.guild.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 帮派公告通知消息
 */
public class ReqGuildStrMessageToWorldMessage extends Message{

	//帮派Id
	private long guildId;
	
	//帮会通知内容
	private String content;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//帮派Id
		writeLong(buf, this.guildId);
		//帮会通知内容
		writeString(buf, this.content);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//帮派Id
		this.guildId = readLong(buf);
		//帮会通知内容
		this.content = readString(buf);
		return true;
	}
	
	/**
	 * get 帮派Id
	 * @return 
	 */
	public long getGuildId(){
		return guildId;
	}
	
	/**
	 * set 帮派Id
	 */
	public void setGuildId(long guildId){
		this.guildId = guildId;
	}
	
	/**
	 * get 帮会通知内容
	 * @return 
	 */
	public String getContent(){
		return content;
	}
	
	/**
	 * set 帮会通知内容
	 */
	public void setContent(String content){
		this.content = content;
	}
	
	
	@Override
	public int getId() {
		return 121324;
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
		//帮派Id
		buf.append("guildId:" + guildId +",");
		//帮会通知内容
		if(this.content!=null) buf.append("content:" + content.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}