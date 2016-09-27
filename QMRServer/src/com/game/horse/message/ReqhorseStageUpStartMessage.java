package com.game.horse.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 开始坐骑升阶消息
 */
public class ReqhorseStageUpStartMessage extends Message{

	//0手动进阶，1自动进阶
	private byte type;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//0手动进阶，1自动进阶
		writeByte(buf, this.type);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//0手动进阶，1自动进阶
		this.type = readByte(buf);
		return true;
	}
	
	/**
	 * get 0手动进阶，1自动进阶
	 * @return 
	 */
	public byte getType(){
		return type;
	}
	
	/**
	 * set 0手动进阶，1自动进阶
	 */
	public void setType(byte type){
		this.type = type;
	}
	
	
	@Override
	public int getId() {
		return 126204;
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
		//0手动进阶，1自动进阶
		buf.append("type:" + type +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}