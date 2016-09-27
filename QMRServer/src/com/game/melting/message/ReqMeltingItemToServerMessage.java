package com.game.melting.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 对指定道具进行熔炼操作消息
 */
public class ReqMeltingItemToServerMessage extends Message{

	//要熔炼的道具唯一ID
	private long itemid;
	
	//熔炼石id
	private long metingid;
	
	//类型：0手动，1自动
	private byte type;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//要熔炼的道具唯一ID
		writeLong(buf, this.itemid);
		//熔炼石id
		writeLong(buf, this.metingid);
		//类型：0手动，1自动
		writeByte(buf, this.type);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//要熔炼的道具唯一ID
		this.itemid = readLong(buf);
		//熔炼石id
		this.metingid = readLong(buf);
		//类型：0手动，1自动
		this.type = readByte(buf);
		return true;
	}
	
	/**
	 * get 要熔炼的道具唯一ID
	 * @return 
	 */
	public long getItemid(){
		return itemid;
	}
	
	/**
	 * set 要熔炼的道具唯一ID
	 */
	public void setItemid(long itemid){
		this.itemid = itemid;
	}
	
	/**
	 * get 熔炼石id
	 * @return 
	 */
	public long getMetingid(){
		return metingid;
	}
	
	/**
	 * set 熔炼石id
	 */
	public void setMetingid(long metingid){
		this.metingid = metingid;
	}
	
	/**
	 * get 类型：0手动，1自动
	 * @return 
	 */
	public byte getType(){
		return type;
	}
	
	/**
	 * set 类型：0手动，1自动
	 */
	public void setType(byte type){
		this.type = type;
	}
	
	
	@Override
	public int getId() {
		return 154201;
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
		//要熔炼的道具唯一ID
		buf.append("itemid:" + itemid +",");
		//熔炼石id
		buf.append("metingid:" + metingid +",");
		//类型：0手动，1自动
		buf.append("type:" + type +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}