package com.game.task.message;

import com.game.task.bean.TargetMonsterInfo;
import java.util.List;
import java.util.ArrayList;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 需要同步的怪物信息消息
 */
public class ResTargetMonsterInfoMessage extends Message{

	//登录时返回的讨伐怪物信息
	private List<TargetMonsterInfo> monsterInfoList = new ArrayList<TargetMonsterInfo>();
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//登录时返回的讨伐怪物信息
		writeShort(buf, monsterInfoList.size());
		for (int i = 0; i < monsterInfoList.size(); i++) {
			writeBean(buf, monsterInfoList.get(i));
		}
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//登录时返回的讨伐怪物信息
		int monsterInfoList_length = readShort(buf);
		for (int i = 0; i < monsterInfoList_length; i++) {
			monsterInfoList.add((TargetMonsterInfo)readBean(buf, TargetMonsterInfo.class));
		}
		return true;
	}
	
	/**
	 * get 登录时返回的讨伐怪物信息
	 * @return 
	 */
	public List<TargetMonsterInfo> getMonsterInfoList(){
		return monsterInfoList;
	}
	
	/**
	 * set 登录时返回的讨伐怪物信息
	 */
	public void setMonsterInfoList(List<TargetMonsterInfo> monsterInfoList){
		this.monsterInfoList = monsterInfoList;
	}
	
	
	@Override
	public int getId() {
		return 120302;
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
		//登录时返回的讨伐怪物信息
		buf.append("monsterInfoList:{");
		for (int i = 0; i < monsterInfoList.size(); i++) {
			buf.append(monsterInfoList.get(i).toString() +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}