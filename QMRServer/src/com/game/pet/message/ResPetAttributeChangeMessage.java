package com.game.pet.message;

import com.game.pet.bean.PetAttribute;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 宠物属性变更消息
 */
public class ResPetAttributeChangeMessage extends Message{

	//宠物Id
	private long petId;
	
	//变更的属性
	private PetAttribute attributeChange;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//宠物Id
		writeLong(buf, this.petId);
		//变更的属性
		writeBean(buf, this.attributeChange);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//宠物Id
		this.petId = readLong(buf);
		//变更的属性
		this.attributeChange = (PetAttribute)readBean(buf, PetAttribute.class);
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
	 * get 变更的属性
	 * @return 
	 */
	public PetAttribute getAttributeChange(){
		return attributeChange;
	}
	
	/**
	 * set 变更的属性
	 */
	public void setAttributeChange(PetAttribute attributeChange){
		this.attributeChange = attributeChange;
	}
	
	
	@Override
	public int getId() {
		return 110105;
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
		//变更的属性
		if(this.attributeChange!=null) buf.append("attributeChange:" + attributeChange.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}