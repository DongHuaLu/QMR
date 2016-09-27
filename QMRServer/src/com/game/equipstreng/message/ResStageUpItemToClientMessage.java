package com.game.equipstreng.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 发送升阶结果消息
 */
public class ResStageUpItemToClientMessage extends Message{

	//装备详细信息
	private com.game.equip.bean.EquipInfo equipInfo;
	
	//是否成功：0失败，1成功
	private byte issuccess;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//装备详细信息
		writeBean(buf, this.equipInfo);
		//是否成功：0失败，1成功
		writeByte(buf, this.issuccess);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//装备详细信息
		this.equipInfo = (com.game.equip.bean.EquipInfo)readBean(buf, com.game.equip.bean.EquipInfo.class);
		//是否成功：0失败，1成功
		this.issuccess = readByte(buf);
		return true;
	}
	
	/**
	 * get 装备详细信息
	 * @return 
	 */
	public com.game.equip.bean.EquipInfo getEquipInfo(){
		return equipInfo;
	}
	
	/**
	 * set 装备详细信息
	 */
	public void setEquipInfo(com.game.equip.bean.EquipInfo equipInfo){
		this.equipInfo = equipInfo;
	}
	
	/**
	 * get 是否成功：0失败，1成功
	 * @return 
	 */
	public byte getIssuccess(){
		return issuccess;
	}
	
	/**
	 * set 是否成功：0失败，1成功
	 */
	public void setIssuccess(byte issuccess){
		this.issuccess = issuccess;
	}
	
	
	@Override
	public int getId() {
		return 130102;
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
		//装备详细信息
		if(this.equipInfo!=null) buf.append("equipInfo:" + equipInfo.toString() +",");
		//是否成功：0失败，1成功
		buf.append("issuccess:" + issuccess +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}