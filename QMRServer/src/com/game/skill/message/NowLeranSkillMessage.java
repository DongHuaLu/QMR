package com.game.skill.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 当前正在学习的技能消息
 */
public class NowLeranSkillMessage extends Message{

	//技能模板Id 如果没有返回-1
	private int skillModelId;
	
	//剩余时间(秒)
	private int remainingTime;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//技能模板Id 如果没有返回-1
		writeInt(buf, this.skillModelId);
		//剩余时间(秒)
		writeInt(buf, this.remainingTime);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//技能模板Id 如果没有返回-1
		this.skillModelId = readInt(buf);
		//剩余时间(秒)
		this.remainingTime = readInt(buf);
		return true;
	}
	
	/**
	 * get 技能模板Id 如果没有返回-1
	 * @return 
	 */
	public int getSkillModelId(){
		return skillModelId;
	}
	
	/**
	 * set 技能模板Id 如果没有返回-1
	 */
	public void setSkillModelId(int skillModelId){
		this.skillModelId = skillModelId;
	}
	
	/**
	 * get 剩余时间(秒)
	 * @return 
	 */
	public int getRemainingTime(){
		return remainingTime;
	}
	
	/**
	 * set 剩余时间(秒)
	 */
	public void setRemainingTime(int remainingTime){
		this.remainingTime = remainingTime;
	}
	
	
	@Override
	public int getId() {
		return 107106;
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
		//技能模板Id 如果没有返回-1
		buf.append("skillModelId:" + skillModelId +",");
		//剩余时间(秒)
		buf.append("remainingTime:" + remainingTime +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}