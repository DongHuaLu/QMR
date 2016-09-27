package com.game.pet.message;

import com.game.pet.bean.PetDetailInfo;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 宠物信息变更消息
 */
public class ResPetChangeMessage extends Message{

	//宠物信息
	private PetDetailInfo pet;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//宠物信息
		writeBean(buf, this.pet);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//宠物信息
		this.pet = (PetDetailInfo)readBean(buf, PetDetailInfo.class);
		return true;
	}
	
	/**
	 * get 宠物信息
	 * @return 
	 */
	public PetDetailInfo getPet(){
		return pet;
	}
	
	/**
	 * set 宠物信息
	 */
	public void setPet(PetDetailInfo pet){
		this.pet = pet;
	}
	
	
	@Override
	public int getId() {
		return 110103;
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
		//宠物信息
		if(this.pet!=null) buf.append("pet:" + pet.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}