package com.game.skill.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 设置默认技能消息
 */
public class SetDefaultSkillMessage extends Message{

	//技能模板Id
	private int defaultSkill;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//技能模板Id
		writeInt(buf, this.defaultSkill);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//技能模板Id
		this.defaultSkill = readInt(buf);
		return true;
	}
	
	/**
	 * get 技能模板Id
	 * @return 
	 */
	public int getDefaultSkill(){
		return defaultSkill;
	}
	
	/**
	 * set 技能模板Id
	 */
	public void setDefaultSkill(int defaultSkill){
		this.defaultSkill = defaultSkill;
	}
	
	
	@Override
	public int getId() {
		return 107203;
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
		//技能模板Id
		buf.append("defaultSkill:" + defaultSkill +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}