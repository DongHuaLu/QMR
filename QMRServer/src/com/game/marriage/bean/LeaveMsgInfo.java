package com.game.marriage.bean;


import com.game.message.Bean;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 留言信息
 */
public class LeaveMsgInfo extends Bean {

	//发布时间
	private int time;
	
	//发布人名字
	private String playername;
	
	//内容
	private String content;
	
	//1未读取，0已经读取
	private byte alread;
	
	//当前留言索引ID
	private long msgid;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//发布时间
		writeInt(buf, this.time);
		//发布人名字
		writeString(buf, this.playername);
		//内容
		writeString(buf, this.content);
		//1未读取，0已经读取
		writeByte(buf, this.alread);
		//当前留言索引ID
		writeLong(buf, this.msgid);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//发布时间
		this.time = readInt(buf);
		//发布人名字
		this.playername = readString(buf);
		//内容
		this.content = readString(buf);
		//1未读取，0已经读取
		this.alread = readByte(buf);
		//当前留言索引ID
		this.msgid = readLong(buf);
		return true;
	}
	
	/**
	 * get 发布时间
	 * @return 
	 */
	public int getTime(){
		return time;
	}
	
	/**
	 * set 发布时间
	 */
	public void setTime(int time){
		this.time = time;
	}
	
	/**
	 * get 发布人名字
	 * @return 
	 */
	public String getPlayername(){
		return playername;
	}
	
	/**
	 * set 发布人名字
	 */
	public void setPlayername(String playername){
		this.playername = playername;
	}
	
	/**
	 * get 内容
	 * @return 
	 */
	public String getContent(){
		return content;
	}
	
	/**
	 * set 内容
	 */
	public void setContent(String content){
		this.content = content;
	}
	
	/**
	 * get 1未读取，0已经读取
	 * @return 
	 */
	public byte getAlread(){
		return alread;
	}
	
	/**
	 * set 1未读取，0已经读取
	 */
	public void setAlread(byte alread){
		this.alread = alread;
	}
	
	/**
	 * get 当前留言索引ID
	 * @return 
	 */
	public long getMsgid(){
		return msgid;
	}
	
	/**
	 * set 当前留言索引ID
	 */
	public void setMsgid(long msgid){
		this.msgid = msgid;
	}
	
	@Override
	public String toString(){
		StringBuffer buf = new StringBuffer("[");
		//发布时间
		buf.append("time:" + time +",");
		//发布人名字
		if(this.playername!=null) buf.append("playername:" + playername.toString() +",");
		//内容
		if(this.content!=null) buf.append("content:" + content.toString() +",");
		//1未读取，0已经读取
		buf.append("alread:" + alread +",");
		//当前留言索引ID
		buf.append("msgid:" + msgid +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}