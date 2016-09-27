package com.game.pet.message;

import com.game.pet.bean.PetDetailInfo;
import java.util.List;
import java.util.ArrayList;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 宠物列表消息
 */
public class ResPetListMessage extends Message{

	//宠物列表
	private List<PetDetailInfo> pets = new ArrayList<PetDetailInfo>();
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//宠物列表
		writeShort(buf, pets.size());
		for (int i = 0; i < pets.size(); i++) {
			writeBean(buf, pets.get(i));
		}
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//宠物列表
		int pets_length = readShort(buf);
		for (int i = 0; i < pets_length; i++) {
			pets.add((PetDetailInfo)readBean(buf, PetDetailInfo.class));
		}
		return true;
	}
	
	/**
	 * get 宠物列表
	 * @return 
	 */
	public List<PetDetailInfo> getPets(){
		return pets;
	}
	
	/**
	 * set 宠物列表
	 */
	public void setPets(List<PetDetailInfo> pets){
		this.pets = pets;
	}
	
	
	@Override
	public int getId() {
		return 110101;
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
		//宠物列表
		buf.append("pets:{");
		for (int i = 0; i < pets.size(); i++) {
			buf.append(pets.get(i).toString() +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}