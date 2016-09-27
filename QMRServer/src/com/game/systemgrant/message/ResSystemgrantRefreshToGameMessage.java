package com.game.systemgrant.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 通知游戏服务器更新数据消息
 */
public class ResSystemgrantRefreshToGameMessage extends Message{

	//类型，1更新，2删除
	private int type;
	
	//jisn字符串
	private String josnstr;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//类型，1更新，2删除
		writeInt(buf, this.type);
		//jisn字符串
		writeString(buf, this.josnstr);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//类型，1更新，2删除
		this.type = readInt(buf);
		//jisn字符串
		this.josnstr = readString(buf);
		return true;
	}
	
	/**
	 * get 类型，1更新，2删除
	 * @return 
	 */
	public int getType(){
		return type;
	}
	
	/**
	 * set 类型，1更新，2删除
	 */
	public void setType(int type){
		this.type = type;
	}
	
	/**
	 * get jisn字符串
	 * @return 
	 */
	public String getJosnstr(){
		return josnstr;
	}
	
	/**
	 * set jisn字符串
	 */
	public void setJosnstr(String josnstr){
		this.josnstr = josnstr;
	}
	
	
	@Override
	public int getId() {
		return 161301;
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
		//类型，1更新，2删除
		buf.append("type:" + type +",");
		//jisn字符串
		if(this.josnstr!=null) buf.append("josnstr:" + josnstr.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}