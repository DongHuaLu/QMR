package com.game.stalls.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 修改摊位名字消息
 */
public class ReqChangeStallsNameMessage extends Message{

	//摊位名字
	private String name;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//摊位名字
		writeString(buf, this.name);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//摊位名字
		this.name = readString(buf);
		return true;
	}
	
	/**
	 * get 摊位名字
	 * @return 
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * set 摊位名字
	 */
	public void setName(String name){
		this.name = name;
	}
	
	
	@Override
	public int getId() {
		return 123211;
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
		//摊位名字
		if(this.name!=null) buf.append("name:" + name.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}