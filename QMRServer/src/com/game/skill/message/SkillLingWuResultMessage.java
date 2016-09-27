package com.game.skill.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 技能领悟结果消息
 */
public class SkillLingWuResultMessage extends Message{

	//技能模板Id
	private int skillModelId;
	
	//技能等级
	private int grade;
	
	//1成功 0失败
	private byte issuccess;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//技能模板Id
		writeInt(buf, this.skillModelId);
		//技能等级
		writeInt(buf, this.grade);
		//1成功 0失败
		writeByte(buf, this.issuccess);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//技能模板Id
		this.skillModelId = readInt(buf);
		//技能等级
		this.grade = readInt(buf);
		//1成功 0失败
		this.issuccess = readByte(buf);
		return true;
	}
	
	/**
	 * get 技能模板Id
	 * @return 
	 */
	public int getSkillModelId(){
		return skillModelId;
	}
	
	/**
	 * set 技能模板Id
	 */
	public void setSkillModelId(int skillModelId){
		this.skillModelId = skillModelId;
	}
	
	/**
	 * get 技能等级
	 * @return 
	 */
	public int getGrade(){
		return grade;
	}
	
	/**
	 * set 技能等级
	 */
	public void setGrade(int grade){
		this.grade = grade;
	}
	
	/**
	 * get 1成功 0失败
	 * @return 
	 */
	public byte getIssuccess(){
		return issuccess;
	}
	
	/**
	 * set 1成功 0失败
	 */
	public void setIssuccess(byte issuccess){
		this.issuccess = issuccess;
	}
	
	
	@Override
	public int getId() {
		return 107105;
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
		buf.append("skillModelId:" + skillModelId +",");
		//技能等级
		buf.append("grade:" + grade +",");
		//1成功 0失败
		buf.append("issuccess:" + issuccess +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}