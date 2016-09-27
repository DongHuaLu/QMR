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
public class ResChangeStallsNameMessage extends Message{

	//摊位名字
	private String name;
	
	//修改结果，0成功1失败
	private byte status;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//摊位名字
		writeString(buf, this.name);
		//修改结果，0成功1失败
		writeByte(buf, this.status);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//摊位名字
		this.name = readString(buf);
		//修改结果，0成功1失败
		this.status = readByte(buf);
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
	
	/**
	 * get 修改结果，0成功1失败
	 * @return 
	 */
	public byte getStatus(){
		return status;
	}
	
	/**
	 * set 修改结果，0成功1失败
	 */
	public void setStatus(byte status){
		this.status = status;
	}
	
	
	@Override
	public int getId() {
		return 123110;
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
		//修改结果，0成功1失败
		buf.append("status:" + status +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}