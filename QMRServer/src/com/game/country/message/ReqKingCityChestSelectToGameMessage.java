package com.game.country.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 王城宝箱奖励选择消息
 */
public class ReqKingCityChestSelectToGameMessage extends Message{

	//选择编号
	private int idx;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//选择编号
		writeInt(buf, this.idx);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//选择编号
		this.idx = readInt(buf);
		return true;
	}
	
	/**
	 * get 选择编号
	 * @return 
	 */
	public int getIdx(){
		return idx;
	}
	
	/**
	 * set 选择编号
	 */
	public void setIdx(int idx){
		this.idx = idx;
	}
	
	
	@Override
	public int getId() {
		return 146205;
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
		//选择编号
		buf.append("idx:" + idx +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}