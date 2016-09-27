package com.game.hiddenweapon.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 使用道具学习技能消息
 */
public class ReqHiddenWeaponStudySkillMessage extends Message{

	//道具唯一ID
	private long item;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//道具唯一ID
		writeLong(buf, this.item);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//道具唯一ID
		this.item = readLong(buf);
		return true;
	}
	
	/**
	 * get 道具唯一ID
	 * @return 
	 */
	public long getItem(){
		return item;
	}
	
	/**
	 * set 道具唯一ID
	 */
	public void setItem(long item){
		this.item = item;
	}
	
	
	@Override
	public int getId() {
		return 162205;
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
		//道具唯一ID
		buf.append("item:" + item +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}