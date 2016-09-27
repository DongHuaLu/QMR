package com.game.monster.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 双倍经验消息消息
 */
public class ResMonsterDoubleNoticeMessage extends Message{

	//类型：1双倍经验，2其他。。。
	private byte type;
	
	//1开启，0关闭
	private byte status;
	
	//内容
	private String content;
	
	//剩余时间（秒）
	private int time;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//类型：1双倍经验，2其他。。。
		writeByte(buf, this.type);
		//1开启，0关闭
		writeByte(buf, this.status);
		//内容
		writeString(buf, this.content);
		//剩余时间（秒）
		writeInt(buf, this.time);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//类型：1双倍经验，2其他。。。
		this.type = readByte(buf);
		//1开启，0关闭
		this.status = readByte(buf);
		//内容
		this.content = readString(buf);
		//剩余时间（秒）
		this.time = readInt(buf);
		return true;
	}
	
	/**
	 * get 类型：1双倍经验，2其他。。。
	 * @return 
	 */
	public byte getType(){
		return type;
	}
	
	/**
	 * set 类型：1双倍经验，2其他。。。
	 */
	public void setType(byte type){
		this.type = type;
	}
	
	/**
	 * get 1开启，0关闭
	 * @return 
	 */
	public byte getStatus(){
		return status;
	}
	
	/**
	 * set 1开启，0关闭
	 */
	public void setStatus(byte status){
		this.status = status;
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
	 * get 剩余时间（秒）
	 * @return 
	 */
	public int getTime(){
		return time;
	}
	
	/**
	 * set 剩余时间（秒）
	 */
	public void setTime(int time){
		this.time = time;
	}
	
	
	@Override
	public int getId() {
		return 114111;
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
		//类型：1双倍经验，2其他。。。
		buf.append("type:" + type +",");
		//1开启，0关闭
		buf.append("status:" + status +",");
		//内容
		if(this.content!=null) buf.append("content:" + content.toString() +",");
		//剩余时间（秒）
		buf.append("time:" + time +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}