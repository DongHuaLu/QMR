package com.game.guild.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 提交帮会贡献物品消息
 */
public class ReqGuildSubmitItemToServerMessage extends Message{

	//帮会Id
	private long guildId;
	
	//物品类型
	private byte itemType;
	
	//物品数目
	private int itemNum;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//帮会Id
		writeLong(buf, this.guildId);
		//物品类型
		writeByte(buf, this.itemType);
		//物品数目
		writeInt(buf, this.itemNum);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//帮会Id
		this.guildId = readLong(buf);
		//物品类型
		this.itemType = readByte(buf);
		//物品数目
		this.itemNum = readInt(buf);
		return true;
	}
	
	/**
	 * get 帮会Id
	 * @return 
	 */
	public long getGuildId(){
		return guildId;
	}
	
	/**
	 * set 帮会Id
	 */
	public void setGuildId(long guildId){
		this.guildId = guildId;
	}
	
	/**
	 * get 物品类型
	 * @return 
	 */
	public byte getItemType(){
		return itemType;
	}
	
	/**
	 * set 物品类型
	 */
	public void setItemType(byte itemType){
		this.itemType = itemType;
	}
	
	/**
	 * get 物品数目
	 * @return 
	 */
	public int getItemNum(){
		return itemNum;
	}
	
	/**
	 * set 物品数目
	 */
	public void setItemNum(int itemNum){
		this.itemNum = itemNum;
	}
	
	
	@Override
	public int getId() {
		return 121214;
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
		//帮会Id
		buf.append("guildId:" + guildId +",");
		//物品类型
		buf.append("itemType:" + itemType +",");
		//物品数目
		buf.append("itemNum:" + itemNum +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}