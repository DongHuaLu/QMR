package com.game.player.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 同步世界服务器玩家装备消息
 */
public class ReqSyncPlayerEquipMessage extends Message{

	//角色id
	private long playerId;
	
	//装备部位
	private byte site;
	
	//单件装备
	private String equip;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//角色id
		writeLong(buf, this.playerId);
		//装备部位
		writeByte(buf, this.site);
		//单件装备
		writeString(buf, this.equip);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//角色id
		this.playerId = readLong(buf);
		//装备部位
		this.site = readByte(buf);
		//单件装备
		this.equip = readString(buf);
		return true;
	}
	
	/**
	 * get 角色id
	 * @return 
	 */
	public long getPlayerId(){
		return playerId;
	}
	
	/**
	 * set 角色id
	 */
	public void setPlayerId(long playerId){
		this.playerId = playerId;
	}
	
	/**
	 * get 装备部位
	 * @return 
	 */
	public byte getSite(){
		return site;
	}
	
	/**
	 * set 装备部位
	 */
	public void setSite(byte site){
		this.site = site;
	}
	
	/**
	 * get 单件装备
	 * @return 
	 */
	public String getEquip(){
		return equip;
	}
	
	/**
	 * set 单件装备
	 */
	public void setEquip(String equip){
		this.equip = equip;
	}
	
	
	@Override
	public int getId() {
		return 103325;
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
		//角色id
		buf.append("playerId:" + playerId +",");
		//装备部位
		buf.append("site:" + site +",");
		//单件装备
		if(this.equip!=null) buf.append("equip:" + equip.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}