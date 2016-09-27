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
 * 周围消失怪物消息
 */
public class ResRoundMonsterDisappearMessage extends Message{

	//消失怪物列表
	private List<Long> monstersIds = new ArrayList<Long>();
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//消失怪物列表
		writeShort(buf, monstersIds.size());
		for (int i = 0; i < monstersIds.size(); i++) {
			writeLong(buf, monstersIds.get(i));
		}
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//消失怪物列表
		int monstersIds_length = readShort(buf);
		for (int i = 0; i < monstersIds_length; i++) {
			monstersIds.add(readLong(buf));
		}
		return true;
	}
	
	/**
	 * get 消失怪物列表
	 * @return 
	 */
	public List<Long> getMonstersIds(){
		return monstersIds;
	}
	
	/**
	 * set 消失怪物列表
	 */
	public void setMonstersIds(List<Long> monstersIds){
		this.monstersIds = monstersIds;
	}
	
	
	@Override
	public int getId() {
		return 101106;
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
		//消失怪物列表
		buf.append("monstersIds:{");
		for (int i = 0; i < monstersIds.size(); i++) {
			buf.append(monstersIds.get(i) +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}