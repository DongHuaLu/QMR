package com.game.biwudao.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 比武岛旗帜占领者帮会名字更新消息
 */
public class ResBiWuDaoGuildnameToClientMessage extends Message{

	//旗帜占领者帮会名字
	private String guildname;
	
	//旗帜占领者帮会id
	private long guildid;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//旗帜占领者帮会名字
		writeString(buf, this.guildname);
		//旗帜占领者帮会id
		writeLong(buf, this.guildid);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//旗帜占领者帮会名字
		this.guildname = readString(buf);
		//旗帜占领者帮会id
		this.guildid = readLong(buf);
		return true;
	}
	
	/**
	 * get 旗帜占领者帮会名字
	 * @return 
	 */
	public String getGuildname(){
		return guildname;
	}
	
	/**
	 * set 旗帜占领者帮会名字
	 */
	public void setGuildname(String guildname){
		this.guildname = guildname;
	}
	
	/**
	 * get 旗帜占领者帮会id
	 * @return 
	 */
	public long getGuildid(){
		return guildid;
	}
	
	/**
	 * set 旗帜占领者帮会id
	 */
	public void setGuildid(long guildid){
		this.guildid = guildid;
	}
	
	
	@Override
	public int getId() {
		return 157104;
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
		//旗帜占领者帮会名字
		if(this.guildname!=null) buf.append("guildname:" + guildname.toString() +",");
		//旗帜占领者帮会id
		buf.append("guildid:" + guildid +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}