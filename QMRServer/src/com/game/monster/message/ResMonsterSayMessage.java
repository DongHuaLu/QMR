package com.game.monster.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 怪物说话消息
 */
public class ResMonsterSayMessage extends Message{

	//怪物id
	private long monsterId;
	
	//说话内容
	private String saycontent;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//怪物id
		writeLong(buf, this.monsterId);
		//说话内容
		writeString(buf, this.saycontent);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//怪物id
		this.monsterId = readLong(buf);
		//说话内容
		this.saycontent = readString(buf);
		return true;
	}
	
	/**
	 * get 怪物id
	 * @return 
	 */
	public long getMonsterId(){
		return monsterId;
	}
	
	/**
	 * set 怪物id
	 */
	public void setMonsterId(long monsterId){
		this.monsterId = monsterId;
	}
	
	/**
	 * get 说话内容
	 * @return 
	 */
	public String getSaycontent(){
		return saycontent;
	}
	
	/**
	 * set 说话内容
	 */
	public void setSaycontent(String saycontent){
		this.saycontent = saycontent;
	}
	
	
	@Override
	public int getId() {
		return 114110;
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
		//怪物id
		buf.append("monsterId:" + monsterId +",");
		//说话内容
		if(this.saycontent!=null) buf.append("saycontent:" + saycontent.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}