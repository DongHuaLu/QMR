package com.game.zones.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 通关选择奖励-翻牌消息
 */
public class ReqSelectAwardMessage extends Message{

	//翻牌编号（0-11）
	private int idx;
	
	//类型:0手动，1自动扫荡
	private byte type;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//翻牌编号（0-11）
		writeInt(buf, this.idx);
		//类型:0手动，1自动扫荡
		writeByte(buf, this.type);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//翻牌编号（0-11）
		this.idx = readInt(buf);
		//类型:0手动，1自动扫荡
		this.type = readByte(buf);
		return true;
	}
	
	/**
	 * get 翻牌编号（0-11）
	 * @return 
	 */
	public int getIdx(){
		return idx;
	}
	
	/**
	 * set 翻牌编号（0-11）
	 */
	public void setIdx(int idx){
		this.idx = idx;
	}
	
	/**
	 * get 类型:0手动，1自动扫荡
	 * @return 
	 */
	public byte getType(){
		return type;
	}
	
	/**
	 * set 类型:0手动，1自动扫荡
	 */
	public void setType(byte type){
		this.type = type;
	}
	
	
	@Override
	public int getId() {
		return 128203;
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
		//翻牌编号（0-11）
		buf.append("idx:" + idx +",");
		//类型:0手动，1自动扫荡
		buf.append("type:" + type +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}