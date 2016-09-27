package com.game.monster.message;

import java.util.List;
import java.util.ArrayList;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 请求BOSS列表消息
 */
public class ResQueryBossListResultMessage extends Message{

	//怪物模型列表
	private List<Integer> monsterModelId = new ArrayList<Integer>();
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//怪物模型列表
		writeShort(buf, monsterModelId.size());
		for (int i = 0; i < monsterModelId.size(); i++) {
			writeInt(buf, monsterModelId.get(i));
		}
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//怪物模型列表
		int monsterModelId_length = readShort(buf);
		for (int i = 0; i < monsterModelId_length; i++) {
			monsterModelId.add(readInt(buf));
		}
		return true;
	}
	
	/**
	 * get 怪物模型列表
	 * @return 
	 */
	public List<Integer> getMonsterModelId(){
		return monsterModelId;
	}
	
	/**
	 * set 怪物模型列表
	 */
	public void setMonsterModelId(List<Integer> monsterModelId){
		this.monsterModelId = monsterModelId;
	}
	
	
	@Override
	public int getId() {
		return 114112;
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
		//怪物模型列表
		buf.append("monsterModelId:{");
		for (int i = 0; i < monsterModelId.size(); i++) {
			buf.append(monsterModelId.get(i) +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}