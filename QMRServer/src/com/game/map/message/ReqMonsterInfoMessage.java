package com.game.map.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 变换方面消息
 */
public class ReqMonsterInfoMessage extends Message{

	//怪物唯一ID
	private long monsterId;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//怪物唯一ID
		writeLong(buf, this.monsterId);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//怪物唯一ID
		this.monsterId = readLong(buf);
		return true;
	}
	
	/**
	 * get 怪物唯一ID
	 * @return 
	 */
	public long getMonsterId(){
		return monsterId;
	}
	
	/**
	 * set 怪物唯一ID
	 */
	public void setMonsterId(long monsterId){
		this.monsterId = monsterId;
	}
	
	
	@Override
	public int getId() {
		return 101214;
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
		//怪物唯一ID
		buf.append("monsterId:" + monsterId +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}