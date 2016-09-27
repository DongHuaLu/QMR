package com.game.map.message;

import java.util.List;
import java.util.ArrayList;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 周围消失宠物消息
 */
public class ResRoundPetDisappearMessage extends Message{

	//消失宠物列表
	private List<Long> petIds = new ArrayList<Long>();
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//消失宠物列表
		writeShort(buf, petIds.size());
		for (int i = 0; i < petIds.size(); i++) {
			writeLong(buf, petIds.get(i));
		}
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//消失宠物列表
		int petIds_length = readShort(buf);
		for (int i = 0; i < petIds_length; i++) {
			petIds.add(readLong(buf));
		}
		return true;
	}
	
	/**
	 * get 消失宠物列表
	 * @return 
	 */
	public List<Long> getPetIds(){
		return petIds;
	}
	
	/**
	 * set 消失宠物列表
	 */
	public void setPetIds(List<Long> petIds){
		this.petIds = petIds;
	}
	
	
	@Override
	public int getId() {
		return 101108;
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
		//消失宠物列表
		buf.append("petIds:{");
		for (int i = 0; i < petIds.size(); i++) {
			buf.append(petIds.get(i) +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}