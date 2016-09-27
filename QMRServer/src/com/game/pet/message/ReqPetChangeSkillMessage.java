package com.game.pet.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 宠物技能变更消息
 */
public class ReqPetChangeSkillMessage extends Message{

	//宠物Id
	private long petId;
	
	//位置
	private int index;
	
	//技能模型
	private int skillModel;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//宠物Id
		writeLong(buf, this.petId);
		//位置
		writeInt(buf, this.index);
		//技能模型
		writeInt(buf, this.skillModel);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//宠物Id
		this.petId = readLong(buf);
		//位置
		this.index = readInt(buf);
		//技能模型
		this.skillModel = readInt(buf);
		return true;
	}
	
	/**
	 * get 宠物Id
	 * @return 
	 */
	public long getPetId(){
		return petId;
	}
	
	/**
	 * set 宠物Id
	 */
	public void setPetId(long petId){
		this.petId = petId;
	}
	
	/**
	 * get 位置
	 * @return 
	 */
	public int getIndex(){
		return index;
	}
	
	/**
	 * set 位置
	 */
	public void setIndex(int index){
		this.index = index;
	}
	
	/**
	 * get 技能模型
	 * @return 
	 */
	public int getSkillModel(){
		return skillModel;
	}
	
	/**
	 * set 技能模型
	 */
	public void setSkillModel(int skillModel){
		this.skillModel = skillModel;
	}
	
	
	@Override
	public int getId() {
		return 110205;
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
		//宠物Id
		buf.append("petId:" + petId +",");
		//位置
		buf.append("index:" + index +",");
		//技能模型
		buf.append("skillModel:" + skillModel +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}