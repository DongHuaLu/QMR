package com.game.equip.message;

import com.game.equip.bean.EquipInfo;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 穿着装备信息消息
 */
public class WearEquipItemMessage extends Message{

	//装备信息
	private EquipInfo equip;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//装备信息
		writeBean(buf, this.equip);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//装备信息
		this.equip = (EquipInfo)readBean(buf, EquipInfo.class);
		return true;
	}
	
	/**
	 * get 装备信息
	 * @return 
	 */
	public EquipInfo getEquip(){
		return equip;
	}
	
	/**
	 * set 装备信息
	 */
	public void setEquip(EquipInfo equip){
		this.equip = equip;
	}
	
	
	@Override
	public int getId() {
		return 106101;
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
		//装备信息
		if(this.equip!=null) buf.append("equip:" + equip.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}