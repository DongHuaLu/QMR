package com.game.equipstreng.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 对指定道具进行强化操作消息
 */
public class ReqStrengItemToServerMessage extends Message{

	//要强化的道具唯一ID
	private long itemid;
	
	//类型：0手动，1自动
	private byte type;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//要强化的道具唯一ID
		writeLong(buf, this.itemid);
		//类型：0手动，1自动
		writeByte(buf, this.type);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//要强化的道具唯一ID
		this.itemid = readLong(buf);
		//类型：0手动，1自动
		this.type = readByte(buf);
		return true;
	}
	
	/**
	 * get 要强化的道具唯一ID
	 * @return 
	 */
	public long getItemid(){
		return itemid;
	}
	
	/**
	 * set 要强化的道具唯一ID
	 */
	public void setItemid(long itemid){
		this.itemid = itemid;
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
		return 130201;
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
		//要强化的道具唯一ID
		buf.append("itemid:" + itemid +",");
		//类型：0手动，1自动
		buf.append("type:" + type +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}